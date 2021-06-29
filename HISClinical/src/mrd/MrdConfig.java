package mrd;

public interface MrdConfig 
{
	//*************** MRD Configuration Settings *****************************

	//Hospital Combo List
	String HOSPITAL_COMBO_LIST = "hospitalComboList";  
	
	// Maximum Search Limit
	String MRD_ONLINE_MERGE_CHECK_MAX_SEARCH_LIMIT_YES = "1";  
	String MRD_ONLINE_MERGE_CHECK_MAX_SEARCH_LIMIT_NO = "0";
	String MRD_ONLINE_MERGE_CHECK_MAX_SEARCH_LIMIT = MRD_ONLINE_MERGE_CHECK_MAX_SEARCH_LIMIT_YES; // To prevent the application to halt
	int MRD_ONLINE_MERGE_MAX_SEARCH_COUNT = 5000; // Five Thousand
	
	// EMR Treatment Offline Issue To Patient Detail Show
	String MRD_EMR_TREATMENT_OFFLINE_DETAIL_SHOW_YES = "1";  
	String MRD_EMR_TREATMENT_OFFLINE_DETAIL_SHOW_NO = "0";
	String MRD_EMR_TREATMENT_OFFLINE_DETAIL_SHOW = MRD_EMR_TREATMENT_OFFLINE_DETAIL_SHOW_YES;

	// Process Type Designation Mapping
	
		///////////Process IDs/////////////////
	String PROCESS_TYPE_MEDICAL_CERTIFICATE="5";
	String PROCESS_TYPE_CONSULTANT="2";
	String PROCESS_TYPE_POSTMORTEM_REQUEST_APPROVED="11";
	String DECEASED_ACCEPTANCE_PROCESS_ID="6";
	String DECEASED_ACCEPTANCE_BROUGHT_BY_PEON="6";
	String POSTMORTEM_REQUEST_APPROVED="11";
	String MEDICAL_CERTIFICATE_REQUEST="5";  // for estimate certificate issue
	String MRD_RECORD_REPORTED_BY_PROCESS_ID="13";
	String MRD_RECORD_REQUESTED_BY_PROCESS_ID="14";
	String PROCESS_ID_MED_N_FIT_CERTIFICATE="1";
	String PROCESS_ID_CASESHEET="2";
    String CAMP_RESOURCE_ALLOCATION_EMPLOYEE_NAME_PROCESSID="4";
	String PROCESS_ID_FOR_DUPLICATE_RECORD_PRINTING="1";
	String PROCESS_ID_FOR_CERTIFICATE_B_ENTRY_FORM_REQUEST="1";

	String PROCESS_ID_SINGLE_PAGE_OPERATING_SURGEONS="15";
		// Procedure for fetching List
	String PROCEDURE_GET_PEON_LIST="ahis_util.proc_emplist_processwise";	
	//Added by Dheeraj on 25-Sept-2018 to get employee list FOR MRD Reported and Requested by
	String PROCEDURE_GET_EMP_LIST="ahis_util.proc_emplist_processwise";  
	String PROCEDURE_GET_METHOD_EMPLOYEE_LIST="AHIS_UTIL.proc_emplist_processwise";

	
	//************    Query File Name   ******************************************
	String QUERY_FILE_FOR_MRD_DAO = "mrd.mrdQuery";
	String QUERY_FILE_FOR_MRD_ESSENTIALDAO = "mrd.mrdEssentialQuery";
	String QUERY_FILE_FOR_MRD_MASTER_DAO = "mrd.masters.mrdMstQuery";


	//************    Database Procedure & Function Names   ******************************************
	String PROC_RPT_SERVICE_AREA_COUNTS = "{call pkg_mrd_rpt.proc_service_area_count_rpt(?,?,?,?,?,?,?)}";
	String PROC_RPT_ADMITTED_COUNTS = "{call pkg_mrd_rpt.proc_admitted_count_rpt(?,?,?,?,?,?)}";
	String PROC_RPT_OPD_REG_COUNTS = "{call pkg_mrd_rpt.proc_opd_reg_counts_rpt(?,?,?,?,?,?)}";
	String PROC_RPT_INVESTIGATION_COUNTS = "{call pkg_mrd_rpt.proc_investigation_counts_rpt(?,?,?,?,?,?)}";
	String PROC_RPT_OT_COUNTS = "{call pkg_mrd_rpt.proc_ot_counts_rpt(?,?,?,?,?,?)}";
	String PROC_GENERATE_CERTIFICATE_ID = "pkg_mrd_dtl.generatecertificateid";
	
	// Patient Detail
	String GET_PAT_EPISODE_DIAGNOSIS_DTL= "{call pkg_emr_view.proc_hrgt_episode_diagnosis_dtl(?,?,?,?,?,?,?)}";
	String GET_PAT_EPISODE_DRUG_DETAIL= "{call pkg_emr_view.proc_hrgt_episode_drug_dtl(?,?,?,?,?,?,?)}";
	String GET_PAT_ALLERGIES_DETAILS= "{call pkg_emr_view.proc_hpmrt_pat_allergy_dtl(?,?,?,?,?,?)}";
	String GET_PAT_ALERTS_DETAILS= "{call pkg_emr_view.proc_hpmrt_pat_alerts_dtl(?,?,?,?,?)}";
	
	
	//************    Reports JRXML ******************************************
	String MRD_JRXML_PATH ="/mrd/reports/jrxml/";
	String SPECIALITY_WISE_OUTDOOR_PATIENTS_REPORT_DEPTWISE="SpecialityWiseDisrtrbutiionofOutdoorPat.jrxml";
	String SPECIALITY_WISE_OUTDOOR_PATIENTS_REPORT_UNITWISE="SpecialityWiseDisrtrbutiionofOutdoorPatDEPTUNITWISE.jrxml";
	String SPECIALITYGENDERWISEOPDPATIENTSREPORT="SpecialitygenderWiseOPDPatientsbetweentwodates.jrxml";
	String SPECIALITYGENDERWISEOPDPATIENTSREPORT_NEW="SpecialitygenderWiseOPDPatientsbetweentwodates.jrxml";
	String REGISTERED_MLC_PATIENTS="RegisteredMLCpatientsBetweenTwoDates.jrxml";
	String SPECIALITY_UNITWISE_SPECIALCLINIC_REPORT="Speciality&UnitWiseSplClinicOPDPatientsbetweentwodates.jrxml";
	String SPECIALITY_WISE_SUNDAYCLINIC_REPORT="SpecialitySundayClinicPatientsbetween.jrxml";
	String SPECIALITY_WISE_OPERATION_REPORT="DeptDateWiseOperation.jrxml";
	String SPECIFIC_SPECIALITY_WISE_OPERATION_REPORT="DeptDateWiseOperationSpecific.jrxml";
	String SPECIALITY_WISE_INVESTIGATION_REPORT="DeptDateWiseInvestigation.jrxml";
	String GENDER_WISE_OUTDOOR_PATIENTS_REPORT="SexWiseDistofOPDPatitnets.jrxml";
	String SPECIFIC_GENDER_WISE_OUTDOOR_PATIENTS_REPORT="SexWiseDistofOPDPatitnetsSpecific.jrxml";
	String SPECIALITY_UNIT_WISE_SPLCLINIC_OPD_PAT_REPORT="specUnitWiseSplClinicOPDPat15.jrxml";
	String DEPT_WISE_MONTHLY_ABSTRACT_OF_PATIENT_REPORT="DeptWiseMonthlyAbstractOfPatient.jrxml";
	
	
	String MRD_ADMISSION_DISCHARGE_STATIC_TODAY="AdmissionAndDischargeStaticToday.jrxml";
	String MRD_ADMISSION_DISCHARGE_STATIC_DATEWISE="AdmissionAndDischargeStaticDateWise.jrxml";
	
	String MRD_ADMISSION_DISCHARGE_LISTING_DATEWISE="AdmissionAndDischargeListingDateWise.jrxml";
	String MRD_DISEASE_WISE_PATIENT_STATIC_DATEWISE="DiseaseWisePatientStaticDateWise.jrxml";
	String MRD_DISEASE_WISE_PATIENT_LISTING_DATEWISE="DiseaseWisePatientListingDateWise.jrxml";
	String MRD_DEATH_PATIENT_LISTING_DATEWISE="DeathPatientListingDateWise.jrxml";
	String MRD_MLC_PATIENT_LISTING_DATEWISE="MlcPatientListingReportDateWise.jrxml";
	String MRD_BROUGHT_DEATH_PATIENT_LISTING_DATEWISE="BroughtDeathPatientListingDatewise.jrxml";
	String MRD_WARD_CENSUS_REPORT_DATEWISE="WardCensusReportDateWise.jrxml";
	String MRD_OPERATION_CENSUS_REPORT_DATEWISE="OperationCensusReportDateWise.jrxml";
	String MRD_LAB_CENSUS_REPORT_DATEWISE="LabCensusReportDateWise.jrxml";
	String MRD_LAB_CENSUS_REPORT_TODAY="LabCensusReportToday.jrxml";
	String MRD_ISSUE_AND_RECEIPT_OF_CASE_PAPER_INTERFACE_DATEWISE="IssueAndReceiptOfCasePaperInterfaceDateWise.jrxml";
	
	
	//************    Values  ***********************************************
	////Record Type//////////
	String RECORD_TYPE_MEDICAL="11";
	String RECORD_TYPE_FITNESS="12";
	String RECORD_TYPE_GENERAL_CASESHEET="13";
	String RECORD_TYPE_MLC_CASESHEET="14";
	String RECORD_TYPE_DEATH_NOTIFICATION="15";
	String RECORD_TYPE_BIRTH_NOTIFICATION="16";
	String RECORD_TYPE_OPD_FILE="17";
	String RECORD_TYPE_ESTIMATE_CERTIFICATE="18";
	String RECORD_TYPE_CERTIFICATE_B="19";
	
	//////MEDICAL CERTIFICATE///////////
	
	String MC_PATIENT_ALL_EPISODE_VO_ARR="mcPatientAllEpisodeVOArr";
	String MC_PATIENT_ADM_ADVICE_VO_BY_EPISODE_ARR="mcPatAdmAdviceVOByEpisodeArr";
	String MC_PATIENT_EPI_REST_ADVICE_VO_BY_EPISODE_ARR="mcPatEpiRestAdviceVOByEpisodeArr";
	String MC_DIAGNOSIS_CODE_TYPE="mcDiagnosisCodeType";
	String MC_PATIENT_DIAGNOSIS_LIST="mcPatientdiagnosisList";
	String MC_PATIENT_ALL_VISIT_EPISODE_VO_ARR="mcPatientAllVisitEpisodeVOArr";
	String MC_LIST_ALL_CONSULTANT="mcListAllConsultant";
	String MC_ISSUED_MEDICAL_CERTIFICATE_ON_BASIS_EPISODE="mcIssuedMedicalCertificateOnBasisepisode";
	
