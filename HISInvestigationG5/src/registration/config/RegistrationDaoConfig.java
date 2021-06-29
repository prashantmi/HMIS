package registration.config;
import registration.*;
/**
 * RegistrationDaoConfig is an interface that defines hard-coded values that are used for development of DAO.
 * @author AHIS
 */
public interface RegistrationDaoConfig extends RegistrationConfig{
	
	//Renewal Configuration Master
	String RenewalConfigProcedure_dml = "{call pkg_reg_dml.proc_hrgt_renewal_config(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)}";
	String RenewalConfigProcedure_view = "{call pkg_reg_view.proc_hrgt_renewal_config(?,?,?,?,?)}";
	//New Registration Page
	String PROCEDURE_GET_DEPT_COMBO="pkg_reg_view.ProcDeptCombo";
	String PROCEDURE_GET_PATIENT_CAT_COMBO="{call pkg_reg_view.proc_gblt_patient_cat_dtl(?,?,?,?,?,?,?)}";
	String PROCEDURE_GET_GENDER_COMBO="{call PKG_GBL_VIEW.PROC_GBLT_GENDER_MST(?,?,?)}";
	String PROCEDURE_GET_CASTE_COMBO="{call PKG_GBL_VIEW.PROC_GBLT_CASTE_MST(?,?,?)}";
	String PROCEDURE_GET_MARITAL_STATUS_COMBO="{call PKG_GBL_VIEW.PROC_GBLT_MARITAL_STATUS_MST(?,?,?)}";
	/*  ## 		Modification Log							
		##		Modify Date				:10thMar'15 
		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
		##		Modify By				:Sheeldarshi 
*/
	String PROCEDURE_GET_RMO_EMPLOYEE_COMBO="{call PKG_REG_VIEW.proc_pist_emp_registration_dtl(?,?,?,?,?)}";
	//End
	String PROCEDURE_GET_RELIGION_COMBO="{call PKG_GBL_VIEW.PROC_GBLT_RELIGION_MST(?,?,?)}";
	String PROCEDURE_GET_COUNTRY_COMBO="{call PKG_GBL_VIEW.PROC_GBLT_COUNTRY_MST(?,?,?)}";
	String PROCEDURE_GET_STATE_COMBO="{call PKG_GBL_VIEW.PROC_GBLT_STATE_MST(?,?,?,?)}";
	String PROCEDURE_GET_NATIONALITY_COMBO="{call PKG_GBL_VIEW.PROC_GBLT_NATIONALITY_MST(?,?,?)}";
	String PROCEDURE_GET_REF_INSTITUTE_COMBO="{call PKG_REG_VIEW.proc_ref_institute_cmb(?,?,?,?,?)}";
	String PROCEDURE_GET_DISTRICT_COMBO="{call PKG_GBL_VIEW.PROC_GBLT_DISTRICT_MST(?,?,?,?)}";
	String PROCEDURE_GET_RELATION_COMBO="{call PKG_GBL_VIEW.PROC_GBLT_RELATION_MST(?,?,?)}";
	String PROCEDURE_GET_OCCUPATION_COMBO="{call PKG_GBL_VIEW.PROC_GBLT_OCCUPATION_MST(?,?,?,?)}";
	String PROCEDURE_GET_VERIFICATION_DOC_COMBO="{call PKG_GBL_VIEW.PROC_GBLT_VERIFICATION_DOC_MST(?,?,?)}";
	String PROCEDURE_GET_HOSPITALS_COMBO="{call PKG_CLINICAL_UTIL_VIEW.PROC_GBLT_HOSPITAL_MST_COMBO(?,?,?,?)}";
	String PROCEDURE_GET_AGE_RANGE_LIST_COMBO="{call PKG_CLINICAL_UTIL_VIEW.PROC_GBLT_AGE_RANGE_COMBO(?::character varying,?::character varying,?::character varying,?)}";
	String PROCEDURE_GET_CLIENT_COMBO="{call pkg_bill_view.proc_hblt_client_mst(?,?,?,?,?)}";
	/*Start: Surabhi
	 * reason: for to add the client combo in the local patient category
	 * date : 29-7-2016*/
	String PROCEDURE_GET_CLIENT_COMBO_LIST="{call pkg_reg_view.proc_hblt_client_mst(?,?,?,?)}";
	//end
	String PROCEDURE_GET_HOSPITALS_COMBO_ROLEBASED="{call PKG_CLINICAL_UTIL_VIEW.PROC_GBLT_HOSPITAL_MST_COMBO_ROLEBASED(?,?,?,?,?,?)}";

/////counter mapping procedure
	String PROCEDURE_GET_DEPT_COMBO_COUNTER_WISE="Ahis_Reg_CounterBound.procDeptCounterWise";
	String PROCEDURE_DEPT_UNIT_ROOM_LIST_COUNTER_WISE="Ahis_Reg_CounterBound.procDeptUnitRoomCounterWise";
	String PROCEDURE_NEW_DEPT_DEPT_UNIT_ROOM_LIST_COUNTER_WISE="Ahis_Reg_CounterBound.procOffVisitComboCounterWise";
	
