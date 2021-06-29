/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.treatmentgiven.presentation;

import java.util.List;
import java.util.Map;

import ehr.diagnosis.business.EHRSection_DiagnosisBO;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO;
import ehr.treatmentgiven.business.EHRSection_TreatmentGivenBO;
public class EHRSection_TreatmentGivenLNK extends  ControllerDATA

{	
	
	public static void saveTreatmentGivenDetails(
			List<EHRSection_TreatmentGivenVO> addTreatmentVO,List<EHRSection_TreatmentGivenVO> revokedTreatment, UserVO userVO) 
	{
		EHRSection_TreatmentGivenBO serviceBO = new EHRSection_TreatmentGivenBO();
		serviceBO.saveTreatmentGivenDetails(addTreatmentVO,revokedTreatment,userVO);

	}
	
	/*Added by VASU on 01.Feb.2018 for TreatmentGiven Details Composition*/
	public static List<EHRSection_TreatmentGivenVO> getPatientEncTreatmentGivenDetail(EHR_AccessPermissionVO _voEHRAccess, EHR_PatientEncounterVO _voEHREncounter, EHRSection_TreatmentGivenVO _voEHRTreatmentGiven)
	{
		EHRSection_TreatmentGivenBO serviceBO = new EHRSection_TreatmentGivenBO();
		return (serviceBO.getPatientEncTreatmentGivenDetail(_voEHRAccess, _voEHREncounter,  _voEHRTreatmentGiven));
		
	}
}
