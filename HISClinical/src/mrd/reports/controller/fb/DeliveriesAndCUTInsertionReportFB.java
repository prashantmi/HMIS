package mrd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class DeliveriesAndCUTInsertionReportFB extends ReportFB
{
	private String hmode;
	private String strDeliveriesCount;
	private String strCuTInsertCount;
	private String strService;

	public String getStrService() {
		return strService;
	}

	public void setStrService(String strService) {
		this.strService = strService;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
	}

	public String getStrDeliveriesCount()
	{
		return strDeliveriesCount;
	}

	public void setStrDeliveriesCount(String strDeliveriesCount)
	{
		this.strDeliveriesCount = strDeliveriesCount;
	}

	public String getStrCuTInsertCount()
	{
		return strCuTInsertCount;
	}

	public void setStrCuTInsertCount(String strCuTInsertCount)
	{
		this.strCuTInsertCount = strCuTInsertCount;
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
