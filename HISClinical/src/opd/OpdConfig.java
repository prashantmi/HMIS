package opd;

//OpdConfig is an interface that defines hard-coded values 
//that are used for development of BO and DAO.

public interface OpdConfig
{
	/************************ Configuration Settings *************************/
	
	String ICD_ENTRY_FORM_ONLY_SCANNED = "1"; 
	String ICD_ENTRY_FORM_NOT_ONLY_SCANNED = "0"; 
	String ICD_ENTRY_FORM_LIST_CONDITION = ICD_ENTRY_FORM_ONLY_SCANNED; 
	
	/*************************************************************************/
	
	String DOCUMENT_STORAGE_PATH = "documentStoragePath";
	String FILE_NAME = "fileName";
	String IS_EXTENSION = "isExtension";
	String IS_EXTENSION_TRUE = "1";/////////////////1=if extension required
	String IS_EXTENSION_FALSE = "0";//////////////////0=if extension not required
	String EXTENSION_NAME = "extensionName";

	////////////////////////////Query File Name///////////////////////////////////////////////////
	String QUERY_FILE_FOR_OPD_MASTERSDAO = "opd.opdMasterQuery";
	String QUERY_FILE_FOR_OPD_ESSENTIALDAO = "opd.opdEssentialQuery";
	String QUERY_FILE_FOR_OPD_DAO = "opd.opdQuery";
	String VALIDATE_DATA="validateData";								 
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/////Symptom Wise Template Mapping Master
	
	String Template_List="templateList";//added by sandip naik on 20/06/2017
	String SYMPTOM_SELECTED_Template_LIST="selectedTemplateList";
	String SYMPTOM_REMAINING_Template_LIST="remainingTemplateList";
	String Template_ALL_Template_LIST="allTemplateList";
	String Symptom_TYPE_BY_Symptom_TYPE_CODE="symptomTypeBysymptomTypeCode";
	String SYMPTOM_SELECTED_Template_LIST_NEW="selectedTemplateListNew";
	String SYMPTOM_REMAINING_Template_LIST_NEW="remainingTemplateListNew";
////////////////////////////////////////////////////////////////////////////////

	/************************* Reports ********************************/
		// Path for OPD reports
	String OPD_JRXML_PATH = "/opd/reports/jrxml/";

		// ICD Search Engine
	String OPD_ICD_GROUP_LIST_REPORT = "ICDGroupList.jrxml";
	String OPD_ICD_SUBGROUP_LIST_REPORT = "ICDSubGroupList.jrxml";
	String OPD_ICD_SUBGROUP_LIST_GROUPWISE_REPORT = "ICDSubGroupListGroupWise.jrxml";
	String OPD_ICD_DISEASE_LIST_REPORT = "ICDDiseaseList.jrxml";
	String OPD_ICD_DISEASE_LIST_GROUPWISE_REPORT = "ICDDiseaseListGroupWise.jrxml";
	String OPD_ICD_DISEASE_LIST_SUBGROUPWISE_REPORT = "ICDDiseaseListSubGroupWise.jrxml";
	String OPD_ICD_SUBDISEASE_LIST_REPORT = "ICDSubDiseaseList.jrxml";
	String OPD_ICD_SUBDISEASE_LIST_GROUPWISE_REPORT = "ICDSubDiseaseListGroupWise.jrxml";
	String OPD_ICD_SUBDISEASE_LIST_SUBGROUPWISE_REPORT = "ICDSubDiseaseListSubGroupWise.jrxml";
	String OPD_ICD_SUBDISEASE_LIST_DISEASEWISE_REPORT = "ICDSubDiseaseListDiseaseWise.jrxml";
	String OPD_PATIENT_ATTENDED_WAITING="OPDPatAttended.jrxml";
	
	
	
	/////////ICD MASTER Desk Parameters////////////

	String OPD_DESKMODE_ICDMASTER_GROUP = "GROUP";
	String OPD_DESKMODE_ICDMASTER_SUBGROUP = "SUBGROUP";
	String OPD_DESKMODE_ICDMASTER_DISEASE = "DISEASE";
	String OPD_DESKMODE_ICDMASTER_SUBDISEASE = "SUBDISEASE";

	///////////////////////////////////////////////////OPD DESK////////////////////////////////
	String OPD_LEFT_DESK_MENU_DTL = "opdLeftDeskMenudtl";
	String OPD_RIGHT_DESK_MENU_DTL = "opdRightDeskMenudtl";
	String OPD_REPORT_DESK_LEFT_MENU_DTL = "opdReportDeskLeftMenudtl";
	String OPD_DESK_LOCATION_LEFT_MENU = "1";
	String OPD_DESK_LOCATION_RIGHT_MENU = "2";
	String OPD_DESK_LOCATION_BOTTOM_MENU = "3";
	String OPD_NO_OF_ATTENDED_PAT = "noOfPatientAttended";
	String OPD_REASON="opdreason";
	
	////////////////////////////////Icd Master & Department Icd Master/////////////////////////////////////////////////////////
	String ESSENTIAL_MAP_DEPT_ICD_MST = "essMapIcdMaster";
	String ARRAY_DEPT_ICD_VO = "arrayDeptIcdVO";
	String ARRAY_DEPT_HOSDIS_VO = "arrayDepthosDisVO";
	String ARR_DESK_MENU_MACRO_MASTER_VO = "arrDeskMenuMacroMstVO";

	String EssentialBO_LIST_ICD_GROUP = "essentialListIcdGroup";
	String EssentialBO_LIST_ICD_SUBGROUP = "essentialListIcdSubGroup";
	String EssentialBO_LIST_ICD_DISEASE = "essentialListIcdDisease";
	String EssentialBO_LIST_HOSDIS_DISEASE = "essentialListHosDisDisease";
	String EssentialBO_LIST_HOSDIS_DISEASE_NOT_ADDED = "essentialListHosDisDiseaseNotAdded";
	String EssentialBO_LIST_ICD_SUBDISEASE = "essentialListIcdSubDisease";
	String EssentialBO_LIST_HOSDIS_SUBDISEASE = "essentialListHosDisSubDisease";
	String EssentialBO_LIST_HOSDIS_SUBDISEASE_NOT_ADDED = "essentialListHosDisSubDiseaseNotAdded";

	String GROUP_LIST_FOR_REMOVAL = "groupListForRemoval";
	String SUB_GROUP_LIST_FOR_REMOVAL = "subGroupListForRemoval";
	String DISEASE_LIST_FOR_REMOVAL = "diseaseListForRemoval";
	String SUB_DISEASE_LIST_FOR_REMOVAL = "subDiseaseListForRemoval";

	String CHOICE_DEPARTMENT = "1";
	String CHOICE_UNIT = "2";

	String CHOICE_GROUP = "1";
	String CHOICE_SUBGROUP = "2";
	String CHOICE_DISEASE = "3";
	String CHOICE_SUBDISEASE = "4";

	String DEFAULT_UNIT_CODE_ICD_MASTER = "00000";
	String DEFAULT_UNIT_CODE_HOS_MASTER = "00000";

	///////////////////// OPD Desk Patient List////////////////////////////////

	String OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING = "1";
	String OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED = "2";

	String OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING = "opdDeskPatientListPatientDtlVOWaiting";
	String OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED = "opdDeskPatientListPatientDtlVOAttended";
	
	String OPD_PATIENT_LIST_DAILYPATIENT_VO = "opdPatientList";
	String OPD_DESK_SELECTED_PATIENT_VO = "opdDeskSelectedPatientVO";
	String OPD_DESK_UNIT_LIST = "opdDeskUnitList";
	String OPD_DESK_UNIT_CODE = "opdDeskUnitCode";
	String OPD_DESK_UNIT_NAME = "opdDeskUnitName";
	String OPD_DESK_ROOM_CODE = "opdDeskRoomCode";
	String OPD_ROOM_LIST = "opdDeskRoomList";
	String OPD_DESK_TEMPLATES_ACTIVE_MAP = "opdDeskTemplateActiveMap";
	String OPD_DESK_TEMPLATES_ACTIVE_IDS_MAP = "opdDeskTemplateActiveIdsMap";
	String OPD_DESK_TEMPLATES_INACTIVE_IDS_MAP = "opdDeskTemplateInActiveIdsMap";
	String OPD_DESK_TEMPLATES_ACTIVE_HTML_DATA_MAP = "opdDeskTemplateActiveHtmlDataMap";
	String OPD_DESK_TEMPLATES_BY_CRNO_VISIT_LIST = "opdDeskTemplateByCrNoVisitList";
	String OPD_DESK_ALL_TEMP_PARAS_BY_CRNO_VISIT_LIST = "opdDeskAllTempParasByCrNoVisitList";
	String OPD_DESK_TEMPWISE_PARAS_BY_CRNO_VISIT_MAP = "opdDeskTempWiseAllParasByCrNoVisitMap";
	String OPD_DESK_ALL_TEMP_PARAS_NAMES_BY_CRNO_VISIT_LIST = "opdDeskAllTempParasNamesByCrNoVisitMap";

	String OPD_DESKMENU_TEMPLATES_ACTIVE_LIST = "opdDeskMenuTemplateActiveList";
	String OPD_DESKMENU_TEMPLATES_ACTIVE_IDS_LIST = "opdDeskMenuTemplateActiveIdsList";
	String OPD_DESKMENU_TEMPLATES_INACTIVE_IDS_LIST = "opdDeskMenuTemplateInActiveIdsList";
	String OPD_LIST_PREV_VISIT_DATES = "opdPreviousVisitDatesList";
	String OPD_PREV_VISIT_PARA_WISE_REP_MAP = "opdPreviousVisitParaWiseRepMap";
	String OPD_PREV_VISIT_PARA_WISE_REP_LIST = "opdPreviousVisitParaWiseRepList";

	//String EssentialBO_LIST_ALL_DEPT = "essentialListallDeptList";
	//String EssentialBO_LIST_ALL_SEATS = "essentialListallSeatsList";
	String EssentialBO_LIST_DEPT_SEATS = "essentialListdeptWiseSeatsList";
	String EssentialBO_LIST_ALL_MENUS = "essentialAllMenusList";
	//String EssentialBO_LIST_ALL_UNITS = "essentialListallUnitsList";
	String EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED = "essentialListallUnitsListNotAssigned";
	String EssentialBO_LIST_ALL_DESK_BY_TYPE = "essentialListallDeskByType";
	String EssentialBO_DIAGNOSIS_NAME_LIST_UNIT_WISE = "diagnosisNameListUnitWise";
	String EssentialBO_DIAGNOSIS_CODE_LIST_UNIT_WISE = "diagnosisCodeListUnitWise";
	String EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST = "hospitalDiagnosisCodeList";
	String ESSENTIALBO_LIST_ALL_ALLERGY_TYPES = "essentialAllergyTypeList";
	String EssentialBO_LIST_ALL_USER_DESKMENU_UNITS = "essentialListallUserDeskMenuUnitsList";
	String EssentialBO_LIST_USER_DESK_SEATS = "essentialListUserDeskMenuUnitsWiseSeatsList";
	String EssentialBO_LIST_USER_DESKMENU_DESKS = "essentialListUserDeskMenuDesks";
	String EssentialBO_LIST_USER_DESKMENU_LIST = "essentialListUserDeskMenuList";
	String EssentialBO_LIST_USER_DESKMENU_TEMPLATE_LIST = "essentialListUserDeskMenuTemplateList";
	String ESSENTIALBO_BELONGING_LIST = "essentialBelongingList";
	String ESSENTIALBO_DESK_MENU_BASED_ON_DESK_TYPE = "deskMenuBasedOnDeskType";
	String ESSENTIALBO_DESK_MENU_BASED_DUTY_ROLE_LIST = "essentialDeskMenuDutyRoleList";
	String OPD_ESSENTIAL_DIAGNOSIS_SUB_DISEASE_LIST_BY_PARENT_CODE = "opdEssentialsDiagnosisSubDiseasesListByParentCode";

	//String ESSENTIALBO_ALL_GROUP_LIST = "essentialAllGroupList";
	String ESSENTIALBO_ALL_USER_BASED_ON_GROUP = "essentialAllUserBasedOnGroup";
	
	String OPD_TEMPLATEADD_SELECTED_UNITS = "opdTemplateAdditionSelectedUnits";
	String OPD_TEMPLATEADD_SELECTED_WARDS = "opdTemplateAdditionSelectedWards";
	String OPD_TEMPLATEADD_SELECTED_SEATS = "opdTemplateAdditionSelectedSeats";
	String OPD_TEMPLATEADD_SELECTED_DESK = "opdTemplateAdditionSelectedDesk";
	
