package bmed.transactions.controller.fb;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ItemMaintContractDtlsFB extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String strHospitalCode = "";
	private String strSeatId = "";

	private String strErrMsg = "";
	private String strWarningMsg = "";
	private String strNormalMsg = "";
	private String strMsgType = "";
	
	private String strCheck;
	private String strMaintenanceContractMode;
	private String strItemBrandId;
	private String strStoreId;
	private String strDeptId;
	private String strEnggItemTypeId;
	private String strEnggItemSubTypeId;
	
	private String strEnggItemSubTypeCmb;
	private String strStoreNameCombo;
	private String strDeptNameCombo;
	private String strEnggItemTypeCmb;
	private String strItemNameCmb;
	private String strFileNo;

	private String strPageNo;

	private String strTermsAndCond;

	private String strPeneltyCond;

	private String strRemarks;

	private String strMaintenanceCost;

	private String strResponseTimeUnitId;

	private String strRoutineUnitId;

	private String strToDate;

	private String strFromDate;

	private String strOrderDate;

	private String strOrderNo;

	private String strTenderDate;

	private String strTenderNo;

	private String strMaintenanceContractName;

	private String strMantContractTypeId;

	private String strMantContractTypeCmb;

	private String strMantContractSuppId;
	private String strUnitIdCmb;
	
	private String strUploadFileLocation;
	private String strUploadFileId;
	
	private String strRoutineFrequency;
	private String strResponseTime;
	
	private FormFile strLocation=null;
	private String strDocRefDate;
	private String strDocRefNo;
	
	private String strPKey;   // HEMNUM_ITEM_ID, HEMSTR_BATCH_NO, HEMNUM_ITEM_SL_NO, HEMNUM_SL_NO, GNUM_HOSPITAL_CODE
	private String strStockInf; //HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
	                            //||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
                                //GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE

	private String strStockInfoVal;
	private String strRenewMantContractTypeId;
		
	private String strRenewTermsAndCond;

	private String strRenewPeneltyCond;

	private String strRenewRemarks;

	private String strRenewMaintenanceCost;


	private String strRenewToDate;

	private String strRenewFromDate;

	private String strRenewOrderDate;

	private String strRenewOrderNo;

	private String strRenewTenderDate;

	private String strRenewTenderNo;

	private String strRenewMaintenanceContractName;

	private String 	strCancelRemarks;
	private String 	strCancelTypeId;
	private String  strItemMaintenanceContractMode;
	private String  strNonItemMaintenanceContractMode;
	private String  strIsReNewPK;
	private String  strIsReNew;
	private String  strRenewRoutineUnitId;
	private String  strRenewResponseTimeUnitId;
	private String strManufacturerName;
	
	
	
	public String getStrManufacturerName() {
		return strManufacturerName;
	}

	public void setStrManufacturerName(String strManufacturerName) {
		this.strManufacturerName = strManufacturerName;
	}

	String [] strAttachedFileName=null;
    private ArrayList<FormFile> formFileMultiple ;

    public String[] getStrAttachedFileName() {
		return strAttachedFileName;
	}

	public void setStrAttachedFileName(String[] strAttachedFileName) {
		this.strAttachedFileName = strAttachedFileName;
	}
    
	public FormFile getFormFileMultiple( int i ) 
	{
		if ( formFileMultiple == null ) 
		{
			formFileMultiple = new ArrayList<FormFile>() ;
		}
		return i + 1 > formFileMultiple.size() ? null : (FormFile)formFileMultiple.get(i) ;
	}

	public void setFormFileMultiple( int i, FormFile formFileMultiple ) 
	{
		if ( this.formFileMultiple == null ) 
		{
			this.formFileMultiple = new ArrayList<FormFile>() ;
		}
		if ( i + 1 > this.formFileMultiple.size() ) 
		{
			this.formFileMultiple.add(formFileMultiple) ;
		}
		else 
		{
			this.formFileMultiple.set(i, formFileMultiple) ;
		}
	}

	public int getFrmFileSize ( ) {
		int retVal = 0 ;
		if ( formFileMultiple != null ) {
			retVal = formFileMultiple.size() ;
		}
		return retVal ;
	}
 // Form File End   
	
	
	public String getStrUnitIdCmb() {
		return strUnitIdCmb;
	}
	public void setStrUnitIdCmb(String strUnitIdCmb) {
		this.strUnitIdCmb = strUnitIdCmb;
	}
	public String getStrFileNo() {
		return strFileNo;
	}
	public void setStrFileNo(String strFileNo) {
		this.strFileNo = strFileNo;
	}
	public String getStrPageNo() {
		return strPageNo;
	}
	public void setStrPageNo(String strPageNo) {
		this.strPageNo = strPageNo;
	}
	
	public String getStrTermsAndCond() {
		return strTermsAndCond;
	}
	public void setStrTermsAndCond(String strTermsAndCond) {
		this.strTermsAndCond = strTermsAndCond;
	}
	public String getStrPeneltyCond() {
		return strPeneltyCond;
	}
	public void setStrPeneltyCond(String strPeneltyCond) {
		this.strPeneltyCond = strPeneltyCond;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrMaintenanceCost() {
		return strMaintenanceCost;
	}
	public void setStrMaintenanceCost(String strMaintenanceCost) {
		this.strMaintenanceCost = strMaintenanceCost;
	}
	public String getStrResponseTimeUnitId() {
		return strResponseTimeUnitId;
	}
	public void setStrResponseTimeUnitId(String strResponseTimeUnitId) {
		this.strResponseTimeUnitId = strResponseTimeUnitId;
	}
	public String getStrRoutineUnitId() {
		return strRoutineUnitId;
	}
	public void setStrRoutineUnitId(String strRoutineUnitId) {
		this.strRoutineUnitId = strRoutineUnitId;
	}
	public String getStrToDate() {
		return strToDate;
	}
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}
	public String getStrFromDate() {
		return strFromDate;
	}
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}
	public String getStrOrderDate() {
		return strOrderDate;
	}
	public void setStrOrderDate(String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}
	public String getStrOrderNo() {
		return strOrderNo;
	}
	public void setStrOrderNo(String strOrderNo) {
		this.strOrderNo = strOrderNo;
	}
	public String getStrTenderDate() {
		return strTenderDate;
	}
	public void setStrTenderDate(String strTenderDate) {
		this.strTenderDate = strTenderDate;
	}
	public String getStrTenderNo() {
		return strTenderNo;
	}
	public void setStrTenderNo(String strTenderNo) {
		this.strTenderNo = strTenderNo;
	}
	public String getStrMaintenanceContractName() {
		return strMaintenanceContractName;
	}
	public void setStrMaintenanceContractName(String strMaintenanceContractName) {
		this.strMaintenanceContractName = strMaintenanceContractName;
	}
	public String getStrMantContractTypeId() {
		return strMantContractTypeId;
	}
	public void setStrMantContractTypeId(String strMantContractTypeId) {
		this.strMantContractTypeId = strMantContractTypeId;
	}
	public String getStrMantContractTypeCmb() {
		return strMantContractTypeCmb;
	}
	public void setStrMantContractTypeCmb(String strMantContractTypeCmb) {
		this.strMantContractTypeCmb = strMantContractTypeCmb;
	}
	public String getStrMantContractSuppId() {
		return strMantContractSuppId;
	}
	public void setStrMantContractSuppId(String strMantContractSuppId) {
		this.strMantContractSuppId = strMantContractSuppId;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getStrMaintenanceContractMode() {
		return strMaintenanceContractMode;
	}
	public void setStrMaintenanceContractMode(String strMaintenanceContractMode) {
		this.strMaintenanceContractMode = strMaintenanceContractMode;
	}
	public String getStrStoreNameCombo() {
		return strStoreNameCombo;
	}
	public void setStrStoreNameCombo(String strStoreNameCombo) {
		this.strStoreNameCombo = strStoreNameCombo;
	}
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public String getStrDeptId() {
		return strDeptId;
	}
	public void setStrDeptId(String strDeptId) {
		this.strDeptId = strDeptId;
	}
	public String getStrDeptNameCombo() {
		return strDeptNameCombo;
	}
	public void setStrDeptNameCombo(String strDeptNameCombo) {
		this.strDeptNameCombo = strDeptNameCombo;
	}
	public String getStrEnggItemTypeId() {
		return strEnggItemTypeId;
	}
	public void setStrEnggItemTypeId(String strEnggItemTypeId) {
		this.strEnggItemTypeId = strEnggItemTypeId;
	}
	public String getStrEnggItemTypeCmb() {
		return strEnggItemTypeCmb;
	}
	public void setStrEnggItemTypeCmb(String strEnggItemTypeCmb) {
		this.strEnggItemTypeCmb = strEnggItemTypeCmb;
	}
	public String getStrEnggItemSubTypeId() {
		return strEnggItemSubTypeId;
	}
	public void setStrEnggItemSubTypeId(String strEnggItemSubTypeId) {
		this.strEnggItemSubTypeId = strEnggItemSubTypeId;
	}
	public String getStrEnggItemSubTypeCmb() {
		return strEnggItemSubTypeCmb;
	}
	public void setStrEnggItemSubTypeCmb(String strEnggItemSubTypeCmb) {
		this.strEnggItemSubTypeCmb = strEnggItemSubTypeCmb;
	}
	
	public String getStrItemNameCmb() {
		return strItemNameCmb;
	}
	public void setStrItemNameCmb(String strItemNameCmb) {
		this.strItemNameCmb = strItemNameCmb;
	}
	public String getStrUploadFileLocation() {
		return strUploadFileLocation;
	}
	public void setStrUploadFileLocation(String strUploadFileLocation) {
		this.strUploadFileLocation = strUploadFileLocation;
	}
	public String getStrUploadFileId() {
		return strUploadFileId;
	}
	public void setStrUploadFileId(String strUploadFileId) {
		this.strUploadFileId = strUploadFileId;
	}
	public String getStrPKey() {
		return strPKey;
	}
	public void setStrPKey(String strPKey) {
		this.strPKey = strPKey;
	}
	public String getStrStockInf() {
		return strStockInf;
	}
	public void setStrStockInf(String strStockInf) {
		this.strStockInf = strStockInf;
	}
	public String getStrItemBrandId() {
		return strItemBrandId;
	}
	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}
	public String getStrRoutineFrequency() {
		return strRoutineFrequency;
	}
	public void setStrRoutineFrequency(String strRoutineFrequency) {
		this.strRoutineFrequency = strRoutineFrequency;
	}
	public String getStrResponseTime() {
		return strResponseTime;
	}
	public void setStrResponseTime(String strResponseTime) {
		this.strResponseTime = strResponseTime;
	}
	public FormFile getStrLocation() {
		return strLocation;
	}
	public void setStrLocation(FormFile strLocation) {
		this.strLocation = strLocation;
	}
	public String getStrDocRefDate() {
		return strDocRefDate;
	}
	public void setStrDocRefDate(String strDocRefDate) {
		this.strDocRefDate = strDocRefDate;
	}
	public String getStrDocRefNo() {
		return strDocRefNo;
	}
	public void setStrDocRefNo(String strDocRefNo) {
		this.strDocRefNo = strDocRefNo;
	}

	public String getStrRenewMantContractTypeId() {
		return strRenewMantContractTypeId;
	}

	public void setStrRenewMantContractTypeId(String strRenewMantContractTypeId) {
		this.strRenewMantContractTypeId = strRenewMantContractTypeId;
	}

	public String getStrRenewRoutineUnitId() {
		return strRenewRoutineUnitId;
	}

	public void setStrRenewRoutineUnitId(String strRenewRoutineUnitId) {
		this.strRenewRoutineUnitId = strRenewRoutineUnitId;
	}

	public String getStrRenewResponseTimeUnitId() {
		return strRenewResponseTimeUnitId;
	}

	public void setStrRenewResponseTimeUnitId(String strRenewResponseTimeUnitId) {
		this.strRenewResponseTimeUnitId = strRenewResponseTimeUnitId;
	}

	public String getStrRenewTermsAndCond() {
		return strRenewTermsAndCond;
	}

	public void setStrRenewTermsAndCond(String strRenewTermsAndCond) {
		this.strRenewTermsAndCond = strRenewTermsAndCond;
	}

	public String getStrRenewPeneltyCond() {
		return strRenewPeneltyCond;
	}

	public void setStrRenewPeneltyCond(String strRenewPeneltyCond) {
		this.strRenewPeneltyCond = strRenewPeneltyCond;
	}

	public String getStrRenewRemarks() {
		return strRenewRemarks;
	}

	public void setStrRenewRemarks(String strRenewRemarks) {
		this.strRenewRemarks = strRenewRemarks;
	}

	public String getStrRenewMaintenanceCost() {
		return strRenewMaintenanceCost;
	}

	public void setStrRenewMaintenanceCost(String strRenewMaintenanceCost) {
		this.strRenewMaintenanceCost = strRenewMaintenanceCost;
	}

	public String getStrRenewToDate() {
		return strRenewToDate;
	}

	public void setStrRenewToDate(String strRenewToDate) {
		this.strRenewToDate = strRenewToDate;
	}

	public String getStrRenewFromDate() {
		return strRenewFromDate;
	}

	public void setStrRenewFromDate(String strRenewFromDate) {
		this.strRenewFromDate = strRenewFromDate;
	}

	public String getStrRenewOrderDate() {
		return strRenewOrderDate;
	}

	public void setStrRenewOrderDate(String strRenewOrderDate) {
		this.strRenewOrderDate = strRenewOrderDate;
	}

	public String getStrRenewOrderNo() {
		return strRenewOrderNo;
	}

	public void setStrRenewOrderNo(String strRenewOrderNo) {
		this.strRenewOrderNo = strRenewOrderNo;
	}

	public String getStrRenewTenderDate() {
		return strRenewTenderDate;
	}

	public void setStrRenewTenderDate(String strRenewTenderDate) {
		this.strRenewTenderDate = strRenewTenderDate;
	}

	public String getStrRenewTenderNo() {
		return strRenewTenderNo;
	}

	public void setStrRenewTenderNo(String strRenewTenderNo) {
		this.strRenewTenderNo = strRenewTenderNo;
	}

	public String getStrRenewMaintenanceContractName() {
		return strRenewMaintenanceContractName;
	}

	public void setStrRenewMaintenanceContractName(
			String strRenewMaintenanceContractName) {
		this.strRenewMaintenanceContractName = strRenewMaintenanceContractName;
	}

	public ArrayList<FormFile> getFormFileMultiple() {
		return formFileMultiple;
	}

	public void setFormFileMultiple(ArrayList<FormFile> formFileMultiple) {
		this.formFileMultiple = formFileMultiple;
	}

	public String getStrCancelRemarks() {
		return strCancelRemarks;
	}

	public void setStrCancelRemarks(String strCancelRemarks) {
		this.strCancelRemarks = strCancelRemarks;
	}

	public String getStrCancelTypeId() {
		return strCancelTypeId;
	}

	public void setStrCancelTypeId(String strCancelTypeId) {
		this.strCancelTypeId = strCancelTypeId;
	}

	public String getStrItemMaintenanceContractMode() {
		return strItemMaintenanceContractMode;
	}

	public void setStrItemMaintenanceContractMode(
			String strItemMaintenanceContractMode) {
		this.strItemMaintenanceContractMode = strItemMaintenanceContractMode;
	}

	public String getStrNonItemMaintenanceContractMode() {
		return strNonItemMaintenanceContractMode;
	}

	public void setStrNonItemMaintenanceContractMode(
			String strNonItemMaintenanceContractMode) {
		this.strNonItemMaintenanceContractMode = strNonItemMaintenanceContractMode;
	}

	public String getStrCheck() {
		return strCheck;
	}

	public void setStrCheck(String strCheck) {
		this.strCheck = strCheck;
	}

	public String getStrIsReNewPK() {
		return strIsReNewPK;
	}

	public void setStrIsReNewPK(String strIsReNewPK) {
		this.strIsReNewPK = strIsReNewPK;
	}

	public String getStrIsReNew() {
		return strIsReNew;
	}

	public void setStrIsReNew(String strIsReNew) {
		this.strIsReNew = strIsReNew;
	}

	public String getStrStockInfoVal() {
		return strStockInfoVal;
	}

	public void setStrStockInfoVal(String strStockInfoVal) {
		this.strStockInfoVal = strStockInfoVal;
	}
	
	
}
