package new_investigation.transactions.delegate;

import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.vo.viewExternalInvVO;
import hisglobal.business.Delegate;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import hisglobal.vo.UserVO;

public class viewExternalInvDelegate extends Delegate {

	public viewExternalInvDelegate() {
		super(new InvestigationEssentialBO());
		// TODO Auto-generated constructor stub
	}

	public Map  showPatDetails(viewExternalInvVO vo, UserVO _UserVO,String reqNos)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.showPatDetails(vo, _UserVO,reqNos);
	}
	
}
