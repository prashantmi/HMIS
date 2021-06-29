package hisglobal.vo;

public class ChartMasterVO extends ValueObject
{
	private String chartId;
	private String hospitalCode;
	private String slNo;
	private String chartName;
	private String chartDescription;
	private String isValid;
	private String chartCategory;
	private String generationType;
	private String seatID;
	private String entryDate;
	private String bodyQuery;
	private String lastModifyDate;
	private String footerQuery;
	private String lastModifiedSeatID;
	private String isGraph;
	private String isActive;
	
	private String isDefault;

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

	public String getChartName()
	{
		return chartName;
	}

	public void setChartName(String chartName)
	{
		this.chartName = chartName;
	}

	public String getChartDescription()
	{
		return chartDescription;
	}

	public void setChartDescription(String chartDescription)
	{
		this.chartDescription = chartDescription;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getChartCategory()
	{
		return chartCategory;
	}

	public void setChartCategory(String chartCategory)
	{
		this.chartCategory = chartCategory;
	}

	public String getGenerationType()
	{
		return generationType;
	}

	public void setGenerationType(String generationType)
	{
		this.generationType = generationType;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getBodyQuery()
	{
		return bodyQuery;
	}

	public void setBodyQuery(String bodyQuery)
	{
		this.bodyQuery = bodyQuery;
	}

	public String getLastModifyDate()
	{
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate)
	{
		this.lastModifyDate = lastModifyDate;
	}

	public String getFooterQuery()
	{
		return footerQuery;
	}

	public void setFooterQuery(String footerQuery)
	{
		this.footerQuery = footerQuery;
	}

	public String getLastModifiedSeatID()
	{
		return lastModifiedSeatID;
	}

	public void setLastModifiedSeatID(String lastModifiedSeatID)
	{
		this.lastModifiedSeatID = lastModifiedSeatID;
	}

	public String getIsGraph()
	{
		return isGraph;
	}

	public void setIsGraph(String isGraph)
	{
		this.isGraph = isGraph;
	}

	public String getIsDefault()
	{
		return isDefault;
	}

	public void setIsDefault(String isDefault)
	{
		this.isDefault = isDefault;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
