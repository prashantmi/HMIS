package inpatient;

//InpatientConfig is an interface that defines hard-coded values 
//that are used for development of BO and DAO.

public interface InpatientConfig
{
	
	/************* Configuration Parameters *****************/
		// IPD Desk List
	String ROOM_ALL_CODE = "0";
	String ROOM_SELECTION_YES ="1";
	String ROOM_SELECTION_NO = "0";
	String IPD_NURSING_DESK_ROOM_SELECTION = ROOM_SELECTION_NO;
	String IPD_NURSING_STATION_ROOM_SELECTION = ROOM_SELECTION_NO;
	String IPD_DOCTOR_DESK_ROOM_SELECTION = ROOM_SELECTION_NO;	
	
		// IPD Desk Unit Wise or Ward Wise
	String UNIT_ALL_CODE = "0";
	String UNIT_SELECTION_YES ="1";
	String UNIT_SELECTION_NO = "0";
	String IPD_NURSING_DESK_UNIT_SELECTION = UNIT_SELECTION_NO;
	String IPD_NURSING_STATION_UNIT_SELECTION = UNIT_SELECTION_YES;
	String IPD_DOCTOR_DESK_UNIT_SELECTION = UNIT_SELECTION_YES;	
	
	// IPD Desk MLC Color Codification
	String MLC_COLOR_CODIFICATION_YES ="1";
	String MLC_COLOR_CODIFICATION_NO = "0";
	String IPD_DESK_MLC_COLOR_CODIFICATION = "#FFCCCC ";//	#B8860B	#F4A460 	#A52A2A 	#8B4513
	String IPD_NURSING_DESK_MLC_COLOR_CODIFICATION = MLC_COLOR_CODIFICATION_YES;
	String IPD_DOCTOR_DESK_MLC_COLOR_CODIFICATION = MLC_COLOR_CODIFICATION_YES;	

	
		// Drug Admisitration Stock Configuration
	/* Configuration Settings Changed by Pragya 2015.12.02 as per New Requiremnt for Drug Stock*/
	String SOURCE_OF_DRUG_PATIENT_STOCK_YES="1";
	String SOURCE_OF_DRUG_PATIENT_STOCK_NO="0";
	String SOURCE_OF_DRUG_PATIENT_STOCK=SOURCE_OF_DRUG_PATIENT_STOCK_YES;
	
	String SOURCE_OF_DRUG_STORE_STOCK_WARD="1";
	String SOURCE_OF_DRUG_STORE_STOCK_PATIENT="2";
	String SOURCE_OF_DRUG_STORE_STOCK=SOURCE_OF_DRUG_STORE_STOCK_PATIENT;

	String SOURCE_OF_DRUG_IS_STOCK="1"; // Old
	String SOURCE_OF_DRUG_IS_PATIENT="2"; // OLD
	String SOURCE_OF_DRUG=SOURCE_OF_DRUG_IS_STOCK; // OLD
	
	String SOURCE_OF_DRUG_PATIENT_FROM_STOCK="1";
	String SOURCE_OF_DRUG_PATIENT_DIRECT_FROM_PATIENT="2";
	String SOURCE_OF_DRUG_PATIENT=SOURCE_OF_DRUG_PATIENT_DIRECT_FROM_PATIENT;
	
	
	String SOURCE_OF_DRUG_PATIENT_FROM_STOCK_DEFAULT_STORE_ID="10101100";
	
		// Colour codification for drug administration 
	String IPD_COLOR_CODE_COSENT = "#CCFFFF";	
	String IPD_COLOR_CODE_ALLERGY = "#FF0000";	
	String IPD_COLOR_CODE_REACTION = "#00BFFF";	
	String IPD_COLOR_CODE_TIME = "#FFFFCC";		
		// "#0000CD" - Medium Blue	// "#0000FF" - Blue	// "#7B68EE" - Medium Slate Blue	// "#00BFFF" - Deep Sky Blue
		// "#87CEEB" - Sky Blue	// "#FF0000"- Red	// "#FFFFFF"- White	// "#FFC0CB"- Pink	// "#D3D3D3"- Light Gray	//"#408080"-Grass Green

		// Drug Allergy
	String DRUG_ALLERGY_TYPE_CODE="11";

		// Time Limits in Hours
	String TIME_LIMIT_FOR_DRUG="2";
	String BEFORE_TIME_LIMIT_FOR_DRUG="2";
	String TIME_LIMIT_FOR_EXTERNAL_TREATMENT="2";
	String BEFORE_TIME_LIMIT_FOR_EXTERNAL_TREATMENT="2";
	
		// Progress Notes Verification By Doctor 
	String NOTES_VERIFICATION_REQUIRED="0";
	String NOTES_VERIFICATION_VERIFIED="1";

		// Doctor Ward Round Detail
	String DOCTOR_WARD_ROUND_DETAIL_ONCALL_REQUIRED_YES="1";
	String DOCTOR_WARD_ROUND_DETAIL_ONCALL_REQUIRED_NO="2";
	String DOCTOR_WARD_ROUND_DETAIL_ONCALL_REQUIRED=DOCTOR_WARD_ROUND_DETAIL_ONCALL_REQUIRED_YES;

		// Delivery Place - Hospital Id
	String DELIVERY_PLACE_ID_HOSPITAL = "11";

		// Blood Transfusion
	String BLOOD_TRANSFUSION_REACTION_START_DATE_LIMIT="2";
	 String INTAKE_OUT_PARANAME_FOR_BLOOD_TRANSFUSION="Blood";//this value comes from HIPD_INTAKEOUT_PARA_MST
	 String INTAKE_OUT_PARAID_FOR_BLOOD_TRANSFUSION="16";//this value comes from HIPD_INTAKEOUT_PARA_MST
	 String ROUTE_ID_FOR_BLOOD_TRANSFUSION="14"; //this value comes from HIPD_INTAKEOUT_ROUTE_MST
	
		// JSY Registration
	String TREATMENT_TYPE_JSY="1";
	String ALL_DEPARTMENTS="0";
	String PROCEDURE_GET_PEON_LIST="ahis_util.proc_emplist_processwise";
	String PROCESS_TYPE_FOR_CALLBOOK_PEONLIST="7";