	//Location Master
	String PROCEDURE_LOCATION_VIEW = "{call pkg_clinical_util_view.proc_hgbt_location_mst(?,?,?,?,?)}";
	String PROCEDURE_LOCATION_TYPE_VIEW = "{call pkg_clinical_util_view.proc_gblt_location_type_mst(?,?,?,?)}";
	String PROCEDURE_LOCATION_DML = "{call pkg_reg_dml.dml_hgbt_location_mst(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	String PROCEDURE_LOCATION_MODIFY = "{call pkg_reg_view.proc_hgbt_location_mst(?,?,?,?,?,?,?)}";
	
	//Room Master
	String PROCEDURE_ROOM_DML = "{call pkg_reg_dml.dml_hgbt_room_mst(?,?,?,?,?,?,?,?,?,?)}";
	String PROCEDURE_ROOM_VIEW = "{call pkg_reg_view.proc_hgbt_room_mst(?,?,?,?,?,?,?)}";
	
	
	//Appointment Type Master
	String PROCEDURE_APPT_TYPE_DML = "{call pkg_appointment_masters.dml_hapts_appt_type_mst(?,?,?,?,?,?,?,?)}";
	String PROCEDURE_APPT_TYPE_VIEW = "{call pkg_reg_view.proc_hapts_appt_type_mst(?,?,?,?,?,?,?)}";
	
	//Slot Duration Master
	String PROCEDURE_SLOT_DURATION_MST_DML = "{call pkg_appointment_masters.dml_hapts_def_duration_mst(?,?,? ,?,?,? ,?)}"; //7 arguments
	String PROCEDURE_SLOT_DURATION_MST_VIEW = "{call pkg_reg_view.proc_hapts_def_duration_mst(?,?,?, ?,?,? ,?,?)}"; //8 arguments
	
		
	//Unit Master
	String PROCEDURE_UNIT_DML = "{call pkg_reg_dml.dml_hgbt_unit_mst(?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?)}"; //28 arguments
	String PROCEDURE_UNIT_VIEW = "{call pkg_reg_view.proc_hgbt_unit_mst(?,?,?,?,?,?,?,?)}";
	String FUNCTION_RENEWAL_VIEW = "{? = call pkg_clinical_util_fun.getrenewaltype(?,?)}";
	String FUNCTION_DEPT_UNITCODE = "{? = call pkg_reg_fun.gendeptunitcode(?,?)}";

	
	//Department Master
	String PROCEDURE_DEPARTMENT_VIEW = "{call pkg_reg_view.proc_gblt_department_mst(?,?,?,?,?,?,?)}";
	String PROCEDURE_HEADOFDEPARTMENT_VIEW = "{call pkg_clinical_util_view.proc_pist_emp_registration_dtl(?,?,?,?,?,?)}";
	String PROCEDURE_CONSULTANT_UNITWISE ="{call pkg_clinical_util_view.proc_pist_consultantunitwise_dtl(?,?,?,?,?,?)}";  //to get consultant list in Brought by  Declayer & Doctor name combo 
	String PROCEDURE_DEPARTMENT_TYPE_VIEW = "{call pkg_clinical_util_view.proc_gblt_department_type(?,?,?,?)}";
	String PROCEDURE_DEPARTMENT_DML = "{call pkg_reg_dml.dml_gblt_department_mst(?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?)}";//20args
	String PROCEDURE_DEPARTMENT_MODIFY = "{call pkg_reg_view.proc_gblt_department_mst(?,?,?,?,?,?,?)}";
	
	//Patient Category Master
	/*  ## 		Modification Log							
		##		Modify Date				:10thMar'15 
		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
		##		Modify By				:Sheeldarshi 
*/
	String PROCEDURE_PATCATEGORY_DML = "{call pkg_reg_dml.dml_gblt_patient_cat_mst(?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?,	?,?,?,?,?, ?,?,?,?)}"; //Total 29 Arguments,last two added by Vasu on 15.May.18
	//End
	String PROCEDURE_PATCATEGORY_VIEW = "{call pkg_reg_view.proc_gblt_patient_cat_mst(?,?,?,?,?,?,?)}";
	String PROCEDURE_PATIENTCATEGORY_VIEW = "{call pkg_clinical_util_view.proc_gblt_patient_cat_mst(?,?,?,?,?,?)}";
	
