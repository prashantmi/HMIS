/** Module Name: PIS
*  Name of Process: PIS Global Configuration 
*  Name of Developer: Sh. Ashwini Mishra 
*  Name of TL: Sh. Ashwini Mishra 
*  Date of Creation: 08-07-2014
*  Last Modifier: Ashwini Mishra 
*  Last Modification Date: 21-11-2014
*  
*/

package hr.pis.config;
/**
 * PisDaoConfig is an interface that defines hard-coded values that are used for development of DAO.
 * @author AHIS
 */
public interface PisDaoConfig extends PisConfig{
	
	
	
	
		//Global
		String PROCEDURE_GET_USER_COMBO="pkg_pis_master_view.procusername";
		String PROCEDURE_HOSPITAL_COMBO="pkg_pis_master_view.prochospitalcombo";
	
	
		String PROCEDURE_GET_APPELLATION_COMBO="pkg_pis_master_view.procappellationcombo";
		String PROCEDURE_GET_SUFFIX_COMBO="pkg_pis_master_view.procsuffixcombo";
		String PROCEDURE_GET_EVENT_COMBO="pkg_pis_master_view.proceventcombo";		
		String PROCEDURE_GET_LAST_EMPLOYEMENT_TYPE_COMBO="pkg_pis_master_view.proclastemploymenttypecombo";		
		String PROCEDURE_GET_GENDER_COMBO="pkg_pis_master_view.procgendercombo";
		String PROCEDURE_GET_MARITAL_STATUS_COMBO="pkg_pis_master_view.procmaritalstatuscombo";
		String PROCEDURE_GET_BLOOD_GRP_COMBO="pkg_pis_master_view.procbloodgrpcombo";
		String PROCEDURE_GET_RELIGION_COMBO="pkg_pis_master_view.procreligioncombo";
		String PROCEDURE_GET_RELATION_COMBO="pkg_pis_master_view.procrelationcombo";		
		String PROCEDURE_GET_PATIENT_CATEGORY_COMBO="pkg_pis_master_view.procpatientcategorycombo";	
		String PROCEDURE_GET_OCCUPATION_COMBO="pkg_pis_master_view.prococcupationcombo";		
		String PROCEDURE_GET_CASTE_COMBO="pkg_pis_master_view.proccastecombo";
		String PROCEDURE_GET_HANDICAP_TYPE_COMBO="pkg_pis_master_view.prochandicaptypecombo";
		String PROCEDURE_GET_NATIONALITY_COMBO="pkg_pis_master_view.procnationalitycombo";
		String PROCEDURE_GET_SALARY_TYPE_COMBO="pkg_pis_master_view.procsalarytypecombo";
		String PROCEDURE_GET_CAT_REV_COMBO="pkg_pis_master_view.proccatrevcombo";
		String PROCEDURE_GET_PAY_SCALE_COMBO="pkg_pis_master_view.procpscombo";
		String PROCEDURE_GET_NATURE_OF_JOB_COMBO="pkg_pis_master_view.procnatureofjob";
		String PROCEDURE_GET_RECRUITMENT_SRC_COMBO="pkg_pis_master_view.procrecruitmentsrc";		
		String PROCEDURE_GET_EMP_OFFICE_COMBO="pkg_pis_master_view.procconfigcombo";
		String PROCEDURE_GET_SER_GRP_COMBO="pkg_pis_master_view.procsergrpcombo";
		String PROCEDURE_GET_STATUS_COMBO="pkg_pis_master_view.procconfigcombo";
		String PROCEDURE_GET_FINAL_STATUS_COMBO="pkg_pis_master_view.procconfigcombo";
		String PROCEDURE_GET_EMP_CLASS_COMBO="pkg_pis_master_view.procempclasscombo";		
		String PROCEDURE_GET_ESTB_SEC_COMBO="pkg_pis_master_view.procestbseccombo";		
		String PROCEDURE_GET_DESIG_COMBO="pkg_pis_master_view.procdesigcombo";
		String PROCEDURE_GET_DEPT_TYPE_COMBO="pkg_pis_master_view.procdepttypecombo";
		String PROCEDURE_GET_DEPT_COMBO="pkg_pis_master_view.procdeptcombo";
		String PROCEDURE_GET_CADRE_COMBO="pkg_pis_master_view.proccadrecombo";
		String PROCEDURE_GET_PLACE_OF_POSTING_COMBO="pkg_pis_master_view.procplaceofpostingcombo";	
		