		// Pending Task List
	String DRUG_SCHEDULE="1";
	String INPATIENT_JRXML_PATH ="/inpatient/reports/jrxml/";
	String CALL_LOGBOOK_REPORT="CallLogNewReport.jrxml";
	String LIST_OF_CASESHEET_REPORT="CaseSheetReporInward.jrxml";
	String LIST_OF_LOSTCASESHEET_REPORT="CaseSheetLost.jrxml";
	String LIST_OF_CASESHEET_ALLSTATUS_REPORT="CaseSheetAllStatus.jrxml";
	String LIST_OF_CASESHEET_LOSTORFOUND_REPORT="CaseSheetLostOrFound.jrxml";
	String JSY_REG_REPORT="JSYREGReport.jrxml";
	String JSY_REG_REPORT_MONTHLY="MonthlyJSYREGReport.jrxml";
	String JSY_REG_REPORT_YEARLY="YearlyJSYREGReport.jrxml";
	String JSY_REG_REPORT_DATEWISE="DateWiseJSYREGReport.jrxml";
	
	String SPECIALITY_WISE_OPERATION_REPORT="DeptDateWiseOperation.jrxml";
	String SPECIFIC_SPECIALITY_WISE_OPERATION_REPORT="DeptDateWiseOperationSpecific.jrxml";
	
	String REGISTERED_MLC_PATIENTS="RegisteredMLCpatientsBetweenTwoDates.jrxml";
	String DISEASE_CODE_REPORT="DiseaseCodeReport.jrxml";
	String DEATH_DETAILS_REPORT="DeathDeatilsReport.jrxml";
	String ABSCONDING_PATIENT_REPORT="ListofAbscondingPatientReport.jrxml";
	String LIST_OF_NONMLC_PATIENT_REPORT_DATEWISE="ListOfNonMlcPatientReport.jrxml";
	String DAILY_INDOORE_REGISTER_FOR_WARD_REPORT="DailyIndooreRegisterForWardReport.jrxml";

    String ORDER_BY_NAME="1";
    String ORDER_BY_WARD_TYPE="2";
	
	
	
	/****************** Query File Name ******************/
	String QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO="inpatient.inpatientEssentialQuery";
	String QUERY_FILE_FOR_INPATIENT_DAO="inpatient.inpatientQuery";
	
	
	/****************** Nursing Desk ******************/
	String DESK_PATIENT_SEARCHED_LIST = "deskPatientSearchedList";
	String NURSING_DESK_DEPT_UNIT_LIST="nursingDeskDeptUnit";
	String NURSING_DESK_WARD_LIST_ON_BASIS_ROLE="nursingDeskWardOnBasisRole";
	String NURSING_DESK_WARD_LIST_ON_BASIS_UNIT="nursingDeskWardOnBasisUnit";
	String NURSING_DESK_ROOM_LIST_ON_BASIS_WARD="nursingDeskRoomOnBasisWard";
	String NURSING_DESK_SELECTED_WARD_CODE="selectedWardCode";
	
	String NURSING_DESK_LIST_MORE_THAN_ONE_YES="1";
	String NURSING_DESK_LIST_MORE_THAN_ONE_NO="0";
	
	String NURSING_DESK_UNIT_CODE="nursingDeskUnitCode";
	String NURSING_DESK_WARD_CODE="nursingDeskWardCode";
	String NURSING_DESK_ROOM_CODE="nursingDeskRoomCode";
	String SELECTED_DIAGNOSIS_TYPE_CODE_FOR_UNIT="selectedDiagnosisTypeCodeForUnit";
	String ARR_PAT_PREVIOUS_PRORESSNOTES="arrPreviousProgressNotes";
	
	String INPATIENT_ADMITTED_PATIENT_LIST_UNIT_WARD_WISE="inpatientAdmittedPatListUnitWardWise";
	

	/****************** Patient Admission Status HIPT_PATADMISSION_DTL ******************/
	String PATIENT_ADMISSION_STATUS_ON_TRANSIT_INSIDE_HOSPITAL="11"; 
	String PATIENT_ADMISSION_STATUS_ADMITTED="12";
	String PATIENT_ADMISSION_STATUS_NOT_REPORTED="13";
	String PATIENT_ADMISSION_STATUS_ADMISSION_CANCELLED="14";
	String PATIENT_ADMISSION_STATUS_ON_LEAVE="15";
	String PATIENT_ADMISSION_STATUS_DISCHARGE="16";
	String PATIENT_ADMISSION_STATUS_ON_TRANSIT_OUTSIDE_HOSPITAL="17";
	String PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL="18";
	
	String INPATIENT_ADMISSION_VO="inpatientAdmissionVO";
	String INPATIENT_DISCHARGE_ADMISSION_VO="inpatientDischargeAdmissionVO";
	
	String INPATIENT_IS_DEAD_YES="1";
	String INPATIENT_IS_DEAD_NO="0";

	
	/****************** For unitInvParaMAppingMaster ******************/
	String EssentialBO_LIST_ALL_UNITSFORUNITWISE = "essentialListallUnitsForUnitWise";
	String EssentialBO_LIST_ALL_UNITSFORWARDWISE = "essentialListallUnitsForWardWise";
	String EssentialBO_LIST_WARDS = "essentialListWards";
	String EssentialBO_LIST_ALL_PARAMETER = "listAllParameters";
	String EssentialBO_LIST_ALL_PARAMETERFORMODIFY = "listAllParametersForModify";
	String EssentialBO_LIST_ALL_PARAMETERFORWARDWISE = "listAllParametersForWardWise";
	String EssentialBO_LIST_PARAMETERSFORMODIFY = "listParametersForModfiy";
	String EssentialBO_LIST_PARAMETERSFORWARDWISE = "listParametersForWardWise";
	String EssentialBO_LIST_WARDS_FORSPECIFICUNITS = "essentialListWardsForSpecificUnits";
	String EssentialBO_LIST_WARDS_FORMODIFY = "essentialListWardsForModify";
	String Initial_Step = "0";
	String UNITWISE_MODE = "0";
	String UNITWARD_WISE = "1";
	String SELECTED_UNIT = "selectedUnit";
	String SELECTED_WARD = "selectedWard";
	String SELECTED_PARAMETER = "selected Parameter";
	String STEP0 = "0";
	String STEP1 = "1";

	
	///////Doctor Round/////////
	String ESSENTIAL_EMPLOYEE_LIST_UNIT_WISE="EssentialEmpListUnitWise";
	
