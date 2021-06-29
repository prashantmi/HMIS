package hisglobal.vo;

public class ProfileRestrictedCategoryMasterVO extends ValueObject 
{
	private String patientCategoryCode;
	private String profileType;
	private String hospitalCode;
	private String seatId;
	private String isMajor;
	private String entryDate;
	private String isActive;
	private String serialNo;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String profileTypeModify;
	public String getPatientCategoryCode() {
		return patientCategoryCode;
	}
	public void setPatientCategoryCode(String patientCategoryCode) {
		this.patientCategoryCode = patientCategoryCode;
	}
	public String getProfileType() {
		return profileType;
	}
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getIsMajor() {
		return isMajor;
	}
	public void setIsMajor(String isMajor) {
		this.isMajor = isMajor;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedSeatId() {
		return lastModifiedSeatId;
	}
	public void setLastModifiedSeatId(String lastModifiedSeatId) {
		this.lastModifiedSeatId = lastModifiedSeatId;
	}
	public String getProfileTypeModify() {
		return profileTypeModify;
	}
	public void setProfileTypeModify(String profileTypeModify) {
		this.profileTypeModify = profileTypeModify;
	}
}
