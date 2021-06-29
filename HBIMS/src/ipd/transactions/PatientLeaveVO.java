package ipd.transactions;
import hisglobal.utility.TransferObject;
import javax.sql.rowset.WebRowSet;

public class PatientLeaveVO implements TransferObject
{
	private static final long serialVersionUID = 02L;
    private String strErrMsgString = null;
    private String strNormalMsgString = null;
    private String strWarningMsgString = null;
    private String curAdmNo = "";
    private String curDtUtWrRmBd = "";
    private String curWrdBedCode = "";
    private String curDept_Unt_RomCode = "";
    private String curAdmAdvNo = "";
    private String strTempVal = "";
    private String strjHLP = "";
	private String strDepartmentName="";
	private String strVacantBed="";
	private String strMsg="";
	private String strIsBedVacant  ="1";
	private String strPhoneNo  ="";
	private String strAddrLeave ="";
           //combo name
	
	private WebRowSet strPatientDetailsWs = null;
	private WebRowSet strOnLineReqDiscountWs = null;
    private String strMsgString = null;
    private String strMsgType = "";
    private String strChk ="";
    private String strAdmStatCode ="";
    private String strCrNo = "";
    private String strCrNo1 = "";
    private String strBId ="";
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
    private String strDepartment ="";     //combo name
	private String strUnit ="";            //combo name
	private String strWard ="";             //combo name
	private String strRoom ="";
	private String strWardTypeCode="0";
	private String strWardCode="";
	private String strBedTypeCode="0";
	private String strRoomTypeCode="0";
	private String strWardName="";
	private String strDeptName="";
	private String strDeptCode="";
	private String strDeptUnitCode="";
	private String strUnitName="";
	private String strTreatmentCategoryName="";
	private String strPatCatCode="";
	private String strConsultantCode="";
	private String strConsultantName="";
	private String strOccRelation="";
	private String strAdmNo="";
	private String strEpisodeCode="";
	private String strVisitNo="";
	private String strMlcNo="";
	private String strIsUrban="";
	private String strAdviceAdmNo="";
	private String strRoomCode="";
	private String strOccEmpName="";
	private String strTreatmentCategoryCode="";
	private String strNewBorn="";
	private String strBedCode="0";
	private String strBedType="";
	private String strBedProperty="";
	private String strBed="";
	private String strServArea="";
	private String strServAreaCode="";
	private String strMsApprovalFlag="";
	private String strMsApprovalStatus="";
	private String strdeptproperty  ="";
	private String[] strhward =null;
	private String strwardproperty  ="";
	private String strunitproperty  ="";
	private String strroomproperty  ="";
	private String strwardType="";
	private String strRoomType="";
	private String strPopUp="0";
	private String strOldWardCode="";
	private String strOldRoomCode="";
	private String strOldBedCode="";
	private String strTransFlg="";
	private String strPvtBedFlg="";
	private String strCmbRed="0";
	private String strCtDate="";
	private String strValidFrom="";
	private String strValidTo="";
	private String strSanctionDt="";
	private String strPatCondL="";
	private String strAdviceL="";
	private String strDaysOnLeave="";
	private WebRowSet roomTypeWS=null;
	private WebRowSet strServAreaWS=null;
	private WebRowSet wardWS=null;
	private WebRowSet roomWs=null;
	private WebRowSet bedTypeWS=null;
	private WebRowSet bedDetailWS=null;
	private WebRowSet  strDepartmenttWs =null;
	private WebRowSet  strUnitWs =null;
	private WebRowSet  strWardWs =null;
	private WebRowSet  strRoomWs =null;
	private WebRowSet wardTypeWS=null;
	private WebRowSet  strLeaveApprovalWS =null;
	private String strLeaveStatusCode ="";
	private String strLeaveSlNo ="";
	private String strRsn_Entry ="";
	private String strTempLeaveJoin ="";
	private String strEntryDate ="";
	private String strSactionDate ="";
	private String strSeatId="";
	private String strHospCode="";
	private String strIsDead="";
	private String strLeaveBy ="";
	private String strLeaveRsn ="";
	private String strRmkL ="";
	private String strRsnL ="";
	private String strRmkJ ="";
	private String strRsnJ ="";
	private String strLeaveType="0";


	private String strBelongingClear = "";
	
    /**
	 * @return the strBelongingClear
	 */
	public String getStrBelongingClear() {
		return strBelongingClear;
	}

	/**
	 * @param strBelongingClear the strBelongingClear to set
	 */
	public void setStrBelongingClear(String strBelongingClear) {
		this.strBelongingClear = strBelongingClear;
	}

	public String getStrLeaveBy() {
		return strLeaveBy;
	}

	public void setStrLeaveBy(String strLeaveBy) {
		this.strLeaveBy = strLeaveBy;
	}

