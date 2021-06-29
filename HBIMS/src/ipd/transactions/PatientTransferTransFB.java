package ipd.transactions;

import javax.sql.rowset.WebRowSet;
import org.apache.struts.action.ActionForm;

public class PatientTransferTransFB extends ActionForm 
{
    private static final long serialVersionUID = 02L;
	private WebRowSet strPatientDetailsWs = null;
	private WebRowSet strOnLineReqDiscountWs = null;
	private String strMsgString = null;
    private String strErrMsgString = null;
    private String strNormalMsgString = null;
    private String strWarningMsgString = null;
    private String strMsgType = "";   
    private String strChk ="";
    private String strCrNo = "";
    private String strCrNo1 = "";
    private String strBId ="";
    private String strMSBed ="";
    private String strApproval_id="";
    private String strExpnseAmt ="";
    private String strRecFrClnt ="";
    private String lastDis ="";
    private String discountType ="";
    private String strDisAmt ="";
    private String strRecFrPat ="";
    private String strNetAmt ="";
    private String strDetls="";
    private WebRowSet strDisBy =null;
    private WebRowSet strDisRsn =null;
    private String strRsn="";
    private String strRmk="";
    private String strAccPreDis ="";
    private String strwardType="";
	private String strWard="";
	private String strRoom="";
	private String strErrorCode="";
	private String strRoomType="";
	private String strWardTypeCode="";
	private String strWardCode="";
	private String strBedTypeCode="";
	private String strRoomTypeCode="";
	private String strWardName="";
	private String strDeptCode="";
	private String strBedType="";
	private String strBed="";
	private String strBedProperty="";
	private String strCtDate="";
	private String strValidFromDp="";
	private String strValidFromDp1="";
	private String strTreatmentCategoryName="";
	private String strTreatmentCategoryCode="";
	private String strDeptUnitCode="";
	private String strConsultantCode="";
	private String strConsultantName="";
	private String strEpisodeCode="";
	private String strMlcNo="";
	private String strAdmNo="";
	private String strAdviceAdmNo="";
	private String strRoomCode="";
	private String strBedCode="";
	private String strServArea="";
	private String strDepartmentName="";
	private String strVacantBed="";
	private String strMsApprovalFlag="";
	private String strMsg="";
    private String strDepartment ="";     //combo name 
	private String strUnit ="";            //combo name   
	private String strTransferTypeValues="";
	private WebRowSet  strDepartmenttWs =null;   
	private WebRowSet  strUnitWs =null;  
	private WebRowSet  strWardWs =null;  
	private WebRowSet  strRoomWs =null;  
	private String strdeptproperty  ="";
	private String[] strhward =null;
	private String strValidFrom="";
	private String strValidTo="";
	private String strPatCondL="";
	private String strDaysOnLeave="";
	private String strAdviceL="";
	private String strwardproperty  ="";
	private String strunitproperty  ="";
	private String strroomproperty  ="";
	private String strSeatId="";
	private String strHospCode="";
	private String strInsert="";
	private String strLocation="";
	private String strIsDead="";
	private String strAdmStatCode ="";
	private String strPrevDblOcc ="0";
	private String strHoldRoom ="0";
	private String strIsBedVacant  ="1";
	private String curDept_Unt_RomCode ="";
	private String strMlc = "";
	private String strPvtWardCode="";
	private String strIpdConfIcuWard="";
	private String strHospChange="";
	private String strTime="";
	private String strHospCombo="";
	private String strTransferTypeBelonging = "";
	private String strTransferTypeIssue = "";
	private String curAdmNo = "";
	private String strIssuedItemRequired = "";
	private String strBelongingRequired = "";
	
	private String strCurrentDeptUnitRoom="";
	
	
	private String strServiceTypeId="";
	private String strBedVacantMode="";
	private String strServiceType="";
	private String strServiceName="";
	private String strValidatedFlag="";
	
	private String strTransferUnit="";
	private String sharableChk="0";
	private String strConsultant="";
	
	
	
	
	
    
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStrServiceName() {
		return strServiceName;
	}

	public void setStrServiceName(String strServiceName) {
		this.strServiceName = strServiceName;
	}

	public String getStrBedVacantMode() {
		return strBedVacantMode;
	}

