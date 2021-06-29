package mrd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class SpecialityUnitWiseOpdPatientsFB extends ReportFB
{
	private String departmentCode;
	private String unit;
	
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
		//this.setDepartmentCode("-1");
		this.setUnit("-1");	
		//this.setChoice(MrdConfig.DEPT_WISE);
		
	}

}
