/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr;

public  interface EHRConfig {
	
	// ****** Configuration Settings
		
		// EHR VO
		public static final String EHR_OBJECT_VO = "ehrVO";

		// Clinical Section Common Object Fields
		public static final String EHR_OBJECT_FIELD_NAME_PATIENT_CRNO = "patCRNo";
		public static final String EHR_OBJECT_FIELD_NAME_PRESENT = "present";
		public static final String EHR_OBJECT_FIELD_NAME_SECTION_DATA = "section_data";
		public static final String EHR_OBJECT_FIELD_NAME_LIST_DATA = "list_data";
		public static final String EHR_JSON_OBJECT_FIELD_NAME_PATIENT_DATA = "patient_data";
		public static final String EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_KEY = "clinicalArea";
		public static final String EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_CODE = "clinicalArea";
		public static final String EHR_OBJECT_FIELD_NAME_EHR_ACTION_KEY = "ehrAction";
		public static final String EHR_SECTION_KEY_INTAKE = "INTAKE";
		public static final String EHR_SECTION_KEY_OUTTAKE = "OUTTAKE";

		// Clinical Data Areas or Document Types
		public static final String EHR_CLINICAL_AREA_OPD_PRESCRIPTION_KEY = "OPD_PRESCRIPTION";
		public static final String EHR_CLINICAL_AREA_OPD_PRESCRIPTION_CODE = "51";
		public static final String EHR_CLINICAL_AREA_DISCHARGE_SUMMARY_KEY = "DISCHARGE_SUMMARY";
		public static final String EHR_CLINICAL_AREA_DISCHARGE_SUMMARY_CODE = "17";
		
		// Clinical Actions
		public static final String EHR_ACTIONS_EDIT_KEY = "E";
		//public static final String EHR_ACTIONS_ENTRY_KEY = "E";
		public static final String EHR_ACTIONS_UPDATE_KEY = "U";
		public static final String EHR_ACTIONS_PREPARE_KEY = "P";
		public static final String EHR_ACTIONS_SELECT_KEY = "S";
		public static final String EHR_ACTIONS_VIEW_KEY = "V";
		//public static final String EHR_ACTIONS_PRINT_KEY = "P";

	

		
		// EHR Sections Key 
		public static final String EHR_SECTION_KEY_TREATMENT = "TREATMENT";
		public static final String EHR_SECTION_KEY_DIAGNOSIS = "DIAGNOSIS";
		public static final String EHR_SECTION_KEY_ALLERGY = "ALLERGIES";
		public static final String EHR_SECTION_KEY_INVESTIGATION = "INVESTIGATION";
		public static final String EHR_SECTION_KEY_CHRONIC_DISEASE = "CHRONICDISEASE";
		public static final String EHR_SECTION_KEY_INTAKE_OUTTAKE = "INTAKEOUTTAKE";
		public static final String EHR_SECTION_KEY_PROGRESS_NOTES = "PROGRESSNOTES";
		public static final String EHR_SECTION_KEY_VITALS = "VITALS";
		public static final String EHR_SECTION_KEY_EXTINESTIGATION = "EXTINESTIGATION";
		public static final String EHR_SECTION_KEY_COMPLAINTS = "COMPLAINTS";
		public static final String EHR_SECTION_KEY_HISTORY = "HISTORY";
		public static final String EHR_SECTION_KEY_EXAMINATION = "EXAMINATION";
		public static final String EHR_SECTION_KEY_PROFILE = "PROFILE";
		public static final String EHR_SECTION_KEY_OPERATION = "OPERATION";
		public static final String EHR_SECTION_KEY_ANCDETAIL = "ANCDETAIL";
		public static final String EHR_SECTION_KEY_VISITREASON ="VISITREASON";
		public static final String EHR_SECTION_KEY_SERVICEAREA = "SERVICEAREA";
		public static final String EHR_SECTION_KEY_ENCOUNTER = "ENCOUNTER";
		public static final String EHR_SECTION_KEY_DOCUMENT_ARCHIVAL = "DOCUMENTARCHIVAL";

