package bmed.vo;

import java.util.List;

import javax.sql.rowset.WebRowSet;

public class TestDtlVO 
{
	  private String strMode;
	  private String strTestId;
	  private String strEquTestId;
	  private String strDeptCodeNew;
	  private String strStoreId;
	  private String strEnggItemTypeId;
	  private String strEnggItemSubTypeId;
	  private String strEnggItemType;
	  private String strEnggItemSubType;
	  private String strDeptName;
	  private String strItemId;
	  private String strItemBrandId;
	  private String strItemSerialNo;
	  private String strItemBatchNo;
	  private String strManufacturerSerialNo;
	  private String strWarrantySlNo;
	  private String strMaintenanceContractSlNo;
	  private String strIsInternal;
	  private String strEmpId;
	  private String strContactPersonName;
	  private String strTestDate;
	  private String strTestTime;
	  private String strResult;
	  private String strEffFrom;
	  private String strRemarks;
	  private String strEntryDate;
	  private String strSeatId;
	  private String strIsValid;
	  private String strLastModDate;
	  private String strLastModSeatId;
	  private String strMsgString;
	  private String strMsgType;
	  private String strHospitalCode;
	  private String strConfirmedBy;
	  private String strTestResult;
	  
	  private String strClassificationMode;
	  private String strInternalExternalMode;
	  private  String [] strParameterOutput;
	  private  String [] strParameterInput;
	  private List<TestParameterDtlVO> testParameterDtlVO;
	  
	  private WebRowSet wrsData;
	  private WebRowSet wrsParamData;
	  private WebRowSet wrsOthersData;
	  private WebRowSet wrsPrvsRsultData;

	  public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	
	public String getStrEnggItemTypeId() {
		return strEnggItemTypeId;
	}
	public void setStrEnggItemTypeId(String strEnggItemTypeId) {
		this.strEnggItemTypeId = strEnggItemTypeId;
	}
	public String getStrEnggItemSubTypeId() {
		return strEnggItemSubTypeId;
	}
	public void setStrEnggItemSubTypeId(String strEnggItemSubTypeId) {
		this.strEnggItemSubTypeId = strEnggItemSubTypeId;
	}
	
