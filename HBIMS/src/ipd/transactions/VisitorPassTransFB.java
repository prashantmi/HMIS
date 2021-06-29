package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import org.apache.struts.action.ActionForm;

public class VisitorPassTransFB extends ActionForm {
	
	private static final long serialVersionUID = 02L;
	
	private String strMsgString = "";
	private String strMsgType = "0";
	private String strSeatId ="10001";
	private String strHospitalCode ="";
	private String strAdmnNo ="";
	private String strhAdmnNo ="";
	private String strCrNo ="";
	private String strName ="";
	private String strageSex ="";
	private String strPatientCategory="";
	private String strAdmnDate="";
	private String strWard="";
	private String strRoomBed="";
	private String strhPassNo="";
	private String[] strPassType=null;
	private String[] strIssueRenewDt =null;
	private String strhIssueRenewDt ="";
	private String[] strValidFrom= null;
	private String[] strhValidFrom=null;
	private String[] strValidUpto= null;
	private String[] strhValidUpto=null;
	private String[] strPaidAmount= null;
	private String strpaidAmount="";
	private String strPassDtl="";
	private String[] strchkvisit=null;
	private String strPassSlNo="";
	private String strhRenewFlg="";
	private String strIsvalid="";
	private String[] strPass_No= null;
	private String[] strValidFromR=null;
	private String[] strValidUptoR=null;
	private String strPass_Valid_for="";
	private String strNewRenew="";
	private String strNewRenewHidden="";
	private String strCtDate = null;
	private String visitorPassDetail="";
	private String visitorNewPassDetail="";
	private String strPatientAdmndtl="";
	private String strNormalMsg="";
	private String[] strhPassType=null;
	private String strNoFreePass="";
	private String strNoPaidPass="";
	private String strNFPassValidity="";
	private String strNPPassValidity="";
	private String strRFPassValidity="";
	private String strRPPassValidity="";
	private String strCount="0";
	private String[] strPass=null;
	private String strSelectedChkNo="0";
	private String strAttendentPassType="";
	private String strAttendentPassValidFrom="";
	private String strAttendentPassValidUpto="";
	private String strAttendentPassPaidAmount="";
	private String strAttendentPassCheckBox="";
	
	/*
	private String strSMFromTime="";
	private String strSMToTime="";
	private String strSEFromTime="";
	private String strSEToTime="";
	private String strWMFromTime="";
	private String strWMToTime="";
	private String strWEFromTime="";
	private String strWEToTime="";
	*/
	private String strcountFree="";
	private String strcountPass="";
	private String strPatStatusCode="";
	private String strIsUrban="";
	private String strPatCatCode="";
	private String strAdmnStatusCode="";
	private String strPatDeadCode="";
	
	
	
