package hisglobal.vo;

/**
 * @author AHIS
 */
public class EpisodeKeywordsUnitWiseVO extends ValueObject
{
	private String episodeKeywordID;
	private String episodeKeyword;
	private String departmentUnitCode;
	private String hospitalCode;
	private String slNo;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public String getEpisodeKeywordID()
	{
		return episodeKeywordID;
	}

	public void setEpisodeKeywordID(String episodeKeywordID)
	{
		this.episodeKeywordID = episodeKeywordID;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
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

	public String getEpisodeKeyword()
	{
		return episodeKeyword;
	}

	public void setEpisodeKeyword(String episodeKeyword)
	{
		this.episodeKeyword = episodeKeyword;
	}
}
