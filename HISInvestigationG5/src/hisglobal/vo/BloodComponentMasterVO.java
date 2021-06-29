package hisglobal.vo;

public class BloodComponentMasterVO extends ValueObject
{
	private String bloodComponentID;
	private String bloodComponentName;
	private String bloodComponentSlNo;
	private String closebagExpiryDays;
	private String openbagExpiryDays;
	private String storageTemperature;
	private String transportTemperature;
	private String isIrradiatedComp;
	private String sourceComp;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	private String shortName;
	private String sourceCompId;
	private String usabilityFlag;
	
	public String getUsabilityFlag()
	{
		return usabilityFlag;
	}

	public void setUsabilityFlag(String usabilityFlag)
	{
		this.usabilityFlag = usabilityFlag;
	}

	public String getSourceCompId()
	{
		return sourceCompId;
	}

	public void setSourceCompId(String sourceCompId)
	{
		this.sourceCompId = sourceCompId;
	}

	public String getShortName()
	{
		return shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}

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

	public String getStorageTemperature()
	{
		return storageTemperature;
	}

	public void setStorageTemperature(String storageTemperature)
	{
		this.storageTemperature = storageTemperature;
	}

	public String getTransportTemperature()
	{
		return transportTemperature;
	}

	public void setTransportTemperature(String transportTemperature)
	{
		this.transportTemperature = transportTemperature;
	}

	public String getClosebagExpiryDays()
	{
		return closebagExpiryDays;
	}

	public void setClosebagExpiryDays(String closebagExpiryDays)
	{
		this.closebagExpiryDays = closebagExpiryDays;
	}

	public String getOpenbagExpiryDays()
	{
		return openbagExpiryDays;
	}

	public void setOpenbagExpiryDays(String openbagExpiryDays)
	{
		this.openbagExpiryDays = openbagExpiryDays;
	}

	public String getIsIrradiatedComp()
	{
		return isIrradiatedComp;
	}

	public void setIsIrradiatedComp(String isIrradiatedComp)
	{
		this.isIrradiatedComp = isIrradiatedComp;
	}

	public String getSourceComp()
	{
		return sourceComp;
	}

	public void setSourceComp(String sourceComp)
	{
		this.sourceComp = sourceComp;
	}
}
