package ipd.reports;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author: Vivek Aggarwal
 * @Date: 12-July-2011
 * @Module:	ADT
 */
public class CompleteWardCensusDetailRptFB extends ActionForm {

	private static final long serialVersionUID = 02L;
	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strUserRemarks = "";
	private String strErrMsg = "";
	private String strDate = "";
	private String strCurrentDate = "";
	private String strReportId = "0";
	private String strHospitalCode = "";
	private String strDeptValues = "";
	private String strUnitValues = "";
	private String strWardValues = "";
	private String strWardAllValues = "";
	private String strWardCodeAll="";
	
	private String strSeatId;
	
	//CheckBoxes
	private String strDeptCheckRequired;
	private String strAdmittedDetailRequired;
	private String strReceivedTransferFromOtherWard;
	private String strDischargedByTransferToOtherWard;
	private String strStillBirth;
	private String strDischargeDetail;
	private String strDiedDetail;
	private String strSummary;
	private String strWardName="";
	
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
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrDeptValues() {
		return strDeptValues;
	}
	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}
	public String getStrUnitValues() {
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
	}
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
	public String getStrAdmittedDetailRequired() {
		return strAdmittedDetailRequired;
	}
	public void setStrAdmittedDetailRequired(String strAdmittedDetailRequired) {
		this.strAdmittedDetailRequired = strAdmittedDetailRequired;
	}
	public String getStrReceivedTransferFromOtherWard() {
		return strReceivedTransferFromOtherWard;
	}
	public void setStrReceivedTransferFromOtherWard(
			String strReceivedTransferFromOtherWard) {
		this.strReceivedTransferFromOtherWard = strReceivedTransferFromOtherWard;
	}
	public String getStrDischargedByTransferToOtherWard() {
		return strDischargedByTransferToOtherWard;
	}
	public void setStrDischargedByTransferToOtherWard(
			String strDischargedByTransferToOtherWard) {
		this.strDischargedByTransferToOtherWard = strDischargedByTransferToOtherWard;
	}
	public String getStrStillBirth() {
		return strStillBirth;
	}
	public void setStrStillBirth(String strStillBirth) {
		this.strStillBirth = strStillBirth;
	}
	public String getStrDischargeDetail() {
		return strDischargeDetail;
	}
	public void setStrDischargeDetail(String strDischargeDetail) {
		this.strDischargeDetail = strDischargeDetail;
	}
	public String getStrDiedDetail() {
		return strDiedDetail;
	}
	public void setStrDiedDetail(String strDiedDetail) {
		this.strDiedDetail = strDiedDetail;
	}
	public String getStrSummary() {
		return strSummary;
	}
	public void setStrSummary(String strSummary) {
		this.strSummary = strSummary;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public String getStrWardName() {
		return strWardName;
	}
	public void setStrWardName(String strWardName) {
		this.strWardName = strWardName;
	}
	public String getStrDeptCheckRequired() {
		return strDeptCheckRequired;
	}
	public void setStrDeptCheckRequired(String strDeptCheckRequired) {
		this.strDeptCheckRequired = strDeptCheckRequired;
	}
	public String getStrWardAllValues() {
		return strWardAllValues;
	}
	public void setStrWardAllValues(String strWardAllValues) {
		this.strWardAllValues = strWardAllValues;
	}
	public String getStrWardCodeAll() {
		return strWardCodeAll;
	}
	public void setStrWardCodeAll(String strWardCodeAll) {
		this.strWardCodeAll = strWardCodeAll;
	}	
}