	String EssentialBO_LIST_ALL_PARAMETERS = "essentialListallParameters";
	//String EssentialBO_LIST_ALL_TEMPLATES = "essentialListallTemplates";
	//String EssentialBO_LIST_ALL_TEMPLATES_VO_MAP = "essentialListallTemplatesVOMap";
	String EssentialBO_LIST_ALL_PAT_PARA_CRNO = "essentialListallPatParaCrno";
	String EssentialBO_LIST_ALL_PAT_PARA_LIST = "essentialListallPatParaList";
	String EssentialBO_LIST_ALL_PAT_TEMP_LIST = "essentialListallPatTempList";
	String ESSENTIALBO_OPTION_SENSITIVITY = "essentialListSensitivity";
	String ESSENTIALBO_OPTION_ALLERGY_REASON = "essentialListAllergyReason";
	String ESSENTIAL_BO_OPTION_ICD_CODE_BASED_ON_DEPT = "essentialIcdCodeBasedOnDept";
	String ESSENTIAL_BO_OPTION_DOCTOR_NAME_BASED_ON_UNIT = "doctorNameBasedOnUnit";
	String ESSENTIAL_BO_OPTION_DESK_TYPE_LIST = "allDeskType";
	String SYSDATE = "SYSDATE";
	String ESSENTIAL_BO_OPTION_LIST_ALL_IMAGE_DESC = "essentialAllImageDesc";
	String ESSENTIAL_BO_LIST_UNIT_NOT_IN_HOPT_UNIT_IMAGEDESC_MST = "essentialUnitNotInTable";
	
	String ESSENTIAL_BO_LIST_ALL_AUDIO_VIDEO_FILE="essentialAllAudioVideoFile";
	String ESSENTIAL_BO_LIST_ALL_AV_FILE_NOT_IN_SELECTED_BASED_ON_UNIT="AllAVFileNotInSelectedBasedOnUnit";
	String ESSENTIAL_BO_LIST_ALL_UNIT_NOT_IN_HOPT_UNIT_PLAYERFILE_MST="allUnitNotInHoptUnitPlayerFileMst";

	// ***** Desk Menu Type
	String DESK_TYPE_OPD = "1";
	String DESK_TYPE_IPD = "2";
	// ********************

	// ***** Desk Menu Location
	String DESK_LOCATION_LEFT = "1";
	String DESK_LOCATION_RIGHT = "2";
	String DESK_LOCATION_TOP = "3";
	String DESK_LOCATION_BOTTOM = "4";
	// ********************

	//****** Desk Menu Default Color
	String DESK_DEFAULT_COLOR = "#96BAEA";//FFDEAD";
	//*************************


/*	String CHOICE_ICD_CODE = "0";
	String CHOICE_HOSPITAL_CODE = "1";
	String CHOICE_SNOMEDCT_CODE = "3";
	String CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE = "2";
	String CHOICE_ICD_AND_HOSPITAL_DEFAULT_CODE = "4";
	String CHOICE_ALL_ICD_HOSPITAL_SNOMED ="5";
	 */
	
	String CHOICE_ICD_CODE = "0";
	String CHOICE_HOSPITAL_CODE = "1";
	String CHOICE_SNOMEDCT_CODE = "4";
	String CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE = "3";
	String CHOICE_ICD_AND_HOSPITAL_DEFAULT_CODE = "2";
	String CHOICE_ALL_ICD_HOSPITAL_SNOMED ="5";
	String CHOICE_ALL_AND_SNOMED_DEFAULT = "6";
	String CHOICE_HOSPITAL_AND_SNOMED_DEFAULT = "7";
	
	String DIAGNOSIS_CODE_TYPE_ICD="1";
	String DIAGNOSIS_CODE_TYPE_HOSPITAL="2";
	String DIAGNOSIS_CODE_TYPE_SNOMED="3";
	String DIAGNOSIS_CODE_TYPE_OTHER="4";
	
	
	String SOURCE_ISOPD = "1";
	String SOURCE_ISIPD = "2";

	//***** Allergy Type Source Flag
	String[] ALLERY_TYPE_SOURCE_FLAG_ARR = {"","Static","Dynamic"}; 
	String ALLERY_TYPE_SOURCE_FLAG_STATIC = "1";
	String ALLERY_TYPE_SOURCE_FLAG_DYNAMIC = "2";
	//*****************

	String OPD_DIAGNOSIS_COMBO_OPTION = "opdDiagnosisComboOption";

	String PATIENT_VO = "patientVO";
	String EPISODE_CLOSE_VO = "episodeCloseVO";
	String COLL_CLOSE_EPISODE = "collCloseEpisode";
	String PREVIOUS_DIAGNOSIS_DETAIL_VO = "previousDiagnosisDetailVO";
	String SELECTED_DIAGNOSIS_TYPE_CODE_FOR_UNIT = "selectedDiagnosisTypeCodeForUnit";
	String REPEAT_DIAGNOSIS_VO = "repeatDiagnosisVO";
	String Latest_DIAGNOSIS_VO = "latestDiagnosisVO";
	String ARR_ADDED_SNOMED="arrAddedSnomed";

	String CHOICE_YES = "0";
	String CHOICE_NO = "1";
	String VIEW_REGISTRATION_CATEGORY = "R";
	String VIEW_PATIENT_CATEGORY = "P";
	//////////////////////////////////Refer Patient/////////////////////////////////////////////
	String OPD_ESSENTIALBO_OPTION_DEPARTMENT = "opdOptionDepartment";
	String OPD_UNIT_WITH_SPECIALITY = "opdUnitWithSpeciality";
	String OPD_ESSENTIALBO_LIST_DEPARTMENTS_GENERAL_UNITS = "opdEssentialDepartmentsGeneralUnitsList";
		// Internal Refer Types
	String PATIENT_INTERNAL_REFER_TYPE_DEPARTMENT = "0";
	String PATIENT_INTERNAL_REFER_TYPE_SPECIAL_UNIT = "1";
	String PATIENT_INTERNAL_REFER_TYPE_SAME_DEPT_UNIT = "2";

	///////////////////////////Consultation Inbox///////////////////////

	String CONSULTATION_INBOX_DETAIL_VO = "consultationInboxDetailVO";
	String PROCESS_ID_COSUTANT_MAIL="2";
	String PROFILE_LIST_COMBO="profileListCombo";
	//////////////Episode Allergies/////////////////   

	String ARRAY_ALL_ALLERGY_DETAIL_VO="arrAllAllergyDtlVO";
	String ARRAY_EPISODE_ALLERGY_VO = "arrayEpisodeAllergyVO";
	String MAP_SELECTED_ALLERGIES = "collSelectedAllergies";
	String MAP_ALLERGY_REASON = "mapAllergyReason";
	String MAP_ALLERGY_CODE_KEY = "mapAllergyCodeKey";
	String OUTER_ALLERGY_MAP = "outerAllergyMap";
	String INNER_ALLERGY_MAP = "innerAllergyMap";
	///////////////////////Econsultation//////////////////////////////////////////////////////////
	String OPD_ECONSULTANT_DETAIL_LIST = "opdEConsultantList";
	String OPD_DEPT_LIST ="opdDeptList";
	String OPD_CONSULTANT_NAME_NO = "opdConsultantNameNo";
	String OPD_CONSULTANT_NO_SEATID = "opdConsultantNoSeatId";
	String OPD_COSULTANT_NEW_MAIL = "0";
	String OPD_COSULTANT_REPLY_MAIL = "1";
	String OPD_COSULTANT_FORWARD_MAIL = "2";
	String OPD_COSULTANT_ACKNOWLEDGEMENT_MAIL = "3";
	String OPD_CONSULTANT_ACK_STATUS_NEW = "1";
	String OPD_CONSULTANT_ACK_STATUS_READ = "2";
	String OPD_CONSULTANT_ACK_STATUS_DELETED = "3";
	String OPD_URL_TO_GET_NO_OF_NEW_MAIL = "/HISClinical/GetNewMailsForOpdConsultaionInbox";
	String OPD_ACK_SUBJECT = "ACKNOWLEDGEMENT MAIL";
	String OPD_ACK_CONTENT = "YOUR MAIL HAS BEEN READ BY RECIPIENT. IT'S A SYSTEM GENERATED MAIL";
	String ACKNOWLEDGED_MAIL_DETAIL="acknowledgedMailDetails";
	String OPD_CONSULTATION_INBOX_MAIL_PATIENT_DETAIL_VO = "opdConsultationInboxMailPatientDetailVO";
	//////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////Document Archival/////////////////////////////////////////
	String DOCUMENT_DTL_VO_ARRAY = "documentDtlArray";
	String ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS_LIST = "optionVerificationDocList";
	String OPD_ADD_DOCUMENT_MAP = "opdAddDocumentMap";
	String OPD_ADD_DOCUMENT_DETAIL_MAP = "opdAddDocumentDetailMap";
	String OPD_DOCUMENT_SERIAL_NO = "opdDocumentSerialNo";
	String OPD_TEMPLATE_CONSENT_HTML_DATA = "opdTempConsentHtml";
	String AUDIO_VIDEO_URL="HISClinical/opd/opdDocumentArchival.cnt";
	//************* Template *********************************

	String CHOICE_PREVVISIT_TEMP_WISE = "0";//"opdChoiceTempWise"; 
	String CHOICE_PREVVISIT_PARA_WISE = "1";//"opdChoiceParaWise"; 
	String CHOICE_PREVVISIT_SHOW_PARA_WISE = "1";
	String CHOICE_PREVVISIT_SHOW_VISIT_WISE = "2";

	//********************************************************

	/////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////Opd Next Visit Appointment////////////////////////
	String ACTIVITY_CODE_OPD_CONSULTATION = "100"; // ACTIVITY CODE FOR OPD CONSULTATION
	String ACTIVITY_NAME_OPD_CONSULTATION = "OPD Consultation"; // ACTIVITY NAME FOR OPD CONSULTATION
	String APPOINTMENT_OPTION_ACTIVITY = "optionAppointmentActivities";
	String APPOINTMENT_PARANAMES = "opdAppointmentParanames";
	String APPOINTMENT_OPTION_PARA1 = "optionsAppointmentPara1";
	String APPOINTMENT_OPTION_PARA2 = "optionsAppointmentPara2";
	String APPOINTMENT_OPTION_PARA3 = "optionsAppointmentPara3";
	String APPOINTMENT_OPTION_PARA4 = "optionsAppointmentPara4";
	String APPOINTMENT_ALL_SLOTDTL = "optionAppointmentAllSlotDtl";
	String APTSTATUS_APPOINTMENT_GIVEN = "1";
	String SLOT_STATUS_VACANT = "1";
	String APPOINTMENT_DATE = "appointmentDate";
	String DEPARTMENT_UNITNAME_APPOINTMENT = "deptUnitNameAppointment";
	String NUMBER_OF_PATIENT_IMAGE_UPLOADED="NumberOfPatientImageUploaded.jrxml";
	
	//	report specific files for PGI
	String OPD_PAT_REFERRAL_DETAIL_TODAY = "ReferDetailToday.jrxml";
	String OPD_PAT_REFERRAL_DETAIL_DATEWISE = "ReferDetailDateWise.jrxml";
	String OPD_DEPT_WISE_REFER_PAT_FROM_TODAY = "DeptWiseRefPatFromToday.jrxml";
	String OPD_DEPT_WISE_REFER_PAT_FROM_DATEWISE = "DeptWiseRefPatFromDateWise.jrxml";
	String OPD_DEPT_WISE_REFER_PAT_TO_TODAY = "DeptWiseRefPatToToday.jrxml";
	String OPD_DEPT_WISE_REFER_PAT_TO_DATEWISE = "DeptWiseRefPatToDateWise.jrxml";

	// report specific files for GNCTD	
	String OPD_DEPT_UNIT_CAT_WISE_STAT_TODAY = "DeptUnitCatWiseToday.jrxml";
	String OPD_DEPT_UNIT_CAT_WISE_STAT_DATEWISE = "DeptUnitCatWiseDateWise.jrxml";
	String OPD_DEPT_UNIT_USER_WISE_STAT_TODAY = "DeptUnitUserWiseToday.jrxml";
	String OPD_DEPT_UNIT_USER_WISE_STAT_DATEWISE = "DeptUnitUserWiseDateWise.jrxml";
	String OPD_DEPT_UNIT_WISE_STAT_TODAY = "DeptUnitWiseToday.jrxml";
	String OPD_DEPT_UNIT_WISE_STAT_DATEWISE = "DeptUnitWiseDateWise.jrxml";
	String OPD_COUNT_OLD_PAT_NEXT_VISIT = "CountOldPatNextVisit.jrxml";
	String OPD_PAT_LIST_REG_CAT_WISE_DATEWISE = "DeptUnitRegCatDateWise.jrxml";
	String OPD_PAT_LIST_REG_CAT_WISE_TODAY = "DeptUnitRegCatToday.jrxml";
	String OPD_PAT_LIST_PAT_CAT_WISE_DATEWISE = "DeptUnitPatCatDateWise.jrxml";
	String OPD_PAT_LIST_PAT_CAT_WISE_TODAY = "DeptUnitPatCatToday.jrxml";
	String OPD_DEPT_WISE_DISEASE_CODE_lIST = "DeptWiseDiseaseCodeList.jrxml";
	String OPD_DISCREPANCY_REPORT_DATEWISE = "DiscrepancyReportDateWise.jrxml";
	String OPD_DISCREPANCY_REPORT_TODAY = "DiscrepancyReportToday.jrxml";
	String OPD_DEPT_UNIT_EPISODE_STATUS_PAT_LIST_DATEWISE = "DeptUnitEpisodeStatusPatListDateWise.jrxml";
	String OPD_DEPT_UNIT_EPISODE_STATUS_PAT_LIST_TODAY = "DeptUnitEpisodeStatusPatListToday.jrxml";
	String OPD_REFERRED_PATIENT_LIST_DATEWISE = "ReferredPatientListDateWise.jrxml";
	String OPD_REFERRED_PATIENT_LIST_TODAY = "ReferredPatientListToday.jrxml";
	String AVERAGE_STAY_TIME_OF_THE_PATIENT="";
	String OPD_LISTING_OF_PATIENT_REPORT="opdPatientListing.jrxml";
	String PATIENT_LISTING_REPORT_FOR_OPD_VISIT="patientListingReportForOPDVisit.jrxml";
	
