package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.transactions.controller.fb.invReportHistoryFB;
import new_investigation.transactions.delegate.invReportHistoryDelegate;

public class invReportHistoryDATA {

	
	public static Map fetchActiveReportData(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO)
	{
		invReportHistoryDelegate masterDelegate = new invReportHistoryDelegate();
		return masterDelegate.fetchActiveReportData(invinvReportHistoryFB, _UserVO);
	}
	
	
	public static Map fetchActiveReportData1(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO)
	{
		invReportHistoryDelegate masterDelegate = new invReportHistoryDelegate();
		return masterDelegate.fetchActiveReportData1(invinvReportHistoryFB, _UserVO);
	}
	
	public static Map fetchActiveReportDataALL(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO)
	{
		invReportHistoryDelegate masterDelegate = new invReportHistoryDelegate();
		return masterDelegate.fetchActiveReportDataall(invinvReportHistoryFB, _UserVO);
	}
	
	public static Map fetchActiveReportDataALLInactive(invReportHistoryFB invinvReportHistoryFB, UserVO _UserVO)
	{
		invReportHistoryDelegate masterDelegate = new invReportHistoryDelegate();
		return masterDelegate.fetchActiveReportDataallInactive(invinvReportHistoryFB, _UserVO);
	}
	
}
