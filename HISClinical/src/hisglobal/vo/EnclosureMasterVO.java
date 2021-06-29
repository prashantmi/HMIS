package hisglobal.vo;

public class EnclosureMasterVO extends ValueObject
{
	private String enclosureId;
	private String slNo;
	private String enclosure;
	private String maxPages;
	private String isValid;
	private String seatId;
	private String lastModifySeatId;
	private String lastModifyDate;
	private String hospitalCode;
	private String entryDate;
	
	
	public String getEnclosureId() {
		return enclosureId;
	}
	public void setEnclosureId(String enclosureId) {
		this.enclosureId = enclosureId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getEnclosure() {
		return enclosure;
	}
	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}
	public String getMaxPages() {
		return maxPages;
	}
	public void setMaxPages(String maxPages) {
		this.maxPages = maxPages;
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
	public String getLastModifySeatId() {
		return lastModifySeatId;
	}
	public void setLastModifySeatId(String lastModifySeatId) {
		this.lastModifySeatId = lastModifySeatId;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
	
}
