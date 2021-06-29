package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class HbltBillReqDtlVO 
{
     private WebRowSet strHbltBillReqDtlWs = null;
     private String strChk = "";
	
	 private String  strRowInserted = "0";
	 private String  strRowUpdated = "0";
	 private String  strRowDeleted = "0";
	
	 private String strMsgType = "";
	 private String strWarning = "";
	 private String strMsg ="";
     private String strmodval = "1";
	 private String strReqNo = "0";
	 private String strReqDate = null;
	 private String strDeptCode ="0";
	 private String strChargeTypeId ="0";
	 private String strBservId = "0";
	 private String strGlbReqNo = null;
	 private String strPatCatCode ="0";
	 private String strEpisodeCode ="0";
	 private String strPukNo ="0";
	 private String strAdmNo ="0";
	 private String strPatAcctNo ="0";
	 private String strApprId ="0";
	 private String strBillNo = "0";
	 private String strRemarks = null;
	 private String strStatus ="0";
	 private String strEntryDate =null;
	 private String strSeatId ="0";
	 private String strIsValid ="1";
	 private String strCancelNo= "0";
	 private String strCancelDate = null;
	 private String strApprBy =null;
	 private String strApprDate =null;
	 private String strReqType ="0";
	 
	 
	 public String getStrEntryDate() 
     {
		HisUtil util = new HisUtil("Hospital Master", "VOHospitalMst");

		try {
			strEntryDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {

			new HisException("IpdBillManagementTrans",
					"HbltBillReqDtlVO.getStrEntryDate()", e.getMessage());
		}
	   return strEntryDate;
	}
     
	 
	 
	public WebRowSet getStrHbltBillReqDtlWs() {
		return strHbltBillReqDtlWs;
	}
	public void setStrHbltBillReqDtlWs(WebRowSet strHbltBillReqDtlWs) {
		this.strHbltBillReqDtlWs = strHbltBillReqDtlWs;
	}
	public String getStrRowInserted() {
		return strRowInserted;
	}
	public void setStrRowInserted(String strRowInserted) {
		this.strRowInserted = strRowInserted;
	}
	public String getStrRowUpdated() {
		return strRowUpdated;
	}
	public void setStrRowUpdated(String strRowUpdated) {
		this.strRowUpdated = strRowUpdated;
	}
	public String getStrRowDeleted() {
		return strRowDeleted;
	}
	public void setStrRowDeleted(String strRowDeleted) {
		this.strRowDeleted = strRowDeleted;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrmodval() {
		return strmodval;
	}
	public void setStrmodval(String strmodval) {
		this.strmodval = strmodval;
	}
	public String getStrReqNo() {
		return strReqNo;
	}
	public void setStrReqNo(String strReqNo) {
		this.strReqNo = strReqNo;
	}
	public String getStrReqDate() {
		return strReqDate;
	}
	public void setStrReqDate(String strReqDate) {
		this.strReqDate = strReqDate;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrChargeTypeId() {
		return strChargeTypeId;
	}
	public void setStrChargeTypeId(String strChargeTypeId) {
		this.strChargeTypeId = strChargeTypeId;
	}
	public String getStrBservId() {
		return strBservId;
	}
	public void setStrBservId(String strBservId) {
		this.strBservId = strBservId;
	}
	public String getStrGlbReqNo() {
		return strGlbReqNo;
	}
	public void setStrGlbReqNo(String strGlbReqNo) {
		this.strGlbReqNo = strGlbReqNo;
	}
	public String getStrPatCatCode() {
		return strPatCatCode;
	}
	public void setStrPatCatCode(String strPatCatCode) {
		this.strPatCatCode = strPatCatCode;
	}
	public String getStrEpisodeCode() {
		return strEpisodeCode;
	}
	public void setStrEpisodeCode(String strEpisodeCode) {
		this.strEpisodeCode = strEpisodeCode;
	}
	public String getStrPukNo() {
		return strPukNo;
	}
	public void setStrPukNo(String strPukNo) {
		this.strPukNo = strPukNo;
	}
	public String getStrAdmNo() {
		return strAdmNo;
	}
	public void setStrAdmNo(String strAdmNo) {
		this.strAdmNo = strAdmNo;
	}
	public String getStrPatAcctNo() {
		return strPatAcctNo;
	}
	public void setStrPatAcctNo(String strPatAcctNo) {
		this.strPatAcctNo = strPatAcctNo;
	}
	public String getStrApprId() {
		return strApprId;
	}
	public void setStrApprId(String strApprId) {
		this.strApprId = strApprId;
	}
	public String getStrBillNo() {
		return strBillNo;
	}
	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrCancelNo() {
		return strCancelNo;
	}
	public void setStrCancelNo(String strCancelNo) {
		this.strCancelNo = strCancelNo;
	}
	public String getStrCancelDate() {
		return strCancelDate;
	}
	public void setStrCancelDate(String strCancelDate) {
		this.strCancelDate = strCancelDate;
	}
	public String getStrApprBy() {
		return strApprBy;
	}
	public void setStrApprBy(String strApprBy) {
		this.strApprBy = strApprBy;
	}
	public String getStrApprDate() {
		return strApprDate;
	}
	public void setStrApprDate(String strApprDate) {
		this.strApprDate = strApprDate;
	}
	public String getStrReqType() {
		return strReqType;
	}
	public void setStrReqType(String strReqType) {
		this.strReqType = strReqType;
	}



	public String getStrChk() {
		return strChk;
	}



	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}
}
