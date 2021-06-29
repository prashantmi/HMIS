package hisglobal.vo;

public class ImagePointerMasterVO extends ValueObject
{
	private String imageDescId;
	private String hospitalCode;
	private String slNo;
	private String imageDesc;
	private String isValid;
	private String isActive;
	private String seatId;
	private String entryDate;
	private String isDefault;
	private String color;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsDefault()
	{
		return isDefault;
	}

	public void setIsDefault(String isDefault)
	{
		this.isDefault = isDefault;
	}

	public String getImageDescId()
	{
		return imageDescId;
	}

	public void setImageDescId(String imageDescId)
	{
		this.imageDescId = imageDescId;
	}

	public String getImageDesc()
	{
		return imageDesc;
	}

	public void setImageDesc(String imageDesc)
	{
		this.imageDesc = imageDesc;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
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
