

package ipd.transactions;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionForm;
public class NursingDeskTransFB extends ActionForm{

    private static final long serialVersionUID = 02L;
  
	private String[] strchk = null;    
	    
	private String strDepartment ="";  //combo name
	private String strUnit ="";        //combo name   
	private String strWard ="";        //combo name  
	private String strRoom ="";        //combo name
	
	private String strdeptproperty  ="";
	private String strwardproperty  ="";
	private String[] strhward =null;
	private String strunitproperty  ="";
	private String strroomproperty  ="";
	private String strAdmitDetailProperty  ="";
	private String strChecklistProperty   ="";
	private String strBedProperty   ="";
	private String[] stradmBed = null;
	private String stradmBedName = "";
	
	
	
	private WebRowSet  strDepartmenttWs =null;  
	private WebRowSet  strUnitWs =null;  
	private WebRowSet  strWardWs =null;  
	private WebRowSet  strRoomWs =null;  
	private WebRowSet  strAdmitDetailWs =null;  
	private WebRowSet  strChecklistWs =null;  
	private WebRowSet  strBedDetailWS =null; 
	
	
	private String strAdmNo = "";
	private String hstradmno = "";
	private String hstrcrno  = "";  
	private String strCrNo = "";
	private String strhmno = "";
	
	private String hiddencrno = "";
	private String hiddenadmno = "";
	private String hiddenbed = "";
	private String hiddenBedName ="";
	private String hiddenchkremark ="";
	private String hiddenbelonging ="";
	
	private String departmentName="";
	private String wardName="";
	
	
	
	private String strPatName  = "";
	private String strAgeSex ="";
	private String[] strBedNo =null;
	
	
	
	private String[] strchklistCode = null;
	private String[] hstrchklistName = null;
	private String[]  strChecklistRemark  = null;
	private String[]  strBelongingRemark  = null;
	
	private String strHospitalCode ="";
	private String StrSeatId = "";
	private String strLastmodiSeatId =""; 
	
	private String strhiddendepartment = "";
	private String strhiddendunit ="";
	private String strhiddenward ="";
	private String strhiddenRoom = "";
	
	
	
	
	private String[]  strArticleName  = null;    //artical Name
	private String[] strQuantity  =null;  //article quantity 
	
	private String[] strMultiRowMode  =null;
	
	
	private String strhtransinflag ="";
	private String strhmoveno ="";
	
	private String strNormalMsg = "";
	private String strErrorMsg  =""; 
	private String strMsgString ="";
	private String strMsgType = "";
	private String strRecord = "";
	private String strIsCancelMode = "";
	
	private String strNursCheckListMandatoryFlag="";
	private String strBelongingItemValues="";
	private String strIssuedItemRequired="";
	private String strBelongingRequired="";
	private String strRemarksMandatoryFlag="";
	private String strIssuedItemValues="";
	
	
	private String[] strItemId=null;
	private String[] strItemType=null;
	private String[] strItemName=null;
	private String[] strItemQuantity=null;
	private String[] strItemRemarks=null;
	
	private String strPreviousOccupiedbed="";
	
	private String strIsSharableFlag="0";
	private String strWadType="";
	
	/*
	 * This is the flag use To check The Process Whether It is  
	 * Nursing Desk(ADT) or Nursing desk(IPD) 
	 * 1 for Nursing Desk(IPD)
	 * 0 for Nursing Desk(ADT)
	 */
	
