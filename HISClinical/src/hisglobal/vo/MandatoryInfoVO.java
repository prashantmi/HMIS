package hisglobal.vo;

public class MandatoryInfoVO extends ValueObject{
	private String requisitionDNo;
	private String labTestcode;
	private String mandatoryCode;
	private String mandatoryValue;
	private String seatID;
	private String sequenceNo;
	private String entryDate;
	
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLabTestcode() {
		return labTestcode;
	}
	public void setLabTestcode(String labTestcode) {
		this.labTestcode = labTestcode;
	}
	public String getMandatoryCode() {
		return mandatoryCode;
	}
	public void setMandatoryCode(String mandatoryCode) {
		this.mandatoryCode = mandatoryCode;
	}
	public String getMandatoryValue() {
		return mandatoryValue;
	}
	public void setMandatoryValue(String mandatoryValue) {
		this.mandatoryValue = mandatoryValue;
	}
	public String getRequisitionDNo() {
		return requisitionDNo;
	}
	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

}
