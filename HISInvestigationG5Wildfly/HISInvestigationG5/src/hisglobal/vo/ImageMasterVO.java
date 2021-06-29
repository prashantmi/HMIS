package hisglobal.vo;

public class ImageMasterVO extends ValueObject
{
	private String imageCode;
	private String hospitalCode;
	private String slNo;
	private String imageName;
	private String fileName;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String isDefault;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	private byte[] imageFile;
	private String imageFirstName;
	private String ext;
	private String uploadImageName;
	
	public String getImageFirstName()
	{
		return imageFirstName;
	}

	public void setImageFirstName(String imageFirstName)
	{
		this.imageFirstName = imageFirstName;
	}

	public String getExt()
	{
		return ext;
	}

	public void setExt(String ext)
	{
		this.ext = ext;
	}

	public byte[] getImageFile()
	{
		return imageFile;
	}

	public void setImageFile(byte[] imageFile)
	{
		this.imageFile = imageFile;
	}

	public String getImageCode()
	{
		return imageCode;
	}

	public void setImageCode(String imageCode)
	{
		this.imageCode = imageCode;
	}

	public String getImageName()
	{
		return imageName;
	}

	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
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

	public String getUploadImageName()
	{
		return uploadImageName;
	}

	public void setUploadImageName(String uploadImageName)
	{
		this.uploadImageName = uploadImageName;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
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
