package hisglobal.vo;

/**
 * ActivityVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */

public class Apt_AppointmentParameterVO extends ValueObject
{
	private String actAptCode;
	private String actCode;
	private String para1;
	private String para2;
	private String para3;
	private String para4;
	private String para5;
	private String para6;
	private String para7;
	private String aptDate;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String level;

	public String getLevel()
	{
		return level;
	}

	public void setLevel(String level)
	{
		this.level = level;
	}

	public String getPara5()
	{
		return para5;
	}

	public void setPara5(String para5)
	{
		this.para5 = para5;
	}

	public String getPara6()
	{
		return para6;
	}

	public void setPara6(String para6)
	{
		this.para6 = para6;
	}

	public String getPara7()
	{
		return para7;
	}

	public void setPara7(String para7)
	{
		this.para7 = para7;
	}

	public String getAptDate()
	{
		return aptDate;
	}

	public void setAptDate(String aptDate)
	{
		this.aptDate = aptDate;
	}

	/**
	 * Retrieves Appointment Parameter Code.
	 * 
	 * @return Value of Appointment Parameter Code.
	 */
	public String getActAptCode()
	{
		return actAptCode;
	}

	/**
	 * Set Appointment Parameter Code.
	 * 
	 * @param Appointment Parameter Code
	 */
	public void setActAptCode(String actAptCode)
	{
		this.actAptCode = actAptCode;
	}

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
	 * Retrieves Parameter Code 1.
	 * 
	 * @return Value of Parameter Code 1.
	 */
	public String getPara1()
	{
		return para1;
	}

	/**
	 * Set Parameter Code 1.
	 * 
	 * @param Parameter Code 1
	 */
	public void setPara1(String para1)
	{
		this.para1 = para1;
	}

	/**
	 * Retrieves Parameter Code 2.
	 * 
	 * @return Value of Parameter Code 2.
	 */
	public String getPara2()
	{
		return para2;
	}

	/**
	 * Set Parameter Code 2.
	 * 
	 * @param Parameter Code 2
	 */
	public void setPara2(String para2)
	{
		this.para2 = para2;
	}

	/**
	 * Retrieves Parameter Code 3.
	 * 
	 * @return Value of Parameter Code 3.
	 */
	public String getPara3()
	{
		return para3;
	}

	/**
	 * Set Parameter Code 3.
	 * 
	 * @param Parameter Code 3
	 */
	public void setPara3(String para3)
	{
		this.para3 = para3;
	}

	/**
	 * Retrieves Parameter Code 4.
	 * 
	 * @return Value of Parameter Code 4.
	 */
	public String getPara4()
	{
		return para4;
	}

	/**
	 * Set Parameter Code 4.
	 * 
	 * @param Parameter Code 4
	 */
	public void setPara4(String para4)
	{
		this.para4 = para4;
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