	public void setStrBedVacantMode(String strBedVacantMode) {
		this.strBedVacantMode = strBedVacantMode;
	}

	public String getStrServiceTypeId() {
		return strServiceTypeId;
	}

	public void setStrServiceTypeId(String strServiceTypeId) {
		this.strServiceTypeId = strServiceTypeId;
	}

	public String getStrCurrentDeptUnitRoom() {
		return strCurrentDeptUnitRoom;
	}

	public void setStrCurrentDeptUnitRoom(String strCurrentDeptUnitRoom) {
		this.strCurrentDeptUnitRoom = strCurrentDeptUnitRoom;
	}
    /**
	 * @return the strIssuedItemRequired
	 */
	public String getStrIssuedItemRequired() {
		return strIssuedItemRequired;
	}

	/**
	 * @param strIssuedItemRequired the strIssuedItemRequired to set
	 */
	public void setStrIssuedItemRequired(String strIssuedItemRequired) {
		this.strIssuedItemRequired = strIssuedItemRequired;
	}

	/**
	 * @return the curAdmNo
	 */
	public String getCurAdmNo() {
		return curAdmNo;
	}

	/**
	 * @param curAdmNo the curAdmNo to set
	 */
	public void setCurAdmNo(String curAdmNo) {
		this.curAdmNo = curAdmNo;
	}

	/**
	 * @return the strTransferTypeBelonging
	 */
	public String getStrTransferTypeBelonging() {
		return strTransferTypeBelonging;
	}

	/**
	 * @param strTransferTypeBelonging the strTransferTypeBelonging to set
	 */
	public void setStrTransferTypeBelonging(String strTransferTypeBelonging) {
		this.strTransferTypeBelonging = strTransferTypeBelonging;
	}

	/**
	 * @return the strTransferTypeIssue
	 */
	public String getStrTransferTypeIssue() {
		return strTransferTypeIssue;
	}

	/**
	 * @param strTransferTypeIssue the strTransferTypeIssue to set
	 */
	public void setStrTransferTypeIssue(String strTransferTypeIssue) {
		this.strTransferTypeIssue = strTransferTypeIssue;
	}

	/**
	 * @return the strHospCombo
	 */
	public String getStrHospCombo() {
		return strHospCombo;
	}

	/**
	 * @param strHospCombo the strHospCombo to set
	 */
	public void setStrHospCombo(String strHospCombo) {
		this.strHospCombo = strHospCombo;
	}

	public String getStrHospChange() {
		return strHospChange;
	}

	public void setStrHospChange(String strHospChange) {
		this.strHospChange = strHospChange;
	}

	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	/**
	 * @return the strIpdConfIcuWard
	 */
	public String getStrIpdConfIcuWard() {
		return strIpdConfIcuWard;
	}

	/**
	 * @param strIpdConfIcuWard the strIpdConfIcuWard to set
	 */
	public void setStrIpdConfIcuWard(String strIpdConfIcuWard) {
		this.strIpdConfIcuWard = strIpdConfIcuWard;
	}

	/**
	 * @return the strPvtWardCode
	 */
	public String getStrPvtWardCode() {
		return strPvtWardCode;
	}

	/**
	 * @param strPvtWardCode the strPvtWardCode to set
	 */
	public void setStrPvtWardCode(String strPvtWardCode) {
		this.strPvtWardCode = strPvtWardCode;
	}

	public String getStrMlc() {
		return strMlc;
	}

	public void setStrMlc(String strMlc) {
		this.strMlc = strMlc;
	}

	public String getStrIsBedVacant() {
		return strIsBedVacant;
	}

	public void setStrIsBedVacant(String strIsBedVacant) {
		this.strIsBedVacant = strIsBedVacant;
	}

	public String getStrIsDead() {
		return strIsDead;
	}

	public void setStrIsDead(String strIsDead) {
		this.strIsDead = strIsDead;
	}

	public String getStrAdmStatCode() {
		return strAdmStatCode;
	}

	public void setStrAdmStatCode(String strAdmStatCode) {
		this.strAdmStatCode = strAdmStatCode;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrHospCode() {
		return strHospCode;
	}

	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}

	public String getStrdeptproperty() {
		return strdeptproperty;
	}

	public void setStrdeptproperty(String strdeptproperty) {
		this.strdeptproperty = strdeptproperty;
	}

