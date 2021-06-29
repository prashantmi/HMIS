package new_investigation.transactions.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.vo.template.invStatusDashboardVO;

public class invStatusDashboardDelegate extends Delegate{

	public invStatusDashboardDelegate() {
		super(new InvestigationEssentialBO());
	}

	public Map getStatusDashboardRecord(invStatusDashboardVO invvstatusdashboardvo, UserVO userVO) {
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getStatusDashboardRecord(invvstatusdashboardvo, userVO);
	}

	public Map getRequestedSampleList(String recordRequested, UserVO userVO) {
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getRequestedSampleListDashboard(recordRequested, userVO);
	}

}
