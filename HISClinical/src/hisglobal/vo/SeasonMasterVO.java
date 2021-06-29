package hisglobal.vo;

public class SeasonMasterVO extends ValueObject
{
	private String seasonDesc;
	private String seasonFromDate;
	private String seasonToDate;
	private String seasonCode;
	private String seasonSlNo;
	private String hospitalCode;
	private String seatID;
	private String entryDate;
	private String days;
	private String seasonFromDay;
	private String seasonFromMonth;
	private String seasonToDay;
	private String seasonToMonth;

	public String getDays()
	{
		return days;
	}

	public void setDays(String days)
	{
		this.days = days;
	}

	public String getSeasonDesc()
	{
		return seasonDesc;
	}

	public void setSeasonDesc(String seasonDesc)
	{
		this.seasonDesc = seasonDesc;
	}

	public String getSeasonFromDate()
	{
		return seasonFromDate;
	}

	public void setSeasonFromDate(String seasonFromDate)
	{
		this.seasonFromDate = seasonFromDate;
	}

	public String getSeasonToDate()
	{
		return seasonToDate;
	}

	public void setSeasonToDate(String seasonToDate)
	{
		this.seasonToDate = seasonToDate;
	}

	public String getSeasonCode()
	{
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode)
	{
		this.seasonCode = seasonCode;
	}

	public String getSeasonSlNo()
	{
		return seasonSlNo;
	}

	public void setSeasonSlNo(String seasonSlNo)
	{
		this.seasonSlNo = seasonSlNo;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
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

	public String getSeasonFromDay()
	{
		return seasonFromDay;
	}

	public void setSeasonFromDay(String seasonFromDay)
	{
		this.seasonFromDay = seasonFromDay;
	}

	public String getSeasonFromMonth()
	{
		return seasonFromMonth;
	}

	public void setSeasonFromMonth(String seasonFromMonth)
	{
		this.seasonFromMonth = seasonFromMonth;
	}

	public String getSeasonToDay()
	{
		return seasonToDay;
	}

	public void setSeasonToDay(String seasonToDay)
	{
		this.seasonToDay = seasonToDay;
	}

	public String getSeasonToMonth()
	{
		return seasonToMonth;
	}

	public void setSeasonToMonth(String seasonToMonth)
	{
		this.seasonToMonth = seasonToMonth;
	}
}
