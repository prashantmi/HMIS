package vo.appointment;

import hisglobal.vo.ValueObject;

/**
 * @author nehasharma
 *
 */
public class ShiftDayVO extends ValueObject {

	String currentDateAppt;// is the count of walk in appts in that particular day
	String priorAppt;
	String overBook;
	String isVipSlotAllowed;// 1 yes 2 NO
	String portal;
	String shiftId; 
	String shiftIdHidden; 
	String startTime;
	String endTime;
	String shiftwiseSelectedDays;
	String weekOfMonth;
	String shiftName;
	String daystr;
	//By Mukund for appointment configuration master
	String opdApptSlots;
	String ipdApptSlots;
	
	
	
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public String getDaystr() {
		return daystr;
	}
	public void setDaystr(String daystr) {
		this.daystr = daystr;
	}
	public String getWeekOfMonth() {
		return weekOfMonth;
	}
	public void setWeekOfMonth(String weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}
	public String getCurrentDateAppt() {
		return currentDateAppt;
	}
	public void setCurrentDateAppt(String currentDateAppt) {
		this.currentDateAppt = currentDateAppt;
	}
	public String getPriorAppt() {
		return priorAppt;
	}
	public void setPriorAppt(String priorAppt) {
		this.priorAppt = priorAppt;
	}
	public String getOverBook() {
		return overBook;
	}
	public void setOverBook(String overBook) {
		this.overBook = overBook;
	}
	public String getIsVipSlotAllowed() {
		return isVipSlotAllowed;
	}
	public void setIsVipSlotAllowed(String isVipSlotAllowed) {
		this.isVipSlotAllowed = isVipSlotAllowed;
	}
	public String getPortal() {
		return portal;
	}
	public void setPortal(String portal) {
		this.portal = portal;
	}
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getShiftwiseSelectedDays() {
		return shiftwiseSelectedDays;
	}
	public void setShiftwiseSelectedDays(String shiftwiseSelectedDays) {
		this.shiftwiseSelectedDays = shiftwiseSelectedDays;
	}
	public String getShiftIdHidden() {
		return shiftIdHidden;
	}
	public void setShiftIdHidden(String shiftIdHidden) {
		this.shiftIdHidden = shiftIdHidden;
	}
	public String getOpdApptSlots() {
		return opdApptSlots;
	}
	public void setOpdApptSlots(String opdApptSlots) {
		this.opdApptSlots = opdApptSlots;
	}
	public String getIpdApptSlots() {
		return ipdApptSlots;
	}
	public void setIpdApptSlots(String ipdApptSlots) {
		this.ipdApptSlots = ipdApptSlots;
	}
	
}