		String PROCEDURE_GET_LANGUAGE_COMBO="pkg_pis_master_view.proclanguagecombo";
		String PROCEDURE_GET_OFFICIAL_NUM_COMBO="pkg_pis_master_view.procofficialnumcombo";
		String PROCEDURE_BANK_COMBO="pkg_pis_master_view.procbankcombo";
		String PROCEDURE_BRANCH_COMBO="pkg_pis_master_view.procbranchcombo";
		String PROCEDURE_GET_IDENTIFICATION_UNIT_COMBO="pkg_pis_master_view.procidentificationunitcombo";
		
		String PROCEDURE_GET_EMPLOYER_TYPE_COMBO = "pkg_pis_master_view.procemployertypecombo";
		String PROCEDURE_GET_TRANSFER_UNIT_COMBO = "pkg_pis_master_view.proctransferunitcombo";
		
		
		String PROCEDURE_GET_MONTH_COMBO="pkg_pis_master_view.procmonthcombo";
		String PROCEDURE_GET_MODULE_COMBO="pkg_pis_master_view.procmodulecombo";
		
		String PROCEDURE_GET_ACTION_COMBO="pkg_pis_pc_mst_view.procactioncombo";
		String PROCEDURE_GET_PROCESS_TYPE_COMBO="pkg_pis_pc_mst_view.procprocesstypecombo";		
		String PROCEDURE_GET_PROCESS_COMBO="pkg_pis_pc_mst_view.procprocesscombo";
		String PROCEDURE_GET_PROC_FIELD_COMBO="pkg_pis_pc_mst_view.procfieldcombo";
		String PROCEDURE_GET_PROC_FLD_VAL_COMBO="pkg_pis_pc_mst_view.procprocfldvalcombo";
		
		String PROCEDURE_GET_STATUS_CHANGE_TYPE_COMBO="pkg_pis_master_view.procstatuschangetypecombo";
		
		String PROCEDURE_GET_UNIVERSITY_BOARD_COMBO="pkg_pis_master_view.universityboardcombo";
		
		String PROCEDURE_GET_COURSE_TYPE_COMBO="pkg_pis_master_view.coursetypecombo";
		String PROCEDURE_GET_COURSE_LEVEL_COMBO="pkg_pis_master_view.courselevelcombo";
		String PROCEDURE_GET_COURSE_COMBO="pkg_pis_master_view.coursecombo";
		
		String PROCEDURE_GET_PENALTY_TYPE_COMBO="pkg_pis_master_view.penaltytypecombo";
		String PROCEDURE_GET_PENALTY_COMBO="pkg_pis_master_view.penaltycombo";
		String PROCEDURE_GET_INVOLVE_NATURE_COMBO="pkg_pis_master_view.involvenatcombo";
	
		String PROCEDURE_GET_TRAVEL_MODE_COMBO="pkg_pis_pc_mst_view.proctravelmodecombo";
		String PROCEDURE_GET_TRANS_FORM_COMBO="pkg_pis_pc_mst_view.proctransformcombo";
		
		String PROCEDURE_GET_RESEARCH_PAPER_VENUE_COMBO="pkg_pis_master_view.procempresearchpaperpubvenuecombo";
		String PROCEDURE_GET_RESEARCH_PAPER_VENUE_CLASSIFICATION_COMBO="pkg_pis_master_view.procempresearchpapervenueclassificationcombo";

		
		
		
		
