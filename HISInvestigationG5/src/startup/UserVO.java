package startup;

import java.util.*;

public class UserVO extends ValueObject
{
    private String userId;
    private String userName;
    private String seatId;
    private String userType;
    private String userLevel;
    private String userEmpID;
	
    public void setUserType (String userType) 
    {
        this.userType = userType;
    }

	public void setSeatId (String seatId) 
	{
	        this.seatId = seatId;
	}

	public void setUserName (String userName) 
	{
	        this.userName = userName;
	}

	public void setUserLevel (String userLevel) 
	{
	        this.userLevel = userLevel;
	}

	public void setUserId (String userId) 
	{
	        this.userId = userId;
	}

	public void setUserEmpID (String userEmpID) 
	{
	        this.userEmpID = userEmpID;
	}

	public String getUserType () 
	{
	        return userType;
	}

	public String getSeatId () 
	{
	        return seatId;
	}

	public String getUserName () 
	{
	        return userName;
	}

	public String getUserLevel () 
	{
	        return userLevel;
	}

	public String getUserId () 
	{
	        return userId;
	}

	public String getUserEmpID () 
	{
	        return userEmpID;
	}
	
	public int hashCode()
	{
		return this.userName.hashCode();
	}
	
	public boolean equals(Object obj) 
	{
		if (obj == this)
			return true;
		if (obj == null|| !(obj instanceof UserVO))
			return false;
		UserVO objUserVO= (UserVO)obj;
		
		return objUserVO.userName.equals(this.userName);
	  }
	 	
}