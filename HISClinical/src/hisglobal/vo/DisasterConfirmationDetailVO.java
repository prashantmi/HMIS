package hisglobal.vo;

public class DisasterConfirmationDetailVO extends ValueObject {

	private String disasterID;
	private String slNo;
	private String confirmBy;
	private String confirmDateAndTime;
	private String personName;
	private String personDetail;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String hospitalCode;
	

	
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
	public String getConfirmBy() {
		return confirmBy;
	}
	public void setConfirmBy(String confirmBy) {
		this.confirmBy = confirmBy;
	}
	public String getConfirmDateAndTime() {
		return confirmDateAndTime;
	}
	public void setConfirmDateAndTime(String confirmDateAndTime) {
		this.confirmDateAndTime = confirmDateAndTime;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonDetail() {
		return personDetail;
	}
	public void setPersonDetail(String personDetail) {
		this.personDetail = personDetail;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
	
}
