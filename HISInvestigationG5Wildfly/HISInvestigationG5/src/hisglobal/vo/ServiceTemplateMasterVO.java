package hisglobal.vo;

public class ServiceTemplateMasterVO extends ValueObject
{
	private String serviceAreaCode;
	private String serviceAreaName;
	private String hospitalCode;
	private String templateName;
	private String procedureName;
	private String isActive="";
	private String procedureCode;
	private String templateCode;
	private String isActiveLabel="";
	private String templateCodeOlder="";
	
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
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
	public String getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getIsActiveLabel() {
		return isActiveLabel;
	}
	public void setIsActiveLabel(String isActiveLabel) {
		this.isActiveLabel = isActiveLabel;
	}
	public String getTemplateCodeOlder() {
		return templateCodeOlder;
	}
	public void setTemplateCodeOlder(String templateCodeOlder) {
		this.templateCodeOlder = templateCodeOlder;
	}
}
