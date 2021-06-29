package enquiry.vo;

import hisglobal.vo.ValueObject;

public class InPatientEnquiryVO extends ValueObject
{
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

	
}
