package hisglobal.vo;

public class ProcedureEquipmentMasterVO extends ValueObject
{
	private String serviceAreaCode;
	private String serviceAreaName;
	private String hospitalCode;
	private String equipmentName;
	private String maxTime;
	private String uot;
	private String procedureName;
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}
	public String getUot() {
		return uot;
	}
	public void setUot(String uot) {
		this.uot = uot;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	private String isActive;

	public String getServiceAreaCode()
	{
		return serviceAreaCode;
	}
	public void setServiceAreaCode(String serviceAreaCode)
	{
		this.serviceAreaCode = serviceAreaCode;
	}
	public String getServiceAreaName()
	{
		return serviceAreaName;
	}
	public void setServiceAreaName(String serviceAreaName)
	{
		this.serviceAreaName = serviceAreaName;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
