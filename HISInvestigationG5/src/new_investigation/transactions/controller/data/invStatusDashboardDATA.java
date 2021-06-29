package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.delegate.invStatusDashboardDelegate;
import new_investigation.vo.template.invStatusDashboardVO;

public class invStatusDashboardDATA {
	
	public static Map getStatusDashboardRecord(invStatusDashboardVO invresultentryvo, UserVO userVO) {
		invStatusDashboardDelegate masterDelegate = new invStatusDashboardDelegate();
		return masterDelegate.getStatusDashboardRecord(invresultentryvo, userVO);
	}

	public static Map getRequestedSampleList(String recordRequested,UserVO userVO) {
		invStatusDashboardDelegate masterDelegate = new invStatusDashboardDelegate();
		return masterDelegate.getRequestedSampleList(recordRequested, userVO);
	}
}
