package hisglobal.vo;

public class ServiceAreaTemplateVO extends ValueObject
{

	private String templateId;
	private String paraId;
	private String paraValue;
	private String visitNo="1";//set for non sitting proc..
	
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public String getTemplateId()
	{
		return templateId;
	}
	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}
	public String getParaId()
	{
		return paraId;
	}
	public void setParaId(String paraId)
	{
		this.paraId = paraId;
	}
	public String getParaValue()
	{
		return paraValue;
	}
	public void setParaValue(String paraValue)
	{
		this.paraValue = paraValue;
	}	
}
