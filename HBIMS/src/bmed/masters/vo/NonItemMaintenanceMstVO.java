package bmed.masters.vo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

public class NonItemMaintenanceMstVO implements Serializable {

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
	private static final long serialVersionUID = 4376979210715954411L;

	/* Process specific instance variables. */

	private boolean fExistStatus;
	private ArrayList<String> listStrTaskName;
	private String strAlertPeriod;
	// private String strBlockCode;
	// private String strBlockName;
	// private String strBuildingCode;
	// private String strBuildingName;
	private String strCurrentDate;
	private String strDeptId;
	private String strDeptName;
	private String strEffectiveFrom;
	private String strEngineeringItemSubTypeId;
	private String strEngineeringItemSubTypeName;
	private String strEngineeringItemTypeId;
	private String strEngineeringItemTypeName;
	private String strErrMsg;
	// private String strFloorId;
	// private String strFloorName;
	private String strHospitalCode;
	private String strIsValid;
	private String strLandmarkDesc;
	private String strMaintenanceId;
	private String strMaintenanceName;
	private String strMaintenancePeriod;
	private String strMaintenancePeriodUnitId;
	private String strMaintenancePeriodUnitName;
	private String strMcSlNo;
	private String strMsgString;
	private String strMsgType;
	private String strNonItemId;
	private String strNonItemName;
	private String strNormalMsg;
	private String strPreferTimeFrom;
	private String strPreferTimeTo;
	private String strRecordStatusName;
	private String strRemarks;
	private String strReqType;
	private String[] strRightTaskId;
	// private String strRoomId;
	// private String strRoomNumber;
	private String strSeatId;
	//private String strStoreId;
	//private String strStoreName;
	private String strVendorId;
	private String strWarningMsg;
	private String strWarrantySlNo;
	//private WebRowSet wrsBlockCodeOptions;
	//private WebRowSet wrsBuildingCodeOptions;
	private WebRowSet wrsDeptIdOptions;
	//private WebRowSet wrsFloorIdOptions;
	private WebRowSet wrsLeftTaskIdOptions;
	private WebRowSet wrsMaintenanceIdOptions;
	private WebRowSet wrsMaintenancePeriodUnitIdOptions;
	private WebRowSet wrsNonItemOptions;
	private WebRowSet wrsRightTaskIdOptions;
	//private WebRowSet wrsRoomIdOptions;
	//private WebRowSet wrsStoreIdOptions;


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
	// public String getStrBlockCode() {
	// return strBlockCode;
	// }
	/**
	 * @return the strBlockName
	 */
	// public String getStrBlockName() {
	// return strBlockName;
	// }
	/**
	 * @return the strBuildingCode
	 */
	// public String getStrBuildingCode() {
	// return strBuildingCode;
	// }
	/**
	 * @return the strBuildingName
	 */
	// public String getStrBuildingName() {
	// return strBuildingName;
	// }
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
	// public String getStrFloorId() {
	// return strFloorId;
	// }
	/**
	 * @return the strFloorName
	 */
	// public String getStrFloorName() {
	// return strFloorName;
	// }
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
	 * @return the strMaintenanceId
	 */
	public String getStrMaintenanceId() {
		return strMaintenanceId;
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
	 * @return the strMaintenancePeriodName
	 */
	public String getStrMaintenancePeriodUnitName() {
		return strMaintenancePeriodUnitName;
	}

	public String getStrMcSlNo() {
		return strMcSlNo;
	}

	/**
	 * @return the strMsgString
	 */
	public String getStrMsgString() {
		return strMsgString;
	}

	/**
	 * @return the strMsgType
	 */
	public String getStrMsgType() {
		return strMsgType;
	}

	public String getStrNonItemId() {
		return strNonItemId;
	}

	public String getStrNonItemName() {
		return strNonItemName;
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
	 * @return the strRoomId
	 */
	// public String getStrRoomId() {
	// return strRoomId;
	// }
	/**
	 * @return the strRoomNumber
	 */
	// public String getStrRoomNumber() {
	// return strRoomNumber;
	// }
	/**
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}

	/**
	 * @return the strStoreName
	 */
	/*public String getStrStoreName() {
		return strStoreName;
	}*/

	public String getStrVendorId() {
		return strVendorId;
	}

	/**
	 * @return the strWarningMsg
	 */
	public String getStrWarningMsg() {
		return strWarningMsg;
	}

	/**
	 * @return the wrsBlockCodeOptions
	 */
//	public WebRowSet getWrsBlockCodeOptions() {
//		return wrsBlockCodeOptions;
//	}

	/**
	 * @return the wrsBuildingCodeOptions
	 */
//	public WebRowSet getWrsBuildingCodeOptions() {
//		return wrsBuildingCodeOptions;
//	}

	/**
	 * @return the strStoreId
	 */
//	public String getStrStoreId() {
//		return strStoreId;
//	}

	public String getStrWarrantySlNo() {
		return strWarrantySlNo;
	}

	/**
	 * @return the wrsFloorIdOptions
	 */
//	public WebRowSet getWrsFloorIdOptions() {
//		return wrsFloorIdOptions;
//	}

	/**
	 * @return the wrsDeptIdOptions
	 */
	public WebRowSet getWrsDeptIdOptions() {
		return wrsDeptIdOptions;
	}

	/**
	 * @return the wrsLeftTaskIdOptions
	 */
	public WebRowSet getWrsLeftTaskIdOptions() {
		return wrsLeftTaskIdOptions;
	}

	/**
	 * @return the wrsMaintenanceIdOptions
	 */
	public WebRowSet getWrsMaintenanceIdOptions() {
		return wrsMaintenanceIdOptions;
	}

	/**
	 * @return the wrsMaintenancePeriodUnitIdOptions
	 */
	public WebRowSet getWrsMaintenancePeriodUnitIdOptions() {
		return wrsMaintenancePeriodUnitIdOptions;
	}

	public WebRowSet getWrsNonItemOptions() {
		return wrsNonItemOptions;
	}

	/**
	 * @return the wrsRoomIdOptions
	 */
//	public WebRowSet getWrsRoomIdOptions() {
//		return wrsRoomIdOptions;
//	}

	/**
	 * @return the wrsStoreIdOptions
	 */
//	public WebRowSet getWrsStoreIdOptions() {
//		return wrsStoreIdOptions;
//	}

	/**
	 * @return the wrsRightTaskIdOptions
	 */
	public WebRowSet getWrsRightTaskIdOptions() {
		return wrsRightTaskIdOptions;
	}

	/**
	 * @return the fExistStatus
	 */
	public boolean isfExistStatus() {
		return fExistStatus;
	}

	/**
	 * @param fExistStatus
	 *            the fExistStatus to set
	 */
	public void setfExistStatus(boolean fExistStatus) {
		this.fExistStatus = fExistStatus;
	}

	/**
	 * @param listStrTaskName
	 *            the listStrTaskName to set
	 */
	public void setListStrTaskName(ArrayList<String> listStrTaskName) {
		this.listStrTaskName = listStrTaskName;
	}

	/**
	 * @param strAlertPeriod
	 *            the strAlertPeriod to set
	 */
	public void setStrAlertPeriod(String strAlertPeriod) {
		this.strAlertPeriod = strAlertPeriod;
	}

	/**
	 * @param strBlockCode
	 *            the strBlockCode to set
	 */
	// public void setStrBlockCode(String strBlockCode) {
	// this.strBlockCode = strBlockCode;
	// }
	/**
	 * @param strBlockName
	 *            the strBlockName to set
	 */
	// public void setStrBlockName(String strBlockName) {
	// this.strBlockName = strBlockName;
	// }
	/**
	 * @param strBuildingCode
	 *            the strBuildingCode to set
	 */
	// public void setStrBuildingCode(String strBuildingCode) {
	// this.strBuildingCode = strBuildingCode;
	// }
	/**
	 * @param strBuildingName
	 *            the strBuildingName to set
	 */
	// public void setStrBuildingName(String strBuildingName) {
	// this.strBuildingName = strBuildingName;
	// }
	/**
	 * @param strCurrentDate
	 *            the strCurrentDate to set
	 */
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}

	/**
	 * @param strDeptId
	 *            the strDeptId to set
	 */
	public void setStrDeptId(String strDeptId) {
		this.strDeptId = strDeptId;
	}

	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}

