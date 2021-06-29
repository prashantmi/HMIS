package hisglobal.vo;

public class EpisodeAttendantDetailVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String slNo;
	private String patRelativeId;
	private String attendantReasonId;
	private String empNo;
	
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
	public String getPatRelativeId() {
		return patRelativeId;
	}
	public void setPatRelativeId(String patRelativeId) {
		this.patRelativeId = patRelativeId;
	}
	public String getAttendantReasonId() {
		return attendantReasonId;
	}
	public void setAttendantReasonId(String attendantReasonId) {
		this.attendantReasonId = attendantReasonId;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	
}
