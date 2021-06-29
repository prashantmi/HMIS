package new_investigation.transactions.bo;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.vo.template.invListingReportNewVO;

public interface InvListingReportNewBOi {
	public Map getInvestigationListingNew(invListingReportNewVO invListingVO, UserVO userVO);
	public List getInvestigationListingNew_Ajax_Server(invListingReportNewVO invListingVO, UserVO userVO);
	public List getInvestigationListingNew_Ajax_Client(invListingReportNewVO invListingVO, UserVO userVO);
}