	String ENTER_BY_NURSE="1";
	String ENTER_BY_DOCTOR="2";
	
	String ARR_DOCTOR_INSTRUCTION="arrDocInstruction";
	String ARR_DOCTOR_PREV_ROUND_DETAIL="arrDocPrevRoundDetail";
	String ARR_UNVERIFIED_RECORD_ENTERBY_NURSE="arrUnverifiedRecEnterByNurse";

	
	//////////OUT TAKE//////////
	String ESSENTIAL_OUT_PARAMETER_LIST="essOutParaList";
	String ESSENTIAL_OUT_ROUTE_LIST="essOutRouteList";
	
	
	/////////INTAKE//////////////
	String ESSENTIAL_IN_PARAMETER_LIST="essInParaList";
	String ESSENTIAL_IN_ROUTE_LIST="essInRouteList";
	
	String INTAKEOUT_MODE_INTAKE="1";
	String INTAKEOUT_MODE_BOTH="2";
	String INTAKEOUT_MODE_OUTTAKE="3";
	

	/////// intake outtake entry mode/////////
	String INTAKEOUT_ENTRY_MODE_DRUG="0";
	String INTAKEOUT_ENTRY_MODE_INTAKE_OUTTAKE="1";
	String INTAKEOUT_ENTRY_MODE_BLOOD_TRANSEFUSION="2";
	
	String ARR_PATIENT_OUTTAKE_DETAIL="arrPatOuttakeDtl";
	String ARR_PAT_ADD_OUTTAKE_DETAIL="arrPatAddOuttakeDtl";
	String ARR_PAT_ADD_INTAKE_DETAIL="arrPatAddIntakeDtl";
	
	
	/////Intake Outtake Summary//////
	String PATIENT_INTAKE_SUMMARY="patientIntakeSummary";
	String PATIENT_OUTTAKE_SUMMARY="patientOuttakeSummary";
	
	
	/////Intake Outtake View Summary//////
	String PATIENT_INTAKE_VIEW_SUMMARY="patientIntakeViewSummary";
	String PATIENT_OUTTAKE_VIEW_SUMMARY="patientOuttakeViewSummary";
	
	
	////////// Intake Outtake Configurable//////////////
	String INTAKE_OUTTAKE_TIME_BASED_YES="1";
	String INTAKE_OUTTAKE_TIME_BASED_NO="0";
	String INTAKE_OUTTAKE_TIME_BASED=INTAKE_OUTTAKE_TIME_BASED_YES;
	
	
	//////// OPD-IPD Patient Tile////////////// 
	String DESK_OPD_TILE="0";
	String DESK_IPD_TILE="1";
	
	String INPATIENT_BULLETIN_DETAIL_VO="inpatientBulletinDetailVO";
	String INPATIENT_STATUS_LIST="inpatientStatusList";

	
	///////////////Bulletin Board//////////////////
	String BULLETIN_BOARD="BULLETINBOARD";
	String BULLETIN_DETAIL="BULLETINDETAIL";

	
	//////////////VITALS MONITOR//////////
	String TEMPLATE_PARAMETER_LIST="templateParameterList";
	String VITALS_VARIFFIED_FLAG_BY_DOCTOR="1";
	String VITALS_VARIFFIED_FLAG_BY_NURSE="0";
	String REMAINING_PARAMETER_LIST="remainingParameterList";
	String SELECTED_PARAMETER_LIST="selectedParameterList";
	String ARR_PAT_VITAL_MONITOR="arrPatVitalMonitor";
	String ARR_MONITOR_MODE_VO="arrMonitorModeVO";
	String ESSENTIALS_MAP_MONITORING_VITALS_ARR = "essentialMapMonitoringVitalsArray";
	String ESSENTIALS_MAP_MONITORING_VITALS_LAST_RECORD_VALUES = "essentialMapMonitoringVitalsLastValues";
	String ESSENTIALS_MAP_MONITORING_VITALS_LAST_RECORD_VALUES_MAP = "essentialMapMonitoringVitalsLastValueMap";
	String ESSENTIALS_MAP_MONITORING_VITALS_RECORD_DATE_LIST = "essentialMapMonitoringVitalsRecordDateList";
	String ESSENTIALS_MAP_MONITORING_VITALS_VALUES_MAP = "essentialMapMonitoringVitalsValuesMap";


