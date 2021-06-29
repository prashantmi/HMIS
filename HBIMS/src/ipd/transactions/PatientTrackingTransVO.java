package ipd.transactions;

import javax.sql.rowset.WebRowSet;

import hisglobal.utility.TransferObject;

public class PatientTrackingTransVO implements TransferObject {
	
	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "0";
	private String strNormalMsg="";
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
	private String strPatientMovementDtl="";
	private String strRsn="";
	private String strRmk="";
	private String strCancelNo = "";
	private String strPhoneno="";
	
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
	private String strRoom="";
	private String strBedCode="0";
	private String strRoomCode="0";
	private String strWardCode="0";
	private String strDeptCode="";
	private String strMsApprovalFlag="";
	private String strWardTypeCode="";
	private String strAdviceAdmNo="";
	private String strMsApprovalStatus="";
	private String strPopUp="";
	private String changeOfBed=""; 
	private String strErrMsgString=""; 
	private String strNormalMsgString="";
	private String strDeptUnitCode="";
	private String strAdmnStatusCode="";
	private String strPatDeadCode="";
	private String strDischargeTime="0";
	private String strInvalidAdmNo="0";
	private String strBillIntegration = "";
	
	private String strUnitCode="";
	private String strUnitValue="";
	private String strAge="";
	private String strSex="";
	private String strTreatmentCategoryCode="";
	
	private String deadStatus="";
	
	private String strDeptName="";
	private String strWardName="";
	private String strBedName="";
	private String strInDateTime=""; 
	private String strOutDateTime="";
	private String strInStatus="";
	private String strOutStatus="";
	private WebRowSet movementDetailsWS=null;
	private WebRowSet admissionListWS=null;
	
	
	public String getDeadStatus() {
		return deadStatus;
	}
	public void setDeadStatus(String deadStatus) {
		this.deadStatus = deadStatus;
	}
	/**
	 * @return the strUnitCode
	 */
	public String getStrUnitCode() {
		return strUnitCode;
	}
	/**
	 * @param strUnitCode the strUnitCode to set
	 */
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	/**
	 * @return the strUnitValue
	 */
	public String getStrUnitValue() {
		return strUnitValue;
	}
	/**
	 * @param strUnitValue the strUnitValue to set
	 */
	public void setStrUnitValue(String strUnitValue) {
		this.strUnitValue = strUnitValue;
	}
	/**
	 * @return the strAge
	 */
	public String getStrAge() {
		return strAge;
	}
	/**
	 * @param strAge the strAge to set
	 */
	public void setStrAge(String strAge) {
		this.strAge = strAge;
	}
	/**
	 * @return the strSex
	 */
	public String getStrSex() {
		return strSex;
	}
	/**
	 * @param strSex the strSex to set
	 */
	public void setStrSex(String strSex) {
		this.strSex = strSex;
	}
	/**
	 * @return the strTreatmentCategoryCode
	 */
	public String getStrTreatmentCategoryCode() {
		return strTreatmentCategoryCode;
	}
	/**
	 * @param strTreatmentCategoryCode the strTreatmentCategoryCode to set
	 */
	public void setStrTreatmentCategoryCode(String strTreatmentCategoryCode) {
		this.strTreatmentCategoryCode = strTreatmentCategoryCode;
	}
	public String getStrBillIntegration() {
		return strBillIntegration;
	}
	public void setStrBillIntegration(String strBillIntegration) {
		this.strBillIntegration = strBillIntegration;
	}	
	public String getStrDischargeTime() {
		return strDischargeTime;
	}
	public void setStrDischargeTime(String strDischargeTime) {
		this.strDischargeTime = strDischargeTime;
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
	public String getStrNormalMsgString() {
		return strNormalMsgString;
	}
	public void setStrNormalMsgString(String strNormalMsgString) {
		this.strNormalMsgString = strNormalMsgString;
	}
	public String getChangeOfBed() {
		return changeOfBed;
	}
	public void setChangeOfBed(String changeOfBed) {
		this.changeOfBed = changeOfBed;
	}
	public String getStrPopUp() {
		return strPopUp;
	}
	public void setStrPopUp(String strPopUp) {
		this.strPopUp = strPopUp;
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
	public WebRowSet getStrDisBy() {
		return strDisBy;
	}
	public WebRowSet getStrDisRsn() {
		return strDisRsn;
	}
	public String getStrAdmnDate() {
		return strAdmnDate;
	}
	public void setStrAdmnDate(String strAdmnDate) {
		this.strAdmnDate = strAdmnDate;
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
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
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
	public void setStrDisBy(WebRowSet strDisBy) {
		this.strDisBy = strDisBy;
	}
	public void setStrDisRsn(WebRowSet strDisRsn) {
		this.strDisRsn = strDisRsn;
	}
	public String getStrMsApprovalStatus() {
		return strMsApprovalStatus;
	}
	public void setStrMsApprovalStatus(String strMsApprovalStatus) {
		this.strMsApprovalStatus = strMsApprovalStatus;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrWardName() {
		return strWardName;
	}
	public void setStrWardName(String strWardName) {
		this.strWardName = strWardName;
	}
	public String getStrErrMsgString() {
		return strErrMsgString;
	}
	public void setStrErrMsgString(String strErrMsgString) {
		this.strErrMsgString = strErrMsgString;
	}
	public WebRowSet getStrDisAdm() {
		return strDisAdm;
	}
	public void setStrDisAdm(WebRowSet strDisAdm) {
		this.strDisAdm = strDisAdm;
	}
	public String getStrCancelNo() {
		return strCancelNo;
	}
	public void setStrCancelNo(String strCancelNo) {
		this.strCancelNo = strCancelNo;
	}
	public String getStrInvalidAdmNo() {
		return strInvalidAdmNo;
	}
	public void setStrInvalidAdmNo(String strInvalidAdmNo) {
		this.strInvalidAdmNo = strInvalidAdmNo;
	}
	public String getStrPatientMovementDtl() {
		return strPatientMovementDtl;
	}
	public void setStrPatientMovementDtl(String strPatientMovementDtl) {
		this.strPatientMovementDtl = strPatientMovementDtl;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrBedName() {
		return strBedName;
	}
	public void setStrBedName(String strBedName) {
		this.strBedName = strBedName;
	}
	public String getStrInDateTime() {
		return strInDateTime;
	}
	public void setStrInDateTime(String strInDateTime) {
		this.strInDateTime = strInDateTime;
	}
	public String getStrOutDateTime() {
		return strOutDateTime;
	}
	public void setStrOutDateTime(String strOutDateTime) {
		this.strOutDateTime = strOutDateTime;
	}
	public String getStrInStatus() {
		return strInStatus;
	}
	public void setStrInStatus(String strInStatus) {
		this.strInStatus = strInStatus;
	}
	public String getStrOutStatus() {
		return strOutStatus;
	}
	public void setStrOutStatus(String strOutStatus) {
		this.strOutStatus = strOutStatus;
	}
	public WebRowSet getMovementDetailsWS() {
		return movementDetailsWS;
	}
	public void setMovementDetailsWS(WebRowSet movementDetailsWS) {
		this.movementDetailsWS = movementDetailsWS;
	}
	public WebRowSet getAdmissionListWS() {
		return admissionListWS;
	}
	public void setAdmissionListWS(WebRowSet admissionListWS) {
		this.admissionListWS = admissionListWS;
	}
	public String getStrPhoneno() {
		return strPhoneno;
	}
	public void setStrPhoneno(String strPhoneno) {
		this.strPhoneno = strPhoneno;
	}	
}