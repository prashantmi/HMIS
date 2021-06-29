package hisglobal.vo;

public class PostDisasterVO extends ValueObject {

	private String disasterID;
	private String slNo;
	private String remarks;
	private String seatId;
	private String isValid;
	private String entryDate;
	
	public String getDisasterID() {
		return disasterID;
	}
	public void setDisasterID(String disasterID) {
		this.disasterID = disasterID;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
}
