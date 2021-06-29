package hisglobal.vo;

public class RosterDtlVO extends ValueObject {

	
      
          

	private String generatedRosterId;	
	private String slNo;	
	private String rosterId;	
	private String areaCode;
	private String areaTypeCode;
	private String empId;
	private String shiftId;
	private String startDateTime;
	private String endDateTime;
	private String roleId;
	private String dutyDone;
	private String dutyRemarks;
	private String cancelRemarks;
	private String isActive;
	private String dutyType;
	private String enterBySeatId;
	private String entryDate;
	private String hospitalCode;
	private String fromDate;
	private String toDate;
	
	

	
	///Extra Variables added for generating the report and making the monthwise emp Roster
	private String empName;
	private String shiftName;
	private String shiftShortName; 
	private String shiftStartTime;
	private String shiftEndTime; 
	private String shiftType;	
	private String rosterName;	
	private String areaName;
	private String areaTypeName;
	private String rosterCatgId;	
	private String rosterCatgName;	
	private String shiftDesc;
	
	/* 
	 * Added By Anant Patel 
	 * on 26th Nov 2014
	 * For monthWiseEmpRosterReport
	  */
	private String incharge;
	///Extra Getters and setters added for generating the report and using in monthwise empl roster 
	
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public String getShiftShortName() {
		return shiftShortName;
	}
	public void setShiftShortName(String shiftShortName) {
		this.shiftShortName = shiftShortName;
	}
	
	public String getShiftStartTime() {
		return shiftStartTime;
	}
	public void setShiftStartTime(String shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}
	public String getShiftEndTime() {
		return shiftEndTime;
	}
	public void setShiftEndTime(String shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}
	public String getShiftType() {
		return shiftType;
	}
	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}
	
	public String getRosterName() {
		return rosterName;
	}
	public void setRosterName(String rosterName) {
		this.rosterName = rosterName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaTypeName() {
		return areaTypeName;
	}
	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}
	
	public String getRosterCatgId() {
		return rosterCatgId;
	}
	public void setRosterCatgId(String rosterCatgId) {
		this.rosterCatgId = rosterCatgId;
	}
	public String getRosterCatgName() {
		return rosterCatgName;
	}
	public void setRosterCatgName(String rosterCatgName) {
		this.rosterCatgName = rosterCatgName;
	}
	
	public String getShiftDesc() {
		return shiftDesc;
	}
	public void setShiftDesc(String shiftDesc) {
		this.shiftDesc = shiftDesc;
	}
	
	/////////regular getter and setters
	
	
	
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getRosterId() {
		return rosterId;
	}
	public void setRosterId(String rosterId) {
		this.rosterId = rosterId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaTypeCode() {
		return areaTypeCode;
	}
	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getDutyDone() {
		return dutyDone;
	}
	public void setDutyDone(String dutyDone) {
		this.dutyDone = dutyDone;
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
	public String getGeneratedRosterId() {
		return generatedRosterId;
	}
	public void setGeneratedRosterId(String generatedRosterId) {
		this.generatedRosterId = generatedRosterId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getDutyType() {
		return dutyType;
	}
	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
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
	
	public String getIncharge() {
		return incharge;
	}
	public void setIncharge(String incharge) {
		this.incharge = incharge;
	}
	
	
	
	

	
}
