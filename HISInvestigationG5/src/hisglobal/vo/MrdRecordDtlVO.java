package hisglobal.vo;

public class MrdRecordDtlVO extends ValueObject
{
	private String recordId;
	private String recordDesc;
	private String slNo;
	private String recordType;
	private String remarks;
	private String recordStatus;
	private String roomCode;
	private String rackId;
	private String ShelfName;
	private String patCrNo;
	private String ShelfId;
	private String episodeCode;
	private String episodeVisitNo;
	private String patAdmNo;
	private String deptUnitCode;
	private String receiveFrom;
	private String entryDate;
	
	private String mrdRecordId;
	private String mrdCode;
	private String isScaned;
	private String putBy;

	private String mrdName;
	private String rackLocation;
	private String rackLabel;
	private String recordStatusLabel;
	private String recordTypeLabel;
	private String raisedRequest;
	private String issuedFor;
	private String issueFlag;

	private String dispatchId;
	private String patName;
	private String changeArchivedFlag;
	private String patAge;
	private String patGender;
	
	/////For OPD FIle Movement
	
	private String visitQueueNo;
	private String unitCode;
	private String unitName;
	
	
	//for Record movement **Added by Akash /////
	private String recordEntryDate;
	

	
	public String getVisitQueueNo() {
		return visitQueueNo;
	}
	public void setVisitQueueNo(String visitQueueNo) {
		this.visitQueueNo = visitQueueNo;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getDispatchId() {
		return dispatchId;
	}
	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}
	public String getReceiveFrom() {
		return receiveFrom;
	}
	public void setReceiveFrom(String receiveFrom) {
		this.receiveFrom = receiveFrom;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getRecordDesc() {
		return recordDesc;
	}
	public void setRecordDesc(String recordDesc) {
		this.recordDesc = recordDesc;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getRackId() {
		return rackId;
	}
	public void setRackId(String rackId) {
		this.rackId = rackId;
	}
	public String getShelfName() {
		return ShelfName;
	}
	public void setShelfName(String shelfName) {
		ShelfName = shelfName;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getShelfId() {
		return ShelfId;
	}
	public void setShelfId(String shelfId) {
		ShelfId = shelfId;
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
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getIsScaned() {
		return isScaned;
	}
	public void setIsScaned(String isScaned) {
		this.isScaned = isScaned;
	}
	public String getPutBy() {
		return putBy;
	}
	public void setPutBy(String putBy) {
		this.putBy = putBy;
	}

	public String getMrdName() {
		return mrdName;
	}
	public void setMrdName(String mrdName) {
		this.mrdName = mrdName;
	}
	public String getRackLocation() {
		return rackLocation;
	}
	public void setRackLocation(String rackLocation) {
		this.rackLocation = rackLocation;
	}
	public String getRackLabel() {
		return rackLabel;
	}
	public void setRackLabel(String rackLabel) {
		this.rackLabel = rackLabel;
	}
	public String getRecordStatusLabel() {
		return recordStatusLabel;
	}
	public void setRecordStatusLabel(String recordStatusLabel) {
		this.recordStatusLabel = recordStatusLabel;
	}
	public String getRecordTypeLabel() {
		return recordTypeLabel;
	}
	public void setRecordTypeLabel(String recordTypeLabel) {
		this.recordTypeLabel = recordTypeLabel;
	}

	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getRaisedRequest() {
		return raisedRequest;
	}
	public void setRaisedRequest(String raisedRequest) {
		this.raisedRequest = raisedRequest;
	}
	public String getIssuedFor() {
		return issuedFor;
	}
	public void setIssuedFor(String issuedFor) {
		this.issuedFor = issuedFor;
	}
	public String getChangeArchivedFlag() {
		return changeArchivedFlag;
	}
	public void setChangeArchivedFlag(String changeArchivedFlag) {
		this.changeArchivedFlag = changeArchivedFlag;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getIssueFlag() {
		return issueFlag;
	}
	public void setIssueFlag(String issueFlag) {
		this.issueFlag = issueFlag;
	}
	public String getRecordEntryDate() {
		return recordEntryDate;
	}
	public void setRecordEntryDate(String recordEntryDate) {
		this.recordEntryDate = recordEntryDate;
	}

	
}
