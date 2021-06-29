package startup;

import hisglobal.persistence.HisResultSet;

public class LoginData extends hisglobal.utility.HisMethods
{
	public boolean isValid(UserVO obj, String pwd)
	{
		boolean retVal = false;
		
		String query    = "";
				
		query   =    " SELECT GNUM_USER_SEATID, "+
					 " 	PSRSTR_EMP_NO,GNUM_USERID,GSTR_USER_NAME, "+
					 "  GNUM_USER_TYPE, GNUM_USERLEVEL "+
					 " FROM GBLT_USER_MST "+
					 " WHERE GSTR_USER_NAME='"+obj.getUserName()+"' "+
					 "	AND GSTR_PASSWORD='"+pwd+"' "+
					 " 	AND GBL_ISVALID=1"+
					 " 	AND GDT_EXPIRY_DATE > SYSDATE";
		//System.out.println("query is "+query);
		HisResultSet rs=null;
				
		try
		{
			rs=super.getRecord(query);
			if(rs!=null && rs.next())
			{
				obj.setSeatId(rs.getString(1));
				obj.setUserEmpID(rs.getString(2));
				obj.setUserId(rs.getString(3));
				obj.setUserType(rs.getString(5));
				obj.setUserLevel(rs.getString(6));
				
				retVal = true;
			}
		}
		catch(Exception e)
		{
			System.out.print("Exception in validateUid "+e);
		}
		
		return retVal;
	}
	
	public void register(String ipnumber, UserVO obj)
	{ 
		try
		{	
			super.updateRecord(	" INSERT INTO GBLT_USER_LOG "+
								" (GNUM_USERID, GNUM_SEAT_ID, GDT_LOGIN_DATE, GSTR_IP_NUMBER) "+
								" VALUES('"+obj.getUserId()+"','"+obj.getSeatId()+"',"+
								" sysdate,'"+ipnumber+"')");
		}
		catch(Exception e)
		{			
			System.out.print("Exception in validateUid "+e);
			throw new HisLoginInsertFailed();
		}
	}
	
	public void logoutUser(String ipnumber, UserVO obj)
	{
		try
		{
			System.out.println("inside logout of logindata ");
			System.out.println("uservo obj is "+obj);
			String query =	" UPDATE GBLT_USER_LOG "+
							" SET GDT_LOGUTT_DATE=(SELECT SYSDATE FROM DUAL) "+
							" WHERE GNUM_SEAT_ID='"+obj.getSeatId()+"' AND"+
							" GSTR_IP_NUMBER='"+ipnumber+"' ";
			super.updateRecord( query );
		}
		catch(Exception e)
		{
			System.out.println("Exception at logoutUser "+e);
			throw new HisLogoutUpdateFailed();
		}	
	}

}