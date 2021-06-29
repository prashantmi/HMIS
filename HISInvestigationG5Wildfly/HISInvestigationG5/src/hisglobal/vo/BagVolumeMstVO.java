package hisglobal.vo;

public class BagVolumeMstVO extends ValueObject
{
	private String bagVolumeID;
	private String bagVolumeSlNo;
	private String bagVolumeDescription;
	private String isActive;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	
	
	
	public String getBagVolumeID()
	{
		return bagVolumeID;
	}
	public void setBagVolumeID(String bagVolumeID)
	{
		this.bagVolumeID = bagVolumeID;
	}
	public String getBagVolumeSlNo()
	{
		return bagVolumeSlNo;
	}
	public void setBagVolumeSlNo(String bagVolumeSlNo)
	{
		this.bagVolumeSlNo = bagVolumeSlNo;
	}
	public String getBagVolumeDescription()
	{
		return bagVolumeDescription;
	}
	public void setBagVolumeDescription(String bagVolumeDescription)
	{
		this.bagVolumeDescription = bagVolumeDescription;
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
