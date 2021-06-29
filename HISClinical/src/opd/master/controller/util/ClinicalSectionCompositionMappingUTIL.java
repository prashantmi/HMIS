package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.ClinicalSectionCompMappingDATA;
import opd.master.controller.data.UserDeskMenuTemplateMappingDATA;
import opd.master.controller.fb.ClinicalSectionCompMapFB;
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

public class ClinicalSectionCompositionMappingUTIL extends  ControllerUTIL
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
	public static void getComposition(ClinicalSectionCompMapFB _fb, HttpServletRequest _request)
	{
		Status objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			//setSysdate(_request);
		
			// Setting Stage for Desk Type Selection & Desk List
			/*_fb.setDeskId("");
			_fb.setUnitCode("");
			_fb.setWardCode("");
			_fb.setUserSeatId("");

			_fb.setHmode("NEW");
			_fb.setIsGoPressed(OpdConfig.STEP0);
			_fb.setIsDeskSelected(OpdConfig.NO);
			_fb.setIsMappingStart(OpdConfig.NO);
			_fb.setIsModificationStart(OpdConfig.NO);
			_fb.setLength("0");*/
			clearSession(_request);
			
			// Setting Desk List
			List<Entry> lstCompositions = new ArrayList<Entry>();
		//	if(!_fb.getHospitalCode().trim().equalsIgnoreCase("-1"))
			//{
				mp = ClinicalSectionCompMappingDATA.getCompositionType(_fb.getHospitalCode(),userVO);			
				lstCompositions = (List<Entry>) mp.get(OpdConfig.COMPOSITION_TYPE);
				if(lstCompositions==null || lstCompositions.size()==0)
				{
					throw new HisRecordNotFoundException("No Compositions Found for Hospital Type");
				}
			//}
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.COMPOSITION_TYPE, lstCompositions);
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
	
}
