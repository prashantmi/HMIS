package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class ComplaintRequestDtlVO {

	private String strBatchNo;
	private String strCancelDate;
	private String strCancelId;
	private String strCancelRemarks;
	private String strCancelSeatid;
	private String strClosedDate;
	private String strClosedSeatid;
	private String strCloseReason;
	private String strCompIntemation;
	private String strComplaintDes;
	private String strContactNo;
	private String strContactPerson;
	private String strCost;
	private String strDeptId;
	private String strDeptName;
	private String strDownTimeFr;
	private String strEmpId;
	private String strEnggItemSubTypeId;
	private String strEnggItemTypeId;
	private String strEntryDate;
	private String strHemFlag;
	private String strHospitalCode;
	private String strIsAttached;
	private String strIsItem;
	private String strIsOnline;
	private String strIsPreventive;
	private String strIsvalid;
	private String strIsWorking;
	private String strEquipmentNameId;
	private String strItemId;
	private String strItemName;
	private String strItemSubTypeName;
	private String strItemTypeName;
	private String strLandmarkDesc;
	private String strMainStatus;
	private String strMainteId;
	private String strMaintenanceName;
	private String strManufSerialNo;
	private String strMcSlNo;
	private String strMode;
	private String strNotWorkingDate;
	private String strPreferTimeFr;
	private String strPreferTimeTo;
	private String strRemarks;
	private String strReqDate;
	private String strReqId;
	private String strReqType;
	private String strSeatid;
	private String strSerialNo;
	private String strServiceEnggName;
	private String strStatusName;
	private String strStatusRemarks;
	private String strStoreId;
	private String strStoreName;
	private String strSubStatus;
	private String strVendorId;
	private String strVendorInvoiceNo;
	private String strVendorName;
	private String strVerifiedId;
	private String strVerifiedRemarks;
	private String strWarrantySlNo;
	private String strRunningStatus;
	private String strEquipmentUIDNo; //Added on 09-July-2013 as per requirement
	private String strManufacturerName;//Added on 09-July-2013 as per requirement
	
	
	private String strSeqSerialNo;
	private String strMainStatusId;
	private String strSubStatusId;
	private String strSeqActionId;
	private String strMainStatusName;
	private String strSubStatusName;
	private String strComplaintNature;
	private String strActionTaken;
	private String strAcknowledgeStatus;
	private String strApprovedStatus;
	private String strMsgType;
	private WebRowSet WrsMCDetails;
	private String strItemSlNo;
	private String ExpiryDateFlg;
	private String strNatureOfService;
	private String strNatureOfServiceId;
	

	public String getExpiryDateFlg() {
		return ExpiryDateFlg;
	}

	public void setExpiryDateFlg(String expiryDateFlg) {
		ExpiryDateFlg = expiryDateFlg;
	}

	public String getStrItemSlNo() {
		return strItemSlNo;
	}

	public void setStrItemSlNo(String strItemSlNo) {
		this.strItemSlNo = strItemSlNo;
	}

	public WebRowSet getWrsMCDetails() {
		return WrsMCDetails;
	}

	public void setWrsMCDetails(WebRowSet wrsMCDetails) {
		WrsMCDetails = wrsMCDetails;
	}

	private WebRowSet wrsData;
	private String strStockInfoVal;

	public String getStrStockInfoVal() {
		return strStockInfoVal;
	}

	public void setStrStockInfoVal(String strStockInfoVal) {
		this.strStockInfoVal = strStockInfoVal;
	}

	/**
	 * @return the strBatchNo
	 */
	public String getStrBatchNo() {
		return strBatchNo;
	}

	/**
	 * @return the strCancelDate
	 */
	public String getStrCancelDate() {
		return strCancelDate;
	}

	/**
	 * @return the strCancelId
	 */
	public String getStrCancelId() {
		return strCancelId;
	}

	/**
	 * @return the strCancelRemarks
	 */
	public String getStrCancelRemarks() {
		return strCancelRemarks;
	}

	/**
	 * @return the strCancelSeatid
	 */
	public String getStrCancelSeatid() {
		return strCancelSeatid;
	}

	/**
	 * @return the strClosedDate
	 */
	public String getStrClosedDate() {
		return strClosedDate;
	}

	/**
	 * @return the strClosedSeatid
	 */
	public String getStrClosedSeatid() {
		return strClosedSeatid;
	}

	/**
	 * @return the strCloseReason
	 */
	public String getStrCloseReason() {
		return strCloseReason;
	}

	/**
	 * @return the strCompIntemation
	 */
	public String getStrCompIntemation() {
		return strCompIntemation;
	}

	/**
	 * @return the strComplaintDes
	 */
	public String getStrComplaintDes() {
		return strComplaintDes;
	}

	/**
	 * @return the strContactNo
	 */
	public String getStrContactNo() {
		return strContactNo;
	}

	/**
	 * @return the strContactPerson
	 */
	public String getStrContactPerson() {
		return strContactPerson;
	}

	/**
	 * @return the strCost
	 */
	public String getStrCost() {
		return strCost;
	}

	/**
	 * @return the strDeptId
	 */
	public String getStrDeptId() {
		return strDeptId;
	}

	/**
	 * @return the strDeptName
	 */
	public String getStrDeptName() {
		return strDeptName;
	}

	/**
	 * @return the strDownTimeFr
	 */
	public String getStrDownTimeFr() {
		return strDownTimeFr;
	}

	/**
	 * @return the strEmpId
	 */
	public String getStrEmpId() {
		return strEmpId;
	}

	/**
	 * @return the strEnggItemSubTypeId
	 */
	public String getStrEnggItemSubTypeId() {
		return strEnggItemSubTypeId;
	}

	/**
	 * @return the strEnggItemTypeId
	 */
	public String getStrEnggItemTypeId() {
		return strEnggItemTypeId;
	}

	/**
	 * @return the strEntryDate
	 */
	public String getStrEntryDate() {
		return strEntryDate;
	}

	/**
	 * @return the strHemFlag
	 */
	public String getStrHemFlag() {
		return strHemFlag;
	}

	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	/**
	 * @return the strIsAttached
	 */
	public String getStrIsAttached() {
		return strIsAttached;
	}

	/**
	 * @return the strIsItem
	 */
	public String getStrIsItem() {
		return strIsItem;
	}

	/**
	 * @return the strIsOnline
	 */
	public String getStrIsOnline() {
		return strIsOnline;
	}

	/**
	 * @return the strIsPreventive
	 */
	public String getStrIsPreventive() {
		return strIsPreventive;
	}

	/**
	 * @return the strIsvalid
	 */
	public String getStrIsvalid() {
		return strIsvalid;
	}

	/**
	 * @return the strIsWorking
	 */
	public String getStrIsWorking() {
		return strIsWorking;
	}

	/**
	 * @return the strItemId
	 */
	public String getStrItemId() {
		return strItemId;
	}

	/**
	 * @return the strItemName
	 */
	public String getStrItemName() {
		return strItemName;
	}

	/**
	 * @return the strItemSubTypeName
	 */
	public String getStrItemSubTypeName() {
		return strItemSubTypeName;
	}

	/**
	 * @return the strItemTypeName
	 */
	public String getStrItemTypeName() {
		return strItemTypeName;
	}

	/**
	 * @return the strLandmarkDesc
	 */
	public String getStrLandmarkDesc() {
		return strLandmarkDesc;
	}

	/**
	 * @return the strMainStatus
	 */
	public String getStrMainStatus() {
		return strMainStatus;
	}

	/**
	 * @return the strMainteId
	 */
	public String getStrMainteId() {
		return strMainteId;
	}

	/**
	 * @return the strMaintenanceName
	 */
	public String getStrMaintenanceName() {
		return strMaintenanceName;
	}

	/**
	 * @return the strManufSerialNo
	 */
	public String getStrManufSerialNo() {
		return strManufSerialNo;
	}

	/**
	 * @return the strMcSlNo
	 */
	public String getStrMcSlNo() {
		return strMcSlNo;
	}

	/**
	 * @return the strMode
	 */
	public String getStrMode() {
		return strMode;
	}

	/**
	 * @return the strNotWorkingDate
	 */
	public String getStrNotWorkingDate() {
		return strNotWorkingDate;
	}

	/**
	 * @return the strPreferTimeFr
	 */
	public String getStrPreferTimeFr() {
		return strPreferTimeFr;
	}

	/**
	 * @return the strPreferTimeTo
	 */
	public String getStrPreferTimeTo() {
		return strPreferTimeTo;
	}

	/**
	 * @return the strRemarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}

	/**
	 * @return the strReqDate
	 */
	public String getStrReqDate() {
		return strReqDate;
	}

	/**
	 * @return the strReqId
	 */
	public String getStrReqId() {
		return strReqId;
	}

	/**
	 * @return the strReqType
	 */
	public String getStrReqType() {
		return strReqType;
	}

	/**
	 * @return the strSeatid
	 */
	public String getStrSeatid() {
		return strSeatid;
	}

	/**
	 * @return the strSerialNo
	 */
	public String getStrSerialNo() {
		return strSerialNo;
	}

	/**
	 * @return the strServiceEnggName
	 */
	public String getStrServiceEnggName() {
		return strServiceEnggName;
	}

	/**
	 * @return the strStatusName
	 */
	public String getStrStatusName() {
		return strStatusName;
	}

	/**
	 * @return the strStatusRemarks
	 */
	public String getStrStatusRemarks() {
		return strStatusRemarks;
	}

	/**
	 * @return the strStoreId
	 */
	public String getStrStoreId() {
		return strStoreId;
	}

	/**
	 * @return the strStoreName
	 */
	public String getStrStoreName() {
		return strStoreName;
	}

	/**
	 * @return the strSubStatus
	 */
	public String getStrSubStatus() {
		return strSubStatus;
	}

	/**
	 * @return the strVendorId
	 */
	public String getStrVendorId() {
		return strVendorId;
	}

	/**
	 * @return the strVendorInvoiceNo
	 */
	public String getStrVendorInvoiceNo() {
		return strVendorInvoiceNo;
	}

	/**
	 * @return the strVendorName
	 */
	public String getStrVendorName() {
		return strVendorName;
	}

	/**
	 * @return the strVerifiedId
	 */
	public String getStrVerifiedId() {
		return strVerifiedId;
	}

	/**
	 * @return the strVerifiedRemarks
	 */
	public String getStrVerifiedRemarks() {
		return strVerifiedRemarks;
	}

	/**
	 * @return the strWarrantySlNo
	 */
	public String getStrWarrantySlNo() {
		return strWarrantySlNo;
	}

	/**
	 * @return the wrsData
	 */
	public WebRowSet getWrsData() {
		return wrsData;
	}

	/**
	 * @param strBatchNo
	 *            the strBatchNo to set
	 */
	public void setStrBatchNo(String strBatchNo) {
		this.strBatchNo = strBatchNo;
	}

	/**
	 * @param strCancelDate
	 *            the strCancelDate to set
	 */
	public void setStrCancelDate(String strCancelDate) {
		this.strCancelDate = strCancelDate;
	}

	/**
	 * @param strCancelId
	 *            the strCancelId to set
	 */
	public void setStrCancelId(String strCancelId) {
		this.strCancelId = strCancelId;
	}

	/**
	 * @param strCancelRemarks
	 *            the strCancelRemarks to set
	 */
	public void setStrCancelRemarks(String strCancelRemarks) {
		this.strCancelRemarks = strCancelRemarks;
	}

	/**
	 * @param strCancelSeatid
	 *            the strCancelSeatid to set
	 */
	public void setStrCancelSeatid(String strCancelSeatid) {
		this.strCancelSeatid = strCancelSeatid;
	}

	/**
	 * @param strClosedDate
	 *            the strClosedDate to set
	 */
	public void setStrClosedDate(String strClosedDate) {
		this.strClosedDate = strClosedDate;
	}

	/**
	 * @param strClosedSeatid
	 *            the strClosedSeatid to set
	 */
	public void setStrClosedSeatid(String strClosedSeatid) {
		this.strClosedSeatid = strClosedSeatid;
	}

	/**
	 * @param strCloseReason
	 *            the strCloseReason to set
	 */
	public void setStrCloseReason(String strCloseReason) {
		this.strCloseReason = strCloseReason;
	}

	/**
	 * @param strCompIntemation
	 *            the strCompIntemation to set
	 */
	public void setStrCompIntemation(String strCompIntemation) {
		this.strCompIntemation = strCompIntemation;
	}

	/**
	 * @param strComplaintDes
	 *            the strComplaintDes to set
	 */
	public void setStrComplaintDes(String strComplaintDes) {
		this.strComplaintDes = strComplaintDes;
	}

	/**
	 * @param strContactNo
	 *            the strContactNo to set
	 */
	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}

	/**
	 * @param strContactPerson
	 *            the strContactPerson to set
	 */
	public void setStrContactPerson(String strContactPerson) {
		this.strContactPerson = strContactPerson;
	}

	/**
	 * @param strCost
	 *            the strCost to set
	 */
	public void setStrCost(String strCost) {
		this.strCost = strCost;
	}

	/**
	 * @param strDeptId
	 *            the strDeptId to set
	 */
	public void setStrDeptId(String strDeptId) {
		this.strDeptId = strDeptId;
	}

	/**
	 * @param strDeptName the strDeptName to set
	 */
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}

	/**
	 * @param strDownTimeFr
	 *            the strDownTimeFr to set
	 */
	public void setStrDownTimeFr(String strDownTimeFr) {
		this.strDownTimeFr = strDownTimeFr;
	}

	/**
	 * @param strEmpId
	 *            the strEmpId to set
	 */
	public void setStrEmpId(String strEmpId) {
		this.strEmpId = strEmpId;
	}

	/**
	 * @param strEnggItemSubTypeId
	 *            the strEnggItemSubTypeId to set
	 */
	public void setStrEnggItemSubTypeId(String strEnggItemSubTypeId) {
		this.strEnggItemSubTypeId = strEnggItemSubTypeId;
	}

	/**
	 * @param strEnggItemTypeId
	 *            the strEnggItemTypeId to set
	 */
	public void setStrEnggItemTypeId(String strEnggItemTypeId) {
		this.strEnggItemTypeId = strEnggItemTypeId;
	}

	/**
	 * @param strEntryDate
	 *            the strEntryDate to set
	 */
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	/**
	 * @param strHemFlag
	 *            the strHemFlag to set
	 */
	public void setStrHemFlag(String strHemFlag) {
		this.strHemFlag = strHemFlag;
	}

	/**
	 * @param strHospitalCode
	 *            the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * @param strIsAttached
	 *            the strIsAttached to set
	 */
	public void setStrIsAttached(String strIsAttached) {
		this.strIsAttached = strIsAttached;
	}

	/**
	 * @param strIsItem
	 *            the strIsItem to set
	 */
	public void setStrIsItem(String strIsItem) {
		this.strIsItem = strIsItem;
	}

	/**
	 * @param strIsOnline
	 *            the strIsOnline to set
	 */
	public void setStrIsOnline(String strIsOnline) {
		this.strIsOnline = strIsOnline;
	}

	/**
	 * @param strIsPreventive
	 *            the strIsPreventive to set
	 */
	public void setStrIsPreventive(String strIsPreventive) {
		this.strIsPreventive = strIsPreventive;
	}

	/**
	 * @param strIsvalid
	 *            the strIsvalid to set
	 */
	public void setStrIsvalid(String strIsvalid) {
		this.strIsvalid = strIsvalid;
	}

	/**
	 * @param strIsWorking
	 *            the strIsWorking to set
	 */
	public void setStrIsWorking(String strIsWorking) {
		this.strIsWorking = strIsWorking;
	}

	/**
	 * @param strItemId
	 *            the strItemId to set
	 */
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}

	/**
	 * @param strItemName the strItemName to set
	 */
	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName;
	}

	/**
	 * @param strItemSubTypeName the strItemSubTypeName to set
	 */
	public void setStrItemSubTypeName(String strItemSubTypeName) {
		this.strItemSubTypeName = strItemSubTypeName;
	}

	/**
	 * @param strItemTypeName the strItemTypeName to set
	 */
	public void setStrItemTypeName(String strItemTypeName) {
		this.strItemTypeName = strItemTypeName;
	}

	/**
	 * @param strLandmarkDesc
	 *            the strLandmarkDesc to set
	 */
	public void setStrLandmarkDesc(String strLandmarkDesc) {
		this.strLandmarkDesc = strLandmarkDesc;
	}

	/**
	 * @param strMainStatus
	 *            the strMainStatus to set
	 */
	public void setStrMainStatus(String strMainStatus) {
		this.strMainStatus = strMainStatus;
	}

	/**
	 * @param strMainteId
	 *            the strMainteId to set
	 */
	public void setStrMainteId(String strMainteId) {
		this.strMainteId = strMainteId;
	}

	/**
	 * @param strMaintenanceName the strMaintenanceName to set
	 */
	public void setStrMaintenanceName(String strMaintenanceName) {
		this.strMaintenanceName = strMaintenanceName;
	}

	/**
	 * @param strManufSerialNo
	 *            the strManufSerialNo to set
	 */
	public void setStrManufSerialNo(String strManufSerialNo) {
		this.strManufSerialNo = strManufSerialNo;
	}

	/**
	 * @param strMcSlNo
	 *            the strMcSlNo to set
	 */
	public void setStrMcSlNo(String strMcSlNo) {
		this.strMcSlNo = strMcSlNo;
	}

	/**
	 * @param strMode
	 *            the strMode to set
	 */
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}

	/**
	 * @param strNotWorkingDate
	 *            the strNotWorkingDate to set
	 */
	public void setStrNotWorkingDate(String strNotWorkingDate) {
		this.strNotWorkingDate = strNotWorkingDate;
	}

	/**
	 * @param strPreferTimeFr
	 *            the strPreferTimeFr to set
	 */
	public void setStrPreferTimeFr(String strPreferTimeFr) {
		this.strPreferTimeFr = strPreferTimeFr;
	}

	/**
	 * @param strPreferTimeTo
	 *            the strPreferTimeTo to set
	 */
	public void setStrPreferTimeTo(String strPreferTimeTo) {
		this.strPreferTimeTo = strPreferTimeTo;
	}

	/**
	 * @param strRemarks
	 *            the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	/**
	 * @param strReqDate
	 *            the strReqDate to set
	 */
	public void setStrReqDate(String strReqDate) {
		this.strReqDate = strReqDate;
	}

	/**
	 * @param strReqId
	 *            the strReqId to set
	 */
	public void setStrReqId(String strReqId) {
		this.strReqId = strReqId;
	}

	/**
	 * @param strReqType
	 *            the strReqType to set
	 */
	public void setStrReqType(String strReqType) {
		this.strReqType = strReqType;
	}

	/**
	 * @param strSeatid
	 *            the strSeatid to set
	 */
	public void setStrSeatid(String strSeatid) {
		this.strSeatid = strSeatid;
	}

	/**
	 * @param strSerialNo
	 *            the strSerialNo to set
	 */
	public void setStrSerialNo(String strSerialNo) {
		this.strSerialNo = strSerialNo;
	}

	/**
	 * @param strServiceEnggName the strServiceEnggName to set
	 */
	public void setStrServiceEnggName(String strServiceEnggName) {
		this.strServiceEnggName = strServiceEnggName;
	}

	/**
	 * @param strStatusName the strStatusName to set
	 */
	public void setStrStatusName(String strStatusName) {
		this.strStatusName = strStatusName;
	}

	/**
	 * @param strStatusRemarks
	 *            the strStatusRemarks to set
	 */
	public void setStrStatusRemarks(String strStatusRemarks) {
		this.strStatusRemarks = strStatusRemarks;
	}

	/**
	 * @param strStoreId
	 *            the strStoreId to set
	 */
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}

	/**
	 * @param strStoreName the strStoreName to set
	 */
	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}

	/**
	 * @param strSubStatus
	 *            the strSubStatus to set
	 */
	public void setStrSubStatus(String strSubStatus) {
		this.strSubStatus = strSubStatus;
	}

	/**
	 * @param strVendorId
	 *            the strVendorId to set
	 */
	public void setStrVendorId(String strVendorId) {
		this.strVendorId = strVendorId;
	}

	/**
	 * @param strVendorInvoiceNo
	 *            the strVendorInvoiceNo to set
	 */
	public void setStrVendorInvoiceNo(String strVendorInvoiceNo) {
		this.strVendorInvoiceNo = strVendorInvoiceNo;
	}

	/**
	 * @param strVendorName the strVendorName to set
	 */
	public void setStrVendorName(String strVendorName) {
		this.strVendorName = strVendorName;
	}

	/**
	 * @param strVerifiedId
	 *            the strVerifiedId to set
	 */
	public void setStrVerifiedId(String strVerifiedId) {
		this.strVerifiedId = strVerifiedId;
	}

	/**
	 * @param strVerifiedRemarks
	 *            the strVerifiedRemarks to set
	 */
	public void setStrVerifiedRemarks(String strVerifiedRemarks) {
		this.strVerifiedRemarks = strVerifiedRemarks;
	}

	/**
	 * @param strWarrantySlNo
	 *            the strWarrantySlNo to set
	 */
	public void setStrWarrantySlNo(String strWarrantySlNo) {
		this.strWarrantySlNo = strWarrantySlNo;
	}

	/**
	 * @param wrsData
	 *            the wrsData to set
	 */
	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public String getStrEquipmentUIDNo() {
		return strEquipmentUIDNo;
	}

	public void setStrEquipmentUIDNo(String strEquipmentUIDNo) {
		this.strEquipmentUIDNo = strEquipmentUIDNo;
	}

	public String getStrManufacturerName() {
		return strManufacturerName;
	}

	public void setStrManufacturerName(String strManufacturerName) {
		this.strManufacturerName = strManufacturerName;
	}

	public String getStrEquipmentNameId() {
		return strEquipmentNameId;
	}

	public void setStrEquipmentNameId(String strEquipmentNameId) {
		this.strEquipmentNameId = strEquipmentNameId;
	}

	public String getStrSeqSerialNo() {
		return strSeqSerialNo;
	}

	public void setStrSeqSerialNo(String strSeqSerialNo) {
		this.strSeqSerialNo = strSeqSerialNo;
	}

	public String getStrMainStatusId() {
		return strMainStatusId;
	}

	public void setStrMainStatusId(String strMainStatusId) {
		this.strMainStatusId = strMainStatusId;
	}

	public String getStrSubStatusId() {
		return strSubStatusId;
	}

	public void setStrSubStatusId(String strSubStatusId) {
		this.strSubStatusId = strSubStatusId;
	}

	public String getStrSeqActionId() {
		return strSeqActionId;
	}

	public void setStrSeqActionId(String strSeqActionId) {
		this.strSeqActionId = strSeqActionId;
	}

	public String getStrMainStatusName() {
		return strMainStatusName;
	}

	public void setStrMainStatusName(String strMainStatusName) {
		this.strMainStatusName = strMainStatusName;
	}

	public String getStrSubStatusName() {
		return strSubStatusName;
	}

	public void setStrSubStatusName(String strSubStatusName) {
		this.strSubStatusName = strSubStatusName;
	}

	public String getStrRunningStatus() {
		return strRunningStatus;
	}

	public void setStrRunningStatus(String strRunningStatus) {
		this.strRunningStatus = strRunningStatus;
	}

	public String getStrComplaintNature() {
		return strComplaintNature;
	}

	public void setStrComplaintNature(String strComplaintNature) {
		this.strComplaintNature = strComplaintNature;
	}

	public String getStrActionTaken() {
		return strActionTaken;
	}

	public void setStrActionTaken(String strActionTaken) {
		this.strActionTaken = strActionTaken;
	}

	public String getStrAcknowledgeStatus() {
		return strAcknowledgeStatus;
	}

	public void setStrAcknowledgeStatus(String strAcknowledgeStatus) {
		this.strAcknowledgeStatus = strAcknowledgeStatus;
	}

	public String getStrApprovedStatus() {
		return strApprovedStatus;
	}

	public void setStrApprovedStatus(String strApprovedStatus) {
		this.strApprovedStatus = strApprovedStatus;
	}

	public String getStrNatureOfService() {
		return strNatureOfService;
	}

	public void setStrNatureOfService(String strNatureOfService) {
		this.strNatureOfService = strNatureOfService;
	}

	public String getStrNatureOfServiceId() {
		return strNatureOfServiceId;
	}

	public void setStrNatureOfServiceId(String strNatureOfServiceId) {
		this.strNatureOfServiceId = strNatureOfServiceId;
	}

}
