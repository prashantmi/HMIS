package hisglobal.vo;

/**
 * PatientStatusChangeVO is the class that specifies getters and setters for all the identifiers which are used for
 * retrieving and inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class PatientStatusChangeVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String patStatusCodeOld;
	private String patStatusCodeNew;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String patStatusChangeDate;

	/**
	 * Retrieves entryDate.
	 * 
	 * @return Value of entryDate.
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets entryDate.
	 * 
	 * @param entryDate
	 */
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * Retrieves episodeCode.
	 * 
	 * @return Value of episodeCode.
	 */
	public String getEpisodeCode()
	{
		return episodeCode;
	}

	/**
	 * Sets episodeCode.
	 * 
	 * @param episodeCode
	 */
	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	/**
	 * Retrieves episodeVisitNo.
	 * 
	 * @return Value of episodeVisitNo.
	 */
	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	/**
	 * Sets episodeVisitNo.
	 * 
	 * @param episodeVisitNo
	 */
	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	/**
	 * Retrieves isValid.
	 * 
	 * @return Value of isValid.
	 */
	public String getIsValid()
	{
		return isValid;
	}

	/**
	 * Sets isValid.
	 * 
	 * @param isValid
	 */
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	/**
	 * Retrieves patCrNo.
	 * 
	 * @return Value of patCrNo.
	 */
	public String getPatCrNo()
	{
		return patCrNo;
	}

	/**
	 * Sets patCrNo.
	 * 
	 * @param patCrNo
	 */
	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	/**
	 * Retrieves patStatusChangeDate.
	 * 
	 * @return Value of patStatusChangeDate.
	 */
	public String getPatStatusChangeDate()
	{
		return patStatusChangeDate;
	}

	/**
	 * Sets patStatusChangeDate.
	 * 
	 * @param patStatusChangeDate
	 */
	public void setPatStatusChangeDate(String patStatusChangeDate)
	{
		this.patStatusChangeDate = patStatusChangeDate;
	}

	/**
	 * Retrieves patStatusCodeNew.
	 * 
	 * @return Value of patStatusCodeNew.
	 */
	public String getPatStatusCodeNew()
	{
		return patStatusCodeNew;
	}

	/**
	 * Sets patStatusCodeNew.
	 * 
	 * @param patStatusCodeNew
	 */
	public void setPatStatusCodeNew(String patStatusCodeNew)
	{
		this.patStatusCodeNew = patStatusCodeNew;
	}

	/**
	 * Retrieves patStatusCodeOld.
	 * 
	 * @return Value of patStatusCodeOld.
	 */
	public String getPatStatusCodeOld()
	{
		return patStatusCodeOld;
	}

	/**
	 * Sets patStatusCodeOld.
	 * 
	 * @param patStatusCodeOld
	 */
	public void setPatStatusCodeOld(String patStatusCodeOld)
	{
		this.patStatusCodeOld = patStatusCodeOld;
	}

	/**
	 * Retrieves seatId.
	 * 
	 * @return Value of seatId.
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * Sets seatId.
	 * 
	 * @param seatId
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

}
