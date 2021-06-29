package hisglobal.vo;

public class ShiftMasterVO extends ValueObject
{
	private String shiftCode;
	private String shiftDescription;
	private String shiftStartTime;
	private String shiftEndTime;
	private String shiftEntryDate;
	private String shiftSeatId;
	private String shiftIsValid;
	
	
	public String getShiftCode() {
		return shiftCode;
	}
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
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
	public String getShiftEntryDate() {
		return shiftEntryDate;
	}
	public void setShiftEntryDate(String shiftEntryDate) {
		this.shiftEntryDate = shiftEntryDate;
	}
	public String getShiftSeatId() {
		return shiftSeatId;
	}
	public void setShiftSeatId(String shiftSeatId) {
		this.shiftSeatId = shiftSeatId;
	}
	public String getShiftIsValid() {
		return shiftIsValid;
	}
	public void setShiftIsValid(String shiftIsValid) {
		this.shiftIsValid = shiftIsValid;
	}	
}
