package hisglobal.vo;

public class ChartUnitMapppingVO extends ValueObject
{
	private String chartId;
	private String hospitalCode;
	private String deptUnitCode;
	private String slNo;
	private String isDefault;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String chartListDesc;
	private String defaultChartListCode;

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

	public String getDeptUnitCode()
	{
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode)
	{
		this.deptUnitCode = deptUnitCode;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getIsDefault()
	{
		return isDefault;
	}

	public void setIsDefault(String isDefault)
	{
		this.isDefault = isDefault;
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

	public String getChartListDesc() {
		return chartListDesc;
	}

	public void setChartListDesc(String chartListDesc) {
		this.chartListDesc = chartListDesc;
	}
}
