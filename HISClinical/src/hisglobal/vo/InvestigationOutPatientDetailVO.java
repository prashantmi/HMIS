package hisglobal.vo;

public class InvestigationOutPatientDetailVO extends ValueObject{
	String requisitionNo;
	String patFirstName;
	String patMiddleName;
	String patLastName;
	String patDOB;
	String patIsActualDOB=registration.RegistrationConfig.IS_ACTUAL_DOB_FALSE;
	String patGenderCode;
	String referredByHospital;
	String referredByHospitalCode;

	public String getReferredByHospitalCode() {
		return referredByHospitalCode;
	}
	public void setReferredByHospitalCode(String referredByHospitalCode) {
		this.referredByHospitalCode = referredByHospitalCode;
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
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	
	public String getReferredByHospital() {
		return referredByHospital;
	}
	public void setReferredByHospital(String referredByHospital) {
		this.referredByHospital = referredByHospital;
	}
	public String getPatIsActualDOB() {
		return patIsActualDOB;
	}
	public void setPatIsActualDOB(String patIsActualDOB) {
		this.patIsActualDOB = patIsActualDOB;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getRequisitionNo() {
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}

}
