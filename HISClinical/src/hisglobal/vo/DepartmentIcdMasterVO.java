package hisglobal.vo;

public class DepartmentIcdMasterVO extends ValueObject
{

	private String departmentCode;
	private String departmentUnitCode;
	private String diseaseCode;
	private String icdSubgroupCode;
	private String icdGroupCode;
	private String seatID;
	private String entryDate;
	private String isValid;
	private String disease;
	private String icdGroup;
	private String icdSubGroup;
	private String parentCode;
	private String hospitalCode;
	private String slNo; 

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

	public String getIcdGroup()
	{
		return icdGroup;
	}

	public void setIcdGroup(String icdGroup)
	{
		this.icdGroup = icdGroup;
	}

	public String getIcdSubGroup()
	{
		return icdSubGroup;
	}

	public void setIcdSubGroup(String icdSubGroup)
	{
		this.icdSubGroup = icdSubGroup;
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

	public String getDiseaseCode()
	{
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode)
	{
		this.diseaseCode = diseaseCode;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIcdGroupCode()
	{
		return icdGroupCode;
	}

	public void setIcdGroupCode(String icdGroupCode)
	{
		this.icdGroupCode = icdGroupCode;
	}

	public String getIcdSubgroupCode()
	{
		return icdSubgroupCode;
	}

	public void setIcdSubgroupCode(String icdSubgroupCode)
	{
		this.icdSubgroupCode = icdSubgroupCode;
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

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

}
