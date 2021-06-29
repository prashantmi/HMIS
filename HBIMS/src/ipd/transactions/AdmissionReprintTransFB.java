package ipd.transactions;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionForm;

public class AdmissionReprintTransFB extends ActionForm {

	private static final long serialVersionUID = 02L;
	private String curDtUtWrRmBd ="";
	private String strAdmDateAndTime ="";
	private String strHiddenPatDtl="";
	private String strIsMLC="";
	private String strMLCNo="";
	private String strIsNewBorn="";
	private String strMotherDtl="";
	private String strPassNo="";
	private String strPassType="";
	private String strIssueDate="";
	private String strValidFrom="";
	private String strValidUpto="";
	private String strPassAmount="";
	private String[] strChkPass=null;
	private String strErrMsgString = "";
	private String strMsgType = "0";
	private String strNormalMsgString="";
	private String strSeatId ="10001";
	private String strHospitalCode ="";
	private String strCrNo ="";
	private String strAdmnNo ="";
	private String strName ="";
	private String strageSex ="";
	private String strPatientCategory="";
	private String strAdmnDate="";
	private String strWard="";
	private String strRoomBed="";
	private String strPatientAdmndtl="";
	private String strRsn="";
	private String strRmk="";
	private WebRowSet strDisAdm =null;
    private WebRowSet strDisBy =null;
    private WebRowSet strDisRsn =null;
    private WebRowSet roomTypeWS=null;
	private WebRowSet roomWs=null;
	private WebRowSet bedTypeWS=null;
	private WebRowSet bedDetailWS=null;
	private WebRowSet strRoomWs =null;
	private String strBedTypeCode="0";
	private String strRoomTypeCode="0";
	private String strBedType="";
	private String strRoomType="";
	private String strBed="";
	private String strRoom="";
	private String strBedCode="0";
	private String strRoomCode="0";
	private String strWardCode="0";
	private String strMsApprovalFlag="";
	private String strWardTypeCode="";
	private String strAdviceAdmNo="";
	private String strMsApprovalStatus="";
	private String strBedProperty="";
	private String changeOfBed="";
	private String strDeptUnitCode="";
	private String strAdmnStatusCode="";
	private String strPatDeadCode="";
	private String strDischargeCancellationTime="0";
	private String curWrdBedCode="";
	private String curDept_Unt_RomCode="";
	private String curAdmAdvNo="";
	private String curAdmNo="";
	private String strCtDt="";
	private String rePrintType="";
	private String strAdmReprintCharge="0";
	private String strPassReprintCharge = "0";
	private String strRePrintCharge = "0";
	private String rePrintCharges = "";
	private String strSelCmbVal = "";
	private String strCase="";
	private String chk="";
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	// Advanced Amount
	private String strIsIntegratedWithBilling="1";
	private String strIsAdvanceAmountAtAdmission="0";
	private String strIsAdvanceAmountAtAdmissionTaken="0";
	private String strAdvanceAmountDate="";
	private String strAdvanceAmountReceiptNo="";
	private String strAdvanceAmount="";

	private String strSaveFlag="";
	private String strPatientCrNo="";
	private String strDischargeTime="";
	private String strPatAdmNo="";
	private String strSaveStatus="0";
	private String DisDateTime="";
	private String DaysStay="";
	

