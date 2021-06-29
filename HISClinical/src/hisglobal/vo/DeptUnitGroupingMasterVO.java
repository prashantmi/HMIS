package hisglobal.vo;

public class DeptUnitGroupingMasterVO extends ValueObject
{
	private String fromDeptCode;
	private String toDeptCode;
	private String fromDeptUnitCode;
	private String toDeptUnitCode;
	private String fromDeptName;
	private String toDeptName;
	private String fromDeptUnitName;
	private String toDeptUnitName;
	private String groupingType;
	private String groupingValidity;
	private String isValid;
	private String seatID;
	private String entryDate;

	public String getFromDeptCode()
	{
		return fromDeptCode;
	}

	public void setFromDeptCode(String fromDeptCode)
	{
		this.fromDeptCode = fromDeptCode;
	}

	public String getToDeptCode()
	{
		return toDeptCode;
	}

	public void setToDeptCode(String toDeptCode)
	{
		this.toDeptCode = toDeptCode;
	}

	public String getFromDeptUnitCode()
	{
		return fromDeptUnitCode;
	}

	public void setFromDeptUnitCode(String fromDeptUnitCode)
	{
		this.fromDeptUnitCode = fromDeptUnitCode;
	}

	public String getToDeptUnitCode()
	{
		return toDeptUnitCode;
	}

	public void setToDeptUnitCode(String toDeptUnitCode)
	{
		this.toDeptUnitCode = toDeptUnitCode;
	}

	public String getGroupingType()
	{
		return groupingType;
	}

	public void setGroupingType(String groupingType)
	{
		this.groupingType = groupingType;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getFromDeptName()
	{
		return fromDeptName;
	}

	public void setFromDeptName(String fromDeptName)
	{
		this.fromDeptName = fromDeptName;
	}

	public String getToDeptName()
	{
		return toDeptName;
	}

	public void setToDeptName(String toDeptName)
	{
		this.toDeptName = toDeptName;
	}

	public String getFromDeptUnitName()
	{
		return fromDeptUnitName;
	}

	public void setFromDeptUnitName(String fromDeptUnitName)
	{
		this.fromDeptUnitName = fromDeptUnitName;
	}

	public String getToDeptUnitName()
	{
		return toDeptUnitName;
	}

	public void setToDeptUnitName(String toDeptUnitName)
	{
		this.toDeptUnitName = toDeptUnitName;
	}

	public String getGroupingValidity() {
		return groupingValidity;
	}

	public void setGroupingValidity(String groupingValidity) {
		this.groupingValidity = groupingValidity;
	}
}
