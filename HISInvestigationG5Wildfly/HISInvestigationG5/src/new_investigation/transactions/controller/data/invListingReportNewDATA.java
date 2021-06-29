package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.delegate.invListingReportNewDelegate;
import new_investigation.vo.template.invListingReportNewVO;

public class invListingReportNewDATA {

	public static Map getInvestigationListingNew(invListingReportNewVO invresultentryvo, UserVO userVO) {
		invListingReportNewDelegate masterDelegate = new invListingReportNewDelegate();
		return masterDelegate.getInvestigationListingNew(invresultentryvo, userVO);
	}
	
	public static List getInvestigationListingNew_Ajax_Server(invListingReportNewVO invresultentryvo, UserVO userVO) {
		invListingReportNewDelegate masterDelegate = new invListingReportNewDelegate();
		return masterDelegate.getInvestigationListingNew_Ajax_Server(invresultentryvo, userVO);
	}

	
	public static List getInvestigationListingNew_Ajax_Client(invListingReportNewVO invresultentryvo, UserVO userVO) {
		invListingReportNewDelegate masterDelegate = new invListingReportNewDelegate();
		return masterDelegate.getInvestigationListingNew_Ajax_Client(invresultentryvo, userVO);
	}
}
