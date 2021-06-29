package hisglobal.vo;

public class BloodDonationCompMasterVO extends ValueObject
{
	private String donationTypeCode;
	private String bloodComponentID;
	private String minBlockDays;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	private String donationComponentSlNo;
	private String maleMinBlockDays;
	private String femaleMinBlockDays;

	public String getDonationTypeCode()
	{
		return donationTypeCode;
	}

	public void setDonationTypeCode(String donationTypeCode)
	{
		this.donationTypeCode = donationTypeCode;
	}

	public String getBloodComponentID()
	{
		return bloodComponentID;
	}

	public void setBloodComponentID(String bloodComponentID)
	{
		this.bloodComponentID = bloodComponentID;
	}

	public String getMinBlockDays()
	{
		return minBlockDays;
	}

	public void setMinBlockDays(String minBlockDays)
	{
		this.minBlockDays = minBlockDays;
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

	public String getDonationComponentSlNo()
	{
		return donationComponentSlNo;
	}

	public void setDonationComponentSlNo(String donationComponentSlNo)
	{
		this.donationComponentSlNo = donationComponentSlNo;
	}

	public String getMaleMinBlockDays()
	{
		return maleMinBlockDays;
	}

	public void setMaleMinBlockDays(String maleMinBlockDays)
	{
		this.maleMinBlockDays = maleMinBlockDays;
	}

	public String getFemaleMinBlockDays()
	{
		return femaleMinBlockDays;
	}

	public void setFemaleMinBlockDays(String femaleMinBlockDays)
	{
		this.femaleMinBlockDays = femaleMinBlockDays;
	}

	

}
