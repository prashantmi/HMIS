package hisglobal.vo;

/**
 * VerificationDocumentVO is the class that specifies getters and setters for all the identifiers
 * which are used for retrieving and inserting data in the DB tables. 
 * @author AHIS
 */

public class VerificationDocumentVO extends ValueObject
{
	private String menuID;
	private String patCrNo;
	private String episodeCode;
	private String serialNo;
	private String submissionDate;
	private String verificationDocuments;
	private String remarks;
	private String isValid;
	private String seatId;
	private String entryDate;

	/**
	 * Retrieves verificationDocuments.
	 * @return Value of verificationDocuments.	
	 */
	public String getVerificationDocuments()
	{
		return verificationDocuments;
	}

	/**
	 * Sets verificationDocuments.
	 * @param verificationDocuments
	 */
	public void setVerificationDocuments(String verificationDocuments)
	{
		this.verificationDocuments = verificationDocuments;
	}

	/**
	 * Retrieves menuID.
	 * @return Value of menuID.	
	 */
	public String getMenuID()
	{
		return menuID;
	}

	/**
	 * Sets menuID.
	 * @param menuID
	 */
	public void setMenuID(String menuID)
	{
		this.menuID = menuID;
	}

	/**
	 * Retrieves episodeCode.
	 * @return Value of episodeCode.	
	 */
	public String getEpisodeCode()
	{
		return episodeCode;
	}

	/**
	 * Sets episodeCode.
	 * @param episodeCode
	 */
	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	/**
	 * Sets submissionDate.
	 * @param submissionDate
	 */
	public void setSubmissionDate(String submissionDate)
	{
		this.submissionDate = submissionDate;
	}

	/**
	 * Retrieves submissionDate.
	 * @return Value of submissionDate.	
	 */
	public String getSubmissionDate()
	{
		return this.submissionDate;
	}

	/**
	 * Sets remarks.
	 * @param remarks
	 */
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	/**
	 * Retrieves remarks.
	 * @return Value of remarks.	
	 */
	public String getRemarks()
	{
		return remarks;
	}

	/**
	 * Retrieves entryDate.
	 * @return Value of entryDate.	
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets entryDate.
	 * @param entryDate
	 */
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * Retrieves isValid.
	 * @return Value of isValid.	
	 */
	public String getIsValid()
	{
		return isValid;
	}

	/**
	 * Sets isValid.
	 * @param isValid
	 */
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	/**
	 * Retrieves patCrNo.
	 * @return Value of patCrNo.	
	 */
	public String getPatCrNo()
	{
		return patCrNo;
	}

	/**
	 * Sets patCrNo.
	 * @param patCrNo
	 */
	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	/**
	 * Retrieves seatId.
	 * @return Value of seatId.	
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * Sets seatId.
	 * @param seatId
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	/**
	 * Retrieves serialNo.
	 * @return Value of serialNo.	
	 */
	public String getSerialNo()
	{
		return serialNo;
	}

	/**
	 * Sets serialNo.
	 * @param serialNo
	 */
	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

}
