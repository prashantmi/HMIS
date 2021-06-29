package bmed.masters.controller.fb;

import hisglobal.masterutil.GenericFormBean;

/**
 * @author Vivek Aggarwal
 * Creation Date:- 12-April-2011 
 * Modifying Date:- 19-April-2011 
 * Module:- BMED(HIS Project)
 * 
 */
public class HemConfigMstFB extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	// Error, warning or Normal Messages
	private String strErrMsg = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";
	
	
	private String strHospitalCode = "";
	
	private String strShowMcDetailsUpto;
	private String strShowWarrantyDetailsUpto;
	private String strFinancialStartDate;
	private String strFinancialEndDate;
	private String strItemUnderAmcOrWarranty;
	
	//Added By Adil Wasi
	private String strFolderName="";
	
	public String getStrFolderName() {
		return strFolderName;
	}
	public void setStrFolderName(String strFolderName) {
		this.strFolderName = strFolderName;
	}
	/*
	 * Getters and Setters for the above Attributes
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
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
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
		
}

