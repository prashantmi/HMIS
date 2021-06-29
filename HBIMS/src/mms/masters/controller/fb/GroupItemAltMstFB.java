package mms.masters.controller.fb;

import hisglobal.masterutil.GenericFormBean;

/**
 * The Class StoreItemMstFB.
 */
public class GroupItemAltMstFB extends GenericFormBean 
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;
	private String hmode="";
	private String strItemCatCombo="";
	private String strItemCat="";
	private String strExGroupId="";
	private String strRpGroupId="";
	private String strRpGroupNameCombo="";
	private String strRpSubGroupNameCombo="";
	private String strErr = "";
	private String strMsg = "";
	private String strWarning = "";
	private String strHospitalCode = "";
	private String strRemarks = "";
	private String strEffectiveFrom = "";
	private String strSeatId = "";
	private String strIsValid = "";
	private String strCtDate = "";
	private String strGroupNameCombo = "";
	private String[] strLeftItemIds=null;
	private String strLeftItemList="";
	private String[] strRightItemIds=null;
	private String strRightItemList="";
	private String[] strLeftNewItemIds=null;
	private String strLeftNewItemList="";
	private String[] strRightNewItemIds=null;
	private String strRightNewItemList="";
	private String strRpSubGroupId="";
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getStrItemCatCombo() {
		return strItemCatCombo;
	}
	public void setStrItemCatCombo(String strItemCatCombo) {
		this.strItemCatCombo = strItemCatCombo;
	}
	public String getStrItemCat() {
		return strItemCat;
	}
	public void setStrItemCat(String strItemCat) {
		this.strItemCat = strItemCat;
	}
	public String getStrExGroupId() {
		return strExGroupId;
	}
	public void setStrExGroupId(String strExGroupId) {
		this.strExGroupId = strExGroupId;
	}
	public String getStrRpGroupId() {
		return strRpGroupId;
	}
	public void setStrRpGroupId(String strRpGroupId) {
		this.strRpGroupId = strRpGroupId;
	}
	public String getStrRpGroupNameCombo() {
		return strRpGroupNameCombo;
	}
	public void setStrRpGroupNameCombo(String strRpGroupNameCombo) {
		this.strRpGroupNameCombo = strRpGroupNameCombo;
	}
	public String getStrRpSubGroupNameCombo() {
		return strRpSubGroupNameCombo;
	}
	public void setStrRpSubGroupNameCombo(String strRpSubGroupNameCombo) {
		this.strRpSubGroupNameCombo = strRpSubGroupNameCombo;
	}
	public String getStrErr() {
		return strErr;
	}
	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
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
	public String getStrCtDate() {
		return strCtDate;
	}
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	public String getStrGroupNameCombo() {
		return strGroupNameCombo;
	}
	public void setStrGroupNameCombo(String strGroupNameCombo) {
		this.strGroupNameCombo = strGroupNameCombo;
	}
	public String[] getStrLeftItemIds() {
		return strLeftItemIds;
	}
	public void setStrLeftItemIds(String[] strLeftItemIds) {
		this.strLeftItemIds = strLeftItemIds;
	}
	public String getStrLeftItemList() {
		return strLeftItemList;
	}
	public void setStrLeftItemList(String strLeftItemList) {
		this.strLeftItemList = strLeftItemList;
	}
	public String[] getStrRightItemIds() {
		return strRightItemIds;
	}
	public void setStrRightItemIds(String[] strRightItemIds) {
		this.strRightItemIds = strRightItemIds;
	}
	public String getStrRightItemList() {
		return strRightItemList;
	}
	public void setStrRightItemList(String strRightItemList) {
		this.strRightItemList = strRightItemList;
	}
	public String[] getStrLeftNewItemIds() {
		return strLeftNewItemIds;
	}
	public void setStrLeftNewItemIds(String[] strLeftNewItemIds) {
		this.strLeftNewItemIds = strLeftNewItemIds;
	}
	public String getStrLeftNewItemList() {
		return strLeftNewItemList;
	}
	public void setStrLeftNewItemList(String strLeftNewItemList) {
		this.strLeftNewItemList = strLeftNewItemList;
	}
	public String[] getStrRightNewItemIds() {
		return strRightNewItemIds;
	}
	public void setStrRightNewItemIds(String[] strRightNewItemIds) {
		this.strRightNewItemIds = strRightNewItemIds;
	}
	public String getStrRightNewItemList() {
		return strRightNewItemList;
	}
	public void setStrRightNewItemList(String strRightNewItemList) {
		this.strRightNewItemList = strRightNewItemList;
	}
	public String getStrRpSubGroupId() {
		return strRpSubGroupId;
	}
	public void setStrRpSubGroupId(String strRpSubGroupId) {
		this.strRpSubGroupId = strRpSubGroupId;
	}
}