	String PROCEDURE_USERLST = "{call pkg_reg_util.proc_gblt_rolebased_userlist_mst(?,?,?,?)}";
	
	String PROCEDURE_GET_DISCLAIMER="{call pkg_reg_view.proc_hrgt_disclaimer_mst(?,?,?,?,?,?,?)}";
	String PROCEDURE_GET_DOB = "GetDob";
	String PROCEDURE_GET_PATIENT_AGE_BY_DOB="ahis_util.dob_age_on";
	
	String PROCEDURE_GET_DETAIL_BY_CAT_ID="{call pkg_reg_view.proc_search_patdtl_by_category_id(?,?,?,?,?,?,?)}";
	
	String PROCEDURE_GET_DETAIL_BY_MOBILE="{call pkg_reg_view.proc_search_patdtl_by_mobile(?,?,?,?,?,?)}";
	
	/************** Deepika Start ***********/
	
	/*************** Deepika End ***********/
	
	/************** Velan Start ***********/
	//Department Unit Room Master
	String PROCEDURE_ROOMSLIST_VIEW = "{call pkg_reg_view.proc_hgbt_room_mst_get_room_list(?,?,?,?,?,?)}";
	String PROCEDURE_DEPTUNITROOM_VIEW = "{call pkg_reg_view.proc_hrgt_dept_unit_room_mst(?,?,?,?,?,?,?)}";
	String PROCEDURE_DEPTUNITROOM_DML = "{call pkg_reg_dml.dml_hrgt_dept_unit_room_mst(?,?,?,?,?,?,?,?,?,?,?,?)}";
		
	//Unit Consultant Master
	String PROCEDURE_UNITCONSULTANT_VIEW = "{call pkg_reg_view.proc_hgbt_unit_consultant_mst(?,?,?,?,?,?,?)}";
	String PROCEDURE_UNITCONSULTANT_DML = "{call pkg_reg_dml.dml_hgbt_unit_consultant_mst(?,?,?,?,?,?,?,?,?,?)}";
		
	//Shift Master
	String PROCEDURE_SHIFT_VIEW = "{call pkg_reg_view.proc_gblt_shift_mst(?,?,?,?,?,?,?)}";
	String PROCEDURE_SHIFT_DML = "{call pkg_reg_dml.dml_gblt_shift_mst(?,?,?,?,?,?,?,?,?,?)}";
	
	//External Institute Master
	String PROCEDURE_EXTINSTITUTE_VIEW = "{call pkg_reg_view.proc_gblt_ext_institute_mst(?,?,?,?,?,?,?)}";
	String PROCEDURE_EXTINSTITUTE_DML = "{call pkg_reg_dml.dml_gblt_ext_institute_mst(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	//Department Unit Room Roster Master
	String PROCEDURE_DEPARTMENT_LIST = "{call pkg_clinical_util_view.proc_gblt_department_mst(?,?,?,?,?)}";
	String PROCEDURE_UNIT_LIST = "{call pkg_clinical_util_view.proc_hgbt_unit_mst(?,?,?,?,?,?)}";
	String PROCEDURE_DEPTUNIT_ROSTER_VIEW = "{call pkg_reg_view.proc_hopt_dept_unit_roster_mst(?,?,?,?,?,?,?,?)}";
	String PROCEDURE_SHIFT_LIST = "{call pkg_clinical_util_view.proc_gblt_shift_mst(?,?,?,?,?,?)}";
	String PROCEDURE_DEPTUNIT_ROSTER_DML = "{call pkg_reg_dml.dml_hopt_dept_unit_roster_mst(?,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?,?)}";
	String PROCEDURE_OPD_ROSTER_MOD = "{call pkg_reg_util.reg_roster(?::numeric,?::timestamp without time zone)}";
	String PROCEDURE_OPD_ROSTER_DUC_MOD = "{call pkg_reg_util.reg_roster_dept_unit(?,?,?)}";

	//Patient Category wise Verification Document Master
	String PROCEDURE_PATCAT_VERIFICATION_LIST = "{call pkg_reg_view.proc_gblt_patcat_verification_mst(?,?,?,?,?,?)}";
	String PROCEDURE_PATCAT_VERIFICATION_DML = "{call pkg_reg_dml.dml_gblt_patcat_verification_mst(?,?,?,?,?,?,?,?)}";
	
	//Disclaimer Master
	String PROCEDURE_DISCLAIMER_DML = "{call pkg_reg_dml.dml_hrgt_disclaimer_mst(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	String PROCEDURE_DISCLAIMER_VIEW = "{call pkg_reg_view.proc_hrgt_disclaimer_mst(?,?,?,?,?,?)}";
	
