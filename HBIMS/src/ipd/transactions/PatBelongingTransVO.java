package ipd.transactions;

import javax.sql.rowset.WebRowSet;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import hisglobal.utility.TransferObject;

/**
 * @author HIS
 *
 */
public class PatBelongingTransVO implements TransferObject {

  private static final long serialVersionUID = 02L;
	
  private String strReturnRmks = "";
	private String strMsgString = "";
	private String strMsgType = "0";
	private String strSeatId ="";
	private String strHospitalCode ="";
	private String strErrMsg = "";
	private String strAdmnNo ="";
	private String strhAdmnNo ="";
	private String strFlag="";
	private String strPatAdmCode = "";
	private String strIsDead = "";
	private String strInvalidCrNo= "0";
	
	private String strCrNo ="";
	private String strName ="";
	private String strageSex ="";
	private String strPatientCategory="";
	private String strAdmnDate="";
	private String strWard="";
	private String strRoomBed="";
	private String strPatientBlngdtl="";
	private String strValue="1";
	private String patBelongingDetail="";
	private String[] strItemName=null;
	private String[] strItemQuantity=null;
	private String[] strRemarks=null;
	private String[] strItemReturnDate=null;
	private String[] strItemReturnTo={""};
	private WebRowSet strPatBelonging = null;
	private String[] strchkvisit=null;
	private String strCtDate = null;
	private String strDeptUnit="";
	private String curWrdBedCode="";
	private String curDept_Unt_RomCode="";
	private String strValueHidden="";
	private String strNormalMsg="";
	private String strItemReturnDateP="";
	private String strItemReturnDateR="";
	private String[] strslno=null;
	private String strWardCode="";
	private String strDeptUnitCode="";
	private String[] strItemNameU=null;
	private String[] strItemQuantityU=null;
	private String[] strRemarksU=null;
	private String[] strItemReturnDateU=null;
	private String[] strItemReturnToU={""};
	private String[] strchkvisitU=null;
	private String[] strRelation=null;
	private String[] strItemId=null;
	private String[] strItemType=null;
	private WebRowSet relationWS=null;
	private WebRowSet patIssuedItemWS=null;
	private String strBelMode="";
	
