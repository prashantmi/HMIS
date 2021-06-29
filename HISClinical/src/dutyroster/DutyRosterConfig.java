package dutyroster;

//DutyRosterConfig is an interface that defines hard-coded values 
//that are used for development of BO and DAO.

public interface DutyRosterConfig {

	// **** Query Property File & Procedures Names
	String QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO = "dutyroster.masters.dutyRosterMstQuery";
	String QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO = "dutyroster.transaction.dutyRosterQuery";
	String QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO = "dutyroster.masters.dutyRosterEssentialQuery";
	String PROCEDURE_GET_DUTY_AREA_LIST="pkg_duty_roster.proc_get_dutyarea";

	String PROCEDURE_GET_DUTY_AREA_LIST_ROSTER_WISE="pkg_duty_roster.proc_get_dutyarea_rosterwise";
	
	String FUNCTION_GET_ROSTER_ID="pkg_duty_roster.gen_roster_id";
	
	
	
	// **** Duty Roster Flag and Variables
	String DUTY_ROSTER_MASTER_LIST_OF_SHIFT_TYPE= "dutyRosterMasterListofShiftType";
	String DUTY_ROSTER_MASTER_VO_SHIFT_MST = "dutyRosterMasterVOofShiftMst";
	

	String DUTY_ROSTER_MASTER_LIST_OF_AREA_TYPE= "dutyRosterMasterListofAreaType";
	String DUTY_ROSTER_MASTER_LIST_OF_AREA= "dutyRosterMasterListofArea";
	String DUTY_ROSTER_MASTER_LIST_OF_ROLE_TYPE = "dutyRosterMasterListofRoleType";
	String DUTY_ROSTER_MASTER_LIST_OF_DESIGNATION= "dutyRosterMasterListofDesignation";
	String DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_UNSELECTED = "dutyRosterMasterListofEmpDetailsUnselected";
	String DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_SELECTED = "dutyRosterMasterListofEmpDetailsSelected";
	String DUTY_ROSTER_MASTER_LIST_OF_ROSTERS= "dutyRosterMasterListofRosters";
	
	String DUTY_ROSTER_MASTER_VO_OF_ROSTER_AREA_CAPACITY_MST= "dutyRosterMasterVOofRosterAreaCapacityMst";
	
	String DUTY_ROSTER_MASTER_LIST_OF_ROSTERS_AND_AREA_TYPE= "dutyRosterMasterListofRostersAndAreaType";

	String DUTY_OFF_FLAG_NOT_REQUIRED = "0";
	String DUTY_OFF_FLAG_DUTY_OFF_ACCOUNT = "1";
	String DUTY_OFF_FLAG_SHIFT_BASED = "2";

	/* ***************** Duty Types *****************/
	
	String DUTY_TYPE_OFFICIAL_DAYS = "1";
	String DUTY_TYPE_TWENTY_FOR_SEVEN = "2";
	String DUTY_TYPE_SUNDAY_HOLIDAY = "3";
	
	/* ************** Roster Mode ************/
	
	String ROSTER_MODE_EMPLOYEE_BASED = "1";
	String ROSTER_MODE_LOCATION_BASED = "2";
	
	String ROSTER_CAT_LIST = "rosterCatOption";
	String DUTY_AREA_TYPE_LIST = "dutyAreaTypeOption";
	
	String ESSENTIAL_ROSTER_TYPE_LIST = "RosterTypeOption";
	String ESSENTIAL_SHIFT_LIST = "ShiftEssentialOption";
	
	String ROSTER_SHIFT_DETAIL = "rosterShiftDetail";
	String ROSTER_TYPE_NAME = "rosterTypeName";
	
	String DUTY_ROLE_NOT_IN_ROSTER_ROLE = "dutyRoleNotIn";
	String DUTY_ROLE_IN_ROSTER_ROLE = "dutyRoleIN";
	
	String ROSTER_DESIG_ASSIGNED_DESIG = "assignedDesignation";
	String ROSTER_DESIG_NOT_ASSIGNED_DESIG = "notAssignedDesignation";
	

	String LIST_OF_YEARS_EMPLOYEE_DUTY_ROSTER_MASTER= "employeeDutyRosterListofYears";
	
