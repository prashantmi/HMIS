package hisglobal.vo;

public class RegPatStatusMstVO extends ValueObject
{
	private String hospitalcode;
	private String statusCode;
	private String statusSlNo;
	private String patStatus;
	private String isActive;
	private String isMlc;

	public String getHospitalcode()
	{
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode)
	{
		this.hospitalcode = hospitalcode;
	}

	public String getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode(String statusCode)
	{
		this.statusCode = statusCode;
	}

	public String getStatusSlNo()
	{
		return statusSlNo;
	}

	public void setStatusSlNo(String statusSlNo)
	{
		this.statusSlNo = statusSlNo;
	}

	public String getPatStatus()
	{
		return patStatus;
	}

	public void setPatStatus(String patStatus)
	{
		this.patStatus = patStatus;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	public String getIsMlc()
	{
		return isMlc;
	}

	public void setIsMlc(String isMlc)
	{
		this.isMlc = isMlc;
	}

}
