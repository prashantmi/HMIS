package new_investigation.transactions.delegate;

import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.transactions.controller.fb.invAntibioticProcessFB;
import new_investigation.vo.invAntibioticProcessVO;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

public class invAntibioticProcessDelegate extends Delegate {

	public invAntibioticProcessDelegate() {
		super(new InvestigationEssentialBO());
	}
	
	public Map  LabComboForAudit(invAntibioticProcessFB fb, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabComboForAudit(fb, _UserVO);
	}
	
	public Map  getAntibioticName(invAntibioticProcessVO vo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getAntibioticName(vo, _UserVO);
	}
	
	public String  getxml(invAntibioticProcessVO vo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getxml(vo, _UserVO);
	}

}