	String IS_OVERLAPPED_YES="1";
	String IS_OVERLAPPED_NO="0";
	
	String MC_SAVE_FLAG_NEW="1";
	String MC_SAVE_FLAG_REST="2";
	String MC_SAVE_FLAG_MODIFY="3";
	
	
	///////////////FITNESS CERTIFICATE//////////////////
	
	String FC_PATIENT_MEDICAL_CERTIFICATE_LIST_BY_EPISODE="fcPatientMedicalCertificateListByEpisode";
	String CERTIFICATE_TYPE_MEDICAL_CERTIFICATE="1";
	String CERTIFICATE_TYPE_FITNESS_CERTIFICATE="2";
	String CERTIFICATE_TYPE_DUPLICATE="3";
	String CERTIFICATE_TYPE_DUPLICATE_MEDICAL_CERTIFICATE="4";
	String CERTIFICATE_TYPE_DUPLICATE_FITNESS_CERTIFICATE="5";
	
	String FITNESS_MC_EXTEND_YES="1";
	String FITNESS_MC_EXTEND_NO="2";
	
	
	///////////////ISSUE CERTIFICATE///////////
	String ALL_GENERATED_MC_OF_PATIENT="allGeneratedMCOfPatient";
	String ALL_GENERATED_FC_OF_PATIENT="allGeneratedFCOfPatient";
	String ALL_GENERATED_DUPLICATE_MC_OF_PATIENT="allGeneratedDuplicateMCOfPatient";
	String ALL_GENERATED_DUPLICATE_FC_OF_PATIENT="allGeneratedDuplicateFCOfPatient";
	
	String CERTIFICATE_IS_DUPLICATE_YES="1";
	String CERTIFICATE_IS_DUPLICATE_NO="0";
	
	
	////////////Certificate issued Record Status HPMRT_CERTIFICATE_ISSUE_DTL ////////////////
	String CERTIFICATE_RECORD_STATUS_NOT_REQUIRED="0";
	String CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT="1";
	String CERTIFICATE_RECORD_STATUS_RECEIVED_BY_MRD_CLERK="2";
	String CERTIFICATE_RECORD_STATUS_LOST="3";
	
	/*String CERTIFICATE_RECORD_STATUS_NOT_REQUIRED="0";
	String CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT="1";
	String CERTIFICATE_RECORD_STATUS_SENT_TO_MRD="2";
	String CERTIFICATE_RECORD_STATUS_RECEIVED="3";
	String CERTIFICATE_RECORD_STATUS_LOST="4";
	String CERTIFICATE_RECORD_STATUS_DESTROY="5";
	String CERTIFICATE_RECORD_STATUS_CONDEM="6";*/
	
	/////////////Certificate Received Record Status HPMRT_RECORD_DISPATCH_DTL ///////////////
	String CERTIFICATE_DISPATCH_RECORD_STATUS_DELAY_SEND_VAL_REQ="0";
	String CERTIFICATE_DISPATCH_RECORD_STATUS_READY_TO_DISPATCH="1";
	String CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER="2";
	String CERTIFICATE_DISPATCH_RECORD_STATUS_IN_MRD="3";
	String CERTIFICATE_DISPATCH_RECORD_STATUS_NOT_RECEIVED_RETURN="4";
	String CERTIFICATE_DISPATCH_RECORD_STATUS_LOST="5";
	
	/*String CERTIFICATE_RECEIVED_RECORD_STATUS_IN_MRD="1";
	String CERTIFICATE_RECEIVED_RECORD_STATUS_ISSUED="2";
	String CERTIFICATE_RECEIVED_RECORD_STATUS_LOST="3";
	String CERTIFICATE_RECEIVED_RECORD_STATUS_DESTROY="4";
	String CERTIFICATE_RECEIVED_RECORD_STATUS_CONDEM="5";*/
	
	/////////////Certificate entryMode HPMRT_RECORD_DISPATCH_DTL ///////////////
	String CERTIFICATE_DISPATCH_ENTRY_MODE_ONLINE="1";
	String CERTIFICATE_DISPATCH_ENTRY_MODE_OFFLINE="2";
	
	/////////////Certificate Record Status HPMRT_MRD_RECORD_DTL ///////////////
	/*String CERTIFICATE_MOVEMENT_RECORD_STATUS_SEND="1";
	String CERTIFICATE_MOVEMENT_RECORD_STATUS_RECEIVED="2";
	String CERTIFICATE_MOVEMENT_RECORD_STATUS_RETURN="3";*/
	//////////////////////////////////////////////////
	String ESSENTIAL_BO_OPTION_ALLDEPT="alldept";
	
	
	
	
	String MRD_RECORD_STATUS_IN_MRD="1";
	String MRD_RECORD_STATUS_ARCHIVED="2";
	String MRD_RECORD_STATUS_ISSUE="3";
	String MRD_RECORD_STATUS_LOST="4";
	String MRD_RECORD_STATUS_DESTROY="5";
	//String MRD_RECORD_STATUS_REJECTED="6";
	
	String MRD_RECORD_STATUS_IN_MRD_LABEL="In Mrd";
	String MRD_RECORD_STATUS_ARCHIVED_LABEL="Archived";
	String MRD_RECORD_STATUS_ISSUE_LABEL="Issued";
	String MRD_RECORD_STATUS_LOST_LABEL="Lost";
	String MRD_RECORD_STATUS_DESTROY_LABEL="Destroy/Condemn";
	//String MRD_RECORD_STATUS_REJECTED_LABEL="Rejected";
	
	String MRD_RECORD_STATUS_ARRAY[]=new String[]{"",MRD_RECORD_STATUS_IN_MRD_LABEL,MRD_RECORD_STATUS_ARCHIVED_LABEL,MRD_RECORD_STATUS_ISSUE_LABEL,MRD_RECORD_STATUS_LOST_LABEL,MRD_RECORD_STATUS_DESTROY_LABEL};
	
	String MRD_RECORD_STATUS_NOT_IN_MRD_LABEL="Not In Mrd";
	String MRD_RECORD_STATUS_NOT_IN_MRD="0";
	
	//************************Rack Shelf Master*****************************
	
	String ASSIGNED_SHELF_TO_RACK="assignedShelfList";
	String NOT_ASSIGNED_SHELF_TO_RACK="noAssignedShelfList";
	String RACK_NAME_LIST="rackNameOption";
	String SHELF_NAME_LIST="shelfNameOption";
	String MRD_RACK_NAME="rackName";
	
	
	String RECORD_TYPE="recordTypeList";
	

	//////////certificate Received//////////////////

	String LIST_OF_CERTIFICATE_FOR_MOVEMENT="lstOfCertificatForMovement";
	String ARR_CHECKLIST_FOR_MEDICAL_CERTIFICATE_VO="arrChecklistForMedicalCertificateVO";
	String SELECTED_CHECK_LIST="selectedCheckList";
	

	String ALL_CERTIFICATE_ISSUE_DTL_VO_LIST ="allCertificateIssueDtlVOList";
	String ALL_RACK_LIST="allRackList";
	String ALL_SELF_LIST="allSelfList";
	
	String ALL_RACK_LIST_VO="allRackListVO";
	String ALL_RACK_SHELF_LIST_VO="allRackShelfListVO";

	String CERTIFICATE_SELECT_TYPE_CR_NO="0";
	String CERTIFICATE_SELECT_TYPE_DEPT="1";
	
	String ALL_UNIT_LIST_FOR_CERTIFICATE_MOVE="allunitListForCertificateMove";
	String CERTIFICATE_MOVEMENT_SELECTED_CERTIFICATE="certificateMovementSelectedCertificate";		

	///////////////Epiosode Rest Advice Detail/////////////
	String REST_ADVICE_CERTIFICATE_STATUS_GENERATED="1";
	String REST_ADVICE_CERTIFICATE_STATUS_NOT_GENERATED="0";
	
	

	
	String TABLE_ID_HPMRT_RECORD_TYPE_CHECKLIST="2";
	String TABLE_ID_HPMRT_RACK_MST="1";
	String TABLE_ID_HPMRT_RECORD_TYPE_CHECKLIST_HPMRNUM_RECORD_TYPE="HPMRNUM_RECORD_TYPE";
	String SLNO="1";
	String IS_COMPULSORY_YES="1";
	String IS_COMPULSORY_NO="0";
	String RECORD_TYPE_ARRAY="recordTypeList";
	String ITEM_LIST="itemLst";
	String BUILDING_LIST="buildingLst";
	String RACK_TYPE_LIST="rackTypeList";
	String BLOCK_LIST="blockLst";
	String ROOM_LIST="roomLst";
	String FLOOR_LIST="floorLst";
	String ROOM_ID_LIST="roomIdList";
	String FETCH_BLOCK_LIST="fetchblockLst";
	String FETCH_ROOM_LIST="fetchroomLst";
	String FETCH_FLOOR_LIST="fetchfloorLst";
	String FETCH_ROOM_ID_LIST="fetchroomIdList";
	String RACK_MST_VO="rackMstVO";
	String[] IS_COMPULSORY_FOR_ENCLOSURE_AND_CHECKLIST_ARRAY={"","No","At Ward Level","At MRD Level"};
	String IS_COMPULSORY_FOR_ENCLOSURE_AND_CHECKLIST_LIST="compulsoryLst";
	
	String MRD_DEPT_UNIT_LIST="mrdDeptUnit";
	String MRD_WARD_LIST_ON_BASIS_UNIT="mrdDeptWards";
	
	String CASESHEET_DISPATCH_SELECTED_CASESHEET="caseSheetDispatchSelectedCaseSheet";

