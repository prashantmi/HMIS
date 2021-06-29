package opd.master.controller.util;

/**
 * @author  CDAC
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.AddUserDeskMenuMasterDATA;
import opd.master.controller.data.AddUserDeskUnitWardMappingMasterDATA;
import opd.master.controller.data.UserDeskMenuTemplateMappingDATA;
import opd.master.controller.fb.UserDeskMenuMasterFB;
import opd.master.controller.fb.UserDeskUnitWardMappingMasterFB;

public class AddUserDeskMenuMasterUTIL extends ControllerUTIL 
{
	//* Setting Essentials for Adding into User Desk Menu Master
	public static void setEssential(UserDeskMenuMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();		
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
		//if(_fb.getControls() !=null && _fb.getControls()[0]!=null)
		//_fb.setDeskType(_fb.getControls()[0]);
			mp = UserDeskMenuTemplateMappingDATA.getAllDeskType(userVO);
			List<Entry> lstDeskTypes = (List<Entry>)mp.get(OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE);
			if(lstDeskTypes==null || lstDeskTypes.size()==0)
			{
				WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE, new ArrayList<Entry>());
				throw new HisRecordNotFoundException("No Desk Type Found");
			}
			//mp=AddUserDeskMenuMasterDATA.getEssentials(userVO);
			
		//	List lstDesks=AddUserDeskMenuMasterDATA.getDeskListByType(_fb.getDeskType(),userVO);
		//	mp.put(OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE,lstDesks);
			/*
			// Getting All Department & Unit List
			List lstUnit=AddUserDeskMenuMasterDATA.getUnitExceptAssignedByDeskType(_fb.getDeskType(),userVO);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED , lstUnit);
			//Map mpDeptNUnit = UserDeskMenuTemplateMappingDATA.getDeptNUnitModeWise(_fb.getAdditionMode(), _fb.getDeskId(), userVO);
			Map mpDeptNUnit = UserDeskMenuTemplateMappingDATA.getDeptNUnitModeWise(_fb.getMappingType(), _fb.getDeskId(), userVO);
			List<Entry> lstDepts = (List<Entry>) mp.get(OpdConfig.EssentialBO_LIST_ALL_DEPT);
			List<Entry> lstUnitsUnitWise = (List<Entry>) mp.get(OpdConfig.EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED);
			List<Entry> lstUnitsUnitUserWise = (List<Entry>) mp.get(OpdConfig.EssentialBO_LIST_ALL_UNITS);
			System.out.println(lstDepts);
			System.out.println(lstUnitsUnitWise);
			System.out.println(lstUnitsUnitUserWise);
			

			
			if(lstDepts==null || lstDepts.size()==0)
			{
				throw new HisRecordNotFoundException("No Clinical Department Found to Add");
			}
			List<Entry> lstDeptsOnlyUnitWise = new ArrayList<Entry>();
			List<Entry> lstDeptsOnlyUnitUserWise = new ArrayList<Entry>();
			for(Entry entDept: lstDepts)
			{				
				boolean flagUnitWise = false, flagUnitUserWise = false;
				// Unit Wise
				for(Entry entUnit: lstUnitsUnitWise)
					if(entUnit.getValue().substring(0, 3).equals(entDept.getValue()))
					{	flagUnitWise = true;	break;	}
				if(flagUnitWise)	lstDeptsOnlyUnitWise.add(entDept);
				// User Unit Wise
				for(Entry entUnit: lstUnitsUnitUserWise)
					if(entUnit.getValue().substring(0, 3).equals(entDept.getValue()))
					{	flagUnitUserWise = true;	break;	}
				if(flagUnitUserWise)	lstDeptsOnlyUnitUserWise.add(entDept);
			}
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
				mp.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDeptsOnlyUnitWise);
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
				mp.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDeptsOnlyUnitUserWise);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED, lstUnitsUnitWise);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_UNITS, lstUnitsUnitUserWise);
*/
			
			WebUTIL.setMapInSession(mp,_request);
			
			//int deskType=Integer.parseInt(_fb.getDeskType());
	     	//_fb.setDeskTypeDesc(DynamicDeskConfig.DESK_TYPES[deskType]);
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
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
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}
	
	//* Setting Selected Data i.e. Department Name , Selected Unit List
	public static void setSelectedData(UserDeskMenuMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			HttpSession session = _request.getSession();

			// Setting Selected Unit Name List
			List lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_UNITS);
			ArrayList alUnits=new ArrayList();
			String[] selunits=_fb.getSelectedUnits();
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				for(int j=0;j<selunits.length;j++ )
					if(selunits[j].equals(entobj.getValue()))
					{
						Entry newobj=new Entry();
						newobj.setValue(entobj.getValue());
						newobj.setLabel(entobj.getLabel());
						alUnits.add(newobj);
					}
			}
			_fb.setSelectedUnitsName(alUnits);
			
		//	objStatus.add(Status.INPROCESS);
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
	}
	
	//* Setting Source Seats List not already added for Selected Units
	public static void setSourceSeats(UserDeskMenuMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		
		List lstSeats=new ArrayList();
		Map mp= new HashMap();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			//String units[]=_fb.getSelectedUnits();
			String deskType=_fb.getDeskType();
			String groupCode=_fb.getGroup();
			
		//	lstSeats=AddUserDeskMenuMasterDATA.getSeatsByNotUnits(units,deskType,userVO,groupCode);
			// Change By Chetan According to Global Mapping Concept
			lstSeats=AddUserDeskMenuMasterDATA.getSeatsByNotUnits(deskType,userVO,groupCode);
			mp.put(OpdConfig.EssentialBO_LIST_DEPT_SEATS,lstSeats);
			WebUTIL.setMapInSession(mp,_request);
				if(lstSeats.size()>0)
					objStatus.add(Status.INPROCESS);
				else
					objStatus.add(Status.INPROCESS,"","No Seat Found Against group");
		}
		catch(HisRecordNotFoundException e)
		{
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
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
	}

	//* Adding Desk To Seats Unit-Wise
	public static boolean AddDesktoSeatsUnitWise(UserDeskMenuMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);

			UserDeskMenuMasterVO voUserDeskMenu;
			int	countSeat=_fb.getSelectedSeats().length;
			for(int i=0;i<countSeat;i++)
			{
				int countUnit=_fb.getSelectedUnits().length;
				for(int j=0;j<countUnit;j++)
				{
					voUserDeskMenu =new UserDeskMenuMasterVO();
					voUserDeskMenu.setUserSeatId(_fb.getSelectedSeats()[i].trim());
					//voUserDeskMenu.setDeptUnitCode(_fb.getSelectedUnits()[j].split("@")[0]);
					voUserDeskMenu.setDeskId(_fb.getDeskId());
					voUserDeskMenu.setIsValid(Config.IS_VALID_ACTIVE);
					voUserDeskMenu.setDeskType(_fb.getDeskType());
					AddUserDeskMenuMasterDATA.AddDesktoSeatsUnitWise(voUserDeskMenu,userVO);
				}
			}
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
			saveFlag=false;
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
		return saveFlag;
	}
	
	//* Adding Desk To Unit
	public static boolean addDeskToUnitWise(UserDeskMenuMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		boolean saveFlag=true;
		try
		{
 			UserVO userVO = getUserVO(_request);
 				
			UserDeskMenuMasterVO[] voUserDeskMenu=null;
			//UserDeskMenuMasterVO[] voUserDeskMenu;
			//voUserDeskMenu =new UserDeskMenuMasterVO();
			
			
			if(_fb.getMappingType().equals(OpdConfig.DESK_MAPPING_TYPE_UNIT_WISE))
			{ 
			int countUnit=_fb.getSelectedUnits().length;
			voUserDeskMenu=new UserDeskMenuMasterVO[countUnit];
			String dept="";
			
			for(int i=0;i<countUnit;i++)
			{  
				voUserDeskMenu[i]=new UserDeskMenuMasterVO();
				dept=_fb.getSelectedUnits()[i].split("@")[0];
			voUserDeskMenu[i].setDeptUnitCode(dept);
			voUserDeskMenu[i].setDeskId(_fb.getDeskId());
			voUserDeskMenu[i].setIsValid(Config.IS_VALID_ACTIVE);
			voUserDeskMenu[i].setDeskType(_fb.getDeskType());
			voUserDeskMenu[i].setMappingType(_fb.getMappingType());
				
			}
			
			}
				
			
			else if(_fb.getMappingType().equals(OpdConfig.DESK_MAPPING_TYPE_WARD_WISE))
			{
				
				int countWards=_fb.getSelectedWards().length;
				voUserDeskMenu=new UserDeskMenuMasterVO[countWards];
								
				for(int i=0;i<countWards;i++)
				{  
				voUserDeskMenu[i]=new UserDeskMenuMasterVO();
				voUserDeskMenu[i].setDeptUnitCode(_fb.getUnitId());
				voUserDeskMenu[i].setDeskId(_fb.getDeskId());
				voUserDeskMenu[i].setIsValid(Config.IS_VALID_ACTIVE);
				voUserDeskMenu[i].setDeskType(_fb.getDeskType());
				voUserDeskMenu[i].setMappingType(_fb.getMappingType());
				voUserDeskMenu[i].setWardCode(_fb.getSelectedWards()[i]);
					
				}
				
				
			}
			else if(_fb.getMappingType().equals(OpdConfig.DESK_MAPPING_TYPE_USER_WISE))
			{
				
				int	countSeat=_fb.getSelectedSeats().length;
				voUserDeskMenu=new UserDeskMenuMasterVO[countSeat];
				for(int i=0;i<countSeat;i++)
				{					
					{
						voUserDeskMenu[i]=new UserDeskMenuMasterVO();
						voUserDeskMenu[i].setUserSeatId(_fb.getSelectedSeats()[i].trim());
						//voUserDeskMenu.setDeptUnitCode(_fb.getSelectedUnits()[j].split("@")[0]);
						voUserDeskMenu[i].setDeskId(_fb.getDeskId());
						voUserDeskMenu[i].setIsValid(Config.IS_VALID_ACTIVE);
						voUserDeskMenu[i].setDeskType(_fb.getDeskType());
						voUserDeskMenu[i].setMappingType(_fb.getMappingType());
					//	AddUserDeskMenuMasterDATA.AddDesktoSeatsUnitWise(voUserDeskMenu,userVO);
				
				
				}
					}
			
			}	
			         AddUserDeskMenuMasterDATA.AddDesktoSeatsUnitWise1(voUserDeskMenu,userVO);
			
		}
		
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
		return saveFlag;
	}
	
	public static void getGroupList(UserDeskMenuMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		
		List lstSeats=new ArrayList();
		List lstGroup=new ArrayList();
		Map mp= new HashMap();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			
			lstGroup=AddUserDeskMenuMasterDATA.getAllGroupList(userVO);
			WebUTIL.setAttributeInSession(_request,OpdConfig.ESSENTIALBO_ALL_GROUP_LIST ,lstGroup );
			mp.put(OpdConfig.EssentialBO_LIST_DEPT_SEATS,lstSeats);
			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
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
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
	}

	//* Setting values for UnitType mapping
		public static void getDetailsToMap(UserDeskMenuMasterFB _fb, HttpServletRequest _request)
		{
			Status  objStatus=new Status();
			Map mp= new HashMap();
			try
			{
				UserVO userVO = getUserVO(_request);
				setSysdate(_request);				
				
				UserDeskMenuMasterVO voDeskMapping = new UserDeskMenuMasterVO();
				HelperMethods.populate(voDeskMapping, _fb);
				
				mp = AddUserDeskMenuMasterDATA.getDetailsToMap(voDeskMapping, userVO);
				
				WebUTIL.setMapInSession(mp,_request);
						objStatus.add(Status.INPROCESS);
				
			}
			catch(HisRecordNotFoundException e)
			{
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
				objStatus.add(Status.ERROR,"","Error");
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
			}	
		}

		//Getting Wards List in unit Ward Wise Mode
		public static void getWardInUnitWardWiseMode(UserDeskMenuMasterFB _fb,HttpServletRequest _request)
		{
			Status  objStatus=new Status();
					
			List lstWards=new ArrayList();
			Map mp= new HashMap();

			try
			{
				UserVO userVO = getUserVO(_request);
				setSysdate(_request);
						
				String deskType=_fb.getDeskType();
				String deptCode=_fb.getUnitId();
				
				
			/*	lstGroup=AddUserDeskUnitWardMappingMasterDATA.getAllGroupList(userVO);
				WebUTIL.setAttributeInSession(_request,OpdConfig.ESSENTIALBO_ALL_GROUP_LIST ,lstGroup );*/

				lstWards=AddUserDeskMenuMasterDATA.getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(deskType,userVO,deptCode);
				mp.put(OpdConfig.EssentialBO_LIST_ALL_WARDS_NOT_ASSIGNED_SEAT,lstWards);
				WebUTIL.setMapInSession(mp,_request);

				objStatus.add(Status.INPROCESS);
			}
			catch(HisRecordNotFoundException e)
			{
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
				objStatus.add(Status.ERROR,"","Error");
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
			}	
		}
		
}
