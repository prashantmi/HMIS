package registration;

import hisglobal.vo.RosterMasterVO;

/**
 * RegistrationConfig is an interface that defines hard-coded values that are
 * used for development of BO and DAO.
 * @author AHIS 
 */
public interface RegistrationConfig {
	
	
	////////////Predefined Registration Master Values///////////////
	
	//String REGISTRATIONDESK_DEFAULT_STATE_CODE = "64";
	//Start:Sheeldarshi:10-Nov-2014:Enquiry Changes
	String REGISTRATIONDESK_DEFAULT_STATE_CODE = "27";
	//End:Sheeldarshi:10-Nov-2014:Enquiry Changes
    String REGISTRATIONDESK_NON_DELHI_DEFAULT_STATE_CODE = "12";
   //Start:Sheeldarshi:05-Nov-2014:Enquiry Changes
    // String REGISTRATIONDESK_DEFAULT_COUNTRY_CODE = "101";
    String REGISTRATIONDESK_DEFAULT_COUNTRY_CODE = "IND";
  //End:Sheeldarshi:05-Nov-2014:Enquiry Changes
    String REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE = "0";
    String REGISTRATIONDESK_MARITAL_STATUS_MARRIED="1";
    String REGISTRATIONDESK_MARITAL_STATUS_SINGLE="2";
    String PATIENT_ADD_TYPE_CURRENT="1";
   // String PRIMARY_CATEGORY_EMPLOYEE_CODE="12"; //These Master Value are in hisglobal.hisconfig.Config.java    
//	String PRIMARY_CATEGORY_STAFF_CODE="15";      //Please UpDate that file also
	//String PRIMARY_CATEGORY_POOR_FREE_CODE="15";
    
    
/////////////////////////New Registration Session Managment Flag/////////////////////
    String SESSION_FLAG_NEW_REGISTRATION="sessionFlagNewRegistration";
    String SESSION_FLAG_SPECIAL="sessionFlagSpecial";
    String SESSION_FLAG_NEW_REGISTRATION_DUPLICATE="sessionFlagNewRegistrationDuplicate";
    String SESSION_FLAG_NEW_REGISTRATION_ROOM_WISE="sessionFlagRoomWiseRegistration";
    String SESSION_FLAG_YES="1";
    String SESSION_FLAG_NO="0";
    String REGISTRATION_ESSENTIAL_MAP="registrationEssentialMap";
/////////////////////////////////////////////////////////////////////////
    String PATIENT_CAT_TYPE_SECONDARY = "1";
    String PATIENT_CAT_TYPE_PRIMARY   = "0";
    String PATIENT_REG_CATEGORY_NORMAL="11";
    String PATIENT_REG_CATEGORY_SPECIAL="12";
    String PATIENT_REG_CATEGORY_EMERGENCY="13";
    String PATIENT_OFFLINE_REG="15";
    
  //  String PATIENT_CATEGORY_EMPLOYEE="13";
    
    String PATIENT_ISUNKNOWN_TRUE  = "1"; 
    String PATIENT_ISUNKNOWN_FALSE  = "0";
    
    String IS_ADDRESS_DELHI_TRUE="1";
    String IS_ADDRESS_DELHI_FALSE="0";
    String PATIENT_ADDRESS_ADD="add";
    String PATIENT_ADDRESS_MODIFY="modify";
    String PATIENT_ADDRESS_STATUS="addressStatus";
    String CHOICE_MISTAKE			="1";
    String CHOICE_ADDITION			="2";
    
    String IS_ACTUAL_DOB_TRUE	="1";
    String IS_ACTUAL_DOB_FALSE	="0";
    
    String RENEWAL_REQUIRED_TRUE="1";
    String RENEWAL_REQUIRED_FALSE="0";
    //String RENEWAL_REQUIRED_NONE="2";
    
    String REGISTRATION_SERVICE_ID="1";
    String NEW_REGISTRATION_TARIFF_ID="1010001";
    String EMERGENCY_REGISTRATION_TARIFF_ID="1010004";
    String SPECIAL_CLINIC_REGISTRATION_TARIFF_ID="1010003";
    String OLD_DEPT_VISIT_TARIFF_ID="1010005";
    String NEW_DEPT_VISIT_TARIFF_ID="1010006";
    String DUPLICATE_RENEW_CARD_TARIFF_ID="1010002";
    String TARIFF_ID="tariffId";
    //String REFER_DEPT_VISIT_TARIFF_ID="3";
    String RENEWAL_TARIFF_ID="1010001";
	String EMERGENCY_NEW_DPT_VISIT_TARIFF_ID="1010008";
	String EMERGENCY_RENEWAL_TARIFF_ID="1010009";
    String SPECIAL_NEW_DEPT_VISIT_TARIFF_ID="1010003";

    
    String IS_OLD_TRUE		="1";
    String IS_OLD_FALSE		="0";
    String IS_HOU_TRUE="1";
    String IS_HOU_FALSE="0";
    String IS_HOU_DOCTOR="2";
    
    String REGISTRATIONDESK_UNKNOWN_PRIMARY_CATEGORY = "11";//>>>SYNOMYNOUS TO GENERAL CATEGORY
    
    String IS_MLC_TRUE		="1";
    String IS_MLC_FALSE		="0";
    //String ARR_EPISODE_VO   = "";
    String IS_REFERRED_TRUE		="1";
    String IS_REFERRED_FALSE	="0";
    String MLC_NO="mlcno";
    String MLC_NO_LIST="mlcNoList";
    String CR_NO_LIST="crNoList";
    String IS_REFERRED_IN_OUT_REFER_EXTERNAL	="0";//>>PATIENT REFERRED TO SOME OTHER HOSPITAL
    
    String IS_REFERRED_IN_OUT_REFER_INTERNAL	="1";//>>PATIENT REFERRED TO SOME DEPARTMENT IN SAME HOSPITAL
    												 // in this case HRGNUM_REFF_DEPT_CODE in HRGT_EPISODE_REFFER_DTL specifies department to which patient is reffered
    
    String IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL	="2";//>>PATIENT ACCEPTED FROM SOME OTHER HOSPITAL
    												 
    String IS_REFERRED_IN_OUT_ACCEPT_INTERNAL	="3";//>>PATIENT ACCEPTED FROM SOME DEPARTMENT IN SAME HOSPITAL
    												 //in this case HRGNUM_REFF_DEPT_CODE in HRGT_EPISODE_REFFER_DTL specifies department from which patient is reffered
    String IS_ASSOCIATED_TRUE = "1";//IT is an associated hospital
    
    String IS_ASSOCIATED_FALSE ="0";//Not an associated hospital
    
    String CAT_PRIORITY="1"; //priority of patient
    
    String IS_FREE_TRUE="1";
    String IS_FREE_FALSE="0";
    String PATIENT_STATUS_CODE_ADMITTED="11";
	String PATIENT_STATUS_CODE_NOT_ADMITTED="12";
	String PATIENT_STATUS_CODE_EMGNOT_ADMITTED="12";
	String PATIENT_STATUS_CODE_DEAD="1";// "13"; // Updated By Anant as isDead have only two values 1 for Dead
	String PATIENT_STATUS_CODE_NOT_DEAD="0"; //"14" // Updated By Anant as isDead have only two values 0 for Not Dead
	
	String OPD_CARD_RENEW="0";
	String OPD_CARD_DUPLICATE="1";
	
    String DEPT_UNIT_IS_ON_DUTY_TRUE="1";
    String DEPT_UNIT_IS_ON_DUTY_FALSE="0";
    
    String DEPT_UNIT_VISITED_TODAY_TRUE="1";
    String DEPT_UNIT_VISITED_TODAY_FALSE="0";
    
    String DEPT_UNIT_IS_CLOSED_FALSE		="0";
    String DEPT_UNIT_IS_CLOSED_TRUE			="1";
    String DEPT_UNIT_IS_CLOSED_NOT_EXIST	="2";
    String DEPT_UNIT_IS_CLOSED_EMG_REG		="3";
    

    String EPISODE_TYPE_CODE_IPD      ="0";		// Not in Use Now
    String EPISODE_TYPE_CODE_OPD_GENERAL ="1";	// 1: OPD
    String EPISODE_TYPE_CODE_OPD_SPECIAL ="4";	//  	
    String EPISODE_TYPE_CODE_EMG_MLC  ="2";		// 2: EMERGENCY
    String EPISODE_TYPE_CODE_EMG_NO_MLC	="3";	
    //String EPISODE_TYPE_CODE_EMG_NO_MLC ="4";
 // 3: OUT REACH
    
    
    
    


    
   
    String EPISODE_ISNEW_TRUE		  = "1";
    String EPISODE_ISNEW_FALSE		  = "0";
    
    String EPISODE_ISOPEN_TRUE		  = "1";
    String EPISODE_ISOPEN_FALSE		  = "0";
    
    //String EPISODE_ISCONFIRMED_TRUE	  = "1";
    //String EPISODE_ISCONFIRMED_FALSE  = "0";
   // String EPISODE_ISCONFIRMED_IRRELEVANT  = "0";//---use????

    String EPISODE_ISCONFIRMED_REGISTERED	  = "0";// used in case of OPD Bay
    String EPISODE_ISCONFIRMED_VISIT_STAMPED  = "1";///we will be using this
    String EPISODE_ISCONFIRMED_VISIT_CONFIRMED  = "2";
    
    String EPISODE_VISIT_TYPE_IPD      ="0";
    String EPISODE_VISIT_TYPE_OPD      ="1";
    String EPISODE_VISIT_TYPE_EMG      ="2";//Earlier the visit type was MLC=3 anf NoN Mlc=2
    String EPISODE_VISIT_TYPE_EMG_MLC  ="2";//Now The visit type is casuality=2 for mlc and non mlc
    String EPISODE_VISIT_TYPE_SPECIAL  ="4";
    
    /*1: OPD GENERAL
    2: EMERGENCY MLC
    3: EMERGENCY NON MLC
    4: OPD SPECIAL
    5: Outreach Home Visit
    6: Outreach Mobile Medical Unit
    7: Outreach Anganwadi Centre
    8: Outpatient Health Mela
    9: Outpatient Health Camps
    0: Others */

    String EPISODE_REFERRAL_ACCEPTANCE_NONE      ="0";
    String EPISODE_REFERRAL_ACCEPTANCE_INTERNAL  ="1";
    String EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL  ="2";
    
    String EPISODE_AUTO_CLOSE_YES = "1";
    String EPISODE_AUTO_CLOSE_NO = "0";
    
   /* String EPISODE_TYPE_CODE_MLC	   ="1";
    String EPISODE_TYPE_CODE_NO_MLC    ="0";
    */
    String FUNCT_CRNO_GENERATION      =" ";
    
    String QUERY_FILE_FOR_MASTERSDAO  ="registration.mastersQuery";
    String QUERY_FILE_FOR_DAO		  ="registration.registrationQuery";
    
    
 // MCTS Registration
	String ARR_LATEST_EPISODE_VO = "arrVoLatest";
    String COLL_LATEST_EPISODE_VO="LATESTOpenEpisodeVO";

    //MCTS Report
    
  //MCTS Report

	
    String MCTS_PATIENT_LIST="MctsPatientList.jrxml";

	String PAT_WISE_PATIENT_VISIT_REPORT_UNIT_GROUP="PatWiseVisitDtlReportGroupByUnit.jrxml";
	String PAT_WISE_PATIENT_VISIT_REPORT_DATE_GROUP="PatWiseVisitDtlReportGroupByDate.jrxml";
	String RPT_DATA_PATIENT_WISE_VISIT = "rptdatapatientwisevisit";
	String MAP_RPT_DATA_PATIENT_WISE_VISIT= "maprptdatapatientwisevisit";
    
    String CARD_NEW_REGISTRATION="NewRegistrationCard";
    String CARD_NEW_DEPT_VISIT="NewDepartmentVisit";
    String CARD_EMG_NEW_DEPT_VISIT="EmgNewDepartmentVisit";
    String CARD_OLD_DEPT_VISIT="OldDepartmentVisit";
    String CARD_EMG_OLD_DEPT_VISIT="EmgOldDepartmentVisit";
    String CARD_DUPLICATE_CARD="DuplicateCardPrinting";
    String CARD_SPECIAL_CLINIC="SpecialClinicRegistration";
    String CARD_OFFLINE_NEW_REGISTRATION="offlineRegistrationCard";
    String CARD_NEW_EMERGENCY_REGISTRATION="NewEmergencyRegistration";
    
