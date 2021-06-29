package hisglobal.vo;

public class YellowSlipMonitoringVO extends ValueObject
{
	private String patCrNo;
	private String enterBy;
	private String userName;
	private String minor;
	private String major;
	private String episodeCode;
	private String episodeVisitNo;
	private String monitoringFlag;
	private String monitoringRemarks;
	private String action;
	private String seatId;
	private String systemIPAddress;
	private String entryDate;
	
	
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
	public String getEnterBy() {
		return enterBy;
	}
	public void setEnterBy(String enterBy) {
		this.enterBy = enterBy;
	}
	public String getMonitoringFlag() {
		return monitoringFlag;
	}
	public void setMonitoringFlag(String monitoringFlag) {
		this.monitoringFlag = monitoringFlag;
	}
	public String getMonitoringRemarks() {
		return monitoringRemarks;
	}
	public void setMonitoringRemarks(String monitoringRemarks) {
		this.monitoringRemarks = monitoringRemarks;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getSystemIPAddress() {
		return systemIPAddress;
	}
	public void setSystemIPAddress(String systemIPAddress) {
		this.systemIPAddress = systemIPAddress;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMinor() {
		return minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	

}
