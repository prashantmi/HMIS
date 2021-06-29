package hisglobal.vo;

public class MBCertificateChecklistVO extends ValueObject
{
	
	private String certificateTypeID;
	private String checklistID;
	private String remarks;
	private String isCompulsory;
	private String isCompulsoryFromChecklist;
	private String isCompulsoryLabel;
	private String serialNo;
	private String checklist;
	private String isValid;
	private String hospitalCode;

	public String getChecklist() {
		return checklist;
	}
	public void setChecklist(String checklist) {
		this.checklist = checklist;
	}
	public String getChecklistID() {
		return checklistID;
	}
	public void setChecklistID(String checklistID) {
		this.checklistID = checklistID;
	}
	public String getCertificateTypeID() {
		return certificateTypeID;
	}
	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsCompulsory() {
		return isCompulsory;
	}
	public void setIsCompulsory(String isCompulsory) {
		this.isCompulsory = isCompulsory;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsCompulsoryLabel() {
		return isCompulsoryLabel;
	}
	public void setIsCompulsoryLabel(String isCompulsoryLabel) {
		this.isCompulsoryLabel = isCompulsoryLabel;
	}
	public String getIsCompulsoryFromChecklist() {
		return isCompulsoryFromChecklist;
	}
	public void setIsCompulsoryFromChecklist(String isCompulsoryFromChecklist) {
		this.isCompulsoryFromChecklist = isCompulsoryFromChecklist;
	}

	
}