	public String getStrLeaveRsn() {
		return strLeaveRsn;
	}

	public void setStrLeaveRsn(String strLeaveRsn) {
		this.strLeaveRsn = strLeaveRsn;
	}

	public String getStrRmkL() {
		return strRmkL;
	}

	public void setStrRmkL(String strRmkL) {
		this.strRmkL = strRmkL;
	}

	public String getStrRsnL() {
		return strRsnL;
	}

	public void setStrRsnL(String strRsnL) {
		this.strRsnL = strRsnL;
	}

	public String getStrRmkJ() {
		return strRmkJ;
	}

	public void setStrRmkJ(String strRmkJ) {
		this.strRmkJ = strRmkJ;
	}

	public String getStrRsnJ() {
		return strRsnJ;
	}

	public void setStrRsnJ(String strRsnJ) {
		this.strRsnJ = strRsnJ;
	}

	public String getStrIsDead() {
		return strIsDead;
	}

	public void setStrIsDead(String strIsDead) {
		this.strIsDead = strIsDead;
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

	public String getStrSactionDate() {
		return strSactionDate;
	}

	public void setStrSactionDate(String strSactionDate) {
		this.strSactionDate = strSactionDate;
	}

	public String getStrTempLeaveJoin() {
		return strTempLeaveJoin;
	}

	public void setStrTempLeaveJoin(String strTempLeaveJoin) {
		this.strTempLeaveJoin = strTempLeaveJoin;
	}

	public String getStrRsn_Entry() {
		return strRsn_Entry;
	}

	public void setStrRsn_Entry(String strRsn_Entry) {
		this.strRsn_Entry = strRsn_Entry;
	}

	public String getStrLeaveSlNo() {
		return strLeaveSlNo;
	}

	public void setStrLeaveSlNo(String strLeaveSlNo) {
		this.strLeaveSlNo = strLeaveSlNo;
	}

	public String getStrLeaveStatusCode() {
		return strLeaveStatusCode;
	}

	public void setStrLeaveStatusCode(String strLeaveStatusCode) {
		this.strLeaveStatusCode = strLeaveStatusCode;
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

	public String getCurAdmNo() {
		return curAdmNo;
	}

	public void setCurAdmNo(String curAdmNo) {
		this.curAdmNo = curAdmNo;
	}

	public String getCurDtUtWrRmBd() {
		return curDtUtWrRmBd;
	}

	public void setCurDtUtWrRmBd(String curDtUtWrRmBd) {
		this.curDtUtWrRmBd = curDtUtWrRmBd;
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

	public String getStrTempVal() {
		return strTempVal;
	}

	public void setStrTempVal(String strTempVal) {
		this.strTempVal = strTempVal;
	}

	public String getStrjHLP() {
		return strjHLP;
	}

	public void setStrjHLP(String strjHLP) {
		this.strjHLP = strjHLP;
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

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
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

	public void setWardTypeWS(WebRowSet ws) {
		// TODO Auto-generated method stub

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

	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}

	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}

	public String getStrTreatmentCategoryName() {
		return strTreatmentCategoryName;
	}

	public void setStrTreatmentCategoryName(String strTreatmentCategoryName) {
		this.strTreatmentCategoryName = strTreatmentCategoryName;
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

	

	public String getStrAdmNo() {
		return strAdmNo;
	}

	public void setStrAdmNo(String strAdmNo) {
		this.strAdmNo = strAdmNo;
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



	public String getStrTreatmentCategoryCode() {
		return strTreatmentCategoryCode;
	}

	public void setStrTreatmentCategoryCode(String strTreatmentCategoryCode) {
		this.strTreatmentCategoryCode = strTreatmentCategoryCode;
	}

	

	public String getStrBedCode() {
		return strBedCode;
	}

	public void setStrBedCode(String strBedCode) {
		this.strBedCode = strBedCode;
	}

	

	public String getStrMsApprovalFlag() {
		return strMsApprovalFlag;
	}

	public void setStrMsApprovalFlag(String strMsApprovalFlag) {
		this.strMsApprovalFlag = strMsApprovalFlag;
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

	public String getStrBedType() {
		return strBedType;
	}

	public void setStrBedType(String strBedType) {
		this.strBedType = strBedType;
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

	public String getStrSanctionDt() {
		return strSanctionDt;
	}

	public void setStrSanctionDt(String strSanctionDt) {
		this.strSanctionDt = strSanctionDt;
	}

	public String getStrAdmStatCode() {
		return strAdmStatCode;
	}

	public void setStrAdmStatCode(String strAdmStatCode) {
		this.strAdmStatCode = strAdmStatCode;
	}

	public String getStrUnitName() {
		return strUnitName;
	}

	public void setStrUnitName(String strUnitName) {
		this.strUnitName = strUnitName;
	}

	public String getStrDeptName() {
		return strDeptName;
	}

	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}

	public String getStrPatCatCode() {
		return strPatCatCode;
	}

	public void setStrPatCatCode(String strPatCatCode) {
		this.strPatCatCode = strPatCatCode;
	}

	public String getStrOccRelation() {
		return strOccRelation;
	}

	public void setStrOccRelation(String strOccRelation) {
		this.strOccRelation = strOccRelation;
	}

	public String getStrVisitNo() {
		return strVisitNo;
	}

	public void setStrVisitNo(String strVisitNo) {
		this.strVisitNo = strVisitNo;
	}

	public String getStrIsUrban() {
		return strIsUrban;
	}

	public void setStrIsUrban(String strIsUrban) {
		this.strIsUrban = strIsUrban;
	}

	public String getStrOccEmpName() {
		return strOccEmpName;
	}

	public void setStrOccEmpName(String strOccEmpName) {
		this.strOccEmpName = strOccEmpName;
	}

	public String getStrNewBorn() {
		return strNewBorn;
	}

	public void setStrNewBorn(String strNewBorn) {
		this.strNewBorn = strNewBorn;
	}

	public String getStrServAreaCode() {
		return strServAreaCode;
	}

	public void setStrServAreaCode(String strServAreaCode) {
		this.strServAreaCode = strServAreaCode;
	}

	public String getStrMsApprovalStatus() {
		return strMsApprovalStatus;
	}

	public void setStrMsApprovalStatus(String strMsApprovalStatus) {
		this.strMsApprovalStatus = strMsApprovalStatus;
	}

	public String getStrPopUp() {
		return strPopUp;
	}

	public void setStrPopUp(String strPopUp) {
		this.strPopUp = strPopUp;
	}

	public String getStrEntryDate() {
		return strEntryDate;
	}

	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	public String getStrOldWardCode() {
		return strOldWardCode;
	}

	public void setStrOldWardCode(String strOldWardCode) {
		this.strOldWardCode = strOldWardCode;
	}

	public String getStrOldRoomCode() {
		return strOldRoomCode;
	}

	public void setStrOldRoomCode(String strOldRoomCode) {
		this.strOldRoomCode = strOldRoomCode;
	}

	public String getStrOldBedCode() {
		return strOldBedCode;
	}

	public void setStrOldBedCode(String strOldBedCode) {
		this.strOldBedCode = strOldBedCode;
	}

	public String getStrTransFlg() {
		return strTransFlg;
	}

	public void setStrTransFlg(String strTransFlg) {
		this.strTransFlg = strTransFlg;
	}

	public String getStrPvtBedFlg() {
		return strPvtBedFlg;
	}

	public void setStrPvtBedFlg(String strPvtBedFlg) {
		this.strPvtBedFlg = strPvtBedFlg;
	}

	public String getStrCmbRed() {
		return strCmbRed;
	}

	public void setStrCmbRed(String strCmbRed) {
		this.strCmbRed = strCmbRed;
	}

	public WebRowSet getRoomTypeWS() {
		return roomTypeWS;
	}

	public void setRoomTypeWS(WebRowSet roomTypeWS) {
		this.roomTypeWS = roomTypeWS;
	}

	public WebRowSet getStrServAreaWS() {
		return strServAreaWS;
	}

	public void setStrServAreaWS(WebRowSet strServAreaWS) {
		this.strServAreaWS = strServAreaWS;
	}

	public WebRowSet getWardWS() {
		return wardWS;
	}

	public void setWardWS(WebRowSet wardWS) {
		this.wardWS = wardWS;
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

	public WebRowSet getWardTypeWS() {
		return wardTypeWS;
	}

	public String getStrIsBedVacant() {
		return strIsBedVacant;
	}

	public void setStrIsBedVacant(String strIsBedVacant) {
		this.strIsBedVacant = strIsBedVacant;
	}

	public String getStrPhoneNo() {
		return strPhoneNo;
	}

	public void setStrPhoneNo(String strPhoneNo) {
		this.strPhoneNo = strPhoneNo;
	}

	public String getStrAddrLeave() {
		return strAddrLeave;
	}

	public void setStrAddrLeave(String strAddrLeave) {
		this.strAddrLeave = strAddrLeave;
	}

	public WebRowSet getStrLeaveApprovalWS() {
		return strLeaveApprovalWS;
	}

	public void setStrLeaveApprovalWS(WebRowSet strLeaveApprovalWS) {
		this.strLeaveApprovalWS = strLeaveApprovalWS;
	}

	public String getStrLeaveType()
	{
		return strLeaveType;
	}

	public void setStrLeaveType(String strLeaveType)
	{
		this.strLeaveType = strLeaveType;
	}



}
