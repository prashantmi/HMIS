package startup;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginActionFunctions extends loginAction{

	public RequestDispatcher loginUser(HttpServletRequest request,HttpServletResponse response, ServletContext context)
	{
		RequestDispatcher rd=null;
		
		
		login loginObj = new login();
		
		String	uid	=request.getParameter("uid");
		String	pwd	=request.getParameter("pwd");
		
		
		loginObj.setUid(uid);
		loginObj.setPwd(pwd);
		
	//	System.out.println("before validating  -uid----"+uid+"--------pwd----"+pwd);
					
		loginObj=loginObj.validateUid(request.getRemoteAddr());
		
		//System.out.println("seat id value inside login action ----"+loginObj.getSeatId());
	
		if(loginObj.getSeatId().equals("INVALID_IP_ADDRESS"))
		{
			//System.out.println(" inside login action 1----"+loginObj.getSeatId());
		
			request.setAttribute("message","User Is Not Allowed To Login From This System");
			rd = request.getRequestDispatcher("../startup/index.jsp");
	//		rd.forward(request,response);
		}
		else	
			if(loginObj.getSeatId().equals("NONE"))		//login by Invalid user
			{
				loginObj.createUnsuccessfulLoginLog(request.getParameter("uid"),request.getRemoteAddr());
				int numberOfUnsuccessFulLogin=loginObj.selectUnsuccessfulLoginLog(request.getParameter("uid"));
				UserMgmtConfigXmlHandler configHandler=new UserMgmtConfigXmlHandler();
				int blockAfter=configHandler.getBlockAfter();
				if(numberOfUnsuccessFulLogin == blockAfter)
				{
					loginObj.lockUserId(request.getParameter("uid"));
					loginObj.updateUnsuccessfulLoginLog(request.getParameter("uid"));
				}
				request.setAttribute("message","Invalid User Id or Password");
				
				
				
				rd = request.getRequestDispatcher("../startup/index.jsp");
		//		rd.forward(request,response);
        	}
		if(loginObj.getSeatId().equals("NO_SEAT_ASSOCIATED"))
		{
			//System.out.println(" inside login action 1----"+loginObj.getSeatId());
		
			request.setAttribute("message","User Is Not Allowed To Login ");
			rd = request.getRequestDispatcher("../startup/index.jsp");
		}
		else 
			if(!loginObj.getSeatId().equals("NONE"))	//login by valid user
			{	
			
//			System.out.println("in login action hospitalid=="+loginObj.getHospitalCode());
			///Checking if the USER ID is locked
			
			if(loginObj.getIsLocked().equals("1"))
			{
				//request.setAttribute("message","");
				throw new HisInvalidUserNameException("User Id Is Locked!! Contact System Administrator");
			}
				
			
			
			String userId = loginObj.getUserId();
//			System.out.println("HOspital Code Form Query--->"+loginObj.getHospitalCode());
//			System.out.println("User Id--->"+userId);
			request.setAttribute("HOSPITAL_CODE",loginObj.getHospitalCode());//14may08
			//request.setAttribute("SEATID",loginObj.getSeatId());
			/*The above changes done due to change in user managment
			 * in user managment now we mapped user id in all tables as seat id 
			 */
			
			try{
			request.setAttribute("SEATID",userId);
			request.setAttribute("ACTUALSEATID", loginObj.getSeatId());
			request.setAttribute("EMPID",loginObj.getEmpId());
			request.setAttribute("userId",userId);
			request.setAttribute("userName",loginObj.getUserName());
			request.setAttribute("IP_ADDR",request.getRemoteAddr());
			request.setAttribute("USER_LEVEL",loginObj.getUserLevel());
			//request.setAttribute("PassWord",loginObj.getPwd());
			request.setAttribute("UserFullName",loginObj.getUserFullName());	
			request.setAttribute("PASSWORD_CHANGED_DATE",loginObj.getChangePasswordDate());
			request.setAttribute("DAYS_BEFORE_PASSWORD_CHANGED",loginObj.getNoOfDaysBeforePasswordChanged());
			
			
			//Code for getting the Date Format and Setting it into the request
			//which will be later set into the session
			
			List dateAndFormatList = new ArrayList();
			dateAndFormatList=getDateAndFormatList(loginObj);
						
			String sysdate = (String) dateAndFormatList.get(0);
			Date dateObj = (Date) dateAndFormatList.get(1);

			request.setAttribute(Config.SYSDATE_LOGIN, sysdate);
			request.setAttribute(Config.SYSDATEOBJECT_LOGIN, dateObj);

			
			
//	System.out.println("answerin loginObj----"+loginObj.getAnswer());
			
	if(loginObj.getAnswer()==null)
		{   request.setAttribute("ANSWER","");	}
	else
		{	request.setAttribute("ANSWER",SecurityUtil.decrypt(loginObj.getAnswer())); }	
	
			request.setAttribute("MOBILE_NO",loginObj.getMobileNo());	
			request.setAttribute("EMAIL_ID",loginObj.getEmailId());
			request.setAttribute("QUESTION_ID",loginObj.getQuestionId());	
			}
			catch(Exception e)
			{
			e.printStackTrace();	
			}
	 		
			//System.out.println("USER LEVEL--"+loginObj.getUserLevel());
			
			boolean status = false;
			status = createUserSession(request,response,userId,context);
			
			////Deleting rows from user unsuccessful log
			loginObj.updateUnsuccessfulLoginLog(loginObj.getUserName());
			//////////
	//		System.out.println("SINGLE_LOGIN_STATUS--->"+request.getAttribute("SINGLE_LOGIN_STATUS"));
			
			String singleLoginStatus=(String)request.getAttribute("SINGLE_LOGIN_STATUS");
			
			// hospLicenseStatus=(String)request.getAttribute("HOSP_LICENSE_STATUS");
			
			if(singleLoginStatus==null)
				singleLoginStatus="ALLOWED";
			
			/*if(hospLicenseStatus==null)
				hospLicenseStatus="ALLOWED";*/
		
			
	//		System.out.println("loginObj.getQuestionId() --->"+loginObj.getQuestionId());
			
			
			
		//First we check whether the difference of default no of days specified 
	    // and the no of days before password is changed is less than or equal to 3		
			
			UserMgmtConfigXmlHandler configHandler=new UserMgmtConfigXmlHandler();
			int paswModificationTime=configHandler.getPasswModificationTime();
								
			
int checkDateDifference=paswModificationTime-loginObj.getNoOfDaysBeforePasswordChanged();					
			
//System.out.println("checkDateDifference------------"+checkDateDifference);


//this parameter prevents from making the session null  &&
//if the user refreshes again in case of  change password page comes as an interface
///the user goes to the earlier page again this prevents the user

String paswCheck=request.getParameter("CHANGE_PASSWORD_CHECK");

//System.out.println("paswCheck------------"+paswCheck);

if(paswCheck==null)
	paswCheck="";
			


///Checking whether the expiry time(E.T.) is >0 &&  E.T.<=NO_OF_DAYS_BEFORE_TO_ASK_USER_FOR_PASSWORD_CHANGED
///&& PASSWORD MODIFICATION TIME !=0  && paswCheck==NO
//CASE1 :: Prompting the user to change the password some days before it is about to expire

if(checkDateDifference<=Config.NO_OF_DAYS_BEFORE_TO_ASK_USER_FOR_PASSWORD_CHANGED && checkDateDifference>0 && paswModificationTime!=0 && !paswCheck.equals("NO"))
			   {
								    
									
						request.setAttribute("message"," ");
						request.setAttribute("PASSWORD", pwd);
						
						
						request.getSession().setAttribute("PASSWORD_CHANGE", "true");
						request.getSession().setAttribute("TIME_TO_EXPIRE", checkDateDifference);
						
					//	rd=request.getRequestDispatcher("../startup/UpdateUser?hmode=CHANGE_PASSWORD");
						
						rd = request.getRequestDispatcher("../startup/changePasswordAfterNoOfDays.jsp");
				}else
//Forcefully Changing the Password of the user					
///Checking whether the expiry time(E.T.) is  < && PASSWORD MODIFICATION TIME !=0  && paswCheck==NO
if(checkDateDifference<=0 && paswModificationTime!=0 && !paswCheck.equals("NO"))
			   {
								
								request.setAttribute("PASSWORD", pwd);
								request.setAttribute("message"," ");
								
						request.getSession().setAttribute("PASSWORD_CHANGE", "false");
						request.getSession().setAttribute("TIME_TO_EXPIRE", checkDateDifference);
								
						rd = request.getRequestDispatcher("../startup/changePasswordAfterNoOfDays.jsp");
						
					//	rd=request.getRequestDispatcher("../startup/UpdateUser?hmode=CHANGE_PASSWORD");
			   }else	

//now we are checking whether the user has given any hint question and answer
// i.e. ( when he logins for the first time)
//if no then he is asked to give any hint question and answer and if he wants to change his 
//old password ,he can change here when he logins for the first time 
		//	System.out.println("Password before 1st login --->"+request.getParameter("pwd"));
			
				if(loginObj.getQuestionId()==null || loginObj.getQuestionId().equals(""))
		   {
						//fn called for getting the hintQueestions combo from database	    
					//request.setAttribute("PASSWORD", pwd);
				//	request.setAttribute("password",request.getParameter("pwd"));
					loginObj.hintQuestions(request);
					request.setAttribute("message"," ");
					rd = request.getRequestDispatcher("../startup/userQuestionDetail.jsp");
			}//if 	user has not login for the first time and he has saved hint question and answer
				else
			{
				if(status == true)
					{
						rd = request.getRequestDispatcher("../startup/mainPage.jsp?userId="+loginObj.getUserId()+"&seatId="+loginObj.getSeatId()+"&empId="+loginObj.getEmpId());
					}
					else
				if(singleLoginStatus.equals("NOT_ALLOWED") && status == false)	
					{
						//request.setAttribute("message","Invalid User-Name or Password");
						rd = request.getRequestDispatcher("../startup/index.jsp");
					
					}
					else
				if(singleLoginStatus.equals("ALLOWED") && status == false)	
						
					{
						request.setAttribute("message","Invalid User-Name or Password");
						rd = request.getRequestDispatcher("../startup/index.jsp");
					}
				/*if(hospLicenseStatus.equals("NOT_ALLOWED") && status == false)	
					
				{
					request.setAttribute("message","Maximum allowed users are already loggin in.Your login is denied.");
					rd = request.getRequestDispatcher("../startup/index.jsp");
				}*/
		
			
			}//inner else closed
			
							
			
	//			rd.forward(request,response);
	
		}//inner  if closed of !loginObj.getSeatId().equals("NONE")	
		
		
		return rd;
		
	}
	
	
	//Checking For Single/Multiple Login from a userId Allowed/Not Allowed 
	//Setting the Marque Message in Session
	
	private boolean createUserSession(HttpServletRequest request,HttpServletResponse response,String userId,ServletContext context)
	{
		login loginObj = new login();
		//ServletContext context 		= 	super.getServletContext();
		ArrayList loginUserList 	= 	new ArrayList();
		loginUserList				=	(ArrayList) context.getAttribute("LOGIN_USER");
		boolean status 				= 	false;
		int noOfLicenses=0;
		String[] temp;
		String val="";
		
	try{	
		
		
		if(loginUserList == null)	//no user login
		{
			loginUserList = new ArrayList();
			status = userCanLogin(context,request,response,userId,loginUserList); //First User Login
			
		}
		else						//some user alredy login
		{
			System.out.println("Logged In User List size....................."+loginUserList.size());
			int idx = -1;

			UserMgmtConfigXmlHandler configHandler=new UserMgmtConfigXmlHandler();
			configHandler.getLoginCount();
			///If Single or Multiple login Allowed { Single-->1 ,Multiple -->2 }
			if(configHandler.getLoginCount()==1)
			{
			
			for(int i=0;i<loginUserList.size();i++)
				{
					val = (String)loginUserList.get(i);
					temp =val.split("#");
					
			
					
					if(temp[0].equals(userId))
					{	
						idx = i;
						break;
					}
				}

			}
			
			//idx==-1 -->User is not login in any other place
			//idx!=-1 -->User is login in any other place also
			
			if(idx == -1) //New user come : Still Not login at mail server
			{
			//	String userId=(String)request.getAttribute("userId");
				String seatId=(String)request.getAttribute("ACTUALSEATID");
				String ipnumber=request.getRemoteAddr();
				String hospitalCode=(String)request.getAttribute("HOSPITAL_CODE");
				
				
				loginObj.maintainLog(userId,seatId,ipnumber,hospitalCode);
				status = userCanLogin(context,request,response,userId,loginUserList);
				
				//noOfLicenses=loginObj.selectMaxUserLicenseHospWise(hospitalCode);
				
				System.out.println("noOfLicenses...."+noOfLicenses);
				
				/*if(loginUserList.size()>noOfLicenses)
				{
					request.setAttribute("HOSP_LICENSE_STATUS","NOT_ALLOWED");
					status = false;
				}*/
				
				
				
			}
			else
			{
				val = (String)loginUserList.get(idx);
				temp = val.split("#");
			//	System.out.println("Login Failed : User id "+loginUserList.get(idx)+" already login");
				request.setAttribute("message","User ID "+temp[1]+" is already login on IP Address "+temp[2]);
				request.setAttribute("SINGLE_LOGIN_STATUS","NOT_ALLOWED");
				status = false;
			}
			 
			
		}
		
		//EssentialDelegate essentialDelegate=new EssentialDelegate();
String marqueMessage="";
	
	//The marque message is Commented due to Performance issues
	marqueMessage=loginObj.getMarqueMessage(ControllerUTIL.getUserVO(request));
//System.out.println("marquemessage "+marqueMessage);		
if(marqueMessage==null)
		{ 
			marqueMessage="";
		}
		request.getSession().setAttribute(Config.MARQUE_MESSAGE, marqueMessage);
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
		
		
		return status;
	}
	
	//Setting the Various User related Values in Session from the Request
	
	private boolean userCanLogin(ServletContext context,HttpServletRequest request,HttpServletResponse response,String userId,ArrayList loginUserList)
	{
		boolean status = false;
		try
		{
			HttpSession session = request.getSession(false);
			
		if(session==null)
		    session=request.getSession(true);
		
		
			
			session.setAttribute("USER_ID",userId);
			session.setAttribute("SEATID",request.getAttribute("SEATID"));
			session.setAttribute("ACTUALSEATID",request.getAttribute("ACTUALSEATID"));
			session.setAttribute("HOSPITAL_CODE",request.getAttribute("HOSPITAL_CODE"));//14may08
			session.setAttribute("EMPID",request.getAttribute("EMPID"));
			session.setAttribute("userId",userId);
			session.setAttribute("userName",request.getAttribute("userName"));
			session.setAttribute("IP_ADDR",request.getAttribute("IP_ADDR"));
			session.setAttribute("UserFullName",request.getAttribute("UserFullName"));
			session.setAttribute("QUESTION_ID",request.getAttribute("QUESTION_ID"));
			session.setAttribute("ANSWER",request.getAttribute("ANSWER"));
			session.setAttribute("MOBILE_NO",request.getAttribute("MOBILE_NO"));
			session.setAttribute("EMAIL_ID",request.getAttribute("EMAIL_ID"));
			session.setAttribute("USER_LEVEL",request.getAttribute("USER_LEVEL"));
			session.setAttribute("PASSWORD_CHANGED_DATE",request.getAttribute("PASSWORD_CHANGED_DATE"));
			session.setAttribute("DAYS_BEFORE_PASSWORD_CHANGED",request.getAttribute("DAYS_BEFORE_PASSWORD_CHANGED"));
						

			//changes done by ankur in case of marque message
			session.setAttribute(Config.MARQUE_MESSAGE,request.getAttribute(Config.MARQUE_MESSAGE));
			
			//Date Object is added into the session after getting it from the request 
			session.setAttribute(Config.SYSDATE_LOGIN,request.getAttribute(Config.SYSDATE_LOGIN));
			session.setAttribute(Config.SYSDATEOBJECT_LOGIN,request.getAttribute(Config.SYSDATEOBJECT_LOGIN));
			
		
			
			String ipAddrs = request.getRemoteAddr();
			String userName = (String)request.getAttribute("userName");
			loginUserList.add(userId+"#"+userName+"#"+ipAddrs+"#"+session.getId());
			System.out.println("session.getId()="+session.getId());
			context.removeAttribute("LOGIN_USER");
			context.setAttribute("LOGIN_USER",loginUserList);
			status = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = false;
			System.out.println("Error : Create User Session "+e);
		}
		return status;
	}
	
	
	//Destroying the User Session and Removing the UserId + IP Address from the User List set in the Context
	
	public boolean destroyUserSession(HttpServletRequest request,ServletContext context)
	{
	//	ServletContext context  = this.getServletContext();
		ArrayList loginUserList = (ArrayList) context.getAttribute("LOGIN_USER");
		String[] temp;
		String val="";		
		
		try{
		
		HttpSession session = request.getSession(false);		
		
		if(session==null)
			  request.getSession(true);
		
		
		if(loginUserList != null)
		{
			for(int i=0;i<loginUserList.size();i++)
			{
				val = (String)loginUserList.get(i);
				temp =val.split("#");
				if(temp!=null && temp[0].equals(session.getAttribute("USER_ID")) && temp[2].equals(session.getAttribute("IP_ADDR")))
					loginUserList.remove(i);
			}
		}
		context.removeAttribute("LOGIN_USER");
		context.setAttribute("LOGIN_USER",loginUserList);
		
		//System.out.println("calling session invalidate ");
 if(session!=null)		
		session.invalidate();
		//System.out.println("called");
		}
	catch(Exception e)
	    {
		e.printStackTrace();
		}
		
		
		return true;
	}
	
	//Getting the Date Format in the Form of Calendar object Which is later set into
	//the Session,which is used to make Dynamic Time increasing every minute in the header part(menuHide.jsp)
	
	public static List getDateAndFormatList(login loginObj)
	{
		String date = "";
		List dateAndFormatList = new ArrayList();
		Calendar cl = Calendar.getInstance();
		
		cl.set(Calendar.DATE, Integer.parseInt(loginObj.getDateField()));
		cl.set(Calendar.MONTH, Integer.parseInt(loginObj.getMonth()) - 1);
		cl.set(Calendar.YEAR, Integer.parseInt(loginObj.getYear()));
		cl.set(Calendar.HOUR_OF_DAY, Integer.parseInt(loginObj.getHour()));
		cl.set(Calendar.MINUTE, Integer.parseInt(loginObj.getMinute()));
		cl.set(Calendar.SECOND, Integer.parseInt(loginObj.getSeconds()));
		Date dateObject = cl.getTime();
		SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
		sf.applyPattern("dd/MM/yyyy HH:mm");
		//TimeZone tz = cl.getTimeZone();
		date = sf.format(dateObject);
		//System.out.println("date in loginAction Functions" + date);
		

		dateAndFormatList.add(date);
		dateAndFormatList.add(dateObject);
		
		
		return dateAndFormatList;
	
	}
}
