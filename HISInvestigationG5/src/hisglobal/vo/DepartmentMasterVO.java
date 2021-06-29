package hisglobal.vo;

public class DepartmentMasterVO extends ValueObject
{

	private String departmentCode;
	private String department;
	private String hodCode;
	private String deptShortName;
	private String locationCode;
	private String seatID;
	private String entryDate;
	private String isValid;
	private String effectiveDate;

	private String hl7Code;
	private String departmentType;

	private String departmentSlNo;
	private String controls;
	private String effectiveFrom;
	private String effectiveTo;
	private String remarks;
	private String choice;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getLastModifiedSeatID()
	{
		return lastModifiedSeatID;
	}

	public void setLastModifiedSeatID(String lastModifiedSeatID)
	{
		this.lastModifiedSeatID = lastModifiedSeatID;
	}

	public String getLastModifyDate()
	{
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate)
	{
		this.lastModifyDate = lastModifyDate;
	}

	public String getChoice()
	{
		return choice;
	}

	public void setChoice(String choice)
	{
		this.choice = choice;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getDepartmentSlNo()
	{
		return departmentSlNo;
	}

	public void setDepartmentSlNo(String departmentSlNo)
	{
		this.departmentSlNo = departmentSlNo;
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

	public String getDepartmentType()
	{
		return departmentType;
	}

	public void setDepartmentType(String departmentType)
	{
		this.departmentType = departmentType;
	}

	public String getDeptShortName()
	{
		return deptShortName;
	}

	public void setDeptShortName(String deptShortName)
	{
		this.deptShortName = deptShortName;
	}

	public String getEffectiveDate()
	{
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getHl7Code()
	{
		return hl7Code;
	}

	public void setHl7Code(String hl7Code)
	{
		this.hl7Code = hl7Code;
	}

	public String getHodCode()
	{
		return hodCode;
	}

	public void setHodCode(String hodCode)
	{
		this.hodCode = hodCode;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getLocationCode()
	{
		return locationCode;
	}

	public void setLocationCode(String locationCode)
	{
		this.locationCode = locationCode;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public void setControls(String controls)
	{
		this.controls = controls;
	}

	public String getControls()
	{
		return controls;
	}

	public String getEffectiveFrom()
	{
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom)
	{
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo()
	{
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo)
	{
		this.effectiveTo = effectiveTo;
	}

}
