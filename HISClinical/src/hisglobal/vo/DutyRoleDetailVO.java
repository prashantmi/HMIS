package hisglobal.vo;

public class DutyRoleDetailVO extends ValueObject {
	
	private String generatedRosterId;
	private String dutyAreaId;
	private String empID;
	private String roleID;
	private String shiftID;
	private String slNo;
	private String dutyRemarks;
	private String cancelRemarks;
	private String toDate;
	private String fromDate;
	private String isValid;
	private String hospitalCode;
	private String enterBy;
	private String entryDate;
	
	public String getDutyAreaId() {
		return dutyAreaId;
	}
	public void setDutyAreaId(String dutyAreaId) {
		this.dutyAreaId = dutyAreaId;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getShiftID() {
		return shiftID;
	}
	public void setShiftID(String shiftID) {
		this.shiftID = shiftID;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getDutyRemarks() {
		return dutyRemarks;
	}
	public void setDutyRemarks(String dutyRemarks) {
		this.dutyRemarks = dutyRemarks;
	}
	public String getCancelRemarks() {
		return cancelRemarks;
	}
	public void setCancelRemarks(String cancelRemarks) {
		this.cancelRemarks = cancelRemarks;
	}
	
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
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
	public String getEnterBy() {
		return enterBy;
	}
	public void setEnterBy(String enterBy) {
		this.enterBy = enterBy;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getGeneratedRosterId() {
		return generatedRosterId;
	}
	public void setGeneratedRosterId(String generatedRosterId) {
		this.generatedRosterId = generatedRosterId;
	}
	

}
