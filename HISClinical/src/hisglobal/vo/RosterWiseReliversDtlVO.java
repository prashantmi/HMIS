
package hisglobal.vo;

public class RosterWiseReliversDtlVO extends ValueObject {

	
	
	private String rosterId;	
	private String generatedRosterId;	
	private String empId;
	private String reliverType;	
	private String entryMode;
	private String fromDate;
	private String toDate;
	private String hospitalCode;
	private String isValid;
	private String enterBySeatId;
	private String entryDate;
	
	
	/////////Extra Variables added for relivers duty assignment/////////////
	private String shiftId;
	private String areaCode;
	private String areaName;
	private String areaTypeCode;
	private String selectedDate;
	private String empName;
	

	
	/////////Extra getters and setters added for relivers duty assignment/////////////
	
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaTypeCode() {
		return areaTypeCode;
	}
	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}
	public String getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getRosterId() {
		return rosterId;
	}
	public void setRosterId(String rosterId) {
		this.rosterId = rosterId;
	}
	public String getGeneratedRosterId() {
		return generatedRosterId;
	}
	public void setGeneratedRosterId(String generatedRosterId) {
		this.generatedRosterId = generatedRosterId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getReliverType() {
		return reliverType;
	}
	public void setReliverType(String reliverType) {
		this.reliverType = reliverType;
	}
	public String getEntryMode() {
		return entryMode;
	}
	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getEnterBySeatId() {
		return enterBySeatId;
	}
	public void setEnterBySeatId(String enterBySeatId) {
		this.enterBySeatId = enterBySeatId;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	
	
	
	

	
	
	
	
	
	
	
	

	
}
