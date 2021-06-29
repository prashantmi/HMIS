package hisglobal.vo;

public class BirthTypeMasterVO extends ValueObject
{
	private String birthTypeId;
	private String slNo;
	private String hospitalCode;
	private String birthNatureId;
	private String birthType;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public String getBirthTypeId()
	{
		return birthTypeId;
	}

	public void setBirthTypeId(String birthTypeId)
	{
		this.birthTypeId = birthTypeId;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getBirthNatureId()
	{
		return birthNatureId;
	}

	public void setBirthNatureId(String birthNatureId)
	{
		this.birthNatureId = birthNatureId;
	}

	public String getBirthType()
	{
		return birthType;
	}

	public void setBirthType(String birthType)
	{
		this.birthType = birthType;
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
