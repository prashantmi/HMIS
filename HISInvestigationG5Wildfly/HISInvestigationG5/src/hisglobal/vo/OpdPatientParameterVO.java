package hisglobal.vo;

public class OpdPatientParameterVO extends ValueObject
{
	private String crNo;
	private String templateId;
	private String paraId;
	private String paraValue;

	public String getCrNo()
	{
		return crNo;
	}

	public void setCrNo(String crNo)
	{
		this.crNo = crNo;
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

	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}
}
