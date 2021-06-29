package opd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


public class OpdDeptUnitDiagnosisStatFB extends ReportFB
{
	private String departmentCode;
	private String unit;
	private String icdCode;
	
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getIcdCode() {
		return icdCode;
	}
	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
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
		this.setDepartmentCode("");
		this.setUnit("");
		this.setIcdCode("");
	}	
}
