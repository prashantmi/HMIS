package hisglobal.vo;

public class RosterTypeMstVO extends ValueObject{

	private String rosterTypeId;
	private String hospitalcode;
	private String serialNo;
	private String rosterTypeName;
	private String rosterCategory;
	private String areaType;
	private String dutyType;
	private String chk;
	private String isActive;
	private String rosterGenerationMethod;
	private String reliverMode;
	
	
	
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
	public String getRosterTypeName() {
		return rosterTypeName;
	}
	public void setRosterTypeName(String rosterTypeName) {
		this.rosterTypeName = rosterTypeName;
	}
	public String getRosterCategory() {
		return rosterCategory;
	}
	public void setRosterCategory(String rosterCategory) {
		this.rosterCategory = rosterCategory;
	}
	
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getDutyType() {
		return dutyType;
	}
	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}
	public String getRosterTypeId() {
		return rosterTypeId;
	}
	public void setRosterTypeId(String rosterTypeId) {
		this.rosterTypeId = rosterTypeId;
	}
	
	public String getRosterGenerationMethod() {
		return rosterGenerationMethod;
	}
	public void setRosterGenerationMethod(String rosterGenerationMethod) {
		this.rosterGenerationMethod = rosterGenerationMethod;
	}
	
	public String getReliverMode() {
		return reliverMode;
	}
	public void setReliverMode(String reliverMode) {
		this.reliverMode = reliverMode;
	}
	
	
}
