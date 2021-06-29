package hisglobal.vo;

public class BloodTransfusionDtlVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String transfussionDate;
	private String serialNo;
	private String admissionNo;
	private String episodeVisitNo;
	private String transfussionStartTime;
	private String transfussionEndTime;
	private String  phlebotomyArm;
	private String bloodBagNo;
	private String bloodBagSequenceNo;
	private String seatID;
	private String entryDate;
	private String isValid;
	private String hospitalCode;
	private String employeeNo;
	private String remark;
	
	private String stockBagSerialNo;
	private String stockDate;
	
	private String bagBatchNo;
	private String bloodBagExpiry;
	private String bloodGroup;
	private String componentName;
	private String sourceFlag;
	private String requisitionNo;
	
	private String transfussionRate;
	private String qtyTransfussed;
	private String bagVolume;
	private String transfusionStatus;
	
	
	public String getTransfusionStatus() {
		return transfusionStatus;
	}
	public void setTransfusionStatus(String transfusionStatus) {
		this.transfusionStatus = transfusionStatus;
	}
	public String getBagVolume() {
		return bagVolume;
	}
	public void setBagVolume(String bagVolume) {
		this.bagVolume = bagVolume;
	}
	public String getTransfussionRate() {
		return transfussionRate;
	}
	public void setTransfussionRate(String transfussionRate) {
		this.transfussionRate = transfussionRate;
	}
	public String getQtyTransfussed() {
		return qtyTransfussed;
	}
	public void setQtyTransfussed(String qtyTransfussed) {
		this.qtyTransfussed = qtyTransfussed;
	}
	public String getRequisitionNo() {
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	public String getBloodBagExpiry() {
		return bloodBagExpiry;
	}
	public void setBloodBagExpiry(String bloodBagExpiry) {
		this.bloodBagExpiry = bloodBagExpiry;
	}
	
	public String getSourceFlag() {
		return sourceFlag;
	}
	public void setSourceFlag(String sourceFlag) {
		this.sourceFlag = sourceFlag;
	}
	public String getStockBagSerialNo() {
		return stockBagSerialNo;
	}
	public void setStockBagSerialNo(String stockBagSerialNo) {
		this.stockBagSerialNo = stockBagSerialNo;
	}
	public String getStockDate() {
		return stockDate;
	}
	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getTransfussionDate() {
		return transfussionDate;
	}
	public void setTransfussionDate(String transfussionDate) {
		this.transfussionDate = transfussionDate;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getTransfussionStartTime() {
		return transfussionStartTime;
	}
	public void setTransfussionStartTime(String transfussionStartTime) {
		this.transfussionStartTime = transfussionStartTime;
	}
	public String getTransfussionEndTime() {
		return transfussionEndTime;
	}
	public void setTransfussionEndTime(String transfussionEndTime) {
		this.transfussionEndTime = transfussionEndTime;
	}
	public String getPhlebotomyArm() {
		return phlebotomyArm;
	}
	public void setPhlebotomyArm(String phlebotomyArm) {
		this.phlebotomyArm = phlebotomyArm;
	}
	public String getBloodBagNo() {
		return bloodBagNo;
	}
	public void setBloodBagNo(String bloodBagNo) {
		this.bloodBagNo = bloodBagNo;
	}
	public String getBloodBagSequenceNo() {
		return bloodBagSequenceNo;
	}
	public void setBloodBagSequenceNo(String bloodBagSequenceNo) {
		this.bloodBagSequenceNo = bloodBagSequenceNo;
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
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getBagBatchNo() {
		return bagBatchNo;
	}
	public void setBagBatchNo(String bagBatchNo) {
		this.bagBatchNo = bagBatchNo;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
}
