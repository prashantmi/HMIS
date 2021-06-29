package new_investigation.transactions.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.bo.InvListingReportNewBO;
import new_investigation.transactions.bo.InvListingReportNewBOi;
import new_investigation.vo.template.invListingReportNewVO;

public class invListingReportNewDelegate extends Delegate{

	public invListingReportNewDelegate() {
		super(new InvListingReportNewBO());
	}

	public Map getInvestigationListingNew(invListingReportNewVO invListingVO, UserVO userVO) {
		InvListingReportNewBOi serviceBO = (InvListingReportNewBOi) super.getServiceProvider();
		return serviceBO.getInvestigationListingNew(invListingVO, userVO);
	}
	
	public List getInvestigationListingNew_Ajax_Server(invListingReportNewVO invListingVO, UserVO userVO) {
		InvListingReportNewBOi serviceBO = (InvListingReportNewBOi) super.getServiceProvider();
		return serviceBO.getInvestigationListingNew_Ajax_Server(invListingVO, userVO);
	}

	public List getInvestigationListingNew_Ajax_Client(invListingReportNewVO invListingVO, UserVO userVO) {
		InvListingReportNewBOi serviceBO = (InvListingReportNewBOi) super.getServiceProvider();
		return serviceBO.getInvestigationListingNew_Ajax_Client(invListingVO, userVO);
	}
}
