package hisglobal.vo;

/**
 * SecondaryCategoryChangeVO is the class that specifies getters and setters for all the identifiers which are used for
 * retrieving and inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class SecondaryCategoryChangeVO extends ValueObject
{

	private String patCrNo;
	private String serialNo;
	private String patPrimaryCat;
	private String patPrimaryCatCode;
	private String patPrevSecondaryCat;
	private String patPrevSecondaryCatCode;
	private String patNewSecondaryCat;
	private String patNewSecondaryCatCode;
	private String validUpto;
	private String remarks;
	private String entryDate;
	private String isValid;
	private String seatId;
	private String systemIPAddress;
	private String episodeCode;
	private String expiryDate;
	private String episodeVisitNo;
	private String isIpdFlag;

	public String getIsIpdFlag() {
		return isIpdFlag;
	}

	public void setIsIpdFlag(String isIpdFlag) {
		this.isIpdFlag = isIpdFlag;
	}

	/**
	 * Retrieves patPrimaryCat.
	 * 
	 * @return Value of patPrimaryCat.
	 */
	public String getPatPrimaryCat()
	{
		return patPrimaryCat;
	}

	/**
	 * Sets patPrimaryCat.
	 * 
	 * @param patPrimaryCat
	 */
	public void setPatPrimaryCat(String patPrimaryCat)
	{
		this.patPrimaryCat = patPrimaryCat;
	}

	/**
	 * Retrieves patPrimaryCatCode.
	 * 
	 * @return Value of patPrimaryCatCode.
	 */
	public String getPatPrimaryCatCode()
	{
		return patPrimaryCatCode;
	}

	/**
	 * Sets patPrimaryCatCode.
	 * 
	 * @param patPrimaryCatCode
	 */
	public void setPatPrimaryCatCode(String patPrimaryCatCode)
	{
		this.patPrimaryCatCode = patPrimaryCatCode;
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

	public String getValidUpto()
	{
		return validUpto;
	}

	public void setValidUpto(String validUpto)
	{
		this.validUpto = validUpto;
	}

	/**
	 * Retrieves remarks.
	 * 
	 * @return Value of remarks.
	 */
	public String getRemarks()
	{
		return remarks;
	}

	/**
	 * Sets remarks.
	 * 
	 * @param remarks
	 */
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
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

	public String getExpiryDate()
	{
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}

}
