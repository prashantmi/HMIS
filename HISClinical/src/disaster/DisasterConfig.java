package disaster;

public interface DisasterConfig {

	String QUERY_FILE_FOR_MASTERSDAO  ="disaster.disasterMasterQuery";
    String QUERY_FILE_FOR_DAO		  ="disaster.disasterQuery";
    
	
	String ESSENTIAL_BO_OPTION_DISASTER_TYPE_LIST="essentialOptionDisasterTypeList";
	String ESSENTIAL_BO_OPTION_DISASTER_TYPE_LIST_DISASTER_TYPE_WISE="essentialOptionDisasterTypeListDisasterTypeWise";
	String ESSENTIAL_BO_OPTION_CONFIRM_BY_LIST="essentialOptionConfirmByList";
	String ESSENTIAL_BO_OPTION_DISASTER_BOX_LIST="essentialOptionDisasterBoxList";
	String ARRAY_KEY_BOUND_DISASTER_AREA_VO="arrayKeyBoundDisasterAreaVo";
	String ALREADY_ASSIGNED_KEY_DETAIL_VO_ARRAY="arrayAlreadyAssignedKeyDetailVO";
	String PREVIOUS_KEY_DETAIL_VO_ARRAY="previousKeyDetailVO";
	String ESSENTIAL_KEY_STATUS_LIST="essentialKeyStatusList";
	String SELECTED_VO_FOR_KEY_ASSIGNMENT="selectedVoForKeyAssignment";
	String SELECTED_AREA_DETAIL_VO="selectedAreaDetailVO";
	String ESSENTIAL_OPTION_DESIGNATION="essentialOptionDesignation";
	String EMPLOYEE_ENQUIRY_VO="employeeSearchVO";
	String ARRAY_ACTIVATED_DISASTER_VO="arrayActivatedDisasterVO";
	String SELECTED_DISASTER_VO="selectedDisasterVO";
	String PREVIOUS_DISASTER_BULLETIN_VO="arrayPreviousDisasterBulletinVO";
	String CURRENT_DISASTER_BULLETIN_VO="currentDisasterBulletinVO";
	String ARRAY_QRT_MASTER_VO="arrayQrtMasterVO";
	String SELECTED_QRT_MASTER_VO="selectedQRTMasterVO";
	String ARRAY_ADDED_QR_TEAM_MEMBER_VO="arrayAddedQRTeamMemberVO";
	String ESSENTIAL_DISASTER_ROLE_LIST="essentialDisasterRoleList";
	String ESSENTIAL_ALL_EMPLOYEE_LIST="essentialAllEmployeeList";
	String ESSENTIAL_ALL_EMPLOYEE_LIST_DISPLAY="essentialAllEmployeeListDisplay";
	String ESSENTIAL_DISASTER_ALTERNATE_ROLE_LIST="essentialAlternateRoleList";
	String ACTIVE_DISASTER_PLAN_VO="activeDisasterPlanVO";
	String ARRAY_ALL_DISASTER_PLAN_VO="arrayAllDisasterPlanVO";
	String ARRAY_CONTENT_VO_DISASTER_PLAN="arrayContentVoDisasterPlan";
	String ARRAY_OF_INITIATED_DISASTER_DETAI_VO="arayOfInitiatedDisasterDetailVO";
	String ESSENTIAL_BO_OPTION_INFO_HEAD_LIST="optionsDisasterInfoHeadList";
	String STRING_NUMBER_OF_DISASTER_PATIENTS="stringNumberOdDisasterPatient";
	String ESSENTIAL_BO_OPTION_DISASTER_CATEGORY_LIST = "essentialOptionCategoryList";
	String STRING_NUMBER_OF_DISASTER_PATIENTS_DISCHARGED="stringNumberofDisasterPatientsDischared";
	String STRING_NUMBER_OF_DISASTER_PATIENTS_ADMITTED="stringNumberOfDisasterPatientsAdmitted";
	String STRING_NUMBER_OF_DISASTER_PATIENTS_DEAD="stringNumberOfDisasterPatientsDead";
	String STRING_ESTIMATED_NUMBER_OF_DISASTER_PATIENTS="stringEstimatedNumberOfDisasterPatients";
	String ARRAY_ALL_QR_TEAM_MEMBER_VO="arrayAllQRTeamMemberVO";
	String ESSENTIAL_ACTIVE_DISASTER_LIST="essentialActiveDisasterList";
	String SELECTED_DISASTER_VO_FOR_DESK="selectedDisasterVOForDesk";
	
