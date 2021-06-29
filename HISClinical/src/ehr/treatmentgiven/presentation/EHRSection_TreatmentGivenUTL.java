/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.treatmentgiven.presentation;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import registration.RegistrationConfig;
import ehr.EHRConfig;
import ehr.diagnosis.presentation.EHRSection_DiagnosisFB;
import ehr.treatmentgiven.presentation.EHRSection_TreatmentGivenFB;
import ehr.treatmentgiven.presentation.EHRSection_TreatmentGivenLNK;
import ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO;
import ehr.diagnosis.presentation.EHRSection_DiagnosisLNK;
import ehr.diagnosis.presentation.EHRSection_DiagnosisUTL;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import emr.vo.EHR_PatEncounterDetailsVO;

public class EHRSection_TreatmentGivenUTL extends ControllerUTIL
{
	
	public static void saveDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_TreatmentGivenFB _fb) 
	{
		String flag=EHRSection_TreatmentGivenUTL.saveGivenTreatmentDetails(_rq,response,_fb);
		
		try
		{
			//writeResponse(response, flag);
		}
		catch (Exception e) {
				e.printStackTrace();
			
		}
		
		
		
	}
	
	public static String saveGivenTreatmentDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_TreatmentGivenFB _fb) 
	{
		
		String isSave = "true";
		Status objStatus = new Status();
		DailyPatientVO selectedPatientVO = new DailyPatientVO();
		
		List<EHRSection_TreatmentGivenVO> addTreatment= new ArrayList<EHRSection_TreatmentGivenVO>();
		List<EHRSection_TreatmentGivenVO> revokedTreatment= new ArrayList<EHRSection_TreatmentGivenVO>();
		List<EHRSection_TreatmentGivenVO> allSaveListTreatment= new ArrayList<EHRSection_TreatmentGivenVO>();
		int currentDiagLength = 0;
		
		try
		{

			HelperMethods.populatetToNullOrEmpty(selectedPatientVO, _fb);
			//currentDiagLength=_fb.getDiagonisticTypeCode().length;		
			//currentDiagLength =_fb.getSnomedCTTreatmentCode().length;
			currentDiagLength = _fb.getTreatmentGivenRecordStatus().length;
			for (int k = 0; k < currentDiagLength; k++)
			{
				//if(!_fb.getSnomedCTTreatmentCode()[k].isEmpty())
				//{
				
				if(_fb.getTreatmentGivenRecordStatus()[k].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_NOT_SAVED))
				{
					
					    EHRSection_TreatmentGivenVO addTreatmentVO = new EHRSection_TreatmentGivenVO();
			
					    addTreatmentVO.setTreatmentType(_fb.getTreatmentType()[k]);
					    addTreatmentVO.setSnomedCTTreatmentName(_fb.getSnomedCTTreatmentName()[k]);
					    addTreatmentVO.setSnomedCTTreatmentCode(_fb.getSnomedCTTreatmentCode()[k]);
					    addTreatmentVO.setTreatmentDate(_fb.getTreatmentDate()[k]);
					    addTreatmentVO.setSnomedPTRemarks(_fb.getSnomedPTRemarks()[k]);
					    addTreatmentVO.setSnomedCIdRemarks(_fb.getSnomedCIdRemarks()[k]);
					    addTreatmentVO.setRemarks(_fb.getRemarks()[k]);
						HelperMethods.populatetToNullOrEmpty(addTreatmentVO, selectedPatientVO);
						addTreatmentVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
                        addTreatmentVO.setAdmissionNo(_fb.getAdmissionNo());
					    addTreatment.add(addTreatmentVO);
				}
				
				if(_fb.getTreatmentGivenRecordStatus()[k].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_TOREVOKE))
				{	
						EHRSection_TreatmentGivenVO episodeRevokeTreatmentVO=new EHRSection_TreatmentGivenVO();
						HelperMethods.populatetToNullOrEmpty(episodeRevokeTreatmentVO, selectedPatientVO);
						//episodeRevokeTreatmentVO.setSnomedCTTreatmentCode(_fb.getSnomedCTTreatmentCode()[k]);
						//episodeRevokeTreatmentVO.setDiagnosisCodeType(OpdConfig.DIAGNOSIS_CODE_TYPE_SNOMED);
						episodeRevokeTreatmentVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
						episodeRevokeTreatmentVO.setAdmissionNo(_fb.getAdmissionNo());
						revokedTreatment.add(episodeRevokeTreatmentVO);
						
						/*EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_rq).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
						patencountervo.setListSaveAllDiagnosis(allSaveListEpisodeDiag);
						WebUTIL.getSession(_rq).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);*/
					}
				
				/*if(_fb.getDiagnosisRecordStatus()[k].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_SAVED))
				{
				
						EHRSection_DiagnosisVO allSaveepisodeDiaVO = new EHRSection_DiagnosisVO();
						allSaveepisodeDiaVO.setDiagnosticCode(_fb.getSnomedCTDiagnosisCode()[k]); 
						allSaveepisodeDiaVO.setDiagnosticTypeCode(_fb.getDiagonisticTypeCode()[k]);
						allSaveepisodeDiaVO.setDiagnosticTypeName(_fb.getDiagnosticTypeName()[k]);
						allSaveepisodeDiaVO.setRemarks(_fb.getRemarks()[k]);
						allSaveepisodeDiaVO.setDiagnosisSite(_fb.getSnomedCTDiagnosisSiteCode()[k]);
						allSaveepisodeDiaVO.setDiagnosisSiteLabel(_fb.getSnomedCTDiagnosisSiteName()[k]);
						allSaveepisodeDiaVO.setDignosisName(_fb.getSnomedCTDiagnosisName()[k]); 
									
						allSaveListEpisodeDiag.add(allSaveepisodeDiaVO);
						
				}*/
			//}
			}
				
			//EHRSection_DiagnosisLNK.saveOpdDiagnosisDetails(addEpisodeDiag,revokedEpisodeDiag, getUserVO(_rq));
			EHRSection_TreatmentGivenLNK.saveTreatmentGivenDetails(addTreatment,revokedTreatment,getUserVO(_rq));
			WebUTIL.getSession(_rq).setAttribute(EHRConfig.EHR_PAT_TREATMENT_GIVEN_DETAILS,addTreatment);
			//allSaveListEpisodeDiag.addAll(addEpisodeDiag); 
			
			//Map essentialMap=(HashMap) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			//essentialMap.put(EHRConfig.EHR_DIAGNOSIS_SAVE, allSaveListEpisodeDiag);
			//session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, essentialMap);
			
			EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_rq).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			//patencountervo.setListSaveAllDiagnosis(allSaveListEpisodeDiag);
			WebUTIL.getSession(_rq).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
		
			
     			
			objStatus.add(Status.DONE, "Record Saved", "");
		

		}
		catch (HisDataAccessException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR, e.getMessage(), "");
			System.out.print(e.getMessage());
		}
		catch (Exception e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			System.out.print(e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return isSave;
		
	}
	
	/*public static void writeResponse(HttpServletResponse resp, String output)
			throws IOException {
		
		try {
			resp.reset();
			resp.flushBuffer();
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "no-cache");
			resp.getWriter().write(output);
		} catch (Exception e) {
						e.printStackTrace();

		}
	}*/
//  Added by Vasu on 01-Feb-2018 for Getting Patient TreatmentGiven Details
	public static boolean getPatientEncTreatmentGivenDetail(EHRSection_TreatmentGivenFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			EHR_PatientEncounterVO _voEHREncounter = new EHR_PatientEncounterVO();
			EHR_AccessPermissionVO _voEHRAccess = new EHR_AccessPermissionVO();
			//EHRSection_DiagnosisVO _voEHRDiagnosis = new EHRSection_DiagnosisVO();
			EHRSection_TreatmentGivenVO _voEHRTreatmentGIven = new EHRSection_TreatmentGivenVO();
			HelperMethods.populate(_voEHREncounter,_fb);
			HelperMethods.populate(_voEHRAccess,_fb);
			//HelperMethods.populate(_voEHRDiagnosis,_fb);
			HelperMethods.populate(_voEHREncounter,userVO);
			
			/*_voEHRDiagnosis.setEpisodeCode(_fb.getEpisodeCode());
			_voEHRDiagnosis.setPatCrNo(_fb.getPatCrNo());*/
			
			_voEHRTreatmentGIven.setEpisodeCode(_fb.getEpisodeCode());
			_voEHRTreatmentGIven.setPatCrNo(_fb.getPatCrNo());
			
			List<EHRSection_TreatmentGivenVO> lstPatEncTreatmentGiven= new ArrayList<EHRSection_TreatmentGivenVO>();
			
			lstPatEncTreatmentGiven = EHRSection_TreatmentGivenLNK.getPatientEncTreatmentGivenDetail( _voEHRAccess,  _voEHREncounter,  _voEHRTreatmentGIven);
			
			WebUTIL.setAttributeInSession(_rq, EHRConfig.EHR_PAT_TREATMENT_GIVEN_DETAILS, lstPatEncTreatmentGiven);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

}




