package startup;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

import java.net.InetAddress;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import oracle.jdbc.driver.OracleTypes;


public class loginDao extends DataAccessObject
{
	public loginDao(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		
	}
	
	//Function used  for validating userID and Password
	public login validateUidDao(String uid,String password,String ipnumber) throws HisException
	{
	
		ResultSet rs=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		login obj=new login();
		
	
		
		
		try{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			Map populateMAP2 = new HashMap();
			
			
			String query = qryHandler_startup.getQuery("select.GBLT_USER_MST.0"); 
			
			
						
			populateMAP.put(sq.next(), uid);
			populateMAP.put(sq.next(),password);
			ArrayList ipaddr=new ArrayList<String>();
			
		   boolean validUser=false;
		
				
			rs =HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next())
			{
				obj.setSeatId("NONE");
			}
			else
			  {
				validUser=true;
				rs.beforeFirst();
			while( rs.next())
			               {
				obj.setSeatId(rs.getString(1));
				obj.setEmpId(rs.getString(2));
				obj.setUserId(rs.getString(3));
				obj.setUserName(rs.getString(4));
				obj.setUserLevel (rs.getString(5));
				obj.setHospitalCode(rs.getString(6));
				obj.setQuestionId(rs.getString(7));
				obj.setUserFullName(rs.getString(8));
				obj.setAnswer(rs.getString(9));
				obj.setMobileNo(rs.getString(10));
				obj.setEmailId(rs.getString(11));
				obj.setIsLocked(rs.getString(12));
				obj.setChangePasswordDate(rs.getString(13));
				obj.setNoOfDaysBeforePasswordChanged(rs.getInt(14));
				
				//Setting the Date Formats
				obj.setDateField(rs.getString(15));
				obj.setMonth(rs.getString(16));
				obj.setYear(rs.getString(17));
				obj.setHour(rs.getString(18));
				obj.setMinute(rs.getString(19));
				obj.setSeconds(rs.getString(20));
				
				    }
			
			  }	
			
			
			//getting ipaddress
			rs.beforeFirst();
			
			if(rs.next())
			{
			Sequence sq2=new Sequence();
			
			String query2 = qryHandler_startup.getQuery("select.GBLT_SEAT_IPADDRESS_DTL.0"); 
			
			//System.out.println("query in validateuid is --->"+query2);
			//System.out.println("seatid  is --->"+obj.getSeatId());
			//System.out.println("hospital code is --->"+obj.getHospitalCode());
			
			
			populateMAP2.put(sq2.next(),obj.getHospitalCode());
			populateMAP2.put(sq2.next(), obj.getSeatId());
						
		   boolean ipCheck=false;
			
			rs2 =HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query2, populateMAP2);
			if(!rs2.next())
			{
				
			}
			else
			  {
				ipCheck=true;
				rs2.beforeFirst();
			while( rs2.next())
			               {
				//System.out.println("ipaddr inside resultset1 --->"+rs2.getString(1));
					ipaddr.add(rs2.getString(1));
				  
			               }
			    obj.setIpaddress(ipaddr);
			  }	
			
			
			
						
            boolean ipStatus=false;
            for(int i=0; i < ipaddr.size() ; i++)
            {
            //	System.out.println("ipaddr is --->"+ipaddr.get(i));
            //	System.out.println("ipnumber is --->"+ipnumber);
            	
            	if(ipaddr.get(i).equals(ipnumber))
            	{
            		ipStatus=true;
            //		System.out.println("ipnumber is equal--->"+ipStatus);
            	}
            	//	System.out.println("ipnumber is unequal--->"+ipStatus);
            	
            }
		
           // System.out.println("ipStatus-->"+ipStatus);
           // System.out.println("validUser-->"+validUser);
           // System.out.println("ipCheck-->"+ipCheck);
            
            
       if(ipStatus==false && validUser==true && ipCheck==true)
		{
    	  // System.out.println("inside  INVALID_IP_ADDRESS--->");	
			obj.setSeatId("INVALID_IP_ADDRESS");	
		}
			
			               
			
			
		}
			//change code for ip address from seat table
			if(obj.getSeatId().equals("0")|| obj.getSeatId().equals(""))
			{

			    // Get IP Address
				InetAddress address = InetAddress.getLocalHost();
	            String ip = address.getHostAddress();

			Sequence sq3=new Sequence();
			
			String query2 = qryHandler_startup.getQuery("select.GBLT_SEAT_MST.0"); 
			
			populateMAP2.put(sq3.next(),obj.getHospitalCode());
			populateMAP2.put(sq3.next(),ip);
			populateMAP2.put(sq3.next(),obj.getHospitalCode());
						
		   boolean ipFound=false;
			
			rs3 =HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query2, populateMAP2);
			if(!rs3.next())
			{
				
			}
			else
			  {
				ipFound=true;
				rs3.beforeFirst();
				while( rs3.next())
			     {
					obj.setSeatId(rs3.getString(1));

			     }	
			  }
			if(ipFound==false)
			{
				obj.setSeatId("NO_SEAT_ASSOCIATED");	
			}
		}
		}
		catch(Exception e){
			
			e.printStackTrace();
			
			throw new HisException(e.getMessage());
		                }
			finally
					{
				
					}
		return obj;
		}
	//End of validateUid
	
	//Function used for maintaing Log of User
	
	public int maintainLog(String uid,String seatid,String ipNo,String hospitalCode) throws HisException
	{
	
		ResultSet rs=null;
		login obj=new login();
		int result=0;
	
		
		
		try{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			
			
			 
			String query = qryHandler_startup.getQuery("insert.GBLT_USER_LOG.0"); 
			
			//System.out.println("query in maintain Log  is --->"+query);
			
			/*System.out.println("uid is --->"+uid);
			System.out.println("seat idis --->"+seatid);
			System.out.println("ip no is --->"+ipNo);
			System.out.println("hospitalCodeis --->"+hospitalCode);
		*/	
			
			populateMAP.put(sq.next(), uid);
			populateMAP.put(sq.next(),seatid);
			populateMAP.put(sq.next(), ipNo);
			populateMAP.put(sq.next(),hospitalCode);
			
		
			
			result =HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

            
            
		//System.out.println("result = " + result);
			
			               
			
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			
			throw new HisException(e.getMessage());
		                }
			finally
					{
			
					}
		return result;
		}//fn closed
	
	
	
	
	//Getting the Hint Questions from the Database and setting into the request
	public void hintQuestionsDao(HttpServletRequest request) throws HisException
	{
		//After creating successfull session ,we are getting the hint questions from database
		
		ResultSet rs=null;
		login obj=new login();
		ArrayList questionCollection=new ArrayList();
	
		
		
		try{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			
			
			String questionQuery ="";
			
			questionQuery= qryHandler_startup.getQuery("select.SGBLT_HINT_QUESTION_MST.0"); 
			
			//System.out.println("questionQuery is --->"+questionQuery);

				rs =HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), questionQuery, populateMAP);
			if(!rs.next())
			{
				
			}
			else
			  {
				rs.beforeFirst();
			while( rs.next()){
				questionCollection.add(rs.getString(1));
				questionCollection.add(rs.getString(2));
			                 }
			  }	

			
		}
		catch(Exception e){
			
			e.printStackTrace();
			
			throw new HisException(e.getMessage());
		                }
			finally
					{
			
					}
	
		request.setAttribute("QUESTION_COLLECTION",questionCollection);
	//	request.getSession().setAttribute("QUESTION_COLLECTION",questionCollection);
		
		
			//	System.out.println("hospitalCode is --->"+request.getSession().getAttribute("HOSPITAL_CODE"));
		
		
	
	
	

		
	}
	
	public int oldPasswordCheckDao(HttpServletRequest request) throws HisException
	{
		//After creating successfull session ,we are getting the hint questions from database
		ResultSet rs=null;
		login obj=new login();
		int result=0;
		String passwordCheckQuery="";
	
		
		
		try{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			
			
			 
			passwordCheckQuery= qryHandler_startup.getQuery("select.VALID.USER.GBLT_USER_MST.0"); 
			
			populateMAP.put(sq.next(), (String)request.getSession().getAttribute("USER_ID"));
			populateMAP.put(sq.next(),SecurityUtil.encrypt(request.getParameter("oldPassword")));
			populateMAP.put(sq.next(), (String)request.getSession().getAttribute("HOSPITAL_CODE"));
		
			
		
			
			rs =HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), passwordCheckQuery, populateMAP);

			while(rs.next())
				result=rs.getInt(1);
            
		//System.out.println("result = " + result);
			
			               
			
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		
			
			throw new HisException(e.getMessage());
		                }
			finally
					{
			
					}
		
					
