package hisglobal.vo;

public class MedicalBoardReferMappingVO extends ValueObject
{
	private String certificateTypeID;
	private String referType;
	private String departmentCode;
	private String department;
	private String departmentUnitCode;
	private String departmentUnit;
	private String referRemarks;
	private String isOptional;
	private String isValid;
	private String hospitalCode;
	private String isReferred;
	private String referVisitDate;
	
	public String getCertificateTypeID() {
		return certificateTypeID;
	}
	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}
	public String getReferType() {
		return referType;
	}
	public void setReferType(String referType) {
		this.referType = referType;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getDepartmentUnit() {
		return departmentUnit;
	}
	public void setDepartmentUnit(String departmentUnit) {
		this.departmentUnit = departmentUnit;
	}
	public String getReferRemarks() {
		return referRemarks;
	}
	public void setReferRemarks(String referRemarks) {
		this.referRemarks = referRemarks;
	}
	public String getIsOptional() {
		return isOptional;
	}
	public void setIsOptional(String isOptional) {
		this.isOptional = isOptional;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsReferred() {
		return isReferred;
	}
	public void setIsReferred(String isReferred) {
		this.isReferred = isReferred;
	}
	public String getReferVisitDate() {
		return referVisitDate;
	}
	public void setReferVisitDate(String referVisitDate) {
		this.referVisitDate = referVisitDate;
	}
	
	
	
	
}
