package bmed.masters.controller.fb;

import java.util.ArrayList;

import hisglobal.masterutil.GenericFormBean;

public class NonItemMaintenanceMstFB extends GenericFormBean {

	/**
	 * The serialization runtime associates with each serializable class a
	 * version number, called a serialVersionUID, which is used during
	 * deserialization to verify that the sender and receiver of a serialized
	 * object have loaded classes for that object that are compatible with
	 * respect to serialization. If the receiver has loaded a class for the
	 * object that has a different serialVersionUID than that of the
	 * corresponding sender's class, then deserialization will result in an
	 * InvalidClassException. A serializable class can declare its own
	 * serialVersionUID explicitly by declaring a field named "serialVersionUID"
	 * that must be static, final, and of type long.
	 */
	private static final long serialVersionUID = -6507883753728298436L;
	
	/* Process specific instance variables.*/
	
	private ArrayList<String> listStrTaskName;
	private String strAlertPeriod;
	/*private String strBlockCode;
	private String strBlockCodeOptions;
	private String strBlockName;
	private String strBuildingCode;
	private String strBuildingCodeOptions;
	private String strBuildingName;*/
	private String strCurrentDate;
	private String strDeptId;
	private String strDeptIdOptions;
	private String strDeptName;
	private String strEffectiveFrom;
	private String strEngineeringItemSubTypeId;
	private String strEngineeringItemSubTypeName;
	private String strEngineeringItemTypeId;
	private String strEngineeringItemTypeName;
	private String strErrMsg;
	//private String strFloorId;
	//private String strFloorIdOptions;
	//private String strFloorName;
	private String strHospitalCode;
	private String strIsValid;
	private String strLandmarkDesc;
	private String strLeftTaskIdOptions;
	private String strMaintenanceId;
	private String strMaintenanceIdOptions;
	private String strMaintenanceName;
	private String strMaintenancePeriod;
	private String strMaintenancePeriodUnitId;
	private String strMaintenancePeriodUnitIdOptions;
	private String strMaintenancePeriodUnitName;
	private String strMcSlNo;
	private String strNonItemId;
	private String strNonItemName;
	private String strNonItemOptions;
	private String strNormalMsg;
	private String strPreferTimeFrom;
	private String strPreferTimeTo;
	private String strRecordStatusName;
	private String strRemarks;
	private String strReqType;
	private String[] strRightTaskId;
	
	private String strRightTaskIdOptions;
	//private String strRoomId;
	//private String strRoomIdOptions;
	//private String strRoomNumber;
	private String strSeatId;
	//private String strStoreId;
	//private String strStoreIdOptions;
	//private String strStoreName;
	private String strVendorId;
	private String strWarningMsg;
	private String strMaintenanceContractDetails;

	private String strUploadFileId;
	
	private String strWarrantySlNo;
	/**
	 * @return the listStrTaskName
	 */
	public ArrayList<String> getListStrTaskName() {
		return listStrTaskName;
	}
	/**
	 * @return the strAlertPeriod
	 */
	public String getStrAlertPeriod() {
		return strAlertPeriod;
	}
	/**
	 * @return the strBlockCode
	 */
//	public String getStrBlockCode() {
//		return strBlockCode;
//	}
	
