package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EmployeePopUpFB extends ActionForm{
	
	private String hmode;
	private String str_first_name;
	private String str_middle_name;
	private String str_last_name;
	private String num_desig_id;
	private String selectedEmployee;
	private String fieldToPopulate;
	private String index;
	private String str_emp_full_name;
	private String str_emp_no;
	private String gnum_dept_code;
	private String gnum_desig_code;
	private String deptname;
	private String str_old_emp_no;
	
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getFieldToPopulate() {
		return fieldToPopulate;
	}

	public void setFieldToPopulate(String fieldToPopulate) {
		this.fieldToPopulate = fieldToPopulate;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		super.reset(mapping, request);
		this.setStr_first_name("");
		this.setStr_middle_name("");
		this.setStr_last_name("");
		this.setNum_desig_id("");
		this.setSelectedEmployee("");
		
	}
	
	public String getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(String selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}


	public String getStr_first_name() {
		return str_first_name;
	}

	public void setStr_first_name(String str_first_name) {
		this.str_first_name = str_first_name;
	}

	public String getStr_middle_name() {
		return str_middle_name;
	}

	public void setStr_middle_name(String str_middle_name) {
		this.str_middle_name = str_middle_name;
	}

	public String getStr_last_name() {
		return str_last_name;
	}

	public void setStr_last_name(String str_last_name) {
		this.str_last_name = str_last_name;
	}

	public String getNum_desig_id() {
		return num_desig_id;
	}

	public void setNum_desig_id(String num_desig_id) {
		this.num_desig_id = num_desig_id;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getStr_emp_full_name() {
		return str_emp_full_name;
	}

	public void setStr_emp_full_name(String str_emp_full_name) {
		this.str_emp_full_name = str_emp_full_name;
	}

	public String getStr_emp_no() {
		return str_emp_no;
	}

	public void setStr_emp_no(String str_emp_no) {
		this.str_emp_no = str_emp_no;
	}

	public String getGnum_dept_code() {
		return gnum_dept_code;
	}

	public void setGnum_dept_code(String gnum_dept_code) {
		this.gnum_dept_code = gnum_dept_code;
	}

	public String getGnum_desig_code() {
		return gnum_desig_code;
	}

	public void setGnum_desig_code(String gnum_desig_code) {
		this.gnum_desig_code = gnum_desig_code;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getStr_old_emp_no() {
		return str_old_emp_no;
	}

	public void setStr_old_emp_no(String str_old_emp_no) {
		this.str_old_emp_no = str_old_emp_no;
	}

}
