package enquiry.vo;

import hisglobal.vo.ValueObject;

public class StaffEnquiryVO extends ValueObject {
	
	private String str_emp_full_name;
	private String gnum_desig_code;
	private String str_emp_no;
	//private String str_emp_no;
	//private String num_dept_id;
	//private String num_desig_id;
	private String str_gender_name;
	//private String str_first_name;
	//private String str_middle_name;
	//private String str_last_name;
	
	private String age;
	private String hgnum_location_code;
	private String gnum_dept_code;
	//private String gnum_desig_code;
	
	
	
	
	public String getStr_emp_full_name() {
		return str_emp_full_name;
	}


	public void setStr_emp_full_name(String str_emp_full_name) {
		this.str_emp_full_name = str_emp_full_name;
	}


	

	
	public String getStr_gender_name() {
		return str_gender_name;
	}


	public void setStr_gender_name(String str_gender_name) {
		this.str_gender_name = str_gender_name;
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




	public String getStr_emp_no() {
		return str_emp_no;
	}


	public void setStr_emp_no(String str_emp_no) {
		this.str_emp_no = str_emp_no;
	}


	public String getGnum_desig_code() {
		return gnum_desig_code;
	}


	public void setGnum_desig_code(String gnum_desig_code) {
		this.gnum_desig_code = gnum_desig_code;
	}


	public String getGnum_dept_code() {
		return gnum_dept_code;
	}


	public void setGnum_dept_code(String gnum_dept_code) {
		this.gnum_dept_code = gnum_dept_code;
	}

		
	
}
