package ipd.transactions;

import hisglobal.utility.TransferObject;



import javax.sql.rowset.WebRowSet;

public class NursingDeskTransVO  implements TransferObject{
	
	   private static final long serialVersionUID = 02L;
	
	
	 
	private String[] strchk = null;  
	private String strDepartment ="";     //combo name 
	private String strUnit ="";            //combo name   
	private String strWard ="";             //combo name 
	private String strRoom ="";
	private String strhmno = "";
	private String strdeptproperty  ="";
	private String[] strhward =null;
	private String strwardproperty  ="";
	private String strunitproperty  ="";
	private String strroomproperty  ="";
	private String strAdmitDetailProperty  ="";
	private String strChecklistProperty   ="";
	private String strBedProperty   ="";
	private String[] stradmBed = null;
	private String stradmBedName = "";
	private String strBillFlag="0";
	private String strBedFlag="0";
	
	
	private WebRowSet  strDepartmenttWs =null;   
	private WebRowSet  strUnitWs =null;  
	private WebRowSet  strWardWs =null;  
	private WebRowSet  strRoomWs =null;  
	private WebRowSet  strChecklistWs =null;
	private WebRowSet  strBedDetailWS =null; 
	
	
	
	private String strAdmNo = "";
	private String hstradmno = "";
	private String strCrNo = "";
	private String hstrcrno  = ""; 
	private String strPatName  = "";
	private String strAgeSex ="";
	private String[] strBedNo =null;
	private WebRowSet  strAdmitDetailWs =null; 
	
	

	private String hiddencrno = "";
	private String hiddenadmno = "";
	private String hiddenbed = "";
	private String hiddenBedName = "";
	private String hiddenchkremark ="";
	private String hiddenbelonging ="";
	
	
	

	private String[] strArticleName  = null;    //artical Name(belonging )
	private String[] strQuantity  =null;  //article quantity
	private String[] strMultiRowMode  =null;
	private String[] strchklistCode = null;
	private String[] hstrchklistName = null;
	private String[] strChecklistRemark  = null;
	private String[] strBelongingRemark  = null;
	
	
	private String strhiddendepartment = "";
	private String strhiddendunit ="";
	private String strhiddenward ="";
	private String strhiddenRoom = "";
	
	
	
	private String StrSeatId = "";
	private String strLastmodiSeatId =""; 
	private String strHospitalCode ="";
	private String strhtransinflag ="";
	private String strhmoveno ="";
	
	private String strnormalMsg = "";  
	private String strErrorMsg  =""; 
	private String strMsgString ="";
	private String strMsgType = "";
	private String strRemarks ="";
	private String strIsCancelMode = "";
	

	private String[] strItemId=null;
	private String[] strItemType=null;
	private String[] strItemName=null;
	private String[] strItemQuantity=null;
	private String[] strItemRemarks=null;
	
	private String departmentName="";
	private String wardName="";
	
	private String strPreviousOccupiedbed="";
	
	private String strIsSharableFlag="0"; 
	
	private String strWadType="";
	private String billcount="0";
	private String sharableChk="0";
	private String strConsultantCode="0";
	private String strConsultantName="";
	private WebRowSet ConsultantWS=null;
	private String  wardDblOcuup ="";
	private String bedNO="";
	private String timelt="";


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
	 * @return the strRemarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}
	/**
	 * @param strRemarks the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
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
	public String getStrDepartment() {
		return strDepartment;
	}
	public void setStrDepartment(String strDepartment) {
		this.strDepartment = strDepartment;
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
	public String[] getStrhward() {
		return strhward;
	}
	public void setStrhward(String[] strhward) {
		this.strhward = strhward;
	}
	public void setStrchk(String[] strchk) {
		this.strchk = strchk;
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
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
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
	public String[] getStrchk() {
		return strchk;
	}
	public String[] getStrBedNo() {
		return strBedNo;
	}
	public void setStrBedNo(String[] strBedNo) {
		this.strBedNo = strBedNo;
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
	public String getHiddencrno() {
		return hiddencrno;
	}
	public String getHiddenadmno() {
		return hiddenadmno;
	}
	public String getHiddenbed() {
		return hiddenbed;
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
	public String getStrnormalMsg() {
		return strnormalMsg;
	}
	public void setStrnormalMsg(String strnormalMsg) {
		this.strnormalMsg = strnormalMsg;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	public String getHiddenBedName() {
		return hiddenBedName;
	}
	public void setHiddenBedName(String hiddenBedName) {
		this.hiddenBedName = hiddenBedName;
	}
	
	public void setStradmBedName(String stradmBedName) {
		this.stradmBedName = stradmBedName;
	}
	public String getStradmBedName() {
		return stradmBedName;
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
	public String getStrhmno() {
		return strhmno;
	}
	public void setStrhmno(String strhmno) {
		this.strhmno = strhmno;
	}
	public String getStrBillFlag() {
		return strBillFlag;
	}
	public void setStrBillFlag(String strBillFlag) {
		this.strBillFlag = strBillFlag;
	}
	public String getStrBedFlag() {
		return strBedFlag;
	}
	public void setStrBedFlag(String strBedFlag) {
		this.strBedFlag = strBedFlag;
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
	public WebRowSet getConsultantWS() {
		return ConsultantWS;
	}
	public void setConsultantWS(WebRowSet consultantWS) {
		ConsultantWS = consultantWS;
	}
	public String getWardDblOcuup() {
		return wardDblOcuup;
	}
	public void setWardDblOcuup(String wardDblOcuup) {
		this.wardDblOcuup = wardDblOcuup;
	}
	public String getBedNO() {
		return bedNO;
	}
	public void setBedNO(String bedNO) {
		this.bedNO = bedNO;
	}
	public String getTimelt() {
		return timelt;
	}
	public void setTimelt(String timelt) {
		this.timelt = timelt;
	}	
	
	
}