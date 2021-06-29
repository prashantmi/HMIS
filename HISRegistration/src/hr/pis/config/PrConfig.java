package hr.pis.config;

/**
 * RegistrationConfig is an interface that defines hard-coded values that are
 * used for development of BO and DAO.
 * 
 * @author AHIS
 */
public interface PrConfig {

	
	//Global
	String JOINING_ESSENTIAL_BO_OPTION_NATURE_OF_JOB = "optionNatureOfjob";
	String JOINING_ESSENTIAL_BO_OPTION_RECRUITMENT_SRC = "optionRecruitmentSrc";
	String JOINING_ESSENTIAL_BO_OPTION_EMP_OFFICE = "optionEmpOffice";
	String JOINING_ESSENTIAL_BO_OPTION_ESTB_SEC = "optionEstbSec";
	String JOINING_ESSENTIAL_BO_OPTION_SER_GRP = "optionSerGrp";
	String JOINING_ESSENTIAL_BO_OPTION_DESIG = "optionDesig";
	String JOINING_ESSENTIAL_BO_OPTION_DEPT = "optionDept";
	String JOINING_ESSENTIAL_BO_OPTION_CADRE = "optionCadre";
	String JOINING_ESSENTIAL_BO_OPTION_EMP_CLASS = "optionEmpClass";
	String JOINING_ESSENTIAL_BO_OPTION_STATUS = "optionStatus";
	String JOINING_ESSENTIAL_BO_OPTION_FINAL_STATUS = "optionFinalStatus";
	String JOINING_ESSENTIAL_BO_OPTION_USER = "optionUser";
	String JOINING_ESSENTIAL_BO_OPTION_LANGUAGE="optionLanguage";

	String JOINING_ESSENTIAL_BO_OPTION_OFFICIAL_NUM="optionOfficialNo";
	String JOINING_ESSENTIAL_BO_OPTION_BANK="optionBank";
	String JOINING_ESSENTIAL_BO_OPTION_BRANCH="optionBranch";
	
	String JOINING_ESSENTIAL_BO_OPTION_IDENTIFICATION_UNIT="optionIdentificationUnit";
	String JOINING_ESSENTIAL_BO_OPTION_EMPLOYER_TYPE = "optionEmployerTypeList";
	String JOINING_ESSENTIAL_BO_OPTION_TRANSFER_UNIT ="optionTransferUnitList";
	String JOINING_ESSENTIAL_BO_OPTION_PLACE_OF_POSTING = "optionPlaceOfPosting";
	
	String JOINING_ESSENTIAL_BO_OPTION_RP_PUB_VENUE ="optionResearchPaperPublicationVanue";
	String JOINING_ESSENTIAL_BO_OPTION_RP_VENUE_CLASSIFICATION = "optionResearchPaperVenueClassification";
	
	
	
		 
	// Employee Posting Detail

	// Emp Posting Dtl List Box
	String POSTING_DTL_PRINT_SLIP = "EmpPostingDtl";

	// Employee Family Detail
	String FAMILY_ESSENTIAL_BO_OPTION_RELATION = "optionRelationList";
	String FAMILY_ESSENTIAL_BO_OPTION_MEDICAL_CATEGORY="optionPatientList";
	String FAMILY_ESSENTIAL_BO_OPTION_OCCUPATION = "optionOccupationList";
	String FAMILY_ESSENTIAL_BO_OPTION_HANDICAP_TYPE = "optionHandiTypeList";

	// EmployeeContactDetails
	String JOINING_ESSENTIAL_BO_OPTION_COUNTRY_LIST="optionCountryList";
	String JOINING_ESSENTIAL_BO_OPTION_STATE_LIST = "optionStateList";
	String JOINING_ESSENTIAL_BO_OPTION_DISTRICT_LIST = "optionDistrictList";

	// Employee Personal Details List Box
	String ESSENTIAL_BO_OPTION_MARITAL_STATUS = "optionMaritalStatusList";
	String ESSENTIAL_BO_OPTION_MOTHER_TONGUE = "optionMotherTongueList";
	String ESSENTIAL_BO_OPTION_BLOOD_GROUP = "optionBloodGroupList";
	String ESSENTIAL_BO_OPTION_CASTE_CATEGORY = "optionCasteCategoryList";
	String ESSENTIAL_BO_OPTION_RELIGION = "optionReligionList";
	String ESSENTIAL_BO_OPTION_HOSPITAL = "optionHospitalList";
	String ESSENTIAL_BO_OPTION_HANDICAP_TYPE = "optionHandicapTypeList";
	String ESSENTIAL_BO_OPTION_DOC_TYPE= "optionDocTypeList";
	// Defualt Parameters for Employee Registration
	String EMPLOYEEREGISTRATION_DEFAULT_NATIONALITY_ID = "1";
	String EMPLOYEEREGISTRATION_DEFAULT_EMPLOYEE_FINAL_STATUS_CODE = "24001";

