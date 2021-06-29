package billing.reports;
/*
 * Bill Enquiry Report VO
 * 
 * author: Debashis Sardar
 * 
 * dated: 05/03/2012
 */
import javax.sql.rowset.WebRowSet;

public class BillEnquiryRptVO {
	private static final long serialVersionUID = 02L;
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strErrMsg = "";
	private String strBillType = "0";
	private String strUserRemarks = "0";
	private String strHospitalCode = "";
	private String strReportFormat = "0";
	private String strBillTypeValues = "0";
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strCrNo="";
	private String strCurrentDate="";
	private String strPatientDtls="";
	private String strMsgString="";
	private String strMsgType="";
	private WebRowSet strBillTypeWs = null;
	private WebRowSet strBillDetailsWs = null;
	private String strTransNo="";
	private String strRcptNo="";
	private String strHospSerName = "0";
	private String strServiceId = "";
	private String[] billNo=null;
	private WebRowSet billContent = null; 
	private WebRowSet billDetailsHtmlWb=null;
	private WebRowSet strOrgCodeValuesWs= null;
	private String[] billDate=null;
	private int[] serialNo=null;
	
	public String getStrHospSerName() {
		return strHospSerName;
	}
	public void setStrHospSerName(String strHospSerName) {
		this.strHospSerName = strHospSerName;
	}
	public String getStrServiceId() {
		return strServiceId;
	}
	public void setStrServiceId(String strServiceId) {
		this.strServiceId = strServiceId;
	}
	public String getStrTransNo() {
		return strTransNo;
	}
	public void setStrTransNo(String strTransNo) {
		this.strTransNo = strTransNo;
	}
	public String getStrRcptNo() {
		return strRcptNo;
	}
	public void setStrRcptNo(String strRcptNo) {
		this.strRcptNo = strRcptNo;
	}
	public String getStrMsgString() {
		return strMsgString;
	}
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getStrIsFooter() {
		return strIsFooter;
	}
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrBillType() {
		return strBillType;
	}
	public void setStrBillType(String strBillType) {
		this.strBillType = strBillType;
	}
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrBillTypeValues() {
		return strBillTypeValues;
	}
	public void setStrBillTypeValues(String strBillTypeValues) {
		this.strBillTypeValues = strBillTypeValues;
	}
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrPatientDtls() {
		return strPatientDtls;
	}
	public void setStrPatientDtls(String strPatientDtls) {
		this.strPatientDtls = strPatientDtls;
	}
	public WebRowSet getStrBillTypeWs() {
		return strBillTypeWs;
	}
	public void setStrBillTypeWs(WebRowSet strBillTypeWs) {
		this.strBillTypeWs = strBillTypeWs;
	}
	public WebRowSet getStrBillDetailsWs() {
		return strBillDetailsWs;
	}
	public void setStrBillDetailsWs(WebRowSet strBillDetailsWs) {
		this.strBillDetailsWs = strBillDetailsWs;
	}
	public String[] getBillNo() {
		return billNo;
	}
	public void setBillNo(String[] billNo) {
		this.billNo = billNo;
	}
	public WebRowSet getBillContent() {
		return billContent;
	}
	public void setBillContent(WebRowSet billContent) {
		this.billContent = billContent;
	}
	public WebRowSet getBillDetailsHtmlWb() {
		return billDetailsHtmlWb;
	}
	public void setBillDetailsHtmlWb(WebRowSet billDetailsHtmlWb) {
		this.billDetailsHtmlWb = billDetailsHtmlWb;
	}
	public WebRowSet getStrOrgCodeValuesWs() {
		return strOrgCodeValuesWs;
	}
	public void setStrOrgCodeValuesWs(WebRowSet strOrgCodeValuesWs) {
		this.strOrgCodeValuesWs = strOrgCodeValuesWs;
	}
	public String[] getBillDate() {
		return billDate;
	}
	public void setBillDate(String[] billDate) {
		this.billDate = billDate;
	}
	public int[] getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int[] serialNo) {
		this.serialNo = serialNo;
	}

	
}
