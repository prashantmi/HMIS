package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AllergyTypeFB extends ActionForm
{
	
	private String allergiesCode;
	private String allergiesType;
	private String allergiesDesc;
	private String localOrGlobalFlag="0";
	private String sourceFlag;
	private String tableQuery;
	private String seatID;
	private String entryDate;
	private String transactionMode;
	private String isValid;
	private String hmode;
	private String allergySensitivity;

	private String tableId;
	private String columnId;	
	private String valueId;
	private String hospitalCodeId;
	private String validationCondition;
	private String longQuery;
	
	
	private String tableName;
	private String columnList;
	private String valueColumn;
	private String hospitalNameColumn;
	
	
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnList() {
		return columnList;
	}
	public void setColumnList(String columnList) {
		this.columnList = columnList;
	}
	public String getAllergiesCode() {
		return allergiesCode;
	}
	public void setAllergiesCode(String allergiesCode) {
		this.allergiesCode = allergiesCode;
	}
	public String getAllergiesDesc() {
		return allergiesDesc;
	}
	public void setAllergiesDesc(String allergiesDesc) {
		this.allergiesDesc = allergiesDesc;
	}
	public String getAllergiesType() {
		return allergiesType;
	}
	public void setAllergiesType(String allergiesType) {
		this.allergiesType = allergiesType;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	
	public String getLocalOrGlobalFlag() {
		return localOrGlobalFlag;
	}
	public void setLocalOrGlobalFlag(String localOrGlobalFlag) {
		this.localOrGlobalFlag = localOrGlobalFlag;
	}
	public String getSourceFlag() {
		return sourceFlag;
	}
	public void setSourceFlag(String sourceFlag) {
		this.sourceFlag = sourceFlag;
	}
	public String getTableQuery() {
		return tableQuery;
	}
	public void setTableQuery(String tableQuery) {
		this.tableQuery = tableQuery;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setAllergiesCode("");
		this.setAllergiesDesc("");
		this.setAllergiesType("");
		this.setSourceFlag("1");
		this.setAllergySensitivity("");
		this.setSeatID("");
		this.setEntryDate("");
		this.setTableId("-1");
		this.setColumnId("-1");
		this.setValueId("-1");
		this.setValidationCondition("");
		this.setHospitalCodeId("-1");
		this.setLongQuery("");
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getValueId() {
		return valueId;
	}
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
	public String getValueColumn() {
		return valueColumn;
	}
	public void setValueColumn(String valueColumn) {
		this.valueColumn = valueColumn;
	}
	public String getHospitalCodeId() {
		return hospitalCodeId;
	}
	public void setHospitalCodeId(String hospitalCodeId) {
		this.hospitalCodeId = hospitalCodeId;
	}
	public String getHospitalNameColumn() {
		return hospitalNameColumn;
	}
	public void setHospitalNameColumn(String hospitalNameColumn) {
		this.hospitalNameColumn = hospitalNameColumn;
	}
	public String getValidationCondition() {
		return validationCondition;
	}
	public void setValidationCondition(String validationCondition) {
		this.validationCondition = validationCondition;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getLongQuery() {
		return longQuery;
	}
	public void setLongQuery(String longQuery) {
		this.longQuery = longQuery;
	}
	public String getAllergySensitivity() {
		return allergySensitivity;
	}
	public void setAllergySensitivity(String allergySensitivity) {
		this.allergySensitivity = allergySensitivity;
	}
		
	
}
