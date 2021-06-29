package opd.transaction.controller.util;

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
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.CsultyDeskLoginDATA;
import opd.transaction.controller.data.OpdPatientDeskDATA;
import opd.transaction.controller.fb.CsultyDeskLoginFB;
import opd.transaction.controller.fb.OpdPatientDeskFB;

public class CsultyDeskLoginUTIL extends ControllerUTIL
{
	// Setting Casuality Desk Essentials
	public static boolean getCsultyDeskEssentials(OpdPatientDeskFB CsultyDeskLoginFB, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			
			/*CsultyDeskLoginFB.setDepartmentUnitCode("-1");
			CsultyDeskLoginFB.setRoomCode("-1");*/
			UserVO userVO = getUserVO(request);
			CsultyDeskLoginFB.setHospitalCode(userVO.getHospitalCode());
			
			
			List list = CsultyDeskLoginDATA.getCsultyDeskEssentials(getUserVO(request));			
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_DESK_UNIT_LIST, list);
			/*if (list.size() == 1)
			{
				Entry entry = (Entry) list.get(0);
				String unitCode = entry.getValue();
				CsultyDeskLoginFB.setDepartmentUnitCode(unitCode);
				flag = false;
			}
			else
			{
				flag = true;
				CsultyDeskLoginFB.setShowRommList("0");
			}*/
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

	// Getting Room By Unit Code
	public static boolean getRoomByUnitCoe(CsultyDeskLoginFB CsultyDeskLoginFB, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		List list = new ArrayList();
		try
		{
			String unitCodeWithDiagCodeType = CsultyDeskLoginFB.getDepartmentUnitCode();
			String unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^"));

			CsultyDeskLoginFB.setRoomCode("-1");
			list = CsultyDeskLoginDATA.getRoomsByUnitCode(getUserVO(request), unitCode);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ROOM_LIST, list);

			if (list.size() == 1)
			{
				Entry entry = (Entry) list.get(0);
				String roomCode = entry.getValue();
				CsultyDeskLoginFB.setRoomCode(roomCode);
				flag = false;
			}
			else
			{
				flag = true;
				CsultyDeskLoginFB.setShowRommList("1");
			}
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ROOM_LIST, list);
			CsultyDeskLoginFB.setShowRommList("0");
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
	
	// Going To Casuality Desk
	public static void goToCsultyDesk(CsultyDeskLoginFB _fb, HttpServletRequest _request)
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
			List<Entry> roomList = (List<Entry>) session.getAttribute(OpdConfig.OPD_ROOM_LIST);
			String roomCode = _fb.getRoomCode();
			for(Entry entry : roomList)
				if(entry.getValue().equals(roomCode))
					roomName=entry.getLabel();
			
			// Desk Name
			deskName = unitName+" "+roomName;
			
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_TYPE, DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE, unitCode);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_UNIT_NAME, unitName);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_DIAGNOSIS_TYPE_CODE, diagnosisTypeCodeForUnit);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE, roomCode);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_DESK_NAME, deskName);
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_ROWS_SPAN,"50,0%,92%,8%,0");		//"Desk Header,Desk Top Menu,Center,Desk Bottom Menu,Desk Footer"

//			Map mp = null;
//			mp = CsultyDeskLoginDATA.getICDAllEssentials(getUserVO(_request));
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

	// Going To Casuality Desk Footer
	public static void getCsultyDeskEssentialForFooter(CsultyDeskLoginFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			Map mp = null;
			mp = CsultyDeskLoginDATA.getICDAllEssentials(getUserVO(_request));
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