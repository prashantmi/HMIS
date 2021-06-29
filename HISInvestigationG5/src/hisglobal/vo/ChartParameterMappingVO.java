package hisglobal.vo;

public class ChartParameterMappingVO extends ValueObject
{
	private String chartId;
	private String hospitalCode;
	private String slNo;
	private String paraType;
	private String paraId;
	private String isValid;
	private String displayOrder;
	private String seatID;
	private String isGraphBased;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String generationType;
	private String chartName;
	private String para;
	
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public String getGenerationType() {
		return generationType;
	}
	public void setGenerationType(String generationType) {
		this.generationType = generationType;
	}
	
	private String paraName;

	public String getParaName()
	{
		return paraName;
	}

	public void setParaName(String paraName)
	{
		this.paraName = paraName;
	}

	public String getChartId()
	{
		return chartId;
	}

	public void setChartId(String chartId)
	{
		this.chartId = chartId;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getParaType()
	{
		return paraType;
	}

	public void setParaType(String paraType)
	{
		this.paraType = paraType;
	}

	public String getParaId()
	{
		return paraId;
	}

	public void setParaId(String paraId)
	{
		this.paraId = paraId;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getDisplayOrder()
	{
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getIsGraphBased()
	{
		return isGraphBased;
	}

	public void setIsGraphBased(String isGraphBased)
	{
		this.isGraphBased = isGraphBased;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getLastModifyDate()
	{
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate)
	{
		this.lastModifyDate = lastModifyDate;
	}

	public String getLastModifiedSeatID()
	{
		return lastModifiedSeatID;
	}

	public void setLastModifiedSeatID(String lastModifiedSeatID)
	{
		this.lastModifiedSeatID = lastModifiedSeatID;
	}

}
