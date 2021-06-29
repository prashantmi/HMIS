package hisglobal.vo;

public class StaffDetailVO extends ValueObject {
	
	private String num_dept_id;
	private String num_desig_id;
	private String str_gender_name;
	private String str_first_name;
	private String str_middle_name;
	private String str_last_name;
	private String str_emp_no;
	private String age;
	private String hgnum_location_code;
	
	
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


	public String getNum_dept_id() {
		return num_dept_id;
	}


	public void setNum_dept_id(String num_dept_id) {
		this.num_dept_id = num_dept_id;
	}


	public String getStr_emp_no() {
		return str_emp_no;
	}


	public void setStr_emp_no(String str_emp_no) {
		this.str_emp_no = str_emp_no;
	}

		
	
}
