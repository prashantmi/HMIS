package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class StaffEnquiryFB extends ActionForm {
	
	private String num_dept_id;
	private String num_desig_id;
	private String str_gender_name;
	private String str_first_name;
	private String str_middle_name;
	private String str_last_name;
	private String str_emp_no;
	private String age;
	private String hgnum_location_code;
	private String hmode;
	private String isDirectCall;
	private String hospitalCode;
	private String hospitalName;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		super.reset(mapping, request);
		this.setStr_first_name("");
		this.setStr_middle_name("");
		this.setStr_last_name("");
		this.setStr_gender_name("");
		this.setNum_dept_id("");
		this.setNum_desig_id("");
		
	}
		
	
	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
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


	public String getStr_emp_no() {
		return str_emp_no;
	}


	public void setStr_emp_no(String str_emp_no) {
		this.str_emp_no = str_emp_no;
	}


	public String getHgnum_location_code() {
		return hgnum_location_code;
	}

	public void setHgnum_location_code(String hgnum_location_code) {
		this.hgnum_location_code = hgnum_location_code;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getNum_dept_id() {
		return num_dept_id;
	}


	public void setNum_dept_id(String num_dept_id) {
		this.num_dept_id = num_dept_id;
	}


	public String getStr_gender_name() {
		return str_gender_name;
	}


	public void setStr_gender_name(String str_gender_name) {
		this.str_gender_name = str_gender_name;
	}


	public String getIsDirectCall() {
		return isDirectCall;
	}


	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}


	public String getHospitalCode() {
		return hospitalCode;
	}


	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}


	public String getHospitalName() {
		return hospitalName;
	}


	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

		
	
}