	/////Disaster Type
	String DISASTER_TYPE_NATURAL="0";
	String DISASTER_TYPE_MANMADE="1";
	String DISASTER_TYPE_NATURAL_LABEL="Natural";
	String DISASTER_TYPE_MANMADE_LABEL="Man Made";
	
	/////DISASTER TYPE MAJOR
	String DISASTER_TYPE_MAJOR_YES="1";
	String DISASTER_TYPE_MAJOR_NO="0";
	
	////Disaster Area
	String IS_INTERNAL_TRUE="1";
	String IS_INTERNAL_FALSE="0";
	String INTERNAL_TRUE_LABEL="Internal";
	String INTERNAL_FALSE_LABEL="External";
	
	/////Disaster Status
	String DISASTER_STATUS_INITIATED="0";
	String DISASTER_STATUS_ACTIVATED="1";
	String DISASTER_STATUS_DEACTIVATED="2";
	String Disaster_STATUS_CANCELLED="3";
	
	//////Commitee Type
	String COMMITTE_TYPE_COMMITTE="1";
	String COMMITTE_TYPE_QR="2";
	
	/////IS Alternate
	String IS_ALTERNATE_FALSE="0";
	String IS_ALTERNATE_TRUE="1";
	
	////Area Type Key Bound
	String AREA_TYPE_KEY_BOUND_YES="1";
	String AREA_TYPE_KEY_BOUND_NO="0";
	
	////Key Location
	String KEY_KEPT_IN_DISASTER_BOX="1";
	String KEY_KEPT_WITH_EMPLOYEE="2";
	
	////Disaster Plan Status
	String PLAN_STATUS_ACTIVE="1";
	String PLAN_STATUS_DEACTIVE="2";
	
	
	//////Content Master id
	
	String CONTENT_ID_PLAN="1";
	
	////Content Status
	String DISASTER_BOX_CONTENT_STATUS_ADDED="1";
	String DISASTER_BOX_CONTENT_STATUS_REMOVED="2";
	
	/////Confirm By Combo
	String CONFIRM_BY_POLICE_LABEL="Police Station";
	String CONFIRM_BY_DISASTER_SITE_LABEL="Disaster Site";
	String CONFIRM_BY_FIRE_STATION_LABEL="Fire Station";
	String CONFIRM_BY_CATS_LABEL="CATS";
	final String CONFIRM_BY_POLICE_VALUE="4";
	final String CONFIRM_BY_DISASTER_SITE_VALUE="2";
	final String CONFIRM_BY_FIRE_STATION_VALUE="3";
	final String CONFIRM_BY_CATS_VALUE="1";
	
	/////key Status Combo
	String KEY_STATUS_NOT_IN_USE_LABEL="Not In Use";
	String KEY_STATUS_NOT_IN_USE_VALUE="0";
	String KEY_STATUS_AVAILABLE_LABEL="AVAILABLE";
	String KEY_STATUS_AVAILABLE_VALUE="1";
	String KEY_STATUS_FOUND_LABEL="Found";
	String KEY_STATUS_FOUND_VALUE="2";
	String KEY_STATUS_NOT_AVAILABLE_LABEL="Not Available";
	String KEY_STATUS_NOT_AVAILABLE_VALUE="3";
	String KEY_STATUS_LOST_LABEL="Lost";
	String KEY_STATUS_LOST_VALUE="4";
	
