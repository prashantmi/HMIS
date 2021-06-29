package startup;

import javax.servlet.*;
import javax.servlet.http.*; 
import java.util.*;
import hisglobal.presentation.HisHttpServletRequest;
import java.io.IOException;




     
public class loginAction extends HttpServlet 
{
	public void init(ServletConfig config) throws ServletException
	{
		//System.out.println("in the init of loginaction");
		super.init(config);
	}
		
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		//System.out.println("in the doget of loginaction");
		this.doPost(request,response);
	}    
   
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	
	{

	
		
		RequestDispatcher rd = null;
		 
		loginActionFunctions objFunction=new loginActionFunctions();
		
		try	{
			
			String hmode =	request.getParameter("hmode");
			//System.out.println("hmode in loginAction---- "+hmode);
			if(hmode == null )
				hmode = "INIT";
			
		 if(hmode.equals("INIT")){
				rd = request.getRequestDispatcher("../startup/index.jsp");
				request.setAttribute("message"," ");
			//	rd.forward(request,response);				
									}
			else
		if(hmode.equals("SESSION_EXPIRED"))
				{
			rd = request.getRequestDispatcher("../startup/sessionIsExpired.jsp");
			request.setAttribute("message"," ");
		//	rd.forward(request,response);				
			
				}
			else
			if(hmode.equals("DONT_CHANGE_PASSWORD"))
					{
								
				request.setAttribute("message"," ");				
				request.setAttribute("PASSWORD", request.getParameter("PASSWORD"));
			//	rd = request.getRequestDispatcher("../startup/mainPage.jsp?seatId="+seatId+"&empId="+empId);
		
				
				rd=request.getRequestDispatcher("loginAction?hmode=LOGIN&uid="+request.getParameter("uid")+"&pwd="+request.getParameter("pwd")+"&CHANGE_PASSWORD_CHECK=NO");	

			//	rd.forward(request,response);				
				
					}
			else
				if(hmode.equals("CHANGE_PASSWORD"))
						{
					
					login loginObj = new login();
					
					String oldValue="GSTR_USR_NAME"+"^"+request.getSession().getAttribute("UserFullName")+"#"+
			         				"GNUM_QUESTION_ID"+"^"+request.getSession().getAttribute("QUESTION_ID")+"#"+	 
			         				"GSTR_HINT_ANSWER"+"^"+request.getSession().getAttribute("ANSWER")+"#"+
			         				"GNUM_MOBILE_NUMBER"+"^"+request.getSession().getAttribute("MOBILE_NO")+"#"+	 
			         				"GSTR_EMAIL_ID"+"^"+request.getSession().getAttribute("EMAIL_ID");
					
					//Since The above code is treated as a Single Line String Declaration
					//Hence we are using string otherwise we would have used  StringBuilder /StringBuffer
					//if we were making multiple strings while string concatination as the String is immutable

					
							
					
					
					int result=loginObj.updatePasswordOnly(request);
					
					
					
						
				//	String seatId=(String)request.getSession().getAttribute("SEATID");
				//	String empId=(String)request.getSession().getAttribute("EMPID");
		
				    rd=request.getRequestDispatcher("loginAction?hmode=LOGIN&uid="+request.getParameter("uid")+"&pwd="+request.getParameter("newPassword"));
					
					//rd = request.getRequestDispatcher("../startup/mainPage.jsp?seatId="+seatId+"&empId="+empId);
					
						if(result > 0)
										{
								
								
										request.setAttribute("message","Your Password has been Updated");   												
										
										
										int auditlog=loginObj.updateAuditLog(request,oldValue);
									
									    }
								
						//			 rd.forward(request,response);
					
					
					
						}
				else
			if(hmode.equals("FORGOT_PASSWORD"))
				{
					
					request.setAttribute("STATUS","INITIAL");
					  
					login loginObj = new login();
					
					Map resultantMAP = new HashMap();
					resultantMAP=loginObj.forgotPassword(request);
					
				//	System.out.println("resultantMAP size===="+resultantMAP.size());
					if(resultantMAP.size()==0)
					{
					   
					   request.setAttribute("message"," Invalid Details ");
					   
					   
					   rd = request.getRequestDispatcher("../startup/forgotPassword.jsp");
					   
					  
					}
				else{  
					
				///	System.out.println("resultantMAP value===="+resultantMAP.get(1));
					request.setAttribute("USER_ID",resultantMAP.get(1));
					request.setAttribute("HOSPITAL_CODE",resultantMAP.get(2));
					request.setAttribute("PASSWORD",resultantMAP.get(3));
					request.setAttribute("USER_NAME",resultantMAP.get(4));
					request.setAttribute("USER_SEATID",resultantMAP.get(5));
					
					request.setAttribute("STATUS","NEW_PASSWORD");
					
					
					 request.setAttribute("message","Please Enter Your New Password ");
						
						rd = request.getRequestDispatcher("../startup/forgotPassword.jsp");
						    
					    }
				
			//		 rd.forward(request,response);
				}
				else
					if(hmode.equals("NEW_PASSWORD"))
					{
						
						//System.out.println("USER_ID value in login action asAttribute ===="+request.getAttribute("USER_ID"));
						//System.out.println("USER_ID value in login action as parameter===="+request.getParameter("userId"));
						
						request.setAttribute("USER_ID",request.getParameter("userId"));
						request.setAttribute("HOSPITAL_CODE",request.getParameter("hospitalCode"));
						request.setAttribute("PASSWORD",request.getParameter("pwd"));
						request.setAttribute("USER_NAME",request.getParameter("userName"));
						request.setAttribute("USER_SEATID",request.getParameter("seatId"));
						 
						  
						login loginObj = new login();
						
						
						
						int result_NEW_PASSWORD=loginObj.newPassword(request);
						//System.out.println("result_NEW_PASSWORD ---"+result_NEW_PASSWORD);
						
		String oldValue="GSTR_PASSWORD"+"^"+request.getAttribute("PASSWORD");
		String newValue="GSTR_PASSWORD"+"^"+SecurityUtil.encrypt((String)request.getParameter("newPassword"));
	    
						
		
		//Since The above code is treated as a Single Line String Declaration
		//Hence we are using string otherwise we would have used  StringBuilder /StringBuffer
		//if we were making multiple strings while string concatination as the String is immutable				
								
		
		
						
						if(result_NEW_PASSWORD > 0)
						{
				int auditlog_forgotpassword=loginObj.updateAuditLogForgotPassword(request,oldValue,newValue);
				//System.out.println("auditlog_forgotpassword ---"+auditlog_forgotpassword);
							
						   request.setAttribute("message"," ");
						   request.setAttribute("STATUS","PASSWORD_CHANGED" );
						   
						    rd = request.getRequestDispatcher("../startup/forgotPassword.jsp");
						}else
						{
							request.setAttribute("message","Your Password is not Changed");
						    rd = request.getRequestDispatcher("../startup/forgotPassword.jsp");	
						}
					
					
				//		 rd.forward(request,response);
				}
					else	
		if(hmode.equals("LOGIN"))
		       {
			
		   ServletContext context 		= this.getServletContext();
		   rd=objFunction.loginUser(request, response,context);
		       
		       
		   }//outer if closed of login 
			else 
			if(hmode.equals("ENTER_HINT_QUESTION"))
			{
				
				login loginObj = new login();
				
			
					 int firstLogin=0;
					 int auditlog_firstLogin=0;
					 
					
									
				String oldValue="GNUM_QUESTION_ID"+"^"+request.getSession().getAttribute("QUESTION_ID")+"#"+ 
					   			 "GSTR_HINT_ANSWER"+"^"+request.getSession().getAttribute("ANSWER");
				
				
				//Since The above code is treated as a Single Line String Declaration
				//Hence we are using string otherwise we would have used  StringBuilder /StringBuffer
				//if we were making multiple strings while string concatination as the String is immutable				
									 
									 
			



				 firstLogin=loginObj.firstLogin(request);
					 
					if(firstLogin > 0)
					 auditlog_firstLogin=loginObj.updateAuditLogFirstTimeLogin(request,oldValue);
					
			//		rd = request.getRequestDispatcher("../startup/mainPage.jsp?seatId="+request.getSession().getAttribute("SEATID")+"&empId="+loginObj.getEmpId());
					    
				rd=request.getRequestDispatcher("loginAction?hmode=LOGIN&uid="+request.getParameter("userId")+"&pwd="+request.getParameter("newPasswd")); 
			
			//	 rd.forward(request,response);
			}
		 
		else
		    	   
		if(hmode.equals("LOG_OUT"))
			{
			
			ServletContext context  = this.getServletContext();
				//System.out.println("inside logout of loginaction");
				login loginObj = new login();
				HttpSession session=request.getSession(false);
				if(session!=null)
				{
				loginObj.logoutUser((String)session.getAttribute("USER_ID"),request.getRemoteAddr(),(String)session.getAttribute("HOSPITAL_CODE"));
				objFunction.destroyUserSession(request,context);
				}
			//	System.out.println("session is destroyed");
				request.setAttribute("message"," ");
				
				
				///Creating a new request
				//used bcoz of error of log out coming  in reports
				//when cancel button is pressed
				
				HisHttpServletRequest newRequest=new HisHttpServletRequest(request);
				
				//setting the hmode as init
				newRequest.setHisParameter("hmode","INIT");
				//request.setAttribute("hmode","INIT");
				
				rd = newRequest.getRequestDispatcher("loginAction");
				// rd.forward(request1, response);
				//response.sendRedirect("loginAction");
				
				///putting the object of newRequest into the request reference
				request=newRequest;
			}
		 
		// if(!hmode.equals("LOG_OUT"))
		      rd.forward(request, response);
		 
		 
		 return;
		 
		}
		//try is closed
		catch(HisInvalidUserNameException e){
			request.setAttribute("message",e.getMessage());
			rd = request.getRequestDispatcher("../startup/index.jsp");
			rd.forward(request,response);
			return;
		}
		catch(Exception e){
			
			//System.out.println("Error in Controller page >> : "+e);
			
			e.printStackTrace();
			
		}			
		finally {
			rd = null;
				}
	}//end of method : doPost
	
	
	
	
	 
  
}//end of class
