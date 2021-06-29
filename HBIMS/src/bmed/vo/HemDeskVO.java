package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class HemDeskVO 
{
	private static final long serialVersionUID = 02L;
	private String strHospCode = "";
	private String strSeatId = "";
	private String strChk = "";
	private String strErr = "";
	private String strMsg = "";
	private String strWarning = "";
	private String strMsgString = "";
	private String strMsgType = "0";
	private String strErrMsg = "";

	private String strComplaintId;
	private String strComplaintSlNo;
	private String strMode;
    private String strEmpId;
	private String strComplaintDate;
	
	private String strEnggItemTypeCmb;

	private String strEnggItemSubTypeCmb;

	private String strSeriveEnggCmb;

	private String strEscalationLevelCmb;

	private String strEnggItemTypeId;

	private String strEnggItemSubTypeId;

	private String strServiceEnggId;

	private String strEscalationLevelId;

	private String strRemarks;

	private String strEscTime;

	private String strEscDate;

	private String strModeofEscIdCmb;

	private String strModeofEscId;

	private String strName;

	private String strDesignation;	
	
	private String strPath;
	private String strWarrantyDetailsTable;
	private String strMaintenanceContractDetailsTable;
	

	private String 	strItemName;

	private String 	strItemBatchNo;

	private String 	strItemSerialNo;

	private String 	strManufacturerSerialNo;

	private String 	strComplaintDescription;

	private String 	strIsWorking;

	private String 	strDate;

	private String 	strTime;

	private String 	strPreferredFromTime;

	private String 	strPreferredToTime;
	
	private String strPrevEsclationDtl;
	
	private String strContactNo;
	
	private String strEmailId;
	private WebRowSet wrsData;
	private WebRowSet wrsMultiRowData;
	private String strCommunicationCmb;
	private String strCommunicationId;  
	private String strReqType;
	
	private String strEquipmentUIDNo; //Added on 09-July-2013 as per requirement
	private String strManufacturerName;//Added on 09-July-2013 as per requirement

	private String strReqId;
	private String strDeptName;
	
	
	private String strHemDesk;

	
	
	private String strComplaintType;
	private String strMainStatusId;
	private String strSubStatusId;
	private String strActionId;
	private String strMainStatusName;
	private String strSubStatusName;
	
	private String strStoreName; //Added on 23-Aug-2013 as per requirement
    private String strLabName;//Added on 23-Aug-2013 as per requirement
    private String strEnggItemType; //Added on 23-Aug-2013 as per requirement
    private String strEnggItemSubType;//Added on 23-Aug-2013 as per requirement
    private String strItemModel; //Added on 23-Aug-2013 as per requirement
    private String 	strIsAcknowledge;
	private String 	strIsApproved;
    private String strComplaintNature;
	private String strActionTaken;
	
	private String strIsHEMAcknowledge;
	private String strAckRemarks;
	private String strIsHEMApproved;
	private String strAprvRemarks;
	private String strComplaintStatus;
	private String strAcknowledgeDt;
	private String strApprovedDt;
	private String strAcknowledgeBy;
	private String strApprovedBy;
	private String strEmpName;
	
	//following fields added for Hemm Desk Schedule process
	private String 	strScheduleDate;
    private String strScheduleTime;
	private String strServiceEngineerRemarks;
	private String strIntimationDate;
	private String strIntimationTime;
	private String strContactPerson;
	private String strCompanyName;
	private String strCompanyAddress;
	private String strVendorId;
	private String strServiceProviderRemarks;
	// Primary Key

	//following fields added for Hemm Desk Attend process
	private String strEngineerName;
    private String strEngineerAddress;
	private String strMobileNo;
	private String strAttendDate;
	private String strAttendTime;
	private String strActualProblemDesc;
	private String strAttendRemarks;
	
	//following fields added for Hemm Engineer Desk Service Ation Details
	private String strVendorServiceEngName="";
	private String strCommunicateIdContactId="";
	private String strServiceAttendDate="";
	private String strServiceAttendTime="";
	private String strServiceClosingDate="";
	private String strServiceClosingTime="";
	private String strIsSparePartsMaintenanceInvolved="";
	//multi-row fields declaration starts
	private String strSpareSeqNo[]={};
    private String strSparePartName[]={};
    private String strSpareManufName[]={};
    private String strSpareSlNo[]={};
    private String strSpareManufSlNo[]={};
	private String strStatus []={};
	private String strSpareCost []={};
	//multi-row fields declaration ends
	
	private String strProblemDescription="";
	private String strSolutionProvided="";
	private String strServiceRemarks="";
	private String strReasonForClosing="";
	private String strIsItemInWorkingCondition="";
	private String strFromDate="";
	private String strFromTime="";
	private String strTotalCost="";
	private String strIsPenality="";
	private String strPenaltyRemarks="";
	private String strPenalityAmount="";
	private String strOfficeOrderNo="";
	private String strOfficeOrderDt="";
	private String strNetCost="";
	private String strVerifiedBy="";
	private String strVerifyDate="";
	private String strVerifyTime="";
	private String strOtherCharges="";
	
	//HEM Closing Details Fields
	private String strClosingDate="";
	private String strClosingTime="";
	private String strHEMReasonForClosing="";
	private String strHEMVendorId="";
	private String strHEMTotalCost="";
	private String strIsHEMItemInWorkingCondition="";
	private String strDownFromDate="";
	private String strDownToDate="";
	private String strHEMActualProblemDesc="";
	private String strDownTotalTime="";
	private String strHEMVerifiedBy="";
	private String strVerifiedByOptions="";
	private String strHEMClosingRemarks="";
	private String strClosingComplaintStatus="";
	private String strPendencyRemarks="";
	
	
	public String getStrClosingComplaintStatus() {
		return strClosingComplaintStatus;
	}

	public void setStrClosingComplaintStatus(String strClosingComplaintStatus) {
		this.strClosingComplaintStatus = strClosingComplaintStatus;
	}

	public String getStrPendencyRemarks() {
		return strPendencyRemarks;
	}

	public void setStrPendencyRemarks(String strPendencyRemarks) {
		this.strPendencyRemarks = strPendencyRemarks;
	}

	public String getStrClosingDate() {
		return strClosingDate;
	}

	public void setStrClosingDate(String strClosingDate) {
		this.strClosingDate = strClosingDate;
	}

	public String getStrClosingTime() {
		return strClosingTime;
	}

	public void setStrClosingTime(String strClosingTime) {
		this.strClosingTime = strClosingTime;
	}

	public String getStrHEMReasonForClosing() {
		return strHEMReasonForClosing;
	}

	public void setStrHEMReasonForClosing(String strHEMReasonForClosing) {
		this.strHEMReasonForClosing = strHEMReasonForClosing;
	}

	public String getStrHEMVendorId() {
		return strHEMVendorId;
	}

	public void setStrHEMVendorId(String strHEMVendorId) {
		this.strHEMVendorId = strHEMVendorId;
	}

	public String getStrHEMTotalCost() {
		return strHEMTotalCost;
	}

	public void setStrHEMTotalCost(String strHEMTotalCost) {
		this.strHEMTotalCost = strHEMTotalCost;
	}

	public String getStrIsHEMItemInWorkingCondition() {
		return strIsHEMItemInWorkingCondition;
	}

	public void setStrIsHEMItemInWorkingCondition(
			String strIsHEMItemInWorkingCondition) {
		this.strIsHEMItemInWorkingCondition = strIsHEMItemInWorkingCondition;
	}

	public String getStrDownFromDate() {
		return strDownFromDate;
	}

	public void setStrDownFromDate(String strDownFromDate) {
		this.strDownFromDate = strDownFromDate;
	}

	public String getStrDownToDate() {
		return strDownToDate;
	}

	public void setStrDownToDate(String strDownToDate) {
		this.strDownToDate = strDownToDate;
	}

	public String getStrHEMActualProblemDesc() {
		return strHEMActualProblemDesc;
	}

	public void setStrHEMActualProblemDesc(String strHEMActualProblemDesc) {
		this.strHEMActualProblemDesc = strHEMActualProblemDesc;
	}

	public String getStrDownTotalTime() {
		return strDownTotalTime;
	}

	public void setStrDownTotalTime(String strDownTotalTime) {
		this.strDownTotalTime = strDownTotalTime;
	}

	public String getStrHEMVerifiedBy() {
		return strHEMVerifiedBy;
	}

	public void setStrHEMVerifiedBy(String strHEMVerifiedBy) {
		this.strHEMVerifiedBy = strHEMVerifiedBy;
	}

	public String getStrVerifiedByOptions() {
		return strVerifiedByOptions;
	}

	public void setStrVerifiedByOptions(String strVerifiedByOptions) {
		this.strVerifiedByOptions = strVerifiedByOptions;
	}

	public String getStrHEMClosingRemarks() {
		return strHEMClosingRemarks;
	}

	public void setStrHEMClosingRemarks(String strHEMClosingRemarks) {
		this.strHEMClosingRemarks = strHEMClosingRemarks;
	}

	public String getStrEngineerName() {
		return strEngineerName;
	}

	public void setStrEngineerName(String strEngineerName) {
		this.strEngineerName = strEngineerName;
	}

	public String getStrEngineerAddress() {
		return strEngineerAddress;
	}

	public void setStrEngineerAddress(String strEngineerAddress) {
		this.strEngineerAddress = strEngineerAddress;
	}

	public String getStrMobileNo() {
		return strMobileNo;
	}

	public void setStrMobileNo(String strMobileNo) {
		this.strMobileNo = strMobileNo;
	}

	public String getStrAttendDate() {
		return strAttendDate;
	}

	public void setStrAttendDate(String strAttendDate) {
		this.strAttendDate = strAttendDate;
	}

	public String getStrAttendTime() {
		return strAttendTime;
	}

	public void setStrAttendTime(String strAttendTime) {
		this.strAttendTime = strAttendTime;
	}

	public String getStrActualProblemDesc() {
		return strActualProblemDesc;
	}

	public void setStrActualProblemDesc(String strActualProblemDesc) {
		this.strActualProblemDesc = strActualProblemDesc;
	}

	public String getStrAttendRemarks() {
		return strAttendRemarks;
	}

	public void setStrAttendRemarks(String strAttendRemarks) {
		this.strAttendRemarks = strAttendRemarks;
	}

	public String getStrScheduleDate() {
		return strScheduleDate;
	}

	public void setStrScheduleDate(String strScheduleDate) {
		this.strScheduleDate = strScheduleDate;
	}

	public String getStrScheduleTime() {
		return strScheduleTime;
	}

	public void setStrScheduleTime(String strScheduleTime) {
		this.strScheduleTime = strScheduleTime;
	}

	public String getStrServiceEngineerRemarks() {
		return strServiceEngineerRemarks;
	}

	public void setStrServiceEngineerRemarks(String strServiceEngineerRemarks) {
		this.strServiceEngineerRemarks = strServiceEngineerRemarks;
	}

	public String getStrIntimationDate() {
		return strIntimationDate;
	}

	public void setStrIntimationDate(String strIntimationDate) {
		this.strIntimationDate = strIntimationDate;
	}

	public String getStrIntimationTime() {
		return strIntimationTime;
	}

	public void setStrIntimationTime(String strIntimationTime) {
		this.strIntimationTime = strIntimationTime;
	}

	public String getStrContactPerson() {
		return strContactPerson;
	}

	public void setStrContactPerson(String strContactPerson) {
		this.strContactPerson = strContactPerson;
	}

	public String getStrCompanyName() {
		return strCompanyName;
	}

	public void setStrCompanyName(String strCompanyName) {
		this.strCompanyName = strCompanyName;
	}

	public String getStrCompanyAddress() {
		return strCompanyAddress;
	}

	public void setStrCompanyAddress(String strCompanyAddress) {
		this.strCompanyAddress = strCompanyAddress;
	}

	public String getStrVendorId() {
		return strVendorId;
	}

	public void setStrVendorId(String strVendorId) {
		this.strVendorId = strVendorId;
	}

	public String getStrServiceProviderRemarks() {
		return strServiceProviderRemarks;
	}

	public void setStrServiceProviderRemarks(String strServiceProviderRemarks) {
		this.strServiceProviderRemarks = strServiceProviderRemarks;
	}

	public String getStrReqType() {
		return strReqType;
	}

	public void setStrReqType(String strReqType) {
		this.strReqType = strReqType;
	}

	public String getStrCommunicationCmb() {
		return strCommunicationCmb;
	}

	public void setStrCommunicationCmb(String strCommunicationCmb) {
		this.strCommunicationCmb = strCommunicationCmb;
	}

	public String getStrCommunicationId() {
		return strCommunicationId;
	}

	public void setStrCommunicationId(String strCommunicationId) {
		this.strCommunicationId = strCommunicationId;
	}

	public WebRowSet getWrsData() {
		return wrsData;
	}

	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
	}

	public String getStrHospCode() {
		return strHospCode;
	}

	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrChk() {
		return strChk;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
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

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	public String getStrComplaintId() {
		return strComplaintId;
	}

	public void setStrComplaintId(String strComplaintId) {
		this.strComplaintId = strComplaintId;
	}

	public String getStrComplaintDate() {
		return strComplaintDate;
	}

	public void setStrComplaintDate(String strComplaintDate) {
		this.strComplaintDate = strComplaintDate;
	}

	public String getStrComplaintSlNo() {
		return strComplaintSlNo;
	}

	public void setStrComplaintSlNo(String strComplaintSlNo) {
		this.strComplaintSlNo = strComplaintSlNo;
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

	public String getStrSeriveEnggCmb() {
		return strSeriveEnggCmb;
	}

	public void setStrSeriveEnggCmb(String strSeriveEnggCmb) {
		this.strSeriveEnggCmb = strSeriveEnggCmb;
	}

	public String getStrEscalationLevelCmb() {
		return strEscalationLevelCmb;
	}

	public void setStrEscalationLevelCmb(String strEscalationLevelCmb) {
		this.strEscalationLevelCmb = strEscalationLevelCmb;
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

	public String getStrServiceEnggId() {
		return strServiceEnggId;
	}

	public void setStrServiceEnggId(String strServiceEnggId) {
		this.strServiceEnggId = strServiceEnggId;
	}

	public String getStrEscalationLevelId() {
		return strEscalationLevelId;
	}

	public void setStrEscalationLevelId(String strEscalationLevelId) {
		this.strEscalationLevelId = strEscalationLevelId;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrEscTime() {
		return strEscTime;
	}

	public void setStrEscTime(String strEscTime) {
		this.strEscTime = strEscTime;
	}

	public String getStrEscDate() {
		return strEscDate;
	}

	public void setStrEscDate(String strEscDate) {
		this.strEscDate = strEscDate;
	}

	public String getStrModeofEscIdCmb() {
		return strModeofEscIdCmb;
	}

	public void setStrModeofEscIdCmb(String strModeofEscIdCmb) {
		this.strModeofEscIdCmb = strModeofEscIdCmb;
	}

	public String getStrModeofEscId() {
		return strModeofEscId;
	}

	public void setStrModeofEscId(String strModeofEscId) {
		this.strModeofEscId = strModeofEscId;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getStrDesignation() {
		return strDesignation;
	}

	public void setStrDesignation(String strDesignation) {
		this.strDesignation = strDesignation;
	}

	public String getStrPath() {
		return strPath;
	}

	public void setStrPath(String strPath) {
		this.strPath = strPath;
	}

	public String getStrWarrantyDetailsTable() {
		return strWarrantyDetailsTable;
	}

	public void setStrWarrantyDetailsTable(String strWarrantyDetailsTable) {
		this.strWarrantyDetailsTable = strWarrantyDetailsTable;
	}

	public String getStrMaintenanceContractDetailsTable() {
		return strMaintenanceContractDetailsTable;
	}

	public void setStrMaintenanceContractDetailsTable(
			String strMaintenanceContractDetailsTable) {
		this.strMaintenanceContractDetailsTable = strMaintenanceContractDetailsTable;
	}

	public String getStrItemName() {
		return strItemName;
	}

	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName;
	}

	public String getStrItemBatchNo() {
		return strItemBatchNo;
	}

	public void setStrItemBatchNo(String strItemBatchNo) {
		this.strItemBatchNo = strItemBatchNo;
	}

	public String getStrItemSerialNo() {
		return strItemSerialNo;
	}

	public void setStrItemSerialNo(String strItemSerialNo) {
		this.strItemSerialNo = strItemSerialNo;
	}

	public String getStrManufacturerSerialNo() {
		return strManufacturerSerialNo;
	}

	public void setStrManufacturerSerialNo(String strManufacturerSerialNo) {
		this.strManufacturerSerialNo = strManufacturerSerialNo;
	}

	public String getStrComplaintDescription() {
		return strComplaintDescription;
	}

	public void setStrComplaintDescription(String strComplaintDescription) {
		this.strComplaintDescription = strComplaintDescription;
	}

	public String getStrIsWorking() {
		return strIsWorking;
	}

	public void setStrIsWorking(String strIsWorking) {
		this.strIsWorking = strIsWorking;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	public String getStrPreferredFromTime() {
		return strPreferredFromTime;
	}

	public void setStrPreferredFromTime(String strPreferredFromTime) {
		this.strPreferredFromTime = strPreferredFromTime;
	}

	public String getStrPreferredToTime() {
		return strPreferredToTime;
	}

	public void setStrPreferredToTime(String strPreferredToTime) {
		this.strPreferredToTime = strPreferredToTime;
	}

	public String getStrPrevEsclationDtl() {
		return strPrevEsclationDtl;
	}

	public void setStrPrevEsclationDtl(String strPrevEsclationDtl) {
		this.strPrevEsclationDtl = strPrevEsclationDtl;
	}

	public String getStrContactNo() {
		return strContactNo;
	}

	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}

	public String getStrEmailId() {
		return strEmailId;
	}

	public void setStrEmailId(String strEmailId) {
		this.strEmailId = strEmailId;
	}

	public String getStrMode() {
		return strMode;
	}

	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}

	public String getStrEmpId() {
		return strEmpId;
	}

	public void setStrEmpId(String strEmpId) {
		this.strEmpId = strEmpId;
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

	public String getStrReqId() {
		return strReqId;
	}

	public void setStrReqId(String strReqId) {
		this.strReqId = strReqId;
	}

	public String getStrDeptName() {
		return strDeptName;
	}

	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}

	public String getStrHemDesk() {
		return strHemDesk;
	}

	public void setStrHemDesk(String strHemDesk) {
		this.strHemDesk = strHemDesk;
	}

	public String getStrComplaintType() {
		return strComplaintType;
	}

	public void setStrComplaintType(String strComplaintType) {
		this.strComplaintType = strComplaintType;
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

	public String getStrActionId() {
		return strActionId;
	}

	public void setStrActionId(String strActionId) {
		this.strActionId = strActionId;
	}

	public String getStrStoreName() {
		return strStoreName;
	}

	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}

	public String getStrLabName() {
		return strLabName;
	}

	public void setStrLabName(String strLabName) {
		this.strLabName = strLabName;
	}

	public String getStrEnggItemType() {
		return strEnggItemType;
	}

	public void setStrEnggItemType(String strEnggItemType) {
		this.strEnggItemType = strEnggItemType;
	}

	public String getStrEnggItemSubType() {
		return strEnggItemSubType;
	}

	public void setStrEnggItemSubType(String strEnggItemSubType) {
		this.strEnggItemSubType = strEnggItemSubType;
	}

	public String getStrItemModel() {
		return strItemModel;
	}

	public void setStrItemModel(String strItemModel) {
		this.strItemModel = strItemModel;
	}

	public String getStrIsAcknowledge() {
		return strIsAcknowledge;
	}

	public void setStrIsAcknowledge(String strIsAcknowledge) {
		this.strIsAcknowledge = strIsAcknowledge;
	}

	public String getStrIsApproved() {
		return strIsApproved;
	}

	public void setStrIsApproved(String strIsApproved) {
		this.strIsApproved = strIsApproved;
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

	public String getStrIsHEMAcknowledge() {
		return strIsHEMAcknowledge;
	}

	public void setStrIsHEMAcknowledge(String strIsHEMAcknowledge) {
		this.strIsHEMAcknowledge = strIsHEMAcknowledge;
	}

	public String getStrAckRemarks() {
		return strAckRemarks;
	}

	public void setStrAckRemarks(String strAckRemarks) {
		this.strAckRemarks = strAckRemarks;
	}

	public String getStrIsHEMApproved() {
		return strIsHEMApproved;
	}

	public void setStrIsHEMApproved(String strIsHEMApproved) {
		this.strIsHEMApproved = strIsHEMApproved;
	}

	public String getStrAprvRemarks() {
		return strAprvRemarks;
	}

	public void setStrAprvRemarks(String strAprvRemarks) {
		this.strAprvRemarks = strAprvRemarks;
	}

	public String getStrComplaintStatus() {
		return strComplaintStatus;
	}

	public void setStrComplaintStatus(String strComplaintStatus) {
		this.strComplaintStatus = strComplaintStatus;
	}

	public String getStrAcknowledgeDt() {
		return strAcknowledgeDt;
	}

	public void setStrAcknowledgeDt(String strAcknowledgeDt) {
		this.strAcknowledgeDt = strAcknowledgeDt;
	}

	public String getStrApprovedDt() {
		return strApprovedDt;
	}

	public void setStrApprovedDt(String strApprovedDt) {
		this.strApprovedDt = strApprovedDt;
	}

	public String getStrAcknowledgeBy() {
		return strAcknowledgeBy;
	}

	public void setStrAcknowledgeBy(String strAcknowledgeBy) {
		this.strAcknowledgeBy = strAcknowledgeBy;
	}

	public String getStrApprovedBy() {
		return strApprovedBy;
	}

	public void setStrApprovedBy(String strApprovedBy) {
		this.strApprovedBy = strApprovedBy;
	}

	public String getStrEmpName() {
		return strEmpName;
	}

	public void setStrEmpName(String strEmpName) {
		this.strEmpName = strEmpName;
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

	public String getStrServiceAttendDate() {
		return strServiceAttendDate;
	}

	public void setStrServiceAttendDate(String strServiceAttendDate) {
		this.strServiceAttendDate = strServiceAttendDate;
	}

	public String getStrServiceAttendTime() {
		return strServiceAttendTime;
	}

	public void setStrServiceAttendTime(String strServiceAttendTime) {
		this.strServiceAttendTime = strServiceAttendTime;
	}

	public String getStrServiceClosingDate() {
		return strServiceClosingDate;
	}

	public void setStrServiceClosingDate(String strServiceClosingDate) {
		this.strServiceClosingDate = strServiceClosingDate;
	}

	public String getStrServiceClosingTime() {
		return strServiceClosingTime;
	}

	public void setStrServiceClosingTime(String strServiceClosingTime) {
		this.strServiceClosingTime = strServiceClosingTime;
	}

	public String getStrIsSparePartsMaintenanceInvolved() {
		return strIsSparePartsMaintenanceInvolved;
	}

	public void setStrIsSparePartsMaintenanceInvolved(
			String strIsSparePartsMaintenanceInvolved) {
		this.strIsSparePartsMaintenanceInvolved = strIsSparePartsMaintenanceInvolved;
	}

	public String[] getStrSpareSeqNo() {
		return strSpareSeqNo;
	}

	public void setStrSpareSeqNo(String[] strSpareSeqNo) {
		this.strSpareSeqNo = strSpareSeqNo;
	}

	public String[] getStrSparePartName() {
		return strSparePartName;
	}

	public void setStrSparePartName(String[] strSparePartName) {
		this.strSparePartName = strSparePartName;
	}

	public String[] getStrSpareManufName() {
		return strSpareManufName;
	}

	public void setStrSpareManufName(String[] strSpareManufName) {
		this.strSpareManufName = strSpareManufName;
	}

	public String[] getStrSpareSlNo() {
		return strSpareSlNo;
	}

	public void setStrSpareSlNo(String[] strSpareSlNo) {
		this.strSpareSlNo = strSpareSlNo;
	}

	public String[] getStrSpareManufSlNo() {
		return strSpareManufSlNo;
	}

	public void setStrSpareManufSlNo(String[] strSpareManufSlNo) {
		this.strSpareManufSlNo = strSpareManufSlNo;
	}

	public String[] getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String[] strStatus) {
		this.strStatus = strStatus;
	}

	public String[] getStrSpareCost() {
		return strSpareCost;
	}

	public void setStrSpareCost(String[] strSpareCost) {
		this.strSpareCost = strSpareCost;
	}

	public String getStrProblemDescription() {
		return strProblemDescription;
	}

	public void setStrProblemDescription(String strProblemDescription) {
		this.strProblemDescription = strProblemDescription;
	}

	public String getStrSolutionProvided() {
		return strSolutionProvided;
	}

	public void setStrSolutionProvided(String strSolutionProvided) {
		this.strSolutionProvided = strSolutionProvided;
	}

	public String getStrServiceRemarks() {
		return strServiceRemarks;
	}

	public void setStrServiceRemarks(String strServiceRemarks) {
		this.strServiceRemarks = strServiceRemarks;
	}

	public String getStrReasonForClosing() {
		return strReasonForClosing;
	}

	public void setStrReasonForClosing(String strReasonForClosing) {
		this.strReasonForClosing = strReasonForClosing;
	}

	public String getStrIsItemInWorkingCondition() {
		return strIsItemInWorkingCondition;
	}

	public void setStrIsItemInWorkingCondition(String strIsItemInWorkingCondition) {
		this.strIsItemInWorkingCondition = strIsItemInWorkingCondition;
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

	public String getStrTotalCost() {
		return strTotalCost;
	}

	public void setStrTotalCost(String strTotalCost) {
		this.strTotalCost = strTotalCost;
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

	public String getStrOfficeOrderNo() {
		return strOfficeOrderNo;
	}

	public void setStrOfficeOrderNo(String strOfficeOrderNo) {
		this.strOfficeOrderNo = strOfficeOrderNo;
	}

	public String getStrOfficeOrderDt() {
		return strOfficeOrderDt;
	}

	public void setStrOfficeOrderDt(String strOfficeOrderDt) {
		this.strOfficeOrderDt = strOfficeOrderDt;
	}

	public String getStrNetCost() {
		return strNetCost;
	}

	public void setStrNetCost(String strNetCost) {
		this.strNetCost = strNetCost;
	}

	public String getStrVerifiedBy() {
		return strVerifiedBy;
	}

	public void setStrVerifiedBy(String strVerifiedBy) {
		this.strVerifiedBy = strVerifiedBy;
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

	public WebRowSet getWrsMultiRowData() {
		return wrsMultiRowData;
	}

	public void setWrsMultiRowData(WebRowSet wrsMultiRowData) {
		this.wrsMultiRowData = wrsMultiRowData;
	}

	public String getStrPenaltyRemarks() {
		return strPenaltyRemarks;
	}

	public void setStrPenaltyRemarks(String strPenaltyRemarks) {
		this.strPenaltyRemarks = strPenaltyRemarks;
	}

	public String getStrOtherCharges() {
		return strOtherCharges;
	}

	public void setStrOtherCharges(String strOtherCharges) {
		this.strOtherCharges = strOtherCharges;
	}
}