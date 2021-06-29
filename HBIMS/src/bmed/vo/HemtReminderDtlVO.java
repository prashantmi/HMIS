package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class HemtReminderDtlVO 
{
	private String strMode;
	
	private String strReqId;
	private String strHospitalCode;
	private String strReminderId;
	private String strReqType;
	private String strReminderById;
	private String strReminderRemarks; 
	private String strReminderSentDate;
	private String strReminderReplyId;
	private String strReplyRemarks;
	private String strReminderReplyDate;
	private String strReminderStatus;
	private String strEntryDate; 
	private String strIsValid;
	private String strSeatId;
	private String remindertype;
	
	private WebRowSet wrsData;

	public String getStrMode() {
		return strMode;
	}

	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}

	public String getStrReqId() {
		return strReqId;
	}

	public void setStrReqId(String strReqId) {
		this.strReqId = strReqId;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrReminderId() {
		return strReminderId;
	}

	public void setStrReminderId(String strReminderId) {
		this.strReminderId = strReminderId;
	}

	public String getStrReqType() {
		return strReqType;
	}

	public void setStrReqType(String strReqType) {
		this.strReqType = strReqType;
	}

	public String getStrReminderById() {
		return strReminderById;
	}

	public void setStrReminderById(String strReminderById) {
		this.strReminderById = strReminderById;
	}

	public String getStrReminderRemarks() {
		return strReminderRemarks;
	}

	public void setStrReminderRemarks(String strReminderRemarks) {
		this.strReminderRemarks = strReminderRemarks;
	}

	public String getStrReminderSentDate() {
		return strReminderSentDate;
	}

	public void setStrReminderSentDate(String strReminderSentDate) {
		this.strReminderSentDate = strReminderSentDate;
	}

	public String getStrReminderReplyId() {
		return strReminderReplyId;
	}

	public void setStrReminderReplyId(String strReminderReplyId) {
		this.strReminderReplyId = strReminderReplyId;
	}

	public String getStrReplyRemarks() {
		return strReplyRemarks;
	}

	public void setStrReplyRemarks(String strReplyRemarks) {
		this.strReplyRemarks = strReplyRemarks;
	}

	public String getStrReminderReplyDate() {
		return strReminderReplyDate;
	}

	public void setStrReminderReplyDate(String strReminderReplyDate) {
		this.strReminderReplyDate = strReminderReplyDate;
	}

	public String getStrReminderStatus() {
		return strReminderStatus;
	}

	public void setStrReminderStatus(String strReminderStatus) {
		this.strReminderStatus = strReminderStatus;
	}

	public String getStrEntryDate() {
		return strEntryDate;
	}

	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public WebRowSet getWrsData() {
		return wrsData;
	}

	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
	}

	public String getRemindertype() {
		return remindertype;
	}

	public void setRemindertype(String remindertype) {
		this.remindertype = remindertype;
	}
	
}
