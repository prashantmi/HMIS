package hisglobal.vo;

/**
 * ActivityVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class Apt_ActivityVO extends ValueObject
{
	private String actCode;
	private String actName;
	private String isValid;
	private String seatId;

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
	 * Retrieves Activity Name.
	 * 
	 * @return Value of Activity Name.
	 */
	public String getActName()
	{
		return actName;
	}

	/**
	 * Sets Activity Name.
	 * 
	 * @param Activity Name
	 */
	public void setActName(String actName)
	{
		this.actName = actName;
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
