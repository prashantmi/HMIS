package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.PendingTaskListDATA;
import inpatient.transaction.controller.fb.PendingTaskListFB;
import inpatient.transaction.vo.PendingSampleCollectionVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PendingTaskListUTIL extends ControllerUTIL
{
	public static boolean getEssentials(PendingTaskListFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		UserVO userVO = getUserVO(_rq);
		Map essentialMap = new HashMap();
		HttpSession session = _rq.getSession();
		ConsentRequestVO pendingConsentVO = new ConsentRequestVO();
		List<ConsentRequestVO> pendingConsentVOList = null;
		List patientDtlVOList = new ArrayList();
		List<DrugAdminDtlVO> drugAdminDtlVOList = null;
		List<PendingSampleCollectionVO> pendingSampleCollectionList = null;
		List<PatientMonitoringMstVO> pendingMonitoringList = null;
		try
		{
			String pendingTaskArray[] = InpatientConfig.PENDING_TASKS;
			_fb.setTaskCode("0");
			String deptUnitCode = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE);
			String wardCode = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE);
			String roomCode = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROOM_CODE);
			PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			if(session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST)!=null)
			{
				//DailyPatientVO[] arrayDailyPatVO = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				DailyPatientVO selectedPatientVO = null;
				for (int i = 0; i < arrayDailyPatVO.length; i++)
					if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
						selectedPatientVO = arrayDailyPatVO[i];
	
				// ////setting the fields in form bean to avoid null referrences///////////////
				String[] strArray = new String[1];
				strArray[0] = "";
			}
			else if (session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO)!=null)
			{
				
				PatientDetailVO selectedPatientVO = null;
				selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				
				String[] strArray = new String[1];
				strArray[0] = "";
			}
			PatientDetailVO selectedPatientVO = null;
			// gets the list of all pending tasks
			if (InpatientConfig.IPD_NURSING_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))
				deptUnitCode = "%";
			essentialMap = PendingTaskListDATA.getPendingTasks(deptUnitCode, wardCode, roomCode, userVO);
			pendingConsentVOList = (List) essentialMap.get(InpatientConfig.PENDING_CONSENT_PATIENT_LIST);
			int array[] = new int[pendingTaskArray.length];
			drugAdminDtlVOList = (List) essentialMap.get(InpatientConfig.PENDING_TREATMENT_LIST);
			pendingSampleCollectionList = (List) essentialMap.get(InpatientConfig.PENDING_SAMPLE_COLLECTION_LIST);
			pendingMonitoringList = (List) essentialMap.get(InpatientConfig.PENDING_MONITORING_LIST);
			int count = 0;

			// ////finding the no. of patient for each type of pending task/////////////////////////////
			if (drugAdminDtlVOList != null) for (int j = 0; j < arrayDailyPatVO.length; j++)
			{
				for (int i = 0; i < drugAdminDtlVOList.size(); i++)
				{
					if (drugAdminDtlVOList.get(i).getPatCrNo().equals(arrayDailyPatVO[j].getPatCrNo()))
					{
						
						//selectedPatientVO=new PatientDetailVO(); selectedPatientVO = arrayDailyPatVO[j];
						//patientDtlVOList.add(selectedPatientVO);
						count++;
						break;
					}
				}
			}
			// array[1]=patientDtlVOList.size();
			array[1] = count;//(drugAdminDtlVOList != null)? drugAdminDtlVOList.size():0;//count;
			count = 0;
			if (pendingSampleCollectionList != null) for (int j = 0; j < arrayDailyPatVO.length; j++)
			{
				for (int i = 0; i < pendingSampleCollectionList.size(); i++)
				{
					if (pendingSampleCollectionList.get(i).getPatCrNo().equals(arrayDailyPatVO[j].getPatCrNo()))
					{
						count++;
						break;
					}
				}
			}
			array[2] = count;//(pendingSampleCollectionList != null)? drugAdminDtlVOList.size():0;
			count = 0;
			if (pendingMonitoringList != null) for (int j = 0; j < arrayDailyPatVO.length; j++)
			{
				for (int i = 0; i < pendingMonitoringList.size(); i++)
				{
					if (pendingMonitoringList.get(i).getPatCrNo().equals(arrayDailyPatVO[j].getPatCrNo()))
					{
						count++;
						break;
					}
				}
			}
			array[3] = count;//(pendingMonitoringList != null)? drugAdminDtlVOList.size():0;//
			patientDtlVOList = new ArrayList();
			if (pendingConsentVOList != null) for (int j = 0; j < arrayDailyPatVO.length; j++)
			{
				for (int i = 0; i < pendingConsentVOList.size(); i++)
				{
					pendingConsentVO = pendingConsentVOList.get(i);
					if (pendingConsentVO.getPatCrNo().equals(arrayDailyPatVO[j].getPatCrNo()))
					{
						selectedPatientVO = new PatientDetailVO();
						selectedPatientVO = arrayDailyPatVO[j];
						patientDtlVOList.add(selectedPatientVO);
						break;
					}
				}
			}
			_fb.setPatCrNo(arrayDailyPatVO[0].getPatCrNo());
			array[0] = patientDtlVOList.size();//(pendingConsentVOList != null)? drugAdminDtlVOList.size():0;//

			// /////////////////////////////////////////////////////////////////////////////

			WebUTIL.setAttributeInSession(_rq, InpatientConfig.PENDING_TASK_LIST_SIZE, array);

			// setting the consent pending list as default page...
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.PENDING_CONSENT_REQUEST_VO_LIST, pendingConsentVOList);
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.INPATIENT_DTL_VO_LIST, patientDtlVOList);
			WebUTIL.setMapInSession(essentialMap, _rq);
			if (patientDtlVOList == null || patientDtlVOList.size() == 0)
			{
				patientDtlVOList = null;
				WebUTIL.setAttributeInSession(_rq, InpatientConfig.INPATIENT_DTL_VO_LIST, patientDtlVOList);
				throw new HisRecordNotFoundException("No Pending Consent Found");
			}

			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.LIST, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}

	/**
	 * get the details of the patient's pending consent
	 * 
	 * @param _fb
	 * @param _rq
	 * @return
	 */
	public static boolean getConsentDetailByCrNo(PendingTaskListFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List<ConsentRequestVO> pendingConsentList = new ArrayList<ConsentRequestVO>();
		try
		{
			HttpSession session = _rq.getSession();
			String patCrNo = _fb.getPatCrNo();
			List<ConsentRequestVO> consentVOList = (List) session.getAttribute(InpatientConfig.PENDING_CONSENT_REQUEST_VO_LIST);
			ConsentRequestVO consentVO = null;

			for (int i = 0; i < consentVOList.size(); i++)
			{
				if (patCrNo.equals(consentVOList.get(i).getPatCrNo()))
				{
					consentVO = new ConsentRequestVO();
					consentVO = consentVOList.get(i);
					pendingConsentList.add(consentVO);
				}
			}
			if (pendingConsentList.size() == 0)
			{
				throw new HisRecordNotFoundException("Detail Not Found");
			}
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.SELECTED_PATIENT_CONSENT_VO_LIST, pendingConsentList);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}

	/**
	 * get the list of patient whose treatment is pending today
	 * 
	 * @param _fb
	 * @param _rq
	 * @return
	 */
	public static boolean getPendingTreatmentList(PendingTaskListFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		List<DrugAdminDtlVO> drugAdminDtlVOList = null;
		List patientDtlVOList = new ArrayList();
		try
		{
			PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			drugAdminDtlVOList = (List) session.getAttribute(InpatientConfig.PENDING_TREATMENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			if (drugAdminDtlVOList != null && drugAdminDtlVOList.size() != 0) for (int j = 0; j < arrayDailyPatVO.length; j++)
			{
				for (int i = 0; i < drugAdminDtlVOList.size(); i++)
				{
					if (drugAdminDtlVOList.get(i).getPatCrNo().equals(arrayDailyPatVO[j].getPatCrNo()))
					{
						selectedPatientVO = new PatientDetailVO();
						selectedPatientVO = arrayDailyPatVO[j];
						patientDtlVOList.add(selectedPatientVO);
						break;
					}
				}
			}

			_fb.setPatCrNo(arrayDailyPatVO[0].getPatCrNo());
			if (patientDtlVOList == null || patientDtlVOList.size() == 0)
			{
				patientDtlVOList = null;
				WebUTIL.setAttributeInSession(_rq, InpatientConfig.INPATIENT_DTL_VO_LIST, patientDtlVOList);
				throw new HisRecordNotFoundException("No Pending Treatment Found");
			}
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.INPATIENT_DTL_VO_LIST, patientDtlVOList);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.LIST, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}

	/**
	 * get the todays's pending treatment detail of selected patient
	 * 
	 * @param _fb
	 * @param _rq
	 * @return
	 */
	public static boolean getPendingTreatmentDetailByCrNo(PendingTaskListFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		DrugAdminDtlVO drugAdminDtlVO = new DrugAdminDtlVO();
		List<DrugAdminDtlVO> drugAdminDtlVOList = new ArrayList<DrugAdminDtlVO>();
		List<DrugAdminDtlVO> pendingTreatmentList = new ArrayList<DrugAdminDtlVO>();
		try
		{
			HttpSession session = _rq.getSession();
			String patCrNo = _fb.getPatCrNo();
			drugAdminDtlVOList = (List) session.getAttribute(InpatientConfig.PENDING_TREATMENT_LIST);
			for (int i = 0; i < drugAdminDtlVOList.size(); i++)
			{
				if (patCrNo.equals(drugAdminDtlVOList.get(i).getPatCrNo()))
				{
					drugAdminDtlVO = new DrugAdminDtlVO();
					drugAdminDtlVO = drugAdminDtlVOList.get(i);
					pendingTreatmentList.add(drugAdminDtlVO);
				}
			}
			if (pendingTreatmentList.size() == 0)
			{
				WebUTIL.setAttributeInSession(_rq, InpatientConfig.SELECTED_PATIENT_TREATMENT_VO_LIST, pendingTreatmentList);
				throw new HisRecordNotFoundException("Detail Not Found");
			}
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.SELECTED_PATIENT_TREATMENT_VO_LIST, pendingTreatmentList);
			objStatus.add(Status.DONE);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}

	/**
	 * getting the list of patient whose sample collection is pending
	 * 
	 * @param _fb
	 * @param _rq
	 * @return
	 */
	public static boolean getPendingSampleCollectionList(PendingTaskListFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		// List <DrugAdminDtlVO> drugAdminDtlVOList=null;
		List patientDtlVOList = new ArrayList();
		List<PendingSampleCollectionVO> pendingSampleCollectionList = null;
		try
		{
			PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			pendingSampleCollectionList = (List) session.getAttribute(InpatientConfig.PENDING_SAMPLE_COLLECTION_LIST);
			PatientDetailVO selectedPatientVO = null;
			if (pendingSampleCollectionList != null)
			{
				for (int j = 0; j < arrayDailyPatVO.length; j++)
				{
					for (int i = 0; i < pendingSampleCollectionList.size(); i++)
					{
						if (pendingSampleCollectionList.get(i).getPatCrNo().equals(arrayDailyPatVO[j].getPatCrNo()))
						{
							selectedPatientVO = new PatientDetailVO();
							selectedPatientVO = arrayDailyPatVO[j];
							patientDtlVOList.add(selectedPatientVO);
							break;
						}
					}
				}
			}
			_fb.setPatCrNo(arrayDailyPatVO[0].getPatCrNo());
			if (patientDtlVOList == null || patientDtlVOList.size() == 0)
			{
				patientDtlVOList = null;
				WebUTIL.setAttributeInSession(_rq, InpatientConfig.INPATIENT_DTL_VO_LIST, patientDtlVOList);
				throw new HisRecordNotFoundException("No Pending Sample For Collection");
			}
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.INPATIENT_DTL_VO_LIST, patientDtlVOList);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.LIST, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}

	/**
	 * gets the detail of pending sample collection of selected patient
	 * 
	 * @param _fb
	 * @param _rq
	 * @return
	 */
	public static boolean getPendingSampleCollectionByCrNo(PendingTaskListFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		PendingSampleCollectionVO pendingSampleColVO = new PendingSampleCollectionVO();
		List<PendingSampleCollectionVO> pendingSampleCollectionVOList = null;
		List<PendingSampleCollectionVO> pendingSampleCol = new ArrayList<PendingSampleCollectionVO>();
		try
		{
			HttpSession session = _rq.getSession();
			String patCrNo = _fb.getPatCrNo();
			pendingSampleCollectionVOList = (List) session.getAttribute(InpatientConfig.PENDING_SAMPLE_COLLECTION_LIST);
			for (int i = 0; i < pendingSampleCollectionVOList.size(); i++)
			{
				if (patCrNo.equals(pendingSampleCollectionVOList.get(i).getPatCrNo()))
				{
					pendingSampleColVO = new PendingSampleCollectionVO();
					pendingSampleColVO = pendingSampleCollectionVOList.get(i);
					pendingSampleCol.add(pendingSampleColVO);
				}
			}
			if (pendingSampleCol.size() == 0)
			{
				WebUTIL.setAttributeInSession(_rq, InpatientConfig.SELECTED_PATIENT_PENDING_SAMPLE_COLLECTION, pendingSampleCol);
				throw new HisRecordNotFoundException("Detail Not Found");
			}
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.SELECTED_PATIENT_PENDING_SAMPLE_COLLECTION, pendingSampleCol);
			objStatus.add(Status.RECORDFOUND);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}

	/**
	 * gets the list of patient whose Instruction/Activity is pending
	 * 
	 * @param _fb
	 * @param _rq
	 * @return
	 */
	public static boolean getPendingInstructionList(PendingTaskListFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		// List pendingInstructionList=null;
		// Map essentialMap = new HashMap();
		List patientDtlVOList = new ArrayList();
		try
		{
			PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			// pendingInstructionList= (List) session.getAttribute(InpatientConfig.PENDING_);
			// PatientDetailVO selectedPatientVO = null;
			/*
			 * if(pendingInstructionList!=null) for(int j=0;j<arrayDailyPatVO.length;j++){ for (int i = 0; i <
			 * pendingInstructionList.size(); i++) { if
			 * (pendingInstructionList.get(i).getPatCrNo().equals(arrayDailyPatVO[j].getPatCrNo())) { selectedPatientVO=new
			 * PatientDetailVO(); selectedPatientVO = arrayDailyPatVO[j]; patientDtlVOList.add(selectedPatientVO); break; } } }
			 */
			_fb.setPatCrNo(arrayDailyPatVO[0].getPatCrNo());
			if (patientDtlVOList == null || patientDtlVOList.size() == 0)
			{
				patientDtlVOList = null;
				WebUTIL.setAttributeInSession(_rq, InpatientConfig.INPATIENT_DTL_VO_LIST, patientDtlVOList);
				throw new HisRecordNotFoundException("No Pending Instruction Found");
			}
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.INPATIENT_DTL_VO_LIST, patientDtlVOList);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.LIST, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}

	/**
	 * gets the pending Instruction/Activity of selected patient
	 * 
	 * @param _fb
	 * @param _rq
	 * @return
	 */
	public static boolean getPendingInstructionByCrNo(PendingTaskListFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		DrugAdminDtlVO drugAdminDtlVO = new DrugAdminDtlVO();
		List<DrugAdminDtlVO> drugAdminDtlVOList = new ArrayList<DrugAdminDtlVO>();
		List<DrugAdminDtlVO> pendingTreatmentList = new ArrayList<DrugAdminDtlVO>();
		try
		{
			HttpSession session = _rq.getSession();
			String patCrNo = _fb.getPatCrNo();
			drugAdminDtlVOList = (List) session.getAttribute(InpatientConfig.PENDING_INSTRUCTION_LIST);
			for (int i = 0; i < drugAdminDtlVOList.size(); i++)
			{
				if (patCrNo.equals(drugAdminDtlVOList.get(i).getPatCrNo()))
				{
					drugAdminDtlVO = new DrugAdminDtlVO();
					drugAdminDtlVO = drugAdminDtlVOList.get(i);
					pendingTreatmentList.add(drugAdminDtlVO);
				}
			}
			if (pendingTreatmentList.size() == 0)
			{
				WebUTIL.setAttributeInSession(_rq, InpatientConfig.SELECTED_PATIENT_PENDING_INSTRUCTION, pendingTreatmentList);
				throw new HisRecordNotFoundException("Detail Not Found");
			}
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.SELECTED_PATIENT_PENDING_INSTRUCTION, pendingTreatmentList);
			objStatus.add(Status.DONE);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.DONE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}

	public static boolean getPendingVitalMonitoring(PendingTaskListFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		List<PatientMonitoringMstVO> monitoringMstVOList = new ArrayList<PatientMonitoringMstVO>();
		List patientDtlVOList = new ArrayList();
		try
		{
			PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			monitoringMstVOList = (List) session.getAttribute(InpatientConfig.PENDING_MONITORING_LIST);
			PatientDetailVO selectedPatientVO = null;
			if (monitoringMstVOList != null) for (int j = 0; j < arrayDailyPatVO.length; j++)
			{
				for (int i = 0; i < monitoringMstVOList.size(); i++)
				{
					if (monitoringMstVOList.get(i).getPatCrNo().equals(arrayDailyPatVO[j].getPatCrNo()))
					{
						selectedPatientVO = new PatientDetailVO();
						selectedPatientVO = arrayDailyPatVO[j];
						patientDtlVOList.add(selectedPatientVO);
						break;
					}
				}
			}
			_fb.setPatCrNo(arrayDailyPatVO[0].getPatCrNo());
			if (patientDtlVOList == null || patientDtlVOList.size() == 0)
			{
				patientDtlVOList = null;
				WebUTIL.setAttributeInSession(_rq, InpatientConfig.INPATIENT_DTL_VO_LIST, patientDtlVOList);
				throw new HisRecordNotFoundException("No Pending Vitals");
			}
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.INPATIENT_DTL_VO_LIST, patientDtlVOList);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.LIST, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}

	public static boolean getPendingMonitoringByCrNo(PendingTaskListFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		PatientMonitoringMstVO monitoringVO = new PatientMonitoringMstVO();
		List<PatientMonitoringMstVO> monitoringMstVOList = new ArrayList<PatientMonitoringMstVO>();
		List<PatientMonitoringMstVO> pendingMonitoringList = new ArrayList<PatientMonitoringMstVO>();
		try
		{
			HttpSession session = _rq.getSession();
			String patCrNo = _fb.getPatCrNo();
			monitoringMstVOList = (List) session.getAttribute(InpatientConfig.PENDING_MONITORING_LIST);
			for (int i = 0; i < monitoringMstVOList.size(); i++)
			{
				if (patCrNo.equals(monitoringMstVOList.get(i).getPatCrNo()))
				{
					monitoringVO = new PatientMonitoringMstVO();
					monitoringVO = monitoringMstVOList.get(i);
					pendingMonitoringList.add(monitoringVO);
				}
			}
			if (pendingMonitoringList.size() == 0)
			{
				WebUTIL.setAttributeInSession(_rq, InpatientConfig.SELECTED_PATIENT_PENDING_MONITORING, pendingMonitoringList);
				throw new HisRecordNotFoundException("Detail Not Found");
			}
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.SELECTED_PATIENT_PENDING_MONITORING, pendingMonitoringList);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return true;
	}
}
