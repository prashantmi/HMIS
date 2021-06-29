package hisglobal.vo;

public class RenewalDetailVO extends ValueObject
{

	private String patCrNo;
	private String serialNo;
	private String renewalType;
	private String oldExpiryDate;
	private String newExpiryDate;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String departmentCode;
	private String departmentUnitCode;
	private String systemIPAddress;
	private String hospitalCode;

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getRenewalType()
	{
		return renewalType;
	}

	public void setRenewalType(String renewalType)
	{
		this.renewalType = renewalType;
	}

	public String getOldExpiryDate()
	{
		return oldExpiryDate;
	}

	public void setOldExpiryDate(String oldExpiryDate)
	{
		this.oldExpiryDate = oldExpiryDate;
	}

	public String getNewExpiryDate()
	{
		return newExpiryDate;
	}

	public void setNewExpiryDate(String newExpiryDate)
	{
		this.newExpiryDate = newExpiryDate;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
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

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}

	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
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
