package hisglobal.vo;

public class AbortionTypeMasterVO extends ValueObject
{
	private String abortionTypeID;
	private String slNo;
	private String hospitalCode;
	private String abortionType;
	private String isActive;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	

	public String getAbortionTypeID() {
		return abortionTypeID;
	}

	public void setAbortionTypeID(String abortionTypeID) {
		this.abortionTypeID = abortionTypeID;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getAbortionType()
	{
		return abortionType;
	}

	public void setAbortionType(String abortionType)
	{
		this.abortionType = abortionType;
	}



	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
