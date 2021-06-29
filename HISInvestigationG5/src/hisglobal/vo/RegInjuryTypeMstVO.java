package hisglobal.vo;

public class RegInjuryTypeMstVO extends ValueObject
{

	private String injuryTypeCode;
	private String injuryTypeSlNo;
	private String injuryDescription;
	private String isActive;
	private String hospitalcode;
	private String isMlc;
	

	public String getInjuryTypeCode()
	{
		return injuryTypeCode;
	}

	public void setInjuryTypeCode(String injuryTypeCode)
	{
		this.injuryTypeCode = injuryTypeCode;
	}

	public String getInjuryTypeSlNo()
	{
		return injuryTypeSlNo;
	}

	public void setInjuryTypeSlNo(String injuryTypeSlNo)
	{
		this.injuryTypeSlNo = injuryTypeSlNo;
	}

	public String getInjuryDescription()
	{
		return injuryDescription;
	}

	public void setInjuryDescription(String injuryDescription)
	{
		this.injuryDescription = injuryDescription;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	public String getHospitalcode()
	{
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode)
	{
		this.hospitalcode = hospitalcode;
	}

	public String getIsMlc() {
		return isMlc;
	}

	public void setIsMlc(String isMlc) {
		this.isMlc = isMlc;
	}
}
