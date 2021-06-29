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
import opd.master.controller.data.AddUserDeskUnitWardMappingMasterDATA;
import opd.master.controller.fb.UserDeskUnitWardMappingMasterFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.UserDeskUnitWardMappingMasterVO;
import hisglobal.vo.UserVO;

public class AddUserDeskUnitWardMappingMasterUTIL extends ControllerUTIL 
{
	//* Setting Essentials for Adding into User Desk Menu Master
	public static void setEssential(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();		
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);	
			if(_fb.getControls() !=null && _fb.getControls()[0]!=null)
				_fb.setDeskType(_fb.getControls()[0]);
			
			
			UserDeskUnitWardMappingMasterVO voUserDeskUnitWardMapping;
			voUserDeskUnitWardMapping =new UserDeskUnitWardMappingMasterVO();
			voUserDeskUnitWardMapping.setDeptUnitCode(_fb.getUnitId());
			
			
			
			mp=AddUserDeskUnitWardMappingMasterDATA.getEssentials(userVO);
			//mp=AddUserDeskUnitWardMappingMasterDATA.getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(_fb.getDeskType(),userVO);
			
			List lstDesks=AddUserDeskUnitWardMappingMasterDATA.getDeskListByType(_fb.getDeskType(),userVO);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE,lstDesks);
			
			List lstUnit=AddUserDeskUnitWardMappingMasterDATA.getUnitExceptAssignedByDeskTypeForUnitWardMappingMaster(_fb.getDeskType(),userVO);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED_SEAT_AND_WARD , lstUnit);


			List<Entry> lstDepts = (List<Entry>) mp.get(OpdConfig.EssentialBO_LIST_ALL_DEPT);
			List<Entry> lstUnitsUnitWise = (List<Entry>) mp.get(OpdConfig.EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED_SEAT_AND_WARD);
			List<Entry> lstUnitsUnitWardWise = (List<Entry>) mp.get(OpdConfig.EssentialBO_LIST_ALL_UNITS_MAPPING_MASTER);
			if(lstDepts==null || lstDepts.size()==0)
			{
				throw new HisRecordNotFoundException("No Clinical Department Found to Add");
			}
			List<Entry> lstDeptsOnlyUnitWise = new ArrayList<Entry>();
			List<Entry> lstDeptsOnlyUnitWardWise = new ArrayList<Entry>();
			for(Entry entDept: lstDepts)
			{				
				boolean flagUnitWise = false, flagUnitUserWise = false;
				// Unit Wise
				for(Entry entUnit: lstUnitsUnitWise)
					if(entUnit.getValue().substring(0, 3).equals(entDept.getValue()))
					{	flagUnitWise = true;	break;	}
				if(flagUnitWise)	lstDeptsOnlyUnitWise.add(entDept);
				// User Ward Wise
				for(Entry entUnit: lstUnitsUnitWardWise)
					if(entUnit.getValue().substring(0, 3).equals(entDept.getValue()))
					{	flagUnitUserWise = true;	break;	}
				if(flagUnitUserWise)	lstDeptsOnlyUnitWardWise.add(entDept);
			}
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
				mp.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDeptsOnlyUnitWise);
			else
				mp.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDeptsOnlyUnitWardWise);			
			mp.put(OpdConfig.EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED_SEAT_AND_WARD, lstUnitsUnitWise);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_UNITS_MAPPING_MASTER, lstUnitsUnitWardWise);
			
			
			
			WebUTIL.setMapInSession(mp,_request);
			
			/*List listDeskType=AddUserDeskUnitWardMappingMasterDATA.getDeskType(userVO);
			for(Object o : listDeskType)
			{
				Entry e = (Entry)o;
				if(_fb.getDeskType().equalsIgnoreCase(e.getValue()))
				{
					_fb.setDeskTypeDesc(e.getLabel());
					break;
				}
			}*/
			int deskType=Integer.parseInt(_fb.getDeskType());
			_fb.setDeskTypeDesc(DynamicDeskConfig.DESK_TYPES[deskType]);
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
	
	
	
	//Getting Wards List in unit Ward Wise Mode
	public static void getWardInUnitWardWiseMode(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
				
		List lstWards=new ArrayList();
		Map mp= new HashMap();

		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
					
			String deskType=_fb.getDeskType();
			String UnitId=_fb.getUnitId();
			
		/*	lstGroup=AddUserDeskUnitWardMappingMasterDATA.getAllGroupList(userVO);
			WebUTIL.setAttributeInSession(_request,OpdConfig.ESSENTIALBO_ALL_GROUP_LIST ,lstGroup );*/

			lstWards=AddUserDeskUnitWardMappingMasterDATA.getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(deskType,userVO,UnitId);
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
	
	//Getting Wards List in unit Ward Wise Mode
	public static void getWardInUnitWardWiseSeatMode(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		
		
		List lstWards=new ArrayList();
		Map mp= new HashMap();

		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
						
			String deskType=_fb.getDeskType();
			String UnitId=_fb.getUnitId();
			
		/*	lstGroup=AddUserDeskUnitWardMappingMasterDATA.getAllGroupList(userVO);
			WebUTIL.setAttributeInSession(_request,OpdConfig.ESSENTIALBO_ALL_GROUP_LIST ,lstGroup );*/

			lstWards=AddUserDeskUnitWardMappingMasterDATA.getAllWardInUnitWardSeatMode(deskType,userVO,UnitId);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_WARDS,lstWards);
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


	//* Setting Selected Data i.e. Department Name , Selected Unit List
	public static void setSelectedData(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			HttpSession session = _request.getSession();

			// Setting Selected Unit Name List
			List lst=(ArrayList)session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_WARDS);
			ArrayList alWards=new ArrayList();
			String[] selwards=_fb.getSelectedWards();
			for(int i=0;i<lst.size();i++)
			{
				Entry entobj=(Entry)lst.get(i);
				for(int j=0;j<selwards.length;j++ )
					if(selwards[j].equals(entobj.getValue()))
					{
						Entry newobj=new Entry();
						newobj.setValue(entobj.getValue());
						newobj.setLabel(entobj.getLabel());
						alWards.add(newobj);
					}
			}
			_fb.setSelectedWardsName(alWards);
			
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
	public static void setSourceSeats(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		
		List lstSeats=new ArrayList();
		Map mp= new HashMap();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			String wards[]=_fb.getSelectedWards();
			String deskType=_fb.getDeskType();
			String groupCode=_fb.getGroup();
			
		/*	lstGroup=AddUserDeskUnitWardMappingMasterDATA.getAllGroupList(userVO);
			WebUTIL.setAttributeInSession(_request,OpdConfig.ESSENTIALBO_ALL_GROUP_LIST ,lstGroup );*/

			lstSeats=AddUserDeskUnitWardMappingMasterDATA.getSeatsByNotWards(wards,deskType,userVO,groupCode);
			mp.put(OpdConfig.EssentialBO_LIST_DEPT_SEATS_MAPPING_MASTER,lstSeats);
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
	
	//Getting the group list
	public static void getGroupList(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		
		List lstSeats=new ArrayList();
		List lstGroup=new ArrayList();
		Map mp= new HashMap();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			
			lstGroup=AddUserDeskUnitWardMappingMasterDATA.getAllGroupList(userVO);
			WebUTIL.setAttributeInSession(_request,OpdConfig.ESSENTIALBO_ALL_GROUP_LIST ,lstGroup );

		//	lstSeats=AddUserDeskUnitWardMappingMasterDATA.getSeatsByNotUnits(units,deskType,userVO);
			mp.put(OpdConfig.EssentialBO_LIST_DEPT_SEATS_MAPPING_MASTER,lstSeats);
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
	
	//* Adding Desk To Unit Wise in User Desk Unit Ward Mapping Master
	public static boolean addDeskToUnitWise(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			UserDeskUnitWardMappingMasterVO voUserDeskUnitWardMapping;
			
			int countUnit=_fb.getSelectedUnits().length;
			for(int i=0;i<countUnit;i++)
			{
				voUserDeskUnitWardMapping =new UserDeskUnitWardMappingMasterVO();
				voUserDeskUnitWardMapping.setDeptUnitCode(_fb.getSelectedUnits()[i]);
				voUserDeskUnitWardMapping.setDeskId(_fb.getDeskId());
				voUserDeskUnitWardMapping.setIsValid(Config.IS_VALID_ACTIVE);
				voUserDeskUnitWardMapping.setDeskType(_fb.getDeskType());
				AddUserDeskUnitWardMappingMasterDATA.AddDesktoUnitWise(voUserDeskUnitWardMapping,userVO);
			}
			
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
	
	//* Adding Desk To Unit Ward Wise in User Desk Unit Ward Mapping Master
	public static boolean addDeskToUnitWardWise(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			UserDeskUnitWardMappingMasterVO voUserDeskUnitWardMapping;
			int countWards=_fb.getSelectedWards().length;
			for(int i=0;i<countWards;i++)
			{
				
				voUserDeskUnitWardMapping =new UserDeskUnitWardMappingMasterVO();
				voUserDeskUnitWardMapping.setWardCode(_fb.getSelectedWards()[i]);
				voUserDeskUnitWardMapping.setDeptUnitCode(_fb.getUnitId());
				voUserDeskUnitWardMapping.setDeskId(_fb.getDeskId());
				voUserDeskUnitWardMapping.setIsValid(Config.IS_VALID_ACTIVE);
				voUserDeskUnitWardMapping.setDeskType(_fb.getDeskType());
				AddUserDeskUnitWardMappingMasterDATA.AddDesktoUnitWardWise(voUserDeskUnitWardMapping,userVO);
			}
			
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
	
	//* Adding Desk To Seats Unit-Ward Seat Wise
	public static boolean addDeskToUnitWardSeatWise(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);

			UserDeskUnitWardMappingMasterVO voUserDeskUnitWardMapping;
			
			int	countSeat=_fb.getSelectedSeats().length;
			for(int i=0;i<countSeat;i++)
			{
					int countWards=_fb.getSelectedWards().length;
					for(int j=0;j<countWards;j++)
					{
									
					voUserDeskUnitWardMapping =new UserDeskUnitWardMappingMasterVO();
					voUserDeskUnitWardMapping.setUserSeatId(_fb.getSelectedSeats()[i].trim());
					voUserDeskUnitWardMapping.setWardCode(_fb.getSelectedWards()[j]);
					voUserDeskUnitWardMapping.setDeptUnitCode(_fb.getUnitId());
					voUserDeskUnitWardMapping.setDeskId(_fb.getDeskId());
					voUserDeskUnitWardMapping.setIsValid(Config.IS_VALID_ACTIVE);
					voUserDeskUnitWardMapping.setDeskType(_fb.getDeskType());
					AddUserDeskUnitWardMappingMasterDATA.AddDesktoUnitWardWise(voUserDeskUnitWardMapping,userVO);
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
	
	}
