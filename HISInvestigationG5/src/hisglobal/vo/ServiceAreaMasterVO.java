package hisglobal.vo;

public class ServiceAreaMasterVO extends ValueObject
{
	private String deptName;
	private String deptCode;
	private String serviceAreaCode;
	private String serviceAreaName;
	private String hospitalCode;
	private String isActive;
	private String isScheduleRequired;
	private String isScheduleRequiredLabel;
	private String isActiveLabel;
	private String strStoreId;
	private String strStoreName;
	private String msg;
	private String strMsgType;
	

	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	public String getIsScheduleRequired() {
		return isScheduleRequired;
	}
	public void setIsScheduleRequired(String isScheduleRequired) {
		this.isScheduleRequired = isScheduleRequired;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getIsScheduleRequiredLabel() {
		return isScheduleRequiredLabel;
	}
	public void setIsScheduleRequiredLabel(String isScheduleRequiredLabel) {
		this.isScheduleRequiredLabel = isScheduleRequiredLabel;
	}
	public String getIsActiveLabel() {
		return isActiveLabel;
	}
	public void setIsActiveLabel(String isActiveLabel) {
		this.isActiveLabel = isActiveLabel;
	}
	public String getStrStoreName() {
		return strStoreName;
	}
	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
}
