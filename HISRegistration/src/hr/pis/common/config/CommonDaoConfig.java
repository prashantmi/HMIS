/**
 * Created By 	: Ashwini Mishra
 * Date			: Mar 2015
 */

package hr.pis.common.config;

/**
 * TransferConfig is an interface that defines hard-coded values that are used for
 * development of DAO.
 * 
 * @author AHIS
 */
public interface CommonDaoConfig extends CommonConfig {

	
	
	// Start: Order Detail
		
		String PROCEDURE_AUTH_ORDER_LIST = "ahistrn.pkg_pis_auth_order_dtl.proc_get_order_list";			
		String PROCEDURE_AUTH_ORDER_DTL = "{call ahistrn.pkg_pis_auth_order_dtl.proc_get_order_dtl(?,?,?,?,?,?,?,?,?,?)}";
		String PROCEDURE_ORDER_DEFAULT_VAL_DTL = "{call ahistrn.pkg_pis_auth_order_dtl.proc_get_default_value_for_order_to_and_copy_to(?,?,?,?,?,?,?)}";
		
		String PROCEDURE_GET_EXISTING_ORDER_LIST = "ahistrn.pkg_pis_auth_order_dtl.proc_get_existing_order_list";
		String PROCEDURE_GET_EXISTING_ORDER_DTL_BY_GO = "{call ahistrn.pkg_pis_auth_order_dtl.proc_get_order_dtl_by_go(?,?,?,?,?,?,?,?,?,?)}";
		String PROCEDURE_FIND_EXISTING_ORDER = "ahistrn.pkg_pis_auth_order_dtl.proc_find_existing_order";
	// End: Order Detail
		
	String PROCEDURE_GET_EMP_LIST = "ahistrn.pkg_pis_common.proc_get_emp_list";
	String PROCEDURE_GET_EMPTY_EMP_LIST = "ahistrn.pkg_pis_common.proc_get_empty_emp_list";
	String PROCEDURE_GET_REG_EMP_LIST = "ahistrn.pkg_pis_common.proc_get_reg_emp_list";
	String PROCEDURE_GET_REG_EMP_LIST_WITH_RM = "ahistrn.pkg_pis_common.proc_get_reg_emp_list_with_rm";
	String PROCEDURE_GET_VALID_EMP_LIST = "ahistrn.pkg_pis_common.proc_get_valid_emp_list";
	String PROCEDURE_GET_VALID_EMP_LIST_WITH_RM = "ahistrn.pkg_pis_common.proc_get_valid_emp_list_with_rm";
	String PROCEDURE_GET_GO_EMP_DATA = "{call ahistrn.pkg_pis_common.proc_get_go_emp_data(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	String PROCEDURE_FIND_EMP = "ahistrn.pkg_pis_common.proc_find_emp";
	
	String PROCEDURE_GET_VALIDATOR_DETAILS = "{call ahistrn.pkg_pis_common.proc_get_validator_dtls(?,?,?,?,?,?,?)}";

	String PROCEDURE_IS_MARRIAGE_DATE_APPLICABLE_VALUE =" ahistrn.pkg_pis_common.procismarriagedateapplicable";
	
	String PROCEDURE_GET_HOLIDAYTYPE_SYS_DTL="{call ahism.pkg_pis_system.proc_get_holidaytype_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_HOLIDAYTYPE_SYS_LIST = "ahism.pkg_pis_system.proc_get_holidaytype_list";
	
	
	String PROCEDURE_GET_LEAVE_ENCASH_REASON_SYS_DTL="{call ahism.pkg_pis_system.proc_get_leave_encash_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_LEAVE_ENCASH_REASON_SYS_LIST = "ahism.pkg_pis_system.proc_get_leave_encash_list";
	
	
	String PROCEDURE_GET_LEAVE_PERIODICITY_REASON_SYS_DTL="{call ahism.pkg_pis_system.proc_get_leave_periodicity_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_LEAVE_PERIODICITY_REASON_SYS_LIST = "ahism.pkg_pis_system.proc_get_leave_periodicity_list";
	
	
	String PROCEDURE_GET_TRANSFER_CRITERIA_SYS_DTL="{call ahism.pkg_pis_system.proc_get_transfer_criteria_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_TRANSFER_CRITERIA_SYS_LIST = "ahism.pkg_pis_system.proc_get_transfer_criteria_list";
	