	/**
	 * @param strEffectiveFrom
	 *            the strEffectiveFrom to set
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	/**
	 * @param strEngineeringItemSubTypeId
	 *            the strEngineeringItemSubTypeId to set
	 */
	public void setStrEngineeringItemSubTypeId(
			String strEngineeringItemSubTypeId) {
		this.strEngineeringItemSubTypeId = strEngineeringItemSubTypeId;
	}

	/**
	 * @param strEngineeringItemSubTypeName
	 *            the strEngineeringItemSubTypeName to set
	 */
	public void setStrEngineeringItemSubTypeName(
			String strEngineeringItemSubTypeName) {
		this.strEngineeringItemSubTypeName = strEngineeringItemSubTypeName;
	}

	/**
	 * @param strEngineeringItemTypeId
	 *            the strEngineeringItemTypeId to set
	 */
	public void setStrEngineeringItemTypeId(String strEngineeringItemTypeId) {
		this.strEngineeringItemTypeId = strEngineeringItemTypeId;
	}

	/**
	 * @param strEngineeringItemTypeName
	 *            the strEngineeringItemTypeName to set
	 */
	public void setStrEngineeringItemTypeName(String strEngineeringItemTypeName) {
		this.strEngineeringItemTypeName = strEngineeringItemTypeName;
	}

