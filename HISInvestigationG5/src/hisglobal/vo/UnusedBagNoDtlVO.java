package hisglobal.vo;

public class UnusedBagNoDtlVO extends ValueObject
{
	private String bagNo;
	private String bagGenerationDate;
	private String bagStatus;
	private String bagUsedDate;
	private String donationTypeFlag;
	
	
	public String getBagNo()
	{
		return bagNo;
	}
	public void setBagNo(String bagNo)
	{
		this.bagNo = bagNo;
	}
	public String getBagGenerationDate()
	{
		return bagGenerationDate;
	}
	public void setBagGenerationDate(String bagGenerationDate)
	{
		this.bagGenerationDate = bagGenerationDate;
	}
	public String getBagStatus()
	{
		return bagStatus;
	}
	public void setBagStatus(String bagStatus)
	{
		this.bagStatus = bagStatus;
	}
	public String getBagUsedDate()
	{
		return bagUsedDate;
	}
	public void setBagUsedDate(String bagUsedDate)
	{
		this.bagUsedDate = bagUsedDate;
	}
	public String getDonationTypeFlag()
	{
		return donationTypeFlag;
	}
	public void setDonationTypeFlag(String donationTypeFlag)
	{
		this.donationTypeFlag = donationTypeFlag;
	}
}
