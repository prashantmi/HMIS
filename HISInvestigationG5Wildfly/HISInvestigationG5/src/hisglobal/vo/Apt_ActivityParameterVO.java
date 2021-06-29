package hisglobal.vo;

/**
 * ActivityVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */

public class Apt_ActivityParameterVO extends ValueObject
{
	private String actCode;
	private String paraCode;
	private String paraLevel;
	private String isValid;
	private String seatId;
	private String entryDate;

	/**
	 * Retrieves Activity Code.
	 * 
	 * @return Value of Activity Code.
	 */
	public String getActCode()
	{
		return actCode;
	}

	/**
	 * Set Activity Code.
	 * 
	 * @param Acivity Code
	 */
	public void setActCode(String actCode)
	{
		this.actCode = actCode;
	}

	/**
	 * Retrieves Parameter Code.
	 * 
	 * @return Value of Parameter Code.
	 */
	public String getParaCode()
	{
		return paraCode;
	}

	/**
	 * Set Parameter Code.
	 * 
	 * @param Parameter Code
	 */
	public void setParaCode(String paraCode)
	{
		this.paraCode = paraCode;
	}

	/**
	 * Retrieves Parameter Level.
	 * 
	 * @return Value of Parameter Level.
	 */
	public String getParaLevel()
	{
		return paraLevel;
	}

	/**
	 * Sets Parameter Level.
	 * 
	 * @param Parameter Level
	 */
	public void setParaLevel(String paraLevel)
	{
		this.paraLevel = paraLevel;
	}

	/**
	 * Retrieves Entry Date.
	 * 
	 * @return Entry Date.
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets Entry Date.
	 * 
	 * @param Entry Date
	 */
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * Retrieves Value of IsValid.
	 * 
	 * @return Value of IsValid.
	 */
	public String getIsValid()
	{
		return isValid;
	}

	/**
	 * Sets IsValid.
	 * 
	 * @param IsValid
	 */
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	/**
	 * Retrieves Seat Id.
	 * 
	 * @return Value of Seat Id.
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * Sets Seat Id.
	 * 
	 * @param SeatId
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}
}
