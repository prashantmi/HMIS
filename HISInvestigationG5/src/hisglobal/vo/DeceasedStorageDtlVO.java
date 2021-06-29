package hisglobal.vo;

public class DeceasedStorageDtlVO extends ValueObject
{
	private String deceasedNo;
	private String storageInTime;
	private String storageUpto;
	private String storageReason;
	private String storageOutTime;
	private String chamberRackId;
	private String bodyPutBy;
	private String outFor;
	private String isStorageExtend;
	
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
	
}
