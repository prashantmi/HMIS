package hisglobal.vo;

public class RosterShiftMstVO extends ValueObject{

	private String chk;
	private String isActive;
	private String hospitalcode;
	private String serialNo;

	private String rosterTypeId;
	private String shiftId;
	private String selectedShiftId;
	
	//extra variable added
	private String shiftType;	
	
	
	
	
	
	public String getSelectedShiftId() {
		return selectedShiftId;
	}
	public void setSelectedShiftId(String selectedShiftId) {
		this.selectedShiftId = selectedShiftId;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRosterTypeId() {
		return rosterTypeId;
	}
	public void setRosterTypeId(String rosterTypeId) {
		this.rosterTypeId = rosterTypeId;
	}
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	public String getShiftType() {
		return shiftType;
	}
	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}
	
}
