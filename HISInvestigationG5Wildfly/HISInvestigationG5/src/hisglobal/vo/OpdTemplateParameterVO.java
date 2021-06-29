package hisglobal.vo;

public class OpdTemplateParameterVO extends ValueObject
{
	private String templateId;
	private String paraId;
	private String parentParaId;
	private String locationId;
	private String rowId;
	private String columnId;
	private String valueObjId;
	private String valueObjProp;
	private String colspan;
	private String paraValue;
	private String sourceFlag;
	private String tableQuery;
	private String isCompulsory;
	private String isRange;
	private String isValid;
	private String seatId;
	private String entryDate;

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIsCompulsory()
	{
		return isCompulsory;
	}

	public void setIsCompulsory(String isCompulsory)
	{
		this.isCompulsory = isCompulsory;
	}

	public String getIsRange()
	{
		return isRange;
	}

	public void setIsRange(String isRange)
	{
		this.isRange = isRange;
	}

	public String getColumnId()
	{
		return columnId;
	}

	public void setColumnId(String columnId)
	{
		this.columnId = columnId;
	}

	public String getLocationId()
	{
		return locationId;
	}

	public void setLocationId(String locationId)
	{
		this.locationId = locationId;
	}

	public String getParaId()
	{
		return paraId;
	}

	public void setParaId(String paraId)
	{
		this.paraId = paraId;
	}

	public String getParaValue()
	{
		return paraValue;
	}

	public void setParaValue(String paraValue)
	{
		this.paraValue = paraValue;
	}

	public String getParentParaId()
	{
		return parentParaId;
	}

	public void setParentParaId(String parentParaId)
	{
		this.parentParaId = parentParaId;
	}

	public String getRowId()
	{
		return rowId;
	}

	public void setRowId(String rowId)
	{
		this.rowId = rowId;
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

	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}

	public String getValueObjProp()
	{
		return valueObjProp;
	}

	public void setValueObjProp(String valueObjProp)
	{
		this.valueObjProp = valueObjProp;
	}

	public String getValueObjId()
	{
		return valueObjId;
	}

	public void setValueObjId(String valueObjId)
	{
		this.valueObjId = valueObjId;
	}

	public String getColspan()
	{
		return colspan;
	}

	public void setColspan(String colspan)
	{
		this.colspan = colspan;
	}

}
