package opd.transaction.controller.fb;

import registration.controller.fb.CRNoFB;

public class OpdConsultationFB extends CRNoFB{
	
	private String mailRequestId;
	private String mailParentRequestId;
    private String episodeCode;//for HRGNUM_EPISODE_NO
    private String episodeVisitNo;//for HRGNUM_VISIT_NO
    private String subject;
    private String content;
    private String mailType;
    private String fromDoctorCode;
    private String fromDoctor;
    private String fromDoctorSeatId;
    private String toDoctor;
    private String toDoctorCode;
    private String toDoctorSeatId;
    private String sentDate;
    private String receiveDate;
    private String ackStatus;
    private String hmode;
    private String selectedRow[];
    private String selectedProfile;
    private String userType;
    private String toDeptId;
    private String toDocId;

    
    
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	public String getMailRequestId() {
		return mailRequestId;
	}
	public void setMailRequestId(String mailRequestId) {
		this.mailRequestId = mailRequestId;
	}
	public String getMailParentRequestId() {
		return mailParentRequestId;
	}
	public void setMailParentRequestId(String mailParentRequestId) {
		this.mailParentRequestId = mailParentRequestId;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMailType() {
		return mailType;
	}
	public void setMailType(String mailType) {
		this.mailType = mailType;
	}
	public String getFromDoctorCode() {
		return fromDoctorCode;
	}
	public void setFromDoctorCode(String fromDoctorCode) {
		this.fromDoctorCode = fromDoctorCode;
	}
	public String getFromDoctor() {
		return fromDoctor;
	}
	public void setFromDoctor(String fromDoctor) {
		this.fromDoctor = fromDoctor;
	}
	public String getFromDoctorSeatId() {
		return fromDoctorSeatId;
	}
	public void setFromDoctorSeatId(String fromDoctorSeatId) {
		this.fromDoctorSeatId = fromDoctorSeatId;
	}
	public String getToDoctor() {
		return toDoctor;
	}
	public void setToDoctor(String toDoctor) {
		this.toDoctor = toDoctor;
	}
	public String getToDoctorCode() {
		return toDoctorCode;
	}
	public void setToDoctorCode(String toDoctorCode) {
		this.toDoctorCode = toDoctorCode;
	}
	public String getToDoctorSeatId() {
		return toDoctorSeatId;
	}
	public void setToDoctorSeatId(String toDoctorSeatId) {
		this.toDoctorSeatId = toDoctorSeatId;
	}
	public String getSentDate() {
		return sentDate;
	}
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getAckStatus() {
		return ackStatus;
	}
	public void setAckStatus(String ackStatus) {
		this.ackStatus = ackStatus;
	}
	public String [] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String [] selectedRow) {
		this.selectedRow = selectedRow;
	}
	
	
	public void reset() {
		this.setAckStatus("");
		this.setContent("");
		this.setEpisodeCode("");
		this.setEpisodeVisitNo("");
		this.setFromDoctor("");
		this.setFromDoctorCode("");
		this.setToDoctor("");
		this.setToDoctorCode("");
		this.setToDoctorSeatId("");
		this.setMailParentRequestId("");
		this.setMailRequestId("");
		this.setMailType("");
		this.setHmode("");
		this.setSubject("");
	
	}
	public String getSelectedProfile() {
		return selectedProfile;
	}
	public void setSelectedProfile(String selectedProfile) {
		this.selectedProfile = selectedProfile;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getToDeptId() {
		return toDeptId;
	}
	public void setToDeptId(String toDeptId) {
		this.toDeptId = toDeptId;
	}
	public String getToDocId() {
		return toDocId;
	}
	public void setToDocId(String toDocId) {
		this.toDocId = toDocId;
	}

}
