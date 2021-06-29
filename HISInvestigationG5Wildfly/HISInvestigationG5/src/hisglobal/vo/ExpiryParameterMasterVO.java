package hisglobal.vo;

public class ExpiryParameterMasterVO extends ValueObject
{
    private String expiryParameterID;
    private String expiryParameterSlNo;
    private String expiryParameterDesc;
    private String isActive;
	private String seatID;
    private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	
	
	public String getExpiryParameterID()
	{
		return expiryParameterID;
	}
	public void setExpiryParameterID(String expiryParameterID)
	{
		this.expiryParameterID = expiryParameterID;
	}
	public String getExpiryParameterSlNo()
	{
		return expiryParameterSlNo;
	}
	public void setExpiryParameterSlNo(String expiryParameterSlNo)
	{
		this.expiryParameterSlNo = expiryParameterSlNo;
	}
	public String getExpiryParameterDesc()
	{
		return expiryParameterDesc;
	}
	public void setExpiryParameterDesc(String expiryParameterDesc)
	{
		this.expiryParameterDesc = expiryParameterDesc;
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
	public String getIsActive()
	{
		return isActive;
	}
	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}
    
	
}
