package hisglobal.vo;

public class EpisodeCloseVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String episodeType;
	private String episodeTypeCode;
	private String admissionNo;
	private String episodeDate;
	private String episodeCloseDate;
	private String episodeCloseType;
	private String isValid;
	
	private String isClosedPreviously;
	
	private String isAutoClose;	// New Field

	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}

	public String getEpisodeCloseDate()
	{
		return episodeCloseDate;
	}

	public void setEpisodeCloseDate(String episodeCloseDate)
	{
		this.episodeCloseDate = episodeCloseDate;
	}

	public String getEpisodeCloseType()
	{
		return episodeCloseType;
	}

	public void setEpisodeCloseType(String episodeCloseType)
	{
		this.episodeCloseType = episodeCloseType;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getEpisodeType()
	{
		return episodeType;
	}

	public void setEpisodeType(String episodeType)
	{
		this.episodeType = episodeType;
	}

	public String getEpisodeTypeCode()
	{
		return episodeTypeCode;
	}

	public void setEpisodeTypeCode(String episodeTypeCode)
	{
		this.episodeTypeCode = episodeTypeCode;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getEpisodeDate()
	{
		return episodeDate;
	}

	public void setEpisodeDate(String episodeDate)
	{
		this.episodeDate = episodeDate;
	}

	public String getIsClosedPreviously() {
		return isClosedPreviously;
	}

	public void setIsClosedPreviously(String isClosedPreviously) {
		this.isClosedPreviously = isClosedPreviously;
	}

	public String getIsAutoClose()
	{
		return isAutoClose;
	}

	public void setIsAutoClose(String isAutoClose)
	{
		this.isAutoClose = isAutoClose;
	}

}