    String EPISODE_REFER_VO="episoderefervo";
    String ARR_OPD_OPEN_EPISODE_VO="arrOpenOPDEpisodeVO";
    String ARR_OPEN_EPISODE_VO="arrOpenEpisodeVO";
    String COLL_OPEN_EPISODE_VO="collOpenEpisodeVO";
    String COLL_EPISODE="collEpisode"; 
    String ARR_EPISODE_REFER_PAT_VO="arrEpisodeRefDtlVO";
    String ARR_SC_EPISODE_REFER_PAT_VO="arrSCEpisodeRefDtlVO";
    String ARR_OPEN_TODAY_MLC_EPISODE_VO="arrOpenTodayMlcEpisodeVO";
    
    String LIST_DEPT_WISE_FILE_NO="lstDeptWiseFileNo";
    String MAP_DEPT_WISE_FILE_NO="mapDeptWiseFileNo";
    
    String ESSENTIALBO_OPTION_PRIMARY_CATEGORY_LIST_EXCEPT_PATIENT_CATEGORY="listPatCatExceptPatientcat";
    String ESSENTIALBO_OPTION_PRIMARY_CATEGORY= "optionPrimaryCategory";
    String ESSENTIALBO_OPTION_PRIMARY_CATEGORY_WITH_EXPIRY_DAYS="optionPrimaryCategoryWithExpiryDays";
    String ESSENTIALBO_OPTION_SECONDARY_CATEGORY= "optionSecondaryCategory";
    String ESSENTIALBO_OPTION_SECONDARY_CATEGORY_WITH_EXPIRY_DAYS= "optionSecondaryCategoryWithExpiryDays";
    String ESSENTIALBO_OPTION_MARITAL_STATUS= "optionMaritalStatus";
    String ESSENTIALBO_OPTION_AREA_CATEGORY= "optionAreaCategory";
    String ESSENTIALBO_OPTION_AGE_TYPE= "optionAgeType";    
    String ESSENTIALBO_OPTION_GENDER= "optionGender";
    String ESSENTIALBO_OPTION_EDUCATION= "optionEducation";
    String ESSENTIALBO_OPTION_CASTE= "optionCaste"; 
    String ESSENTIALBO_OPTION_USER= "optionUser";
    String ESSENTIALBO_OPTION_RELIGION= "optionReligion";
    String ESSENTIAL_BO_OPTION_ACTIVE_DISASTER_LIST="optionDisasterList";
    String ESSENTIAL_BO_OPTION_DEPARTMENT = "optionDepartmentList";
    String ESSENTIALBO_OPTION_SALUTATION= "optionSalutation";
    String ESSENTIALBO_OPTION_DESIGNATION= "optionDesignation";


    String ESSENTIALBO_OPTION_ALL_DEPARTMENT= "optionAllDepartment";
    String ESSENTIALBO_OPTION_LOCATION= "optionLocation";
    String ESSENTIALBO_OPTION_LOCATION_TYPE="LocationType"; 
    String ESSENTIALBO_OPTION_STATE= "optionState";
    String ESSENTIALBO_OPTION_ALL_STATE="optionAllState";
    String ESSENTIALBO_OPTION_COUNTRY= "optionCountry";
    String ESSENTIALBO_OPTION_REF_HOSPITAL= "optionRefHospital";
    String ESSENTIALBO_OPTION_REG_CATEGORY= "optionRegCategory";
    String ESSENTIALBO_OPTION_CMO_REGISTER= "optioncmoregister";
    String ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS="optionVerificationDoc";
    String ESSENTIALBO_RELATIVE_CODE="relativeCode";
    String ESSENTIALBO_WARD_CODE="wardcode";
    String ESSENTIALBO_OPTION_DOCTOR_DESK= "optiondoctordesk";
    String ESSENTIALBO_OPTION_CRMLCNO= "mlccrno";
    String ESSENTIALBO_OPTION_UNIT_CHANGE_TO_DEPTUNIT= "unitChangeToDeptUnit";
    String ESSENTIALBO_OPTION_REFERDTLMOD= "unitChangeToDeptUnit";

    String ESSENTIALBO_OPTION_BUILDING="optionBuilding";

    String ESSENTIALBO_OPTION_DISTRICT_ON_DEFAULT_STATE= "lstDistrictOnTheBasisOfState";
    String ESSENTIALBO_OPTION_DEPARTMENT_TYPE="essentialDepartmentTypelist";
    String ESSENTIALBO_OPTION_GLOBAL_DEPARTMENT="listOfGlobalDepartment";
    String ARRAY_GLOBAL_DEPARTMENT_VO="arrayDepartmentVOGlobalDepartment";
    String GLOBAL_DEPARTMENT_VO="VOforGlobalDepartment";
    
    String ARRAY_DOCUMENT_UPLOAD_VO_MLC="arrayDocumentUploadVOForMlc";
    

    String ESSENTIALBO_OPTION_ALL_SPECIAL_UNIT="allSpecialClinicUnit";

  
    String ESSENTIAL_BO_DEATH_MANNER="deathManner";
	String ESSENTIAL_BO_OPTION_DIAGNOSISTYPE="diagnosisType";
	String ESSENTIAL_BO_OPTION_ALLDEPARMENT="alldepartment";
	String ESSENTIAL_BO_OPTION_ALLDEPT="alldept";
	String ESSENTIAL_BO_OPTION_UNITTYPE="unitType";
	String ESSENTIAL_BO_OPTION_DEPT_UNIT_WITH_CASUALITY="unitwithcasuality";	
	String ESSENTIAL_BO_OPTION_UNIT_BASED_ON_SEATID="unitBasedOnSeatId";
	String ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT="unitBasedOnDept";
	String ESSENTIAL_BO_OPTION_EPISODE_ACTION="optionEpisodeAction";
	String ESSENTIALBO_OPTION_ALL_DEPARTMENT_LIST="allDeptList";
	String ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE="stateWiseDistrictList";
    
    String ESSENTIALBO_OPTION_ADDRESS_TYPE="optionAddressType";
    String ESSENTIALBO_OPTION_DEPARTMENT_WITH_CASUALITY="optiondeptwithcasuality";
    String ESSENTIALBO_OPTION_EDIT_ADDRESS_TYPE="optionEditAddressType";
    String ESSENTIALBO_OPTION_EDIT_ADDRESS_TYPE_FOR_VALIDATION="optionEditAddressTypeForValidation";
    String ESSENTIALBO_OPTION_NATIONALITY="optionNationality";
    
    String ESSENTIALBO_OPTION_REFERRED_TO_DEPT="optionReferToDept";
    String ESSENTIALBO_OPTION_REFERRED_FROM_DEPT="optionReferFromDept";
    String ESSENTIALBO_OPTION_REFERRED_ALL_DEPT="optionReferAllDept";
    String ESSENTIALBO_OPTION_OCCUPATION_DTL="optionOccupationDetail";
    String ESSENTIALBO_OPTION_RELATION_DTL="optionRelationDetail";

    String ESSENTIALBO_OPTION_PATIENT_CATEGORY="optionPatientCategory";
    String ESSENTIALBO_OPTION_REGISTRATION_CATEGORY="optionRegCategory";
    String ESSENTIALBO_OPTION_ALL_UNIT="allUnit";
    String ESSENTIAL_BO_OPTION_BLOCK="optionBlockCode";
    String ESSENTIAL_BO_OPTION_FLOOR="optionFloorCode";
    String ESSENTIAL_BO_OPTION_ESTATE_ROOM="optionEstateRoomCode";
    String ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL="optionReferDepartmentList";
    String ESSENTIALBO_OPTION_ICD_CODE_LIST="optionIcdCodeList";
    String ESSENTIALBO_OPTION_HOSPITAL_DIAGNOSOSIS_CODE_LIST="optionHospitalDiagnosisCode";
    String ARR_EPISODE_OLD_PAT_REFER_VO="arrEpisodeOldPatReferVO";
    

    String BILL_AMOUNT="billAmount";
    String REGISTRATIONDESK_OPTION_DEPARTMENT = "optionDepartmentToSelect";
    String REGISTRATIONDESK_EPISODEVO_ARR = "arrEpisodeVO";
    String REGISTRATIONDESK_CRNOMODIFICATION_ARR = "CRNoForModification";
    String REGISTRATIONDESK_EPISODEVO_ARR_REPRINT = "arrEpisodeVOReprint";
    String REGISTRATIONDESK_EPISODEVO_ARR_DUPLICATE= "arrEpisodeVODuplicate";
    String REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION="arrayEpisodeVOReprintRegistration";
    String REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_OLD_PATIENT="arrayEpisodeVOReprintOldPatient";
    String REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_RENEWAL="arrayEpisodeVOReprintRenewal";
    String ADDRESS_CITY_LOCATION_DETAIL_VO="cityLocationDetailVO";
    
    String ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT= "optionNewDeptVisitDepartment";
    String ESSENTIALBO_OPTION_MLC_DTL="optionsmlcdtl";
    String ARRAY_MLC_VO_POLICE_VERIFICATION="arrayMlcVoPoliceVerification"; 
    String ESSENTIALBO_OPTION_OLD_DEPT_VISIT_DEPARTMENT= "optionOldDeptVisitDepartment";    
    String USERVO = "userVO";    
    
    String REGISTRATIONDESK_DEFAULT_ADDRESS_TYPE_CODE = RegistrationConfig.PATIENT_ADD_TYPE_CURRENT;    
    String REGISTRATIONDESK_UNKNOWN_LOCATION_CODE="0";
    String REGISTRATIONDESK_DEFAULT_PRIMARY_CAT_CODE = "1";
    String ESSENTIALBO_OPTION_REVISIT_DEPARTMENT= "optionRevisitDepartment";    
    String ESSENTIALBO_OPTION_ALL_SEASON="optionAllSeason";
    String SEASON_MASTER_VO="seasonMasterVO";
    String EXT_INSTITUTE_MASTER_VO="estInstituteMasterVO";
    String SELECTED_MLC_VO_FOR_UPLOAD="selectedMlcVOforUpload";
    String ESSENTIALBO_OPTION_MLC_NO_BASED_ON_CR_NO="mlcNoBasedOnCrNo";
    String SELECTED_MLC_VO_FOR_POLICE_VERIFICATION="selectedMLCVoForVerfication";
    String TRANSACTION_CONTEXT_OLD_AHIS="OLD";
    String TRANSACTION_CONTEXT_AHIS="AHIS";    
    
    String REG_DESK_TAB_NEW_REGISTRATION="New Registration";
    String REG_DESK_TAB_NEW_DEPT_VISIT="New Dept Visit";
    String REG_DESK_TAB_PATIENT_MODIFICATION_DETAIL="Patient detail modification";
    String REG_DESK_TAB_GROUP="tab group";       
    String REG_DESKMODE_NEWREG="NEWREG";
    String REG_DESKMODE_NEWDEPTVISIT="NEWDEPTVISIT";
    String REG_DESKMODE_PATIENTDTLMOD="PATIENTMOD";    
    String REG_DESKMODE_NEWREG_ROOM_WISE="NEWREG_ROOM_WISE";
    String REG_DESKMODE_OLD_PATIENTDTL="OLDDEPTVISIT";
    String REG_DESKMODE_OLD_PATIENT_VISIT_ROOMWISE="OLDPATIENT_VISIT_ROOM_WISE";
	String REG_DSK_JS_FUNC_ON_CLICK="change_tab";	
	String REG_DSK_HTML_MODE_FIELD_NAME="deskmode";	
	String TAB_GROUP="Tab_Group";
	String DIAGNOSIS_PROVISIONAL_CODE="5";
	
	//Object ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS = null;
	
