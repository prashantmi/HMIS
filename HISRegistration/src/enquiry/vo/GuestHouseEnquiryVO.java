package enquiry.vo;

import hisglobal.vo.ValueObject;

public class GuestHouseEnquiryVO extends ValueObject {

	private String guestHouse;
	private String locationBuilding;
	private String locationBlock;
	private String locationFloor;
	private String locationRoom;
	private String bed;
	private String rent;
	private String status;
	private String vacantOn;
	
	public String getLocationBuilding() {
		return locationBuilding;
	}
	public void setLocationBuilding(String locationBuilding) {
		this.locationBuilding = locationBuilding;
	}
	public String getLocationBlock() {
		return locationBlock;
	}
	public void setLocationBlock(String locationBlock) {
		this.locationBlock = locationBlock;
	}
	public String getLocationFloor() {
		return locationFloor;
	}
	public void setLocationFloor(String locationFloor) {
		this.locationFloor = locationFloor;
	}
	public String getLocationRoom() {
		return locationRoom;
	}
	public void setLocationRoom(String locationRoom) {
		this.locationRoom = locationRoom;
	}
	public String getGuestHouse() {
		return guestHouse;
	}
	public void setGuestHouse(String guestHouse) {
		this.guestHouse = guestHouse;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVacantOn() {
		return vacantOn;
	}
	public void setVacantOn(String vacantOn) {
		this.vacantOn = vacantOn;
	}
		
	
}
