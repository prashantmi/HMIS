package billing.reports;
/*
 * Bill Enquiry Report FB
 * 
 * author: Debashis Sardar
 * 
 * dated: 05/03/2012
 */
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BillEnquiryRptFB extends ActionForm {
	
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
	private String strCrNo1="";
	private String strCurrentDate="";
	private String strPatientDtls="";
	private String strMsgString="";
	private String strMsgType="";
	private WebRowSet strBillTypeWs = null;
	private String strReportType="";
	private String strPatientDetailsView="";
	private String strTransNo="";
	private String strRcptNo="";
	private String strHospSerName = "0";
	private String strServiceId = "";
	private String strBillTypeName="";
	private String[] chk=null;
	private String reportContent="";
	private String hospitalHeader="";
	private String hospitalAddress="";
	private String hospitalPhNo="";
	private String reportHeader="";
	private String reportFooter="";
	private String strReportingFormat="test";//should be pdf or html
	private WebRowSet billDetailsHtmlWb=null;
	private String strConsolidatedCreditReport="";
	private String strOrgCode="";
	private String strOrgCodeValues="";	
	private String[] billDate=null;
	private int[] serialNo=null;

	
	ResourceBundle res = ResourceBundle.getBundle("hisglobal.hisconfig.hisReport");
	
	public String getStrBillTypeName() {
		return strBillTypeName;
	}

	public void setStrBillTypeName(String strBillTypeName) {
		this.strBillTypeName = strBillTypeName;
	}

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

	public String getStrReportType() {
		return strReportType;
	}

	public void setStrReportType(String strReportType) {
		this.strReportType = strReportType;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{

		this.strIsFooter="";
		this.strReportId="";
		this.strBillType="";
		this.strUserRemarks="";
		this.strHospitalCode="";
		this.strReportFormat="";
		this.strBillTypeValues="";
		this.strEffectiveFrom="";
		this.strEffectiveTo="";
		this.strCrNo="";
		this.strCurrentDate="";
		this.strPatientDtls="";
		this.strMsgString="";
		this.strMsgType="";
		this.strBillTypeWs=null;
	}
	
	public WebRowSet getStrBillTypeWs() {
		return strBillTypeWs;
	}
	public void setStrBillTypeWs(WebRowSet strBillTypeWs) {
		this.strBillTypeWs = strBillTypeWs;
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

	public String getStrPatientDetailsView() {
		return strPatientDetailsView;
	}

	public void setStrPatientDetailsView(String strPatientDetailsView) {
		this.strPatientDetailsView = strPatientDetailsView;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public String getHospitalHeader() {
		return res.getString("REPORT_HEADER");
	}

	public void setHospitalHeader(String hospitalHeader) {
		this.hospitalHeader = hospitalHeader;
	}

	public String getHospitalAddress() {
		return res.getString("REPORT_ADDRESS");
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getHospitalPhNo() {
		return res.getString("REPORT_CONTACT");
	}

	public void setHospitalPhNo(String hospitalPhNo) {
		this.hospitalPhNo = hospitalPhNo;
	}

	public String getReportHeader() {
		return reportHeader;
	}

	public void setReportHeader(String reportHeader) {
		this.reportHeader = reportHeader;
	}

	public String getReportFooter() {
		return res.getString("REPORT_FOOTER");
	}

	public void setReportFooter(String reportFooter) {
		this.reportFooter = reportFooter;
	}

	public String getStrReportingFormat() {
		return strReportingFormat;
	}

	public void setStrReportingFormat(String strReportingFormat) {
		this.strReportingFormat = strReportingFormat;
	}

	public WebRowSet getBillDetailsHtmlWb() {
		return billDetailsHtmlWb;
	}

	public void setBillDetailsHtmlWb(WebRowSet billDetailsHtmlWb) {
		this.billDetailsHtmlWb = billDetailsHtmlWb;
	}

	public String getStrConsolidatedCreditReport() {
		return strConsolidatedCreditReport;
	}

	public void setStrConsolidatedCreditReport(String strConsolidatedCreditReport) {
		this.strConsolidatedCreditReport = strConsolidatedCreditReport;
	}

	public String getStrOrgCode() {
		return strOrgCode;
	}

	public void setStrOrgCode(String strOrgCode) {
		this.strOrgCode = strOrgCode;
	}

	public String getStrOrgCodeValues() {
		return strOrgCodeValues;
	}

	public void setStrOrgCodeValues(String strOrgCodeValues) {
		this.strOrgCodeValues = strOrgCodeValues;
	}

	public String getStrCrNo1() {
		return strCrNo1;
	}

	public void setStrCrNo1(String strCrNo1) {
		this.strCrNo1 = strCrNo1;
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
