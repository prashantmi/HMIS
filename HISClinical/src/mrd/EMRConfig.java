package mrd;

public interface EMRConfig 
{
	//*************** EMR Configuration Settings *****************************

	//*************** EMR Keys *****************************
	String EMR_DESKMENU_PATIENT_DTL_CONSULTATIONINBOX = "emrDeskMenuPatientDetailConsultationInbox";  
	
	//************    Database Procedure & Function Names   ******************************************
	String GET_PAT_EPISODE_DIAGNOSIS_DTL= "{call pkg_emr_view.proc_hrgt_episode_diagnosis_dtl(?,?,?,?,?,?,?)}";
	String GET_PAT_EPISODE_DRUG_DETAIL= "{call pkg_emr_view.proc_hrgt_episode_drug_dtl(?,?,?,?,?,?,?)}";
	String GET_PAT_ALLERGIES_DETAILS= "{call pkg_emr_view.proc_hpmrt_pat_allergy_dtl(?,?,?,?,?,?)}";
	String GET_PAT_ALERTS_DETAILS= "{call pkg_emr_view.proc_hpmrt_pat_alerts_dtl(?,?,?,?,?)}";
	String GET_CONSULTATION_DETAILS= "{call pkg_emr_view.proc_hopt_consultation_dtl(?,?,?,?,?)}";
	String GET_CONSENT_DETAILS= "{call pkg_emr_view.proc_hgbt_consent_dtl(?,?,?,?,?,?,?,?)}";
	
	

	
	
	
	
}