	/**
	 * @return the strBlockCodeOptions
	 */
//	public String getStrBlockCodeOptions() {
//		return strBlockCodeOptions;
//	}
	/**
	 * @return the strBlockName
	 */
//	public String getStrBlockName() {
//		return strBlockName;
//	}
	/**
	 * @return the strBuildingCode
	 */
//	public String getStrBuildingCode() {
//		return strBuildingCode;
//	}
	/**
	 * @return the strBuildingCodeOptions
	 */
//	public String getStrBuildingCodeOptions() {
//		return strBuildingCodeOptions;
//	}
	/**
	 * @return the strBuildingName
	 */
//	public String getStrBuildingName() {
//		return strBuildingName;
//	}
	/**
	 * @return the strCurrentDate
	 */
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	/**
	 * @return the strDeptId
	 */
	public String getStrDeptId() {
		return strDeptId;
	}
	/**
	 * @return the strDeptIdOptions
	 */
	public String getStrDeptIdOptions() {
		return strDeptIdOptions;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	/**
	 * @return the strEffectiveFrom
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	/**
	 * @return the strEngineeringItemSubTypeId
	 */
	public String getStrEngineeringItemSubTypeId() {
		return strEngineeringItemSubTypeId;
	}
	/**
	 * @return the strEngineeringItemSubTypeName
	 */
	public String getStrEngineeringItemSubTypeName() {
		return strEngineeringItemSubTypeName;
	}
	/**
	 * @return the strEngineeringItemTypeId
	 */
	public String getStrEngineeringItemTypeId() {
		return strEngineeringItemTypeId;
	}
	/**
	 * @return the strEngineeringItemTypeName
	 */
	public String getStrEngineeringItemTypeName() {
		return strEngineeringItemTypeName;
	}
	/**
	 * @return the strErrMsg
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}
	/**
	 * @return the strFloorId
	 */
//	public String getStrFloorId() {
//		return strFloorId;
//	}
	/**
	 * @return the strFloorIdOptions
	 */
//	public String getStrFloorIdOptions() {
//		return strFloorIdOptions;
//	}
	/**
	 * @return the strFloorName
	 */
//	public String getStrFloorName() {
//		return strFloorName;
//	}
	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	/**
	 * @return the strIsValid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}
	/**
	 * @return the strLandmarkDesc
	 */
	public String getStrLandmarkDesc() {
		return strLandmarkDesc;
	}
	/**
	 * @return the strLeftTaskIdOptions
	 */
	public String getStrLeftTaskIdOptions() {
		return strLeftTaskIdOptions;
	}
	/**
	 * @return the strMaintenanceId
	 */
	public String getStrMaintenanceId() {
		return strMaintenanceId;
	}
	/**
	 * @return the strMaintenanceIdOptions
	 */
	public String getStrMaintenanceIdOptions() {
		return strMaintenanceIdOptions;
	}
	/**
	 * @return the strMaintenanceName
	 */
	public String getStrMaintenanceName() {
		return strMaintenanceName;
	}
	/**
	 * @return the strMaintenancePeriod
	 */
	public String getStrMaintenancePeriod() {
		return strMaintenancePeriod;
	}
	/**
	 * @return the strMaintenancePeriodUnitId
	 */
	public String getStrMaintenancePeriodUnitId() {
		return strMaintenancePeriodUnitId;
	}
	/**
	 * @return the strMaintenancePeriodUnitIdOptions
	 */
	public String getStrMaintenancePeriodUnitIdOptions() {
		return strMaintenancePeriodUnitIdOptions;
	}
	/**
	 * @return the strMaintenancePeriodName
	 */
	public String getStrMaintenancePeriodUnitName() {
		return strMaintenancePeriodUnitName;
	}
	public String getStrMcSlNo() {
		return strMcSlNo;
	}
	public String getStrNonItemId() {
		return strNonItemId;
	}
	public String getStrNonItemName() {
		return strNonItemName;
	}
	public String getStrNonItemOptions() {
		return strNonItemOptions;
	}
	/**
	 * @return the strNormalMsg
	 */
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	/**
	 * @return the strPreferTimeFrom
	 */
	public String getStrPreferTimeFrom() {
		return strPreferTimeFrom;
	}
	/**
	 * @return the strPreferTimeTo
	 */
	public String getStrPreferTimeTo() {
		return strPreferTimeTo;
	}
	/**
	 * @return the strRecordStatusName
	 */
	public String getStrRecordStatusName() {
		return strRecordStatusName;
	}
	/**
	 * @return the strRemarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}
	public String getStrReqType() {
		return strReqType;
	}
	/**
	 * @return the strRightTaskId
	 */
	public String[] getStrRightTaskId() {
		return strRightTaskId;
	}
	/**
	 * @return the strRightTaskIdOptions
	 */
	public String getStrRightTaskIdOptions() {
		return strRightTaskIdOptions;
	}
	/**
	 * @return the strRoomId
	 */
//	public String getStrRoomId() {
//		return strRoomId;
//	}
	/**
	 * @return the strRoomIdOptions
	 */
//	public String getStrRoomIdOptions() {
//		return strRoomIdOptions;
//	}
	/**
	 * @return the strRoomNumber
	 */
//	public String getStrRoomNumber() {
//		return strRoomNumber;
//	}
	/**
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}
	public String getStrVendorId() {
		return strVendorId;
	}
	/**
	 * @return the strStoreId
	 */
//	public String getStrStoreId() {
//		return strStoreId;
//	}
	/**
	 * @return the strStoreIdOptions
	 */
//	public String getStrStoreIdOptions() {
//		return strStoreIdOptions;
//	}
	/**
	 * @return the strStoreName
	 */
//	public String getStrStoreName() {
//		return strStoreName;
//	}
	/**
	 * @return the strWarningMsg
	 */
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public String getStrWarrantySlNo() {
		return strWarrantySlNo;
	}
	/**
	 * @param listStrTaskName the listStrTaskName to set
	 */
	public void setListStrTaskName(ArrayList<String> listStrTaskName) {
		this.listStrTaskName = listStrTaskName;
	}
	/**
	 * @param strAlertPeriod the strAlertPeriod to set
	 */
	public void setStrAlertPeriod(String strAlertPeriod) {
		this.strAlertPeriod = strAlertPeriod;
	}
	/**
	 * @param strBlockCode the strBlockCode to set
	 */
//	public void setStrBlockCode(String strBlockCode) {
//		this.strBlockCode = strBlockCode;
//	}
	/**
	 * @param strBlockCodeOptions the strBlockCodeOptions to set
	 */
//	public void setStrBlockCodeOptions(String strBlockCodeOptions) {
//		this.strBlockCodeOptions = strBlockCodeOptions;
//	}
	/**
	 * @param strBlockName the strBlockName to set
	 */
//	public void setStrBlockName(String strBlockName) {
//		this.strBlockName = strBlockName;
//	}
	/**
	 * @param strBuildingCode the strBuildingCode to set
	 */
//	public void setStrBuildingCode(String strBuildingCode) {
//		this.strBuildingCode = strBuildingCode;
//	}
	/**
	 * @param strBuildingCodeOptions the strBuildingCodeOptions to set
	 */
//	public void setStrBuildingCodeOptions(String strBuildingCodeOptions) {
//		this.strBuildingCodeOptions = strBuildingCodeOptions;
//	}
	/**
	 * @param strBuildingName the strBuildingName to set
	 */
//	public void setStrBuildingName(String strBuildingName) {
//		this.strBuildingName = strBuildingName;
//	}
	/**
	 * @param strCurrentDate the strCurrentDate to set
	 */
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	/**
	 * @param strDeptId the strDeptId to set
	 */
	public void setStrDeptId(String strDeptId) {
		this.strDeptId = strDeptId;
	}
	/**
	 * @param strDeptIdOptions the strDeptIdOptions to set
	 */
	public void setStrDeptIdOptions(String strDeptIdOptions) {
		this.strDeptIdOptions = strDeptIdOptions;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	/**
	 * @param strEffectiveFrom the strEffectiveFrom to set
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	/**
	 * @param strEngineeringItemSubTypeId the strEngineeringItemSubTypeId to set
	 */
	public void setStrEngineeringItemSubTypeId(String strEngineeringItemSubTypeId) {
		this.strEngineeringItemSubTypeId = strEngineeringItemSubTypeId;
	}
	/**
	 * @param strEngineeringItemSubTypeName the strEngineeringItemSubTypeName to set
	 */
	public void setStrEngineeringItemSubTypeName(
			String strEngineeringItemSubTypeName) {
		this.strEngineeringItemSubTypeName = strEngineeringItemSubTypeName;
	}
	/**
	 * @param strEngineeringItemTypeId the strEngineeringItemTypeId to set
	 */
	public void setStrEngineeringItemTypeId(String strEngineeringItemTypeId) {
		this.strEngineeringItemTypeId = strEngineeringItemTypeId;
	}
	/**
	 * @param strEngineeringItemTypeName the strEngineeringItemTypeName to set
	 */
	public void setStrEngineeringItemTypeName(String strEngineeringItemTypeName) {
		this.strEngineeringItemTypeName = strEngineeringItemTypeName;
	}
	/**
	 * @param strErrMsg the strErrMsg to set
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	/**
	 * @param strFloorId the strFloorId to set
	 */
//	public void setStrFloorId(String strFloorId) {
//		this.strFloorId = strFloorId;
//	}
	/**
	 * @param strFloorIdOptions the strFloorIdOptions to set
	 */
//	public void setStrFloorIdOptions(String strFloorIdOptions) {
//		this.strFloorIdOptions = strFloorIdOptions;
//	}
	/**
	 * @param strFloorName the strFloorName to set
	 */
//	public void setStrFloorName(String strFloorName) {
//		this.strFloorName = strFloorName;
//	}
	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	/**
	 * @param strIsValid the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	/**
	 * @param strLandmarkDesc the strLandmarkDesc to set
	 */
	public void setStrLandmarkDesc(String strLandmarkDesc) {
		this.strLandmarkDesc = strLandmarkDesc;
	}
	/**
	 * @param strLeftTaskIdOptions the strLeftTaskIdOptions to set
	 */
	public void setStrLeftTaskIdOptions(String strLeftTaskIdOptions) {
		this.strLeftTaskIdOptions = strLeftTaskIdOptions;
	}
	/**
	 * @param strMaintenanceId the strMaintenanceId to set
	 */
	public void setStrMaintenanceId(String strMaintenanceId) {
		this.strMaintenanceId = strMaintenanceId;
	}
	/**
	 * @param strMaintenanceIdOptions the strMaintenanceIdOptions to set
	 */
	public void setStrMaintenanceIdOptions(String strMaintenanceIdOptions) {
		this.strMaintenanceIdOptions = strMaintenanceIdOptions;
	}
	/**
	 * @param strMaintenanceName the strMaintenanceName to set
	 */
	public void setStrMaintenanceName(String strMaintenanceName) {
		this.strMaintenanceName = strMaintenanceName;
	}
	/**
	 * @param strMaintenancePeriod the strMaintenancePeriod to set
	 */
	public void setStrMaintenancePeriod(String strMaintenancePeriod) {
		this.strMaintenancePeriod = strMaintenancePeriod;
	}
	/**
	 * @param strMaintenancePeriodUnitId the strMaintenancePeriodUnitId to set
	 */
	public void setStrMaintenancePeriodUnitId(String strMaintenancePeriodUnitId) {
		this.strMaintenancePeriodUnitId = strMaintenancePeriodUnitId;
	}
	/**
	 * @param strMaintenancePeriodUnitIdOptions the strMaintenancePeriodUnitIdOptions to set
	 */
	public void setStrMaintenancePeriodUnitIdOptions(
			String strMaintenancePeriodUnitIdOptions) {
		this.strMaintenancePeriodUnitIdOptions = strMaintenancePeriodUnitIdOptions;
	}
	/**
	 * @param strMaintenancePeriodName the strMaintenancePeriodName to set
	 */
	public void setStrMaintenancePeriodUnitName(String strMaintenancePeriodUnitName) {
		this.strMaintenancePeriodUnitName = strMaintenancePeriodUnitName;
	}
	public void setStrMcSlNo(String strMcSlNo) {
		this.strMcSlNo = strMcSlNo;
	}
	public void setStrNonItemId(String strNonItemId) {
		this.strNonItemId = strNonItemId;
	}
	public void setStrNonItemName(String strNonItemName) {
		this.strNonItemName = strNonItemName;
	}
	public void setStrNonItemOptions(String strNonItemOptions) {
		this.strNonItemOptions = strNonItemOptions;
	}
	/**
	 * @param strNormalMsg the strNormalMsg to set
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	/**
	 * @param strPreferTimeFrom the strPreferTimeFrom to set
	 */
	public void setStrPreferTimeFrom(String strPreferTimeFrom) {
		this.strPreferTimeFrom = strPreferTimeFrom;
	}
	/**
	 * @param strPreferTimeTo the strPreferTimeTo to set
	 */
	public void setStrPreferTimeTo(String strPreferTimeTo) {
		this.strPreferTimeTo = strPreferTimeTo;
	}
	/**
	 * @param strRecordStatusName the strRecordStatusName to set
	 */
	public void setStrRecordStatusName(String strRecordStatusName) {
		this.strRecordStatusName = strRecordStatusName;
	}
	/**
	 * @param strRemarks the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public void setStrReqType(String strReqType) {
		this.strReqType = strReqType;
	}
	/**
	 * @param strRightTaskId the strRightTaskId to set
	 */
	public void setStrRightTaskId(String[] strRightTaskId) {
		this.strRightTaskId = strRightTaskId;
	}
	/**
	 * @param strRightTaskIdOptions the strRightTaskIdOptions to set
	 */
	public void setStrRightTaskIdOptions(String strRightTaskIdOptions) {
		this.strRightTaskIdOptions = strRightTaskIdOptions;
	}
	/**
	 * @param strRoomId the strRoomId to set
	 */
//	public void setStrRoomId(String strRoomId) {
//		this.strRoomId = strRoomId;
//	}
	/**
	 * @param strRoomIdOptions the strRoomIdOptions to set
	 */
//	public void setStrRoomIdOptions(String strRoomIdOptions) {
//		this.strRoomIdOptions = strRoomIdOptions;
//	}
	/**
	 * @param strRoomNumber the strRoomNumber to set
	 */
//	public void setStrRoomNumber(String strRoomNumber) {
//		this.strRoomNumber = strRoomNumber;
//	}
	/**
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public void setStrVendorId(String strVendorId) {
		this.strVendorId = strVendorId;
	}
	/**
	 * @param strStoreId the strStoreId to set
	 */
//	public void setStrStoreId(String strStoreId) {
//		this.strStoreId = strStoreId;
//	}
	/**
	 * @param strStoreIdOptions the strStoreIdOptions to set
	 */
//	public void setStrStoreIdOptions(String strStoreIdOptions) {
//		this.strStoreIdOptions = strStoreIdOptions;
//	}
	/**
	 * @param strStoreName the strStoreName to set
	 */
//	public void setStrStoreName(String strStoreName) {
//		this.strStoreName = strStoreName;
//	}
	/**
	 * @param strWarningMsg the strWarningMsg to set
	 */
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	public void setStrWarrantySlNo(String strWarrantySlNo) {
		this.strWarrantySlNo = strWarrantySlNo;
	}
	/**
	 * @param strMaintenanceContractDetails the strMaintenanceContractDetails to set
	 */
	public void setStrMaintenanceContractDetails(
			String strMaintenanceContractDetails) {
		this.strMaintenanceContractDetails = strMaintenanceContractDetails;
	}
	/**
	 * @return the strMaintenanceContractDetails
	 */
	public String getStrMaintenanceContractDetails() {
		return strMaintenanceContractDetails;
	}
	public String getStrUploadFileId() {
		return strUploadFileId;
	}
	public void setStrUploadFileId(String strUploadFileId) {
		this.strUploadFileId = strUploadFileId;
	}
	
	
	
	
	
}
