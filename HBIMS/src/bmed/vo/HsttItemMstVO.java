package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class HsttItemMstVO 
{
	private String strMode;
  
	private String strItemId;
	private String strHospitalCode;
	private String strGroupId;   
	private String strSubgroupId;         
	private String strItemCatNo;         
	private String strItemName;           
	private String strBatchnoReq;         
	private String strSerialnoReq;        
	private String strExpirydateReq;      
	private String strShelflife;           
	private String strShelflifeUnit;      
	private String strInventoryUnitid;      
	private String strPurchasedLeadtime;  
	private String strPurLeadtimeUnit;   
	private String strConsumableFlag;     
	private String strParamFlag;          
	private String strRemarks;               
	private String strEffectiveFrm;          
	private String strLstmodSeatid;         
	private String strLstmodDate;            
	private String strEntryDate;             
	private String strSeatid;            
	private String strIsvalid;               
	private String strAssestFlag;         
	private String strDepriciationCost;   
	private String strUserCode;
	  
 	private WebRowSet wrsData;

 	/*
	 * Getters and Setters for the above Attributes
	 */
	public String getStrMode() {
		return strMode;
	}

	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}

	public String getStrItemId() {
		return strItemId;
	}

	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrGroupId() {
		return strGroupId;
	}

	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}

	public String getStrSubgroupId() {
		return strSubgroupId;
	}

	public void setStrSubgroupId(String strSubgroupId) {
		this.strSubgroupId = strSubgroupId;
	}

	public String getStrItemCatNo() {
		return strItemCatNo;
	}

	public void setStrItemCatNo(String strItemCatNo) {
		this.strItemCatNo = strItemCatNo;
	}

	public String getStrItemName() {
		return strItemName;
	}

	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName;
	}

	public String getStrBatchnoReq() {
		return strBatchnoReq;
	}

	public void setStrBatchnoReq(String strBatchnoReq) {
		this.strBatchnoReq = strBatchnoReq;
	}

	public String getStrSerialnoReq() {
		return strSerialnoReq;
	}

	public void setStrSerialnoReq(String strSerialnoReq) {
		this.strSerialnoReq = strSerialnoReq;
	}

	public String getStrExpirydateReq() {
		return strExpirydateReq;
	}

	public void setStrExpirydateReq(String strExpirydateReq) {
		this.strExpirydateReq = strExpirydateReq;
	}

	public String getStrShelflife() {
		return strShelflife;
	}

	public void setStrShelflife(String strShelflife) {
		this.strShelflife = strShelflife;
	}

	public String getStrShelflifeUnit() {
		return strShelflifeUnit;
	}

	public void setStrShelflifeUnit(String strShelflifeUnit) {
		this.strShelflifeUnit = strShelflifeUnit;
	}

	public String getStrInventoryUnitid() {
		return strInventoryUnitid;
	}

	public void setStrInventoryUnitid(String strInventoryUnitid) {
		this.strInventoryUnitid = strInventoryUnitid;
	}

	public String getStrPurchasedLeadtime() {
		return strPurchasedLeadtime;
	}

	public void setStrPurchasedLeadtime(String strPurchasedLeadtime) {
		this.strPurchasedLeadtime = strPurchasedLeadtime;
	}

	public String getStrPurLeadtimeUnit() {
		return strPurLeadtimeUnit;
	}

	public void setStrPurLeadtimeUnit(String strPurLeadtimeUnit) {
		this.strPurLeadtimeUnit = strPurLeadtimeUnit;
	}

	public String getStrConsumableFlag() {
		return strConsumableFlag;
	}

	public void setStrConsumableFlag(String strConsumableFlag) {
		this.strConsumableFlag = strConsumableFlag;
	}

	public String getStrParamFlag() {
		return strParamFlag;
	}

	public void setStrParamFlag(String strParamFlag) {
		this.strParamFlag = strParamFlag;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrEffectiveFrm() {
		return strEffectiveFrm;
	}

	public void setStrEffectiveFrm(String strEffectiveFrm) {
		this.strEffectiveFrm = strEffectiveFrm;
	}

	public String getStrLstmodSeatid() {
		return strLstmodSeatid;
	}

	public void setStrLstmodSeatid(String strLstmodSeatid) {
		this.strLstmodSeatid = strLstmodSeatid;
	}

	public String getStrLstmodDate() {
		return strLstmodDate;
	}

	public void setStrLstmodDate(String strLstmodDate) {
		this.strLstmodDate = strLstmodDate;
	}

	public String getStrEntryDate() {
		return strEntryDate;
	}

	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	public String getStrSeatid() {
		return strSeatid;
	}

	public void setStrSeatid(String strSeatid) {
		this.strSeatid = strSeatid;
	}

	public String getStrIsvalid() {
		return strIsvalid;
	}

	public void setStrIsvalid(String strIsvalid) {
		this.strIsvalid = strIsvalid;
	}

	public String getStrAssestFlag() {
		return strAssestFlag;
	}

	public void setStrAssestFlag(String strAssestFlag) {
		this.strAssestFlag = strAssestFlag;
	}

	public String getStrDepriciationCost() {
		return strDepriciationCost;
	}

	public void setStrDepriciationCost(String strDepriciationCost) {
		this.strDepriciationCost = strDepriciationCost;
	}

	public String getStrUserCode() {
		return strUserCode;
	}

	public void setStrUserCode(String strUserCode) {
		this.strUserCode = strUserCode;
	}

	public WebRowSet getWrsData() {
		return wrsData;
	}

	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
	}

}
