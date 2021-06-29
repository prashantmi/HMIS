package ehr.vo;

import java.util.ArrayList;
import java.util.List;

import hisglobal.vo.EpisodeVO;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import ehr.ImageExam.EHRSection_ImageExamVO;
import ehr.allergies.vo.EHRSection_AllergiesVO;
import ehr.casesummary.vo.EHRSection_CaseSummaryVO;
import ehr.chronicdisease.vo.EHRSection_ChronicDiseaseVO;
import ehr.complaints.vo.EHRSection_ComplaintsVO;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.examination.vo.EHRSection_ExaminationVO;
import ehr.medicationAdvice.vo.EHRSection_Medication_AdviceVO;
import ehr.ot.vo.EHRSection_OTDetailsVO;
import ehr.patientreferral.vo.EHRSection_PatientReferralVO;
import ehr.questionnaire.vo.EHRSection_QuestionnaireVO;
import ehr.serviceprocedure.vo.EHRSection_ServiceProcedureVO;
import ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO;
import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import ehr.visitreason.vo.EHRSection_VisitReasonVO;
//import ehr.vitals.vo.EHRSection_VitalVO;
import ehr.followup.vo.EHRSection_FollowupVO;
import ehr.history.vo.EHRSection_HistoryVO;
import ehr.hospitalcourse.vo.EHRSection_HospitalCourseVO;
import ehr.investigation.vo.EHRSection_InvestigationAdviceVO;
import emr.vo.EHR_PatEncounterDetailsVO;
import emr.vo.PatientClinicalDocDetailVO;

public class EHRVO
{
	private String patCrNo;

	PatientDetailVO voPatientDtl;

	// Current Encounter Details
	HospitalMstVO voHospital;
	
	List <EHRSection_DiagnosisVO> listDiagnosisVO = new ArrayList<EHRSection_DiagnosisVO>();
	
	List <EHRSection_TreatmentVO> listTreatmentVO = new ArrayList<EHRSection_TreatmentVO>();
	
	List <EHRSection_FollowupVO> listFollowUpVO = new ArrayList<EHRSection_FollowupVO>();
	
	List <EHRSection_InvestigationAdviceVO> listInvestigationResultsVO = new ArrayList<EHRSection_InvestigationAdviceVO>();
	
	List<EpisodeVO> listEpisodeVO = new ArrayList<EpisodeVO>();
	
	List<PatientClinicalDocDetailVO> listClinicalDocEssentials =  new ArrayList<PatientClinicalDocDetailVO>();
	
	List<EHRSection_StatusAtDischargeVO> listStatusAtDischarge = new ArrayList<EHRSection_StatusAtDischargeVO>();
	
	List <EHRSection_TreatmentVO> listDrugAdviceVO = new ArrayList<EHRSection_TreatmentVO>();
	
	List <EHRSection_ChronicDiseaseVO> listChronicDiseaseVO = new ArrayList<EHRSection_ChronicDiseaseVO>();
	
	EHRSection_FollowupVO followUpVO;
	EHRSection_FollowupVO followUpVO_OPD;
		
	EHRSection_VisitReasonVO cheifComplaintsVO;
	
	//EHRSection_StatusAtDischargeVO hospitalCourseVO;
	
	EHRSection_CaseSummaryVO caseSummaryVO;
	
	
	EHRSection_Medication_AdviceVO medicationVO;
	
	public EHRSection_Medication_AdviceVO getMedicationVO() {
		return medicationVO;
	}

	public void setMedicationVO(EHRSection_Medication_AdviceVO medicationVO) {
		this.medicationVO = medicationVO;
	}

	EHRSection_HospitalCourseVO hospitalCourseVO;
	
	EHRSection_StatusAtDischargeVO statusAtDischargeVO;
	
	private String currDateTime;
	
	private String department;
	
	List <EHRSection_OTDetailsVO> listOTDetailVO = new ArrayList<EHRSection_OTDetailsVO>();
	
	List<EHRSection_InvestigationAdviceVO> listOPDINVDtl = new ArrayList<EHRSection_InvestigationAdviceVO>();
	
	List<EHRSection_AllergiesVO> listAllergies = new ArrayList<EHRSection_AllergiesVO>();
	
	List<EHRSection_PatientReferralVO> listPatReferrals = new ArrayList<EHRSection_PatientReferralVO>();

	List<EHRSection_ServiceProcedureVO> listServiceProcedures = new ArrayList<EHRSection_ServiceProcedureVO>();
	
