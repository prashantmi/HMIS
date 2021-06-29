package new_investigation.transactions.delegate;

import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.vo.InvValueAuditVO;
import new_investigation.vo.template.ResultEntryVO;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

public class InvValueAuditDelegate extends Delegate {

	public InvValueAuditDelegate()
	{
		super(new InvestigationEssentialBO());
	}
	
	
	public Map  LabComboForAudit(InvValueAuditFB fb, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabComboForAudit(fb, _UserVO);
	}
	
	public Map  AllTestComboForAudit(InvValueAuditVO vo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.AllTestComboForAudit(vo, _UserVO);
	}
	
	public Map  TestComboForAudit(InvValueAuditVO vo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.TestComboForAudit(vo, _UserVO);
	}
	
	public Map  getlistauditprocess(InvValueAuditVO vo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getlistauditprocess(vo, _UserVO);
	}
	
	public Map  showPatDetails(InvValueAuditVO vo, UserVO _UserVO,String dNo,String testCode,String labCode)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.showPatDetails(vo, _UserVO,dNo,testCode,labCode);
	}

}
