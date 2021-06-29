package hisglobal.vo;

public class PatDischargeReqDtlVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String slNo;
	private String patAdmNo;
	private String nextVisitDate;
	private String remarks;
	private String requestType;
	private String dischargeStatus;
	private String nextVisitDays;
	private String isDead;
	private String adviceBy;
	
	
	public String getIsDead() {
		return isDead;
	}
	public void setIsDead(String isDead) {
		this.isDead = isDead;
	}
	public String getDischargeStatus() {
		return dischargeStatus;
	}
	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}
	public String getNextVisitDays() {
		return nextVisitDays;
	}
	public void setNextVisitDays(String nextVisitDays) {
		this.nextVisitDays = nextVisitDays;
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
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getNextVisitDate() {
		return nextVisitDate;
	}
	public void setNextVisitDate(String nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getAdviceBy() {
		return adviceBy;
	}
	public void setAdviceBy(String adviceBy) {
		this.adviceBy = adviceBy;
	}
}
