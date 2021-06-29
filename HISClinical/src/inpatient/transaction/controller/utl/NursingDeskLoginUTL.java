package inpatient.transaction.controller.utl;

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
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.NursingDeskLoginDATA;
import inpatient.transaction.controller.fb.NursingDeskLoginFB;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;

public class NursingDeskLoginUTL extends ControllerUTIL
{
	/**
	 *  Getting Ward List based on Role
	 * 
	 * @param fb
	 * @param request
	 * @return true if added
	 */ 
	public static boolean getWardListBasedOnRole(NursingDeskLoginFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		List listWard = new ArrayList();
		try
		{
			fb.setWardCode("-1");
			fb.setRoomCode("-1");
			
			listWard=NursingDeskLoginDATA.getWardListBasedOnRole(getUserVO(request));
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_ROLE, listWard);			
			if(listWard.size() == 1)
			{
				Entry entry = (Entry) listWard.get(0);
				String wardCode = entry.getValue();
				fb.setWardCode(wardCode.split("#")[0]);
				fb.setDepartmentUnitCode(wardCode.split("#")[1]);
				flag=false;
			}
			else
			{
				flag=true; 
				fb.setUnitList("1");
				fb.setRoomList("0");
			}			
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_ROLE, listWard);
			fb.setUnitList("0");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}

	// Getting the List of Unit for Nursing desk
	public static boolean getDeptUnitList(NursingDeskLoginFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			fb.setDepartmentUnitCode("-1");
			fb.setWardCode("-1");
			fb.setRoomCode("-1");

			List lstUnit=NursingDeskLoginDATA.getDeptUnitList(getUserVO(request));
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_DEPT_UNIT_LIST ,lstUnit);
			if(lstUnit.size() == 1)
			{
				Entry entry = (Entry) lstUnit.get(0);
				String unitCode = entry.getValue();
				fb.setDepartmentUnitCode(unitCode);
				flag = false;
			}
			else
			{
				flag = true;
				fb.setUnitList("0");
				fb.setRoomList("0");
			}
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");		
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");			
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");			
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");			
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	// Getting the List of Ward on the Basis of Unit 
	public static boolean getWardOnBasisOfUnitCode(NursingDeskLoginFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		List listWard = new ArrayList();
		try
		{
			String unitCodeWithDiagCodeType = fb.getDepartmentUnitCode();
			String unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^"));
			
			fb.setWardCode("-1");
			fb.setRoomCode("-1");

			listWard=NursingDeskLoginDATA.getWardOnBasisOfUnitCode(unitCode,getUserVO(request));
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT, listWard);
			if(listWard.size() == 1)
			{
				Entry entry = (Entry) listWard.get(0);
				String wardCode = entry.getValue();
				fb.setWardCode(wardCode);
				flag=false;
			}
			else
			{
				flag=true; 
				fb.setUnitList("1");				
				fb.setRoomList("0");
			}
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT, listWard);
			fb.setUnitList("0");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	// Getting Room List By Ward Code
	public static boolean getRoomOnBasisOfWardCode(NursingDeskLoginFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		List listRoom = new ArrayList();
		try
		{
			if(InpatientConfig.IPD_NURSING_DESK_ROOM_SELECTION.equals(InpatientConfig.ROOM_SELECTION_NO)
					|| InpatientConfig.IPD_NURSING_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))
			{
				if(InpatientConfig.IPD_NURSING_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))
				{
					String wardCode = fb.getWardCode();
					fb.setWardCode(wardCode.split("#")[0]);
					fb.setDepartmentUnitCode(wardCode.split("#")[1]);
				}
				fb.setRoomCode(InpatientConfig.ROOM_ALL_CODE);
				flag=false;
			}
			else
			{
				String unitCodeWithDiagCodeType = fb.getDepartmentUnitCode();
				String unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^"));
				
				fb.setRoomCode("-1");
				listRoom=NursingDeskLoginDATA.getRoomOnBasisOfWardCode(unitCode,fb.getWardCode(),getUserVO(request));
			
				if(listRoom.size() == 1)
				{
					Entry entry = (Entry) listRoom.get(0);
					String roomCode = entry.getValue();
					fb.setRoomCode(roomCode);
					flag=false;
				}
				else
				{
					flag=true; 
					fb.setRoomList("1");
					fb.setUnitList("1");
				}
			}
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_ROOM_LIST_ON_BASIS_WARD, listRoom);
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request,InpatientConfig.NURSING_DESK_ROOM_LIST_ON_BASIS_WARD, listRoom);
			fb.setRoomList("0");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	// Going To Nursing Desk
	public static void goToNursingDesk(NursingDeskLoginFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		String deskName = "", unitName = "", wardName = "", roomName = "";
		HttpSession session = null;
		try
		{
			session = _request.getSession();			

			if(InpatientConfig.IPD_NURSING_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))
			{
				// Getting Ward Code & Ward Name
				List<Entry> wardList = (List<Entry>) session.getAttribute(InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_ROLE);
				String wardCode = _fb.getWardCode();
				for(Entry entry : wardList)
					if(entry.getValue().split("#")[0].equals(wardCode))
						wardName=entry.getLabel();
				
				String unitCode = _fb.getDepartmentUnitCode();
				String roomCode = _fb.getRoomCode();
				
				// Desk Name
				deskName = unitName+" "+wardName+" "+roomName;
				
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_TYPE, DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK);
				//---- Setting Unit at Session of the Selected Ward but not setting it as Selected Unit 
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE, unitCode);
				//---- Empty Unit Name as Not Showing Unit Name at Desk
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_UNIT_NAME, unitName);
				//---- Setting Diagnosis Code Type as ICD fixed (not Unit Based Selection)
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_DIAGNOSIS_TYPE_CODE, OpdConfig.CHOICE_ICD_CODE);
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE , wardCode);
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE , roomCode);
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_DESK_NAME, deskName);
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_ROWS_SPAN,"50,0%,92%,8%,0");
			}
			else
			{
				// Unit Diagnosis Type Code 
				String diagnosisTypeCodeForUnit = String.valueOf(_fb.getDepartmentUnitCode().charAt(_fb.getDepartmentUnitCode().indexOf("^") + 1));
	
				// Getting Unit Code & Unit Name
				List<Entry> unitList = (List<Entry>) session.getAttribute(InpatientConfig.NURSING_DESK_DEPT_UNIT_LIST);
				String unitCode = _fb.getDepartmentUnitCode().substring(0, _fb.getDepartmentUnitCode().indexOf("^"));
				for(Entry entry : unitList)
					if(entry.getValue().substring(0,entry.getValue().indexOf("^")).equals(unitCode))
						unitName=entry.getLabel();
	
				// Getting Ward Code & Ward Name
				List<Entry> wardList = (List<Entry>) session.getAttribute(InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT);
				String wardCode = _fb.getWardCode();
				for(Entry entry : wardList)
					if(entry.getValue().equals(wardCode))
						wardName=entry.getLabel();
	
				// Getting Room Code & Room Name
				List<Entry> roomList = (List<Entry>) session.getAttribute(InpatientConfig.NURSING_DESK_ROOM_LIST_ON_BASIS_WARD);
				String roomCode = _fb.getRoomCode();
				for(Entry entry : roomList)
					if(entry.getValue().equals(roomCode))
						roomName=entry.getLabel();

				// Desk Name
				deskName = unitName+" "+wardName+" "+roomName;
				
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_TYPE, DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK);
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE, unitCode);
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_UNIT_NAME, unitName);
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_DIAGNOSIS_TYPE_CODE, diagnosisTypeCodeForUnit);
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE , wardCode);
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE , roomCode);
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_DESK_NAME, deskName);
				DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DYNAMIC_DESK_ROWS_SPAN,"50,0%,92%,8%,0");
			}
//			Map mp = null;
//			mp = NursingDeskLoginDATA.getICDAllEssentials(getUserVO(_request));
//			DynamicDeskUTIL.setMapInSession(mp, _request);

			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
}
