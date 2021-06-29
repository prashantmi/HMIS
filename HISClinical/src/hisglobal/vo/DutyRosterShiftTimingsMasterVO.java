
package hisglobal.vo;

public class DutyRosterShiftTimingsMasterVO extends ValueObject{

	private String shiftCode;
	private String hospitalCode;
	private String serialNo;
	private String weekDayCode;	
	private String shiftStartTime;
	private String shiftEndTime;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String lastModDate;
	private String lastModSeatId;
	
	
	
	
	public String getWeekDayCode() {
		return weekDayCode;
	}
	public void setWeekDayCode(String weekDayCode) {
		this.weekDayCode = weekDayCode;
	}
	public String getShiftCode() {
		return shiftCode;
	}
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getShiftStartTime() {
		return shiftStartTime;
	}
	public void setShiftStartTime(String shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}
	public String getShiftEndTime() {
		return shiftEndTime;
	}
	public void setShiftEndTime(String shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLastModDate() {
		return lastModDate;
	}
	public void setLastModDate(String lastModDate) {
		this.lastModDate = lastModDate;
	}
	public String getLastModSeatId() {
		return lastModSeatId;
	}
	public void setLastModSeatId(String lastModSeatId) {
		this.lastModSeatId = lastModSeatId;
	}
	
	
	
	
	
	
}