	// EHR SECTION RECORD Status
	String EHR_SECTION_RECORD_STATUS_SAVED ="1";
	String EHR_SECTION_RECORD_STATUS_NOT_SAVED ="2";
	String EHR_SECTION_RECORD_STATUS_TOREVOKE ="4";
	
	String HOSPITAL_DEATILS="hospitaldetails";
	
	String EHR_PAT_ENCOUNTER_DETAILS_VO="ehrPatEncounterDetails";
	
	//Functions & Procedures 
		//Diagnosis
	 String GET_EHR_PATIENT_DIAGNOSIS= "{call pkg_ehr_view.proc_hrgt_episode_diagnosis_dtl(?,?,?,?,?,?)}";

	
	
	
	//********DIAGNOSIS**********//
	//DIAGNOSIS VIEW
	 String GET_PREV_EPISODE_DIAGNOSIS_DTL= "{call pkg_ehr_view.proc_hrgt_episode_diagnosis_dtl(?,?,?,?,?,?)}";
	 String GET_DIAGNOSIS_TYPE= "{call pkg_ehr_view.proc_hgbt_diagnosis_type_mst(?,?,?,?,?,?,?)}";
	 //DIAGNOSIS DML
	 String SAVE_DIAGNOSIS_DETAILS= "{call pkg_ehr_dml.proc_hrgt_episode_diagnosis_dml(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying)}";
	 String REVOKE_DIAGNOSIS= "{call pkg_ehr_dml.proc_revoke_diagnosis_dtl(?,?,?,?,?,?,?,?,? )}";
	 String EHR_DIAGNOSIS_SAVE="ehrDiagnosisSaveData";
	 
	 String EHR_DIAGNOSIS_ESSENTIAL_SAVE="ehrDiagnosisEssentialSaveData";
	
	//************TREATMENT*************//
	    String PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST="patientTreatmentDtlPrevDrugDetailList";
		String ESSENTIALS_LIST_DOSAGE_FREQUECY="essentialsListDosageFrequency";
		String ESSENTIALS__DOSAGE_FREQUECY_ARRAY="essentialsArrayOfDosageFrequencyVO";
		String ESSENTIALS_LIST_ALL_DRUGS="essentialsListAllDrugs";
		String ESSENTIALS_LIST_ALL_DRUGS_FOR_SEARCH="essentialListAllDrugForSearch";
		String ESSENTIALS_LIST_ALL_DRUG_DOSES="essentialsListAllDrugDoses";
		String ESSENTIALS_LIST_ALL_DRUG_ROUTE="essentialsListAllDrugRoutes";
		String ESSENTIALS_LIST_ALL_DRUG_ADMIN_FLAGS="essentialsListAllDrugAdmin";
		String ESSENTIALS_LIST_ALL_PREGNANT_CATEGORY="essentialsListAllPregCat";
		String EHR_TREATMENT_SAVE="ehrTreatmentSaveData";
	    String EHR_TREATMENT_ESSENTIAL_SAVE="ehrTreatmentEssentialSaveData";
	    String SAVE_TREATMENT_DETAILS= "{call pkg_ehr_dml.proc_hrgt_episode_drug_dml(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying)}";
		 
			//************VISITREASON*************//
		 String VISIT_REASON_ESSENTAILS = "visitreasonessentails";
			String OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST = "opdEssentailAllVisitSummaryOfCurrentEpisodeVisitList";
			String GET_VISIT_REASON = "{call pkg_ehr_view.proc_pat_visit_reasons_dtl_upp(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?)}";
			String SAVE_VISIT_REASON_DETAILS= "{call pkg_ehr_dml.proc_hrgt_episode_dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";
			
		

			//************FOLLOWUP*************//
			String FOLLOWUP_ESSENTAILS = "followupessentails";
			String OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST = "opdEssentailAllVisitOfEpisodeList";
			//String OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST = "opdEssentailAllVisitSummaryOfCurrentEpisodeVisitList";
			String OPD_ESSENTIAL_ALL_KEYWORDS_LIST = "opdEssentailAllKeywordsList";
			String OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE = "4";
			String OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS = "0";
			String OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS = "1";
			// Choice
			String YES = "1";
			String NO = "0";

