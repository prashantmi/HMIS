 

package new_investigation.reportGenerator.TemplateHelper.vo;

 

 
public class UserVO extends ValueObject
{
	//private TransactionVO transactionInfo = new TransactionVO();
	private String userId;
	private String userSeatId;
	private String userName;
	private String seatId;
	private String userType;
	private String userLevel;
	private String userEmpID;
	private String tariffID;
	private String hospitalCode;
	private String ipAddress;
	private String moduleId;

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	 
	public String getTariffID()
	{
		return tariffID;
	}

	 
	public void setTariffID(String tariffID)
	{
		this.tariffID = tariffID;
	}

	 
 
	 
 
	 
	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	 
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	 
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	 
	public void setUserLevel(String userLevel)
	{
		this.userLevel = userLevel;
	}

	 
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	 
	public void setUserEmpID(String userEmpID)
	{
		this.userEmpID = userEmpID;
	}

	 
	public String getUserType()
	{
		return userType;
	}

	 
	public String getSeatId()
	{
		return seatId;
	}

	 
	public String getUserName()
	{
		return userName;
	}

	 
	public String getUserLevel()
	{
		return userLevel;
	}

	 
	public String getUserId()
	{
		return userId;
	}

	 
	public String getUserEmpID()
	{
		return userEmpID;
	}

	 
	public String getUserSeatId()
	{
		return userSeatId;
	}

	public void setUserSeatId(String userSeatId)
	{
		this.userSeatId = userSeatId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

}