	String LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER= "employeeDutyRosterListofMonths";
	
	int NO_OF_YEARS_TO_BE_SHOWN_IN_EMPLOYEE_DUTY_ROSTER=3;
	
	String MAX_DAYS_OF_A_MONTH_IN_EMPLOYEE_DUTY_ROSTER="maxDaysOfaMonth";
	String FIRST_DAY_OF_THE_WEEK_IN_EMPLOYEE_DUTY_ROSTER="firstDayofWeek";
	
	String LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE="listOfEmpBasedOnAreaTypeAndAreaCode";
	
	String SIZE_OF_EMP_LIST="sizeOfEmpList";
	
	String LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE= "listofShiftOnTheBasisOfRosterType";
	
	String EMPLOYEE_WISE_VO_ROSTER_DETAILS="EmployeeWiseVoRosterDetails";
	
	
	String LIST_OF_EMPLOYEES_WITH_ROSTER="listOfEmployeesWithRoster";

	String ESSENTIAL_DUTY_BLOCK_LIST = "dutyBlockOption";
	String DUTY_AREA_CODE_SELECTED_IN_BLOCK_AREA = "dutyAreaCodeSelected";
	String DUTY_AREA_CODE_NOT_SELECTED_IN_BLOCK_AREA = "dutyAreaCodeNotSelected";
	String BLOCK_AREA_DETAIL = "blockAreaDetail";
	String DUTY_AREA_CODE = "dutyAreaCodeList";
	

	String LIST_OF_ROSTERS_AND_AREA_TYPE_HAVING_ROSTER_MODE_LOCATION= "ListofRostersAndAreaTypeHavingRosterModeLocation";

	String LIST_OF_AREA_BASED_ON_BLOCK= "ListofAreaBasedOnBlock";
	
	String DUTY_AREA_TYPE_CODE_BLOCK= "6";
	
	String DUTY_AREA_TYPE_CODE_ESTATE_BLOCK= "7";
	
	String LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE="listOfDesignationOnTheBasisOfRosterType";
	
	String LOCATION_WISE_VO_ROSTER_DETAILS="LocationWiseVoRosterDetails";
	
	String TOTAL_LOCATION_WISE_ROSTERS="totalLocationWiseRosters";
	
	String LIST_OF_ROSTERS_LOCATION_WISE_FOR_BLOCK="listOfRostersLocationWiseForBlock";
	
	String LIST_OF_ROSTERS_LOCATION_WISE_FOR_AREA="listOfRostersLocationWiseForArea";
	
	String VO_OF_ROSTERS_LOCATION_WISE_FOR_AREA="VoOfRostersLocationWiseForArea";
	
	String VO_OF_ROSTERS_LOCATION_WISE_FOR_BLOCK="VoOfRostersLocationWiseForBlock";
	
	String LIST_OF_ROSTER_CATEGORY="listOfRosterCategory";
	
	
	String LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY="listOfRotersOnTheBasisOfRosterCategory";
	
	String DAY_OFF_SHIFT_TYPE_CODE="0";
	
	String LIST_OF_DAY_OFF_SHIFTS="listOfDayOffShifts";
	
	//Different Shift Types
	
	int MORNING_SHIFT_TYPE_ID=1;
	int EVENING_SHIFT_TYPE_ID=2;
	int NIGHT_SHIFT_TYPE_ID=3;
	int DAY_SHIFT_TYPE_ID=4;
	int EARLY_MORNING_SHIFT_TYPE_ID=5;
	
	
	String MORNING_SHIFT_TYPE_TEXT_BOX_NAME="morningCapacity";
	String EVENING_SHIFT_TYPE_TEXT_BOX_NAME="eveningCapacity";
	String NIGHT_SHIFT_TYPE_TEXT_BOX_NAME="nightCapacity";
	String DAY_SHIFT_TYPE_TEXT_BOX_NAME="dayCapacity";
	String EARLY_MORNING_SHIFT_TYPE_TEXT_BOX_NAME="earlyMorningCapacity";
	
	
	
