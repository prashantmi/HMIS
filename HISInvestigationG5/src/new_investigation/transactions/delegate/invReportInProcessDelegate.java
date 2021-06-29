package new_investigation.transactions.delegate;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.invReportInProcessVO;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

public class invReportInProcessDelegate extends Delegate {

	public invReportInProcessDelegate()
	{
		super(new InvestigationEssentialBO());
	}
	
	
	
	public Map setResultReportPrintingEssentialsOnLoad( UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setResultReportPrintingEssentialsOnLoad( _UserVO);
	}
	
	public Map setResultReportPrintingEssentials(invReportInProcessVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);
	}
	
	public void saveInJobWorkOrder(List<invReportInProcessVO> vo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 serviceBO.saveInJobWorkOrder(vo, _UserVO);
	}

	
	public List<invReportInProcessVO> getdno(invReportInProcessVO vo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.getdno(vo, _UserVO);
	}
	
}
