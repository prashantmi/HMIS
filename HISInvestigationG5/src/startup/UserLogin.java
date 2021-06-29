package startup;

import hisglobal.*;
import hisglobal.persistence.HisResultSet;

import java.util.*;
import startup.*;

public class UserLogin extends hisglobal.utility.HisMethods
{
	private static HashMap map = new HashMap();
	
	public boolean validateUid(String userName,String pwd,String ipnumber)
	{
		boolean retVal = false;
		
		String query=" SELECT GNUM_USER_SEATID, "+
					 " 	PSRSTR_EMP_NO,GNUM_USERID,GSTR_USER_NAME "+
					 " FROM GBLT_USER_MST "+
					 " WHERE GSTR_USER_NAME='"+userName+"' "+
					 "	AND GSTR_PASSWORD='"+pwd+"' "+
					 " 	AND GBL_ISVALID=1"+
					 " 	AND GDT_EXPIRY_DATE > SYSDATE";

		HisResultSet rs=null;
				
		try
		{
			rs=super.getRecord(query);
			if(rs!=null && rs.next())
			{	
				super.updateRecord(	" INSERT INTO GBLT_USER_LOG "+
									" (GNUM_USERID, GNUM_SEAT_ID, GDT_LOGIN_DATE, GSTR_IP_NUMBER) "+
									" VALUES('"+rs.getString(3)+"','"+rs.getString(1)+
									" ',sysdate,'"+ipnumber+"')");
				retVal = true;
			}
			else
				retVal = false;
				
		}
		catch(Exception e)
		{
			System.out.print("Exception in validateUid "+e);
		}
		
		return retVal;
	}
	
	public void logoutUser(String seatId,String ipnumber)
	{
		try
		{
			String query =	"UPDATE GBLT_USER_LOG "+
							"SET GDT_LOGUTT_DATE=(SELECT SYSDATE FROM DUAL) "+
							"WHERE GNUM_SEAT_ID='"+seatId+"' AND GSTR_IP_NUMBER='"+ipnumber+"' ";
			super.updateRecord( query );
		}
		catch(Exception e)
		{
			System.out.println("Exception at logoutUser "+e);
		}
	}
	
	public boolean isLoggedIn(String userName,String pwd)
	{
		boolean retVal = false;
		if(!map.isEmpty())
		{
			UserVO objUserVO = createUserVO(userName,pwd);		
			retVal = map.containsKey(objUserVO);
		}				
		return retVal;	
	}   
	
	public String loggedInIP(String userName, String pwd)
	{
		String ipAdd = null;
		if(!map.isEmpty())
		{
			UserVO objUserVO = createUserVO(userName,pwd);
			
			if(map.containsKey(objUserVO))
				ipAdd = (String)map.get(objUserVO);
		}
		return ipAdd;
	}
	
	public void createUser(String userName,String pwd,String remoteAdd)
	{
		UserVO objUserVO = createUserVO(userName,pwd);
		map.put(objUserVO,remoteAdd);
	}
	
	public void destroyUser(String userName,String pwd)
	{
		if(!map.isEmpty())
		{
			UserVO objUserVO = createUserVO(userName,pwd);
			map.remove(objUserVO);
		}
	}
	
   	public UserVO createUserVO(String userName,String pwd)
   	{
		UserVO objUserVO = new UserVO();
		
		String query=" SELECT GNUM_USER_SEATID, "+
					 " 	PSRSTR_EMP_NO,GNUM_USERID,GSTR_USER_NAME "+
					 "  GNUM_USER_TYPE, GNUM_USERLEVEL "+
					 " FROM GBLT_USER_MST "+
					 " WHERE GSTR_USER_NAME='"+userName+"' "+
					 "	AND GSTR_PASSWORD='"+pwd+"' "+
					 " 	AND GBL_ISVALID=1"+
					 " 	AND GDT_EXPIRY_DATE > SYSDATE";

		HisResultSet rs=null;
		
		try
		{
			rs=super.getRecord(query);
			
			if(rs!=null && rs.next())
			{
				objUserVO.setSeatId(rs.getString(1));
				objUserVO.setUserEmpID(rs.getString(2));
				objUserVO.setUserId(rs.getString(3));
                objUserVO.setUserName(rs.getString(4));
				objUserVO.setUserType(rs.getString(5));
				objUserVO.setUserLevel(rs.getString(6));
			}
			else
				objUserVO = null;
		}
		catch(Exception e)
		{
			System.out.print("Exception in validateUid "+e);
		}
		finally
		{
			rs.close();
			rs = null;
		}
		
		return objUserVO;
   	}

}