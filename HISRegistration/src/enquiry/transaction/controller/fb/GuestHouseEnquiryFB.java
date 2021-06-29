package enquiry.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class GuestHouseEnquiryFB extends ActionForm {

	private String hmode; 
	private String guestHouse;
	private String locationBuilding;
	private String locationBlock;
	private String locationFloor;
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getGuestHouse() {
		return guestHouse;
	}
	public void setGuestHouse(String guestHouse) {
		this.guestHouse = guestHouse;
	}
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
	
	
}
