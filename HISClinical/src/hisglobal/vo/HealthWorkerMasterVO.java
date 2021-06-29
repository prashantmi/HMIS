package hisglobal.vo;

public class HealthWorkerMasterVO extends ValueObject
{
	private String healthWorkerID;
	private String slNo;
	private String hospitalCode;
	private String healthWorker;
	private String isValid;
	private String shortName;
	private String seatID;
	private String description;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public String getHealthWorkerID()
	{
		return healthWorkerID;
	}

	public void setHealthWorkerID(String healthWorkerID)
	{
		this.healthWorkerID = healthWorkerID;
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

	public String getHealthWorker()
	{
		return healthWorker;
	}

	public void setHealthWorker(String healthWorker)
	{
		this.healthWorker = healthWorker;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getShortName()
	{
		return shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
