package hisglobal.vo;

public class DisasterPlanDetailVO extends ValueObject {

	private String planDate;
	private String planId;
	private String slNo;
	private String hospitalCode;
	private String planStatus;
	private String remarks;
	private String changeReason;
	private String effectiveFrom;
	private String effectiveTo;
	private String scanDocID;
	private String templateID;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastmodifyDate;
	private String lastModifySeatID;
	private byte[] planDoc;
	
	public byte[] getPlanDoc() {
		return planDoc;
	}
	public void setPlanDoc(byte[] planDoc) {
		this.planDoc = planDoc;
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	public String getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	public String getEffectiveTo() {
		return effectiveTo;
	}
	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	public String getScanDocID() {
		return scanDocID;
	}
	public void setScanDocID(String scanDocID) {
		this.scanDocID = scanDocID;
	}
	public String getTemplateID() {
		return templateID;
	}
	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}
	
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLastmodifyDate() {
		return lastmodifyDate;
	}
	public void setLastmodifyDate(String lastmodifyDate) {
		this.lastmodifyDate = lastmodifyDate;
	}
	public String getLastModifySeatID() {
		return lastModifySeatID;
	}
	public void setLastModifySeatID(String lastModifySeatID) {
		this.lastModifySeatID = lastModifySeatID;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
}
