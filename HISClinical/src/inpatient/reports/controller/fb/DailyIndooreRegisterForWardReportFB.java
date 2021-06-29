package inpatient.reports.controller.fb;

import hisglobal.presentation.ReportFB;

public class DailyIndooreRegisterForWardReportFB extends ReportFB{
	
	private String departmentCode="%";
	private String wardCode;
	private String patPrimaryCatCode;
	
	private String strDeptName;
	private String strWardName;
	
	private String disType;

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}

	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	public String getStrDeptName() {
		return strDeptName;
	}

	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}

	public String getStrWardName() {
		return strWardName;
	}

	public void setStrWardName(String strWardName) {
		this.strWardName = strWardName;
	}

	public String getDisType() {
		return disType;
	}

	public void setDisType(String disType) {
		this.disType = disType;
	}
	
}
