package billing.transactions;

import hisglobal.utility.TransferObject;

import javax.sql.rowset.WebRowSet;

public class AddServicesIPDTransVO implements TransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "0";

	private String strErrPrimKeyLog = "";
	
	private String strHospitalCode = "";
	private String strSeatId = "";
	private String strCtDate = "";
	private String strDateString = "";
	private String strQuantity = "";
	
	private String strCrNo = "";
	private String strDischargeDate = "";
	private String strPatientCategory = "";
	private String strAccountNo = "";
	private String strReqNo = "";
	
	private String strGroupId = "";
	private String strChargeTypeId = "";
	private String strWardCode = "" ;
	private String strTariffCode = "";
	private String strTariffUnitTempId = "";
		
	 private String[] strCompChargeCheck = null;
	 private String[] strOfflineTariffName = null;
	 private String[] strOfflineTariffDetailsHidden = null;
	 private String[] strOfflineTariffRateUnit = null;
	 private String[] strOfflineTariffQty = null;
	 private String[] strOfflineTariffUnit = null;
	 private String[] strOfflineTariffServiceTax = null;
	 private String[] strOfflineTariffServiceTaxAmtVal = null;
	 private String[] strOfflineTariffNetAmount = null;
	 private String[] strOfflineActualTariffAmtVal = null;
	

	  
	 
	 private WebRowSet wsPateintCategory = null;
	 private WebRowSet wsOfflineGroupDetails = null;
	 private WebRowSet wsPreviousAddServiceDetails = null;	 
	 private WebRowSet wsTariffList = null;
	 private WebRowSet wsTariffUnit = null;
	 
	public String getStrTariffUnitTempId() {
		return strTariffUnitTempId;
	}

	public void setStrTariffUnitTempId(String strTariffUnitTempId) {
		this.strTariffUnitTempId = strTariffUnitTempId;
	}

	public WebRowSet getWsTariffUnit() {
		return wsTariffUnit;
	}

	public void setWsTariffUnit(WebRowSet wsTariffUnit) {
		this.wsTariffUnit = wsTariffUnit;
	}

	public WebRowSet getWsTariffList() {
		return wsTariffList;
	}

	public void setWsTariffList(WebRowSet wsTariffList) {
		this.wsTariffList = wsTariffList;
	}

	public WebRowSet getWsPreviousAddServiceDetails() {
		return wsPreviousAddServiceDetails;
	}

	public void setWsPreviousAddServiceDetails(WebRowSet wsPreviousAddServiceDetails) {
		this.wsPreviousAddServiceDetails = wsPreviousAddServiceDetails;
	}

	public WebRowSet getWsOfflineGroupDetails() {
		return wsOfflineGroupDetails;
	}

	public void setWsOfflineGroupDetails(WebRowSet wsOfflineGroupDetails) {
		this.wsOfflineGroupDetails = wsOfflineGroupDetails;
	}

	 

	public String[] getStrOfflineTariffName() {
		return strOfflineTariffName;
	}

	public void setStrOfflineTariffName(String[] strOfflineTariffName) {
		this.strOfflineTariffName = strOfflineTariffName;
	}

	public String[] getStrOfflineTariffDetailsHidden() {
		return strOfflineTariffDetailsHidden;
	}

	public void setStrOfflineTariffDetailsHidden(
			String[] strOfflineTariffDetailsHidden) {
		this.strOfflineTariffDetailsHidden = strOfflineTariffDetailsHidden;
	}

	public String[] getStrOfflineTariffRateUnit() {
		return strOfflineTariffRateUnit;
	}

	public void setStrOfflineTariffRateUnit(String[] strOfflineTariffRateUnit) {
		this.strOfflineTariffRateUnit = strOfflineTariffRateUnit;
	}

	public String[] getStrOfflineTariffQty() {
		return strOfflineTariffQty;
	}

	public void setStrOfflineTariffQty(String[] strOfflineTariffQty) {
		this.strOfflineTariffQty = strOfflineTariffQty;
	}

	public String[] getStrOfflineTariffUnit() {
		return strOfflineTariffUnit;
	}

	public void setStrOfflineTariffUnit(String[] strOfflineTariffUnit) {
		this.strOfflineTariffUnit = strOfflineTariffUnit;
	}

	public String[] getStrOfflineTariffServiceTax() {
		return strOfflineTariffServiceTax;
	}

	public void setStrOfflineTariffServiceTax(String[] strOfflineTariffServiceTax) {
		this.strOfflineTariffServiceTax = strOfflineTariffServiceTax;
	}

	public String[] getStrOfflineTariffServiceTaxAmtVal() {
		return strOfflineTariffServiceTaxAmtVal;
	}

	public void setStrOfflineTariffServiceTaxAmtVal(
			String[] strOfflineTariffServiceTaxAmtVal) {
		this.strOfflineTariffServiceTaxAmtVal = strOfflineTariffServiceTaxAmtVal;
	}

	public String[] getStrOfflineTariffNetAmount() {
		return strOfflineTariffNetAmount;
	}

	public void setStrOfflineTariffNetAmount(String[] strOfflineTariffNetAmount) {
		this.strOfflineTariffNetAmount = strOfflineTariffNetAmount;
	}

	public String[] getStrOfflineActualTariffAmtVal() {
		return strOfflineActualTariffAmtVal;
	}

	public void setStrOfflineActualTariffAmtVal(
			String[] strOfflineActualTariffAmtVal) {
		this.strOfflineActualTariffAmtVal = strOfflineActualTariffAmtVal;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrCtDate() {
		return strCtDate;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}

	public String getStrDischargeDate() {
		return strDischargeDate;
	}

	public void setStrDischargeDate(String strDischargeDate) {
		this.strDischargeDate = strDischargeDate;
	}

	public String getStrPatientCategory() {
		return strPatientCategory;
	}

	public void setStrPatientCategory(String strPatientCategory) {
		this.strPatientCategory = strPatientCategory;
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

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public WebRowSet getWsPateintCategory() {
		return wsPateintCategory;
	}

	public void setWsPateintCategory(WebRowSet wsPateintCategory) {
		this.wsPateintCategory = wsPateintCategory;
	}

	public String getStrAccountNo() {
		return strAccountNo;
	}

	public void setStrAccountNo(String strAccountNo) {
		this.strAccountNo = strAccountNo;
	}

	public String getStrGroupId() {
		return strGroupId;
	}

	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}

	public String getStrChargeTypeId() {
		return strChargeTypeId;
	}

	public void setStrChargeTypeId(String strChargeTypeId) {
		this.strChargeTypeId = strChargeTypeId;
	}

	public String getStrWardCode() {
		return strWardCode;
	}

	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}

	public String getStrTariffCode() {
		return strTariffCode;
	}

	public void setStrTariffCode(String strTariffCode) {
		this.strTariffCode = strTariffCode;
	}

	public String getStrDateString() {
		return strDateString;
	}

	public void setStrDateString(String strDateString) {
		this.strDateString = strDateString;
	}

	public String getStrQuantity() {
		return strQuantity;
	}

	public void setStrQuantity(String strQuantity) {
		this.strQuantity = strQuantity;
	}

	public String getStrReqNo() {
		return strReqNo;
	}

	public void setStrReqNo(String strReqNo) {
		this.strReqNo = strReqNo;
	}

	public String[] getStrCompChargeCheck() {
		return strCompChargeCheck;
	}

	public void setStrCompChargeCheck(String[] strCompChargeCheck) {
		this.strCompChargeCheck = strCompChargeCheck;
	}

	public String getStrErrPrimKeyLog() {
		return strErrPrimKeyLog;
	}

	public void setStrErrPrimKeyLog(String strErrPrimKeyLog) {
		this.strErrPrimKeyLog = strErrPrimKeyLog;
	}
 

}
