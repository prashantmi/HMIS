package hisglobal.vo;

public class Process_Entr_TemplateDtllVO extends ValueObject
{
	private String serviceReqNo;
	private String serviceAreaCode;
	private String[] procedureCode;
	private String patCrNo;
	private String[] serviceDate; 
	private String paraId;
	private String paraValue;
	private String templateId;
	private String visit;
	private String visitNo;
	
	public String getServiceReqNo()
	{
		return serviceReqNo;
	}
	public void setServiceReqNo(String serviceReqNo)
	{
		this.serviceReqNo = serviceReqNo;
	}
	public String getPatCrNo()
	{
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}
	public String[] getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(String[] serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getParaValue() {
		return paraValue;
	}
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getVisit() {
		return visit;
	}
	public void setVisit(String visit) {
		this.visit = visit;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public String getServiceAreaCode() {
		return serviceAreaCode;
	}
	public void setServiceAreaCode(String serviceAreaCode) {
		this.serviceAreaCode = serviceAreaCode;
	}
	public String[] getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String[] procedureCode) {
		this.procedureCode = procedureCode;
	}
		
}
