/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	
## Module Name					: 	Desk
## Process/Database Object Name	:	Dynamic Desk
## Purpose						:	
## Date of Creation				: 	
## Modification Log				:	Line- 61				
##		Modify Date				: 	12-12-2014
##		Reason	(CR/PRS)		:	Not getting DeptUnitCode from request
##		Modify By				:	Akash Singh
*/
package hisglobal.utility.dynamicdesk.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.data.DynamicDeskDATA;
import hisglobal.utility.dynamicdesk.controller.fb.DynamicDeskFB;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONValue;

public class DynamicDeskUTIL extends ControllerUTIL
{
	// Getting Dynamic Desk ID
	public static boolean getDynamicDeskEssentials(DynamicDeskFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			UserVO userVO = (UserVO)getUserVO(_request);
			HttpSession session = _request.getSession();
			
			UserDeskMenuMasterVO userDeskMstVO = new UserDeskMenuMasterVO();
			//userDeskMstVO.setDeptUnitCode(_request.getParameter("departmentUnitCode"));
			userDeskMstVO.setDeptUnitCode(_fb.getDepartmentUnitCode());
			userDeskMstVO.setDeskType(_fb.getDeskType());
			userDeskMstVO.setWardCode(_fb.getWardCode());
			System.out.println(" Unit Code :"+_request.getParameter("departmentUnitCode")+"  deskType :"+_fb.getDeskType()+"  wardCode :"+_fb.getWardCode());
			//String deskID = DynamicDeskDATA.getDynamicDeskEssentials(userDeskMstVO, userVO);
			Map map = DynamicDeskDATA.getDynamicDeskEssentials(userDeskMstVO, userVO);
			
			String deskID = (String)map.get(DynamicDeskConfig.DYNAMIC_DESK_ID);
			_fb.setDeskID(deskID);
			//WebUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_ID, deskID);
			DeskDetailVO[] deskDtl = (DeskDetailVO[])map.get(DynamicDeskConfig.DYNAMIC_DESK_MENU_DTL);
			map.remove(DynamicDeskConfig.DYNAMIC_DESK_MENU_DTL);
			List<DeskDetailVO> lstTopMenus , lstLeftMenus, lstRightMenus, lstBottomMenus;
			lstTopMenus = new ArrayList<DeskDetailVO>();
			lstLeftMenus = new ArrayList<DeskDetailVO>();
			lstRightMenus = new ArrayList<DeskDetailVO>();
			lstBottomMenus = new ArrayList<DeskDetailVO>();
			if(deskDtl!=null)
			{
				for(DeskDetailVO vo:deskDtl)
				{
					if(vo.getLocation().equals(DynamicDeskConfig.DYNAMIC_DESK_MENU_LOCATION_TOP))
						lstTopMenus.add(vo);
					else if(vo.getLocation().equals(DynamicDeskConfig.DYNAMIC_DESK_MENU_LOCATION_LEFT))
						lstLeftMenus.add(vo);
					else if(vo.getLocation().equals(DynamicDeskConfig.DYNAMIC_DESK_MENU_LOCATION_RIGHT))
						lstRightMenus.add(vo);
					else if(vo.getLocation().equals(DynamicDeskConfig.DYNAMIC_DESK_MENU_LOCATION_BOTTOM))
						lstBottomMenus.add(vo);
				}
			}
			System.out.println("top menu list Size :"+lstTopMenus.size());
			System.out.println("left menu list Size :"+lstLeftMenus.size());
			System.out.println("right menu list Size :"+lstRightMenus.size());
			System.out.println("bottom menu list Size :"+lstBottomMenus.size());
			map.put(DynamicDeskConfig.DYNAMIC_DESK_TOP_MENU_DTL, lstTopMenus);
			map.put(DynamicDeskConfig.DYNAMIC_DESK_LEFT_MENU_DTL, lstLeftMenus);
			map.put(DynamicDeskConfig.DYNAMIC_DESK_RIGHT_MENU_DTL, lstRightMenus);
			map.put(DynamicDeskConfig.DYNAMIC_DESK_BOTTOM_MENU_DTL, lstBottomMenus);
			
			// Desk Name If Not present set Empty String 
			String deskName = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_DESK_NAME);
			if(deskName==null)	deskName = "";
			
			map.put(DynamicDeskConfig.DYNAMIC_DESK_DESK_NAME, deskName);
			
