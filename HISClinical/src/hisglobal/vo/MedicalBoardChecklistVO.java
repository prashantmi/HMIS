package hisglobal.vo;

public class MedicalBoardChecklistVO extends ValueObject
{
	private String reqID;
	private String checklistID;
	private String certificateTypeID;
	private String remarks;
	private String isCompulsory;
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
	public String getReqID() {
		return reqID;
	}
	public void setReqID(String reqID) {
		this.reqID = reqID;
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

	
}
