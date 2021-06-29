package hisglobal.vo;

public class ConsultationProfileDtlVO extends ValueObject
{
	private String profileId;
	private String mailRequestId;
	private String profileHeader;
	
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getMailRequestId() {
		return mailRequestId;
	}
	public void setMailRequestId(String mailRequestId) {
		this.mailRequestId = mailRequestId;
	}
	public String getProfileHeader() {
		return profileHeader;
	}
	public void setProfileHeader(String profileHeader) {
		this.profileHeader = profileHeader;
	}
	
	
}
