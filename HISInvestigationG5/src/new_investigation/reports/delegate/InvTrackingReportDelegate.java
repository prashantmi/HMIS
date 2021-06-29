package new_investigation.reports.delegate;

import java.util.Map;

import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;
import new_investigation.reports.bo.InvTrackingReportBO;
import new_investigation.reports.bo.InvTrackingReportBOI;
import new_investigation.vo.InvTrackingReportVO;

@SuppressWarnings("rawtypes")
public class InvTrackingReportDelegate extends Delegate {

	public InvTrackingReportDelegate() {
		super(new InvTrackingReportBO());
	}
	
	public Map AjaxGetPatDetails(InvTrackingReportVO vo, UserVO userVO) {
		InvTrackingReportBOI serviceBO = (InvTrackingReportBOI) super.getServiceProvider();
		return serviceBO.AjaxGetPatDetails(vo, userVO);
	}
	
	public Map AjaxGetPatReqnList(InvTrackingReportVO vo, UserVO userVO) {
		InvTrackingReportBOI serviceBO = (InvTrackingReportBOI) super.getServiceProvider();
		return serviceBO.AjaxGetPatReqnList(vo, userVO);
	} 
	
	public Map AjaxGetReqnTestParamList(InvTrackingReportVO vo, UserVO userVO) {
		InvTrackingReportBOI serviceBO = (InvTrackingReportBOI) super.getServiceProvider();
		return serviceBO.AjaxGetReqnTestParamList(vo, userVO);
	}
	
	public Map GetTestTurnAroundTime(InvTrackingReportVO vo, UserVO userVO) {
		InvTrackingReportBOI serviceBO = (InvTrackingReportBOI) super.getServiceProvider();
		return serviceBO.GetTestTurnAroundTime(vo, userVO);
	}
	
	public String  isfromFTPorMONGO( UserVO _UserVO)
	{
		InvTrackingReportBOI serviceBO = (InvTrackingReportBOI) super.getServiceProvider();
		return serviceBO.isfromFTPorMONGO( _UserVO);
	}
}


