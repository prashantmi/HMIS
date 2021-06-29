package startup;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.Auth;
import login.CSRFTokenUtil;
import HisGlobal.Config;
import HisGlobal.HisHttpServletRequest;
import java.security.SecureRandom;

import usermgmt.masters.umgmtSessionConfigXmlHandler;


public class loginController extends HttpServlet{
		
	public static final int SALT_BYTE_SIZE = 24;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		Auth authObj = new Auth();
		this.setValues(request,authObj);
		String hmode = request.getParameter("hmode");
		
		String nextPage = "";
		HttpSession session = null;	
		String token =null;
		
		//Getting only previously created session
		session = request.getSession(false);
		if(session!=null)
		System.out.println("Session Details are as follows:"+ session.getId());
		try
		{		
			//if(session!=null)
			//	token = CSRFTokenUtil.getToken(session);
			if ( hmode == null )
			{		
				hmode="INIT";				
				nextPage = getFileMapping("LOGIN_PAGE");	
				SecureRandom random = new SecureRandom();
			    byte[] salt = new byte[SALT_BYTE_SIZE];
			    random.nextBytes(salt);
			    
			    if(session==null)
				{
					session = request.getSession();//creating new session
					System.out.println("Created new session with Id  "+session.getId());
					//token = CSRFTokenUtil.getToken(session);
				}
			    else
			    {
			    	request.getSession(false).invalidate();
					session = request.getSession();
			    }
				session.setAttribute("SALT",salt.toString());
				//session.setAttribute("CSRF_TOKEN",token);
				//request.setAttribute("CSRF_TOKEN",token);
				//System.out.println("--Request CSRF Attribute-----"+request.getAttribute("CSRF_TOKEN")+"-------");

			}
			else if(hmode.equals("LOGIN"))
			{				
				nextPage = makeLogin(session,request,authObj);											 
			}
			else if(hmode.equals("FORGET_PASS"))
				nextPage = getFileMapping("FORGOT_PASSWORD_PAGE");									
			else if(hmode.equals("RESET_PASS"))
			{
				nextPage = getFileMapping("RESET_PASSWORD_PAGE");			
				String msg = authObj.forgetPassword();
				request.setAttribute("msg",msg);					
			}
			else if(hmode.equals("CHANGE_PASS"))
				nextPage = getFileMapping("CHANGE_PASSWORD_PAGE");									
			else if(hmode.equals("CHANGE_PASSWORD"))
			{
				nextPage = getFileMapping("CHANGE_PASSWORD_PAGE");						
				String msg = authObj.changePassword();			
				request.setAttribute("msg",msg);
			}
			else if(hmode.equals("LOGOUT") )
			{				
						
				nextPage = makeLogout(session,response);
               // HisHttpServletRequest newRequest=new HisHttpServletRequest(request);
				
				//setting the hmode as init
				//newRequest.setHisParameter("hmode","INIT");
				request.setAttribute("hmode","INIT");
				
				//RequestDispatcher rq = newRequest.getRequestDispatcher(nextPage);
				// rd.forward(request1, response);
				//response.sendRedirect("loginAction");
				
				///putting the object of newRequest into the request reference
				//request=newRequest;
				RequestDispatcher rq = request.getRequestDispatcher(nextPage);	
				rq.forward(request,response);
				//response.sendRedirect("../startup/admin");
				//response.sendRedirect(nextPage);
			}					
			if(!hmode.equals("LOGOUT"))
			{						
				RequestDispatcher rq = request.getRequestDispatcher(nextPage);	
				rq.forward(request,response);
			}				
		}
		catch(Exception e)
		{
			System.out.println("Exception in Do Post Method "+e);
			
		}		System.out.println("iNSIDE IF");
	}
	private void setValues(HttpServletRequest request,Auth authObj)
	{
		authObj.setConfirmPass(request.getParameter("confirmPass"));
		authObj.setHint(request.getParameter("hint"));
		authObj.setMessage(request.getParameter("message"));
		authObj.setNewPass(request.getParameter("newPass"));
		authObj.setOldPass(request.getParameter("oldPass"));
		authObj.setPass(request.getParameter("pass"));
		authObj.setUser(request.getParameter("user"));
		authObj.setCaptchaResponse(request.getParameter("captchaResponse"));
	}
	
	private String getFileMapping(String key)
	{
		String fileName = "";
		Map fileMapping = new HashMap();
		fileMapping.put("LOGIN_PAGE","login.jsp");
		fileMapping.put("FORGOT_PASSWORD_PAGE","forgetPass.jsp");
		fileMapping.put("RESET_PASSWORD_PAGE","forgetPass.jsp");
		fileMapping.put("INDEX_PAGE","index.jsp");
		fileMapping.put("INDEX_PAGE1","index1.jsp");
		fileMapping.put("CHANGE_PASSWORD_PAGE","changePassword.jsp");		
		fileName = fileMapping.get(key).toString();
		return fileName;	
	}
	
	private String makeLogin(HttpSession session,HttpServletRequest request,Auth authObj) throws Exception 
	{
		String isRefresh = null;					
		/*if(session==null)
		{
			session = request.getSession();//creating new session
			System.out.println("Created new session with Id  "+session.getId());
		}
		System.out.println("IS_REFRESH"+session.getAttribute("IS_REFRESH"));*/
		
		String userId = authObj.getUser();
		
		
		String nextPage = "";
		String strHospitalCode="";
		String HosCode="100";
		String isSessionValid="1";
		
		//if(isRefresh.equals("1"))
		//{			
			//seat = authObj.authorizeUser();
			//authenticatimg the Authorized user
			//strHospitalCode = authObj.authorizeUser();
		
		// Code added for Password Salted Hashing
			String salt=(String) session.getAttribute("SALT");
			String token=(String) session.getAttribute("CSRF_TOKEN");
			// session.invalidate();
			// session = null;
			 //session = request.getSession();//creating new session
			// session.setAttribute("USER_ID",userId);
			 
			 SecureRandom random = new SecureRandom();
			    byte[] saltNew = new byte[SALT_BYTE_SIZE];
			    random.nextBytes(saltNew);
			    token = CSRFTokenUtil.getToken(session);
				session.setAttribute("SALT",saltNew.toString());
				session.setAttribute("CSRF_TOKEN",token.toString());
			strHospitalCode = authObj.authorizeUser(salt);
			//end of code for password salted hashing
			
			//session.setAttribute("SESSION_ATTR_KEY",request.getAttribute("CSRF_TOKEN"));
			//System.out.println("--Sessrion CSRF Attribute-----"+request.getAttribute("CSRF_TOKEN")+"-------");
			if(!authObj.getUser().matches("[A-Za-z0-9_]+") || authObj.getUser().length()>20)
				{
				 authObj.setMessage("Invalid User Id or Password");		
					request.setAttribute("message",authObj.getMessage());
					nextPage = getFileMapping("LOGIN_PAGE");	
				}
				String parm = authObj.getCaptchaResponse();
				String c= (String)session.getAttribute("CAPTCHA_KEY") ;
			    if(parm!=null && !parm.equals(c) )
			    {
			    	authObj.setMessage("Invalid Captcha");		
					request.setAttribute("message",authObj.getMessage());
					nextPage = getFileMapping("LOGIN_PAGE");	
					if(session!=null)
					{
						System.out.println("Invalidating session if login has not been successful"+session.getId());
						isSessionValid="0";
						session.invalidate();
						
					}
			    }
			//System.out.println("Hospital Code"+strHospitalCode);
			    else if(strHospitalCode == null || strHospitalCode.equals(""))
			{				
				authObj.setMessage("Invalid User Id or Password");		
				request.setAttribute("message",authObj.getMessage());
				nextPage = getFileMapping("LOGIN_PAGE");	
				if(session!=null)
				{
					System.out.println("Invalidating session if login has not been successful"+session.getId());
					isSessionValid="0";
					session.invalidate();
					
				}
				
			}
			// New Code Added here
			else 
			{
				if(session!=null)
				{
					System.out.println("Invalidating session which was before login"+session.getId());
					if(isSessionValid.equals("1"))
					request.getSession(false).invalidate();
					
				}
				session = request.getSession();//creating new session
				System.out.println("Created new session after login with Id  "+session.getId());
				
				
				session.setAttribute("USER_ID",userId);
				session.setAttribute("IS_REFRESH", isRefresh);
				
				token = CSRFTokenUtil.getToken(session);
				session.setAttribute("CSRF_TOKEN",token.toString());
				
				System.out.println("USER_ID"+session.getAttribute("USER_ID"));
				isRefresh = (String)session.getAttribute("IS_REFRESH");
				if(isRefresh==null)
					isRefresh = "1";
				if(strHospitalCode.equals(HosCode))
				{
					session.setAttribute("HOSPITAL_CODE",strHospitalCode);
					session.setAttribute("SEAT_ID", strHospitalCode+"1");
					System.out.println("HOSPITAL_CODE"+session.getAttribute("HOSPITAL_CODE"));
					System.out.println("SEAT_ID"+session.getAttribute("SEAT_ID"));
					/*Setting the maximum time interval of Session Time Out*/
					//session.setMaxInactiveInterval(sessionTimeOutInterval);		
					//authObj.logInAction(strHospitalCodeAndSeat[1],request.getRemoteAddr());
					nextPage = getFileMapping("INDEX_PAGE1");
					session.setAttribute("IS_REFRESH","2");
					session.setAttribute("USER_ROLE","SUPER");
				}
				else{
				umgmtSessionConfigXmlHandler configObj = new umgmtSessionConfigXmlHandler();
				//int loginCount = configObj.getLoginCount(strHospitalCode);
			
				//int loginCount = configObj.getLoginCount();							
				//int sessionTimeOutInterval = configObj.getSessionTimeOut();							
				//ServletContext sc = super.getServletContext();							
				userInfoList userList = null;							
				//synchronized(sc)
				//{								
					//userList = (userInfoList)sc.getAttribute("userList");
					
					//if(userList==null)
						//userList = new userInfoList();	
								
					//Checking the condition that only one user should login from one one username								
					/*if(userList.contains(strHospitalCode[1]) && loginCount==1)
					{
						
						authObj.setMessage("Some One Already Logged in with this User Name from I.P Address :"+userList.getFirstIPAddress(strHospitalCodeAndSeat[1]));		
						request.setAttribute("message",authObj.getMessage());
						nextPage = getFileMapping("LOGIN_PAGE");
						session.invalidate();
												
					}
					else
					{*/						
						
						//userList.add(strHospitalCodeAndSeat[1],request.getRemoteAddr());									
						//sc.setAttribute("userList",userList);
						//session.setAttribute("SEATID",strHospitalCodeAndSeat[1]);									
						session.setAttribute("HOSPITAL_CODE",strHospitalCode);
						session.setAttribute("SEAT_ID", strHospitalCode+"1");
						session.setAttribute("USER_ROLE","ADMIN");
						System.out.println("HOSPITAL_CODE"+session.getAttribute("HOSPITAL_CODE"));
						System.out.println("SEAT_ID"+session.getAttribute("SEAT_ID"));
						/*Setting the maximum time interval of Session Time Out*/
						//session.setMaxInactiveInterval(sessionTimeOutInterval);		
						//authObj.logInAction(strHospitalCodeAndSeat[1],request.getRemoteAddr());
						nextPage = getFileMapping("INDEX_PAGE");
						session.setAttribute("IS_REFRESH","2");
						
//						if(strHospitalCode.equals(Config.HOSPITAL_CODE_VALUE))
//						{
//							session.setAttribute(Config.CLIENT,"2");
//							System.out.println("HOSPITAL_CODE"+session.getAttribute("HOSPITAL_CODE"));
//						}
//						else
//						{
//							session.setAttribute(Config.CLIENT,"1");
//							System.out.println("HOSPITAL_CODE"+session.getAttribute("HOSPITAL_CODE"));
//						}
//						
																
					}							
				//}//end of synchronized block
			  }	// end of else	
			//session.setAttribute("IS_REFRESH","2");
		//}
		//else		
			//nextPage = getFileMapping("INDEX_PAGE");						
		return nextPage;
	}
	
	private String makeLogout(HttpSession session,HttpServletResponse response)
	{
		String nextPage = "";
		nextPage = getFileMapping("LOGIN_PAGE");
		
		//String seatId = null;					
		//if(session!=null)
		//	seatId = (String)session.getAttribute("SEATID");										
		/*if (seatId != null)
		{ 						
			authObj.logOutAction(seatId);
			ServletContext sc = super.getServletContext();
			synchronized(sc)
			{
				userInfoList userList = null;
				userList = (userInfoList)sc.getAttribute("userList");
				userList.remove(seatId);
				sc.setAttribute("userList",userList);
			}
			session.removeAttribute("IS_REFRESH");
			session.removeAttribute("SEATID");						
		}	*/				
		if(session!=null)
			session.invalidate();	
		return nextPage;	
	}

}
