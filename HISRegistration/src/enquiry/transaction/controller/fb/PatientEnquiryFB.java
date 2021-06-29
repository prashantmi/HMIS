package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PatientEnquiryFB extends ActionForm {
	

	private String isDirectCall;
	
	private String hrgnum_puk;
	private String hrgstr_fname;
	private String hrgstr_mname;
	private String hrgstr_lname;
	private String gnum_gender_code;
	private String hrgstr_father_name;
	private String hrgstr_spousename;
	private String hrgstr_house_no;
	private String hrgstr_street_no;
	private String hrgstr_district;
	private String gnum_city_loc_code;
	private String hrgstr_city_location;
	private String hrgstr_city;
	private String gnum_state_code;
	private String hrgstr_state_name;
	private String gnum_country_code;
	private String gnum_dept_code;
	private String hrgnum_pincode;
	private String hrgstr_contact_no;
	private String fromDate;
	private String toDate;
	private String hmode;
	private String sortOn;
	private String order;
	private String hrgnum_isunknown;
	private String hrgnum_is_urban;
	private String num_dist_id;
	private String hrgnum_is_broughtdead;
	private String hrgnum_is_mlc;
	private String fromDateHidden;
	private String toDateHidden;
	private String hrgstr_age;
	private String gstr_tehsil;
	private String isCurrentHospitalSearch="1";
	private String hrgstr_mobile_no;

	public String getGstr_tehsil() {
		return gstr_tehsil;
	}

	public void setGstr_tehsil(String gstr_tehsil) {
		this.gstr_tehsil = gstr_tehsil;
	}

	public String getHrgstr_age() {
		return hrgstr_age;
	}

	public void setHrgstr_age(String hrgstr_age) {
		this.hrgstr_age = hrgstr_age;
	}

	public String getHrgnum_is_urban() {
		return hrgnum_is_urban;
	}

	public void setHrgnum_is_urban(String hrgnum_is_urban) {
		this.hrgnum_is_urban = hrgnum_is_urban;
	}

	public String getHrgnum_isunknown() {
		return hrgnum_isunknown;
	}

	public void setHrgnum_isunknown(String hrgnum_isunknown) {
		this.hrgnum_isunknown = hrgnum_isunknown;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		super.reset(mapping, request);
		this.setFromDate("");
		this.setGnum_city_loc_code("");
		this.setGnum_country_code("");
		this.setGnum_dept_code("");
		this.setGnum_gender_code("");
		this.setGnum_state_code("");
		this.setHrgnum_pincode("");
		this.setHrgnum_puk("");
		this.setHrgstr_city("");
		this.setHrgstr_city_location("");
		this.setHrgstr_contact_no("");
		this.setHrgstr_district("");
		this.setHrgstr_father_name("");
		this.setHrgstr_fname("");
		this.setHrgstr_house_no("");
		this.setHrgstr_spousename("");
		this.setHrgstr_lname("");
		this.setHrgstr_mname("");
		this.setHrgstr_state_name("");
		this.setHrgstr_street_no("");
		this.setToDate("");
		this.setHrgnum_isunknown("");
		this.setHrgnum_is_broughtdead("");
		this.setHrgnum_is_mlc("");
		this.setFromDateHidden("");
		this.setToDateHidden("");
		this.setHrgstr_age("");
		this.setGstr_tehsil("");
		this.setHrgnum_is_urban("");
		this.setNum_dist_id("");
		this.setHrgstr_mobile_no("");
		
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	public String getHrgstr_fname() {
		return hrgstr_fname;
	}
	public void setHrgstr_fname(String hrgstr_fname) {
		this.hrgstr_fname = hrgstr_fname;
	}
		public String getHrgstr_mname() {
		return hrgstr_mname;
	}
	public void setHrgstr_mname(String hrgstr_mname) {
		this.hrgstr_mname = hrgstr_mname;
	}
	public String getHrgstr_lname() {
		return hrgstr_lname;
	}
	public void setHrgstr_lname(String hrgstr_lname) {
		this.hrgstr_lname = hrgstr_lname;
	}
	public String getGnum_gender_code() {
		return gnum_gender_code;
	}
	public void setGnum_gender_code(String gnum_gender_code) {
		this.gnum_gender_code = gnum_gender_code;
	}
	
	public String getHrgstr_house_no() {
		return hrgstr_house_no;
	}
	public void setHrgstr_house_no(String hrgstr_house_no) {
		this.hrgstr_house_no = hrgstr_house_no;
	}
	public String getHrgstr_street_no() {
		return hrgstr_street_no;
	}
	public void setHrgstr_street_no(String hrgstr_street_no) {
		this.hrgstr_street_no = hrgstr_street_no;
	}
	public String getHrgstr_district() {
		return hrgstr_district;
	}
	public void setHrgstr_district(String hrgstr_district) {
		this.hrgstr_district = hrgstr_district;
	}
	public String getGnum_city_loc_code() {
		return gnum_city_loc_code;
	}
	public void setGnum_city_loc_code(String gnum_city_loc_code) {
		this.gnum_city_loc_code = gnum_city_loc_code;
	}
	public String getHrgstr_city_location() {
		return hrgstr_city_location;
	}
	public void setHrgstr_city_location(String hrgstr_city_location) {
		this.hrgstr_city_location = hrgstr_city_location;
	}
	public String getHrgstr_city() {
		return hrgstr_city;
	}
	public void setHrgstr_city(String hrgstr_city) {
		this.hrgstr_city = hrgstr_city;
	}
	public String getGnum_state_code() {
		return gnum_state_code;
	}
	public void setGnum_state_code(String gnum_state_code) {
		this.gnum_state_code = gnum_state_code;
	}
	public String getHrgstr_state_name() {
		return hrgstr_state_name;
	}
	public void setHrgstr_state_name(String hrgstr_state_name) {
		this.hrgstr_state_name = hrgstr_state_name;
	}
	public String getGnum_country_code() {
		return gnum_country_code;
	}
	public void setGnum_country_code(String gnum_country_code) {
		this.gnum_country_code = gnum_country_code;
	}
	public String getGnum_dept_code() {
		return gnum_dept_code;
	}
	public void setGnum_dept_code(String gnum_dept_code) {
		this.gnum_dept_code = gnum_dept_code;
	}
		public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getHrgnum_pincode() {
		return hrgnum_pincode;
	}
	public void setHrgnum_pincode(String hrgnum_pincode) {
		this.hrgnum_pincode = hrgnum_pincode;
	}
	public String getHrgstr_contact_no() {
		return hrgstr_contact_no;
	}
	public void setHrgstr_contact_no(String hrgstr_contact_no) {
		this.hrgstr_contact_no = hrgstr_contact_no;
	}
	public String getHrgnum_puk() {
		return hrgnum_puk;
	}
	public void setHrgnum_puk(String hrgnum_puk) {
		this.hrgnum_puk = hrgnum_puk;
	}

	

	public String getHrgnum_is_broughtdead() {
		return hrgnum_is_broughtdead;
	}

	public void setHrgnum_is_broughtdead(String hrgnum_is_broughtdead) {
		this.hrgnum_is_broughtdead = hrgnum_is_broughtdead;
	}

	public String getHrgnum_is_mlc() {
		return hrgnum_is_mlc;
	}

	public void setHrgnum_is_mlc(String hrgnum_is_mlc) {
		this.hrgnum_is_mlc = hrgnum_is_mlc;
	}

	public String getSortOn() {
		return sortOn;
	}

	public void setSortOn(String sortOn) {
		this.sortOn = sortOn;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getHrgstr_father_name() {
		return hrgstr_father_name;
	}

	public void setHrgstr_father_name(String hrgstr_father_name) {
		this.hrgstr_father_name = hrgstr_father_name;
	}

	public String getHrgstr_spousename() {
		return hrgstr_spousename;
	}

	public void setHrgstr_spousename(String hrgstr_spousename) {
		this.hrgstr_spousename = hrgstr_spousename;
	}

	

	public String getNum_dist_id() {
		return num_dist_id;
	}

	public void setNum_dist_id(String num_dist_id) {
		this.num_dist_id = num_dist_id;
	}

	public String getIsDirectCall() {
		return isDirectCall;
	}

	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}

	public String getFromDateHidden() {
		return fromDateHidden;
	}

	public void setFromDateHidden(String fromDateHidden) {
		this.fromDateHidden = fromDateHidden;
	}

	public String getToDateHidden() {
		return toDateHidden;
	}

	public void setToDateHidden(String toDateHidden) {
		this.toDateHidden = toDateHidden;
	}

	public String getIsCurrentHospitalSearch() {
		return isCurrentHospitalSearch;
	}

	public void setIsCurrentHospitalSearch(String isCurrentHospitalSearch) {
		this.isCurrentHospitalSearch = isCurrentHospitalSearch;
	}

	public String getHrgstr_mobile_no() {
		return hrgstr_mobile_no;
	}

	public void setHrgstr_mobile_no(String hrgstr_mobile_no) {
		this.hrgstr_mobile_no = hrgstr_mobile_no;
	}

	
}
