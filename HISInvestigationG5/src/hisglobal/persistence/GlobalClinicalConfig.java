package hisglobal.persistence;

/**
 * Global Clinical Configuration File for Global Procedures & Functions Names 
 * (pkg_clinical_gbl_view & pkg_clinical_gbl_fun Packages)
 * 
 * @author Pragya Sharma 
 * Creation Date: 23-May-2011
 */

public interface GlobalClinicalConfig
{
	// **** Global Procedures
	String PROC_PAT_DIAGNOSIS_DTL = "{call pkg_clinical_gbl_view.proc_diagnosis_dtl(?,?,?,?,?,?,?)}";
	String PROC_PAT_EPISODE_DTL = "{call pkg_clinical_gbl_view.proc_episode_dtl(?,?,?,?,?,?,?,?)}";
	String PROC_DEPARTMENT_LIST = "{call pkg_clinical_gbl_view.proc_department_lst(?,?,?,?,?,?)}";
	String PROC_UNIT_LIST = "{call pkg_clinical_gbl_view.proc_unit_lst(?,?,?,?,?,?,?)}";
	String PROC_WARD_LIST = "{call pkg_clinical_gbl_view.proc_ward_lst(?,?,?,?,?,?)}";
	String PROC_ADMITTED_PATIENT_LIST = "{call pkg_clinical_gbl_view.proc_admitted_patient_lst(?,?,?,?,?,?,?)}";
	String PROC_GUEST_HOUSE_LIST = "{CALL pkg_clinical_gbl_view.proc_est_guest_house_lst(?,?,?,?)}";
	String PROC_EMPLOYEE_LIST = "{CALL pkg_clinical_gbl_view.proc_pist_employee_lst(?,?,?,?)}";
	String PROC_GUEST_NAME_LIST = "{CALL pkg_clinical_gbl_view.proc_guest_house_allotment_lst(?,?,?,?,?,?)}";
	
	String PROC_SUPPLIER_WARD_LIST = "{call pkg_clinical_gbl_view.proc_supplier_ward_lst(?,?,?,?,?)}";


	// **** Global Functions     //Added by Pawan Kumar B N on 18-Dec-2012
	String FUN_PATIENT_DIAGNOSIS = "pkg_clinical_gbl_fun.get_pat_diagnosis_names";
}
