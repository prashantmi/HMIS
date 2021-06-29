package hisglobal.vo;

public class PostmortemRequestDetailVO extends ValueObject
{
	private String postmortemId;
	private String srNo;
	private String patMlcNo;
	private String deceasedNo;
	private String caseNo;
	private String requestDateTime;
	private String postmortemStatus;
	private String postmortemStatusLabel;
	private String policeStation;
	private String docketNo;
	private String officerIncharge;
	private String ioDesignation;
	private String ioBatchNo;
	private String dutyOffName;
	private String dutyOffDesignation;
	private String dutyOffBatchNo;
	private String caseRemarks;
	private String deathDate;
	private String incidenceDate;
	private String deathPlace;
	private String isRepeat;
	private String RepeatReason;
	private String approvedBy;
	private String conductBy;
	private String postmortemType;
	private String postmortemReqType;
	private String deceasedName;
	private String autopsyType;
	private String consentStatus;
	private String postmortemTypeName;
	private String cancelReason;
	private String color;
	
	//Relative
	private String relativeName;
	private String relativeCode;
	private String relativeAddress;
	private String relativeContactNo;
	
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelativeCode() {
		return relativeCode;
	}
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
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
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getPostmortemTypeName() {
		return postmortemTypeName;
	}
	public void setPostmortemTypeName(String postmortemTypeName) {
		this.postmortemTypeName = postmortemTypeName;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getPatMlcNo() {
		return patMlcNo;
	}
	public void setPatMlcNo(String patMlcNo) {
		this.patMlcNo = patMlcNo;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getRequestDateTime() {
		return requestDateTime;
	}
	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public String getPostmortemStatus() {
		return postmortemStatus;
	}
	public void setPostmortemStatus(String postmortemStatus) {
		this.postmortemStatus = postmortemStatus;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getDocketNo() {
		return docketNo;
	}
	public void setDocketNo(String docketNo) {
		this.docketNo = docketNo;
	}
	public String getOfficerIncharge() {
		return officerIncharge;
	}
	public void setOfficerIncharge(String officerIncharge) {
		this.officerIncharge = officerIncharge;
	}
	public String getIoDesignation() {
		return ioDesignation;
	}
	public void setIoDesignation(String ioDesignation) {
		this.ioDesignation = ioDesignation;
	}
	public String getIoBatchNo() {
		return ioBatchNo;
	}
	public void setIoBatchNo(String ioBatchNo) {
		this.ioBatchNo = ioBatchNo;
	}
	public String getDutyOffName() {
		return dutyOffName;
	}
	public void setDutyOffName(String dutyOffName) {
		this.dutyOffName = dutyOffName;
	}
	public String getDutyOffDesignation() {
		return dutyOffDesignation;
	}
	public void setDutyOffDesignation(String dutyOffDesignation) {
		this.dutyOffDesignation = dutyOffDesignation;
	}
	public String getDutyOffBatchNo() {
		return dutyOffBatchNo;
	}
	public void setDutyOffBatchNo(String dutyOffBatchNo) {
		this.dutyOffBatchNo = dutyOffBatchNo;
	}
	public String getCaseRemarks() {
		return caseRemarks;
	}
	public void setCaseRemarks(String caseRemarks) {
		this.caseRemarks = caseRemarks;
	}
	public String getDeathPlace() {
		return deathPlace;
	}
	public void setDeathPlace(String deathPlace) {
		this.deathPlace = deathPlace;
	}
	public String getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}
	public String getRepeatReason() {
		return RepeatReason;
	}
	public void setRepeatReason(String repeatReason) {
		RepeatReason = repeatReason;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getConductBy() {
		return conductBy;
	}
	public void setConductBy(String conductBy) {
		this.conductBy = conductBy;
	}
	public String getPostmortemType() {
		return postmortemType;
	}
	public void setPostmortemType(String postmortemType) {
		this.postmortemType = postmortemType;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public String getIncidenceDate() {
		return incidenceDate;
	}
	public void setIncidenceDate(String incidenceDate) {
		this.incidenceDate = incidenceDate;
	}
	public String getPostmortemReqType() {
		return postmortemReqType;
	}
	public void setPostmortemReqType(String postmortemReqType) {
		this.postmortemReqType = postmortemReqType;
	}
	public String getDeceasedName() {
		return deceasedName;
	}
	public void setDeceasedName(String deceasedName) {
		this.deceasedName = deceasedName;
	}
	public String getAutopsyType() {
		return autopsyType;
	}
	public void setAutopsyType(String autopsyType) {
		this.autopsyType = autopsyType;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getConsentStatus() {
		return consentStatus;
	}
	public void setConsentStatus(String consentStatus) {
		this.consentStatus = consentStatus;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPostmortemStatusLabel() {
		return postmortemStatusLabel;
	}
	public void setPostmortemStatusLabel(String postmortemStatusLabel) {
		this.postmortemStatusLabel = postmortemStatusLabel;
	}
}
