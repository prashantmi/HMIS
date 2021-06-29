package enquiry;

public interface enquiryConfig {
	
	//////Query file for dao///////
	
	
	 String QUERY_FILE_FOR_ENQUIRY_DAO="enquiry.enquiryQuery";
	
	  
	String PATIENT_ENQUIRY_COMMON_ENQUIRY_VO="patEnquiryCommonEnquiryVO";
	String PATIENT_ENQUIRY_IN_PATIENT_VO="patEnquiryInPatientVO";
	String IN_PATIENT_ENQUIRY_VO="inPatientEnquiryVO";
	String OPD_PATIENT_ENQUIRY_VO="EpisodeEnquiryVO";
	String DEPARTMENT_LOCATION_ENQUIRY_VO="departmentLocationEnquiryVo";
	String ENQUIRY_OPTION_DESIGNATION="designationOption";
	String ENQUIRY_BO_OPTION_ALLDEPT="enquiryDeparmentOption";
	String STAFF_ENQUIRY_STAFF_ENQUIRY_VO="staffEnquiryVo";
	String ENQUIRY_FREE_PATIENT_CAT_LIST="freePatCatList";
	
	
	String OPD_ENQUIERY_VO_ARRAY="opdEnquiryVOArray";

	
	String ALL_CONSULTANT_DETAILS_OPD_ENQUIRY_VO="allConsultantDetailsOpdEnquiryVO";
	
	String IS_HOU_TRUE="1";
	String IS_HOU_FALSE="0";

	/****Consultant Enquiry*******************/
	
	String HOSPITAL_CONSULTANT_VO_ARRAY="consultantVoArray";
	String HOSPITAL_CONSULTANT_VO_ARRAY_VIEW="consultantVoArrayView";
	String CONSULTANT_DETAIL_VO_ARRAY="consultantDetailVoArray";
	String UNIT_WORKING_DAYS="unitWorkingDays";
	String CONSULTANT_ENQUIRY_MAP="consultantEnquiryMap";
	String CONSULTANT_ENQUIRY_MAP_KEY="consultantEnquiryMapKey";
	/************************************************/
	
	String ALL_COMPONENT_BLOOD_STOCK_MAP="allComponentBloodStockMap";
	
	String IN_STOCK_BLOOD_COMPONENT_STOCK_ENQUIRY="1";
	String ALL_BLOOD_COMPONENT_STOCK_ENQUIRY="2";

	String ALL_BLOOD_GROUPS_LIST="allBloodGroupsList";
    String VOLUNTARY_BLOOD_DONORS="voluntaryBlooddONORS";
    String EMERGENCY_CALL_NO="0";
    String EMERGENCY_CALL_YES="1";
    
    String ALL_DEPARTMENT_WITH_DEPARTMENT_TYPE="listAllDepartmentWithType";
    String DEPARTMENT_ANDDEPARTMENT_TYPE_MAP="mapDepartmentAndDepartmentType";
    String DEPARTMENT_ANDDEPARTMENT_TYPE_MAP_VIEW="mapDepartmentAndDepartmentTypeView";
    String ARRAY_DEPARTMENT_ENQUIRY_VO="arrayDepartmentEnquiryVO";
    String ALL_DEPT_LIST="allDeptList";
    String GENERAL_UNIT_LIST="generalUnitList";
    String SPECIAL_UNIT_LIST="specialUnitList";
    
    

    String ARRAY_UNIT_DETAIL_DEPARTMENT_ENQUIRY_VO="arrayDepartmentUnitDetailEnquiryVO";
    String DEPARTMENT_UNIT_ENQUIRY_DETAIL_VO="departmentUnitEnquiryDetailVO";
    String DEPARTMENT_UNIT_CONSULTANT_ENQUIRY_DETAIL_VO="departmentUnitConsultantEnquiryVO";
    String DEPARTMENT_UNIT_ROOM_ENQUIRY_DETAIL_VO="departmentUnitRoomEnquiryVO";
    String DEPARTMENT_UNIT_WARD_ENQUIRY_DETAIL_VO="departmentUnitWardEnquiryVO";
    String DEPARTMENT_UNIT_WORKING_DAYS="departmentUnitWorkingDays";
    
    String DEPARTMENT_UNIT_WARD_LIST="wardList"; 
    String WARD_TYPE_MAP="wardTypeMap"; 
    String ENQUIRY_WARD_DETAIL_VO="wardDetailVO"; 
    String ENQUIRY_WARD_DETAIL_VO_VIEW="wardDetailVOView"; 


    String VO_OF_HOSPITAL_MST="hospitalMstVO";
    String VO_ARRAY_OF_REGISTRATION_TIMINGS="registartionTimingsVOArray";
    
    String[] registrationCategoryCode={"11","12","13"};
    String[] registrationDescriptionCode={"Normal","Special","Casuality"};
    
    String MAP_OF_HOSPITAL_CATEGORY="mapOfhospitalCategory";
    String MAP_OF_HOSPITAL_SEASONS="mapOfhospitalSeasons";
    String MAP_OF_HOSPITAL_SHIFTS="mapOfhospitalShifts";
    
    

