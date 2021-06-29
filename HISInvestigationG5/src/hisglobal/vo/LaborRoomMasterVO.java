package hisglobal.vo;

public class LaborRoomMasterVO extends ValueObject
{
	private String laborRoomId;
	private String slNo;
	private String hospitalCode;
	private String laborRoomName;
	private String isValid;
	private String laborRoomDescription;
	private String seatID;
	private String laborRoomShortName;
	private String departmentCode;
	private String entryDate;
	private String lastModifyDate;
	private String headOfDepartmentEmpId;
	private String lastModifiedSeatID;

	public String getLaborRoomId()
	{
		return laborRoomId;
	}

	public void setLaborRoomId(String laborRoomId)
	{
		this.laborRoomId = laborRoomId;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getLaborRoomName()
	{
		return laborRoomName;
	}

	public void setLaborRoomName(String laborRoomName)
	{
		this.laborRoomName = laborRoomName;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getLaborRoomDescription()
	{
		return laborRoomDescription;
	}

	public void setLaborRoomDescription(String laborRoomDescription)
	{
		this.laborRoomDescription = laborRoomDescription;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getLaborRoomShortName()
	{
		return laborRoomShortName;
	}

	public void setLaborRoomShortName(String laborRoomShortName)
	{
		this.laborRoomShortName = laborRoomShortName;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getLastModifyDate()
	{
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate)
	{
		this.lastModifyDate = lastModifyDate;
	}

	public String getHeadOfDepartmentEmpId()
	{
		return headOfDepartmentEmpId;
	}

	public void setHeadOfDepartmentEmpId(String headOfDepartmentEmpId)
	{
		this.headOfDepartmentEmpId = headOfDepartmentEmpId;
	}

	public String getLastModifiedSeatID()
	{
		return lastModifiedSeatID;
	}

	public void setLastModifiedSeatID(String lastModifiedSeatID)
	{
		this.lastModifiedSeatID = lastModifiedSeatID;
	}
}
