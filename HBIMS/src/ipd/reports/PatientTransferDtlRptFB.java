package ipd.reports;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionForm;

public class PatientTransferDtlRptFB extends ActionForm{
	
	private static final long serialVersionUID = 02L;

	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	private String strHospitalCode = "";
	private String strEffectiveFrom = "";
	private String strEffectiveTo = "";
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strUserRemarks = "0";
	private String strCase = "1";
	private String strCrNo = "";
	private String strErrMsg = "";
	private String strDeptValues = "";
	/*private String strUnitValues = "";
	private String strWardValues = "";*/
	private String strCurrentDate = "";
	private String strTransferToWardCode="";
	private String strTransferToDeptCode="";
	private String strTransferCase="1";
	private String strTransferType="1";
	private String strMovementType="1";
	private String strUnitValues = "";
	private String strTransferToUnitCode="";
	private String strDeptTransferValues="";
	
	private String strHospNameValues=""; // Added By Pawan Kumar B N on 16-03-2012
	private String strHospitalName = ""; // Added By Pawan Kumar B N on 16-03-2012
	private String strSeatId = ""; // Added By Pawan Kumar B N on 16-03-2012

	private String strIsTimeRequired = "0"; // Added By Pawan Kumar B N on 16-03-2012
	private String strCurrentTime = ""; // Added By Pawan Kumar B N on 16-03-2012
	private String strFromTime= ""; // Added By Pawan Kumar B N on 16-03-2012
	private String strToTime = ""; // Added By Pawan Kumar B N on 16-03-2012
	private WebRowSet strWardAllWs=null;
	private String strWardAllValues="";
	private String strWardCodeAll="";
	private String strTransferToWardCodeAll="";
	
	
	public String getStrWardAllValues() {
		return strWardAllValues;
	}
	public void setStrWardAllValues(String strWardAllValues) {
		this.strWardAllValues = strWardAllValues;
	}
	public WebRowSet getStrWardAllWs() {
		return strWardAllWs;
	}
	public void setStrWardAllWs(WebRowSet strWardAllWs) {
		this.strWardAllWs = strWardAllWs;
	}
	public String getStrDeptTransferValues() {
		return strDeptTransferValues;
	}
	public void setStrDeptTransferValues(String strDeptTransferValues) {
		this.strDeptTransferValues = strDeptTransferValues;
	}
	public String getStrTransferToUnitCode() {
		return strTransferToUnitCode;
	}
	public void setStrTransferToUnitCode(String strTransferToUnitCode) {
		this.strTransferToUnitCode = strTransferToUnitCode;
	}
	public String getStrTransferCase() {
		return strTransferCase;
	}
	public void setStrTransferCase(String strTransferCase) {
		this.strTransferCase = strTransferCase;
	}
	public String getStrTransferToWardCode() {
		return strTransferToWardCode;
	}
	public void setStrTransferToWardCode(String strTransferToWardCode) {
		this.strTransferToWardCode = strTransferToWardCode;
	}
	public String getStrTransferToDeptCode() {
		return strTransferToDeptCode;
	}
	public void setStrTransferToDeptCode(String strTransferToDeptCode) {
		this.strTransferToDeptCode = strTransferToDeptCode;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrUnitCode() {
		return strUnitCode;
	}
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
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
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
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
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrDeptValues() {
		return strDeptValues;
	}
	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}
/*	public String getStrUnitValues() {
		return strUnitValues;
	}
	public void setStrUnitValues(String strUnitValues) {
		this.strUnitValues = strUnitValues;
	}
	public String getStrWardValues() {
		return strWardValues;
	}
	public void setStrWardValues(String strWardValues) {
		this.strWardValues = strWardValues;
	}*/
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrTransferType() {
		return strTransferType;
	}
	public void setStrTransferType(String strTransferType) {
		this.strTransferType = strTransferType;
	}
	public String getStrMovementType() {
		return strMovementType;
	}
	public void setStrMovementType(String strMovementType) {
		this.strMovementType = strMovementType;
	}
	public String getStrUnitValues() {
		return strUnitValues;
	}
	public void setStrUnitValues(String strUnitValues) {
		this.strUnitValues = strUnitValues;
	}
	public String getStrHospNameValues() {
		return strHospNameValues;
	}
	public void setStrHospNameValues(String strHospNameValues) {
		this.strHospNameValues = strHospNameValues;
	}
	public String getStrHospitalName() {
		return strHospitalName;
	}
	public void setStrHospitalName(String strHospitalName) {
		this.strHospitalName = strHospitalName;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsTimeRequired() {
		return strIsTimeRequired;
	}
	public void setStrIsTimeRequired(String strIsTimeRequired) {
		this.strIsTimeRequired = strIsTimeRequired;
	}
	public String getStrCurrentTime() {
		return strCurrentTime;
	}
	public void setStrCurrentTime(String strCurrentTime) {
		this.strCurrentTime = strCurrentTime;
	}
	public String getStrFromTime() {
		return strFromTime;
	}
	public void setStrFromTime(String strFromTime) {
		this.strFromTime = strFromTime;
	}
	public String getStrToTime() {
		return strToTime;
	}
	public void setStrToTime(String strToTime) {
		this.strToTime = strToTime;
	}
	public String getStrWardCodeAll() {
		return strWardCodeAll;
	}
	public void setStrWardCodeAll(String strWardCodeAll) {
		this.strWardCodeAll = strWardCodeAll;
	}
	public String getStrTransferToWardCodeAll() {
		return strTransferToWardCodeAll;
	}
	public void setStrTransferToWardCodeAll(String strTransferToWardCodeAll) {
		this.strTransferToWardCodeAll = strTransferToWardCodeAll;
	}
}