	/**
	 * @param strErrMsg
	 *            the strErrMsg to set
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	/**
	 * @param strFloorId
	 *            the strFloorId to set
	 */
	// public void setStrFloorId(String strFloorId) {
	// this.strFloorId = strFloorId;
	// }
	/**
	 * @param strFloorName
	 *            the strFloorName to set
	 */
	// public void setStrFloorName(String strFloorName) {
	// this.strFloorName = strFloorName;
	// }
	/**
	 * @param strHospitalCode
	 *            the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * @param strIsValid
	 *            the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	/**
	 * @param strLandmarkDesc
	 *            the strLandmarkDesc to set
	 */
	public void setStrLandmarkDesc(String strLandmarkDesc) {
		this.strLandmarkDesc = strLandmarkDesc;
	}

	/**
	 * @param strMaintenanceId
	 *            the strMaintenanceId to set
	 */
	public void setStrMaintenanceId(String strMaintenanceId) {
		this.strMaintenanceId = strMaintenanceId;
	}

	/**
	 * @param strMaintenanceName
	 *            the strMaintenanceName to set
	 */
	public void setStrMaintenanceName(String strMaintenanceName) {
		this.strMaintenanceName = strMaintenanceName;
	}

	/**
	 * @param strMaintenancePeriod
	 *            the strMaintenancePeriod to set
	 */
	public void setStrMaintenancePeriod(String strMaintenancePeriod) {
		this.strMaintenancePeriod = strMaintenancePeriod;
	}

	/**
	 * @param strMaintenancePeriodUnitId
	 *            the strMaintenancePeriodUnitId to set
	 */
	public void setStrMaintenancePeriodUnitId(String strMaintenancePeriodUnitId) {
		this.strMaintenancePeriodUnitId = strMaintenancePeriodUnitId;
	}

	/**
	 * @param strMaintenancePeriodName
	 *            the strMaintenancePeriodName to set
	 */
	public void setStrMaintenancePeriodUnitName(
			String strMaintenancePeriodUnitName) {
		this.strMaintenancePeriodUnitName = strMaintenancePeriodUnitName;
	}

	public void setStrMcSlNo(String strMcSlNo) {
		this.strMcSlNo = strMcSlNo;
	}

	/**
	 * @param strMsgString
	 *            the strMsgString to set
	 */
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	/**
	 * @param strMsgType
	 *            the strMsgType to set
	 */
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public void setStrNonItemId(String strNonItemId) {
		this.strNonItemId = strNonItemId;
	}

	public void setStrNonItemName(String strNonItemName) {
		this.strNonItemName = strNonItemName;
	}

	/**
	 * @param strNormalMsg
	 *            the strNormalMsg to set
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}

	/**
	 * @param strPreferTimeFrom
	 *            the strPreferTimeFrom to set
	 */
	public void setStrPreferTimeFrom(String strPreferTimeFrom) {
		this.strPreferTimeFrom = strPreferTimeFrom;
	}

	/**
	 * @param strPreferTimeTo
	 *            the strPreferTimeTo to set
	 */
	public void setStrPreferTimeTo(String strPreferTimeTo) {
		this.strPreferTimeTo = strPreferTimeTo;
	}

	/**
	 * @param strRecordStatusName
	 *            the strRecordStatusName to set
	 */
	public void setStrRecordStatusName(String strRecordStatusName) {
		this.strRecordStatusName = strRecordStatusName;
	}

	/**
	 * @param strRemarks
	 *            the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public void setStrReqType(String strReqType) {
		this.strReqType = strReqType;
	}

	/**
	 * @param strRightTaskId
	 *            the strRightTaskId to set
	 */
	public void setStrRightTaskId(String[] strRightTaskId) {
		this.strRightTaskId = strRightTaskId;
	}

