package hisglobal.vo;

public class BloodComponentStockMasterVO extends ValueObject
{
	private String bloodGroupCode;
	private String bloodComponentID;
	private String slNo;
	private String bufferStock;
	private String reorderLevel;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;

	public String getBloodGroupCode()
	{
		return bloodGroupCode;
	}

	public void setBloodGroupCode(String bloodGroupCode)
	{
		this.bloodGroupCode = bloodGroupCode;
	}

	public String getBloodComponentID()
	{
		return bloodComponentID;
	}

	public void setBloodComponentID(String bloodComponentID)
	{
		this.bloodComponentID = bloodComponentID;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getBufferStock()
	{
		return bufferStock;
	}

	public void setBufferStock(String bufferStock)
	{
		this.bufferStock = bufferStock;
	}

	public String getReorderLevel()
	{
		return reorderLevel;
	}

	public void setReorderLevel(String reorderLevel)
	{
		this.reorderLevel = reorderLevel;
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
