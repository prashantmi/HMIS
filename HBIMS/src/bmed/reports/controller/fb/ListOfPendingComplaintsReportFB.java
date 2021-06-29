package bmed.reports.controller.fb;


import org.apache.struts.action.ActionForm;

/**
 * Developer : Vivek Aggarwal
 * Version : 1.0
 * Date : 23-June-	2011
 *
 */
public class ListOfPendingComplaintsReportFB extends ActionForm 
{
	private static final long serialVersionUID = 02L;
	
	private String strNormalMsg="";
	private String strErrMsg="";
	private String strWarningMsg="";
	private String strHospCode="";
	private String strStoreId="";
	private String strIssueChkDetail="1";
	private String strBatchhNo="1";
	private String strCurrentDate="";
	private String strItemVal="";
	private String strStoreVal="";
	private String strIsFooter="";
	private String strUserRemarks="";
	
	private String strItemCategNo = "";
	private String strReportFormat = "0";
	private String strReportId = "";

	private String strFromDate = "";
	private String strToDate = "";
	private String strUniqId;
	private String strUniqValCmb;
	
	
	private String strDeptId;
	private String strItemId;

	
	
	public String getStrDeptId() {
		return strDeptId;
	}
	public void setStrDeptId(String strDeptId) {
		this.strDeptId = strDeptId;
	}
	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public String getStrIssueChkDetail() {
		return strIssueChkDetail;
	}
	public void setStrIssueChkDetail(String strIssueChkDetail) {
		this.strIssueChkDetail = strIssueChkDetail;
	}
	public String getStrBatchhNo() {
		return strBatchhNo;
	}
	public void setStrBatchhNo(String strBatchhNo) {
		this.strBatchhNo = strBatchhNo;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrItemVal() {
		return strItemVal;
	}
	public void setStrItemVal(String strItemVal) {
		this.strItemVal = strItemVal;
	}
	public String getStrStoreVal() {
		return strStoreVal;
	}
	public void setStrStoreVal(String strStoreVal) {
		this.strStoreVal = strStoreVal;
	}
	public String getStrIsFooter() {
		return strIsFooter;
	}
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	public String getStrItemCategNo() {
		return strItemCategNo;
	}
	public void setStrItemCategNo(String strItemCategNo) {
		this.strItemCategNo = strItemCategNo;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrFromDate() {
		return strFromDate;
	}
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}
	public String getStrToDate() {
		return strToDate;
	}
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}
	public String getStrUniqId() {
		return strUniqId;
	}
	public void setStrUniqId(String strUniqId) {
		this.strUniqId = strUniqId;
	}
	public String getStrUniqValCmb() {
		return strUniqValCmb;
	}
	public void setStrUniqValCmb(String strUniqValCmb) {
		this.strUniqValCmb = strUniqValCmb;
	}
	

}
