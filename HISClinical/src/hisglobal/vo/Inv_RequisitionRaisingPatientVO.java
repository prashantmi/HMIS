package hisglobal.vo;

public class Inv_RequisitionRaisingPatientVO extends ValueObject{
	private static final long serialVersionUID = -4294089437863920752L;
	private String patCRNo;
	private String patFirstName;
    private String patMiddleName;
	private String patLastName;
    private String patGender;
	private String patGenderCode;
    private String patAge;
    private String patAgeUnit;
    private String patDOB;
    private String isActualDob;
    private String patCategory;
	private String patCategoryCode;
   	private String patHusbandName;
	private String patGuardianName;
	private String patDepartment;
	//patientDtl table
	
	private String patStatus;
	private String patStatusCode;
	
	
	
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}
	public String getPatDepartment() {
		return patDepartment;
	}
	public void setPatDepartment(String patDepartment) {
		this.patDepartment = patDepartment;
	}
	
	
	public String getIsActualDob() {
		return isActualDob;
	}
	public void setIsActualDob(String isActualDob) {
		this.isActualDob = isActualDob;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatAgeUnit() {
		return patAgeUnit;
	}
	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCrNo) {
		this.patCRNo = patCrNo;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getPatHusbandName() {
		return patHusbandName;
	}
	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatCategory() {
		return patCategory;
	}
	public void setPatCategory(String patCategory) {
		this.patCategory = patCategory;
	}
	public String getPatCategoryCode() {
		return patCategoryCode;
	}
	public void setPatCategoryCode(String patCategoryCode) {
		this.patCategoryCode = patCategoryCode;
	}
	public String getPatStatusCode() {
		return patStatusCode;
	}
	public void setPatStatusCode(String patStatusCode) {
		this.patStatusCode = patStatusCode;
	}
	
	
	
}
