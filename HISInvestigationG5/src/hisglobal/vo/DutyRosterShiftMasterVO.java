package hisglobal.vo;

public class DutyRosterShiftMasterVO extends ValueObject{

	private String shiftCode;
	private String hospitalCode;
	private String serialNo;
	private String shiftTypeCode;	
	private String shiftDescription;
	private String shiftStartTime;
	private String shiftEndTime;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String lastModDate;
	private String lastModSeatId;
	private String shiftShortName;
	private String isDayWiseShift;
	
	
	public String getIsDayWiseShift() {
		return isDayWiseShift;
	}
	public void setIsDayWiseShift(String isDayWiseShift) {
		this.isDayWiseShift = isDayWiseShift;
	}
	public String getShiftShortName() {
		return shiftShortName;
	}
	public void setShiftShortName(String shiftShortName) {
		this.shiftShortName = shiftShortName;
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
	public String getShiftTypeCode() {
		return shiftTypeCode;
	}
	public void setShiftTypeCode(String shiftTypeCode) {
		this.shiftTypeCode = shiftTypeCode;
	}
	public String getShiftDescription() {
		return shiftDescription;
	}
	public void setShiftDescription(String shiftDescription) {
		this.shiftDescription = shiftDescription;
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