return result;
		
	}
	
	public int firstLoginDao(HttpServletRequest request) throws HisException
	{
		 
		int result=0;
		String passwordUpdateQuery="";
	
		
		
		try{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			
			
			 
			passwordUpdateQuery= qryHandler_startup.getQuery("UPDATE.GBLT_USER_MST.0");  
			
			populateMAP.put(sq.next(),SecurityUtil.encrypt(request.getParameter("newPassword")));
			populateMAP.put(sq.next(),SecurityUtil.encrypt(request.getParameter("answer")));
			populateMAP.put(sq.next(),request.getParameter("hintQuestion"));
			populateMAP.put(sq.next(),(String)request.getSession().getAttribute("USER_ID"));
			populateMAP.put(sq.next(),(String)request.getSession().getAttribute("USER_ID"));
			populateMAP.put(sq.next(),(String)request.getSession().getAttribute("HOSPITAL_CODE"));
		
			request.getSession().setAttribute("ANSWER",request.getParameter("answer"));
			request.setAttribute("ANSWER",request.getParameter("answer"));
			
			request.getSession().setAttribute("QUESTION_ID",request.getParameter("hintQuestion"));
			request.setAttribute("QUESTION_ID",request.getParameter("hintQuestion")); 	
			
			result =HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), passwordUpdateQuery, populateMAP);
			
			//System.out.println("result of first login ---"+result);
			/*System.out.println("QuestionId ---"+request.getSession().getAttribute("QUESTION_ID"));
			System.out.println("ANWSER ---"+request.getSession().getAttribute("ANWSER"));*/
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		
			
			throw new HisException(e.getMessage());
		                }
			finally
					{
			
					}

		return result;
	}
	//
	public int updatePasswordOnly(HttpServletRequest request) throws HisException
	{
		 
		int result=0;
		String passwordUpdateQuery="";
	
		
		
		try{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			
			
			 
			passwordUpdateQuery= qryHandler_startup.getQuery("UPDATE.GBLT_USER_MST.1");  
			
			populateMAP.put(sq.next(),SecurityUtil.encrypt(request.getParameter("newPassword")));
			populateMAP.put(sq.next(),SecurityUtil.encrypt(request.getParameter("oldPassword")));
			populateMAP.put(sq.next(),(String)request.getSession().getAttribute("USER_ID"));
			populateMAP.put(sq.next(),(String)request.getSession().getAttribute("USER_ID"));
			populateMAP.put(sq.next(),(String)request.getSession().getAttribute("HOSPITAL_CODE"));
			populateMAP.put(sq.next(),SecurityUtil.encrypt(request.getParameter("oldPassword")));		
			
		
			
			result =HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), passwordUpdateQuery, populateMAP);
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		
			
			throw new HisException(e.getMessage());
		                }
			finally
					{
			
					}

		return result;
	}
	//Function used for getting Forgot Password
	public Map forgotPassword(HttpServletRequest request) throws HisException
	{
		//After creating successfull session ,we are getting the hint questions from database
		
		ResultSet rs=null;
			
		String forgotPasswordQuery="";
		
	    Map resultantMAP = new HashMap();
		
		
		try{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			
			
			 
			forgotPasswordQuery= qryHandler_startup.getQuery("select.FORGOT_PASSWORD_GBLT_USER_MST.0");  
			
			populateMAP.put(sq.next(), request.getParameter("hintQuestion"));
			populateMAP.put(sq.next(),request.getParameter("userName"));
			populateMAP.put(sq.next(), SecurityUtil.encrypt(request.getParameter("answer")));
			//System.out.println("userName---->"+request.getParameter("userName"));
			
			rs =HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), forgotPasswordQuery, populateMAP);
			
			while(rs.next())
			{
				Sequence sq1=new Sequence();
				
				resultantMAP.put(sq1.next(), rs.getString(1));
				resultantMAP.put(sq1.next(), rs.getString(2));
				resultantMAP.put(sq1.next(), rs.getString(3));
				resultantMAP.put(sq1.next(), rs.getString(4));
				resultantMAP.put(sq1.next(), rs.getString(5));
				
							
			}
				
		}
		catch(Exception e){
			
			e.printStackTrace();
						
			throw new HisException(e.getMessage());
		                }
			finally
					{
			
					}		 
				
