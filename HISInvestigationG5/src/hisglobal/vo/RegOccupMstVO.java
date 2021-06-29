package hisglobal.vo;

public class RegOccupMstVO extends ValueObject
{

	private String occupCode;
	private String occupSlNo;
	private String occupName;

	private String hospitalcode;
	private String isActive;

	public String getHospitalcode()
	{
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode)
	{
		this.hospitalcode = hospitalcode;
	}

	public String getOccupCode()
	{
		return occupCode;
	}

	public void setOccupCode(String occupCode)
	{
		this.occupCode = occupCode;
	}

	public String getOccupName()
	{
		return occupName;
	}

	public void setOccupName(String occupName)
	{
		this.occupName = occupName;
	}

	public String getOccupSlNo()
	{
		return occupSlNo;
	}

	public void setOccupSlNo(String occupSlNo)
	{
		this.occupSlNo = occupSlNo;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

}
