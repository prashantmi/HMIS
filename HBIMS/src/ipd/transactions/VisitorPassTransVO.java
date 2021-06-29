package ipd.transactions;

import javax.sql.rowset.WebRowSet;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import hisglobal.utility.TransferObject;

public class VisitorPassTransVO implements TransferObject {
	
	private static final long serialVersionUID = 02L;
	
	private String strMsgString = "";
	private String strMsgType = "0";
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
	private String[] strValidFrom= null;
	private String[] strValidUpto= null;
	private String[] strPaidAmount= null;
	private String strCount="0";
	private String strPassSlNo="";
	private String strSeatId ="";
	private String strHospitalCode ="";
	private String strhRenewFlg="";
	private String strIsvalid="";
	private String[] strPass_No= null;
	private String strPass_Valid_for="";
	private String strNewRenew="";
	private String strhIssueRenewDt ="";
	private String[] strhValidFrom=null;
	private String[] strhValidUpto=null;
	private String strCtDate = null;
	private String strpaidAmount="";
	private String strPatientAdmndtl="";
	private String[] strchkvisit=null;
	private String[] strValidUptoR=null;
	private String[] strhPassType=null;
	private String[] strPass=null;
	private String strNoFreePass="";
	private String strNoPaidPass="";
	private String strNFPassValidity="";
	private String strNPPassValidity="";
	private String strRFPassValidity="";
	private String strRPPassValidity="";
	private String strSelectedChkNo="0";
	private String strAttendancePassCount="0";
	private String passType="";
	private String strcountFree="";
	private String strcountPass="";
	private int strRemainderFreePass=0;
	private int strRemainderPaidPass=0;
	private String strAttendentPassType="";
	private String strAttendentPassValidFrom="";
	private String strAttendentPassValidUpto="";
	private String strAttendentPassPaidAmount="";
	private String strAttendentPassCheckBox="";
	
	private WebRowSet passDetailWS=null;
	private WebRowSet newPassDetails=null;
	private WebRowSet strPasstype =null;
	private String strPatStatusCode="";
	private String strIsUrban="";
	private String strPatCatCode="";
	private String strAdmnStatusCode="";
	private String strPatDeadCode="";
	
	
	private String[] strAdmissionNo = null;
	private String[] strPassNo = null;
	private String[] strPuk = null;
	private String[] strIssueDate = null;
//	private String[] strValidFrom = null;
	private String[] strValidTo = null;
//	private String[] strPassType = null;
	private String[] strPassAmount = null;
	private String[] strPatName = null;
	private String[] strMomName = null;
	private String[] strMomCrNo = null;
	private String[] strAdmDtTime = null;
	private String[] strDeptName = null;
	private String[] strWardName = null;
	private String[] strUnitName = null;
	private String[] strRoomName = null;
	private String[] strIsNewBorn = null;
	private String[] strIsMLC = null;
	private String[] strFSName = null;

