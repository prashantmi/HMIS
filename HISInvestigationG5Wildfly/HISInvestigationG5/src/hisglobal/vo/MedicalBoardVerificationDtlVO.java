package hisglobal.vo;

public class MedicalBoardVerificationDtlVO extends ValueObject
{
	private String reqId;
	private String sereialNo;
	private String certificateResult;
	private String opinionCode;
	private String opinion;
	private String verifyEmpId;
	private String verifyDate;
	private String remarks;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String hospitalCode;
	
	private String opinionDesc;
	private String empId;
	private String empName;
	
	
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getSereialNo() {
		return sereialNo;
	}
	public void setSereialNo(String sereialNo) {
		this.sereialNo = sereialNo;
	}
	public String getCertificateResult() {
		return certificateResult;
	}
	public void setCertificateResult(String certificateResult) {
		this.certificateResult = certificateResult;
	}
	public String getOpinionCode() {
		return opinionCode;
	}
	public void setOpinionCode(String opinionCode) {
		this.opinionCode = opinionCode;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getVerifyEmpId() {
		return verifyEmpId;
	}
	public void setVerifyEmpId(String verifyEmpId) {
		this.verifyEmpId = verifyEmpId;
	}
	public String getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getOpinionDesc() {
		return opinionDesc;
	}
	public void setOpinionDesc(String opinionDesc) {
		this.opinionDesc = opinionDesc;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