	// report specific files for AYUSH	
	String DIAGNOSIS_WISE_OPD_AND_IPD_TODAY = "DiagnosisWiseOpdAndIpdToday.jrxml";
	String DIAGNOSIS_WISE_OPD_AND_IPD_DATEWISE = "DiagnosisWiseOpdAndIpdDateWise.jrxml"; 
	
	String COUNT_NO_OF_PATIENTS_OF_DISEASE_IN_FINANCIAL_YEAR="CountNoOfPatientOfAnoRectangleDisease.jrxml";
	

	/////////////////////OPD SERVICE REQUISITION ////////////////////
	String SERVICE_REQ_DATE = "DateofService";
	String SERVICE_ALL_SERVICE_REQ_DTL_BY_CRNO = "allServiceReqDtlByCrNo";
	String OPTIONS_SERVICE_AREA = "OptionServiceArea";
	String SERVICE_STATUS_NOT_VISITED = "0";
	String SERVICE_STATUS_VISITED = "1";
	String SERVICE_STATUS_SERVICE_PROVIDED = "2";
	String SERVICE_STATUS_SCHEDULE_PASSED = "3";

	String MORBIDITY_REPORT = "MorbidityReportFormat.jrxml";
	String ICD_CODE_REPORT = "IcdCodeReport.jrxml";
	String OPD_ROSTER_DETAIL = "opdRoster.jrxml";
	String OPD_DEPT_UNIT_DOCTOR_WISE_DATEWISE = "DeptUnitDoctorWiseDateWise.jrxml";
	String OPD_DEPT_UNIT_DOCTOR_WISE_TODAY = "DeptUnitDoctorWiseToday.jrxml";
	String OPD_DEPT_UNIT_DIAGNOSIS_STAT_DATEWISE = "DeptUnitDiagnosisStatDateWise.jrxml";
	String OPD_DEPT_UNIT_DIAGNOSIS_STAT_TODAY = "DeptUnitDiagnosisStatDateWise.jrxml";

	////////////////////////patient profile///////////////////////////////////////////////
	String OPD_PATIENT_PROFILE_EPISODEVO_ARRAY = "opdPatientProfileEpisodeVoArray";
	String OPD_PATIENT_PROFILE_HTML = "opdPatientProfileHtml";
	String OPD_DIAGNOSIS_HTML = "opdDiagnosisHtml";
	String OPD_PATIENT_PROFILE_TEMP_HTML = "opdPatientProfileTempHtml";
	String OPD_FILE_HEADER = "opdFileHeader";
	String OPD_PATIENT_PREVIOUS_PROFILE_ARRAY = "opdPatientPreviousProfileArray";
	String PATIENT_PROFILE_USER_DESK_MENU_TEMPLATE_MASTER_VO="patientProfileUserDeskMenuTemplateMasterVO";
	
	String PATIENT_PROFILE_EPISODE_PROFILES_LIST = "patientProfileEpisodeProfilesList";
	String PATIENT_PROFILE_PROFORMA_OBJECT = "patientProfileProformaObject";
	String PATIENT_PROFILE_BASED_DESK_MENUS_LIST = "patientProfileBasedDeskMenusList";
	String OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY = "opdPatientProfileEpisodeDiagnosisVoArray";
	String OPD_PATIENT_PROFILE_EPISODEALLERGIESVO_ARRAY = "opdPatientProfileEpisodeAllergiesVoArray";
	String OPD_PATIENT_PROFILE_EPISODEEXAMIMAGESVO_ARRAY = "opdPatientProfileEpisodeExamImagesVoArray";
	String OPD_PATIENT_PROFILE_NEXTVISITDETAIL_EPISODEVO = "opdPatientProfileNextVisitDetailEpisodeVO";
	String OPD_PATIENT_PROFILE_EPISODEINVESTIGATIONVO_ARRAY = "opdPatientProfileEpisodeInvestigationVoArray";
	String OPD_PATIENT_PROFILE_EPISODETREATMENTVO_ARRAY = "opdPatientProfileEpisodeTreatmentVoArray";
	String OPD_PATIENT_PROFILE_ALERTSVO_ARRAY = "opdPatientProfileAlertsVoArray";
	String OPD_PATIENT_PROFILE_PROGRESS_NOTES_ARRAY = "opdPatientProfileProgressNotesVoArray";
	String OPD_PATIENT_PROFILE_EPISODE_EXT_INVESTIGATION_VO_ARRAY = "opdPatientProfileEpisodeExtInvestigationVoArray";
	String OPD_PATIENT_PROFILE_AUTO_MENU_LIST = "opdPatientProfileAutoMenuList";

	String PATIENT_PROFILE_ACCESS_PRIVILEDGES_LIST = "patientProfileAccessPriviledgesList";
	String OPD_PATIENT_PROFILE_ESSENTIAL_UNIT_LIST = "opdPatientProfileEssentailUnitList";
	String OPD_PATIENT_PROFILE_ESSENTIAL_ALL_UNIT_LIST = "opdPatientProfileEssentailAllUnitList";
	String OPD_PATIENT_PROFILE_ADDED_UNIT_LIST = "opdPatientProfileAddedUnitList";
	String OPD_PATIENT_PROFILE_ESSENTIAL_USER_LIST = "opdPatientProfileEssentailUserList";
	String OPD_PATIENT_PROFILE_ADDED_USER_LIST = "opdPatientProfileAddedUserList";
	String OPD_PATIENT_PROFILE_SEARCHED_USER_LIST = "opdPatientProfileSearchedUserList";
	String OPD_PATIENT_PROFILE_ALL_USERS_MAP = "opdPatientProfileAllUsersMap";
	String OPD_PATIENT_PROFILE_ALL_UNITS_MAP = "opdPatientProfileAllUnitsMap";
	String PATIENT_PROFILE_DIAGNOSIS_DTL_VO_ARRAY="patientProfileDiagnosisDtlVoAray";
	String PATIENT_PROFILE_ALLERGY_DTL_VO_ARRAY="patientProfileAllergyDtlVoAray";
	String PATIENT_PROFILE_INVESTIGATION_DTL_VO_ARRAY="patientProfileInvestigationDtlVoAray";
	String PATIENT_PROFILE_TREATMENT_DTL_VO_ARRAY="patientProfileTreatmentDtlVoAray";
	String PATIENT_PROFILE_ALERTS_DTL_VO_ARRAY="patientProfileAlertsDtlVoAray";
	String PATIENT_PROFILE_FOOTER_DTL_VO_ARRAY="patientProfileFooterDtlVoAray";
	String PATIENT_PROFILE_DISCHARGE_DRUG_DTL_VO_ARRAY="patientProfileDischargeDrugDtlVoAray";
	String PATIENT_PROFILE_DISCHARGE_DIET_ADVICE_DTL_VO="patientProfileDischargeDietAdviceDtlVo";
	String PATIENT_PROFILE_DISCHARGE_REST_ADVICE_DTL_VO="patientProfileDischargeRestAdviceDtlVo";
	String PATIENT_PROFILE_DISCHARGE_EXT_TREATMENT_DTL_VO_ARRAY="patientProfileDischargeExtTreatmentDtlVoAray";
	String PATIENT_PROFILE_DISCHARGE_OTHER_INSTRUCTIONS_DTL_VO_ARRAY="patientProfileDischargeOtherInstructionsDtlVoArray";
	String PATIENT_PROFILE_DISCHARGE_TYPE_LIST="patientProfileDischargeTypeList";
	String PATIENT_PROFILE_DISCHARGE_ADVICED_BY_LIST="patientProfileDischargeAdvicedByList";
	String PATIENT_PROFILE_DISCHARGE_OTHER_ACTIVITY_DTL_VO_ARRAY="patientProfileDischargeOtherActivityDtlVoArray";
	String PATIENT_PROFILE_COMPLAINTS_DTL_VO_ARRAY="patientProfileComplaintsDtlVoArray";
	String PATIENT_PROFILE_COMPLAINTS_DTL_DATE_WISE_VO_ARRAY="patientProfileComplaintsDtlDateWiseVoArray";
	String PATIENT_PROFILE_OT_DTL_VO_ARRAY="patientProfileOTDtlVoAray";
	String PATIENT_PROFILE_IMAGE_DTL_VO_ARRAY="patientProfileImageDtlVoAray";
	String PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_YES="1";
	String PATIENT_PROFILE_DTL_GENERIC_CHECK_FLAG_NO="2";
	String PATIENT_PROFILE_ALLERGY_DTL_DESK_TILE="patientProfileAllergyDtl_Desk_Tile";
	String PATIENT_PROFILE_ALERTS_DTL_DESK_TILE="patientProfileAlertsDtl_Desk_Tile";
	/*String PATIENT_PROFILE_ALLERGY_DTL_CHECK_FLAG_YES="1";
	String PATIENT_PROFILE_ALLERGY_DTL_CHECK_FLAG_NO="2";
	String PATIENT_PROFILE_INVESTIGATION_DTL_CHECK_FLAG_YES="1";
	String PATIENT_PROFILE_INVESTIGATION_DTL_CHECK_FLAG_NO="2";
	String PATIENT_PROFILE_TREATMENT_DTL_CHECK_FLAG_YES="1";
	String PATIENT_PROFILE_TREATMENT_DTL_CHECK_FLAG_NO="2";*/
	String HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_INPROCESS="1";
	String HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED="2";
	String HRGT_DISCLAIMER_MST_DISCHARGE_SUMMARY_USABILITY_FLAG="4";
	String PAT_PROFILE_DTL_DISCLAIMER_DTL_VO="patProfileDtlDisclaimerDtlVO";
	String PROFILE_DRUG_ADVICE_DTL_ENTRY_MODE_DISCHARGE="1";
	String PROFILE_DRUG_ADVICE_DTL_ENTRY_MODE_DRUG_DETAIL="0";
	String PATIENT_PROFILE_LIST_ALL_TEMP_PARAS="patientProfileListAllTempParas";
	String PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_PARAMETER_WISE="1";
	String PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE="2";
	String PATIENT_PROFILE_DRUG_SCHEDULE_DTL_VO_LIST="patientProfileDrugScheduleVOList";
	String PATIENT_PROFILE_PROGRESS_NOTES_DTL_VO_ARRAY="patientProfileProgressNotesDtlVoAray";
	String PATIENT_PROFILE_EXT_INV_DTL_VO_ARRAY="patientProfileExtInvestigationDtlVoAray";
	String PATIENT_PROFILE_CHART_VIEW_DTL_VO_ARRAY="patientProfileChartViewDtlVoAray";
	
	
	String DISCLAIMER_REQUIRED_YES="1";
	String DISCLAIMER_REQUIRED_NO="0";
	
	String OPD_DESK_PROFILE_BOUND="opdDeskProfileBound";
	String PROFILE_BOUND_OPD="1";
	String PROFILE_BOUND_IPD="2";
	
	//not in use profile type combo is created using profile type master 
	String[] PROFILE_TYPE_OPD=new String[]{"","Refer/Transfer Profile","","Case Sheet","General"};
	String[] PROFILE_TYPE_IPD=new String[]{"","Refer/Transfer Profile","Discharge Summary/Final Discharge","Case Sheet","General"};
	////////////////////////////////////////////
	
	String PROFILE_TYPE_REFER="11";
	String PROFILE_TYPE_DISCHARGE="13";
	String PROFILE_TYPE_CASESHEET="3";
	String PROFILE_TYPE_GENERAL="12";
	
	String PROFILE_TYPE_OPD_VISIT_PROFILE = "14";
	
	String PROFILE_TYPE_REFER_DESC="Refer/Transfer";
	String PROFILE_TYPE_DISCHARGE_DESC="Discharge";
	String PROFILE_TYPE_CASESHEET_DESC="Case Sheet";
	String PROFILE_TYPE_GENERAL_DESC="General";
	String PROFILE_HEADER_DISCHARGE_TYPE_DESC="Discharge Report";
	String OPD_PATIENT_PROFILE_OPERATION_DETAIL_VO_ARRAY = "opdPatientProfileOperationDetailVoArray";
	String PROFILE_TYPE_VO_LIST="profileTypeVOList";
	
	//////Profile Type Generation Mode
	String PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED="1";
	String PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT="2";
	String PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE="3";
	
	String PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED_LABEL="Customized";
	String PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_LABEL="Automatic";

	/////Profile Type Usablity
	String PROFILE_TYPE_USABLITY_OPD="1";
	String PROFILE_TYPE_USABLITY_IPD="2";
	String PROFILE_TYPE_USABLITY_OPD_AND_IPD="3";
	String PROFILE_TYPE_USABLITY_OPD_LABEL="OPD";
	String PROFILE_TYPE_USABLITY_IPD_LABEL="IPD";
	String PROFILE_TYPE_USABLITY_OPD_AND_IPD_LABEL="OPD and IPD";
	
