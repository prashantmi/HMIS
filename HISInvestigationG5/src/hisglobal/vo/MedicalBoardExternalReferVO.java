package hisglobal.vo;

public class MedicalBoardExternalReferVO extends ValueObject {
	
	 private String reqID;
	 private String slNo;
	 private String extReferTo;
	 private String referReason;
	 private String hospitalCode;
	 private String isValid;
	 private String seatId;
	 private String entryDate;
	 
	public String getReqID() {
		return reqID;
	}
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getExtReferTo() {
		return extReferTo;
	}
	public void setExtReferTo(String extReferTo) {
		this.extReferTo = extReferTo;
	}
	public String getReferReason() {
		return referReason;
	}
	public void setReferReason(String referReason) {
		this.referReason = referReason;
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
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

}
