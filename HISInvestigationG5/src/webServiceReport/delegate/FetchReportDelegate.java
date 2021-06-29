package webServiceReport.delegate;

import java.util.Map;

import webServiceReport.bo.FetchReportBO;
import webServiceReport.bo.FetchReportBOi;
import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;
import webServiceReport.bo.FetchReportBOi;
public class FetchReportDelegate extends Delegate {

	public FetchReportDelegate()
	{
		super(new FetchReportBO());
	}

	public String  getReportFileName(String crNo,String reqNo,String reqDno,String hosCode)
	{
		FetchReportBOi serviceBO = (FetchReportBOi) super.getServiceProvider();
		return serviceBO.getReportFileName(crNo, reqNo, reqDno, hosCode);
	}
	
	public String  getPortalVal(String hosCode)
	{
		FetchReportBOi serviceBO = (FetchReportBOi) super.getServiceProvider();
		return serviceBO.getPortalVal(hosCode);
	}
}
