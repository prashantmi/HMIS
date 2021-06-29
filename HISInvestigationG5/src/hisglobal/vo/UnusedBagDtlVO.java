package hisglobal.vo;

public class UnusedBagDtlVO extends ValueObject
{
	private String bagNo;
	private String bagType;
	private String bagTypeDesc;
	private String tubingNo;
	private String donationTypeFlag;
	private String bagStatus;
	
	
	public String getBagNo()
	{
		return bagNo;
	}
	public void setBagNo(String bagNo)
	{
		this.bagNo = bagNo;
	}
	
	public String getBagType()
	{
		return bagType;
	}
	public void setBagType(String bagType)
	{
		this.bagType = bagType;
	}
	public String getBagTypeDesc()
	{
		return bagTypeDesc;
	}
	public void setBagTypeDesc(String bagTypeDesc)
	{
		this.bagTypeDesc = bagTypeDesc;
	}
	public String getTubingNo()
	{
		return tubingNo;
	}
	public void setTubingNo(String tubingNo)
	{
		this.tubingNo = tubingNo;
	}
	public String getDonationTypeFlag()
	{
		return donationTypeFlag;
	}
	public void setDonationTypeFlag(String donationTypeFlag)
	{
		this.donationTypeFlag = donationTypeFlag;
	}
	public String getBagStatus()
	{
		return bagStatus;
	}
	public void setBagStatus(String bagStatus)
	{
		this.bagStatus = bagStatus;
	}
}
