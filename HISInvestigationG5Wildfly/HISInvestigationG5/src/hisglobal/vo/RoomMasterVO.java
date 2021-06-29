package hisglobal.vo;

public class RoomMasterVO extends ValueObject
{
	private String roomCode;
	private String roomName;
	private String sequenceNo;
	private String entryDate;	
	private String seatID;
	private String isValid;	
	private String roomDescription;
	private String locationName;
	private String locationCode;
	private String effectiveDate;
	private String inactiveFromDate;
	private String inactiveToDate;
	
	private String buildingCode;
	private String buildingName;
	private String blockCode;
	private String blockName;
	private String floorCode;
	private String floorName;
	private String estateRoomId;
	private String estateRoomName;
	private String hospitalCode;

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getRoomCode()
	{
		return roomCode;
	}

	public void setRoomCode(String roomCode)
	{
		this.roomCode = roomCode;
	}

	public String getRoomName()
	{
		return roomName;
	}

	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getSequenceNo()
	{
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo)
	{
		this.sequenceNo = sequenceNo;
	}
	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getInactiveFromDate() {
		return inactiveFromDate;
	}

	public void setInactiveFromDate(String inactiveFromDate) {
		this.inactiveFromDate = inactiveFromDate;
	}

	public String getInactiveToDate() {
		return inactiveToDate;
	}

	public void setInactiveToDate(String inactiveToDate) {
		this.inactiveToDate = inactiveToDate;
	}	

	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getEstateRoomId() {
		return estateRoomId;
	}

	public void setEstateRoomId(String estateRoomId) {
		this.estateRoomId = estateRoomId;
	}

	public String getEstateRoomName() {
		return estateRoomName;
	}

	public void setEstateRoomName(String estateRoomName) {
		this.estateRoomName = estateRoomName;
	}

}
