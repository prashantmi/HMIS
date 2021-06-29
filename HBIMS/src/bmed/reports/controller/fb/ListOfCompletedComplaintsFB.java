package bmed.reports.controller.fb;
import org.apache.struts.action.ActionForm;

public class ListOfCompletedComplaintsFB extends ActionForm 
{
	private static final long serialVersionUID = 02L;
	
	private String strNormalMsg="";
	private String strErrMsg="";
	private String strWarningMsg="";
	private String strHospCode="";
	private String strStoreId="";
	private String IssueChkDetail="1";
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
	private String strUniqId="0";
	private String strUniqValCmb;
	private String strDeptId;
	private String strDeptCmb;
	private String strItemId;
	private String strItemCmb;
	
	
	
	

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

	public String getStrUserRemarks() {
		return strUserRemarks;
	}

	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}

	public String getStrIsFooter() {
		return strIsFooter;
	}

	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}

	public String getStrStoreVal() {
		return strStoreVal;
	}

	public void setStrStoreVal(String strStoreVal) {
		this.strStoreVal = strStoreVal;
	}

	public String getStrItemVal() {
		return strItemVal;
	}

	public void setStrItemVal(String strItemVal) {
		this.strItemVal = strItemVal;
	}

	public String getStrCurrentDate() {
		return strCurrentDate;
	}

	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
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

	

	public String getIssueChkDetail() {
		return IssueChkDetail;
	}

	public void setIssueChkDetail(String issueChkDetail) {
		IssueChkDetail = issueChkDetail;
	}

	public String getStrBatchhNo() {
		return strBatchhNo;
	}

	public void setStrBatchhNo(String strBatchhNo) {
		this.strBatchhNo = strBatchhNo;
	}

	public String getStrWarningMsg() {
		return strWarningMsg;
	}

	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

	/**
	 * @return the strItemCategNo
	 */
	public String getStrItemCategNo() {
		return strItemCategNo;
	}

	/**
	 * @param strItemCategNo the strItemCategNo to set
	 */
	public void setStrItemCategNo(String strItemCategNo) {
		this.strItemCategNo = strItemCategNo;
	}

	/**
	 * @return the strReportFormat
	 */
	public String getStrReportFormat() {
		return strReportFormat;
	}

	/**
	 * @param strReportFormat the strReportFormat to set
	 */
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}

	/**
	 * @return the strReportId
	 */
	public String getStrReportId() {
		return strReportId;
	}

	/**
	 * @param strReportId the strReportId to set
	 */
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}

	/**
	 * @return the strFromDate
	 */
	public String getStrFromDate() {
		return strFromDate;
	}

	/**
	 * @param strFromDate the strFromDate to set
	 */
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}

	/**
	 * @return the strToDate
	 */
	public String getStrToDate() {
		return strToDate;
	}

	/**
	 * @param strToDate the strToDate to set
	 */
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}

	public String getStrDeptId() {
		return strDeptId;
	}

	public void setStrDeptId(String strDeptId) {
		this.strDeptId = strDeptId;
	}

	public String getStrDeptCmb() {
		return strDeptCmb;
	}

	public void setStrDeptCmb(String strDeptCmb) {
		this.strDeptCmb = strDeptCmb;
	}

	public String getStrItemId() {
		return strItemId;
	}

	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}

	public String getStrItemCmb() {
		return strItemCmb;
	}

	public void setStrItemCmb(String strItemCmb) {
		this.strItemCmb = strItemCmb;
	}
}