	String MAP_OF_EMPLOYEE_DETAILS_FOR_DATEWISE_DUTY_ROSTER="mapOfEmpDetailsForDateWiseDutyRoster";
	
	
	String MAP_OF_ROSTER_PRINT_DETAILS="mapOfRosterPrintDetails";
	
	String MAP_FOR_ORDER_ROSTER_PRINT_DETAILS="mapForOrderOfRosterPrintDetails";
	
	String MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS="mapForMonthWiseEmpRosterReportDetails";
	
	
	String VO_OF_EMP_WISE_ROSTER="VoOfEmpWiseRoster";
	
	
	String VO_OF_ROSTER_PRINT_DETAILS="voOfRosterPrintDetails";
	
	String MAP_FOR_MONTHWISE_DUTY_ROSTER_REPORT_DETAILS="mapForMonthWiseEmpRosterReportDetails";
	
	
/* ************** Roster Generation Method ************/
	
	String ROSTER_GENERATION_METHOD_MONTHWISE= "0";
	String ROSTER_GENERATION_METHOD_DATE_RANGE_WISE = "1";
	
	String LIST_OF_ROSTER_MAIN_CATEGORY="listOfRosterMainCategory";
	String VO_OF_ROSTER_CATEGORY="vOfRosterCategory";
	
	
	/* ************** Shift Timings List ************/
	
	String VO_OF_SHIFT_TIMINGS="vOfShiftTimings";
	
/* ************** Monthly Gazetted Holiday Map ************/
	
	String MAP_OF_MONTHLY_GAZETTED_HOLIDAYS="mapOfMonthlyGazettedHolidays";
	
	
/* ************** Monthly Gazetted Holiday Map ************/
	
	String MAP_OF_DAY_WISE_SHIFT_TIMINGS="mapOfDayWiseShiftTimings";
	
	int MAPPED_EMP_WITH_ROSTER=1;
	int UN_MAPPED_EMP_WITH_ROSTER=2;
	int MAPPED_EMP_WITH_NO_ROSTER=3;

	String TOTAL_VO_OF_EMP_WISE_MONTHLY_ROSTER="totalVoOfEmpWiseMonthlyRoster";


	String TOTAL_VO_OF_AREA_EMP_MAPPING="totalVoOfAreaEmpMapping";
	
	String MAP_OF_LEGENDS_SHIFT="mapOfLegendsShift";
	
	String TOTAL_MAP_OF_EMPWISE_MONTHLY_ROSTERS="totalMapOfEmpWiseMonthlyRosters";
	
	String MAP_OF_EMPWISE_MONTHLY_ROSTERS="mapOfEmpWiseMonthlyRosters";
	
	String TOTAL_MAP_OF_EMP_AREA_MAPPING="totalMapOfEmpAreaMapping";
	
	String EMP_MAPPED_AREA="empMappedArea";
	

///Holiday  CODE
	
	String GAZETTED_HOLIDAY_CODE="C";
	String RESTRICTED_HOLIDAY_CODE="R";

	
	///LEAVE  CODE 	Changes by Chetan Sharma as per new database
		
		//String SANCTIONED_LEAVE="S";
		//String APPROVED_LEAVE="A";
		 
	String SANCTIONED_LEAVE="50";
	String APPROVED_LEAVE="30";

		
//total vo of all mapped emp leave
	String TOTAL_VO_OF_ALL_MAPPED_EMP_LEAVE="totalVoOfAllMappedEmpLeave";	
	
	
	//diffrent types of shift types colors
	String DAY_OFF_COLOR="#C1AED9";
	String MORNING_SHIFT_TYPE_COLOR="orange";
	String EVENING_SHIFT_TYPE_COLOR="aqua";
	String NIGHT_SHIFT_TYPE_COLOR="#A6A38C";//"#D1D1D1";//"#DBDBDB";//"#C0C0C0";//"#A6A38C";
	String DAY_SHIFT_TYPE_COLOR="yellow";
	String EARLY_MORNING_SHIFT_TYPE_COLOR="#FFCF9F;";
	String MULTIPLE_SHIFT_TYPE_COLOR="Red";

	//emp leave color
	String EMP_LEAVE_COLOR="Pink";
	
