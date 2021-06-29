package bmed.masters.vo;

import hisglobal.utility.TransferObject;

/**
 * @author:- Vivek Aggarwal 
 * Creation Date:- 12-April-2011 
 * Modifying Date:- 19-April-2011 
 * Module:- BMED(HIS Project)
 * 
 */
public class HemConfigMstVO implements TransferObject {

	private static final long serialVersionUID = 02L;

	
	private boolean bExistStatus;


	private String strMsgString = "";
	private String strMsgType = "";
	
	private String strShowMcDetailsUpto;
	private String strShowWarrantyDetailsUpto;
	private String strFinancialStartDate;
	private String strFinancialEndDate;
	private String strItemUnderAmcOrWarranty;
	private String strHospitalCode;
	
	//Added by Adil Wasi
	private String strFolderName="";
	/*
	 * Getters and Setters for the above Attributes
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public boolean isBExistStatus() {
		return bExistStatus;
	}
	public void setBExistStatus(boolean existStatus) {
		bExistStatus = existStatus;
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
	public String getStrShowMcDetailsUpto() {
		return strShowMcDetailsUpto;
	}
	public void setStrShowMcDetailsUpto(String strShowMcDetailsUpto) {
		this.strShowMcDetailsUpto = strShowMcDetailsUpto;
	}
	public String getStrShowWarrantyDetailsUpto() {
		return strShowWarrantyDetailsUpto;
	}
	public void setStrShowWarrantyDetailsUpto(String strShowWarrantyDetailsUpto) {
		this.strShowWarrantyDetailsUpto = strShowWarrantyDetailsUpto;
	}
	public String getStrFinancialStartDate() {
		return strFinancialStartDate;
	}
	public void setStrFinancialStartDate(String strFinancialStartDate) {
		this.strFinancialStartDate = strFinancialStartDate;
	}
	public String getStrFinancialEndDate() {
		return strFinancialEndDate;
	}
	public void setStrFinancialEndDate(String strFinancialEndDate) {
		this.strFinancialEndDate = strFinancialEndDate;
	}
	public String getStrItemUnderAmcOrWarranty() {
		return strItemUnderAmcOrWarranty;
	}
	public void setStrItemUnderAmcOrWarranty(String strItemUnderAmcOrWarranty) {
		this.strItemUnderAmcOrWarranty = strItemUnderAmcOrWarranty;
	}
	
	public String getStrFolderName() {
		return strFolderName;
	}
	public void setStrFolderName(String strFolderName) {
		this.strFolderName = strFolderName;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	

}
