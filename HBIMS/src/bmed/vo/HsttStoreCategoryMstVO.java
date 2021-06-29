package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class HsttStoreCategoryMstVO 
{
	private String strMode;
	
	private String strStoreId;          
	private String strItemCatNo;           
	private String strHospitalCode;          
	private String strCatSlno;              
	private String strRemarks;                 
	private String strEffectiveFrm;            
	private String strEffectiveTo;             
	private String strLstmodDate;              
	private String strLstmodSeatid;           
	private String strEntryDate;               
	private String strSeatid;                  
	private String strIsvalid;
	private String strPhysicalPeriod;      
	private String strLstphysicalDate;
	private String strLst_LstphysicalDate;  
	private String strIsStockLedgerReq;
	private String strIsItembound; 
	private String strNewitemFlag;      
	  
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

	public String getStrStoreId() {
		return strStoreId;
	}

	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}

	public String getStrItemCatNo() {
		return strItemCatNo;
	}

	public void setStrItemCatNo(String strItemCatNo) {
		this.strItemCatNo = strItemCatNo;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrCatSlno() {
		return strCatSlno;
	}

	public void setStrCatSlno(String strCatSlno) {
		this.strCatSlno = strCatSlno;
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

	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}

	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}

	public String getStrLstmodDate() {
		return strLstmodDate;
	}

	public void setStrLstmodDate(String strLstmodDate) {
		this.strLstmodDate = strLstmodDate;
	}

	public String getStrLstmodSeatid() {
		return strLstmodSeatid;
	}

	public void setStrLstmodSeatid(String strLstmodSeatid) {
		this.strLstmodSeatid = strLstmodSeatid;
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

	public String getStrPhysicalPeriod() {
		return strPhysicalPeriod;
	}

	public void setStrPhysicalPeriod(String strPhysicalPeriod) {
		this.strPhysicalPeriod = strPhysicalPeriod;
	}

	public String getStrLstphysicalDate() {
		return strLstphysicalDate;
	}

	public void setStrLstphysicalDate(String strLstphysicalDate) {
		this.strLstphysicalDate = strLstphysicalDate;
	}

	public String getStrLst_LstphysicalDate() {
		return strLst_LstphysicalDate;
	}

	public void setStrLst_LstphysicalDate(String strLst_LstphysicalDate) {
		this.strLst_LstphysicalDate = strLst_LstphysicalDate;
	}

	public String getStrIsStockLedgerReq() {
		return strIsStockLedgerReq;
	}

	public void setStrIsStockLedgerReq(String strIsStockLedgerReq) {
		this.strIsStockLedgerReq = strIsStockLedgerReq;
	}

	public String getStrIsItembound() {
		return strIsItembound;
	}

	public void setStrIsItembound(String strIsItembound) {
		this.strIsItembound = strIsItembound;
	}

	public String getStrNewitemFlag() {
		return strNewitemFlag;
	}

	public void setStrNewitemFlag(String strNewitemFlag) {
		this.strNewitemFlag = strNewitemFlag;
	}

	public WebRowSet getWrsData() {
		return wrsData;
	}

	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
	}
}
