package bmed.transactions.controller.fb;

import hisglobal.transactionutil.GenericFormBean;

public class HemComplaintApprovalDeskFB extends GenericFormBean 
{
	private static final long serialVersionUID = 02L;
    private String strHospCode ="";
    private String strSeatId ="";
    private String strChk ="";
    private String strErr = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";
    private String strMsgString = "";
	private String strMsgType = "0";
	private String strErrMsg = "";
	
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

	private String 	strContactPersonName;

	private String 	strContactPersonNo;

	private String 	strLandMarkDescription;

	private String 	strIsApproved;
    private String  strDeptId;

	private String strIsReject;
	
	private String strWarrantyDetailsTable;
	private String strMaintenanceContractDetailsTable;
	
	private String strWarrantySlNo;
	private String strMcSlNo;
	private String strPath;
	private String strFromTime;
    private String strToTime;
    private String strComplaintAppDtls;
    private String strHiddComplaintAppDtls;
    private String strRemarks;
    private String strComplaintStatus;
    private String strStatus;
    private String strUploadFileId;
    
    private String strDeptName;
    private String strStoreName;
	

	//Primary Key

	public String getStrUploadFileId() {
		return strUploadFileId;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrStoreName() {
		return strStoreName;
	}
	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}
	public void setStrUploadFileId(String strUploadFileId) {
		this.strUploadFileId = strUploadFileId;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public String getStrComplaintStatus() {
		return strComplaintStatus;
	}
	public void setStrComplaintStatus(String strComplaintStatus) {
		this.strComplaintStatus = strComplaintStatus;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrHiddComplaintAppDtls() {
		return strHiddComplaintAppDtls;
	}
	public void setStrHiddComplaintAppDtls(String strHiddComplaintAppDtls) {
		this.strHiddComplaintAppDtls = strHiddComplaintAppDtls;
	}
	public String getStrFromTime() {
		return strFromTime;
	}
	public void setStrFromTime(String strFromTime) {
		this.strFromTime = strFromTime;
	}
	public String getStrToTime() {
		return strToTime;
	}
	public void setStrToTime(String strToTime) {
		this.strToTime = strToTime;
	}
	public String getStrPath() {
		return strPath;
	}
	public void setStrPath(String strPath) {
		this.strPath = strPath;
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
	
	
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
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
	public String getStrContactPersonName() {
		return strContactPersonName;
	}
	public void setStrContactPersonName(String strContactPersonName) {
		this.strContactPersonName = strContactPersonName;
	}
	public String getStrContactPersonNo() {
		return strContactPersonNo;
	}
	public void setStrContactPersonNo(String strContactPersonNo) {
		this.strContactPersonNo = strContactPersonNo;
	}
	public String getStrLandMarkDescription() {
		return strLandMarkDescription;
	}
	public void setStrLandMarkDescription(String strLandMarkDescription) {
		this.strLandMarkDescription = strLandMarkDescription;
	}
	public String getStrIsApproved() {
		return strIsApproved;
	}
	public void setStrIsApproved(String strIsApproved) {
		this.strIsApproved = strIsApproved;
	}
	public String getStrIsReject() {
		return strIsReject;
	}
	public void setStrIsReject(String strIsReject) {
		this.strIsReject = strIsReject;
	}
	public String getStrWarrantyDetailsTable() {
		return strWarrantyDetailsTable;
	}
	public void setStrWarrantyDetailsTable(String strWarrantyDetailsTable) {
		this.strWarrantyDetailsTable = strWarrantyDetailsTable;
	}
	public String getStrDeptId() {
		return strDeptId;
	}
	public void setStrDeptId(String strDeptId) {
		this.strDeptId = strDeptId;
	}
	public String getStrComplaintSlNo() {
		return strComplaintSlNo;
	}
	public void setStrComplaintSlNo(String strComplaintSlNo) {
		this.strComplaintSlNo = strComplaintSlNo;
	}
	public String getStrWarrantySlNo() {
		return strWarrantySlNo;
	}
	public void setStrWarrantySlNo(String strWarrantySlNo) {
		this.strWarrantySlNo = strWarrantySlNo;
	}
	public String getStrMcSlNo() {
		return strMcSlNo;
	}
	public void setStrMcSlNo(String strMcSlNo) {
		this.strMcSlNo = strMcSlNo;
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
	public String getStrComplaintAppDtls() {
		return strComplaintAppDtls;
	}
	public void setStrComplaintAppDtls(String strComplaintAppDtls) {
		this.strComplaintAppDtls = strComplaintAppDtls;
	}
}