	String PATIENT_ADMISSION_STATUS_DISCHARGE="16";
	String PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY="patDetailsForCaseSheetDispatchVOArray";
	String ENCLOSURE_NAMES_CASESHEET_DISPATCH_VO_ARRAY="enclosureNamesForCaseSheetDispatchVOArray";
	String CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY="checklistForCaseSheetDispatchVOArray";
	String SELECTED_CHECKLIST_VO_LIST="selectedCheclistVOList";
	String RECORD_TYPE_CASESHEET="13";
	String COMPLUSORY_AT_WARD_LEVEL="1";
	String COMPLUSORY_AT_MRD_LEVEL="2";
	
	
	String DIAGNOSIS_CHOICE_ICD_CODE = "0";
	String DIAGNOSIS_CHOICE_HOSPITAL_CODE = "1";
	
	///////////// Patient Family Doctor Detail//////////////
	String ARR_EXISTING_PAT_FAMILY_DOC="arrExistingPatFamilyDoc";
	String FAMILY_DOCTOR_PHYSICIAN_TYPE_LIST="familyDoctorPhysicianTypeList";

	String CHECKLIST_BY_WARD="1";
	String CHECKLIST_BY_APPROVAL_LEVEL="2";
	String CHECKLIST_BY_RECEIVING_LEVEL="3";
	//String CHECKLIST_BY_PEON="4";
	String CHECKLIST_BY_ARCHIVAL_POINT="4";

	

	
	
	////////// Merging of CR Number////////////
	String ARR_TO_BE_MERGED_CR_NUMBER_VO="arrToBeMergedCrNumberVO";
	String ARR_SEARCH_PATIENT="arrSearchPatient";
	String ARR_MERGED_PATIENT="arrMergedPatient";
	
	String ESSENTIAL_GENDER_LIST="essenttialGenderList";
	String ESSENTIAL_CITY_LOCATION_LIST="essenttialCityLocationList";
	String ARR_ONLINE_SEARCH_PATIENT="arrOnlinesearchPatient";
	
	String CR_NUMBER_IS_MERGED_YES="1";
	String CR_NUMBER_IS_MERGED_REVOKED="0";
	
	String CR_NUMBER_IS_MAIN_YES="1";
	String CR_NUMBER_IS_MAIN_NO="0";

////////////////////EMR//////////////////////////////////
	String ESSENTIAL_DEPARTMENT_UNIT_LIST="departmentUnitList";
	String PATIENT_DTL_VO="patientDtlVOInEMR";
	String EMR_TABS[]=new String[]{"Patient Profile","Allergies","Chronic Disease","Diagnosis","Treatment","Investigation","External Investigation","Complaints","History","Examination","Vitals","InTake OutTake","Progress Notes","Profile","Image Examination","Operation","ANC Detail"};
	String EMR_TABS_URL[]=new String[]{"PERSONALPROFILE","ALLERGIES","CHRONICDISEASE","DIAGNOSIS","TREATMENT","INVESTIGATION","EXTINESTIGATION","COMPLAINTS","HISTORY","EXAMINATION","VITALS","INTAKEOUTTAKE","PROGRESSNOTES","PROFILE","IMAGEEXAMINATION","OPERATION","ANCDETAIL"};
	String EMR_TABS_ARRAY="emrTabsArray";
	String EMR_TABS_IPD[]=new String[]{"Patient Profile","Allergies","Chronic Disease","Diagnosis","Treatment","Investigation","Intake OutTake"};
	String EMR_TABS_ARRAY_IPD="emrTabsArrayIpd";
	String PAT_ADDRESS_DETAILS="patAddressDetails";
	String PAT_IMAGE_DETAILS="patImageDetails";
	String PAT_DEFAULT_IMAGE_DETAIL="patDefaultImageDetails";
	String PAT_EPISODE_NEXT_VISIT_ARRAY="patEpisodeNextVisitArray";
	String PAT_FAMILY_DOC_DETAILS="patFamilyDocDetails";
	String PAT_EPISODE_DIAGNOSIS_VO_ARRAY="episodeDiagnosisVOArray";
	String PAT_EPISODE_ALLERGIES_VO_ARRAY="patEpisodeAllergiesArray";
	String PAT_CHRONIC_DISEASE_ARRAY="patChronicDiseaseArray";
	String PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY="patDiagnosisDetailsVisitArray";
	String PREVIOUS_CRNO="previousCrNo";
	String PATIENT_RECORD_MAP="patientRecordMap";
	String PERSONAL_PROFILE_MAP="personalProfileMap";
	String PAT_DRUG_ADVICE_DETAILS_VO_ARRAY="patDrugAdviceDetailsVOArray";
	String PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY="patDrugAdviceOfflineDetailsVOArray";
	String PAT_INVESTIGATION_DETAILS_VO_ARRAY="patInvestigationDetailsVOArray";
	String PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY="patProgressNotesDocRundDtlArray";
	String PAT_INTAKE_OUTAKE_DETAILS="patIntakeOutakeDetails";
	String EPISODE_TREE_ARRAY="episodeTreeArray";
	String ADMISSION_TREE_ARRAY="admissionTreeArray";
	String PAT_INTAKE_DETAIL_ARRAY="patIntakeDetailsArray";
	String PAT_OUTTAKE_DETAIL_ARRAY="patOuttakeDetailsArray";
	String PAT_INTAKE_OUTTAKE_MAP="patIntakeOuttakeMap";
	String PAT_VITAL_DETAIL_ARRAY="patVitalDetailsArray";
	String PAT_EXT_INVESTIGATION_ARRAY="patExtInvestigationArray";
	String PAT_COMPLAINTS_DETAILS_ARRAY="patComplaintsDetailsArray";
	String PAT_HISTORY_DETAILS_ARRAY="patHistoryDetailsArray";
	String PAT_EXAMINATION_DETAILS_ARRAY="patExaminationDetailsArray";
	String PATIENT_PROFILE_DTL_VO_ARRAY="patientProfileDtlVOArray";
	String PATIENT_EXAMINATION_IMAGES="patientExaminationImages";
	String PATIENT_OPERATION_LIST="patientOperationList";
	String OPERATION_TEMPLATE_LIST="operationTemplateList";
	String OPERATION_TEMPLATE_PARA_ID_VALUE="operationTemplateParaIdValue";
	String PAT_ANC_DETAIL_VO_ARRAY="patAncDetailVOArray";
	String EPISODE_SUMMARY_VO_ARRAY="episodeSummaryVOArray";
	String EPISODE_VO_ARRAY="episodeVOArray";
	String PATIENT_SCANNED_DOCS="patientScannedDocs";
	String PATIENT_CR_MERGE_LIST="patientCRMergeList";
	String PAT_VISIT_REASON_ARRAY="patVisitReasonArray";
	String PAT_SERVICE_PROCEDURE_ARRAY = "patServiceProcedureArray";
	String PAT_DOCUMENT_UPLOAD_ARRAY = "patDocumentUploadArray";
	
	String EPR_PATIENT_CATEGORY_NORMAL="1";
	String EPR_PATIENT_CATEGORY_RESTRICTED="2";
	
	String PAT_MLC_VO="patMlcVO";
	
	String IS_DEFAULT_EPR_TAB_YES="1";
	String IS_DEFAULT_EPR_TAB_NO="0";
	String EPR_TAB_TYPE_OPD="1";
	String EPR_TAB_TYPE_IPD="2";
	String EPR_TAB_TYPE_OPD_AND_IPD="0";
	String EPR_TAB_ACCESS_DTL_VO_LIST="eprTabAccessDtlVOList";
	String EPR_PATIENT_CATEGORY="eprPatientCategory";
	String EPR_DISPLAY_ALL_RECORD_YES="1";
	String EPR_DISPLAY_ALL_RECORD_NO="0";
	String EPR_RECORD_ACCESS_UNIT_BOUND="0";
	String EPR_RECORD_ACCESS_USER_BOUND="1";
	String ESSENTIAL_EPR_TAB_LIST="essentialEprTabList";
	
	String [] EPISODE_VISIT_TYPE={"IPD","OPD","CASUALITY","CASUALITY","SPECIAL"};
	String PAT_ADMISSION_DTL_VO_ARRAY="patAdmissionDtlVOArray";
	String EPISODE_REF_DTL_VO_ARRAY="episodeRefDtlVOArray";
	
/////////////////////////////////////////////////////////


	
	//Patient Image upload
	String PATIENT_IMAGE_DTL_VO_LIST="patientImageDtlVoList";
	String PATIENT_IMAGE_MAP="patientImageMap"; 
	String PATIENT_IMAGE_UPLOADED_IN_SESSION="patientImageInSession";
	
	
	//Process Wise Designation Mapping
	String PROCESS_TYPE_LIST="processTypeList";
	String DESIGNATION_LIST_NOT_MAPPED="desigNotMapped";
	String DESIGNATION_LIST_MAPPED="desigMapped";
	//String PROCESS_TYPE_NAME="processTypeName";

	//Case Sheet Type
	String CASESHEET_TYPE_GENERAL="1";
	String CASESHEET_TYPE_MLC="0";
	
	//Case sheet status
	String CASE_SHEET_READY_TO_DISPATCH="0";
	String CASE_SHEET_IN_WARD="1";
	String CASE_SHEET_DISPATCHED="2";
	String CASE_SHEET_INMRD="3";
	String CASE_SHEET_LOST="4";
	String CASE_SHEET_PARTIAL_LOST="5";
	String CASE_SHEET_ISSUED_FROM_WARD="6";
	String CASE_SHEET_CANCEL="7";
	String CASE_SHEET_DELAY_SEND="8";
	
	String CASE_SHEET_ORIGINAL="0";
	String CASE_SHEET_DUPLICATE="1";
	String CASE_SHEET_ENCLOSURE_LIST="caseSheetEnclosureList";
	String CASE_SHEET_CHECKLIST="caseSheetCheckList";
	
	//case sheet dispatch record status
	String CASE_SHEET_DISPATCH_RECORD_STATUS_DELAY_SEND="0";
	String CASE_SHEET_DISPATCH_RECORD_STATUS_READY_TO_DIPATCH="1";
	String CASE_SHEET_DISPATCH_RECORD_STATUS_SEND="2";
	String CASE_SHEET_DISPATCH_RECORD_STATUS_IN_MRD="3";
	String CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN="4";
	String CASE_SHEET_DISPATCH_RECORD_STATUS_LOST="5";
	
	//case sheet ready
	String CASESHEET_ENCLOSURE_CHECKLIST_MAP="enclosureChecklistMap";
	
	//Case sheet Monitoring
	String CASE_SHEET_DETAIL_VO_LIST="caseSheetDtlVOList";
	String CASE_SHEET_LOST_FOUND_VO="caseSheetLostFoundVO";
	
