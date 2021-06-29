package opd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


public class OpdDeptUnitCatWiseStatFB extends ReportFB
{
	private String departmentCode;
	private String unit;
	private String patPrimaryCatCode;
	
	
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
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
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setPatPrimaryCatCode("");
		this.setDepartmentCode("");
		this.setUnit("");
	}
}