	//Advice On Discharge
	String PAT_PROFILE_DTL_DISCHARGE_DRUG_DETAIL_VO_ARRAY="patProfileDtlDischargeDrugDetailVOArray";
	String PAT_PROFILE_DTL_DISCHARGE_DIET_DETAIL_VO="patProfileDtlDischargeDietDetailVO";
	String PAT_PROFILE_DTL_DISCHARGE_REST_ADVICE_DETAIL_VO="patProfileDtlDischargeRestAdviceDetailVO";
	String PAT_PROFILE_DTL_DISCHARGE_OTHER_ADVICE_DETAIL_VO="patProfileDtlDischargeOtherAdviceDetailVO";
	String PAT_PROFILE_DTL_DISCHARGE_MODIFY_FLAG_YES="1";
	
	//Patient Profile OT Details
	String OPERATION_TEMPLATE_LIST_ALL="operationTemplateList";
	String OPERATION_TEMPLATE_PARA_ID_VALUE_ALL="operationTemplateParaIdValue";
	String OPERATION_TEMPLATE_MAP_ALL_LIST="operationTemplateMapAllList";
	
	
	// Profile Type
	String PATIENT_PROFILE_TYPE_FORWORD = "1";
	String PATIENT_PROFILE_TYPE_DISCHARGE_SUMMARY = "2";
	String PROFILE_TYPE_LIST="profileTypeList";
	String PROFILE_GENERATION_DEFAULT="0";
	String PROFILE_GENERATION_CUSTOMIZED="1";
	// Access Type
	String PATIENT_PROFILE_ACCESS_TYPE_ALL = "1";
	String PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC_OR_USER_SPCIFIC = "2";
	
	//String PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC="1";
	String PATIENT_PROFILE_ACCESS_TYPE_RESTRICTED_USERS = "2";
	String PATIENT_PROFILE_ACCESS_POLICY_VO_ARRAY="patientProfileAccessPolicyVOArray";
	String PATIENT_PROFILE_ACCESS_POLICY_DETAIL_VO="patientProfileAccessPolicyDetailVO";
	String PATIENT_PROFILE_ACCESS_TYPE_TO_ALL="0";
	String PATIENT_PROFILE_ACCESS_TYPE_OTHER="1";
	String PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC="2";
	String PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND="3";
	//String PATIENT_PROFILE_ACCESS_TYPE_OWNING_UNIT = "4";
	
	// User Search Modes
	String PATIENT_PROFILE_USER_SEARCH_BY_USER_NAME = "1";
	String PATIENT_PROFILE_USER_SEARCH_BY_EMPLOYEE_ID = "2";
	String PATIENT_PROFILE_USER_SEARCH_BY_EMPLOYEE_NAME = "3";
	
	// Profile Inbox
	String PATIENT_PROFILES_FOR_INBOX_LIST = "patientProfileForInboxList";
	String PATIENT_PROFILES_FOR_ALL_LIST = "patientProfileForAllList";
	///////////////////////////////////////////////////////////////////////////////////////

	///////////////////////Patient Belonging/////////////

	String PATIENT_BELONGING_MAP = "patientBelongingMap";
	String PATIENT_BELONGING_DETAILS_VOS = "patientBelongingDetailVOs";
	String SELECTED_PATIENT_BELONGING_VO = "selectedPatientBelongingVO";

	String OPTION_VALUE_SELF = "0";
	String OPTION_VALUE_RELATIVE = "1";
	String OPTION_VALUE_OTHER = "2";

	/////////////////////////////Audio Video Player/////////////////////////////

	String SELECTED_FILE_PATH_FOR_PLAYER = "selectedFilePathForPlayer";


	String ESSENTIAL_BO_UNIT_AUDIO_VIDEO_LIST = "essentialAudioVideoList";

	//////////// Image Upload//////////////
	String UPLOADED_IMAGE_AS_ARRAY = "uploadedImageAsArray";
	String UPLOADED_IMAGE_NAME = "uploadedImageName";
	String OPD_ADD_IMAGE_DETAIL_MAP = "opdAddImageDetailMap";
	String OPD_ADD_IMAGE_MAP = "opdAddImageMap";
	String OPD_IMAGE_FIRST_NAME = "IMAGE";

	
	// **************  Image Examination Tab
	String OPD_ESSENTIAL_IMAGES_OF_DEPTUNIT = "opdEssentialImagesOfDeptUnitList";
	String OPD_ESSENTIAL_IMAGE_POINTERS_OF_DEPTUNIT = "opdEssentialImagePointersOfDeptUnitList";
	String OPD_ESSENTIAL_OLD_ADDED_IMAGES_OF_DEPTUNIT = "opdEssentialOldAddedImagesOfDeptUnitList";
	String OPD_IMAGE_EXAM_IMAGES_VIEW_LOG_LIST_OF_IMAGE_DTL_VO = "opdImageExamImagesViewLogListOfImageDtlVO";

		// Image Editor Relates 
	String OPD_IMAGE_EDITOR_FB = "OpdImageEditorFormBean";
	String OPD_IMAGE_EDITOR_FB_DEFAULT_OUT_FILE_NAME = "DefaultImageExam.png";
	String OPD_EXAMINATION_IMAGE_PATH_VAR = "opdInImagePath";
	String OPD_EXAMINATION_PAT_IMAGE_PATH_VAR = "opdOutImagePath";


	////////////Audio/Video Upload//////////
	String UPLOADED_AUDIO_VIDEO_FILE_AS_ARRAY = "uploadedAudioVideoFileAsArray";
	String UPLOADED_AUDIO_VIDEO_FILE_NAME = "uploadedAudioVideoFileName";
	String OPD_AUDIO_VIDEO_NAME = "AUDVID";

	//////// Unit image Description //////////
	String OPD_ESSENTIAL_ALL_IMAGE_LIST = "opdEssentialAllImageList";
	String OPD_ESSENTIAL_SELECTED_IMAGE_LIST = "opdEssentialselectedImageList";
	String OPD_ESSENTIAL_UNSELECTED_IMAGE_LIST = "opdEssentialunselectedImageList";
	String OPD_ESSENTIAL_NOT_ADDED_UNIT_LIST = "opdEssentialNotAddedunit";

	String ARR_VIEW_IMAGE_UNIT_WISE_VO = "arrViewImageUnitWiseVO";
	
	String OPD_ESSENTIAL_ALL_ROLE_LIST="opdEssentialAllRoleList";

	
	String  OPD_PATIENT_LIST_COLOR_CODIFICATION_INFO= "opdPatientListColorCodificationInfo";

	///////ICD Hospital Master
	String OPD_ESSENTIAL_HOS_DISEASE_LIST="opdEssentialHosDiseaseName";
	String OPD_ICD_DISEASE_LIST_BY_HOSDIS="opdIcdDiseaseListByHosDis";

	
	//********************************************************************
		//	Desk Mapping & Template Mapping with Desk at Different Modes
	
	// Addition Modes
	String USER_DESK_ADDITION_MODE_UNIT_WISE="0";
	String USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE="1";
	String USER_DESK_ADDITION_MODE_DESK_WISE="2";	
	String USER_DESK_ADDITION_MODE_UNIT_WARD_WISE="3";
	String USER_DESK_ADDITION_MODE_WARD_SEAT_WISE="4";
	
	String DESK_MAPPING_TYPE_UNIT_WISE = "2";
	String DESK_MAPPING_TYPE_WARD_WISE = "3";
	String DESK_MAPPING_TYPE_USER_WISE = "4";
	
	// Steps
	String STEP0 = "0";
	String STEP1 = "1";
	String STEP2 = "2";

	// Choice
	String YES = "1";
	String NO = "0";

	// Essentials
	String EssentialBO_LIST_ALL_DESK_TYPE = "essentialListAllDeskType";
	String EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE = "allDesk";

	// Unit Mapped Lists
	String LIST_MAPPED_UNITS="lstMappedUnits";								 
	String LIST_MAPPED_UNITS_FOR_UNITSEAT="lstMappedUnitsForUnitSeat";
	String LIST_MAPPED_UNITS_FOR_WARD="lstMappedUnitsForWard";
	String LIST_MAPPED_UNITS_FOR_UNITSEATWARD="lstMappedUnitsForUnitSeatWard";

	String EssentialBO_LIST_SEAT_BASED_ON_UNIT="essentialBoListSeatBasedOnUnit";
	
	String EssentialBO_LIST_WARD_UNIT_WISE="essBoListWardUnitWise";
	
	String EssentialBO_LIST_WARD_UNIT_WISE_FOR_UWS="essBoListWardUnitWiseForUWS";
	String EssentialBO_LIST_SEAT_UNIT_N_WARD_WISE="essBoListWardUnitNWardWise";
	
	String MENU_TEMPLATE_LIST_BY_DESKID="menuTemplateListByDeskId";
	String ARR_DESK_MENU_N_CATEGORY_VO="arrdeskMenuNCategoryVO";
	String ARR_USER_DESK_MENU_TEMP_VO="arrUserDeskMenuTempVO";
	String EssentialBO_LIST_ALL_TEMPLATES = "essentialListallTemplates";
	String EssentialBO_LIST_ALL_TEMPLATES_VO_MAP = "essentialListallTemplatesVOMap";

	String EssentialBO_LIST_ALL_DEPT = "essentialListallDeptList";
	String EssentialBO_LIST_ALL_UNITS = "essentialListallUnitsList";
	String EssentialBO_LIST_ALL_WARDS = "essentialListAllWards"; 
	String Essential_BO_LIST_ALL_DESK_TYPE="essentialListAllDeskType";
	String ESSENTIALBO_ALL_GROUP_LIST = "essentialAllGroupList";
	String EssentialBO_LIST_ALL_SEATS = "essentialListallSeatsList";

	String LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS = "lstOfEntryObjectsOfSelectedUnits";
	String LIST_OF_ENTRY_OBJECTS_OF_SELECTED_SEATS = "lstOfEntryObjectsOfSelectedSeats";
	String LIST_OF_ENTRY_OBJECTS_OF_SELECTED_WARDS = "lstOfEntryObjectsOfSelectedWards";
	//************************************************************************************
	
	
	String EssentialBO_LIST_USER_DESKMENU_DESKS_WITHOUTSEAT = "essentialListUserDeskMenuDesksWithoutSeat";
	String EssentialBO_LIST_USERDESKUNITS_NOT_UNITTEMPLATE = "essentialListUserDeskUnitsNotUnitTemplate";
	String EssentialBO_LIST_ALL_UNITS_NOT_TEMPLATE = "essentialListallUnitsListNotTemplate";
	String EssentialBO_LIST_USERDESKUNITS_SEAT_NOTNULL = "essentialListallUnitsWithSeat";
	String EssentialBO_LIST_UNITS_FOR_UNITWARDWSISE = "essentialUitsForUnitWardWise";
	String EssentialBO_LIST_WARDS_UNITWARDWISE = "essentialListWardsForUnitWardWise";
	String EssentialBO_LIST_WARDS_UNITWARDSEATWISE = "essentialListWardsForUnitWardSeatWise";
	String EssentialBO_LIST_UNITS_FOR_UNITWARDSEATWSISE = "essentialListWardsForWardSeatWise";
	String EssentialBO_LIST_DISEASE_TYPE = "essentialListDiseaseType";
	String EssentialBO_LIST_ICD_MAPPED="essentialListMappedIcd";
	String EssentialBO_LIST_SNOMED_MAPPED="essentialListMappedSnomed";
	////User Desk Is Default Status
	String USER_DESK_IS_DEFAULT_YES="1";
	String USER_DESK_IS_DEFAULT_NO="0";

	//Hospital Disease Master, Setting Tab labels
	
	String HOSPITALDISEASE_MASTER_DISEASE = "DISEASE";
	String HOSPITALDISEASE_MASTER_SUBDISEASE = "SUBDISEASE";
	
	//OPD Desk Master, setting Is default status in OPD Desk Master
	String IS_DEFAULT="1";

	




	// For unit desk menu mapping master

	// OPD Doctor Desk 
	String OPD_DOCTOR_DESK_PAT_LIST_STAMPED_COLOR = "";//#FFE4E1";
	String OPD_DOCTOR_DESK_PAT_LIST_CONFIRMED_COLOR = "#FFE4E1";//#FFCCFF";
	String OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR = "#0000FF";
	String OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR_NAME = "Blue";
	String OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR = "#000000";
	String OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR_NAME = "Black";
	
	// STEPS
	String STEP3 = "3"; 
	String STEP4=  "4";

	String EssentialBO_LIST_ALL_DESK = "essentialListAllDesk";
	
	
	String SLNO="1";
	
	//User Desk Unit Ward Mapping Master Addition Mode
	String USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WISE="0";
	String USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_WISE="1";
	String USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_SEAT_WISE="2";
	String EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED_SEAT_AND_WARD = "essentialListallUnitsListNotAssignedSeatAndWard";
	String EssentialBO_LIST_ALL_WARDS_NOT_ASSIGNED_SEAT = "essentialListallWardsListNotAssignedSeat";
	String EssentialBO_LIST_ALL_WARDS_NOT_ASSIGNED_SEAT_FORWARDWISE = "essentialListallWardsListNotAssignedSeatForWardWise";
	String EssentialBO_LIST_DEPT_SEATS_MAPPING_MASTER = "essentialListdeptWiseSeatsListMappingMaster";
	//String EssentialBO_LIST_ALL_WARDS = "essentialListAllWards"; 
	String EssentialBO_LIST_ALL_UNITS_MAPPING_MASTER = "essentialListallUnitsList";
	
	
	//Allergy type Master Dynamic Mode
	String ALLERGYTYPE_ALL_TABLE_NAME="allTableName";
	String ALLERGYTYPE_ALL_COLUMN_NAME="allColumnName";
	String ALLERGYTYPE_PRIMARY_KEY_LIST="primaryKeyList";
	String ALLERGYTYPE_HOSPITAL_CODE_COLUMN="hospitalCodeColumn";
	