	//Counter Master
	String PROCEDURE_COUNTERTYPE_LIST = "{call pkg_clinical_util_view.proc_gblt_counter_type_mst(?,?,?,?,?)}";
	String PROCEDURE_COUNTER_DML = "{call pkg_reg_dml.dml_gblt_counter_mst(?,?,?,?,?,?,?,?,?,?,?)}";
	String PROCEDURE_COUNTER_VIEW = "{call pkg_reg_view.proc_gblt_counter_mst(?,?,?,?,?,?,?)}";

	//UOM Master
	String PROCEDURE_UOM_DML = "{call pkg_reg_dml.dml_gblt_uom_mst(?,?,?,?,?,?,?)}";
	String PROCEDURE_UOM_VIEW = "{call pkg_reg_view.proc_gblt_uom_mst(?,?,?,?,?,?)}";
	
	//Primary Category Change
	String PROCEDURE_PRICAT_CHANGE_DML = "{call pkg_reg_dml.dml_hrgt_pricat_change_dtl(?,?,?,?,?,?::character varying,?::character varying,?::character varying,?::character varying,?::timestamp without time zone,?,?,?,?,?,?)}";
	String PROCEDURE_PRICAT_CHANGE_VIEW = "{call pkg_reg_view.proc_hrgt_pricat_change_dtl(?,?,?,?,?,?)}";
	String FUNCTION_GET_NEW_RENEWAL_DATE = "{? = call pkg_reg_util.fun_calc_patcat_expiry(?,?,?)}";

	//Patient Status Master
	String PROCEDURE_EMG_PAT_STATUS_DML = "{call pkg_reg_dml.dml_hrgt_pat_status_mst(?,?,?,?,?,?)}";
	String PROCEDURE_EMG_PAT_STATUS_VIEW = "{call pkg_reg_view.proc_hrgt_pat_status_mst(?,?,?,?,?,?)}";
	
	//Mlc Case Type Master
	String PROCEDURE_EMG_MLC_CASE_TYPE_DML = "{call pkg_reg_dml.dml_hrgt_mlc_case_type_mst(?,?,?,?,?,?,?)}";
	String PROCEDURE_EMG_MLC_CASE_TYPE_VIEW = "{call pkg_reg_view.proc_hrgt_mlc_case_type_mst(?,?,?,?,?,?)}";
	
	//Death Manner Master
	String PROCEDURE_EMG_DEATH_MANNER_DML = "{call pkg_reg_dml.dml_hgbt_death_manner_mst(?,?,?,?,?,?,?,?,?)}";
	String PROCEDURE_EMG_DEATH_MANNER_VIEW = "{call pkg_reg_view.proc_hgbt_death_manner_mst(?,?,?,?,?,?)}";
		
	//Police Station Master
	String PROCEDURE_POLICE_STATION_DML = "{call pkg_reg_dml.dml_hrgt_police_station_mst(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"; //26
	String PROCEDURE_POLICE_STATION_VIEW = "{call pkg_reg_view.proc_hrgt_police_station_mst(?,?,?,?,?,?,?)}";
		
	/*************** Velan End ************/
	
	/************** Garima Start ***********/
	
	//Patient Referral Process
	String PROCEDURE_REFERRAL_SPL_UNIT_COMBO="{call PKG_REG_VIEW.proc_referral_specialunit_cmb(?,?,?,?,?,?)}";
	String PROCEDURE_REFERRAL_DEPT_COMBO="{call PKG_REG_VIEW.proc_referral_dept_cmb(?,?,?,?,?,?)}";
	String PROCEDURE_USER_COMBO="{call pkg_clinical_util_view.proc_gblt_user_mst(?,?,?,?,?)}";
	
	/*************** Garima End ***********/
/*start:yogender*/
	// Emergency case...
	String PROCEDURE_EMG_CASE_DML = "{call pkg_reg_dml.dml_hrgt_emrgency_case_mst(?,?,?,?,?,?,?,?,?)}";
	String PROCEDURE_EMG_CASE_VIEW = "{call pkg_reg_view.proc_hrgt_emrgency_case_mst(?,?,?,?,?,?,?,?)}";
	
	/*end:yogender*/
	
/*****************Surabhi Start***********/
	
	String PROCEDURE_REGCONFIG_VIEW = "{call pkg_reg_view.proc_hrgt_reg_configuration_mst(?,?,?,?)}";
	String PROCEDURE_REGCONFIG_DML = "{call pkg_reg_dml.dml_hrgt_reg_configuration_mst(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	/*****************Surabhi End**************/
	String PROCEDURE_GET_LETTER_TYPE_COMBO="{call pkg_reg_view.proc_hrgt_credit_letter_type_sys(?,?,?,?)}";



}