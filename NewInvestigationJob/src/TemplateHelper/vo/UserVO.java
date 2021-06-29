/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TemplateHelper.vo;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */

/**
 * UserVO is the class that specifies getters and setters for all the identifiers
 * which are used for retrieving and inserting user information in the DB tables. 
 * @author AHIS
 */
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
	 * Retrieves transactionInfo.
	 * @return Value of transactionInfo.	
	 */
/*	public hisglobal.vo.TransactionVO getTransactionInfo()
	{
		return transactionInfo;
	}
*/
	/**
	 * Sets transactionInfo.
	 * @param transactionInfo
	 */
/*	public void setTransactionInfo(hisglobal.vo.TransactionVO transactionInfo)
	{
		this.transactionInfo = transactionInfo;
	}
*/
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

}
