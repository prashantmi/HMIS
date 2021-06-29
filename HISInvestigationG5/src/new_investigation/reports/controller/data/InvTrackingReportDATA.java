package new_investigation.reports.controller.data;

import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.reports.delegate.InvTrackingReportDelegate;
import new_investigation.vo.InvTrackingReportVO;

@SuppressWarnings({ "rawtypes" })
public class InvTrackingReportDATA {
	
	public static Map AjaxGetPatDetails(InvTrackingReportVO vo, UserVO userVO) {
		InvTrackingReportDelegate reportDelegate = new InvTrackingReportDelegate();
		return reportDelegate.AjaxGetPatDetails(vo, userVO);
	}

	public static Map AjaxGetPatReqnList(InvTrackingReportVO vo, UserVO userVO) {
		InvTrackingReportDelegate reportDelegate = new InvTrackingReportDelegate();
		return reportDelegate.AjaxGetPatReqnList(vo, userVO);
	}
	
	public static Map AjaxGetReqnTestParamList(InvTrackingReportVO vo, UserVO userVO) {
		InvTrackingReportDelegate reportDelegate = new InvTrackingReportDelegate();
		return reportDelegate.AjaxGetReqnTestParamList(vo, userVO);
	}
	
	public static Map GetTestTurnAroundTime(InvTrackingReportVO vo, UserVO userVO) {
		InvTrackingReportDelegate reportDelegate = new InvTrackingReportDelegate();
		return reportDelegate.GetTestTurnAroundTime(vo, userVO);
	}

	public static String isfromFTPorMONGO( UserVO _UserVO)
	{
		InvTrackingReportDelegate reportDelegate = new InvTrackingReportDelegate();
		return reportDelegate.isfromFTPorMONGO( _UserVO);
	}
	
	
}
