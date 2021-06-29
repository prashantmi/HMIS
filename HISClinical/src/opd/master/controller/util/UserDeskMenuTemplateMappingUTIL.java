package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.UserDeskMenuTemplateMappingDATA;
import opd.master.controller.fb.UserDeskMenuTemplateMappingFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

public class UserDeskMenuTemplateMappingUTIL extends  ControllerUTIL
{
	//// Getting All Desk Type for First Step in Temp Mapping
	public static void getEssential(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		Map mp = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
		
			mp = UserDeskMenuTemplateMappingDATA.getAllDeskType(userVO);
			
			/*if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE))    //For Desk Wise Mode
			{
				//mp=UserDeskMenuTemplateMappingDATA.getAllDesk(userVO);
				mp=UserDeskMenuTemplateMappingDATA.getAllDeskType(userVO);
			}
			
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE)) //For Unit wise & Unit-Seat Wise mode
			{
				//mp=UserDeskMenuTemplateMappingDATA.getAllUnit(userVO);
				//mp.put(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS_WITHOUTSEAT,lst);
				mp=UserDeskMenuTemplateMappingDATA.getAllDeskType(userVO);
			}
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE)) //For Unit wise & Unit-Seat Wise mode
			{	
				mp=UserDeskMenuTemplateMappingDATA.getAllDeskType(userVO);
				//mp=UserDeskMenuTemplateMappingDATA.getUnitSeatWise(userVO);
				//mp.put(OpdConfig.EssentialBO_LIST_SEAT_BASED_ON_UNIT,lst);
				//mp.put(OpdConfig.EssentialBO_LIST_DESK_BASED_ON_UNIT_N_SEAT,lst);
			}
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE)) //For Ward Wise mode
			{
				mp=UserDeskMenuTemplateMappingDATA.getAllDeskType(userVO);
				//mp=UserDeskMenuTemplateMappingDATA.getUnitForWardWise(userVO);
				//mp.put(OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE,lst);
				//mp.put(OpdConfig.EssentialBO_LIST_DESK_BASED_ON_UNIT_N_WARD,lst);
			}
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE)) //For WardSeat Wise mode
			{
				mp=UserDeskMenuTemplateMappingDATA.getAllDeskType(userVO);
				//mp=UserDeskMenuTemplateMappingDATA.getUnitForWardSeatWise(userVO);
				//mp.put(OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE_FOR_UWS,lst);
				//mp.put(OpdConfig.EssentialBO_LIST_SEAT_UNIT_N_WARD_WISE,lst);
				//mp.put(OpdConfig.EssentialBO_LIST_DESK_BASED_ON_UNIT_N_WARD_N_SEAT,lst);
				
			}*/
			
			List<Entry> lstDeskTypes = (List<Entry>)mp.get(OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE);
			if(lstDeskTypes==null || lstDeskTypes.size()==0)
			{
				WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE, new ArrayList<Entry>());
				throw new HisRecordNotFoundException("No Desk Type Found");
			}
			