			setMapInSession(map, _request);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
			flag = false;
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
			flag = false;
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return flag;
	}

	// Getting Dynamic Desk Menus
	public static boolean getDynamicDeskMenus(DynamicDeskFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			UserVO userVO = (UserVO)getUserVO(_request);
						
			DeskDetailVO deskDtlVO = new DeskDetailVO();
			deskDtlVO.setDeskId(_fb.getDeskID());
			deskDtlVO.setLocation(_fb.getMenuLocation());			
			
			DeskDetailVO[] menus = DynamicDeskDATA.getDynamicDeskMenus(deskDtlVO, userVO);
						
			WebUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_MENU_DTL, menus);
			//if(menus == null || menus.length ==0)
				//flag=false;
			
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
			flag = false;
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
			flag = false;
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return flag;
	}
	
	//*****  Dynamic Desk Session Helper Methods
	
	/**
	 * This will create Dynamic Desk specific collection if not already there in sesion.
	 * else this Will remove the corresponding items of collection from session. 
	 * @param _request
	 */
	public static void refreshSessionState(HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		HashSet dynamicDeskSpecificItems = (HashSet) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_SPECIFIC_SESSION_ITEMS);
		if (dynamicDeskSpecificItems == null)
		{
			System.out.println("refreshDynamicDeskSessionState : case null");
			Collection DynamicDeskSpecificCollection = new HashSet();
			session.setAttribute(DynamicDeskConfig.DYNAMIC_DESK_SPECIFIC_SESSION_ITEMS, DynamicDeskSpecificCollection);
		}
		else
		{
			System.out.println("refreshDynamicDeskSessionState");
			Iterator it = dynamicDeskSpecificItems.iterator();
			while (it.hasNext())
			{
				System.out.println();
				session.removeAttribute((String) it.next());
			}
		}
	}
	
	/**
	 * This will create Dynamic Desk specific collection if not already there in sesion.
	 * do not remove the corresponding items of collection from session if already exist 
	 * @param _request
	 */
	public static void createSessionState(HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		HashSet dynamicDeskSpecificItems = (HashSet) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_SPECIFIC_SESSION_ITEMS);
		if (dynamicDeskSpecificItems == null)
		{
			System.out.println("refreshDynamicDeskSessionState : case null");
			Collection DynamicDeskSpecificCollection = new HashSet();
			session.setAttribute(DynamicDeskConfig.DYNAMIC_DESK_SPECIFIC_SESSION_ITEMS, DynamicDeskSpecificCollection);
		}
	}
	
	/**
	 * This method sets the map(key(as an attrib name)-value) in session
	 * It also retrievs the dynamic desk specific collection from the session
	 * and adds the key to this collection 
	 */
	public static void setMapInSession(Map _mp, HttpServletRequest request)
	{
		Set stEntry = _mp.entrySet();
		Iterator itr = stEntry.iterator();
		HttpSession session = request.getSession();
		HashSet dynamicDeskSpecificItems = (HashSet) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_SPECIFIC_SESSION_ITEMS);
		//System.out.println(session.isNew());
		while (itr.hasNext())
		{
			Map.Entry entry = (Map.Entry) itr.next();
			String strKey = (String) entry.getKey();
			session.setAttribute(strKey, entry.getValue());
			dynamicDeskSpecificItems.add(strKey);
		}
		session.setAttribute(DynamicDeskConfig.DYNAMIC_DESK_SPECIFIC_SESSION_ITEMS, dynamicDeskSpecificItems);
	}

	/** Sets Attributes in Dynamic Desk Specific Session 
	 * @param _request
	 * @param _attrName
	 * @param _attrValue
	 */
	public static void setAttributeInSession(HttpServletRequest _request, String _attrName, Object _attrValue)
	{
		HttpSession session = _request.getSession();
		session.setAttribute(_attrName, _attrValue);
		HashSet dynamicDeskSpecificItems = (HashSet) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_SPECIFIC_SESSION_ITEMS);
		if (dynamicDeskSpecificItems == null) dynamicDeskSpecificItems = new HashSet();
		dynamicDeskSpecificItems.add(_attrName);
	}
	
	// Getting Desk Essentials
	/*Addead By Akash 05-09-2014*/
	
	/**
	 * @param fb
	 * @param request
	 * @return
	 */
	public static boolean getDeskEssentials(DynamicDeskFB fb, HttpServletRequest request)
	{		
		Status objStatus = new Status();
		boolean flag = true;
		try
		{			
			UserVO userVO = (UserVO)getUserVO(request);
			DeskDetailVO deskDetailVO = new DeskDetailVO();
			deskDetailVO.setDeskType(fb.getDeskType());
			Map<String, Object> mpEssential = DynamicDeskDATA.getNewDeskEssentials(deskDetailVO, userVO);

			DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_TYPE, fb.getDeskType());
			DynamicDeskUTIL.setMapInSession(mpEssential, request);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
		return flag;
	}

	public static String getDeskMenusList(DynamicDeskFB objFB, String sessionKey, HttpServletRequest request, HttpServletResponse response) 
	{
		String strAjaxRes = new String();
		HttpSession objSession = request.getSession();
		try
		{
			List<DeskDetailVO> lstMenus = (List<DeskDetailVO>)objSession.getAttribute(sessionKey);
			Map<String,Object> mp = new HashMap<String, Object>();

			List<Map<String,String>> lstDatamap = new ArrayList<Map<String,String>>();

			//StringBuffer sbAjaxRes = new StringBuffer();
			//sbAjaxRes.append("[");
			if(lstMenus!=null && lstMenus.size()>0)
			{
				for(DeskDetailVO vo : lstMenus)
				{
					Map<String,String> mpCols = new LinkedHashMap<String, String>();
					mpCols.put("deskId", (vo.getDeskId()==null)?"":vo.getDeskId());
					mpCols.put("deskMenuId", (vo.getDeskMenuId()==null)?"":vo.getDeskMenuId());
					mpCols.put("location", (vo.getLocation()==null)?"":vo.getLocation());
					mpCols.put("displayOrder", (vo.getDisplayOrder()==null)?"":vo.getDisplayOrder());
					mpCols.put("userDeskMenuName", (vo.getUserDeskMenuName()==null)?"":vo.getUserDeskMenuName());
					mpCols.put("color", (vo.getColor()==null)?"":vo.getColor());
					mpCols.put("deskUrl", (vo.getDeskUrl()==null)?"":vo.getDeskUrl());
					mpCols.put("deskMenuName", (vo.getDeskMenuName()==null)?"":vo.getDeskMenuName());
					mpCols.put("deskType", (vo.getDeskType()==null)?"":vo.getDeskType());
					mpCols.put("usabilityFlag", (vo.getUsabilityFlag()==null)?"":vo.getUsabilityFlag());
					mpCols.put("deskMenuType", (vo.getDeskMenuType()==null)?"":vo.getDeskMenuType());
					mpCols.put("deskUrlType", (vo.getDeskUrlType()==null)?"":vo.getDeskUrlType());
					mpCols.put("isDefaultProfile", (vo.getIsDefaultProfile()==null)?"":vo.getIsDefaultProfile());
					mpCols.put("profileOrder", (vo.getProfileOrder()==null)?"":vo.getProfileOrder());
					mpCols.put("isLoginBound", (vo.getIsLoginBound()==null)?"":vo.getIsLoginBound());
					mpCols.put("dutyRoleId", (vo.getDutyRoleId()==null)?"":vo.getDutyRoleId());
					mpCols.put("isTemplateBased", (vo.getIsTemplateBased()==null)?"":vo.getIsTemplateBased());
					mpCols.put("templateCategory", (vo.getTemplateCategory()==null)?"":vo.getTemplateCategory());
					mpCols.put("deskMenuImg", (vo.getDeskMenuImg()==null)?"":vo.getDeskMenuImg());
					mpCols.put("menuExtUrl", (vo.getMenuExtUrl()==null)?"":vo.getMenuExtUrl());
					lstDatamap.add(mpCols);
					/*sbAjaxRes.append("{");
					sbAjaxRes.append("\"deskId\"");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDeskId());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("deskMenuId");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDeskMenuId());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("llocation");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getLocation());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("displayOrder");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDisplayOrder());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("userDeskMenuName");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getUserDeskMenuName());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("color");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getColor());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("deskUrl");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDeskUrl());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("deskMenuName");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDeskMenuName());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("deskType");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(vo.getDeskType());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("usabilityFlag");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append((vo.getUsabilityFlag()==null)?"":vo.getUsabilityFlag());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("deskMenuType");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append((vo.getDeskMenuType()==null)?"":vo.getDeskMenuType());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("isDefaultProfile");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append((vo.getIsDefaultProfile()==null)?"":vo.getIsDefaultProfile());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("profileOrder");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append((vo.getProfileOrder()==null)?"":vo.getProfileOrder());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("isLoginBound");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append((vo.getIsLoginBound()==null)?"":vo.getIsLoginBound());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("dutyRoleId");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append((vo.getDutyRoleId()==null)?"":vo.getDutyRoleId());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("isTemplateBased");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append((vo.getIsTemplateBased()==null)?"":vo.getIsTemplateBased());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("templateCategory");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append((vo.getTemplateCategory()==null)?"":vo.getTemplateCategory());sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("deskMenuImg");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append((vo.getDeskMenuImg()==null)?"":vo.getDeskMenuImg());sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");*/
					
				}
				//if(lstMenus.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
			}
			//sbAjaxRes.append("]");
			
			mp.put("list",lstDatamap);
			strAjaxRes = JSONValue.toJSONString(mp);
			System.out.println(strAjaxRes);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return strAjaxRes;
	}

	public static void writeResponse(HttpServletResponse resp, String output)
	{
		try
		{
			resp.reset();
			//resp.setContentType("text/xml");
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
			resp.getWriter().write(output);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
