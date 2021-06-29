package mortuary.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class DeceasedStorageFB extends ActionForm
{
	private String hmode;
	private String deceasedNo;
	private String storageInTime;
	private String storageUpto;
	private String storageReason;
	private String storageOutTime;
	private String chamberRackId;
	private String bodyPutBy;
	private String outFor;
	private String isStorageExtend;
	private String selectedDeceased;
	private String patCrNo;
	private String chamberId;
	private String isStorageByRelative;
	private String mortuaryCode;
	private String mortuaryAreaCode;
	
	////Storage Relative
	private String storageRelativeName;
	private String storageRelativeAddress;
	private String storageRelativeContactNo;
	private String storageRelativeCode;
	
	
	public String getChamberId() {
		return chamberId;
	}
	public void setChamberId(String chamberId) {
		this.chamberId = chamberId;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getStorageInTime() {
		return storageInTime;
	}
	public void setStorageInTime(String storageInTime) {
		this.storageInTime = storageInTime;
	}
	public String getStorageUpto() {
		return storageUpto;
	}
	public void setStorageUpto(String storageUpto) {
		this.storageUpto = storageUpto;
	}
	public String getStorageReason() {
		return storageReason;
	}
	public void setStorageReason(String storageReason) {
		this.storageReason = storageReason;
	}
	public String getStorageOutTime() {
		return storageOutTime;
	}
	public void setStorageOutTime(String storageOutTime) {
		this.storageOutTime = storageOutTime;
	}
	public String getChamberRackId() {
		return chamberRackId;
	}
	public void setChamberRackId(String chamberRackId) {
		this.chamberRackId = chamberRackId;
	}
	public String getBodyPutBy() {
		return bodyPutBy;
	}
	public void setBodyPutBy(String bodyPutBy) {
		this.bodyPutBy = bodyPutBy;
	}
	public String getOutFor() {
		return outFor;
	}
	public void setOutFor(String outFor) {
		this.outFor = outFor;
	}
	public String getIsStorageExtend() {
		return isStorageExtend;
	}
	public void setIsStorageExtend(String isStorageExtend) {
		this.isStorageExtend = isStorageExtend;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSelectedDeceased() {
		return selectedDeceased;
	}
	public void setSelectedDeceased(String selectedDeceased) {
		this.selectedDeceased = selectedDeceased;
	}
	public String getIsStorageByRelative() {
		return isStorageByRelative;
	}
	public void setIsStorageByRelative(String isStorageByRelative) {
		this.isStorageByRelative = isStorageByRelative;
	}
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
	public String getMortuaryCode() {
		return mortuaryCode;
	}
	public void setMortuaryCode(String mortuaryCode) {
		this.mortuaryCode = mortuaryCode;
	}
	public String getMortuaryAreaCode() {
		return mortuaryAreaCode;
	}
	public void setMortuaryAreaCode(String mortuaryAreaCode) {
		this.mortuaryAreaCode = mortuaryAreaCode;
	}
	
}