	//Diagnosis Is Repeat
	String DIAGNOSIS_IS_REPEAT_NEW="0";
	String DIAGNOSIS_IS_REPEAT_REPEAT="1";
	String DIAGNOSIS_IS_REPEAT_STOP="2";
	String DIAGNOSIS_IS_REPEAT_CANCELLED="3";
	
	String DIAGNOSIS_IS_REPEAT_LABEL_NEW="Added";
	String DIAGNOSIS_IS_REPEAT_LABEL_REPEAT="Repeated";
	String DIAGNOSIS_IS_REPEAT_LABEL_STOP="Revoked";
	String DIAGNOSIS_IS_REPEAT_LABEL_CANCELLED="Canceled";
	
	
	String DIAGNOSIS_IS_REPEAT_ARRAY[]=new String[]{DIAGNOSIS_IS_REPEAT_LABEL_NEW,DIAGNOSIS_IS_REPEAT_LABEL_REPEAT,DIAGNOSIS_IS_REPEAT_LABEL_STOP,DIAGNOSIS_IS_REPEAT_LABEL_CANCELLED};
	
	String STOP_DIAGNOSIS_VO="stopDiagnosisVO";
	String PREVIOUS_ALL_DIAGNOSIS_VO="prevAllDiagVO";
	String DIAGNOSIS_SITE_LIST="diagnosisSiteList";
	String ALL_REVOKED_DIAGNOSIS_VO="allRevokedDiagnosisVO";
	String DIAGNOSIS_LIST_ICD="diagnosisListIcd";
	String DIAGNOSIS_LIST_HOSPITAL="diagnosisListHospital";
	String DIAGNOSIS_TYPE_LIST_ICD="diagnosisTypeListIcd";
	String DIAGNOSIS_CODE_LIST = "diagnosisCodeList";
	
	
	////Allergy wise Symptom Master
	String ALLERGY_TYPE="allergyType";
	String SYMPTOM_DESC_WHERE_SYMPTOM_TYPE_IS_ONE="symptomDescription";
	String ALLERGY_TYPE_FOR_GETTING_DETAIL="allergyTypeForGettingDetail";
	String SYMPTOM_LIST_FOR_GETTING_DETAIL="symptomListForGettingDetail";
	String SYMPTOM_ALL_SYMPTOM_LIST="allSymptomList";
	String SYMPTOM_SELECTED_SYMPTOM_LIST="selectedSymptomList";
	String SYMPTOM_REMAINING_SYMPTOM_LIST="remainingSymptomList";
	String ALLERGY_TYPE_BY_ALLERGY_TYPE_CODE="allergyTypeByAllergyTypeCode";
	
	

	
	//////opd consent mapping Master getting  list
	String OPD_ESSENTIAL_SERVICE_TYPE_LIST="opdEssentialServiceTypeList";
	String OPD_ESSENTIAL_SERVICE_LIST_BY_SERVICETYPEID="opdEssentialServiceListByServiceTypeID";
	String OPD_ESSENTIAL_TEMPLATE_LIST_BY_TEMPLATEIDTYPE="opdEssentialTemplateListByTemplateID";
	String OPD_SELECTED_TEMPLATE_LIST="opdSelectedTemplateList";
	String OPD_SELECTED_CONSENTMAPPING_VO="opdSelectedConsentMappingVO";
	String OPD_SELECTED_TEMPLATE_LIST_BY_TEMPLATETYPE_TEMPLATEID="opdSelectedTemplateListByTemplateTypeAndTemplateID";
	String OPD_SERVICE_LIST="opdServiceList";
	String OPD_TEMPLATE_LIST="opdTemplateList";
	String OPD_REMAINING_TEMPLATE_LIST="remainingTemplateList";
	String OPD_SELECTED_SERVICEID_LIST="selectedServiceIdListByTemplateType";
	String OPD_REMAINING_TEMPLATE_LIST_BY_TEMPLATETYPE="opdRemainingTemplateListByTemplateID";
	String OPD_SELECTED_SERVICE_LIST="opdSelectesServiceLst";
	String SERVICE_LIST="serviceList";
	String SELECTED_SERVICE_LIST="selectedServiceList";
	
	/////opd template mode////////
	String TEMPLATE_MODE_CONSENT="1";
	String TEMPLATE_MODE_GUIDELINE="2";

	
	//Allergy Site & Symptoms
	String ALL_ALLERGY_SITE_LIST="allAllergySiteList";
	String ALLERGY_COMMON_SYMPTOM_LIST="allergyCommonSymptomList";
	String ALLERGY_TYPE_SYMPTOM_LIST="allergyTypeSymptomList";
	String ALLERGY_SITE_MAPPING_MAP="allergySiteMappingMap";
	String ALLERGY_SITE_MAPPING_LIST="allergySiteMappingList";
	String ALLERGY_SYMPTOM_MAPPING_MAP="allergySymptomMappingMap";
	String ALLERGY_SYMPTOM_MAPPING_LIST="allergySymptomMappingList";
	
	//Allergy Symptom Type
	String ALLERGY_SYMPTOM_COMMON="0";
	String ALLERGY_SYMPTOM_ALLERGY_TYPE="1";
	
	String ALLERGY_SITE_DESCRIPTION="allergySiteDesc";
	String ALLERGY_SYMPTOMS_DESCRIPTION="allergySymptomDesc";
	String ESSENTIALBO_OPTION_ALLERGY_ADVICE = "essentialListAdvice";

	// Generic Template Tile 
	String GENERIC_TEMP_TILE_ALL_TEMP_DTL_MAP = "genericTemplateTileAllTempDtlMap";
	String GENERIC_TEMP_TILE_ALL_TEMP_PARA_MAP = "genericTemplateTileAllTempParaMap";
	String GENERIC_TEMP_TILE_ALL_VALUED_TEMP_PARA_MAP = "genericTemplateTileAllValuedTempParaMap";
	String GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS = "genericTemplateTileSelectedReportTemps";
	String GENERIC_TEMP_TILE_ALL_VISIT_DATES = "genericTemplateTileAllVisitDates";
	String GENERIC_TEMP_TILE_SELECTED_VISIT_DATES = "genericTemplateTileSelectedVisitDates";
	String GENERIC_TEMP_TILE_TEMPLATE_DESK_ID = "genericTemplateTileTemplateDeskId";
	
	String GENERIC_TEMP_PROFILE_REPORT_MODE_MAP = "genericTemplateProfileReportModeMap";

	
	String GENERIC_TEMP_TILE_ALL_VISIT_DATES_COMPLAINTS = "genericTemplateTileAllVisitDatesComplaints";
	String GENERIC_TEMP_TILE_ALL_VISIT_DATES_HISTORY = "genericTemplateTileAllVisitDatesHistory";
	String GENERIC_TEMP_TILE_ALL_VISIT_DATES_EXAMINATION = "genericTemplateTileAllVisitDatesExam";
	String GENERIC_TEMP_TILE_ALL_VISIT_DATES_VITAL = "genericTemplateTileAllVisitDatesVital";
	
	String GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS_COMPLAINTS = "genericTemplateTileSelectedReportTempsComp";
	String GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS_HISTORY = "genericTemplateTileSelectedReportTempsHistory";
	String GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS_EXAMINATION = "genericTemplateTileSelectedReportTempsExam";
	String GENERIC_TEMP_TILE_SELECTED_REPORT_TEMPS_VITAL = "genericTemplateTileSelectedReportTempsVital";//Added By Shweta for Vitals
	////////////Consent  Status//////////////
	String CONSENT_REQUEST="1";
	String CONSENT_RECEIVED="2";
	
	///consent Request ////////
	String CONSENT_REQUEST_VO_LIST="consnetRequestVoList";
	String ALL_RELATIONSHIP_LIST="allRelationshipList";
	String RECEIVED_CONSENT_REQUEST_VO_LIST="receivedConsentRequestVoList";
	String PENDING_CONSENT_REQUEST_VO_LIST="pendingConsentRequestVoList";
	String CONSENTREQUEST_VO_WITH_TEMPLATE_ID="consentRequestVOWithTemplateID";
	String SELECTED_CONSENT_REQUEST="selectedConsentRequest";
	///
	String OPD_URL_TO_GET_NO_OF_CONSENT_REQUEST = "/HISClinical/GetNewConsentRequestConsentInbox";
	
	String ESSENTIALBO_LIST_CHANGE_ROOM = "essentialChangeRoomList";
	String ESSENTIALBO_OLD_ROOM_NAME = "essentialOldRoomName";
	String IS_REVOKE="1";
	
	

	///User Desk Menu Template Mapping Master
	String EssentialBO_LIST_ALL_TEMPLATES_NOT_ADDED="essentialBoListAllTempNotAdded";
	String EssentialBO_LIST_UNIT_FOR_MAPPING="essentialBoListUnitForMapping";
	String EssentialBO_LIST_UNIT_SEAT_WISE="essentialBoListUnitSeatWise";
	String EssentialBO_LIST_DESK_BASED_ON_UNIT_N_SEAT="essentialBoListDeskBasedOnUnitNSeat";
	String EssentialBO_LIST_UNIT_WARD_WISE="essBoListUnitWardWise";
	String EssentialBO_LIST_UNIT_WARD_SEAT_WISE="essBoListUnitWardSeatWise";
	String EssentialBO_LIST_DESK_BASED_ON_UNIT_N_WARD="essentialBoListDeskBasedOnUnitNWard";
	
	String EssentialBO_LIST_DESK_BASED_ON_UNIT_N_WARD_N_SEAT="essentialBoListDeskBasedOnUnitNWardNSeat";
	String ARR_TEMPLATE_N_CATEGORY_VO="arrTemplateNCategoryVO";

	

	/// DESK MENU MASTER////////	
	String IS_TEMPLATE_YES="1";
	String IS_TEMPLATE_NO="0";
	String TEMPLATE_BASED_DESK_MENU_DEFAULT_URL = "GENERICTEMPLATE";
	String ESSENTIALBO_LIST_DESK_TYPE="listOfDeskType";
	String ESSENTIALBO_LIST_TEMPLATE_CATEGORY="deskMenuMasterTreamentCategoryList";
	

	///////drug Dose Master/////////
	
	String ESSENTIALBO_ITEM_TYPE_LIST="itemTypeList";
	String NEW_DRUG_DOSE_MST_DTL_LIST="newDrugDoseMstDtlList";
	String MODIFY_DRUG_DOSE_MST_DTL_VO_LIST="modifyDrugDoseMstDtlVOList";
	String DRUG_CATEGORY="10";


	// Patient Treatment Detail
	String ESSENTIALS_LIST_DOSAGE_FREQUECY="essentialsListDosageFrequency";
	String ESSENTIALS__DOSAGE_FREQUECY_ARRAY="essentialsArrayOfDosageFrequencyVO";
	String ESSENTIALS_LIST_ALL_DRUGS="essentialsListAllDrugs";
	String ESSENTIALS_LIST_ALL_DRUGS_FOR_SEARCH="essentialListAllDrugForSearch";
	String ESSENTIALS_LIST_ALL_DRUG_DOSES="essentialsListAllDrugDoses";
	String ESSENTIALS_LIST_ALL_DRUG_ROUTE="essentialsListAllDrugRoutes";
	String ESSENTIALS_LIST_ALL_DRUG_ADMIN_FLAGS="essentialsListAllDrugAdmin";
	String ESSENTIALS_LIST_ALL_PREGNANT_CATEGORY="essentialsListAllPregCat";
	
	String LIST_DRUG_ROUTE_FOR_PARTICULAR_DRUG="listOfDrugRouteForParticularDrug";
	String ESSENTIALS_LIST_ALL_ITEM_TYPE="essentialsListAllItemType";
	String PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST="patientTreatmentDtlPrevAllDrugDetailList";
	String PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG="patPrevAllDrugAdviceForLog";
	String PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST="patientTreatmentDtlPrevDrugDetailList";
	String PAT_TREATMENT_DTL_DRUG_DETAIL_LIST="patientTreatmentDtlDrugDetailList";
	String PAT_TREATMENT_DTL_SERACH_DRUG_DETAIL_LIST="patientTreatmentDtlSearchDrugDetailList";
	String PAT_TREATMENT_DTL_PREV_ALL_REST_DETAIL_LIST="patientTreatmentDtlPrevRestDetailList";
	String PAT_TREATMENT_DTL_TODAY_REST_DETAIL_LIST="patientTreatmentDtlTodayRestDetailList";
	String PAT_TREATMENT_DTL_TODAY_DRUG_DETAIL_LIST="patientTreatmentDtlTodayDrugDetailList";
	String ESSENTIALS_LIST_ALL_EXT_TREATMENT="essentialsListAllExtTreatmentList";
	String OTHER_INSTRUCTION_LIST_BY_DEPTUNITCODE_AND_GENDER="otherInstructionListByDeptUnitAndGender";
	String ONE_TIME_ACTIVITY_LIST_BY_DEPTUNITCODE_AND_GENDER="oneTimeActivityListByDeptUnitAndGender";
	String PAT_TREATMENT_DTL_EXT_DETAIL_LIST="patExtTreatmentDetailList";
	String PAT_TREATMENT_DTL_PREV_EXT_DETAIL_LIST="patPrevExtTreatmentDetailList";
	String PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST="patientPrevExternelTreatmentDetail";
	String PAT_TREATMENT_DTL_PREV_ALL_DIET_ADVICE_DETAIL_LIST="patientAllPrevDietAdviceDtl";
	String PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE="patPrevDrugSchedule";
	String PAT_TREATMENT_DTL_TODAY_DIET_DETAIL_LIST="patTodayDietAdviceDtl";
	String PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST="allDietTypeList";
	String PAT_TREATMENT_DTL_TODAY_EXT_DETAIL_LIST="patTodayExternalTreatmentDetail";
	String MAX_ENTRY_DATE_BY_CRNO="maxEntryDateByCrNo";
	String MAX_ENTRY_DATE_BY_CRNO_FOR_EXTTREAT="maxEntryDateByCrNoForExtTreat";
	String PREV_OTHER_INST_AND_ACTIVITY_LIST="prevOtherInstructionAndActivityList";
		
