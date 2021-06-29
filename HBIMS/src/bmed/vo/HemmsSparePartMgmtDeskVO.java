package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class HemmsSparePartMgmtDeskVO 
{
	private String strItemId;             
	private String strStoreId;          
	private String strItemCategoryId;
	private String strSparePartId;
	private String strSparePartSerialNo;
	private String strManufacturerId;
	private String strBatchNo;
	private String strGroupName;
	private String strSubGroupName;
	private String strEquipName;
	private String strSparePartName;
	private String strSupplierName;
	private String strRate;
	private String strRateUnitId;
	private String strRecievedDate;
	private String strRecievedQty;
	private String strRecievedQtyUnit;
	private String strWarrantyFromDate;
	private String strWarrantyUpto;     
	private String strWarrantyUptoUnit;
	private String strSpecification; 
	private String strBidNo;
	private String strBidNoDate;
	private String strPONo;
	private String strPODate;
	private String strBillNo;
	private String strBillDate;
	private String strPageNo;
	private String strMsgType;
	private String strIsvalid;             
	private String strSeatid;        
	private String strMode;
	private String strMsgString;
	private String strHospitalCode;       
	private String strRecievedNo;
	
	private WebRowSet wsEquipGroupNameCmb;
	private WebRowSet wsEquipSubGroupNameCmb;
	private WebRowSet wsEquipNameCmb;
	private WebRowSet wsSparePartNameCmb;
	private WebRowSet wsManufactureCmb;
	private WebRowSet wsSupplierNameCmb;
	
	private WebRowSet wrsData;
	private WebRowSet wrsValueData;
	
	private String strStatus;
	
	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	/*
	 * Getters and Setters for the above Attributes
	 */
	public String getStrMode() {
		return strMode;
	}

	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}

	public String getStrItemId() {
		return strItemId;
	}

	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}

	public String getStrStoreId() {
		return strStoreId;
	}

	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}


	public String getStrWarrantyUpto() {
		return strWarrantyUpto;
	}

	public void setStrWarrantyUpto(String strWarrantyUpto) {
		this.strWarrantyUpto = strWarrantyUpto;
	}

	public String getStrWarrantyUptoUnit() {
		return strWarrantyUptoUnit;
	}

	public void setStrWarrantyUptoUnit(String strWarrantyUptoUnit) {
		this.strWarrantyUptoUnit = strWarrantyUptoUnit;
	}

	public String getStrSpecification() {
		return strSpecification;
	}

	public void setStrSpecification(String strSpecification) {
		this.strSpecification = strSpecification;
	}

	public String getStrIsvalid() {
		return strIsvalid;
	}

	public void setStrIsvalid(String strIsvalid) {
		this.strIsvalid = strIsvalid;
	}

	public String getStrSeatid() {
		return strSeatid;
	}

	public void setStrSeatid(String strSeatid) {
		this.strSeatid = strSeatid;
	}

	public WebRowSet getWrsData() {
		return wrsData;
	}

	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
	}

	public WebRowSet getWrsValueData() {
		return wrsValueData;
	}

	public void setWrsValueData(WebRowSet wrsValueData) {
		this.wrsValueData = wrsValueData;
	}

	public String getStrItemCategoryId() {
		return strItemCategoryId;
	}

	public void setStrItemCategoryId(String strItemCategoryId) {
		this.strItemCategoryId = strItemCategoryId;
	}


	public String getStrSparePartId() {
		return strSparePartId;
	}

	public void setStrSparePartId(String strSparePartId) {
		this.strSparePartId = strSparePartId;
	}

	public String getStrSparePartSerialNo() {
		return strSparePartSerialNo;
	}

	public void setStrSparePartSerialNo(String strSparePartSerialNo) {
		this.strSparePartSerialNo = strSparePartSerialNo;
	}

	public String getStrManufacturerId() {
		return strManufacturerId;
	}

	public void setStrManufacturerId(String strManufacturerId) {
		this.strManufacturerId = strManufacturerId;
	}

	public String getStrWarrantyFromDate() {
		return strWarrantyFromDate;
	}

	public void setStrWarrantyFromDate(String strWarrantyFromDate) {
		this.strWarrantyFromDate = strWarrantyFromDate;
	}


	public WebRowSet getWsEquipGroupNameCmb() {
		return wsEquipGroupNameCmb;
	}

	public void setWsEquipGroupNameCmb(WebRowSet wsEquipGroupNameCmb) {
		this.wsEquipGroupNameCmb = wsEquipGroupNameCmb;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrGroupName() {
		return strGroupName;
	}

	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}

	public WebRowSet getWsEquipNameCmb() {
		return wsEquipNameCmb;
	}

	public void setWsEquipNameCmb(WebRowSet wsEquipNameCmb) {
		this.wsEquipNameCmb = wsEquipNameCmb;
	}

	public String getStrEquipName() {
		return strEquipName;
	}

	public void setStrEquipName(String strEquipName) {
		this.strEquipName = strEquipName;
	}

	public WebRowSet getWsSparePartNameCmb() {
		return wsSparePartNameCmb;
	}

	public void setWsSparePartNameCmb(WebRowSet wsSparePartNameCmb) {
		this.wsSparePartNameCmb = wsSparePartNameCmb;
	}

	public String getStrSparePartName() {
		return strSparePartName;
	}

	public void setStrSparePartName(String strSparePartName) {
		this.strSparePartName = strSparePartName;
	}

	public WebRowSet getWsManufactureCmb() {
		return wsManufactureCmb;
	}

	public void setWsManufactureCmb(WebRowSet wsManufactureCmb) {
		this.wsManufactureCmb = wsManufactureCmb;
	}

	public String getStrSupplierName() {
		return strSupplierName;
	}

	public void setStrSupplierName(String strSupplierName) {
		this.strSupplierName = strSupplierName;
	}

	public WebRowSet getWsSupplierNameCmb() {
		return wsSupplierNameCmb;
	}

	public void setWsSupplierNameCmb(WebRowSet wsSupplierNameCmb) {
		this.wsSupplierNameCmb = wsSupplierNameCmb;
	}

	public String getStrRate() {
		return strRate;
	}

	public void setStrRate(String strRate) {
		this.strRate = strRate;
	}

	public String getStrRateUnitId() {
		return strRateUnitId;
	}

	public void setStrRateUnitId(String strRateUnitId) {
		this.strRateUnitId = strRateUnitId;
	}

	public String getStrRecievedDate() {
		return strRecievedDate;
	}

	public void setStrRecievedDate(String strRecievedDate) {
		this.strRecievedDate = strRecievedDate;
	}

	public String getStrBidNo() {
		return strBidNo;
	}

	public void setStrBidNo(String strBidNo) {
		this.strBidNo = strBidNo;
	}

	public String getStrBidNoDate() {
		return strBidNoDate;
	}

	public void setStrBidNoDate(String strBidNoDate) {
		this.strBidNoDate = strBidNoDate;
	}

	public String getStrPONo() {
		return strPONo;
	}

	public void setStrPONo(String strPONo) {
		this.strPONo = strPONo;
	}

	public String getStrPODate() {
		return strPODate;
	}

	public void setStrPODate(String strPODate) {
		this.strPODate = strPODate;
	}

	public String getStrBillNo() {
		return strBillNo;
	}

	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}

	public String getStrBillDate() {
		return strBillDate;
	}

	public void setStrBillDate(String strBillDate) {
		this.strBillDate = strBillDate;
	}

	public String getStrPageNo() {
		return strPageNo;
	}

	public void setStrPageNo(String strPageNo) {
		this.strPageNo = strPageNo;
	}

	public WebRowSet getWsEquipSubGroupNameCmb() {
		return wsEquipSubGroupNameCmb;
	}

	public void setWsEquipSubGroupNameCmb(WebRowSet wsEquipSubGroupNameCmb) {
		this.wsEquipSubGroupNameCmb = wsEquipSubGroupNameCmb;
	}

	public String getStrBatchNo() {
		return strBatchNo;
	}

	public void setStrBatchNo(String strBatchNo) {
		this.strBatchNo = strBatchNo;
	}

	public String getStrSubGroupName() {
		return strSubGroupName;
	}

	public void setStrSubGroupName(String strSubGroupName) {
		this.strSubGroupName = strSubGroupName;
	}

	public String getStrRecievedQty() {
		return strRecievedQty;
	}

	public void setStrRecievedQty(String strRecievedQty) {
		this.strRecievedQty = strRecievedQty;
	}

	public String getStrRecievedQtyUnit() {
		return strRecievedQtyUnit;
	}

	public void setStrRecievedQtyUnit(String strRecievedQtyUnit) {
		this.strRecievedQtyUnit = strRecievedQtyUnit;
	}

	public String getStrRecievedNo() {
		return strRecievedNo;
	}

	public void setStrRecievedNo(String strRecievedNo) {
		this.strRecievedNo = strRecievedNo;
	}

 	

}
