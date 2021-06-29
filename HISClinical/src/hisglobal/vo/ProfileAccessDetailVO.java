package hisglobal.vo;

public class ProfileAccessDetailVO extends ValueObject
{
	private String profileId;
	private String serialNo;
	private String userId;
	private String userName;
	private String departmentUnitCode;
	private String wardCode;
	private String accessType;

	private String isValid;
	private String seatId;
	private String entryDate;
	private String hospitalCode;
	private String userEmpID;
	private String userSeatId;
	private String userType;
	private String userLevel;
	
	public String getProfileId()
	{
		return profileId;
	}

	public void setProfileId(String profileId)
	{
		this.profileId = profileId;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getWardCode()
	{
		return wardCode;
	}

	public void setWardCode(String wardCode)
	{
		this.wardCode = wardCode;
	}

	public String getAccessType()
	{
		return accessType;
	}

	public void setAccessType(String accessType)
	{
		this.accessType = accessType;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserEmpID() {
		return userEmpID;
	}

	public void setUserEmpID(String userEmpID) {
		this.userEmpID = userEmpID;
	}

	public String getUserSeatId() {
		return userSeatId;
	}

	public void setUserSeatId(String userSeatId) {
		this.userSeatId = userSeatId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
}