	String OTHER_INSTRUCTION_LIST_FOR_SAVE="otherInstructionListForSave";
	
	String DRUG_DOSE_INDICATION_LIST="drugDoseIndicationList";
	String DRUG_SAFTY_ALERT_LIST="drugSaftyAlertList";
	String COLUM_HEADER_MAP_SAFTY_ALERT="columeHeaderMapForSaftyAlert";
	String COLUM_HEADER_MAP_DRUG_DOSE_INDICATION="columHeaderMapForDrugDoseIndication";
	
	String PREV_OTHER_INSTRUCTION_LIST="prevOtherInstructionList";
	String PREV_OTHER_ACTIVITY_LIST="prevOtherActivityList";
	String ALL_OTHER_INSTRUCTION_LIST_BY_GENDER="allOtherInstructionListByGender";
	String ALL_ONE_TIME_ACTIVITY_LIST_BY_GENDER="allOneTimeActivityListByGender";
	
	String OTHER_INSTRUCTION_LIST_FOR_BOTH="otherInstructionListForBoth";
	String ONE_TIME_ACTIVITY_LIST_FOR_BOTH="oneTimeActivityListForBoth";
	
	String SELECTED_CHOICE="selectedChoice";
	String DEFAULT_DRUG_PROFILE_DRUG_LIST="defaultDrugProfileDrugList";
	String DRUGLIST_LIST_FOR_SEARCH="drugListLstForSearch";
	String PARTICULAR_DRUGLIST_DEATIL="particularDrugListDtl";
	String DRUG_STOCK_LIST="drugStockListDtl";
	
	//rx flag
	String REVOKE="0";
	String NEW_RECORD="1";
	String SIMPLE_RX="2";
	String RX_EXTENSION="3";
	String CHANGE_IN_PREV="4";
	
	// Parameter Range Master Gender Flags
	String[] GENDER_FLAG_FOR_ARR = {"Both","Male","Female","Not Required"};
	String GENDER_FLAG_FOR_BOTH="0";
	String GENDER_FLAG_FOR_MALE="1";
	String GENDER_FLAG_FOR_FEMALE="2";
	String GENDER_FLAG_FOR_NOT_REQUIRED="3";
	
	//Admin Flag	
	String SCHEDULE="1";
	String DRUG_GIVEN_IN_PER_SCHEDULE="2";
	String EXTRA_DOSE_GIVEN="3";
	String DRUG_GIVEN_WITHOUT_SCHEDULE="4";
	
	
	String CONSNET_REQUEST_STATUS="0";
	String SERVICE_TYPE_FOR_CONSENT="13";
	String IS_EPISODE_OPEN="1";
	
	
	String MORNING_SHIFT_ID="0";
	String NOON_SHIFT_ID="1";
	String EVENING_SHIFT_ID="2";
	String NIGHT_SHIFT_ID="3";
	
	
	String[] TREATMENT_ADVICE_SHIFT_NAME_ARRAY={"Morning", "Noon", "Evening", "Night"};
	String[] TREATMENT_ADVICE_SHIFT_START_HOURS_TIME={"06", "12", "17", "20"};
	String[] TREATMENT_ADVICE_SHIFT_START_MINUT_TIME={"00", "00", "00", "00"};
	String[] TREATMENT_ADVICE_SHIFT_END_HOURS_TIME={"11", "16", "19", "06"};
	String[] TREATMENT_ADVICE_SHIFT_END_MINUT_TIME={"59", "59", "59", "59"};
	
	       	
	String IS_FREQUENCY_BOUND="1";
	String IS_NOT_FREQUENCY_BOUND="0";
	
	String IS_MASTER_BOUND="1";
	String IS_NOT_MASTER_BOUND="0";
	
	String ENTRY_MODE="1";
	
	
	//treatment type
	String EXTERNAL_TREATMENT="1";
	String ONE_TIME_ACTIVITY="2";
	String OTHER_INSTRUCTION="3";
	
	String FOR_ALL="0";
	String FOR_MALE_ONLY="1";
	String FOR_FEMALE_ONLY="2";

	String PAT_TREATMENT_DTL_RXCONTINUE_DRUG_DETAIL_LIST="patTreatmentRxContinueDetailList";
	String PAT_TREATMENT_DTL_REVOKE_DRUG_DETAIL_LIST="patTreatmentRevockDetailList";
	String PAT_TREATMENT_DTL_REPEAT_DRUG_DETAIL_LIST="patTreatmentRepeatDetailList";
	
	

	String EMPTY_STOMACH="1";
	String NOT_EMPTY_STOMACH="0";
	String LIST_OF_DRUGDOSE_FOR_POPUP="listOfDrugDoseForPopup";
	String DRUG_SHEDULE_MAP="drugSheduleMap";
	String PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG="prevDrugScheduleListForParticularDrug";
	String PAT_DRUG_LOG="patDrugLog";
	String PAT_PREGNANT_YES="1";
	String PAT_PREGNANT_NO="0";

	////////////// PATIENT ALERT/////////////////
	String ARR_ASSIGNED_PATIENT_ALERT_VO="arrAssignedPatientAlertVO";
	String ESSENTIAL_ALL_PAT_ALERT_LIST="essentialAllpatAlertList";
	String ARR_ADDED_PATIENT_ALERT="arrAddedPatientAlert";
	String ARR_ALL_PATIENT_ALERT_VO="arrAllPatientAlertVO";
	String PATIENT_ALERT_REVOKED_YES="1";
	String PATIENT_ALERT_REVOKED_NO="0";
	
	/* ******************** Template Mapping ****************************/
	
	String TEMPLATE_MAPPING_DEPT_NOT_ASSIGNED="deptListNotAdded";
	String TEMPLATE_MAPPING_UNIT_NOT_ASSIGNED="unitListNotAdded";
	String TEMPLATE_MAPPING_WARD_NOT_ASSIGNED="wardListNotAdded";
	String TEMPLATE_NOT_ASSIGNED="templateListNotAdded";
	String TEMPLATE_ASSIGNED="templateListAdded";
	String TEMPLATE_MAPPING_DETAIL="templateMappingDetail";
	String TEMPLATE_CATEGORY_NAME="templateCategoryName";
	

	String ALL_MENU_LIST="allMenuList";
	String DESC_TYPE_DESC="descTypeDesc";
	String SELECTED_MENU_LIST="selectedMenuList";
	String REMANING_MENU_LIST="remaningMenuList";
	

	String[] DRUG_ROUTE_CLASSIFICATION_ARRAY = {"","Tropical","Enteral","Parental","Other"};
	
	String DRUG_ROUTE_CLASSIFICATION_LIST="drugRouteClassificationLst";

	
	
	////////    EXTERNAL INVESTIGATION    ////////////
	String EXT_INV_PARAMETER_LIST="extInvParameterList";
	String ARR_EXTERNAL_INVESTIGATION_DTL="arrExternalInvestigationDtl";
	String ARR_ADDED_EXTERNAL_INVESTIGATION_DTL="arrAddedExternalInvestigationDtl";
	
	String EXT_INV_ENTRY_MODE_BY_PATIENT="0";
	String EXT_INV_ENTRY_MODE_BY_HOSPITAL="1";
	String EXT_INV_ENTRY_MODE_BY_PORTAL="2";
	
	String TEST_CONDUCTED_IN_HOUSE="0";
	String TEST_CONDUCTED_FROM_EXT_LAB="1";

	
	//////Profile Access Policy///////////
	
	String PROFILE_ACCESS_POLICY_POLICY_TYPE_RESTRICTED="2";
	String PROFILE_ACCESS_POLICY_POLICY_TYPE_NORMAL="1";

	//Desk Wise Default Profile Master
	
	String PROFILE_BOUND_ONE="1";
	String DESK_TYPE_LIST="deskTypeLst";
	String DESK_NAME_LIST="deskNameLst";
	String DEFAULT_MENU_NAME_LIST="defaultmenuNameLst";
	String ALL_MENU_NAME_LIST="allmenuNameLst";
	String NON_DEFAULT_MENU_NAME_LIST="nondefaultmenuNameLst";
	String USABILITY_FLAG_TWO="2";
	String USABILITY_FLAG_ONE="1";
	String IS_PROFILE_DEFAULT_YES="2";
	String IS_PROFILE_DEFAULT_NO="1";

	////////////// Macro Master Process Id List /////////////////////
	String MACRO_PROCESS_ID_PROGRESS_NOTES = "1";			// Nurse Progress Notes
	String MACRO_PROCESS_ID_DOCTOR_INSTUCTIONS = "2";		// Doctor Instructions
	String MACRO_PROCESS_ID_VISIT_NOTES = "3";				// Doctor Visit Notes	
	//String MACRO_PROCESS_ID_REACTION_SUMMARY="3";   
	String MACRO_PROCESS_ID_BULLETIN_BOARD = "4";			// Bulletein Board Patient Status
	String MACRO_PROCESS_ID_MLC_PATIENT_CONDITION = "5";	// MLC Patient Condition
	String MACRO_PROCESS_ID_MLC_PATIENT_DIAGNOSIS = "6";	// MLC Patient Diagnosis
	String MACRO_PROCESS_ID_CALL_BOOK_NURSE_REMARKS = "7";	// Call Book Nurse Remarks
	String MACRO_PROCESS_ID_CALL_BOOK_DOCTOR_REMARKS = "8";	// Call Book Doctor Remarks
	
	String MACRO_PROCESS_ID_ANC_VISIT_REMARKS = MACRO_PROCESS_ID_VISIT_NOTES;	
	String MACRO_PROCESS_ID_VISIT_SUMMARY = MACRO_PROCESS_ID_VISIT_NOTES;

	
	/////////////////OT EMR////////////////////////////////////
	String OT_NAME_ACTUAL="1";
	///////////////////////////////////////////////////////////

	
	//Patient Profile Review Detail
	
	String PATIENT_PROFILE_REVIEW_DETAIL_PREVIOUS_PROFILE_VO_ARRAY="patientProfileReviewDetailPreviousProfileVOArray";
	String PATIENT_PROFILE_REVIEW_DETAIL_PREVIOUS_REVIEW_DTL_VO_ARRAY="patientProfileReviewDetailPreviousReviewDtlVOArray";
	
	String LIST_UNITS_NOTIN_TABLE="lstUnitsNotInTable";
	
	String RELATIONSHIP_CODE_FOR_SELF="10";
	
	//////////// Next Visit Detail/Visit Summary ///////////
	
	String[] OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW = {"SOS","Days","","","Scheduled Date"};
	String[] OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR = {"SOS","Days","Weeks","Months",""};
	String OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS = "0";
	String OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS = "1";
	String OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC = "1";
	String OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_WEEKS_SPECIFIC = "2";
	String OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_MONTHS_SPECIFIC = "3";
	String OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE = "4";

	String OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST = "opdEssentailAllVisitOfEpisodeList";
	String OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST = "opdEssentailAllVisitSummaryOfCurrentEpisodeVisitList";
	String OPD_ESSENTIAL_ALL_KEYWORDS_LIST = "opdEssentailAllKeywordsList";
	String OPD_DYNAMIC_VISIT_DETAIL_MAP = "opdDynamicVisitDetailMap";
	String OPD_EPISODE_VISIT_DIAGNOSIS_DETAIL = "opdEpisodeVisitDiagnosisDetail";
	
	
	////////////// Generic Macro Popup //////////////////	
	String GENERIC_MACRO_POPUP_LIST_BY_PROCESS_ID = "genericMacroPopupListByProcessID";
	
	////////////// Opd Roster Schedule Popup //////////////////	
	String OPD_ROSTER_SCHEDULE_POPUP_LIST_OF_SCHEDULE_DATES = "opdRosterSchedulePopupListOfScheduleDates";

	
	//Profile Restricted Category master
	
	String PROFILE_RESTRICTED_CATEGORY_PATIENT_CATEGORY_LIST="profileRestrictedCategoryPatientCategoryList";
	String PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST="profileGroupMasterDeptUnitCodeList";
	String ALL_PATIENT_PRIMARY_CATEGORY_LIST="allPatientPrimaryCategoryList";
	String PATIENT_PRIMARY_CATEGORY_MAPPED_WITH_PROFILE_TYPE_LIST="categoryMappedWithProfileTypeList";
	String PATIENT_PRIMARY_CATEGORY_NOT_MAPPED_WITH_PROFILE_TYPE_LIST="categoryNotMappedWithProfileTypeList";
	
