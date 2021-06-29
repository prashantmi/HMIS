package hisglobal.vo;

public class DynamicVisitSummaryVO extends ValueObject
{
	private String dynamicSummaryHeader;

	private String dynamicSummaryId;
	private String hospitalCode;
	private String deskMenuId;
	private String header;
	private String query;
	private String displayLogic;
	private String isValid;
	private String seatId;
	private String entryDate;

	public String getDynamicSummaryHeader()
	{
		return dynamicSummaryHeader;
	}

	public void setDynamicSummaryHeader(String dynamicSummaryHeader)
	{
		this.dynamicSummaryHeader = dynamicSummaryHeader;
	}

	public String getDynamicSummaryId()
	{
		return dynamicSummaryId;
	}

	public void setDynamicSummaryId(String dynamicSummaryId)
	{
		this.dynamicSummaryId = dynamicSummaryId;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getDeskMenuId()
	{
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId)
	{
		this.deskMenuId = deskMenuId;
	}

	public String getHeader()
	{
		return header;
	}

	public void setHeader(String header)
	{
		this.header = header;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	public String getDisplayLogic()
	{
		return displayLogic;
	}

	public void setDisplayLogic(String displayLogic)
	{
		this.displayLogic = displayLogic;
	}

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
	
	// Static Class to Hold Column Values
	public static class ColumnLogic
	{
		private String column;
		private String width;
		private boolean remarkable;
		public String getColumn()
		{
			return column;
		}
		public void setColumn(String column)
		{
			this.column = column;
		}
		public String getWidth()
		{
			return width;
		}
		public void setWidth(String width)
		{
			this.width = width;
		}
		public boolean isRemarkable()
		{
			return remarkable;
		}
		public void setRemarkable(boolean remarkable)
		{
			this.remarkable = remarkable;
		}
	}
	
}
