/**
## 		Modification Log		: DESK_TYPES_CODE_FOR_DOC_DESK					
##		Modify Date				: 20-01-2015
##		Reason	(CR/PRS)		: Toget count for Ipd & opd Only
##		Modify By				: Akash Singh
*/
package hisglobal.utility.dynamicdesk;

//OpdConfig is an interface that defines hard-coded values 
//that are used for development of BO and DAO.

public interface DynamicDeskConfig
{
	String DYNAMIC_DESK_TYPE = "dynamicDeskType";
	String DYNAMIC_DESK_UNIT_CODE = "dynamicDeskUnitCode";
	String DYNAMIC_DESK_WARD_CODE = "dynamicDeskWardCode";
	String DYNAMIC_DESK_ROOM_CODE = "dynamicDeskRoomCode";
	String DYNAMIC_DESK_ID = "dynamicDeskID";
	String DYNAMIC_DESK_DESK_NAME = "dynamicDeskDeskName";
	String DYNAMIC_DESK_DIAGNOSIS_TYPE_CODE="dynamicDeskDiagnosisTypeCode";
	String DYNAMIC_DESK_UNIT_NAME = "dynamicDeskUnitName";
	String DYNAMIC_DESK_LIST_KEY = "dynamicDeskListKey";

	// property file for queries
	String QUERY_FILE_FOR_DYNAMIC_DESK = "hisglobal.utility.dynamicdesk.dynamicDeskQuery";
	// Database Objects
	String PROC_FOR_DYNAMIC_DESK_ID = "PKG_DYNAMIC_DESK_VIEW.GET_DYNAMIC_DESK_ID";
	String PROC_FOR_PATIENT_COUNT = "PKG_DYNAMIC_DESK_VIEW.GET_PATIENT_COUNT";
	String PROC_FOR_GET_DESK_MENUS = "pkg_dynamic_desk_view.get_desk_menus";
	
	// Desk Types 
	String[] DESK_TYPES = {"","OPD Doctor Desk","Casualty Desk","IPD Nursing Desk","IPD Doctor Desk","Blood Bank Camp Desk","IPD Nursing Station","Enquiry Help Desk","CMO Desk","Postmortem Desk","Disaster Desk", "Diet & Kitchen Desk", "OPD Bay Desk"};
	String DESK_TYPE_OPD_DOCTOR_DESK = "1";
	String DESK_TYPE_CASUALITY_DESK = "2";
	String DESK_TYPE_IPD_NURSING_DESK = "3";
	String DESK_TYPE_IPD_DOCTOR_DESK = "4";
	String DESK_TYPE_BLOODBANK_CAMP_DESK = "5";
	String DESK_TYPE_IPD_NURSING_STATION = "6";
	String DESK_TYPE_HELP_DESK = "7";
	String DESK_TYPE_CMO_DESK = "8";
	String DESK_TYPE_POSTMORTEM_DESK = "9";
	String DESK_TYPE_DISASTER_DESK = "10";
	String DESK_TYPE_DIEK_KITCHEN_DESK = "11";
	String DESK_TYPE_OPD_BAY_DESK = "12";
	String DESK_TYPE_IPD_NON_ACPT_DOCTOR_DESK = "13";
	String DESK_TYPE_IPD_ON_LEAVE_DOCTOR_DESK = "14";
	String DESK_TYPE_IPD_IN_TRANSIT_DOCTOR_DESK = "15";
	
	String[] DESK_TYPES_CODE = {"","1","2","3","4","5","6","7","8","9","10","11","12"};
	String[] DESK_TYPES_CODE_FOR_DOC_DESK = {"","1","4","2"}; // Added by Akash For Header for OPD & IPD Desk

	// Desk Menu Location
	String DYNAMIC_DESK_MENU_LOCATION_LEFT = "1";
	String DYNAMIC_DESK_MENU_LOCATION_RIGHT = "2";
	String DYNAMIC_DESK_MENU_LOCATION_TOP = "3";
	String DYNAMIC_DESK_MENU_LOCATION_BOTTOM = "4";
	