	////////DISCHARGE REQUEST TYPE/////////
	String DISCHARGE_REQUEST_TYPE_REQUEST="1";
	String DISCHARGE_REQUEST_TYPE_REVOKE="2";
	
	
	//////////// Drug Administration ////////////
	String PREV_ALL_DRUG_DETAIL_LIST_OF_PATIENT="prevAllDrugDetailListOfPatient";
	String PREV_DRUG_SCHEDULE_OF_PATIENT="prevAllDrugScheduleOfPatient";
	String PAT_LAST_VISIT_DRUG_DETAIL_LST="patientLastVisitDrugDetailList";
	String ESSENTIALS_LIST_DOSAGE_FREQUECY="essentialsListDosageFrequency";
	String ESSENTIALS_LIST_ALL_DRUGS="allEssentialDrugList";
	String ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO="essentialListOfAllDrugRouteVO";
	//String LAST_DRUG_ROUTE_LST_BY_ITEMTYPE="lastDrugRouteLstByItemType";
	String LAST_DRUG_ADVICED_LST="lastDrugAdvicedLst";
	String SOS_PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_LIST="sosDrugTreatmentAdministrationDtlList";
	String PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_ARRAY="drugTreatmentAdministrationDtlArray";
	String ESSENTIALS__DOSAGE_FREQUECY_ARRAY="dosageFrequencyArray";
	String ESSENTIALS_DRUG_EXEC_LIST_BY_CRNO="drugExecutionListByCrNo";
	String ESSENTIALS_DRUG_EXEC_LIST_DRUGWISE="drugExecutionLstDrugWise";
	String PREV_DRUG_SCHEDULE_LIST_WITH_PARTICULAR_DRUG_NAME="DrugSchdForParticularDrugWithDrugName";
	String EXECUTED_DRUG_LST_BY_CRNO="executedDrugLstByCRNO";
	String SELECTED_IVFLUIDS_LIST="selectedIvfluidList";
	String MAX_ENTRY_DATE_FROMDRUGDETAIL_BY_CRNO="maxEntryDateByCrNoFromDrugDetail";
	String PREV_INSTRUNCTION_LIST_FOR_PAT="prevInstListForPat";
	String PREV_ONE_TIME_ACTIVITY_LIST_FOR_PAT="prevOneTimeActivityList";
	String TODAY_EXT_TREATMENT_LIST_FOR_PAT="todayExtTreatListForPat";
	String EXECUTED_PAT_EXT_TREAT_LIST="executedPatExtTreatmentList";
	String EXECUTED_PAT_ACTIVITY_LST="executedPatActivityLst";
	String ESSENTIALS_LIST_BATCHNO_FROM_STORE="essentialBatchNoList";
	String ESSENTIALS_LIST_BRANDLIST_FROM_GENERICTYPE="essentialBrandListFromGeneric";
	
	String TODAY_DRUG_DETAIL_LIST_FOR_PATIENT="todayDrugDtlLstForPatient";
	String SOS_DRUG_LIST_FOR_PATIENT="sosDrugListForPatient";
	
	String SELECTED_ACTIVITY_DETAIL_LIST="selectedActivityDetailList";
	
	String IS_IVFLUID_NO="0";
	
	
	//SOS FLAG
	String IS_SOS_IVFLUID="1";
	String IS_NOT_SOS_IVFLUID="0";

	// IS Empty Stomatch Flag
	String IS_EMPTY_STOMATCH_YES="1";
	String IS_EMPTY_STOMATCH_NO="0";
	
	String MORNING_SHIFT_ID="0";
	String NOON_SHIFT_ID="1";
	String EVENING_SHIFT_ID="2";
	String NIGHT_SHIFT_ID="3";
	
	String MORNING_SHIFT_SCHDULE_LST="morrningShiftSchduleLst";
	String NOON_SHIFT_SCHDULE_LST="noonShiftSchduleLst";
	String EVENING_SHIFT_SCHDULE_LST="eveShiftSchduleLst";
	String NIGHT_SHIFT_SCHDULE_LST="nightShiftSchduleLst";
	
	String DATEWISE_TREAT_INFO="dateWise";
	String DRUGWISE_TREAT_INFO="drugWise";
	
	String IS_DURATION_BOUND_YES="1";
	String IS_DURATION_BOUND_NO="0";
	
	String IN_PATIENT_BUTTETIN_DETAIL_VO_ARRAY="inPatientBulletinDetailVOArray";
	
	String IPD_DRUG_LIST_COLOR_CODIFICATION_INFO="ipdDrugListColourCodificationInfo";
	
	
	////////// MACRO MASTER ////////////
	String PROGRESS_NOTES_LIST="progressNotesList";
	String VISIT_NOTES_LIST="visitNotesList";

	
	/************** Drug Reaction ***************/
	String ESSENTIALS_ALL_DRUG_EXEC_LIST_BY_CRNO="allDrugExecutedListByCrNo";
	String ESSENTIALS_ADMINISTRATION_DATE_LST="allAdministrationDateList";
	String ESSENTIALS_DRUG_EXECUTION_LIST_BY_ADMINISTRATIONDATE="allExecDrugListByAdministrationDate";
	String MAX_ADMINISTRATION_DATE_BY_CRNO="maxAdministrationDateByCrNo";

	String TEMPLATEID_LIST_FOR_DRUG_REACTION="templateIdListForDrugReaction";
	String SELECTED_DRUG_ADMIN_DETAIL_VO="selectedDrugAdminDtlVO";
	String PREV_DRUG_REACTION_LIST_BY_CRNO="prevDrugReactionListByCrNo";
	String DRUG_REACTION_DTL_LIST_FOR_PARTICULAR_DRUG="drugReactionDtlForParticularDrug";
	
	//REACTION FLAG
	String IS_REACTION_NO="0";
	String IS_REACTION_YES="1";
	
	/************** Doctor Call Book *************/
	String CONSULTANT_LIST="consultantLst";
	String CONSULTANT_PHONE_LIST="consultantPhoneLst";
	String PEON_LIST="peonLst";
	String DOC_CALL_BY_PEON_YES="1";
	String DOC_CALL_BY_PHONE_YES="1";
	String DOC_CALL_BY_PEON_NO="0";
	String DOC_CALL_BY_PHONE_NO="0";
	String CALL_TYPE_AUTOMATIC="1";
	String CALL_TYPE_MANUAL="2";
	String CALL_COMMUNICATED_TO_DR="1";
	String DOCTOR_NOT_AVAILABLE="2";
	String DEPT_UNIT_LIST="deptUnitList";
	String CALL_BOOK_VO_ARRAY="callBookVoArray";
	String CALL_BOOK_VO="callBookVo";
	String CALL_INBOX_VO_ARRAY="callInboxVoArray";
	String PENDING_CALL_BOOK_VO_ARRAY="pendingCallBookVoArray";
	String AUTO_CALL_BOOK_VO_ARRAY="autoCallBookVoArray";
	String ACK_CALL_BOOK_VO_ARRAY="ackCallBookVoArray";
	String CALL_PRIORITY_LOW="1";
	String CALL_PRIORITY_MEDIUM="2";
	String CALL_PRIORITY_HIGH="3";
	String CALL_PRIORITY_NAME_LOW="Low";
	String CALL_PRIORITY_NAME_MEDIUM="Medium";
	String CALL_PRIORITY_NAME_HIGH="High";
	
