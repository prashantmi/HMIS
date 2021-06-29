package bmed.transactions.controller.fb;

import org.apache.struts.action.ActionForm;

public class OfflineSparePartAddTransFB extends ActionForm {
	
	
	
	/**
	 * Serial Version Id
	 */
	private static final long serialVersionUID = -469345547387402918L;
	
	
	private String strDeptCode;
	private String strDeptNameCombo;
	private String strStoreId;
	private String strStoreNameCombo;
	private String strEngineeringItemTypeId;
	private String strEngineeringItemTypeCmb;
	private String strEngineeringItemSubTypeId;
	private String strEngineeringItemSubTypeCmb;
	private String strItemCategoryId;
	private String strItemCategoryNameCombo;	
	private String strItemId;
	private String strItemNameCombo;
	
	private String strItemManufacturerSerialNo;
	
	private String ManufactNo;// Extra Field for trapping the item Manufacturer Sl No. from the Stock Details
	
	private String strSparePartId;
	private String strSparePartNameCombo;
	private String strSparePartSerialNo;
	private String strManufacturerId;
	private String strManufacturerNameCombo;
	private String strManufacturerSerialNo;
	
	private String strSparePartStockDetails;
	private String strSparePartStockDetailsTable;
	
	private String strWarrantyFromDate;
	private String strWarrantyUpto;
	private String strUnitId;
	private String strUnitNameCombo;
	private String strSpecification;
	private String strPerformedDate;
	
	
	private String strHospitalCode;
	private String strIsValid;
	private String strSeatId;
	
	private String strErrMsg;
	private String strNormalMsg;
	private String strWarningMsg;
	
	private String strStockInfoVal;
	
	/*
	 * Getters and Setters for the above Attributes
	 */
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrDeptNameCombo() {
		return strDeptNameCombo;
	}
	public void setStrDeptNameCombo(String strDeptNameCombo) {
		this.strDeptNameCombo = strDeptNameCombo;
	}
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public String getStrStoreNameCombo() {
		return strStoreNameCombo;
	}
	public void setStrStoreNameCombo(String strStoreNameCombo) {
		this.strStoreNameCombo = strStoreNameCombo;
	}
	public String getStrEngineeringItemTypeId() {
		return strEngineeringItemTypeId;
	}
	public void setStrEngineeringItemTypeId(String strEngineeringItemTypeId) {
		this.strEngineeringItemTypeId = strEngineeringItemTypeId;
	}
	public String getStrEngineeringItemTypeCmb() {
		return strEngineeringItemTypeCmb;
	}
	public void setStrEngineeringItemTypeCmb(String strEngineeringItemTypeCmb) {
		this.strEngineeringItemTypeCmb = strEngineeringItemTypeCmb;
	}
	public String getStrEngineeringItemSubTypeId() {
		return strEngineeringItemSubTypeId;
	}
	public void setStrEngineeringItemSubTypeId(String strEngineeringItemSubTypeId) {
		this.strEngineeringItemSubTypeId = strEngineeringItemSubTypeId;
	}
	public String getStrEngineeringItemSubTypeCmb() {
		return strEngineeringItemSubTypeCmb;
	}
	public void setStrEngineeringItemSubTypeCmb(String strEngineeringItemSubTypeCmb) {
		this.strEngineeringItemSubTypeCmb = strEngineeringItemSubTypeCmb;
	}
	public String getStrItemCategoryId() {
		return strItemCategoryId;
	}
	public void setStrItemCategoryId(String strItemCategoryId) {
		this.strItemCategoryId = strItemCategoryId;
	}
	public String getStrItemCategoryNameCombo() {
		return strItemCategoryNameCombo;
	}
	public void setStrItemCategoryNameCombo(String strItemCategoryNameCombo) {
		this.strItemCategoryNameCombo = strItemCategoryNameCombo;
	}
	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrItemNameCombo() {
		return strItemNameCombo;
	}
	public void setStrItemNameCombo(String strItemNameCombo) {
		this.strItemNameCombo = strItemNameCombo;
	}
	public String getStrSparePartId() {
		return strSparePartId;
	}
	public void setStrSparePartId(String strSparePartId) {
		this.strSparePartId = strSparePartId;
	}
	public String getStrSparePartNameCombo() {
		return strSparePartNameCombo;
	}
	public void setStrSparePartNameCombo(String strSparePartNameCombo) {
		this.strSparePartNameCombo = strSparePartNameCombo;
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
	public String getStrManufacturerNameCombo() {
		return strManufacturerNameCombo;
	}
	public void setStrManufacturerNameCombo(String strManufacturerNameCombo) {
		this.strManufacturerNameCombo = strManufacturerNameCombo;
	}
	public String getStrManufacturerSerialNo() {
		return strManufacturerSerialNo;
	}
	public void setStrManufacturerSerialNo(String strManufacturerSerialNo) {
		this.strManufacturerSerialNo = strManufacturerSerialNo;
	}
	public String getStrWarrantyFromDate() {
		return strWarrantyFromDate;
	}
	public void setStrWarrantyFromDate(String strWarrantyFromDate) {
		this.strWarrantyFromDate = strWarrantyFromDate;
	}
	public String getStrWarrantyUpto() {
		return strWarrantyUpto;
	}
	public void setStrWarrantyUpto(String strWarrantyUpto) {
		this.strWarrantyUpto = strWarrantyUpto;
	}
	public String getStrUnitId() {
		return strUnitId;
	}
	public void setStrUnitId(String strUnitId) {
		this.strUnitId = strUnitId;
	}
	public String getStrUnitNameCombo() {
		return strUnitNameCombo;
	}
	public void setStrUnitNameCombo(String strUnitNameCombo) {
		this.strUnitNameCombo = strUnitNameCombo;
	}
	public String getStrSpecification() {
		return strSpecification;
	}
	public void setStrSpecification(String strSpecification) {
		this.strSpecification = strSpecification;
	}
	public String getStrPerformedDate() {
		return strPerformedDate;
	}
	public void setStrPerformedDate(String strPerformedDate) {
		this.strPerformedDate = strPerformedDate;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
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
	public String getStrStockInfoVal() {
		return strStockInfoVal;
	}
	public void setStrStockInfoVal(String strStockInfoVal) {
		this.strStockInfoVal = strStockInfoVal;
	}
	public String getStrItemManufacturerSerialNo() {
		return strItemManufacturerSerialNo;
	}
	public void setStrItemManufacturerSerialNo(String strItemManufacturerSerialNo) {
		this.strItemManufacturerSerialNo = strItemManufacturerSerialNo;
	}
	public String getManufactNo() {
		return ManufactNo;
	}
	public void setManufactNo(String manufactNo) {
		ManufactNo = manufactNo;
	}
	public String getStrSparePartStockDetails() {
		return strSparePartStockDetails;
	}
	public void setStrSparePartStockDetails(String strSparePartStockDetails) {
		this.strSparePartStockDetails = strSparePartStockDetails;
	}
	public String getStrSparePartStockDetailsTable() {
		return strSparePartStockDetailsTable;
	}
	public void setStrSparePartStockDetailsTable(
			String strSparePartStockDetailsTable) {
		this.strSparePartStockDetailsTable = strSparePartStockDetailsTable;
	}
}


