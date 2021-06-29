package hisglobal.vo;

public class BloodBagComponentDtlVO extends ValueObject
{
	private String bagNo;
	private String bloodComponentID;
	private String bloodComponentName;
	private String bloodBagExpiry; /// Pass Number of Days for expiry calculation
	private String bloodBagSeqNo;
	private String bagVolume;
	private String bagStatus;
	private String storageID;
	private String bagTypeCode;
	private String bagTypeDesc;
	private String reasonCode;
	private String reserveStatus;
	private String bloodGroupCode;
	private String methodID;
	private String donationTypeFlag;
	private String donationTypeCode;
	private String donationTypeDesc;

	
	
	public String getDonationTypeDesc()
	{
		return donationTypeDesc;
	}
	public void setDonationTypeDesc(String donationTypeDesc)
	{
		this.donationTypeDesc = donationTypeDesc;
	}
	public String getBagNo()
	{
		return bagNo;
	}
	public void setBagNo(String bagNo)
	{
		this.bagNo = bagNo;
	}
	public String getBloodComponentID()
	{
		return bloodComponentID;
	}
	public void setBloodComponentID(String bloodComponentID)
	{
		this.bloodComponentID = bloodComponentID;
	}
	public String getBloodBagExpiry()
	{
		return bloodBagExpiry;
	}
	public void setBloodBagExpiry(String bloodBagExpiry)
	{
		this.bloodBagExpiry = bloodBagExpiry;
	}
	public String getBloodBagSeqNo()
	{
		return bloodBagSeqNo;
	}
	public void setBloodBagSeqNo(String bloodBagSeqNo)
	{
		this.bloodBagSeqNo = bloodBagSeqNo;
	}
	public String getBagVolume()
	{
		return bagVolume;
	}
	public void setBagVolume(String bagVolume)
	{
		this.bagVolume = bagVolume;
	}
	public String getBagStatus()
	{
		return bagStatus;
	}
	public void setBagStatus(String bagStatus)
	{
		this.bagStatus = bagStatus;
	}
	public String getStorageID()
	{
		return storageID;
	}
	public void setStorageID(String storageID)
	{
		this.storageID = storageID;
	}
	
	public String getBagTypeCode()
	{
		return bagTypeCode;
	}
	public void setBagTypeCode(String bagTypeCode)
	{
		this.bagTypeCode = bagTypeCode;
	}
	public String getReasonCode()
	{
		return reasonCode;
	}
	public void setReasonCode(String reasonCode)
	{
		this.reasonCode = reasonCode;
	}
	public String getReserveStatus()
	{
		return reserveStatus;
	}
	public void setReserveStatus(String reserveStatus)
	{
		this.reserveStatus = reserveStatus;
	}
	public String getBloodGroupCode()
	{
		return bloodGroupCode;
	}
	public void setBloodGroupCode(String bloodGroupCode)
	{
		this.bloodGroupCode = bloodGroupCode;
	}
	public String getMethodID()
	{
		return methodID;
	}
	public void setMethodID(String methodID)
	{
		this.methodID = methodID;
	}
	public String getDonationTypeFlag()
	{
		return donationTypeFlag;
	}
	public void setDonationTypeFlag(String donationTypeFlag)
	{
		this.donationTypeFlag = donationTypeFlag;
	}
	public String getDonationTypeCode()
	{
		return donationTypeCode;
	}
	public void setDonationTypeCode(String donationTypeCode)
	{
		this.donationTypeCode = donationTypeCode;
	}
	public String getBloodComponentName()
	{
		return bloodComponentName;
	}
	public void setBloodComponentName(String bloodComponentName)
	{
		this.bloodComponentName = bloodComponentName;
	}
	public String getBagTypeDesc()
	{
		return bagTypeDesc;
	}
	public void setBagTypeDesc(String bagTypeDesc)
	{
		this.bagTypeDesc = bagTypeDesc;
	}
	
	
	
}
