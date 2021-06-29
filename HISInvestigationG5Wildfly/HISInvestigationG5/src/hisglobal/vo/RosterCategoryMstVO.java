package hisglobal.vo;

public class RosterCategoryMstVO extends ValueObject{

	
	
	
	
    private String rosterCategoryCode;
	private String hospitalCode;
	private String serialNo;
	private String rosterCategoryName;
	private String rosterMainCategoryCode;	
	private String isValid;
	private String empNo;
	private String seatId;	
	private String entryDate;
	private String desigId;
	private String lastModDate;
	private String lastModSeatId;
	private String rosterMode;
	private String noOfHours;
	private String maxOff;
	private String maxContinuousOff;	 
	 private String dutyOffFlag;
	 private String dutyOffAccount;
	
	 
	 
	public String getRosterMainCategoryCode() {
		return rosterMainCategoryCode;
	}
	public void setRosterMainCategoryCode(String rosterMainCategoryCode) {
		this.rosterMainCategoryCode = rosterMainCategoryCode;
	}
	
	public String getRosterMode() {
		return rosterMode;
	}
	public void setRosterMode(String rosterMode) {
		this.rosterMode = rosterMode;
	}
	public String getDutyOffFlag() {
		return dutyOffFlag;
	}
	public void setDutyOffFlag(String dutyOffFlag) {
		this.dutyOffFlag = dutyOffFlag;
	}
	public String getDutyOffAccount() {
		return dutyOffAccount;
	}
	public void setDutyOffAccount(String dutyOffAccount) {
		this.dutyOffAccount = dutyOffAccount;
	}
	public String getMaxOff() {
		return maxOff;
	}
	public void setMaxOff(String maxOff) {
		this.maxOff = maxOff;
	}
	public String getMaxContinuousOff() {
		return maxContinuousOff;
	}
	public void setMaxContinuousOff(String maxContinuousOff) {
		this.maxContinuousOff = maxContinuousOff;
	}
	public String getNoOfHours() {
		return noOfHours;
	}
	public void setNoOfHours(String noOfHours) {
		this.noOfHours = noOfHours;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRosterCategoryCode() {
		return rosterCategoryCode;
	}
	public void setRosterCategoryCode(String rosterCategoryCode) {
		this.rosterCategoryCode = rosterCategoryCode;
	}
	public String getRosterCategoryName() {
		return rosterCategoryName;
	}
	public void setRosterCategoryName(String rosterCategoryName) {
		this.rosterCategoryName = rosterCategoryName;
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
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getLastModSeatId() {
		return lastModSeatId;
	}
	public void setLastModSeatId(String lastModSeatId) {
		this.lastModSeatId = lastModSeatId;
	}
	public String getLastModDate() {
		return lastModDate;
	}
	public void setLastModDate(String lastModDate) {
		this.lastModDate = lastModDate;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
	
}
