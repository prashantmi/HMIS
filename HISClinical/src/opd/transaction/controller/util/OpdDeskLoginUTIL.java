package opd.transaction.controller.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import opd.OpdConfig;
import opd.transaction.controller.data.OpdDeskLoginDATA;
import opd.transaction.controller.fb.OpdDeskLoginFb;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;

public class OpdDeskLoginUTIL extends ControllerUTIL
{
	// Getting OPD Desk Login essentials
	public static boolean getOpdDeskEssentials(OpdDeskLoginFb opdDeskLoginFb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			opdDeskLoginFb.setDepartmentUnitCode("-1");
			opdDeskLoginFb.setRoomCode("-1");
			
			List list = OpdDeskLoginDATA.getOpdDeskEssentials(getUserVO(request));
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_DESK_UNIT_LIST, list);
			if (list.size() == 1)
			{
				Entry entry = (Entry) list.get(0);
				String unitCode = entry.getValue();
				opdDeskLoginFb.setDepartmentUnitCode(unitCode);
				flag = false;
			}
			else
			{
				flag = true;
				opdDeskLoginFb.setShowRommList("0");
			}
			objStatus.add(Status.INPROCESS);
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

	// Getting Room List
	public static boolean getRoomByUnitCode(OpdDeskLoginFb opdDeskLoginFb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		List list = new ArrayList();
		boolean flag = true;
		try
		{
			String unitCodeWithDiagCodeType = opdDeskLoginFb.getDepartmentUnitCode();
			String unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^"));

			opdDeskLoginFb.setRoomCode("-1");
			list = OpdDeskLoginDATA.getRoomsByUnitCode(getUserVO(request), unitCode);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ROOM_LIST, list);
			
			if (list.size() == 1)
			{
				Entry entry = (Entry) list.get(0);
				String roomCode = entry.getValue();
				opdDeskLoginFb.setRoomCode(roomCode);
				flag = false;
			}
			else
			{
				flag = true;
				opdDeskLoginFb.setShowRommList("1");
			}
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ROOM_LIST, list);
			opdDeskLoginFb.setShowRommList("0");
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
	
	// Setting OPD Desk Related Setting in Session
	public static void goToOpdDesk(OpdDeskLoginFb _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		String deskName = "", unitName = "", roomName = "";
		HttpSession session = null;
		try
		{
			session = _request.getSession();			
			
			// Unit Diagnosis Type Code 
			String diagnosisTypeCodeForUnit = String.valueOf(_fb.getDepartmentUnitCode().charAt(_fb.getDepartmentUnitCode().indexOf("^") + 1));
			
			// Getting Unit Code & Unit Name
			List<Entry> unitList = (List<Entry>) session.getAttribute(OpdConfig.OPD_DESK_UNIT_LIST);
			String unitCode = _fb.getDepartmentUnitCode().substring(0, _fb.getDepartmentUnitCode().indexOf("^"));
			for(Entry entry : unitList)
				if(entry.getValue().substring(0,entry.getValue().indexOf("^")).equals(unitCode))
					unitName=entry.getLabel();

			// Getting Room Code & Room Name
			List<Entry> roomList = (List<Entry>)session.getAttribute(OpdConfig.OPD_ROOM_LIST);
			String roomCode = _fb.getRoomCode();
			for(Entry entry : roomList)
				if(entry.getValue().equals(roomCode))
					roomName=entry.getLabel();
			
			// Desk Name
			deskName = unitName+" "+roomName;
			System.out.println("Desk Name in Util :"+deskName);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_TYPE, DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE, unitCode);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_UNIT_NAME, unitName);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_DIAGNOSIS_TYPE_CODE, diagnosisTypeCodeForUnit);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE, roomCode);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_DESK_NAME, deskName);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_ROWS_SPAN, "50,0%,92%,8%,0");
			
//			Map mp = null;
//			mp = OpdDeskLoginDATA.getICDAllEssentials(getUserVO(_request));
//			DynamicDeskUTIL.setMapInSession(mp, _request);

			objStatus.add(Status.INPROCESS);
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
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// Getting OPD Desk Essentials for the OPD Desk Footer
	public static void getOPDDeskEssentialForFooter(OpdDeskLoginFb _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			Map mp = null;
			mp = OpdDeskLoginDATA.getICDAllEssentials(getUserVO(_request));
			DynamicDeskUTIL.setMapInSession(mp, _request);
			
			objStatus.add(Status.INPROCESS);
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
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
}
