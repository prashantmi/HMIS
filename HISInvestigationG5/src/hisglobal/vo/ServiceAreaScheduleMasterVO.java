package hisglobal.vo;

public class ServiceAreaScheduleMasterVO extends ValueObject
{
	private String hospitalCode;
	private String isValid;	
	private String isValidLabel;
	private String maxSittingRequired;
	private String intervalType;
	private String intervalTypeLabel;
	private String intervalString;
	private String unitOfInterval;
	private String serviceAreaCode;
	private String serviceAreaName;
	private String msg;
	private String serviceProvided;
	private String serviceProvidedString;


	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public String getMaxSittingRequired() {
		return maxSittingRequired;
	}
	public void setMaxSittingRequired(String maxSittingRequired) {
		this.maxSittingRequired = maxSittingRequired;
	}
	public String getIntervalType() {
		return intervalType;
	}
	public void setIntervalType(String intervalType) {
		this.intervalType = intervalType;
	}
	public String getIntervalString() {
		return intervalString;
	}
	public void setIntervalString(String intervalString) {
		this.intervalString = intervalString;
	}
	public String getUnitOfInterval() {
		return unitOfInterval;
	}
	public void setUnitOfInterval(String unitOfInterval) {
		this.unitOfInterval = unitOfInterval;
	}
	public String getServiceAreaName() {
		return serviceAreaName;
	}
	public void setServiceAreaName(String serviceAreaName) {
		this.serviceAreaName = serviceAreaName;
	}
	public String getServiceProvided() {
		return serviceProvided;
	}
	public void setServiceProvided(String serviceProvided) {
		this.serviceProvided = serviceProvided;
	}
	public String getServiceProvidedString() {
		return serviceProvidedString;
	}
	public void setServiceProvidedString(String serviceProvidedString) {
		this.serviceProvidedString = serviceProvidedString;
	}
	public String getIsValidLabel() {
		return isValidLabel;
	}
	public void setIsValidLabel(String isValidLabel) {
		this.isValidLabel = isValidLabel;
	}
	public String getIntervalTypeLabel() {
		return intervalTypeLabel;
	}
	public void setIntervalTypeLabel(String intervalTypeLabel) {
		this.intervalTypeLabel = intervalTypeLabel;
	}
	public String getServiceAreaCode() {
		return serviceAreaCode;
	}
	public void setServiceAreaCode(String serviceAreaCode) {
		this.serviceAreaCode = serviceAreaCode;
	}
}