	String IS_MODIFICATION_SUCCESSFULL="modifation status";	
	String IS_RECORD_FOUND="record found";
	String PREGNANCY_MANNER_OF_DEATH="5";	
	String IS_BABY_DELIVERED_TRUE="1";
	String STATUS="status";	
	String STATUS_MESSAGE="";
	String PATIENT_UNKNOWN="Unknown";
	String ISUNKNOWN="1";
	String APPLICATION_EXECUTION_ERROR="Application Execution error";
	String DATA_ACCESS_ERROR="Data Access Error";	
	String REGDESK_ADDRESSVO_ARR="arrAddressVO";
	String EPISODE_ACTION_CODE_NOT_ATTENDED="1";	
	String EPISODE_ACTION_CODE_ATTENDED_DISPOSED="2";
	String EPISODE_ACTION_CODE_ATTENDED_OBSERVATION="3";
	String EPISODE_ACTION_CODE_ATTENDED_ADMITTED="4";
	String EPISODE_ACTION_CODE_ATTENDED_CASUALITY="5";
	String EPISODE_ACTION_ATTENDED_WARD="6";
	String EPISODE_ACTION_ATTENDED_REF_OTHER_HOSPITAL="7";
	String EPISODE_ACTION_DEATH="8";
	String ARR_SELECTED_DIAGNOSIS_NAME="selecteddiagnosisdetail";	
	String ARR_SELECTED_DIAGNOSIS_CODE="selecteddiagnosiscode";
	String PATIENT_BROUGHT_DEAD_TRUE="1";	
	String PATIENT_BROUGHT_DEAD_FALSE="0";
	String IS_TRANSFER_TRUE="1";
	String IS_TRANSFER_FALSE="0";
	String PATIENT_MLC_VO="mlcvo";
	String PATIENT_MLC_VO_ARRAY="mlcvoarray";
	String PATIENT_VO="patientVO";
	String ARRAY_OPEN_EPISODE_VISITED_TODAY="arrayOpenEpisodeVisitedToday";
	String ARRAY_EPISODES_TO_BE_STAMPED="arrayEpisodeToBeStamped";
	String ARRAY_EPISODE_REFER_VO_RENEWAL="arrayEpisodeReferVOInRenewal";
	String REGISTRATIONDESK_UNKNOWN_SECONDARY_CATEGORY= "6"; 
	String REGISTRATIONDESK_UNKNOWN_STATE_CODE="0";
	String UPLOADED_FILE_AS_ARRAY ="uploadedfileasarray";
	String UPLOADED_FILE_NAME ="uploadedfilename";
	String BODY_HANDOVER_TO_RELATIVE="R";
	String BODY_HANDOVER_TO_MORTUARY="M";
	String YEAR="";	
	String arrPATIENT_VO="ArrayPatientVO";
	
	String EPISODE_VO="episodeVO";

	String ARR_EPISODE_REFER_VO = "arrEpisodeReferVO";

	String ARR_EPISODE_VO = "arrEpisodeVO";

	String IMAGE_UPLOADED_TRUE = "1";

	String IMAGE_UPLOADED_FALSE = "0";

	String retrieveBypatNameCRNO = "";

	String EMGREGISTRATION_OPTION_DEPARTMENT = "optionemergencydepartment";

	String UNIT_CONSULTANT_VO = "unitConsultantVO";

	String DEPT_UNIT_CODE = "deptUnitCOde";

	String DEFAULT_SEQUENCE_NO = "001";

	String EMPLOYEE_AS_CONSULTANT = "employeeswhichareconsultant";

	String ALL_ROOMS = "allRooms";

	String COLLECTION_NEW_ROOMS_TO_UNIT = "newRoomsToUnit";

	String COLLECTION_NEW_CONSULTANTS_TO_UNIT = "newRoomsToUnit";
	
	String DEFAULT_STATE_NAME="defaultStateName";
	
	String DISTRICT_LIST_DEFAULTSTATE="districtList";
	
	String ROOM_MST_BLOCK="block";
	
	String ROOM_MST_FLOOR="floor";
	
	String ROOM_MST_ESTATEROOM="estateRoom";
	
	String ROOM_MST_ROOMVO="roomVO";
	
	String EMPLOYEE_MST_EMPLOYEEVO="empVO";


	// UNIT TYPES USED IN UNIT MASTER FOR FIELD hgnum_isgeneral for
	// hgbt_unit_mst
	// ********************************************************
	
	


	

	//UNIT TYPES USED IN UNIT MASTER FOR FIELD hgnum_isgeneral for hgbt_unit_mst
	//********************************************************
	String UNIT_TYPE_GENERAL="1";
	String UNIT_TYPE_SPECIALITY="2";
	String UNIT_TYPE_CASUALITY="3";
	String TYPE_OF_UNIT="typeOfUnit";
	//********************************************************	
	String EMPLOYEE_ISCONSULTANT_TRUE="1";
	String DEPT_SHIFT_ROSTER_VO="DeptShiftRosterVO";
	String DEPT_UNIT_ROSTER_VO="DeptUnitRosterVO";
	String DEPT_UNIT_ROOM_MASTER_SEQUENCE="deptunitroommstsequence";
	String ROOM_TO_DEPT_UNIT="roomToUnit";
	
	String SYSADATEOBJECT="SYSDATEOBJECT";
	String DEP_UNIT_ROOM_MASTER_ARR_DELETE="deptUnitRoomMstdeleteArr";
	String DEP_UNIT_ROOM_MASTER_ARR_UPDATE="deptUnitRoomMstupdateArr";
	String INACTIVE_TILL_DATE="22-MAR-2050";
	String ESSENTIALBO_OPTION_SHIFT="optionShift";
	String ESSENTIALBO_OPTION_SHIFT_FOR_REG="optionShiftForReg";
	String UNIT_MASTER_VO="unitMasterVO";
	String DEPT_UNIT_ROOM_MASTER_VO="deptUnitRoomMasterVO";
	String EFFECTIVE_CASE_FIRST_SERIAL_NO="1";
	
	//******************************************
	
    //Department types
	String DEPT_TYPE_CLINICAL="Clinical";///value 1
	String DEPT_TYPE_ADMINISTRATIVE_SERVICES="Administrative Services";///value 2
	String DEPT_TYPE_PARA_CLINICAL="Para Clinical";//////value 3
	String DEPT_TYPE_NON_CLINICAL="NonClinical";///value 4
	
	String DEPT_TYPE_CLINICAL_VALUE="1";
	String DEPT_TYPE_ADMINISTRATIVE_SERVICES_VALUE="2";
	String DEPT_TYPE_PARA_CLINICAL_VALUE="3";
	String DEPT_TYPE_NON_CLINICAL_VALUE="4";
	
	//String DEPT_TYPE_EDUCATION="education";
	//String DEPT_TYPE_OPD="opd";
	//String DEPT_TYPE_IPD="ipd";
	//String DEPT_TYPE_CASUALITY="casuality";
	
	String IS_VALID_COMBO="isValidCombo";
	//******************************************
	String TIME_STRING=" 00:01";
	////////************************************************* 
	
	//////////////path for registration reports//////////////
	
	String REGISTRATION_JRXML_PATH="/registration/reports/jrxml/";
	
	String REGISTRATION_JRXML_PATH_REPORT1="/registration/reports/report1/jrxml/";
	
	String REGISTRATION_JRXML_PATH_REPORT2="/registration/reports/report2/jrxml/";
	
	//report specific files
	String MLC_POLICE_INFO_PROFORMA="MLCPoliceInfoReport.jrxml";
	
	String HOSPITAL_PATIENT_STATISTICS_DATEWISE="HospitalPatStatisticsDateWise.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_TODAY="HospitalPatStatisticsToday.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATE_WISE_TODAY="NewHosPatStatTOdayStateWise.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATEWSIE_DATEWISE="NewHosPatStatStateWiseDatewise.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATEWSIE_MONTHWISE="NewHosPatStatStateWiseMonthWise.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATEWSIE_YEARWISE="NewHosPatStatStateWiseYearwise.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATE_WISE_TODAY_NEW_PATIENT="NewHosPatStatTOdayStateWiseNewPatient.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATEWSIE_DATEWISE_NEW_PATIENT="NewHosPatStatStateWiseDatewiseNewPatient.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATEWSIE_MONTHWISE_NEW_PATIENT="NewHosPatStatStateWiseMonthWiseNewPatient.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATEWSIE_YEARWISE_NEW_PATIENT="NewHosPatStatStateWiseYearwiseNewPatient.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATE_WISE_TODAY_OLD_PATIENT="NewHosPatStatTOdayStateWiseOldPatient.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATEWSIE_DATEWISE_OLD_PATIENT="NewHosPatStatStateWiseDatewiseOldPatient.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATEWSIE_MONTHWISE_OLD_PATIENT="NewHosPatStatStateWiseMonthWiseOldPatient.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_STATEWSIE_YEARWISE_OLD_PATIENT="NewHosPatStatStateWiseYearwiseOldPatient.jrxml";
	String PATIENT_REG_ANALYSIS_USER_PAT_VISIT_WISE_DATEWISE="PatientRegAnalysisUserPatVistWiseDateWise.jrxml";
	String PATIENT_REG_ANALYSIS_USER_PAT_VISIT_WISE_TODAY="PatientRegAnalysisUserPatVisitWiseToday.jrxml";	
	String SERVICE_UNIT_WISE_REVISIT_PATIENT_DATEWISE="ServiceUnitWiseRevisitPatDateWise.jrxml";
	String SERVICE_UNIT_WISE_REVISIT_PATIENT_TODAY="ServiceUnitWiseRevisitPatToday.jrxml";
	String PATIENT_BELONGING_DETAIL="PatientBelongingDetails.jrxml";
	String SPECIAL_CLINIC_PAT_STATISTICS_TODAY="SpecialClinicPatStatToday.jrxml";
	String SPECIAL_CLINIC_PAT_STATISTICS_DATEWISE="SpecialClinicPatStatDateWise.jrxml";

	String SERVICE_UNIT_WISE_NEW_REG_TODAY="ServiceUnitWiseNewRegToday.jrxml";
	String SERVICE_UNIT_WISE_NEW_REG_DATEWISE="ServiceUnitWiseNewRegDateWise.jrxml";
	String POOR_FREE_PATIENTS_TODAY="PoorFreePatientsToday.jrxml";
	String POOR_FREE_PATIENTS_DATEWISE="PoorFreePatientsDateWise.jrxml";
	String FOLLOW_UP_PATIENTS_TODAY="FollowUpPatientsToday.jrxml";
	String FOLLOW_UP_PATIENTS_DATEWISE="FollowUpPatientsDateWise.jrxml";
	String MLC_PATIENTS_LIST_PGI_TODAY="MlcPatientListPgiToday.jrxml";
	String MLC_PATIENTS_LIST_PGI_DATEWISE="MlcPatientListPgiDateWise.jrxml";
	String ANONYMOUS_PAT_N_DET_WHO_BROUGHT_TODAY="AnonymousPatAndDetWhoBroughtToday.jrxml";
	String ANONYMOUS_PAT_N_DET_WHO_BROUGHT_DATEWISE="AnonymousPatAndDetWhoBroughtDateWise.jrxml";
	String ANONYMOUS_PATIENTS_COUNT_TODAY="AnonymousPatientPgiToday.jrxml";
	String ANONYMOUS_PATIENTS_COUNT_DATEWISE="AnonymousPatientPgiDateWise.jrxml";
	