	//Doctor Call Acknowledge
	String CALL_ACKNOWLEDGE_VO_ARRAY="callAcknowledgeVoArray";

	
	/************* Stock Entry Of Blood *********/
	String ALL_REQUESTED_COMPONENT_LIST_BY_CRNO="allRequestedComponentListByCrNoAndEpisodeCode";
	String ALL_ABO_LIST="allABOList";
	String ALL_RH_LIST="allRHList";
	String COMPONENT_LIST_FOR_COMBO="componentListForCombo";
	String REQUISITION_NO_COMBO_LIST="requisitionNoComboList";
	String INSTOCK_BLOODBAG_LIST_BYCRNO="inStockBloodBagListByCrNo";
	String BAG_CROSSMATCH_BUT_NOT_ISSUED="0";
	String CROSS_MATCH_LIST_BY_CRNO="crossMatchListByCrNo";
	String INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO="instockNotTransfusedBloodBagListForPatient";
	
	//requisition Type
	String HBNUM_REQ_TYPE_INTERNAL_FROM_OPD="1";
	String HBNUM_REQ_TYPE_INTERNAL_FROM_IPD="2";
	
	//Stock Status
	String IN_STOCK="1";
	String TRANSFUSED="2";
	String RETURN_TO_BLOOD_BANK="3";
	String CANCELED_OR_DISCARD="4";
	
	//Requisition Status ///
	String HBNUM_REQ_STATUS_BAG_ISSUES_COMPLETED="0";
	String HBNUM_REQ_STATUS_RAISED_BUT_NOT_ACCEPTED_IN_BLOOD_BANK="1";
	String HBNUM_REQ_STATUS_RAISED_AND_ACCEPTED="2";
	String HBNUM_REQ_STATUS_PARTIALY_ACCEPTED="3";
	String HBNUM_REQ_STATUS_RQUISITION_NOT_ACCEPTED_OR_DENIED="4";
	String HBNUM_REQ_STATUS_REQUISITION_CANCELLED_AFTER_ACCEPTANCE="5";
	String HBNUM_REQ_STATUS_CROSSMACHTED="6";
		
	//Source Flag
	String FROM_IN_HOUSE_BLOOD_BANK="1";
	String ARRANGE_BY_PATIENT_FROM_OTHER_BANK="2";

	
	/*********Doctor Ward Round Detail***************/
	String HIPD_DOCTOR_WARDROUND_DTL_ROUND_TYPE_ROUTINE="1";
	String HIPD_DOCTOR_WARDROUND_DTL_ROUND_TYPE_ONCALL="2";
	String HIPD_DOCTOR_WARDROUND_DTL_ROUND_TYPE_ROUTINE_LABEL="Routine";
	String HIPD_DOCTOR_WARDROUND_DTL_ROUND_TYPE_ONCALL_LABEL="OnCall";
	String HIPD_DOCTOR_WARDROUND_DTL_ROUND_NO_INITIALLY="1";
	String DOCTOR_WARD_ROUND_DETAIL_ONCALL_DETAIL_VOARRAY="doctorWardRoundDetailOnCallDetailVoArray";
	String DOCTOR_WARD_ROUND_DETAIL_ONCALL_DETAIL_FLAG_YES="1";
	String DOCTOR_WARD_ROUND_DETAIL_ONCALL_DETAIL_FLAG_NO="2";
	String DOCTOR_WARD_ROUND_DETAIL_ONCALL_LIST="doctorWardRoundDetailOnCallList";
	

	/*********************** ANC Detail *********************************/
	String ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST = "ancDetailEssentialBloodGroupList";
	String ANCDETAIL_ESSENTIAL_CASTE_LIST = "ancDetailEssentialCasteList";
	String ANCDETAIL_ESSENTIAL_OCCUPATION_LIST = "ancDetailEssentialOccupationList";
	String ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST = "ancDetailEssentialEducationStatusList";
	String ANCDETAIL_ESSENTIAL_MENSTRUAL_CYCLE_LIST = "ancDetailEssentialMenstrualCycleList";
	String ANCDETAIL_ESSENTIAL_DELIVERY_PLACE_LIST = "ancDetailEssentialDeliveryPlaceList";
	String ANCDETAIL_ESSENTIAL_BIRTH_TYPE_LIST = "ancDetailEssentialBirthTypeList";
	String ANCDETAIL_ESSENTIAL_GENDER_LIST = "ancDetailEssentialGenderList";
	String ANCDETAIL_ESSENTIAL_DELIVERY_TYPE_LIST = "ancDetailEssentialDeliveryTypeList";
	String ANCDETAIL_ESSENTIAL_MACROS_BY_PROCESS_ID_LIST = "ancDetailEssentialMacrosByProcessIdList";
	String ANCDETAIL_PATIENT_ANC_PREV_HISTORY_DETAIL_LIST = "ancDetailPatANCPreviousHistoryDetailList";
	String ANCDETAIL_PATIENT_ANC_HISTORY_DETAIL_LIST = "ancDetailPatANCHistoryDetailList";
	String ANCDETAIL_PATIENT_ANC_HISTORY_DELIVERY_DETAIL_LIST = "ancDetailPatANCHistoryDeliveryDetailList";
	
	String ANCDETAIL_PATIENT_DETAIL_FROM_HRGT = "ancDetailPatientDetailFromHRGT";
	String ANCDETAIL_PATIENT_ANC_DETAIL = "ancDetailPatientANCDetail";
	String ANCDETAIL_PATIENT_ANC_VISIT_DETAIL = "ancDetailPatientANCVisitDetail";
	String ANCDETAIL_PATIENT_ANC_DETAIL_FLAG = "ancDetailPatientANCDetailFlag";
	
	// Family Type
	String[] FAMILY_TYPE_ARR = {"","Nuclear","Joint"};
	String FAMILY_TYPE_NUCLEAR ="1";
	String FAMILY_TYPE_JOINT ="2";

	// Delivery Status
	String[] DELIVERY_STATUS_ARR = {"","Delivery","Abortion"};
	String DELIVERY_STATUS_DELIVERY = "1";
	String DELIVERY_STATUS_ABORTION = "2";

