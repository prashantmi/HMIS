/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.diagnosis.presentation;

import java.util.List;
import java.util.Map;

import ehr.diagnosis.business.EHRSection_DiagnosisBO;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class EHRSection_DiagnosisLNK extends  ControllerDATA

{	
	
	public static void saveOpdDiagnosisDetails(
			List<EHRSection_DiagnosisVO> addepisodeDiaVO, List<EHRSection_DiagnosisVO> episodeRevokeDiaVO, UserVO userVO) 
	{
		EHRSection_DiagnosisBO serviceBO = new EHRSection_DiagnosisBO();
		serviceBO.saveOpdDiagnosisDetails(addepisodeDiaVO, episodeRevokeDiaVO,userVO);

	}
	
	public static Map getSnomdDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		EHRSection_DiagnosisBO serviceBO = new EHRSection_DiagnosisBO();
		return (serviceBO.getSnomdDiagnosisEssential(_patDtlVO, _userVO));
	}
	/*Added by VASU on 26.Sept.2017 for patient Diagnosis Details Composition*/
	public static List<EHRSection_DiagnosisVO> getPatientEncDiagnosisDetail(EHR_AccessPermissionVO _voEHRAccess, EHR_PatientEncounterVO _voEHREncounter, EHRSection_DiagnosisVO _voEHRDiagnosis)
	{
		EHRSection_DiagnosisBO serviceBO = new EHRSection_DiagnosisBO();
		return (serviceBO.getPatientEncDiagnosisDetail(_voEHRAccess, _voEHREncounter,  _voEHRDiagnosis));
		
	}
}
