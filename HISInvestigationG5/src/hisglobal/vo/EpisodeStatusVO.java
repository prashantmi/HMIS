package hisglobal.vo;

public class EpisodeStatusVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String episodeTypeCode;
	private String episodeType; //episode type
	private String episodeDate;
	private String episodeStatus;
	private String departmentCode;
	private String department;
	private String departmentUnit;
	private String departmentUnitCode;
	private String episodeCloseDate;
	private String episodeCloseType;
	private String entryDate;
	private String seatId;
	private String isValid;

	public java.lang.String getEpisodeCloseDate()
	{
		return episodeCloseDate;
	}

	public void setEpisodeCloseDate(java.lang.String episodeCloseDate)
	{
		this.episodeCloseDate = episodeCloseDate;
	}

	public java.lang.String getEpisodeCloseType()
	{
		return episodeCloseType;
	}

	public void setEpisodeCloseType(java.lang.String episodeCloseType)
	{
		this.episodeCloseType = episodeCloseType;
	}

	public java.lang.String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(java.lang.String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public java.lang.String getEpisodeDate()
	{
		return episodeDate;
	}

	public void setEpisodeDate(java.lang.String episodeDate)
	{
		this.episodeDate = episodeDate;
	}

	public java.lang.String getEpisodeStatus()
	{
		return episodeStatus;
	}

	public void setEpisodeStatus(java.lang.String episodeStatus)
	{
		this.episodeStatus = episodeStatus;
	}

	public java.lang.String getEpisodeType()
	{
		return episodeType;
	}

	public void setEpisodeType(java.lang.String episodeType)
	{
		this.episodeType = episodeType;
	}

	public java.lang.String getEpisodeTypeCode()
	{
		return episodeTypeCode;
	}

	public void setEpisodeTypeCode(java.lang.String episodeTypeCode)
	{
		this.episodeTypeCode = episodeTypeCode;
	}

	public java.lang.String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(java.lang.String isValid)
	{
		this.isValid = isValid;
	}

	public java.lang.String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(java.lang.String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public java.lang.String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(java.lang.String seatId)
	{
		this.seatId = seatId;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getDepartmentUnit()
	{
		return departmentUnit;
	}

	public void setDepartmentUnit(String departmentUnit)
	{
		this.departmentUnit = departmentUnit;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}
}
