package opd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

//Created by Singaravelan on 9th sep 2013
public class OPDPatientAttendedReportFB extends ReportFB {
	
	private String userCode;
	private String hmode;
	private String departmentCode;
	
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	
	
	

}