return resultantMAP;
		
	}
	
	public int newPassword(HttpServletRequest request) throws HisException
	{
		//After creating successfull session ,we are getting the hint questions from database
		
		String passwordUpdateQuery="";
		
	    Map populateMAP = new HashMap();
	    int result=0;
		
		
		try{
			Sequence sq=new Sequence();
			
			//System.out.println("USER_ID value in dao===="+request.getAttribute("USER_ID"));
			
			
			passwordUpdateQuery= qryHandler_startup.getQuery("UPDATE.GBLT_USER_MST.1");  
			
			populateMAP.put(sq.next(),SecurityUtil.encrypt(request.getParameter("newPassword")));
			populateMAP.put(sq.next(),(String)request.getAttribute("PASSWORD"));
			populateMAP.put(sq.next(),(String)request.getAttribute("USER_ID"));
			populateMAP.put(sq.next(),(String)request.getAttribute("USER_ID"));
			populateMAP.put(sq.next(),(String)request.getAttribute("HOSPITAL_CODE"));
			populateMAP.put(sq.next(),(String)request.getAttribute("PASSWORD"));		
			
		
			
			result =HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), passwordUpdateQuery, populateMAP);
			
				
		}
		catch(Exception e){
			
			e.printStackTrace();
		
			
			throw new HisException(e.getMessage());
		                }
			finally
					{
			
					}		 
				
