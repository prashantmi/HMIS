package hisglobal.vo;

public class EpisodeAllergySitedDtlVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String serialNo;
	private String allergiesCode;
	private String patAdmNo;
	private String reasonCode;		//Allergy ID
	private String allergySiteID;
	
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getAllergiesCode() {
		return allergiesCode;
	}
	public void setAllergiesCode(String allergiesCode) {
		this.allergiesCode = allergiesCode;
	}
	
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getAllergySiteID() {
		return allergySiteID;
	}
	public void setAllergySiteID(String allergySiteID) {
		this.allergySiteID = allergySiteID;
	}
	
	
}