	public String[] getStrhward() {
		return strhward;
	}

	public void setStrhward(String[] strhward) {
		this.strhward = strhward;
	}

	public String getStrwardproperty() {
		return strwardproperty;
	}

	public void setStrwardproperty(String strwardproperty) {
		this.strwardproperty = strwardproperty;
	}

	public String getStrunitproperty() {
		return strunitproperty;
	}

	public void setStrunitproperty(String strunitproperty) {
		this.strunitproperty = strunitproperty;
	}

	public String getStrroomproperty() {
		return strroomproperty;
	}

	public void setStrroomproperty(String strroomproperty) {
		this.strroomproperty = strroomproperty;
	}

	public WebRowSet getStrDepartmenttWs() {
		return strDepartmenttWs;
	}

	public void setStrDepartmenttWs(WebRowSet strDepartmenttWs) {
		this.strDepartmenttWs = strDepartmenttWs;
	}

	public WebRowSet getStrUnitWs() {
		return strUnitWs;
	}

	public void setStrUnitWs(WebRowSet strUnitWs) {
		this.strUnitWs = strUnitWs;
	}

	public WebRowSet getStrWardWs() {
		return strWardWs;
	}

	public void setStrWardWs(WebRowSet strWardWs) {
		this.strWardWs = strWardWs;
	}

	public WebRowSet getStrRoomWs() {
		return strRoomWs;
	}

	public void setStrRoomWs(WebRowSet strRoomWs) {
		this.strRoomWs = strRoomWs;
	}

	public String getStrDepartment() {
		return strDepartment;
	}

	public void setStrDepartment(String strDepartment) {
		this.strDepartment = strDepartment;
	}

	public String getStrUnit() {
		return strUnit;
	}

	public void setStrUnit(String strUnit) {
		this.strUnit = strUnit;
	}

	public String getStrWard() {
		return strWard;
	}

	public void setStrWard(String strWard) {
		this.strWard = strWard;
	}

	public String getStrRoom() {
		return strRoom;
	}

	public void setStrRoom(String strRoom) {
		this.strRoom = strRoom;
	}

	public String getStrBId() {
		return strBId;
	}

	public void setStrBId(String strBId) {
		this.strBId = strBId;
	}

	public String getStrExpnseAmt() {
		return strExpnseAmt;
	}

	public void setStrExpnseAmt(String strExpnseAmt) {
		this.strExpnseAmt = strExpnseAmt;
	}

	public String getStrRecFrClnt() {
		return strRecFrClnt;
	}

	public void setStrRecFrClnt(String strRecFrClnt) {
		this.strRecFrClnt = strRecFrClnt;
	}

	

	public String getStrDisAmt() {
		return strDisAmt;
	}

	public void setStrDisAmt(String strDisAmt) {
		this.strDisAmt = strDisAmt;
	}

	public String getStrRecFrPat() {
		return strRecFrPat;
	}

	public void setStrRecFrPat(String strRecFrPat) {
		this.strRecFrPat = strRecFrPat;
	}

	public String getStrNetAmt() {
		return strNetAmt;
	}

	public void setStrNetAmt(String strNetAmt) {
		this.strNetAmt = strNetAmt;
	}

	public String getStrDetls() {
		return strDetls;
	}

	public void setStrDetls(String strDetls) {
		this.strDetls = strDetls;
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

	public String getStrAccPreDis() {
		return strAccPreDis;
	}

	public void setStrAccPreDis(String strAccPreDis) {
		this.strAccPreDis = strAccPreDis;
	}

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}

