package new_investigation.transactions.delegate;

import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.transactions.controller.fb.invReportHistoryFB;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

public class invReportHistoryDelegate extends Delegate {

	public invReportHistoryDelegate() {
		super(new InvestigationEssentialBO());
		// TODO Auto-generated constructor stub
	}
	
	
	public Map  fetchActiveReportData(invReportHistoryFB fb, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.fetchActiveReportData(fb, _UserVO);
	}
	
	public Map  fetchActiveReportData1(invReportHistoryFB fb, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.fetchActiveReportData1(fb, _UserVO);
	}
	
	public Map  fetchActiveReportDataall(invReportHistoryFB fb, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.fetchActiveReportDataall(fb, _UserVO);
	}
	
	public Map  fetchActiveReportDataallInactive(invReportHistoryFB fb, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.fetchActiveReportDataallInactive(fb, _UserVO);
	}

}
