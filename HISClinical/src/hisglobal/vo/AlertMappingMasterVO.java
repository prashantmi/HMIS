package hisglobal.vo;

public class AlertMappingMasterVO extends ValueObject
{
	private String roleId;
	private String userSeatID;
	private String entryMode;
	private String alertCode;
	private String hospitalCode;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	
	private String extuserID;
	private String slNo;
	private String applicationAlert;
	private String smsAlert;
	private String faxAlert;
	private String emailAlert;
	private String pagerAlert;
	private String alertMappingMode;

	public String getAlertMappingMode() {
		return alertMappingMode;
	}

	public void setAlertMappingMode(String alertMappingMode) {
		this.alertMappingMode = alertMappingMode;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getLastModifiedSeatID()
	{
		return lastModifiedSeatID;
	}

	public void setLastModifiedSeatID(String lastModifiedSeatID)
	{
		this.lastModifiedSeatID = lastModifiedSeatID;
	}

	public String getLastModifyDate()
	{
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate)
	{
		this.lastModifyDate = lastModifyDate;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public String getEntryMode()
	{
		return entryMode;
	}

	public void setEntryMode(String entryMode)
	{
		this.entryMode = entryMode;
	}

	public String getAlertCode()
	{
		return alertCode;
	}

	public void setAlertCode(String alertCode)
	{
		this.alertCode = alertCode;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getExtuserID() {
		return extuserID;
	}

	public void setExtuserID(String extuserID) {
		this.extuserID = extuserID;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getApplicationAlert() {
		return applicationAlert;
	}

	public void setApplicationAlert(String applicationAlert) {
		this.applicationAlert = applicationAlert;
	}

	public String getSmsAlert() {
		return smsAlert;
	}

	public void setSmsAlert(String smsAlert) {
		this.smsAlert = smsAlert;
	}

	public String getFaxAlert() {
		return faxAlert;
	}

	public void setFaxAlert(String faxAlert) {
		this.faxAlert = faxAlert;
	}

	public String getEmailAlert() {
		return emailAlert;
	}

	public void setEmailAlert(String emailAlert) {
		this.emailAlert = emailAlert;
	}

	public String getPagerAlert() {
		return pagerAlert;
	}

	public void setPagerAlert(String pagerAlert) {
		this.pagerAlert = pagerAlert;
	}

	public String getUserSeatID() {
		return userSeatID;
	}

	public void setUserSeatID(String userSeatID) {
		this.userSeatID = userSeatID;
	}
}