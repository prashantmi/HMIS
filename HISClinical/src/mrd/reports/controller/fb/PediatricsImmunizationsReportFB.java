package mrd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class PediatricsImmunizationsReportFB extends ReportFB
{
	
	private String hmode;

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
	
}
