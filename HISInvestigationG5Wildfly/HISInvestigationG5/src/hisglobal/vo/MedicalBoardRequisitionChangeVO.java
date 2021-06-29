package hisglobal.vo;

public class MedicalBoardRequisitionChangeVO extends ValueObject
{
	private String reqID;
	private String slNo;
	private String certificateTypeID;
	private String certificateTypeName;
	private String oldExamDate;
	private String newExamDate;
	private String changeReason;
	private String reqMode;
	private String approvedDate;
	private String approvedBy;
	
	
	public String getReqID() {
		return reqID;
	}
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}
	
	public String getCertificateTypeID() {
		return certificateTypeID;
	}
	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}
	
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getCertificateTypeName() {
		return certificateTypeName;
	}
	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}
	public String getOldExamDate() {
		return oldExamDate;
	}
	public void setOldExamDate(String oldExamDate) {
		this.oldExamDate = oldExamDate;
	}
	public String getNewExamDate() {
		return newExamDate;
	}
	public void setNewExamDate(String newExamDate) {
		this.newExamDate = newExamDate;
	}
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	public String getReqMode() {
		return reqMode;
	}
	public void setReqMode(String reqMode) {
		this.reqMode = reqMode;
	}
	public String getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	
	
}