	List<EHRSection_HistoryVO> voPatHistory = new ArrayList<EHRSection_HistoryVO>();
	
	List<EHRSection_ExaminationVO> voEncExamination = new ArrayList<EHRSection_ExaminationVO>();
	
	List<EHRSection_ComplaintsVO> voComplaints = new ArrayList<EHRSection_ComplaintsVO>();
	
	List<EHRSection_QuestionnaireVO> voQuestionnaire = new ArrayList<EHRSection_QuestionnaireVO>();
	/*List<EHRSection_VitalVO> voEncVital = new ArrayList<EHRSection_VitalVO>();*/
	
	List <EHRSection_ImageExamVO> ImageVO = new ArrayList<EHRSection_ImageExamVO>();
	
	public List<EHRSection_ImageExamVO> getImageVO() {
		return ImageVO;
	}

	public void setImageVO(List<EHRSection_ImageExamVO> imageVO) {
		ImageVO = imageVO;
	}

	/*public List<EHRSection_VitalVO> getVoEncVital() {
		return voEncVital;
	}

	public void setVoEncVital(List<EHRSection_VitalVO> voEncVital) {
		this.voEncVital = voEncVital;
	}*/

	public HospitalMstVO getVoHospital() {
		return voHospital;
	}

	public void setVoHospital(HospitalMstVO voHospital) {
		this.voHospital = voHospital;
	}
	
	public PatientDetailVO getVoPatientDtl()
	{
		return voPatientDtl;
	}
	
	public void setVoPatientDtl(PatientDetailVO voPatientDtl)
	{
		this.voPatientDtl = voPatientDtl;
	}
	
    
    public EHRSection_FollowupVO getFollowUpVO()
	{
		return followUpVO;
	}

    public void setFollowUpVO(EHRSection_FollowupVO followUpVO)
    {
        this.followUpVO = followUpVO;
    }

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public List<EHRSection_DiagnosisVO> getListDiagnosisVO() {
		return listDiagnosisVO;
	}

	public void setListDiagnosisVO(List<EHRSection_DiagnosisVO> listDiagnosisVO) {
		this.listDiagnosisVO = listDiagnosisVO;
	}

	public List<EHRSection_TreatmentVO> getListTreatmentVO() {
		return listTreatmentVO;
	}

	public void setListTreatmentVO(List<EHRSection_TreatmentVO> listTreatmentVO) {
		this.listTreatmentVO = listTreatmentVO;
	}

	public List<EHRSection_FollowupVO> getListFollowUpVO() {
		return listFollowUpVO;
	}

	public void setListFollowUpVO(List<EHRSection_FollowupVO> listFollowUpVO) {
		this.listFollowUpVO = listFollowUpVO;
	}

	public List<EHRSection_InvestigationAdviceVO> getListInvestigationResultsVO() {
		return listInvestigationResultsVO;
	}

	public void setListInvestigationResultsVO(
			List<EHRSection_InvestigationAdviceVO> listInvestigationResultsVO) {
		this.listInvestigationResultsVO = listInvestigationResultsVO;
	}

	public List<EpisodeVO> getListEpisodeVO() {
		return listEpisodeVO;
	}

	public void setListEpisodeVO(List<EpisodeVO> listEpisodeVO) {
		this.listEpisodeVO = listEpisodeVO;
	}

	public List<PatientClinicalDocDetailVO> getListClinicalDocEssentials() {
		return listClinicalDocEssentials;
	}

	public void setListClinicalDocEssentials(
			List<PatientClinicalDocDetailVO> listClinicalDocEssentials) {
		this.listClinicalDocEssentials = listClinicalDocEssentials;
	}

	public List<EHRSection_StatusAtDischargeVO> getListStatusAtDischarge() {
		return listStatusAtDischarge;
	}

	public void setListStatusAtDischarge(
			List<EHRSection_StatusAtDischargeVO> listStatusAtDischarge) {
		this.listStatusAtDischarge = listStatusAtDischarge;
	}

	public List<EHRSection_TreatmentVO> getListDrugAdviceVO() {
		return listDrugAdviceVO;
	}

	public void setListDrugAdviceVO(List<EHRSection_TreatmentVO> listDrugAdviceVO) {
		this.listDrugAdviceVO = listDrugAdviceVO;
	}

	public EHRSection_VisitReasonVO getCheifComplaintsVO() {
		return cheifComplaintsVO;
	}

