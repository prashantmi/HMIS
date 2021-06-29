package hisglobal.vo;

/**
 * ParameterVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */

public class Apt_ParameterVO extends ValueObject
{
	private String paraCode;
	private String paraName;
	private String tableName;
	private String columnList;
	private String isValid;
	private String seatId;
	private String entryDate;

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
	 * Retrieves Parameter Name.
	 * 
	 * @return Value of Parameter Name.
	 */
	public String getParaName()
	{
		return paraName;
	}

	/**
	 * Sets Parameter Name.
	 * 
	 * @param Parameter Name
	 */
	public void setParaName(String paraName)
	{
		this.paraName = paraName;
	}

	/**
	 * Retrieves Table Name.
	 * 
	 * @return Value of Table Name.
	 */
	public String getTableName()
	{
		return tableName;
	}

	/**
	 * Sets Table Name.
	 * 
	 * @param Table Name
	 */
	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	/**
	 * Retrieves Column List.
	 * 
	 * @return Value of Column List.
	 */
	public String getColumnList()
	{
		return columnList;
	}

	/**
	 * Sets Column List.
	 * 
	 * @param Column List
	 */
	public void setColumnList(String columnList)
	{
		this.columnList = columnList;
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