			String GET_FOLLOW_UP= "{call pkg_ehr_view.proc_pat_follow_up_details(?,?,?,?,?,?,?,?)}";
			String SAVE_FOLLOW_UP_DETAILS= "{call pkg_ehr_dml.proc_save_pat_follow_up_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			
			//Functions & Procedures 
			//Patient Detail Tile Composition  
		 String GET_EHR_SECTION_PATIENT_DETAIL= "{call pkg_ehr_view.proc_hipt_patadmission_dtl(?,?,?,?,?,?,?,?,?)}";

		
			
			
			/* Added By Nilesh Gupta*/
			//********** STATUS AT DISCHARGE**************//
			String STATUS_AT_DISCHARGE = "dischargeessentails";
			String GET_STATUS_AT_DIRCHARGE="{call pkg_ehr_view.proc_pat_status_at_discharge_dtl(?,?,?,?,?)}"; //Total 5 parameters
			String SAVE_STATUS_AT_DISCHARGE="{call pkg_ehr_dml.ehrt_pat_condition_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)}"; //Total 15 parameters
                 
			//************PATIENT CLINICAL DOCUMENTS*************//
			String GET_CLINICAL_DOCUMENTS_TYPE= "{call pkg_ehr_view.proc_pat_clinical_doc_type_details(?,?,?,?,?)}";
			String PROCEDURE_GET_DOCUMENT_ID = "pkg_ehr_dml.generatedocumentid";
			String CLINICAL_DOCUMENT_TYPE_DETAILS="clinicalDocTypeDtl";
			String CLINICAL_SECTION_COMP_LIST="profileTypeList";
			String CLINICAL_SECTION_COMP_SELECT_JSON="clinicalSectionSelectJson";
			String CLINICAL_DOCUMENT_STATUS_GENERATED="1";
			String CLINICAL_DOCUMENT_STATUS_INPROCESS="2";
			String CLINICAL_DOCUMENT_STATUS_CANCELLED_NOTINUSE="3";
			String CLINICAL_SECTION_COMP_LIST_HTML="lstClinicalSecCompHTML";
			
			
			/********************PATIENT CLINICAL DOCUMENT(NEW)************************/
			////////////////////////patient document///////////////////////////////////////////////
			String OPD_PATIENT_DOCUMENT_EPISODEVO_ARRAY = "opdPatientDocumentEpisodeVoArray";
			String OPD_PATIENT_DOCUMENT_HTML = "opdPatientDocumentHtml";
			String OPD_DIAGNOSIS_HTML = "opdDiagnosisHtml";
			String OPD_PATIENT_DOCUMENT_TEMP_HTML = "opdPatientDocumentTempHtml";
			String OPD_FILE_HEADER = "opdFileHeader";
			String OPD_PATIENT_PREVIOUS_DOCUMENT_ARRAY = "opdPatientPreviousDocumentArray";
			String PATIENT_DOCUMENT_USER_DESK_MENU_TEMPLATE_MASTER_VO="patientDocumentUserDeskMenuTemplateMasterVO";
			
			String PATIENT_DOCUMENT_EPISODE_DOCUMENTS_LIST = "patientDocumentEpisodeDocumentsList";
			String PATIENT_DOCUMENT_PROFORMA_OBJECT = "patientDocumentProformaObject";
			String PATIENT_DOCUMENT_BASED_DESK_MENUS_LIST = "patientDocumentBasedDeskMenusList";
			
			String PATIENT_DOCUMENT_ACCESS_PRIVILEDGES_LIST = "patientDocumentAccessPriviledgesList";
			String OPD_PATIENT_DOCUMENT_ESSENTIAL_UNIT_LIST = "opdPatientDocumentEssentailUnitList";
			String OPD_PATIENT_DOCUMENT_ESSENTIAL_ALL_UNIT_LIST = "opdPatientDocumentEssentailAllUnitList";
			String OPD_PATIENT_DOCUMENT_ADDED_UNIT_LIST = "opdPatientDocumentAddedUnitList";
			String OPD_PATIENT_DOCUMENT_ESSENTIAL_USER_LIST = "opdPatientDocumentEssentailUserList";
			String OPD_PATIENT_DOCUMENT_ADDED_USER_LIST = "opdPatientDocumentAddedUserList";
			String OPD_PATIENT_DOCUMENT_SEARCHED_USER_LIST = "opdPatientDocumentSearchedUserList";
			String OPD_PATIENT_DOCUMENT_ALL_USERS_MAP = "opdPatientDocumentAllUsersMap";
			String OPD_PATIENT_DOCUMENT_ALL_UNITS_MAP = "opdPatientDocumentAllUnitsMap";
			String DISCLAIMER_REQUIRED_YES="1";
			String DISCLAIMER_REQUIRED_NO="0";
			
