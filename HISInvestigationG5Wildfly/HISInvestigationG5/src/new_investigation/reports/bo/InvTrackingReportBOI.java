package new_investigation.reports.bo;

import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.vo.InvTrackingReportVO;

@SuppressWarnings("rawtypes")
public interface InvTrackingReportBOI {
	
	public Map AjaxGetPatDetails(InvTrackingReportVO vo, UserVO userVO);
	public Map AjaxGetPatReqnList(InvTrackingReportVO vo, UserVO userVO); 
	public Map AjaxGetReqnTestParamList(InvTrackingReportVO vo, UserVO userVO);
	public Map GetTestTurnAroundTime(InvTrackingReportVO vo, UserVO userVO);
	public String isfromFTPorMONGO(UserVO _UserVO);

}
