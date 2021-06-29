package hisglobal.vo;

public class MrdRecordMovementVO extends ValueObject {
	
	private String mrdRecordId;
	private String handOverToId;
	private String remarks;
	private String movementType;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String hospitalCode;
	private String ipAddress;
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}
	public String getHandOverToId() {
		return handOverToId;
	}
	public void setHandOverToId(String handOverToId) {
		this.handOverToId = handOverToId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMovementType() {
		return movementType;
	}
	public void setMovementType(String movementType) {
		this.movementType = movementType;
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
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
