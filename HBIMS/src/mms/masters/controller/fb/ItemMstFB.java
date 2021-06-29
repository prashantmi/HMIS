/**
 * 
 */
package mms.masters.controller.fb;

import org.apache.struts.upload.FormFile;
import hisglobal.masterutil.GenericFormBean;

// TODO: Auto-generated Javadoc
/**
 * Developer : Tanvi Sappal Version : 1.0 Date : 15/May/2009
 */
/**
 * @author deps
 *
 */
public class ItemMstFB extends GenericFormBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;

	/** The str norm mssgstring. */
	private String strNormMssgstring = "";
	
	/** The str warn mssgstring. */
	private String strWarnMssgstring = "";
	
	/** The str err mssgstring. */
	private String strErrMssgstring = "";
	
	/** The str hosp code. */
	private String strHospCode = "0";
	
	/** The str seat id. */
	private String strSeatId = "";
	
	/** The str remarks. */
	private String strRemarks = "";
	
	/** The str effective from. */
	private String strEffectiveFrom = "";
	
	/** The str chk. */
	private String strChk = "";
	
	/** The str is valid. */
	private String strIsValid = "";
	
	/** The str current date. */
	private String strCurrentDate = "";

	/** The str combo values. */
	private String strComboValues = "";

	/** The str item brand id. */
	private String strItemBrandId = "";
	
	/** The str gen item id. */
	private String strGenItemId = "";
	
	/** The str item id. */
	private String strItemId = "";
	
	/** The str item cat no. */
	private String strItemCatNo = "";
	
	/** The str item cat name. */
	private String strItemCatName = "";
	
	/** The str item name. */
	private String strItemName = "";
	
	private String strGenericItem = "";
	
	/** The str gen item name. */
	private String strGenItemValues = "";
	
	/** The str manufacturer id. */
	private String strManufacturerId = "";
	
	/** The str manufacturer name. */
	private String strManufacturerName = "";
	
	/** The str rate unit name. */
	private String strRateUnitName = "";
	
	/** The str default rate. */
	private String strDefaultRate = "";
	
	/** The str rate unit id. */
	private String strRateUnitId = "";
	
	/** The str inventory unit id. */
	private String strInventoryUnitId = "";
	
	/** The str approved type. */
	private String strApprovedType = "";
	
	/** The str issue type. */
	private String strIssueType = "";
	
	/** The str specification. */
	private String strSpecification = "";
	
	/** The str item make. */
	private String strItemMake = "";
	
	/** The str last mod seat id. */
	private String strLastModSeatId = "";
	
	/** The str last mod date. */
	private String strLastModDate = "";
	
	/** The str set sachet flag. */
	private String strSetSachetFlag = "0";
	
	/** The str spare part flag. */
	private String strSparePartFlag = "0";
	
	/** The str item type id. */
	private String strItemTypeId = "";
	
	/** The str item type name. */
	private String strItemTypeName = "";
	
	/** The str is quantified. */
	private String strIsQuantified = "0";
	
	/** The str group id. */
	private String strGroupId = "";
	
	/** The str group name. */
	private String strGroupName = "";

	/** The str item type combo. */
	private String strItemTypeCombo = "";
	
	/** The str manufacturer combo. */
	private String strManufacturerCombo = "";
	
	/** The str rate unit combo. */
	private String strRateUnitCombo = "";

	private String strItemType = "1";
	
	
	private String strNewCPACode=""; 
	
	private String strCPACode="";
	
	private String strPrevCPACode="";
	
	private String strIsItemCodeRequired = "0";
	
	private String strGenItemName="";
	private String strSterilizationFlag="0";
	private String strSterilizationLife;
	private String strUploadFlag="0";
	
	private FormFile strLocation=null;
	private String strFileNo = "";
	private String strPageNo = "";
	private String strFileName = "";
	private String strUploadFileId;
	private String strUploadFileName;
	private String strUploadFileLocation;
	private String strSerialNo;
	private String strGenericItemId;
	
	private String strBatchNo="";
	private String strExpiryDate="";
	private String strManufDate="";
	
	private String strParam="";
	private String strConsumableType="";
	
	
	private String strItemClass="";
	
	private String strHSNCode="";
	private String strIsMisc="0";
	
	

	public String getStrIsMisc() {
		return strIsMisc;
	}

	public void setStrIsMisc(String strIsMisc) {
		this.strIsMisc = strIsMisc;
	}

	public String getStrHSNCode() {
		return strHSNCode;
	}

	public void setStrHSNCode(String strHSNCode) {
		this.strHSNCode = strHSNCode;
	}

	public String getStrItemClass() {
		return strItemClass;
	}

	public void setStrItemClass(String strItemClass) {
		this.strItemClass = strItemClass;
	}

	public String getStrConsumableType() {
		return strConsumableType;
	}

	public void setStrConsumableType(String strConsumableType) {
		this.strConsumableType = strConsumableType;
	}

	public String getStrBatchNo() {
		return strBatchNo;
	}

	public String getStrManufDate() {
		return strManufDate;
	}

	public void setStrManufDate(String strManufDate) {
		this.strManufDate = strManufDate;
	}

	public void setStrBatchNo(String strBatchNo) {
		this.strBatchNo = strBatchNo;
	}

	public String getStrExpiryDate() {
		return strExpiryDate;
	}

	public void setStrExpiryDate(String strExpiryDate) {
		this.strExpiryDate = strExpiryDate;
	}

	public String getStrParam() {
		return strParam;
	}

	public void setStrParam(String strParam) {
		this.strParam = strParam;
	}

	/**
	 * Defines whether this item is branded, non-branded or reserved.
	 * 1 --> Branded
	 * 2 --> Non Branded
	 * 3 --> Reserved
	 */
	private String strBrandReserveFlag ="";
	
	private String strBrandReserveStatus="";
	
	
	private String strApprovedTypeName;
	private String strApprovedTypeOptions;
	
	
	public String getStrGenItemName() {
		return strGenItemName;
	}

	public void setStrGenItemName(String strGenItemName) {
		this.strGenItemName = strGenItemName;
	}

	public String getStrItemType() {
		return strItemType;
	}

	public void setStrItemType(String strItemType) {
		this.strItemType = strItemType;
	}

	/**
	 * Gets the str item type combo.
	 * 
	 * @return the strItemTypeCombo
	 */
	public String getStrItemTypeCombo() {
		return strItemTypeCombo;
	}

	/**
	 * Sets the str item type combo.
	 * 
	 * @param strItemTypeCombo the strItemTypeCombo to set
	 */
	public void setStrItemTypeCombo(String strItemTypeCombo) {
		this.strItemTypeCombo = strItemTypeCombo;
	}

	/**
	 * Gets the str manufacturer combo.
	 * 
	 * @return the strManufacturerCombo
	 */
	public String getStrManufacturerCombo() {
		return strManufacturerCombo;
	}

	/**
	 * Sets the str manufacturer combo.
	 * 
	 * @param strManufacturerCombo the strManufacturerCombo to set
	 */
	public void setStrManufacturerCombo(String strManufacturerCombo) {
		this.strManufacturerCombo = strManufacturerCombo;
	}

	/**
	 * Gets the str rate unit combo.
	 * 
	 * @return the strRateUnitCombo
	 */
	public String getStrRateUnitCombo() {
		return strRateUnitCombo;
	}

	/**
	 * Sets the str rate unit combo.
	 * 
	 * @param strRateUnitCombo the strRateUnitCombo to set
	 */
	public void setStrRateUnitCombo(String strRateUnitCombo) {
		this.strRateUnitCombo = strRateUnitCombo;
	}

	/**
	 * Gets the str norm mssgstring.
	 * 
	 * @return the strNormMssgstring
	 */
	public String getStrNormMssgstring() {
		return strNormMssgstring;
	}

	/**
	 * Sets the str norm mssgstring.
	 * 
	 * @param strNormMssgstring the strNormMssgstring to set
	 */
	public void setStrNormMssgstring(String strNormMssgstring) {
		this.strNormMssgstring = strNormMssgstring;
	}

	/**
	 * Gets the str warn mssgstring.
	 * 
	 * @return the strWarnMssgstring
	 */
	public String getStrWarnMssgstring() {
		return strWarnMssgstring;
	}

	/**
	 * Sets the str warn mssgstring.
	 * 
	 * @param strWarnMssgstring the strWarnMssgstring to set
	 */
	public void setStrWarnMssgstring(String strWarnMssgstring) {
		this.strWarnMssgstring = strWarnMssgstring;
	}

	/**
	 * Gets the str err mssgstring.
	 * 
	 * @return the strErrMssgstring
	 */
	public String getStrErrMssgstring() {
		return strErrMssgstring;
	}

	/**
	 * Sets the str err mssgstring.
	 * 
	 * @param strErrMssgstring the strErrMssgstring to set
	 */
	public void setStrErrMssgstring(String strErrMssgstring) {
		this.strErrMssgstring = strErrMssgstring;
	}

	/**
	 * Gets the str hosp code.
	 * 
	 * @return the strHospCode
	 */
	public String getStrHospCode() {
		return strHospCode;
	}

	/**
	 * Sets the str hosp code.
	 * 
	 * @param strHospCode the strHospCode to set
	 */
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}

	/**
	 * Gets the str seat id.
	 * 
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}

	/**
	 * Sets the str seat id.
	 * 
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * Gets the str remarks.
	 * 
	 * @return the strRemarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}

	/**
	 * Sets the str remarks.
	 * 
	 * @param strRemarks the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	/**
	 * Gets the str effective from.
	 * 
	 * @return the strEffectiveFrom
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	/**
	 * Sets the str effective from.
	 * 
	 * @param strEffectiveFrom the strEffectiveFrom to set
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	/**
	 * Gets the str chk.
	 * 
	 * @return the strChk
	 */
	public String getStrChk() {
		return strChk;
	}

	/**
	 * Sets the str chk.
	 * 
	 * @param strChk the strChk to set
	 */
	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	/**
	 * Gets the str is valid.
	 * 
	 * @return the strIsValid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}

	/**
	 * Sets the str is valid.
	 * 
	 * @param strIsValid the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	/**
	 * Gets the str current date.
	 * 
	 * @return the strCurrentDate
	 */
	public String getStrCurrentDate() {
		return strCurrentDate;
	}

	/**
	 * Sets the str current date.
	 * 
	 * @param strCurrentDate the strCurrentDate to set
	 */
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}

	/**
	 * Gets the str item brand id.
	 * 
	 * @return the strItemBrandId
	 */
	public String getStrItemBrandId() {
		return strItemBrandId;
	}

	/**
	 * Sets the str item brand id.
	 * 
	 * @param strItemBrandId the strItemBrandId to set
	 */
	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}

	/**
	 * Gets the str gen item id.
	 * 
	 * @return the strItemId
	 */
	public String getStrGenItemId() {
		return strGenItemId;
	}

	/**
	 * Sets the str gen item id.
	 * 
	 * @param strGenItemId the str gen item id
	 */
	public void setStrGenItemId(String strGenItemId) {
		this.strGenItemId = strGenItemId;
	}

	/**
	 * Gets the str item cat no.
	 * 
	 * @return the strItemCatNo
	 */
	public String getStrItemCatNo() {
		return strItemCatNo;
	}

	/**
	 * Sets the str item cat no.
	 * 
	 * @param strItemCatNo the strItemCatNo to set
	 */
	public void setStrItemCatNo(String strItemCatNo) {
		this.strItemCatNo = strItemCatNo;
	}

	/**
	 * Gets the str item cat name.
	 * 
	 * @return the strItemCatName
	 */
	public String getStrItemCatName() {
		return strItemCatName;
	}

	/**
	 * Sets the str item cat name.
	 * 
	 * @param strItemCatName the strItemCatName to set
	 */
	public void setStrItemCatName(String strItemCatName) {
		this.strItemCatName = strItemCatName;
	}

	 

	/**
	 * Gets the str manufacturer id.
	 * 
	 * @return the strManufacturerId
	 */
	public String getStrManufacturerId() {
		return strManufacturerId;
	}

	/**
	 * Sets the str manufacturer id.
	 * 
	 * @param strManufacturerId the strManufacturerId to set
	 */
	public void setStrManufacturerId(String strManufacturerId) {
		this.strManufacturerId = strManufacturerId;
	}

	/**
	 * Gets the str manufacturer name.
	 * 
	 * @return the strManufacturerName
	 */
	public String getStrManufacturerName() {
		return strManufacturerName;
	}

	/**
	 * Sets the str manufacturer name.
	 * 
	 * @param strManufacturerName the strManufacturerName to set
	 */
	public void setStrManufacturerName(String strManufacturerName) {
		this.strManufacturerName = strManufacturerName;
	}

	/**
	 * Gets the str default rate.
	 * 
	 * @return the strDefaultRate
	 */
	public String getStrDefaultRate() {
		return strDefaultRate;
	}

	/**
	 * Sets the str default rate.
	 * 
	 * @param strDefaultRate the strDefaultRate to set
	 */
	public void setStrDefaultRate(String strDefaultRate) {
		this.strDefaultRate = strDefaultRate;
	}

	/**
	 * Gets the str rate unit id.
	 * 
	 * @return the strRateUnitId
	 */
	public String getStrRateUnitId() {
		return strRateUnitId;
	}

	/**
	 * Sets the str rate unit id.
	 * 
	 * @param strRateUnitId the strRateUnitId to set
	 */
	public void setStrRateUnitId(String strRateUnitId) {
		this.strRateUnitId = strRateUnitId;
	}

	/**
	 * Gets the str approved type.
	 * 
	 * @return the strApprovedType
	 */
	public String getStrApprovedType() {
		return strApprovedType;
	}

	/**
	 * Sets the str approved type.
	 * 
	 * @param strApprovedType the strApprovedType to set
	 */
	public void setStrApprovedType(String strApprovedType) {
		this.strApprovedType = strApprovedType;
	}

	/**
	 * Gets the str issue type.
	 * 
	 * @return the strIssueType
	 */
	public String getStrIssueType() {
		return strIssueType;
	}

	/**
	 * Sets the str issue type.
	 * 
	 * @param strIssueType the strIssueType to set
	 */
	public void setStrIssueType(String strIssueType) {
		this.strIssueType = strIssueType;
	}

	/**
	 * Gets the str specification.
	 * 
	 * @return the strSpecification
	 */
	public String getStrSpecification() {
		return strSpecification;
	}

	/**
	 * Sets the str specification.
	 * 
	 * @param strSpecification the str specification
	 */
	public void setStrSpecification(String strSpecification) {
		this.strSpecification = strSpecification;
	}

	/**
	 * Gets the str item make.
	 * 
	 * @return the strItemMake
	 */
	public String getStrItemMake() {
		return strItemMake;
	}

	/**
	 * Sets the str item make.
	 * 
	 * @param strItemMake the strItemMake to set
	 */
	public void setStrItemMake(String strItemMake) {
		this.strItemMake = strItemMake;
	}

	/**
	 * Gets the str last mod seat id.
	 * 
	 * @return the strLastModSeatId
	 */
	public String getStrLastModSeatId() {
		return strLastModSeatId;
	}

	/**
	 * Sets the str last mod seat id.
	 * 
	 * @param strLastModSeatId the strLastModSeatId to set
	 */
	public void setStrLastModSeatId(String strLastModSeatId) {
		this.strLastModSeatId = strLastModSeatId;
	}

	/**
	 * Gets the str last mod date.
	 * 
	 * @return the strLastModDate
	 */
	public String getStrLastModDate() {
		return strLastModDate;
	}

	/**
	 * Sets the str last mod date.
	 * 
	 * @param strLastModDate the strLastModDate to set
	 */
	public void setStrLastModDate(String strLastModDate) {
		this.strLastModDate = strLastModDate;
	}

	/**
	 * Gets the str set sachet flag.
	 * 
	 * @return the strSetSachetFlag
	 */
	public String getStrSetSachetFlag() {
		return strSetSachetFlag;
	}

	/**
	 * Sets the str set sachet flag.
	 * 
	 * @param strSetSachetFlag the strSetSachetFlag to set
	 */
	public void setStrSetSachetFlag(String strSetSachetFlag) {
		this.strSetSachetFlag = strSetSachetFlag;
	}

	/**
	 * Gets the str spare part flag.
	 * 
	 * @return the strSparePartFlag
	 */
	public String getStrSparePartFlag() {
		return strSparePartFlag;
	}

	/**
	 * Sets the str spare part flag.
	 * 
	 * @param strSparePartFlag the strSparePartFlag to set
	 */
	public void setStrSparePartFlag(String strSparePartFlag) {
		this.strSparePartFlag = strSparePartFlag;
	}

	/**
	 * Gets the str item type id.
	 * 
	 * @return the strItemTypeId
	 */
	public String getStrItemTypeId() {
		return strItemTypeId;
	}

	/**
	 * Sets the str item type id.
	 * 
	 * @param strItemTypeId the strItemTypeId to set
	 */
	public void setStrItemTypeId(String strItemTypeId) {
		this.strItemTypeId = strItemTypeId;
	}

	/**
	 * Gets the str item type name.
	 * 
	 * @return the strItemTypeName
	 */
	public String getStrItemTypeName() {
		return strItemTypeName;
	}

	/**
	 * Sets the str item type name.
	 * 
	 * @param strItemTypeName the strItemTypeName to set
	 */
	public void setStrItemTypeName(String strItemTypeName) {
		this.strItemTypeName = strItemTypeName;
	}

	/**
	 * Gets the str is quantified.
	 * 
	 * @return the strIsQuantified
	 */
	public String getStrIsQuantified() {
		return strIsQuantified;
	}

	/**
	 * Sets the str is quantified.
	 * 
	 * @param strIsQuantified the strIsQuantified to set
	 */
	public void setStrIsQuantified(String strIsQuantified) {
		this.strIsQuantified = strIsQuantified;
	}

	/**
	 * Gets the str group id.
	 * 
	 * @return the strGroupId
	 */
	public String getStrGroupId() {
		return strGroupId;
	}

	/**
	 * Sets the str group id.
	 * 
	 * @param strGroupId the strGroupId to set
	 */
	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}

	/**
	 * Gets the str group name.
	 * 
	 * @return the strGroupName
	 */
	public String getStrGroupName() {
		return strGroupName;
	}

	/**
	 * Sets the str group name.
	 * 
	 * @param strGroupName the strGroupName to set
	 */
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}

	/**
	 * Gets the str item id.
	 * 
	 * @return the strItemId
	 */
	public String getStrItemId() {
		return strItemId;
	}

	/**
	 * Sets the str item id.
	 * 
	 * @param strItemId the strItemId to set
	 */
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}

	/**
	 * Gets the str item name.
	 * 
	 * @return the strItemName
	 */
	public String getStrItemName() {
		return strItemName;
	}

	/**
	 * Sets the str item name.
	 * 
	 * @param strItemName the strItemName to set
	 */
	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName;
	}

	/**
	 * Gets the str combo values.
	 * 
	 * @return the strComboValues
	 */
	public String getStrComboValues() {
		return strComboValues;
	}

	/**
	 * Sets the str combo values.
	 * 
	 * @param strComboValues the strComboValues to set
	 */
	public void setStrComboValues(String strComboValues) {
		this.strComboValues = strComboValues;
	}

	/**
	 * Gets the str inventory unit id.
	 * 
	 * @return the strInventoryUnitId
	 */
	public String getStrInventoryUnitId() {
		return strInventoryUnitId;
	}

	/**
	 * Sets the str inventory unit id.
	 * 
	 * @param strInventoryUnitId the strInventoryUnitId to set
	 */
	public void setStrInventoryUnitId(String strInventoryUnitId) {
		this.strInventoryUnitId = strInventoryUnitId;
	}

	/**
	 * Gets the str rate unit name.
	 * 
	 * @return the strRateUnitName
	 */
	public String getStrRateUnitName() {
		return strRateUnitName;
	}

	/**
	 * Sets the str rate unit name.
	 * 
	 * @param strRateUnitName the strRateUnitName to set
	 */
	public void setStrRateUnitName(String strRateUnitName) {
		this.strRateUnitName = strRateUnitName;
	}

	/**
	 * @return the strGenericItem
	 */
	public String getStrGenericItem() {
		return strGenericItem;
	}

	/**
	 * @param strGenericItem the strGenericItem to set
	 */
	public void setStrGenericItem(String strGenericItem) {
		this.strGenericItem = strGenericItem;
	}

	/**
	 * @return the strGenItemValues
	 */
	public String getStrGenItemValues() {
		return strGenItemValues;
	}

	/**
	 * @param strGenItemValues the strGenItemValues to set
	 */
	public void setStrGenItemValues(String strGenItemValues) {
		this.strGenItemValues = strGenItemValues;
	}

	/**
	 * @return the strNewCPACode
	 */
	public String getStrNewCPACode() {
		return strNewCPACode;
	}

	/**
	 * @param strNewCPACode the strNewCPACode to set
	 */
	public void setStrNewCPACode(String strNewCPACode) {
		this.strNewCPACode = strNewCPACode;
	}

	/**
	 * @return the strCPACode
	 */
	public String getStrCPACode() {
		return strCPACode;
	}

	/**
	 * @param strCPACode the strCPACode to set
	 */
	public void setStrCPACode(String strCPACode) {
		this.strCPACode = strCPACode;
	}

	/**
	 * @return the strPrevCPACode
	 */
	public String getStrPrevCPACode() {
		return strPrevCPACode;
	}

	/**
	 * @param strPrevCPACode the strPrevCPACode to set
	 */
	public void setStrPrevCPACode(String strPrevCPACode) {
		this.strPrevCPACode = strPrevCPACode;
	}

	/**
	 * @return the strIsItemCodeRequired
	 */
	public String getStrIsItemCodeRequired() {
		return strIsItemCodeRequired;
	}

	/**
	 * @param strIsItemCodeRequired the strIsItemCodeRequired to set
	 */
	public void setStrIsItemCodeRequired(String strIsItemCodeRequired) {
		this.strIsItemCodeRequired = strIsItemCodeRequired;
	}

	public String getStrBrandReserveFlag() {
		return strBrandReserveFlag;
	}

	public void setStrBrandReserveFlag(String strBrandReserveFlag) {
		this.strBrandReserveFlag = strBrandReserveFlag;
	}

	/**
	 * @param strBrandReserveStatus the strBrandReserveStatus to set
	 */
	public void setStrBrandReserveStatus(String strBrandReserveStatus) {
		this.strBrandReserveStatus = strBrandReserveStatus;
	}

	/**
	 * @return the strBrandReserveStatus
	 */
	public String getStrBrandReserveStatus() {
		return strBrandReserveStatus;
	}

	/**
	 * @return the strApprovedTypeName
	 */
	public String getStrApprovedTypeName() {
		return strApprovedTypeName;
	}

	/**
	 * @param strApprovedTypeName the strApprovedTypeName to set
	 */
	public void setStrApprovedTypeName(String strApprovedTypeName) {
		this.strApprovedTypeName = strApprovedTypeName;
	}

	/**
	 * @return the strApprovedTypeOptions
	 */
	public String getStrApprovedTypeOptions() {
		return strApprovedTypeOptions;
	}

	/**
	 * @param strApprovedTypeOptions the strApprovedTypeOptions to set
	 */
	public void setStrApprovedTypeOptions(String strApprovedTypeOptions) {
		this.strApprovedTypeOptions = strApprovedTypeOptions;
	}

	public String getStrSterilizationFlag() {
		return strSterilizationFlag;
	}

	public void setStrSterilizationFlag(String strSterilizationFlag) {
		this.strSterilizationFlag = strSterilizationFlag;
	}

	public String getStrSterilizationLife() {
		return strSterilizationLife;
	}

	public void setStrSterilizationLife(String strSterilizationLife) {
		this.strSterilizationLife = strSterilizationLife;
	}

	public String getStrUploadFlag() {
		return strUploadFlag;
	}

	public void setStrUploadFlag(String strUploadFlag) {
		this.strUploadFlag = strUploadFlag;
	}

	public FormFile getStrLocation() {
		return strLocation;
	}

	public void setStrLocation(FormFile strLocation) {
		this.strLocation = strLocation;
	}

	public String getStrFileNo() {
		return strFileNo;
	}

	public void setStrFileNo(String strFileNo) {
		this.strFileNo = strFileNo;
	}

	public String getStrPageNo() {
		return strPageNo;
	}

	public void setStrPageNo(String strPageNo) {
		this.strPageNo = strPageNo;
	}

	public String getStrFileName() {
		return strFileName;
	}

	public void setStrFileName(String strFileName) {
		this.strFileName = strFileName;
	}

	public String getStrUploadFileId() {
		return strUploadFileId;
	}

	public void setStrUploadFileId(String strUploadFileId) {
		this.strUploadFileId = strUploadFileId;
	}

	public String getStrUploadFileName() {
		return strUploadFileName;
	}

	public void setStrUploadFileName(String strUploadFileName) {
		this.strUploadFileName = strUploadFileName;
	}

	public String getStrUploadFileLocation() {
		return strUploadFileLocation;
	}

	public void setStrUploadFileLocation(String strUploadFileLocation) {
		this.strUploadFileLocation = strUploadFileLocation;
	}

	public String getStrSerialNo() {
		return strSerialNo;
	}

	public void setStrSerialNo(String strSerialNo) {
		this.strSerialNo = strSerialNo;
	}

	public String getStrGenericItemId() {
		return strGenericItemId;
	}

	public void setStrGenericItemId(String strGenericItemId) {
		this.strGenericItemId = strGenericItemId;
	}

	
}
