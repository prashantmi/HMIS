package hisglobal.vo;

public class IcdSubgroupMasterVO extends ValueObject
{
	private String icdSubgroupCode;
	private String icdSubgroup;		// May be Formatted  as "40char..(groupcode)"  
	private String icdSubgroupFull;	// Always contains complete Sub Group Name 
	private String icdGroupCode;
	private String seatId;
	private String entryDate;
	private String isValid;
	private String hospitalCode;
	private String subgroupParentCode;
	private String subgroupLevel;
	
	public String getIcdSubgroupCode()
	{
		return icdSubgroupCode;
	}

	public void setIcdSubgroupCode(String icdSubgroupCode)
	{
		this.icdSubgroupCode = icdSubgroupCode;
	}

	public String getIcdSubgroup()
	{
		return icdSubgroup;
	}

	public void setIcdSubgroup(String icdSubgroup)
	{
		this.icdSubgroup = icdSubgroup;
	}

	public String getIcdGroupCode()
	{
		return icdGroupCode;
	}

	public void setIcdGroupCode(String icdGroupCode)
	{
		this.icdGroupCode = icdGroupCode;
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

	public String getSubgroupParentCode()
	{
		return subgroupParentCode;
	}

	public void setSubgroupParentCode(String subgroupParentCode)
	{
		this.subgroupParentCode = subgroupParentCode;
	}

	public String getSubgroupLevel()
	{
		return subgroupLevel;
	}

	public void setSubgroupLevel(String subgroupLevel)
	{
		this.subgroupLevel = subgroupLevel;
	}

	public String getIcdSubgroupFull()
	{
		return icdSubgroupFull;
	}

	public void setIcdSubgroupFull(String icdSubgroupFull)
	{
		this.icdSubgroupFull = icdSubgroupFull;
	}

}
