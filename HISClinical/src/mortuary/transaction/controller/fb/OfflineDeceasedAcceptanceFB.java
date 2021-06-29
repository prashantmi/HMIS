package mortuary.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

import registration.controller.fb.CRNoFB;

public class OfflineDeceasedAcceptanceFB extends CRNoFB
{
	//private String patCrNo;
	private String hmode;
	private String entryMode;
	private String deathDateTime;
	private String bodyStatus;
	private String isMlcCase;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patGenderCode;
	private String patDOB;
	private String isActualDob;
	private String patMotherName;
	private String patHusbandName;
	private String patGuardianName;
	private String patMaritalStatusCode;
	private String add1;
	private String add2;
	private String contactNo;
	private String diagnosis;
	private String deathHistory;
	private String isPregnant;
	private String obstetricHistory;
	private String patIdMark1;
	private String patIdMark2;
	private String issueDateTime;
	private String episodeCode;
	private String episodeVisitNo;
	private String departmentCode;
	private String departmentUnitCode;
	private String wardCode;
	private String roomNo;
	private String theatreCode;
	private String relativeName;
	private String relativeAddress;
	private String relativeContactNo;
	private String relativeCode;
	private String bodyPutBy;
	private String storageReason;
	private String chamberId;
	private String chamberRackId;
	private String storageUpto;
	private String officerName;
	private String officerDesignation;
	private String officerBadgeNo;
	private String bodyHandoverTo;
	private String bodyHandoverDateTime;
	private String relativeCodeName;
	private String handoverFlag;
	private String storageRelativeName;
	private String storageRelativeAddress;
	private String storageRelativeContactNo;
	private String storageRelativeCode;
	private String isDead;
	
	public String getStorageRelativeName() {
		return storageRelativeName;
	}
	public void setStorageRelativeName(String storageRelativeName) {
		this.storageRelativeName = storageRelativeName;
	}
	public String getStorageRelativeAddress() {
		return storageRelativeAddress;
	}
	public void setStorageRelativeAddress(String storageRelativeAddress) {
		this.storageRelativeAddress = storageRelativeAddress;
	}
	public String getStorageRelativeContactNo() {
		return storageRelativeContactNo;
	}
	public void setStorageRelativeContactNo(String storageRelativeContactNo) {
		this.storageRelativeContactNo = storageRelativeContactNo;
	}
	public String getStorageRelativeCode() {
		return storageRelativeCode;
	}
	public void setStorageRelativeCode(String storageRelativeCode) {
		this.storageRelativeCode = storageRelativeCode;
	}
	public String getRelativeCodeName() {
		return relativeCodeName;
	}
	public void setRelativeCodeName(String relativeCodeName) {
		this.relativeCodeName = relativeCodeName;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getOfficerDesignation() {
		return officerDesignation;
	}
	public void setOfficerDesignation(String officerDesignation) {
		this.officerDesignation = officerDesignation;
	}
	public String getOfficerBadgeNo() {
		return officerBadgeNo;
	}
	public void setOfficerBadgeNo(String officerBadgeNo) {
		this.officerBadgeNo = officerBadgeNo;
	}
	/*public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}*/
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getEntryMode() {
		return entryMode;
	}
	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}
	public String getDeathDateTime() {
		return deathDateTime;
	}
	public void setDeathDateTime(String deathDateTime) {
		this.deathDateTime = deathDateTime;
	}
	public String getBodyStatus() {
		return bodyStatus;
	}
	public void setBodyStatus(String bodyStatus) {
		this.bodyStatus = bodyStatus;
	}
	public String getIsMlcCase() {
		return isMlcCase;
	}
	public void setIsMlcCase(String isMlcCase) {
		this.isMlcCase = isMlcCase;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getIsActualDob() {
		return isActualDob;
	}
	public void setIsActualDob(String isActualDob) {
		this.isActualDob = isActualDob;
	}
	public String getPatMotherName() {
		return patMotherName;
	}
	public void setPatMotherName(String patMotherName) {
		this.patMotherName = patMotherName;
	}
	public String getPatHusbandName() {
		return patHusbandName;
	}
	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getPatMaritalStatusCode() {
		return patMaritalStatusCode;
	}
	public void setPatMaritalStatusCode(String patMaritalStatusCode) {
		this.patMaritalStatusCode = patMaritalStatusCode;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getDeathHistory() {
		return deathHistory;
	}
	public void setDeathHistory(String deathHistory) {
		this.deathHistory = deathHistory;
	}
	public String getIsPregnant() {
		return isPregnant;
	}
	public void setIsPregnant(String isPregnant) {
		this.isPregnant = isPregnant;
	}
	public String getObstetricHistory() {
		return obstetricHistory;
	}
	public void setObstetricHistory(String obstetricHistory) {
		this.obstetricHistory = obstetricHistory;
	}
	public String getPatIdMark1() {
		return patIdMark1;
	}
	public void setPatIdMark1(String patIdMark1) {
		this.patIdMark1 = patIdMark1;
	}
	public String getPatIdMark2() {
		return patIdMark2;
	}
	public void setPatIdMark2(String patIdMark2) {
		this.patIdMark2 = patIdMark2;
	}
	public String getIssueDateTime() {
		return issueDateTime;
	}
	public void setIssueDateTime(String issueDateTime) {
		this.issueDateTime = issueDateTime;
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
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getTheatreCode() {
		return theatreCode;
	}
	public void setTheatreCode(String theatreCode) {
		this.theatreCode = theatreCode;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelativeAddress() {
		return relativeAddress;
	}
	public void setRelativeAddress(String relativeAddress) {
		this.relativeAddress = relativeAddress;
	}
	public String getRelativeContactNo() {
		return relativeContactNo;
	}
	public void setRelativeContactNo(String relativeContactNo) {
		this.relativeContactNo = relativeContactNo;
	}
	public String getRelativeCode() {
		return relativeCode;
	}
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
	}
	public String getBodyPutBy() {
		return bodyPutBy;
	}
	public void setBodyPutBy(String bodyPutBy) {
		this.bodyPutBy = bodyPutBy;
	}
	public String getStorageReason() {
		return storageReason;
	}
	public void setStorageReason(String storageReason) {
		this.storageReason = storageReason;
	}
	public String getChamberId() {
		return chamberId;
	}
	public void setChamberId(String chamberId) {
		this.chamberId = chamberId;
	}
	public String getChamberRackId() {
		return chamberRackId;
	}
	public void setChamberRackId(String chamberRackId) {
		this.chamberRackId = chamberRackId;
	}
	public String getStorageUpto() {
		return storageUpto;
	}
	public void setStorageUpto(String storageUpto) {
		this.storageUpto = storageUpto;
	}
	public String getBodyHandoverTo() {
		return bodyHandoverTo;
	}
	public void setBodyHandoverTo(String bodyHandoverTo) {
		this.bodyHandoverTo = bodyHandoverTo;
	}
	public String getBodyHandoverDateTime() {
		return bodyHandoverDateTime;
	}
	public void setBodyHandoverDateTime(String bodyHandoverDateTime) {
		this.bodyHandoverDateTime = bodyHandoverDateTime;
	}
	public String getHandoverFlag() {
		return handoverFlag;
	}
	public void setHandoverFlag(String handoverFlag) {
		this.handoverFlag = handoverFlag;
	}
	
}