	//Profile Group Detail
	
	String PROFILE_GROUP_DETAIL_GROUP_LIST="profileGroupDetailGroupList";
	String PROFILE_GROUP_DETAIL_ADDED_UNIT_LIST="profileGroupDetailAddedUnitList";
	String PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST_ALL="profileGroupMasterDeptUnitCodeListAll";
	String PROFILE_GROUP_DETAIL_ALL_USERS="profileGroupDetailAllUsers";
	String PROFILE_GROUP_DETAIL_USER_PRESENT="1";
	String PROFILE_GROUP_DETAIL_USER_NOT_PRESENT="0";
	
	// Profile Access Policy
	String PROFILE_ACCESS_POLICY_GROUP_LIST="profileAccessPolicyGroupList";
	String PROFILE_ACCESS_POLICY_POLICY_TYPE_RESTRICTED_LABEL="Restricted";
	String PROFILE_ACCESS_POLICY_POLICY_TYPE_NORMAL_LABEL="Normal";

	
	
	///////////////// Casulaty Desk Patient List /////////////////////////////////
	
	String CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING = "1";
	String CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED = "2";
	String CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE = "3";	
	
	String CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING = "casualtyDeskPatientListPatientDtlVOWaiting";
	String CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED = "casualtyDeskPatientListPatientDtlVOAttended";
	String CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE = "DeskPatientListPatientDtlVOInTriage";
	
	String DRUG_LIST_FOR_PRINTING="drugListForPrinting";
	String PAT_DETAIL_VO_FOR_PRINTING="patDetailVoForPrinting";
	
	
	//ipdOpdFlag for treatment
	
	String IS_OPD_TREATMENT="0";
	String IS_IPD_TREATMENT="1";

	String IS_NOT_DISCHARGE_ADVICE="0";
	String IS_DISCHARGE_ADVICE="1";
	String PREV_ALL_DIET_ADVICE_DTL_FOR_PRINTING="prevAllDietAdviceDtlForPrinting";
	String PREV_REST_DETAIL_LIST_FOR_PRINTING="prevRestAdviceDtl";
	

	//ExtTreatmapping master
	
	String ALL_CLINICAL_DEPARTMENT_LIST="allClinicalDepartmentMaster";
	String ALL_UNIT_LIST="allUnitList";
	String NOT_MAPPED_ALL_UNIT_LIST="notMappedAllUnitList";
	String ALL_EXT_TREATMENT_LIST="allExtTreatList";
	String MAPPED_UNIT_EXT_TREATMENT_VO_ARRAY="mappedExtTreatmentVOArray";
	String MAPPED_UNIT_EXT_TREATMENT_LIST="mappedExtTreatmentLst";
	String LIST_ALL_EXT_TREAT_NOT_IN_SELECTED_BASED_ON_UNIT="lstAllExtTreatNotInSelectedBasedOnUnit";
 

	///Patient Attendant Detail
	String PATIENT_ALL_ATTENDANT_VO_LIST="patientAllAttendantVOList";
	String PATIENT_EPISODE_ATTENDANT_LIST="patientEpisodeAttendantList";
	String PATIENT_TODAY_VISITED_EPISODE="patientTodayVisitedEpisode";
	String ALL_ATTENDANT_REASON_LIST="allAttendantReasonList";
	String PATIENT_FATHER_MOTHER_SPOUCE_NAME="patientFatherMotherSpouceName";
	String PATIENT_ATTENDANT_EXISTING="1";
	String PATIENT_ATTENDANT_NEW="2";
	
	///unit macro mapping
	String ALL_MACRO_LIST="allMacroList";
	String ALL_MACRO_PROCESS_LIST="allMacroProcessList";
	String MAPPED_UNIT_MACRO_VO_ARRAY="mappedUnitMacroVOArray";
	String MAPPED_UNIT_MACRO_LIST="mappedUnitMacroList";
	String LIST_ALL_MACRO_NOT_IN_SELECTED_BASED_ON_UNIT="lstAllMacroNotInSelectdeBasedOnUnit";
	
	//Profile type tab mapping
	String DESK_MENU_ADDED_TO_PROFILE_TYPE_LIST="deskMenuAddedToProfileType";
	String DESK_MENU_NOT_ADDED_TO_PROFILE_TYPE_LIST="deskMenuNotAddedToProfileType";

	String SELECTED_PATIENT_PROFILE="selectPatientProfile";

	//unit image descrition master
	String ALL_IMAGE_DESCRIPTION_WITH_COLOR="allImageDescWithColour";
	String MAPPED_UNIT_IMAGE_DESC_VO_ARRAY="mappedUnitimageDescVoArray";
	String MAPPED_IMAGE_DESC_LIST="mappedImageDescLst";
	String LIST_ALL_IMAGE_DESC_NOT_IN_SELECTED_BASED_ON_UNIT="listOfAllImageDescNOtInSelectedByUnit";
	
	// keyword master
	String KEYWORD_MST_VO_FOR_MODIFY="keywordMstVO";
	
	//DRUG LIST ITEM MASTER
	String DRUG_DOSE_LIST_FOR_PARTICULAR_ITEMTYPE="drugDoseListForItemType";
	String SELECTED_DRUG_LIST="selectedDrugList";
	String ESSENTIALS_LIST_ALL_DRUGLIST_NAME_LIST="allDrugListNameList";
	String STATUS_FLAG_FOR_MODIFY="1";
	String STATUS_FLAG_FOR_ADD="0";
	
	//UNIT DRUG LIST MAPPING MASTER
	String ALL_DRUGLIST_LIST="allDrugListList";
	String MAPPED_UNIT_DRUG_LIST_VO_ARRAY="mappedUnitDrugListVoArray";
	String MAPPED_UNIT_DRUGLIST_LIST="mappedDrugListLst";
	String LIST_ALL_DRUGLIST_NOT_IN_SELECTED_BASED_ON_UNIT="allDrugListNotInSelectedBasedonUnit";
	String IS_DEFAULT_YES="1";
	String IS_DEFAULT_NO="0";
	
	String OPD_ESSENTIAL_ALL_EPISODE_KEYWORD_LIST = "opdEssentialAllEpisodeKeywordList";
	String OPD_ESSENTIAL_SELECTED_KEYWORD_LIST = "opdEssentialselectedKeywordList";
	String OPD_ESSENTIAL_UNSELECTED_KEYWORD_LIST="opdEssentialUnselectedKeywordList";
	
	// ICD Include Exclude Master
	String OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP = "opdEssentialsAllUsedICDGroupList";
	String OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP = "opdEssentialsAllUsedICDSubgroupList";
	String OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE = "opdEssentialsAllUsedICDSubgroupListGroupWise";
	String OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBDISEASE = "opdEssentialsAllICDDiseaseSubdiseaseList";
	String OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE = "opdEssentialsAllICDDiseaseSubgroupWise";
	String OPD_ESSENTIAL_GROUP_NAME = "opdEssentialsGroupName";
	String OPD_ESSENTIAL_SUBGROUP_NAME = "opdEssentialsSubgroupName";
	String OPD_ESSENTIAL_DISEASE_SUBDISEASE_NAME = "opdEssentialsDiseaseSubdiseaseName";
	
		// Disease Master Record Type
	String[] ICD_DISEASE_RECORD_TYPE_ARR = {"Disease", "Include", "Exclude", "Synonym"};
	String ICD_DISEASE_RECORD_TYPE_DISEASE = "0";
	String ICD_DISEASE_RECORD_TYPE_INCLUDE = "1";
	String ICD_DISEASE_RECORD_TYPE_EXCLUDE = "2";
	String ICD_DISEASE_RECORD_TYPE_SYNONYM = "3";
		// Disease Commonly Used
	String[] ICD_DISEASE_IS_COMMONLY_USED_ARR = {"Not Common", "Common"};
	String ICD_DISEASE_IS_COMMONLY_USED_NOTCOMMON = "0";
	String ICD_DISEASE_IS_COMMONLY_USED_COMMON = "1";

	// Disesae Site Master
	String[] ICD_DISEASE_SITE_CODETYPE_ARR = {"Not Required", "ICD Subgroup", "ICD Disease"};
	String ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED = "0";
	String ICD_DISEASE_SITE_CODETYPE_ICD_SUBGROUP = "1";
	String ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE = "2";
	
	// Charts
	String[] CHART_CATEGORY_ARR = {"OPD & IPD", "OPD", "IPD"};
	String CHART_CATEGORY_OPD = "1";
	String CHART_CATEGORY_IPD = "2";
	String CHART_CATEGORY_OPD_IPD = "0";
	
	String[] CHART_GENERATION_TYPE_ARR = {"","Row Wise","Column Wise"};
	String CHART_GENERATION_TYPE_ROW_WISE = "1";
	String CHART_GENERATION_TYPE_COLUMN_WISE = "2";
	String CHART_GENERATION_TYPE_COLUMN_WISE_LABEL = "Column Wise";
	String CHART_GENERATION_TYPE_ROW_WISE_LABEL = "Row Wise";
	
	String CHART_NAME_LIST = "chartNameList";

	String[] CHART_PARAMETER_TYPE_ARR = {"", "Clinical", "Investigation", "Intake Output"};
	String CHART_PARAMETER_TYPE_CLINICAL = "1";
	String CHART_PARAMETER_TYPE_INVESTIGATION = "2";
	String CHART_PARAMETER_TYPE_INTAKE_OUTPUT = "3";
	
	String ALL_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL = "allParameterListOfParameterTypeClinical";
	String ALL_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION = "allParameterListOfParameterTypeInvestigation";
	String ALL_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT = "allParameterListOfParameterTypeIntakeOutput";

	String PARA_NOT_ADDED_TO_CHART_PARA_LIST = "parameterNotAddedToChartParameterList";
	String UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL = "unaddedParameterListOfParameterTypeClinical";
	String UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION = "unaddedParameterListOfParameterTypeInvestigation";
	String UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT = "unaddedParameterListOfParameterTypeIntakeOutput";

	String PARA_ADDED_TO_CHART_PARA_LIST = "parameterAddedToChartParameterList";
	String ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL = "addedParameterListOfParameterTypeClinical";
	String ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION = "addedParameterListOfParameterTypeInvestigation";
	String ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT = "addedParameterListOfParameterTypeIntakeOutput";

	String CHOICE_EPISODE_WISE = "0"; 
	String CHOICE_DATE_WISE = "1"; 
	
	String CHART_GRAPH_GENERATED = "graphgenerated";
	String CHART_GRAPH_NOT_GENERATED = "graphNot Generated";

	// Unit Mapping Default Mode
	String IS_DEFAULT_CURRENT_VISIT = "1";
	String IS_DEFAULT_COMPLETE_EPISODE = "2";
	
	String OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE = "opdEssentialsAllDeskNUnitWiseChartsList";
	String OPD_ESSENTIAL_DATE_EPISODE_OR_ADMISSION_START = "opdEssentialsEpisodeOrAdmissionStartDate";
	String OPD_GENERIC_CHART_MAP_OF_CHART_HEADER_DETAIL = "opdGenericChartMapOfChartHeaderDetail";
	String OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL = "opdGenericChartMapOfChartDetail";
	String OPD_GENERIC_CHART_MAP_OF_CHART_DETAIL_KEYS_AS_AARAY = "opdGenericChartMapOfChartDetailKeysAsArr";
	String OPD_ESSENTIAL_LIST_ALL_CHARTS_PARAMETERS = "opdEssentialsAllDeskNUnitWiseChartParameters";
	String OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS = "opdEssentialsAllDefaultChartsDetailsMap";
	
	// Chart Unit Mapping 
	
	String LIST_ALL_CHARTLIST_NOT_IN_SELECTED_BASED_ON_UNIT = "allChartListNotSelectedBasedOnUnit";
	String MAPPED_UNIT_CHARTLIST_LIST = "chartListMappedWithUnit";
	String ALL_UNIT_LIST_NOT_MAPPED_WITH_CHART = "unitNotMappedWithChart";
	
	String NONE="0";
	String CURRENT_VISIT = "1";
	String COMPLETE_EPISODE = "2";
	
	String MAPPED_UNIT_CHART_LIST_VO_ARRAY = "mappedUnitChartListVoArray";

	String ALL_UNIT_LIST_NOT_MAPPED = "unitNotMapped";
	String MAPPED_UNIT_LIST = "mappesUnitList";
	String ALL_DISEASE_LIST_NOT_MAPPED ="notMappedDisease";
	String MAPPED_DISEASE_LIST_WITH_UNIT ="diseaseMappedWithUnit";
	String MAPPED_UNIT_ICD_DISEASE_LIST_VO_ARRAY= "mappedUnitIcdDiseaseListVOArray";
	String DISEASE_LIST_NOT_MAPPED = "notMappedDiseaseList";
	String MAPPED_DISEASE_LIST = "mappedDisease";
	
	
	// Department Unit Hospital Disease Master
	String LIST_OF_ALL_CLINICAL_DEPARTMENT= "listOfAllDepartment";
	String DEPT_WISE_LIST_OF_ALL_NOT_MAPPED_UNIT = "listOfNotMappedUnit";
	String LIST_OF_UNIT_MAPPED_WITH_DEPT= "listOfMappedUnit";
	String ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE = "listOfHospitalDisease";
	String LIST_OF_MAPPED_HOSPITAL_DISEASE_WITH_UNIT = "listOfMappedHosDisease";
	String LIST_OF_MAPPED_UNIT_HOSPITAL_DISEASE_VO_ARRAY = "listOfMappedDiseaseWithUnit";
	String LIST_OF_HOSPITAL_DISEASE = "listOfDisease";
	String LIST_NOT_MAPPED_HOSPITAL_DISEASE ="listOfNotMappedDisease";
	
	
	// ICD Mapping Master
	String OPD_ESSENTIAL_LIST_ALL_CHRONIC_DISEASE= "chronicDiseaseList";
	String OPD_ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE="hospitalDiseaseList";
	String OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE="";
	
