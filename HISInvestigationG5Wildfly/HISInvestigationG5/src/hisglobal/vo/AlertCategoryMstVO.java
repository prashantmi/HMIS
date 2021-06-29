package hisglobal.vo;

public class AlertCategoryMstVO extends ValueObject
{
	private String hospitalcode;
	private String alertCategoryCode;
	private String alertCatagorySlNo;
	private String aleretCatagoryType;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String lastModDate;
	private String lastModSeatId;

	public String getHospitalcode()
	{
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode)
	{
		this.hospitalcode = hospitalcode;
	}

	public String getAlertCategoryCode()
	{
		return alertCategoryCode;
	}

	public void setAlertCategoryCode(String alertCategoryCode)
	{
		this.alertCategoryCode = alertCategoryCode;
	}

	public String getAlertCatagorySlNo()
	{
		return alertCatagorySlNo;
	}

	public void setAlertCatagorySlNo(String alertCatagorySlNo)
	{
		this.alertCatagorySlNo = alertCatagorySlNo;
	}

	public String getAleretCatagoryType()
	{
		return aleretCatagoryType;
	}

	public void setAleretCatagoryType(String aleretCatagoryType)
	{
		this.aleretCatagoryType = aleretCatagoryType;
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

	public String getLastModDate()
	{
		return lastModDate;
	}

	public void setLastModDate(String lastModDate)
	{
		this.lastModDate = lastModDate;
	}

	public String getLastModSeatId()
	{
		return lastModSeatId;
	}

	public void setLastModSeatId(String lastModSeatId)
	{
		this.lastModSeatId = lastModSeatId;
	}

}
