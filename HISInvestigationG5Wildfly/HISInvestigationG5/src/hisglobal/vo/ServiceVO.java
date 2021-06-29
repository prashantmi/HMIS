package hisglobal.vo;

public class ServiceVO extends ValueObject
{
	private String serviceAreaCode;
	private String serviceCode;
	private String serviceName;

	public String getServiceAreaCode()
	{
		return serviceAreaCode;
	}

	public void setServiceAreaCode(String serviceAreaCode)
	{
		this.serviceAreaCode = serviceAreaCode;
	}

	public String getServiceCode()
	{
		return serviceCode;
	}

	public void setServiceCode(String serviceCode)
	{
		this.serviceCode = serviceCode;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

}
