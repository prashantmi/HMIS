package hisglobal.vo;

public class BloodDonationTypeMasterVO extends ValueObject
{
	private String donationTypeCode;
	private String donationTypeDesc;
	private String donationTypeSlNo;
	private String isValid;
	private String seatID;
	private String areaCode;
	private String minDuration;
	private String maxDuration;
	private String remarks;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	private String isConsentFlag;
	private String areaName;
	private String isApheresis;

	public String getDonationTypeCode()
	{
		return donationTypeCode;
	}

	public void setDonationTypeCode(String donationTypeCode)
	{
		this.donationTypeCode = donationTypeCode;
	}

	public String getDonationTypeDesc()
	{
		return donationTypeDesc;
	}

	public void setDonationTypeDesc(String donationTypeDesc)
	{
		this.donationTypeDesc = donationTypeDesc;
	}

	public String getDonationTypeSlNo()
	{
		return donationTypeSlNo;
	}

	public void setDonationTypeSlNo(String donationTypeSlNo)
	{
		this.donationTypeSlNo = donationTypeSlNo;
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

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getMinDuration()
	{
		return minDuration;
	}

	public void setMinDuration(String minDuration)
	{
		this.minDuration = minDuration;
	}

	public String getMaxDuration()
	{
		return maxDuration;
	}

	public void setMaxDuration(String maxDuration)
	{
		this.maxDuration = maxDuration;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
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

	public String getIsConsentFlag()
	{
		return isConsentFlag;
	}

	public void setIsConsentFlag(String isConsentFlag)
	{
		this.isConsentFlag = isConsentFlag;
	}

	public String getAreaName()
	{
		return areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	public String getIsApheresis()
	{
		return isApheresis;
	}

	public void setIsApheresis(String isApheresis)
	{
		this.isApheresis = isApheresis;
	}
}
