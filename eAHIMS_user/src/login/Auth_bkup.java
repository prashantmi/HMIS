 
package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import startup.qryHandler_startup;

public class Auth_bkup extends HisGlobal.HisMethods 
{
	
	private String user 		=	"";
	private String pass 		= 	"";
	private String message		= 	"" ;
	private String hint 		= 	"" ;
	private String oldPass 		=	"" ;
	private String newPass 		= 	"" ;
	private String confirmPass 	= 	"";
	
	

	public java.lang.String getConfirmPass( ) 
	{
		return confirmPass;
	}

	public void setConfirmPass( java.lang.String confirmPass ) 
	{
		this.confirmPass = confirmPass;
	}

	public String getNewPass( ) 
	{
		return newPass;
	}

	public void setNewPass( String newPass ) 
	{
		this.newPass = newPass;
	}

	public java.lang.String getOldPass( ) 
	{
		return oldPass;
	}

	public void setOldPass( java.lang.String oldPass ) 
	{
		this.oldPass = oldPass;
	}
	

	public java.lang.String getHint( ) 
	{
		return hint;
	}

	public void setHint( java.lang.String hint ) {
		this.hint = hint;
	}
	
	

	public java.lang.String getMessage( ) {
		return message;
	}

	public void setMessage( java.lang.String message ) {
		this.message = message;
	}
	 
	
	public void setUser( String User ) {this.user = User ;}
	public void setPass ( String Pass ){this.pass = Pass ;}
	
	public String getUser(){ return this.user;	}
	public String getPass(){ return this.pass;	}
	
