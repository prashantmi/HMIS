package hisglobal.vo;

public class IcdSnomedMappingMasterVO extends ValueObject 
{
	
	private String alertName;
	private String patAlertId;
	private String seatID;
	private String entryDate;
	private String isValid;
	private String serialNo;
	private String isActive;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String icdCode;
	private String conceptID;
	private String icdName;
	private String conceptName;
	private String[] selsnomed;
	private String[] selSnomedName;
	private String mappingSq;
	
	public String getAlertName() {
		return alertName;
	}
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}
	public String getPatAlertId() {
		return patAlertId;
	}
	public void setPatAlertId(String patAlertId) {
		this.patAlertId = patAlertId;
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
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedSeatId() {
		return lastModifiedSeatId;
	}
	public void setLastModifiedSeatId(String lastModifiedSeatId) {
		this.lastModifiedSeatId = lastModifiedSeatId;
	}
	public String getIcdCode() {
		return icdCode;
	}
	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
	}
	public String getConceptID() {
		return conceptID;
	}
	public void setConceptID(String conceptID) {
		this.conceptID = conceptID;
	}
	public String getIcdName() {
		return icdName;
	}
	public void setIcdName(String icdName) {
		this.icdName = icdName;
	}
	public String getConceptName() {
		return conceptName;
	}
	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}
	
	public String[] getSelsnomed() {
		return selsnomed;
	}
	public void setSelsnomed(String[] selsnomed) {
		this.selsnomed = selsnomed;
	}
	
	public String[] getSelSnomedName() {
		return selSnomedName;
	}
	public void setSelSnomedName(String[] selSnomedName) {
		this.selSnomedName = selSnomedName;
	}
	public String getMappingSq() {
		return mappingSq;
	}
	public void setMappingSq(String mappingSq) {
		this.mappingSq = mappingSq;
	}

	
}