return result;
		
	}
	public int updateUserDetails(HttpServletRequest request) throws HisException
	{
		//After creating successfull session ,we areupdating the user details
		int result=0;
		String userUpdateQuery="";
		userUpdateQuery= qryHandler_startup.getQuery("UPDATE.GBLT_USER_MST.USER_DETAILS.0"); 
		
		
		
		try{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			
			populateMAP.put(sq.next(),request.getParameter("userFullName"));
			populateMAP.put(sq.next(),request.getParameter("hintQuestion"));
			populateMAP.put(sq.next(),SecurityUtil.encrypt(request.getParameter("answer")));
			populateMAP.put(sq.next(),request.getParameter("mobileNo"));
			populateMAP.put(sq.next(),request.getParameter("emailId"));
			populateMAP.put(sq.next(),request.getSession().getAttribute("USER_ID"));
			populateMAP.put(sq.next(),request.getSession().getAttribute("USER_ID"));
			populateMAP.put(sq.next(),request.getSession().getAttribute("HOSPITAL_CODE"));
				
			
			result =HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), userUpdateQuery, populateMAP);
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		
			
			throw new HisException(e.getMessage());
		                }
			finally
					{
			
					}

					
				
		
				
			return result;
		
	}
	//function for updating audit log of user details
	                                   																				
	public int updateAuditLogDao(HttpServletRequest request,String moduleId,String menuId,String primarKeyValue,String oldValue,String newValue,String seatId,String userId,String ipAddress,String userName,String tableName) throws HisException
	{
	
		int result=0;
		String userUpdateQuery="";
		userUpdateQuery= qryHandler_startup.getQuery("UPDATE.HAUT_ADMIN_ACTIVITY.AUDIT_LOG.0"); 
		
		
		
	   	Sequence sq=new Sequence();		
		Map populateMAP = new HashMap();
	   	
	   
	    
	   	try{
			
			
			populateMAP.put(sq.next(),moduleId);
			populateMAP.put(sq.next(),menuId);
			populateMAP.put(sq.next(),primarKeyValue);
			populateMAP.put(sq.next(),oldValue);
			populateMAP.put(sq.next(),newValue);
			populateMAP.put(sq.next(),"");
			populateMAP.put(sq.next(),seatId);
			populateMAP.put(sq.next(),userId);
			populateMAP.put(sq.next(),ipAddress);
			populateMAP.put(sq.next(),"update by "+userName);
			populateMAP.put(sq.next(),tableName);
		
			
				
			
			result =HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), userUpdateQuery, populateMAP);
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		
			
			throw new HisException(e.getMessage());
		                }
			finally
					{
			
					}

					
				
		
				
			return result;
		
	}
	public void logoutUser(String userId,String ipnumber,String _hospitalCode) throws HisException
	{
		String userUpdateQuery="";
		userUpdateQuery= qryHandler_startup.getQuery("update.GBLT_USER_LOG.0"); 
		
		
		try
		{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			populateMAP.put(sq.next(), userId);
			populateMAP.put(sq.next(),ipnumber);
			populateMAP.put(sq.next(),_hospitalCode);
			
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), userUpdateQuery, populateMAP);
		}
		
		catch(Exception e){
			throw new HisException(e.getMessage());
		}
		finally{
			
		}
	}
	
	public void createUnsuccessfulLoginLog(String userName,String ipAddress) throws HisException
	{
		String userUpdateQuery="";
		userUpdateQuery= qryHandler_startup.getQuery("insert.GBLT_LOGIN_UNSUCESSFUL_LOG"); 
		
		
		try
		{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			populateMAP.put(sq.next(), "'"+userName+"'");
			populateMAP.put(sq.next(), "'"+ipAddress+"'");
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), userUpdateQuery, populateMAP);
		}
		
		catch(Exception e){
			throw new HisException(e.getMessage());
		}
		finally{
			
		}
	}
	
	public int selectUnsuccessfulLoginLog(String userName) throws HisException
	{
		ResultSet rs=null;
		int numberOfUnsuccessFulLogin=0;
		String userUpdateQuery="";
		userUpdateQuery= qryHandler_startup.getQuery("select.GBLT_LOGIN_UNSUCESSFUL_LOG"); 
		
		
		try
		{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			populateMAP.put(sq.next(), userName);
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), userUpdateQuery, populateMAP);
			if(rs.next())
			{
				numberOfUnsuccessFulLogin=rs.getInt(1);
			}
			
		}
		
		catch(Exception e){
			throw new HisException(e.getMessage());
		}
		finally{
			
		}
		return numberOfUnsuccessFulLogin;
	}
	
	
	public int selectMaxUserLicenseHospWise(String hospCode) throws HisException
	{
		ResultSet rs=null;
		int numberOfLicense=0;
		String hospLicenseQuery="";
		hospLicenseQuery= qryHandler_startup.getQuery("select.GBLT_HOSP_MAX_LICENSE"); 
		
		
		try
		{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			populateMAP.put(sq.next(), hospCode);
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), hospLicenseQuery, populateMAP);
			if(rs.next())
			{
				numberOfLicense=rs.getInt(1);
			}
			
		}
		
		catch(Exception e){
			throw new HisException(e.getMessage());
		}
		finally{
			
		}
		return numberOfLicense;
	}
	
	
	public void updateUnsuccessfulLoginLog(String userName) throws HisException
	{
		String userUpdateQuery="";
		userUpdateQuery= qryHandler_startup.getQuery("delete.GBLT_LOGIN_UNSUCESSFUL_LOG"); 
		
		
		try
		{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			populateMAP.put(sq.next(), userName);
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), userUpdateQuery, populateMAP);
		}
		
		catch(Exception e){
			throw new HisException(e.getMessage());
		}
		finally{
			
		}
	}
	
	public void updateIsLocked(String userName) throws HisException
	{
		ResultSet rs=null;
		login obj=new login();
		int result=0;
		String userUpdateQuery="";
		userUpdateQuery= qryHandler_startup.getQuery("update.is_loced.GBLT_USER_MST"); 
		
		
		try
		{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			populateMAP.put(sq.next(), userName);
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), userUpdateQuery, populateMAP);
		}
		
		catch(Exception e){
			throw new HisException(e.getMessage());
		}
		finally{
			
		}
	}

	public String getUserRolesAndPrivelages(String userId,String hospitalCode) throws HisException
	{
		ResultSet rs=null;
		login obj=new login();
		int result=0;
		String rolesQuery="";
		rolesQuery= qryHandler_startup.getQuery("SELECT_ROLES.GBLT_SEAT_ROLE_MST"); 
		
		//Code commented instead we are using StringBuilder
		//String rolesNameConcate="";
		//String rolesIdConcate="";
			
		StringBuilder rolesNameConcates=new StringBuilder();
		StringBuilder rolesIdConcates=new StringBuilder();
		
		
	   	
		try
		{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			
			populateMAP.put(sq.next(), userId);
			populateMAP.put(sq.next(), hospitalCode);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), rolesQuery, populateMAP);
	
			
			rs.beforeFirst();
			while(rs.next())
			{
				rolesIdConcates.append(rs.getString(1));
				rolesIdConcates.append("@");
				
				rolesNameConcates.append(rs.getString(2));
				rolesNameConcates.append("@");
				
				//Code commented instead we are using StringBuilder
				//rolesIdConcate+=rs.getString(1)+"@";
				//rolesNameConcate+=rs.getString(2)+"@";
				
					
			}
		
		}
		
		catch(Exception e){
			throw new HisException(e.getMessage());
		}
		
			
	finally{
			
		}
	

	 rolesIdConcates.append("#");
	 rolesIdConcates.append(rolesNameConcates);
	 
	 return rolesIdConcates.toString();
	 
	  //Code commented instead we are using StringBuilder
	 //return rolesIdConcate+"#"+rolesNameConcate;
	
	}
	
	
	public String getUserMenus(String roleId,String hospitalCode) throws HisException
	{
		ResultSet rs=null;
		login obj=new login();
		int result=0;
		String rolesQuery="";
		rolesQuery= qryHandler_startup.getQuery("SELECT_MENUS.GBLT_ROLE_MENU_MST"); 
		
		StringBuffer menuBuffer=new StringBuffer(100);
		StringBuffer servicesBuffer=new StringBuffer(100);
		StringBuffer reportsBuffer=new StringBuffer(100);
		StringBuffer setupBuffer=new StringBuffer(100);
		StringBuffer helpBuffer=new StringBuffer(100);
		
		boolean serviceMenuFound = false;
		boolean setUpMenuFound = false;
		boolean reportsMenuFound = false;
		boolean helpMenuFound = false;
		
		int serviceCounter = 1;
		int reportCounter = 1;
		int setupCounter = 1;
		int helpCounter = 1;
		
		try
		{
			Sequence sq=new Sequence();
			
			Map populateMAP = new HashMap();
			
			
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), hospitalCode);
			populateMAP.put(sq.next(), roleId);
			
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), rolesQuery, populateMAP);
	
			
		
		}
		
		catch(Exception e){
			throw new HisException(e.getMessage());
		}
		
			
	finally{
			
		}
	
	menuBuffer.append("<table width='100%' cellspacing='1' cellpadding='0' border='0'>");
	
	servicesBuffer.append("<tr>");
	servicesBuffer.append("<td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<div align=\"left\"><b><font size='2' face='verdana'>Services</font></b></div></td>");
	servicesBuffer.append("<td  width='70%' class=\"tdfonthead\" ><font size='2' face='verdana'>&nbsp;</font></td>");
	servicesBuffer.append("</tr>");
	
	reportsBuffer.append("<tr>");
	reportsBuffer.append("<td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<div align=\"left\"><b><font size='2' face='verdana'>Reports</font></b></div></td>");
	reportsBuffer.append("<td  width='70%' class=\"tdfonthead\" ><font size='2' face='verdana'>&nbsp;</font></td>");
	reportsBuffer.append("</tr>");
	

	setupBuffer.append("<tr>");
	setupBuffer.append("<td  width='30%' class=\"tdfonthead\"' >&nbsp;&nbsp;&nbsp;<div align=\"left\"><b><font size='2' face='verdana'>Setup</font></b></div></td>");
	setupBuffer.append("<td  width='70%' class=\"tdfonthead\" ><font size='2' face='verdana'>&nbsp;</font></td>");
	setupBuffer.append("</tr>");
	
	helpBuffer.append("<tr>");
	helpBuffer.append("<td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<div align=\"left\"><b><font size='2' face='verdana'>Help</font></b></div></td>");
	helpBuffer.append("<td  width='70%' class=\"tdfonthead\" ><font size='2' face='verdana'>&nbsp;</font></td>");
	helpBuffer.append("</tr>");
	
	
	try{
		
		rs.beforeFirst();
		while(rs.next())
		{
			
			 
			String menuId = rs.getString(1);
		//	 System.out.println("menuId.substring(0,1)---> "+menuId.substring(0,1));
			if(menuId.substring(0,1).equals("1"))
			{
				servicesBuffer.append("<tr><td  width='30%' bgcolor='#F5F3F3'></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+serviceCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
				serviceMenuFound = true;
				serviceCounter++;
			}
			else if(menuId.substring(0,1).equals("2"))
			{
				setupBuffer.append("<tr><td  width='30%' bgcolor='#F5F3F3'></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+setupCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
				setUpMenuFound = true;
				setupCounter++;
			}
			else if(menuId.substring(0,1).equals("3"))
			{
				reportsBuffer.append("<tr><td  width='30%' bgcolor='#F5F3F3'></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+reportCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
				reportsMenuFound = true;
				reportCounter++;
			}
			
			else if(menuId.substring(0,1).equals("4"))
			{
				helpBuffer.append("<tr><td  width='30%' bgcolor='#F5F3F3'></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+helpCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
				helpMenuFound = true;
				helpCounter++;
			}
			
			
		}
		if(serviceMenuFound || reportsMenuFound || setUpMenuFound || helpMenuFound)
			menuBuffer.append("<tr><td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Menu Details</font></b></td><td  width='70%' class=\"tdfonthead\" ><font size='2' face='verdana'>&nbsp;</font></td></tr>");
		else
			menuBuffer.append("<tr><td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Menu Details</font></b></td><td  width='70%' class=\"tdfonthead\" ><font size='2' face='verdana' color='red'>Not Available</font></td></tr>");
	
		
		if(setUpMenuFound)
			menuBuffer.append(setupBuffer);		
		
		if(serviceMenuFound)
			menuBuffer.append(servicesBuffer);
		
		if(reportsMenuFound)
			menuBuffer.append(reportsBuffer);
				
		if(helpMenuFound)
			menuBuffer.append(helpBuffer);
		
		menuBuffer.append("</table>");
		
		
		}
	catch(Exception e)
	{
		e.printStackTrace(); 
		
	}
	
	
	
	
	 return menuBuffer.toString();
	
	}
	

	

public String getUserAllMenus(String roleId,String hospitalCode) throws HisException
{
	ResultSet rs=null;
	login obj=new login();
	int result=0;
	String rolesQuery="";
	rolesQuery= qryHandler_startup.getQuery("SELECT_ALL_MENUS.GBLT_ROLE_MENU_MST"); 
	
	String arrayOfRoles=roleId.substring(0, roleId.length()-1);
	
	
	
	rolesQuery+="AND a.gnum_role_id in("+arrayOfRoles+") order by menuName"; 
	
	StringBuffer menuBuffer=new StringBuffer(100);
	StringBuffer servicesBuffer=new StringBuffer(100);
	StringBuffer reportsBuffer=new StringBuffer(100);
	StringBuffer setupBuffer=new StringBuffer(100);
	StringBuffer helpBuffer=new StringBuffer(100);
	
	boolean serviceMenuFound = false;
	boolean setUpMenuFound = false;
	boolean reportsMenuFound = false;
	boolean helpMenuFound = false;
	
	int serviceCounter = 1;
	int reportCounter = 1;
	int setupCounter = 1;
	int helpCounter = 1;
	
	try
	{
		Sequence sq=new Sequence();
		
		Map populateMAP = new HashMap();
		
		
		populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
		populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
		populateMAP.put(sq.next(), hospitalCode);
		
		
		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), rolesQuery, populateMAP);

		
	
	}
	
	catch(Exception e){
		throw new HisException(e.getMessage());
	}
	
		
finally{
		
	}

menuBuffer.append("<table width='100%' cellspacing='1' cellpadding='0' border='0'>");

servicesBuffer.append("<tr>");
servicesBuffer.append("<td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<div align=\"left\"><b><font size='2' face='verdana'>Services</font></b></div></td>");
servicesBuffer.append("<td  width='70%' class=\"tdfonthead\" ><font size='2' face='verdana'>&nbsp;</font></td>");
servicesBuffer.append("</tr>");

reportsBuffer.append("<tr>");
reportsBuffer.append("<td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<div align=\"left\"><b><font size='2' face='verdana'>Reports</font></b></div></td>");
reportsBuffer.append("<td  width='70%' class=\"tdfonthead\" ><font size='2' face='verdana'>&nbsp;</font></td>");
reportsBuffer.append("</tr>");


setupBuffer.append("<tr>");
setupBuffer.append("<td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<div align=\"left\"><b><font size='2' face='verdana'>Setup</font></b></div></td>");
setupBuffer.append("<td  width='70%' class=\"tdfonthead\" ><font size='2' face='verdana'>&nbsp;</font></td>");
setupBuffer.append("</tr>");

helpBuffer.append("<tr>");
helpBuffer.append("<td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<div align=\"left\"><b><font size='2' face='verdana'>Help</font></b></div></td>");
helpBuffer.append("<td  width='70%' class=\"tdfonthead\" ><font size='2' face='verdana'>&nbsp;</font></td>");
helpBuffer.append("</tr>");


try{
	
	rs.beforeFirst();
	while(rs.next())
	{
		
		 
		String menuId = rs.getString(1);
		// System.out.println("menuId.substring(0,1)---> "+menuId.substring(0,1));
		if(menuId.substring(0,1).equals("1"))
		{
			servicesBuffer.append("<tr><td  width='30%' bgcolor='#F5F3F3'></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+serviceCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
			serviceMenuFound = true;
			serviceCounter++;
		}
		else if(menuId.substring(0,1).equals("2"))
		{
			setupBuffer.append("<tr><td  width='30%' bgcolor='#F5F3F3'></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+setupCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
			setUpMenuFound = true;
			setupCounter++;
		}
		else if(menuId.substring(0,1).equals("3"))
		{
			reportsBuffer.append("<tr><td  width='30%' bgcolor='#F5F3F3'></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+reportCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
			reportsMenuFound = true;
			reportCounter++;
		}
		
		else if(menuId.substring(0,1).equals("4"))
		{
			helpBuffer.append("<tr><td  width='30%' bgcolor='#F5F3F3'></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+helpCounter+".&nbsp;&nbsp;"+rs.getString(2)+"</font></td></tr>");
			helpMenuFound = true;
			helpCounter++;
		}
		
		
	}
	if(serviceMenuFound || reportsMenuFound || setUpMenuFound || helpMenuFound)
		menuBuffer.append("<tr><td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Menu Details</font></b></td><td  width='70%' class=\"tdfonthead\"><font size='2' face='verdana'>&nbsp;</font></td></tr>");
	else
		menuBuffer.append("<tr><td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<b><font size='2' face='verdana'>Menu Detail</font></b></td><td  width='70%' class=\"tdfonthead\"><font size='2' face='verdana' color='red'>Not Available</font></td></tr>");

	
	if(setUpMenuFound)
		menuBuffer.append(setupBuffer);		
	
	if(serviceMenuFound)
		menuBuffer.append(servicesBuffer);
	
	if(reportsMenuFound)
		menuBuffer.append(reportsBuffer);
			
	if(helpMenuFound)
		menuBuffer.append(helpBuffer);
	
	menuBuffer.append("</table>");
	
	
	}
catch(Exception e)
{
	e.printStackTrace(); 
	
}




 return menuBuffer.toString();

}

public List getTablePrivelages(String userId,String hospitalCode) throws HisException
{
	ResultSet rs=null;
	ResultSet rs2=null;
	login obj=new login();
	int result=0;
	String privelagesQuery="";
	String tablesQuery="";
	StringBuffer privelagesBuffer=new StringBuffer(100);

List tablesList=new ArrayList();
	
	try{
		tablesQuery= qryHandler_startup.getQuery("SELECT_ALL_TABLES.GBLT_ROLE_SEAT_TABLE_DTL");
	   }
catch(Exception e)
	{
	e.printStackTrace();
	}
	
	try
	{
		Sequence sq=new Sequence();
		
		Map populateMAP = new HashMap();
		
		
		populateMAP.put(sq.next(), userId);
		populateMAP.put(sq.next(), hospitalCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), tablesQuery, populateMAP);

		
	
	}
	
	catch(Exception e){
		throw new HisException(e.getMessage());
	}
	
		
finally{
		
	}

try 
	{

	rs.beforeFirst();
	while(rs.next())
		{
		tablesList.add(rs.getString(1));
		tablesList.add(rs.getString(2));
		tablesList.add(rs.getString(3));
		tablesList.add(rs.getString(4));
		tablesList.add(rs.getString(5));
		}
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}


 return tablesList;

}


public String createPrivelages(String userId,String hospitalCode,List tablesList) throws HisException
{
	
	login obj=new login();
	int result=0;
	String privelagesQuery="";
	ResultSet rs2=null;
	
	
	StringBuffer privelagesBuffer=new StringBuffer(100);
	
	privelagesBuffer.append("<table width='100%' cellspacing='1' cellpadding='0' border='0'>");
	
try{
	
	for(int i=0 ; i < tablesList.size() ; i=i+5)
	{
		int j=i;
		privelagesBuffer.append("<tr>");
		privelagesBuffer.append("<td  width='30%' class=\"tdfonthead\" >&nbsp;&nbsp;&nbsp;<div align=\"left\"><b><font size='2' face='verdana'>"+tablesList.get(j+3)+"</font></b></div></td>");
		privelagesBuffer.append("<td  width='70%' class=\"tdfonthead\" ><font size='2' face='verdana'>&nbsp;</font></td>");
		privelagesBuffer.append("</tr>");

		
		
			
		
		
		//Creating the Dynamic Query for privelages(Data)
if(tablesList.get(j+4).equals("NA"))
{
		privelagesQuery=" SELECT DISTINCT ("+tablesList.get(j+2)+"), "+
            " (SELECT "+tablesList.get(j+3)+
               " FROM "+tablesList.get(j+1)+
              " WHERE gnum_hospital_code = a.GNUM_HOSPITAL_CODE "+
               " AND gnum_isvalid IN (1, 2) "+
               " AND "+tablesList.get(j+2)+" = a."+tablesList.get(j+2)+
               " AND ROWID ="+
                " (SELECT MAX (ROWID)"+
                " FROM "+tablesList.get(j+1)+
                "  WHERE gnum_hospital_code = a.GNUM_HOSPITAL_CODE "+
                 " AND gnum_isvalid IN (1, 2)"+
                 " AND "+tablesList.get(j+2)+"= a."+tablesList.get(j+2)+")) "+
                 " AS "+tablesList.get(j+3)+
            	" FROM "+tablesList.get(j+1)+" a "+
            	" WHERE gnum_hospital_code ="+hospitalCode+
            	" AND gnum_isvalid IN (1, 2) "+
            	" AND "+tablesList.get(j+2)+" IN ( "+
               " SELECT gnum_column_value "+
                " FROM gblt_role_seat_table_dtl "+
                " WHERE "+
                "  gnum_seatid = pkg_usermgmt.fun_getseatid ("+userId+",  a.GNUM_HOSPITAL_CODE) "+
                "  AND gnum_hospital_code = a.GNUM_HOSPITAL_CODE"+
                "  AND gbl_isvalid = '1' "+
                "  AND gnum_metatable_id = "+tablesList.get(j)+" )"+
            	"  ORDER BY "+tablesList.get(j+3);
		
	//	System.out.println("dynamic privelagesQuery-------"+privelagesQuery);
		
		try{
			rs2=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), privelagesQuery);
			
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}

}
else
{
	privelagesQuery=tablesList.get(j+4)+" IN ( "+
    " SELECT gnum_column_value "+
    " FROM gblt_role_seat_table_dtl "+
    " WHERE "+
    "  gnum_seatid = pkg_usermgmt.fun_getseatid ("+userId+",  '"+hospitalCode+"') "+
    "  AND gnum_hospital_code = '"+hospitalCode+"' "+
    "  AND gbl_isvalid = '1' "+
    "  AND gnum_metatable_id = "+tablesList.get(j)+" )"+
	"  ORDER BY orderByName ";
	
	//System.out.println("Tables from Query privelagesQuery-------"+privelagesQuery);
	
	Map populateMAP = new HashMap();	
	Sequence sq=new Sequence();	
	populateMAP.put(sq.next(), hospitalCode);
	
	
	
	try{
		rs2=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), privelagesQuery,populateMAP);
		
		}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}		
	
		
		
	
		
		
		
		
		 
		
		try
		{
			rs2.beforeFirst();
			
			int counter=1;
			while(rs2.next())
			{
			privelagesBuffer.append("<tr><td  width='30%' bgcolor='#F5F3F3'></td><td  width='70%' bgcolor='#F5F3F3'><font size='2' face='verdana'>"+counter+".&nbsp;&nbsp;"+rs2.getString(2)+"</font></td></tr>");
			counter++;
			}
		}
		catch (Exception e) {
		e.printStackTrace();	
							
		}
	
	}
	
	privelagesBuffer.append("</table>");
}
catch(Exception e)
{
	e.printStackTrace(); 
	
}




 return privelagesBuffer.toString();

}
public String marqueMsg(UserVO _userVO)
{
		String message="";
		String errorMsg="";
	try {
			ResultSet rs=null;
			Connection conn =super.getTransactionContext().getConnection();
			Procedure proc = new Procedure("pkg_usermgmt.proc_marque_msg");
			proc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			proc.addInParameter(2, Types.VARCHAR, _userVO.getSeatId());
			proc.addOutParameter(3,Types.VARCHAR);
			//proc.addOutParameter(4, OracleTypes.CURSOR);
			proc.addOutParameter(4, Types.REF);
			proc.execute(conn);
			errorMsg = (String) proc.getParameterAt(3);
			rs = (ResultSet) proc.getParameterAt(4);
			int i=1;
			while(rs.next())
			{
				message=message+" * "+(String)rs.getString(1);
				i++;
			}
		//	System.out.println("_patientVO.getPatAge():::::...."+ errorMsg);
		} catch (Exception e) {
			e.printStackTrace();
				throw new HisDataAccessException("" + e);
		}
		return message;
	}


