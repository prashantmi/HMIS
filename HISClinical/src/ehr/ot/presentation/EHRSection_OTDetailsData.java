/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.ot.presentation;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import ehr.diagnosis.business.EHRSection_DiagnosisBO;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import ehr.ot.business.EHRSection_OTDetailsBO;
import ehr.ot.vo.EHRSection_OTDetailsVO;
import ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO;
import ehr.treatmentgiven.business.EHRSection_TreatmentGivenBO;
public class EHRSection_OTDetailsData extends  ControllerDATA

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

	public static Map getENCOTEssential(EHRSection_OTDetailsVO _voEHROTDetails,UserVO userVO) {
		EHRSection_OTDetailsBO EssentialBO= new EHRSection_OTDetailsBO();
		return EssentialBO.getENCOTEssential(_voEHROTDetails, userVO);
	}
	
	public static void saveOperationDetails(EHRSection_OTDetailsVO _voEHROTDetails, UserVO _userVO) {
		EHRSection_OTDetailsBO EssentialBO= new EHRSection_OTDetailsBO();
		EssentialBO.saveOperationDetails(_voEHROTDetails, _userVO);

	}

	//Added by Vasu on 30.July.2019
	public static void deletePreviousOperationDetails(EHRSection_OTDetailsVO eHROTDetailsVO, UserVO userVO)
	{
		EHRSection_OTDetailsBO EssentialBO= new EHRSection_OTDetailsBO();
		EssentialBO.deletePreviousOperationDetails(eHROTDetailsVO, userVO);
	}
}
