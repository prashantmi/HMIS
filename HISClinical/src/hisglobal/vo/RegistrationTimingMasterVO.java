package hisglobal.vo;

public class RegistrationTimingMasterVO extends ValueObject
{
	private String regCatCode;
	private String seasonCode;
	private String weekDay;
	private String shiftCode;
	private String slNo;
	private String hospitalCode;
	private String isValid;
	private String entryDate;
	private String seatID;

	public String getRegCatCode()
	{
		return regCatCode;
	}

	public void setRegCatCode(String regCatCode)
	{
		this.regCatCode = regCatCode;
	}

	public String getSeasonCode()
	{
		return seasonCode;
	}

	public void setSeasonCode(String seasonCode)
	{
		this.seasonCode = seasonCode;
	}

	public String getWeekDay()
	{
		return weekDay;
	}

	public void setWeekDay(String weekDay)
	{
		this.weekDay = weekDay;
	}

	public String getShiftCode()
	{
		return shiftCode;
	}

	public void setShiftCode(String shiftCode)
	{
		this.shiftCode = shiftCode;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

}
