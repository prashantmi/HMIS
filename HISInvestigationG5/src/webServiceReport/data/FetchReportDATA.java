package webServiceReport.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import webServiceReport.delegate.FetchReportDelegate;

public class FetchReportDATA {
	
	public static String getReportFileName(String crNo,String reqNo,String reqDno,String hosCode)
	{
		FetchReportDelegate masterDelegate = new FetchReportDelegate();
		return masterDelegate.getReportFileName(crNo, reqNo, reqDno, hosCode);
	}

	public static String getPortalVal(String hosCode)
	{
		FetchReportDelegate masterDelegate = new FetchReportDelegate();
		return masterDelegate.getPortalVal(hosCode);
	}
}
