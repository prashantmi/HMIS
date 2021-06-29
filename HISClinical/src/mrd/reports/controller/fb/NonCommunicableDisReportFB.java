package mrd.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class NonCommunicableDisReportFB extends ReportFB
{
	//private String disType;

//	public String getDisType() {
//		return disType;
//	}
//
//	public void setDisType(String disType) {
//		this.disType = disType;
//	}
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		//this.setDisType("%");
	}
	
}