	// Birth Nature
	String[] BIRTH_NATURE_ARR =	{"","Live Birth", "Still Birth","Neonatal Death"};	
	String BIRTH_NATURE_LIVE_BIRTH ="1";
	String BIRTH_NATURE_STILL_BIRTH ="2";
	String BIRTH_NATURE_NEONATAL_DEATH ="3";
	
	// ANC History Entry mode
	String ANC_HISTORY_ENTRY_ONLINE = "1";
	String ANC_HISTORY_ENTRY_OFFLINE = "2";
	
	// ANC Pregnancy Detection Method
	String[] PREGNANCY_DETECTION_ARR = {"","UPT","Ultra Sound","Urine Test"};
	String PREGNANCY_DETECTION_METHOD_UPT = "1";
	String PREGNANCY_DETECTION_METHOD_ULTRA_SOUND = "2";
	String PREGNANCY_DETECTION_METHOD_URINE_TEST = "3";

	
	/*********************** ANC Delivery Detail *********************************/
	String ANCDETAIL_ESSENTIAL_LABOR_ROOM_LIST = "ancDetailEssentialLaborRoomList";
	String ANCDETAIL_ESSENTIAL_LABOR_ROOM_AREA_LIST = "ancDetailEssentialLaborRoomAreaList";
	String ANCDETAIL_ESSENTIAL_INDUCTION_METHOD_LIST = "ancDetailEssentialInductionMethodList";
	String ANCDETAIL_ESSENTIAL_PLACENTA_TYPE_LIST = "ancDetailEssentialPlacentaTypeList";
	String ANCDETAIL_ESSENTIAL_PLACENTA_REMOVAL_METHOD_LIST = "ancDetailEssentialPlacentaRemovalMethodList";
	String ANCDETAIL_ESSENTIAL_COMPLICATIONS_LIST = "ancDetailEssentialComplicationsList";
		
	// ANC Rupture Type
	String[] RUPTURE_TYPE_ARR = {"","Spontaneous","Artificial","Premature(PROM)"};
	String RUPTURE_TYPE_SPONTANEOUS = "1";
	String RUPTURE_TYPE_ARTIFICIAL = "2";
	String RUPTURE_TYPE_PROM = "3";

	// ANC Mother Status
	String[] MOTHER_STATUS_ARR = {"","Dead","Alive"};
	String MOTHER_STATUS_DEAD = "1";
	String MOTHER_STATUS_ALIVE = "2";
	
	// ANC Mother Status
	String[] MOTHER_DEATH_CAUSE_ARR = {"","Obstetric","Non-Obstetric"};
	String MOTHER_DEATH_CAUSE_OBSTETRIC = "1";
	String MOTHER_DEATH_CAUSE_NON_OBSTETRIC = "2";

	// Method Type
	String[] METHOD_TYPE_ARR = {"","Indunction Method","Placenta Removal Method"};
	String METHOD_TYPE_INDUCTION_METHODS ="1";
	String METHOD_TYPE_PLACENTA_REMOVAL_METHODS ="2";
	
	// Complications Type
	String[] COMPLICATION_TYPE_ARR = {"","ANC Complications","Delivery Complications","Abortion Complications","Post Delivery Complications","Trauma Causes"};
	String COMPLICATION_TYPE_ANC = "1";
	String COMPLICATION_TYPE_DELIVERY = "2";
	String COMPLICATION_TYPE_ABORTION = "3";
	String COMPLICATION_TYPE_POST_ANC = "4";
	String COMPLICATION_TYPE_TRAUMA_CAUSE = "5";
	
	// Labor Room Area Type
	String LABOR_ROOM_AREATYPE_WARD = "1";
	String LABOR_ROOM_AREATYPE_OT = "2";
	String LABOR_ROOM_AREATYPE_LABOR_RROM = "3";
	
	
	/*********************** ANC New Natal Detail *********************************/
	String ANCDETAIL_ESSENTIAL_BIRTH_TRAUMA_CAUSE_LIST = "ancDetailEssentialBirthTraumaCauseList";
	String ANCDETAIL_ESSENTIAL_APGAR_TIME_LIST = "ancDetailEssentialApgarTimeList";
	String ANCDETAIL_ESSENTIAL_ANOMOLY_TYPE_LIST = "ancDetailEssentialAnomolyTypeList";
	String ANCDETAIL_DELIVERY_OUTCOME_LIST = "ancDetailDeliveryOutcomeList";
	String ANCDETAIL_PATINET_ANC_DETAIL_VO = "ancDetailPatientANCDetailVO";
	String ANCDETAIL_PATINET_ANC_DELIVERY_DETAIL = "ancDetailPatientANCDeliveryDetailVO";
	String ANCDETAIL_DELIVERY_OUTCOME_APGAR_DETAIL_LIST = "ancDetailDeliveryOutcomeApgarDetailList";
	String ANCDETAIL_ESSENTIAL_SELECTED_NEO_NAT_APGAR_TIME_LIST = "ancDetailEssentialSelectedNeoNatApgarTimeList";
	String ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST = "ancDetailSelectedNeoNatApgarDetailList";
	String ANCDETAIL_SELECTED_NEONAT_ADDED_APGAR_DETAIL_LIST = "ancDetailSelectedNeoNatAddedApgarDetailList";
	
	// Umbilical Arteries
	String[] UMBILICAL_ARTERIES_ARR = {"","Single","Two"};
	String UMBILICAL_ARTERIES_SINGLE = "1";
	String UMBILICAL_ARTERIES_TWO = "2";
	
	// When Still Birth Detected
	String[] STILL_BIRTH_DETECTION_ARR = {"","During Delivery","After Delivery","Before Delivery"};
	String STILL_BIRTH_DETECTION_DURING_DELIVERY = "1";
	String STILL_BIRTH_DETECTION_AFTER_DELIVERY = "2";
	String STILL_BIRTH_DETECTION_BEFORE_DELIVERY = "3";
	
