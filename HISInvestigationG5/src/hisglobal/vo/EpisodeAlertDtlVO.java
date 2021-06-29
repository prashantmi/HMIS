package hisglobal.vo;

public class EpisodeAlertDtlVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String patAlertId;
	private String admissionNo;
	private String remarks;
	private String durationDate;
	private String alertName;
	private String isRevoked;
	private String entryDate;
	private String seatId;
	private String isValid;
	
	
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
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
	public String getPatAlertId() {
		return patAlertId;
	}
	public void setPatAlertId(String patAlertId) {
		this.patAlertId = patAlertId;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDurationDate() {
		return durationDate;
	}
	public void setDurationDate(String durationDate) {
		this.durationDate = durationDate;
	}
	public String getAlertName() {
		return alertName;
	}
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}
	public String getIsRevoked() {
		return isRevoked;
	}
	public void setIsRevoked(String isRevoked) {
		this.isRevoked = isRevoked;
	}
}
