package hisglobal.vo;

public class BldReqIndicationDtlVO extends ValueObject
{
	private String hmode;
	private String reqNo;
	private String bldComponentId;
	private String indicationId;
	private String seatId;
	private String entryDate;
	private String isValid;
	private String hospitalCode;
	
	
	public String getHmode()
	{
		return hmode;
	}
	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
	public String getReqNo()
	{
		return reqNo;
	}
	public void setReqNo(String reqNo)
	{
		this.reqNo = reqNo;
	}
	public String getBldComponentId()
	{
		return bldComponentId;
	}
	public void setBldComponentId(String bldComponentId)
	{
		this.bldComponentId = bldComponentId;
	}
	public String getIndicationId()
	{
		return indicationId;
	}
	public void setIndicationId(String indicationId)
	{
		this.indicationId = indicationId;
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
	public String getHospitalCode()
	{
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}
	
	
}
