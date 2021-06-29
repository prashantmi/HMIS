package hisglobal.vo;

/**
 * DuplicateRenewVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class DuplicateRenewVO extends ValueObject
{
	private String patCrNo;
	private String duplicateRenewDate;
	private String duplicateRenewCost;
	private String duplicateRenewRemarks;
	private String duplicateRenewFlag;
	private String departmentList;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String department;
	private String departmentCode;
	private String departmentUnit;
	private String departmentUnitCode;
	private String systemIPAddress;

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

	/**
	 * Retrieves departmentList.
	 * 
	 * @return Value of departmentList.
	 */
	public String getDepartmentList()
	{
		return departmentList;
	}

	/**
	 * Sets departmentList.
	 * 
	 * @param departmentList
	 */
	public void setDepartmentList(String departmentList)
	{
		this.departmentList = departmentList;
	}

	/**
	 * Retrieves duplicateRenewCost.
	 * 
	 * @return Value of duplicateRenewCost.
	 */
	public String getDuplicateRenewCost()
	{
		return duplicateRenewCost;
	}

	/**
	 * Sets duplicateRenewCost.
	 * 
	 * @param duplicateRenewCost
	 */
	public void setDuplicateRenewCost(String duplicateRenewCost)
	{
		this.duplicateRenewCost = duplicateRenewCost;
	}

	/**
	 * Retrieves duplicateRenewDate.
	 * 
	 * @return Value of duplicateRenewDate.
	 */
	public String getDuplicateRenewDate()
	{
		return duplicateRenewDate;
	}

	/**
	 * Sets duplicateRenewDate.
	 * 
	 * @param duplicateRenewDate
	 */
	public void setDuplicateRenewDate(String duplicateRenewDate)
	{
		this.duplicateRenewDate = duplicateRenewDate;
	}

	/**
	 * Retrieves duplicateRenewFlag.
	 * 
	 * @return Value of duplicateRenewFlag.
	 */
	public String getDuplicateRenewFlag()
	{
		return duplicateRenewFlag;
	}

	/**
	 * Sets duplicateRenewFlag.
	 * 
	 * @param duplicateRenewFlag
	 */
	public void setDuplicateRenewFlag(String duplicateRenewFlag)
	{
		this.duplicateRenewFlag = duplicateRenewFlag;
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
	 * Retrieves duplicateRenewRemarks.
	 * 
	 * @return Value of duplicateRenewRemarks.
	 */
	public String getDuplicateRenewRemarks()
	{
		return duplicateRenewRemarks;
	}

	/**
	 * Sets duplicateRenewRemarks.
	 * 
	 * @param duplicateRenewRemarks
	 */
	public void setDuplicateRenewRemarks(String duplicateRenewRemarks)
	{
		this.duplicateRenewRemarks = duplicateRenewRemarks;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getDepartmentUnit()
	{
		return departmentUnit;
	}

	public void setDepartmentUnit(String departmentUnit)
	{
		this.departmentUnit = departmentUnit;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

}
