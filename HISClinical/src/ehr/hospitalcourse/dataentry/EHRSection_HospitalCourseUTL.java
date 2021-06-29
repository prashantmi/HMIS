/*
 * 	Vasu
 * Date:- 01-08-2018
*/
package ehr.hospitalcourse.dataentry;

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
import ehr.casesummary.dataentry.EHRSection_CaseSummaryDATA;
import ehr.casesummary.dataentry.EHRSection_CaseSummaryFB;
import ehr.casesummary.vo.EHRSection_CaseSummaryVO;
import ehr.followup.dataentry.EHRSection_FollowupDATA;
import ehr.hospitalcourse.vo.EHRSection_HospitalCourseVO;
import ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO;
import ehr.vo.EHRVOUtility;
import emr.vo.EHR_PatEncounterDetailsVO;

public class EHRSection_HospitalCourseUTL extends ControllerUTIL{

	public static void getEssentials(EHRSection_HospitalCourseFB _fb,HttpServletRequest _request, HttpServletResponse response) {
			
			Boolean flag_exist=true;
			HttpSession session = _request.getSession();
			Status objStatus = new Status();
			EHRSection_CaseSummaryVO summaryDtlVO = null;
			EHRSection_StatusAtDischargeVO visitVOPrint = new EHRSection_StatusAtDischargeVO();
			Map mpEssential = new HashMap();
			
			try
			{
				PatientDetailVO selectedPatientVO =null;
				UserVO userVO = getUserVO(_request);
				HospitalMstVO hospVO = getHospitalVO(_request);
				setSysdate(_request);	// Required for Current Date

				EHRSection_HospitalCourseVO hospitalCourseVO = new EHRSection_HospitalCourseVO();
				selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				hospitalCourseVO.setAdmissionNo(selectedPatientVO.getPatAdmNo());
				hospitalCourseVO.setPatCrNo(selectedPatientVO.getPatCrNo());
				hospitalCourseVO.setEpisodeCode(selectedPatientVO.getEpisodeCode());
				hospitalCourseVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
				hospitalCourseVO.setDepartmentCode(selectedPatientVO.getDepartmentCode());
				hospitalCourseVO.setDepartmentUnitCode(selectedPatientVO.getDepartmentUnitCode());
							
				hospitalCourseVO = EHRSection_HospitalCourseDATA.getEssentials(hospitalCourseVO, userVO);	
					
				if(hospitalCourseVO.getHospitalCourse()!=null)
				{
					_fb.setHospitalCourse(hospitalCourseVO.getHospitalCourse());
				}
					
					
			   EHRVOUtility.setHospitalCourseVO(_request, _fb.getPatCrNo(), hospitalCourseVO); //Added by Vasu on 05.Mar.2018
					
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

	public static String saveEhrDischargedetails(HttpServletRequest _request,
			HttpServletResponse response, EHRSection_HospitalCourseFB _fb) {

		Status objStatus = new Status();
		String saveFlag = "true";
		try
		{
			HttpSession session = WebUTIL.getSession(_request);
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			PatientDetailVO selectedPatientVO =null;
			
			EHRSection_HospitalCourseVO dischargeVO = new EHRSection_HospitalCourseVO();
			
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			dischargeVO.setAdmissionNo(selectedPatientVO.getPatAdmNo());
			dischargeVO.setPatCrNo(selectedPatientVO.getPatCrNo());
			dischargeVO.setEpisodeCode(selectedPatientVO.getEpisodeCode());
			dischargeVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
			dischargeVO.setDepartmentCode(selectedPatientVO.getDepartmentCode());
			dischargeVO.setDepartmentUnitCode(selectedPatientVO.getDepartmentUnitCode());
			dischargeVO.setHospitalCourse(_fb.getHospitalCourse());
			dischargeVO.setSnomdPTHospitalCourse(_fb.getSnomdPTHospitalCourse());
			dischargeVO.setSnomdCIdHospitalCourse(_fb.getSnomdCIdHospitalCourse());
			
			
			EHRSection_HospitalCourseDATA.saveDetails(dischargeVO, getUserVO(_request));
			
			EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_request).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			patencountervo.setListSaveAllHospitalCourse(dischargeVO);
			
			WebUTIL.getSession(_request).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
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
	
	public static void saveDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_HospitalCourseFB _fb) 
	{
		String flag=EHRSection_HospitalCourseUTL.saveEhrDischargedetails(_rq,response,_fb);
		
		try
		{
			writeResponse(response, flag);
		}
		catch (Exception e) {
			// //LOGGER_INV.log(Level.INFO,e.getMessage());}
			e.printStackTrace();
			
		}
		
		
		
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