	String PROCEDURE_GET_TRANSFER_MODE_SYS_DTL="{call ahism.pkg_pis_system.proc_get_transfer_mode_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_TRANSFER_MODE_SYS_LIST = "ahism.pkg_pis_system.proc_get_transfer_mode_list";
	
	
	String PROCEDURE_GET_TRANSFER_TYPE_SYS_DTL="{call ahism.pkg_pis_system.proc_get_transfer_type_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_TRANSFER_TYPE_SYS_LIST = "ahism.pkg_pis_system.proc_get_transfer_type_list";
	
	
	String PROCEDURE_GET_CONFIGURATION_MST_DTL="{call ahism.pkg_pis_system.proc_get_configuration_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_CONFIGURATION_MST_LIST = "ahism.pkg_pis_system.proc_get_configuration_mst_list";
	
	/*Pay Scale Type*/
	String PROCEDURE_GET_PAYSCALE_TYPE_MST_DTL="{call ahism.pkg_pis_system.proc_get_payscale_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_PAYSCALE_TYPE_MST_LIST = "ahism.pkg_pis_system.proc_get_payscale_mst_list";
	
	/*Pay Scale Category*/
	String PROCEDURE_GET_PAYSCALE_CATEGORY_MST_DTL="{call ahism.pkg_pis_system.proc_get_payscalecategory_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_PAYSCALE_CATEGORY_MST_LIST = "ahism.pkg_pis_system.proc_get_payscalecategory_mst_list";
	
	/*Pay Scale Revision*/
	String PROCEDURE_GET_PAYSCALE_REVISION_MST_DTL="{call ahism.pkg_pis_system.proc_get_payscalerevision_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_PAYSCALE_REVISION_MST_LIST = "ahism.pkg_pis_system.proc_get_payscalerevision_mst_list";
	
	/*Service Group*/
	String PROCEDURE_GET_SERVICE_GROUP_MST_DTL="{call ahism.pkg_pis_system.proc_get_servicegroup_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_SERVICE_GROUP_MST_LIST = "ahism.pkg_pis_system.proc_get_servicegroup_mst_list";
	
	/*Emplyoee Office*/
	String PROCEDURE_GET_EMPLOYEE_CLASS_MST_DTL="{call ahism.pkg_pis_system.proc_get_employeeclass_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_EMPLOYEE_CLASS_MST_LIST = "ahism.pkg_pis_system.proc_get_employeeclass_mst_list";
	
	/*Dealing Office*/
	String PROCEDURE_GET_DEALING_OFFICE_MST_DTL="{call ahism.pkg_pis_system.proc_get_dealingoffice_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_DEALING_OFFICE_MST_LIST = "ahism.pkg_pis_system.proc_get_dealingoffice_mst_list";
	
	/*Status*/
	String PROCEDURE_GET_STATUS_MST_DTL="{call ahism.pkg_pis_system.proc_get_status_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_STATUS_MST_LIST = "ahism.pkg_pis_system.proc_get_status_mst_list";
	
	/*Final Status*/
	String PROCEDURE_GET_FINAL_STATUS_MST_DTL="{call ahism.pkg_pis_system.proc_get_finalstatus_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_FINAL_STATUS_MST_LIST = "ahism.pkg_pis_system.proc_get_finalstatus_mst_list";
	
	String PROCEDURE_GET_CONFIG_MST_DTL="{call ahism.pkg_pis_system.proc_get_config_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_CONFIG_MST_LIST = "ahism.pkg_pis_system.proc_get_config_mst_list";
	
	String PROCEDURE_GET_IDENTIFICATION_UNIT_SYS_DTL="{call ahism.pkg_pis_system.proc_get_identification_unit_sys_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_IDENTIFICATION_UNIT_SYS_LIST = "ahism.pkg_pis_system.proc_get_identification_unit_sys_list";
	
	String PROCEDURE_GET_ADDRESS_TYPE_MST_DTL="{call ahism.pkg_pis_system.proc_get_address_type_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_ADDRESS_TYPE_MST_LIST = "ahism.pkg_pis_system.proc_get_address_type_mst_list";
	
	String PROCEDURE_GET_TRANSFER_UNIT_MST_DTL="{call ahism.pkg_pis_system.proc_get_transfer_unit_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_TRANSFER_UNIT_MST_LIST = "ahism.pkg_pis_system.proc_get_transfer_unit_mst_list";
	