	String DISASTER_INFO_HEAD_LIST="disasterInfoHeadList";
	String DISASTER_AREA_TYPE_LIST="disasterAreaTypeList";
	String DISASTER_AREA_INCHARGE_LIST="disasterAreaInchargeList";
	String DISASTER_AREA_VO="disasterAreaVO";
	String AREA_TYPE_NAME_AND_LIMIT="areaTypeNameAndLimit";
	
	String DISASTER_AREA_VO_LIST="disasterAreaVOlist";
	String DISASTER_BOX_CONTENT_VO_LIST="disasterBoxContentVOlist";
	
	String ESSENTIAL_DISASTER_BOX_CONTENT_LIST="contentList";
	String DISASTER_BOX_CONTENT_VO="disasterBoxContentVO";
	

	String ESSENTIAL_BO_OPTION_INFO_HEAD_DETAILS="optionsDisasterInfoHeadDetails";
	
	String PROCEDURE_GET_INFO_DETAIL = "PKG_DISASTER_DTL.proc_get_infoDtl";
	
	
	
	////DISASTER INFORMATION MODE 
	
	String DISASTER_INFORMATION_MODE_TELEPHONE="1";
	String DISASTER_INFORMATION_MODE_PEON="2";
	String DISASTER_INFORMATION_MODE_FAX="3";
	
	
	///Already Informed List
	
	String ALREADY_INFORMED_LIST_OF_DISASTER_INFO_DTL="alreadyInformedListOfDisasterInfoDtl";
	
	
	//////////////Path for Disaster Reports//////////////
	
	String DISASTER_JRXML_PATH="/disaster/reports/jrxml/";
	
	String DISASTER_DATE_RANGE_WISE_REPORT="DisasterDateRangeWiseReport.jrxml";
	
	String DISASTER_WISE_PATIENT_DETAIL_REPORT="DisasterWisePatientListing.jrxml";
	
	String DISASTER_STATIC_REPORT="DisasterStaticReport.jrxml"; 
	
	String DISASTER_YEAR_WISE_REPORT="DisasterYearWiseReport.jrxml";
	
	String DISASTER_GENDER_AGE_RANGE_WISE_REPORT="DisasterGenderAgeRangeWiseReport.jrxml";
	
	String ESSENTIAL_DISASTER_LIST="essentialDisasterList";
	
	String ESSENTIAL_ACTIVE_DISASTER_PLAN = "essentialActiveDisasterPlan";
	
	String CONTENT_NAME_IS_DISASTER_PLAN="1";
	
	String DISASTER_COUNT_FOR_TODAY = "disasterCountForToday";
	
	String ESSENTIAL_DISASTER_CONFIRMATION_DETAIL_VO = "essentialDisasterConfirmationDetailVO";
	
	String 	DISASTER_INFORMATION_CONTACT_DETAIL_VO="disasterInformationContactDetailVO";
	
	String LOG_OF_DISASTER_INFORMATION_DETAIL_VO = "logOfDisasterInformationDetailVO";
	
	
	/////List of Fb , data , of ref no.info date, time,mode 
	
	String LIST_OF_REF_NO_FB_DATA = "listOfRefNoFbData";
	String LIST_OF_INFO_DATE_FB_DATA = "listOfInfoDateFbData";
	String LIST_OF_INFO_TIME_FB_DATA = "listOfinfoTimeFbData";
	String LIST_OF_INFO_MODE_FB_DATA = "listOfInfoModeFbData";
	
	/// DISASTER INFORMATION ID for Heads
	
	String DISASTER_INFORMATION_ID_FOR_QRT="18";
	String DISASTER_INFORMATION_ID_FOR_IN_CHARGE_HOD ="12";
	
	
	String DISASTER_ACTIVATION_TIME_BOUND_DAYS="30";

	String YEAR_LIST_FOR_DISASTER_YEARWISE_REPORT="yearListForDisasterYearWiseReport";

}
