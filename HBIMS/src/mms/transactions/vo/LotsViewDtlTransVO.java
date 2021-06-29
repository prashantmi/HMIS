package mms.transactions.vo;

import javax.sql.rowset.WebRowSet;

public class LotsViewDtlTransVO {
	
	private String strErrMsg = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";
	private String strHospitalCode = "";
	private String strAgendaNo = "";
	private String strStoreId = "";
	private String strStoreName = "";
	private String strItemCategoryName= "";
	private String strItemCategoryNo= "";
	private String strItemId="";
	private String strItemBrandId= "";
	private String strItemName= "";
	private String strItemBrandName= "";
	private String strCondemnationType= "";
	private String strCancelRemarks= "";
	private String strCancelDate= "";
	private String strCancelBy="";
	private String strPath="";
	private String strSetRequestDetails = "";
	private String strMsgString="";
	private String strMsgType="";
	private String strSetItemDetails = "";
	private WebRowSet itemDtlWS=null;
	public WebRowSet getItemDtlWS() {
		return itemDtlWS;
	}
	public void setItemDtlWS(WebRowSet itemDtlWS) {
		this.itemDtlWS = itemDtlWS;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrAgendaNo() {
		return strAgendaNo;
	}
	public void setStrAgendaNo(String strAgendaNo) {
		this.strAgendaNo = strAgendaNo;
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
	public String getStrItemCategoryName() {
		return strItemCategoryName;
	}
	public void setStrItemCategoryName(String strItemCategoryName) {
		this.strItemCategoryName = strItemCategoryName;
	}
	public String getStrItemCategoryNo() {
		return strItemCategoryNo;
	}
	public void setStrItemCategoryNo(String strItemCategoryNo) {
		this.strItemCategoryNo = strItemCategoryNo;
	}
	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrItemBrandId() {
		return strItemBrandId;
	}
	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}
	public String getStrItemName() {
		return strItemName;
	}
	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName;
	}
	public String getStrItemBrandName() {
		return strItemBrandName;
	}
	public void setStrItemBrandName(String strItemBrandName) {
		this.strItemBrandName = strItemBrandName;
	}
	public String getStrCondemnationType() {
		return strCondemnationType;
	}
	public void setStrCondemnationType(String strCondemnationType) {
		this.strCondemnationType = strCondemnationType;
	}
	public String getStrCancelRemarks() {
		return strCancelRemarks;
	}
	public void setStrCancelRemarks(String strCancelRemarks) {
		this.strCancelRemarks = strCancelRemarks;
	}
	public String getStrCancelDate() {
		return strCancelDate;
	}
	public void setStrCancelDate(String strCancelDate) {
		this.strCancelDate = strCancelDate;
	}
	public String getStrPath() {
		return strPath;
	}
	public void setStrPath(String strPath) {
		this.strPath = strPath;
	}
	public String getStrSetRequestDetails() {
		return strSetRequestDetails;
	}
	public void setStrSetRequestDetails(String strSetRequestDetails) {
		this.strSetRequestDetails = strSetRequestDetails;
	}
	public String getStrSetItemDetails() {
		return strSetItemDetails;
	}
	public void setStrSetItemDetails(String strSetItemDetails) {
		this.strSetItemDetails = strSetItemDetails;
	}
	public String getStrCancelBy() {
		return strCancelBy;
	}
	public void setStrCancelBy(String strCancelBy) {
		this.strCancelBy = strCancelBy;
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

}