	/*Course Type Details*/	
	String PROCEDURE_GET_COURSE_TYPE_MST_DTL="{call ahism.pkg_pis_system.proc_get_course_type_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_COURSE_TYPE_MST_LIST = "ahism.pkg_pis_system.proc_get_course_type_mst_list";
	
/*Course Regulating Body Type Details*/	
	String PROCEDURE_GET_COURSE_REGULATING_BODY_TYPE_MST_DTL="{call ahism.pkg_pis_system.proc_get_course_regulating_body_type_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_COURSE_REGULATING_BODY_TYPE_MST_LIST = "ahism.pkg_pis_system.proc_get_course_regulating_body_type_mst_list";
	
	
/*Increament Withheld Reason Details*/	
	String PROCEDURE_GET_INCREAMENT_WITHHELD_REASON_SYSTEM_DTL="{call ahism.pkg_pis_system.proc_get_increament_withheld_reason_sys_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_INCREAMENT_WITHHELD_REASON_SYSTEM_LIST = "ahism.pkg_pis_system.proc_get_increament_withheld_reason_sys_list";	
	
/*Increament Type Details*/	
	String PROCEDURE_GET_INCREAMENT_TYPE_SYSTEM_DTL="{call ahism.pkg_pis_system.proc_get_increament_type_sys_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_INCREAMENT_TYPE_SYSTEM_LIST = "ahism.pkg_pis_system.proc_get_increament_type_sys_list";	
	
	/*Leave Avail Type Details*/	
	String PROCEDURE_GET_LEAVE_AVAIL_TYPE_SYSTEM_DTL="{call ahism.pkg_pis_system.proc_get_leaveavail_type_sys_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_LEAVE_AVAIL_TYPE_SYSTEM_LIST = "ahism.pkg_pis_system.proc_get_leaveavail_type_sys_list";
	
	/*Salary Type Details*/	
	String PROCEDURE_GET_SALARY_TYPE_SYSTEM_DTL="{call ahism.pkg_pis_system.proc_get_salary_type_sys_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_SALARY_TYPE_SYSTEM_LIST = "ahism.pkg_pis_system.proc_get_salary_type_sys_list";
	
	/*Appellation Details*/	
	String PROCEDURE_GET_APPELLATION_SYSTEM_DTL="{call ahism.pkg_pis_system.proc_get_appellation_sys_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_APPELLATION_SYSTEM_LIST = "ahism.pkg_pis_system.proc_get_appellation_sys_list";
	
	/*Blood Group Details*/	
	String PROCEDURE_GET_BLOOD_GROUP_SYSTEM_DTL="{call ahism.pkg_pis_system.proc_get_blood_group_sys_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_BLOOD_GROUP_SYSTEM_LIST = "ahism.pkg_pis_system.proc_get_blood_group_sys_list";
	
	/*City Details*/	
	String PROCEDURE_GET_CITY_SYSTEM_DTL="{call ahism.pkg_pis_system.proc_get_city_sys_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_CITY_SYSTEM_LIST = "ahism.pkg_pis_system.proc_get_city_sys_list";
	
	/*Month Details*/	
	String PROCEDURE_GET_MONTH_SYSTEM_DTL="{call ahism.pkg_pis_system.proc_get_month_sys_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_MONTH_SYSTEM_LIST = "ahism.pkg_pis_system.proc_get_month_sys_list";
	
	
	/*Leave Credit Period Details*/	
	String PROCEDURE_GET_LEAVE_CREDIT_PERIOD_SYSTEM_DTL="{call ahism.pkg_pis_system.proc_get_leave_credit_period_sys_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_LEAVE_CREDIT_PERIOD_SYSTEM_LIST = "ahism.pkg_pis_system.proc_get_leave_credit_period_sys_list";
	
	/*Increment Configuration Details*/	
	String PROCEDURE_GET_INC_CONFIG_SYSTEM_DTL="{call ahism.pkg_pis_system.proc_get_inc_config_sys_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_INC_CONFIG_SYSTEM_LIST = "ahism.pkg_pis_system.proc_get_inc_config_sys_list";
		

	//Transportation Mode Master
	String PROCEDURE_GET_TRANS_MODE_MST_DTL="{call ahism.pkg_pis_pc_mst.proc_get_transportation_mode_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_TRANS_MODE_MST_LIST = "ahism.pkg_pis_pc_mst.proc_get_transportation_mode_mst_list";
	//String PROCEDURE_GET_TRAVEL_MODE_MST_NAME_COUNT = "{? = call ahism.pkg_pis_pc_mst.fun_gettravelmodemstnamecount(?,?,?,?,?)}";

