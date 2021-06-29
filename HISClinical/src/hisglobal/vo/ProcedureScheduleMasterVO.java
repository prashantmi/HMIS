package hisglobal.vo;

public class ProcedureScheduleMasterVO extends ValueObject
{
	private String hospitalCode;
	private String procedureName;
	private String procedureCode;
	private String isValid;	
	private String maxSittingRequired;
	private String intervalType;
	private String intervalTypeLabel;
	private String intervalString;
	private String unitOfInterval;
	private String msg;
	private String serviceProvided;
	private String serviceProvidedString;
	private String[] strServiceToBeGiven;//services mapped in proc sch mst
	private String[] strInterval;//interval mapped in proc sch mst
	private String strServiceToBeGivenCodes;//service codes concatenated with ^..used only for immunizable schedule..
	private String strAgeRange;//age range concatenated with ^..used only for immunizable schedule..
	private String strIsProcImmunizable="0";//0 non immunizable..1 immunizable..
	
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
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
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
	public String getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}
	public String getIntervalTypeLabel() {
		return intervalTypeLabel;
	}
	public void setIntervalTypeLabel(String intervalTypeLabel) {
		this.intervalTypeLabel = intervalTypeLabel;
	}
	public String[] getStrServiceToBeGiven() {
		return strServiceToBeGiven;
	}
	public void setStrServiceToBeGiven(String[] strServiceToBeGiven) {
		this.strServiceToBeGiven = strServiceToBeGiven;
	}
	public String[] getStrInterval() {
		return strInterval;
	}
	public void setStrInterval(String[] strInterval) {
		this.strInterval = strInterval;
	}
	public String getStrServiceToBeGivenCodes() {
		return strServiceToBeGivenCodes;
	}
	public void setStrServiceToBeGivenCodes(String strServiceToBeGivenCodes) {
		this.strServiceToBeGivenCodes = strServiceToBeGivenCodes;
	}
	public String getStrAgeRange() {
		return strAgeRange;
	}
	public void setStrAgeRange(String strAgeRange) {
		this.strAgeRange = strAgeRange;
	}
	public String getStrIsProcImmunizable() {
		return strIsProcImmunizable;
	}
	public void setStrIsProcImmunizable(String strIsProcImmunizable) {
		this.strIsProcImmunizable = strIsProcImmunizable;
	}
}
