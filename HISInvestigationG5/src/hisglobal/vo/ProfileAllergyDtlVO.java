package hisglobal.vo;

public class ProfileAllergyDtlVO extends ValueObject
{
	private String profileId;
	private String allergyName;
	private String allergyTypeCode;
	private String allergyTypeName;
	private String duration;
	private String allergyID;
	private String entryDate;
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	public String getAllergyTypeCode() {
		return allergyTypeCode;
	}
	public void setAllergyTypeCode(String allergyTypeCode) {
		this.allergyTypeCode = allergyTypeCode;
	}
	public String getAllergyTypeName() {
		return allergyTypeName;
	}
	public void setAllergyTypeName(String allergyTypeName) {
		this.allergyTypeName = allergyTypeName;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getAllergyID() {
		return allergyID;
	}
	public void setAllergyID(String allergyID) {
		this.allergyID = allergyID;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
}
