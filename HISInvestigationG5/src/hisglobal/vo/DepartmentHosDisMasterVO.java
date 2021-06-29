package hisglobal.vo;

public class DepartmentHosDisMasterVO extends ValueObject
{

	private String departmentCode;
	private String departmentUnitCode;
	private String hospitalDiseaseCode;
	private String seatID;
	private String entryDate;
	private String isValid;
	private String hospitalCode;

	
	private String disease;
	private String parentCode;

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getParentCode()
	{
		return parentCode;
	}

	public void setParentCode(String parentCode)
	{
		this.parentCode = parentCode;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}



	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
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

	public String getDisease()
	{
		return disease;
	}

	public void setDisease(String disease)
	{
		this.disease = disease;
	}


	public String getHospitalDiseaseCode() {
		return hospitalDiseaseCode;
	}

	public void setHospitalDiseaseCode(String hospitalDiseaseCode) {
		this.hospitalDiseaseCode = hospitalDiseaseCode;
	}

}