public login[] getAllLowLevelUsersForTheUserGroup(String userId,String hospitalCode) throws HisException
{
	ResultSet rs=null;
	login[] loginObjArray=null;
	ArrayList userList= new ArrayList();
	
	String userQuery="";
	userQuery= qryHandler_startup.getQuery("SELECT_ALL_LOW_LEVEL_USERS_FOR_THE_USER_GROUP.GBLT_USER_MST"); 
	
	
	
	try
	{
		Sequence sq=new Sequence();
		
		Map populateMAP = new HashMap();
		
		populateMAP.put(sq.next(), hospitalCode);
		populateMAP.put(sq.next(), userId);
		populateMAP.put(sq.next(), userId);
			
		
		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), userQuery, populateMAP);
		rs.last();
		
		loginObjArray=new login[rs.getRow()];
		int i=0;
		
		rs.beforeFirst();
		while(rs.next())
		{
			loginObjArray[i]=new login();
		
			loginObjArray[i].setUserId(rs.getString(1));
			loginObjArray[i].setUserName(rs.getString(2));
			loginObjArray[i].setEffectiveDate(rs.getString(3));
			loginObjArray[i].setExpiryDate(rs.getString(4));
			loginObjArray[i].setStatus(rs.getString(5));
			loginObjArray[i].setIsLocked(rs.getString(6));
		i++;
		
		}
	
	}
	
	catch(Exception e){
		e.printStackTrace();
		throw new HisException(e.getMessage());
	}
	
		
