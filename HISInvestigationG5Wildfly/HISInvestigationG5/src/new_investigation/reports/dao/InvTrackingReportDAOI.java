package new_investigation.reports.dao;

import java.util.List;
import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.vo.InvTrackingReportVO;

public interface InvTrackingReportDAOI {
	
	public InvTrackingReportVO AjaxGetPatDetails(InvTrackingReportVO vo, UserVO userVO);
	public List<InvTrackingReportVO> AjaxGetPatSampleReqnList(InvTrackingReportVO vo, UserVO userVO);
	public List<InvTrackingReportVO> AjaxGetPatPatientReqnList(InvTrackingReportVO vo, UserVO userVO) ;
	public List<InvTrackingReportVO> AjaxGetReqnTestParamList(InvTrackingReportVO vo, UserVO userVO);
	public Map<String, String> GetTestTurnAroundTime(InvTrackingReportVO vo, UserVO userVO);
	public String isfromFTPorMONGO( UserVO _UserVO);

}
