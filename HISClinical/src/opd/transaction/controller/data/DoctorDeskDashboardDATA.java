package opd.transaction.controller.data;

import java.util.Map;

import opd.bo.OpdEssentialBO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import investigationDesk.vo.viewInvestigationVO;
public class DoctorDeskDashboardDATA extends ControllerDATA 
{

	public Map<String, Object> getDailyPatientDetail(PatientDetailVO patientDetailVO, UserVO userVO) 
	{
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getDailyPatientDetail(patientDetailVO, userVO);
	}

	public Map<String, Object> getEpisodeDiagnosisDetail(EpisodeDiagnosisVO episodeDiagnosisVO, UserVO userVO) 
	{
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getEpisodeDiagnosisDetail(episodeDiagnosisVO, userVO);
	}

	public Map<String, Object> getEpisodeDrugDetail(PatDrugTreatmentDetailVO patDrugTreatmentDetailVO, UserVO userVO) 
	{
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getEpisodeDrugDetail(patDrugTreatmentDetailVO, userVO);
	}

	public Map<String, Object> getPatMedicalHistory(PatientAlertsDetailVO patientAlertsDetailVO,PatAllergyDtlVO patAllergyDtlVO, UserVO userVO) 
	{
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getPatMedicalHistory(patientAlertsDetailVO, patAllergyDtlVO, userVO);
	}
	
	
	public Map<String, Object> getInvestigationDetail(viewInvestigationVO patviewInvestigationVO,UserVO userVO) 
	{
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getPatInvestigationDetail(patviewInvestigationVO.getPatCrNo().toString(),userVO);
	}
	
	
	

	public static Map<String, Object> getDeskPatDtl(PatientDetailVO patientDetailVO,UserVO userVO, String deskType) 
	{
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getDeskPatDtl(patientDetailVO, userVO, deskType);
	}

}
