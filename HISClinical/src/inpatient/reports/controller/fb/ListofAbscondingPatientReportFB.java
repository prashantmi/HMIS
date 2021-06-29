package inpatient.reports.controller.fb;

import hisglobal.presentation.ReportFB;

public class ListofAbscondingPatientReportFB  extends ReportFB{
	
	private String departmentCode="%";
	private String wardCode;
	
	private String strDeptName;
	private String strWardName;
	
	private String strDischargeTypeCode;
	
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
	public String getStrDischargeTypeCode() {
		return strDischargeTypeCode;
	}
	public void setStrDischargeTypeCode(String strDischargeTypeCode) {
		this.strDischargeTypeCode = strDischargeTypeCode;
	}
	
	
}
