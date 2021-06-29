package hisglobal.vo;

public class MenstrualCycleMasterVO extends ValueObject
{
	private String menstrualCycleId;
	private String slNo;
	private String hospitalCode;
	private String menstrualCycleDesc;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public String getMenstrualCycleId()
	{
		return menstrualCycleId;
	}

	public void setMenstrualCycleId(String menstrualCycleId)
	{
		this.menstrualCycleId = menstrualCycleId;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getMenstrualCycleDesc()
	{
		return menstrualCycleDesc;
	}

	public void setMenstrualCycleDesc(String menstrualCycleDesc)
	{
		this.menstrualCycleDesc = menstrualCycleDesc;
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

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}
}
