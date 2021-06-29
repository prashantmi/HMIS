package mrd.vo;

import hisglobal.vo.ValueObject;

public class CaseSheetEnquiryVO extends ValueObject
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
	private String fromDate;
	private String toDate;
	private String deathCase;

	private String enquiryType;
	private String hgnum_pat_alert_id;
	private String recordType;
	private String hgnum_allergy_type_code;
	private String icdCode;
	private String entryDate;

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
	public String getDeathCase() {
		return deathCase;
	}
	public void setDeathCase(String deathCase) {
		this.deathCase = deathCase;
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

	public String getEnquiryType() {
		return enquiryType;
	}
	public void setEnquiryType(String enquiryType) {
		this.enquiryType = enquiryType;
	}
	public String getHgnum_pat_alert_id() {
		return hgnum_pat_alert_id;
	}
	public void setHgnum_pat_alert_id(String hgnum_pat_alert_id) {
		this.hgnum_pat_alert_id = hgnum_pat_alert_id;
	}

	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getHgnum_allergy_type_code() {
		return hgnum_allergy_type_code;
	}
	public void setHgnum_allergy_type_code(String hgnum_allergy_type_code) {
		this.hgnum_allergy_type_code = hgnum_allergy_type_code;
	}
	public String getIcdCode() {
		return icdCode;
	}
	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	

	
}
