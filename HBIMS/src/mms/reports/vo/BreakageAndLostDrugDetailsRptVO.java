package mms.reports.vo;

import javax.sql.rowset.WebRowSet;

public class BreakageAndLostDrugDetailsRptVO {
	
	private String strMode;
	private String strHospitalCode = "";
	private String strSeatId = "";
	
	private String strMsgType = "";
	private String strMsgString = "";
	
	private String strStoreId = "";
	private String strDrugWarehouseTypeId = "";

	private WebRowSet strDrugWarehouseTypeWs = null;
	private WebRowSet strStoreWs = null;
	
	private WebRowSet strItemCatWs = null;
	private WebRowSet strDistrictStoreWs = null;
	
	
	
	public WebRowSet getStrItemCatWs() {
		return strItemCatWs;
	}
	public void setStrItemCatWs(WebRowSet strItemCatWs) {
		this.strItemCatWs = strItemCatWs;
	}
	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	/**
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}
	/**
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	/**
	 * @return the strMsgType
	 */
	public String getStrMsgType() {
		return strMsgType;
	}
	/**
	 * @param strMsgType the strMsgType to set
	 */
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	/**
	 * @return the strMsgString
	 */
	public String getStrMsgString() {
		return strMsgString;
	}
	/**
	 * @param strMsgString the strMsgString to set
	 */
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public WebRowSet getStrStoreWs() {
		return strStoreWs;
	}
	public void setStrStoreWs(WebRowSet strStoreWs) {
		this.strStoreWs = strStoreWs;
	}
	public String getStrDrugWarehouseTypeId() {
		return strDrugWarehouseTypeId;
	}
	public void setStrDrugWarehouseTypeId(String strDrugWarehouseTypeId) {
		this.strDrugWarehouseTypeId = strDrugWarehouseTypeId;
	}
	public WebRowSet getStrDrugWarehouseTypeWs() {
		return strDrugWarehouseTypeWs;
	}
	public void setStrDrugWarehouseTypeWs(WebRowSet strDrugWarehouseTypeWs) {
		this.strDrugWarehouseTypeWs = strDrugWarehouseTypeWs;
	}
	public WebRowSet getStrDistrictStoreWs() {
		return strDistrictStoreWs;
	}
	public void setStrDistrictStoreWs(WebRowSet strDistrictStoreWs) {
		this.strDistrictStoreWs = strDistrictStoreWs;
	}
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	

}

