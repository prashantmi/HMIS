package hisglobal.vo;

public class DonorImageDtlVO extends ValueObject
{
	private String donorRegistrationNo;
	private String SlNo;
	private String hospitalCode;
	private String isDefault;
	private String ipAddress;
	private String fileNo;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String lastModifiedSeatId;
	private String lastModifiedDate;
	private byte[] imageFile;
	private String imageFileName;
	public String getDonorRegistrationNo()
	{
		return donorRegistrationNo;
	}
	public void setDonorRegistrationNo(String donorRegistrationNo)
	{
		this.donorRegistrationNo = donorRegistrationNo;
	}
	public String getSlNo()
	{
		return SlNo;
	}
	public void setSlNo(String slNo)
	{
		SlNo = slNo;
	}
	public String getHospitalCode()
	{
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}
	public String getIsDefault()
	{
		return isDefault;
	}
	public void setIsDefault(String isDefault)
	{
		this.isDefault = isDefault;
	}
	public String getIpAddress()
	{
		return ipAddress;
	}
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	public String getFileNo()
	{
		return fileNo;
	}
	public void setFileNo(String fileNo)
	{
		this.fileNo = fileNo;
	}
	public String getIsValid()
	{
		return isValid;
	}
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}
	public String getSeatId()
	{
		return seatId;
	}
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}
	public String getEntryDate()
	{
		return entryDate;
	}
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}
	public String getLastModifiedSeatId()
	{
		return lastModifiedSeatId;
	}
	public void setLastModifiedSeatId(String lastModifiedSeatId)
	{
		this.lastModifiedSeatId = lastModifiedSeatId;
	}
	public String getLastModifiedDate()
	{
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate)
	{
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public byte[] getImageFile()
	{
		return imageFile;
	}
	public void setImageFile(byte[] imageFile)
	{
		this.imageFile = imageFile;
	}
	public String getImageFileName()
	{
		return imageFileName;
	}
	public void setImageFileName(String imageFileName)
	{
		this.imageFileName = imageFileName;
	}
	
}