	// Desk Menu Template Based Flag
	String DYNAMIC_DESK_MENU_TEMPLATE_FLAG_NO = "0";
	String DYNAMIC_DESK_MENU_TEMPLATE_FLAG_YES = "1";
	String DYNAMIC_DESK_MENU_TEMPLATE_BASED_FIXED_URL = "GENERICTEMPLATE";

	// Desk Menu Template Based Flag
	String[] DYNAMIC_DESK_MENU_USABILITY_FLAG_ARR = {"","None","Profile Based"};
	String DYNAMIC_DESK_MENU_USABILITY_FLAG_NONE = "1";
	String DYNAMIC_DESK_MENU_USABILITY_FLAG_PROFILE_BASED = "2";

	// Dynamic Desk Rows Span
	String DYNAMIC_DESK_ROWS_SPAN = "dynamicDeskRowsSpan";
	
	String DYNAMIC_DESK_MENU_DTL = "dynamicDeskMenuDetail";
	String DYNAMIC_DESK_TOP_MENU_DTL = "dynamicDeskTopMenuDetail";
	String DYNAMIC_DESK_LEFT_MENU_DTL = "dynamicDeskLeftMenuDetail";
	String DYNAMIC_DESK_RIGHT_MENU_DTL = "dynamicDeskRightMenuDetail";
	String DYNAMIC_DESK_BOTTOM_MENU_DTL = "dynamicDeskBottomMenuDetail";
	String DYNAMIC_DESK_NONPATIENT_CENTRIC_MENU_DTL = "dynamicDeskNonPatientCentricMenuDetail";
	
	// Session Specific
	String DYNAMIC_DESK_SPECIFIC_SESSION_ITEMS = "DynamicDeskSpecificSessionItems";
	//Desk Patient List
	String DESK_PATIENT_LIST = "deskPatientList";
	String DESK_SELECTED_PATIENT_DTL_VO = "deskSelectedPatientVO";

	// Nursing Desk Login User VO
	String DYNAMIC_DESK_USER_VO = "dynamicDeskUserVO";	
	String DYNAMIC_DESK_NURSING_DATA_FLAG_MAP = "dynamicDeskNursingDataFlagMap";	
	
	// Desk Login Process Values
	String ESSENTIAL_BO_LOGIN_USER_LIST = "esssentialLoginUserList";
	String PROCEDURE_GET_USER_LIST = "PKG_DUTY_ROSTER.proc_get_users_wardwise";
	String ESSENTIAL_BO_LOGIN_USER_ROLEIDLIST = "esssentialLoginUserRoleIdList"; 
	
	// Postmortem Desk Deceased List
	String POSTMORTEM_DESK_DECEASED_LIST = "postmortemDeskDeceasedList";	
	String DYNAMIC_DESK_ICD_DISEASE_LIST = "dynamicDeskICDDiseaseList";
	
	// All OPD patient for today for Docter Desk New
	String PROC_FOR_ALL_PATIENT_LIST = "PKG_DYNAMIC_DESK_VIEW.PROC_GET_PATIENT_LIST";
	
	// All IPD patient for today for Docter Desk New
	String PROC_FOR_TODAY_ALL_IPD_PATIENT_LIST = "PKG_DYNAMIC_DESK_VIEW.PROC_GET_IPD_PATIENT_LIST_TODAY";
	
	//opd desk and opd bay desk patient list query package
	
	String PROC_FOR_PATIENT_DTL_FOR_TODAY = "PKG_DYNAMIC_DESK_VIEW.RETRIEVE_PATIENT_LIST_FOR_TODAY";
	String GET_DESK_PATIENT_DTL= "{call PKG_DYNAMIC_DESK_VIEW.get_desk_patient_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
}
	
