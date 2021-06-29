package hisglobal.vo;

/**
 * DonorAdversereactionVO is the class that specifies getters and setters for all the identifiers
 * which are used for retrieving and inserting data in the DonorAdverseReaction table. 
 * @author AHIS
 */


public class DonorAdverseReactionVO extends ValueObject
{
	
	private String donorRegistrationNo;
	private String donorVisitNo;
	private String reasonCode;
	private String slNo;
	private String reactionStartDate;
	private String reactionEndDate;
	private String reactionStartTime;
	private String reactionEndTime;
	private String controlSummary;
	private String reactionSummary;
	private String hospitalCode;
	private String futurePrecaution;
	private String seatId;
	private String entryDate;
	private String isValid;
	private String ipAddress;
	
	
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
	public String getReasonCode()
	{
		return reasonCode;
	}
	public void setReasonCode(String reasonCode)
	{
		this.reasonCode = reasonCode;
	}
	public String getSlNo()
	{
		return slNo;
	}
	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}
	public String getReactionStartTime()
	{
		return reactionStartTime;
	}
	public void setReactionStartTime(String reactionStartTime)
	{
		this.reactionStartTime = reactionStartTime;
	}
	public String getReactionEndTime()
	{
		return reactionEndTime;
	}
	public void setReactionEndTime(String reactionEndTime)
	{
		this.reactionEndTime = reactionEndTime;
	}
	public String getControlSummary()
	{
		return controlSummary;
	}
	public void setControlSummary(String controlSummary)
	{
		this.controlSummary = controlSummary;
	}
	public String getReactionSummary()
	{
		return reactionSummary;
	}
	public void setReactionSummary(String reactionSummary)
	{
		this.reactionSummary = reactionSummary;
	}
	public String getHospitalCode()
	{
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}
	public String getFuturePrecaution()
	{
		return futurePrecaution;
	}
	public void setFuturePrecaution(String futurePrecaution)
	{
		this.futurePrecaution = futurePrecaution;
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
	public String getIsValid()
	{
		return isValid;
	}
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}
	
	public String getIpAddress()
	{
		return ipAddress;
	}
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	public String getReactionStartDate()
	{
		return reactionStartDate;
	}
	public void setReactionStartDate(String reactionStartDate)
	{
		this.reactionStartDate = reactionStartDate;
	}
	public String getReactionEndDate()
	{
		return reactionEndDate;
	}
	public void setReactionEndDate(String reactionEndDate)
	{
		this.reactionEndDate = reactionEndDate;
	}
}
