package bmed.transactions.controller.fb;

import org.apache.struts.action.ActionForm;

public class ComplaintLogOfflineFB extends ActionForm {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -7575784903074721537L;

	private String strActionId;
	private String strActionOptions;
	private String strActiveComplaintsBody;
	private String strActivePreventiveMaintenanceBody;
	private String strAllocateToExternalServiceProvider;
	private String strApprovalDetailsTable;
	private String strAttendDate;
	private String strAttenderDetailsTable;
	private String strAttendTime;
	private String strCancelRemarks;
	private String strCancelTypeId;
	private String strCancelTypeName;
	private String strCancelTypeOptions;
	private String strCloseDetailsTable;
	private String strClosingDate;
	private String strClosingTime;
	private String strCommunicationId;
	private String strComplaintDate;
	private String strComplaintDescription;
	private String strComplaintId;
	private String strComplaintTime;
	private String strComplaintType;
	private String strConfigPropertyValue;
	private String strContactNo;
	private String strContactPerson;
	private String strContactPersonName;
	private String strCtDate;
	private String strDeptCode;
	private String strDeptCodeNew;
	private String strDeptCombo;
	private String strVenderCombo;
	private String strDeptName;
	private String strDeptOptions;
	private String strDesignation;
	private String strEmailId;
	private String strEmpId;
	private String strEmpName;
	private String strEngineeringItemSubTypeCmb;
	private String strEngineeringItemSubTypeId;
	private String strEngineeringItemTypeCmb;
	private String strEngineeringItemTypeId;
	private String strErrMsg;
	private String strExpectedDateToAttend;
	private String strExpectedTimeToAttend;
	private String strExpectedVisit;
	private String strExpectedVisitUnitId;
	private String strExpectedVisitUnitOptions;
	private String strExternalServiceProvidercheckBoxChecked;
	private String strExternalServiceProvidercheckBoxDisplay;
	private String strFilterValueOptions;
	private String strGatePassNo;
	private String strGrievancesDetailsTable;
	private String StrHiddenComplaintId;
	private String strHospitalCode;
	private String strIntimationDate;
	private String strIntimationTime;
	private String strInvoiceNo;
	private String strIsAttached;
	private String strIsHemDesk;
	private String strIsItemInWorkingCondition;
	private String strIsValid;
	private String strItemBatchNo;
	private String strItemId;
	private String strVendorId;
	private String strVendorContactNo;
	private String strVendorAddr;
	private String strItemName;
	private String strItemNameCombo;
	private String strItemOrNonItemMode;// mode for Item/Non-Item
	private String strItemSerialNo;
	private String strLandMarkDescription;
	private String strMainteId;
	private String strMaintenanceContractDetailsTable;
	private String strManufactureNameOptions;
	private String strManufacturerSerialNo;
	private String strManufSerialNo;
	private String strNonItemId;
	private String strNonItemNameCombo;
	private String strNoOfReminders;
	private String strNormalMsg;
	private String strOtherServiceEngineerDetailsTable;
	private String strPageFlag;
	private String strPerformedDate;
	private String strPreferredFromTime;
	private String strPreferredToTime;
	private String strProblemDescription;
	private String strReasonForClosing;
	private String strRemarks;
	private String strReminderDetails;
	private String strRemindersDetailsTable;
	private String strRepaiedByVendor;
	private String strReplyRemarks;
	private String strSchedulesDetailsTable;
	private String strSeatId;
	private String strSerialNo;
	private String strServiceEnggId;
	private String strServiceEngineerDetailsTable;
	private String strServiceEngineerName;
	private String strServiceEngnieerRemarks;
	private String strServiceProviderRemarks;
	private String strSkillId;	
	private String strSolutionProvided;
	private String strSpareItemSerialNo;
	private String strSpareManufacturerId;
	private String strSpareManufactureSerialNo;
	private String strSparePartDetailsTable;
	private String strSparePartId;
	private String strSparePartMaintenaceStatusTableRow;
	private String strSparePartNameOptions;
	private String strSparePartsRequired;
	private String strSparePartStatusId;
	private String strSparePartStatusOptions;
	private String strSparePartStockDetailsRadio;
	private String strSpecification;
	private String strStockInfoVal;
	private String strStoreId;
	private String strStoreNameCombo;
	private String strTotalCostInvolved;
	private String strTotalDownTime;
	private String strVerifiedBy;
	private String strVerifiedByOptions;
	private String strWarningMsg;	
	private String strWarrantyAndMaintenanceContractDivDisplay;
	private String strWarrantyDetailsTable;
	private String strWarrantyFromDate;
	private String strWarrantyOrMaintenanceSlNoAndType;
	private String strWarrantyUpto;
	private String strWarrantyUptoUnitId;
	private String strWarrantyUptoUnitOptions;
	private String strWorkingCondition;
	private String strWorkStatus;
	private String strTaskTable;
	private String strPath;
	private String strUploadFileId;
	
	private String strIsCostInvolved;
	private String strCost;
	private String strBillNo;
	private String strBillDate;
	
	private String strFromDate;
	private String strFromTime;
	private String strToDate;
	private String strToTime;
	
	
	private String isRepairedByVendor;
	
	private String strVendorServiceEngName;
	private String strCommunicateIdContactId;
	
	private String strIsSparePartsMaintenanceInvolved;
	
	private String strSparePartsFlag;
	
	private String strIsPenality;
	private String strPenalityAmount;
	
	
	private String strVerifyDate;
	private String strVerifyTime;
	
	private String strServiceEngNameCombo;
	
	private String strEmpComboOptions;
	
	private String  strAttendedContactPerson;

	public String getStrAttendedContactPerson() {
		return strAttendedContactPerson;
	}

	public void setStrAttendedContactPerson(String strAttendedContactPerson) {
		this.strAttendedContactPerson = strAttendedContactPerson;
	}

	public String getIsRepairedByVendor() {
		return isRepairedByVendor;
	}

	public void setIsRepairedByVendor(String isRepairedByVendor) {
		this.isRepairedByVendor = isRepairedByVendor;
	}

