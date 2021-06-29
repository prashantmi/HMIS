package opd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

public class CountNoOfPatientOfAnoRectangleDiseaseReportFB extends ReportFB {

	private String dynamicQuery;
	private String allHospitalCode;
	private String[] multipleHospitalCode;
	
	private String label;
	private String isDynamicQueryRequired;
	
	private String strFromYear;
	private String strMinYear;
	private String strToYear;
	
	public String getDynamicQuery() {
		return dynamicQuery;
	}
	public void setDynamicQuery(String dynamicQuery) {
		this.dynamicQuery = dynamicQuery;
	}
	public String getAllHospitalCode() {
		return allHospitalCode;
	}
	public void setAllHospitalCode(String allHospitalCode) {
		this.allHospitalCode = allHospitalCode;
	}
	public String[] getMultipleHospitalCode() {
		return multipleHospitalCode;
	}
	public void setMultipleHospitalCode(String[] multipleHospitalCode) {
		this.multipleHospitalCode = multipleHospitalCode;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getIsDynamicQueryRequired() {
		return isDynamicQueryRequired;
	}
	public void setIsDynamicQueryRequired(String isDynamicQueryRequired) {
		this.isDynamicQueryRequired = isDynamicQueryRequired;
	}
	public String getStrFromYear() {
		return strFromYear;
	}
	public void setStrFromYear(String strFromYear) {
		this.strFromYear = strFromYear;
	}
	public String getStrMinYear() {
		return strMinYear;
	}
	public void setStrMinYear(String strMinYear) {
		this.strMinYear = strMinYear;
	}
	public String getStrToYear() {
		return strToYear;
	}
	public void setStrToYear(String strToYear) {
		this.strToYear = strToYear;
	}
	
	
}
