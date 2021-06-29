package hisglobal.vo;

public class ComponentExpiryMasterVO extends ValueObject
{

	private String bloodComponentID;
	private String expiryParameterID;
	private String componentExpirySlNo; 
	private String bagExpiryDays;
	private String isActive;
	private String seatID;
    private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	
	
	
	public String getBloodComponentID()
	{
		return bloodComponentID;
	}
	public void setBloodComponentID(String bloodComponentID)
	{
		this.bloodComponentID = bloodComponentID;
	}
	public String getExpiryParameterID()
	{
		return expiryParameterID;
	}
	public void setExpiryParameterID(String expiryParameterID)
	{
		this.expiryParameterID = expiryParameterID;
	}
	public String getComponentExpirySlNo()
	{
		return componentExpirySlNo;
	}
	public void setComponentExpirySlNo(String componentExpirySlNo)
	{
		this.componentExpirySlNo = componentExpirySlNo;
	}
	public String getBagExpiryDays()
	{
		return bagExpiryDays;
	}
	public void setBagExpiryDays(String bagExpiryDays)
	{
		this.bagExpiryDays = bagExpiryDays;
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