		//Leave
		String PROCEDURE_GET_LEAVE_COMBO = "pkg_pis_leave_master_view.procleavecombo";
		String PROCEDURE_GET_LEAVE_TYPE_COMBO = "pkg_pis_leave_master_view.procleavetypecombo";
		String PROCEDURE_GET_LEAVE_AVAIL_TYPE_COMBO = "pkg_pis_leave_master_view.procleaveavailtypecombo";
		String PROCEDURE_GET_PERIODCITY_COMBO="pkg_pis_leave_master_view.procleaveperiodicitycombo";
				
				
		//Transfer
		String PROCEDURE_GET_TRANSFER_MODE_COMBO="pkg_pis_transfer_master_view.proctransfermode";
		String PROCEDURE_GET_TRANSFER_TYPE_COMBO="pkg_pis_transfer_master_view.proctransfertype";
		String PROCEDURE_GET_TRANSFER_CRITERIA_COMBO="pkg_pis_transfer_master_view.proctransfercriteria";
		String PROCEDURE_GET_TRANSFER_REASON_COMBO = "pkg_pis_transfer_master_view.proctransferreason";
		
		String PROCEDURE_GET_TRANSFER_REQ_COMBO="pkg_pis_transfer_master_view.procrequestcombo";
		
		//Promotion
		String PROCEDURE_GET_PROMOTION_MODE_COMBO="pkg_pis_promotion_master_view.procprommodecombo";
		String PROCEDURE_GET_PROMOTION_TYPE_COMBO="pkg_pis_promotion_master_view.procpromtypecombo";
		String PROCEDURE_GET_PROMOTION_DUE_COMBO="pkg_pis_promotion_master_view.procpromduecombo";
		String PROCEDURE_GET_PROMOTION_SCR_COMBO="pkg_pis_promotion_master_view.procpromscrcombo";
		
		//Suspension
		String PROCEDURE_GET_SUSPENSION_MODE_COMBO="pkg_pis_master_view.procsuspmodecombo";
		
		//Increment
		String PROCEDURE_GET_INC_WH_REASON_COMBO="pkg_pis_increment_master_view.procincwhreasoncombo";
		String PROCEDURE_GET_INC_TYPE_COMBO="pkg_pis_increment_master_view.procinctypecombo";
		
		
		
		String PROCEDURE_PS_CATEGORY_COMBO = "pkg_pis_master_view.proc_get_pscat_combo";
		String PROCEDURE_PS_REVISION_COMBO = "pkg_pis_master_view.procpayscrevisioncombo";
		String PROCEDURE_PS_CATEGORY_TYPE_COMBO = "pkg_pis_master_view.proc_get_pscat_type_combo";
		
		String OPTIONS_PRD_EVENT_LIST="ahistrn.pkg_pis_view.optionPRDEventList";
	
		
		//Process Field Value
		String PROCEDURE_PROC_FIELD_COMBO = "pkg_pis_pc_mst_view.proc_get_procfld_combo";
		String PROCEDURE_PROC_FIELD_VAL_COMBO = "pkg_pis_pc_mst_view.proc_get_procfldval_combo";
		
		//Measuring Unit Master
		String PROCEDURE_GET_MEASURING_UNIT_TYPE_COMBO="pkg_pis_master_view.measuringunittypecombo";
		
		
		//Immovable Property Declaration
		String PROCEDURE_GET_CHANGE_COMBO="pkg_pis_pd_immov_prop_declaration_view.changecombo";
		String PROCEDURE_GET_ACT_ON_PROP_COMBO="pkg_pis_pd_immov_prop_declaration_view.actiononpropcombo";
		String PROCEDURE_GET_IMMOV_PROP_TYPE_COMBO="pkg_pis_pd_immov_prop_declaration_view.immovproptypecombo";
		String PROCEDURE_GET_PROP_AREA_UNIT_COMBO="pkg_pis_pd_immov_prop_declaration_view.propareaunitcombo";
		String PROCEDURE_GET_LAND_NATURE_COMBO="pkg_pis_pd_immov_prop_declaration_view.landnaturecombo";
		String PROCEDURE_GET_PROP_ACQUIRED_TYPE_COMBO="pkg_pis_pd_immov_prop_declaration_view.propacquiredtypecombo";
		String PROCEDURE_GET_CURRENCY_UNIT_COMBO="pkg_pis_pd_immov_prop_declaration_view.currencyunitcombo";
		String PROCEDURE_GET_COUNTRY_COMBO="pkg_pis_pd_immov_prop_declaration_view.countrycombo";
		String PROCEDURE_GET_STATE_COMBO="pkg_pis_pd_immov_prop_declaration_view.statecombo";
		String PROCEDURE_GET_DISTRICT_COMBO="pkg_pis_pd_immov_prop_declaration_view.districtcombo";
		