	String HOSPITAL_CASH_COL_SUMMARY_TODAY="HospitalCashColSummaryToday.jrxml";
	String HOSPITAL_CASH_COL_SUMMARY_DATEWISE="HospitalCashColSummaryDateWise.jrxml";
	String NEW_OLD_TOTAL_PAT_OPD_WISE_TODAY="NewOldTotalPatOPDWiseToday.jrxml";
	String NEW_OLD_TOTAL_PAT_OPD_WISE_DATEWISE="NewOldTotalPatOPDWiseDateWise.jrxml";
	String DEPARTMENT_WISE_NEW_REG_TODAY="DeptWiseNewRegToday.jrxml";
	String DEPARTMENT_WISE_NEW_REG_DATEWISE="DeptWiseNewRegDateWise.jrxml";
	String PIN_CODE_WISE_STATISTICS_TODAY="PinCodeWiseStatisticsToday.jrxml";
	String PIN_CODE_WISE_STATISTICS_DATEWISE="PinCodeWiseStatisticsDateWise.jrxml";
	String NEW_PATIENTS_REGISTRATION_TODAY="NewPatientsRegistrationToday.jrxml";
	String NEW_PATIENTS_REGISTRATION_DATEWISE="NewPatientsRegistrationDateWise.jrxml";
	String DEPT_UNIT_WISE_CASH_COLLECTION_TODAY="DeptUnitWiseCashCollectionToday.jrxml";
	String DEPT_UNIT_WISE_CASH_COLLECTION_DATEWISE="DeptUnitWiseCashCollectionDateWise.jrxml";
	String DEPT_UNIT_WISE_TOTAL_PATIENT_STATISTICS_TODAY="DeptUnitWiseTotalPatientToday.jrxml";
	String DEPT_UNIT_WISE_TOTAL_PATIENT_STATISTICS_DATEWISE="DeptUnitWiseTotalPatientDateWise.jrxml";
	String DEPT_WISE_REG_CATEGORY_WISE_TODAY="DeptWiseRegCategoryWiseToday.jrxml";
	String DEPT_WISE_REG_CATEGORY_WISE_DATEWISE="DeptWiseRegCategoryWiseDateWise.jrxml";
	String DEPT_WISE_PAT_CAT_WISE_TODAY="DeptWisePatCatWiseToday.jrxml";
	String DEPT_WISE_PAT_CAT_WISE_DATEWISE="DeptWisePatCatWiseDateWise.jrxml";
	String AGE_WISE_DEPT_WISE_REG_PAT_TODAY="AgeWiseDeptWiseRegPatToday.jrxml";
	String AGE_WISE_DEPT_WISE_REG_PAT_DATEWISE="AgeWiseDeptWiseRegPatDateWise.jrxml";
	String EMERGENCY_PATIENT_LIST_DATEWISE="EmergencyPatListDateWise.jrxml";
	String EMERGENCY_PATIENT_LIST_TODAY="EmergencyPatListToday.jrxml";
	String CATEGORY_WISE_PATIENT_REGISTRATION_TODAY="CategoryWisePatRegToday.jrxml";
	String CATEGORY_WISE_PATIENT_REGISTRATION_DATEWISE="CategoryWisePatRegDateWisesd.jrxml";
	String AGE_WISE_TODAY="AgeWiseToday.jrxml";
	String AGE_WISE_DATEWISE="AgeWiseDateWisesd.jrxml";
	String EMERGENCY_REGISTERED_TODAY_NEW="EmergencyRegisteredPatTodayNew.jrxml";
	String EMERGENCY_REGISTERED_TODAY_OLD="EmergencyRegisteredPatTodayOld.jrxml";
	String EMERGENCY_REGISTERED_DATEWISE_OLD="EmergencyRegisteredPatOld.jrxml";
	String EMERGENCY_REGISTERED_DATEWISE_NEW="EmergencyRegisteredPatNew.jrxml";
	String GROUP_WISE_CASH_COLLECTED_TODAY_NEW="GroupWiseCashCollTodayNew.jrxml";
	String GROUP_WISE_CASH_COLLECTED_TODAY_OLD="GroupWiseCashCollTodayOld.jrxml";
	String GROUP_WISE_CASH_COLLECTED_DATEWISE_OLD="GroupWiseCashCollOld.jrxml";
	String GROUP_WISE_CASH_COLLECTED_DATEWISE_NEW="GroupWiseCashCollNew.jrxml";
	String MLC_PATIENT_LIST_TODAY="MlcPatLstToday.jrxml";
	String MLC_PATIENT_LIST_DATEWISE="MlcPatLstDateWise.jrxml";
	String BROUGHT_DEAD_PATIENT_TODAY="ListPatBroughtDeadToday.jrxml";
	String BROUGHT_DEAD_PATIENT_DATEWISE="ListPatBroughtDeadDateWise.jrxml";
	String PIN_CODE_WISE_TODAY_OLD="PINWISETODAYOLD.jrxml";
	String PIN_CODE_WISE_TODAY_NEW="PINWISETODAYNEW.jrxml";
	String PIN_CODE_WISE_DATEWISE_OLD="PINWISEOLD.jrxml";
	String PIN_CODE_WISE_DATEWISE_NEW="PINWISENEW.jrxml";
	String DEPARTMENT_WISE_REG_PAT_TODAY="DeptWiseRegPatLstReportToday.jrxml";
	String DEPARTMENT_WISE_REG_PAT_DATEWISE="DeptWiseRegPatLstReportDateWise.jrxml";
	String DEPARTMENT_WISE_TOTAL_PAT_TODAY="TotPatDeptWiseToday.jrxml";
	String DEPARTMENT_WISE_TOTAL_PAT_DATEWISE="TotPatDeptWiseDateWise.jrxml";
	String ROOM_WISE_TOTAL_PAT_TODAY="RoomWiseTotalPatToday.jrxml";
	String ROOM_WISE_TOTAL_PAT_DATEWISE="RoomWiseTotalPatDateWise.jrxml";
	String TOTAL_PAT_STAT_TODAY_OLD="TotPatStatTodayOld.jrxml";
	String TOTAL_PAT_STAT_TODAY_NEW="TotPatStatTodayNew.jrxml";
	String TOTAL_PAT_STAT_DATEWISE_OLD="TotPatStatOLD.jrxml";
	String TOTAL_PAT_STAT_DATEWISE_NEW="TotPatStatNew.jrxml";
	String USER_WISE_CASH_COLL_TODAY_OLD="UserWiseCashCollectionTodayOld.jrxml";
	String USER_WISE_CASH_COLL_TODAY_NEW="UserWiseCashCollectionTodayNew.jrxml";
	String USER_WISE_CASH_COLL_OLD="UserWiseCashCollectionOld.jrxml";
	String USER_WISE_CASH_COLL_NEW="UserWiseCashCollectionNew.jrxml";
	String USER_WISE_PAT_LIST_TODAY_OLD="userWisePatLstTodayOld.jrxml";
	String USER_WISE_PAT_LIST_TODAY_NEW="userWisePatLstTodayNew.jrxml";
	String USER_WISE_PAT_LIST_OLD="userWisePatLstOld.jrxml";
	String USER_WISE_PAT_LIST_NEW="userWisePatLstNew.jrxml";
	String USER_WISE_REG_TODAY="UserWiseRegToday.jrxml";
	String USER_WISE_REG_DATEWISE="UserWiseRegDateWise.jrxml";
	String UNKNOWN_PATIENT_REPORT_TODAY="UnknownPatientReportToday.jrxml";
	String UNKNOWN_PATIENT_REPORT_DATEWISE="UnknownPatientReportDateWise.jrxml";
	String MLC_PATIENT_LISTING_BY_ALL_CMO_REPORT_TODAY="MlcPatientListByAllCMOToday.jrxml";
	String MLC_PATIENT_LISTING_BY_ALL_CMO_REPORT_DATEWISE="MlcPatientListByAllCMODateWise.jrxml";
	String SHIFT_WISE_CASES_SEEN_BY_CMO_ALL_SHIFT_REPORT="ShiftWiseCaseSeenByCMODateWise.jrxml";
	String SHIFT_WISE_CASES_SEEN_BY_CMO_REPORT="ShiftWiseCaseSeenByCMOShiftWise.jrxml";
	String DIAGNOSIS_STATISTICAL_LOCATION_WISE_DATEWISE="DiagnosisStatLocationWiseDateWise.jrxml";
	String DIAGNOSIS_STATISTICAL_LOCATION_WISE_TODAY="DiagnosisStatLocationWiseToday.jrxml";
	String DEPT_UNIT_ROOM_CONSULTANT_TIMING_REPORT="deptUnitRoomConsultantTimingReport.jrxml";
	String DEPT_UNIT_ROOM_CONSULTANT_ROSTER_REPORT="deptUnitRoomConsultantRosterReport.jrxml";
	
	////////////////////New Reports,with changed Names and Path//////////////
	String DEPARTMENT_WISE_OUT_PATIENT_TODAY_REPORT="DepartmentWiseOutPatientTodayReport.jrxml";
	String DEPARTMENT_WISE_OUT_PATIENT_DATE_WISE_REPORT="DepartmentWiseOutPatientDateWiseReport.jrxml";
	String LIST_OF_FREE_PATIENTS_TODAY="ListOfPoorFreePatientsToday.jrxml";
	String LIST_OF_FREE_PATIENTS_DATEWISE="ListOfPoorFreePatientsDateWise.jrxml";
	String FEES_COLLECTED_FOR_OPD_SPECIAL_CLINIC_TODAY="FeesCollectedForOpdSpecialClinicToday.jrxml";
	String FEES_COLLECTED_FOR_OPD_SPECIAL_CLINIC_DATEWISE="FeesCollectedForOpdSpecialClinicDateWise.jrxml";
	String NO_OF_ANONYMOUS_PATIENTS_LIST_TODAY="NoOfAnonymousPatientListToday.jrxml";
	String NO_OF_ANONYMOUS_PATIENTS_LIST_DATEWISE="NoOfAnonymousPatientListDateWise.jrxml";
	String SERVICE_UNIT_WISE_NEW_REG_TODAY_REPORT1="ServiceUnitWiseNewRegToday.jrxml";
	String SERVICE_UNIT_WISE_NEW_REG_DATEWISE_REPORT1="ServiceUnitWiseNewRegDateWise.jrxml";
	String SERVICE_UNIT_WISE_REVISIT_PATIENT_DATEWISE_REPORT1="ServiceUnitWiseRevisitPatDateWise.jrxml";
	String SERVICE_UNIT_WISE_REVISIT_PATIENT_TODAY_REPORT1="ServiceUnitWiseRevisitPatToday.jrxml";
	String SPECIAL_CLINIC_PAT_STATISTICS_TODAY_REPORT1="SpecialClinicPatStatToday.jrxml";
	String SPECIAL_CLINIC_PAT_STATISTICS_DATEWISE_REPORT1="SpecialClinicPatStatDateWise.jrxml";
	String MLC_PATIENTS_LIST_PGI_TODAY_REPORT1="MlcPatientListPgiToday.jrxml";
	String MLC_PATIENTS_LIST_PGI_DATEWISE_REPORT1="MlcPatientListPgiDateWise.jrxml";
	String ANONYMOUS_PAT_N_DET_WHO_BROUGHT_TODAY_REPORT1="AnonymousPatAndDetWhoBroughtToday.jrxml";
	String ANONYMOUS_PAT_N_DET_WHO_BROUGHT_DATEWISE_REPORT1="AnonymousPatAndDetWhoBroughtDateWise.jrxml";
	
	String AGE_WISE_DATEWISE_REPORT1="NEwAgeWiseRegReportDATEWISE.jrxml";	
	String CONSULTANT_ROSTER_REPORT="ConsultantRosterReport.jrxml";
	String DEPARTMENT_WISE_LIST_OF_PATIENTS_TODAY_ORDER_BY_ENTRY_DATE="DeptWiseListofPatientsTodayOrderByEntryDate.jrxml";
	String DEPARTMENT_WISE_LIST_OF_PATIENTS_TODAY_ORDER_BY_NAME="DeptWiseListofPatientsTodayOrderByName.jrxml";
	String DEPARTMENT_WISE_LIST_OF_PATIENTS_TODAY_ORDER_BY_CR_NO="DeptWiseListofPatientsTodayOrderByCrNo.jrxml";
	String DEPARTMENT_WISE_LIST_OF_PATIENTS_TODAY_ORDER_BY_FILE_NO="DeptWiseListofPatientsTodayOrderByFileNo.jrxml";
	String DEPARTMENT_WISE_LIST_OF_PATIENTS_DATEWISE_ORDER_BY_ENTRY_DATE="DeptWiseListofPatientsDateWiseOrderByEntryDate.jrxml";
	String DEPARTMENT_WISE_LIST_OF_PATIENTS_DATEWISE_ORDER_BY_NAME="DeptWiseListofPatientsDateWiseOrderByName.jrxml";
	String DEPARTMENT_WISE_LIST_OF_PATIENTS_DATEWISE_ORDER_BY_CR_NO="DeptWiseListofPatientsDateWiseOrderByCrNo.jrxml";
	String DEPARTMENT_WISE_LIST_OF_PATIENTS_DATEWISE_ORDER_BY_FILE_NO="DeptWiseListofPatientsDateWiseOrderByFileNo.jrxml";
	
	String AGE_WISE_TODAY_REPORT1="NEwAgeWiseRegReportTODAY.jrxml";
	String AGE_WISE_TODAY_REPORT1_NEW_PATIENT="NEwAgeWiseRegReportTODAYNewPatient.jrxml";
	String AGE_WISE_TODAY_REPORT1_OLD_PATIENT="NEwAgeWiseRegReportTODAYOldPatient.jrxml";
	String AGE_WISE_DATE_WISE_REPORT1="NewAgeWiseRegReportDateWiseNew.jrxml";
	String AGE_WISE_DATE_WISE_REPORT1_NEW_PATIENT="NewAgeWiseRegReportDateWiseNewForNewPatient.jrxml";
	String AGE_WISE_DATE_WISE_REPORT1_OLD_PATIENT="NewAgeWiseRegReportDateWiseNewForOldPatient.jrxml";
	String AGE_WISE_MONTH_WISE_REPORT1="NewAgeWiseRegReportMonthWiseNew.jrxml";
	String AGE_WISE_MONTH_WISE_REPORT1_NEW_PATIENT="NewAgeWiseRegReportMonthWiseNewForNewPatient.jrxml";
	String AGE_WISE_MONTH_WISE_REPORT1_OLD_PATIENT="NewAgeWiseRegReportMonthWiseNewForOldPatient.jrxml";
	String AGE_WISE_YEAR_WISE_REPORT1="NewAgeWiseRegReportYearWiseNew.jrxml";
	String AGE_WISE_YEAR_WISE_REPORT1_NEW_PATIENT="NewAgeWiseRegReportYearWiseNewForNewPatient.jrxml";
	String AGE_WISE_YEAR_WISE_REPORT1_OLD_PATIENT="NewAgeWiseRegReportYearWiseNewForOldPatient.jrxml";
	
