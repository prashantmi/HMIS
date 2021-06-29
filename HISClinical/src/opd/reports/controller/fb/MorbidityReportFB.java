package opd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class MorbidityReportFB extends ReportFB
{
	private String fromHr;
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping, request);
	}
	public String getFromHr() {
		return fromHr;
	}
	public void setFromHr(String fromHr) {
		this.fromHr = fromHr;
	}
}