package bmed.transactions.vo;

import javax.sql.rowset.WebRowSet;

public class ItemWarrantyDtlsTransVO 
{
private WebRowSet wrsDepartmentOptions;
	
	private String strMsgString = "";
	private String strMsgType = "";
	private String strMode;
	private String strDeptCode;
	private String strDeptName;
	private String strDeptShort;
	private String strDeptSlNo;
	private String strDeptType;
	private String strEffectiveFrm;
	private String strEffectiveTo;
	private String strEntryDate;
	private String strHl7Code;
	private String strHodCode;
	private String strHospitalCode;
	private String strIsValid;
	private String strLocationCode;
	private String strLstModDate;
	private String strLstModSeatId;
	private String strRemarks;
	private String strSeatId;
	private String strItemNameId;
	private String strItemBrandId;
	private String strBatchNo;
	private String strProgramName;
	
	private String strWarrantySuppId;
	private String strStoreId;
	private String strDeptId;
	private String strEnggItemTypeId;
	private String strEnggItemSubTypeId;
	
	private String strSerialNo = "0";
	private String strItemSerialNo = "0";
	private String strWarrantyStartDate;
	private String strWarrantyUpTo;
	private String strWarrantyId;
	private String strOrderDate;

	private String strOrderNo;

	private String strTenderDate;

	private String strTenderNo;
	
	private String strTermsAndCond;

	private String strDocRefNo;
	private String strDocRefDate;
	private String strUploadFileId;
	private String strUploadFileName;

	private WebRowSet strItemBrandComboWS = null;
	
	public String getStrItemBrandId() {
		return strItemBrandId;
	}
	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}
	public WebRowSet getWrsDepartmentOptions() {
		return wrsDepartmentOptions;
	}
	public void setWrsDepartmentOptions(WebRowSet wrsDepartmentOptions) {
		this.wrsDepartmentOptions = wrsDepartmentOptions;
	}
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrDeptShort() {
		return strDeptShort;
	}
	public void setStrDeptShort(String strDeptShort) {
		this.strDeptShort = strDeptShort;
	}
	public String getStrDeptSlNo() {
		return strDeptSlNo;
	}
	public void setStrDeptSlNo(String strDeptSlNo) {
		this.strDeptSlNo = strDeptSlNo;
	}
	public String getStrDeptType() {
		return strDeptType;
	}
	public void setStrDeptType(String strDeptType) {
		this.strDeptType = strDeptType;
	}
	public String getStrEffectiveFrm() {
		return strEffectiveFrm;
	}
	public void setStrEffectiveFrm(String strEffectiveFrm) {
		this.strEffectiveFrm = strEffectiveFrm;
	}
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	public String getStrEntryDate() {
		return strEntryDate;
	}
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}
	public String getStrHl7Code() {
		return strHl7Code;
	}
	public void setStrHl7Code(String strHl7Code) {
		this.strHl7Code = strHl7Code;
	}
	public String getStrHodCode() {
		return strHodCode;
	}
	public void setStrHodCode(String strHodCode) {
		this.strHodCode = strHodCode;
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
	public String getStrLocationCode() {
		return strLocationCode;
	}
	public void setStrLocationCode(String strLocationCode) {
		this.strLocationCode = strLocationCode;
	}
	public String getStrLstModDate() {
		return strLstModDate;
	}
	public void setStrLstModDate(String strLstModDate) {
		this.strLstModDate = strLstModDate;
	}
	public String getStrLstModSeatId() {
		return strLstModSeatId;
	}
	public void setStrLstModSeatId(String strLstModSeatId) {
		this.strLstModSeatId = strLstModSeatId;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrItemNameId() {
		return strItemNameId;
	}
	public void setStrItemNameId(String strItemNameId) {
		this.strItemNameId = strItemNameId;
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
	public String getStrItemSerialNo() {
		return strItemSerialNo;
	}
	public void setStrItemSerialNo(String strItemSerialNo) {
		this.strItemSerialNo = strItemSerialNo;
	}
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public String getStrDeptId() {
		return strDeptId;
	}
	public void setStrDeptId(String strDeptId) {
		this.strDeptId = strDeptId;
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
	public String getStrWarrantySuppId() {
		return strWarrantySuppId;
	}
	public void setStrWarrantySuppId(String strWarrantySuppId) {
		this.strWarrantySuppId = strWarrantySuppId;
	}
	public String getStrWarrantyStartDate() {
		return strWarrantyStartDate;
	}
	public void setStrWarrantyStartDate(String strWarrantyStartDate) {
		this.strWarrantyStartDate = strWarrantyStartDate;
	}
	public String getStrWarrantyUpTo() {
		return strWarrantyUpTo;
	}
	public void setStrWarrantyUpTo(String strWarrantyUpTo) {
		this.strWarrantyUpTo = strWarrantyUpTo;
	}
	public String getStrWarrantyId() {
		return strWarrantyId;
	}
	public void setStrWarrantyId(String strWarrantyId) {
		this.strWarrantyId = strWarrantyId;
	}
	public String getStrOrderDate() {
		return strOrderDate;
	}
	public void setStrOrderDate(String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}
	public String getStrOrderNo() {
		return strOrderNo;
	}
	public void setStrOrderNo(String strOrderNo) {
		this.strOrderNo = strOrderNo;
	}
	public String getStrTenderDate() {
		return strTenderDate;
	}
	public void setStrTenderDate(String strTenderDate) {
		this.strTenderDate = strTenderDate;
	}
	public String getStrTenderNo() {
		return strTenderNo;
	}
	public void setStrTenderNo(String strTenderNo) {
		this.strTenderNo = strTenderNo;
	}
	public String getStrTermsAndCond() {
		return strTermsAndCond;
	}
	public void setStrTermsAndCond(String strTermsAndCond) {
		this.strTermsAndCond = strTermsAndCond;
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
	public WebRowSet getStrItemBrandComboWS() {
		return strItemBrandComboWS;
	}
	public void setStrItemBrandComboWS(WebRowSet strItemBrandComboWS) {
		this.strItemBrandComboWS = strItemBrandComboWS;
	}
	/**
	 * @return the strProgramName
	 */
	public String getStrProgramName() {
		return strProgramName;
	}
	/**
	 * @param strProgramName the strProgramName to set
	 */
	public void setStrProgramName(String strProgramName) {
		this.strProgramName = strProgramName;
	}
	public String getStrDocRefNo() {
		return strDocRefNo;
	}
	public void setStrDocRefNo(String strDocRefNo) {
		this.strDocRefNo = strDocRefNo;
	}
	public String getstrDocRefDate() {
		return strDocRefDate;
	}
	public void setstrDocRefDate(String strDocRefDate) {
		this.strDocRefDate = strDocRefDate;
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
}