	String DEPT_USER_WISE_LIST_OF_NEW_PATIENT_REPORT_ORDER_BY_DATE="DeptWiseUserIpWiseListofNewPatientsOrderByDate.jrxml";
	String DEPT_USER_WISE_LIST_OF_NEW_PATIENT_REPORT_ORDER_BY_NAME="DeptWiseUserIpWiseListofNewPatientsOrderByName.jrxml";
	String DEPT_USER_WISE_LIST_OF_NEW_PATIENT_REPORT_ORDER_BY_CRNO="DeptWiseUserIpWiseListofNewPatientsOrderByCrNo.jrxml";
	String DEPT_USER_WISE_LIST_OF_NEW_PATIENT_REPORT_ORDER_BY_FILENO="DeptWiseUserIpWiseListofNewPatientsOrderByFileNo.jrxml";
	String DEPT_USER_WISE_LIST_OF_OLD_PATIENT_REPORT_ORDER_BY_DATE="DeptWiseUserIpWiseListofOldPatientsOrderByDate.jrxml";
	String DEPT_USER_WISE_LIST_OF_OLD_PATIENT_REPORT_ORDER_BY_NAME="DeptWiseUserIpWiseListofOldPatientsOrderByName.jrxml";
	String DEPT_USER_WISE_LIST_OF_OLD_PATIENT_REPORT_ORDER_BY_CRNO="DeptWiseUserIpWiseListofOldPatientsOrderByCrNo.jrxml";
	String DEPT_USER_WISE_LIST_OF_OLD_PATIENT_REPORT_ORDER_BY_FILENO="DeptWiseUserIpWiseListofOldPatientsOrderByFileNo.jrxml";
	String DEPT_USER_WISE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_DATE="DeptWiseUserIpWiseListofAllPatientsOrderByDate.jrxml";
	String DEPT_USER_WISE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_NAME="DeptWiseUserIpWiseListofAllPatientsOrderByName.jrxml";
	String DEPT_USER_WISE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_CRNO="DeptWiseUserIpWiseListofAllPatientsOrderByCrNo.jrxml";
	String DEPT_USER_WISE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_FILENO="DeptWiseUserIpWiseListofAllPatientsOrderByFileNo.jrxml";
	String DEPT_USER_WISE_LIST_OF_REVISIT_PATIENT_REPORT_ORDER_BY_DATE="DeptWiseUserIpWiseListofRevisitPatientsOrderByDate.jrxml";
	String DEPT_USER_WISE_LIST_OF_REVISIT_PATIENT_REPORT_ORDER_BY_NAME="DeptWiseUserIpWiseListofRevisitPatientsOrderByName.jrxml";
	String DEPT_USER_WISE_LIST_OF_REVISIT_PATIENT_REPORT_ORDER_BY_CRNO="DeptWiseUserIpWiseListofRevisitPatientsOrderByCrNo.jrxml";
	String DEPT_USER_WISE_LIST_OF_REVISIT_PATIENT_REPORT_ORDER_BY_FILENO="DeptWiseUserIpWiseListofRevisitPatientsOrderByFileNo.jrxml";
	String DEPT_WISE_CONSOLIDATE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_DATE="DeptWiseConsolidateListofAllPatientsOrderByDate.jrxml";
	String DEPT_WISE_CONSOLIDATE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_FILENO="DeptWiseConsolidateListofAllPatientsOrderByFileNo.jrxml";
	
	
	String DEPT_PERCENTAGE_WISE_REPORT_DATEWISE="DepartmentPercentageWiseStatisticsDateWise.jrxml";
	
	String SPECIAL_CLINIC_OUT_PATIENT_STATISTICS_TODAY="SpecialClinicOutPatientStatisticsToday.jrxml";
	String SPECIAL_CLINIC_OUT_PATIENT_STATISTICS_DATEWISE="SpecialClinicOutPatientStatisticsDateWise.jrxml";
	String SPECIAL_CLINIC_OUT_PATIENT_STATISTICS_MONTHWISE="SpecialClinicOutPatientStatisticsMonthWise.jrxml";
	String SPECIAL_CLINIC_OUT_PATIENT_STATISTICS_YEARWISE="SpecialClinicOutPatientStatisticsYearWise.jrxml";
	
	String FREE_NEW_DEPT_VISIT_REPORT="FreeNewDeptVisitReport.jrxml";
	
	String HOSPITAL_WISE_PATIENT_LISTING_REPORT="HospitalWiseListofAllPatients.jrxml";
	String ALL_HOSPITAL_PATIENT_LISTING_REPORT="AllHospitalListofAllPatients.jrxml";

	String PATIENT_LISTING_REPORT_JRXML="PatientListingReport.jrxml";
	String PATIENT_LISTING_REPORT_GROUP_WISE_JRXML="PatientListingReportGroupWise.jrxml";
	String PATIENT_LISTING_REPORT_CONSOLIDATED_JRXML="PatientListingReportConsolidated.jrxml";
		
	
	String USER_WISE_REGISTRATION_REPORT="UserWiseRegistration.jrxml";
	
	String LIST_OF_PATIENTS_REFER_INOUT_TODAY="ListOfPatientsReferInOutToday.jrxml";
	String LIST_OF_PATIENTS_REFER_INOUT_DATEWISE="ListOfPatientsReferInOutDateWise.jrxml";
	
	String REG_CAT_NORMAL="NORMAL";
	String CHARGES="charges";
	String DEPARTMENT_CODE="deptCode";
	//String DEFAULT_PRIMARY_CATEGORY="11";
	String AMOUNT_COLLECTED="amountCollected";

	
	String UPLOAD_CHART_IMAGE="chartImage";

	String VIEWORMODIFY="viewOrModify";
	String MISTAKE="1";
	String ADDITION="2";

	String REGISTRATION_TYPE_GENERAL_OPD="generalOpd";
	String REGISTRATION_TYPE_SPECIAL_CLINIC="specialClinic";
	String REGISTRATION_TYPE_EMERGENCY_CLINIC="emergencyClinic";
	String EMPLOYEE_MASTER_VO_ARRAY="empMasterVOArray";
	String EMPLOYEE_MASTER_VO="empMasterVO";
	String REG_DESK_UNIT_WITH_SPECIALITY="specialUnits";
	String UNIT_CONSULTANT_LIST="unitConsultantList";
	
	//String REGISTRATION_TYPE_GENERAL_OPD="generalOpd";
	//String REGISTRATION_TYPE_SPECIAL_CLINIC="specialClinic";
	String PATIENT_CAT="patientCat";
	

	///////**************************************************
	
	//String DEPT_UNIT_ROOM_MASTER_SEQUENCE="deptunitroommstsequence";
	/////////////////////////////////////////
	String NEW_REGISTRATION_SLIP="0";

	/////////////////////////// Renewwal//////////////////////
	String ISEXPIRY_TYPE_NOT_REQ="0";
	String ISEXPIRY_TYPE_MONTH="1";
	String ISEXPIRY_TYPE_DAY="2";
	
	//String IS_CARD_PRINT="1";		//0 if card printing is not required after renewal of registration
									//1 if card printing is required after renewal of registration
	
	String RENEWAL_REQUIRED_EPISODE_ARRAY="arrRenewalRequiredEpisode";
	
	String REGISTRATION_RENEWED_EPISODE_ARRAY="registrationRenewedEpisodeArray";
	
	String REGISTRATION_RENEWED="registrationRenewed";
	
	String SPECIFY_EXPIRY_TRUE="1";		//Specify expiry date when new episode is opened
	String SPECIFY_EXPIRY_FALSE="0";	//Not to specify expiry date when old episode is continued

	String DIAGNOSIS_LIST="diagnosisList";
	String ICD_CODE_LIST="icdCodeList";
	String UNIT_DIAGNOSIS_TYPE_CODE="unitDiagnosisType";
	
	String SEARCH_LIST_DIAGNOSIS_CODE="searchListDiagnosisCode";
	
	String DISEASE_NAME="1";
	String ICD_CODE="0";

	String REFERED_FLAG_PAT_MOD="referFlagForPatMod"; //refer flag for 10min modification

	////////////////////////////////////////Process ID////////////////////////////////////
	String NEW_REGISTRATION_PROCESS_ID="10001";
	String MLC_PROCESS_ID="10002";
	String PATIENT_CATEGORY_PROCESS_ID="10003";

	
	
	/////Display Flag for Yellow Slip Entry///////////
	String DISPLAY_FLAG_NONE="1";
	String DISPLAY_FLAG_RIGHT="2";
	String DISPLAY_FLAG_LEFT="3";
	
	String DISPLAY_FLAG_LABEL_NONE="NONE";
	String DISPLAY_FLAG_LABEL_RIGHT="RIGHT";
	String DISPLAY_FLAG_LABEL_LEFT="LEFT";
	String DOCUMENT_DTL_COLL="documentDtlCollection";
	String VERIFICATION_DTL="verificationDtl";
	String IS_EPISODE_OPEN="0";
//////////////////////////////////////////URL/////////////////////
	String REG_URL_FOR_AMOUNT_BY_CAT="/AHIMS/AmountByPatientCat";
	String REG_URL_FOR_STATE_CHANGE="/AHIMS/DistrictListByState";
	

	////////PATIENT REFER LIST FLAG//////////////////
	String IS_PAT_REFER_BY_LIST_TRUE="1"; 
	String IS_PAT_REFER_BY_LIST_FALSE="2";
	
	
	
	/////////////////////////registration shift type//////////
	
	String SHIFT_TYPE_OPD="1";
	String SHIFT_TYPE_REGISTRATION="2";
	
		////////////////////////////////////////////////////////////////////////
		
	///////////////Revoking Secondary Category/////////////
	String REVOKE_TREATMENT_CATEGORY_VALUE="0";
	
	String ARRAY_PRIMARY_CATEGORY_CHANGE_VOS="arrayPrimaryCategoryChangeVO";
	
	

	
	String ARRAY_REGISTRATION_TIMING_VOS = "arrayRegistrationTimingVO";
	
	
	String PATIENT_STATUS_LIST = "PatientStatusList";
	/////////////////////////////////////For Default Image////////////////////
	String IS_IMAGE_DEFAULT_FALSE="0";
	String IS_IMAGE_DEFAULT_TRUE="1";
	/////////////////////////////////////////////////////////////////////////
	


	String ARRAY_DEPT_GROUPING_VO="arrayDeptgroupingVO";
	String ARRAY_UNIT_GROUPING_VO="arrayUnitgroupingVO";
	String ALL_DEPT_EXCEPT_FROM_DEPT="allDeptExceptFromDept";
	String ALL_UNIT_EXCEPT_FROM_UNIT="allUnitExceptFromUnit";
	
	String GROUPING_TYPE_DEPT_WISE="1";
	String GROUPING_TYPE_DEPT_UNIT_WISE="2";
	String GROUPING_MODE_SINGLE="1";
	String GROUPING_MODE_VICE_VERSA="2";

	/////////value for duplicate card printing//////////
	
	String DUPLICATE_CARD="1";
	String REPRINT_REGISTRATION="2";
	String REPRINT_OLD_VISIT_SLIP="3";
	String REPRINT_RENEWAL="4";
    String PRINT_BACK_PAGE = "5";
	////////////////////////////////////////
	
	String EMERGENCY_CASE_LIST="emergencyCasesList";

	String IS_ADDRESS_CURRENT_TRUE="1";
	String IS_ADDRESS_CURRENT_FALSE="0";
	String IS_ADDRESS_MODIFIED="0";
	String IS_ADDRESS_ADDED="1";
	//////////////////////////////////Brought Dead Flag //////////////////////////////// 
	String IS_BROUGHT_DEAD_FALSE="0";
	String IS_BROUGHT_DEAD_TRUE="1";
	/////////////////////////////////////////////////////////////////////////
	String IS_CARD_PRINT_FALSE="0";
	String IS_CARD_PRINT_NEW_DEPARTMENT="1";
	String IS_CARD_PRINT_OLD_PATIENT="2";
	/////////////////////////////
	
	
	String IS_BROUGHT_BY_FALSE="0";
	String IS_BROUGHT_BY_TRUE="1";
	String IS_RELATIVE_FALSE="0";
	String IS_RELATIVE_TRUE="1";
	
