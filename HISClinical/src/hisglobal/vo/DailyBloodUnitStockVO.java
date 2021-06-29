package hisglobal.vo;

public class DailyBloodUnitStockVO extends ValueObject
{
	private String bloodGroup;
	private String screenUnitNo;
	private String unscreenUnitNo;
	

	public String getBloodGroup()
	{
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}
	public String getScreenUnitNo()
	{
		return screenUnitNo;
	}
	public void setScreenUnitNo(String screenUnitNo)
	{
		this.screenUnitNo = screenUnitNo;
	}
	public String getUnscreenUnitNo()
	{
		return unscreenUnitNo;
	}
	public void setUnscreenUnitNo(String unscreenUnitNo)
	{
		this.unscreenUnitNo = unscreenUnitNo;
	}
	
}