	public String getStrChk() {
		return strChk;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public WebRowSet getStrPatientDetailsWs() {
		return strPatientDetailsWs;
	}

	public void setStrPatientDetailsWs(WebRowSet strPatientDetailsWs) {
		this.strPatientDetailsWs = strPatientDetailsWs;
	}

	public WebRowSet getStrOnLineReqDiscountWs() {
		return strOnLineReqDiscountWs;
	}

	public void setStrOnLineReqDiscountWs(WebRowSet strOnLineReqDiscountWs) {
		this.strOnLineReqDiscountWs = strOnLineReqDiscountWs;
	}

	public String getStrCrNo1() {
		return strCrNo1;
	}

	public void setStrCrNo1(String strCrNo1) {
		this.strCrNo1 = strCrNo1;
	}

	public String getStrApproval_id() {
		return strApproval_id;
	}

	public void setStrApproval_id(String strApproval_id) {
		this.strApproval_id = strApproval_id;
	}

	public String getLastDis() {
		return lastDis;
	}

	public void setLastDis(String lastDis) {
		this.lastDis = lastDis;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getStrwardType() {
		return strwardType;
	}

	public void setStrwardType(String strwardType) {
		this.strwardType = strwardType;
	}

	public String getStrRoomType() {
		return strRoomType;
	}

	public void setStrRoomType(String strRoomType) {
		this.strRoomType = strRoomType;
	}

	public String getStrWardTypeCode() {
		return strWardTypeCode;
	}

	public void setStrWardTypeCode(String strWardTypeCode) {
		this.strWardTypeCode = strWardTypeCode;
	}

	public String getStrWardCode() {
		return strWardCode;
	}

	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
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

	public String getStrWardName() {
		return strWardName;
	}

	public void setStrWardName(String strWardName) {
		this.strWardName = strWardName;
	}

	public String getStrDeptCode() {
		return strDeptCode;
	}

	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}

	public String getStrBedType() {
		return strBedType;
	}

	public void setStrBedType(String strBedType) {
		this.strBedType = strBedType;
	}

	public String getStrTreatmentCategoryName() {
		return strTreatmentCategoryName;
	}

	public void setStrTreatmentCategoryName(String strTreatmentCategoryName) {
		this.strTreatmentCategoryName = strTreatmentCategoryName;
	}

	public String getStrTreatmentCategoryCode() {
		return strTreatmentCategoryCode;
	}

	public void setStrTreatmentCategoryCode(String strTreatmentCategoryCode) {
		this.strTreatmentCategoryCode = strTreatmentCategoryCode;
	}

	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}

	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}

	public String getStrConsultantCode() {
		return strConsultantCode;
	}

	public void setStrConsultantCode(String strConsultantCode) {
		this.strConsultantCode = strConsultantCode;
	}

	public String getStrConsultantName() {
		return strConsultantName;
	}

	public void setStrConsultantName(String strConsultantName) {
		this.strConsultantName = strConsultantName;
	}

	public String getStrEpisodeCode() {
		return strEpisodeCode;
	}

	public void setStrEpisodeCode(String strEpisodeCode) {
		this.strEpisodeCode = strEpisodeCode;
	}

	public String getStrMlcNo() {
		return strMlcNo;
	}

	public void setStrMlcNo(String strMlcNo) {
		this.strMlcNo = strMlcNo;
	}

	public String getStrAdmNo() {
		return strAdmNo;
	}

	public void setStrAdmNo(String strAdmNo) {
		this.strAdmNo = strAdmNo;
	}

	public String getStrAdviceAdmNo() {
		return strAdviceAdmNo;
	}

	public void setStrAdviceAdmNo(String strAdviceAdmNo) {
		this.strAdviceAdmNo = strAdviceAdmNo;
	}

	public String getStrRoomCode() {
		return strRoomCode;
	}

	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}

	public String getStrBedCode() {
		return strBedCode;
	}

	public void setStrBedCode(String strBedCode) {
		this.strBedCode = strBedCode;
	}

	public String getStrDepartmentName() {
		return strDepartmentName;
	}

	public void setStrDepartmentName(String strDepartmentName) {
		this.strDepartmentName = strDepartmentName;
	}

	public String getStrVacantBed() {
		return strVacantBed;
	}

	public void setStrVacantBed(String strVacantBed) {
		this.strVacantBed = strVacantBed;
	}

	public String getStrMsApprovalFlag() {
		return strMsApprovalFlag;
	}

	public void setStrMsApprovalFlag(String strMsApprovalFlag) {
		this.strMsApprovalFlag = strMsApprovalFlag;
	}

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	public String getStrBed() {
		return strBed;
	}

	public void setStrBed(String strBed) {
		this.strBed = strBed;
	}

	public String getStrBedProperty() {
		return strBedProperty;
	}

	public void setStrBedProperty(String strBedProperty) {
		this.strBedProperty = strBedProperty;
	}

	public String getStrServArea() {
		return strServArea;
	}

	public void setStrServArea(String strServArea) {
		this.strServArea = strServArea;
	}

	public String getStrCtDate() {
		return strCtDate;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public String getStrValidFromDp() {
		return strValidFromDp;
	}

	public void setStrValidFromDp(String strValidFromDp) {
		this.strValidFromDp = strValidFromDp;
	}

	public String getStrValidFromDp1() {
		return strValidFromDp1;
	}

	public void setStrValidFromDp1(String strValidFromDp1) {
		this.strValidFromDp1 = strValidFromDp1;
	}

	public String getStrValidFrom() {
		return strValidFrom;
	}

	public void setStrValidFrom(String strValidFrom) {
		this.strValidFrom = strValidFrom;
	}

	public String getStrValidTo() {
		return strValidTo;
	}

	public void setStrValidTo(String strValidTo) {
		this.strValidTo = strValidTo;
	}

	public String getStrPatCondL() {
		return strPatCondL;
	}

	public void setStrPatCondL(String strPatCondL) {
		this.strPatCondL = strPatCondL;
	}

	public String getStrAdviceL() {
		return strAdviceL;
	}

	public void setStrAdviceL(String strAdviceL) {
		this.strAdviceL = strAdviceL;
	}

	public String getStrDaysOnLeave() {
		return strDaysOnLeave;
	}

	public void setStrDaysOnLeave(String strDaysOnLeave) {
		this.strDaysOnLeave = strDaysOnLeave;
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

	public String getStrWarningMsgString() {
		return strWarningMsgString;
	}

	public void setStrWarningMsgString(String strWarningMsgString) {
		this.strWarningMsgString = strWarningMsgString;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrInsert() {
		return strInsert;
	}

	public void setStrInsert(String strInsert) {
		this.strInsert = strInsert;
	}

	public String getStrLocation() {
		return strLocation;
	}

	public void setStrLocation(String strLocation) {
		this.strLocation = strLocation;
	}

	public String getStrPrevDblOcc() {
		return strPrevDblOcc;
	}

	public void setStrPrevDblOcc(String strPrevDblOcc) {
		this.strPrevDblOcc = strPrevDblOcc;
	}

	public String getStrHoldRoom() {
		return strHoldRoom;
	}

	public void setStrHoldRoom(String strHoldRoom) {
		this.strHoldRoom = strHoldRoom;
	}

	public String getCurDept_Unt_RomCode() {
		return curDept_Unt_RomCode;
	}

	public void setCurDept_Unt_RomCode(String curDept_Unt_RomCode) {
		this.curDept_Unt_RomCode = curDept_Unt_RomCode;
	}

	public String getStrErrorCode() {
		return strErrorCode;
	}

	public void setStrErrorCode(String strErrorCode) {
		this.strErrorCode = strErrorCode;
	}

	public String getStrMSBed() {
		return strMSBed;
	}

	public void setStrMSBed(String strMSBed) {
		this.strMSBed = strMSBed;
	}

	public String getStrTransferTypeValues() {
		return strTransferTypeValues;
	}

	public void setStrTransferTypeValues(String strTransferTypeValues) {
		this.strTransferTypeValues = strTransferTypeValues;
	}

	public String getStrBelongingRequired() {
		return strBelongingRequired;
	}

	public void setStrBelongingRequired(String strBelongingRequired) {
		this.strBelongingRequired = strBelongingRequired;
	}

	public String getStrServiceType() {
		return strServiceType;
	}

	public void setStrServiceType(String strServiceType) {
		this.strServiceType = strServiceType;
	}

	public String getStrValidatedFlag() {
		return strValidatedFlag;
	}

	public void setStrValidatedFlag(String strValidatedFlag) {
		this.strValidatedFlag = strValidatedFlag;
	}

	public String getStrTransferUnit() {
		return strTransferUnit;
	}

	public void setStrTransferUnit(String strTransferUnit) {
		this.strTransferUnit = strTransferUnit;
	}

	public String getSharableChk() {
		return sharableChk;
	}

	public void setSharableChk(String sharableChk) {
		this.sharableChk = sharableChk;
	}

	public String getStrConsultant() {
		return strConsultant;
	}

	public void setStrConsultant(String strConsultant) {
		this.strConsultant = strConsultant;
	}
                                                                                                                                  
}