		String PROCEDURE_GET_LOCATION_COMBO="pkg_pis_master_view.proclocationcombo";
		
		

		//Disciplinary
		String PROCEDURE_GET_COMPLAIN_TYPE_COMBO="pkg_pis_dp_master_view.proccomplainttypecombo";
		String PROCEDURE_GET_COMPLAIN_CATEGORY_COMBO="pkg_pis_dp_master_view.proccomplaintcategorycombo";
		String PROCEDURE_GET_COMPLAINT_SOURCE_COMBO="pkg_pis_dp_master_view.proccomplaintsourcecombo";
		String PROCEDURE_GET_INVESTIGATING_OFFICER_TYPE_COMBO="pkg_pis_dp_master_view.procinvestigatingofficertypecombo";
		String PROCEDURE_GET_ACTIVITY_TYPE_COMBO="pkg_pis_dp_master_view.procactivitytypecombo";
		String PROCEDURE_GET_PROCEEDING_TYPE_COMBO="pkg_pis_dp_master_view.proproceedingtypecombo";

		//ACR Track Detail
		String PROCEDURE_GET_ACR_LOCATION_COMBO="pkg_pis_acr_view.acrlocationcombo";
		String PROCEDURE_GET_ACR_COMP_STATUS_COMBO="pkg_pis_acr_view.acrcompstatuscombo";
		String PROCEDURE_GET_ACR_PER_GRADE_COMBO="pkg_pis_acr_view.acrpergradecombo";

		//Demotion Detail
		String PROCEDURE_GET_DEMOTION_MODE_COMBO="pkg_pis_master_view.procdemotionmodecombo";
		String PROCEDURE_GET_DEMOTION_STATUS_COMBO="pkg_pis_master_view.procdemotionstatuscombo";
		
		
		//Employee Certificate
		String PROCEDURE_GET_EMPLOYEE_CERTIFICATE_TYPE_COMBO="pkg_pis_sb_certificate_master_view.procemployeecertificatetypecombo";
		String PROCEDURE_GET_SB_CERTIFICATE_NAMES_COMBO="pkg_pis_sb_certificate_master_view.procecertficatenames";
		
		String PROCEDURE_GET_AWARD_TYPE="pkg_pis_master_view.procempawardtypecombo";
		String PROCEDURE_GET_AWARD_LEVEL="pkg_pis_master_view.procempawardlevelcombo";
		
		String PROCEDURE_GET_DOC_TYPE_COMBO="ahistrn.pkg_pis_emp_personnel_dtl.procdoctypecombo";
		
		//Start: Emp. Registration
		String PROCEDURE_GET_EMP_DETAIL_BY_POPUP = "{call pkg_pis_emp_registration_dtl.proc_search_emp_dtl(?,?,?,?,?,?)}";
		String PROCEDURE_GET_PENDING_EMPLOYEE_REGISTRATION_LIST = "pkg_pis_emp_registration_dtl.proc_get_pending_employee_list";
		String PROCEDURE_GET_VALIDATED_EMPLOYEE_REGISTRATION_LIST = "pkg_pis_emp_registration_dtl.proc_get_validated_employee_list";
		String PROCEDURE_GET_REJECTED_EMPLOYEE_REGISTRATION_LIST = "pkg_pis_emp_registration_dtl.proc_get_rejected_employee_list";
		String PROCEDURE_GET_EMP_DETAIL_FOR_VALIDATION = "{call pkg_pis_emp_registration_dtl.proc_search_emp_dtl_for_validation(?,?,?,?,?,?,?)}";	
		String PROCEDURE_VALIDATE_GUEST_LOGIN = "{call pkg_pis_emp_registration_dtl.proc_validate_guest_login(?,?,?,?,?,?)}";
		//End: Emp. Registration	
				
						
		
}
