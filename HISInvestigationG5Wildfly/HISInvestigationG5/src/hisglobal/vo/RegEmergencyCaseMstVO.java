package hisglobal.vo;

public class RegEmergencyCaseMstVO extends ValueObject
{

	private String hospitalcode;
	private String isActive;
	private String emergencyCase;
	private String isMlcReq;
	private String caseType;
	private String emergencyCode;
	private String emergencySlNo;

	public String getHospitalcode()
	{
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode)
	{
		this.hospitalcode = hospitalcode;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	public String getEmergencyCase()
	{
		return emergencyCase;
	}

	public void setEmergencyCase(String emergencyCase)
	{
		this.emergencyCase = emergencyCase;
	}

	public String getIsMlcReq()
	{
		return isMlcReq;
	}

	public void setIsMlcReq(String isMlcReq)
	{
		this.isMlcReq = isMlcReq;
	}

	public String getCaseType()
	{
		return caseType;
	}

	public void setCaseType(String caseType)
	{
		this.caseType = caseType;
	}

	public String getEmergencyCode()
	{
		return emergencyCode;
	}

	public void setEmergencyCode(String emergencyCode)
	{
		this.emergencyCode = emergencyCode;
	}

	public String getEmergencySlNo()
	{
		return emergencySlNo;
	}

	public void setEmergencySlNo(String emergencySlNo)
	{
		this.emergencySlNo = emergencySlNo;
	}

}