				// Setting Stage for Template Mapping Initially
			_fb.setHmode("NEW");
			_fb.setIsGoPressed(OpdConfig.STEP0);
			_fb.setIsDeskSelected(OpdConfig.NO);
			_fb.setIsMappingStart(OpdConfig.NO);
			_fb.setIsModificationStart(OpdConfig.NO);
			_fb.setLength("0");
			//WebUTIL.setMapInSession(mp,_request);
			WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE, lstDeskTypes);
			objStatus.add(Status.NEW);		
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}	
	
	//// Getting All Desk List on Selection of Desk Type
	public static void getDesk(UserDeskMenuTemplateMappingFB _fb, HttpServletRequest _request)
	{
		Status objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
		
			// Setting Stage for Desk Type Selection & Desk List
			_fb.setDeskId("");
			_fb.setUnitCode("");
			_fb.setWardCode("");
			_fb.setUserSeatId("");

			_fb.setHmode("NEW");
			_fb.setIsGoPressed(OpdConfig.STEP0);
			_fb.setIsDeskSelected(OpdConfig.NO);
			_fb.setIsMappingStart(OpdConfig.NO);
			_fb.setIsModificationStart(OpdConfig.NO);
			_fb.setLength("0");
			clearSession(_request);
			
			// Setting Desk List
			List<Entry> lstDesks = new ArrayList<Entry>();
			if(!_fb.getDeskType().trim().equalsIgnoreCase("-1"))
			{
				mp = UserDeskMenuTemplateMappingDATA.getAllDesk(_fb.getDeskType(),userVO);			
				lstDesks = (List<Entry>) mp.get(OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE);
				if(lstDesks==null || lstDesks.size()==0)
				{
					throw new HisRecordNotFoundException("No Desk Found for Desk Type");
				}
			}
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE, lstDesks);
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW,e.getMessage(),"");
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}
	
	// Clearing Session
	private static void clearSession(HttpServletRequest _request)
	{
		// Desk-Wise
		WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE, null);
		
		// Unit-Wise
		WebUTIL.setAttributeInSession(_request, OpdConfig.LIST_MAPPED_UNITS, null);
		
		// Unit Seat-Wise
		WebUTIL.setAttributeInSession(_request, OpdConfig.LIST_MAPPED_UNITS_FOR_UNITSEAT, null);
		WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_SEAT_BASED_ON_UNIT, null);
		
		// Unit Ward-Wise
		WebUTIL.setAttributeInSession(_request, OpdConfig.LIST_MAPPED_UNITS_FOR_WARD, null);
		WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE, null);
		
		// Unit Ward Seat-Wise
		WebUTIL.setAttributeInSession(_request, OpdConfig.LIST_MAPPED_UNITS_FOR_UNITSEATWARD, null);
		WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE_FOR_UWS, null);
		WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_SEAT_UNIT_N_WARD_WISE, null);
	}

	//// Getting Template Mapped Unit List for Addition Mode Unit-Wise
	public static void getMappedUnits(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map mp = new HashMap();		
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
			
			mp = UserDeskMenuTemplateMappingDATA.getMappedUnits(_fb.getDeskId(),userVO);			
			
				// Settings in Unit-Wise Desk Selection
			_fb.setUnitCode("");
			_fb.setWardCode("");
			_fb.setUserSeatId("");

			_fb.setHmode("NEW");
			_fb.setIsDeskSelected(OpdConfig.YES);
			_fb.setLength("0");
			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();			
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}	

	//// Getting Template Mapped Unit List for Addition Mode Unit Seat-Wise
	public static void getMappedUnitsForUnitSeat(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map mp = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_SEAT_BASED_ON_UNIT, null);
			mp = UserDeskMenuTemplateMappingDATA.getMappedUnitsForUnitSeat(_fb.getDeskId(),userVO);

			// Settings in Unit Seat-Wise Desk Selection
			_fb.setUnitCode("");
			_fb.setWardCode("");
			_fb.setUserSeatId("");

			_fb.setHmode("NEW");
			_fb.setIsDeskSelected(OpdConfig.YES);
			_fb.setLength("0");
			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();			
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}

	//// Getting Template Mapped Seat List for Selected Unit for Addition Mode Unit Seat-Wise
	public static void getSeatListByUnit(UserDeskMenuTemplateMappingFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
			
			_fb.setTempUnitCode(_fb.getUnitCode());	//------------
			List lstSeats = UserDeskMenuTemplateMappingDATA.getSeatListByUnit(_fb.getDeskId(), _fb.getUnitCode(), userVO);
			
			if(lstSeats.size()==0)
				objStatus.add(Status.NEW, "No Seat Exist For Selected Unit","");
			else
				objStatus.add(Status.NEW);

			// Settings in Unit-Wise Desk Selection
			_fb.setWardCode("");
			_fb.setUserSeatId("");

			_fb.setHmode("NEW");
			_fb.setIsDeskSelected(OpdConfig.YES);
			_fb.setLength("0");
			WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_SEAT_BASED_ON_UNIT, lstSeats);
			//WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_DESK_BASED_ON_UNIT_N_SEAT, lstDesk);
			objStatus.add(Status.TRANSINPROCESS);
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}

	//// Getting Template Mapped Unit List for Addition Mode Unit Ward-Wise
	public static void getMappedUnitsForWard(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE, null);
			//LIST_MAPPED_UNITS_FOR_WARD
			mp = UserDeskMenuTemplateMappingDATA.getMappedUnitsForWard(_fb.getDeskId(), userVO);
			
				// Settings in Unit-Wise Desk Selection
			_fb.setUnitCode("");
			_fb.setWardCode("");
			_fb.setUserSeatId("");

			_fb.setHmode("NEW");
			_fb.setIsDeskSelected(OpdConfig.YES);
			_fb.setLength("0");
			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}	

	//// Getting Template Mapped Ward List for Selected Unit for Addition Mode Unit Ward-Wise
	public static void getWardListByUnit(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
			
			_fb.setTempUnitCode(_fb.getUnitCode());
			List lstWard = UserDeskMenuTemplateMappingDATA.getWardListByUnit(_fb.getDeskId(), _fb.getUnitCode(), userVO);
			
			if(lstWard.size()==0)
				objStatus.add(Status.NEW, "No Ward Exist For Selected Unit","");
			else
				objStatus.add(Status.NEW);

			// Settings in Unit-Wise Desk Selection
			_fb.setWardCode("");
			_fb.setUserSeatId("");

			_fb.setHmode("NEW");
			_fb.setIsDeskSelected(OpdConfig.YES);
			_fb.setLength("0");
			WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE, lstWard); 
			//WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_DESK_BASED_ON_UNIT_N_WARD, lstDesk);
			objStatus.add(Status.TRANSINPROCESS);
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}
	
	//// Getting Template Mapped Unit List for Addition Mode Unit Ward Seat-Wise
	public static void getMappedUnitsForUnitSeatWard(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE_FOR_UWS, null);
			WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_SEAT_UNIT_N_WARD_WISE, null);
			mp = UserDeskMenuTemplateMappingDATA.getMappedUnitsForUnitSeatWard(_fb.getDeskId(),userVO);
			
				// Settings in Unit-Wise Desk Selection
			_fb.setUnitCode("");
			_fb.setWardCode("");
			_fb.setUserSeatId("");

			_fb.setHmode("NEW");
			_fb.setIsDeskSelected(OpdConfig.YES);
			_fb.setLength("0");
			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}

	//// Getting Template Mapped Ward List for Selected Unit for Addition Mode Unit Ward Seat-Wise
	public static void getWardListByUnitForUWS(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
			
			_fb.setTempUnitCode(_fb.getUnitCode());	//------------
			WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_SEAT_UNIT_N_WARD_WISE, null);
			List lstWard = UserDeskMenuTemplateMappingDATA.getWardListByUnitForUWS(_fb.getDeskId(), _fb.getUnitCode(), userVO);
			
			if(lstWard.size()==0)
				objStatus.add(Status.NEW, "No Ward Exist For Selected Unit","");
			else
				objStatus.add(Status.NEW);

			// Settings in Unit-Wise Desk Selection
			_fb.setWardCode("");
			_fb.setUserSeatId("");

			_fb.setHmode("NEW");
			_fb.setIsDeskSelected(OpdConfig.YES);
			_fb.setLength("0");
			WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE_FOR_UWS, lstWard); 
			//WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_SEAT_UNIT_N_WARD_WISE, lstSeat);
			//WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_DESK_BASED_ON_UNIT_N_WARD_N_SEAT, lstDesk);
			objStatus.add(Status.TRANSINPROCESS);
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}
	
	//// Getting Template Mapped Seat List for Selected Unit & Ward for Addition Mode Unit Ward Seat-Wise
	public static void getSeatListByUnitNWard(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
			
			List lstSeat = UserDeskMenuTemplateMappingDATA.getSeatListByUnitNWard(_fb.getDeskId(), _fb.getWardCode(), _fb.getUnitCode(), userVO);

			if(lstSeat.size()==0)
				objStatus.add(Status.NEW, "No Seat Exist For Selected Ward","");
			else
				objStatus.add(Status.NEW);

			// Settings in Unit-Wise Desk Selection
			_fb.setUserSeatId("");

			_fb.setHmode("NEW");
			_fb.setIsDeskSelected(OpdConfig.YES);
			_fb.setLength("0");
			WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_SEAT_UNIT_N_WARD_WISE, lstSeat); 
			//WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_DESK_BASED_ON_UNIT_N_WARD_N_SEAT, lstDesk);
			objStatus.add(Status.TRANSINPROCESS);
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}
	
	//// Getting Template List for Addition Mode Desk-Wise
	public static void getTemplateByDeskType(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus = new Status();
		UserDeskMenuTemplateMasterVO[] userDeskMenuTempVO=null;
		try
		{
			UserVO userVO = getUserVO(_request);

			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE))
				userDeskMenuTempVO = UserDeskMenuTemplateMappingDATA.getTemplateByDeskType(_fb.getDeskId(), userVO);			
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
				userDeskMenuTempVO = UserDeskMenuTemplateMappingDATA.getTemplateByDeskNUnit(_fb.getUnitCode(), _fb.getDeskId(), userVO);
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
				userDeskMenuTempVO = UserDeskMenuTemplateMappingDATA.getTemplateByDeskUnitNSeat(_fb.getUserSeatId(), _fb.getUnitCode(), _fb.getDeskId(), userVO);
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
				userDeskMenuTempVO = UserDeskMenuTemplateMappingDATA.getTemplateByDeskUnitNWard(_fb.getWardCode(), _fb.getUnitCode(), _fb.getDeskId(), userVO);
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
				userDeskMenuTempVO = UserDeskMenuTemplateMappingDATA.getTemplateByDeskUnitNWardNSeat(_fb.getUserSeatId(), _fb.getWardCode(), _fb.getUnitCode(), _fb.getDeskId(), userVO);
			
				// Settings in Desk-Wise Desk Selection
			WebUTIL.setAttributeInSession(_request, OpdConfig.MENU_TEMPLATE_LIST_BY_DESKID, userDeskMenuTempVO);
			_fb.setHmode("NEW");
			_fb.setIsDeskSelected(OpdConfig.YES);
			_fb.setIsMappingStart(OpdConfig.NO);
			_fb.setIsModificationStart(OpdConfig.NO);
			_fb.setLength((userDeskMenuTempVO==null)?"0":String.valueOf(userDeskMenuTempVO.length));
			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
			//objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}	
	}
	
	//// Getting Template List of Template Category of Desk Menu
	public static void getTemplateByTemplateCategory(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session = _request.getSession();
		try
		{
			String deskMenuId = _fb.getDeskMenuId();
			String tempCategory = null;
			DeskMenuMasterVO[] deskMenuMstVO = (DeskMenuMasterVO[])session.getAttribute(OpdConfig.ARR_DESK_MENU_N_CATEGORY_VO);
			for(int i=0;i<deskMenuMstVO.length;i++)
				if(deskMenuId.equalsIgnoreCase(deskMenuMstVO[i].getDeskMenuId()))
				{
					tempCategory = deskMenuMstVO[i].getTemplateCategory();
					break;
				}
			List<TemplateMasterVO> lstTemplateMasterVO = (List<TemplateMasterVO>)session.getAttribute(OpdConfig.ARR_TEMPLATE_N_CATEGORY_VO);
			List<TemplateMasterVO> lstTemp = getList(lstTemplateMasterVO, tempCategory);
			WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_ALL_TEMPLATES, lstTemp);
			
			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
			//objStatus.add(Status.RECORDFOUND);
			//objStatus.add(Status.LIST);
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
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}
	
	//// Extract Templates of Given Template Category
	private static List<TemplateMasterVO> getList(List<TemplateMasterVO> lst, String tempCat)
	{
		List<TemplateMasterVO> list = new ArrayList<TemplateMasterVO>();
		for(TemplateMasterVO vo : lst)
			if(tempCat.equals(vo.getTemplateCategory()))
				list.add(vo);
		return list;
	}
	
	//// Adding Template to Desk Menu
	public static void addRowForDeskId(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		try
		{
			// Adding Selected Temp to Menu
			addingPartInRowForDeskId(_fb, _request);
			
			// Getting Selected Temp VO
			Map<String, TemplateMasterVO> mp = (Map<String, TemplateMasterVO>) session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_TEMPLATES_VO_MAP);
			TemplateMasterVO tempVO;
			tempVO = mp.get(_fb.getTemplateId());

			// Removing Added Template from List
			List<TemplateMasterVO> lstTemplateMasterVO = (List<TemplateMasterVO>) session.getAttribute(OpdConfig.ARR_TEMPLATE_N_CATEGORY_VO);
			lstTemplateMasterVO.remove(tempVO);
			WebUTIL.setAttributeInSession(_request,OpdConfig.ARR_TEMPLATE_N_CATEGORY_VO, lstTemplateMasterVO);
			// Clearing Template List
			_fb.setDeskMenuId("");
			_fb.setTemplateId("");
			_fb.setIsDefault(OpdConfig.YES);
			WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_ALL_TEMPLATES, new ArrayList<TemplateMasterVO>());
			
			/*if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))------------
				_fb.setIsGoPressed(OpdConfig.STEP1);
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
				_fb.setIsGoPressed(OpdConfig.STEP1);*/
			
			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
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
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}
	}
	
	//// Adding Part
	private	static void addingPartInRowForDeskId(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		HttpSession session=_request.getSession();
		try
		{
			// Getting Selected Desk Menu Name
			DeskMenuMasterVO[] arrDeskMenus = (DeskMenuMasterVO[])session.getAttribute( OpdConfig.ARR_DESK_MENU_N_CATEGORY_VO);
			String deskMenuName = "";
			if(arrDeskMenus!=null){
			for(DeskMenuMasterVO vo : arrDeskMenus)
			    if(vo.getDeskMenuId().equalsIgnoreCase(_fb.getDeskMenuId()))
			    {
			    	deskMenuName=vo.getDeskMenuName();
			    	break;
			    }}
			// Getting Selected Temp VO
			Map<String, TemplateMasterVO> mp = (Map<String, TemplateMasterVO>) session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_TEMPLATES_VO_MAP);
			TemplateMasterVO tempVO;
			tempVO = mp.get(_fb.getTemplateId());
			// Is Default Name
			String isDefaultName="";
			if(_fb.getIsDefault().equals(OpdConfig.NO))
				isDefaultName="False";
			else
				isDefaultName="True";

			// Adding Selected Temp to Menu
			List<UserDeskMenuTemplateMasterVO> lstUserDeskMenuTempDeskVO = (List<UserDeskMenuTemplateMasterVO>) session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO);
			UserDeskMenuTemplateMasterVO userMenuTempVO = new UserDeskMenuTemplateMasterVO();
			
			userMenuTempVO.setDeskId(_fb.getDeskId());
			userMenuTempVO.setDeskMenuId(_fb.getDeskMenuId());
			userMenuTempVO.setDeskMenuName(deskMenuName);
			userMenuTempVO.setTemplateId(_fb.getTemplateId());
			userMenuTempVO.setTemplateName(tempVO.getTemplateName());
			userMenuTempVO.setIsDefault(_fb.getIsDefault());
			userMenuTempVO.setIsDefaultName(isDefaultName);

			lstUserDeskMenuTempDeskVO.add(userMenuTempVO);
			WebUTIL.setAttributeInSession(_request,OpdConfig.ARR_USER_DESK_MENU_TEMP_VO , lstUserDeskMenuTempDeskVO);
			_fb.setAddedLength(Integer.toString(lstUserDeskMenuTempDeskVO.size()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//// Deleting Template from Desk Menu
	public static void deleteRowForDeskId(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		try
		{
			// Removing Template Mapping from Addded
			List<UserDeskMenuTemplateMasterVO> lstUserDeskMenuTempDeskVO = (List<UserDeskMenuTemplateMasterVO>) session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO);
			UserDeskMenuTemplateMasterVO userMenuTempVO = null;			
			for(UserDeskMenuTemplateMasterVO vo : lstUserDeskMenuTempDeskVO)
				if(vo.getTemplateId().equalsIgnoreCase(_fb.getHiddenTemplateId()))
					userMenuTempVO = vo;
			if(userMenuTempVO != null)	lstUserDeskMenuTempDeskVO.remove(userMenuTempVO);
			WebUTIL.setAttributeInSession(_request,OpdConfig.ARR_USER_DESK_MENU_TEMP_VO , lstUserDeskMenuTempDeskVO);
			_fb.setAddedLength(Integer.toString(lstUserDeskMenuTempDeskVO.size()));

			// Adding Template Back in List
			Map<String, TemplateMasterVO> mp = (Map<String, TemplateMasterVO>) session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_TEMPLATES_VO_MAP);
			TemplateMasterVO tempVO = mp.get(_fb.getHiddenTemplateId());
			List<TemplateMasterVO> lstTemplateMasterVO = (List<TemplateMasterVO>)session.getAttribute(OpdConfig.ARR_TEMPLATE_N_CATEGORY_VO);
			lstTemplateMasterVO.add(tempVO);
			
			// Clearing Template List
			_fb.setDeskMenuId("");
			_fb.setTemplateId("");
			_fb.setIsDefault(OpdConfig.YES);
			WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_ALL_TEMPLATES, new ArrayList<TemplateMasterVO>());
			
			/*if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))--------
				_fb.setIsGoPressed(OpdConfig.STEP1);
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
				_fb.setIsGoPressed(OpdConfig.STEP1);*/

			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
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
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}
	}

	//// Getting Essentials for Adding Template in Addition Mode Desk-Wise
	public static void getMenuNTemplateToAdd(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			// Getting Template List along with Template Category
			List<TemplateMasterVO> templateMasterVO = UserDeskMenuTemplateMappingDATA.getAllTemplatesVO(userVO);
			mp.put(OpdConfig.ARR_TEMPLATE_N_CATEGORY_VO, templateMasterVO);			
			if(templateMasterVO==null || templateMasterVO.size()==0)
			{
				throw new HisRecordNotFoundException("No Template Exists to Map");
			}
						
			// Getting Selected Desk Menus
			DeskMenuMasterVO[] deskMenuMstVO = UserDeskMenuTemplateMappingDATA.getMenuNameBasedOnDeskId(_fb.getDeskId(), userVO);
			mp.put(OpdConfig.ARR_DESK_MENU_N_CATEGORY_VO , deskMenuMstVO);
			if(deskMenuMstVO==null || deskMenuMstVO.length==0)
			{
				throw new HisRecordNotFoundException("No Template-based Menu Found in the Selected Desk");
			}
			
			// Setting Essentials to map Template with Desk
				// Template List to Add for Selected Desk Menu Initially Empty
			mp.put(OpdConfig.EssentialBO_LIST_ALL_TEMPLATES, new ArrayList<TemplateMasterVO>());
				/*// List of Entry object of Desk Menu--------------
			List lstDeskMenu=new ArrayList();
			for(int i=0; i<deskMenuMstVO.length; i++)
			{
				Entry objEntry = new Entry();
				objEntry.setLabel(deskMenuMstVO[i].getDeskMenuName());
				objEntry.setValue(deskMenuMstVO[i].getDeskMenuId());
				lstDeskMenu.add(objEntry);
			}
			mp.put(OpdConfig.EssentialBO_LIST_USER_DESKMENU_LIST, lstDeskMenu);*/
				// Putting All Templates in Map template Id Wise 
			Map<String, TemplateMasterVO> mapTempVOs = new HashMap<String, TemplateMasterVO>();
			for(TemplateMasterVO vo : templateMasterVO)
				mapTempVOs.put(vo.getTemplateId(), vo);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_TEMPLATES_VO_MAP, mapTempVOs);
				// Setting Added Template VO Array Initially Empty
			mp.put(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO , new ArrayList<UserDeskMenuTemplateMasterVO>());
			
				// Setting Form Bean and Session Essentials
			_fb.setLength("0");
			_fb.setAddedLength("0");
			_fb.setIsMappingStart(OpdConfig.YES);
			_fb.setIsModificationStart(OpdConfig.NO);
			/*
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))------
			//	_fb.setIsGoPressed(OpdConfig.STEP1);
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
			{
				//_fb.setIsGoPressed(OpdConfig.STEP2);
				objStatus.add(Status.LIST);
			}
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
			{
				//_fb.setIsGoPressed(OpdConfig.STEP2);
				objStatus.add(Status.LIST);
			}*/
			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.NEW);
			//objStatus.add(Status.RECORDFOUND);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");			
			objStatus.add(Status.NEW,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}	

	//// Getting Essentials for Modifying Template Mapping
	public static void modifyForDeskId(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		Map mp= new HashMap();
		try
		{
			// Setting Already Added Template Mappings
			UserDeskMenuTemplateMasterVO[] userDeskMenuTempVO = (UserDeskMenuTemplateMasterVO[]) session.getAttribute(OpdConfig.MENU_TEMPLATE_LIST_BY_DESKID);			
			List<UserDeskMenuTemplateMasterVO> lstUserDeskMenuTempDeskVO = new ArrayList<UserDeskMenuTemplateMasterVO>();
			for(UserDeskMenuTemplateMasterVO vo : userDeskMenuTempVO)
				lstUserDeskMenuTempDeskVO.add(vo);
			
			WebUTIL.setAttributeInSession(_request,OpdConfig.ARR_USER_DESK_MENU_TEMP_VO , lstUserDeskMenuTempDeskVO);
			_fb.setAddedLength(Integer.toString(lstUserDeskMenuTempDeskVO.size()));
			
			UserVO userVO = getUserVO(_request);
			
			// Getting Template List along with Template Category
			List<TemplateMasterVO> templateMasterVO = UserDeskMenuTemplateMappingDATA.getAllTemplatesVO(userVO);
			mp.put(OpdConfig.ARR_TEMPLATE_N_CATEGORY_VO, templateMasterVO);			
			if(templateMasterVO==null || templateMasterVO.size()==0)
			{
				throw new HisRecordNotFoundException("No Template Exists to Map");
			}
						
			// Getting Selected Desk Menus
			DeskMenuMasterVO[] deskMenuMstVO = UserDeskMenuTemplateMappingDATA.getMenuNameBasedOnDeskId(_fb.getDeskId(), userVO);
			mp.put(OpdConfig.ARR_DESK_MENU_N_CATEGORY_VO , deskMenuMstVO);
			if(deskMenuMstVO==null || deskMenuMstVO.length==0)
			{
				throw new HisRecordNotFoundException("No Template-based Menu Found in the Selected Desk");
			}
			// Setting Essentials to map Template with Desk
				// Template List to Add for Selected Desk Menu Initially Empty
			mp.put(OpdConfig.EssentialBO_LIST_ALL_TEMPLATES, new ArrayList<TemplateMasterVO>());
				// Putting All Templates in Map template Id Wise 
			Map<String, TemplateMasterVO> mapTempVOs = new HashMap<String, TemplateMasterVO>();
			for(TemplateMasterVO vo : templateMasterVO)
				mapTempVOs.put(vo.getTemplateId(), vo);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_TEMPLATES_VO_MAP, mapTempVOs);
				// Removing Already Mapped Templates 
			for(UserDeskMenuTemplateMasterVO vo : lstUserDeskMenuTempDeskVO)
			{
				TemplateMasterVO tempVO = mapTempVOs.get(vo.getTemplateId());
				templateMasterVO.remove(tempVO);
			}			
				// Setting Form Bean and Session Essentials
			_fb.setLength("0");
			_fb.setIsMappingStart(OpdConfig.YES);
			_fb.setIsModificationStart(OpdConfig.YES);
			
			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");			
			objStatus.add(Status.NEW,e.getMessage(),"");
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
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}
	}	
	
	//// Deleting/Removing All Mapped Templates in Current Selected Desk Template Mapping
	public static boolean deleteForDeskId(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean isDelete = true;
		try
		{
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE))
				UserDeskMenuTemplateMappingDATA.deleteForDeskId(_fb.getDeskId(), getUserVO(_request));
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
				UserDeskMenuTemplateMappingDATA.deleteForDeskNUnit(_fb.getUnitCode(), _fb.getDeskId(), getUserVO(_request));
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
				UserDeskMenuTemplateMappingDATA.deleteForDeskUnitNSeat(_fb.getUserSeatId(),_fb.getUnitCode(),_fb.getDeskId(),getUserVO(_request));
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
				UserDeskMenuTemplateMappingDATA.deleteForDeskUnitNWard(_fb.getWardCode(),_fb.getUnitCode(),_fb.getDeskId(),getUserVO(_request));
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
				UserDeskMenuTemplateMappingDATA.deleteForDeskUnitNWardNSeat(_fb.getUserSeatId(),_fb.getWardCode(),_fb.getUnitCode(),_fb.getDeskId(),getUserVO(_request));

			objStatus.add(Status.DONE,"","Record Deleted Successfully");
		}
		catch(HisDataAccessException e)
		{
			isDelete = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			isDelete = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			isDelete = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			isDelete = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return isDelete;
	}	

	//// Saving Template Mapped Records in Desk-Wise Mode & Modifying in all Modes
	public static boolean saveForDeskId(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		boolean isSave = true;
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		try	
		{
			// Add Recent If Any 
			if(!_fb.getDeskMenuId().equalsIgnoreCase("-1"))	addingPartInRowForDeskId(_fb, _request);
			
			List<UserDeskMenuTemplateMasterVO> lstUserDeskMenuTempDeskVO = (List<UserDeskMenuTemplateMasterVO>) session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO);
			
			// Setting Display Order
			Map<String, Integer> mpDeskMenuDisplayOrder = new HashMap<String, Integer>();
			for(UserDeskMenuTemplateMasterVO vo : lstUserDeskMenuTempDeskVO)
			{
				vo.setAdditionMode(_fb.getAdditionMode());
				Integer order = mpDeskMenuDisplayOrder.get(vo.getDeskMenuId());
				if(order==null)
				{
					mpDeskMenuDisplayOrder.put(vo.getDeskMenuId(), 1);
					vo.setDisplayOrder("1");
				}
				else
				{
					vo.setDisplayOrder(Integer.toString(order+1));
					mpDeskMenuDisplayOrder.put(vo.getDeskMenuId(), order+1);
				}
				vo.setUserSeatId((_fb.getUserSeatId()==null || _fb.getUserSeatId().trim().equals("-1") || _fb.getUserSeatId().trim().equals(""))?null:_fb.getUserSeatId().trim());
				vo.setUnitCode((_fb.getUnitCode()==null || _fb.getUnitCode().trim().equals("-1") || _fb.getUnitCode().trim().equals(""))?null:_fb.getUnitCode().trim());
				vo.setWardCode((_fb.getWardCode()==null || _fb.getWardCode().trim().equals("-1") || _fb.getWardCode().trim().equals(""))?null:_fb.getWardCode().trim());
			}
			
			UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO = new UserDeskMenuTemplateMasterVO[lstUserDeskMenuTempDeskVO.size()]; 
			userDeskMenuDeskVO = lstUserDeskMenuTempDeskVO.toArray(userDeskMenuDeskVO);
			
			UserDeskMenuTemplateMappingDATA.saveForDeskId(userDeskMenuDeskVO, getUserVO(_request));

			objStatus.add(Status.DONE,"","Record Saved Successfully");
		}
		catch(HisDataAccessException e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
		return isSave;
	}	

	//// Setting First Step/Unit Selection Essentials for Adding except Desk-Wise Mode
	public static void getDeptAndUnit(UserDeskMenuTemplateMappingFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();		
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
			
			// Getting Template List along with Template Category
			List<TemplateMasterVO> templateMasterVO = UserDeskMenuTemplateMappingDATA.getAllTemplatesVO(userVO);
			mp.put(OpdConfig.ARR_TEMPLATE_N_CATEGORY_VO, templateMasterVO);			
			if(templateMasterVO==null || templateMasterVO.size()==0)
			{
				throw new HisRecordNotFoundException("No Template Exists to Map");
			}
						
			// Getting Selected Desk Menus
			DeskMenuMasterVO[] deskMenuMstVO = UserDeskMenuTemplateMappingDATA.getMenuNameBasedOnDeskId(_fb.getDeskId(), userVO);
			mp.put(OpdConfig.ARR_DESK_MENU_N_CATEGORY_VO , deskMenuMstVO);
			if(deskMenuMstVO==null || deskMenuMstVO.length==0)
			{
				throw new HisRecordNotFoundException("No Template-based Menu Found in the Selected Desk");
			}

			// Getting All Department & Unit List
			Map mpDeptNUnit = UserDeskMenuTemplateMappingDATA.getDeptNUnitModeWise(_fb.getAdditionMode(), _fb.getDeskId(), userVO);
			List<Entry> lstDepts = (List<Entry>) mpDeptNUnit.get(OpdConfig.EssentialBO_LIST_ALL_DEPT);
			List<Entry> lstUnits = (List<Entry>) mpDeptNUnit.get(OpdConfig.EssentialBO_LIST_ALL_UNITS);
			if(lstDepts==null || lstDepts.size()==0)
			{
				throw new HisRecordNotFoundException("No Clinical Department Found to Add");
			}
			if(lstUnits==null || lstUnits.size()==0)
			{
				throw new HisRecordNotFoundException("No Clinical Unit Found to Add");
			}
			List<Entry> lstUnitsOnly = new ArrayList<Entry>();
			List<Entry> lstDeptsOnly = new ArrayList<Entry>();
			for(Entry entObj: lstUnits)
			{				
				entObj.setValue(entObj.getValue().split("@")[0].trim());
				lstUnitsOnly.add(entObj);
			}
			for(Entry entDept: lstDepts)
			{				
				boolean flag = false;
				for(Entry entUnit: lstUnitsOnly)
					if(entUnit.getValue().substring(0, 3).equals(entDept.getValue()))
					{	flag = true;	break;	}
				if(flag)	lstDeptsOnly.add(entDept);
			}
			
			mp.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDeptsOnly);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_UNITS, lstUnitsOnly);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_WARDS, new ArrayList<Entry>());
			

			// Setting Essentials to map Template with Desk
				// Template List to Add for Selected Desk Menu Initially Empty
			mp.put(OpdConfig.EssentialBO_LIST_ALL_TEMPLATES, new ArrayList<TemplateMasterVO>());
				// Putting All Templates in Map template Id Wise 
			Map<String, TemplateMasterVO> mapTempVOs = new HashMap<String, TemplateMasterVO>();
			for(TemplateMasterVO vo : templateMasterVO)
				mapTempVOs.put(vo.getTemplateId(), vo);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_TEMPLATES_VO_MAP, mapTempVOs);
				// Setting Added Template VO Array Initially Empty
			mp.put(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO , new ArrayList<UserDeskMenuTemplateMasterVO>());
			
				// Setting Form Bean and Session Essentials
			_fb.setIsGoPressed(OpdConfig.STEP0);
			_fb.setLength("0");
			_fb.setAddedLength("0");
			_fb.setIsMappingStart(OpdConfig.NO);
			_fb.setIsModificationStart(OpdConfig.NO);

			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
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
	
	//// Setting Template Add Setup after Unit Selection in Unit-Wise Addition Mode
	public static void getTemplatesToAddInUnitWise(UserDeskMenuTemplateMappingFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			// Setting Selected Unit Name List
			getSelectedUnits(_fb, _request);
			
			// Setting Form Bean and Session Essentials
			_fb.setIsGoPressed(OpdConfig.STEP1);
			_fb.setLength("0");
			_fb.setAddedLength("0");
			_fb.setIsMappingStart(OpdConfig.YES);
			_fb.setIsModificationStart(OpdConfig.NO);

			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
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
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}
	
	//// Getting Selected Units
	private static void getSelectedUnits(UserDeskMenuTemplateMappingFB _fb, HttpServletRequest _request)
	{
		//Status objStatus=new Status();
		try
		{
			HttpSession session = _request.getSession();
			
			// Setting Selected Unit Name List
			List<Entry> lst = (ArrayList<Entry>)session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_UNITS);
			List<Entry> alUnits = new ArrayList<Entry>();
			String[] selunits = _fb.getSelectedUnits();
			
			for(String unitCode : selunits)
				for(Entry entobj : lst)
					if(unitCode.equals(entobj.getValue()))
					{
						Entry newobj=new Entry();
						newobj.setValue(entobj.getValue());
						newobj.setLabel(entobj.getLabel());
						alUnits.add(newobj);
					}
			//_fb.setSelectedUnitsName(alUnits);
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS, alUnits);			
			
			//objStatus = (Status)_request.getAttribute(Config.STATUS_OBJECT);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			//_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}	
	
	//// Saving Template Mapped Records in Unit-Wise Addition Mode
	public static boolean saveForUnitWise(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		boolean isSave = true;
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		try	
		{
			// Add Recent If Any 
			if(!_fb.getDeskMenuId().equalsIgnoreCase("-1"))	addingPartInRowForDeskId(_fb, _request);
			
			List<UserDeskMenuTemplateMasterVO> lstUserDeskMenuTempDeskVO = (List<UserDeskMenuTemplateMasterVO>) session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO);
			
			// Setting Display Order
			Map<String, Integer> mpDeskMenuDisplayOrder = new HashMap<String, Integer>();
			for(UserDeskMenuTemplateMasterVO vo : lstUserDeskMenuTempDeskVO)
			{
				vo.setAdditionMode("-1");	// Setting in Non Desk-Wise Mode No need for Deletion
				Integer order = mpDeskMenuDisplayOrder.get(vo.getDeskMenuId());
				if(order==null)
				{
					mpDeskMenuDisplayOrder.put(vo.getDeskMenuId(), 1);
					vo.setDisplayOrder("1");
				}
				else
				{
					vo.setDisplayOrder(Integer.toString(order+1));
					mpDeskMenuDisplayOrder.put(vo.getDeskMenuId(), order+1);
				}
				vo.setUserSeatId(null);
				vo.setUnitCode(null);
				vo.setWardCode(null);
			}
			
			List<UserDeskMenuTemplateMasterVO> lstSaveFinal = new ArrayList<UserDeskMenuTemplateMasterVO>();
			List<Entry> lstSelectedUnits = (List<Entry>)session.getAttribute(OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS);
			for(Entry entUnit: lstSelectedUnits)
			{
				for(UserDeskMenuTemplateMasterVO vo : lstUserDeskMenuTempDeskVO)
				{
					UserDeskMenuTemplateMasterVO newVO = new UserDeskMenuTemplateMasterVO();
					WebUTIL.populate(newVO, vo);
					newVO.setUnitCode(entUnit.getValue());
					lstSaveFinal.add(newVO);
				}
			}			
			
			UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO = new UserDeskMenuTemplateMasterVO[lstSaveFinal.size()]; 
			userDeskMenuDeskVO = lstSaveFinal.toArray(userDeskMenuDeskVO);
			
			UserDeskMenuTemplateMappingDATA.saveForDeskId(userDeskMenuDeskVO, getUserVO(_request));
			objStatus.add(Status.DONE,"","Record Saved Successfully");
		}
		catch(HisDataAccessException e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}
		return isSave;
	}	
	
	//// Setting Seat Selection Essentials for Adding
	public static void getGroupList(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session = _request.getSession();
		Map mp = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);			
			
			// Setting Selected Unit Name List
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
				getSelectedUnits(_fb, _request);
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
				getSelectedWards(_fb, _request);
			

			List<Entry> lstUnits = (List<Entry>)session.getAttribute(OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS);
			List<Entry> lstWards = (List<Entry>)session.getAttribute(OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_WARDS);
						
			// Getting All Department & Unit List
			Map mpGroupNSeat = null;
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
				mpGroupNSeat = UserDeskMenuTemplateMappingDATA.getGroupNSeatModeWise(_fb.getAdditionMode(), _fb.getDeskId(), lstUnits, null, userVO);
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
				mpGroupNSeat = UserDeskMenuTemplateMappingDATA.getGroupNSeatModeWise(_fb.getAdditionMode(), _fb.getDeskId(), lstUnits, lstWards, userVO);
			
			List<Entry> lstGroups = (List<Entry>) mpGroupNSeat.get(OpdConfig.ESSENTIALBO_ALL_GROUP_LIST);
			List<Entry> lstSeats = (List<Entry>) mpGroupNSeat.get(OpdConfig.EssentialBO_LIST_ALL_SEATS);
			if(lstGroups==null || lstGroups.size()==0)
			{
				throw new HisRecordNotFoundException("No Group Found to Add");
			}
			if(lstSeats==null || lstSeats.size()==0)
			{
				throw new HisRecordNotFoundException("No Seat Found to Add");
			}
			
			List<Entry> lstGroupsOnly = new ArrayList<Entry>();
			for(Entry entGroup: lstGroups)
			{				
				boolean flag = false;
				for(Entry entSeat: lstSeats)
					if(entSeat.getValue().split("@")[1].equals(entGroup.getValue()))
					{	flag = true;	break;	}
				if(flag)	lstGroupsOnly.add(entGroup);
			}
			
			mp.put(OpdConfig.ESSENTIALBO_ALL_GROUP_LIST, lstGroupsOnly);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_SEATS, lstSeats);

			// Setting Form Bean and Session Essentials
			_fb.setIsGoPressed(OpdConfig.STEP1);

			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW,e.getMessage(),"");
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
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}
	
	//// Setting Template Add Setup after Seat Selection in Unit Seat-Wise Addition Mode
	public static void getTemplatesToAddInUnitSeatWise(UserDeskMenuTemplateMappingFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			// Setting Selected Seats Name List
			getSelectedSeats(_fb, _request);
			
			// Setting Form Bean and Session Essentials
			_fb.setIsGoPressed(OpdConfig.STEP2);
			_fb.setLength("0");
			_fb.setAddedLength("0");
			_fb.setIsMappingStart(OpdConfig.YES);
			_fb.setIsModificationStart(OpdConfig.NO);

			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
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
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}
	
	//// Getting Selected Units
	private static void getSelectedSeats(UserDeskMenuTemplateMappingFB _fb, HttpServletRequest _request)
	{
		//Status objStatus=new Status();
		try
		{
			HttpSession session = _request.getSession();
			
			// Setting Selected Seat List
			List<Entry> lst = (ArrayList<Entry>)session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_SEATS);
			List<Entry> alSeats = new ArrayList<Entry>();
			String[] selseats=_fb.getSelectedSeats();
			
			for(String seatCode : selseats)
				for(Entry entobj : lst)
					if(seatCode.equals(entobj.getValue()))
					{
						Entry newobj=new Entry();
						newobj.setValue(entobj.getValue().split("@")[0]);
						newobj.setLabel(entobj.getLabel());
						alSeats.add(newobj);
					}
			//_fb.setSelectedSeatsName(alSeats);
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_SEATS, alSeats);			
			
			//objStatus = (Status)_request.getAttribute(Config.STATUS_OBJECT);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			//_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}	
	
	//// Saving Template Mapped Records in Unit Seat-Wise Addition Mode
	public static boolean saveForUnitSeat(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		boolean isSave = true;
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		try	
		{
			// Add Recent If Any 
			if(!_fb.getDeskMenuId().equalsIgnoreCase("-1"))	addingPartInRowForDeskId(_fb, _request);
			
			List<UserDeskMenuTemplateMasterVO> lstUserDeskMenuTempDeskVO = (List<UserDeskMenuTemplateMasterVO>) session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO);
			
			// Setting Display Order
			Map<String, Integer> mpDeskMenuDisplayOrder = new HashMap<String, Integer>();
			for(UserDeskMenuTemplateMasterVO vo : lstUserDeskMenuTempDeskVO)
			{
				vo.setAdditionMode("-1");	// Setting in Non Desk-Wise Mode No need for Deletion
				Integer order = mpDeskMenuDisplayOrder.get(vo.getDeskMenuId());
				if(order==null)
				{
					mpDeskMenuDisplayOrder.put(vo.getDeskMenuId(), 1);
					vo.setDisplayOrder("1");
				}
				else
				{
					vo.setDisplayOrder(Integer.toString(order+1));
					mpDeskMenuDisplayOrder.put(vo.getDeskMenuId(), order+1);
				}
				vo.setUserSeatId(null);
				vo.setUnitCode(null);
				vo.setWardCode(null);
			}
			
			List<UserDeskMenuTemplateMasterVO> lstSaveFinal = new ArrayList<UserDeskMenuTemplateMasterVO>();
			List<Entry> lstSelectedUnits = (List<Entry>)session.getAttribute(OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS);
			List<Entry> lstSelectedSeats = (List<Entry>)session.getAttribute(OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_SEATS);
			for(Entry entUnit: lstSelectedUnits)
			{
				for(Entry entSeat: lstSelectedSeats)
				{
					for(UserDeskMenuTemplateMasterVO vo : lstUserDeskMenuTempDeskVO)
					{
						UserDeskMenuTemplateMasterVO newVO = new UserDeskMenuTemplateMasterVO();
						WebUTIL.populate(newVO, vo);
						newVO.setUnitCode(entUnit.getValue());
						newVO.setUserSeatId(entSeat.getValue());
						lstSaveFinal.add(newVO);
					}
				}
			}			
			
			UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO = new UserDeskMenuTemplateMasterVO[lstSaveFinal.size()]; 
			userDeskMenuDeskVO = lstSaveFinal.toArray(userDeskMenuDeskVO);
			
			UserDeskMenuTemplateMappingDATA.saveForDeskId(userDeskMenuDeskVO, getUserVO(_request));
			objStatus.add(Status.DONE,"","Record Saved Successfully");
		}
		catch(HisDataAccessException e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}
		return isSave;
	}
	
	//// Setting Ward Selection Essentials for Adding
	public static void getWardInUnitWardWiseMode(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);			

			List lstWards = new ArrayList();
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
				lstWards = UserDeskMenuTemplateMappingDATA.getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(_fb.getDeskId(), userVO, _fb.getUnitCode());
			else if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
				lstWards = UserDeskMenuTemplateMappingDATA.getWardExceptAssignedByDeskTypeForUnitWardSeat(_fb.getDeskId(), userVO, _fb.getUnitCode());

			if(lstWards==null || lstWards.size()==0)
			{
				WebUTIL.setAttributeInSession(_request, OpdConfig.EssentialBO_LIST_ALL_WARDS, new ArrayList<Entry>());
				throw new HisRecordNotFoundException("No Ward Found to Add");
			}
			mp.put(OpdConfig.EssentialBO_LIST_ALL_WARDS, lstWards);

			// Setting Form Bean and Session Essentials
			WebUTIL.setMapInSession(mp,_request);
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW,e.getMessage(),"");
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
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}
	
	//// Setting Template Add Setup after Ward Selection in Unit Ward-Wise Addition Mode
	public static void getTemplatesToAddInUnitWardWise(UserDeskMenuTemplateMappingFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			// Setting Selected Wards Name List
			getSelectedWards(_fb, _request);
			
			// Setting Form Bean and Session Essentials
			_fb.setIsGoPressed(OpdConfig.STEP1);
			_fb.setLength("0");
			_fb.setAddedLength("0");
			_fb.setIsMappingStart(OpdConfig.YES);
			_fb.setIsModificationStart(OpdConfig.NO);

			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
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
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}

	//// Getting Selected Wards
	private static void getSelectedWards(UserDeskMenuTemplateMappingFB _fb, HttpServletRequest _request)
	{
		//Status objStatus=new Status();
		try
		{
			HttpSession session = _request.getSession();
			
			// Setting Selected Unit Name List
			List<Entry> lst = (ArrayList<Entry>)session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_UNITS);
			List<Entry> alUnits = new ArrayList<Entry>();
			for(Entry entobj : lst)
				if(_fb.getUnitCode().equals(entobj.getValue()))
				{
					Entry newobj=new Entry();
					newobj.setValue(entobj.getValue());
					newobj.setLabel(entobj.getLabel());
					alUnits.add(newobj);
				}
			WebUTIL.setAttributeInSession(_request, OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS, alUnits);			
			

			// Setting Selected Ward List
			lst = (ArrayList<Entry>)session.getAttribute(OpdConfig.EssentialBO_LIST_ALL_WARDS);
			List<Entry> alWards = new ArrayList<Entry>();
			String[] selWards=_fb.getSelectedWards();
			
			for(String seatCode : selWards)
				for(Entry entobj : lst)
					if(seatCode.equals(entobj.getValue()))
					{
						Entry newobj=new Entry();
						newobj.setValue(entobj.getValue().split("@")[0]);
						newobj.setLabel(entobj.getLabel());
						alWards.add(newobj);
					}
			WebUTIL.setAttributeInSession(_request, OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_WARDS, alWards);			
			
			//objStatus = (Status)_request.getAttribute(Config.STATUS_OBJECT);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			//_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}	
	
	////Saving Template Mapped Records in Unit Ward-Wise Addition Mode
	public static boolean saveForUnitWardWise(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		boolean isSave = true;
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		
		try	
		{
			// Add Recent If Any 
			if(!_fb.getDeskMenuId().equalsIgnoreCase("-1"))	addingPartInRowForDeskId(_fb, _request);
			
			List<UserDeskMenuTemplateMasterVO> lstUserDeskMenuTempDeskVO = (List<UserDeskMenuTemplateMasterVO>) session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO);
			
			// Setting Display Order
			Map<String, Integer> mpDeskMenuDisplayOrder = new HashMap<String, Integer>();
			for(UserDeskMenuTemplateMasterVO vo : lstUserDeskMenuTempDeskVO)
			{
				vo.setAdditionMode("-1");	// Setting in Non Desk-Wise Mode No need for Deletion
				Integer order = mpDeskMenuDisplayOrder.get(vo.getDeskMenuId());
				if(order==null)
				{
					mpDeskMenuDisplayOrder.put(vo.getDeskMenuId(), 1);
					vo.setDisplayOrder("1");
				}
				else
				{
					vo.setDisplayOrder(Integer.toString(order+1));
					mpDeskMenuDisplayOrder.put(vo.getDeskMenuId(), order+1);
				}
				vo.setUserSeatId(null);
				vo.setUnitCode(null);
				vo.setWardCode(null);
			}
			
			List<UserDeskMenuTemplateMasterVO> lstSaveFinal = new ArrayList<UserDeskMenuTemplateMasterVO>();
			List<Entry> lstSelectedUnits = (List<Entry>)session.getAttribute(OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS);
			List<Entry> lstSelectedWards = (List<Entry>)session.getAttribute(OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_WARDS);
			for(Entry entUnit: lstSelectedUnits)
			{
				for(Entry entWard: lstSelectedWards)
				{
					for(UserDeskMenuTemplateMasterVO vo : lstUserDeskMenuTempDeskVO)
					{
						UserDeskMenuTemplateMasterVO newVO = new UserDeskMenuTemplateMasterVO();
						WebUTIL.populate(newVO, vo);
						newVO.setUnitCode(entUnit.getValue());
						newVO.setWardCode(entWard.getValue());
						lstSaveFinal.add(newVO);
					}
				}
			}			
			
			UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO = new UserDeskMenuTemplateMasterVO[lstSaveFinal.size()]; 
			userDeskMenuDeskVO = lstSaveFinal.toArray(userDeskMenuDeskVO);
			
			UserDeskMenuTemplateMappingDATA.saveForDeskId(userDeskMenuDeskVO, getUserVO(_request));
			objStatus.add(Status.DONE,"","Record Saved Successfully");
		}
		catch(HisDataAccessException e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}
		return isSave;
	}	
	
	//// Setting Template Add Setup after Seat Selection in Unit Ward Seat-Wise Addition Mode
	public static void getTemplatesToAddInUnitWardSeatWise(UserDeskMenuTemplateMappingFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			// Setting Selected Seats Name List
			getSelectedSeats(_fb, _request);
			
			// Setting Form Bean and Session Essentials
			_fb.setIsGoPressed(OpdConfig.STEP2);
			_fb.setLength("0");
			_fb.setAddedLength("0");
			_fb.setIsMappingStart(OpdConfig.YES);
			_fb.setIsModificationStart(OpdConfig.NO);

			objStatus.add(Status.NEW);
			objStatus.add(Status.TRANSINPROCESS);
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
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}
	
	////Saving Template Mapped Records in Unit Ward Seat-Wise Addition Mode
	public static boolean saveForUnitWardSeatWise(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		boolean isSave = true;
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		
		try	
		{
			// Add Recent If Any 
			if(!_fb.getDeskMenuId().equalsIgnoreCase("-1"))	addingPartInRowForDeskId(_fb, _request);
			
			List<UserDeskMenuTemplateMasterVO> lstUserDeskMenuTempDeskVO = (List<UserDeskMenuTemplateMasterVO>) session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO);
			
			// Setting Display Order
			Map<String, Integer> mpDeskMenuDisplayOrder = new HashMap<String, Integer>();
			for(UserDeskMenuTemplateMasterVO vo : lstUserDeskMenuTempDeskVO)
			{
				vo.setAdditionMode("-1");	// Setting in Non Desk-Wise Mode No need for Deletion
				Integer order = mpDeskMenuDisplayOrder.get(vo.getDeskMenuId());
				if(order==null)
				{
					mpDeskMenuDisplayOrder.put(vo.getDeskMenuId(), 1);
					vo.setDisplayOrder("1");
				}
				else
				{
					vo.setDisplayOrder(Integer.toString(order+1));
					mpDeskMenuDisplayOrder.put(vo.getDeskMenuId(), order+1);
				}
				vo.setUserSeatId(null);
				vo.setUnitCode(null);
				vo.setWardCode(null);
			}
			
			List<UserDeskMenuTemplateMasterVO> lstSaveFinal = new ArrayList<UserDeskMenuTemplateMasterVO>();
			List<Entry> lstSelectedUnits = (List<Entry>)session.getAttribute(OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS);
			List<Entry> lstSelectedWards = (List<Entry>)session.getAttribute(OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_WARDS);
			List<Entry> lstSelectedSeats = (List<Entry>)session.getAttribute(OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_SEATS);
			for(Entry entUnit: lstSelectedUnits)
			{
				for(Entry entWard: lstSelectedWards)
				{
					for(Entry entSeat: lstSelectedSeats)
					{
						for(UserDeskMenuTemplateMasterVO vo : lstUserDeskMenuTempDeskVO)
						{
							UserDeskMenuTemplateMasterVO newVO = new UserDeskMenuTemplateMasterVO();
							WebUTIL.populate(newVO, vo);
							newVO.setUnitCode(entUnit.getValue());
							newVO.setWardCode(entWard.getValue());
							newVO.setUserSeatId(entSeat.getValue());
							lstSaveFinal.add(newVO);
						}
					}
				}
			}			
			
			UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO = new UserDeskMenuTemplateMasterVO[lstSaveFinal.size()]; 
			userDeskMenuDeskVO = lstSaveFinal.toArray(userDeskMenuDeskVO);
			
			UserDeskMenuTemplateMappingDATA.saveForDeskId(userDeskMenuDeskVO, getUserVO(_request));
			objStatus.add(Status.DONE,"","Record Saved Successfully");
		}
		catch(HisDataAccessException e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}
		return isSave;
	}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public static void getWardInUnitWardSeatWiseMode(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
				
		List essentialListallWardsListNotAssignedSeat=new ArrayList();
		
		Map mp= new HashMap();

		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			String unitCodeWithDiagCodeType = _fb.getUnitCode();
			String unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("@"));		
			String deskType=_fb.getDeskType();
						

			essentialListallWardsListNotAssignedSeat=UserDeskMenuTemplateMappingDATA.getWardExceptAssignedByDeskTypeForUnitWardSeat(deskType,userVO,unitCode);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_WARDS_NOT_ASSIGNED_SEAT,essentialListallWardsListNotAssignedSeat);
			WebUTIL.setMapInSession(mp,_request);

			//objStatus.add(Status.INPROCESS);
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

	public static void saveForUnitSeatWard(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		UserDeskMenuTemplateMasterVO[] userDeskMenuTempDeskVO=null;
		UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO=null;
		int count=0,j=0;
		
		String unitCodeWithDiagCodeType = "";
		String unitCode = "";
		
		try	
		{
			//deleteForDeskId(_fb, _request);
			userDeskMenuTempDeskVO=(UserDeskMenuTemplateMasterVO[])session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO);
			
			if(userDeskMenuTempDeskVO==null && _fb.getDeskMenuId().equals("-1"))
				count=0;
			if(userDeskMenuTempDeskVO==null && !_fb.getDeskMenuId().equals("-1"))
				count=1;
			if(userDeskMenuTempDeskVO!=null && _fb.getDeskMenuId().equals("-1"))
				count=userDeskMenuTempDeskVO.length;
			if(userDeskMenuTempDeskVO!=null && !_fb.getDeskMenuId().equals("-1"))
				count=userDeskMenuTempDeskVO.length+1;
			
				int x= _fb.getSelectedWards().length;
				int z=_fb.getSelectedSeats().length;
				
				if(userDeskMenuTempDeskVO!=null)
				{
					int y=userDeskMenuTempDeskVO.length+1;
					userDeskMenuDeskVO=new UserDeskMenuTemplateMasterVO[x*y*z];
				}
				else
					userDeskMenuDeskVO=new UserDeskMenuTemplateMasterVO[x*z];
				
				unitCodeWithDiagCodeType = _fb.getUnitCode();
				unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("@"));
				
				for(int i=0,k=0;k<_fb.getSelectedWards().length;k++)
				{	
					for(int m=0;m<_fb.getSelectedSeats().length;m++)
					{
					if(count > 0)
					{
						
						
						if(!_fb.getDeskMenuId().equals("-1"))
						{
							userDeskMenuDeskVO[i]=new UserDeskMenuTemplateMasterVO();
							userDeskMenuDeskVO[i].setTemplateId(_fb.getTemplateId());
							userDeskMenuDeskVO[i].setDeskMenuId(_fb.getDeskMenuId());
							userDeskMenuDeskVO[i].setIsDefault(_fb.getIsDefault());
							userDeskMenuDeskVO[i].setDeskId(_fb.getDeskId());
						//	userDeskMenuDeskVO[j].setUnitCode(_fb.getUnitCode());
							userDeskMenuDeskVO[i].setSeatId(_fb.getSelectedSeats()[m]);
							_fb.setUserSeatId(_fb.getSelectedSeats()[m]);
							userDeskMenuDeskVO[i].setWardCode(_fb.getSelectedWards()[k]);
							_fb.setWardCode(_fb.getSelectedWards()[k]);
							_fb.setSeatId(_fb.getSelectedSeats()[m]);
							
							userDeskMenuDeskVO[i].setUnitCode(unitCode);
							_fb.setUnitCode(unitCode);
							deleteForDeskId(_fb, _request);
							i++;
						
						}
						
						if(userDeskMenuTempDeskVO!=null)
						{
						
							//int j=1;
							int len=userDeskMenuTempDeskVO.length;
							for(int a=0;a<len;a++)
							{
								userDeskMenuDeskVO[i]=new UserDeskMenuTemplateMasterVO();
								
								userDeskMenuDeskVO[i].setTemplateId(userDeskMenuTempDeskVO[a].getTemplateId());
								userDeskMenuDeskVO[i].setDeskMenuId(userDeskMenuTempDeskVO[a].getDeskMenuId());
								userDeskMenuDeskVO[i].setIsDefault(userDeskMenuTempDeskVO[a].getIsDefault());
								userDeskMenuDeskVO[i].setDeskId(_fb.getDeskId());
								//userDeskMenuDeskVO[j].setUnitCode(_fb.getUnitCode());
								userDeskMenuDeskVO[i].setSeatId(_fb.getSelectedSeats()[m]);
								_fb.setSeatId(_fb.getSelectedSeats()[m]);
								_fb.setUserSeatId(_fb.getSelectedSeats()[m]);
								userDeskMenuDeskVO[i].setWardCode(_fb.getSelectedWards()[k]);
								_fb.setWardCode(_fb.getSelectedWards()[k]);
								userDeskMenuDeskVO[i].setUnitCode(unitCode);
								_fb.setUnitCode(unitCode);
								deleteForDeskId(_fb, _request);
								i++;
								
							}
						
						}
					}
						
				}
			}
				UserDeskMenuTemplateMappingDATA.saveForDeskId(userDeskMenuDeskVO,getUserVO(_request));
			
			objStatus.add(Status.NEW);
			objStatus.add(Status.DONE,"","Record added Successfully");
			
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
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void saveForUnitWard(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		UserDeskMenuTemplateMasterVO[] userDeskMenuTempDeskVO=null;
		UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO=null;
		int count=0,j=0;
	
		try	
		{
			//deleteForDeskId(_fb, _request);
			userDeskMenuTempDeskVO=(UserDeskMenuTemplateMasterVO[])session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO);
			
			if(userDeskMenuTempDeskVO==null && _fb.getDeskMenuId().equals("-1"))
				count=0;
			if(userDeskMenuTempDeskVO==null && !_fb.getDeskMenuId().equals("-1"))
				count=1;
			if(userDeskMenuTempDeskVO!=null && _fb.getDeskMenuId().equals("-1"))
				count=userDeskMenuTempDeskVO.length;
			if(userDeskMenuTempDeskVO!=null && !_fb.getDeskMenuId().equals("-1"))
				count=userDeskMenuTempDeskVO.length+1;
			
				int x= _fb.getSelectedWards().length;
				int y=userDeskMenuTempDeskVO.length+1;
				
				userDeskMenuDeskVO=new UserDeskMenuTemplateMasterVO[x*y];
				
				for(int i=0,k=0;k<_fb.getSelectedWards().length;k++)
				{	
					
					if(count > 0)
					{
						
						
						if(!_fb.getDeskMenuId().equals("-1"))
						{
							userDeskMenuDeskVO[i]=new UserDeskMenuTemplateMasterVO();
							userDeskMenuDeskVO[i].setTemplateId(_fb.getTemplateId());
							userDeskMenuDeskVO[i].setDeskMenuId(_fb.getDeskMenuId());
							userDeskMenuDeskVO[i].setIsDefault(_fb.getIsDefault());
							userDeskMenuDeskVO[i].setDeskId(_fb.getDeskId());
						//	userDeskMenuDeskVO[j].setUnitCode(_fb.getUnitCode());
							//userDeskMenuDeskVO[i].setSeatId(_fb.getSelectedSeats()[m]);
							userDeskMenuDeskVO[i].setWardCode(_fb.getSelectedWards()[k]);
							//_fb.setSeatId(_fb.getSelectedSeats()[m]);
							userDeskMenuDeskVO[i].setUnitCode(_fb.getUnitCode());
						
							deleteForDeskId(_fb, _request);
							i++;
						
						}
						
						if(userDeskMenuTempDeskVO!=null)
						{
						
							//int j=1;
							int len=userDeskMenuTempDeskVO.length;
							for(int a=0;a<len;a++)
							{
								userDeskMenuDeskVO[i]=new UserDeskMenuTemplateMasterVO();
								
								userDeskMenuDeskVO[i].setTemplateId(userDeskMenuTempDeskVO[a].getTemplateId());
								userDeskMenuDeskVO[i].setDeskMenuId(userDeskMenuTempDeskVO[a].getDeskMenuId());
								userDeskMenuDeskVO[i].setIsDefault(userDeskMenuTempDeskVO[a].getIsDefault());
								userDeskMenuDeskVO[i].setDeskId(_fb.getDeskId());
								//userDeskMenuDeskVO[j].setUnitCode(_fb.getUnitCode());
								//userDeskMenuDeskVO[i].setSeatId(_fb.getSelectedSeats()[m]);
							//	_fb.setSeatId(_fb.getSelectedSeats()[m]);
								userDeskMenuDeskVO[i].setWardCode(_fb.getSelectedWards()[k]);
								userDeskMenuDeskVO[i].setUnitCode(_fb.getUnitCode());
								
								deleteForDeskId(_fb, _request);
								i++;
								
							}
						
						}
					
				}
			}
				UserDeskMenuTemplateMappingDATA.saveForDeskId(userDeskMenuDeskVO,getUserVO(_request));
			
			objStatus.add(Status.NEW);
			objStatus.add(Status.DONE,"","Record added Successfully");
			
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
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	//* Getting Desk List By Units with seat is null
	public static void getDeskListByUnit(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstDesks=new ArrayList();
		Map mp= new HashMap();
		
		try
		{
			String unitCode=_fb.getUnitCode();
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			lstDesks=UserDeskMenuTemplateMappingDATA.getDeskListByUnit(unitCode,userVO);
			mp.put(OpdConfig.EssentialBO_LIST_USER_DESKMENU_DESKS_WITHOUTSEAT,lstDesks);
			
			WebUTIL.setMapInSession(mp,_request);
			if(lstDesks.size()==0)
			{
				objStatus.add(Status.DONE,"No Desk Exist For Selected Unit","");
			}
			else
			objStatus.add(Status.NEW);
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
	
	public static void saveForUnit(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstSelUnits=new ArrayList();
		
		HttpSession session = _request.getSession();
		UserDeskMenuTemplateMasterVO[] userDeskMenuTempDeskVO=null;
		UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO=null;
		int count;
		int finalCount;
		
		try
		{
			
			lstSelUnits=(ArrayList)session.getAttribute(OpdConfig.OPD_TEMPLATEADD_SELECTED_UNITS);
			
			
			userDeskMenuTempDeskVO=(UserDeskMenuTemplateMasterVO[])session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO);
			
			if(userDeskMenuTempDeskVO==null)
				count=1;
			else
				count=userDeskMenuTempDeskVO.length+1;
			finalCount=count*lstSelUnits.size();
			
			userDeskMenuDeskVO=new UserDeskMenuTemplateMasterVO[finalCount];
			
			for(int i=0;i<lstSelUnits.size();i++)
			{
				userDeskMenuDeskVO[i]=new UserDeskMenuTemplateMasterVO();
				
				userDeskMenuDeskVO[i].setTemplateId(_fb.getTemplateId());
				userDeskMenuDeskVO[i].setDeskMenuId(_fb.getDeskMenuId());
				userDeskMenuDeskVO[i].setIsDefault(_fb.getIsDefault());
				userDeskMenuDeskVO[i].setDeskId(_fb.getDeskId());
				userDeskMenuDeskVO[i].setDeptUnitCode(((Entry)lstSelUnits.get(i)).getValue());
				
			}
			
			if(userDeskMenuTempDeskVO!=null)
			{
				
				int j=lstSelUnits.size();
				int len=userDeskMenuTempDeskVO.length;
				
				for(int k=0;k<lstSelUnits.size();k++)
				{
					for(int i=0;i<len;i++)
					{
						userDeskMenuDeskVO[j]=new UserDeskMenuTemplateMasterVO();
						
						userDeskMenuDeskVO[j].setTemplateId(userDeskMenuTempDeskVO[i].getTemplateId());
						userDeskMenuDeskVO[j].setDeskMenuId(userDeskMenuTempDeskVO[i].getDeskMenuId());
						userDeskMenuDeskVO[j].setIsDefault(userDeskMenuTempDeskVO[i].getIsDefault());
						userDeskMenuDeskVO[j].setDeskId(_fb.getDeskId());
						userDeskMenuDeskVO[j].setDeptUnitCode(((Entry)lstSelUnits.get(k)).getValue());
						j++;
					}
				}	
			}
			
			UserDeskMenuTemplateMappingDATA.saveForUnit(userDeskMenuDeskVO,getUserVO(_request));
			objStatus.add(Status.DONE,"","Record added Successfully");
			
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
	
	public static void getDeskListByUnitNSeat(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstDesk=new ArrayList();
		
		try
		{
			String unitCode=_fb.getUnitCode();
			String seatId=_fb.getUserSeatId();
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			lstDesk=UserDeskMenuTemplateMappingDATA.getDeskListByUnitNSeat(seatId,unitCode,userVO);
			WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_DESK_BASED_ON_UNIT_N_SEAT, lstDesk); 
			
			if(lstDesk.size()==0)
			{
				objStatus.add(Status.DONE,"No Desk Exist For Selected Seat","");
			}
			else
			objStatus.add(Status.NEW);
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
	
	public static void getDeskListByUnitNWard(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstDesk=new ArrayList();
		
		try
		{
			String unitCode=_fb.getUnitCode();
			String wardCode=_fb.getWardCode();
			UserVO userVO = getUserVO(_request);
			
			
			lstDesk=UserDeskMenuTemplateMappingDATA.getDeskListByUnitNWard(wardCode,unitCode,userVO);
			WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_DESK_BASED_ON_UNIT_N_WARD, lstDesk); 
			
			if(lstDesk.size()==0)
			{
				objStatus.add(Status.DONE,"No Ward Exist For Selected Unit","");
			}
			else
			objStatus.add(Status.NEW);
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
	
	public static void getDeskListByUnitNWardNSeat(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstDesk=new ArrayList();
		
		try
		{
			String unitCode=_fb.getUnitCode();
			String wardCode=_fb.getWardCode();
			String seatId=_fb.getUserSeatId();
			UserVO userVO = getUserVO(_request);
			
			
			lstDesk=UserDeskMenuTemplateMappingDATA.getDeskListByUnitNWardNSeat(seatId,wardCode,unitCode,userVO);
			WebUTIL.setAttributeInSession(_request,OpdConfig.EssentialBO_LIST_DESK_BASED_ON_UNIT_N_WARD_N_SEAT, lstDesk); 
			
			if(lstDesk.size()==0)
			{
				objStatus.add(Status.DONE,"No Desk Exist For Selected Seat","");
			}
			else
			objStatus.add(Status.NEW);
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
	
	public static void setSourceSeats(UserDeskMenuTemplateMappingFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		
		List lstSeats=new ArrayList();
		List lstGroup=new ArrayList();
		Map mp= new HashMap();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			String groupCode=_fb.getGroup();
			
			if(_fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
			{
				String wards[]=_fb.getSelectedWards();
				lstSeats=UserDeskMenuTemplateMappingDATA.getSeatsByNotUnits(wards,userVO,groupCode);
			}
				
			else
			{
				String units[]=null;
				units=_fb.getSelectedUnits();
				for(int i =0;i<_fb.getSelectedUnits().length;i++)
				{
					String unitCodeWithDiagCodeType = _fb.getSelectedUnits()[i];
					units=unitCodeWithDiagCodeType.split("@");
					
					
				}
					
				lstSeats=UserDeskMenuTemplateMappingDATA.getSeatsByNotUnits(units,userVO,groupCode);
			}
				
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
	}*/
		
}
