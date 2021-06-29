package hisglobal.vo;

public class BloodReasonMstVO extends ValueObject
{
    private String reasonCode;
    private String reasonType;
    private String reason;
    private String slNo;
    private String isActive;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	
	
	public String getReasonCode()
	{
		return reasonCode;
	}
	public void setReasonCode(String reasonCode)
	{
		this.reasonCode = reasonCode;
	}
	public String getReasonType()
	{
		return reasonType;
	}
	public void setReasonType(String reasonType)
	{
		this.reasonType = reasonType;
	}
	public String getReason()
	{
		return reason;
	}
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	public String getSlNo()
	{
		return slNo;
	}
	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}
	public String getIsActive()
	{
		return isActive;
	}
	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
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