finally{
		
	}

 return loginObjArray;

 }

public int resetPassword(String superUserId,String userId,String hospitalCode,String userPassword) throws HisException
{
	ResultSet rs=null;
	login[] loginObjArray=null;
	int result=0;
	
	String resetQuery="";
	resetQuery= qryHandler_startup.getQuery("UPDATE_RESET_PASSWORD.GBLT_USER_MST"); 
	
	
	String emptyStr="";
	String lockStatus="0";
	
	try
	{
		Sequence sq=new Sequence();
		
		Map populateMAP = new HashMap();
		
	  	populateMAP.put(sq.next(),lockStatus);
		populateMAP.put(sq.next(), emptyStr);
		populateMAP.put(sq.next(), emptyStr);
		populateMAP.put(sq.next(), userPassword);
		populateMAP.put(sq.next(), superUserId);
		populateMAP.put(sq.next(), userId);
		populateMAP.put(sq.next(), hospitalCode); 
			
		
		result=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), resetQuery, populateMAP);
		
		
	
	}
	
	catch(Exception e){
		e.printStackTrace();
		throw new HisException(e.getMessage());
	}
	
		
finally{
		
	}

 return result;

 }

public int resetPasswordUnsucessfullLog(String userId,String hospitalCode) throws HisException
{
	ResultSet rs=null;
	login[] loginObjArray=null;
	int result=0;
	
	String resetQuery="";
	resetQuery= qryHandler_startup.getQuery("UPDATE_RESET_PASSWORD.GBLT_LOGIN_UNSUCESSFUL_LOG"); 
	
	
	
	try
	{
		Sequence sq=new Sequence();
		
		Map populateMAP = new HashMap();
			  	
		populateMAP.put(sq.next(), userId);
		populateMAP.put(sq.next(), hospitalCode); 
			
		
		result=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), resetQuery, populateMAP);
		
		
	
	}
	
	catch(Exception e){
		e.printStackTrace();
		throw new HisException(e.getMessage());
	}
	
		
