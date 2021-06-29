package hisglobal.vo;

/**
 * ChangeCategoryVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class ChangeCategoryVO extends ValueObject
{

	private String patCrNo;
	private String serialNo;
	private String patPrevPrimaryCat;
	private String patPrevPrimaryCatCode;
	private String patPrevSecondaryCat;
	private String patPrevSecondaryCatCode;
	private String patNewPrimaryCat;
	private String patNewPrimaryCatCode;
	private String patNewSecondaryCat;
	private String patNewSecondaryCatCode;
	private String categoryChangeDate;
	private String categoryChangeRemarks;
	private String entryDate;
	private String isValid;
	private String seatId;
	private String systemIPAddress;
	private String episodeCode;

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
	 * Retrieves episodeCode.
	 * 
	 * @return Value of episodeCode.
	 */
	public String getEpisodeCode()
	{
		return episodeCode;
	}

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

	/**
	 * Retrieves serialNo.
	 * 
	 * @return Value of serialNo.
	 */
	public String getSerialNo()
	{
		return serialNo;
	}

	/**
	 * Sets serialNo.
	 * 
	 * @param serialNo
	 */
	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	/**
	 * Retrieves categoryChangeDate.
	 * 
	 * @return Value of categoryChangeDate.
	 */
	public String getCategoryChangeDate()
	{
		return categoryChangeDate;
	}

	/**
	 * Sets categoryChangeDate.
	 * 
	 * @param categoryChangeDate
	 */
	public void setCategoryChangeDate(String categoryChangeDate)
	{
		this.categoryChangeDate = categoryChangeDate;
	}

	/**
	 * Retrieves categoryChangeRemarks.
	 * 
	 * @return Value of categoryChangeRemarks.
	 */
	public String getCategoryChangeRemarks()
	{
		return categoryChangeRemarks;
	}

	/**
	 * Sets categoryChangeRemarks.
	 * 
	 * @param categoryChangeRemarks
	 */
	public void setCategoryChangeRemarks(String categoryChangeRemarks)
	{
		this.categoryChangeRemarks = categoryChangeRemarks;
	}

	/**
	 * Retrieves patPrevPrimaryCat.
	 * 
	 * @return Value of patPrevPrimaryCat.
	 */
	public String getPatPrevPrimaryCat()
	{
		return patPrevPrimaryCat;
	}

	/**
	 * Sets patPrevPrimaryCat.
	 * 
	 * @param patPrevPrimaryCat
	 */
	public void setPatPrevPrimaryCat(String patPrevPrimaryCat)
	{
		this.patPrevPrimaryCat = patPrevPrimaryCat;
	}

	/**
	 * Retrieves patPrevPrimaryCatCode.
	 * 
	 * @return Value of patPrevPrimaryCatCode.
	 */
	public String getPatPrevPrimaryCatCode()
	{
		return patPrevPrimaryCatCode;
	}

	/**
	 * Sets patPrevPrimaryCatCode.
	 * 
	 * @param patPrevPrimaryCatCode
	 */
	public void setPatPrevPrimaryCatCode(String patPrevPrimaryCatCode)
	{
		this.patPrevPrimaryCatCode = patPrevPrimaryCatCode;
	}

	/**
	 * Retrieves patPrevSecondaryCat.
	 * 
	 * @return Value of patPrevSecondaryCat.
	 */
	public String getPatPrevSecondaryCat()
	{
		return patPrevSecondaryCat;
	}

	/**
	 * Sets patPrevSecondaryCat.
	 * 
	 * @param patPrevSecondaryCat
	 */
	public void setPatPrevSecondaryCat(String patPrevSecondaryCat)
	{
		this.patPrevSecondaryCat = patPrevSecondaryCat;
	}

	/**
	 * Retrieves patPrevSecondaryCatCode.
	 * 
	 * @return Value of patPrevSecondaryCatCode.
	 */
	public String getPatPrevSecondaryCatCode()
	{
		return patPrevSecondaryCatCode;
	}

	/**
	 * Sets patPrevSecondaryCatCode.
	 * 
	 * @param patPrevSecondaryCatCode
	 */
	public void setPatPrevSecondaryCatCode(String patPrevSecondaryCatCode)
	{
		this.patPrevSecondaryCatCode = patPrevSecondaryCatCode;
	}

	/**
	 * Retrieves patNewPrimaryCat.
	 * 
	 * @return Value of patNewPrimaryCat.
	 */
	public String getPatNewPrimaryCat()
	{
		return patNewPrimaryCat;
	}

	/**
	 * Sets patNewPrimaryCat.
	 * 
	 * @param patNewPrimaryCat
	 */
	public void setPatNewPrimaryCat(String patNewPrimaryCat)
	{
		this.patNewPrimaryCat = patNewPrimaryCat;
	}

	/**
	 * Retrieves patNewPrimaryCatCode.
	 * 
	 * @return Value of patNewPrimaryCatCode.
	 */
	public String getPatNewPrimaryCatCode()
	{
		return patNewPrimaryCatCode;
	}

	/**
	 * Sets patNewPrimaryCatCode.
	 * 
	 * @param patNewPrimaryCatCode
	 */
	public void setPatNewPrimaryCatCode(String patNewPrimaryCatCode)
	{
		this.patNewPrimaryCatCode = patNewPrimaryCatCode;
	}

	/**
	 * Retrieves patNewSecondaryCat.
	 * 
	 * @return Value of patNewSecondaryCat.
	 */
	public String getPatNewSecondaryCat()
	{
		return patNewSecondaryCat;
	}

	/**
	 * Sets patNewSecondaryCat.
	 * 
	 * @param patNewSecondaryCat
	 */
	public void setPatNewSecondaryCat(String patNewSecondaryCat)
	{
		this.patNewSecondaryCat = patNewSecondaryCat;
	}

	/**
	 * Retrieves patNewSecondaryCatCode.
	 * 
	 * @return Value of patNewSecondaryCatCode.
	 */
	public String getPatNewSecondaryCatCode()
	{
		return patNewSecondaryCatCode;
	}

	/**
	 * Sets patNewSecondaryCatCode.
	 * 
	 * @param patNewSecondaryCatCode
	 */
	public void setPatNewSecondaryCatCode(String patNewSecondaryCatCode)
	{
		this.patNewSecondaryCatCode = patNewSecondaryCatCode;
	}

	/**
	 * Retrieves systemIPAddress.
	 * 
	 * @return Value of systemIPAddress.
	 */
	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}

	/**
	 * Sets systemIPAddress.
	 * 
	 * @param systemIPAddress
	 */
	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
	}

}
