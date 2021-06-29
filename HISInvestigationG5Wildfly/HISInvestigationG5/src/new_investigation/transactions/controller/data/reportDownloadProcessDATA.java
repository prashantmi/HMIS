package new_investigation.transactions.controller.data;

import java.util.List;
import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.transactions.controller.fb.reportDownloadProcessFB;
import new_investigation.transactions.delegate.reportDownloadProcessDelegate;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.reportDownloadProcessVO;

public class reportDownloadProcessDATA {

	
	public static String fetchMobileNo(reportDownloadProcessFB fb)
	{
		reportDownloadProcessDelegate masterDelegate = new reportDownloadProcessDelegate();
		return masterDelegate.fetchMobileNo(fb);
	}
	
	public static String fetchusername(reportDownloadProcessFB fb)
	{
		reportDownloadProcessDelegate masterDelegate = new reportDownloadProcessDelegate();
		return masterDelegate.fetchusername(fb);
	}
	
	public static Map fetchlist(reportDownloadProcessVO vo)
	{
		reportDownloadProcessDelegate masterDelegate = new reportDownloadProcessDelegate();
		return masterDelegate.fetchlist(vo );
	}

	public static Map Pfetchlist(reportDownloadProcessVO vo)
	{
		reportDownloadProcessDelegate masterDelegate = new reportDownloadProcessDelegate();
		return masterDelegate.Pfetchlist(vo );
	}
	
	public static Map savePublicResultReportPrintingDetails(List<InvResultReportPrintingVO> invresultreportprintingvo)
	{
		reportDownloadProcessDelegate masterDelegate = new reportDownloadProcessDelegate();
		return masterDelegate.savePublicResultReportPrintingDetails(invresultreportprintingvo);
	}

	
	public static String validatecrno(reportDownloadProcessFB fb)
	{
		reportDownloadProcessDelegate masterDelegate = new reportDownloadProcessDelegate();
		return masterDelegate.validatecrno(fb);
	}
	
	public static Map loginInsertDetails(InvResultReportPrintingVO invresultreportprintingvo)
	{
		reportDownloadProcessDelegate masterDelegate = new reportDownloadProcessDelegate();
		return masterDelegate.loginInsertDetails(invresultreportprintingvo);
	}
	
}