	String CASE_SHEET_DISPATCH_VO_LIST="caseSheetDispatchVOList";
	String CASE_SHEET_ENCLOSURE_PRE_ADDED_VO_LIST="enclosurePreAddedVOList";
	String CASE_SHEET_CHECKLIST_PRE_ADDED_VO_LIST="checklistPreAddedVOList";
	
	//Discharge printing
	String DISCHARGE_PAT_LIST="dischargePatList";
	String IS_DUPLICATE_NO="0";
	String IS_DUPLICATE_YES="1";
	String PATIENT_PROFILE_BYTE_ARRAY="patientProfileByteArray";
	String HAND_OVER_TO_RELATIVE="0";
	String HAND_OVER_TO_POLICE="1";
	String HAND_OVER_TO_SELF="2";
	String IS_MLC_YES="1";
	String IS_MLC_NO="0";
	String IS_PATIENT_DEAD_NO="0";
	String IS_PATIENT_DEAD_YES="1";
	String IS_PRINTED_NO="0";
	String IS_PRINTED_YES="1";
	
	/**********************summon detail******************************/
	
	
	//summon detail upload desk
	String SUMMON_DETAIL_UPLOAD_MODE_MANUAL="MANUAL";
	String SUMMON_DETAIL_UPLOAD_MODE_COMPUTERIZED="COMPUTERIZED";
	
	//record type flag
	String NOT_IN_DATABASE="0";
	String IN_DATABASE="1";

	//////For tab generation 
	String TAB_DSK_JS_FUNC_ON_CLICK="change_tab";
	String TAB_DSK_HTML_MODE_FIELD_NAME="deskmode";	
	String TAB_GROUP="Tab_Group";

	//entry Mode flag
	String ONLINE="1";
	String OFFLINE="0";
	
	//Status Flag
	String SUMMON_RECEIVED="1";
	String SUMMON_ASSIGN="2";
	String PRESENT_IN_COURT="3";
	String NOT_PRESENT_IN_COURT="4";
	String SUMMON_ACCEPTED="5";
	
	// IsEmployeeFlag
	String IS_EMPLOYEE_YES="1";
	String IS_EMPLOYEE_NO="0";
	
	//isSummonUploadFlag
	String IS_SUMMON_UPLOAD_YES="1";
	String IS_SUMMON_UPLOAD_NO="0";
	
	//assign flag
	String EMPLOYEE_AS_REQUIRED_IN_SUMMON="1";
	String ARRANGEMENT_TO_ATTEND_SUMMON="0";
	
	//hearing attended flag
	String IS_HEARING_ATTENDED_YES="1";
	String IS_HEARING_ATTENDED_NO="0";
	
	//selcting criteria for search
	
	String ALL_RECORD="1";
	String ATTENDED_SUMMON="2";
	String NEXT_HEARING="3";
	String POST_DETAIL="4";
	String NOT_ATTENDED="5";
	String PARTICULAR_EMPLOYEE="6";
	String SUMMON_TYPE="7";
	
	//display Flag
	String DISPLAY_MODE_IS_STASTICAL="0";
	String DISPLAY_MODE_IS_LISTING="1";
	
	//search Type
	String PAT_SEARCH_BY_NAME="0";
	String PAT_SEARCH_BY_MLCNO="1";
	String PAT_SEARCH_BY_POSTMARTEM_NO="2";
	String PAT_SERACH_BY_CRNO="3";
	String PAT_SERACH_BY_ADMISSION_NO="4"; 
	
	//file tracking flag
	String ENABLE="1";
	String DISABLE="2";
	String SUMMON_FILE_TRACKING_FLAG=DISABLE; 
	
	String MANUAL_SUMMON_DETAIL_FLAG=ENABLE;
	 
	String ADDRESS_TYPE_CODE="1";
	String SUMMON_TYPE_NUM_CATEGORY_ID="2";
	String ALL_CID_NO_INFO_LIST="allCIDNoInfoList";
	
	String MIN_AGE_FOR_GETTING_MARRIED="18";
	String CHILD_AGE_IN_YEAR_FOR_ASKING_MOTHER_NAME="2";
	String MAX_VALUE_OF_MONTH="11";
	String MAX_VALUE_OF_WEAK="4";
	String MAX_VALUE_OF_DAYS="31";
	
	String IS_COMPUTERIZED_SUMMON_DTL="0";
	String IS_MANUAL_SUMMON_DTL="1";
	
	
	String ALL_GENDER_LIST="allGenderList";
	String ALL_SUMMON_TYPE_LIST="allSummonTypeList";
	String ALL_SUMMON_RECEIVED_LIST="allSummonReceivedList";
	String SELECTED_SUMMON_RECEIVED_VO="selectedSummonReceivedVO";
	String ALL_AGE_TYPE_LIST="allAgeTypeList";
	String SEARCH_EMPLOYEE_lIST="searchEmployeeList";
	String SELECTED_EMPLOYEE_DETAIL="selectedEmployeeDetail";
	String POST_SUMMON_LIST="postSummonList";
	String SELECTED_POST_SUMMON_VO="selectedPostSummonVO";
	String SUMMON_ASSIGN_CHANGE_LIST="summonAssignChangeList";
	String SELECTED_SUMMON_ASSIGN_CHANGE_VO="selectedSummonAssignChangeVO";
	String ALL_SUMMON_ASSIGNED_LIST="allSummonAssignedList";
	String ALL_SEARCH_SUMMON_DTL_VO_LIST="allSearchSummonDtlVOList";
	String ALL_EMPLOYEE_LIST="allEmployeeList";
	String PAT_DETAIL_lIST_BY_MLCNO="patDetailListByMLCNo";
	String PAT_DETAIL_LIST_BY_POSTMORTEMID="patDetailListByPostMortemId";
	String PAT_DETAIL_lIST_BY_ADMISSION_NO="patDetailListByAdmissionNo";
	String PAT_DETAIL_LIST_BY_ADMISSION_NO="patDetailListByAdmissionNo";

	/**********insurance claim receiving**************************/
	
	
	String INSURANCE_FILE_TRACKING_FLAG=DISABLE;  
	//String INSURANCE_FILE_TRACKING_FLAG_DISABLE=DISABLE;
	//String INSURANCE_FILE_TRACKING_FLAG_ENABLE=ENABLE;
	
	//RECEIVING MODE
	String RECEIVING_MODE_COMPANY="1";
	String RECEIVING_MODE_PATIENT_OR_RELATIVE="2";
	
	//sending Mode
	String SENDING_MODE_COMPANY="1";
	String SENDING_MODE_PATIENT_OR_RELATIVE="2";
	
	
	String INSURANCE_TYPE_NUM_CATEGORY_ID="8";
	
	String INSURANCE_COMPANY_TYPE="3";
	
	//insurance status
	
	String INSURENCE_STATUS_REQUEST_RECEIVE="1";
	String INSURENCE_STATUS_OPINION_FILLED="2";
	String INSURENCE_STATUS_OPINION_SEND="3";
	
	String ALL_CID_NO_INFO_LIST_FOR_INSURANCE="cidNoInfoLidtForInsurance";
	String ALL_INSURANCE_COMPANY_LIST="allInsuranceCompanyList";
	String ALL_PAT_INFO_LIST="allPatInfoList";
	
	////Birth/Death Registration Upload Desk
	String BIRTH_DEATH_REGISTRATION_UPLOAD_MODE_BIRTH="BIRTH";
	String BIRTH_DEATH_REGISTRATION_UPLOAD_MODE_DEATH="DEATH";
	
	///Birth Death Registration Upload
	String ARR_BIRTH_REGISTRATION_UPLOAD_LIST_VO="arrBirthRegistrationUploadListVO";
	String ARR_DEATH_REGISTRATION_UPLOAD_LIST_VO="arrDeathRegistrationUploadListVO";
	String ARR_BIRTH_REGISTRATION_UPLOAD_DUPLICATE_VO="arrBirthRegistrationUploadDuplicateVO";
	String ARR_DEATH_REGISTRATION_UPLOAD_DUPLICATE_VO="arrDeathRegistrationUploadDuplicateVO";
	
	String RREGISTRATION_UPLOAD_MODE_BIRTH="1";
	String RREGISTRATION_UPLOAD_MODE_DEATH="2";
	
	///Birth/Death Entry Mode Upload
	String BIRTH_DEATH_ENTRY_MODE_UPLOAD="1";
	String BIRTH_DEATH_ENTRY_MODE_UPLOAD_N_HANDOVER="2";
	String BIRTH_DEATH_ENTRY_MODE_DUPLICATE_N_HANDOVER="3";
	
	String ESSENTIAL_ALL_RELATION_LIST="essentialAllRelationList";
	String BIRTH_REGISTRATION_UPLOAD_DETAIL_VO="birthRegistrationUploadDetailVO";
	String MOTHER_DETAIL_VO="motherDetailVO";
	String CHILD_DETAIL_VO="childDetailVO";
	String BIRTH_REGISTRATION_SLIP_DETAIL_VO="birthRegistrationSlipDetailVO";
	String SERACH_DEATH_REGISTRATION_UPLOAD_LIST="searchDeathRegUploadList";
	
	String YES="1";
	String NO="0";
	
	String SEARCH_TYPE_MOTHER="1";
	String SEARCH_TYPE_CHILD="0";

	
	
	String WEEK_OF_MONTH="1";
	String UNITS_BASED_ON_SPECIALITY="lstUnit";
	String EXT_LAB_LIST="lab";
	String EXt_LAB_TEST_LIST="labTest";
	
	String DEPT_WISE="1";
	String UNIT_WISE="2";
	String GENDER_WISE="3";
	String LAB_WISE="1";
	String LAB_TEST_WISE="2";
	
	String UNIT_VO_ARRAY="unitVo";
	
	String MALE_LABEL="Male";
	String FEMALE_LABEL="Female";
	String OTHERS_LABEL="Others";
	
	String OPERATION_TYPE_MAJOR="10";
	String OPERATION_TYPE_MINOR="11";
	String MAJOR_LABEL="Major";
	String MINOR_LABEL="Minor";

	
	
	////Check List By ////HPMRT_RECORD_CHECKLIST_DTL//////
	String CHECK_LIST_BY_DISPATCH="1";
	String CHECK_LIST_BY_APPROVAL="2";
	String CHECK_LIST_BY_RECEIVING="3";
	String CHECK_LIST_BY_ARCHIVAL="4";
	
	
	///Certificate Accept Reject Flag/////
	String CERTIFICATE_ACCEPT="1";
	String CERTIFICATE_REJECT="2";
	