	public void setCheifComplaintsVO(EHRSection_VisitReasonVO cheifComplaintsVO) {
		this.cheifComplaintsVO = cheifComplaintsVO;
	}

	/*public void setDepartment(String department) {
		this.department = department;
	}*/

	public String getCurrDateTime() {
		return currDateTime;
	}

	public void setCurrDateTime(String currDateTime) {
		this.currDateTime = currDateTime;
	}

	public List<EHRSection_OTDetailsVO> getListOTDetailVO() {
		return listOTDetailVO;
	}

	public void setListOTDetailVO(List<EHRSection_OTDetailsVO> listOTDetailVO) {
		this.listOTDetailVO = listOTDetailVO;
	}

	public EHRSection_CaseSummaryVO getCaseSummaryVO() {
		return caseSummaryVO;
	}

	public void setCaseSummaryVO(EHRSection_CaseSummaryVO caseSummaryVO) {
		this.caseSummaryVO = caseSummaryVO;
	}

	public EHRSection_HospitalCourseVO getHospitalCourseVO() {
		return hospitalCourseVO;
	}

	public void setHospitalCourseVO(EHRSection_HospitalCourseVO hospitalCourseVO) {
		this.hospitalCourseVO = hospitalCourseVO;
	}

	public EHRSection_StatusAtDischargeVO getStatusAtDischargeVO() {
		return statusAtDischargeVO;
	}

	public void setStatusAtDischargeVO(
			EHRSection_StatusAtDischargeVO statusAtDischargeVO) {
		this.statusAtDischargeVO = statusAtDischargeVO;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public EHRSection_FollowupVO getFollowUpVO_OPD() {
		return followUpVO_OPD;
	}

	public void setFollowUpVO_OPD(EHRSection_FollowupVO followUpVO_OPD) {
		this.followUpVO_OPD = followUpVO_OPD;
	}

	public List<EHRSection_InvestigationAdviceVO> getListOPDINVDtl() {
		return listOPDINVDtl;
	}

	public void setListOPDINVDtl(List<EHRSection_InvestigationAdviceVO> listOPDINVDtl) {
		this.listOPDINVDtl = listOPDINVDtl;
	}

	public List<EHRSection_ChronicDiseaseVO> getListChronicDiseaseVO() {
		return listChronicDiseaseVO;
	}

	public void setListChronicDiseaseVO(
			List<EHRSection_ChronicDiseaseVO> listChronicDiseaseVO) {
		this.listChronicDiseaseVO = listChronicDiseaseVO;
	}

	public List<EHRSection_AllergiesVO> getListAllergies() {
		return listAllergies;
	}

	public void setListAllergies(List<EHRSection_AllergiesVO> listAllergies) {
		this.listAllergies = listAllergies;
	}

	public List<EHRSection_PatientReferralVO> getListPatReferrals() {
		return listPatReferrals;
	}

	public void setListPatReferrals(
			List<EHRSection_PatientReferralVO> listPatReferrals) {
		this.listPatReferrals = listPatReferrals;
	}

	public List<EHRSection_ServiceProcedureVO> getListServiceProcedures() {
		return listServiceProcedures;
	}

	public void setListServiceProcedures(
			List<EHRSection_ServiceProcedureVO> listServiceProcedures) {
		this.listServiceProcedures = listServiceProcedures;
	}

	public List<EHRSection_HistoryVO> getVoPatHistory() {
		return voPatHistory;
	}

	public void setVoPatHistory(List<EHRSection_HistoryVO> voPatHistory) {
		this.voPatHistory = voPatHistory;
	}

	public List<EHRSection_ExaminationVO> getVoEncExamination() {
		return voEncExamination;
	}

	public void setVoEncExamination(List<EHRSection_ExaminationVO> voEncExamination) {
		this.voEncExamination = voEncExamination;
	}

	public List<EHRSection_ComplaintsVO> getVoComplaints() {
		return voComplaints;
	}

	public void setVoComplaints(List<EHRSection_ComplaintsVO> voComplaints) {
		this.voComplaints = voComplaints;
	}

	public List<EHRSection_QuestionnaireVO> getVoQuestionnaire() {
		return voQuestionnaire;
	}

	public void setVoQuestionnaire(List<EHRSection_QuestionnaireVO> voQuestionnaire) {
		this.voQuestionnaire = voQuestionnaire;
	}

	

	
}
