package hisglobal.vo;

public class Consultants
{

	private String empNo;
	private String empName;

	public Consultants(String empNo, String empName)
	{
		this.empNo = empNo;
		this.empName = empName;
	}

	public String getEmpName()
	{
		return empName;
	}

	public void setEmpName(String empName)
	{
		this.empName = empName;
	}

	public String getEmpNo()
	{
		return empNo;
	}

	public void setEmpNo(String empNo)
	{
		this.empNo = empNo;
	}

}
