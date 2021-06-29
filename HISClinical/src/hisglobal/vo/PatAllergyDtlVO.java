package hisglobal.vo;

public class PatAllergyDtlVO extends ValueObject
{
	private String patCrNo;
	private String serialNo;
	private String allergyTypeName;
	private String allergyTypeCode;
	private String allergyID;
	private String allergyName;
	private String revokeRemarks;
	private String effectiveFrm;
	private String effectiveTo;
	private String duration;
	private String entryDate;
	private String durationDays;

	
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getAllergyTypeName() {
		return allergyTypeName;
	}
	public void setAllergyTypeName(String allergyTypeName) {
		this.allergyTypeName = allergyTypeName;
	}
	public String getAllergyTypeCode() {
		return allergyTypeCode;
	}
	public void setAllergyTypeCode(String allergyTypeCode) {
		this.allergyTypeCode = allergyTypeCode;
	}
	public String getAllergyID() {
		return allergyID;
	}
	public void setAllergyID(String allergyID) {
		this.allergyID = allergyID;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	public String getRevokeRemarks() {
		return revokeRemarks;
	}
	public void setRevokeRemarks(String revokeRemarks) {
		this.revokeRemarks = revokeRemarks;
	}
	public String getEffectiveFrm() {
		return effectiveFrm;
	}
	public void setEffectiveFrm(String effectiveFrm) {
		this.effectiveFrm = effectiveFrm;
	}
	public String getEffectiveTo() {
		return effectiveTo;
	}
	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	

	public String getDurationDays() {
		return durationDays;
	}
	public void setDurationDays(String durationDays) {
		this.durationDays = durationDays;
	}

	
}
