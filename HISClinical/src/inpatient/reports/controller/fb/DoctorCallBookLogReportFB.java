package inpatient.reports.controller.fb;

import hisglobal.presentation.ReportFB;

public class DoctorCallBookLogReportFB extends ReportFB
{
	private String unitCode;

	private String deptUnitCode;
	private String wardCode;
	private String crNo;
	private String admnNo;
	private String empNo;

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getDeptUnitCode() {
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
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

	public String getUnitCode()
	{
		return unitCode;
	}

	public void setUnitCode(String unitCode)
	{
		this.unitCode = unitCode;
	}
}
