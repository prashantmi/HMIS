package hisglobal.vo;

public class ScreeningOfOtherTTDReportVO extends ValueObject
{
	private String testName;
	private String unitTested;
	private String foundPositive;
	private String totalMale;
	private String totalFemale;
	private String maleFoundPositive;
	private String femaleFoundPositive;
	public String getTestName()
	{
		return testName;
	}
	public void setTestName(String testName)
	{
		this.testName = testName;
	}
	public String getUnitTested()
	{
		return unitTested;
	}
	public void setUnitTested(String unitTested)
	{
		this.unitTested = unitTested;
	}
	public String getFoundPositive()
	{
		return foundPositive;
	}
	public void setFoundPositive(String foundPositive)
	{
		this.foundPositive = foundPositive;
	}
	public String getTotalMale()
	{
		return totalMale;
	}
	public void setTotalMale(String totalMale)
	{
		this.totalMale = totalMale;
	}
	public String getTotalFemale()
	{
		return totalFemale;
	}
	public void setTotalFemale(String totalFemale)
	{
		this.totalFemale = totalFemale;
	}
	public String getMaleFoundPositive()
	{
		return maleFoundPositive;
	}
	public void setMaleFoundPositive(String maleFoundPositive)
	{
		this.maleFoundPositive = maleFoundPositive;
	}
	public String getFemaleFoundPositive()
	{
		return femaleFoundPositive;
	}
	public void setFemaleFoundPositive(String femaleFoundPositive)
	{
		this.femaleFoundPositive = femaleFoundPositive;
	}
		
	
}
