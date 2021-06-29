package startup;

import javax.servlet.*;
import javax.servlet.http.*;
import hisglobal.*;
import java.io.*;
import java.util.*;
//import menu.*;
import startup.*;

public class LoginActionBackUP extends HttpServlet implements HttpSessionListener
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		this.doPost(request,response);
	}
   
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			RequestDispatcher rd = null;
			String hmode =	request.getParameter("hmode");
			
			if(hmode == null)
				hmode = "INIT";
			
			if(hmode.equals("INIT"))
			{
				rd = request.getRequestDispatcher("../startup/index.jsp");
				request.setAttribute("message"," ");				
				rd.forward(request,response);
			}
			else if(hmode.equals("LOGIN"))
			{
				login loginObj = new login();
				
				String	uid	=request.getParameter("uid");
				String	pwd	=request.getParameter("pwd");
							
				loginObj.setUid(uid);
				loginObj.setPwd(pwd);
				
				loginObj.validateUid(request.getRemoteAddr());
					
				if(!loginObj.getSeatId().equals("NONE"))	//login by valid user
				{	
					String userId = loginObj.getUserId();
					
					request.setAttribute("SEATID",loginObj.getSeatId());
					request.setAttribute("EMPID",loginObj.getEmpId());
					request.setAttribute("userId",userId);
					request.setAttribute("userName",loginObj.getUserName());
					
					boolean status = false;
					status = createUserSession(request,userId);
					if(status == true)
						rd = request.getRequestDispatcher("../startup/mainPage.jsp");
					else
						rd = request.getRequestDispatcher("../startup/error.jsp");
				}
				else	//login by Invalid user
				{
					rd = request.getRequestDispatcher("../startup/index.jsp");
					request.setAttribute("message","Invalid User-Name or Password");
				}
				rd.forward(request,response);
			}
			else if(hmode.equals("LOG_OUT"))
			{
				login loginObj = new login();
				
				loginObj.logoutUser((String)request.getSession(false).getAttribute("SEATID"),request.getRemoteAddr());
				destroyUserSession(request);
				request.setAttribute("message"," ");
				response.sendRedirect("loginAction");
			}
		}
		catch(Exception e)
		{
		    System.out.println("Error in Controller page : "+e);
		}			
	}//end of method : doPost
	
	private boolean createUserSession(HttpServletRequest request,String userId)
	{
		ServletContext context 		= 	this.getServletContext();
		ArrayList loginUserList 	= 	new ArrayList();
		loginUserList				=	(ArrayList) context.getAttribute("LOGIN_USER");
		boolean status 				= 	false;
		String[] temp;
		String val="";
		if(loginUserList == null)	//no user login
		{
			loginUserList = new ArrayList();
			status = userCanLogin(context,request,userId,loginUserList);			//First User Login
		}
		else						//some user alredy login
		{
			int idx = -1;
			
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
			if(idx == -1)	//New user come : Still Not login at mail server
				status = userCanLogin(context,request,userId,loginUserList);
			else
			{
				val = (String)loginUserList.get(idx);
				temp = val.split("#");
				System.out.println("Login Failed : User id "+loginUserList.get(idx)+" already login");
				request.setAttribute("message","User ID "+temp[1]+" is already login on IP Address "+temp[2]);
				status = false;
			}
		}
		return status;
	}
	
	private boolean userCanLogin(ServletContext context,HttpServletRequest request,String userId,ArrayList loginUserList)
	{
		boolean status = false;
		try
		{
			menuConfigure menuObj = new menuConfigure();
			HttpSession session = request.getSession();
		
			session.setAttribute("USER_ID",userId);
			session.setAttribute("SEATID",request.getAttribute("SEATID"));
			session.setAttribute("EMPID",request.getAttribute("EMPID"));
			session.setAttribute("userId",userId);
			session.setAttribute("userName",request.getAttribute("userName"));
			
			session.setMaxInactiveInterval(Integer.parseInt(menuObj.getSessionExpired()));//session will destroy after 15 Seconds
			String ipAddrs = request.getRemoteAddr();
			String userName = (String)request.getAttribute("userName");
			loginUserList.add(userId+"#"+userName+"#"+ipAddrs);
			context.setAttribute("LOGIN_USER",loginUserList);
			status = true;
		}
		catch(Exception e)
		{
			status = false;
			System.out.println("Error : Create User Session "+e);
		}
		return status;
	}
	
	private boolean destroyUserSession(HttpServletRequest request)
	{
		ServletContext context  = this.getServletContext();
		ArrayList loginUserList = (ArrayList) context.getAttribute("LOGIN_USER");
		String[] temp;
		String val="";		
		HttpSession session = request.getSession();
		
		for(int i=0;i<loginUserList.size();i++)
		{
			val = (String)loginUserList.get(i);
			temp =val.split("#");
			if(temp[0].equals(session.getAttribute("USER_ID")))
				loginUserList.remove(i);
		}		
		session.invalidate();		
		return true;
	}
	
	public void sessionCreated(HttpSessionEvent se)
	{
		System.out.println("create new session");
	}
	
	public void sessionDestroyed(HttpSessionEvent se) //(HttpSessionBindingEvent event)
	{
		System.out.println("destroyed");
		String[] temp;
		String val="";
		HttpSession session = se.getSession();
		
		ServletContext context = session.getServletContext();
		
		//RequestDispatcher rd = context.getRequestDispatcher("login.jsp");
		
		
		ArrayList loginUserList = (ArrayList) context.getAttribute("LOGIN_USER");
		
		for(int i=0;i<loginUserList.size();i++)
		{
			val = (String)loginUserList.get(i);
			temp =val.split("#");
			
			if(temp[0].equals(session.getAttribute("USER_ID")))
				loginUserList.remove(i);
		}
	}
}//end of class
