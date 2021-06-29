package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import hisglobal.vo.PatientDetailVO;
import inpatient.transaction.controller.data.NursingStationPatListDATA;
import inpatient.transaction.controller.fb.NursingStationPatListFB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NursingStationPatListUTL extends ControllerUTIL
{
	/** Getting the List of Admitted Patient List on the basis of unitCode & wardCode
	 * @param fb
	 * @param request
	 */
	public static void getAdmittedPatientList(NursingStationPatListFB fb,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		PatientDetailVO[] patDtlVO=null;
		Status objStatus = new Status();
		
		try
		{
			fb.setDepartmentUnitCode((String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			fb.setWardCode((String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE));
			fb.setRoomCode((String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE));
			String deptUnitCode=fb.getDepartmentUnitCode();
			String wardCode=fb.getWardCode();
			String roomCode=fb.getRoomCode();
			
			patDtlVO=NursingStationPatListDATA.getAdmittedPatientList(roomCode,deptUnitCode,wardCode,getUserVO(request));
		//	WebUTIL.setAttributeInSession(request,InpatientConfig.INPATIENT_ADMITTED_PATIENT_LIST_UNIT_WARD_WISE ,patDtlVO);
			DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DESK_PATIENT_LIST, patDtlVO);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	//Get Order By Ascending
	public static void orderByAscending(NursingStationPatListFB fb,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		PatientDetailVO[] patDtlVO;
		patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
		List list=new ArrayList();
		
		for(int i=0;i<patDtlVO.length;i++)
		{
			patDtlVO[i].setSortType(Config.SORT_TYPE_ASC);
			list.add(patDtlVO[i]);
		}
		Collections.sort(list);
		
		patDtlVO=(PatientDetailVO[])list.toArray(patDtlVO);
		session.setAttribute(DynamicDeskConfig.DESK_PATIENT_LIST,patDtlVO);
	}
	
	//Get Order By Descending 
	public static void orderByDescending(NursingStationPatListFB fb,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		PatientDetailVO[] patDtlVO;
		patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
		List list=new ArrayList();
		
		for(int i=0;i<patDtlVO.length;i++)
		{
			patDtlVO[i].setSortType(Config.SORT_TYPE_DSC);
			list.add(patDtlVO[i]);
		}
		Collections.sort(list);
		
		patDtlVO=(PatientDetailVO[])list.toArray(patDtlVO);
		session.setAttribute(DynamicDeskConfig.DESK_PATIENT_LIST,patDtlVO);
	}

	public static void getOrderByAdmissionNo(NursingStationPatListFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (int i = 0; i < patDtlVO.length; i++)
			{

				patDtlVO[i].setOrederBy(Config.ORDER_BY_ADMISSION_NO);
			}
			if (fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) orderByAscending(fb, request);
			else if (fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) orderByDescending(fb, request);
			fb.setHmode("");
			objStatus.add(Status.TRANSINPROCESS);
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
		}
	}
	
	public static void getOrderByPatName(NursingStationPatListFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (int i = 0; i < patDtlVO.length; i++)
			{

				patDtlVO[i].setOrederBy(Config.IPD_PATIENT_OREDRE_BY_NAME);
			}
			if (fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) orderByAscending(fb, request);
			else if (fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) orderByDescending(fb, request);
			fb.setHmode("");
			objStatus.add(Status.TRANSINPROCESS);
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
		}
	}
	
	public static void getOrderByCrNo(NursingStationPatListFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (int i = 0; i < patDtlVO.length; i++)
			{

				patDtlVO[i].setOrederBy(Config.OPD_PATIENT_OREDRE_BY_CR_NO);
			}
			if (fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) orderByAscending(fb, request);
			else if (fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) orderByDescending(fb, request);
			fb.setHmode("");
			objStatus.add(Status.TRANSINPROCESS);
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
		}
	}
	
	public static void getOrderByAdmissionDate(NursingStationPatListFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (int i = 0; i < patDtlVO.length; i++)
			{

				patDtlVO[i].setOrederBy(Config.ORDER_BY_ADMISSION_DATE);
			}
			if (fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) orderByAscending(fb, request);
			else if (fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) orderByDescending(fb, request);
			fb.setHmode("");
			objStatus.add(Status.TRANSINPROCESS);
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
		}
	}
	
}