	///Certifiacte Received Desk Mode
	String CERTIFICATE_RECEIVED_IN_MRD_MODE_ACCEPT="ACCEPT";
	String CERTIFICATE_RECEIVED_IN_MRD_MODE_ARCHIVAL="ARCHIVAL";
	String CERTIFICATE_RECEIVED_IN_MRD_MODE_LOSTFOUND="LOSTFOUND";
	
	String RECORD_TYPE_FOR_ACCEPTANCE="recordTypeForAcceptance";
	String RECORD_TYPE_NAME_FOR_ACCEPTANCE="recordTypeNameForAcceptance";
	String MRD_CODE_FOR_ACCEPTANCE="mrdCodeForAcceptance";
	String ADM_NO_FOR_ACCEPTANCE="admNoForAcceptance";
	String CR_NO_FOR_ACCEPTANCE="crNoForAcceptance";
	String ARR_RECORD_LIST_TO_ACCEPT_IN_MRD_BY_RECORD_TYPE_VO="arrRecordListToAcceptInMrdByRecordTypeVO";

	String ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_RECORD_TYPE_VO="arrRecordListToArchivedInMrdByRecordTypeVO";
	String ARR_LOST_RECORD_LIST_TO_BE_FOUND_BY_RECORD_TYPE_VO="arrLostRecordListToBeFoundByRecordTypeVO";
	
	
	String ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_ADM_NO_VO="arrRecordListToArchivedInMrdByAdmNoVO";
	
	String ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_CR_NO_VO="arrRecordListToArchivedInMrdByCRNoVO";

	
	String CERTIFICATE_ACCEPT_IN_MRD="1";
	String CERTIFICATE_LOST_BEFORE_MRD="2";
	
	/////Mrd List///
	String LIST_ALL_MRD="listAllMrd";
	String LIST_ALL_MRD_USER_BASED="listAllMrdUserBased";
	
	////Record Bounding Mode//
	String RECORD_BOUNDING_MODE_MRD_WISE="1";
	String RECORD_BOUNDING_MODE_RACK_WISE="2";
	String RECORD_BOUNDING_MODE_SHELF_WISE="3";
	
	
	String LIST_OF_RACK_BASED_ON_MRD="listOfRackBasedOnMrd";
	String LIST_OF_SHELF_BASED_ON_RACK="listOfShelfBasedOnRack";
	String MRD_RECORD_DTL_VO="mrdrecorddtlvo";
	
	/////Rack Status////
	String MRD_RACK_STATUS_NOT_IN_USED="0";
	String MRD_RACK_STATUS_WORKING="1";
	
	////Shelf Status////////
	String MRD_RACK_SHELF_STATUS_NOT_IN_USED="0";
	String MRD_RACK_SHELF_STATUS_SPACE_AVAILABLE="1";
	String MRD_RACK_SHELF_STATUS_OCCUPIED="2";
	
	////Record Lost Flag/////
	String RECORD_LOST_TYPE_PARTIAL="1";
	String RECORD_LOST_TYPE_COMPLETE="2";
	
	String ENCLOSURE_DETAIL_ACCEPT_IN_MRD_VO="enclosureDetailAcceptInMrdVO";
	String ENCLOSURE_ACCEPTED_IN_MRD_MAP="enclosureAcceptedinMrdMap";
	
	String LIST_RECORD_PUT_BY_IN_MRD="listRecordPutByInMrd";
	
	String LIST_CHECKLIST_BY_RECORD_TYPE="listChecklistByRecordType";
	String ARR_CHECKED_CHECKLIST_BY_DISPATCH_ID="arrCheckedChecklistByDispatchId";

	
	//checklist Mode ///
	String CHECKLIST_MODE_DISPATCH_LEVEL="1";
	String CHECKLIST_MODE_APPROVAL_LEVEL="2";
	String CHECKLIST_MODE_HANDOVER_LEVEL="3";
	String CHECKLIST_MODE_ARCHIVAL_POINT="4";
	
	String RECORD_DISPATCH_VO_ARRAY="recordDispatchVOArray";
	String RECORD_ENCLOSURE_SUMMARY_VO_ARRAY="recordEnclosureVOArray";
	
	String GET_EMP_LIST_PROCEDURE="PKG_MRD_DTL.proc_emplist_wardwise";
	

	//Case Sheet Enquiry
	String CASE_SHEET_ENQUIRY_DISCHARGE_TYPE_LIST="caseSheetEnquiryDIschargeTypeList";
	
	String DYNAMIC_DESK_ICD_DISEASE_LIST="dynamicdeskicddisease";
	String COMMON_CASE_SHEET_ENQUIRY_VO_ARRAY="commonCaseSheetEnquiryVoArray"; 
	String CASE_SHEET_ENQUIRY_VO="CaseSheetEnquiryVo";
	String CASE_SHEET_ENQUIRY_RECORD_STORAGE_VO="CaseSheetEnquiryRecordStorageVO";
	String RECORD_TYPE_GENERAL_CASESHEET_LABEL="General CaseSheet";
	String RECORD_TYPE_MLC_CASESHEET_LABEL="MLC CaseSheet";
	String CASE_SHEET_ENQUIRY_ALERT_TYPE_LIST="caseSheetEnquiryAlertTypeList";
	String CASE_SHEET_ENQUIRY_ALLERGY_TYPE_LIST="caseSheetEnquiryAllergyTypeList";
	String CASE_SHEET_ENQUIRY_DIAGNOSIS_CODE_LIST="caseSheetEnquiryDiagnosisCodeList";
	String CASE_SHEET_ENQUIRY_PATIENT_WISE="1";
	String CASE_SHEET_ENQUIRY_CHRONIC_DISEASE_WISE="2";
	String CASE_SHEET_ENQUIRY_ALLERGY_WISE="3";
	String CASE_SHEET_ENQUIRY_DIAGNOSIS_WISE="4";
	
	String RECIEVING_HANDOVER_EMP_LIST_OPTION="handoverEmployeeList";
	
	String SELECTED_RECORD_MAP="selectedRecordMap";
	String ARR_CHECKLIST_BY_RECORD_TYPE_N_MODE_VO="arrChecklistByRecordTypeNMode";
	String CHECKLIST_ARCHIVED_IN_MRD_MAP="checklistArchivedInMrdMap";
	
	String LIST_MRD_BASED_ON_RECORD_TYPE="listMrdBasedOnRecordType";
	String LIST_MRD_FB="listMrdFb";
	String ARR_REQUEST_PURPOSE_MST_VO="arrRequestPurposeMstVO";
	
	//////HPMRT_MRDRECORD_REQ_DTL//////Request Status///old//not in use
	/*String MRD_RECORD_REQUEST_STATUS_RAISED="1";
	String MRD_RECORD_REQUEST_STATUS_ACCEPTED="2";
	String MRD_RECORD_REQUEST_STATUS_REJECTED="3";
	String MRD_RECORD_REQUEST_STATUS_ISSUED="4";
	String MRD_RECORD_REQUEST_STATUS_PRTIAL_ISSUE="5";
	String MRD_RECORD_REQUEST_STATUS_RETURN="6";
	String MRD_RECORD_REQUEST_STATUS_ACCEPTED_BY_MRD="7";
	String MRD_RECORD_REQUEST_STATUS_REJECTED_BY_MRD="8";
	String MRD_RECORD_ISSUED_BUT_REQUESTED_FOR_EXTENSION="9";
	
	String MRD_RECORD_REQUEST_STATUS_RAISED_LABEL="Raised";
	String MRD_RECORD_REQUEST_STATUS_ACCEPTED_LABEL="Accepted";
	String MRD_RECORD_REQUEST_STATUS_REJECTED_LABEL="Rejected";
	String MRD_RECORD_REQUEST_STATUS_ISSUED_LABEL="Issued";
	String MRD_RECORD_REQUEST_STATUS_PRTIAL_ISSUE_LABEL="Partial Issued";
	String MRD_RECORD_REQUEST_STATUS_RETURN_LABEL="Return";
	String MRD_RECORD_REQUEST_STATUS_ACCEPTED_BY_MRD_LABEL="Accepted At MRD";
	String MRD_RECORD_REQUEST_STATUS_REJECTED_BY_MRD_LABEL="Rejected at MRD";
	String MRD_RECORD_ISSUED_BUT_REQUESTED_FOR_EXTENSION_LABEL="Extented";
	
	String MRD_RECORD_REQUEST_STATUS_ARRAY[]=new String[]{"",MRD_RECORD_REQUEST_STATUS_RAISED_LABEL,MRD_RECORD_REQUEST_STATUS_ACCEPTED_LABEL,MRD_RECORD_REQUEST_STATUS_REJECTED_LABEL,MRD_RECORD_REQUEST_STATUS_ISSUED_LABEL,MRD_RECORD_REQUEST_STATUS_PRTIAL_ISSUE_LABEL,MRD_RECORD_REQUEST_STATUS_RETURN_LABEL,MRD_RECORD_REQUEST_STATUS_ACCEPTED_BY_MRD_LABEL,MRD_RECORD_REQUEST_STATUS_REJECTED_BY_MRD_LABEL,MRD_RECORD_ISSUED_BUT_REQUESTED_FOR_EXTENSION_LABEL};
	*/
	
//////HPMRT_MRDRECORD_REQ_DTL//////Request Status
	String MRD_RECORD_REQUEST_STATUS_RAISED="1";
	String MRD_RECORD_REQUEST_STATUS_IN_PROCESS="2";
	String MRD_RECORD_REQUEST_STATUS_CLOSED="3";
	
	String MRD_RECORD_REQUEST_STATUS_RAISED_LABEL="Raised";
	String MRD_RECORD_REQUEST_STATUS_IN_PROCESS_LABEL="In Process";
	String MRD_RECORD_REQUEST_STATUS_CLOSED_LABEL="Closed";
	
	String MRD_RECORD_REQUEST_STATUS_ARRAY[]=new String[]{"",MRD_RECORD_REQUEST_STATUS_RAISED_LABEL,MRD_RECORD_REQUEST_STATUS_IN_PROCESS_LABEL,MRD_RECORD_REQUEST_STATUS_CLOSED_LABEL};

	
	
