package hisglobal.vo;

public class BagCancellationDtlVO extends ValueObject 
{
	private String tubingNo;
	private String bagNo;
	private String donorRegistrationNo;
	private String donorVisitNo;
	private String cancelReason;
	private String bagStatus;
	
	
	public String getTubingNo()
	{
		return tubingNo;
	}
	public void setTubingNo(String tubingNo)
	{
		this.tubingNo = tubingNo;
	}
	public String getBagNo()
	{
		return bagNo;
	}
	public void setBagNo(String bagNo)
	{
		this.bagNo = bagNo;
	}
	public String getDonorRegistrationNo()
	{
		return donorRegistrationNo;
	}
	public void setDonorRegistrationNo(String donorRegistrationNo)
	{
		this.donorRegistrationNo = donorRegistrationNo;
	}
	public String getDonorVisitNo()
	{
		return donorVisitNo;
	}
	public void setDonorVisitNo(String donorVisitNo)
	{
		this.donorVisitNo = donorVisitNo;
	}
	public String getCancelReason()
	{
		return cancelReason;
	}
	public void setCancelReason(String cancelReason)
	{
		this.cancelReason = cancelReason;
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
