package opd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


public class OpdDeptUnitDoctorWiseFB extends ReportFB
{
	private String departmentCode;
	private String unit;
	private String doctorCode;
	private String isDoctorFound;
	
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping, request);
		this.setDepartmentCode("");
		this.setUnit("");
		this.setDoctorCode("");
	}
	public String getIsDoctorFound() {
		return isDoctorFound;
	}
	public void setIsDoctorFound(String isDoctorFound) {
		this.isDoctorFound = isDoctorFound;
	}
}