	//HOLIDAY COLOR
	String HOLIDAY_COLOR="#B3FFB3";
	
	//HYPERLINK COLOR
	String HYPERLINK_COLOR="Blue"; 
	
	//ROSTER GENERATED COLOR
		
	String ROSTER_GENERATED_COLOR="black"; //"#717171";


	//String 	ROSTER_STATUS_IS_GENERATED="1";
	String 	ROSTER_STATUS_IS_GENERATED="2";
	
	String 	ROSTER_STATUS_IS_CANCELLED="2";
	

	//total vo of all mapped emp leave
	String TOTAL_VO_OF_ROSTER_GENERATED_DAYS="totalVoOfRosterGeneratedDays";	

	
	String SHIFT_NAME_ON_BUTTON_COLOR="black";

	String HEADER_LIST_OF_SHIFTS_FOR_REPORT="headerListOfShiftsForReport";
	
	
	String LIST_OF_ALL_DESIGNATIONS= "listofAllDesignations";


	String LIST_OF_EMP_BASED_ON_DESIGNATION="listOfEmpBasedOnDesignation";
	
	String LIST_OF_DESIGNATION_BASED_ON_HOSPITAL="listOfDesignationBasedOnHospital";

	String LIST_OF_AREA_BASED_ON_EMPLOYEE="listOfAreaBasedOnEmployee";	
	
	String LIST_OF_ROSTERS_BASED_ON_AREA_EMPLOYEE="listOfRostersBasedOnAreaEmployee";	
	
	
	//total vo of all mapped emp leave
	String TOTAL_VO_OF_EMPWISE_ROSTER_DTL="totalVoOfEmpWiseRosterDtl";

	
	String TOTAL_MAP_OF_EMP_ROSTER="totalMapOfEmpRoster";

	String LIST_OF_SUPERVISIOR_EMPLOYEES="listOfSupervisiorEmployees";

	String LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY="listOfAreasOnTheBasisOfRosterCategory";
	
	
	String LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY_AND_AREA="listOfRotersOnTheBasisOfRosterCategoryAndArea";


	String LIST_OF_ROSTER_GENERATED_DAYS="listOfRosterGeneratedDays";


	String VO_OF_ROSTER_SHIFT_CAPACITY="voOfRosterShiftCapacity";

	
	String MAP_OF_ROSTER_SHIFT_CAPACITY="mapOfRosterShiftCapacity";
	

	String LOCATION_WISE_GENERATED_ROSTER_COLOR="#F1ECE2";
	
	//**************Duty Role Assignment*************************/


	String ESSENTIAL_ROSTER_TYPE_FOR_ROLE_ASSIGNMENT="essentialRosterTypeForRoleAssignment";
	String ESSENTIAL_SHIFT_TYPE_BASEDON_ROSTER="essentialShiftTypeBasedOnRoster";
	String ESSENTIAL_EMPLOYEE_LIST_FOR_ROSTER_AND_SHIFT="essentialEmployeeListForRosterAndShift";
	String ESSENTIAL_DUTY_ROLE_FOR_ROSTER="essentialDutyRoleForRoster";
	String EMPLOYEE_LIST_ROSTER_AREA_AND_SHIFT_WISE="employeeListRosterAreaAndShiftWise";
	String EMPLOYEE_LIST_ROSTER_DTL_VO_ARRAY="employeeListRosterDetailVOArray";
	String LIST_OF_ROLE_MAPPED_WITH_EMPLOYEE="listOfRoleAlreadyMappedWithEmployee";
	String LIST_OF_ROLE_ASSIGNED_DATE_RANGE_WISE="listOfRoleAssignedDateRangeWise";
						
					/******Reliver Mode*******/
	
	String RELIVER_MODE_ROSTER_AREA_WISE="1";
	
	String RELIVER_MODE_ROSTER_TYPE_WISE = "2";
	
	
	String RELIVER_TYPE_FROM_ROSTER="1";
	
	String RELIVER_TYPE_FROM_PROCESS = "2";
	
	String EMP_RELIVER_LIST="empReliverList";

