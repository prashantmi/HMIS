package hisglobal.vo;

public class ConsultationDtlVO extends ValueObject
{
	private String patCrNo;
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
	private String departmentUnitCode;

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
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

	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
}
