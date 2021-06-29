/*
 * Vasu
 * Date:- 05-03-2019
 * New Process Status At Discharge
*/
package ehr.medicationAdvice.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ehr.EHRConfig;

import ehr.medicationAdvice.vo.EHRSection_Medication_AdviceVO;
import ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO;
import ehr.vo.EHRVOUtility;
import emr.vo.EHR_PatEncounterDetailsVO;

public class EHRSection_Medication_AdviceUTL extends ControllerUTIL{

public static void getEssentials(EHRSection_Medication_AdviceFB _fb,HttpServletRequest _request, HttpServletResponse response) {
		
		Boolean flag_exist=true;
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		EHRSection_Medication_AdviceVO summaryDtlVO = null;
		EHRSection_StatusAtDischargeVO visitVOPrint = new EHRSection_StatusAtDischargeVO();
		Map mpEssential = new HashMap();
		
		try
		{
			PatientDetailVO selectedPatientVO =null;
			UserVO userVO = getUserVO(_request);
			HospitalMstVO hospVO = getHospitalVO(_request);
			setSysdate(_request);	// Required for Current Date

			EHRSection_Medication_AdviceVO summaryVO = new EHRSection_Medication_AdviceVO();
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			summaryVO.setAdmissionNo(selectedPatientVO.getPatAdmNo());
			summaryVO.setPatCrNo(selectedPatientVO.getPatCrNo());
			summaryVO.setEpisodeCode(selectedPatientVO.getEpisodeCode());
			summaryVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
			summaryVO.setDeptCode(selectedPatientVO.getDepartmentCode());
			summaryVO.setDeptUnitCode(selectedPatientVO.getDepartmentUnitCode());
						
			summaryDtlVO = EHRSection_Medication_AdviceDATA.getEssentials(summaryVO, userVO);	
				
			if(summaryDtlVO.getMedication()!=null)
			{
				_fb.setMedication(summaryDtlVO.getMedication());
			}
				
				
		   EHRVOUtility.setmedicationVO(_request, _fb.getPatCrNo(), summaryDtlVO);
				
			objStatus.add(Status.TRANSINPROCESS);
		
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


public static String saveEhrDischargedetails(HttpServletRequest _request,HttpServletResponse response, EHRSection_Medication_AdviceFB _fb) {

	Status objStatus = new Status();
	String saveFlag = "true";
	try
	{
		PatientDetailVO selectedPatientVO =null;
		HttpSession session = WebUTIL.getSession(_request);
		UserVO userVO = getUserVO(_request);
		setSysdate(_request);
		
		EHRSection_Medication_AdviceVO summaryVO = new EHRSection_Medication_AdviceVO();
		selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		summaryVO.setAdmissionNo(selectedPatientVO.getPatAdmNo());
		summaryVO.setPatCrNo(selectedPatientVO.getPatCrNo());
		summaryVO.setEpisodeCode(selectedPatientVO.getEpisodeCode());
		summaryVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
		summaryVO.setDeptCode(selectedPatientVO.getDepartmentCode());
		summaryVO.setDeptUnitCode(selectedPatientVO.getDepartmentUnitCode());
		summaryVO.setMedication(_fb.getMedication());
		summaryVO.setSnomdPTMedication(_fb.getSnomdPTMedication());
		summaryVO.setSnomdCIDMedication(_fb.getSnomdCIDMedication());
		
		EHRSection_Medication_AdviceDATA.saveDetails(summaryVO, getUserVO(_request));
		EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_request).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);

		objStatus.add(Status.TRANSINPROCESS);
	}
	catch (HisRecordNotFoundException e)
	{
		//System.out.println("Inside HisRecordNotFoundException");
		saveFlag="false";
		objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
	}
	catch (HisDataAccessException e)
	{
		//System.out.println("Inside HisDataAccessException");
		saveFlag="false";
		objStatus.add(Status.ERROR_DA, e.getMessage(), "");
	}
	catch (HisApplicationExecutionException e)
	{
		//System.out.println("Inside HisApplicationExecutionException");
		saveFlag="false";
		objStatus.add(Status.ERROR_AE, e.getMessage(), "");
	}
	catch (HisException e)
	{
		//System.out.println("Inside HisException");
		saveFlag="false";
		objStatus.add(Status.ERROR, e.getMessage(), "");
	}
	catch (Exception e)
	{
		//System.out.println("Inside HisException");
		saveFlag="false";
		objStatus.add(Status.ERROR_AE, e.getMessage(), "");
	}
	finally
	{
		WebUTIL.setStatus(_request, objStatus);
	}
	return saveFlag;
	//return isSave;
	
}
	public static void saveDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_Medication_AdviceFB _fb) 
	{
		String flag=EHRSection_Medication_AdviceUTL.saveEhrDischargedetails(_rq,response,_fb);
		
	
	}
	
	public static void writeResponse(HttpServletResponse resp, String output)
			throws IOException {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::writeResponse");

		try {
			resp.reset();
			resp.flushBuffer();
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "no-cache");
			resp.getWriter().write(output);
		} catch (Exception e) {
						e.printStackTrace();

		}
	}
	

}
