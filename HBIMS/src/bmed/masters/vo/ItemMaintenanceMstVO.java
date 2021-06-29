package bmed.masters.vo;



import hisglobal.utility.TransferObject;

import javax.sql.rowset.WebRowSet;

public class ItemMaintenanceMstVO  implements TransferObject
{
	/** The Constant serialVersionUID. */
private static final long serialVersionUID = 02L;

/** The B exist status. */
private Boolean BExistStatus = false;

/** The str msg string. */
private String strMsgString = "";

/** The str msg type. */
private String strMsgType = "0";

/** The str hospital code. */
private String strHospitalCode;

/** The str chk. */
private String strChk;

/** The str remarks. */
private String strRemarks = "";

/** The str effective from. */
private String strEffectiveFrom = "";

private String strLstmodSeatId = "";

/** The str entry date. */
private String strEntryDate = "";

/** The str seat id. */
private String strSeatId = "";

/** The str is valid. */
private String strIsValid = "";

/** The str ct date. */
private String strCtDate = "";

/** The str msg. */
private String strMsg = "";

/** The str warning. */
private String strWarning = "";

/** The str errmsg. */
private String strErrmsg = "";

/** The str left item list. */
private String strLeftItemList = "";

/** The str right item list. */
private String strRightItemList = "";

/** The str right item ids. */
private String[] strRightItemIds = null;

/** The str left item ids. */
private String[] strLeftItemIds = null;

private String strFromHour;

private String strFromMin;

private String strToHour;
private String strDeptID;
private String strToMin;
private String strDtl;
private String strMaintenancePeriodInDays;
private String strUnitId;
private String strTaskId;

private String strStoreId;

private String strStoreName;

private String strItemCatgId;

private String strEnggItemTypeId;

private String strEnggItemSubTypeId;

private String strItemid;

private String strBatchNo;

private String strSerialNo;

private String strMaintenanceId;

private String strMaintenanceDesc;

private String strAvalaibleTaskList;

private String strSelectedTaskList;

private String strPreferTimeTo;

private String strPreferTimeFrom;

private String strMaintenancePeriod;

private String strMaintenancePeriodUnit;

private String strAlertDuration;

private String 	strItemCategoryCmb;

private String strEnggItemTypeCmb;


private String strEnggItemSubTypeCmb;

private String strItemNameCmb;

private String strBatchNoCmb;

private String strSerialNoCmb;

private String strMaintenanceCmb;

private String strComplaintFlg;

private String strItemBrandId;


private WebRowSet wsUnitName = null;

private WebRowSet wsItemCatgName = null;

private WebRowSet wsItemName = null;

private WebRowSet wsBatchName = null;

private WebRowSet wsTaskHlp = null;

private WebRowSet wsItemSlNo = null;

private WebRowSet wsMaintenanceDtl = null;

private WebRowSet wsWarrantyDtl = null;


private WebRowSet wsEnggItemSubType = null;

private WebRowSet wsEnggItemType = null;

private WebRowSet wsMaintenanceCmb = null;

private WebRowSet wsLeftListBox = null;

private WebRowSet wsRightListBox = null;


public String getStrComplaintFlg() {
	return strComplaintFlg;
}

public void setStrComplaintFlg(String strComplaintFlg) {
	this.strComplaintFlg = strComplaintFlg;
}



public String getStrStoreId() {
	return strStoreId;
}

public void setStrStoreId(String strStoreId) {
	this.strStoreId = strStoreId;
}

public String getStrStoreName() {
	return strStoreName;
}

public void setStrStoreName(String strStoreName) {
	this.strStoreName = strStoreName;
}

public String getStrItemCatgId() {
	return strItemCatgId;
}

public void setStrItemCatgId(String strItemCatgId) {
	this.strItemCatgId = strItemCatgId;
}

public String getStrEnggItemTypeId() {
	return strEnggItemTypeId;
}

public void setStrEnggItemTypeId(String strEnggItemTypeId) {
	this.strEnggItemTypeId = strEnggItemTypeId;
}

public String getStrEnggItemSubTypeId() {
	return strEnggItemSubTypeId;
}

public void setStrEnggItemSubTypeId(String strEnggItemSubTypeId) {
	this.strEnggItemSubTypeId = strEnggItemSubTypeId;
}

public String getStrItemid() {
	return strItemid;
}

public void setStrItemid(String strItemid) {
	this.strItemid = strItemid;
}

public String getStrBatchNo() {
	return strBatchNo;
}

public void setStrBatchNo(String strBatchNo) {
	this.strBatchNo = strBatchNo;
}

public String getStrSerialNo() {
	return strSerialNo;
}

public void setStrSerialNo(String strSerialNo) {
	this.strSerialNo = strSerialNo;
}

public String getStrMaintenanceId() {
	return strMaintenanceId;
}

public void setStrMaintenanceId(String strMaintenanceId) {
	this.strMaintenanceId = strMaintenanceId;
}

public String getStrMaintenanceDesc() {
	return strMaintenanceDesc;
}

public void setStrMaintenanceDesc(String strMaintenanceDesc) {
	this.strMaintenanceDesc = strMaintenanceDesc;
}

public String getStrAvalaibleTaskList() {
	return strAvalaibleTaskList;
}

public void setStrAvalaibleTaskList(String strAvalaibleTaskList) {
	this.strAvalaibleTaskList = strAvalaibleTaskList;
}

public String getStrSelectedTaskList() {
	return strSelectedTaskList;
}

public void setStrSelectedTaskList(String strSelectedTaskList) {
	this.strSelectedTaskList = strSelectedTaskList;
}

public String getStrPreferTimeTo() {
	return strPreferTimeTo;
}

public void setStrPreferTimeTo(String strPreferTimeTo) {
	this.strPreferTimeTo = strPreferTimeTo;
}

public String getStrPreferTimeFrom() {
	return strPreferTimeFrom;
}

public void setStrPreferTimeFrom(String strPreferTimeFrom) {
	this.strPreferTimeFrom = strPreferTimeFrom;
}

public String getStrMaintenancePeriod() {
	return strMaintenancePeriod;
}

public void setStrMaintenancePeriod(String strMaintenancePeriod) {
	this.strMaintenancePeriod = strMaintenancePeriod;
}

public String getStrMaintenancePeriodUnit() {
	return strMaintenancePeriodUnit;
}

public void setStrMaintenancePeriodUnit(String strMaintenancePeriodUnit) {
	this.strMaintenancePeriodUnit = strMaintenancePeriodUnit;
}

public String getStrAlertDuration() {
	return strAlertDuration;
}

public void setStrAlertDuration(String strAlertDuration) {
	this.strAlertDuration = strAlertDuration;
}

public Boolean getBExistStatus() {
	return BExistStatus;
}

public void setBExistStatus(Boolean existStatus) {
	BExistStatus = existStatus;
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

public String getStrLstmodSeatId() {
	return strLstmodSeatId;
}

public void setStrLstmodSeatId(String strLstmodSeatId) {
	this.strLstmodSeatId = strLstmodSeatId;
}

public String getStrEntryDate() {
	return strEntryDate;
}

public void setStrEntryDate(String strEntryDate) {
	this.strEntryDate = strEntryDate;
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

public String getStrErrmsg() {
	return strErrmsg;
}

public void setStrErrmsg(String strErrmsg) {
	this.strErrmsg = strErrmsg;
}

public String getStrItemCategoryCmb() {
	return strItemCategoryCmb;
}

public void setStrItemCategoryCmb(String strItemCategoryCmb) {
	this.strItemCategoryCmb = strItemCategoryCmb;
}

public String getStrEnggItemTypeCmb() {
	return strEnggItemTypeCmb;
}

public void setStrEnggItemTypeCmb(String strEnggItemTypeCmb) {
	this.strEnggItemTypeCmb = strEnggItemTypeCmb;
}

public String getStrEnggItemSubTypeCmb() {
	return strEnggItemSubTypeCmb;
}

public void setStrEnggItemSubTypeCmb(String strEnggItemSubTypeCmb) {
	this.strEnggItemSubTypeCmb = strEnggItemSubTypeCmb;
}

public String getStrItemNameCmb() {
	return strItemNameCmb;
}

public void setStrItemNameCmb(String strItemNameCmb) {
	this.strItemNameCmb = strItemNameCmb;
}

public String getStrBatchNoCmb() {
	return strBatchNoCmb;
}

public void setStrBatchNoCmb(String strBatchNoCmb) {
	this.strBatchNoCmb = strBatchNoCmb;
}

public String getStrSerialNoCmb() {
	return strSerialNoCmb;
}

public void setStrSerialNoCmb(String strSerialNoCmb) {
	this.strSerialNoCmb = strSerialNoCmb;
}

public String getStrMaintenanceCmb() {
	return strMaintenanceCmb;
}

public void setStrMaintenanceCmb(String strMaintenanceCmb) {
	this.strMaintenanceCmb = strMaintenanceCmb;
}

public String getStrLeftItemList() {
	return strLeftItemList;
}

public void setStrLeftItemList(String strLeftItemList) {
	this.strLeftItemList = strLeftItemList;
}

public String getStrRightItemList() {
	return strRightItemList;
}

public void setStrRightItemList(String strRightItemList) {
	this.strRightItemList = strRightItemList;
}

public String[] getStrRightItemIds() {
	return strRightItemIds;
}

public void setStrRightItemIds(String[] strRightItemIds) {
	this.strRightItemIds = strRightItemIds;
}

public String[] getStrLeftItemIds() {
	return strLeftItemIds;
}

public void setStrLeftItemIds(String[] strLeftItemIds) {
	this.strLeftItemIds = strLeftItemIds;
}

public WebRowSet getWsItemCatgName() {
	return wsItemCatgName;
}

public void setWsItemCatgName(WebRowSet wsItemCatgName) {
	this.wsItemCatgName = wsItemCatgName;
}

public WebRowSet getWsItemName() {
	return wsItemName;
}

public void setWsItemName(WebRowSet wsItemName) {
	this.wsItemName = wsItemName;
}

public WebRowSet getWsBatchName() {
	return wsBatchName;
}

public void setWsBatchName(WebRowSet wsBatchName) {
	this.wsBatchName = wsBatchName;
}

public WebRowSet getWsItemSlNo() {
	return wsItemSlNo;
}

public void setWsItemSlNo(WebRowSet wsItemSlNo) {
	this.wsItemSlNo = wsItemSlNo;
}

public WebRowSet getWsEnggItemSubType() {
	return wsEnggItemSubType;
}

public void setWsEnggItemSubType(WebRowSet wsEnggItemSubType) {
	this.wsEnggItemSubType = wsEnggItemSubType;
}

public WebRowSet getWsEnggItemType() {
	return wsEnggItemType;
}

public void setWsEnggItemType(WebRowSet wsEnggItemType) {
	this.wsEnggItemType = wsEnggItemType;
}

public WebRowSet getWsMaintenanceCmb() {
	return wsMaintenanceCmb;
}

public void setWsMaintenanceCmb(WebRowSet wsMaintenanceCmb) {
	this.wsMaintenanceCmb = wsMaintenanceCmb;
}

public WebRowSet getWsLeftListBox() {
	return wsLeftListBox;
}

public void setWsLeftListBox(WebRowSet wsLeftListBox) {
	this.wsLeftListBox = wsLeftListBox;
}

public WebRowSet getWsRightListBox() {
	return wsRightListBox;
}

public void setWsRightListBox(WebRowSet wsRightListBox) {
	this.wsRightListBox = wsRightListBox;
}

public WebRowSet getWsUnitName() {
	return wsUnitName;
}

public void setWsUnitName(WebRowSet wsUnitName) {
	this.wsUnitName = wsUnitName;
}

public String getStrFromHour() {
	return strFromHour;
}

public void setStrFromHour(String strFromHour) {
	this.strFromHour = strFromHour;
}

public String getStrFromMin() {
	return strFromMin;
}

public void setStrFromMin(String strFromMin) {
	this.strFromMin = strFromMin;
}

public String getStrToHour() {
	return strToHour;
}

public void setStrToHour(String strToHour) {
	this.strToHour = strToHour;
}

public String getStrToMin() {
	return strToMin;
}

public void setStrToMin(String strToMin) {
	this.strToMin = strToMin;
}

public String getStrMaintenancePeriodInDays() {
	return strMaintenancePeriodInDays;
}

public void setStrMaintenancePeriodInDays(String strMaintenancePeriodInDays) {
	this.strMaintenancePeriodInDays = strMaintenancePeriodInDays;
}

public String getStrUnitId() {
	return strUnitId;
}

public void setStrUnitId(String strUnitId) {
	this.strUnitId = strUnitId;
}

public String getStrChk() {
	return strChk;
}

public void setStrChk(String strChk) {
	this.strChk = strChk;
}

public String getStrTaskId() {
	return strTaskId;
}

public void setStrTaskId(String strTaskId) {
	this.strTaskId = strTaskId;
}

public WebRowSet getWsTaskHlp() {
	return wsTaskHlp;
}

public void setWsTaskHlp(WebRowSet wsTaskHlp) {
	this.wsTaskHlp = wsTaskHlp;
}

public String getStrDtl() {
	return strDtl;
}

public void setStrDtl(String strDtl) {
	this.strDtl = strDtl;
}

public String getStrItemBrandId() {
	return strItemBrandId;
}

public void setStrItemBrandId(String strItemBrandId) {
	this.strItemBrandId = strItemBrandId;
}

public String getStrDeptID() {
	return strDeptID;
}

public void setStrDeptID(String strDeptID) {
	this.strDeptID = strDeptID;
}

public WebRowSet getWsMaintenanceDtl() {
	return wsMaintenanceDtl;
}

public void setWsMaintenanceDtl(WebRowSet wsMaintenanceDtl) {
	this.wsMaintenanceDtl = wsMaintenanceDtl;
}

public WebRowSet getWsWarrantyDtl() {
	return wsWarrantyDtl;
}

public void setWsWarrantyDtl(WebRowSet wsWarrantyDtl) {
	this.wsWarrantyDtl = wsWarrantyDtl;
}

}