	//Transportation Type MASTER
	String PROCEDURE_GET_TRANS_TYPE_MST_DTL="{call ahism.pkg_pis_pc_mst.proc_get_transportation_type_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_TRANS_TYPE_MST_LIST = "ahism.pkg_pis_pc_mst.proc_get_transportation_type_mst_list";	
	//String PROCEDURE_GET_TRANS_FORM_MST_NAME_COUNT= "{? = call ahism.pkg_pis_pc_mst.fun_gettransformmstnamecount(?,?,?,?,?,?)}";
	
	//Transportation Class MASTER
		String PROCEDURE_GET_TRANS_CLASS_MST_DTL="{call ahism.pkg_pis_pc_mst.proc_get_transportation_class_mst_dtl(?,?,?,?,?,?)}";
		String PROCEDURE_GET_TRANS_CLASS_MST_LIST = "ahism.pkg_pis_pc_mst.proc_get_transportation_class_mst_list";
		
		//String PROCEDURE_GET_TRANS_FORM_CLASS_MST_NAME_COUNT= "{? = call ahism.pkg_pis_pc_mst.fun_gettransformclassmstnamecount(?,?,?,?,?,?)}";
		
	
	
	//Leavetype System Master
	
	String PROCEDURE_GET_LEAVETYPE_SYS_DTL="{call ahism.pkg_pis_system.proc_get_leavetype_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_LEAVETYPE_SYS_LIST = "ahism.pkg_pis_system.proc_get_leavetype_list";
	
	
	//Process Field Value
	String PROCEDURE_PROCESS_FIELD_VALUE_LIST = "ahism.pkg_pis_pc_mst.proc_get_proc_field_value_mst_list";
	String PROCEDURE_PROCESS_FIELD_VALUE_DTL="{call ahism.pkg_pis_pc_mst.proc_get_proc_field_value_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_PROCESS_FIELD_VALUE_MST_NAME_COUNT = "{? = call ahism.pkg_pis_pc_mst.fun_getprocessfieldValuemstnamecount(?,?,?,?,?)}";
	