	// Transfer To Flag
	String[] BABY_TRANSFER_TO_ARR = {"","To Ward","To Nursery","To Mortuary"};
	String BABY_TRANSFER_TO_WARD = "1";
	String BABY_TRANSFER_TO_NURSERY = "2";
	String BABY_TRANSFER_TO_MORTUARY = "3";

	
	/******************** ANC Team Detail ***********************/
	String ANCDETAIL_ESSENTIAL_CONSULTANT_LIST_FOR_TEAM_DTL ="ancDetailEssentialConsultantListForTeamDtl";
	String ANCDETAIL_ESSENTIAL_ALL_ROLE_LIST = "ancDetailEssentialallRoleList";

	
	/*********************** ANC Child Handover Detail *********************************/
	String ANCDETAIL_ESSENTIAL_RELATIONSHIP_LIST ="ancDetailEssentialRelationshipList";
	String ANCDETAIL_ESSENTIAL_NEONAT_ADM_DETAIL ="ancDetailEssentialNeonatAdmDtl";
	String ANCDETAIL_ESSENTIAL_NEONAT_HANDOVER_DETAIL ="ancDetailEssentialNeonatHandoverDtl";
	
	
	/*********************** ANC Trimester Wise Checklist Detail *********************************/
	String ANCDETAIL_ESSENTIAL_CHECKLIST_ALL_TRIMESTER_LIST ="ancDetailEssentialChecklistAllTrimesterList";
	String ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST ="ancDetailEssentialTrimesterWiseCheckList";
	String ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS ="ancDetailEssentialTrimesterWiseCheckListDrugs";
	String ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS ="ancDetailEssentialTrimesterWiseCheckListInvestigations";
	String ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS_ADDED ="ancDetailEssentialTrimesterWiseCheckListDrugsAdded";
	String ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS_ADDED ="ancDetailEssentialTrimesterWiseCheckListInvestigationsAdded";

	String ANCDETAIL_ESSENTIAL_ADDED_DRUGS_CHECKLIST ="ancDetailEssentialAddedDrugsCheckList";
	String ANCDETAIL_ESSENTIAL_ADDED_INVESTIGATIONS_CHECKLIST ="ancDetailEssentialAddedInvestigationsCheckList";
	//String ANCDETAIL_ESSENTIAL_GIVEN_EXT_DRUGS_CHECKLIST ="ancDetailEssentialGivenExtDrugsCheckList";
	String ANCDETAIL_ESSENTIAL_GIVEN_INT_INVESTIGATIONS_CHECKLIST ="ancDetailEssentialGivenIntInvestigationsCheckList";
	
	// Trimester List
	String[] TRIMESTER_ARR = {"","Trimester 1", "Trimester 2", "Trimester 3"};
	String TRIMESTER_ONE ="1";
	String TRIMESTER_TWO ="2";
	String TRIMESTER_THREE ="3";
	String TRIMESTER_DURATION_IN_MONTHS ="3";

	// In Trimester Field Flag
	//String[] TRIMESTER_CHECKLIST_FLAG_ARR = {};
	String TRIMESTER_CHECKLIST_FLAG_NO = "0";
	String TRIMESTER_CHECKLIST_FLAG_YES = "1";
	String TRIMESTER_CHECKLIST_FLAG_MUST = "2";
	
	// Trimester Checklist type
	String TRIMESTER_CHECKLIST_TYPE_INVESTIGATION = "1";
	String TRIMESTER_CHECKLIST_TYPE_DRUG = "2";
	
		
	/******************BLOOD TRANSFUSION REACTION************/
	String BLOOD_TRANSFUSION_VO_LIST="bloodTransfusionVOList";
	String TEMPLATEID_LIST_FOR_TRANSFUSION_REACTION="templateIdListForTransfusionReaction";
	String BLOOD_TRANSFUSION_REACTION_ESSENTIAL_MACROS_BY_PROCESS_ID_LIST="transfusionReactionMacrosListByProceesId";
		
	// Area Category 
	String AREA_CATEGORY_URBAN = "0";
	String AREA_CATEGORY_SEMI_URBAN = "1";
	String AREA_CATEGORY_RURAL = "2";
	
	//in patient print label file name
	String INPATIENT_PRINT_LABEL_FILE_NAME="inPatientPrintLabel";
	String PRINT_LABEL_TYPE[]={"Case Sheet Label","Patient Tag","Print Case Sheet"};
	
	// jsy Registration //
	String PAT_ANC_DETAIL_FOR_JSY_REGISTRATION="PatAncDetailForJsyRegistration";
	String PAT_DETAIL_FOR_JSY_REGISTRATION="PatDetailForJsyRegistration";
	String JSY_RULE_DETAIL="JsyRuleDetail";
	
	String AREA_TYPE_URBAN_LABLE="Urban";
	String AREA_TYPE_URBAN_VALUE="0";
	String AREA_TYPE_RURAL_LABLE="Rural";
	String AREA_TYPE_RURAL_VALUE="2";
	String AREA_TYPE_SEMIURBAN_LABLE="Semi-Urban";
	String AREA_TYPE_SEMIURBAN_VALUE="1";
	
	String AREA_CATEGORY_LIST="AreaCategoryList";

	String CALL_PENDING="Pending";
	String CALL_ACKNOLWEDGED="Acknowledged";
	String CALL_AUTOMATIC="Automatic";
	String CALL_MANUAL="Manual";
	
	String JSY_FLAG_YES="1";
	String JSY_FLAG_NO="0";
	
	
	/********************Pending task******************/
	String PENDING_CONSENT_REQUEST_VO_LIST="pendingConsentVOList";
	String PENDING_CONSENT_PATIENT_LIST="pendingConsentPatientList";
	String SELECTED_PATIENT_CONSENT_VO_LIST="selectedPatientConsentVO";

