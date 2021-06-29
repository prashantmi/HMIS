package vo.registration;

import hisglobal.vo.ValueObject;

public class PatientIdVO extends ValueObject{
	
	private String patIdNo;
	private String patDocType;
	private String patCrNo;
	private String patIsAlternateId;
	private String isValid;
	private String entryDate;
	private String hospitalCode;
	private String isGlobal;
	private String memSlNo;

	
	
	public String getPatIdNo() {
		return patIdNo;
	}
	public void setPatIdNo(String patIdNo) {
		this.patIdNo = patIdNo;
	}
	public String getPatDocType() {
		return patDocType;
	}
	public void setPatDocType(String patDocType) {
		this.patDocType = patDocType;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPatIsAlternateId() {
		return patIsAlternateId;
	}
	public void setPatIsAlternateId(String patIsAlternateId) {
		this.patIsAlternateId = patIsAlternateId;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsGlobal() {
		return isGlobal;
	}
	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}
	public String getMemSlNo() {
		return memSlNo;
	}
	public void setMemSlNo(String memSlNo) {
		this.memSlNo = memSlNo;
	}
	
	
}