	String ESSENTIAL_INJURY_TYPE_LIST="essentialInjuryTypeList";
	String ESSENTIAL_INJURY_NATURE_LIST="essentialInjuryNatureList";
	
	
	//report hospital details
	String HOSPITAL_NAME="Civil Hospital";
	String HOSPITAL_ADDRESS="Alibagh,Maharashtra-110026 (INDIA)";
	String HOSPITAL_CONTACT_INFORMATION="";
	String HOSPITAL_SHORT_NAME="PGI";
	
	//Patient Category ID Required
	String PAT_CAT_ID_REQUIRED_YES="1";
	String PAT_CAT_ID_REQUIRED_NO="0";
	
	//Location Master Details
	String BUILDING="buildinglist";
	String BLOCK="blocklist";
	String FLOOR="floorlist";
	String LOCATION_TYPE="locationtypelist";
	String ROOM_NO="roomNotypelist";
	
	String ESSENTIAL_LOCATION_MASTER_VO="essentialLocationMasterVO";
	String ESSENTIAL_DISCLAIMER_MASTER_VO="essentialDisclaimerMasterVO";
	String DISCLAIMER_DEFAULT_YES="1";
	String DISCLAIMER_DEFAULT_NO="0"; 
	
	String DISCLAIMER_MAPPING_DEFAULT="DEFAULT"; //1-Mapping Required YES, 0-Mapping Required NO
	String DISCLAIMER_MAPPING_DEPARTMENT_WISE="DEPARTMENT_WISE";
	String DISCLAIMER_MAPPING_UNIT_WISE="UNIT_WISE";
	

	String SMODE_ADDITION="1";
	String SMODE_MODIFICATION="2";
	
	String DEPARTMENT_MASTER_PNO="1";

	String UNIT_MASTER_PNO="4";
	String EXTERNAL_INSTITUTE_MASTER_MODIFY_PNO="5";

	String PATIENT_CAT_MASTER_PNO="2";
	String SEASON_MASTER_PNO="3";
	


	String VO_OF_HRGT_PATIENT_DTL="voOfHrgtPatientDetail";

	String CMO_LIST="cmolist";
	String SHIFT_LIST="shiftlist";

	
	String VO_S_OF_HRGT_PATIENT_AUDIT_DTL="voSOfHrgtPatientAuditDetail";
	
	String VO_S_OF_HRGT_PATIENT_ADD_DTL="voSOfHrgtPatientAddDetail";
	
	String VO_S_OF_HRGT_PRICAT_CHANGE_DTL="voSOfHrgtPrimaryCategoryChangeDetail";
	
	String VO_S_OF_HRGT_SECCAT_CHANGE_DTL="voSOfHrgtSecondaryCategoryChangeDetail";
		
	String VO_S_OF_HRGT_UNKNOWN_CHANGE_DTL="voSOfHrgtUnknownToKnownChangeDetail";
	
	String VO_S_OF_HRGT_PATIENT_MLC_DTL="voSOfHrgtPatientMlcDetail";
	//////////MLC Details//
	String MLC_PATIENT_COMES_HIMSELF="0";
	String MLC_PATIENT_BROUGHT_BY_POLICE="1";
	
	///////////////////////////
	
	/************Diclaimer Master flag ************/
	String DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL="1";
	String DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL="2";
	String DEFAULT_DISCLAIMER_USABILITY_FLAG_CASUALTIY="3";
	String DISCLAIMER_IS_HEADER_YES="1";
	String DISCLAIMER_IS_HEADER_NO="0";
	String DISCLAIMER_ALIGN_CENTER="1";
	String DISCLAIMER_ALIGN_RIGHT="2";
	String DISCLAIMER_ALIGN_LEFT="3";
	
	/***************/
	
	String PATIENT_BROUGHT_BY_DETAIL_VO="patientBroughtByDetail";
	
	
	String ALL_PATIENT_VO_LIST="allPatientVOList";
	String REGISTRATIONDESK_PATIENT_VO="registrationPatientVo";
	
	//Lucene Directory
	String LUCENE_INDEX_SEARCH_OBJECT="luceneIndexSearchObject";
	String LUCENE_DIRECTORY_OBJECT_FOR_WINDOWS="c:/RAOL/AHIS/luceneDir/indexFile";
	String LUCENE_DIRECTORY_OBJECT_FOR_LINUX="\\root\\RAOL\\AHIS\\luceneDir\\indexFile";
	int SIZE_OF_LUCENE_DIRECTORY=5000;
	
	String PATIENT_STATUS_FIT="1";
	String PATIENT_STATUS_UNFIT="0";
	String PATIENT_CONDITION_MACRO_LIST="patientConditionMacroList";
	
	String PATIENT_INJURY_WITHIN_24_HOURS="1";
	String PATIENT_INJURY_AFTER_24_HOURS="2";
	
	String DUTY_OFFICER_IS_IO="0";
	String DUTY_OFFICER_IS_OTHER="1";
	
	String HANDOVER_TO_IO="0";
	String HANDOVER_TO_DUTY_OFFICER="1";
	String HANDOVER_TO_OTHER="2";
	String MLC_VO_POLICE_VERIFICATION="mlcVOPoliceVerification";
	String MLC_NO_IS_DUPLICATE_YES="0";
	String MLC_NO_IS_DUPLICATE_NO="1";
	
	String POLICE_VERIFICATION_DETAIL_VO="policeVerificationDetailVO";
	String PATIENT_BROUGHT_BY_VO="patientBroughtByVO";
	
	//patient brought by
	String PATIENT_BROUGHT_BY_OTHER="0";
	String PATIENT_BROUGHT_BY_RELATIVE="1";
	String PATIENT_BROUGHT_BY_POLICE="2";
	String PATIENT_BROUGHT_BY_108="3";
	
	String ESSENTIAL_DEATH_MANNER_LIST="essentialDeathMannerList";
	String ESSENTIAL_DEATH_ON_SET_DATE_N_RECENT_VISIT_DATE="essentialDeathOnSetDateNRecentVisitDate";
	String IS_PREGNATNT_YES="1";
	String IS_PREGNATNT_NO="0";
	String IS_DELIVERY_YES="1";
	String IS_DELIVERY_NO="0";
	String IS_HANDOVER_TRUE="1";
	String IS_HANDOVER_FALSE="1";
	
	//Body handover TO
	String 	DEAD_BODY_HANDOVER_TO_MORTUARY="1";
	String 	DEAD_BODY_HANDOVER_TO_POLICE="2";
	String 	DEAD_BODY_HANDOVER_TO_RELATIVES="3";
	
	//Patient Category Type
	String PATIENT_CATEGORY_TYPE_PATIENT_CATEGORY="0";
	String PATIENT_CATEGORY_TYPE_TREATMENT_CATEGORY="1";
	String PATIENT_CATEGORY_TYPE_OTHER="2";
	

	////Process Id For Desgnation/////////
	String DEPARTMENT_DESIGNATION_MAPPING_PROCESS_ID="1";
	String UNIT_CONSULTANT_DESIGNATION_MAPPING_PROCESSID="2";
	String CMO_DESIGNATION_MAPPING_PROCESSID="9";
	
	//Death Certificate No of Copies////
	String NO_OF_DEATH_CERTIFICATE_COPIES_NORMAL="4";
	String NO_OF_DEATH_CERTIFICATE_COPIES_MLC="5";
	
	String PRINT_DEATH_CERTIFICATE_YES="1";
	String PRINT_DEATH_CERTIFICATE_NO="0";
	
	String CERTIFICATE_TYPE_DEATH_CERTIFICATE="15";
	String RECORD_TYPE_DEATH_CERTIFICATE="15";
	
	String IS_MLC_YES="1";
	String IS_MLC_NO="0";
	
	String IS_DUPLICATE_CERTIFICATE_YES="1";
	String IS_DUPLICATE_CERTIFICATE_NO="0";

	String PRINT_FLAG_YES="1";
	String PRINT_FLAG_NO="0";
	
	///FOR identifying patient detail modification is part of which desk
	///registration or special
	String REGISTRATION_DESK_TYPE="registrationDeshType";
	String REGISTRATION_DESK_TYPE_SPECIAL="special";
	String REGISTRATION_DESK_TYPE_ROOM_WISE="roomWise";
	
	// Deadbody Handover 
	
	String DEADBODY_HANDOVER_DETAIL="Deadbody Handover Detail";
	String LIST_OF_DEAD_PATIENT="ListOfDeadPatient";
	
	String CLINICAL_DEPT_LIST="clinicalDeptList";

	////Modification Requested By
	String MODIFICATION_REQUESTED_BY_SELF="0";
	String MODIFICATION_REQUESTED_BY_RELATIVE="1";
	String MODIFICATION_REQUESTED_BY_POLICE="2";
	
	String RELATION_MASTER_VALUE_FOR_SELF="10";
	
	
	//////valid age for requesting modification///
	String VALID_AGE_FOR_REQUESTING_MODIFICATION="14";
	
	String YES="1";
	String NO="0";
	String ANC_DETAIL_FOR_DEATH_PATIENT_VO="ancDetailForDeathPatientVO";
	
	/* **************Yellow Slip Entry*****************************************/
	String YELLOW_SLIP_ENTRY_MODE_ENTRY="1";
	String YELLOW_SLIP_ENTRY_MODE_MODIFICATION="2";
	String PREVIOUS_DIAGNOSIS_VO_LIST="previousDiagnosisVOList";
	
	
	// yellow Slip monitoring
	
	String YELLOW_SLIP_MONITORING_FLAG_NO_ERROR="1";
	String YELLOW_SLIP_MONITORING_FLAG_MINOR_ERROR="2";
	String YELLOW_SLIP_MONITORING_FLAG_MAJOR_ERROR="3";
	
	String YELLOW_SLIP_ENTRY_USER_LIST="yellowSlipEntryUserList";
	String YELLOW_SLIP_MONITORING_VO_LIST="yellowSlipMonitoringVOList";
	String YELLOW_SLIP_ENTRY_VO_LIST="yellowSlipEntryVOList";
	
	String EPISODE_DIAGNOSIS_VO_LIST="episodeDiagnosisVOList";
	
	////////document upload flags
	
	String DOCUMENT_TYPE_DOC="1";
	String DOCUMENT_TYPE_AUDIO_VIDEO="2"; // Used as External Investigation Report
	String DOCUMENT_TYPE_MLC="3";
	
	String DOCUMENT_TYPE_DOC_LABEL="Patient Documents";
	String DOCUMENT_TYPE_AUDIO_VIDEO_LABEL="External Investigation Reports";//"Audio/Video File";-- Commented By Pragya 2014.08.22
	String DOCUMENT_TYPE_MLC_LABEL="MLC Document";
	
	

	String ESSENTIAL_BO_OPTION_UNIT_BASED_ON_WEEKDAY="unitBasedOnWeekDay";

	//Unit---> Is Unit////
	String IS_UNIT_DEPARTMENT_UNIT="0";
	String IS_UNIT_DOCTOR_UNIT="1";
	
	String ADMITTED_PATIENT_VO="admittedPatientVO";
	String AGE_RANGE_OPTION_LIST="ageRangeOptionList";
	
	String ESSENTIAL_OPTION_ROOM_LIST="essentialOptionRoomList";

	////Room Usabilty Flag
	
	String ROOM_USABILITY_NO_BOUND="0";
	String ROOM_USABILITY_OLD_PATIENT="1";
	String ROOM_USABILITY_EXTERNAL_REFERRAL="2";
	String ROOM_USABILITY_INTERNAL_REFERRAL="3";
	String ROOM_USABILITY_FORCEFUL="4";
	String ROOM_USABILITY_NEW_OLD="5";
	String ROOM_USABILITY_NEW_OLD_FORCEFUL="6";
	String ROOM_USABILITY_NOT_REQUIRED="7";
	
	//////Week of Month Values stored in Database////////
	
	String WEEK_OF_MONTH_1="1";
	String WEEK_OF_MONTH_2="2";
	String WEEK_OF_MONTH_3="3";
	String WEEK_OF_MONTH_4="4";
	String WEEK_OF_MONTH_5="5";
	
	
	////income check for bpl
	String BPL_MONTHLY_INCOME="2000";
	//////age wise department check
	String MAX_AGE_TO_REGISTER_IN_CHILD_DEPT="14";
	String CHILD_DEPT_CODE="123,152,153";

