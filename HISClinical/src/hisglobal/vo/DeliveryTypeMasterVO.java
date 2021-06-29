package hisglobal.vo;

public class DeliveryTypeMasterVO extends ValueObject
{
	private String deliveryTypeId;
	private String slNo;
	private String hospitalCode;
	private String deliveryType;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public String getDeliveryTypeId()
	{
		return deliveryTypeId;
	}

	public void setDeliveryTypeId(String deliveryTypeId)
	{
		this.deliveryTypeId = deliveryTypeId;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getDeliveryType()
	{
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType)
	{
		this.deliveryType = deliveryType;
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