	String SELECTED_PATIENT_TREATMENT_VO_LIST="selectedPatientTreatmentVO";
	String PENDING_TASKS[]={"Consent","Treatment","Sample Collection","Vital Monitoring"};
	//String PENDING_TASKS[]={"Consent","Treatment","Sample Collection","Vital Monitoring","Local Application","Instruction","OT Instruction"};
	String PENDING_TASK_LIST="pendingTaskList";
	String INPATIENT_DTL_VO_LIST="inpatientDtlVOList";
	String PENDING_TREATMENT_LIST="pendingTreatmentList";
	String PENDING_TASK_LIST_SIZE="pendingTaskListSize";
	String PENDING_SAMPLE_COLLECTION_LIST="pendingSampleCollectionList";
	String SELECTED_PATIENT_PENDING_SAMPLE_COLLECTION="selectedPatPendingSampleCol";
	String PENDING_INSTRUCTION_LIST="pendingInstructionList";
	String SELECTED_PATIENT_PENDING_INSTRUCTION="selectedPatPendingInstruction";

	String SYSADATEOBJECT="SYSDATEOBJECT";
	String WARD_LIST="listWard";

	String[] CASESHEET_STATUS_FOR_REPORT={"All","In Ward","Lost","Lost or Found"};
	String CASESHEET_STATUS_FOR_REPORT_LIST="statusLst";
	
	String PENDING_MONITORING_LIST="pendingMonitoringList";
	String SELECTED_PATIENT_PENDING_MONITORING="selectedPatPendingMonitoring";
	String HEALTHWORKER_DETAIL="HealthWorkerDetail";
	
	/***************Patient Medical Record*************/
	String PATIENT_CURRENT_ALLERGY_VO_ARR="patientCurrentAllergyVOArr";
	String PATIENT_CURRENT_CHRONIC_DISEASE_VO_ARR="patientCurrentChronicDiseaseVOArr";
	
	////Discharge Request List//////
	String PATIENT_DISCHARGE_REQUEST_LIST="patientDischargerequestList";
	
	String NEXT_VISIT_SELECTION_DATE="1";
	String NEXT_VISIT_SELECTION_DAYS="0";
	
	///Discharge Compulsory Flag
	String CHECK_DISCHARGE_SUMMARY_GENERATED_REQUIRED_YES="1";
	String CHECK_DISCHARGE_SUMMARY_GENERATED_REQUIRED_NO="0";
	String DISCHARGE_STATUS_LIST="dischargestatuslist";
	
	String ESSENTIAL_PROFILE_STATUS="essentialProfileStatus";
	
	//blood transfusion status
	String BLOOD_TRANSFUSSION_COMPLETED="1";
	String BLOOD_TRANSFUSSION_PARTIALLY="2";
	String BLOOD_TRANSFUSSION_CANCELLED="3";
	
	String IS_TRANSFUSION_YES="1";
	String IS_TRANSFUSION_NO="0";
		 
	// Phlebotomy Arm
	String PHLEBOTOMY_ARM_LEFT="1";
	String PHLEBOTOMY_ARM_RIGHT="2";
	
	// Labor Room Area Master
	String ESSENTIALBO_LIST_LABOR_ROOM="essentialLaborRoomList";
	String ESSENTIALBO_LIST_AREA_TYPE="essentialAreaTypeList";

	// Labor Room Master
	String INPATIENT_ESSENTIAL_LIST_ALL_CLINICAL_DEPARTMENTS= "inpatientEssentialListAllClinicalDepartments"	;
	
	
	
	// List of Transfusion Reaction Detail
	
	String TEMPLATE_ID_LIST_FOR_TRANSFUSION_REACTION="templateIdListForTransfusionReact";
	
	String TRANSFUSION_REACTION_TEMPLATE_DETAIL="transfusionReactionTemplateDtl";
	
	
	String TRANSFUSION_REACTION_DETAIL="transfusionReactionDtl";
	
	String PREVIOUS_TRANSFUSION_REACTION_DETAIL="previousTransfusionReactionDtl";
	
	String BLOOD_BAG_LIST_BY_REQUISTION_NO="bloodBagListByRequistionNo";
	
	 
	String PROCEDURE_GET_BLOOD_GROUP = "Pkg_BloodBank_Mst.proc_blood_group";
	String RH_FLAG_POSITIVE_PLUS ="+";
	String RH_FLAG_NEGATIVE_PLUS ="-";
	String RH_FLAG_POSITIVE ="Positive";
	String RH_FLAG_NEGATIVE ="Negative";
	String RH_FLAG_NIL ="Nil";
	String RH_FLAG_WEAK_POSITIVE ="W(Positive)";
	String RH_FLAG_MIXED_FIELD="MixedField";
	String RH_FLAG_POSITIVE_VALUE ="1";
	String RH_FLAG_NEGATIVE_VALUE ="2";
	String RH_FLAG_NIL_VALUE ="0";
	String RH_FLAG_WEAK_POSITIVE_VALUE="3";
	String RH_FLAG_MIXED_FIELD_VALUE="4";
	
	
	
	
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
	
	String ESSENTIALBO_OPTION_PATIENT_CATEGORY="optionPatientCategory";
    String ESSENTIALBO_OPTION_REGISTRATION_CATEGORY="optionRegCategory";
    String ESSENTIALBO_OPTION_PRIMARY_CATEGORY= "optionPrimaryCategory";
    String ESSENTIALBO_OPTION_REG_CATEGORY= "optionRegCategory";
    
    String PATIENT_CAT_TYPE_SECONDARY = "1";
    String PATIENT_CAT_TYPE_PRIMARY   = "0";
    String PATIENT_REG_CATEGORY_NORMAL="11";
    String PATIENT_REG_CATEGORY_SPECIAL="12";
    String PATIENT_REG_CATEGORY_EMERGENCY="13";
    String PATIENT_OFFLINE_REG="15";
    
    String DEPT_TYPE_CLINICAL_VALUE="1";
    String HOSPITAL_COMBO_LIST="hospitalComboList";
    String ESSENTIAL_BO_OPTION_ALLDEPARMENT="alldepartment";
    
    //addedby neharajgariya date:16dec2016 purpose:intakeoutput master
    String Para_id = "paraid";
    
    
    String PROC_CROSSMATCHED_BAG_REVERT = "{call pkg_blood_bank.crossmatched_bag_revert(?::numeric,?::numeric,?::character varying,?::character varying,?::character varying,?::character varying)}";   //for Drug Brand list
    String PREV_IPD_DOC_ROUND_DETAILS_CHANGE = "1"; //Added by Vasu on 26.Sept.2018
    
} 