	//////////////////////
	String ESSENTIAL_OPTION_CONSTULTANT_HOU_1_2_LIST="essentialOptionConsultantHou12List";
	
	String CONSULTANT_IS_BOUND="0";
	String CONSULTANT_IS_NOT_BOUND="1";

	String UNIT_CONSULTANT_ROSTER_MASTER_VO_LIST="unitConsultantRosterMasterVOList";

	String ALL_UNIT_CONSULTANT_ROSTER_MASTER_VO_LIST_EXCEPT_SELECTED_UNIT_ROOM="AllUnitConsultantRosterMasterVOListExceptSelectedUnitRoom";
	
	
////////Lists used in Department Unit Room Consultant Roster Master 
	
	String LIST_FOR_WEEK1_OF_MONTH="listForWeek1OfMonth";
	String LIST_FOR_WEEK2_OF_MONTH="listForWeek2OfMonth";
	String LIST_FOR_WEEK3_OF_MONTH="listForWeek3OfMonth";
	String LIST_FOR_WEEK4_OF_MONTH="listForWeek4OfMonth";
	String LIST_FOR_WEEK5_OF_MONTH="listForWeek5OfMonth";
	String LIST_FOR_SHIFTS="listForShifts";
	String SHIFT_FOR_DEPT_UNIT_ROOM_CONSULTANT_ROSTER_MST="shiftForDeptUnitRoomConsultantRosterMst";
	
	String  COLLECTION_OF_ROSTER_MASTER_VO="CollectionOfRosterMasterVO";
	
	String CONSULTANT_ROSTER_VALUE="1";
	
	
//////Days of Week Values stored in Database////////
	
	String DAY_OF_WEEK_SUNDAY="1";
	String DAY_OF_WEEK_MONDAY="2";
	String DAY_OF_WEEK_TUESDAY="3";
	String DAY_OF_WEEK_WEDNESDAY="4";
	String DAY_OF_WEEK_THURSDAY="5";
	String DAY_OF_WEEK_FRIDAY="6";
	String DAY_OF_WEEK_SATURDAY="7";

	////Category Wise Verification Document Master
	
	String VERIFICATION_DOCUMENT_MAPPED_IN_PRIMARY_CATEGORY="verificationDocMappedInPrimaryCategory";
	
	String VERIFICATION_DOCUMENT_NOT_MAPPED_IN_PRIMARY_CATEGORY="verificationDocNotMappedInPrimaryCategory";
	
	String PATIENT_CATEGORY_VERIFICATION_MST_IS_REQ="0";
	
	///vERIFICATION dOCUMENT ADDED OR NOT 
	
	String VERIFICATION_DOCUMENT_ADDED="1";

	
	String VERIFICATION_DOCUMENT_NOT_ADDED="0";
	
	//////// Triage Process ///////////////////////////
	String PATIENT_EPISODE_TRIAGE_DETAIL_VO = "patientEpisodeTriageDetailVO";
	
	
	String DEPT_WISE="1";
	
	String UNIT_WISE="2";
	
	String UNITS_BASED_ON_SPECIALITY="unitsBasedOnSpeciality";
	
	String IS_FILE_REQUIRED_TRUE="1";
	String IS_FILE_REQUIRED_FALSE="0";

	
	String HOSPITAL_PAT_STAT_VO_ARRAY="hosPatStatVOArray";
	String HOSPITAL_PAT_STAT_STATEWISE_VO_ARRAY="hosPatStatStateWiseVOArray";
	String HOSPITAL_PATIENT_STATISTICS_DATEWISE_REPORT1="NewUpdatedHospitalPatStatisticsDateWise.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_MONTHWISE_REPORT1="NewUpdatedHospitalPatStatisticsMonthWise.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_YEARWISE_REPORT1="NewUpdatedHospitalPatStatisticsYearWise.jrxml";
	String HOSPITAL_PATIENT_STATISTICS_TODAY_REPORT1="NewHospitalPatStatisticsToday.jrxml";
	String HOSPITAL_PAT_STAT_REPORT_TITLE="Out Patient Statistics";
	String HOSPITAL_PAT_STAT_STATE_WISE_REPORT_TITLE="Daily Census Of State Wise Outdoor/Indoor/Emergency Admission/New Registration";
	String AGE_WISE_REG_REPORT_TITLE="Daily Census Of Age Wise Outdoor/Indoor/Emergency Admission/New Registration";
	String DATELABEL_DATE="Date";
	String DATELABEL_MONTH="Month";
	String DATELABEL_YEAR="Year";
	

	///////////////////redirect of OPD Patient ////////////////////
	String LIST_OF_DEPT_UNIT_BY_ROSTER="listOfDepatUnitByRoster";
	String ROOM_LIST_BY_DEPT_UNIT="roomListByDeptUnit";
	String PARTICULAR_ROOM_DETAIL_BY_DEPT_UNIT="particularRoomDetailByDeptUnit";
	String TODAY_VISIT_PAT_LIST_BY_ROOM="todayVisitPatListByPat";
	String LIST_OF_ALL_ACTIVE_DEPT_UNIT_BY_ROSTER="allActiveDepatUnitList";
	String ALL_ACTIVE_ROOM_LIST_BY_DEPT_UNIT="allActiveRoomListByDeptUnit";
	String TEMP_TODAY_PAT_VISIT_LIST="tempTodayPatVisitList";
	
	String MODE_OF_TRANSFER_ALL_PAT="0";
	String MODE_OF_TRANSFER_PAT_WISE="1";
	
	String DEACTIVATE_ROOM_NO="0";
	String DEACTIVATE_ROOM_YES="1";
	
	String AGE_WISE_REPORT_VO_ARRAY="ageWiseReportVOArray";
	
	String ESSENTIAL_BO_OPTION_DEPT_UNIT="essentialOptionDeptUnit";
	String ESSENTIAL_BO_OPTION_DEPT_UNIT_VIEW="essentialOptionDeptUnitView";
	String ESSENTIAL_BO_OPTION_ROOM="essentialOptionRoom";
	String ESSENTIAL_BO_OPTION_ROOM_VIEW="essentialOptionRoomView";
	
	
	//Unit Datewise Roster Active /Inactive Room List
	String UNIT_DATE_WISE_ACTIVE_ROOM_LIST="unitDateWiseActiveRoomList";
	String UNIT_DATE_WISE_INACTIVE_ROOM_LIST="unitDateWiseInActiveRoomList";
	String REDIRECT_TO_OLD_PATIENT_PROCESS="redirectToOldPatientProcess";
	
	
	//Unit Room Master Capacity Mode
	String UNIT_ROOM_WISE_CAPACITY_MODE="1";
	String UNIT_ROOM_DAY_WISE_CAPACITY_MODE="2";
	
	String ESSENTIAL_DISEASE_SIDE_LIST="essentialDiseaseSideList";
	

	// Department Counter Mapping
	 String COUNTER_NAME_LIST = "CounterList"; 
	 String DEPT_NOT_ADDED_TO_COUNTER_DEPT_LIST = "departmentNotAddedToCounterDeptList";
	 String DEPT_ADDED_TO_COUNTER_DEPT_LIST = "departmentAddedToCounterDeptList";
	 
	// Unit Counter Mapping
	 String UNIT_NOT_ADDED_TO_COUNTER_UNIT_LIST = "unitNotAddedToCounterUnitList";
	 String UNIT_ADDED_TO_COUNTER_UNIT_LIST = "unittAddedToCounterUnitList";
	 

	String ESSENTIALBO_OPTION_DEPT_UNIT_DAYS= "deptUnitDays";
	
	String NO_DEPT_UNIT_WORKING_TRUE= "0";
	
	String DAILY_PATIENT_VO_ARRAY="dailyPatientVOArray";
	String FILE_PRINT_FLAG_YES="1";
	String FILE_PRINT_FLAG_NO="0";
	String FILE_NO_PRINT="fileNoPrint";
	
	String FILE_NUMBER_PRINT="5";
	String FILE_NUMBER_PRINT_DUPLICATE="6";
	String MODE_FILE_NUMBER_PRINT="1";
	String MODE_SLIP_PRINT="2";
	
	String FILE_NO_REQUIRED_FALSE="0";
	String FILE_NO_REQUIRED_DEPT_WISE="1";
	String FILE_NO_REQUIRED_UNIT_WISE="2";
	
	String DEPT_WHOSE_FILE_REQ_LIST="deptWhoseFileReqList";
	String UNIT_WHOSE_FILE_REQ_LIST="unitWhoseFileReqList";
	
	//Card and File Printing Configuration Setting
	String PRINT_TYPE_CARD="1";
	String PRINT_TYPE_LABEL="2";
	String PRINT_TYPE_FILE="1";
	String PRINT_ON_SAME_SYSTEM="1";
	String PRINT_ON_OTHER_SYSTEM="0";
	String FILE_PRINT_NOT_REQUIRED="000";
	String PRINTER_NAME_FOR_CARD_OR_LABEL="HISPRINTER";
	String PRINTER_NAME_FOR_FILE="HISFILEPRINTER";
	
	///Renewal not required department
	String RENEWAL_NOT_REQUIRED_DEPT_CODE="128,158";
	
	///Renewal not required department unit
	String RENEWAL_NOT_REQUIRED_DEPT_UNIT_CODE="13819,13412";
	
	////for specific printing for radiotherapy department
	String RADIOTHERAPY_DEPT_CODE="128";
	String EXTERNAL_INSTITUTE_PGI="102";
	String SESSION_DEPARTMENT_CODE="sessionDepartmentCode";
	String REFER_BY="referredBy";
	
	String REGISTRATION_SLIP_OBJECT="registrationSlipObject";
	
	String DEPT_UNIT_FOR_FREE_REG="13819,13412,14622";

	String GENDER_SPECIFIC_DEPT_CODE="118,157";	
	
	String DEPT_FOR_FREE_REG="158";
	
	//////Process Type For Registration
	String NEW_REGISTRATION_PROCESS="1";
	String ROOM_WISE_REGISTRATION_PROCESS="2";
	String EMERGENCY_REGISTRATION_PROCESS="3";
	String SPECIAL_REGISTRATION_PROCESS="4";
	
	String HOSPITAL_COMBO_LIST="hospitalComboList";
	String HOSPITAL_COMBO_SEATID_WISE_LIST="hospitalComboSeatIDWise";
	// User Wise Cash Collection Report
	String RPT_DATA_USER_WISE_CASH_COLLECTION = "rptDataUserWiseCashCollectionData";
    String ESSENTIALBO_OPTION_PRIMARY_CATEGORY_SHORT_NAMES = "lstPrimaryCategoryShortNames";
	String CATEGORY_WISE_USER_WISE_REGISTRATION_REPORT="CategoryWiseUserWiseRegistration.jrxml";
    
    
	String CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO = "casualtyDeskLstPatDtlVO";
	String CASUALTYDESK_OPTION_EXAMINATION_TYPE="casualtyOptionExaminationType";
	String CASUALTYDESK_OPTION_POLICESTATION="casualtyOptionPoliceStation";
	String CASUALTY_DESK_LIST_OF_POLICE_EXAM_REQT_DTL_VO = "casualtyDeskLstPoliceExamReqtDtlVO";
	
	//String LOCAL_LANGUAGE="localLanguage";
	String LOCAL_LANGUAGE="marathi";
	
    //********************** Reports **************************************************************
    	// Report Modes
    String DSS_REPORTS_MODES_OPD_STATS_CONSOLIDATED = "OPDSTATCONSOL";
    String DSS_REPORTS_MODES_OPD_STATS_HOSPITALWISE = "OPDSTATHOSWISE";
    String DSS_REPORTS_MODES_AGEWISE_REG_STATS_CONSOLIDATED = "AGEWISESTATCONSOL";
    String DSS_REPORTS_MODES_AGEWISE_REG_STATS_HOSPITALWISE = "AGEWISESTATHOSWISE";
    String DSS_REPORTS_MODES_EMG_CENSUS_STATS_CONSOLIDATED = "EMGCENSSTATCONSOL";
    String DSS_REPORTS_MODES_EMG_CENSUS_STATS_HOSPITALWISE = "EMGCENSSTATHOSWISE";
    
    	// Parameter Form Types
    String DSS_REPORTS_PARAMETER_FORM_TYPE_ALL_LISTS = "1";
    String DSS_REPORTS_PARAMETER_FORM_TYPE_EMG_STATS = "2";
    
