package hisglobal.vo;

public class CaseSheetDtlVO extends ValueObject
{
private String serialNo;
	
	private String caseSheetId;
	private String patAdmNo;
	private String admDateTime;
	private String patCrNo;
	private String patName;
	private String caseSheetStatus;
	private String caseSheetStatusName;
	private String isDuplicate;
	private String isDuplicateValue;
	private String disDate;
	private String patGenderAge;
	private String remarks;
	private String cancelRemarks;
	private String departmentCode;
	private String departmentUnitCode;
	private String wardCode;
	private String roomCode;
	private String recordStatus;
	private String recordStatusName;
	private String episodeCode;
	private String episodeVisitNo;
	private String isDelay;
	private String caseSheetType;
	private String creationDate;
	
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getCaseSheetStatus() {
		return caseSheetStatus;
	}
	public void setCaseSheetStatus(String caseSheetStatus) {
		this.caseSheetStatus = caseSheetStatus;
	}
	public String getIsDuplicate() {
		return isDuplicate;
	}
	public void setIsDuplicate(String isDuplicate) {
		this.isDuplicate = isDuplicate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCancelRemarks() {
		return cancelRemarks;
	}
	public void setCancelRemarks(String cancelRemarks) {
		this.cancelRemarks = cancelRemarks;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getDisDate() {
		return disDate;
	}
	public void setDisDate(String disDate) {
		this.disDate = disDate;
	}
	public String getPatGenderAge() {
		return patGenderAge;
	}
	public void setPatGenderAge(String patGenderAge) {
		this.patGenderAge = patGenderAge;
	}
	public String getCaseSheetId() {
		return caseSheetId;
	}
	public void setCaseSheetId(String caseSheetId) {
		this.caseSheetId = caseSheetId;
	}
	public String getCaseSheetStatusName() {
		return caseSheetStatusName;
	}
	public void setCaseSheetStatusName(String caseSheetStatusName) {
		this.caseSheetStatusName = caseSheetStatusName;
	}
	public String getIsDuplicateValue() {
		return isDuplicateValue;
	}
	public void setIsDuplicateValue(String isDuplicateValue) {
		this.isDuplicateValue = isDuplicateValue;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getRecordStatusName() {
		return recordStatusName;
	}
	public void setRecordStatusName(String recordStatusName) {
		this.recordStatusName = recordStatusName;
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
	public String getIsDelay() {
		return isDelay;
	}
	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}
	public String getCaseSheetType() {
		return caseSheetType;
	}
	public void setCaseSheetType(String caseSheetType) {
		this.caseSheetType = caseSheetType;
	}
	public String getAdmDateTime()
	{
		return admDateTime;
	}
	public void setAdmDateTime(String admDateTime)
	{
		this.admDateTime = admDateTime;
	}
	/**
	 * @return the creationDate
	 */
	public String getCreationDate()
	{
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate)
	{
		this.creationDate = creationDate;
	}
}
