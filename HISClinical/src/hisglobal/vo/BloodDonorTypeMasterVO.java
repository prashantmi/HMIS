package hisglobal.vo;

public class BloodDonorTypeMasterVO extends ValueObject
{
	private String donorTypeCode;
	private String donorTypeDesc;
	private String donorTypeSlNo;
	private String cardExpiryDays;
	private String usabilityFlag;
	private String groupingFlag;
	private String investigationFlag;
	private String requisitionBasedFlag; 
	private String cardUnitAccountFlag;
	private String isValid;
	private String seatID;
	private String isCardPrint;
	private String minimumAge;
	private String maximumAge;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	private String isPaid;

	public String getDonorTypeCode()
	{
		return donorTypeCode;
	}

	public void setDonorTypeCode(String donorTypeCode)
	{
		this.donorTypeCode = donorTypeCode;
	}

	public String getDonorTypeDesc()
	{
		return donorTypeDesc;
	}

	public void setDonorTypeDesc(String donorTypeDesc)
	{
		this.donorTypeDesc = donorTypeDesc;
	}

	public String getDonorTypeSlNo()
	{
		return donorTypeSlNo;
	}

	public void setDonorTypeSlNo(String donorTypeSlNo)
	{
		this.donorTypeSlNo = donorTypeSlNo;
	}

	public String getCardExpiryDays()
	{
		return cardExpiryDays;
	}

	public void setCardExpiryDays(String cardExpiryDays)
	{
		this.cardExpiryDays = cardExpiryDays;
	}

	public String getUsabilityFlag()
	{
		return usabilityFlag;
	}

	public void setUsabilityFlag(String usabilityFlag)
	{
		this.usabilityFlag = usabilityFlag;
	}

	public String getGroupingFlag()
	{
		return groupingFlag;
	}

	public void setGroupingFlag(String groupingFlag)
	{
		this.groupingFlag = groupingFlag;
	}

	public String getInvestigationFlag()
	{
		return investigationFlag;
	}

	public void setInvestigationFlag(String investigationFlag)
	{
		this.investigationFlag = investigationFlag;
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

	public String getIsCardPrint()
	{
		return isCardPrint;
	}

	public void setIsCardPrint(String isCardPrint)
	{
		this.isCardPrint = isCardPrint;
	}

	public String getMinimumAge()
	{
		return minimumAge;
	}

	public void setMinimumAge(String minimumAge)
	{
		this.minimumAge = minimumAge;
	}

	public String getMaximumAge()
	{
		return maximumAge;
	}

	public void setMaximumAge(String maximumAge)
	{
		this.maximumAge = maximumAge;
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

	public String getCardUnitAccountFlag()
	{
		return cardUnitAccountFlag;
	}

	public void setCardUnitAccountFlag(String cardUnitAccountFlag)
	{
		this.cardUnitAccountFlag = cardUnitAccountFlag;
	}

	public String getRequisitionBasedFlag()
	{
		return requisitionBasedFlag;
	}

	public void setRequisitionBasedFlag(String requisitionBasedFlag)
	{
		this.requisitionBasedFlag = requisitionBasedFlag;
	}

	public String getIsPaid()
	{
		return isPaid;
	}

	public void setIsPaid(String isPaid)
	{
		this.isPaid = isPaid;
	}
}
