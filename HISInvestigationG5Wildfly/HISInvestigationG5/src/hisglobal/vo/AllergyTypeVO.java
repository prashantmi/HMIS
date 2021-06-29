package hisglobal.vo;

public class AllergyTypeVO extends ValueObject
{
	private String allergiesCode;
	private String allergiesType;
	private String allergiesDesc;
	private String sourceFlag;
	private String tableQuery;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String allergySensitivity;
	
	private String tableId;
	private String columnId;
	private String valueId;
	private String valueColumn;
	private String hospitalCodeId;
	
	
	private String longQuery;
	private String tableName;
	private String columnList;
	private String hospitalNameColumn;
	private String validationCondition;
	
	
	
	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getColumnList()
	{
		return columnList;
	}

	public void setColumnList(String columnList)
	{
		this.columnList = columnList;
	}

	public String getAllergiesCode()
	{
		return allergiesCode;
	}

	public void setAllergiesCode(String allergiesCode)
	{
		this.allergiesCode = allergiesCode;
	}

	public String getAllergiesDesc()
	{
		return allergiesDesc;
	}

	public void setAllergiesDesc(String allergiesDesc)
	{
		this.allergiesDesc = allergiesDesc;
	}

	public String getAllergiesType()
	{
		return allergiesType;
	}

	public void setAllergiesType(String allergiesType)
	{
		this.allergiesType = allergiesType;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getSourceFlag()
	{
		return sourceFlag;
	}

	public void setSourceFlag(String sourceFlag)
	{
		this.sourceFlag = sourceFlag;
	}

	public String getTableQuery()
	{
		return tableQuery;
	}

	public void setTableQuery(String tableQuery)
	{
		this.tableQuery = tableQuery;
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