	public String getStrAdmnStatusCode() {
		return strAdmnStatusCode;
	}
	public void setStrAdmnStatusCode(String strAdmnStatusCode) {
		this.strAdmnStatusCode = strAdmnStatusCode;
	}
	public String getStrPatDeadCode() {
		return strPatDeadCode;
	}
	public void setStrPatDeadCode(String strPatDeadCode) {
		this.strPatDeadCode = strPatDeadCode;
	}
	public String getStrIsUrban() {
		return strIsUrban;
	}
	public void setStrIsUrban(String strIsUrban) {
		this.strIsUrban = strIsUrban;
	}
	public String getStrPatCatCode() {
		return strPatCatCode;
	}
	public void setStrPatCatCode(String strPatCatCode) {
		this.strPatCatCode = strPatCatCode;
	}
	public String getStrPatStatusCode() {
		return strPatStatusCode;
	}
	public void setStrPatStatusCode(String strPatStatusCode) {
		this.strPatStatusCode = strPatStatusCode;
	}
	public String getStrcountFree() {
		return strcountFree;
	}
	public void setStrcountFree(String strcountFree) {
		this.strcountFree = strcountFree;
	}
	public String getStrcountPass() {
		return strcountPass;
	}
	public void setStrcountPass(String strcountPass) {
		this.strcountPass = strcountPass;
	}
	public String[] getStrhPassType() {
		return strhPassType;
	}
	public void setStrhPassType(String[] strhPassType) {
		this.strhPassType = strhPassType;
	}
	public String getStrPatientAdmndtl() {
		return strPatientAdmndtl;
	}
	public void setStrPatientAdmndtl(String strPatientAdmndtl) {
		this.strPatientAdmndtl = strPatientAdmndtl;
	}
	public String getVisitorPassDetail() {
		return visitorPassDetail;
	}
	public void setVisitorPassDetail(String visitorPassDetail) {
		this.visitorPassDetail = visitorPassDetail;
	}
	public String getStrNewRenew() {
		return strNewRenew;
	}
	public void setStrNewRenew(String strNewRenew) {
		this.strNewRenew = strNewRenew;
	}
	public String getStrIsvalid() {
		return strIsvalid;
	}
	public void setStrIsvalid(String strIsvalid) {
		this.strIsvalid = strIsvalid;
	}
	public String getStrhRenewFlg() {
		return strhRenewFlg;
	}
	public void setStrhRenewFlg(String strhRenewFlg) {
		this.strhRenewFlg = strhRenewFlg;
	}
	public String getStrPassSlNo() {
		return strPassSlNo;
	}
	public void setStrPassSlNo(String strPassSlNo) {
		this.strPassSlNo = strPassSlNo;
	}
	public String[] getStrIssueRenewDt() {
		return strIssueRenewDt;
	}
	public void setStrIssueRenewDt(String[] strIssueRenewDt) {
		this.strIssueRenewDt = strIssueRenewDt;
	}
	public String[] getStrValidFrom() {
		return strValidFrom;
	}
	public void setStrValidFrom(String[] strValidFrom) {
		this.strValidFrom = strValidFrom;
	}
	public String[] getStrValidUpto() {
		return strValidUpto;
	}
	public void setStrValidUpto(String[] strValidUpto) {
		this.strValidUpto = strValidUpto;
	}
	public String[] getStrPaidAmount() {
		return strPaidAmount;
	}
	public void setStrPaidAmount(String[] strPaidAmount) {
		this.strPaidAmount = strPaidAmount;
	}
	public String[] getStrPass_No() {
		return strPass_No;
	}
	public void setStrPass_No(String[] strPass_No) {
		this.strPass_No = strPass_No;
	}
	public String getStrPatientCategory() {
		return strPatientCategory;
	}
	public void setStrPatientCategory(String strPatientCategory) {
		this.strPatientCategory = strPatientCategory;
	}
	public String getStrAdmnDate() {
		return strAdmnDate;
	}
	public void setStrAdmnDate(String strAdmnDate) {
		this.strAdmnDate = strAdmnDate;
	}
	public String getStrWard() {
		return strWard;
	}
	public void setStrWard(String strWard) {
		this.strWard = strWard;
	}
	public String getStrRoomBed() {
		return strRoomBed;
	}
	public void setStrRoomBed(String strRoomBed) {
		this.strRoomBed = strRoomBed;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
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
	public String getStrAdmnNo() {
		return strAdmnNo;
	}
	public void setStrAdmnNo(String strAdmnNo) {
		this.strAdmnNo = strAdmnNo;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrageSex() {
		return strageSex;
	}
	public void setStrageSex(String strageSex) {
		this.strageSex = strageSex;
	}
	public String getStrPassDtl() {
		return strPassDtl;
	}
	public void setStrPassDtl(String strPassDtl) {
		this.strPassDtl = strPassDtl;
	}
	public String getStrPass_Valid_for() {
		return strPass_Valid_for;
	}
	public void setStrPass_Valid_for(String strPass_Valid_for) {
		this.strPass_Valid_for = strPass_Valid_for;
	}
	public String getStrhAdmnNo() {
		return strhAdmnNo;
	}
	public void setStrhAdmnNo(String strhAdmnNo) {
		this.strhAdmnNo = strhAdmnNo;
	}
	public String getStrNewRenewHidden() {
		return strNewRenewHidden;
	}
	public void setStrNewRenewHidden(String strNewRenewHidden) {
		this.strNewRenewHidden = strNewRenewHidden;
	}
	public String getStrhIssueRenewDt() {
		return strhIssueRenewDt;
	}
	public void setStrhIssueRenewDt(String strhIssueRenewDt) {
		this.strhIssueRenewDt = strhIssueRenewDt;
	}
	
	public String[] getStrhValidFrom() {
		return strhValidFrom;
	}
	public void setStrhValidFrom(String[] strhValidFrom) {
		this.strhValidFrom = strhValidFrom;
	}
	public String[] getStrhValidUpto() {
		return strhValidUpto;
	}
	public void setStrhValidUpto(String[] strhValidUpto) {
		this.strhValidUpto = strhValidUpto;
	}
	public String getStrCtDate() { // function for getting sysdate in date
		// picker
		HisUtil util = new HisUtil("Bed Status Master", "VOBed Status Mst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "Bed Status Master",
			"VOBedStatusMst.getStrCtDate()-->" + e.getMessage());
		}
		return strCtDate;
	}
	public String getStrhPassNo() {
		return strhPassNo;
	}
	public void setStrhPassNo(String strhPassNo) {
		this.strhPassNo = strhPassNo;
	}
	public String getStrpaidAmount() {
		return strpaidAmount;
	}
	public void setStrpaidAmount(String strpaidAmount) {
		this.strpaidAmount = strpaidAmount;
	}
	public String[] getStrPassType() {
		return strPassType;
	}
	public void setStrPassType(String[] strPassType) {
		this.strPassType = strPassType;
	}
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String[] getStrchkvisit() {
		return strchkvisit;
	}
	public void setStrchkvisit(String[] strchkvisit) {
		this.strchkvisit = strchkvisit;
	}
	public String[] getStrValidUptoR() {
		return strValidUptoR;
	}
	public void setStrValidUptoR(String[] strValidUptoR) {
		this.strValidUptoR = strValidUptoR;
	}
	public String getStrNoFreePass() {
		return strNoFreePass;
	}
	public void setStrNoFreePass(String strNoFreePass) {
		this.strNoFreePass = strNoFreePass;
	}
	public String getStrNoPaidPass() {
		return strNoPaidPass;
	}
	public void setStrNoPaidPass(String strNoPaidPass) {
		this.strNoPaidPass = strNoPaidPass;
	}
	public String getStrNFPassValidity() {
		return strNFPassValidity;
	}
	public void setStrNFPassValidity(String strNFPassValidity) {
		this.strNFPassValidity = strNFPassValidity;
	}
	public String getStrNPPassValidity() {
		return strNPPassValidity;
	}
	public void setStrNPPassValidity(String strNPPassValidity) {
		this.strNPPassValidity = strNPPassValidity;
	}
	public String getStrRFPassValidity() {
		return strRFPassValidity;
	}
	public void setStrRFPassValidity(String strRFPassValidity) {
		this.strRFPassValidity = strRFPassValidity;
	}
	public String getStrRPPassValidity() {
		return strRPPassValidity;
	}
	public void setStrRPPassValidity(String strRPPassValidity) {
		this.strRPPassValidity = strRPPassValidity;
	}
	
	public String[] getStrValidFromR() {
		return strValidFromR;
	}
	public void setStrValidFromR(String[] strValidFromR) {
		this.strValidFromR = strValidFromR;
	}
	public String getVisitorNewPassDetail() {
		return visitorNewPassDetail;
	}
	public void setVisitorNewPassDetail(String visitorNewPassDetail) {
		this.visitorNewPassDetail = visitorNewPassDetail;
	}
	public String getStrCount() {
		return strCount;
	}
	public void setStrCount(String strCount) {
		this.strCount = strCount;
	}
	public String[] getStrPass() {
		return strPass;
	}
	public void setStrPass(String[] strPass) {
		this.strPass = strPass;
	}
	public String getStrSelectedChkNo() {
		return strSelectedChkNo;
	}
	public void setStrSelectedChkNo(String strSelectedChkNo) {
		this.strSelectedChkNo = strSelectedChkNo;
	}
	public String getStrAttendentPassType() {
		return strAttendentPassType;
	}
	public void setStrAttendentPassType(String strAttendentPassType) {
		this.strAttendentPassType = strAttendentPassType;
	}
	public String getStrAttendentPassValidFrom() {
		return strAttendentPassValidFrom;
	}
	public void setStrAttendentPassValidFrom(String strAttendentPassValidFrom) {
		this.strAttendentPassValidFrom = strAttendentPassValidFrom;
	}
	public String getStrAttendentPassValidUpto() {
		return strAttendentPassValidUpto;
	}
	public void setStrAttendentPassValidUpto(String strAttendentPassValidUpto) {
		this.strAttendentPassValidUpto = strAttendentPassValidUpto;
	}
	public String getStrAttendentPassPaidAmount() {
		return strAttendentPassPaidAmount;
	}
	public void setStrAttendentPassPaidAmount(String strAttendentPassPaidAmount) {
		this.strAttendentPassPaidAmount = strAttendentPassPaidAmount;
	}
	public String getStrAttendentPassCheckBox() {
		return strAttendentPassCheckBox;
	}
	public void setStrAttendentPassCheckBox(String strAttendentPassCheckBox) {
		this.strAttendentPassCheckBox = strAttendentPassCheckBox;
	}

}
