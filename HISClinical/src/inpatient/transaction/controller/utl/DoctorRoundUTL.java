package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.NurseRoundDtlVO;
import hisglobal.vo.PatientDetailVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.DoctorRoundDATA;
import inpatient.transaction.controller.fb.DoctorRoundFB;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoctorRoundUTL extends ControllerUTIL
{
	public static void getEssentialDocRoundForNurse(DoctorRoundFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = request.getSession();

		try
		{
			String unitCode=fb.getUnitCode();

			// Employee List should be on the basis of unitCode
			List lstEmployee = DoctorRoundDATA.getEmployeeListUnitWise(unitCode, getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.ESSENTIAL_EMPLOYEE_LIST_UNIT_WISE, lstEmployee);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(request, InpatientConfig.ESSENTIAL_EMPLOYEE_LIST_UNIT_WISE, new ArrayList());
			objStatus.add(Status.UNSUCESSFULL, "", "Doctor Not Found");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void getDeptUnitList(DoctorRoundFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PatientDetailVO[] dailyPatientVOs = null;
		
		try
		{
			List lstUnit=DoctorRoundDATA.getDeptUnitList(getUserVO(request));
			
			// Episode, Visit, Admission No
			/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(fb.getPatCrNo()))
				{
					voDP = vo;
					break;
				}*/

			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			fb.setPatCrNo(voDP.getPatCrNo());			
			fb.setUnitCode(voDP.getDepartmentUnitCode());

			WebUTIL.setAttributeInSession(request,InpatientConfig.DEPT_UNIT_LIST ,lstUnit);
			
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
		
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	
	}
	public static void saveDoctorVisitNotes(DoctorRoundFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		DoctorRoundDtlVO docRoundDtlVO = new DoctorRoundDtlVO();

		try
		{
			/*PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
			{
				if (fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO = arrayDailyPatVO[i];
				}
			}*/
			PatientDetailVO[] dailyPatientVOs = null;
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}

			HelperMethods.populate(docRoundDtlVO, fb);
			HelperMethods.populate(docRoundDtlVO, voDP);
			docRoundDtlVO.setEmployeeNo(fb.getEmployeeNo().split("#")[0]); /* Nilesh Gupta 8-11-2017 */ 
			DoctorRoundDATA.saveDoctorVisitNotes(docRoundDtlVO, getUserVO(request));
			// Nursing Desk Data Set
			PatNursingDeskDataFlagsMenuWiseUTL.setMenuDetailFlag(request, fb.getDeskMenuId());
			
			objStatus.add(Status.DONE, "", "Visit Notes Added Successfully");
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
	}

	public static void getDoctorInstruction(DoctorRoundFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		String patAdmNo = "";
		try
		{
			/*PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
			{
				if (fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO = arrayDailyPatVO[i];
				}
			}*/
			PatientDetailVO[] dailyPatientVOs = null;
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}

			HelperMethods.populate(fb, voDP);
			patAdmNo = fb.getPatAdmNo();
			DoctorRoundDtlVO[] docRoundDtlVO = DoctorRoundDATA.getDoctorInstruction(patAdmNo, getUserVO(request));
			DoctorRoundDtlVO[] docRoundDtlVO1 =null;
			
			int count=0;
			int len=0;
			for(int i=0;i<docRoundDtlVO.length;i++)
			{
				if(docRoundDtlVO[i].getInstruction()!=null)
				{
					len++;
				}
			}	
			docRoundDtlVO1=new DoctorRoundDtlVO[len];
			for(int i=0;i<docRoundDtlVO.length;i++)
			{
				if(docRoundDtlVO[i].getInstruction()!=null)
				{
					docRoundDtlVO1[count]=new DoctorRoundDtlVO();
					docRoundDtlVO1[count]=docRoundDtlVO[i];
					count++;
				}
			}
			WebUTIL.setAttributeInSession(request, InpatientConfig.ARR_DOCTOR_INSTRUCTION, docRoundDtlVO1);
			if(len==0)
				objStatus.add(Status.UNSUCESSFULL,"","No Doctor Instructions Found");
			else
				objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
			WebUTIL.setStatus(request, objStatus);
		}
	}

	public static void getUnverifiedEntryByNurse(DoctorRoundFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		String patAdmNo = "";
		PatientDetailVO[] dailyPatientVOs = null;

		try
		{
			/*PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
			{
				if (fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO = arrayDailyPatVO[i];
				}
			}
*/
		
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			if(dailyPatientVOs!=null)
			HelperMethods.populate(fb, dailyPatientVOs);
			patAdmNo = fb.getPatAdmNo();

			DoctorRoundDtlVO[] arrUnverifiedRecordVO = DoctorRoundDATA.getUnverifiedEntryByNurse(patAdmNo, getUserVO(request));

			if (arrUnverifiedRecordVO.length == 0) 
			{
				objStatus.add(Status.TRANSINPROCESS, "", "");
			}
			else
			{
				WebUTIL.setAttributeInSession(request, InpatientConfig.ARR_UNVERIFIED_RECORD_ENTERBY_NURSE, arrUnverifiedRecordVO);
				objStatus.add(Status.LIST);
			}	
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
			WebUTIL.setStatus(request, objStatus);
		}
	}

	public static void getRecordDetail(DoctorRoundFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		int index = Integer.parseInt(fb.getRadioBtn());
		String patCrNo = fb.getPatCrNo();

		try
		{
			DoctorRoundDtlVO[] arrUnverifiedRecordVO = (DoctorRoundDtlVO[]) session.getAttribute(InpatientConfig.ARR_UNVERIFIED_RECORD_ENTERBY_NURSE);
			DoctorRoundDtlVO recordDtlVO = DoctorRoundDATA.getRecordDetail(arrUnverifiedRecordVO[index], getUserVO(request));
			HelperMethods.populate(fb, recordDtlVO);
			fb.setPatCrNo(patCrNo);
			fb.setEnterBy(arrUnverifiedRecordVO[index].getUserName());
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
			WebUTIL.setStatus(request, objStatus);
		}
	}

	public static void saveNVerifyNotesByDoctor(DoctorRoundFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		DoctorRoundDtlVO docRoundDtlVO = new DoctorRoundDtlVO();
		int index = Integer.parseInt(fb.getRadioBtn());

		try
		{
			/*
			 * PatientDetailVO[] arrayDailyPatVO=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			 * PatientDetailVO selectedPatientVO=null; for(int i=0;i<arrayDailyPatVO.length;i++) {
			 * if(fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo())) { selectedPatientVO=arrayDailyPatVO[i]; } }
			 */
			DoctorRoundDtlVO[] arrUnverifiedRecordVO = (DoctorRoundDtlVO[]) session.getAttribute(InpatientConfig.ARR_UNVERIFIED_RECORD_ENTERBY_NURSE);
			HelperMethods.populate(docRoundDtlVO, arrUnverifiedRecordVO[index]);
			docRoundDtlVO.setProgressNote(fb.getProgressNote());
			docRoundDtlVO.setInstruction(fb.getInstruction());
			docRoundDtlVO.setVisitNote(fb.getVisitNote());
			DoctorRoundDATA.saveNVerifyNotesByDoctor(docRoundDtlVO, getUserVO(request));
			getUnverifiedEntryByNurse(fb, request);
		//	objStatus.add(Status.LIST, "", "Record Added Successfully");
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
	}

	public static boolean saveNotesByDoctor(DoctorRoundFB fb, HttpServletRequest request)
	{
		boolean bFlag = true;
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		DoctorRoundDtlVO docRoundDtlVO = new DoctorRoundDtlVO();
		PatientDetailVO[] dailyPatientVOs = null;

		try
		{
			/*PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
			{
				if (fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO = arrayDailyPatVO[i];
				}
			}*/
			
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			fb.setPatCrNo(voDP.getPatCrNo());			
			fb.setUnitCode(voDP.getDepartmentUnitCode());

			
			
			if(voDP!=null)
			HelperMethods.populate(docRoundDtlVO, voDP);
			docRoundDtlVO.setInstruction(fb.getInstruction());
			docRoundDtlVO.setProgressNote(fb.getProgressNote());
			docRoundDtlVO.setVisitNote(fb.getVisitNote());
			docRoundDtlVO.setDocVisitDate(fb.getDocVisitDate());
			docRoundDtlVO.setDocVisitTimeHr(fb.getDocVisitTimeHr());
			docRoundDtlVO.setDocVisitTimeMin(fb.getDocVisitTimeMin());
			//snomed-ct integration
			docRoundDtlVO.setSnomdCIdVisitNotes(fb.getSnomdCIdVisitNotes());
			docRoundDtlVO.setSnomdPTVisitNotes(fb.getSnomdPTVisitNotes());
			docRoundDtlVO.setSnomdPTProgessNotes(fb.getSnomdPTProgessNotes());
			docRoundDtlVO.setSnomdCIdProgressNotes(fb.getSnomdCIdProgressNotes());
			docRoundDtlVO.setSnomdPTInstruction(fb.getSnomdPTInstruction());
			docRoundDtlVO.setSnomdCIdInstruction(fb.getSnomdCIdInstruction());
			
			DoctorRoundDATA.saveNotesByDoctor(docRoundDtlVO, getUserVO(request));
		}
		catch (HisDataAccessException e)
		{
			bFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			bFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			bFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			bFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return bFlag;
	}

	// Getting Doctor Previous Round Detail
	public static void getDoctorPrevRoundDtl(DoctorRoundFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		String patAdmNo = "";
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
		/*	PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
			{
				if (fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO = arrayDailyPatVO[i];
				}
			}
*/

			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
	
			HelperMethods.populate(fb, voDP);
			patAdmNo = fb.getPatAdmNo();
			DoctorRoundDtlVO[] docRoundDtlVO = DoctorRoundDATA.getDoctorPrevRoundDetail(patAdmNo, getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.ARR_DOCTOR_PREV_ROUND_DETAIL, docRoundDtlVO);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void getVisitNotesToAdd(DoctorRoundFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			List lst=DoctorRoundDATA.getVisitNotesToAdd(fb.getProcessId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.VISIT_NOTES_LIST , lst);
			if(lst.size()>0)
				objStatus.add(Status.NEW, "", "");
			else
				objStatus.add(Status.NEW, "", "No Record Found");
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}	
	}
	
	public static void getPreviousNurseProgressNotes(DoctorRoundFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		String patAdmNo="";
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
			/*DailyPatientVO[] arrayDailyPatVO=(DailyPatientVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			DailyPatientVO selectedPatientVO=null;
			for(int i=0;i<arrayDailyPatVO.length;i++)
			{
				if(fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO=arrayDailyPatVO[i];
				}
			}*/
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
	
			HelperMethods.populate(fb, voDP);
			patAdmNo=fb.getPatAdmNo();
			NurseRoundDtlVO[] arrNursePrevProgressNotes=DoctorRoundDATA.getPreviousNurseProgressNotes(patAdmNo,getUserVO(request));
			WebUTIL.setAttributeInSession(request,InpatientConfig.ARR_PAT_PREVIOUS_PRORESSNOTES , arrNursePrevProgressNotes);
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}	
	}
	
	//Added by Vasu on 26.Sept.2018 to update doctor Notes
	public static boolean updateNotesByDoctor(DoctorRoundFB fb, HttpServletRequest request)
	{
		boolean bFlag = true;
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		DoctorRoundDtlVO docRoundDtlVO = new DoctorRoundDtlVO();
		

		try
		{
			PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
			{
				if (fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO = arrayDailyPatVO[i];
				}
			}

			HelperMethods.populate(docRoundDtlVO, selectedPatientVO);
			docRoundDtlVO.setInstruction(fb.getInstruction());
			docRoundDtlVO.setProgressNote(fb.getProgressNote());
			docRoundDtlVO.setVisitNote(fb.getVisitNote());
			docRoundDtlVO.setDocVisitDate(fb.getDocVisitDate());
			docRoundDtlVO.setDocVisitTimeHr(fb.getDocVisitTimeHr());
			docRoundDtlVO.setDocVisitTimeMin(fb.getDocVisitTimeMin());
			docRoundDtlVO.setRoundDate(fb.getRoundDate());
			docRoundDtlVO.setRoundNo(fb.getRoundNo());
			docRoundDtlVO.setSerialNo(fb.getSerialNo());
			
			DoctorRoundDATA.upateNotesByDoctor(docRoundDtlVO, getUserVO(request));
		}
		catch (HisDataAccessException e)
		{
			bFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			bFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			bFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			bFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return bFlag;
	}
}