    	// Duration Modes
    String DSS_REPORTS_DURATION_MODE_YEARLY = "1";
    String DSS_REPORTS_DURATION_MODE_MONTHLY = "2";
    String DSS_REPORTS_DURATION_MODE_DATEWISE = "3";
    
    	// Target DSS Table
    String DSS_REPORTS_TARGET_TABLE_REGISTRATION = "1";
    String DSS_REPORTS_TARGET_TABLE_EPISODE = "2";
    
    	// KEYS
    String DSS_REPORTS_MINIMUM_MONTH_YEAR = "DSSMinimumMonthYear";
    String DSS_REPORTS_LIST_YEARS = "DSSListYears";
    String DSS_REPORTS_LIST_MONTHS = "DSSListMonths";
    String DSS_REPORTS_DATA_MAP = "DSSReportsDataMap";
    String DSS_REPORTS_LIST_SELECTED_HOS = "DSSListSelectedHos";
    String DSS_REPORTS_LIST_SELECTED_DEPT = "DSSListSelectedDept";
    String DSS_REPORTS_LIST_SELECTED_PAT_CATEGORIES = "DSSListSelectedPatCategories";
    String DSS_REPORTS_LIST_AGE_GROUPS = "DSSListAgeGroups";
    String DSS_REPORTS_LIST_HOS_WISE_SORTED_DATES = "DSSListHosWiseSortedDates";

    //** End Reports ******************************************************************************

    //To Check the Renewal Fee And Amt Collection
	String PAT_CAT_PAID_FEE="5.00";
	String PAT_CAT_FREE_FEE="0";
	String PAT_CAT_FREE_FEES="0.00";
    

	
	//Vehicle list in new Emergency service
    String ESSENTIALBO_OPTION_VEHICLE= "optionVehicle"; 

    //Ambulance wise cases registered
    String AMBULANCE_WISE_CASES_REGISTERED ="AmbulanceWiseCasesBroughtReport.jrxml";
    
    //OPD Card Print Settings
    
    String OPD_CARD_PAID="PAID (AFS)";
    
    String OPD_CARD_HEADER_LINE_1="Ayurveda Central Research Institute";
    String OPD_CARD_HEADER_LINE_2="Central Council for Research in Ayurvedic Sciences, Deptt. Of AYUSH";
    String OPD_CARD_HEADER_LINE_3="Ministry of Health & Family Welfare Govt. of India";
    String OPD_CARD_HEADER_LINE_4="Road No 66, Punjabi Bagh(W),New Delhi-110026";
    String OPD_CARD_HEADER_LINE_4_PHONE_LABEL="Phone No:";
    String OPD_CARD_HEADER_LINE_4_PHONE_VALUE="011-25229128, 25221059";
    String OPD_CARD_HEADER_LINE_4_FAX_LABEL="Fax No:";
    String OPD_CARD_HEADER_LINE_4_FAX_VALUE="25225546";
    String OPD_CARD_HEADER_LINE_4_EMAIL_LABEL="Email ID:";
    String OPD_CARD_HEADER_LINE_4_EMAIL_VALUE="acri.delhi@gmail.com";
    
    
   String OPD_CARD_CRNO_HINDI_LABEL= "&#2325;&#2375;&#2344;&#2381;&#2342;&#2381;&#2352;&#2368;&#2351; &#2346;&#2306;&#2332;&#2368;&#2325;&#2352;&#2339; &#2360;&#2306;&#2326;&#2381;&#2351;&#2366;";
   String OPD_CARD_DATETIME_HINDI_LABEL="&#2342;&#2367;&#2344;&#2366;&#2306;&#2325; &#2324;&#2352; &#2360;&#2350;&#2351;";
   String OPD_CARD_PATIENT_NAME_HINDI_LABEL="&#2352;&#2379;&#2327;&#2368; &#2325;&#2366; &#2344;&#2366;&#2350;";
   String OPD_CARD_AGE_HINDI_LABEL="&#2313;&#2350;&#2381;&#2352;";
   String OPD_CARD_SEX_HINDI_LABEL="&#2354;&#2367;&#2306;&#2327;";
   String OPD_CARD_ADDRESS_HINDI_LABEL="&#2346;&#2340;&#2366;";
   String OPD_CARD_CONTACTNO_HINDI_LABEL="&#2347;&#2379;&#2344; &#2360;&#2306;&#2326;&#2381;&#2351;&#2366;";
   String OPD_CARD_DEPT_HINDI_LABEL="&#2357;&#2367;&#2349;&#2366;&#2327;&#47;&#2325;&#2350;&#2352;&#2366;&#2360;&#2306;";
   String OPD_CARD_CONSULTANT_HINDI_LABEL="&#2330;&#2367;&#2325;&#2367;&#2340;&#2381;&#2360;&#2325;";
   String OPD_CARD_DIAGNOSIS_HINDI_LABEL="&#2344;&#2367;&#2342;&#2366;&#2344;";
   String OPD_CARD_OCCUPATION_HINDI_LABEL="&#2357;&#2381;&#2351;&#2357;&#2360;&#2366;&#2351;";
   String OPD_CARD_AYURVEDA_HINDI_LABEL="&#2310;&#2351;&#2369;&#2352;&#2381;&#2357;&#2375;&#2342;";
   String OPD_CARD_KENDRIYA_HINDI_LABEL="&#2325;&#2375;&#2344;&#2381;&#2342;&#2381;&#2352;&#2368;&#2351;";
   String OPD_CARD_ANUSANDHAN_HINDI_LABEL="&#2309;&#2344;&#2369;&#2360;&#2306;&#2343;&#2366;&#2344;";
   String OPD_CARD_SANSTHAN_HINDI_LABEL="&#2360;&#2306;&#2360;&#2381;&#2341;&#2366;&#2344;";
   String OPD_CARD_AYURVEDIC_HINDI_LABEL="&#2310;&#2351;&#2369;&#2352;&#2381;&#2357;&#2375;&#2342;&#2367;&#2325;";
   String OPD_CARD_AYURVEDEEYA_HINDI_LABEL="&#2310;&#2351;&#2369;&#2352;&#2381;&#2357;&#2375;&#2342;&#2368;&#2351;";
   String OPD_CARD_PARISHAD_HINDI_LABEL="&#2346;&#2352;&#2367;&#2359;&#2342;";
   String OPD_CARD_AYUSH_HINDI_LABEL="&#2310;&#2351;&#2369;&#2359;";
   String OPD_CARD_SWASTHYA_HINDI_LABEL="&#2360;&#2381;&#2357;&#2366;&#2360;&#2381;&#2341;&#2351;";
   String OPD_CARD_AND_HINDI_LABEL="&#2324;&#2352;";
   String OPD_CARD_EVM_HINDI_LABEL="&#2319;&#2357;&#2306;";
   String OPD_CARD_PARIVAR_HINDI_LABEL="&#2346;&#2352;&#2367;&#2357;&#2366;&#2352;";
   String OPD_CARD_KALYAN_HINDI_LABEL="&#2325;&#2354;&#2381;&#2351;&#2366;&#2339;";
   String OPD_CARD_MANTRALAYA_HINDI_LABEL="&#2350;&#2306;&#2340;&#2381;&#2352;&#2366;&#2354;&#2351;";
   String OPD_CARD_VIGYAN_HINDI_LABEL="&#2357;&#2367;&#2332;&#2381;&#2334;&#2366;&#2344;";
   String OPD_CARD_GOVTOFINDIA_HINDI_LABEL="&#2349;&#2366;&#2352;&#2340; &#2360;&#2352;&#2325;&#2366;&#2352;";
   String OPD_CARD_ROADNO_HINDI_LABEL="&#2350;&#2366;&#2352;&#2381;&#2327; &#2360;&#2306;&#2326;&#2381;&#2351;&#2366;";
   String OPD_CARD_PUNJABI_BAG_HINDI_ADDRESS="&#2350;&#2366;&#2352;&#2381;&#2327; &#2360;&#2306;&#2326;&#2381;&#2351;&#2366; &#54;&#54; &#44; &#2346;&#2306;&#2332;&#2366;&#2348;&#2368; &#2348;&#2366;&#2327; &#40;&#2346;&#41; &#44;&#2344;&#2312; &#2342;&#2367;&#2354;&#2381;&#2354;&#2368; &#49;&#49;&#48;&#44;&#48;&#50;&#54;";
   
   String OPD_CARD_NAME_MARATHI_LABEL="&#2344;&#2366;&#2357;";
   String OPD_CARD_CASTE_MARATHI_LABEL="&#2332;&#2366;&#2340;";
   String OPD_CARD_CATEGORY_MARATHI_LABEL="&#2357;&#2352;&#2381;&#2327";
   String OPD_CARD_AGE_MARATHI_LABEL="&#2357;&#2351;&#40; &#2357;&#2352;&#2381;&#2359;&#2375; &#2325;&#2367;&#2306;&#2357;&#2366; &#2350;&#2361;&#2367;&#2344;&#2375; &#41;";
   String OPD_CARD_HOWLONGILL_MARATHI_LABEL="&#2325;&#2367;&#2340;&#2368; &#2325;&#2366;&#2355; &#2310;&#2332;&#2366;&#2352;&#2368; &#2310;&#2361;&#2375; &#40; &#2350;&#2361;&#2367;&#2344;&#2375; &#2325;&#2367;&#2306;&#2357;&#2366; &#2357;&#2352;&#2381;&#2359;&#2375;&#41;";
   String OPD_CARD_OCCUPATION_MARATHI_LABEL="&#2357;&#2381;&#2351;&#2357;&#2360;&#2366;&#2351;";
   String OPD_CARD_RESIDENCE_MARATHI_LABEL="&#2328;&#2352;&#2330;&#2366; &#2346;&#2340;&#2366;";
   String OPD_CARD_MONTHLYINC_MARATHI_LABEL="&#2350;&#2366;&#2360;&#2367;&#2325; &#2313;&#2340;&#2381;&#2346;&#2334;";
   String OPD_CARD_DISEASE_MARATHI_LABEL="&#2352;&#2379;&#2327;&#2366;&#2330;&#2375; &#2344;&#2366;&#2357;";
   String OPD_CARD_RESULT_MARATHI_LABEL="&#2344;&#2367;&#2359;&#2381;&#2325;&#2359;&#2375;";
   String OPD_CARD_DATE_MARATHI_LABEL="&#2342;&#2367;&#2344;&#2366;&#2306;&#2325;";
   String OPD_CARD_PRESCRIPTIONS_MARATHI_LABEL="&#2324;&#2359;&#2343; &#2351;&#2379;&#2330;&#2344;&#2366;";
   String OPD_CARD_HOWMANYDAYSILL_MARATHI_LABEL="&#2325;&#2367;&#2340;&#2368; &#2342;&#2367;&#2357;&#2360;&#2366;&#2306;&#2360;&#2366;&#2336;&#2368;";
   String OPD_CARD_AADHAAR_MARATHI_LABEL="&#2310;&#2343;&#2366;&#2352;";
   String OPD_CARD_ROOM_MARATHI_LABEL="&#2357;&#2367;&#2349;&#2366;&#2327;&#47;&#2326;&#2379;&#2354;&#2368";
   String OPD_CARD_SYMPTOMSPROGRESS_MARATHI_LABEL="&#2352;&#2379;&#2327;&#2354;&#2325;&#2381;&#2359;&#2339;&#2375; &#2357; &#2352;&#2369;&#2327;&#2381;&#2339;&#2366;&#2330;&#2368; &#2346;&#2381;&#2352;&#2327;&#2340;&#2368";
   String OPD_CARD_NO_MARATHI_LABEL="&#2325;&#2381;&#2352;&#46";
   String OPD_CARD_FEE_MARATHI_LABEL="&#2358;&#2369;&#2354;&#2381;&#2325";
   
   String PATIENT_VO_LIST="lstPatientVo";
   
   String HOSP_ADDRESS = "Raigarh,Alibagh,Maharashtra";
   String HOSP_NAME = "Civil Hospital";
   String PHDM_HEADER1="CIVIL HOSPITAL,ALIBARGH,MAHARASTRA-110026";
   String PHDM_HEADER2="Directorate of Health Services";
   String PHDM_HEADER3="(Public Health Department, Government of Maharashtra)";
   String PHDM_HEADER4="Aarogya Bhavan, St. Georges Hospital Compund, ,Mumbai-400001";
   String PHDM_HEADER5="Tel No.022-22651026/22631831,Fax No.022-22625799";
}//end
