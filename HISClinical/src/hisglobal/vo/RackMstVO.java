package hisglobal.vo;

public class RackMstVO extends ValueObject
{
	private String rackId;
	private String rackName;
	private String rackType;
	private String itemId;
	private String remarks;
	private String roomId;
	private String hospitalCode;
	private String isValid;
	private String blockId;
	private String floorId;
	private String buildingCode;
	private String roomName;
	private String seatId;
	private String entrydate;
	private String serialNo;
	private String rackTypeName;
	private String rackStatus;
	private String mrdCode;
	
	public String getRackTypeName() {
		return rackTypeName;
	}
	public void setRackTypeName(String rackTypeName) {
		this.rackTypeName = rackTypeName;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRackId() {
		return rackId;
	}
	public void setRackId(String rackId) {
		this.rackId = rackId;
	}
	public String getRackName() {
		return rackName;
	}
	public void setRackName(String rackName) {
		this.rackName = rackName;
	}
	public String getRackType() {
		return rackType;
	}
	public void setRackType(String rackType) {
		this.rackType = rackType;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
	public String getBuildingCode() {
		return buildingCode;
	}
	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}
	public String getRackStatus() {
		return rackStatus;
	}
	public void setRackStatus(String rackStatus) {
		this.rackStatus = rackStatus;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	
	
	
}
