package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CaseSheetEnquiryFB extends ActionForm
{
	
	private String hrgnum_puk;
	private String hrgstr_fname;
	private String hrgstr_mname;
	private String hrgstr_lname;
	private String hipnum_admno;
	private String hrgnum_mlc_no;
	private String hipdt_disstatus_code;
	private String hrgnum_isunknown;
	private String hrgnum_is_broughtdead;
	private String fromDate="";
	private String toDate="";
	private String hmode;
	private String deathCase;
	private String gnum_gender_code;
	private String hrgnum_name;
	private String selectedIndex;
	private String profileId;
	private String profileStatus;
	private String profileHTML;
	private String order;
	private String sortOn;
	private String enquiryType;
	//chronic disease
	private String hgnum_pat_alert_id;
	//Allergy
	private String hgnum_allergy_type_code;
	//Diagnosis
	private String searchCode;
	private String searchDisease;
	private String selectedCode;
	private String icdCode;
	
public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		this.setHrgnum_puk("");
		this.setHrgstr_fname("");
		this.setHrgstr_lname("");
		this.setHrgstr_mname("");
		this.setHipnum_admno("");
		this.setHrgnum_mlc_no("");
		this.setFromDate("");
		this.setToDate("");
		this.setHrgnum_isunknown("");
		this.setHrgnum_is_broughtdead("");
		this.setDeathCase("");
		this.setHipdt_disstatus_code("");
		
		
	}
	
	
	public String getHrgnum_puk() {
		return hrgnum_puk;
	}
	public void setHrgnum_puk(String hrgnum_puk) {
		this.hrgnum_puk = hrgnum_puk;
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
	
	public String getHrgnum_mlc_no() {
		return hrgnum_mlc_no;
	}
	public void setHrgnum_mlc_no(String hrgnum_mlc_no) {
		this.hrgnum_mlc_no = hrgnum_mlc_no;
	}
	
	public String getHrgnum_isunknown() {
		return hrgnum_isunknown;
	}
	public void setHrgnum_isunknown(String hrgnum_isunknown) {
		this.hrgnum_isunknown = hrgnum_isunknown;
	}
	public String getHrgnum_is_broughtdead() {
		return hrgnum_is_broughtdead;
	}
	public void setHrgnum_is_broughtdead(String hrgnum_is_broughtdead) {
		this.hrgnum_is_broughtdead = hrgnum_is_broughtdead;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDeathCase() {
		return deathCase;
	}
	public void setDeathCase(String deathCase) {
		this.deathCase = deathCase;
	}
	public String getHrgnum_name() {
		return hrgnum_name;
	}
	public void setHrgnum_name(String hrgnum_name) {
		this.hrgnum_name = hrgnum_name;
	}
	public String getGnum_gender_code() {
		return gnum_gender_code;
	}
	public void setGnum_gender_code(String gnum_gender_code) {
		this.gnum_gender_code = gnum_gender_code;
	}
	public String getHipnum_admno() {
		return hipnum_admno;
	}
	public void setHipnum_admno(String hipnum_admno) {
		this.hipnum_admno = hipnum_admno;
	}
	public String getHipdt_disstatus_code() {
		return hipdt_disstatus_code;
	}
	public void setHipdt_disstatus_code(String hipdt_disstatus_code) {
		this.hipdt_disstatus_code = hipdt_disstatus_code;
	}
	public String getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getProfileHTML() {
		return profileHTML;
	}
	public void setProfileHTML(String profileHTML) {
		this.profileHTML = profileHTML;
	}
	public String getProfileStatus() {
		return profileStatus;
	}
	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}


	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}


	public String getSortOn() {
		return sortOn;
	}


	public void setSortOn(String sortOn) {
		this.sortOn = sortOn;
	}


	

	public String getHgnum_pat_alert_id() {
		return hgnum_pat_alert_id;
	}


	public void setHgnum_pat_alert_id(String hgnum_pat_alert_id) {
		this.hgnum_pat_alert_id = hgnum_pat_alert_id;
	}


	public String getEnquiryType() {
		return enquiryType;
	}


	public void setEnquiryType(String enquiryType) {
		this.enquiryType = enquiryType;
	}


	public String getHgnum_allergy_type_code() {
		return hgnum_allergy_type_code;
	}


	public void setHgnum_allergy_type_code(String hgnum_allergy_type_code) {
		this.hgnum_allergy_type_code = hgnum_allergy_type_code;
	}


	public String getSearchCode() {
		return searchCode;
	}


	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}


	public String getSearchDisease() {
		return searchDisease;
	}


	public void setSearchDisease(String searchDisease) {
		this.searchDisease = searchDisease;
	}


	public String getSelectedCode() {
		return selectedCode;
	}


	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}


	public String getIcdCode() {
		return icdCode;
	}


	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
	}
}
