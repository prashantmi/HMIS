package hisglobal.vo;

public class DutyRosterAreaEmployeeVO extends ValueObject{

	
	
	 
	private String empId;
	private String areaTypeCode;
	private String areaCode;
	private String dutyRoleId;	
	private String hospitalCode;
	private String seatId;
	private String entryDate;
	private String isValid;
	
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getAreaTypeCode() {
		return areaTypeCode;
	}
	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getDutyRoleId() {
		return dutyRoleId;
	}
	public void setDutyRoleId(String dutyRoleId) {
		this.dutyRoleId = dutyRoleId;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
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
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}


	
	
}
