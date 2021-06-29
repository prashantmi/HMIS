package bmed.masters.controller.fb;

import hisglobal.masterutil.GenericFormBean;

public class ItemMaintenanceMstFB extends GenericFormBean 
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;
	
	/** The str err. */
	private String strErr;
	
	/** The str msg. */
	private String strMsg;
	
	/** The str warning. */
	private String strWarning;
	
	/** The str msg string. */
	private String strMsgString;
	
	/** The str msg type. */
	private String strMsgType;
	
	/** The str hospital code. */
	private String strHospitalCode;
	
	
	/** The str remarks. */
	private String strRemarks;
	
	/** The str effective from. */
	private String strEffectiveFrom;
	
	/** The str ct date. */
	private String strCtDate;
	
	/** The str chk. */
	private String strChk;
	
	/** The str is valid. */
	private String strIsValid;
	
	private String strSeatId = "";
	
	/** The str left item list. */
	private String strLeftItemList;
	
	/** The str right item list. */
	private String strRightItemList;
	
	/** The str right item ids. */
	private String[] strRightItemIds = null;
	
	/** The str left item ids. */
	private String[] strLeftItemIds = null;
	
	private String strTempCmbValue;
	
	private String strStoreIdValue;
	
	private String strTaskHlp;
	
	private String strFromHour;
	
	private String strTaskId;
	
	private String strFromMin;
	
	private String strToHour;
	
	private String strToMin;
	
	private String strStoreId;
	
	private String strStoreName;
	
	private String strItemCatgId;
	
	private String strEnggItemTypeId;
	
	private String strEnggItemSubTypeId;
	
	private String strItemid;
	
	private String strGetView;
	
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
	
	private String strUnitId;
	
	private String strUnitIdCmb;
	
	private String strEnggItemSubTypeCmb;
	
	private String strItemNameCmb;
	
	private String strBatchNoCmb;
	
	private String strSerialNoCmb;
	
	private String strMaintenanceCmb;
	
	private String strMaintenancePeriodInDays;
	
	private String strComplaintFlg;
	
	private String strDeptID;

	private String strItemBrandId;
	
	private String strStockInfoVal;
	
	private String strIsExternal;
	
	private String strIsInternal;
	
	private String strUploadFileId;
	
	public String getStrUploadFileId() {
		return strUploadFileId;
	}

	public void setStrUploadFileId(String strUploadFileId) {
		this.strUploadFileId = strUploadFileId;
	}

	public String getStrIsExternal() {
		return strIsExternal;
	}

	public void setStrIsExternal(String strIsExternal) {
		this.strIsExternal = strIsExternal;
	}

	public String getStrIsInternal() {
		return strIsInternal;
	}

	public void setStrIsInternal(String strIsInternal) {
		this.strIsInternal = strIsInternal;
	}

	public String getStrItemBrandId() {
		return strItemBrandId;
	}

	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}

	public String getStrComplaintFlg() {
		return strComplaintFlg;
	}

	public void setStrComplaintFlg(String strComplaintFlg) {
		this.strComplaintFlg = strComplaintFlg;
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

	public String getStrCtDate() {
		return strCtDate;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public String getStrChk() {
		return strChk;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
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

	public String getStrUnitId() {
		return strUnitId;
	}

	public void setStrUnitId(String strUnitId) {
		this.strUnitId = strUnitId;
	}

	public String getStrUnitIdCmb() {
		return strUnitIdCmb;
	}

	public void setStrUnitIdCmb(String strUnitIdCmb) {
		this.strUnitIdCmb = strUnitIdCmb;
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

	public String getStrMaintenancePeriodInDays() {
		return strMaintenancePeriodInDays;
	}

	public void setStrMaintenancePeriodInDays(String strMaintenancePeriodInDays) {
		this.strMaintenancePeriodInDays = strMaintenancePeriodInDays;
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

	public String getStrStoreIdValue() {
		return strStoreIdValue;
	}

	public void setStrStoreIdValue(String strStoreIdValue) {
		this.strStoreIdValue = strStoreIdValue;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrTempCmbValue() {
		return strTempCmbValue;
	}

	public void setStrTempCmbValue(String strTempCmbValue) {
		this.strTempCmbValue = strTempCmbValue;
	}

	public String getStrTaskId() {
		return strTaskId;
	}

	public void setStrTaskId(String strTaskId) {
		this.strTaskId = strTaskId;
	}

	public String getStrGetView() {
		return strGetView;
	}

	public void setStrGetView(String strGetView) {
		this.strGetView = strGetView;
	}

	public String getStrTaskHlp() {
		return strTaskHlp;
	}

	public void setStrTaskHlp(String strTaskHlp) {
		this.strTaskHlp = strTaskHlp;
	}

	public String getStrDeptID() {
		return strDeptID;
	}

	public void setStrDeptID(String strDeptID) {
		this.strDeptID = strDeptID;
	}

	public String getStrStockInfoVal() {
		return strStockInfoVal;
	}

	public void setStrStockInfoVal(String strStockInfoVal) {
		this.strStockInfoVal = strStockInfoVal;
	}
}
	