	String VO_OF_EMP_RELIVERS_ROSTER_WISE="voOfEmpReliversRosterWise";

	
	String ROSTER_WISE_MAPPED_AREAS="rosterWiseMappedAreas";
	
	
	String LIST_OF_EMP_TYPE_ROSTERS="listOfEmpTypeRosters";
	

	String LIST_OF_REQUEST_BY_EMP_TO_BE_EXCHANGED="listOfRequestByEmpToBeExchanged";
	

	String LIST_OF_REQUEST_WITH_EMP_TO_BE_EXCHANGED="listOfRequestWithEmpToBeExchanged";
	
			
	/************EXCHANGE  REQUEST STATUS*********************/
	
	String EXCHANGE_REQUEST_STATUS_RAISED="1";
	
	
	String EXCHANGE_REQUEST_STATUS_APPROVED="2";
	
	
	String EXCHANGE_REQUEST_STATUS_CANCELLED="3";

	/*******NURSE ROLE DETAIL**************************/
	
	String ESSENTIAL_CATEGORY_LIST_FOR_NURSE="categoryListForNurse";
	String ESSENTIAL_ALL_DUTY_ROLE_LIST="allDutyRoleList";
	String ROSTER_DETAIL_MAP="rosterDetailMap";
	
	String PROCEDURE_GET_EMP_NAME_BY_EMPID="PKG_BLOODBANK_MST.emp_name_empidwise";
	
	String ROSTER_MAIN_CATEGORY_NURSING="11";
	String ROSTER_AREA_TYPE_FOR_NURSE="3";


	String LIST_OF_REQUESTED_BY_EMP_DUTY_LIST_TO_BE_EXCHANGED="listOfRequestedByEmpDutyListToBeExchanged";
	
	String LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_EXCHANGED="listOfRequestedWithEmpDutyListToBeExchanged";
	
	String LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_CHANGED="listOfRequestedWithEmpDutyListToBeChanged";
	
	
		

	/************Duty Done ******************/

	String DUTY_DONE_IS_EXCHANGED="4";
	
	String DUTY_DONE_IS_CHANGED="4";
	
	String DUTY_DONE_IS_CANCEL="5";	
	
	String DUTY_DONE_IS_FREE="3";
	
	
	/************Duty Type ******************/
	
	
	
	String DUTY_TYPE_NORMAL="1";
	
	String DUTY_TYPE_AS_RELIVER="2";

	String DUTY_TYPE_EXCHANGE="3";
	
	String DUTY_TYPE_CHANGE="3";
	
	/////////Locationwise roster//////////
	
	String LOCATION_WISE_ROSTER_IS_NOT_GENERATED="0";
	
	String LOCATION_WISE_ROSTER_IS_GENERATED="1";
	
	String COLOR_CODE_OF_EXCHANGED_DUTY="blue";


/***************************Relivers Process*********************************/
	
	String EMP_LIST_BASED_ON_ROSTER_CATEGORY="empListBasedOnRosterCategory";
	
	String RELIVER_EMP_LIST_BASED_ON_ROSTER_CATEGORY="reliverEmpListBasedOnRosterCategory";
		
	String SHIFT_LIST_BASED_ON_ROSTER_CATEGORY="shiftListBasedOnRosterCategory";



	/************RELIVER  REQUEST STATUS*********************/
	
	String RELIVER_REQUEST_STATUS_RAISED="1";
	
	
	String RELIVER_REQUEST_STATUS_APPROVED="2";
	
	
	String RELIVER_REQUEST_STATUS_CANCELLED="3";
	
	
		/************REASON FOR RELIVER ********************/
	
	String RELIVER_REASON_EMPLOYEE="1";
	
	
	String RELIVER_REASON_OVERLOAD="2";
	
	
	String EMP_WORK_REPORT_LIST="empWorkReportList"; 
	
	String EMP_WORK_REPORT_MAP="empWorkReportMap";
	
	
			/***********Type Of User**************/
	
	String USER_TYPE_EMPLOYEE="1";
	
	String USER_TYPE_NON_EMPLOYEE="2";
	
	/******************************************/
	String DUTY_ROSTER_EMP_SEQ_MAP="dutyRosterEmpSeqMap";
	
	String HOSPITAL_MST_VO="hospitalMasterVO";
}