	//////HPMRT_REQ_RECORD_DTL//////Request Status///old//not in use
	/*String REQUESTED_RECORD_STATUS_RAISED="1";
	String REQUESTED_RECORD_STATUS_ACCEPTED="2";
	String REQUESTED_RECORD_STATUS_REJECTED="3";
	String REQUESTED_RECORD_STATUS_ISSUED="4";
	
	String REQUESTED_RECORD_STATUS_RAISED_LABEL="Raised";
	String REQUESTED_RECORD_STATUS_ACCEPTED_LABEL="Accepted";
	String REQUESTED_RECORD_STATUS_REJECTED_LABEL="Rejected";
	String REQUESTED_RECORD_STATUS_ISSUED_LABEL="Issued";
	
	String REQUESTED_RECORD_STATUS_ARRAY[]=new String[]{"",REQUESTED_RECORD_STATUS_RAISED_LABEL,REQUESTED_RECORD_STATUS_ACCEPTED_LABEL,REQUESTED_RECORD_STATUS_REJECTED_LABEL,REQUESTED_RECORD_STATUS_ISSUED_LABEL};
*/
	
	
//////HPMRT_REQ_RECORD_DTL//////Request Status
	String REQUESTED_RECORD_STATUS_RAISED="1";
	String REQUESTED_RECORD_STATUS_ACCEPTED_AT_DEPT="2";
	String REQUESTED_RECORD_STATUS_REJECTED_AT_DEPT="3";
	String REQUESTED_RECORD_STATUS_ISSUED="4";
	String REQUESTED_RECORD_STATUS_PATIAL_ISSUED="5";//not in use
	String REQUESTED_RECORD_STATUS_RETURN="6";
	String REQUESTED_RECORD_STATUS_ACCEPTED_AT_MRD="7"; //not in use
	String REQUESTED_RECORD_STATUS_REJECTED_AT_MRD="8";
	
	String REQUESTED_RECORD_STATUS_RAISED_LABEL="Raised";
	String REQUESTED_RECORD_STATUS_ACCEPTED__AT_DEPT_LABEL="Accepted";
	String REQUESTED_RECORD_STATUS_REJECTED__AT_DEPT_LABEL="Rejected";
	String REQUESTED_RECORD_STATUS_ISSUED_LABEL="Issued";
	String REQUESTED_RECORD_STATUS_PATIAL_ISSUED_LABEL="Partial Issue";//not in use
	String REQUESTED_RECORD_STATUS_RETURN_LABEL="Returned";
	String REQUESTED_RECORD_STATUS_ACCEPTED_AT_MRD_LABEL="Accepted"; // not in use
	String REQUESTED_RECORD_STATUS_REJECTED_AT_MRD_LABEL="Rejected";
	
	String REQUESTED_RECORD_STATUS_ARRAY[]=new String[]{"",REQUESTED_RECORD_STATUS_RAISED_LABEL,REQUESTED_RECORD_STATUS_ACCEPTED__AT_DEPT_LABEL,REQUESTED_RECORD_STATUS_REJECTED__AT_DEPT_LABEL,REQUESTED_RECORD_STATUS_ISSUED_LABEL,REQUESTED_RECORD_STATUS_PATIAL_ISSUED_LABEL,REQUESTED_RECORD_STATUS_RETURN_LABEL,REQUESTED_RECORD_STATUS_ACCEPTED_AT_MRD_LABEL,REQUESTED_RECORD_STATUS_REJECTED_AT_MRD_LABEL};

	//summon inbox
	String ALL_ASSIGNED_SUMMON_DETAIL_LIST="allSummonAssignList";
	String SELECTED_SUMMON_ASSIGN_ACCEPTED_VO="selectedSummonAssignAcceptedVo";

	///////HPMRT_MRDRECORD_REQ_DTL/////////Request Mode
	String MRD_RECORD_REQUEST_MODE_BY_USER="1";
	String MRD_RECORD_REQUEST_MODE_AUTOGENERATED="2";
	String MRD_RECORD_REQUEST_MODE_EXTERNAL="3";
	
	String ARR_CASESHEET_SEARCHED_RECORD_VO="arrCaseSheetSearchedRecordVO";
	String ARR_TO_BE_REQUESTED_RECORD_VO="arrToBeRequestedRecordVO";
	
	String MRD_RECORD_REQUEST_VO_LIST="mrdRecordRequestVOList";
	String REQUEST_RECORD_VO_LIST="requestRecordVOList";
	
	//request purpose priority
	String RECORD_REQUEST_PRIORITY_LOW="1";
	String RECORD_REQUEST_PRIORITY_MEDIUM="2";
	String RECORD_REQUEST_PRIORITY_HIGH="3";
	
	String [] RECORD_REQUEST_PRIORITY_ARRAY =new String []{"","Low","Medium","High"};

	///////insurance claim record entry//////////////
	
	String INSURANCE_RECEIVED_DTL_LIST="insuranceReceivedDtlList";
	String SELECTED_INSURANCE_DETAIL_VO="selectedInsuranceDetailVO";
	String LIST_OF_EMP_DOCTOR="listOfEmpDoctor";

	String MRD_RECORD_DTL_VO_LIST="mrdRecordDtlVOList";
	String MRD_RECORD_ISSUE_DTL_VO_LIST="mrdRecordIssueDtlVOList";
	
	//Record Movable Type
	String RECORD_MOVABLE_TYPE_DISPATCH_ONLY="1";
	String RECORD_MOVABLE_TYPE_MOVABLE_ONLY="2";
	String RECORD_MOVABLE_TYPE_DISPATCH_AND_MOVE_ONLY="3";
	String RECORD_MOVABLE_TYPE_DISPATCH_OFFLINE="4";
	
	String RECORD_TYPE_PATIENT_CENTRIC_YES="1";
	
	
	///Mrd Record Return 
	String ISSUED_MRD_RECORD_REQUEST_VO_LIST="issuedMrdRecordRequestVOList";
	String ISSUED_MRD_RECORD_BY_REQUEST_ID_VO_LIST="issuedMrdrecordByRequestIdVOList";
	
	String RECORD_RETURN_DEFAULT_REMARKS="From Record Return";
	
	String ARR_ONLINE_PENDING_REQUEST_LIST="arrOnlinePendingRequestList";
	String PATIENT_ADDMISSION_NO_WISE_DTLLIST="addNoWiseDtlLst";
	String PATIENT_DIAGNOSIS_DTLLIST="patDiagDtlLst";
	String ONLINE_PENDING_REQUEST_STATUS_DETAIL="onlinePendingRequestStatusDetail";
	String LOGIN_USER_REQUEST_BY_DETAILS="loginuserrequestbydetails";
	String EXTENDED_REQUEST_DAYS="extendedrequestdays";

	String AGE_RANGE_COMBO="lstAge";
	String AGE_SEX_RELIGION_HOS_REGISTRATION_REPORT="AgeDateReligionHosRegistration.jrxml";
	String AGE_SEX_ALLRELIGION_HOS_REGISTRATION_REPORT="AgeDateAllReligionHosRegistration.jrxml";
	String ALL_AGE_SEX_RELIGION_HOS_REGISTRATION_REPORT="AllAgeDateReligionHosRegistration.jrxml";
	String All_AGE_SEX_ALLRELIGION_HOS_REGISTRATION_REPORT="AllAgeDateAllReligionHosRegistration.jrxml";
	String AGE_SEX_RELIGION_DEPT_REGISTRATION_REPORT="AgeSexReligionDeptReg.jrxml";
	String AGE_SEX_ALLRELIGION_DEPT_REGISTRATION_REPORT="AgeSexAllReligionDeptReg.jrxml";
	String ALL_AGE_SEX_RELIGION_DEPT_REGISTRATION_REPORT="AllAgeSexReligionDeptReg.jrxml";
	String ALL_AGE_SEX_ALL_RELIGION_DEPT_REGISTRATION_REPORT="AllAgeSexAllReligionDeptReg.jrxml";
	
	//MLC Report
	String MLC_AGE_SEX_RELIGION_HOS_REGISTRATION_REPORT="MlcAgeDateReligionHosRegistration.jrxml";
	String MLC_AGE_SEX_ALLRELIGION_HOS_REGISTRATION_REPORT="MlcAgeDateAllReligionHosRegistration.jrxml";
	String MLC_ALL_AGE_SEX_RELIGION_HOS_REGISTRATION_REPORT="MlcAllAgeDateReligionHosRegistration.jrxml";
	String MLC_All_AGE_SEX_ALLRELIGION_HOS_REGISTRATION_REPORT="MlcAllAgeDateAllReligionHosRegistration.jrxml";
	String MLC_AGE_SEX_RELIGION_DEPT_REGISTRATION_REPORT="MlcAgeSexReligionDeptReg.jrxml";
	String MLC_AGE_SEX_ALLRELIGION_DEPT_REGISTRATION_REPORT="MlcAgeSexAllReligionDeptReg.jrxml";
	String MLC_ALL_AGE_SEX_RELIGION_DEPT_REGISTRATION_REPORT="MlcAllAgeSexReligionDeptReg.jrxml";
	String MLC_ALL_AGE_SEX_ALL_RELIGION_DEPT_REGISTRATION_REPORT="MlcAllAgeSexAllReligionDeptReg.jrxml";
	
	
	String SPECIALITY_WISE_DIST_INDOOR_PATIENTS_UNITWISE="SpecialitWiseIndoorPatient.jrxml";
	String BIRTH_DETAILS_REPORT="BirthDetailsOnDate.jrxml";
	String DEATH_DETAILS_REPORT="DeathDeatilsReport.jrxml";
	String DISEASE_CODE_REPORT="DiseaseCodeReport.jrxml";
	String FORM_P_REPORT="FormPReport.jrxml";
	String REGISTRATION_CENSUS_REPORT="RegistrationCensusReport.jrxml";
	String REGISTRATION_PATIENT_LISTING_REPORT="RegistrationPatientListingReport.jrxml";
	String OPD_PAT_STATISTICS_REG_CAT_WISE_TODAY="OpdStatisticsRegCatReportToday.jrxml";
	String OPD_PAT_STATISTICS_PAT_CAT_WISE_TODAY="OpdStatisticsPatCatReportToday.jrxml";
	String OPD_PAT_STATISTICS_REG_CAT_WISE_DATEWISE="OpdStatisticsRegCatReportDateWise.jrxml";
	String OPD_PAT_STATISTICS_PAT_CAT_WISE_DATEWISE="OpdStatisticsPatCatReportDateWise.jrxml";
	String FLUOROSIS_ERADICATION_PROGRAMME_REPORT="FluorosisEradicationProgrammeReport.jrxml";
	String LIC_REPORT="LICReport.jrxml";
	String PATIENT_NOT_DISCHARGED_REPORT="PatientNotDischarged.jrxml";
	String TWENTY_POINT_REPORT="TwentyPointsReport.jrxml";
	String SPECIALITY_WISE__ALLUNITS_SUNDAYCLINIC_REPORT="SpecialitySundayClinicPatientsbetweenAllUnits.jrxml";
	//String COMMUNICABLE_DISEASE_REPORT="CommunicabbleDisease.jrxml";
	String COMMUNICABLE_DISEASE_REPORT="CommunicableDisease.jrxml";
	String NON_COMMUNICABLE_DISEASE_REPORT="NonCommunicabbleDisease.jrxml";
	String INDOOR_PATIENTS_BETWEEN_TWO_DATES="IndoorPatientsBetweenTwoDates.jrxml";
	String SEX_RATIO_REPORT="SexRatioReport.jrxml";
	String PEDIATRICS_IMMUNIZATIONS_REPORT="PediatricsImmunizationsReport.jrxml";
	String DELIVERIES_AND_CUT_INSERTION_REPORT="DeliveriesAndCUTInsertionReport.jrxml";
	String ESSENTIAL_DISEASE_TYPE="lstDisType";





	
	String LIST_LOST_RECORD_REPORTED_BY_EMP="listLostRecordReportedByEmp";
	