	public String getStrBillNo() {
		return strBillNo;
	}

	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}

	public String getStrUploadFileId() {
		return strUploadFileId;
	}

	public void setStrUploadFileId(String strUploadFileId) {
		this.strUploadFileId = strUploadFileId;
	}

	public String getStrActionId() {
		return strActionId;
	}

	public String getStrActionOptions() {
		return strActionOptions;
	}

	public String getStrActiveComplaintsBody() {
		return strActiveComplaintsBody;
	}

	public String getStrActivePreventiveMaintenanceBody() {
		return strActivePreventiveMaintenanceBody;
	}

	/**
	 * @return the strAllocateToExternalServiceProvider
	 */
	public String getStrAllocateToExternalServiceProvider() {
		return strAllocateToExternalServiceProvider;
	}

	public String getStrApprovalDetailsTable() {
		return strApprovalDetailsTable;
	}

	/**
	 * @return the strAttendDate
	 */
	public String getStrAttendDate() {
		return strAttendDate;
	}

	/**
	 * @return the strAttenderDetailsTable
	 */
	public String getStrAttenderDetailsTable() {
		return strAttenderDetailsTable;
	}

	/**
	 * @return the strAttendTime
	 */
	public String getStrAttendTime() {
		return strAttendTime;
	}

	/**
	 * @return the strCancelRemarks
	 */
	public String getStrCancelRemarks() {
		return strCancelRemarks;
	}

	/**
	 * @return the strCancelTypeId
	 */
	public String getStrCancelTypeId() {
		return strCancelTypeId;
	}

	/**
	 * @return the strCancelTypeName
	 */
	public String getStrCancelTypeName() {
		return strCancelTypeName;
	}

	/**
	 * @return the strCancelTypeOptions
	 */
	public String getStrCancelTypeOptions() {
		return strCancelTypeOptions;
	}

	public String getStrCloseDetailsTable() {
		return strCloseDetailsTable;
	}

	/**
	 * @return the strClosingDate
	 */
	public String getStrClosingDate() {
		return strClosingDate;
	}

	/**
	 * @return the strClosingTime
	 */
	public String getStrClosingTime() {
		return strClosingTime;
	}

	/**
	 * @return the strCommunicationId
	 */
	public String getStrCommunicationId() {
		return strCommunicationId;
	}

	public String getStrComplaintDate() {
		return strComplaintDate;
	}

	public String getStrComplaintDescription() {
		return strComplaintDescription;
	}

	/**
	 * @return the strComplaintId
	 */
	public String getStrComplaintId() {
		return strComplaintId;
	}

	public String getStrComplaintTime() {
		return strComplaintTime;
	}

	public String getStrComplaintType() {
		return strComplaintType;
	}

	public String getStrConfigPropertyValue() {
		return strConfigPropertyValue;
	}

	public String getStrContactNo() {
		return strContactNo;
	}

	/**
	 * @return the strContactPerson
	 */
	public String getStrContactPerson() {
		return strContactPerson;
	}

	public String getStrContactPersonName() {
		return strContactPersonName;
	}

	public String getStrCtDate() {
		return strCtDate;
	}

	public String getStrDeptCode() {
		return strDeptCode;
	}

	public String getStrDeptCodeNew() {
		return strDeptCodeNew;
	}

	public String getStrDeptCombo() {
		return strDeptCombo;
	}

	public String getStrDeptName() {
		return strDeptName;
	}

	public String getStrDeptOptions() {
		return strDeptOptions;
	}

	public String getStrDesignation() {
		return strDesignation;
	}

	public String getStrEmpId() {
		return strEmpId;
	}

	public String getStrEmpName() {
		return strEmpName;
	}

	public String getStrEngineeringItemSubTypeCmb() {
		return strEngineeringItemSubTypeCmb;
	}

	public String getStrEngineeringItemSubTypeId() {
		return strEngineeringItemSubTypeId;
	}

	public String getStrEngineeringItemTypeCmb() {
		return strEngineeringItemTypeCmb;
	}

	public String getStrEngineeringItemTypeId() {
		return strEngineeringItemTypeId;
	}

	public String getStrErrMsg() {
		return strErrMsg;
	}

	/**
	 * @return the strExpectedDateToAttend
	 */
	public String getStrExpectedDateToAttend() {
		return strExpectedDateToAttend;
	}

	/**
	 * @return the strExpectedTimeToAttend
	 */
	public String getStrExpectedTimeToAttend() {
		return strExpectedTimeToAttend;
	}

	/**
	 * @return the strExpectedVisit
	 */
	public String getStrExpectedVisit() {
		return strExpectedVisit;
	}

	/**
	 * @return the strExpectedVisitUnitId
	 */
	public String getStrExpectedVisitUnitId() {
		return strExpectedVisitUnitId;
	}

	/**
	 * @return the strExpectedVisitUnitOptions
	 */
	public String getStrExpectedVisitUnitOptions() {
		return strExpectedVisitUnitOptions;
	}

	/**
	 * @return the strExternalServiceProvidercheckBoxChecked
	 */
	public String getStrExternalServiceProvidercheckBoxChecked() {
		return strExternalServiceProvidercheckBoxChecked;
	}

	/**
	 * @return the strExternalServiceProvidercheckBoxDisplay
	 */
	public String getStrExternalServiceProvidercheckBoxDisplay() {
		return strExternalServiceProvidercheckBoxDisplay;
	}

	/**
	 * @return the strFilterValueOptions
	 */
	public String getStrFilterValueOptions() {
		return strFilterValueOptions;
	}

	/**
	 * @return the strGatePassNo
	 */
	public String getStrGatePassNo() {
		return strGatePassNo;
	}

	public String getStrGrievancesDetailsTable() {
		return strGrievancesDetailsTable;
	}

	/**
	 * @return the strHiddenComplaintId
	 */
	public String getStrHiddenComplaintId() {
		return StrHiddenComplaintId;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	/**
	 * @return the strIntimationDate
	 */
	public String getStrIntimationDate() {
		return strIntimationDate;
	}

	/**
	 * @return the strIntimationTime
	 */
	public String getStrIntimationTime() {
		return strIntimationTime;
	}

	/**
	 * @return the strInvoiceNo
	 */
	public String getStrInvoiceNo() {
		return strInvoiceNo;
	}

	public String getStrIsAttached() {
		return strIsAttached;
	}

	/**
	 * @return the strIsHemDesk
	 */
	public String getStrIsHemDesk() {
		return strIsHemDesk;
	}

	public String getStrIsItemInWorkingCondition() {
		return strIsItemInWorkingCondition;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	/**
	 * @return the strItemBatchNo
	 */
	public String getStrItemBatchNo() {
		return strItemBatchNo;
	}

	public String getStrItemId() {
		return strItemId;
	}

	/**
	 * @return the strItemName
	 */
	public String getStrItemName() {
		return strItemName;
	}

	public String getStrItemNameCombo() {
		return strItemNameCombo;
	}

	public String getStrItemOrNonItemMode() {
		return strItemOrNonItemMode;
	}

	/**
	 * @return the strItemSerialNo
	 */
	public String getStrItemSerialNo() {
		return strItemSerialNo;
	}

	public String getStrLandMarkDescription() {
		return strLandMarkDescription;
	}

	/**
	 * @return the strMainteId
	 */
	public String getStrMainteId() {
		return strMainteId;
	}

	/**
	 * @return the strMaintenanceContractDetailsTable
	 */
	public String getStrMaintenanceContractDetailsTable() {
		return strMaintenanceContractDetailsTable;
	}

	/**
	 * @return the strManufactureNameOptions
	 */
	public String getStrManufactureNameOptions() {
		return strManufactureNameOptions;
	}

	/**
	 * @return the strManufacturerSerialNo
	 */
	public String getStrManufacturerSerialNo() {
		return strManufacturerSerialNo;
	}

	/**
	 * @return the strManufSerialNo
	 */
	public String getStrManufSerialNo() {
		return strManufSerialNo;
	}

	/**
	 * @return the strNonItemId
	 */
	public String getStrNonItemId() {
		return strNonItemId;
	}

	/**
	 * @return the strNonItemNameCombo
	 */
	public String getStrNonItemNameCombo() {
		return strNonItemNameCombo;
	}

	public String getStrNoOfReminders() {
		return strNoOfReminders;
	}

	public String getStrNormalMsg() {
		return strNormalMsg;
	}

	/**
	 * @return the strOtherServiceEngineerDetailsTable
	 */
	public String getStrOtherServiceEngineerDetailsTable() {
		return strOtherServiceEngineerDetailsTable;
	}

	/**
	 * @return the strPageFlag
	 */
	public String getStrPageFlag() {
		return strPageFlag;
	}

	/**
	 * @return the strPerformedDate
	 */
	public String getStrPerformedDate() {
		return strPerformedDate;
	}

	public String getStrPreferredFromTime() {
		return strPreferredFromTime;
	}

	public String getStrPreferredToTime() {
		return strPreferredToTime;
	}

	/**
	 * @return the strProblemDescription
	 */
	public String getStrProblemDescription() {
		return strProblemDescription;
	}

	/**
	 * @return the strReasonForClosing
	 */
	public String getStrReasonForClosing() {
		return strReasonForClosing;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public String getStrReminderDetails() {
		return strReminderDetails;
	}

	public String getStrRemindersDetailsTable() {
		return strRemindersDetailsTable;
	}

	/**
	 * @return the strRepaiedByVendor
	 */
	public String getStrRepaiedByVendor() {
		return strRepaiedByVendor;
	}

	public String getStrReplyRemarks() {
		return strReplyRemarks;
	}

	/**
	 * @return the strSchedulesDetailsTable
	 */
	public String getStrSchedulesDetailsTable() {
		return strSchedulesDetailsTable;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	/**
	 * @return the strSerialNo
	 */
	public String getStrSerialNo() {
		return strSerialNo;
	}

	/**
	 * @return the strServiceEnggId
	 */
	public String getStrServiceEnggId() {
		return strServiceEnggId;
	}

	/**
	 * @return the strServiceEngineerDetailsTable
	 */
	public String getStrServiceEngineerDetailsTable() {
		return strServiceEngineerDetailsTable;
	}

	/**
	 * @return the strServiceEngineerName
	 */
	public String getStrServiceEngineerName() {
		return strServiceEngineerName;
	}

	/**
	 * @return the strServiceEngnieerRemarks
	 */
	public String getStrServiceEngnieerRemarks() {
		return strServiceEngnieerRemarks;
	}

	/**
	 * @return the strServiceProviderRemarks
	 */
	public String getStrServiceProviderRemarks() {
		return strServiceProviderRemarks;
	}

	/**
	 * @return the strSkillId
	 */
	public String getStrSkillId() {
		return strSkillId;
	}

	/**
	 * @return the strSolutionProvided
	 */
	public String getStrSolutionProvided() {
		return strSolutionProvided;
	}

	/**
	 * @return the strSpareItemSerialNo
	 */
	public String getStrSpareItemSerialNo() {
		return strSpareItemSerialNo;
	}

	/**
	 * @return the strSpareManufacturerId
	 */
	public String getStrSpareManufacturerId() {
		return strSpareManufacturerId;
	}

	/**
	 * @return the strSpareManufactureSerialNo
	 */
	public String getStrSpareManufactureSerialNo() {
		return strSpareManufactureSerialNo;
	}

	public String getStrSparePartDetailsTable() {
		return strSparePartDetailsTable;
	}

	/**
	 * @return the strSparePartId
	 */
	public String getStrSparePartId() {
		return strSparePartId;
	}

	/**
	 * @return the strSparePartMaintenaceStatusTableRow
	 */
	public String getStrSparePartMaintenaceStatusTableRow() {
		return strSparePartMaintenaceStatusTableRow;
	}

	/**
	 * @return the strSparePartNameOptions
	 */
	public String getStrSparePartNameOptions() {
		return strSparePartNameOptions;
	}

	/**
	 * @return the strSparePartsRequired
	 */
	public String getStrSparePartsRequired() {
		return strSparePartsRequired;
	}

	/**
	 * @return the strSparePartStatusId
	 */
	public String getStrSparePartStatusId() {
		return strSparePartStatusId;
	}

	/**
	 * @return the strSparePartStatusOptions
	 */
	public String getStrSparePartStatusOptions() {
		return strSparePartStatusOptions;
	}

	/**
	 * @return the strSparePartStockDetailsRadio
	 */
	public String getStrSparePartStockDetailsRadio() {
		return strSparePartStockDetailsRadio;
	}

	/**
	 * @return the strSpecification
	 */
	public String getStrSpecification() {
		return strSpecification;
	}

	public String getStrStockInfoVal() {
		return strStockInfoVal;
	}

	public String getStrStoreId() {
		return strStoreId;
	}

	public String getStrStoreNameCombo() {
		return strStoreNameCombo;
	}

	/**
	 * @return the strTotalCostInvolved
	 */
	public String getStrTotalCostInvolved() {
		return strTotalCostInvolved;
	}

	/**
	 * @return the strTotalDownTime
	 */
	public String getStrTotalDownTime() {
		return strTotalDownTime;
	}

	

	/**
	 * @return the strVerifiedBy
	 */
	public String getStrVerifiedBy() {
		return strVerifiedBy;
	}

	/**
	 * @return the strVerifiedByOptions
	 */
	public String getStrVerifiedByOptions() {
		return strVerifiedByOptions;
	}

	public String getStrWarningMsg() {
		return strWarningMsg;
	}

	/**
	 * @return the strWarrantyAndMaintenanceContractDivDisplay
	 */
	public String getStrWarrantyAndMaintenanceContractDivDisplay() {
		return strWarrantyAndMaintenanceContractDivDisplay;
	}

	/**
	 * @return the strWarrantyDetailsTable
	 */
	public String getStrWarrantyDetailsTable() {
		return strWarrantyDetailsTable;
	}

	/**
	 * @return the strWarrantyFromDate
	 */
	public String getStrWarrantyFromDate() {
		return strWarrantyFromDate;
	}

	public String getStrWarrantyOrMaintenanceSlNoAndType() {
		return strWarrantyOrMaintenanceSlNoAndType;
	}

	/**
	 * @return the strWarrantyUpto
	 */
	public String getStrWarrantyUpto() {
		return strWarrantyUpto;
	}

	/**
	 * @return the strWarrantyUptoUnitId
	 */
	public String getStrWarrantyUptoUnitId() {
		return strWarrantyUptoUnitId;
	}

	/**
	 * @return the strWarrantyUptoUnitOptions
	 */
	public String getStrWarrantyUptoUnitOptions() {
		return strWarrantyUptoUnitOptions;
	}

	/**
	 * @return the strWorkingCondition
	 */
	public String getStrWorkingCondition() {
		return strWorkingCondition;
	}

	/**
	 * @return the strWorkStatus
	 */
	public String getStrWorkStatus() {
		return strWorkStatus;
	}

	public void setStrActionId(String strActionId) {
		this.strActionId = strActionId;
	}

	public void setStrActionOptions(String strActionOptions) {
		this.strActionOptions = strActionOptions;
	}

	public void setStrActiveComplaintsBody(String strActiveComplaintsBody) {
		this.strActiveComplaintsBody = strActiveComplaintsBody;
	}

	public void setStrActivePreventiveMaintenanceBody(
			String strActivePreventiveMaintenanceBody) {
		this.strActivePreventiveMaintenanceBody = strActivePreventiveMaintenanceBody;
	}

	/**
	 * @param strAllocateToExternalServiceProvider
	 *            the strAllocateToExternalServiceProvider to set
	 */
	public void setStrAllocateToExternalServiceProvider(
			String strAllocateToExternalServiceProvider) {
		this.strAllocateToExternalServiceProvider = strAllocateToExternalServiceProvider;
	}

	public void setStrApprovalDetailsTable(String strApprovalDetailsTable) {
		this.strApprovalDetailsTable = strApprovalDetailsTable;
	}

	/**
	 * @param strAttendDate the strAttendDate to set
	 */
	public void setStrAttendDate(String strAttendDate) {
		this.strAttendDate = strAttendDate;
	}

	/**
	 * @param strAttenderDetailsTable
	 *            the strAttenderDetailsTable to set
	 */
	public void setStrAttenderDetailsTable(String strAttenderDetailsTable) {
		this.strAttenderDetailsTable = strAttenderDetailsTable;
	}

	/**
	 * @param strAttendTime the strAttendTime to set
	 */
	public void setStrAttendTime(String strAttendTime) {
		this.strAttendTime = strAttendTime;
	}

	/**
	 * @param strCancelRemarks
	 *            the strCancelRemarks to set
	 */
	public void setStrCancelRemarks(String strCancelRemarks) {
		this.strCancelRemarks = strCancelRemarks;
	}

	/**
	 * @param strCancelTypeId
	 *            the strCancelTypeId to set
	 */
	public void setStrCancelTypeId(String strCancelTypeId) {
		this.strCancelTypeId = strCancelTypeId;
	}

	/**
	 * @param strCancelTypeName
	 *            the strCancelTypeName to set
	 */
	public void setStrCancelTypeName(String strCancelTypeName) {
		this.strCancelTypeName = strCancelTypeName;
	}

	/**
	 * @param strCancelTypeOptions
	 *            the strCancelTypeOptions to set
	 */
	public void setStrCancelTypeOptions(String strCancelTypeOptions) {
		this.strCancelTypeOptions = strCancelTypeOptions;
	}

	public void setStrCloseDetailsTable(String strCloseDetailsTable) {
		this.strCloseDetailsTable = strCloseDetailsTable;
	}

	/**
	 * @param strClosingDate the strClosingDate to set
	 */
	public void setStrClosingDate(String strClosingDate) {
		this.strClosingDate = strClosingDate;
	}

	/**
	 * @param strClosingTime the strClosingTime to set
	 */
	public void setStrClosingTime(String strClosingTime) {
		this.strClosingTime = strClosingTime;
	}

	/**
	 * @param strCommunicationId
	 *            the strCommunicationId to set
	 */
	public void setStrCommunicationId(String strCommunicationId) {
		this.strCommunicationId = strCommunicationId;
	}

	public void setStrComplaintDate(String strComplaintDate) {
		this.strComplaintDate = strComplaintDate;
	}

	public void setStrComplaintDescription(String strComplaintDescription) {
		this.strComplaintDescription = strComplaintDescription;
	}

	/**
	 * @param strComplaintId
	 *            the strComplaintId to set
	 */
	public void setStrComplaintId(String strComplaintId) {
		this.strComplaintId = strComplaintId;
	}

	public void setStrComplaintTime(String strComplaintTime) {
		this.strComplaintTime = strComplaintTime;
	}

	public void setStrComplaintType(String strComplaintType) {
		this.strComplaintType = strComplaintType;
	}

	public void setStrConfigPropertyValue(String strConfigPropertyValue) {
		this.strConfigPropertyValue = strConfigPropertyValue;
	}

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

	public void setStrContactPersonName(String strContactPersonName) {
		this.strContactPersonName = strContactPersonName;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}

	public void setStrDeptCodeNew(String strDeptCodeNew) {
		this.strDeptCodeNew = strDeptCodeNew;
	}

	public void setStrDeptCombo(String strDeptCombo) {
		this.strDeptCombo = strDeptCombo;
	}

	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}

	public void setStrDeptOptions(String strDeptOptions) {
		this.strDeptOptions = strDeptOptions;
	}

	public void setStrDesignation(String strDesignation) {
		this.strDesignation = strDesignation;
	}

	public void setStrEmpId(String strEmpId) {
		this.strEmpId = strEmpId;
	}

	public void setStrEmpName(String strEmpName) {
		this.strEmpName = strEmpName;
	}

	public void setStrEngineeringItemSubTypeCmb(
			String strEngineeringItemSubTypeCmb) {
		this.strEngineeringItemSubTypeCmb = strEngineeringItemSubTypeCmb;
	}

	public void setStrEngineeringItemSubTypeId(
			String strEngineeringItemSubTypeId) {
		this.strEngineeringItemSubTypeId = strEngineeringItemSubTypeId;
	}

	public void setStrEngineeringItemTypeCmb(String strEngineeringItemTypeCmb) {
		this.strEngineeringItemTypeCmb = strEngineeringItemTypeCmb;
	}

	public void setStrEngineeringItemTypeId(String strEngineeringItemTypeId) {
		this.strEngineeringItemTypeId = strEngineeringItemTypeId;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	/**
	 * @param strExpectedDateToAttend
	 *            the strExpectedDateToAttend to set
	 */
	public void setStrExpectedDateToAttend(String strExpectedDateToAttend) {
		this.strExpectedDateToAttend = strExpectedDateToAttend;
	}

	/**
	 * @param strExpectedTimeToAttend
	 *            the strExpectedTimeToAttend to set
	 */
	public void setStrExpectedTimeToAttend(String strExpectedTimeToAttend) {
		this.strExpectedTimeToAttend = strExpectedTimeToAttend;
	}

	/**
	 * @param strExpectedVisit
	 *            the strExpectedVisit to set
	 */
	public void setStrExpectedVisit(String strExpectedVisit) {
		this.strExpectedVisit = strExpectedVisit;
	}

	/**
	 * @param strExpectedVisitUnitId
	 *            the strExpectedVisitUnitId to set
	 */
	public void setStrExpectedVisitUnitId(String strExpectedVisitUnitId) {
		this.strExpectedVisitUnitId = strExpectedVisitUnitId;
	}

	/**
	 * @param strExpectedVisitUnitOptions
	 *            the strExpectedVisitUnitOptions to set
	 */
	public void setStrExpectedVisitUnitOptions(
			String strExpectedVisitUnitOptions) {
		this.strExpectedVisitUnitOptions = strExpectedVisitUnitOptions;
	}

	/**
	 * @param strExternalServiceProvidercheckBoxChecked
	 *            the strExternalServiceProvidercheckBoxChecked to set
	 */
	public void setStrExternalServiceProvidercheckBoxChecked(
			String strExternalServiceProvidercheckBoxChecked) {
		this.strExternalServiceProvidercheckBoxChecked = strExternalServiceProvidercheckBoxChecked;
	}

	/**
	 * @param strExternalServiceProvidercheckBoxDisplay
	 *            the strExternalServiceProvidercheckBoxDisplay to set
	 */
	public void setStrExternalServiceProvidercheckBoxDisplay(
			String strExternalServiceProvidercheckBoxDisplay) {
		this.strExternalServiceProvidercheckBoxDisplay = strExternalServiceProvidercheckBoxDisplay;
	}

	/**
	 * @param strFilterValueOptions
	 *            the strFilterValueOptions to set
	 */
	public void setStrFilterValueOptions(String strFilterValueOptions) {
		this.strFilterValueOptions = strFilterValueOptions;
	}

	/**
	 * @param strGatePassNo the strGatePassNo to set
	 */
	public void setStrGatePassNo(String strGatePassNo) {
		this.strGatePassNo = strGatePassNo;
	}

	public void setStrGrievancesDetailsTable(String strGrievancesDetailsTable) {
		this.strGrievancesDetailsTable = strGrievancesDetailsTable;
	}

	/**
	 * @param strHiddenComplaintId
	 *            the strHiddenComplaintId to set
	 */
	public void setStrHiddenComplaintId(String strHiddenComplaintId) {
		StrHiddenComplaintId = strHiddenComplaintId;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * @param strIntimationDate
	 *            the strIntimationDate to set
	 */
	public void setStrIntimationDate(String strIntimationDate) {
		this.strIntimationDate = strIntimationDate;
	}

	/**
	 * @param strIntimationTime
	 *            the strIntimationTime to set
	 */
	public void setStrIntimationTime(String strIntimationTime) {
		this.strIntimationTime = strIntimationTime;
	}

	/**
	 * @param strInvoiceNo the strInvoiceNo to set
	 */
	public void setStrInvoiceNo(String strInvoiceNo) {
		this.strInvoiceNo = strInvoiceNo;
	}

	public void setStrIsAttached(String strIsAttached) {
		this.strIsAttached = strIsAttached;
	}

	/**
	 * @param strIsHemDesk
	 *            the strIsHemDesk to set
	 */
	public void setStrIsHemDesk(String strIsHemDesk) {
		this.strIsHemDesk = strIsHemDesk;
	}

	public void setStrIsItemInWorkingCondition(
			String strIsItemInWorkingCondition) {
		this.strIsItemInWorkingCondition = strIsItemInWorkingCondition;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	/**
	 * @param strItemBatchNo
	 *            the strItemBatchNo to set
	 */
	public void setStrItemBatchNo(String strItemBatchNo) {
		this.strItemBatchNo = strItemBatchNo;
	}

	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}

	/**
	 * @param strItemName
	 *            the strItemName to set
	 */
	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName;
	}

	public void setStrItemNameCombo(String strItemNameCombo) {
		this.strItemNameCombo = strItemNameCombo;
	}

	public void setStrItemOrNonItemMode(String strItemOrNonItemMode) {
		this.strItemOrNonItemMode = strItemOrNonItemMode;
	}

	/**
	 * @param strItemSerialNo
	 *            the strItemSerialNo to set
	 */
	public void setStrItemSerialNo(String strItemSerialNo) {
		this.strItemSerialNo = strItemSerialNo;
	}

	public void setStrLandMarkDescription(String strLandMarkDescription) {
		this.strLandMarkDescription = strLandMarkDescription;
	}

	/**
	 * @param strMainteId the strMainteId to set
	 */
	public void setStrMainteId(String strMainteId) {
		this.strMainteId = strMainteId;
	}

	/**
	 * @param strMaintenanceContractDetailsTable
	 *            the strMaintenanceContractDetailsTable to set
	 */
	public void setStrMaintenanceContractDetailsTable(
			String strMaintenanceContractDetailsTable) {
		this.strMaintenanceContractDetailsTable = strMaintenanceContractDetailsTable;
	}

	/**
	 * @param strManufactureNameOptions the strManufactureNameOptions to set
	 */
	public void setStrManufactureNameOptions(String strManufactureNameOptions) {
		this.strManufactureNameOptions = strManufactureNameOptions;
	}

	/**
	 * @param strManufacturerSerialNo
	 *            the strManufacturerSerialNo to set
	 */
	public void setStrManufacturerSerialNo(String strManufacturerSerialNo) {
		this.strManufacturerSerialNo = strManufacturerSerialNo;
	}

	/**
	 * @param strManufSerialNo the strManufSerialNo to set
	 */
	public void setStrManufSerialNo(String strManufSerialNo) {
		this.strManufSerialNo = strManufSerialNo;
	}

	/**
	 * @param strNonItemId
	 *            the strNonItemId to set
	 */
	public void setStrNonItemId(String strNonItemId) {
		this.strNonItemId = strNonItemId;
	}

	/**
	 * @param strNonItemNameCombo
	 *            the strNonItemNameCombo to set
	 */
	public void setStrNonItemNameCombo(String strNonItemNameCombo) {
		this.strNonItemNameCombo = strNonItemNameCombo;
	}

	public void setStrNoOfReminders(String strNoOfReminders) {
		this.strNoOfReminders = strNoOfReminders;
	}

	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}

	/**
	 * @param strOtherServiceEngineerDetailsTable
	 *            the strOtherServiceEngineerDetailsTable to set
	 */
	public void setStrOtherServiceEngineerDetailsTable(
			String strOtherServiceEngineerDetailsTable) {
		this.strOtherServiceEngineerDetailsTable = strOtherServiceEngineerDetailsTable;
	}

	/**
	 * @param strPageFlag
	 *            the strPageFlag to set
	 */
	public void setStrPageFlag(String strPageFlag) {
		this.strPageFlag = strPageFlag;
	}

	/**
	 * @param strPerformedDate the strPerformedDate to set
	 */
	public void setStrPerformedDate(String strPerformedDate) {
		this.strPerformedDate = strPerformedDate;
	}

	public void setStrPreferredFromTime(String strPreferredFromTime) {
		this.strPreferredFromTime = strPreferredFromTime;
	}

	public void setStrPreferredToTime(String strPreferredToTime) {
		this.strPreferredToTime = strPreferredToTime;
	}

	/**
	 * @param strProblemDescription
	 *            the strProblemDescription to set
	 */
	public void setStrProblemDescription(String strProblemDescription) {
		this.strProblemDescription = strProblemDescription;
	}

	/**
	 * @param strReasonForClosing the strReasonForClosing to set
	 */
	public void setStrReasonForClosing(String strReasonForClosing) {
		this.strReasonForClosing = strReasonForClosing;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public void setStrReminderDetails(String strReminderDetails) {
		this.strReminderDetails = strReminderDetails;
	}

	public void setStrRemindersDetailsTable(String strRemindersDetailsTable) {
		this.strRemindersDetailsTable = strRemindersDetailsTable;
	}

	/**
	 * @param strRepaiedByVendor the strRepaiedByVendor to set
	 */
	public void setStrRepaiedByVendor(String strRepaiedByVendor) {
		this.strRepaiedByVendor = strRepaiedByVendor;
	}

	public void setStrReplyRemarks(String strReplyRemarks) {
		this.strReplyRemarks = strReplyRemarks;
	}

	/**
	 * @param strSchedulesDetailsTable
	 *            the strSchedulesDetailsTable to set
	 */
	public void setStrSchedulesDetailsTable(String strSchedulesDetailsTable) {
		this.strSchedulesDetailsTable = strSchedulesDetailsTable;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * @param strSerialNo the strSerialNo to set
	 */
	public void setStrSerialNo(String strSerialNo) {
		this.strSerialNo = strSerialNo;
	}

	/**
	 * @param strServiceEnggId
	 *            the strServiceEnggId to set
	 */
	public void setStrServiceEnggId(String strServiceEnggId) {
		this.strServiceEnggId = strServiceEnggId;
	}

	/**
	 * @param strServiceEngineerDetailsTable
	 *            the strServiceEngineerDetailsTable to set
	 */
	public void setStrServiceEngineerDetailsTable(
			String strServiceEngineerDetailsTable) {
		this.strServiceEngineerDetailsTable = strServiceEngineerDetailsTable;
	}

	/**
	 * @param strServiceEngineerName the strServiceEngineerName to set
	 */
	public void setStrServiceEngineerName(String strServiceEngineerName) {
		this.strServiceEngineerName = strServiceEngineerName;
	}

	/**
	 * @param strServiceEngnieerRemarks
	 *            the strServiceEngnieerRemarks to set
	 */
	public void setStrServiceEngnieerRemarks(String strServiceEngnieerRemarks) {
		this.strServiceEngnieerRemarks = strServiceEngnieerRemarks;
	}

	/**
	 * @param strServiceProviderRemarks
	 *            the strServiceProviderRemarks to set
	 */
	public void setStrServiceProviderRemarks(String strServiceProviderRemarks) {
		this.strServiceProviderRemarks = strServiceProviderRemarks;
	}

	/**
	 * @param strSkillId
	 *            the strSkillId to set
	 */
	public void setStrSkillId(String strSkillId) {
		this.strSkillId = strSkillId;
	}

	/**
	 * @param strSolutionProvided
	 *            the strSolutionProvided to set
	 */
	public void setStrSolutionProvided(String strSolutionProvided) {
		this.strSolutionProvided = strSolutionProvided;
	}

	/**
	 * @param strSpareItemSerialNo the strSpareItemSerialNo to set
	 */
	public void setStrSpareItemSerialNo(String strSpareItemSerialNo) {
		this.strSpareItemSerialNo = strSpareItemSerialNo;
	}

	/**
	 * @param strSpareManufacturerId the strSpareManufacturerId to set
	 */
	public void setStrSpareManufacturerId(String strSpareManufacturerId) {
		this.strSpareManufacturerId = strSpareManufacturerId;
	}

	/**
	 * @param strSpareManufactureSerialNo the strSpareManufactureSerialNo to set
	 */
	public void setStrSpareManufactureSerialNo(String strSpareManufactureSerialNo) {
		this.strSpareManufactureSerialNo = strSpareManufactureSerialNo;
	}

	public void setStrSparePartDetailsTable(String strSparePartDetailsTable) {
		this.strSparePartDetailsTable = strSparePartDetailsTable;
	}

	/**
	 * @param strSparePartId the strSparePartId to set
	 */
	public void setStrSparePartId(String strSparePartId) {
		this.strSparePartId = strSparePartId;
	}

	/**
	 * @param strSparePartMaintenaceStatusTableRow the strSparePartMaintenaceStatusTableRow to set
	 */
	public void setStrSparePartMaintenaceStatusTableRow(
			String strSparePartMaintenaceStatusTableRow) {
		this.strSparePartMaintenaceStatusTableRow = strSparePartMaintenaceStatusTableRow;
	}

	/**
	 * @param strSparePartNameOptions the strSparePartNameOptions to set
	 */
	public void setStrSparePartNameOptions(String strSparePartNameOptions) {
		this.strSparePartNameOptions = strSparePartNameOptions;
	}

	/**
	 * @param strSparePartsRequired the strSparePartsRequired to set
	 */
	public void setStrSparePartsRequired(String strSparePartsRequired) {
		this.strSparePartsRequired = strSparePartsRequired;
	}

	/**
	 * @param strSparePartStatusId the strSparePartStatusId to set
	 */
	public void setStrSparePartStatusId(String strSparePartStatusId) {
		this.strSparePartStatusId = strSparePartStatusId;
	}

	/**
	 * @param strSparePartStatusOptions the strSparePartStatusOptions to set
	 */
	public void setStrSparePartStatusOptions(String strSparePartStatusOptions) {
		this.strSparePartStatusOptions = strSparePartStatusOptions;
	}

	/**
	 * @param strSparePartStockDetailsRadio the strSparePartStockDetailsRadio to set
	 */
	public void setStrSparePartStockDetailsRadio(
			String strSparePartStockDetailsRadio) {
		this.strSparePartStockDetailsRadio = strSparePartStockDetailsRadio;
	}

	/**
	 * @param strSpecification the strSpecification to set
	 */
	public void setStrSpecification(String strSpecification) {
		this.strSpecification = strSpecification;
	}

	public void setStrStockInfoVal(String strStockInfoVal) {
		this.strStockInfoVal = strStockInfoVal;
	}

	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}

	public void setStrStoreNameCombo(String strStoreNameCombo) {
		this.strStoreNameCombo = strStoreNameCombo;
	}

	/**
	 * @param strTotalCostInvolved the strTotalCostInvolved to set
	 */
	public void setStrTotalCostInvolved(String strTotalCostInvolved) {
		this.strTotalCostInvolved = strTotalCostInvolved;
	}

	/**
	 * @param strTotalDownTime the strTotalDownTime to set
	 */
	public void setStrTotalDownTime(String strTotalDownTime) {
		this.strTotalDownTime = strTotalDownTime;
	}

	

	/**
	 * @param strVerifiedBy the strVerifiedBy to set
	 */
	public void setStrVerifiedBy(String strVerifiedBy) {
		this.strVerifiedBy = strVerifiedBy;
	}

	/**
	 * @param strVerifiedByOptions the strVerifiedByOptions to set
	 */
	public void setStrVerifiedByOptions(String strVerifiedByOptions) {
		this.strVerifiedByOptions = strVerifiedByOptions;
	}

	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

	/**
	 * @param strWarrantyAndMaintenanceContractDivDisplay
	 *            the strWarrantyAndMaintenanceContractDivDisplay to set
	 */
	public void setStrWarrantyAndMaintenanceContractDivDisplay(
			String strWarrantyAndMaintenanceContractDivDisplay) {
		this.strWarrantyAndMaintenanceContractDivDisplay = strWarrantyAndMaintenanceContractDivDisplay;
	}

	/**
	 * @param strWarrantyDetailsTable
	 *            the strWarrantyDetailsTable to set
	 */
	public void setStrWarrantyDetailsTable(String strWarrantyDetailsTable) {
		this.strWarrantyDetailsTable = strWarrantyDetailsTable;
	}

	/**
	 * @param strWarrantyFromDate the strWarrantyFromDate to set
	 */
	public void setStrWarrantyFromDate(String strWarrantyFromDate) {
		this.strWarrantyFromDate = strWarrantyFromDate;
	}

	public void setStrWarrantyOrMaintenanceSlNoAndType(
			String strWarrantyOrMaintenanceSlNoAndType) {
		this.strWarrantyOrMaintenanceSlNoAndType = strWarrantyOrMaintenanceSlNoAndType;
	}

	/**
	 * @param strWarrantyUpto the strWarrantyUpto to set
	 */
	public void setStrWarrantyUpto(String strWarrantyUpto) {
		this.strWarrantyUpto = strWarrantyUpto;
	}

	/**
	 * @param strWarrantyUptoUnitId the strWarrantyUptoUnitId to set
	 */
	public void setStrWarrantyUptoUnitId(String strWarrantyUptoUnitId) {
		this.strWarrantyUptoUnitId = strWarrantyUptoUnitId;
	}

	/**
	 * @param strWarrantyUptoUnitOptions the strWarrantyUptoUnitOptions to set
	 */
	public void setStrWarrantyUptoUnitOptions(String strWarrantyUptoUnitOptions) {
		this.strWarrantyUptoUnitOptions = strWarrantyUptoUnitOptions;
	}

	/**
	 * @param strWorkingCondition the strWorkingCondition to set
	 */
	public void setStrWorkingCondition(String strWorkingCondition) {
		this.strWorkingCondition = strWorkingCondition;
	}

	/**
	 * @param strWorkStatus the strWorkStatus to set
	 */
	public void setStrWorkStatus(String strWorkStatus) {
		this.strWorkStatus = strWorkStatus;
	}

	public String getStrEmailId() {
		return strEmailId;
	}

	public void setStrEmailId(String strEmailId) {
		this.strEmailId = strEmailId;
	}

	/**
	 * @param strTaskTable the strTaskTable to set
	 */
	public void setStrTaskTable(String strTaskTable) {
		this.strTaskTable = strTaskTable;
	}

	/**
	 * @return the strTaskTable
	 */
	public String getStrTaskTable() {
		return strTaskTable;
	}

	public String getStrPath() {
		return strPath;
	}

	public void setStrPath(String strPath) {
		this.strPath = strPath;
	}

	public String getStrBillDate() {
		return strBillDate;
	}

	public void setStrBillDate(String strBillDate) {
		this.strBillDate = strBillDate;
	}

	public String getStrCost() {
		return strCost;
	}

	public void setStrCost(String strCost) {
		this.strCost = strCost;
	}

	public String getStrFromDate() {
		return strFromDate;
	}

	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}

	public String getStrFromTime() {
		return strFromTime;
	}

	public void setStrFromTime(String strFromTime) {
		this.strFromTime = strFromTime;
	}

	public String getStrToDate() {
		return strToDate;
	}

	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}

	public String getStrToTime() {
		return strToTime;
	}

	public void setStrToTime(String strToTime) {
		this.strToTime = strToTime;
	}

	public String getStrIsCostInvolved() {
		return strIsCostInvolved;
	}

	public void setStrIsCostInvolved(String strIsCostInvolved) {
		this.strIsCostInvolved = strIsCostInvolved;
	}

	public String getStrVenderCombo() {
		return strVenderCombo;
	}

	public void setStrVenderCombo(String strVenderCombo) {
		this.strVenderCombo = strVenderCombo;
	}

	public String getStrVendorId() {
		return strVendorId;
	}

	public void setStrVendorId(String strVendorId) {
		this.strVendorId = strVendorId;
	}

	public String getStrVendorContactNo() {
		return strVendorContactNo;
	}

	public void setStrVendorContactNo(String strVendorContactNo) {
		this.strVendorContactNo = strVendorContactNo;
	}

	public String getStrVendorAddr() {
		return strVendorAddr;
	}

	public void setStrVendorAddr(String strVendorAddr) {
		this.strVendorAddr = strVendorAddr;
	}

	public String getStrVendorServiceEngName() {
		return strVendorServiceEngName;
	}

	public void setStrVendorServiceEngName(String strVendorServiceEngName) {
		this.strVendorServiceEngName = strVendorServiceEngName;
	}

	public String getStrCommunicateIdContactId() {
		return strCommunicateIdContactId;
	}

	public void setStrCommunicateIdContactId(String strCommunicateIdContactId) {
		this.strCommunicateIdContactId = strCommunicateIdContactId;
	}

	public String getStrIsSparePartsMaintenanceInvolved() {
		return strIsSparePartsMaintenanceInvolved;
	}

	public void setStrIsSparePartsMaintenanceInvolved(
			String strIsSparePartsMaintenanceInvolved) {
		this.strIsSparePartsMaintenanceInvolved = strIsSparePartsMaintenanceInvolved;
	}

	

	public String getStrIsPenality() {
		return strIsPenality;
	}

	public void setStrIsPenality(String strIsPenality) {
		this.strIsPenality = strIsPenality;
	}

	public String getStrPenalityAmount() {
		return strPenalityAmount;
	}

	public void setStrPenalityAmount(String strPenalityAmount) {
		this.strPenalityAmount = strPenalityAmount;
	}

	public String getStrVerifyDate() {
		return strVerifyDate;
	}

	public void setStrVerifyDate(String strVerifyDate) {
		this.strVerifyDate = strVerifyDate;
	}

	public String getStrVerifyTime() {
		return strVerifyTime;
	}

	public void setStrVerifyTime(String strVerifyTime) {
		this.strVerifyTime = strVerifyTime;
	}

	public String getStrServiceEngNameCombo() {
		return strServiceEngNameCombo;
	}

	public void setStrServiceEngNameCombo(String strServiceEngNameCombo) {
		this.strServiceEngNameCombo = strServiceEngNameCombo;
	}

	public String getStrEmpComboOptions() {
		return strEmpComboOptions;
	}

	public void setStrEmpComboOptions(String strEmpComboOptions) {
		this.strEmpComboOptions = strEmpComboOptions;
	}

	public String getStrSparePartsFlag() {
		return strSparePartsFlag;
	}

	public void setStrSparePartsFlag(String strSparePartsFlag) {
		this.strSparePartsFlag = strSparePartsFlag;
	}

	

	
}
