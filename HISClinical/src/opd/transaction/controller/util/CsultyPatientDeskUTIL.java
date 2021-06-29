package opd.transaction.controller.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import opd.OpdConfig;
import opd.transaction.controller.data.CsultyPatientDeskDATA;
import opd.transaction.controller.fb.CsultyPatientDeskFB;
import registration.RegistrationConfig;
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
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class CsultyPatientDeskUTIL extends ControllerUTIL
{
	// Getting Casuality Patients List
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void getCsultyPatientList(CsultyPatientDeskFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		PatientDetailVO[] dailyPatientVOs = null;
		List<Entry> lstColorCodes = null;
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			_fb.setDepartmentUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			String roomCode = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE);
			dailyPatientVOs = CsultyPatientDeskDATA.getCsultyPatientList(_fb.getDepartmentUnitCode(), roomCode, userVO);
			
			for(int i=0;i< dailyPatientVOs.length;i++)
			{
				setColorCodificationOfDailyPat(dailyPatientVOs[i]);
				dailyPatientVOs[i].setOrederBy(Config.OPD_PATIENT_OREDRE_BY_COLOR_CODE);
				dailyPatientVOs[i].setSortType(Config.SORT_TYPE_ASC);
			}
			//Arrays.sort(dailyPatientVOs);
			
			DynamicDeskUTIL.setAttributeInSession(_request, DynamicDeskConfig.DESK_PATIENT_LIST, dailyPatientVOs);
			session.setAttribute("counter", String.valueOf(dailyPatientVOs.length));			
			
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

			// Setting propeties for Pagination
			if(dailyPatientVOs.length>0)
				_fb.setCurrentPage(1);
			
			// Setting Color Code List
			lstColorCodes = new ArrayList<Entry>();
			/*for(PatientCategoryVO vo :lstPatientCat)
			{
				Entry entObj = new Entry();
				entObj.setLabel(vo.getPatCatName());
				entObj.setValue(Config.OPD_PAT_LIST_COLOR_CODE_PRIORITY_ARR[Integer.parseInt(vo.getPriority())]);
				lstColorCodes.add(entObj);
			}*/
			Entry entMLC= new Entry("MLC",Config.OPD_PAT_LIST_COLOR_CODE_MLC);
			lstColorCodes.add(entMLC);
			Entry entUnknown= new Entry("Unknown",Config.OPD_PAT_LIST_COLOR_CODE_UNKNOWN);
			lstColorCodes.add(entUnknown);
			Entry entDead= new Entry("Brought Dead",Config.OPD_PAT_LIST_COLOR_CODE_BROUGHT_DEAD);
			lstColorCodes.add(entDead);
		/*	Entry entVisited= new Entry("Triage",Config.OPD_PAT_LIST_COLOR_CODE_VISITD);
			lstColorCodes.add(entVisited);		*/	
			session.setAttribute(OpdConfig.OPD_PATIENT_LIST_COLOR_CODIFICATION_INFO, lstColorCodes);

			
			String pageChoice = (_fb.getPageChoice()==null || _fb.getPageChoice().trim().equals(""))? null:_fb.getPageChoice();

			// Setting Three Separate List for Waiting, Attended, in Triage 
			List<PatientDetailVO> lstPatientsWaiting = new ArrayList<PatientDetailVO>();
			List<PatientDetailVO> lstPatientsAttended = new ArrayList<PatientDetailVO>();
			List<PatientDetailVO> lstPatientsInTriage = new ArrayList<PatientDetailVO>();
			
			for(PatientDetailVO vo: dailyPatientVOs)
			{
				if((vo.getTriageDurationHours()!=null && !vo.getTriageDurationHours().trim().equals("")))
				{
					lstPatientsInTriage.add(vo);
					if(pageChoice==null && _fb.getPatCrNo()!=null && !_fb.getPatCrNo().trim().equals("") && _fb.getPatCrNo().equalsIgnoreCase(vo.getPatCrNo()))
						pageChoice = OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE;
				}
				else if(vo.getIsConfirmed().equals(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED))
				{
					lstPatientsWaiting.add(vo);
					if(pageChoice==null && _fb.getPatCrNo()!=null && !_fb.getPatCrNo().trim().equals("") && _fb.getPatCrNo().equalsIgnoreCase(vo.getPatCrNo()))
						pageChoice = OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING;
				}
				else if(vo.getIsConfirmed().equals(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED))
				{
					lstPatientsAttended.add(vo);
					if(pageChoice==null && _fb.getPatCrNo()!=null && !_fb.getPatCrNo().trim().equals("") && _fb.getPatCrNo().equalsIgnoreCase(vo.getPatCrNo()))
						pageChoice = OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED;
				}
			}
			WebUTIL.setAttributeInSession(_request, OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING, lstPatientsWaiting);
			WebUTIL.setAttributeInSession(_request, OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED, lstPatientsAttended);
			WebUTIL.setAttributeInSession(_request, OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE, lstPatientsInTriage);
			
			// Setting Page Choice
			_fb.setPageChoice((pageChoice==null)?OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING:pageChoice);
			
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

	// Get In Order
	public static void getInOrder(CsultyPatientDeskFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();

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
		if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
			lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
		else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
			lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);
		else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE))
			lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE);
		
		for (PatientDetailVO vo : lstPatientDtl)	vo.setSortType(Config.SORT_TYPE_ASC);
		Collections.sort(lstPatientDtl);

		// Setting Page Choice List in Session after Sorting
		if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
			session.setAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING, lstPatientDtl);
		else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
			session.setAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED, lstPatientDtl);
		else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE))
			session.setAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE, lstPatientDtl);
	}

	// Get In Desc Order
	public static void getInDscOrder(CsultyPatientDeskFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();

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
		if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
			lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
		else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
			lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);
		else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE))
			lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE);

		for (PatientDetailVO vo : lstPatientDtl)	vo.setSortType(Config.SORT_TYPE_DSC);
		Collections.sort(lstPatientDtl);

		// Setting Page Choice List in Session after Sorting
		if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
			session.setAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING, lstPatientDtl);
		else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
			session.setAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED, lstPatientDtl);
		else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE))
			session.setAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE, lstPatientDtl);
	}

	// Get By Queue No
	public static void getOrderByQueueNo(CsultyPatientDeskFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
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
			if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE);
			
			for (PatientDetailVO vo : lstPatientDtl)	vo.setOrederBy(Config.OPD_PATIENT_OREDRE_BY_QUEUE_NO);
			
			
			if (_fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) getInOrder(_fb, _request);
			else if (_fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) getInDscOrder(_fb, _request);
			_fb.setHmode("");
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
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// Get By CR No
	public static void getOrderByCrNo(CsultyPatientDeskFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
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
			if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE);
			
			for (PatientDetailVO vo : lstPatientDtl)	vo.setOrederBy(Config.OPD_PATIENT_OREDRE_BY_CR_NO);

			
			if (_fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) getInOrder(_fb, _request);
			else if (_fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) getInDscOrder(_fb, _request);
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
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// Order By Name
	public static void getOrderByName(CsultyPatientDeskFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
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
			if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE);
			
			for (PatientDetailVO vo : lstPatientDtl)	vo.setOrederBy(Config.OPD_PATIENT_OREDRE_BY_NAME);
			
			
			if (_fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) getInOrder(_fb, _request);
			else if (_fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) getInDscOrder(_fb, _request);
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
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// Order By Patient Category
	public static void getOrderByPatientCategory(CsultyPatientDeskFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
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
			if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE);
			
			for (PatientDetailVO vo : lstPatientDtl)	vo.setOrederBy(Config.OPD_PATIENT_OREDRE_BY_PATIENT_CATEGORY);
			
			
			if (_fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) getInOrder(_fb, _request);
			else if (_fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) getInDscOrder(_fb, _request);
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
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// Order By Color Code
	public static void getOrderByColorCode(CsultyPatientDeskFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		try
		{
//			DailyPatientVO[] dailyPatientVOs;
//			dailyPatientVOs = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
//			for (int i = 0; i < dailyPatientVOs.length; i++)
//			{
//				dailyPatientVOs[i].setOrederBy(Config.OPD_PATIENT_OREDRE_BY_COLOR_CODE);
//			}

			// Getting Page Choice List
			List<PatientDetailVO> lstPatientDtl = new ArrayList<PatientDetailVO>();
			if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE);
			
			for (PatientDetailVO vo : lstPatientDtl)	vo.setOrederBy(Config.OPD_PATIENT_OREDRE_BY_COLOR_CODE);
			
			
			if (_fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) getInOrder(_fb, _request);
			else if (_fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) getInDscOrder(_fb, _request);
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
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// Order By Triage Duration
	public static void getOrderByTriageDuration(CsultyPatientDeskFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		try
		{
			// Getting Page Choice List
			List<PatientDetailVO> lstPatientDtl = new ArrayList<PatientDetailVO>();
			if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);
			else if(_fb.getPageChoice().equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE))
				lstPatientDtl = (List<PatientDetailVO>) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE);
			
			for (PatientDetailVO vo : lstPatientDtl)	vo.setOrederBy(Config.OPD_PATIENT_OREDRE_BY_TRIAGE_DURATION);
			
			
			if (_fb.getOrderBy().equals(Config.SORT_TYPE_ASC)) getInOrder(_fb, _request);
			else if (_fb.getOrderBy().equals(Config.SORT_TYPE_DSC)) getInDscOrder(_fb, _request);
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
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	// Saving Patient Episode
	public static void saveOpdPatientEpisode(CsultyPatientDeskFB _fb, HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		PatientDetailVO[] dailyPatientVOs = null;
		Status objStatus = new Status();
		boolean flag = false;
		int i = 0;
		try
		{
			UserVO userVO = getUserVO(_request);

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
			/*dailyPatientVOs = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			if (dailyPatientVOs != null && dailyPatientVOs.length > 0)
			{
				for (; i < dailyPatientVOs.length; i++)
				{
					if (dailyPatientVOs[i].getPatCrNo().equals(_fb.getPatCrNo()))
					{
						flag = true;
						break;
					}
				}
			}*/
			
			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			_fb.setPatCrNo(patientDetailVO.getPatCrNo());
			if (flag) CsultyPatientDeskDATA.saveOpdPatientEpisode(patientDetailVO.getPatCrNo(), 
					patientDetailVO.getSerialNo(), patientDetailVO.getEpisodeVisitNo(), patientDetailVO.getEpisodeCode(),
					RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED, _fb.getEpisodeIsOpen(),
					_fb.getEpisodeNextVisitDate(), userVO);
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
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
	
	/*
	 * Setting Color Codification of Patients in Casuality Desk 
	 * 
	 * 1. Set Primary Key Priority Wise Color
	 * 2. Set Unknown Patient
	 * 3. Brought Dead
	 * 4. MLC
	 * 5. Visited
	 * 
	 */
	public static void setColorCodificationOfDailyPat(DailyPatientVO _vo)
	{
		String color="";
		String colorOrder="";
		boolean flag=false;
		
		try
		{
			if(_vo.getColorCode()==null)
			{
				// Setting Values
				String arr[]=_vo.getPatPrimaryCatCode().split("\\^");		
				int priority=Integer.parseInt(arr[1]);
				_vo.setPatPrimaryCatCode(arr[0]);
				arr=_vo.getEpisodeVisitNo().split("\\^");		
				String isConfirmed=arr[1];
				_vo.setEpisodeVisitNo(arr[0]);
	
				
				// Primary Key Priority
				if(priority >=0 && priority <=5)
				{
					color=Config.OPD_PAT_LIST_COLOR_CODE_PRIORITY_ARR[priority];
					int i=Integer.parseInt(Config.OPD_PAT_LIST_COLOR_CODE_PRIORITY_ORDER);
					i+=Integer.parseInt(Config.OPD_PAT_LIST_COLOR_CODE_PRIORITY_ARR_ORDER[priority]);
					colorOrder=Integer.toString(i);
				}
				
				// Unknown		
				if(!flag)
				{
					if(_vo.getIsUnknown()!=null && Integer.parseInt(_vo.getIsUnknown())==1)
					{
						color=Config.OPD_PAT_LIST_COLOR_CODE_UNKNOWN;
						colorOrder=Config.OPD_PAT_LIST_COLOR_CODE_UNKNOWN_ORDER;
						flag=true;
					}
				}
				
				// Brought Dead		
				if(!flag)
				{
					if(_vo.getIsBroughtDead()!=null && Integer.parseInt(_vo.getIsBroughtDead())==1)
					{
						color=Config.OPD_PAT_LIST_COLOR_CODE_BROUGHT_DEAD;
						colorOrder=Config.OPD_PAT_LIST_COLOR_CODE_BROUGHT_DEAD_ORDER;
						flag=true;
					}
				}	
		
				// MLC	
				if(!flag)
				{
					if(_vo.getIsMLC()!=null && Integer.parseInt(_vo.getIsMLC())==1)
					{
						color=Config.OPD_PAT_LIST_COLOR_CODE_MLC;
						colorOrder=Config.OPD_PAT_LIST_COLOR_CODE_MLC_ORDER;
						flag=true;
					}
				}	
		
				// Visited		
				if(isConfirmed==RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED)
				{
					color=Config.OPD_PAT_LIST_COLOR_CODE_VISITD;
					colorOrder=Config.OPD_PAT_LIST_COLOR_CODE_VISITD_ORDER;
				}
			}
			else
				color=_vo.getColorCode();
		}
		catch (Exception e)
		{
			System.out.println("Error in Color Codification ... ");
			e.printStackTrace();			
		}
		finally
		{
			_vo.setColorCode(color);
			_vo.setColorCodeOrder(colorOrder);
		}		
	}
}