	public String getStrEffFrom() {
		return strEffFrom;
	}
	public void setStrEffFrom(String strEffFrom) {
		this.strEffFrom = strEffFrom;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrEntryDate() {
		return strEntryDate;
	}
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrLastModDate() {
		return strLastModDate;
	}
	public void setStrLastModDate(String strLastModDate) {
		this.strLastModDate = strLastModDate;
	}
	public String getStrLastModSeatId() {
		return strLastModSeatId;
	}
	public void setStrLastModSeatId(String strLastModSeatId) {
		this.strLastModSeatId = strLastModSeatId;
	}
	public WebRowSet getWrsData() {
		return wrsData;
	}
	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
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
	
	public String getStrTestId() {
		return strTestId;
	}
	public void setStrTestId(String strTestId) {
		this.strTestId = strTestId;
	}
	
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrEquTestId() {
		return strEquTestId;
	}
	public void setStrEquTestId(String strEquTestId) {
		this.strEquTestId = strEquTestId;
	}
	public String getStrDeptCodeNew() {
		return strDeptCodeNew;
	}
	public void setStrDeptCodeNew(String strDeptCodeNew) {
		this.strDeptCodeNew = strDeptCodeNew;
	}
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrItemSerialNo() {
		return strItemSerialNo;
	}
	public void setStrItemSerialNo(String strItemSerialNo) {
		this.strItemSerialNo = strItemSerialNo;
	}
	public String getStrItemBatchNo() {
		return strItemBatchNo;
	}
	public void setStrItemBatchNo(String strItemBatchNo) {
		this.strItemBatchNo = strItemBatchNo;
	}
	public String getStrManufacturerSerialNo() {
		return strManufacturerSerialNo;
	}
	public void setStrManufacturerSerialNo(String strManufacturerSerialNo) {
		this.strManufacturerSerialNo = strManufacturerSerialNo;
	}
	public String getStrWarrantySlNo() {
		return strWarrantySlNo;
	}
	public void setStrWarrantySlNo(String strWarrantySlNo) {
		this.strWarrantySlNo = strWarrantySlNo;
	}
	public String getStrMaintenanceContractSlNo() {
		return strMaintenanceContractSlNo;
	}
	public void setStrMaintenanceContractSlNo(String strMaintenanceContractSlNo) {
		this.strMaintenanceContractSlNo = strMaintenanceContractSlNo;
	}
	public String getStrIsInternal() {
		return strIsInternal;
	}
	public void setStrIsInternal(String strIsInternal) {
		this.strIsInternal = strIsInternal;
	}
	public String getStrEmpId() {
		return strEmpId;
	}
	public void setStrEmpId(String strEmpId) {
		this.strEmpId = strEmpId;
	}
	public String getStrContactPersonName() {
		return strContactPersonName;
	}
	public void setStrContactPersonName(String strContactPersonName) {
		this.strContactPersonName = strContactPersonName;
	}
	public String getStrTestDate() {
		return strTestDate;
	}
	public void setStrTestDate(String strTestDate) {
		this.strTestDate = strTestDate;
	}
	public String getStrResult() {
		return strResult;
	}
	public void setStrResult(String strResult) {
		this.strResult = strResult;
	}
	public List<TestParameterDtlVO> getTestParameterDtlVO() {
		return testParameterDtlVO;
	}
	public void setTestParameterDtlVO(List<TestParameterDtlVO> testParameterDtlVO) {
		this.testParameterDtlVO = testParameterDtlVO;
	}
	public String getStrTestTime() {
		return strTestTime;
	}
	public void setStrTestTime(String strTestTime) {
		this.strTestTime = strTestTime;
	}
	public String getStrConfirmedBy() {
		return strConfirmedBy;
	}
	public void setStrConfirmedBy(String strConfirmedBy) {
		this.strConfirmedBy = strConfirmedBy;
	}
	public String getStrClassificationMode() {
		return strClassificationMode;
	}
	public void setStrClassificationMode(String strClassificationMode) {
		this.strClassificationMode = strClassificationMode;
	}
	public String getStrItemBrandId() {
		return strItemBrandId;
	}
	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}
	public String getStrInternalExternalMode() {
		return strInternalExternalMode;
	}
	public void setStrInternalExternalMode(String strInternalExternalMode) {
		this.strInternalExternalMode = strInternalExternalMode;
	}
	public String[] getStrParameterOutput() {
		return strParameterOutput;
	}
	public void setStrParameterOutput(String[] strParameterOutput) {
		this.strParameterOutput = strParameterOutput;
	}
	public String[] getStrParameterInput() {
		return strParameterInput;
	}
	public void setStrParameterInput(String[] strParameterInput) {
		this.strParameterInput = strParameterInput;
	}
	public String getStrEnggItemType() {
		return strEnggItemType;
	}
	public void setStrEnggItemType(String strEnggItemType) {
		this.strEnggItemType = strEnggItemType;
	}
	public String getStrEnggItemSubType() {
		return strEnggItemSubType;
	}
	public void setStrEnggItemSubType(String strEnggItemSubType) {
		this.strEnggItemSubType = strEnggItemSubType;
	}
	/**
	 * @return the strTestResult
	 */
	public String getStrTestResult() {
		return strTestResult;
	}
	/**
	 * @param strTestResult the strTestResult to set
	 */
	public void setStrTestResult(String strTestResult) {
		this.strTestResult = strTestResult;
	}
	public WebRowSet getWrsParamData() {
		return wrsParamData;
	}
	public void setWrsParamData(WebRowSet wrsParamData) {
		this.wrsParamData = wrsParamData;
	}
	public WebRowSet getWrsOthersData() {
		return wrsOthersData;
	}
	public void setWrsOthersData(WebRowSet wrsOthersData) {
		this.wrsOthersData = wrsOthersData;
	}
	public WebRowSet getWrsPrvsRsultData() {
		return wrsPrvsRsultData;
	}
	public void setWrsPrvsRsultData(WebRowSet wrsPrvsRsultData) {
		this.wrsPrvsRsultData = wrsPrvsRsultData;
	}
	  

}