	//Mrd Record Movement (Rack Shelf Change) Detail
	String FROM_RACK_LIST_BASED_ON_MRD="fromRackListBasedOnMrd";
	String FROM_SHELF_LIST_BASED_ON_RACK="fromShelfListBasedOnRack";
	String TO_RACK_LIST_BASED_ON_MRD="toRackListBasedOnMrd";
	String TO_SHELF_LIST_BASED_ON_RACK="toShelfListBasedOnRack";
	String ARR_MRD_RECORD_TO_BE_MOVED="arrMrdRecordToBeMoved";
	
	////HPMRT_MRD_RACKSHELF_CHANGE_DTL Entry Mode Flag////
	String MRD_RACKSHELF_CHANGE_ENTRY_MODE_BY_MRD="0";
	String MRD_RACKSHELF_CHANGE_ENTRY_MODE_BY_RETURN="1";
	
	///Record Selection Flag/////
	String RECORD_SELECTION_ALL="0";
	String RECORD_SELECTION_SELECTED="1";
	
	////Operation Selection flag/////
	
	String MOVEMENT_SELECTED="1";
	String DESTROY_SELECTED="0";
	
	///MRD Record Found///////////
	String ARR_MRD_LOST_RECORD_LIST_VO="arrMrdLostRecordListVO";
	String STRING_RECORD_PREVIOUS_LOCATION="stringRecordPreviousLocation";
	
	
	
	//mrd master
	String ESSENTIAL_ALL_DEPARTMENT_LIST="allDeptList";
	String ESSENTIAL_ALL_EMP_BASED_ON_DEPT="allEmpBasedOnDept";
	
	String MRD_TYPE_MAIN_MRD="1";
	String MRD_TYPE_SUB_MRD="2";
	
	String MRD_MST_VO="mrdMasterVO";
	String MAIN_MRD_FLAG_EXISTENCE_CHECK="mainMrdFlagExistenceCheck";
	
	String ESSENTIAL_MRD_LIST_OPTION="mrdListOption";
	String RACK_SHELF_MST_VO="rackShelfMstVO";



	///Bounding Record Type/////////
	String RECORD_TYPE_FOR_BOUNDING_LIST="recordTypeForBoundingList";
	String ALL_RACK_LIST_BASED_ON_MRD_CODE="allRackListBasedOnMrd";
	String ALL_SHELF_LIST_BASED_ON_RACK="allShelfListBasedOnRack";
	
	////Enclosure Mapping/////
	String RECORD_TYPE_NAME="recordTypeName";
	String ARR_ADDED_RECORD_TYPE_ENCLOSURE_VO="arrAddedRecordTypeEnclosureVO";
	String LIST_NOT_MAPPED_ENCLOSURE="listNotMappedEnclosure";
	String LIST_NEW_ADDEDD_ENCLOSURE_VO="listNewAddedEnclosureVO";
	
	
	String ALL_RECORD_TYPE_LIST_FOR_MRD="allRecordTypeListForMrd";
	String REQUEST_PURPOSE_MST_VO_FOR_MODIFY="requestPurposeMstVOForModify";
	String MRD_CHECK_LIST_MST_VO_FOR_MODIFY="mrdCheckListVOForModify";
	String ENCLOSURE_MST_VO_FOR_MODIFY="enclosureMstVOForModify";
	String ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE="allAddeCheckListListByRecordType";
	
	String LIST_NOT_MAPPED_CHECK_LIST="listNotMappedCheckList";
	String LIST_NEW_ADDEDD_CHECKLIST_VO="newAddedCheckListVO";
	String ALL_CHECKLIST_LIST="allCheckListList";
	
	String INSURANCE_DETAIL_VO_LIST_FOR_SEARCH="insuranceDtlVOListForSearch";
	String INSURANCE_DETAIL_VO_FOR_DETAIL="insuranceDetailVoForDetail";

	String RECORD_REQUEST_ONLINE="1";
	String RECORD_REQUEST_OFFLINE="2";

	String PATIENT_PROFILE_VO_LIST="patientProfileVOList";
	String PATIENT_PROFILE_VO_ARRAY="patientProfileVOArray";
	String PATIENT_PROFILE_HTML_STRING="patientProfileHtmlString";
	String BURN_FILE_DIRECTORY_WINDOWS="C:\\AHIMS\\cdburnFile";
	String BURN_FILE_DIRECTORY_LINUX="\\AHIMS\\cdburnFile";
	
	///OPD File Tracking Desk Mode
	String OPD_FILE_MOVEMENT="MOVEMENT";
	String OPD_FILE_RETURN="RETURN";
	String LIST_ALL_OPD_FILE_MRD_USER_BASED="listOfOPDFileMrdUserBased";
	
	String EPR_ACCESS_NOT_ADDED_USERID_LIST="eprAccessNotAddedUserIdList";
	String EPR_ACCESS_ADDED_USERID_LIST="eprAccessAddedUserIdList";
	String EPR_ACCESS_ALL_USERID_LIST="eprAccessAllUserIdList";
	String EPR_ACCESS_TABID_USERID_MAP="eprAccessTabIdUserIdMap";
	String EPR_TAB_ACCESS_USER_WISE_LIST="eprTabAccessUserWiseList";

	String ARRAY_MRD_RECORD_VOLIST_OPD_FILES_FOR_MOVEMENT="arrayMrdRecordVOListOfOpdFilesForMovement";
	String ARRAY_MRD_RECORD_VOLIST_OPD_FILES_FOR_RETURN="arrayMrdRecordVoListOfOPDDilesForReturn";
	String LIST_RECORD_HANDOVER_TO_FROM_MRD="listRecordhandoverToFromMrd";
	String LIST_RECORD_HANDOVER_BY_IN_MRD="listRecordhandoverToInMrd";
	
	////ISSUE Flag MRD Record Detail
	String MRD_RECORD_ISSUE_FLAG_IN_MRD="0";
	String MRD_RECORD_ISSUE_FLAG_ON_REQUEST="1";
	String MRD_RECORD_ISSUE_FLAG_MOVEMENT="2";
	String MRD_CODE_FOR_TRACKING="mrdCodeForTracking";
	String MRD_NAME_FOR_TRACKING="mrdNameForTracking";
	
	////Movement Type Flag
	String MOVEMENT_TYPE_ISSED_FROM_MRD="1";
	String MOVEMENT_TYPE_RETURN_TO_MRD="2";

	//EPR Restricted Category Master
	String EPR_ADDED_PAT_CAT_LIST="eprAddedPatCatList";
	String EPR_NOT_ADDED_PAT_CAT_LIST="eprNotAddedPatCatList";
	
	String ESSENTIAL_OPTION_DESIGNATION="essentialOptionDesignation";
	String EMPLOYEE_ENQUIRY_VO="employeeEnquiryVO";
	
	// Pediatric Immunization Report
	String MRD_REPORT_DATA = "mrdReportData";
	
	//Patient EMR Audit
	String PATIENT_EMR_AUDIT_TYPE_LIST="patientEmrAuditTypeList";
	String PATIENT_EMR_AUDIT_AUDIT_DIAGNOSIS_BY_CRNO_LIST = "patientEmrAuditDiagnosisByCrNoList";
	String PATIENT_EMR_AUDIT_AUDIT_DIAGNOSIS_DIAL_TILE_BY_PRIMARY_KEY_LIST = "patientEmrAuditDiagnosisDialTileByPrimaryKeyList";
	String PATIENT_EMR_AUDIT_PREVIOUS_DTL_BY_PRIMARY_KEY_LIST = "patientEmrAuditPreviousDtlByPrimaryKeyList";
	String PATIENT_EMR_AUDIT_USER_LIST="patientEmrAuditUserList";
	
	String PROCEDURE_GET_AUDIT_TYPE_LIST 	= "{call Pkg_EMR_Dtl.getPatientEmrAuditType(?,?,?)}";
	String PROCEDURE_GET_AUDIT_USER_LIST 	= "{call Pkg_EMR_Dtl.getPatientEmrAuditUser(?,?,?, ?,?,?)}";
	String PROCEDURE_GET_AUDIT_PATIENT_LIST = "{call Pkg_EMR_Dtl.getPatientEmrAudit(?,?,?, ?,?,?, ?,?,?)}";
	String PROCEDURE_GET_AUDIT_PATIENT_DIGNOSIS_DIAL_BY_PRIMARY_KEY_LIST = "{call Pkg_EMR_Dtl.getPatEmrAuditDiagByPrimaryKey(?,?,?, ?,?,?, ?,?,?, ?)}";
	String PROCEDURE_GET_PATIENT_EMR_PREVIOUS_AUDIT_DTL_LIST = "{call Pkg_EMR_Dtl.getPrevPatEmrAuditByPrimaryKey(?,?,?, ?,?,?)}";
	String PROCEDURE_SAVE_PATIENT_EMR_AUDIT_DTL = "{call pkg_emr_dtl.dml_hpmrt_emr_audit_dtl(?,?,?, ?,?,?, ?,?,?, ?,?,?, ?)}";
	
	// Patient Category
	String MRD_PATIENT_CATEGORY_LIST = "mrdPatCatList";
	