	public String[] getStrIsMLC() {
		return strIsMLC;
	}
	public void setStrIsMLC(String[] strIsMLC) {
		this.strIsMLC = strIsMLC;
	}
	public String[] getStrIsNewBorn() {
		return strIsNewBorn;
	}
	public void setStrIsNewBorn(String[] strIsNewBorn) {
		this.strIsNewBorn = strIsNewBorn;
	}
	public String getStrPatStatusCode() {
		return strPatStatusCode;
	}
	public void setStrPatStatusCode(String strPatStatusCode) {
		this.strPatStatusCode = strPatStatusCode;
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
	public String getStrpaidAmount() {
		return strpaidAmount;
	}
	public void setStrpaidAmount(String strpaidAmount) {
		this.strpaidAmount = strpaidAmount;
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
	public String getStrPassSlNo() {
		return strPassSlNo;
	}
	public void setStrPassSlNo(String strPassSlNo) {
		this.strPassSlNo = strPassSlNo;
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
	public String getStrhPassNo() {
		return strhPassNo;
	}
	public void setStrhPassNo(String strhPassNo) {
		this.strhPassNo = strhPassNo;
	}
	public String[] getStrPassType() {
		return strPassType;
	}
	public void setStrPassType(String[] strPassType) {
		this.strPassType = strPassType;
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
	public WebRowSet getPassDetailWS() {
		return passDetailWS;
	}
	public void setPassDetailWS(WebRowSet passDetailWS) {
		this.passDetailWS = passDetailWS;
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
	public String getStrCtDate() { // function for gettin sysdate in date
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
	public WebRowSet getStrPasstype() {
		return strPasstype;
	}
	public void setStrPasstype(WebRowSet strPasstype) {
		this.strPasstype = strPasstype;
	}
	public String getPassType() {
		return passType;
	}
	public void setPassType(String passType) {
		this.passType = passType;
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
	public WebRowSet getNewPassDetails() {
		return newPassDetails;
	}
	public void setNewPassDetails(WebRowSet newPassDetails) {
		this.newPassDetails = newPassDetails;
	}
	public int getStrRemainderFreePass() {
		return strRemainderFreePass;
	}
	public void setStrRemainderFreePass(int strRemainderFreePass) {
		this.strRemainderFreePass = strRemainderFreePass;
	}
	public int getStrRemainderPaidPass() {
		return strRemainderPaidPass;
	}
	public void setStrRemainderPaidPass(int strRemainderPaidPass) {
		this.strRemainderPaidPass = strRemainderPaidPass;
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
	public String[] getStrAdmissionNo() {
		return strAdmissionNo;
	}
	public void setStrAdmissionNo(String[] strAdmissionNo) {
		this.strAdmissionNo = strAdmissionNo;
	}
	public String[] getStrPassNo() {
		return strPassNo;
	}
	public void setStrPassNo(String[] strPassNo) {
		this.strPassNo = strPassNo;
	}
	public String[] getStrIssueDate() {
		return strIssueDate;
	}
	public void setStrIssueDate(String[] strIssueDate) {
		this.strIssueDate = strIssueDate;
	}
	public String[] getStrValidTo() {
		return strValidTo;
	}
	public void setStrValidTo(String[] strValidTo) {
		this.strValidTo = strValidTo;
	}
	public String[] getStrPassAmount() {
		return strPassAmount;
	}
	public void setStrPassAmount(String[] strPassAmount) {
		this.strPassAmount = strPassAmount;
	}
	public String[] getStrPatName() {
		return strPatName;
	}
	public void setStrPatName(String[] strPatName) {
		this.strPatName = strPatName;
	}
	public String[] getStrMomName() {
		return strMomName;
	}
	public void setStrMomName(String[] strMomName) {
		this.strMomName = strMomName;
	}
	public String[] getStrMomCrNo() {
		return strMomCrNo;
	}
	public void setStrMomCrNo(String[] strMomCrNo) {
		this.strMomCrNo = strMomCrNo;
	}
	public String[] getStrAdmDtTime() {
		return strAdmDtTime;
	}
	public void setStrAdmDtTime(String[] strAdmDtTime) {
		this.strAdmDtTime = strAdmDtTime;
	}
	public String[] getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String[] strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String[] getStrWardName() {
		return strWardName;
	}
	public void setStrWardName(String[] strWardName) {
		this.strWardName = strWardName;
	}
	public String[] getStrUnitName() {
		return strUnitName;
	}
	public void setStrUnitName(String[] strUnitName) {
		this.strUnitName = strUnitName;
	}
	public String[] getStrRoomName() {
		return strRoomName;
	}
	public void setStrRoomName(String[] strRoomName) {
		this.strRoomName = strRoomName;
	}
	public String[] getStrPuk() {
		return strPuk;
	}
	public void setStrPuk(String[] strPuk) {
		this.strPuk = strPuk;
	}
	public String[] getStrFSName() {
		return strFSName;
	}
	public void setStrFSName(String[] strFSName) {
		this.strFSName = strFSName;
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
	public String getStrAttendancePassCount() {
		return strAttendancePassCount;
	}
	public void setStrAttendancePassCount(String strAttendancePassCount) {
		this.strAttendancePassCount = strAttendancePassCount;
	}
	
}
