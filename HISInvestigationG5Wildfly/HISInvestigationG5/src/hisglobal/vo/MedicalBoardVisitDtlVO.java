package hisglobal.vo;

public class MedicalBoardVisitDtlVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String visitNo;
	private String reqID;
	private String visitDate;
	private String reason;
	private String boardNo;
	private String isValid;
	private String isReferred;
	private String isInvestigationRaised;
	private String hospitalCode;
	
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
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public String getReqID() {
		return reqID;
	}
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
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
	public String getIsInvestigationRaised() {
		return isInvestigationRaised;
	}
	public void setIsInvestigationRaised(String isInvestigationRaised) {
		this.isInvestigationRaised = isInvestigationRaised;
	}

	
	
}
