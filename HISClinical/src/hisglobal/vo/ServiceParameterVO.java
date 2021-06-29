package hisglobal.vo;

public class ServiceParameterVO extends ValueObject
{
	private String serviceCode;
	private String parameterId;
	private String parameterName;
	private String parentId;
	private String parameterLevel;

	public String getServiceCode()
	{
		return serviceCode;
	}

	public void setServiceCode(String serviceCode)
	{
		this.serviceCode = serviceCode;
	}

	public String getParameterId()
	{
		return parameterId;
	}

	public void setParameterId(String parameterId)
	{
		this.parameterId = parameterId;
	}

	public String getParameterName()
	{
		return parameterName;
	}

	public void setParameterName(String parameterName)
	{
		this.parameterName = parameterName;
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public String getParameterLevel()
	{
		return parameterLevel;
	}

	public void setParameterLevel(String parameterLevel)
	{
		this.parameterLevel = parameterLevel;
	}

}
