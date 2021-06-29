package new_investigation.transactions.delegate;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.transactions.controller.fb.reportDownloadProcessFB;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.reportDownloadProcessVO;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

public class reportDownloadProcessDelegate extends Delegate {

	public reportDownloadProcessDelegate()
	{
		super(new InvestigationEssentialBO());
	}

	
	public String  fetchMobileNo(reportDownloadProcessFB fb)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.fetchMobileNo(fb);
	}
	
	public String  fetchusername(reportDownloadProcessFB fb)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.fetchusername(fb);
	}

	

	public Map  fetchlist(reportDownloadProcessVO  vo)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.fetchlist(vo);
	}
	
	public Map  Pfetchlist(reportDownloadProcessVO  vo)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.Pfetchlist(vo);
	}
	
	public Map savePublicResultReportPrintingDetails(List<InvResultReportPrintingVO> invresultreportprintingvo)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.savePublicResultReportPrintingDetails(invresultreportprintingvo);
	}

	public String  validatecrno(reportDownloadProcessFB fb)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.validatecrno(fb);
	}
	
	public Map loginInsertDetails(InvResultReportPrintingVO invresultreportprintingvo)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.loginInsertDetails(invresultreportprintingvo);
	}

}
