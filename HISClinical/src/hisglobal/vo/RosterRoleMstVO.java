package hisglobal.vo;

public class RosterRoleMstVO extends ValueObject{

	private String hospitalcode;
	private String serialNo;

	private String rosterTypeId;
	private String dutyRole;
	
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
	public String getDutyRole() {
		return dutyRole;
	}
	public void setDutyRole(String dutyRole) {
		this.dutyRole = dutyRole;
	}
		
}