	private String strCheckFlagType="0";
	private String strRemarks ="";
	private String strIcuWardType="";
	private String strPvtWardType="";
	private String billcount="0";
	private String sharableChk="0";
	private String strConsultantCode="0";
	private String strConsultantName="";
	private String timelt="";


	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrCheckFlagType() {
		return strCheckFlagType;
	}
	public void setStrCheckFlagType(String strCheckFlagType) {
		this.strCheckFlagType = strCheckFlagType;
	}
	public String getStrWadType() {
		return strWadType;
	}
	public void setStrWadType(String strWadType) {
		this.strWadType = strWadType;
	}
	public String getStrPreviousOccupiedbed() {
		return strPreviousOccupiedbed;
	}
	public void setStrPreviousOccupiedbed(String strPreviousOccupiedbed) {
		this.strPreviousOccupiedbed = strPreviousOccupiedbed;
	}
/**
 * @return the strItemRemarks
 */
	public String[] getStrItemRemarks() {
		return strItemRemarks;
	}
	/**
	 * @param strItemRemarks the strItemRemarks to set
	 */
	public void setStrItemRemarks(String[] strItemRemarks) {
		this.strItemRemarks = strItemRemarks;
	}
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
	 * @return the strBelongingItemValues
	 */
	public String getStrBelongingItemValues() {
		return strBelongingItemValues;
	}
	/**
	 * @param strBelongingItemValues the strBelongingItemValues to set
	 */
	public void setStrBelongingItemValues(String strBelongingItemValues) {
		this.strBelongingItemValues = strBelongingItemValues;
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
	 * @return the strRemarksMandatoryFlag
	 */
	public String getStrRemarksMandatoryFlag() {
		return strRemarksMandatoryFlag;
	}
	/**
	 * @param strRemarksMandatoryFlag the strRemarksMandatoryFlag to set
	 */
	public void setStrRemarksMandatoryFlag(String strRemarksMandatoryFlag) {
		this.strRemarksMandatoryFlag = strRemarksMandatoryFlag;
	}
	/**
	 * @return the strIssuedItemValues
	 */
	public String getStrIssuedItemValues() {
		return strIssuedItemValues;
	}
	/**
	 * @param strIssuedItemValues the strIssuedItemValues to set
	 */
	public void setStrIssuedItemValues(String strIssuedItemValues) {
		this.strIssuedItemValues = strIssuedItemValues;
	}
	/**
	 * @return the strIsCancelMode
	 */
	public String getStrIsCancelMode() {
		return strIsCancelMode;
	}
	/**
	 * @param strIsCancelMode the strIsCancelMode to set
	 */
	public void setStrIsCancelMode(String strIsCancelMode) {
		this.strIsCancelMode = strIsCancelMode;
	}
	/**
	 * @return the strNursCheckListMandatoryFlag
	 */
	public String getStrNursCheckListMandatoryFlag() {
		return strNursCheckListMandatoryFlag;
	}
	/**
	 * @param strNursCheckListMandatoryFlag the strNursCheckListMandatoryFlag to set
	 */
	public void setStrNursCheckListMandatoryFlag(
			String strNursCheckListMandatoryFlag) {
		this.strNursCheckListMandatoryFlag = strNursCheckListMandatoryFlag;
	}
	/**
	 * @return the strRecord
	 */
	public String getStrRecord() {
		return strRecord;
	}
	/**
	 * @param strRecord the strRecord to set
	 */
	public void setStrRecord(String strRecord) {
		this.strRecord = strRecord;
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
	public WebRowSet getStrDepartmenttWs() {
		return strDepartmenttWs;
	}
	public void setStrDepartmenttWs(WebRowSet strDepartmenttWs) {
		this.strDepartmenttWs = strDepartmenttWs;
	}
	public String getStrDepartment() {
		return strDepartment;
	}
	public void setStrDepartment(String strDepartment) {
		this.strDepartment = strDepartment;
	}
	public String getStrdeptproperty() {
		return strdeptproperty;
	}
	public void setStrdeptproperty(String strdeptproperty) {
		this.strdeptproperty = strdeptproperty;
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
	public String getStrAdmNo() {
		return strAdmNo;
	}
	public void setStrAdmNo(String strAdmNo) {
		this.strAdmNo = strAdmNo;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrPatName() {
		return strPatName;
	}
	public void setStrPatName(String strPatName) {
		this.strPatName = strPatName;
	}
	public String getStrAgeSex() {
		return strAgeSex;
	}
	public void setStrAgeSex(String strAgeSex) {
		this.strAgeSex = strAgeSex;
	}
	
	public WebRowSet getStrAdmitDetailWs() {
		return strAdmitDetailWs;
	}
	public void setStrAdmitDetailWs(WebRowSet strAdmitDetailWs) {
		this.strAdmitDetailWs = strAdmitDetailWs;
	}
	public String getStrAdmitDetailProperty() {
		return strAdmitDetailProperty;
	}
	public void setStrAdmitDetailProperty(String strAdmitDetailProperty) {
		this.strAdmitDetailProperty = strAdmitDetailProperty;
	}
	
	
	public String[] getStrArticleName() {
		return strArticleName;
	}
	public void setStrArticleName(String[] strArticleName) {
		this.strArticleName = strArticleName;
	}
	public String[] getStrQuantity() {
		return strQuantity;
	}
	public void setStrQuantity(String[] strQuantity) {
		this.strQuantity = strQuantity;
	}
	public String[] getStrchk() {
		return strchk;
	}
	public void setStrchk(String[] strchk) {
		this.strchk = strchk;
	}
	public String[] getStrhward() {
		return strhward;
	}
	public void setStrhward(String[] strhward) {
		this.strhward = strhward;
	}
	public String getStrChecklistProperty() {
		return strChecklistProperty;
	}
	public void setStrChecklistProperty(String strChecklistProperty) {
		this.strChecklistProperty = strChecklistProperty;
	}
	public WebRowSet getStrChecklistWs() {
		return strChecklistWs;
	}
	public void setStrChecklistWs(WebRowSet strChecklistWs) {
		this.strChecklistWs = strChecklistWs;
	}
	public WebRowSet getStrBedDetailWS() {
		return strBedDetailWS;
	}
	public void setStrBedDetailWS(WebRowSet strBedDetailWS) {
		this.strBedDetailWS = strBedDetailWS;
	}
	public String getStrBedProperty() {
		return strBedProperty;
	}
	public void setStrBedProperty(String strBedProperty) {
		this.strBedProperty = strBedProperty;
	}
	public String getStrSeatId() {
		return StrSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		StrSeatId = strSeatId;
	}
	
	public String[] getStrchklistCode() {
		return strchklistCode;
	}
	public void setStrchklistCode(String[] strchklistCode) {
		this.strchklistCode = strchklistCode;
	}
	public String[] getHstrchklistName() {
		return hstrchklistName;
	}
	public void setHstrchklistName(String[] hstrchklistName) {
		this.hstrchklistName = hstrchklistName;
	}
	public String[] getStrChecklistRemark() {
		return strChecklistRemark;
	}
	public void setStrChecklistRemark(String[] strChecklistRemark) {
		this.strChecklistRemark = strChecklistRemark;
	}
	public String[] getStrBelongingRemark() {
		return strBelongingRemark;
	}
	public void setStrBelongingRemark(String[] strBelongingRemark) {
		this.strBelongingRemark = strBelongingRemark;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrLastmodiSeatId() {
		return strLastmodiSeatId;
	}
	public void setStrLastmodiSeatId(String strLastmodiSeatId) {
		this.strLastmodiSeatId = strLastmodiSeatId;
	}
	public String getHstradmno() {
		return hstradmno;
	}
	public void setHstradmno(String hstradmno) {
		this.hstradmno = hstradmno;
	}
	public void setStrBedNo(String[] strBedNo) {
		this.strBedNo = strBedNo;
	}
	public String[] getStrBedNo() {
		return strBedNo;
	}
	public String getHstrcrno() {
		return hstrcrno;
	}
	public void setHstrcrno(String hstrcrno) {
		this.hstrcrno = hstrcrno;
	}
	
	public void setHiddencrno(String hiddencrno) {
		this.hiddencrno = hiddencrno;
	}
	public void setHiddenadmno(String hiddenadmno) {
		this.hiddenadmno = hiddenadmno;
	}
	public void setHiddenbed(String hiddenbed) {
		this.hiddenbed = hiddenbed;
	}
	public String getHiddenchkremark() {
		return hiddenchkremark;
	}
	public void setHiddenchkremark(String hiddenchkremark) {
		this.hiddenchkremark = hiddenchkremark;
	}
	public String[] getStradmBed() {
		return stradmBed;
	}
	public void setStradmBed(String[] stradmBed) {
		this.stradmBed = stradmBed;
	}
	public String getHiddenbelonging() {
		return hiddenbelonging;
	}
	public void setHiddenbelonging(String hiddenbelonging) {
		this.hiddenbelonging = hiddenbelonging;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	public String getHiddenbed() {
		return hiddenbed;
	}
	public String getHiddenBedName() {
		return hiddenBedName;
	}
	public void setHiddenBedName(String hiddenBedName) {
		this.hiddenBedName = hiddenBedName;
	}
	public String getStradmBedName() {
		return stradmBedName;
	}
	public void setStradmBedName(String stradmBedName) {
		this.stradmBedName = stradmBedName;
	}
	public String getStrhiddendepartment() {
		return strhiddendepartment;
	}
	public void setStrhiddendepartment(String strhiddendepartment) {
		this.strhiddendepartment = strhiddendepartment;
	}
	public String getStrhiddendunit() {
		return strhiddendunit;
	}
	public void setStrhiddendunit(String strhiddendunit) {
		this.strhiddendunit = strhiddendunit;
	}
	public String getStrhiddenward() {
		return strhiddenward;
	}
	public void setStrhiddenward(String strhiddenward) {
		this.strhiddenward = strhiddenward;
	}
	public String getStrhiddenRoom() {
		return strhiddenRoom;
	}
	public void setStrhiddenRoom(String strhiddenRoom) {
		this.strhiddenRoom = strhiddenRoom;
	}
	public String getStrhtransinflag() {
		return strhtransinflag;
	}
	public void setStrhtransinflag(String strhtransinflag) {
		this.strhtransinflag = strhtransinflag;
	}
	public String getStrhmoveno() {
		return strhmoveno;
	}
	public void setStrhmoveno(String strhmoveno) {
		this.strhmoveno = strhmoveno;
	}
	public String[] getStrMultiRowMode() {
		return strMultiRowMode;
	}
	public void setStrMultiRowMode(String[] strMultiRowMode) {
		this.strMultiRowMode = strMultiRowMode;
	}
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrhmno() {
		return strhmno;
	}
	public void setStrhmno(String strhmno) {
		this.strhmno = strhmno;
	}
	public String getHiddencrno() {
		return hiddencrno;
	}
	public String getHiddenadmno() {
		return hiddenadmno;
	}
	public String getStrBelongingRequired() {
		return strBelongingRequired;
	}
	public void setStrBelongingRequired(String strBelongingRequired) {
		this.strBelongingRequired = strBelongingRequired;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getStrIsSharableFlag() {
		return strIsSharableFlag;
	}
	public void setStrIsSharableFlag(String strIsSharableFlag) {
		this.strIsSharableFlag = strIsSharableFlag;
	}
	public String getStrIcuWardType() {
		return strIcuWardType;
	}
	public void setStrIcuWardType(String strIcuWardType) {
		this.strIcuWardType = strIcuWardType;
	}
	public String getStrPvtWardType() {
		return strPvtWardType;
	}
	public void setStrPvtWardType(String strPvtWardType) {
		this.strPvtWardType = strPvtWardType;
	}
	public String getBillcount() {
		return billcount;
	}
	public void setBillcount(String billcount) {
		this.billcount = billcount;
	}
	public String getSharableChk() {
		return sharableChk;
	}
	public void setSharableChk(String sharableChk) {
		this.sharableChk = sharableChk;
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
	public String getTimelt() {
		return timelt;
	}
	public void setTimelt(String timelt) {
		this.timelt = timelt;
	}
	
}
