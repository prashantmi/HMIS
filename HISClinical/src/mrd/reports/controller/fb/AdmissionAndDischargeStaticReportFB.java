package mrd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

public class AdmissionAndDischargeStaticReportFB extends ReportFB{

	private String departmentCode;
	private String unit;
	private String patRegCatCode;
	private String patPrimaryCatCode;
	private String view;
	private String dynamicQuery;
	//private String allHospitalCode;
	
	private String strDeptName;
	private String strUnitName;
	private String label;
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
	public String getPatRegCatCode() {
		return patRegCatCode;
	}
	public void setPatRegCatCode(String patRegCatCode) {
		this.patRegCatCode = patRegCatCode;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getDynamicQuery() {
		return dynamicQuery;
	}
	public void setDynamicQuery(String dynamicQuery) {
		this.dynamicQuery = dynamicQuery;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrUnitName() {
		return strUnitName;
	}
	public void setStrUnitName(String strUnitName) {
		this.strUnitName = strUnitName;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