	public String authorizeUser() throws Exception
	{   
		String encryptpass =  SecurityUtil.encrypt(this.pass);
		//String hasgPassword = PasswordHash.createHash(this.pass);
		//String hasgPassword1 = PasswordHash.createHash(this.pass);

		ResultSet newRs = null;
		String strHospitalCode="";
		String hashPassword="";

		try
		{
			String strQuery ="select GSTR_PASSWORD from GBLT_USER_MST where GSTR_USER_NAME like '"+this.user+"' ";
			//strHospitalCode = super.getField(strQuery );

		//	boolean a =PasswordHash.validatePassword(this.pass, hashPassword);

			//System.out.println(a);
			
			String query = qryHandler_startup.getQuery("select.GBLT_USER_MST.0"); 
			
			Connection conn = null;
			PreparedStatement ps = null;

			conn = super.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
			
			
				ps.setString(1,this.user);
				ps.setString(2,encryptpass);
				newRs=ps.executeQuery();
				conn.commit();
				conn.setAutoCommit(true);
			try
			{	newRs.beforeFirst();
			   
				while(newRs.next())
				{
					strHospitalCode=newRs.getString(1);
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception in  the combo "+e);
			}
			finally
			{
				super.closeConnection(conn);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
		}
		//return seat_id ;
		return strHospitalCode;
	}
	
	public String forgetPassword()
	{
		String defaultPassward="123";
		String msg ="Information is incorrect...";
		String encryptpass="";
		String query =" select GNUM_USERID from gblt_user_mst where GSTR_USER_NAME='"+this.user+"' and GSTR_HINT_QUESTIONS='"+this.hint+"' ";
		String userId = super.getField(query );
		try
		{
		encryptpass =  SecurityUtil.encrypt(defaultPassward);
		}catch(Exception e){System.out.println("Exception is "+e);}
		if (!userId.equals("") && userId != null )
		{  
			// reset password to 123
			String updateQry=	"update GBLT_USER_MST set GSTR_OLD_PASSWORD = GSTR_PASSWORD , GSTR_PASSWORD ='"+encryptpass+"' where GNUM_USERID = '"+userId+"'" ;
			 boolean flag = false ;
		   try
		   {
		      super.updateRecord(updateQry);
		      flag = true ;
		   }
		   
		   catch( Exception e ) 
		   { flag = false ; System.out.println("error in try block");}
		   if ( flag )
			  msg = "........Your Password has been reset to 123 kindly change your password...";
		   else
		      msg = "Error while updation...!";
		}	
		//this.message = msg ;
		System.out.println("message = "+this.message);
		return msg ;
	}
	
	public String changePassword()
	{
		String encryptpass="";
		String newEncryptpass="";
		String msg ="Information is incorrect...";
		try
		{
			encryptpass = SecurityUtil.encrypt(this.oldPass);
		}
		catch(Exception e)
		{
			System.out.println("Exception is "+e);
		}
		String query ="select GNUM_USERID from GBLT_USER_MST where GSTR_USER_NAME like '"+this.user+"' and GSTR_PASSWORD like '"+encryptpass+"'" ;
		String userId = super.getField(query );
		
		if (!userId.equals("") && userId != null )
		{  
			// reset password to 123
		  try
		  {
			newEncryptpass =  SecurityUtil.encrypt(this.newPass);
		  }
		  catch(Exception e)
		  {
			  System.out.println("Exception is"+e);
		  }
			
			String updateQry = "update GBLT_USER_MST set GSTR_OLD_PASSWORD = GSTR_PASSWORD,GSTR_PASSWORD ='"+newEncryptpass+"' where GNUM_USERID = '"+userId+"'" ;
			System.out.println("update qry = "+updateQry);
			boolean flag = false ;
		   try
		   {
		      flag = super.updateRecord(updateQry);
		   System.out.println("______"+flag);
		   }
		   catch( Exception e ) { System.out.println("error in try block");}
		   
		   if ( flag )
			  msg = "Your Password has been set...";
		   else
		      msg = "Error while updation...!";
		}	
		
		//this.message = msg ;
		return msg ; 
	
	
	}
	public void logOutAction( String seatId )
	{
		System.out.println("INSIDE auth bean updatefr FUNCTION");
		String query="";
		try
		{
			query=	"update GBLT_USER_LOG set GDT_LOGUTT_DATE = sysdate where GDT_LOGUTT_DATE is null "+
			        " and GNUM_SEAT_ID ='"+seatId+"' ";
			super.updateRecord(query );
		}
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
		}
		System.out.println("uodate query is"+query);
	}
	
	public void logInAction(String seatId , String ipAddr) throws Exception
	{
		
		String encryptpass =  SecurityUtil.encrypt(this.pass);
		try
		{
		String query ="select GNUM_SEAT_ID from GBLT_HOSPITAL_MST where GSTR_USER_NAME like '"+this.user+"' and GSTR_PASSWORD='"+encryptpass+"' ";
		System.out.println("logInAction query is "+query);
		String userId = super.getField(query);
		System.out.println("userId = "+userId);
		
//<<<<<<< Auth.java
		//String insertQuery="insert into GBLT_USER_LOG " +
			///	"values('"+userId+"','"+seatId+"',sysdate,null,'"+ipAddr+"')";
//=======
		//String insertQuery="insert into GBLT_USER_LOG " +
			//	"values('"+userId+"','"+seatId+"',sysdate,null,'"+ipAddr+"')";
//>>>>>>> 1.3
		
//<<<<<<< Auth.java
		//System.out.println("insertQuery = "+insertQuery);
	
			//super.updateRecord(insertQuery);
//=======
		//System.out.println("insertQuery = "+insertQuery);

		//	super.updateRecord(insertQuery);
//>>>>>>> 1.3
		}
		catch(Exception e)
		{
			System.out.println("exception e = "+e);
		}
		
		
		
	}
	public static  String myFrame= "<frameset rows='20%,80%' border='1'>"+
	"	<frame id='header' src='../HisGlobal/header.jsp' scrolling='no' border='1' noresize>"+

	"	<frameset id='expand' cols='23%,*' border='0'>"+
	"		<frame id='menu' src='menu.jsp' scrolling='yes' noresize>"+
	"		<frame id='content' src='content.jsp' scrolling='yes' noresize>"+
	"	</frameset>"+

	"</frameset>";

}