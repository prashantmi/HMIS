package hisglobal.vo;

public class MrdRackShelfChangeDtlVO extends ValueObject
{
	private String mrdRecordId;
	private String toMrdCode;
	private String toRackId;
	private String toShelfId;
	private String fromMrdCode;
	private String fromRackId;
	private String fromShelfId;
	private String entryMode;
	private String remarks;
	private String putBy;
	
	private String movementOptionFlag;
	
	
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}
	public String getToMrdCode() {
		return toMrdCode;
	}
	public void setToMrdCode(String toMrdCode) {
		this.toMrdCode = toMrdCode;
	}
	public String getToRackId() {
		return toRackId;
	}
	public void setToRackId(String toRackId) {
		this.toRackId = toRackId;
	}
	public String getToShelfId() {
		return toShelfId;
	}
	public void setToShelfId(String toShelfId) {
		this.toShelfId = toShelfId;
	}
	public String getFromMrdCode() {
		return fromMrdCode;
	}
	public void setFromMrdCode(String fromMrdCode) {
		this.fromMrdCode = fromMrdCode;
	}
	public String getFromRackId() {
		return fromRackId;
	}
	public void setFromRackId(String fromRackId) {
		this.fromRackId = fromRackId;
	}
	public String getFromShelfId() {
		return fromShelfId;
	}
	public void setFromShelfId(String fromShelfId) {
		this.fromShelfId = fromShelfId;
	}
	public String getEntryMode() {
		return entryMode;
	}
	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPutBy() {
		return putBy;
	}
	public void setPutBy(String putBy) {
		this.putBy = putBy;
	}
	public String getMovementOptionFlag() {
		return movementOptionFlag;
	}
	public void setMovementOptionFlag(String movementOptionFlag) {
		this.movementOptionFlag = movementOptionFlag;
	}
	
	
}
