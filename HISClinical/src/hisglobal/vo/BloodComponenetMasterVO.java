package hisglobal.vo;

public class BloodComponenetMasterVO extends ValueObject
{
	private String bloodComponentID;
	private String bloodComponentName;
	private String bloodComponentSlNo;
	private String bagExpiryDays;
	private String separationFlag;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;

	public String getBloodComponentID()
	{
		return bloodComponentID;
	}

	public void setBloodComponentID(String bloodComponentID)
	{
		this.bloodComponentID = bloodComponentID;
	}

	public String getBloodComponentName()
	{
		return bloodComponentName;
	}

	public void setBloodComponentName(String bloodComponentName)
	{
		this.bloodComponentName = bloodComponentName;
	}

	public String getBloodComponentSlNo()
	{
		return bloodComponentSlNo;
	}

	public void setBloodComponentSlNo(String bloodComponentSlNo)
	{
		this.bloodComponentSlNo = bloodComponentSlNo;
	}

	public String getBagExpiryDays()
	{
		return bagExpiryDays;
	}

	public void setBagExpiryDays(String bagExpiryDays)
	{
		this.bagExpiryDays = bagExpiryDays;
	}

	public String getSeparationFlag()
	{
		return separationFlag;
	}

	public void setSeparationFlag(String separationFlag)
	{
		this.separationFlag = separationFlag;
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
