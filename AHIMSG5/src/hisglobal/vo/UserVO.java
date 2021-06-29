/**********************************************************
 Project:	   AHIMS_G5	
 File:         UserVO.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.vo;

/**
 * UserVO is the class that specifies getters and setters for all the identifiers
 * which are used for retrieving and inserting user information in the DB tables.
 */
public class UserVO extends ValueObject
{
	private TransactionVO transactionInfo = new TransactionVO();
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
	private String districtCode;
	private String usrName;
	private String designation;
	private String emailId;
	private String mobileNo;
	private String swapcardNo;
	private HospitalMstVO strHospitalMstVo;
	private String districtName;
	private String checkBackDateDayEndFlag;
	private String userBoundDefaultGender;
	
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

	/**
	 * Retrieves tariffID.
	 * @return Value of tariffID.	
	 */
	public String getTariffID()
	{
		return tariffID;
	}

	/**
	 * Sets tariffID.
	 * @param tariffID
	 */
	public void setTariffID(String tariffID)
	{
		this.tariffID = tariffID;
	}



	/**
	 * Sets userType.
	 * @param userType
	 */
	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	/**
	 * Sets seatId.
	 * @param seatId
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	/**
	 * Sets userName.
	 * @param userName
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * Sets userName.
	 * @param userName
	 */
	public void setUserLevel(String userLevel)
	{
		this.userLevel = userLevel;
	}

	/**
	 * Sets userId.
	 * @param userId
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * Sets userEmpID.
	 * @param userEmpID
	 */
	public void setUserEmpID(String userEmpID)
	{
		this.userEmpID = userEmpID;
	}

	/**
	 * Retrieves userType.
	 * @return Value of userType.	
	 */
	public String getUserType()
	{
		return userType;
	}

	/**
	 * Retrieves seatId.
	 * @return Value of seatId.	
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * Retrieves userName.
	 * @return Value of userName.	
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Retrieves userName.
	 * @return Value of userName.	
	 */
	public String getUserLevel()
	{
		return userLevel;
	}

	/**
	 * Retrieves userId.
	 * @return Value of userId.	
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Retrieves userEmpID.
	 * @return Value of userEmpID.	
	 */
	public String getUserEmpID()
	{
		return userEmpID;
	}

	/**
	 * Retieves User Seat Id to which it is mapped
	 * @return
	 */
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

	public TransactionVO getTransactionInfo() {
		return transactionInfo;
	}

	public void setTransactionInfo(TransactionVO transactionInfo) {
		this.transactionInfo = transactionInfo;
	}

	public String getDistrictCode()
	{
		return districtCode;
	}

	public void setDistrictCode(String districtCode)
	{
		this.districtCode = districtCode;
	}

	public String getUsrName()
	{
		return usrName;
	}

	public void setUsrName(String usrName)
	{
		this.usrName = usrName;
	}

	public String getDesignation()
	{
		return designation;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getMobileNo()
	{
		return mobileNo;
	}

	public void setMobileNo(String mobileNo)
	{
		this.mobileNo = mobileNo;
	}

	public String getSwapcardNo()
	{
		return swapcardNo;
	}

	public void setSwapcardNo(String swapcardNo)
	{
		this.swapcardNo = swapcardNo;
	}

	public HospitalMstVO getStrHospitalMstVo() {
		return strHospitalMstVo;
	}

	public void setStrHospitalMstVo(HospitalMstVO strHospitalMstVo) {
		this.strHospitalMstVo = strHospitalMstVo;
	}

	public String getDistrictName()
	{
		return districtName;
	}

	public void setDistrictName(String districtName)
	{
		this.districtName = districtName;
	}

	public String getCheckBackDateDayEndFlag() {
		return checkBackDateDayEndFlag;
	}

	public void setCheckBackDateDayEndFlag(String checkBackDateDayEndFlag) {
		this.checkBackDateDayEndFlag = checkBackDateDayEndFlag;
	}

	public String getUserBoundDefaultGender() {
		return userBoundDefaultGender;
	}

	public void setUserBoundDefaultGender(String userBoundDefaultGender) {
		this.userBoundDefaultGender = userBoundDefaultGender;
	}

}