finally{
		
	}

 return result;

 }

//Procedure for getting the Menus for the User// 

public ResultSet getMenuForUser(String _hospitalCode,String _seatId)
{
		ResultSet rs=null;
		String errorMsg="";
	try {
		
			Connection conn =super.getTransactionContext().getConnection();
			Procedure proc = new Procedure("pkg_usermgmt.proc_menu");
			proc.addInParameter(1, Types.VARCHAR,_hospitalCode);
			proc.addInParameter(2, Types.VARCHAR, _seatId);
			proc.addOutParameter(3,Types.VARCHAR);
			//proc.addOutParameter(4, OracleTypes.CURSOR);
			proc.addOutParameter(4, Types.REF);
			
			proc.execute(conn);
			errorMsg = (String) proc.getParameterAt(3);
			rs = (ResultSet) proc.getParameterAt(4);
		
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
				throw new HisDataAccessException("" + e);
		}
		return rs;
	}
/* @Author: Amit Chhikara 
 * Creation Date: 31-07-2014
 * Purpose: to get menu module wise
 * 
 * 
 */
public ResultSet getMenuForUserModuleWise(String _hospitalCode,String _seatId)
{
		ResultSet rs=null;
		String errorMsg="";
		JDBCTransactionContext tx=new JDBCTransactionContext();
	try {
			tx.begin();
			Connection conn =tx.getConnection();
			Procedure proc = new Procedure("pkg_usermgmt.proc_gblt_menu_mst");
			proc.addInParameter(1, Types.VARCHAR,"1");
			proc.addInParameter(2, Types.VARCHAR,_hospitalCode);
			proc.addInParameter(3, Types.VARCHAR, "0"); // user id not used in mode 1
			proc.addInParameter(4, Types.VARCHAR, _seatId);
			proc.addOutParameter(5,Types.VARCHAR);
			//proc.addOutParameter(4, OracleTypes.CURSOR);
			proc.addOutParameter(6, Types.REF);
			
			proc.execute(conn);
			errorMsg = (String) proc.getParameterAt(5);
			rs = (ResultSet) proc.getParameterAt(6);
		
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
				throw new HisDataAccessException("" + e);
		}finally{
			try{
				tx.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return rs;
	}
}