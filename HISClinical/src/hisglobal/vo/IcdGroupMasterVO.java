package hisglobal.vo;

public class IcdGroupMasterVO extends ValueObject
{
	private String icdGroupCode;
	private String icdGroup;		// May be Formatted  as "40char..(groupcode)"  
	private String icdGroupFull;	// Always contains complete Group Name 
	private String seatId;
	private String entryDate;
	private String isValid;
	private String hospitalCode;
	private String chapterId;
	private String remark;

	public String getIcdGroupCode()
	{
		return icdGroupCode;
	}

	public void setIcdGroupCode(String icdGroupCode)
	{
		this.icdGroupCode = icdGroupCode;
	}

	public String getIcdGroup()
	{
		return icdGroup;
	}

	public void setIcdGroup(String icdGroup)
	{
		this.icdGroup = icdGroup;
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

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getChapterId()
	{
		return chapterId;
	}

	public void setChapterId(String chapterId)
	{
		this.chapterId = chapterId;
	}

	public String getIcdGroupFull()
	{
		return icdGroupFull;
	}

	public void setIcdGroupFull(String icdGroupFull)
	{
		this.icdGroupFull = icdGroupFull;
	}
}
