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
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.InpatientDeskDATA;
import inpatient.transaction.controller.fb.InpatientDeskFB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InpatientDeskUTL extends ControllerUTIL
{
	/** Getting the List of Admitted Patient List on the basis of unitCode & wardCode
	 * @param fb
	 * @param request
	 */
	public static void getAdmittedPatientList(InpatientDeskFB fb,HttpServletRequest request)
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
			
			if(InpatientConfig.IPD_NURSING_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))
				deptUnitCode=InpatientConfig.UNIT_ALL_CODE;

			patDtlVO=InpatientDeskDATA.getAdmittedPatientList(roomCode,deptUnitCode,wardCode,getUserVO(request));
		//	WebUTIL.setAttributeInSession(request,InpatientConfig.INPATIENT_ADMITTED_PATIENT_LIST_UNIT_WARD_WISE ,patDtlVO);
			DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DESK_PATIENT_LIST, patDtlVO);
			DynamicDeskUTIL.setAttributeInSession(request, InpatientConfig.DESK_PATIENT_SEARCHED_LIST, patDtlVO);
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
	
	// Serached Patient List
	public static void getSearchResultList(InpatientDeskFB objFB_p, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		PatientDetailVO[] patDtlVO=null;
		PatientDetailVO[] searchedPatDtlVO=null;
		List<PatientDetailVO> lstPatSearched = null;
		Status objStatus = new Status();
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			
			if(objFB_p.getStrSearchByType().equals("1") && objFB_p.getStrSearchBy()!=null && !objFB_p.getStrSearchBy().trim().equals(""))
			{
				lstPatSearched = new ArrayList<PatientDetailVO>();
				for(PatientDetailVO voPatient : patDtlVO)
					if(voPatient.getPatAdmNo().equals(objFB_p.getStrSearchBy().trim()))
						lstPatSearched.add(voPatient);
			}
			else if(objFB_p.getStrSearchByType().equals("2") && objFB_p.getStrSearchBy()!=null && !objFB_p.getStrSearchBy().trim().equals(""))
			{
				lstPatSearched = new ArrayList<PatientDetailVO>();
				for(PatientDetailVO voPatient : patDtlVO)
					if(voPatient.getPatName().trim().toLowerCase().startsWith(objFB_p.getStrSearchBy().trim().toLowerCase()))
						lstPatSearched.add(voPatient);
			}
			else if(objFB_p.getStrSearchByType().equals("3") && objFB_p.getStrSearchBy()!=null && !objFB_p.getStrSearchBy().trim().equals(""))
			{
				lstPatSearched = new ArrayList<PatientDetailVO>();
				for(PatientDetailVO voPatient : patDtlVO)
					if(voPatient.getPatCrNo().equals(objFB_p.getStrSearchBy().trim()))
						lstPatSearched.add(voPatient);
			}
			else
			{
				searchedPatDtlVO = patDtlVO;
			}
			
			if(lstPatSearched!=null && lstPatSearched.size()==0)
				objStatus.add(Status.TRANSINPROCESS, "No Patient Match with Search Criteria","");
			else
				objStatus.add(Status.TRANSINPROCESS);
			if(lstPatSearched!=null)
			{
				searchedPatDtlVO = new PatientDetailVO[lstPatSearched.size()];
				for(int i=0; i<lstPatSearched.size(); i++)
					searchedPatDtlVO[i] = (PatientDetailVO) lstPatSearched.get(i);
			}
			WebUTIL.setAttributeInSession(request, InpatientConfig.DESK_PATIENT_SEARCHED_LIST, searchedPatDtlVO);
			objFB_p.setCurrentPage(1);
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
	public static void orderByAscending(InpatientDeskFB fb,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		PatientDetailVO[] patDtlVO;
		patDtlVO = (PatientDetailVO[]) session.getAttribute(InpatientConfig.DESK_PATIENT_SEARCHED_LIST);
		List list=new ArrayList();
		
		for(int i=0;i<patDtlVO.length;i++)
		{
			patDtlVO[i].setSortType(Config.SORT_TYPE_ASC);
			list.add(patDtlVO[i]);
		}
		Collections.sort(list);
		
		patDtlVO=(PatientDetailVO[])list.toArray(patDtlVO);
		session.setAttribute(InpatientConfig.DESK_PATIENT_SEARCHED_LIST,patDtlVO);
	}
	
	//Get Order By Descending 
	public static void orderByDescending(InpatientDeskFB fb,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		PatientDetailVO[] patDtlVO;
		patDtlVO = (PatientDetailVO[]) session.getAttribute(InpatientConfig.DESK_PATIENT_SEARCHED_LIST);
		List list=new ArrayList();
		
		for(int i=0;i<patDtlVO.length;i++)
		{
			patDtlVO[i].setSortType(Config.SORT_TYPE_DSC);
			list.add(patDtlVO[i]);
		}
		Collections.sort(list);
		
		patDtlVO=(PatientDetailVO[])list.toArray(patDtlVO);
		session.setAttribute(InpatientConfig.DESK_PATIENT_SEARCHED_LIST,patDtlVO);
	}

	public static void getOrderByAdmissionNo(InpatientDeskFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(InpatientConfig.DESK_PATIENT_SEARCHED_LIST);
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
	
	public static void getOrderByPatName(InpatientDeskFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(InpatientConfig.DESK_PATIENT_SEARCHED_LIST);
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
	
	public static void getOrderByCrNo(InpatientDeskFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(InpatientConfig.DESK_PATIENT_SEARCHED_LIST);
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
	
	public static void getOrderByAdmissionDate(InpatientDeskFB fb, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		PatientDetailVO[] patDtlVO;
		try
		{
			patDtlVO = (PatientDetailVO[]) session.getAttribute(InpatientConfig.DESK_PATIENT_SEARCHED_LIST);
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
