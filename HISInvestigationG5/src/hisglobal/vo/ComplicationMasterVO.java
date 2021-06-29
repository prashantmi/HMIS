package hisglobal.vo;

public class ComplicationMasterVO extends ValueObject
{
	private String complicationId;
	private String slNo;
	private String complicationTypeId;
	private String complication;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	private String complicationTypeName;
	
	public String getComplicationTypeName() {
		return complicationTypeName;
	}
	public void setComplicationTypeName(String complicationTypeName) {
		this.complicationTypeName = complicationTypeName;
	}
	public String getComplicationId() {
		return complicationId;
	}
	public void setComplicationId(String complicationId) {
		this.complicationId = complicationId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getComplicationTypeId() {
		return complicationTypeId;
	}
	public void setComplicationTypeId(String complicationTypeId) {
		this.complicationTypeId = complicationTypeId;
	}
	public String getComplication() {
		return complication;
	}
	public void setComplication(String complication) {
		this.complication = complication;
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
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifiedSeatID() {
		return lastModifiedSeatID;
	}
	public void setLastModifiedSeatID(String lastModifiedSeatID) {
		this.lastModifiedSeatID = lastModifiedSeatID;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}


}
