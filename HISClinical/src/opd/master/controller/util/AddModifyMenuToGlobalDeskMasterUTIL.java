package opd.master.controller.util;

/**
 * @author  CDAC
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.AddModifyMenuToGlobalDeskMasterDATA;
import opd.master.controller.fb.DeskMasterFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.UserVO;

public class AddModifyMenuToGlobalDeskMasterUTIL extends ControllerUTIL 
{
	//* Setting Desk Type List
	public static void setDesktype(DeskMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			setSysdate(_request);
			
			if(_fb.getControls()[0]!=null && _fb.getControls()[0]!="")
			{
				_fb.setDeskType(_fb.getControls()[0]);
			}
			
			List listDeskType=AddModifyMenuToGlobalDeskMasterDATA.getDeskType(userVO);
			WebUTIL.getSession(_request).setAttribute(OpdConfig.ESSENTIAL_BO_OPTION_DESK_TYPE_LIST,listDeskType);			
			for(int i=0;i<listDeskType.size();i++)
			{
				Entry entobj=(Entry)listDeskType.get(i);
				if(entobj.getValue().equals(_fb.getDeskType()))
				{
					_fb.setDeskTypeDesc(entobj.getLabel());
					break;
				}
			}
			//int deskType=Integer.parseInt(_fb.getDeskType());
			//_fb.setDeskTypeDesc(DynamicDeskConfig.DESK_TYPES[deskType]);	// Setting the Desk Type Description
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	//* Setting Essentials
	public static void setEssential(DeskMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		Map mp=new HashMap();

		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			mp=AddModifyMenuToGlobalDeskMasterDATA.getEssentials(_fb.getDeskType(),userVO);
			WebUTIL.setMapInSession(mp,_request);

			//String deskTypeDesc=AddModifyMenuToGlobalDeskMasterDATA.getDeskTypeDesc(_fb.getDeskType(),userVO);
			//_fb.setDeskTypeDesc(deskTypeDesc);
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
	}

	
	// Fetch Record from Desk Master & Desk Detail
	//* Getting Menu From Request
	public static String menuNameFromReq(HttpServletRequest _req,String menuId)
	{
		HttpSession session = _req.getSession();
		List lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_MENUS);
		
		for(int i=0;i<lst.size();i++)
		{
			Entry obj=(Entry)lst.get(i);
			String[] menuIdsplit=obj.getValue().split("@");
			if(menuIdsplit.length>1)
				if(menuIdsplit[0].equals(menuId))
					return obj.getLabel();
			else if(obj.getValue().equals(menuId))
				return obj.getLabel();
		}
		return "";
	}

	//* Deleting Menu From Request
	public static void deleteMenuFromReq(HttpServletRequest _req,String menuId)
	{
		HttpSession session = _req.getSession();
		List lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_MENUS);
		
		for(int i=0;i<lst.size();i++)
		{
			Entry obj=(Entry)lst.get(i);
			String[] menuIdsplit=obj.getValue().split("@");
			if(menuIdsplit.length>1)
				if(menuIdsplit[0].equals(menuId))
					lst.remove(i);
			else if(obj.getValue().equals(menuId))
				lst.remove(i);
		}
	}
	
	//* Getting All Menus of Desk
	public static boolean FetchMenusOfDesk(DeskMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			
			// Setting Selected Desk Id from 'chk'
			String chk=_fb.getChk()[0].replace("^","@");
			String[] concatid=chk.split("@");
			_fb.setDeskId(concatid[0]);
            System.out.print(_fb.getDeskId());
			DeskMasterVO voDeskMst;
			voDeskMst = AddModifyMenuToGlobalDeskMasterDATA.fetchDeskVOByGlobalDeskId(_fb.getDeskId(),userVO);

			_fb.setDeskName(voDeskMst.getDeskName());
			_fb.setDeskType(voDeskMst.getDeskType());
			_fb.setIsDefault(voDeskMst.getIsDefault());
			_fb.setIsActive(voDeskMst.getIsValid());
			
		/*	int defaultValue=Integer.parseInt(_fb.getIsDefault());
				
			if(defaultValue==1)
			{
				_fb.setCheckForDefault("0");				
			}
			else
			{
				AddModifyMenuToDeskMasterUTIL.getisDefault(_fb,_request);		
			}
			*/
			
			
			
			String deskTypeDesc=AddModifyMenuToGlobalDeskMasterDATA.getDeskTypeDesc(_fb.getDeskType(),userVO);
			_fb.setDeskTypeDesc(deskTypeDesc);
			//int deskType=Integer.parseInt(_fb.getDeskType());
			//_fb.setDeskTypeDesc(DynamicDeskConfig.DESK_TYPES[deskType]);	//Setting the Desk Type Description 

			Map mp=AddModifyMenuToGlobalDeskMasterDATA.getEssentials(_fb.getDeskType(),userVO);
			WebUTIL.setMapInSession(mp,_request);
			WebUTIL.populate(_fb, mp);
			voDeskMst.setDeskId(_fb.getDeskId());		
			
			// Getting All Desk Menus			
			DeskDetailVO[] voDeskMenuList = AddModifyMenuToGlobalDeskMasterDATA.getMenuListByGlobalDeskId(voDeskMst,userVO);
			ArrayList leftMenuList = new ArrayList<Entry>();
			ArrayList rightMenuList = new ArrayList<Entry>();
			ArrayList topMenuList = new ArrayList<Entry>();
			ArrayList bottomMenuList = new ArrayList<Entry>();
			for(DeskDetailVO vo : voDeskMenuList)
			{
				if(vo.getIsLoginBound()==null || vo.getIsLoginBound().trim().equals(""))	vo.setIsLoginBound(OpdConfig.NO);
				if(vo.getDutyRoleId()==null || vo.getDutyRoleId().trim().equals(""))		vo.setDutyRoleId("-1");
				
				// Left Menu
				if(vo.getLocation().equalsIgnoreCase(OpdConfig.DESK_LOCATION_LEFT))
				{
					leftMenuList.add(new Entry(menuNameFromReq(_request,vo.getDeskMenuId()),vo.getDeskMenuId()+"@"+vo.getColor()+"@"+vo.getUserDeskMenuName()+"@"+vo.getIsLoginBound()+"@"+vo.getDutyRoleId()));
					deleteMenuFromReq(_request,vo.getDeskMenuId());
				}
	
				// Right Menu
				if(vo.getLocation().equalsIgnoreCase(OpdConfig.DESK_LOCATION_RIGHT))
				{
					rightMenuList.add(new Entry(menuNameFromReq(_request,vo.getDeskMenuId()),vo.getDeskMenuId()+"@"+vo.getColor()+"@"+vo.getUserDeskMenuName()+"@"+vo.getIsLoginBound()+"@"+vo.getDutyRoleId()));
					deleteMenuFromReq(_request,vo.getDeskMenuId());
				}

				// Top Menu
				if(vo.getLocation().equalsIgnoreCase(OpdConfig.DESK_LOCATION_TOP))
				{
					topMenuList.add(new Entry(menuNameFromReq(_request,vo.getDeskMenuId()),vo.getDeskMenuId()+"@"+vo.getColor()+"@"+vo.getUserDeskMenuName()+"@"+vo.getIsLoginBound()+"@"+vo.getDutyRoleId()));
					deleteMenuFromReq(_request,vo.getDeskMenuId());
				}
	
				// Bottom Menu
				if(vo.getLocation().equalsIgnoreCase(OpdConfig.DESK_LOCATION_BOTTOM))
				{
					bottomMenuList.add(new Entry(menuNameFromReq(_request,vo.getDeskMenuId()),vo.getDeskMenuId()+"@"+vo.getColor()+"@"+vo.getUserDeskMenuName()+"@"+vo.getIsLoginBound()+"@"+vo.getDutyRoleId()));
					deleteMenuFromReq(_request,vo.getDeskMenuId());
				}
			}
			_fb.setModLeftMenuList(leftMenuList);
			_fb.setModRightMenuList(rightMenuList);
			_fb.setModTopMenuList(topMenuList);
			_fb.setModBottomMenuList(bottomMenuList);
			
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
		return true;
	}	
	
	//  Save Records to Desk Master & Desk Detail 
	//* Getting Desk Detail VO
	public static DeskDetailVO getDeskDetailVO(String[] menuList,int i,DeskMasterFB _fb,String loc,HttpServletRequest _req)
	{
		DeskDetailVO objVO=new DeskDetailVO();

		objVO.setDeskId(_fb.getDeskId());
		
		String split[]=menuList[i].split("@");
		objVO.setDeskMenuId(split[0]);
		objVO.setColor(split[1]);
		objVO.setUserDeskMenuName(split[2]);
		objVO.setIsLoginBound(split[3]);
		objVO.setDutyRoleId(split[4]);
		objVO.setLocation(loc);
		objVO.setDisplayOrder(String.valueOf(i+1));
		objVO.setIsValid(Config.IS_VALID_ACTIVE);
		return objVO;
	}
	
	//* Getting Desk Detail VO
	public static void addDeskDetailVO(String[] menuList,List<DeskDetailVO> lstDeskDtl, String loc)
	{
		for(int i=0;i<menuList.length;i++)
		{
			DeskDetailVO objVO=new DeskDetailVO();
			//objVO.setDeskId(_fb.getDeskId()); Not Found Yet
			String split[]=menuList[i].split("@");
			objVO.setDeskMenuId(split[0]);
			objVO.setColor(split[1]);
			objVO.setUserDeskMenuName(split[2]);
			objVO.setIsLoginBound(split[3]);
			objVO.setDutyRoleId(split[4]);
			objVO.setLocation(loc);
			objVO.setDisplayOrder(String.valueOf(i+1));
			//objVO.setIsValid(Config.IS_VALID_ACTIVE);  Will Set From Desk VO
			lstDeskDtl.add(objVO);
		}
	}

	//* Saving Desk & Desk Detail
	public static boolean SaveMenuToGlobalDeskMaster(DeskMasterFB _fb,HttpServletRequest _request)
	{
		boolean flag = true;
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			DeskMasterVO voDeskMst=new DeskMasterVO();
			
			voDeskMst.setDeskName(_fb.getDeskName());
			voDeskMst.setDeskType(_fb.getDeskType());
			voDeskMst.setIsDefault("1");
			voDeskMst.setIsValid(_fb.getIsActive());
			voDeskMst.setDeskId("-1");
			
			// Get Desk Detail
			List<DeskDetailVO> lstDeskDetail = new ArrayList<DeskDetailVO>();
				// Left Menu
			String menuList[]=_fb.getLeftMenuList();
			addDeskDetailVO(menuList,lstDeskDetail,OpdConfig.DESK_LOCATION_LEFT);
				// Right Menu
			menuList=_fb.getRightMenuList();
			addDeskDetailVO(menuList,lstDeskDetail,OpdConfig.DESK_LOCATION_RIGHT);
				// Top Menu
			menuList=_fb.getTopMenuList();
			addDeskDetailVO(menuList,lstDeskDetail,OpdConfig.DESK_LOCATION_TOP);
				// Bottom Menu
			menuList=_fb.getBottomMenuList();
			addDeskDetailVO(menuList,lstDeskDetail,OpdConfig.DESK_LOCATION_BOTTOM);
			
			AddModifyMenuToGlobalDeskMasterDATA.saveCompleteGlobalDeskDetail(voDeskMst,lstDeskDetail,userVO);
			/*_fb.setDeskId(AddModifyMenuToGlobalDeskMasterDATA.SaveDesk(voDeskMst,userVO));		
			
			DeskDetailVO voDeskDetail;			

			//Left Menu
			String menuList[]=_fb.getLeftMenuList();
			for(int i=0;i<menuList.length;i++)
			{
				voDeskDetail = getDeskDetailVO(menuList,i,_fb,OpdConfig.DESK_LOCATION_LEFT,_request);
				AddModifyMenuToGlobalDeskMasterDATA.SaveDeskDetail(voDeskDetail,userVO);
			}
			//	Right Menu
			menuList=_fb.getRightMenuList();
			for(int i=0;i<menuList.length;i++)
			{
				voDeskDetail=getDeskDetailVO(menuList,i,_fb,OpdConfig.DESK_LOCATION_RIGHT,_request);
				AddModifyMenuToGlobalDeskMasterDATA.SaveDeskDetail(voDeskDetail,userVO);
			}
			//	Top Menu
			menuList=_fb.getTopMenuList();
			for(int i=0;i<menuList.length;i++)
			{
				voDeskDetail=getDeskDetailVO(menuList,i,_fb,OpdConfig.DESK_LOCATION_TOP,_request);
				AddModifyMenuToGlobalDeskMasterDATA.SaveDeskDetail(voDeskDetail,userVO);
			}
			//Bottom Menu
			menuList=_fb.getBottomMenuList();
			for(int i=0;i<menuList.length;i++)
			{
				voDeskDetail=getDeskDetailVO(menuList,i,_fb,OpdConfig.DESK_LOCATION_BOTTOM,_request);
				AddModifyMenuToGlobalDeskMasterDATA.SaveDeskDetail(voDeskDetail,userVO);
			}*/
			
			objStatus.add(Status.INPROCESS,"Record Saved Successfully","");			
		}
		catch(HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage()+"..  Please try Again");
		}
		catch(HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
		return flag;
	}

	//* Update Records to Desk Master & Desk Detail 
	public static boolean UpdateMenuToDeskMaster(DeskMasterFB _fb,HttpServletRequest _request)
	{
		boolean flag = true; 
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);

			DeskMasterVO voDeskMst=new DeskMasterVO();
			voDeskMst.setDeskId(_fb.getDeskId());
			voDeskMst.setDeskName(_fb.getDeskName());
			voDeskMst.setDeskType(_fb.getDeskType());
			voDeskMst.setIsDefault(_fb.getIsDefault());
			voDeskMst.setIsValid(_fb.getIsActive());
			
			// Get Desk Detail
			List<DeskDetailVO> lstDeskDetail = new ArrayList<DeskDetailVO>();
				// Left Menu
			String menuList[]=_fb.getLeftMenuList();
			addDeskDetailVO(menuList,lstDeskDetail,OpdConfig.DESK_LOCATION_LEFT);
				// Right Menu
			menuList=_fb.getRightMenuList();
			addDeskDetailVO(menuList,lstDeskDetail,OpdConfig.DESK_LOCATION_RIGHT);
				// Top Menu
			menuList=_fb.getTopMenuList();
			addDeskDetailVO(menuList,lstDeskDetail,OpdConfig.DESK_LOCATION_TOP);
				// Bottom Menu
			menuList=_fb.getBottomMenuList();
			addDeskDetailVO(menuList,lstDeskDetail,OpdConfig.DESK_LOCATION_BOTTOM);
			
			AddModifyMenuToGlobalDeskMasterDATA.updateCompleteGlobalDesk(voDeskMst, lstDeskDetail, userVO);
			
			/*AddModifyMenuToGlobalDeskMasterDATA.UpdateDesk(voDeskMst,userVO);
			//AddModifyMenuToGlobalDeskMasterDATA.DeleteAllDeskMenus(_fb.getDeskId(),userVO);

			DeskDetailVO voDeskDetail;

			//Left Menu
			menuList =_fb.getLeftMenuList();
			for(int i=0;i<menuList.length;i++)
			{
				voDeskDetail = getDeskDetailVO(menuList,i,_fb,OpdConfig.DESK_LOCATION_LEFT,_request);
				AddModifyMenuToGlobalDeskMasterDATA.SaveDeskDetail(voDeskDetail,userVO);
			}
			//	Right Menu
			menuList=_fb.getRightMenuList();
			for(int i=0;i<menuList.length;i++)
			{
				voDeskDetail=getDeskDetailVO(menuList,i,_fb,OpdConfig.DESK_LOCATION_RIGHT,_request);
				AddModifyMenuToGlobalDeskMasterDATA.SaveDeskDetail(voDeskDetail,userVO);
			}
			//	Top Menu
			menuList=_fb.getTopMenuList();
			for(int i=0;i<menuList.length;i++)
			{
				voDeskDetail=getDeskDetailVO(menuList,i,_fb,OpdConfig.DESK_LOCATION_TOP,_request);
				AddModifyMenuToGlobalDeskMasterDATA.SaveDeskDetail(voDeskDetail,userVO);
			}
			//Bottom Menu
			menuList=_fb.getBottomMenuList();
			for(int i=0;i<menuList.length;i++)
			{
				voDeskDetail=getDeskDetailVO(menuList,i,_fb,OpdConfig.DESK_LOCATION_BOTTOM,_request);
				AddModifyMenuToGlobalDeskMasterDATA.SaveDeskDetail(voDeskDetail,userVO);
			}*/
			
			objStatus.add(Status.INPROCESS, "Record Modified Successfully", "");
		}
		catch(HisRecordNotFoundException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage()+"..  Please try Again");
		}
		catch(HisDataAccessException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
		return flag;
	}
	
	//Getting IsDefault status of Desk in OPD Desk Master
	public static void getisDefaultGlobal(DeskMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			DeskMasterVO _deskMstVO= new DeskMasterVO();

			_deskMstVO.setDeskType(_fb.getDeskType());
			_deskMstVO.setDeskId("-1");
			
			boolean defaultValue=AddModifyMenuToGlobalDeskMasterDATA.getisDefaultGlobal(_deskMstVO,userVO);
			if(defaultValue)
			{
				_fb.setCheckForDefault("0");
			}
			else
			{
				_fb.setCheckForDefault("1");
			}
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
}

