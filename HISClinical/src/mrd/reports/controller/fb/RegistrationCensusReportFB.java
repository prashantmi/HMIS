package mrd.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class RegistrationCensusReportFB extends ReportFB
{
	private String patCatCode;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		this.setPatCatCode("%");
	}

	
	public String getPatCatCode() {
		return patCatCode;
	}

	public void setPatCatCode(String patCatCode) {
		this.patCatCode = patCatCode;
	}
	
}
