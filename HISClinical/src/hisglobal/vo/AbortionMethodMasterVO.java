package hisglobal.vo;

public class AbortionMethodMasterVO extends ValueObject
{
	private String abortionMethodId;
	private String slNo;
	private String hospitalCode;
	private String abortionTypeId;
	private String abortionMethod;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public String getAbortionMethodId()
	{
		return abortionMethodId;
	}

	public void setAbortionMethodId(String abortionMethodId)
	{
		this.abortionMethodId = abortionMethodId;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getAbortionTypeId()
	{
		return abortionTypeId;
	}

	public void setAbortionTypeId(String abortionTypeId)
	{
		this.abortionTypeId = abortionTypeId;
	}

	public String getAbortionMethod()
	{
		return abortionMethod;
	}

	public void setAbortionMethod(String abortionMethod)
	{
		this.abortionMethod = abortionMethod;
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
