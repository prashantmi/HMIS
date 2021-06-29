package opd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


public class OpdDeptUnitRegCatPatCatFB extends ReportFB
{
	private String departmentCode;
	private String unit;
	private String patRegCatCode;
	private String patPrimaryCatCode;
	private String view;
	
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
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
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setDepartmentCode("");
		this.setUnit("");
		this.setPatPrimaryCatCode("");
		this.setPatRegCatCode("");
		this.setView("");
	}
}