			String OPD_DESK_DOCUMENT_BOUND="opdDeskDocumentBound";
			String DOCUMENT_BOUND_OPD="1";
			String DOCUMENT_BOUND_IPD="2";
			
			//not in use document type combo is created using document type master 
			String[] DOCUMENT_TYPE_OPD=new String[]{"","Refer/Transfer Document","","Case Sheet","General"};
			String[] DOCUMENT_TYPE_IPD=new String[]{"","Refer/Transfer Document","Discharge Summary/Final Discharge","Case Sheet","General"};
			////////////////////////////////////////////
			
			String DOCUMENT_TYPE_REFER="11";
			String DOCUMENT_TYPE_DISCHARGE="13";
			String DOCUMENT_TYPE_CASESHEET="3";
			String DOCUMENT_TYPE_GENERAL="12";
			
			String DOCUMENT_TYPE_OPD_VISIT_DOCUMENT = "14";
			
			String DOCUMENT_TYPE_REFER_DESC="Refer/Transfer";
			String DOCUMENT_TYPE_DISCHARGE_DESC="Discharge";
			String DOCUMENT_TYPE_CASESHEET_DESC="Case Sheet";
			String DOCUMENT_TYPE_GENERAL_DESC="General";
			String DOCUMENT_HEADER_DISCHARGE_TYPE_DESC="Discharge Report";
			String OPD_PATIENT_DOCUMENT_OPERATION_DETAIL_VO_ARRAY = "opdPatientDocumentOperationDetailVoArray";
			String DOCUMENT_TYPE_VO_LIST="documentTypeVOList";
			
			//////Document Type Generation Mode
			String DOCUMENT_TYPE_GENERATION_MODE_CUSTOMIZED="1";
			String DOCUMENT_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT="2";
			String DOCUMENT_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE="3";
			
			String DOCUMENT_TYPE_GENERATION_MODE_CUSTOMIZED_LABEL="Customized";
			String DOCUMENT_TYPE_GENERATION_MODE_AUTOMATICE_LABEL="Automatic";
			
			/////Document Type Usablity
			String DOCUMENT_TYPE_USABLITY_OPD="1";
			String DOCUMENT_TYPE_USABLITY_IPD="2";
			String DOCUMENT_TYPE_USABLITY_OPD_AND_IPD="3";
			String DOCUMENT_TYPE_USABLITY_OPD_LABEL="OPD";
			String DOCUMENT_TYPE_USABLITY_IPD_LABEL="IPD";
			String DOCUMENT_TYPE_USABLITY_OPD_AND_IPD_LABEL="OPD and IPD";
			
			
			
			// Document Type
			String PATIENT_DOCUMENT_TYPE_FORWORD = "1";
			String PATIENT_DOCUMENT_TYPE_DISCHARGE_SUMMARY = "2";
			String DOCUMENT_TYPE_LIST="documentTypeList";
			String DOCUMENT_GENERATION_DEFAULT="0";
			String DOCUMENT_GENERATION_CUSTOMIZED="1";
			// Access Type
			String PATIENT_DOCUMENT_ACCESS_TYPE_ALL = "1";
			String PATIENT_DOCUMENT_ACCESS_TYPE_UNIT_SPECIFIC_OR_USER_SPCIFIC = "2";
			
