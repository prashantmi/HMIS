package inpatient.reports.controller.fb;

import hisglobal.presentation.ReportFB;

public class ListOfCaseSheetFB extends ReportFB
{
	private String unitCode;
	private String wardCode;
	private String crNo;
	private String admnNo;
	private String empNo;
	private String hmode;
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getCrNo() {
		return crNo;
	}

	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}

	public String getAdmnNo() {
		return admnNo;
	}

	public void setAdmnNo(String admnNo) {
		this.admnNo = admnNo;
	}
}