	//Promotion Mode Master
	String PROCEDURE_PROMOTION_MODE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_proc_prom_mode_sys_mst_list";
	String PROCEDURE_PROMOTION_MODE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_proc_prom_mode_sys_mst_dtl(?,?,?,?,?,?)}";
	
	//Promotion Type Master
	String PROCEDURE_PROMOTION_TYPE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_proc_prom_type_sys_mst_list";
	String PROCEDURE_PROMOTION_TYPE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_proc_prom_type_sys_mst_dtl(?,?,?,?,?,?)}";
	
	//Measuring Unit Type Master
	String PROCEDURE_MEASURING_UNIT_TYPE_MST_LIST = "ahism.pkg_pis_pc_mst.proc_get_measuring_unit_type_mst_list";
	String PROCEDURE_MEASURING_UNIT_TYPE_MST_DTL="{call ahism.pkg_pis_pc_mst.proc_get_measuring_unit_type_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_MEASURING_UNIT_TYPE_MST_NAME_COUNT = "{? = call ahism.pkg_pis_pc_mst.fun_getunittypecount(?,?,?,?)}";
	
	//Measuring Unit Master
	String PROCEDURE_MEASURING_UNIT_MST_LIST = "ahism.pkg_pis_pc_mst.proc_get_measuring_unit_mst_list";
	String PROCEDURE_MEASURING_UNIT_MST_DTL="{call ahism.pkg_pis_pc_mst.proc_get_measuring_unit_mst_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_GET_MEASURING_UNIT_MST_NAME_COUNT = "{? = call ahism.pkg_pis_pc_mst.fun_getunitnamecount(?,?,?,?)}";
	
	//Immovable Property Type System Master
	String PROCEDURE_IMMOV_PROP_TYPE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_immov_prop_type_sys_mst_list";
	String PROCEDURE_IMMOV_PROP_TYPE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_immov_prop_type_sys_mst_dtl(?,?,?,?,?,?)}";
		
	//Land Nature System Master
	String PROCEDURE_LAND_NATURE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_land_nature_sys_mst_list";
	String PROCEDURE_LAND_NATURE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_land_nature_sys_mst_dtl(?,?,?,?,?,?)}";
		
	//Property Acquired Type System Master
	String PROCEDURE_PROP_ACQUIRED_TYPE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_prop_acquired_type_sys_mst_list";
	String PROCEDURE_PROP_ACQUIRED_TYPE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_prop_acquired_type_sys_mst_dtl(?,?,?,?,?,?)}";
		
	//Action On Property System Master
	String PROCEDURE_ACTION_ON_PROP_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_action_on_prop_sys_mst_list";
	String PROCEDURE_ACTION_ON_PROP_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_action_on_prop_sys_mst_dtl(?,?,?,?,?,?)}";
	
	//Promotion Scrutinize System Master
	String PROCEDURE_PROM_SCRUTINIZE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_prom_scrutinize_sys_mst_list";
	String PROCEDURE_PROM_SCRUTINIZE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_prom_scrutinize_sys_mst_dtl(?,?,?,?,?,?)}";
	
	//ACR Complete Status System Master
	String PROCEDURE_ACR_COMP_STATUS_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_acr_comp_status_sys_mst_list";
	String PROCEDURE_ACR_COMP_STATUS_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_acr_comp_status_sys_mst_dtl(?,?,?,?,?,?)}";
	
	//ACR Location System Master
	String PROCEDURE_ACR_LOCATION_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_acr_location_sys_mst_list";
	String PROCEDURE_ACR_LOCATION_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_acr_location_sys_mst_dtl(?,?,?,?,?,?)}";
	
	//ACR Per Grade System Master
	String PROCEDURE_ACR_PER_GRADE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_acr_per_grade_sys_mst_list";
	String PROCEDURE_ACR_PER_GRADE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_acr_per_grade_sys_mst_dtl(?,?,?,?,?,?)}";
		
	//Property Change System Master
	String PROCEDURE_PD_CHANGE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_pd_change_sys_mst_list";
	String PROCEDURE_PD_CHANGE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_pd_change_sys_mst_dtl(?,?,?,?,?,?)}";
	
	//Suspension Mode Master
	String PROCEDURE_SUSPENSION_MODE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_susp_mode_sys_mst_list";
	String PROCEDURE_SUSPENSION_MODE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_susp_mode_sys_mst_dtl(?,?,?,?,?,?)}";
	
	//DP Complaint Type System Master
	String PROCEDURE_DP_COMP_TYPE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_dp_comp_type_sys_mst_list";
	String PROCEDURE_DP_COMP_TYPE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_dp_comp_type_sys_mst_dtl(?,?,?,?,?,?)}";
		
	//DP Involved Nature System Master
	String PROCEDURE_DP_INVOLVE_NAT_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_dp_involve_nat_sys_mst_list";
	String PROCEDURE_DP_INVOLVE_NAT_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_dp_involve_nat_sys_mst_dtl(?,?,?,?,?,?)}";
		
	//DP Penalty Type System Master
	String PROCEDURE_DP_PENALTY_TYPE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_dp_penalty_type_sys_mst_list";
	String PROCEDURE_DP_PENALTY_TYPE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_dp_penalty_type_sys_mst_dtl(?,?,?,?,?,?)}";
	
	//DP Penalty System Master
	String PROCEDURE_DP_PENALTY_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_dp_penalty_sys_mst_list";
	String PROCEDURE_DP_PENALTY_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_dp_penalty_sys_mst_dtl(?,?,?,?,?,?)}";
		
	//DP Involved Officer Category System Master
	String PROCEDURE_DP_IO_CAT_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_dp_io_cat_sys_mst_list";
	String PROCEDURE_DP_IO_CAT_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_dp_io_cat_sys_mst_dtl(?,?,?,?,?,?)}";
	
	//DP Activity Type System Master
	String PROCEDURE_DP_ACTIVITY_TYPE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_dp_activity_type_sys_mst_list";
	String PROCEDURE_DP_ACTIVITY_TYPE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_dp_activity_type_sys_mst_dtl(?,?,?,?,?,?)}";
	
	//DP Proceeding Type System Master
	String PROCEDURE_DP_PROCEEDING_TYPE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_dp_proceeding_type_sys_mst_list";
	String PROCEDURE_DP_PROCEEDING_TYPE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_dp_proceeding_type_sys_mst_dtl(?,?,?,?,?,?)}";
		
	//DP Status Change Type System Master
	String PROCEDURE_DP_STATUS_CHANGE_TYPE_SYSTEM_MST_LIST = "ahism.pkg_pis_system.proc_get_dp_status_change_type_sys_mst_list";
	String PROCEDURE_DP_STATUS_CHANGE_TYPE_SYSTEM_MST_DTL="{call ahism.pkg_pis_system.proc_get_dp_status_change_type_sys_mst_dtl(?,?,?,?,?,?)}";
		
		
}



