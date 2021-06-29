package hisglobal.vo;

public class PatientAlertsDetailVO extends ValueObject
{
	private String patCrNo;
	private String alertName;
	private String patAlertId;
	private String effectiveFrom;
	private String remarks;
	private String durationDate;
	private String effectiveTo;
	private String episodeCode;
	private String episodeVisitNo;
	private String revokeRemarks;
	private String admissionNo;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String durationDays;
	private String departmentUnitCode;
	private String hospitalCode;
	private String hospitalName;
	private String snomedCtId;
	private boolean checkedRevoke;
	
	public PatientAlertsDetailVO()
	{
		this.checkedRevoke=false;
	}
	
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
	public String getAlertName() {
		return alertName;
	}
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}
	public String getPatAlertId() {
		return patAlertId;
	}
	public void setPatAlertId(String patAlertId) {
		this.patAlertId = patAlertId;
	}
	public String getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
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
	public String getEffectiveTo() {
		return effectiveTo;
	}
	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
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
	public String getRevokeRemarks() {
		return revokeRemarks;
	}
	public void setRevokeRemarks(String revokeRemarks) {
		this.revokeRemarks = revokeRemarks;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getDurationDays() {
		return durationDays;
	}
	public void setDurationDays(String durationDays) {
		this.durationDays = durationDays;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	public boolean isCheckedRevoke()
	{
		return checkedRevoke;
	}

	public void setCheckedRevoke(boolean checkedRevoke)
	{
		this.checkedRevoke = checkedRevoke;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getSnomedCtId() {
		return snomedCtId;
	}

	public void setSnomedCtId(String snomedCtId) {
		this.snomedCtId = snomedCtId;
	}
}
