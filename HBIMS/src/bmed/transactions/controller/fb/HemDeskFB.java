package bmed.transactions.controller.fb;

import hisglobal.transactionutil.GenericFormBean;

public class HemDeskFB extends GenericFormBean 
{
	private static final long serialVersionUID = 02L;
    private String strHospCode ="";
    private String strSeatId ="";
    private String strChk ="";
    private String strGoFlg ="";
	//Primary Key
	private String strReqNo = "";
	private String strStoreId = "";
	private String strReqTypeId ="";
	private String strItemCatgNo = "";
	private String strIsUrgentFlg ="";
	private String strIndentPeriod="";	
		
	private String strNormalMsg = "";
	private String strWarningMsg = "";
    private String strMsgString = "";
	private String strMsgType = "0";
	private String strErrMsg = "";
	private String strReqType;
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
	
	private String 	strComplaintId;
	private String 	strComplaintSlNo;

	private String 	strComplaintDate;

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
	private String strReqId;
	private String strEmailId;
	private String strDeptName;
	
	private String strCommunicationCmb;
	private String strCommunicationId;
	
	private String strHemDesk;
	private String strUploadFileId;
	private String strStoreName;
	
	
	public String getStrStoreName() {
		return strStoreName;
	}
	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}
	public String getStrUploadFileId() {
		return strUploadFileId;
	}
	public void setStrUploadFileId(String strUploadFileId) {
		this.strUploadFileId = strUploadFileId;
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
	public String getStrPrevEsclationDtl() {
		return strPrevEsclationDtl;
	}
	public void setStrPrevEsclationDtl(String strPrevEsclationDtl) {
		this.strPrevEsclationDtl = strPrevEsclationDtl;
	}
	public String getStrComplaintId() {
		return strComplaintId;
	}
	public void setStrComplaintId(String strComplaintId) {
		this.strComplaintId = strComplaintId;
	}
	public String getStrComplaintSlNo() {
		return strComplaintSlNo;
	}
	public void setStrComplaintSlNo(String strComplaintSlNo) {
		this.strComplaintSlNo = strComplaintSlNo;
	}
	public String getStrComplaintDate() {
		return strComplaintDate;
	}
	public void setStrComplaintDate(String strComplaintDate) {
		this.strComplaintDate = strComplaintDate;
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
	public String getStrPath() {
		return strPath;
	}
	public void setStrPath(String strPath) {
		this.strPath = strPath;
	}
	public String getStrGoFlg() {
		return strGoFlg;
	}
	public void setStrGoFlg(String strGoFlg) {
		this.strGoFlg = strGoFlg;
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
	public String getStrReqNo() {
		return strReqNo;
	}
	public void setStrReqNo(String strReqNo) {
		this.strReqNo = strReqNo;
	}
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public String getStrReqTypeId() {
		return strReqTypeId;
	}
	public void setStrReqTypeId(String strReqTypeId) {
		this.strReqTypeId = strReqTypeId;
	}
	public String getStrItemCatgNo() {
		return strItemCatgNo;
	}
	public void setStrItemCatgNo(String strItemCatgNo) {
		this.strItemCatgNo = strItemCatgNo;
	}
	public String getStrIsUrgentFlg() {
		return strIsUrgentFlg;
	}
	public void setStrIsUrgentFlg(String strIsUrgentFlg) {
		this.strIsUrgentFlg = strIsUrgentFlg;
	}
	public String getStrIndentPeriod() {
		return strIndentPeriod;
	}
	public void setStrIndentPeriod(String strIndentPeriod) {
		this.strIndentPeriod = strIndentPeriod;
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
	public String getStrReqType() {
		return strReqType;
	}
	public void setStrReqType(String strReqType) {
		this.strReqType = strReqType;
	}
	/**
	 * @param strHemDesk the strHemDesk to set
	 */
	public void setStrHemDesk(String strHemDesk) {
		this.strHemDesk = strHemDesk;
	}
	/**
	 * @return the strHemDesk
	 */
	public String getStrHemDesk() {
		return strHemDesk;
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

}