	String sessionOptionUserLaboratoryList="sessionOptionUserLaboratoryList";
	
	String VIEW_REGISTRATION_CATEGORY = "R";
	String VIEW_PATIENT_CATEGORY = "P";

	///Postmortem Status
	String POSTMORTEM_STATUS_REQUEST_RAISED="1";
	String POSTMORTEM_STATUS_REQUEST_APPROVED="2";
	String POSTMORTEM_STATUS_REQUEST_CANCELED="3";
	String POSTMORTEM_STATUS_REQUEST_WAVEOFF="4";
	String POSTMORTEM_STATUS_REQUEST_POSTMORTEM_ENTRY="5";
	String POSTMORTEM_STATUS_REQUEST_POSTMORTEM_DONE="6";



	///////////Medical Camp///////////STARTED
	
	String MRD_MEDICAL_CAMP_VO_LIST="mrdMedicalCampVOList";
	String  MRD_MEDICAL_CAMP_EMP_VO_LIST="mrdMedicalCampEmpVOList";
    String BLOODBANK_DEPT_CODE="151";
    //Camp Resource
    String QUERY_FILE_FOR_MEDICAL_CAMP_TRANSACTIONDAO = "bloodbank.transactions.bloodBankTransactionQuery";
    String PROC_MED_CAMP_EMP_DETAIL_HPMRT_MED_CAMP_TEAM_DTL=  "{call PKG_MRD_DTL.proc_hpmrt_med_camp_team_dtl(?,?,?,?,?,?,?,?,?,?)}";
    String MRD_MEDICAL_CAMP_DATEWISE="medicalCampConductedBetweenTwoDatesReport.jrxml";
	///////////Medical Camp///////////ENDED
    

    String MEDICAL_CERTIFICATE_REQUEST_ADVISEDBY_DETAIL="medicalCertificateRequestAdvisedByDetail";
	String PROC_GET_EPISODE_DIAGNOSIS_DTL = "PKG_CLINICAL_GBL_FUN.GET_PAT_DIAGNOSIS_NAMES";
    String SELECTED_PATIENT_DIAGNOSIS_DTL_VO="selectedPatientDiagnosisDtlVo";

    //Estimate Certificate Request //
    String ESTIMATE_PROCEDURE_TARIFF_DETAIL_LIST="estimateProceduretariffDtl";
    String TARIFF_LIST_ESTIMATE_CERTIFICATE_REQUEST="tariffListEstimateCertificateRequest";
	String HBLNUM_IS_ESTIMATION_STATUS="1";
	//GENMODE USER FOR DISTINGUISH BETWEEN AUTOMATIC AND MANUAL REQUEST
	String ESTIMATE_REQUEST_GENMODE_AUTOMATIC="1";
	String MEDICAL_CERIFICATE_REQUEST_GENMODE_AUTOMATIC="1";
	//Estimate certificate request	
	String PROCEDURE_ESTIMATE_CERTIFICATE_REQUEST="PKG_MRD_DML.proc_pat_estimatedtl"; 
	String PROCEDURE_ESTIMATE_TARIFF_DTL="PKG_MRD_DML.proc_hpmrt_pat_estimate_tariff_dtl"; 
	
	//Estimate certificate Request status
	String ESTIMATE_CERTIFICATE_REQUEST_STATUS="1";
	//Procedure for previous Estimate Certificate Req
	String PROCEDURE_GET_PREV_REQUEST_DTL="pkg_mrd_view.proc_hpmrt_pat_estimate_dtl";
	//Previous Estimate Request 
	String PREVIOUS_ESTIMATE_CERTIFIATE_DTL="previousEstimateCertificateDtl";
	//Procedure for Certificate issue process to get certificate Request details 
	String PROCEDURE_GET_CERTIFICATE_REQUEST_DTL="pkg_mrd_view.proc_certificateIssue_hpmrt_pat_estimate_dtl";
	String PROCEDURE_GET_CERTIFICATE_ISSUE_PAT_DTL="pkg_mrd_view.proc_hrgt_episode_dtl";
	//Certificate Issue Certificate Request  Patien details
	String ESTIMATE_CERTIFIATE_REQUEST_STATUS_DTL="EstimateCertificateRequestDtl";
	String ALL_REQUESTED_TARIFFS_LIST="TariffsEstimateCertificateRequest";
	String ESTIMATE_CERTIFIATE_ISSUE_PAT_DTL="EstimateCertificateIssuePatDtl";
	//Estimate certificate issue details update	
	String PROCEDURE_ESTIMATE_CERTIFICATE_ISSUE_DTL_UPDATE="PKG_MRD_DML.proc_hpmrt_pat_estimate_dtl";
	String ESTIMATE_CERTIFICATE_ISSUE_PAT_DETAIL="estimateCertificateIssuePatDtl";
	String RECORD_STATUS_IN_DEPARTMENTUNIT_WARD="1";
	String  ESTIMATE_REQUEST_ADVISEDBY_DETAIL="estimateReqAdvisedByDtl";
	String ESTIMATE_CERTIFIATE_GENERATION_DTL="estimateCertificategenerationDtl";
	String ESTIMATE_CERTIFIATE_DIAGNOSIS_DTL="estimateCertificatediagnosisDtl";
	//Medical certificate Request 
	String PREVIOUS_MEDICAL_CERTIFIATE_DTL="previousMedicalCertificateDtl";
	String PROCEDURE_GET_PREV_MEDICAL_REQUEST_DTL= "{call pkg_mrd_view.proc_hpmrt_pat_medical_dtl(?,?,?,?,?,?,?,?)}";	
	String PROCEDURE_MEDICAL_CERTIFICATE_REQUEST="PKG_MRD_DML.proc_hpmrt_pat_medical_dtl";
	String PROCEDURE_FITNESS_CERTIFICATE_REQUEST="PKG_MRD_DML.proc_hpmrt_pat_fitness_dtl";
	String MEDICAL_CERTIFICATE_PAT_REQ_DTL="medicalCertificatepatReqList";
	String MEDICAL_CERTIFICATE_ONLINE_BILL_DTL="bill_interface.func_online_bill_dtl";
	String ENTRY_MODE_WITH_CR_NO="1";
	String ENTRY_MODE_WITHOUT_CR_NO="2";
	
	String CERTIFICATE_REQ_BY_PATIENT="0";
	String CERTIFICATE_REQ_BY_RELATIVE="1";
	String ESSENTIAL_ALL_MRD_EMP_LIST="allEmpList";
	String DUPLICATE_RECORD_PRINTING_AND_HANDOVER="duplicateRecordPrintingaAndHandover";
	String REQUESTED="1";
	String HANDED_OVER="2";
	String PROCEDURE_DUPLICATE_RECORD_REQUEST="pkg_mrd_dml.proc_hpmrt_duplicate_printing_dtl";
	String PROCEDURE_DUPLICATE_RECORD_REQUEST_HANDOVER="pkg_mrd_dml.proc_update_hpmrt_duplicate_printing_dtl";
	
	
	String TARRIF_ID_MEDICAL="1060006";
	String TARRIF_ID_FITNESS="1060004";
	String TARRIF_ID_GENERAL_CASESHEET="1060005";
	String TARRIF_ID_MLC_CASESHEET="1060007";
	String TARRIF_ID_DEATH_NOTIFICATION="1060002";
	String TARRIF_ID_BIRTH_NOTIFICATION="1060001";
	String TARRIF_ID_OPD_FILE="1060008";
	String TARRIF_ID_ESTIMATE_CERTIFICATE="1060003";
	
    //added by Manisha Gangwar on 12.Oct.2015   
	//Certificate B Entry Form
	String PROCEDURE_CERTIFICATE_B_ENTRY_FORM_REQUEST = "{CALL pkg_mrd_dml.proc_hpmrt_certificate_b_entry_form(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	String PROCEDURE_CERTIFICATE_B_ENTRY_FORM_PREV_REQUEST="{call pkg_mrd_view.proc_hpmrt_certificate_b_entry_form(?,?,?,?,?}";
	String CERTIFICATE_B_ENTRY_FORM_REQUEST="certificateBEntryFormRequest";
	
	 //added by Manisha Gangwar on 05.NOV.2015   
	// Procedure Unit Mapping 
		String MAPPED_UNIT_PROCEDURE_LIST = "ProceudretMappedWithUnit";
		String ALL_UNIT_LIST="allUnitList";
		String ALL_CLINICAL_DEPARTMENT_LIST="allClinicalDepartmentMaster";
		String ALL_UNIT_LIST_NOT_MAPPED_WITH_PROCEDURE = "unitNotMappedWithChart";
		String PROCEDURE_NAME_LIST = "chartNameList";
		String CURRENT_VISIT = "1";
		String COMPLETE_EPISODE = "2";
		String NONE="0";
		String MAPPED_UNIT_PROCEDURE_LIST_VO_ARRAY = "mappedUnitChartListVoArray";
		String LIST_ALL_PROCEDURE_NOT_IN_SELECTED_BASED_ON_UNIT = "allProcedureNotSelectedBasedOnUnit";
	
		
	//added by Neha Rajgariya Date:24March2017
		String SELECTED_CERTIFICATE_PATIENT_DETAILS = "selectedpatientcertificatedetails";

		String PAT_MEDICAL_VO_MEDICAL_CERTIFICATE = "patmedicalvomedicalcertificate";

		String MRD_RECORD_VO_LIST = "mrdRecordVOList";
		
		
		
		//added by Manisha Gangwar Date:25July2017
				String MRD_RECORD_DTL_IS_SCANNED_YES = "1";
				String MRD_RECORD_DTL_IS_SCANNED_NO ="0";
				
				//Added by Vasu on 06.March.2019
				String PREV_ICD_CODES = "prevIcdCodes";
				
				String ICD_RECORD_STATUS_SAVED = "1";
				String ICD_RECORD_STATUS_NOT_SAVED = "2";
				
				//Added By Shweta on 09.May.2019
				String PATIENT_UPLOADED_DOC_VO_ARRAY="patientuploadeddocVOArray";
				String PAT_EXT_TREATMENT_DETAILS_VO_ARRAY="patExtTreatmentDetailsVOArray";
				
				String GET_BULLETINES= "{call pkg_bulletin_generation.proc_bulletin_type_list(?,?,?,?,?)}";

				String BULLETIN_TYPE = "Bulletin";
				 String BULLETIN_TEMP_LIST = "bulletintemplatelist";
				 String PDF_FOR_BULLETIN = "bulletindataforpdf";


}

