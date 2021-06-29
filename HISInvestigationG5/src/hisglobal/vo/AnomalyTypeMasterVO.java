package hisglobal.vo;

public class AnomalyTypeMasterVO extends ValueObject
{
	private String anomalyTypeID;
	private String slNo;
	private String anomalyType;
	private String isActive;
	private String hospitalCode;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public String getAnomalyTypeID() {
		return anomalyTypeID;
	}
	public void setAnomalyTypeID(String anomalyTypeID) {
		this.anomalyTypeID = anomalyTypeID;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getAnomalyType() {
		return anomalyType;
	}
	public void setAnomalyType(String anomalyType) {
		this.anomalyType = anomalyType;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
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

	
	
}
