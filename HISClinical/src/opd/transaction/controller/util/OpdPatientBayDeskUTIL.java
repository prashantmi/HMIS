package opd.transaction.controller.util;

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
import hisglobal.utility.dynamicdesk.controller.data.DynamicDeskDATA;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import opd.OpdConfig;
import opd.transaction.controller.data.OpdPatientBayDeskDATA;
import opd.transaction.controller.fb.OpdPatientDeskFB;
import org.apache.struts.action.ActionMapping;
import registration.RegistrationConfig;
import org.json.simple.JSONValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class OpdPatientBayDeskUTIL extends ControllerUTIL
{
	// Getting Todays OPD Patient List
	public static void getTodayOpdPatientList(OpdPatientDeskFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		PatientDetailVO[] dailyPatientVOs = null;
		Status objStatus = new Status();
		try
		{
			_fb.setDepartmentUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));			
			String roomCode = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE);
			dailyPatientVOs = OpdPatientBayDeskDATA.getTodayPatientList(getUserVO(_request), _fb.getDepartmentUnitCode(), roomCode);

			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DESK_PATIENT_LIST, dailyPatientVOs);			
			DynamicDeskUTIL.setAttributeInSession(_request,"counter", String.valueOf(dailyPatientVOs.length));
			
			// Setting Patient CR No
			session.removeAttribute(OpdConfig.OPD_NO_OF_ATTENDED_PAT);
			session.getAttribute(OpdConfig.OPD_NO_OF_ATTENDED_PAT);
			String crNo = "";
			for (int i = 0; i < dailyPatientVOs.length; i++)
			{
				if (dailyPatientVOs[i].getPatCrNo().equals(_fb.getPatCrNo()))
				{
					crNo = dailyPatientVOs[i].getPatCrNo();
					break;
				}
			}
			_fb.setSelectedPatCrNo(crNo);

			String pageChoice = (_fb.getPageChoice()==null || _fb.getPageChoice().trim().equals(""))? null:_fb.getPageChoice();
			
			// Setting Two Separate List for 
			List<PatientDetailVO> lstPatientsWaiting = new ArrayList<PatientDetailVO>();
			List<PatientDetailVO> lstPatientsAttended = new ArrayList<PatientDetailVO>();
			
			for(PatientDetailVO vo: dailyPatientVOs)
			{
				if(vo.getIsConfirmed().equals(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED))
				{
					lstPatientsWaiting.add(vo);
					if(pageChoice==null && _fb.getPatCrNo()!=null && !_fb.getPatCrNo().trim().equals("") && _fb.getPatCrNo().equalsIgnoreCase(vo.getPatCrNo()))
						pageChoice = OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING;
				}
				else if(vo.getIsConfirmed().equals(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED))
				{
					lstPatientsAttended.add(vo);
					if(pageChoice==null && _fb.getPatCrNo()!=null && !_fb.getPatCrNo().trim().equals("") && _fb.getPatCrNo().equalsIgnoreCase(vo.getPatCrNo()))
						pageChoice = OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED;
				}
			}
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING, lstPatientsWaiting);
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED, lstPatientsAttended);
			
			// Setting Page Choice
			_fb.setPageChoice((pageChoice==null)?OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING:pageChoice);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			session.removeAttribute("counter");
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

	// *
	public static void getInOrder(OpdPatientDeskFB opdPatientDeskFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
//		DailyPatientVO[] dailyPatientVOs;
//		dailyPatientVOs = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
//		List list = new ArrayList();
//		for (int i = 0; i < dailyPatientVOs.length; i++)
//		{
//			dailyPatientVOs[i].setSortType(Config.SORT_TYPE_ASC);
//			list.add(dailyPatientVOs[i]);
//		}
//		Collections.sort(list);
//		dailyPatientVOs = (DailyPatientVO[]) list.toArray(dailyPatientVOs);
//		session.setAttribute(DynamicDeskConfig.DESK_PATIENT_LIST, dailyPatientVOs);
		
		// Getting Page Choice List
		List<PatientDetailVO> lstPatientDtl = new ArrayList<PatientDetailVO>();
		if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
			lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
		else if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
			lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);

		for (PatientDetailVO vo : lstPatientDtl)	vo.setSortType(Config.SORT_TYPE_ASC);
		Collections.sort(lstPatientDtl);

		// Setting Page Choice List in Session after Sorting
		if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
			session.setAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING, lstPatientDtl);
		else if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
			session.setAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED, lstPatientDtl);
	}

	// *
	public static void getInDscOrder(OpdPatientDeskFB opdPatientDeskFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();

//		DailyPatientVO[] dailyPatientVOs;
//		dailyPatientVOs = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
//		List list = new ArrayList();
//		for (int i = 0; i < dailyPatientVOs.length; i++)
//		{
//			dailyPatientVOs[i].setSortType(Config.SORT_TYPE_DSC);
//			list.add(dailyPatientVOs[i]);
//		}
//		Collections.sort(list);
//		dailyPatientVOs = (DailyPatientVO[]) list.toArray(dailyPatientVOs);
//		session.setAttribute(DynamicDeskConfig.DESK_PATIENT_LIST, dailyPatientVOs);

		// Getting Page Choice List
		List<PatientDetailVO> lstPatientDtl = new ArrayList<PatientDetailVO>();
		if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
			lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
		else if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
			lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);

		for (PatientDetailVO vo : lstPatientDtl)	vo.setSortType(Config.SORT_TYPE_DSC);
		Collections.sort(lstPatientDtl);

		// Setting Page Choice List in Session after Sorting
		if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
			session.setAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING, lstPatientDtl);
		else if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
			session.setAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED, lstPatientDtl);
	}

	// *
	public static void getOrderByQueueNo(OpdPatientDeskFB opdPatientDeskFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		try
		{
//			DailyPatientVO[] dailyPatientVOs;
//			dailyPatientVOs = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
//			for (int i = 0; i < dailyPatientVOs.length; i++)
//			{
//				dailyPatientVOs[i].setOrederBy(Config.OPD_PATIENT_OREDRE_BY_QUEUE_NO);
//			}

			// Getting Page Choice List
			List<PatientDetailVO> lstPatientDtl = new ArrayList<PatientDetailVO>();
			if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
			else if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);

			for (PatientDetailVO vo : lstPatientDtl)	vo.setOrederBy(Config.OPD_PATIENT_OREDRE_BY_QUEUE_NO);

			
			if (opdPatientDeskFB.getOrderBy().equals(Config.SORT_TYPE_ASC)) getInOrder(opdPatientDeskFB, request);
			else if (opdPatientDeskFB.getOrderBy().equals(Config.SORT_TYPE_DSC)) getInDscOrder(opdPatientDeskFB, request);
			opdPatientDeskFB.setHmode("");
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
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// *
	public static void getOrderByCrNo(OpdPatientDeskFB opdPatientDeskFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		try
		{
//			DailyPatientVO[] dailyPatientVOs;
//			dailyPatientVOs = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
//			for (int i = 0; i < dailyPatientVOs.length; i++)
//			{
//				dailyPatientVOs[i].setOrederBy(Config.OPD_PATIENT_OREDRE_BY_CR_NO);
//			}

			// Getting Page Choice List
			List<PatientDetailVO> lstPatientDtl = new ArrayList<PatientDetailVO>();
			if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
			else if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);

			for (PatientDetailVO vo : lstPatientDtl)	vo.setOrederBy(Config.OPD_PATIENT_OREDRE_BY_CR_NO);

			
			if (opdPatientDeskFB.getOrderBy().equals(Config.SORT_TYPE_ASC)) getInOrder(opdPatientDeskFB, request);
			else if (opdPatientDeskFB.getOrderBy().equals(Config.SORT_TYPE_DSC)) getInDscOrder(opdPatientDeskFB, request);
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
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// *
	public static void getOrderByName(OpdPatientDeskFB opdPatientDeskFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		try
		{
//			DailyPatientVO[] dailyPatientVOs;
//			dailyPatientVOs = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
//			for (int i = 0; i < dailyPatientVOs.length; i++)
//			{
//				dailyPatientVOs[i].setOrederBy(Config.OPD_PATIENT_OREDRE_BY_NAME);
//			}

			// Getting Page Choice List
			List<PatientDetailVO> lstPatientDtl = new ArrayList<PatientDetailVO>();
			if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
			else if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);

			for (PatientDetailVO vo : lstPatientDtl)	vo.setOrederBy(Config.OPD_PATIENT_OREDRE_BY_NAME);

			
			if (opdPatientDeskFB.getOrderBy().equals(Config.SORT_TYPE_ASC)) getInOrder(opdPatientDeskFB, request);
			else if (opdPatientDeskFB.getOrderBy().equals(Config.SORT_TYPE_DSC)) getInDscOrder(opdPatientDeskFB, request);
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
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// *
	public static void getOrderByPatientCategory(OpdPatientDeskFB opdPatientDeskFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		try
		{
//			DailyPatientVO[] dailyPatientVOs;
//			dailyPatientVOs = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
//			for (int i = 0; i < dailyPatientVOs.length; i++)
//			{
//				dailyPatientVOs[i].setOrederBy(Config.OPD_PATIENT_OREDRE_BY_PATIENT_CATEGORY);
//			}

			// Getting Page Choice List
			List<PatientDetailVO> lstPatientDtl = new ArrayList<PatientDetailVO>();
			if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
			else if(opdPatientDeskFB.getPageChoice().equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);

			for (PatientDetailVO vo : lstPatientDtl)	vo.setOrederBy(Config.OPD_PATIENT_OREDRE_BY_PATIENT_CATEGORY);

			
			if (opdPatientDeskFB.getOrderBy().equals(Config.SORT_TYPE_ASC)) getInOrder(opdPatientDeskFB, request);
			else if (opdPatientDeskFB.getOrderBy().equals(Config.SORT_TYPE_DSC)) getInDscOrder(opdPatientDeskFB, request);
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
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// *
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void saveOpdPatientEpisode(OpdPatientDeskFB opdPatientDeskFB, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Status objStatus = new Status();
		boolean flag = false;
		PatientDetailVO[] dailyPatientVOs = null;
		int i = 0;
		try
		{
			UserVO userVO = getUserVO(request);

			String attendedPatNo = (String) session.getAttribute(OpdConfig.OPD_NO_OF_ATTENDED_PAT);
			if (attendedPatNo == null || attendedPatNo.equals(""))
			{
				attendedPatNo = "1";
			}
			else
			{
				int no = Integer.parseInt(attendedPatNo);
				no = no + 1;
				attendedPatNo = String.valueOf(no);
			}
			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			flag = true;
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(opdPatientDeskFB.getPatCrNo()))
			{
				flag = false;
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (i = 0; i < dailyPatientVOs.length; i++)
				{
					if (opdPatientDeskFB.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))					{
						flag = true;
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}	
			/*dailyPatientVOs = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			if (dailyPatientVOs != null && dailyPatientVOs.length > 0)
			{
				for (; i < dailyPatientVOs.length; i++)
				{
					if (dailyPatientVOs[i].getPatCrNo().equals(opdPatientDeskFB.getPatCrNo()))
					{
						flag = true;
						break;
					}
				}
			}*/
			if (flag) OpdPatientBayDeskDATA.saveOpdPatientEpisode(patientDetailVO.getPatCrNo(), patientDetailVO
					.getSerialNo(), patientDetailVO.getEpisodeVisitNo(), patientDetailVO.getEpisodeCode(),
					RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED, opdPatientDeskFB.getEpisodeIsOpen(),
					opdPatientDeskFB.getEpisodeNextVisitDate(), userVO);
			session.setAttribute(OpdConfig.OPD_NO_OF_ATTENDED_PAT, attendedPatNo);
			objStatus.add(Status.DONE);
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
	}

	public static boolean getOpdDeskEssentials(OpdPatientDeskFB opdPatientDeskFB,HttpServletRequest request) 
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{			
			List list = OpdPatientBayDeskDATA.getOpdDeskEssentials(getUserVO(request));
			DynamicDeskUTIL.setAttributeInSession(request, OpdConfig.OPD_DESK_UNIT_LIST, list);
			if (list.size() == 1)
			{
				Entry entry = (Entry) list.get(0);
				String unitCode = entry.getValue();
				opdPatientDeskFB.setDepartmentUnitCode(unitCode);
				flag = false;
			}
			else
			{
				flag = true;
				opdPatientDeskFB.setShowRommList("0");
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
	public static StringBuffer getRoomByUnitCode(OpdPatientDeskFB objFB, HttpServletRequest request, String departmentUnitCode)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus = new Status();
		List lstRoom = new ArrayList();
		boolean flag = true;
		try
		{
			System.out.println("departmentUnitCode :"+departmentUnitCode);
			String unitCode = departmentUnitCode.substring(0, departmentUnitCode.indexOf("^"));
			objFB.setRoomCode("0");
			lstRoom = DynamicDeskDATA.getRoomsByUnitCode(getUserVO(request), unitCode);
			//lstRoom = OpdDeskLoginDATA.getRoomsByUnitCode(getUserVO(request), unitCode);
			DynamicDeskUTIL.setAttributeInSession(request, OpdConfig.OPD_ROOM_LIST, lstRoom);
			System.out.println("lstRoom 0 :"+lstRoom.get(0));
			if(lstRoom!=null && lstRoom.size()>0)
			{
				sbAjaxRes.append("[");
				for(int i=0; i<lstRoom.size(); i++)
				{
					String room=lstRoom.get(i).toString();
					String [] roomDetail= room.split(",");
					String roomId= roomDetail[0].substring(1, roomDetail[0].length());
					String roomName= roomDetail[1].substring(0, (roomDetail[1].length()-1));
					System.out.println("Room id :"+roomId+"  RoomName :"+roomName);
					sbAjaxRes.append("{");
					sbAjaxRes.append("roomCode");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(roomId);sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("roomName");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(roomName);sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");					
				}
				if(lstRoom.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
				sbAjaxRes.append("]");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ROOM_LIST, lstRoom);
			objFB.setShowRommList("0");
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
		return sbAjaxRes;
	}

	// Getting Todays OPD Patient List
	public static void getTodayAllPatientList(OpdPatientDeskFB _fb, HttpServletRequest _request)
	{	
		System.out.println("OpdPatientDeskUTIL.getTodayAllPatientList()");
		HttpSession session = _request.getSession();
		PatientDetailVO[] dailyPatientVOs = null;
		Status objStatus = new Status();
		try
		{
			_fb.setDepartmentUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));			
			String roomCode = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE);
			dailyPatientVOs = OpdPatientBayDeskDATA.getTodayAllPatientList(getUserVO(_request), _fb.getDepartmentUnitCode(), roomCode);

			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DESK_PATIENT_LIST, dailyPatientVOs);			
			DynamicDeskUTIL.setAttributeInSession(_request,"counter", String.valueOf(dailyPatientVOs.length));
			
			// Setting Patient CR No
			session.removeAttribute(OpdConfig.OPD_NO_OF_ATTENDED_PAT);
			session.getAttribute(OpdConfig.OPD_NO_OF_ATTENDED_PAT);
			String crNo = "";
			for (int i = 0; i < dailyPatientVOs.length; i++)
			{
				if (dailyPatientVOs[i].getPatCrNo().equals(_fb.getPatCrNo()))
				{
					crNo = dailyPatientVOs[i].getPatCrNo();
					break;
				}
			}
			_fb.setSelectedPatCrNo(crNo);

			String pageChoice = (_fb.getPageChoice()==null || _fb.getPageChoice().trim().equals(""))? null:_fb.getPageChoice();
			
			// Setting Two Separate List for 
			List<PatientDetailVO> lstPatientsWaiting = new ArrayList<PatientDetailVO>();
			List<PatientDetailVO> lstPatientsAttended = new ArrayList<PatientDetailVO>();
			
			for(PatientDetailVO vo: dailyPatientVOs)
			{
				if(vo.getIsConfirmed().equals(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED))
				{
					lstPatientsWaiting.add(vo);
					if(pageChoice==null && _fb.getPatCrNo()!=null && !_fb.getPatCrNo().trim().equals("") && _fb.getPatCrNo().equalsIgnoreCase(vo.getPatCrNo()))
						pageChoice = OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING;
				}
				else if(vo.getIsConfirmed().equals(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED))
				{
					lstPatientsAttended.add(vo);
					if(pageChoice==null && _fb.getPatCrNo()!=null && !_fb.getPatCrNo().trim().equals("") && _fb.getPatCrNo().equalsIgnoreCase(vo.getPatCrNo()))
						pageChoice = OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED;
				}
			}
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING, lstPatientsWaiting);
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED, lstPatientsAttended);
			
			// Setting Page Choice
			_fb.setPageChoice((pageChoice==null)?OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING:pageChoice);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			session.removeAttribute("counter");
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

	public static void AJX_G_PATIENTLIST(OpdPatientDeskFB opdPatientDeskFB,	HttpServletRequest objRequest, HttpServletResponse objResponse,ActionMapping objMapping_p) 
	{		
		Status objStatus=new Status();
		String outputString="";
		Map<String, Object> map1 = new HashMap<String, Object>();
		  		
		try
		{
			
			UserVO userVO=getUserVO(objRequest);
			//userVO.setModuleId(PisConfig.MODULE_ID_PIS);	
			
			//System.out.println("Employee Number = "+objRequest.getParameter("selectedEmpNo"));
			//System.out.println("Employee Number Attribute= "+objRequest.getAttribute("selectedEmpNo"));
			
			//String _selecedEmpNo = objRequest.getParameter("selectedEmpNo");
			
			String _page = objRequest.getParameter("page"), _limit = objRequest.getParameter("rows"), 
				   _sidx = objRequest.getParameter("sidx"), _sord = objRequest.getParameter("sord"), _where = "";
			//_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			//_where=HelperMethods.getListFilterCriteria(objRequest.getParameter("filters"), objRequest.getParameter("_search"),1);
			int i_page=Integer.parseInt(_page),i_limit=Integer.parseInt(_limit);			
			Map<String, Object> mapObj=OpdPatientBayDeskDATA.getTodayAllPatientList_AJAX(userVO,i_page,i_limit,_sidx,_sord,_where);
			String r_count =(String)mapObj.get("count"),r_total_page =(String)mapObj.get("total_pages"),r_page =(String)mapObj.get("page");
			List<List<String>> lstDtl= (List<List<String>>) mapObj.get("listObj");;
			
			if(lstDtl!=null && lstDtl.size()>0){
				//if(!$sidx) $sidx =1;
				 List<Map<String, Object>> ListObj2 = new ArrayList<Map<String, Object>>();
				 for (List<String> list2 : lstDtl) {Map<String, Object> map2 = new HashMap<String, Object>();
				 List<String> ListObj1 = new ArrayList<String>();for (String temp : list2) {ListObj1.add(temp);}
				 map2.put("id", list2.get(0));map2.put("cell", ListObj1);ListObj2.add(map2);}
				 map1.put("page", r_page);map1.put("total", r_total_page);map1.put("records", r_count);map1.put("rows", ListObj2);
			}else{System.out.println("List is Null or Empty.");}
			
			outputString=JSONValue.toJSONString(map1);
			System.out.println("outputString "+outputString);
		}		
		catch(Exception e){
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, outputString);
		}	
	}

	public static void writeResponse(HttpServletResponse resp, String output){
		try{
			resp.reset();
			resp.setContentType("text/xml");
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
		resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
}
