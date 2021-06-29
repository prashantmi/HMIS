package bmed.vo;

import javax.sql.rowset.WebRowSet;


public class HemComplaintApprovalDeskVO 
{
	private static final long serialVersionUID = 02L;
    private String strHospCode ="";
    private String strSeatId ="";
    private String strChk ="";
    private String strErr = "";
	private String strMsg = "";
	private String strWarning = "";
    private String strMsgString = "";
	private String strMsgType = "0";
	private String strErrMsg = "";
	
	private String 	strComplaintType;
	private String 	strComplaintId;
	private String 	strComplaintSlNo;
	private String strMainStatusId;
	private String strSubStatusId;
	private String strActionId;
	private String strComplaintDate;
	private String strRemarks;
	private String strApprovedDate;
	private String strApprovedBy;
	private String strApprovalStatus;
	private String 	strItemName;
	private String 	strMode;
	private String 	strItemBatchNo;
	private String strEmpName;
	private String strEmpId;
	private String strAprovedDeptId;
	private String strRunningStatus;
	private String 	strItemSerialNo;

	private String strMainStatusName;
	private String strSubStatusName;
	
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
	private String strWarrantySlNo;
	private String strMcSlNo;
	 private String strComplaintAppDtls;
	 
	 private String strComplaintNature;
	 private String strActionTaken;
	 
	 private String strEquipmentUIDNo; //Added on 09-July-2013 as per requirement
	 private String strManufacturerName;//Added on 09-July-2013 as per requirement
	 private String strItemId;//Added on 15/5/2018 
	 public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	WebRowSet WrsComplaintNatureComboOptions;
	 WebRowSet WrsActionTakenComboOptions;

	//Primary Key

	public String getStrComplaintAppDtls() {
		return strComplaintAppDtls;
	}
	public void setStrComplaintAppDtls(String strComplaintAppDtls) {
		this.strComplaintAppDtls = strComplaintAppDtls;
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
	public String getStrComplaintType() {
		return strComplaintType;
	}
	public void setStrComplaintType(String strComplaintType) {
		this.strComplaintType = strComplaintType;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrApprovedDate() {
		return strApprovedDate;
	}
	public void setStrApprovedDate(String strApprovedDate) {
		this.strApprovedDate = strApprovedDate;
	}
	public String getStrApprovedBy() {
		return strApprovedBy;
	}
	public void setStrApprovedBy(String strApprovedBy) {
		this.strApprovedBy = strApprovedBy;
	}
	public String getStrApprovalStatus() {
		return strApprovalStatus;
	}
	public void setStrApprovalStatus(String strApprovalStatus) {
		this.strApprovalStatus = strApprovalStatus;
	}
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	public String getStrEmpName() {
		return strEmpName;
	}
	public void setStrEmpName(String strEmpName) {
		this.strEmpName = strEmpName;
	}
	public String getStrEmpId() {
		return strEmpId;
	}
	public void setStrEmpId(String strEmpId) {
		this.strEmpId = strEmpId;
	}
	public String getStrAprovedDeptId() {
		return strAprovedDeptId;
	}
	public void setStrAprovedDeptId(String strAprovedDeptId) {
		this.strAprovedDeptId = strAprovedDeptId;
	}
	public String getStrRunningStatus() {
		return strRunningStatus;
	}
	public void setStrRunningStatus(String strRunningStatus) {
		this.strRunningStatus = strRunningStatus;
	}
	public WebRowSet getWrsComplaintNatureComboOptions() {
		return WrsComplaintNatureComboOptions;
	}
	public void setWrsComplaintNatureComboOptions(
			WebRowSet wrsComplaintNatureComboOptions) {
		WrsComplaintNatureComboOptions = wrsComplaintNatureComboOptions;
	}
	public WebRowSet getWrsActionTakenComboOptions() {
		return WrsActionTakenComboOptions;
	}
	public void setWrsActionTakenComboOptions(WebRowSet wrsActionTakenComboOptions) {
		WrsActionTakenComboOptions = wrsActionTakenComboOptions;
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
}