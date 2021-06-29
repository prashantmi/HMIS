package hisglobal.vo;

/**
 * PrimaryCategoryChangeVO is the class that specifies getters and setters for all the identifiers which are used for
 * retrieving and inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class PrimaryCategoryChangeVO extends ValueObject
{

	private String patCrNo;
	private String serialNo;
	private String patPrevPrimaryCat;
	private String patPrevPrimaryCatCode;
	private String patNewPrimaryCat;
	private String patNewPrimaryCatCode;
	private String effectiveFrom;
	private String effectiveTo;
	private String cardNo;
	private String validUpto;
	private String issuingAuthority;
	private String verificationDocumentId;
	private String remarks;
	private String entryDate;
	private String isValid;
	private String seatId;
	private String systemIPAddress;

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

	public String getEffectiveTo()
	{
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo)
	{
		this.effectiveTo = effectiveTo;
	}

	public String getEffectiveFrom()
	{
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom)
	{
		this.effectiveFrom = effectiveFrom;
	}

	public String getCardNo()
	{
		return cardNo;
	}

	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}

	public String getValidUpto()
	{
		return validUpto;
	}

	public void setValidUpto(String validUpto)
	{
		this.validUpto = validUpto;
	}

	public String getIssuingAuthority()
	{
		return issuingAuthority;
	}

	public void setIssuingAuthority(String issuingAuthority)
	{
		this.issuingAuthority = issuingAuthority;
	}

	public String getVerificationDocumentId()
	{
		return verificationDocumentId;
	}

	public void setVerificationDocumentId(String verificationDocumentId)
	{
		this.verificationDocumentId = verificationDocumentId;
	}

}
