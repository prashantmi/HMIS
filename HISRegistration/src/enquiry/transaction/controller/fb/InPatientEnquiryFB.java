package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class InPatientEnquiryFB extends PatientEnquiryFB {
	
	private String hipnum_admno;
	private String admDate;
	private String hipnum_roomno;
	private String lastVisitDate;
	private String dischargeDate;
	private String unit;
	private String wardNo;
	private String bedNo;
	private String status;
	private String reffered;
	private String dept;
	
	private String hgnum_deptunitcode;
	private String hgnum_ward_code;
	private String admissionNo;
	private String admissionWithin;
	private String isCurrentHospitalSearch="1";
	private String gnum_hospital_code;
	
	//This filed is used for storing temp data
	private String globalDeptCode;
		
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		super.reset(mapping, request);
		this.setBedNo("");
		this.setDept("");
		this.setDischargeDate("");
		this.setHipnum_admno("");
		this.setAdmDate("");
		this.setHipnum_roomno("");
		this.setLastVisitDate("");
		this.setStatus("");
		this.setUnit("");
		this.setReffered("");
		this.setWardNo("");
	}

	public String getHipnum_admno() {
		return hipnum_admno;
	}

	public void setHipnum_admno(String hipnum_admno) {
		this.hipnum_admno = hipnum_admno;
	}

	public String getAdmDate() {
		return admDate;
	}

	public void setAdmDate(String admDate) {
		this.admDate = admDate;
	}

	public String getHipnum_roomno() {
		return hipnum_roomno;
	}

	public void setHipnum_roomno(String hipnum_roomno) {
		this.hipnum_roomno = hipnum_roomno;
	}

	public String getLastVisitDate() {
		return lastVisitDate;
	}

	public void setLastVisitDate(String lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getWardNo() {
		return wardNo;
	}

	public void setWardNo(String wardNo) {
		this.wardNo = wardNo;
	}

	public String getBedNo() {
		return bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReffered() {
		return reffered;
	}

	public void setReffered(String reffered) {
		this.reffered = reffered;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getHgnum_deptunitcode() {
		return hgnum_deptunitcode;
	}

	public void setHgnum_deptunitcode(String hgnum_deptunitcode) {
		this.hgnum_deptunitcode = hgnum_deptunitcode;
	}

	public String getHgnum_ward_code() {
		return hgnum_ward_code;
	}

	public void setHgnum_ward_code(String hgnum_ward_code) {
		this.hgnum_ward_code = hgnum_ward_code;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getAdmissionWithin() {
		return admissionWithin;
	}

	public void setAdmissionWithin(String admissionWithin) {
		this.admissionWithin = admissionWithin;
	}

	public String getIsCurrentHospitalSearch() {
		return isCurrentHospitalSearch;
	}

	public void setIsCurrentHospitalSearch(String isCurrentHospitalSearch) {
		this.isCurrentHospitalSearch = isCurrentHospitalSearch;
	}

	public String getGnum_hospital_code() {
		return gnum_hospital_code;
	}

	public void setGnum_hospital_code(String gnum_hospital_code) {
		this.gnum_hospital_code = gnum_hospital_code;
	}

	public String getGlobalDeptCode() {
		return globalDeptCode;
	}

	public void setGlobalDeptCode(String globalDeptCode) {
		this.globalDeptCode = globalDeptCode;
	}

	
	
	
	
}