			//String PATIENT_DOCUMENT_ACCESS_TYPE_UNIT_SPECIFIC="1";
			String PATIENT_DOCUMENT_ACCESS_TYPE_RESTRICTED_USERS = "2";
			String PATIENT_DOCUMENT_ACCESS_POLICY_VO_ARRAY="patientDocumentAccessPolicyVOArray";
			String PATIENT_DOCUMENT_ACCESS_POLICY_DETAIL_VO="patientDocumentAccessPolicyDetailVO";
			String PATIENT_DOCUMENT_ACCESS_TYPE_TO_ALL="0";
			String PATIENT_DOCUMENT_ACCESS_TYPE_OTHER="1";
			String PATIENT_DOCUMENT_ACCESS_TYPE_UNIT_SPECIFIC="2";
			String PATIENT_DOCUMENT_ACCESS_TYPE_USER_BOUND="3";
			//String PATIENT_DOCUMENT_ACCESS_TYPE_OWNING_UNIT = "4";
			
			// User Search Modes
			String PATIENT_DOCUMENT_USER_SEARCH_BY_USER_NAME = "1";
			String PATIENT_DOCUMENT_USER_SEARCH_BY_EMPLOYEE_ID = "2";
			String PATIENT_DOCUMENT_USER_SEARCH_BY_EMPLOYEE_NAME = "3";
			
			// Document Inbox
			String PATIENT_DOCUMENTS_FOR_INBOX_LIST = "patientDocumentForInboxList";
			String PATIENT_DOCUMENTS_FOR_ALL_LIST = "patientDocumentForAllList";
			
			
	
			//************END PATIENT CLINICAL DOCUMENTS*************//
			
			//******************INVESTIGATION RESULTS***************************//
			 String GET_EHR_PATIENT_INVESTIGATION= "{call pkg_ehr_view.proc_hrgt_episode_investigation_dtl(?,?,?,?,?,?,?)}";
			
			//*****************END INVESTIGATION******************//
			// ****** Configuration Session Keys
			String EHR_COMPOSITION_SESSION_KEY_LIST_PATIENT_ENCOUNTER_DIAGNOSIS = "EHRCompositionSessionKeyListPatientEncounterDiagnosis";
			String EHR_COMPOSITION_SESSION_KEY_LIST_PATIENT_ENCOUNTER_INVESTIGATION = "EHRCompositionSessionKeyListPatientEncounterInvestigation";
			
			
			//******************TEMPLATE COMPOSITION***************************//
			String PROC_FOR_TEMPLATE_LIST = "PKG_EHR_VIEW.GET_GENERIC_TEMPLATES_LIST";
			String PROC_FOR_PATIENT_CLINICAL_DTL_HPMRT_HRGT = "PKG_EHR_VIEW.GETPATIENTFINALCLINICALDTL_HPMRT_HRGT";
			 //*****************Treatment Given*********************************//
			String GET_EHR_PATIENT_TREATMENTGIVEN = "{call pkg_ehr_view.proc_hrgt_episode_treat_admin_dtl(?,?,?,?,?,?)}";
			String SAVE_TREATMENT_GIVEN = "{call pkg_ehr_dml.proc_hrgt_episode_treat_admin_dtl_dml(?,?,?,?,?, ?,?,?,?,?, ?,?,?)}"; //Total 13 parameters
			String MEDICATION_TYPE_DRUG = "1";
			String MEDICATION_TYPE_OPERATION_PROCEDURE="2";
			String MEDICATION_TYPE_SERVICE_PROCEDURE="3";
			String MEDICATION_TYPE_BLOOD_TRANSFUSION="4";
			String MEDICATION_TYPE_INVESTIGATION="5";
			String EHR_PAT_TREATMENT_GIVEN_DETAILS = "ehrPatTreatmentGivenDetails";
			String REVOKE_TREATMENT_GIVEN= "{call pkg_ehr_dml.proc_revoke_treatmentgiven_dtl(?,?,?,?,?,?,?,?,? )}";
			
			
			//Added by Dheeraj
			String PREVIOUS_PRESCRIPTIONS = "previousPrescriptions";
			String PRESCRIPTION_DETAILS = "prescriptionImages";
			
			//Added by Vasu on 03.Nov.2018
			String EHR_SINGLE_PAGE_DISCHARGE_PAT_PROFILE_DTL_VO="ehrSinglePageDischargePatProfileDtlVO";
			