    String ORDER_BY_NAME="1";
    String ORDER_BY_WARD_TYPE="2";
    String OPD_SCHEDULE_ENQUIRY_DEPT_VO="opdScheduleEnquirydepartmentVO";
    String OPD_SCHEDULE_ENQUIRY_SPECIAL_CLINIC_VO="opdScheduleEnquirySpecialClinicVO";
    String OPD_SCHEDULE_ENQUIRY_DEPT_VO_VIEW="opdScheduleEnquirydepartmentVOView";
    String OPD_SCHEDULE_ENQUIRY_SPECIAL_CLINIC_VO_VIEW="opdScheduleEnquirySpecialClinicVOView";
    String OPD_SCHEDULE_ENQUIRY_DETAIL_VO="opdScheduleEnquiryDetailVO";
    String OPD_SCHEDULE_ENQUIRY_UNIT_WORKING_DETAIL="opdScheduleEnquiryUnitDetail";
    String UNIT_ROOM_LIST="unitRoomList";
    String IS_DEPT_UNIT_ON_DUTY_YES="1";
    String IS_DEPT_UNIT_ON_DUTY_NO="0";
    String OPD_SCHEDULE_ENQUIRY_DEPT_LIST="deptList";
    
    String TELEPHONE_ENQUIRY_DEPT_TELEPHONE_DETAIL_VO="deptTelephoneVO";
    String TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO="empTelephoneVO";
    String TELEPHONE_ENQUIRY_TELEPHONE_DETAIL_VO="telephoneVOList";
    String TELEPHONE_ENQUIRY_DEPT_TELEPHONE_DETAIL_VO_VIEW="deptTelephoneVOView";
    String TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO_VIEW="empTelephoneVOView";
    String TELEPHONE_ENQUIRY_IS_IMPORTANT_YES="1";
    
    String OPERATION_THEATER_ENQUIRY_VO="operationTheaterEnqVO";
    String OPERATION_TYPE_MAP="operationTypeMap";
    
    String HOSPITAL_FACILITY_MST_VO_LIST="hospitalFacilityMstVOList";
    String HOSPITAL_FACILITY_MST_VO="hospitalFacilityMstVO";
    

    // Hospital Charge Enquiry
    
    String HOSPITAL_CHARGE_ENQUIRY_VO_LIST="hospitalChargeEnquiryVOList";
    String HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW="hospitalChargeEnquiryVOListView";
    String HOSPITAL_CHARGE_ENQUIRY_GROUP_LIST="hospitalChargeEnquiryGroupList";
    String HOSPITAL_CHARGE_DETAIL_MAP="hospitalChargeDetailMap";
    String HOSPITAL_CHARGE_DETAIL_VO_LIST="hospitalChargeDetailVOList";
    String HOSPITAL_CHARGE_TYPE_LIST="hospitalChargeTypeList";
    String HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW1="hospitalChargeEnquiryVOListView1";
    String HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW2="hospitalChargeEnquiryVOListView2";
    int RECORD_PER_PAGE=20;
    
    String HOSPITAL_LABORATORY_LIST="hospitalLaboratoryList";
    String HOSPITAL_LAB_TEST_LIST="hospitalLabTestList";
    String HOSPITAL_LAB_TEST_LIST_VIEW="hospitalLabTestListView";
    String HOSPITAL_LABORATORY_MAP="hospitalLabMap";
    
    String OT_CONSULTANT_LIST_VIEW="otConsultantListView";
    String OT_CONSULTANT_LIST="otConsultantList";
    String OT_CONSULTANT_WORKING_DAYS="otConsultantWorkingDays";
    
    
    String HOLIDAY_LIST="holidayList";
    String HOLIDAY_YEAR_LIST="yearList";
    
    String GUEST_HOUSE_LIST="guestHouseList";
    String GUEST_HOUSE_DETAIL_LIST="guestHouseDetailList";
    
    String TEMPLATE_LIST_FOR_GUIDLINE="templateListForGuidline";
    String SELECTED_TEMPLATEID_LIST="selectedTemplateIdList";
    
    ///Mortuary Deceased Enquiry////
    String DECEASED_TYPE_ALL="1";
    String DECEASED_TYPE_NORMAL="2";
    String DECEASED_TYPE_UNKNOWN="3";
    String DECEASED_TYPE_UNCLAIMED="4";
    
    String ENQUIRY_DECEASED_DETAIL_VO="enquiryDeceasedDetailVO";
    String ENQUIRY_FILTER_DECEASED_DETAIL_VO="enquiryFilterDeceasedDetailVO";
    
    String DECEASED_GENERAL_APPEARANCE_VO="deceasedGeneralAppearanceVO";
    String DECEASED_DEFAULT_IMAGE_VO="deceasedDefaultImageVO";
    String DECEASED_POSTMORTEM_STATUS="deceasedPostmortemStatus";
    String DECEASED_STORAGE_DETAIL="deceasedStorageDetail";
    String DECEASED_HANDOVER_DETAIL="deceasedHandoverDetail";
    
}
