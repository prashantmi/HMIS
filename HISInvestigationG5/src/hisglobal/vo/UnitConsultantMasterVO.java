package hisglobal.vo;

public class UnitConsultantMasterVO extends ValueObject
{

	private String departmentUnitCode;
	private String empNo;
	private String entryDate;
	private String seatID;
	private String locationCode;
	private String isValid;
	private String isHOU;
	private String employeeFullName;
	private String hierarchyLevel;
	private String hospitalCode;

	public UnitConsultantMasterVO()
	{
		// TODO Auto-generated constructor stub
	}

	public UnitConsultantMasterVO(String _duCode, String _eCode)
	{
		this.departmentUnitCode = _duCode;
		this.empNo = _eCode;
	}

	public boolean equals(Object arg0)
	{
		if (arg0 == this) return true;
		if (!(arg0 instanceof UnitConsultantMasterVO)) return false;
		UnitConsultantMasterVO vo = (UnitConsultantMasterVO) arg0;
		return (vo.getDepartmentUnitCode().equalsIgnoreCase(this.departmentUnitCode) && vo.getEmpNo().equalsIgnoreCase(this.empNo));

	}

	public String getEmployeeFullName()
	{
		return employeeFullName;
	}

	public void setEmployeeFullName(String employeeFullName)
	{
		this.employeeFullName = employeeFullName;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getEmpNo()
	{
		return empNo;
	}

	public void setEmpNo(String empNo)
	{
		System.out.println("in set: " + empNo);
		this.empNo = empNo;
		System.out.println("in set: " + this.empNo);
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIsHOU()
	{
		return isHOU;
	}

	public void setIsHOU(String isHOU)
	{
		this.isHOU = isHOU;
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

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(String hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}

}//end of class