	// hr desk
	String HR_DESK_EMP_NO = "hrdeskempno";
	String HR_DESK_EMP_NAME = "hrdeskempname";
	String HR_DESK_EMP_DESIGNATION = "hrdeskempdesignation";
	String HE_DESK_EMP_DEPARTMENT = "hrdeskempdepartment";

	// hr desk end

	/************************************************** Masters **************************************************/
	// PS Category Master
	String PS_CATEGORY_ESSENTIAL_BO_OPTION_CATEGORY_LIST = "optionCategoryList";
	String PS_CATEGORY_ESSENTIAL_BO_OPTION_REVISION_LIST = "optionRevisionList";
	String PS_CATEGORY_ESSENTIAL_BO_OPTION_TYPE_LIST = "optionTypeList";

	// PS Grade or PayBand Master
	String PS_GRADE_PB_ESSENTIAL_BO_OPTION_CATEGORY_LIST = "optionCatRevList";

	// Pay Scale Master
	String PS_ESSENTIAL_BO_OPTION_CATEGORY_LIST = "optionCatRevList";
	String PS_ESSENTIAL_BO_OPTION_GRADE_OR_PB_LIST = "optionGradePBList";

	// Employee Registration List Box
	String ESSENTIAL_BO_OPTION_DEPARTMENT = "optionDepartmentList";
	String ESSENTIAL_BO_OPTION_NATURE_OF_JOB = "optionNatureOfJobList";
	String ESSENTIAL_BO_OPTION_APPELLATION1 = "optionAppellation1List";
	String ESSENTIAL_BO_OPTION_APPELLATION2 = "optionAppellation2List";
	String ESSENTIAL_BO_OPTION_SUFFIX = "optionSuffixList";
	String ESSENTIAL_BO_OPTION_NATIONALITY = "optionNationalityList";
	String ESSENTIAL_BO_OPTION_GENDER = "optionGenderList";
	String ESSENTIAL_BO_OPTION_SERVICE_GROUP = "optionServiceGroupList";
	String ESSENTIAL_BO_OPTION_DESIGNATION = "optionDesignationList";
	String ESSENTIAL_BO_OPTION_STATUS = "optionStatusList";
	String ESSENTIAL_BO_OPTION_FINAL_STATUS = "optionFinalStatusList";
	String ESSENTIAL_BO_OPTION_LAST_EMPLOYMENT_TYPE = "optionLastEmploymentTypeList";

	// EmployeeQualificationDetails
	String QUALIFICATION_BO_OPTION_COURSE_LIST = "optionCourseList";
	String QUALIFICATION_BO_OPTION_SCORETYPE_LIST = "optionScoreTypeList";
	String QUALIFICATION_BO_OPTION_COLLEGE_LIST = "optionCollegeList";
	String QUALIFICATION_BO_OPTION_BOARD_LIST = "optionBoardList";
	
		
	// Employee Pay Details
	String PAY_BO_OPTION_SALARY_TYPE_LIST = "optionSalaryTypeList";
	String PAY_BO_OPTION_CATEGORY_REVISION_LIST = "optionCategoryRevisionList";
	String PAY_BO_OPTION_PAYSCALE_LIST = "optionPayScaleList";

	
	// Department
		public static final String JOINING_ESSENTIAL_BO_OPTION_DEPARTMENT_TYPE = "optionDeptTypeList";
//		String ESSENTIAL_BO_OPTION_LOCATION="optionLocationList";
		
		//TRANSFER
		String JOINING_ESSENTIAL_BO_OPTION_TRANSFER_MODE = "optionTransferModeList";

		String JOINING_ESSENTIAL_BO_OPTION_TRANSFER_TYPE = "optionTransferTypeList";

		String JOINING_ESSENTIAL_BO_OPTION_TRANSFER_CRITERIA = "optionTransferCriteriaList";
		
		String JOINING_ESSENTIAL_BO_OPTION_AWARD_TYPE ="optionAwardTypeList";
		String JOINING_ESSENTIAL_BO_OPTION_AWARD_LEVEL ="optionAwardLevelList";
		
		
		
	

}// end
