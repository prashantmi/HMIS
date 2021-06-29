package mrd.masters.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.EprTabAccessDtlVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.masters.controller.data.EprTabAccessMasterDATA;
import mrd.masters.controller.fb.EprTabAccessMasterFB;

public class EprTabAccessMasterUTL extends ControllerUTIL
{
	
	public static void getEssentialForTabAccessMst(EprTabAccessMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		//List allDepartmentUnitList=null;
		Map essentialMap=null;
		try
		{
			essentialMap=EprTabAccessMasterDATA.getEssentialForTabAccessMst(getUserVO(request));
			//WebUTIL.setAttributeInSession(request, MrdConfig.ESSENTIAL_DEPARTMENT_UNIT_LIST, allDepartmentUnitList);
			WebUTIL.setMapInSession(essentialMap, request);
		
		}
		catch(HisRecordNotFoundException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR," ","");			
		}
		catch(HisDataAccessException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
		}		
		catch(Exception e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in UTIL","");
		}		
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	
	public static void getTabAccessPolicy(EprTabAccessMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		List<EprTabAccessDtlVO> tabAccessDtlMstVOList=null;
		List<EprTabAccessDtlVO> tabAccessUserWiseList=null;
		String tabIdAccess="";
		List<Entry> addedUserIdList=new ArrayList<Entry>();
		Map map=null;
		Map userTabIdMap=new HashMap();
		try
		{
			EprTabAccessDtlVO eprTabAccessDtlVO=new EprTabAccessDtlVO();
			eprTabAccessDtlVO.setDepartmentUnitCode(fb.getDepartmentUnitCode());
			eprTabAccessDtlVO.setPolicyType(fb.getPolicyType());
			eprTabAccessDtlVO.setToDepartmentUnitCode(fb.getToDepartmentUnitCode());
			if(!fb.getPolicyType().equals("-1")){
				map=EprTabAccessMasterDATA.getTabAccessPolicy(eprTabAccessDtlVO,getUserVO(request));
				//WebUTIL.setAttributeInSession(request, MrdConfig.ESSENTIAL_DEPARTMENT_UNIT_LIST, allDepartmentUnitList);
				//WebUTIL.setMapInSession(essentialMap, request);
				if(map!=null){
					tabAccessDtlMstVOList=(List)map.get(MrdConfig.EPR_TAB_ACCESS_DTL_VO_LIST);
					Iterator itr=tabAccessDtlMstVOList.iterator();
					int i=0;
					while (itr.hasNext()) {
						EprTabAccessDtlVO eprTabAccessVO = (EprTabAccessDtlVO) itr.next();
						HelperMethods.setNullToEmpty(eprTabAccessVO);
						if(i==0)
							tabIdAccess=eprTabAccessVO.getTabId()+"#"+eprTabAccessVO.getAccessType()+"#"+eprTabAccessVO.getUserLevel();
						else
							tabIdAccess=tabIdAccess+"@"+eprTabAccessVO.getTabId()+"#"+eprTabAccessVO.getAccessType()+"#"+eprTabAccessVO.getUserLevel();
						i++;
					}
					
					tabAccessUserWiseList=(List)map.get(MrdConfig.EPR_TAB_ACCESS_USER_WISE_LIST);
					for(i=0;i<tabAccessUserWiseList.size();i++){
						if(userTabIdMap.containsKey(tabAccessUserWiseList.get(i).getTabId())){
							addedUserIdList=(List)userTabIdMap.get(tabAccessUserWiseList.get(i).getTabId());
							Entry entry=new Entry();
							entry.setValue(tabAccessUserWiseList.get(i).getUserId());
							entry.setLabel(tabAccessUserWiseList.get(i).getUserName());
							addedUserIdList.add(entry);
							userTabIdMap.put(tabAccessUserWiseList.get(i).getTabId(),addedUserIdList);
						}
						else{
							addedUserIdList=new ArrayList<Entry>();
							Entry entry=new Entry();
							entry.setValue(tabAccessUserWiseList.get(i).getUserId());
							entry.setLabel(tabAccessUserWiseList.get(i).getUserName());
							addedUserIdList.add(entry);
							userTabIdMap.put(tabAccessUserWiseList.get(i).getTabId(),addedUserIdList);
						}
					}
				}
				WebUTIL.setAttributeInSession(request, MrdConfig.EPR_ACCESS_TABID_USERID_MAP, userTabIdMap);
				WebUTIL.setAttributeInSession(request, MrdConfig.EPR_TAB_ACCESS_DTL_VO_LIST, tabAccessDtlMstVOList);
				//System.out.println("tabIdAccess ="+tabIdAccess);
				fb.setTabIdAccess(tabIdAccess);
				objStatus.add(Status.TRANSINPROCESS);
			}
		}
		catch(HisRecordNotFoundException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR," ","");			
		}
		catch(HisDataAccessException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
		}		
		catch(Exception e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in UTIL","");
		}		
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}

	
	
	public static void getUsers(EprTabAccessMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		Map essentialMap=null;
		HttpSession session=request.getSession();
		List addedUserIdList=null;
		List allUserIdList=null;
		List notAddedUserIdList=null;
		try
		{
			String tabId=request.getParameter("selectedTabId");
			fb.setSelectedIndex(request.getParameter("selectedIndex"));
			fb.setSelectedtabId(tabId);
			Map userTabIdMap=(Map)session.getAttribute(MrdConfig.EPR_ACCESS_TABID_USERID_MAP);
			if(userTabIdMap!=null){
				addedUserIdList = (List)userTabIdMap.get(tabId);
			}
			essentialMap=EprTabAccessMasterDATA.getUsersForTabAccess(getUserVO(request));
			if(addedUserIdList!=null && addedUserIdList.size()>0)
				essentialMap.put(MrdConfig.EPR_ACCESS_ADDED_USERID_LIST, addedUserIdList);
			else{
				essentialMap.put(MrdConfig.EPR_ACCESS_ADDED_USERID_LIST, new ArrayList());
			}
			notAddedUserIdList=(List)essentialMap.get(MrdConfig.EPR_ACCESS_NOT_ADDED_USERID_LIST);
			allUserIdList=notAddedUserIdList;
			WebUTIL.setAttributeInSession(request, MrdConfig.EPR_ACCESS_ALL_USERID_LIST, allUserIdList);
			List list=removeFromList(notAddedUserIdList,addedUserIdList);
			essentialMap.put(MrdConfig.EPR_ACCESS_NOT_ADDED_USERID_LIST, list);
			WebUTIL.setMapInSession(essentialMap, request);
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch(HisRecordNotFoundException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR," ","");			
		}
		catch(HisDataAccessException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
		}		
		catch(Exception e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in UTIL","");
		}		
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
		
	public static void saveUsersToSession(EprTabAccessMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		String tabId=fb.getSelectedtabId();
		Map userTabIdMap=new HashMap();
		HttpSession session=request.getSession();
		List userIdList=null;
		List notAddedUserIdList=null;
		//List addedUserIdList=null;
		try
		{
			userTabIdMap=(Map)session.getAttribute(MrdConfig.EPR_ACCESS_TABID_USERID_MAP);
			notAddedUserIdList=(List)session.getAttribute(MrdConfig.EPR_ACCESS_NOT_ADDED_USERID_LIST);
			if(userTabIdMap==null)
				userTabIdMap=new HashMap();
			userIdList=new ArrayList();
			if(fb.getSelectedUsers()!=null){
				String userId[]=fb.getSelectedUsers().split("#");
				//addedUserIdList=new ArrayList();
				for (int i = 0; i < userId.length; i++) {
					//userIdList.add(userId[i]);
					Entry entry=new Entry();
					entry.setValue(userId[i]);
					if(notAddedUserIdList.contains(entry)){
						//System.out.println("index = "+ notAddedUserIdList.indexOf(entry));
						userIdList.add(notAddedUserIdList.get(notAddedUserIdList.indexOf(entry)));
						notAddedUserIdList.remove(entry);
					}
				}
			}
			
			userTabIdMap.put(tabId, userIdList);
			WebUTIL.setAttributeInSession(request, MrdConfig.EPR_ACCESS_TABID_USERID_MAP, userTabIdMap);
			WebUTIL.setAttributeInSession(request, MrdConfig.EPR_ACCESS_NOT_ADDED_USERID_LIST, notAddedUserIdList);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(Exception e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in UTIL","");
		}		
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	

	public static void saveTabAccessPolicy(EprTabAccessMasterFB fb,
			HttpServletRequest _request) {
		Status objStatus=new Status();
		List<EprTabAccessDtlVO> previousTabAccessDtlVOList=null;
		List<EprTabAccessDtlVO> insertTabAccessDtlVOList=null;
		List<EprTabAccessDtlVO> updateTabAccessDtlVOList=null;
		//boolean flag=false;
		//boolean isInsert=true;
		try
		{
			EprTabAccessDtlVO eprTabAccessDtlVO=null;
			String tabIdArray[]=fb.getTabId();
			//String previousTabIdArray[]=fb.getTabIdAccess().split("@");
			//String tabIdInsert="";
			//String tabIdUpdate="";
		/*	String userLevelArray[]=new String [tabIdArray.length];
			for(int i=0;i<tabIdArray.length;i++){
				if(fb.getAccessType()[i].equals(MrdConfig.EPR_RECORD_ACCESS_UNIT_BOUND)){
					userLevelArray[i]="";
				}
				else{
					userLevelArray[i]="";
				}
			}*/
			
			previousTabAccessDtlVOList=(List)_request.getSession().getAttribute(MrdConfig.EPR_TAB_ACCESS_DTL_VO_LIST);
			if(previousTabAccessDtlVOList==null) previousTabAccessDtlVOList=new ArrayList<EprTabAccessDtlVO>();
			if(tabIdArray!=null){
				insertTabAccessDtlVOList=new ArrayList<EprTabAccessDtlVO>();
				updateTabAccessDtlVOList=new ArrayList<EprTabAccessDtlVO>();
				for(int i=0;i<tabIdArray.length;i++){
					eprTabAccessDtlVO=new EprTabAccessDtlVO();
					eprTabAccessDtlVO.setDepartmentUnitCode(fb.getDepartmentUnitCode());
					eprTabAccessDtlVO.setToDepartmentUnitCode(fb.getToDepartmentUnitCode());
					eprTabAccessDtlVO.setPolicyType(fb.getPolicyType());
					eprTabAccessDtlVO.setTabId(tabIdArray[i].split("#")[0]);
					eprTabAccessDtlVO.setAccessType(fb.getAccessType()[i]);
					if(eprTabAccessDtlVO.getAccessType().equals(MrdConfig.EPR_RECORD_ACCESS_UNIT_BOUND))
						eprTabAccessDtlVO.setUserLevel(fb.getUserLevel()[Integer.parseInt(tabIdArray[i].split("#")[1])]);
					else
						eprTabAccessDtlVO.setUserLevel("0");
					if(!previousTabAccessDtlVOList.contains(eprTabAccessDtlVO))
						insertTabAccessDtlVOList.add(eprTabAccessDtlVO);
				}
				//for Update
				for(int i=0;i<tabIdArray.length;i++){
					eprTabAccessDtlVO=new EprTabAccessDtlVO();
					eprTabAccessDtlVO.setDepartmentUnitCode(fb.getDepartmentUnitCode());
					eprTabAccessDtlVO.setToDepartmentUnitCode(fb.getToDepartmentUnitCode());
					eprTabAccessDtlVO.setPolicyType(fb.getPolicyType());
					eprTabAccessDtlVO.setTabId(tabIdArray[i].split("#")[0]);
					eprTabAccessDtlVO.setAccessType(fb.getAccessType()[i]);
					if(eprTabAccessDtlVO.getAccessType().equals(MrdConfig.EPR_RECORD_ACCESS_UNIT_BOUND))
						eprTabAccessDtlVO.setUserLevel(fb.getUserLevel()[Integer.parseInt(tabIdArray[i].split("#")[1])]);
					else
						eprTabAccessDtlVO.setUserLevel("0");
					for(int j=0;j<previousTabAccessDtlVOList.size();j++){
						if(previousTabAccessDtlVOList.get(j).getTabId().equals(eprTabAccessDtlVO.getTabId())
								&& !previousTabAccessDtlVOList.get(j).equals(eprTabAccessDtlVO)){
							updateTabAccessDtlVOList.add(eprTabAccessDtlVO);
							
						}else
						if(!previousTabAccessDtlVOList.get(j).getTabId().equals(eprTabAccessDtlVO.getTabId())
							&&	!updateTabAccessDtlVOList.contains(eprTabAccessDtlVO)){
							updateTabAccessDtlVOList.add(eprTabAccessDtlVO);
							
						}
					}
				}
			}	
				/* 1//for insert
				for(int i=0;i<tabIdArray.length;i++){
					for(int j=0;j<previousTabIdArray.length;j++){
						//if(!previousTabIdArray[j].equals(""))
							if(tabIdArray[i].equals(previousTabIdArray[j].split("#")[0])){
								if(!fb.getAccessType()[i].equals(previousTabIdArray[j].split("#")[1])){
									flag=true;
									//tabIdInsert=tabIdInsert+"@"+tabIdArray[i]+"#"+fb.getAccessType()[i];
									break;
								}else{
									flag=false;
									break;
								}
								
							}
							else{
								flag=true;
								//tabIdInsert=tabIdInsert+"@"+tabIdArray[i]+"#"+fb.getAccessType()[i];
							}
						
					}
					if(flag)
						tabIdInsert=tabIdInsert+"@"+tabIdArray[i]+"#"+fb.getAccessType()[i];
				}
				tabIdInsert=tabIdInsert.replaceFirst("@", "");
				flag=false;
				//for update
				for(int j=0;j<previousTabIdArray.length;j++){
					if(!previousTabIdArray[j].equals(""))
					for(int i=0;i<tabIdArray.length;i++){
						if(previousTabIdArray[j].split("#")[0].equals(tabIdArray[i])){
							if(!fb.getAccessType()[i].equals(previousTabIdArray[j].split("#")[1])){
								//tabIdUpdate=tabIdUpdate+"@"+previousTabIdArray[j].split("#")[0]+"#"+previousTabIdArray[j].split("#")[1];
								flag=true;
								break;
							}else {
								flag=false;
								break;
							}
						}
						else{
							flag=true;
							//tabIdUpdate=tabIdUpdate+"@"+previousTabIdArray[j].split("#")[0]+"#"+previousTabIdArray[j].split("#")[1];
						}
						
					}
					if(flag)
						tabIdUpdate=tabIdUpdate+"@"+previousTabIdArray[j].split("#")[0]+"#"+previousTabIdArray[j].split("#")[1];
						
				}
				tabIdUpdate=tabIdUpdate.replaceFirst("@", "");
			}
			
			insertTabAccessDtlVOList=new ArrayList<EprTabAccessDtlVO>();
			String tabIdAcessArray[]=tabIdInsert.split("@");
			if(!tabIdInsert.equals(""))
			for(int i=0;i<tabIdAcessArray.length;i++){
				eprTabAccessDtlVO=new EprTabAccessDtlVO();
				eprTabAccessDtlVO.setDepartmentUnitCode(fb.getDepartmentUnitCode());
				eprTabAccessDtlVO.setToDepartmentUnitCode(fb.getToDepartmentUnitCode());
				eprTabAccessDtlVO.setPolicyType(fb.getPolicyType());
				eprTabAccessDtlVO.setTabId(tabIdAcessArray[i].split("#")[0]);
				eprTabAccessDtlVO.setAccessType(tabIdAcessArray[i].split("#")[1]);
				if(eprTabAccessDtlVO.getAccessType().equals(MrdConfig.EPR_RECORD_ACCESS_UNIT_BOUND))
					eprTabAccessDtlVO.setUserLevel(fb.getUserLevel()[i]);
				insertTabAccessDtlVOList.add(eprTabAccessDtlVO);
			}
			
			updateTabAccessDtlVOList=new ArrayList<EprTabAccessDtlVO>();
			tabIdAcessArray=tabIdUpdate.split("@");
			
			if(!tabIdUpdate.equals(""))
			for(int i=0;i<tabIdAcessArray.length;i++){
				eprTabAccessDtlVO=new EprTabAccessDtlVO();
				eprTabAccessDtlVO.setDepartmentUnitCode(fb.getDepartmentUnitCode());
				eprTabAccessDtlVO.setToDepartmentUnitCode(fb.getToDepartmentUnitCode());
				eprTabAccessDtlVO.setPolicyType(fb.getPolicyType());
				eprTabAccessDtlVO.setTabId(tabIdAcessArray[i].split("#")[0]);
				eprTabAccessDtlVO.setAccessType(tabIdAcessArray[i].split("#")[1]);
				updateTabAccessDtlVOList.add(eprTabAccessDtlVO);
			}*/
			
			/*if(tabIdArray!=null){
				insertTabAccessDtlVOList=new ArrayList<EprTabAccessDtlVO>();
				updateTabAccessDtlVOList=new ArrayList<EprTabAccessDtlVO>();
				
				for (int i=0;i<tabIdArray.length;i++) {
					eprTabAccessDtlVO=new EprTabAccessDtlVO();
					eprTabAccessDtlVO.setDepartmentUnitCode(fb.getDepartmentUnitCode());
					eprTabAccessDtlVO.setPolicyType(fb.getPolicyType());
					eprTabAccessDtlVO.setTabId(tabIdArray[i]);
					eprTabAccessDtlVO.setAccessType(fb.getAccessType()[i]);
					int j=0;
					for(j=0;j<previousTabIdArray.length;j++){
						if(!previousTabIdArray[j].equals(""))
						if(eprTabAccessDtlVO.getTabId().equals(previousTabIdArray[j].split("#")[0])){
							flag=true;
							if(eprTabAccessDtlVO.getAccessType().equals(previousTabIdArray[j].split("#")[1])){
								isInsert=false;
								break;
							}	
						}
						if(eprTabAccessDtlVO.getTabId().equals(previousTabIdArray[j].split("#")[0])){
							if(!eprTabAccessDtlVO.getAccessType().equals(previousTabIdArray[j].split("#")[1])){
								flag=true;
								break;
							}
						}
						else{
							flag=false;
						}		
					}
								
				
					if(flag){
						EprTabAccessDtlVO vo=eprTabAccessDtlVO;
						vo.setTabId(previousTabIdArray[j].split("#")[0]);
						updateTabAccessDtlVOList.add(vo);
						insertTabAccessDtlVOList.add(eprTabAccessDtlVO);
					}else if(isInsert){
						insertTabAccessDtlVOList.add(eprTabAccessDtlVO);
					}	
				}
			
			}*/
			
			List userList=(List)_request.getSession().getAttribute(MrdConfig.EPR_ACCESS_ALL_USERID_LIST);
			Map userTabIdMap=(Map)_request.getSession().getAttribute(MrdConfig.EPR_ACCESS_TABID_USERID_MAP);
			for(int i=0;i<tabIdArray.length;i++){
				if(fb.getSelectAllUser()!=null && fb.getSelectAllUser().equals("1")){
					userTabIdMap.put(tabIdArray[i].split("#")[0], userList);
				}
				if(fb.getAccessType()[i].equals(MrdConfig.EPR_RECORD_ACCESS_UNIT_BOUND)
						&& userTabIdMap.containsKey(tabIdArray[i].split("#")[0])){
					userTabIdMap.remove(tabIdArray[i].split("#")[0]);
				}
			}
									
			eprTabAccessDtlVO=new EprTabAccessDtlVO();
			eprTabAccessDtlVO.setDepartmentUnitCode(fb.getDepartmentUnitCode());
			eprTabAccessDtlVO.setToDepartmentUnitCode(fb.getToDepartmentUnitCode());
			eprTabAccessDtlVO.setPolicyType(fb.getPolicyType());
			
			EprTabAccessMasterDATA.saveTabAccessPolicy(insertTabAccessDtlVOList,updateTabAccessDtlVOList,userTabIdMap,eprTabAccessDtlVO,getUserVO(_request));
			
			objStatus.add(Status.DONE,"","Record Saved Successfully");
			
		}
		catch(HisRecordNotFoundException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR," ","");			
		}
		catch(HisDataAccessException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
		}		
		catch(Exception e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in UTIL","");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	
	public static List removeFromList(List <Entry> list1,List <Entry> list2){
		List list=list1;
		if(list!=null && list2!=null)
		for(int i=0;i<list2.size();i++){
			if(list.contains(list2.get(i))){
				list.remove(list2.get(i));
			}
		}
		return list;
	}
	
}
