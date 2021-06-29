package bmed.reports.vo;

import javax.sql.rowset.WebRowSet;

public class EquipmentInspectionOrTestReportVO 
{
	private String strMode="";
	private String strHospitalCode="";
	private String strSeatid;
	private String strEquipmentTestChkDetail="1";
	
	private String strUserRemarks="";
	
	private String strReportFormat = "0";
	private String strReportId = "";

	private String strFromDate = "";
	private String strToDate = "";
	private String strUniqId="0";
	private String strUniqValCmb;
	private String strStoreId;
	private String strStoreCmb;
	private String strItemId;
	private String strTestId;
	private String strTestCmb;
	private String strEquipmentTestSlNoId;
	
	private WebRowSet wrsData;
	private WebRowSet wrsItemName;
	private WebRowSet wrsTestData;
	private WebRowSet wrsEquipmentSlNo;
	
	
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrSeatid() {
		return strSeatid;
	}
	public void setStrSeatid(String strSeatid) {
		this.strSeatid = strSeatid;
	}
	public String getStrEquipmentTestChkDetail() {
		return strEquipmentTestChkDetail;
	}
	public void setStrEquipmentTestChkDetail(String strEquipmentTestChkDetail) {
		this.strEquipmentTestChkDetail = strEquipmentTestChkDetail;
	}
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrFromDate() {
		return strFromDate;
	}
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}
	public String getStrToDate() {
		return strToDate;
	}
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}
	public String getStrUniqId() {
		return strUniqId;
	}
	public void setStrUniqId(String strUniqId) {
		this.strUniqId = strUniqId;
	}
	public String getStrUniqValCmb() {
		return strUniqValCmb;
	}
	public void setStrUniqValCmb(String strUniqValCmb) {
		this.strUniqValCmb = strUniqValCmb;
	}
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public String getStrStoreCmb() {
		return strStoreCmb;
	}
	public void setStrStoreCmb(String strStoreCmb) {
		this.strStoreCmb = strStoreCmb;
	}
	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrTestId() {
		return strTestId;
	}
	public void setStrTestId(String strTestId) {
		this.strTestId = strTestId;
	}
	public String getStrTestCmb() {
		return strTestCmb;
	}
	public void setStrTestCmb(String strTestCmb) {
		this.strTestCmb = strTestCmb;
	}
	public String getStrEquipmentTestSlNoId() {
		return strEquipmentTestSlNoId;
	}
	public void setStrEquipmentTestSlNoId(String strEquipmentTestSlNoId) {
		this.strEquipmentTestSlNoId = strEquipmentTestSlNoId;
	}
	public WebRowSet getWrsData() {
		return wrsData;
	}
	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
	}
	public WebRowSet getWrsItemName() {
		return wrsItemName;
	}
	public void setWrsItemName(WebRowSet wrsItemName) {
		this.wrsItemName = wrsItemName;
	}
	public WebRowSet getWrsTestData() {
		return wrsTestData;
	}
	public void setWrsTestData(WebRowSet wrsTestData) {
		this.wrsTestData = wrsTestData;
	}
	public WebRowSet getWrsEquipmentSlNo() {
		return wrsEquipmentSlNo;
	}
	public void setWrsEquipmentSlNo(WebRowSet wrsEquipmentSlNo) {
		this.wrsEquipmentSlNo = wrsEquipmentSlNo;
	}
	
	
	
}
