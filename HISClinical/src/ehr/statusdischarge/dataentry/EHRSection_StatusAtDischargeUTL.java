/*
 * Vasu
 * Date:- 05-03-2019
 * New Process Status At Discharge
*/
package ehr.statusdischarge.dataentry;

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
import ehr.followup.dataentry.EHRSection_FollowupDATA;
import ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO;
import ehr.vo.EHRVOUtility;
import emr.vo.EHR_PatEncounterDetailsVO;

public class EHRSection_StatusAtDischargeUTL extends ControllerUTIL{

	public static void getEssentials(EHRSection_StatusAtDischargeFB _fb,HttpServletRequest _request, HttpServletResponse response) {
		
		Boolean flag_exist=true;
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		EHRSection_StatusAtDischargeVO statusDischargeDtlVO = null;
		EHRSection_StatusAtDischargeVO visitVOPrint = new EHRSection_StatusAtDischargeVO();
		Map mpEssential = new HashMap();
		
		try
		{
			PatientDetailVO selectedPatientVO =null;
			UserVO userVO = getUserVO(_request);
			HospitalMstVO hospVO = getHospitalVO(_request);
			setSysdate(_request);	// Required for Current Date

			EHRSection_StatusAtDischargeVO statusDischargeVO = new EHRSection_StatusAtDischargeVO();
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			statusDischargeVO.setAdmissionNo(selectedPatientVO.getPatAdmNo());
			statusDischargeVO.setPatCrNo(selectedPatientVO.getPatCrNo());
			statusDischargeVO.setEpisodeCode(selectedPatientVO.getEpisodeCode());
			statusDischargeVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
			statusDischargeVO.setDepartmentCode(selectedPatientVO.getDepartmentCode());
			statusDischargeVO.setDepartmentUnitCode(selectedPatientVO.getDepartmentUnitCode());
						
			statusDischargeDtlVO = EHRSection_StatusAtDischargeDATA.getEssentials(statusDischargeVO, userVO);	
				
			if(statusDischargeDtlVO.getStatusAtDischarge()!=null)
			{
				_fb.setStatusAtDischarge(statusDischargeDtlVO.getStatusAtDischarge());
			}
				
				
		   EHRVOUtility.setStatusAtDischargeVO(_request, _fb.getPatCrNo(), statusDischargeDtlVO); //Added by Vasu on 05.Mar.2018
				
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
			HttpServletResponse response, EHRSection_StatusAtDischargeFB _fb) {

		Status objStatus = new Status();
		String saveFlag = "true";
		try
		{
			PatientDetailVO selectedPatientVO =null;
			HttpSession session = WebUTIL.getSession(_request);
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			EHRSection_StatusAtDischargeVO dischargeVO = new EHRSection_StatusAtDischargeVO();
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			dischargeVO.setAdmissionNo(selectedPatientVO.getPatAdmNo());
			dischargeVO.setPatCrNo(_fb.getPatCrNo());
			dischargeVO.setIsConfirmed(_fb.getIsConfirmed());
			dischargeVO.setDepartmentUnitCode(selectedPatientVO.getDepartmentUnitCode());
			dischargeVO.setStatusAtDischarge(_fb.getStatusAtDischarge());
			dischargeVO.setSnomdCIdstatusAtDischarge(_fb.getSnomdCIdStatusAtDischarge());
			dischargeVO.setSnomdPTstatusAtDischarge(_fb.getSnomdPTStatusAtDischarge());
			//dischargeVO.setAdmissionNo(_fb.getAdmissionNo());
			dischargeVO.setEpisodeCode(selectedPatientVO.getEpisodeCode());
			dischargeVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
			//dischargeVO.setDataExist(_fb.getDataExist());
			//System.out.println("Inside-->> saveEhrDischargedetails\n\nPatCrNo"+_fb.getPatCrNo()+"\nIsConfirmed "+_fb.getIsConfirmed()+"\ngetDepartmentUnitCode"+_fb.getDepartmentUnitCode()+"\nStatusAtDischarge"+_fb.getStatusAtDischarge()+"\nSnomdCIdStatusAtDischarge"+_fb.getSnomdCIdStatusAtDischarge()+"\nSnomdPTStatusAtDischarge"+_fb.getSnomdPTStatusAtDischarge()+"\nAdmissionNo"+_fb.getAdmissionNo()+"\nEpisodeCode"+_fb.getEpisodeCode()+"Inside Save--> DataExist: "+_fb.getDataExist());
			
			
			
			EHRSection_StatusAtDischargeDATA.saveDetails(dischargeVO, getUserVO(_request));
			EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_request).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			patencountervo.setListSaveAllDischarge(dischargeVO);/* Nilesh Gupta*/
			
			WebUTIL.getSession(_request).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			
			saveFlag="false";
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			
			saveFlag="false";
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			
			saveFlag="false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{

		
			saveFlag="false";
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			
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
	
	public static void saveDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_StatusAtDischargeFB _fb) 
	{
		String flag=EHRSection_StatusAtDischargeUTL.saveEhrDischargedetails(_rq,response,_fb);
		
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