	/**
	 * @return the strItemId
	 */
	public String[] getStrItemId() {
		return strItemId;
	}
	/**
	 * @param strItemId the strItemId to set
	 */
	public void setStrItemId(String[] strItemId) {
		this.strItemId = strItemId;
	}
	/**
	 * @return the strItemType
	 */
	public String[] getStrItemType() {
		return strItemType;
	}
	/**
	 * @param strItemType the strItemType to set
	 */
	public void setStrItemType(String[] strItemType) {
		this.strItemType = strItemType;
	}
	/**
	 * @return the strMsgString
	 */
	public String getStrMsgString() {
		return strMsgString;
	}
	/**
	 * @param strMsgString the strMsgString to set
	 */
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	/**
	 * @return the strMsgType
	 */
	public String getStrMsgType() {
		return strMsgType;
	}
	/**
	 * @param strMsgType the strMsgType to set
	 */
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	/**
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}
	/**
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	/**
	 * @return the strAdmnNo
	 */
	public String getStrAdmnNo() {
		return strAdmnNo;
	}
	/**
	 * @param strAdmnNo the strAdmnNo to set
	 */
	public void setStrAdmnNo(String strAdmnNo) {
		this.strAdmnNo = strAdmnNo;
	}
	/**
	 * @return the strhAdmnNo
	 */
	public String getStrhAdmnNo() {
		return strhAdmnNo;
	}
	/**
	 * @param strhAdmnNo the strhAdmnNo to set
	 */
	public void setStrhAdmnNo(String strhAdmnNo) {
		this.strhAdmnNo = strhAdmnNo;
	}
	/**
	 * @return the strCrNo
	 */
	public String getStrCrNo() {
		return strCrNo;
	}
	/**
	 * @param strCrNo the strCrNo to set
	 */
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	/**
	 * @return the strName
	 */
	public String getStrName() {
		return strName;
	}
	/**
	 * @param strName the strName to set
	 */
	public void setStrName(String strName) {
		this.strName = strName;
	}
	/**
	 * @return the strageSex
	 */
	public String getStrageSex() {
		return strageSex;
	}
	/**
	 * @param strageSex the strageSex to set
	 */
	public void setStrageSex(String strageSex) {
		this.strageSex = strageSex;
	}
	/**
	 * @return the strPatientCategory
	 */
	public String getStrPatientCategory() {
		return strPatientCategory;
	}
	/**
	 * @param strPatientCategory the strPatientCategory to set
	 */
	public void setStrPatientCategory(String strPatientCategory) {
		this.strPatientCategory = strPatientCategory;
	}
	/**
	 * @return the strAdmnDate
	 */
	public String getStrAdmnDate() {
		return strAdmnDate;
	}
	/**
	 * @param strAdmnDate the strAdmnDate to set
	 */
	public void setStrAdmnDate(String strAdmnDate) {
		this.strAdmnDate = strAdmnDate;
	}
	/**
	 * @return the strWard
	 */
	public String getStrWard() {
		return strWard;
	}
	/**
	 * @param strWard the strWard to set
	 */
	public void setStrWard(String strWard) {
		this.strWard = strWard;
	}
	/**
	 * @return the strRoomBed
	 */
	public String getStrRoomBed() {
		return strRoomBed;
	}
	/**
	 * @param strRoomBed the strRoomBed to set
	 */
	public void setStrRoomBed(String strRoomBed) {
		this.strRoomBed = strRoomBed;
	}
	/**
	 * @return the strPatientBlngdtl
	 */
	public String getStrPatientBlngdtl() {
		return strPatientBlngdtl;
	}
	/**
	 * @param strPatientBlngdtl the strPatientBlngdtl to set
	 */
	public void setStrPatientBlngdtl(String strPatientBlngdtl) {
		this.strPatientBlngdtl = strPatientBlngdtl;
	}
	/**
	 * @return the strValue
	 */
	public String getStrValue() {
		return strValue;
	}
	/**
	 * @param strValue the strValue to set
	 */
	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}
	/**
	 * @return the patBelongingDetail
	 */
	public String getPatBelongingDetail() {
		return patBelongingDetail;
	}
	/**
	 * @param patBelongingDetail the patBelongingDetail to set
	 */
	public void setPatBelongingDetail(String patBelongingDetail) {
		this.patBelongingDetail = patBelongingDetail;
	}
	/**
	 * @return the strItemName
	 */
	public String[] getStrItemName() {
		return strItemName;
	}
	/**
	 * @param strItemName the strItemName to set
	 */
	public void setStrItemName(String[] strItemName) {
		this.strItemName = strItemName;
	}
	/**
	 * @return the strItemQuantity
	 */
	public String[] getStrItemQuantity() {
		return strItemQuantity;
	}
	/**
	 * @param strItemQuantity the strItemQuantity to set
	 */
	public void setStrItemQuantity(String[] strItemQuantity) {
		this.strItemQuantity = strItemQuantity;
	}
	/**
	 * @return the strRemarks
	 */
	public String[] getStrRemarks() {
		return strRemarks;
	}
	/**
	 * @param strRemarks the strRemarks to set
	 */
	public void setStrRemarks(String[] strRemarks) {
		this.strRemarks = strRemarks;
	}
	/**
	 * @return the strItemReturnDate
	 */
	public String[] getStrItemReturnDate() {
		return strItemReturnDate;
	}
	/**
	 * @param strItemReturnDate the strItemReturnDate to set
	 */
	public void setStrItemReturnDate(String[] strItemReturnDate) {
		this.strItemReturnDate = strItemReturnDate;
	}
	/**
	 * @return the strItemReturnTo
	 */
	public String[] getStrItemReturnTo() {
		return strItemReturnTo;
	}
	/**
	 * @param strItemReturnTo the strItemReturnTo to set
	 */
	public void setStrItemReturnTo(String[] strItemReturnTo) {
		this.strItemReturnTo = strItemReturnTo;
	}
	/**
	 * @return the strPatBelonging
	 */
	public WebRowSet getStrPatBelonging() {
		return strPatBelonging;
	}
	/**
	 * @param strPatBelonging the strPatBelonging to set
	 */
	public void setStrPatBelonging(WebRowSet strPatBelonging) {
		this.strPatBelonging = strPatBelonging;
	}
	/**
	 * @return the strchkvisit
	 */
	public String[] getStrchkvisit() {
		return strchkvisit;
	}
	/**
	 * @param strchkvisit the strchkvisit to set
	 */
	public void setStrchkvisit(String[] strchkvisit) {
		this.strchkvisit = strchkvisit;
	}
	/**
	 * @return the strDeptUnit
	 */
	public String getStrDeptUnit() {
		return strDeptUnit;
	}
	/**
	 * @param strDeptUnit the strDeptUnit to set
	 */
	public void setStrDeptUnit(String strDeptUnit) {
		this.strDeptUnit = strDeptUnit;
	}
	/**
	 * @return the curWrdBedCode
	 */
	public String getCurWrdBedCode() {
		return curWrdBedCode;
	}
	/**
	 * @param curWrdBedCode the curWrdBedCode to set
	 */
	public void setCurWrdBedCode(String curWrdBedCode) {
		this.curWrdBedCode = curWrdBedCode;
	}
	/**
	 * @return the curDept_Unt_RomCode
	 */
	public String getCurDept_Unt_RomCode() {
		return curDept_Unt_RomCode;
	}
	/**
	 * @param curDept_Unt_RomCode the curDept_Unt_RomCode to set
	 */
	public void setCurDept_Unt_RomCode(String curDept_Unt_RomCode) {
		this.curDept_Unt_RomCode = curDept_Unt_RomCode;
	}
	/**
	 * @return the strValueHidden
	 */
	public String getStrValueHidden() {
		return strValueHidden;
	}
	/**
	 * @param strValueHidden the strValueHidden to set
	 */
	public void setStrValueHidden(String strValueHidden) {
		this.strValueHidden = strValueHidden;
	}
	/**
	 * @return the strNormalMsg
	 */
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	/**
	 * @param strNormalMsg the strNormalMsg to set
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	/**
	 * @return the strItemReturnDateP
	 */
	public String getStrItemReturnDateP() {
		return strItemReturnDateP;
	}
	/**
	 * @param strItemReturnDateP the strItemReturnDateP to set
	 */
	public void setStrItemReturnDateP(String strItemReturnDateP) {
		this.strItemReturnDateP = strItemReturnDateP;
	}
	/**
	 * @return the strItemReturnDateR
	 */
	public String getStrItemReturnDateR() {
		return strItemReturnDateR;
	}
	/**
	 * @param strItemReturnDateR the strItemReturnDateR to set
	 */
	public void setStrItemReturnDateR(String strItemReturnDateR) {
		this.strItemReturnDateR = strItemReturnDateR;
	}
	/**
	 * @return the strslno
	 */
	public String[] getStrslno() {
		return strslno;
	}
	/**
	 * @param strslno the strslno to set
	 */
	public void setStrslno(String[] strslno) {
		this.strslno = strslno;
	}
	/**
	 * @return the strWardCode
	 */
	public String getStrWardCode() {
		return strWardCode;
	}
	/**
	 * @param strWardCode the strWardCode to set
	 */
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	/**
	 * @return the strDeptUnitCode
	 */
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}
	/**
	 * @param strDeptUnitCode the strDeptUnitCode to set
	 */
	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}
	/**
	 * @return the strItemNameU
	 */
	public String[] getStrItemNameU() {
		return strItemNameU;
	}
	/**
	 * @param strItemNameU the strItemNameU to set
	 */
	public void setStrItemNameU(String[] strItemNameU) {
		this.strItemNameU = strItemNameU;
	}
	/**
	 * @return the strItemQuantityU
	 */
	public String[] getStrItemQuantityU() {
		return strItemQuantityU;
	}
	/**
	 * @param strItemQuantityU the strItemQuantityU to set
	 */
	public void setStrItemQuantityU(String[] strItemQuantityU) {
		this.strItemQuantityU = strItemQuantityU;
	}
	/**
	 * @return the strRemarksU
	 */
	public String[] getStrRemarksU() {
		return strRemarksU;
	}
	/**
	 * @param strRemarksU the strRemarksU to set
	 */
	public void setStrRemarksU(String[] strRemarksU) {
		this.strRemarksU = strRemarksU;
	}
	/**
	 * @return the strItemReturnDateU
	 */
	public String[] getStrItemReturnDateU() {
		return strItemReturnDateU;
	}
	/**
	 * @param strItemReturnDateU the strItemReturnDateU to set
	 */
	public void setStrItemReturnDateU(String[] strItemReturnDateU) {
		this.strItemReturnDateU = strItemReturnDateU;
	}
	/**
	 * @return the strItemReturnToU
	 */
	public String[] getStrItemReturnToU() {
		return strItemReturnToU;
	}
	/**
	 * @param strItemReturnToU the strItemReturnToU to set
	 */
	public void setStrItemReturnToU(String[] strItemReturnToU) {
		this.strItemReturnToU = strItemReturnToU;
	}
	/**
	 * @return the strchkvisitU
	 */
	public String[] getStrchkvisitU() {
		return strchkvisitU;
	}
	/**
	 * @param strchkvisitU the strchkvisitU to set
	 */
	public void setStrchkvisitU(String[] strchkvisitU) {
		this.strchkvisitU = strchkvisitU;
	}
	/**
	 * @return the strCtDate
	 */
	public String getStrCtDate() {
		HisUtil util = new HisUtil("Patient Belonging Transaction", "PatBelongingTransVO");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "Patient Belonging Transaction",
					"PatBelongingTransVO.getStrCtDate()-->" + e.getMessage());
		}
		return strCtDate;
	}
	/**
	 * @return the strReturnRmks
	 */
	public String getStrReturnRmks() {
		return strReturnRmks;
	}
	/**
	 * @param strReturnRmks the strReturnRmks to set
	 */
	public void setStrReturnRmks(String strReturnRmks) {
		this.strReturnRmks = strReturnRmks;
	}
	/**
	 * @return the strErrMsg
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}
	/**
	 * @param strErrMsg the strErrMsg to set
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	/**
	 * @return the strPatAdmCode
	 */
	public String getStrPatAdmCode() {
		return strPatAdmCode;
	}
	/**
	 * @param strPatAdmCode the strPatAdmCode to set
	 */
	public void setStrPatAdmCode(String strPatAdmCode) {
		this.strPatAdmCode = strPatAdmCode;
	}
	/**
	 * @return the strIsDead
	 */
	public String getStrIsDead() {
		return strIsDead;
	}
	/**
	 * @param strIsDead the strIsDead to set
	 */
	public void setStrIsDead(String strIsDead) {
		this.strIsDead = strIsDead;
	}
	/**
	 * @return the strInvalidCrNo
	 */
	public String getStrInvalidCrNo() {
		return strInvalidCrNo;
	}
	/**
	 * @param strInvalidCrNo the strInvalidCrNo to set
	 */
	public void setStrInvalidCrNo(String strInvalidCrNo) {
		this.strInvalidCrNo = strInvalidCrNo;
	}
	/**
	 * @return the strFlag
	 */
	public String getStrFlag() {
		return strFlag;
	}
	/**
	 * @param strFlag the strFlag to set
	 */
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public WebRowSet getRelationWS() {
		return relationWS;
	}
	public void setRelationWS(WebRowSet relationWS) {
		this.relationWS = relationWS;
	}
	public String[] getStrRelation() {
		return strRelation;
	}
	public void setStrRelation(String[] strRelation) {
		this.strRelation = strRelation;
	}
	public WebRowSet getPatIssuedItemWS() {
		return patIssuedItemWS;
	}
	public void setPatIssuedItemWS(WebRowSet patIssuedItemWS) {
		this.patIssuedItemWS = patIssuedItemWS;
	}
	public String getStrBelMode() {
		return strBelMode;
	}
	public void setStrBelMode(String strBelMode) {
		this.strBelMode = strBelMode;
	}
	
  }