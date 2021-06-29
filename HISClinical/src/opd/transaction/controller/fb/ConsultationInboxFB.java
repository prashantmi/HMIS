package opd.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class ConsultationInboxFB extends ActionForm
{
	private String mailRequestId;
	private String mailParentRequestId;
	private String episodeCode;// for HRGNUM_EPISODE_NO
	private String episodeVisitNo;// for HRGNUM_VISIT_NO
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
	private String mode;
	private String[] selectedMailId;
	private String selectedPatCrNo;
	private String profileId;
	private String mailPatCrNo;

	public String getMailPatCrNo()
	{
		return mailPatCrNo;
	}

	public void setMailPatCrNo(String mailPatCrNo)
	{
		this.mailPatCrNo = mailPatCrNo;
	}

	public String getProfileId()
	{
		return profileId;
	}

	public void setProfileId(String profileId)
	{
		this.profileId = profileId;
	}

	public String getSelectedPatCrNo()
	{
		return selectedPatCrNo;
	}

	public void setSelectedPatCrNo(String selectedPatCrNo)
	{
		this.selectedPatCrNo = selectedPatCrNo;
	}

	public String getMailRequestId()
	{
		return mailRequestId;
	}

	public void setMailRequestId(String mailRequestId)
	{
		this.mailRequestId = mailRequestId;
	}

	public String getMailParentRequestId()
	{
		return mailParentRequestId;
	}

	public void setMailParentRequestId(String mailParentRequestId)
	{
		this.mailParentRequestId = mailParentRequestId;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getMailType()
	{
		return mailType;
	}

	public void setMailType(String mailType)
	{
		this.mailType = mailType;
	}

	public String getFromDoctorCode()
	{
		return fromDoctorCode;
	}

	public void setFromDoctorCode(String fromDoctorCode)
	{
		this.fromDoctorCode = fromDoctorCode;
	}

	public String getFromDoctor()
	{
		return fromDoctor;
	}

	public void setFromDoctor(String fromDoctor)
	{
		this.fromDoctor = fromDoctor;
	}

	public String getFromDoctorSeatId()
	{
		return fromDoctorSeatId;
	}

	public void setFromDoctorSeatId(String fromDoctorSeatId)
	{
		this.fromDoctorSeatId = fromDoctorSeatId;
	}

	public String getToDoctor()
	{
		return toDoctor;
	}

	public void setToDoctor(String toDoctor)
	{
		this.toDoctor = toDoctor;
	}

	public String getToDoctorCode()
	{
		return toDoctorCode;
	}

	public void setToDoctorCode(String toDoctorCode)
	{
		this.toDoctorCode = toDoctorCode;
	}

	public String getToDoctorSeatId()
	{
		return toDoctorSeatId;
	}

	public void setToDoctorSeatId(String toDoctorSeatId)
	{
		this.toDoctorSeatId = toDoctorSeatId;
	}

	public String getSentDate()
	{
		return sentDate;
	}

	public void setSentDate(String sentDate)
	{
		this.sentDate = sentDate;
	}

	public String getReceiveDate()
	{
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate)
	{
		this.receiveDate = receiveDate;
	}

	public String getAckStatus()
	{
		return ackStatus;
	}

	public void setAckStatus(String ackStatus)
	{
		this.ackStatus = ackStatus;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public void reset()
	{
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
		// this.setPatCrNo("");
		this.setMailPatCrNo("");
		this.setSelectedMailId(new String[]{});		
		this.setProfileId("");
	}

	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}

	public String[] getSelectedMailId()
	{
		return selectedMailId;
	}

	public void setSelectedMailId(String[] selectedMailId)
	{
		this.selectedMailId = selectedMailId;
	}
}
