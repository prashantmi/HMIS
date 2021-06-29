package startup;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.UserVO;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;




//import HisGlobal.*;

public class login
{
	private String uid="";
	private String pwd="";
	private String seatId="";
	private String empId="";
	private String userName = "" ;
	private String hintQuestion = "" ;
	private String answer = "" ;
	private String userId   = "" ;
	private String userLevel   = "" ;
    //14may08
	private String hospitalCode   = "" ;
	private String questionId="";
	private String userFullName="";
	private String mobileNo="";
	private String emailId="";
	private String isLocked="";
	private ArrayList ipaddress=new ArrayList();
	private String changePasswordDate="";
	private int noOfDaysBeforePasswordChanged=0;
	private String status="";
	private String effectiveDate="";
	private String expiryDate="";
	
	private String dateField="";
	private String month="";
	private String year="";
	private String hour="";
	private String minute="";
	private String seconds="";
	

	/*setter methods
	 * 
	 * 
	 */
	
	
	public String getDateField() {
		return dateField;
	}
	public void setDateField(String dateField) {
		this.dateField = dateField;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getSeconds() {
		return seconds;
	}
	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}
	public String getChangePasswordDate() {
		return changePasswordDate;
	}
	public void setChangePasswordDate(String changePasswordDate) {
		this.changePasswordDate = changePasswordDate;
	}
	public int getNoOfDaysBeforePasswordChanged() {
		return noOfDaysBeforePasswordChanged;
	}
	public void setNoOfDaysBeforePasswordChanged(
			int noOfDaysBeforePasswordChanged) {
		this.noOfDaysBeforePasswordChanged = noOfDaysBeforePasswordChanged;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}
	public void setUserName(String userName){
		this.userName=userName;
	}
	public void setUid(String uid){
		this.uid=uid;
	}
	public void setPwd(String pwd){
		this.pwd=pwd;
	}
	public void setUserLevel(String abc){
		this.userLevel=abc;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	
	
	/*getter methods
	 * 
	 * 
	 * 
	 */
	public String getUserFullName() {
		return userFullName;
	}
	public String getUid(){
		return this.uid;
	}
	public String getPwd(){
		return this.pwd;
	}

	public String getSeatId(){
		return this.seatId;
	}
	public String getEmpId(){
		return this.empId;
	}	
	public String getUserId(){
		return this.userId;
	}
	public String getUserName(){
		return this.userName;
	}
	public String getUserLevel(){
		return this.userLevel;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getHintQuestion() {
		return hintQuestion;
	}
	public void setHintQuestion(String hintQuestion) {
		this.hintQuestion = hintQuestion;
	}
	public String getAnswer() {
		return answer;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	public ArrayList getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(ArrayList ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	public String getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	/*
	methods used in bean
	*/
	/*
	*	The following function is added by Ashish Nayyar.
	*	This function will encrypt the password using MD5 encryption algo  
	*/
	public String applyMD5(byte[] b) throws HisException
	{
		MessageDigest md	=	null;
		String encryptedString 	=	"";
		try
		{
			md=MessageDigest.getInstance("MD5");
			md.reset();
			md.update(b);
			byte [] hash	=	md.digest();
			
			for(int i=0;i<hash.length;i++)
			{
				int v=hash[i] & 0xFF;
				if(v<16) 
					encryptedString	+="0";
				encryptedString	+=Integer.toString(v,16).toUpperCase();
			}
			//System.out.println("Encrypted password "+ encryptedString);
		}
		catch(Exception e){
			throw new HisException(e.getMessage());
		}
		return encryptedString;
	}
  
	public login validateUid(String ipnumber) throws HisException
	{
		
	
		
		login loginObj=new login(); 
		
		JDBCTransactionContext tx=new JDBCTransactionContext();
		loginDao dao=new loginDao(tx);
			
		try{
			tx.begin();
			loginObj=dao.validateUidDao(this.uid,SecurityUtil.encrypt(this.pwd), ipnumber);
					
		}
		catch(Exception e){
			
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally{
			tx.close();
			
		}
		return loginObj;
	}//End of validateUid
	
	//here we are maintaing the log of the user being login
	
	public login maintainLog(String userId,String seatId,String ipnumber,String hospitalCode) throws HisException
	{
		
	
		
		login loginObj=new login(); 
		
		JDBCTransactionContext tx=new JDBCTransactionContext();
		loginDao dao=new loginDao(tx);
		
		
		
		int result=0;
				
		
		
		
		try{
			tx.begin();
				result=dao.maintainLog(userId,seatId,ipnumber,hospitalCode);	
				//dao.updateUnsuccessfulLoginLog(this.uid);
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally{
			tx.close();
			
		}
		return loginObj;
	}//End of validateUid
	//Getting the Hint Questions from the Database and setting into the request
	public void hintQuestions(HttpServletRequest request) throws HisException
	{
		//After creating successfull session ,we are getting the hint questions from database
		
		 
		
		JDBCTransactionContext tx=new JDBCTransactionContext();
		loginDao dao=new loginDao(tx);
						
		
		try{
			tx.begin();
		dao.hintQuestionsDao(request);
			}
		catch(Exception e){
			
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally{
			tx.close();
			
		}
		
	}
	//Function used for checking old Password
	public int oldPasswordCheck(HttpServletRequest request) throws HisException
	{
		//After creating successfull session ,we are getting the hint questions from database
		// System.out.println("entry in password check--->");
		 int validUser=0;
			
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
						
			
			try{
				tx.begin();
				validUser=dao.oldPasswordCheckDao(request);
				// System.out.println("entry in oldPasswordCheck check--->"+validUser);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
			return validUser;
			

	}
	//function for updating password and giving hint question and answer
	//After creating successfull session ,we are updating  the password details and hint question
	public int firstLogin(HttpServletRequest request) throws HisException
	{
		 
		 int firstLogin=0;
			
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
						
			
			try{
				tx.begin();
				firstLogin=dao.firstLoginDao(request);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
		return firstLogin;	
		
		
	}
	//function for updating password only 
	public int updatePasswordOnly(HttpServletRequest request) throws HisException
	{
		 
		    int result=0;
			
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
						
			
			try{
				tx.begin();
				result=dao.updatePasswordOnly(request);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
			
		return result;
		
	}
	//Function used for getting Forgot Password
	public Map forgotPassword(HttpServletRequest request) throws HisException
	{
		//After creating successfull session ,we are getting the hint questions from database
				
			Map resultantMAP = new HashMap();
		 
			
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
					
			
			try{
				tx.begin();
				resultantMAP=dao.forgotPassword(request);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
return resultantMAP;
		
	}
	//
	public int newPassword(HttpServletRequest request) throws HisException
	{
		//After creating successfull session ,we are getting the hint questions from database
				
		int result=0;
		 
			
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
							
			
			try{
				tx.begin();
				result=dao.newPassword(request);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
return result;
		
	}
	//FUNCTION USED FOR UPDATING USER DETAILS
	
	public int updateUserDetails(HttpServletRequest request) throws HisException
	{
		//After creating successfull session ,we areupdating the user details
		
				
		int validUser=0;
		 
			
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
		
			
			try{
				tx.begin();
				validUser=dao.updateUserDetails(request);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
return validUser;
		
	}
	//function for updating audit log of user details
	public int updateAuditLog(HttpServletRequest request,String oldValue) throws HisException
	{
		 
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			int result=0;		
			
			
		   	String moduleId = "12" ; //menuId.substring(3,5);
		   	String menuId = "10" ;  //request.getParameter("menuId");
		   	String ipAddress = request.getRemoteAddr();
			String primarKeyValue="";			
		   	String userName="";
		   	String seatId="";
		   	String tableName="";

		   	
		   	
		   	primarKeyValue=(String)request.getSession().getAttribute("USER_ID")+"^"+
		   					request.getSession().getAttribute("HOSPITAL_CODE");
		    
		 
		   	String newValue="GSTR_USR_NAME"+"^"+request.getParameter("userFullName")+"#"+
		   			 "GNUM_QUESTION_ID"+"^"+request.getParameter("hintQuestion")+"#"+ 
		   			 "GSTR_HINT_ANSWER"+"^"+request.getParameter("answer")+"#"+
		   			 "GNUM_MOBILE_NUMBER"+"^"+request.getParameter("mobileNo")+"#"+
		   			 "GSTR_EMAIL_ID"+"^"+request.getParameter("emailId");
		   
		  
		   	
		   	//Since The above code is treated as a Single Line String Declaration
			//Hence we are using string otherwise we would have used  StringBuilder /StringBuffer
			//if we were making multiple strings while string concatination as the String is immutable				
			 	
		   	
		   	seatId=(String)request.getSession().getAttribute("ACTUALSEATID");
		   	userId=(String)request.getSession().getAttribute("USER_ID");
		   	userName=(String)request.getSession().getAttribute("userName");
		   	tableName="GBLT_USER_MST";
		   	
		   // System.out.println("pkValue ---"+primarKeyValue);
		  //  System.out.println("oldValue ---"+oldValue);
		 //   System.out.println("newValue ---"+newValue);
		   	
			
			try{
				tx.begin();
			 result=dao.updateAuditLogDao(request,moduleId,menuId,primarKeyValue,oldValue,newValue,seatId,userId,ipAddress,userName,tableName);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
			
		return result;
		
	}//auditlog for first time login
	
	
	public int updateAuditLogFirstTimeLogin(HttpServletRequest request,String oldValue) throws HisException
	{
		 
			
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			int result=0;		
			
			
		   	String moduleId = "12" ; //menuId.substring(3,5);
		   	String menuId = "10" ;  //request.getParameter("menuId");
		   	String ipAddress = request.getRemoteAddr();
			String userName="";
		   	String seatId="";
		   	String tableName="";

		   	
		   	
		   	String primarKeyValue=(String)request.getSession().getAttribute("USER_ID")+"^"+
		   					request.getSession().getAttribute("HOSPITAL_CODE");
		    
		   	String newValue="GNUM_QUESTION_ID"+"^"+request.getParameter("hintQuestion")+"#"+ 
		   			 "GSTR_HINT_ANSWER"+"^"+request.getParameter("answer");
		   	
		
		   	
		   	//Since The above code is treated as a Single Line String Declaration
			//Hence we are using string otherwise we would have used  StringBuilder /StringBuffer
			//if we were making multiple strings while string concatination as the String is immutable				
				   	
	
		
		   	
		   	
		   	
		   	seatId=(String)request.getSession().getAttribute("ACTUALSEATID");
		   	userId=(String)request.getSession().getAttribute("USER_ID");
		   	userName=(String)request.getSession().getAttribute("userName");
		   	tableName="GBLT_USER_MST";
		   	
		//   	System.out.println("pkValue ---"+primarKeyValue);
		  //  System.out.println("oldValue ---"+oldValue);
		  //  System.out.println("newValue ---"+newValue);
		   	
			
			try{
				tx.begin();
			 result=dao.updateAuditLogDao(request,moduleId,menuId,primarKeyValue,oldValue,newValue,seatId,userId,ipAddress,userName,tableName);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
			
		return result;
		
	}
	//auditlog for change  password in case he forgets his password
	
	public int updateAuditLogForgotPassword(HttpServletRequest request,String oldValue,String newValue) throws HisException
	{
		 
						
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			int result=0;		
			
			
		   	String moduleId = "12" ; //menuId.substring(3,5);
		   	String menuId = "10" ;  //request.getParameter("menuId");
		   	String ipAddress = request.getRemoteAddr();
			String userName="";
		   	String seatId="";
		   	String tableName="";

		   	
		   	//	System.out.println("USER_ID value in auditlog of login===="+request.getAttribute("USER_ID"));
			
		   	String primarKeyValue=(String)request.getAttribute("USER_ID")+"^"+
		   					request.getAttribute("HOSPITAL_CODE");
		    

		  	//Since The above code is treated as a Single Line String Declaration
			//Hence we are using string otherwise we would have used  StringBuilder /StringBuffer
			//if we were making multiple strings while string concatination as the String is immutable				
				   	
		  
		   	
		   	
		   	seatId=(String)request.getAttribute("USER_SEATID");
		   	userId=(String)request.getAttribute("USER_ID");
		   	userName=(String)request.getAttribute("USER_NAME");
		   	tableName="GBLT_USER_MST";
		   	
		 //  	System.out.println("pkValue ---"+primarKeyValue);
		 //   System.out.println("oldValue ---"+oldValue);
		 //   System.out.println("newValue ---"+newValue);
		   	
			
			try{
				tx.begin();
			    result=dao.updateAuditLogDao(request,moduleId,menuId,primarKeyValue,oldValue,newValue,seatId,userId,ipAddress,userName,tableName);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
			
		return result;
		
	}
	//logout function
	public void logoutUser(String userId,String ipnumber,String _hospitalCode) throws HisException
	{			
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
					
			try{
				tx.begin();
				dao.logoutUser(userId,ipnumber,_hospitalCode);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
		
	}
	
	public void createUnsuccessfulLoginLog(String userName,String ipAddress) throws HisException
	{
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
									
			try{
				tx.begin();
				dao.createUnsuccessfulLoginLog(userName,ipAddress);
				//dao.maintainLogForUnsuccessFullLogin(uid,ipNo,hospitalCode);
				//numberOfUnsuccessFulLogin=dao.selectUnsuccessfulLoginLog(userName);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
		
	}
	
	public int selectUnsuccessfulLoginLog(String userName) throws HisException
	{
		
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			int numberOfUnsuccessFulLogin=0;		
			
			try{
				tx.begin();				
				numberOfUnsuccessFulLogin=dao.selectUnsuccessfulLoginLog(userName);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
		return numberOfUnsuccessFulLogin;
	}
	
	
	public int selectMaxUserLicenseHospWise(String hospCode) throws HisException
	{
		
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			int numberOfLicense=0;		
			
			try{
				tx.begin();				
				numberOfLicense=dao.selectMaxUserLicenseHospWise(hospCode);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
		return numberOfLicense;
	}
	
	public void updateUnsuccessfulLoginLog(String userName) throws HisException
	{
	
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			try{
				tx.begin();
				dao.updateUnsuccessfulLoginLog(userName);
				//numberOfUnsuccessFulLogin=dao.selectUnsuccessfulLoginLog(userName);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
		
	}

	public void lockUserId(String userName) throws HisException
	{
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			try{
				tx.begin();
				dao.updateIsLocked(userName);
				//numberOfUnsuccessFulLogin=dao.selectUnsuccessfulLoginLog(userName);
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
		
	}
	
	public Map getUserRolesAndPrivelages(String userId,String hospitalCode) throws HisException
	{
		login loginObj=new login();
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			String rolesList="";
			String[] rolesArray=null;
			String[] roleIdArray=null;
			String[] rolenameArray=null;
			String privelagesList="";
			List tablesList=new ArrayList();			
			Map EssentialMap=new HashMap();
			
			try{
				tx.begin();
				rolesList=dao.getUserRolesAndPrivelages( userId,hospitalCode);
				
			
			
				
				rolesArray=rolesList.split("#");
				
				if(rolesArray!=null)
				{	
				
				roleIdArray=rolesArray[0].split("@");
				rolenameArray=rolesArray[1].split("@");
				
				if(rolesList!=null)
					EssentialMap.put(Config.LIST_OF_ROLES_CONCATE,rolesList);
				
				if(roleIdArray!=null)
					EssentialMap.put(Config.LIST_OF_ROLES_ID,roleIdArray);
				
				if(rolenameArray!=null)
					EssentialMap.put(Config.LIST_OF_ROLES_NAME,rolenameArray);
				
				}
				
				if(roleIdArray.length==1 && rolenameArray.length==1)
				{
					String roleId=roleIdArray[0];				
					String menusList=loginObj.getUserMenus(roleId, hospitalCode);
							
					if(menusList!=null)
						EssentialMap.put(Config.LIST_OF_MENUS,menusList);
					
				}
				
				tablesList=dao.getTablePrivelages(userId, hospitalCode);
				
		
				if(tablesList!=null)
					privelagesList=dao.createPrivelages(userId, hospitalCode,tablesList);
				
				if(privelagesList!=null)
					EssentialMap.put(Config.LIST_OF_PRIVELAGES,privelagesList);
					
			
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
		return EssentialMap;
	}
	
	
	public String getUserMenus(String roleId,String hospitalCode) throws HisException
	{
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			String menusList="";
			
			try{
				tx.begin();
				menusList=dao.getUserMenus( roleId,hospitalCode);
			
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
		return menusList;
	}
	
	public String getUserAllMenus(String roleId,String hospitalCode) throws HisException
	{
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			String allMenusList="";
			
			try{
				tx.begin();
				allMenusList=dao.getUserAllMenus( roleId,hospitalCode);
			
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
		return allMenusList;
	}
	public String getMarqueMessage(UserVO _userVO){
		
		String message="";       
        

        JDBCTransactionContext tx =new JDBCTransactionContext();
		try{
			tx.begin();
			
			loginDao objEssentialDAO=new loginDao(tx);
			message=objEssentialDAO.marqueMsg(_userVO);
	        
		}
		catch(HisRecordNotFoundException e){
			tx.rollback();
			e.getEssentialMap();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
		//	System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return message;
	}

	
	public login[] getAllLowLevelUsersForTheUserGroup(String userId,String hospitalCode) throws HisException
	{
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			login[] loginObjArray=null;
			
			try{
				tx.begin();
				loginObjArray=dao.getAllLowLevelUsersForTheUserGroup(userId,hospitalCode);
			
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
		return loginObjArray;
	}
	
	public int resetPassword(String superUserId,String userId,String hospitalCode, String userPassword) throws HisException
	{
			JDBCTransactionContext tx=new JDBCTransactionContext();
			loginDao dao=new loginDao(tx);
			int result1=0;
			int result2=0;
			
			try{
				tx.begin();
				result1=dao.resetPassword(superUserId,userId,hospitalCode,userPassword);
				
			if(result1 > 0)
				result2=dao.resetPasswordUnsucessfullLog(userId, hospitalCode);
			
				}
			catch(Exception e){
				
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally{
				tx.close();
				
			}
						
		return result1;
	}
	
}
