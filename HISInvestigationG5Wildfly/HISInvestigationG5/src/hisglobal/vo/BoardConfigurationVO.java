package hisglobal.vo;

public class BoardConfigurationVO extends ValueObject{

	 private String departmentCode;
	 private String departmentUnitCode;
	 private String roomCode;
	 private String departmentIncharge;
	 private String hospitalCode;

	 
	 public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getDepartmentIncharge() {
		return departmentIncharge;
	}
	public void setDepartmentIncharge(String departmentIncharge) {
		this.departmentIncharge = departmentIncharge;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

}
