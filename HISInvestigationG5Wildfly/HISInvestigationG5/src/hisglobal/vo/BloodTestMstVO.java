package hisglobal.vo;

public class BloodTestMstVO extends ValueObject
{
	private String bloodTestId;
	private String bloodTestName;
	private String bloodTestSlNo;
	private String bloodTestDescription;
	private String isActive;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	public String getBloodTestId()
	{
		return bloodTestId;
	}
	public void setBloodTestId(String bloodTestId)
	{
		this.bloodTestId = bloodTestId;
	}
	public String getBloodTestName()
	{
		return bloodTestName;
	}
	public void setBloodTestName(String bloodTestName)
	{
		this.bloodTestName = bloodTestName;
	}
	public String getBloodTestSlNo()
	{
		return bloodTestSlNo;
	}
	public void setBloodTestSlNo(String bloodTestSlNo)
	{
		this.bloodTestSlNo = bloodTestSlNo;
	}
	public String getBloodTestDescription()
	{
		return bloodTestDescription;
	}
	public void setBloodTestDescription(String bloodTestDescription)
	{
		this.bloodTestDescription = bloodTestDescription;
	}
	public String getIsActive()
	{
		return isActive;
	}
	public void setIsActive(String isActive)
	{
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