	public String getDisDateTime() {
		return DisDateTime;
	}
	public void setDisDateTime(String disDateTime) {
		DisDateTime = disDateTime;
	}
	public String getDaysStay() {
		return DaysStay;
	}
	public void setDaysStay(String daysStay) {
		DaysStay = daysStay;
	}
	public String getStrSaveStatus() {
		return strSaveStatus;
	}
	public void setStrSaveStatus(String strSaveStatus) {
		this.strSaveStatus = strSaveStatus;
	}
	public String getStrDischargeTime() {
		return strDischargeTime;
	}
	public void setStrDischargeTime(String strDischargeTime) {
		this.strDischargeTime = strDischargeTime;
	}
	public String getStrIsIntegratedWithBilling()
	{
		return strIsIntegratedWithBilling;
	}
	public void setStrIsIntegratedWithBilling(String strIsIntegratedWithBilling)
	{
		this.strIsIntegratedWithBilling = strIsIntegratedWithBilling;
	}
	public String getStrIsAdvanceAmountAtAdmission()
	{
		return strIsAdvanceAmountAtAdmission;
	}
	public void setStrIsAdvanceAmountAtAdmission(String strIsAdvanceAmountAtAdmission)
	{
		this.strIsAdvanceAmountAtAdmission = strIsAdvanceAmountAtAdmission;
	}
	public String getStrIsAdvanceAmountAtAdmissionTaken()
	{
		return strIsAdvanceAmountAtAdmissionTaken;
	}
	public void setStrIsAdvanceAmountAtAdmissionTaken(String strIsAdvanceAmountAtAdmissionTaken)
	{
		this.strIsAdvanceAmountAtAdmissionTaken = strIsAdvanceAmountAtAdmissionTaken;
	}
	public String getStrAdvanceAmountDate()
	{
		return strAdvanceAmountDate;
	}
	public void setStrAdvanceAmountDate(String strAdvanceAmountDate)
	{
		this.strAdvanceAmountDate = strAdvanceAmountDate;
	}
	public String getStrAdvanceAmountReceiptNo()
	{
		return strAdvanceAmountReceiptNo;
	}
	public void setStrAdvanceAmountReceiptNo(String strAdvanceAmountReceiptNo)
	{
		this.strAdvanceAmountReceiptNo = strAdvanceAmountReceiptNo;
	}
	public String getStrAdvanceAmount()
	{
		return strAdvanceAmount;
	}
	public void setStrAdvanceAmount(String strAdvanceAmount)
	{
		this.strAdvanceAmount = strAdvanceAmount;
	}
	public String getCurWrdBedCode() {
		return curWrdBedCode;
	}
	public void setCurWrdBedCode(String curWrdBedCode) {
		this.curWrdBedCode = curWrdBedCode;
	}
	public String getCurDept_Unt_RomCode() {
		return curDept_Unt_RomCode;
	}
	public void setCurDept_Unt_RomCode(String curDept_Unt_RomCode) {
		this.curDept_Unt_RomCode = curDept_Unt_RomCode;
	}
	public String getCurAdmAdvNo() {
		return curAdmAdvNo;
	}
	public void setCurAdmAdvNo(String curAdmAdvNo) {
		this.curAdmAdvNo = curAdmAdvNo;
	}
	public String getCurAdmNo() {
		return curAdmNo;
	}
	public void setCurAdmNo(String curAdmNo) {
		this.curAdmNo = curAdmNo;
	}
	public String getStrDischargeCancellationTime() {
		return strDischargeCancellationTime;
	}
	public void setStrDischargeCancellationTime(String strDischargeCancellationTime) {
		this.strDischargeCancellationTime = strDischargeCancellationTime;
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
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}
	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}
	public String getChangeOfBed() {
		return changeOfBed;
	}
	public void setChangeOfBed(String changeOfBed) {
		this.changeOfBed = changeOfBed;
	}
               
	public String getStrBedProperty() {
		return strBedProperty;
	}
	public void setStrBedProperty(String strBedProperty) {
		this.strBedProperty = strBedProperty;
	}
	public String getStrMsApprovalStatus() {
		return strMsApprovalStatus;
	}
	public void setStrMsApprovalStatus(String strMsApprovalStatus) {
		this.strMsApprovalStatus = strMsApprovalStatus;
	}
	public String getStrMsApprovalFlag() {
		return strMsApprovalFlag;
	}
	public void setStrMsApprovalFlag(String strMsApprovalFlag) {
		this.strMsApprovalFlag = strMsApprovalFlag;
	}
	public String getStrWardTypeCode() {
		return strWardTypeCode;
	}
	public void setStrWardTypeCode(String strWardTypeCode) {
		this.strWardTypeCode = strWardTypeCode;
	}
	public String getStrAdviceAdmNo() {
		return strAdviceAdmNo;
	}
	public void setStrAdviceAdmNo(String strAdviceAdmNo) {
		this.strAdviceAdmNo = strAdviceAdmNo;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	public String getStrBedType() {
		return strBedType;
	}
	public void setStrBedType(String strBedType) {
		this.strBedType = strBedType;
	}
	public String getStrRoomType() {
		return strRoomType;
	}
	public void setStrRoomType(String strRoomType) {
		this.strRoomType = strRoomType;
	}
	public String getStrBed() {
		return strBed;
	}
	public void setStrBed(String strBed) {
		this.strBed = strBed;
	}
	public String getStrRoom() {
		return strRoom;
	}
	public void setStrRoom(String strRoom) {
		this.strRoom = strRoom;
	}
	public String getStrBedCode() {
		return strBedCode;
	}
	public void setStrBedCode(String strBedCode) {
		this.strBedCode = strBedCode;
	}
	public String getStrRoomCode() {
		return strRoomCode;
	}
	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}
	public String getStrBedTypeCode() {
		return strBedTypeCode;
	}
	public void setStrBedTypeCode(String strBedTypeCode) {
		this.strBedTypeCode = strBedTypeCode;
	}
	public String getStrRoomTypeCode() {
		return strRoomTypeCode;
	}
	public void setStrRoomTypeCode(String strRoomTypeCode) {
		this.strRoomTypeCode = strRoomTypeCode;
	}
	public String getStrRsn() {
		return strRsn;
	}
	public void setStrRsn(String strRsn) {
		this.strRsn = strRsn;
	}
	public String getStrRmk() {
		return strRmk;
	}
	public void setStrRmk(String strRmk) {
		this.strRmk = strRmk;
	}
	public WebRowSet getStrDisBy() {
		return strDisBy;
	}
	public void setStrDisBy(WebRowSet strDisBy) {
		this.strDisBy = strDisBy;
	}
	public WebRowSet getStrDisRsn() {
		return strDisRsn;
	}
	public void setStrDisRsn(WebRowSet strDisRsn) {
		this.strDisRsn = strDisRsn;
	}
	public String getStrPatientAdmndtl() {
		return strPatientAdmndtl;
	}
	public void setStrPatientAdmndtl(String strPatientAdmndtl) {
		this.strPatientAdmndtl = strPatientAdmndtl;
	}

	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
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
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrAdmnNo() {
		return strAdmnNo;
	}
	public void setStrAdmnNo(String strAdmnNo) {
		this.strAdmnNo = strAdmnNo;
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
	public WebRowSet getRoomTypeWS() {
		return roomTypeWS;
	}
	public void setRoomTypeWS(WebRowSet roomTypeWS) {
		this.roomTypeWS = roomTypeWS;
	}
	public WebRowSet getRoomWs() {
		return roomWs;
	}
	public void setRoomWs(WebRowSet roomWs) {
		this.roomWs = roomWs;
	}
	public WebRowSet getBedTypeWS() {
		return bedTypeWS;
	}
	public void setBedTypeWS(WebRowSet bedTypeWS) {
		this.bedTypeWS = bedTypeWS;
	}
	public WebRowSet getBedDetailWS() {
		return bedDetailWS;
	}
	public void setBedDetailWS(WebRowSet bedDetailWS) {
		this.bedDetailWS = bedDetailWS;
	}
	public WebRowSet getStrRoomWs() {
		return strRoomWs;
	}
	public void setStrRoomWs(WebRowSet strRoomWs) {
		this.strRoomWs = strRoomWs;
	}
	public String getStrErrMsgString() {
		return strErrMsgString;
	}
	public void setStrErrMsgString(String strErrMsgString) {
		this.strErrMsgString = strErrMsgString;
	}
	public String getStrNormalMsgString() {
		return strNormalMsgString;
	}
	public void setStrNormalMsgString(String strNormalMsgString) {
		this.strNormalMsgString = strNormalMsgString;
	}
	public WebRowSet getStrDisAdm() {
		return strDisAdm;
	}
	public void setStrDisAdm(WebRowSet strDisAdm) {
		this.strDisAdm = strDisAdm;
	}
	public String getStrCtDt() {
		return strCtDt;
	}
	public void setStrCtDt(String strCtDt) {
		this.strCtDt = strCtDt;
	}
	public String getRePrintType() {
		return rePrintType;
	}
	public void setRePrintType(String rePrintType) {
		this.rePrintType = rePrintType;
	}
	public String getCurDtUtWrRmBd() {
		return curDtUtWrRmBd;
	}
	public void setCurDtUtWrRmBd(String curDtUtWrRmBd) {
		this.curDtUtWrRmBd = curDtUtWrRmBd;
	}
	public String getStrAdmDateAndTime() {
		return strAdmDateAndTime;
	}
	public void setStrAdmDateAndTime(String strAdmDateAndTime) {
		this.strAdmDateAndTime = strAdmDateAndTime;
	}
	public String getStrHiddenPatDtl() {
		return strHiddenPatDtl;
	}
	public void setStrHiddenPatDtl(String strHiddenPatDtl) {
		this.strHiddenPatDtl = strHiddenPatDtl;
	}
	public String getStrIsMLC() {
		return strIsMLC;
	}
	public void setStrIsMLC(String strIsMLC) {
		this.strIsMLC = strIsMLC;
	}
	public String getStrMLCNo() {
		return strMLCNo;
	}
	public void setStrMLCNo(String strMLCNo) {
		this.strMLCNo = strMLCNo;
	}
	public String getStrIsNewBorn() {
		return strIsNewBorn;
	}
	public void setStrIsNewBorn(String strIsNewBorn) {
		this.strIsNewBorn = strIsNewBorn;
	}
	public String getStrMotherDtl() {
		return strMotherDtl;
	}
	public void setStrMotherDtl(String strMotherDtl) {
		this.strMotherDtl = strMotherDtl;
	}
	public String getStrPassNo() {
		return strPassNo;
	}
	public void setStrPassNo(String strPassNo) {
		this.strPassNo = strPassNo;
	}
	public String getStrPassType() {
		return strPassType;
	}
	public void setStrPassType(String strPassType) {
		this.strPassType = strPassType;
	}
	public String getStrIssueDate() {
		return strIssueDate;
	}
	public void setStrIssueDate(String strIssueDate) {
		this.strIssueDate = strIssueDate;
	}
	public String getStrValidFrom() {
		return strValidFrom;
	}
	public void setStrValidFrom(String strValidFrom) {
		this.strValidFrom = strValidFrom;
	}
	public String getStrValidUpto() {
		return strValidUpto;
	}
	public void setStrValidUpto(String strValidUpto) {
		this.strValidUpto = strValidUpto;
	}
	public String getStrPassAmount() {
		return strPassAmount;
	}
	public void setStrPassAmount(String strPassAmount) {
		this.strPassAmount = strPassAmount;
	}
	public String[] getStrChkPass() {
		return strChkPass;
	}
	public void setStrChkPass(String[] strChkPass) {
		this.strChkPass = strChkPass;
	}
	public String getStrAdmReprintCharge() {
		return strAdmReprintCharge;
	}
	public void setStrAdmReprintCharge(String strAdmReprintCharge) {
		this.strAdmReprintCharge = strAdmReprintCharge;
	}
	public String getStrPassReprintCharge() {
		return strPassReprintCharge;
	}
	public void setStrPassReprintCharge(String strPassReprintCharge) {
		this.strPassReprintCharge = strPassReprintCharge;
	}
	public String getStrRePrintCharge() {
		return strRePrintCharge;
	}
	public void setStrRePrintCharge(String strRePrintCharge) {
		this.strRePrintCharge = strRePrintCharge;
	}
	public String getRePrintCharges() {
		return rePrintCharges;
	}
	public void setRePrintCharges(String rePrintCharges) {
		this.rePrintCharges = rePrintCharges;
	}
	public String getStrSelCmbVal() {
		return strSelCmbVal;
	}
	public void setStrSelCmbVal(String strSelCmbVal) {
		this.strSelCmbVal = strSelCmbVal;
	}
	public String getStrSaveFlag() {
		return strSaveFlag;
	}
	public void setStrSaveFlag(String strSaveFlag) {
		this.strSaveFlag = strSaveFlag;
	}
	public String getStrPatientCrNo() {
		return strPatientCrNo;
	}
	public void setStrPatientCrNo(String strPatientCrNo) {
		this.strPatientCrNo = strPatientCrNo;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrPatAdmNo() {
		return strPatAdmNo;
	}
	public void setStrPatAdmNo(String strPatAdmNo) {
		this.strPatAdmNo = strPatAdmNo;
	}
}