	/**
	 * @param strStoreId
	 *            the strStoreId to set
	 */
	// public void setStrStoreId(String strStoreId) {
	// this.strStoreId = strStoreId;
	// }

	/**
	 * @param strRoomId
	 *            the strRoomId to set
	 */
	// public void setStrRoomId(String strRoomId) {
	// this.strRoomId = strRoomId;
	// }
	/**
	 * @param strRoomNumber
	 *            the strRoomNumber to set
	 */
	// public void setStrRoomNumber(String strRoomNumber) {
	// this.strRoomNumber = strRoomNumber;
	// }
	/**
	 * @param strSeatId
	 *            the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * @param strStoreName
	 *            the strStoreName to set
	 */
//	public void setStrStoreName(String strStoreName) {
//		this.strStoreName = strStoreName;
//	}

	public void setStrVendorId(String strVendorId) {
		this.strVendorId = strVendorId;
	}

	/**
	 * @param wrsBlockCodeOptions
	 *            the wrsBlockCodeOptions to set
	 */
//	public void setWrsBlockCodeOptions(WebRowSet wrsBlockCodeOptions) {
//		this.wrsBlockCodeOptions = wrsBlockCodeOptions;
//	}

	/**
	 * @param wrsBuildingCodeOptions
	 *            the wrsBuildingCodeOptions to set
	 */
//	public void setWrsBuildingCodeOptions(WebRowSet wrsBuildingCodeOptions) {
//		this.wrsBuildingCodeOptions = wrsBuildingCodeOptions;
//	}

	/**
	 * @param strWarningMsg
	 *            the strWarningMsg to set
	 */
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

	/**
	 * @param wrsFloorIdOptions
	 *            the wrsFloorIdOptions to set
	 */
//	public void setWrsFloorIdOptions(WebRowSet wrsFloorIdOptions) {
//		this.wrsFloorIdOptions = wrsFloorIdOptions;
//	}

	public void setStrWarrantySlNo(String strWarrantySlNo) {
		this.strWarrantySlNo = strWarrantySlNo;
	}

	/**
	 * @param wrsDeptIdOptions
	 *            the wrsDeptIdOptions to set
	 */
	public void setWrsDeptIdOptions(WebRowSet wrsDeptIdOptions) {
		this.wrsDeptIdOptions = wrsDeptIdOptions;
	}

	/**
	 * @param wrsLeftTaskIdOptions
	 *            the wrsLeftTaskIdOptions to set
	 */
	public void setWrsLeftTaskIdOptions(WebRowSet wrsLeftTaskIdOptions) {
		this.wrsLeftTaskIdOptions = wrsLeftTaskIdOptions;
	}

	/**
	 * @param wrsMaintenanceIdOptions
	 *            the wrsMaintenanceIdOptions to set
	 */
	public void setWrsMaintenanceIdOptions(WebRowSet wrsMaintenanceIdOptions) {
		this.wrsMaintenanceIdOptions = wrsMaintenanceIdOptions;
	}

	/**
	 * @param wrsMaintenancePeriodUnitIdOptions
	 *            the wrsMaintenancePeriodUnitIdOptions to set
	 */
	public void setWrsMaintenancePeriodUnitIdOptions(
			WebRowSet wrsMaintenancePeriodUnitIdOptions) {
		this.wrsMaintenancePeriodUnitIdOptions = wrsMaintenancePeriodUnitIdOptions;
	}

	public void setWrsNonItemOptions(WebRowSet wrsNonItemOptions) {
		this.wrsNonItemOptions = wrsNonItemOptions;
	}

	/**
	 * @param wrsRightTaskIdOptions
	 *            the wrsRightTaskIdOptions to set
	 */
	public void setWrsRightTaskIdOptions(WebRowSet wrsRightTaskIdOptions) {
		this.wrsRightTaskIdOptions = wrsRightTaskIdOptions;
	}

	/**
	 * @param wrsRoomIdOptions
	 *            the wrsRoomIdOptions to set
	 */
//	public void setWrsRoomIdOptions(WebRowSet wrsRoomIdOptions) {
//		this.wrsRoomIdOptions = wrsRoomIdOptions;
//	}

	/**
	 * @param wrsStoreIdOptions
	 *            the wrsStoreIdOptions to set
	 */
//	public void setWrsStoreIdOptions(WebRowSet wrsStoreIdOptions) {
//		this.wrsStoreIdOptions = wrsStoreIdOptions;
//	}

}
