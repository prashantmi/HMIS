
package hisglobal.vo;

public class DutyRosterOtherAreaMstVO extends ValueObject{

    private String otherAreaCode;
	private String otherAreaName;
	private String hospitalCode;
	private String isValid;
	private String seatId;	
	private String lastModSeatId;
	private String lastModDate;
	private String entryDate;
	private String serialNo;
	
	
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	public String getOtherAreaCode() {
		return otherAreaCode;
	}
	public void setOtherAreaCode(String otherAreaCode) {
		this.otherAreaCode = otherAreaCode;
	}
	public String getOtherAreaName() {
		return otherAreaName;
	}
	public void setOtherAreaName(String otherAreaName) {
		this.otherAreaName = otherAreaName;
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
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getLastModSeatId() {
		return lastModSeatId;
	}
	public void setLastModSeatId(String lastModSeatId) {
		this.lastModSeatId = lastModSeatId;
	}
	public String getLastModDate() {
		return lastModDate;
	}
	public void setLastModDate(String lastModDate) {
		this.lastModDate = lastModDate;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
	
}