	String[] MAPPING_TYPE_ARR = {"","Hospital Disease", "Chronic Disease", "Disease Site"};
	
	String HOSPITAL_DISEASE = "1";
	String CHRONIC_DISEASE = "2";
	String DISEASE_SITE = "3";
	
	String OPD_ESSENTIAL_LIST_ICD_GROUP="icdGroupList";
	String OPD_ESSENTIAL_LIST_ICD_SUBGROUP_GROUPWISE="icdSubGroupList";
	String OPD_ESSENTIAL_LIST_ICD_DISEASE_SUBGROUPWISE="diseaseListSubgroupWise";
	String MAPPED_ICD_DISEASE_LIST_WITH_HOSPITAL_DISEASE="listOfMappedDiseaseWithHospitalDisease";
	String MAPPED_ICD_DISEASE_LIST_WITH_CHRONIC_DISEASE="listOfMappedDiseaseWithChronicDisease";
	String LIST_OF_MAPPED_ICD_DISEASE_WITH_MAPPING_TYPE="listOfMappedDisease";
	String MAPPED_ICD_DISEASE_LIST = "listOfMappedIcdDisease"; 
	String ICD_DISEASE_LIST_NOT_MAPPED ="listOfNotMappedIcdDisease";
	String MAPPED_ICD_DISEASE_LIST_VO_ARRAY = "listOfMappedDiseaseArray";
	String ICD_MAPPED_DISEASE="mappedDisease";
	String OPD_ESSENTIAL_LIST_ICD_DISEASE_POPUP="diseaseListInPopUp";
	String OPD_ICD_DISEASE_LIST_FOR_POPUP="diseaseList";
	
	
	
	
	// Icd Index Level Master
	
	String OPD_ESSENTIAL_LIST_ICD_INDEX_TERM="icdIndexTermList";
	String OPD_ESSENTIAL_LIST_ICD_PARENT_MODIFIER="icdParentModifierList";
	
	String OPD_LIST_ICD_GROUP="icdGroupList";
	String OPD_LIST_ICD_SUB_GROUP="icdSubGroupList";
	String OPD_LIST_ICD_DISEASE="icdDiseaseList";
	
	String OPD_LIST_DUAL_ICD_SUB_GROUP="dualIcdSubGroupList";
	String OPD_LIST_DUAL_ICD_DISEASE="dualIcdDiseaseList";


	//ICD Cross Reference Master
	
	String OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_TERM="icdCrossModifierLevel1";
	String OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL2_TERM="icdCrossModifierLevel2";
	String OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL3_TERM="icdCrossModifierLevel3";
    String OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL4_TERM="icdCrossModifierLevel4";
    String OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL5_TERM="icdCrossModifierLevel5";
    String OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL6_TERM="icdCrossModifierLevel6";
    String OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL7_TERM="icdCrossModifierLevel7";
    String OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL8_TERM="icdCrossModifierLevel8";
    String OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL9_TERM="icdCrossModifierLevel9";
    String OPD_ESSENTIAL_LIST_SEE_TERMS="icdCrossSeeTerms";

	String OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_REFTERM="icdCrossModifierLevel1ForRef";

	String OPD_PAGE_FLAG_ADD="addpage";
    String OPD_PAGE_FLAG_MODIFY="modifypage";


    
    //**************** ICD Entry Form
	String ICD_ENTRY_FORM_NEW_ENTRY = "NEWENTRY";
	String ICD_ENTRY_FORM_MODIFICATION = "MODIFICATION";
	
	String TAB_DSK_JS_FUNC_ON_CLICK="change_tab";
	String TAB_DSK_HTML_MODE_FIELD_NAME="deskMode";	
	String TAB_GROUP="Tab_Group";

	String ICD_ENTRY_FORM_PATIENT_LIST = "icdEntryformPatientList";
	String ICD_ENTRY_FORM_PATIENT_MAP = "icdEntryformPatientMap";
	String ICD_ENTRY_FORM_SELECTED_PATIENT_SET = "icdEntryformSelectedPatientSet";
	String ICD_ENTRY_FORM_SEEN_PATIENT_SET = "icdEntryformSeenPatientSet";
		// Configurations
	String DIAGNOSIS_TYPE_PROVISIONAL = "11";
		// ICD Entry Status set in HSCN_DOCUMENT_MST
	String[] ICD_ENTRY_FORM_ICD_FLAG_ARR = {"Not Seen","ICD Not Available","ICD Entered"};
	String ICD_ENTRY_FORM_ICD_FLAG_UNREAD = "0";
	String ICD_ENTRY_FORM_ICD_FLAG_READ_NOT_ICD = "1";
	String ICD_ENTRY_FORM_ICD_FLAG_READ_AND_ICD = "2";
    
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
	
	String INPATIENT_IS_DEAD_YES="1";
	String INPATIENT_IS_DEAD_NO="0";
	
	///Postmortem Status
	String POSTMORTEM_STATUS_REQUEST_RAISED="1";
	String POSTMORTEM_STATUS_REQUEST_APPROVED="2";
	String POSTMORTEM_STATUS_REQUEST_CANCELED="3";
	String POSTMORTEM_STATUS_REQUEST_WAVEOFF="4";
	String POSTMORTEM_STATUS_REQUEST_POSTMORTEM_ENTRY="5";
	String POSTMORTEM_STATUS_REQUEST_POSTMORTEM_DONE="6";
	
	/*Setting processId for Employee List*/
	///////////Process ID/////////////////
	String DECEASED_ACCEPTANCE_PROCESS_ID="6";
	String DECEASED_ACCEPTANCE_BROUGHT_BY_PEON="6";
	String POSTMORTEM_REQUEST_APPROVED="11";
	
	
	
	//************    Reports JRXML ******************************************
	String OPD_DISEASE_WISE_PAT_IN_OUT="DiseaseWisePatientsInOut.jrxml";
	String OPD_DISEASE_WISE_PAT_ALL="DiseaseWisePatAttendedAllReport.jrxml";
	
	
	/****************** Query File Name ******************/
	String QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO="inpatient.inpatientEssentialQuery";
	String QUERY_FILE_FOR_INPATIENT_DAO="inpatient.inpatientQuery";
	
	String REPORTS_MINIMUM_YEAR="reportsMinYear";
	String REPORTS_LIST_OF_MIN_YEARS="reportsListOfMinYear";
	
	//*********** Patient Complaint****************/
	String[] PATIENT_COMPLAINTS_DURATION_VALUES_ARR = {"<1 Month","1-3 Month","3-6 Month","6-12 Month","1-2 Year","2-3 Year",">3 Year"};
	
	//******Episode Visit Summary Details*****/
	String OPD_ESSENTIAL_ALL_EPISODE_VISIT_LIST = "opdEssentailAllEpisodeVisitDetailList";

	String PROC_FOR_PATIENT_SUMMARY = "PKG_DYNAMIC_DESK_VIEW.GET_PATIENT_SUMMARY";
	
	String PROC_GET_DAILY_PATIENT_DETAIL= "{call PKG_OPD_VIEW.proc_hrgt_daily_patient_dtl(?,?,?,?,?,?,?)}";
	String PROC_GET_PATIENT_VISIT_DTL= "{call PKG_OPD_VIEW.proc_dss_patient_visit_dtl(?,?,?,?,?,?)}";
	String PROC_GET_PATIENT_REFERRED_DTL= "{call PKG_OPD_VIEW.proc_hrgt_episode_ref_dtl(?,?,?,?,?,?)}";
	
	String OPD_ESSENTIAL_REEERRED_DETAIL_LIST = "opdEssentailResferredDetailList";
	//**********Global Mapping Type Details******/
	
	String EssentialBO_LIST_DEPT="essentialBoListDept";
	
	
	//**********Drug Details via DB_Link***************//
	//Created By: Chetan Sharma //
	// Date: 2015_12_4          //
	
	String PROC_OPD_VIEW_DRUGS_DETAILS = "{call pkg_opd_view.proc_HSTT_DRUGBRAND_MST(?,?,?,?,?,?,?)}";   //for Drug Brand list
    String PROC_OPD_VIEW_GET_ITEM_TYPE_DETAILS ="{call pkg_opd_view.proc_HSTT_ITEMTYPE_MST(?,?,?,?,?,?)}";
	
    String lstDrugs="1";
	String serachDrugList="2";
	String lstDrugAdminFlag="1";
	String lstPregCat="1";
	
	String lstItemTypes="1";
	
	//**Generic Drug Details via DB_Link**//
	//Created By: Manisha Gangwar //
	// Date: 06.04.2016         //
	String PROC_OPD_VIEW_GENERICBRAND_BYCONFIG_DRUGS_LIST = "{call pkg_opd_view.proc_getBrandNonBrandDrugList_byConfig(?,?,?,?,?,?,?)}"; 
	String PROC_OPD_VIEW_GENERIC_DRUGS_LIST = "{call pkg_opd_view.proc_hstt_drug_mst(?,?,?,?,?,?,?)}";    //for Generic Drug list
	String PROC_OPD_VIEW_DRUG_ADMIN_FLAG_LIST = "{call pkg_opd_view.proc_cims_drug_admin_mst_sys(?,?,?)}";    //for drugAdminFlagsList
	String PROC_OPD_VIEW_PAT_PREGNANT_CATEGORY_LIST = "{call pkg_opd_view.proc_cims_preg_cat_mst_sys(?,?,?)}";    //for drugAdminFlagsList
	String FUNC_EMR_VIEW_PAT_DRUG_ADVICE_ALERTS = "{? =call pkg_emr_view.pat_drug_advice_alerts(?,?,?,?,?,?,?,?,?,?,?,?)}"; //for drug Treatment alerts
	String PROC_OPD_VIEW_DRUG_STOCK_LIST = "{call pkg_opd_view.proc_get_stock_dtl(?,?,?,?,?,?)}"; //for Drug Stock List added by Shruti Shail on 10-Feb-2017

	String ALLERGY_TYPE_CODE_MISCELLANEOUS ="10";
	//addedby:Neha
		String ADVISED_BY_LIST="advisedby";
		
		String PATIENT_INVESTIGATION_PROFILE = "patientInvestigationProfile"; //Added by Vasu on 06-Nov-2017
		
		String IPD_FLAG="ipdFlag";
		/**Added by Vasu on 05.Oct.2018 for ICD-O Integration*/
		String CHOICE_ICDO_CODE = "9";
		String CHOICE_ICD_DEFAULT_ICDO_CODE = "10";	
		String CHOICE_ICDO_DEFAULT_ICD_CODE = "11";
		String DIAGNOSIS_CODE_TYPE_ICDO="4";
		String EssentialBO_DIAGNOSIS_SITE_LIST_UNIT_WISE = "diagnosisSiteListUnitWise";
		String EssentialBO_MORPHOLOGY_LIST_UNIT_WISE = "morphologyListUnitWise";
		
		//Added by Dheeraj 
		String QUERY_FILE_FOR_UNIPAGE_DAO = "opd.opdEssentialQuery";
		String COMPOSITION_TYPE = "compositionType";
		String CLINICAL_SECTION = "clinicalSection";
		String CLINICAL_SUB_SECTION = "subClinicalSection";
		String INTERFACE_TYPE = "interfaceType";
		String SECTION_INTERFACE = "sectionInterface";
		String GNUM_IS_VALID = "1";
		
		//Added by Vasu on 17.Dec.2018
		String ENTRY_MODE_FOR_DISCHARGE_ADVICE_DRUG = "3";
		
		//Added by prachi
		String SECTION_TYPE = "section";
		String TEMPLATE_TYPE = "template";
		
		//Added by Shweta on 12-03-2019
		String ESSENTIALBO_LIST_CLINICAL_SECTION="singlePageInterfaceMasterClinicalSectionList";
		String ESSENTIALBO_LIST_INTERFACE_TYPE="singlePageInterfaceMasterInterfaceTypeList";
		
		//Added by prachi
				
				String ESSENTIAL_DATA = "essentialdata";
				
				String SECTION_TEMPLATE_MAPPING_LIST ="sectionTemplateMappingList";
				
		
	   //Added by Vasu on 18.April.2019
		String PATIENT_PROFILE_FOOTER_ESSENTIALS = "patientProfileFooterEssentials";
		String PATIENT_PROFILE_FOOTER_CONSULTANT_NAME = "patientProfileFooterConsultantName";
		String TEST_NAME_INVESTIGATION_EHR_SECTION="investigationtestname";
		String IMAGE_EXAM_SOURCE="imageexamsource";
		String IMAGE_EXAM_LIST="imageexamlist";
		String IMAGE_EXAM_PREV_LIST="imageexamprevlist";
} 
