package hisglobal.vo;

public class PatientBelongingVO extends ValueObject
{

	private String patCrNo;
	private String belongingItemCode;
	private String belongingItemName;
	private String quantity;
	private String collectionDate;
	private String handOverBy;
	private String handOverTo;
	private String handOverDate;
	private String seatID;
	private String entryDate;
	private String isValid;
	private String remarks;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getBelongingItemCode()
	{
		return belongingItemCode;
	}

	public void setBelongingItemCode(String belongingItemCode)
	{
		this.belongingItemCode = belongingItemCode;
	}

	public String getQuantity()
	{
		return quantity;
	}

	public void setQuantity(String quantity)
	{
		this.quantity = quantity;
	}

	public String getCollectionDate()
	{
		return collectionDate;
	}

	public void setCollectionDate(String collectionDate)
	{
		this.collectionDate = collectionDate;
	}

	public String getHandOverBy()
	{
		return handOverBy;
	}

	public void setHandOverBy(String handOverBy)
	{
		this.handOverBy = handOverBy;
	}

	public String getHandOverTo()
	{
		return handOverTo;
	}

	public void setHandOverTo(String handOverTo)
	{
		this.handOverTo = handOverTo;
	}

	public String getHandOverDate()
	{
		return handOverDate;
	}

	public void setHandOverDate(String handOverDate)
	{
		this.handOverDate = handOverDate;
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

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
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

	public String getBelongingItemName()
	{
		return belongingItemName;
	}

	public void setBelongingItemName(String belongingItemName)
	{
		this.belongingItemName = belongingItemName;
	}

}
