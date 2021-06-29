package opd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class ICDCodeReportFB extends ReportFB
{
	private String fromHr;
	private String departmentCode;
	private String unit;

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setDepartmentCode("");
		this.setUnit("");
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public String getFromHr()
	{
		return fromHr;
	}

	public void setFromHr(String fromHr)
	{
		this.fromHr = fromHr;
	}
}