			String SAVE_DISC_FOLLOW_UP_DETAILS= "{call pkg_ehr_dml.proc_save_pat_discharge_follow_up_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?)}";
            
			String PATIENT_DETAILS = "patientDetails";
			
			String CLINICAL_SECTION_COMP_SECTIONS_PRINT_LIST="clinicalSectionsPrintList";
			
			String OPERATIONS_LIST = "operationsList";
			String SURGEON_LIST = "surgeonList";
			String PREV_OT_DETAIL_LIST = "prevOTDetailList";
			
			String SAVE_CASE_SUMMARY="{call pkg_ehr_dml.ehrt_pat_enc_summary_dtl(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?::character varying,?::character varying,?::character varying)}"; 
			String GET_CASE_SUMMARY="{call pkg_ehr_view.proc_ehrt_pat_enc_summary_dtl(?,?,?,?,?,?,?,?)}"; //Total 8 parameters
            
			String SAVE_HOSPITAL_COURSE="{call pkg_ehr_dml.ehrt_pat_course_dtl(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?::character varying,?::character varying,?::character varying)}"; 
			
			String GET_HOSPITAL_COURSE="{call pkg_ehr_view.proc_ehrt_pat_course_dtl(?,?,?,?,?,?,?,?)}"; //Total 8 parameters
			
			String GET_CONDITION_AT_DISCHARGE="{call pkg_ehr_view.proc_ehrt_pat_condition_dtl(?,?,?,?,?,?,?,?)}"; //Total 8 parameters
			String  FOLLOWUP_ESSENTAILS_NEW = "followupessentailsnew"; 
			
			//Added by Vasu on 14.May.2019
			String PROC_FOR__TEMPLATE_CLINICAL_DATA = "PKG_EHR_VIEW.GET_TEMPLATE_CLINICAL_DATA";
			String PROC_FOR_PAT_REFERRAL_DATA = "PKG_EHR_VIEW.GET_PAT_REFERRAL_DATA";
			String PROC_FOR_SERVICE_PROCEDURES = "PKG_EHR_VIEW.GET_PAT_SERVICE_PROCEDURES";

			//Added by Prachi for composition section mapping
			//****DML********//
			String SAVE_COMPOSITION_SECTION_MAPPING = "{call pkg_opd_dml.proc_ehrt_comp_sec_temp_map_mst_dml(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying)}";
			String COMP_ENTRY_KEY ="1";
			String COMP_PRINT_KEY = "2";
			
			//Added by Vasu on 05.Aug.2019
			String PARAMETERS_FOR_EXTERNAL_INVESTIGATION = "parametersForExtInv";
			String TESTS_FOR_EXTERNAL_INVESTIGATION = "testsForExtInv";
			String ADDED_EXTERNAL_INVESTIGATIONS = "addedExternalInvestigations";
			
		    String SAVE_EXT_INV_DETAILS= "{call pkg_ehr_dml.proc_hrgt_episode_extexam_dtl_dml(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying)}";

		    String ARR_ADDED_EXTERNAL_INVESTIGATION_DTL = "arrAddedExternalInvestigationDtl";
		    
			//Added By Shweta for getting Service Packages 
			String PKG_FOR_SERVICE_PACKAGES = "PKG_EHR_VIEW.get_pat_service_package";
		//	String ARR_ADDED_EXTERNAL_INVESTIGATION_DTL = "arrAddedExternalInvestigationDtl";
		    
		   //Added by Prachi Tiwari on 09-10-2019
		    String SAVE_MEDICATION="{call pkg_ehr_dml.ehrt_pat_medication_dtl(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?::character varying,?::character varying,?::character varying,?::character varying,?::character varying, ?::character varying,?::character varying,?::character varying)}"; 
			String GET_MEDICATION="{call pkg_ehr_view.proc_ehrt_pat_medication_dtl(?,?,?,?,?,?,?,?)}"; //Total 8 parameters
            String REFERRED_DEPARTMENT_LIST = "referredDepartmentList";
            String EPISODE_HISTORY_LIST_FOR_DASHBOARD = "episodeHistoryListForDashboard";
		    
}