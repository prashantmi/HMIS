package hisglobal.vo;

public class MbInvestigationMappingMstVO extends ValueObject{

	private String certificateTypeID;
	private String hospitalCode;
	private String sereialNo;
	private String labTestCode;
	private String isValid;
	private String isOptional;
	private String seatId;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifySeatId;
	
	private String isOptionalName;
	private String labTestName;
	
	
	public String getIsOptionalName() {
		return isOptionalName;
	}
	public void setIsOptionalName(String isOptionalName) {
		this.isOptionalName = isOptionalName;
	}
	public String getLabTestName() {
		return labTestName;
	}
	public void setLabTestName(String labTestName) {
		this.labTestName = labTestName;
	}
	public String getCertificateTypeID() {
		return certificateTypeID;
	}
	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}
	public String getLabTestCode() {
		return labTestCode;
	}
	public void setLabTestCode(String labTestCode) {
		this.labTestCode = labTestCode;
	}
	public String getIsOptional() {
		return isOptional;
	}
	public void setIsOptional(String isOptional) {
		this.isOptional = isOptional;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSereialNo() {
		return sereialNo;
	}
	public void setSereialNo(String sereialNo) {
		this.sereialNo = sereialNo;
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
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifySeatId() {
		return lastModifySeatId;
	}
	public void setLastModifySeatId(String lastModifySeatId) {
		this.lastModifySeatId = lastModifySeatId;
	}
	
}
