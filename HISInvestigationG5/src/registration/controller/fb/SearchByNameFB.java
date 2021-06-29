package registration.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchByNameFB extends ActionForm{

	private String crNoToRetrieve;
	private String hmode;
	private String hrgstr_fname;
	private String hrgstr_mname;
	private String hrgstr_lname;
	//private String gnum_gender_code;/* Changed by Akash as per OPD DEsk Changes */
	private String hrgstr_age;
	private String gnum_city_loc_code;
	private String hrgdt_episode_date;
	private String fromDate;
	private String toDate;
	private String hrgstr_father_name;
	private String hrgnum_id_no;
	private String hrgstr_file_no;
	private String gnum_hospital_code;
	private String hancstr_mcts_no;
	private String gstr_gender_code; /* Changed by Akash as per OPD DEsk Changes */
	public String getCrNoToRetrieve() {
		return crNoToRetrieve;
	}
	public void setCrNoToRetrieve(String crNoToRetrieve) {
		this.crNoToRetrieve = crNoToRetrieve;
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
	
/*	public String getGnum_gender_code() {
		return gnum_gender_code;
	}
	public void setGnum_gender_code(String gnum_gender_code) {
		this.gnum_gender_code = gnum_gender_code;
	}*/
	public String getHrgstr_age() {
		return hrgstr_age;
	}
	public void setHrgstr_age(String hrgstr_age) {
		this.hrgstr_age = hrgstr_age;
	}
	public String getGnum_city_loc_code() {
		return gnum_city_loc_code;
	}
	public void setGnum_city_loc_code(String gnum_city_loc_code) {
		this.gnum_city_loc_code = gnum_city_loc_code;
	}
	public String getHrgdt_episode_date() {
		return hrgdt_episode_date;
	}
	public void setHrgdt_episode_date(String hrgdt_episode_date) {
		this.hrgdt_episode_date = hrgdt_episode_date;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		this.setCrNoToRetrieve("");
		this.setHrgstr_fname("");
		this.setHrgstr_lname("");
		this.setHrgstr_mname("");
		this.gstr_gender_code="-1";
		this.hrgstr_age="-1";
		this.gnum_city_loc_code="";
		this.hrgstr_father_name="";
		this.hrgnum_id_no="";
		this.fromDate="";
		this.toDate="";
		this.hancstr_mcts_no="";
		this.setHrgstr_file_no("");
		
	}
	public String getHrgstr_father_name() {
		return hrgstr_father_name;
	}
	public void setHrgstr_father_name(String hrgstr_father_name) {
		this.hrgstr_father_name = hrgstr_father_name;
	}
	public String getHrgnum_id_no() {
		return hrgnum_id_no;
	}
	public void setHrgnum_id_no(String hrgnum_id_no) {
		this.hrgnum_id_no = hrgnum_id_no;
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
	public String getHrgstr_file_no() {
		return hrgstr_file_no;
	}
	public void setHrgstr_file_no(String hrgstr_file_no) {
		this.hrgstr_file_no = hrgstr_file_no;
	}
	public String getGnum_hospital_code() {
		return gnum_hospital_code;
	}
	public void setGnum_hospital_code(String gnum_hospital_code) {
		this.gnum_hospital_code = gnum_hospital_code;
	}
	public String getHancstr_mcts_no() {
		return hancstr_mcts_no;
	}
	public void setHancstr_mcts_no(String hancstr_mcts_no) {
		this.hancstr_mcts_no = hancstr_mcts_no;
	}
	public String getGstr_gender_code() {
		return gstr_gender_code;
	}
	public void setGstr_gender_code(String gstr_gender_code) {
		this.gstr_gender_code = gstr_gender_code;
	}
	

}
