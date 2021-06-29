package opd.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class DiagnosisWiseOpdAndIpdReportFB extends ReportFB{
	
	private String dynamicQuery;
	private String allHospitalCode;
	private String[] multipleHospitalCode;
	
	private String label;
	private String isDynamicQueryRequired;
	private String strDiagnosisCode;
	private String strDiagnosisName;
	
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
	
	public String getStrDiagnosisCode() {
		return strDiagnosisCode;
	}
	public void setStrDiagnosisCode(String strDiagnosisCode) {
		this.strDiagnosisCode = strDiagnosisCode;
	}
	public String getStrDiagnosisName() {
		return strDiagnosisName;
	}
	public void setStrDiagnosisName(String strDiagnosisName) {
		this.strDiagnosisName = strDiagnosisName;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping, request);
	}
}
