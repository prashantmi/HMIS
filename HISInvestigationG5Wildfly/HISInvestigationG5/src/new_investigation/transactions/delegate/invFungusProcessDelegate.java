package new_investigation.transactions.delegate;

import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.transactions.controller.fb.invFungusProcessFB;
import new_investigation.vo.invFungusProcessVO;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

public class invFungusProcessDelegate extends Delegate {

	public invFungusProcessDelegate() {
		super(new InvestigationEssentialBO());
	}
	
	public Map  LabComboForAudit(invFungusProcessFB fb, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabComboForAudit(fb, _UserVO);
	}
	
	public Map  getAntibioticName(invFungusProcessVO vo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getAntibioticName(vo, _UserVO);
	}
	
	public String  getxml(invFungusProcessVO vo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getxml(vo, _UserVO);
	}

}
