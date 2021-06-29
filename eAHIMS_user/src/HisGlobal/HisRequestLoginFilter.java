package HisGlobal;

//import hisglobal.presentation.HisHttpServletRequest;
//import hisglobal.presentation.WebUTIL;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import startup.login;

public class HisRequestLoginFilter implements Filter {

	private FilterConfig fc;

	public void init(FilterConfig fc)
	{
		this.fc = fc;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		//System.out.println("Entering AuthFilter");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		System.out.println("INside Do Filter");
		ServletContext context  = fc.getServletContext();
		HisRoleAuthorization authorization=new HisRoleAuthorization();
		HttpSession session = httpReq.getSession(false);
		String uri=httpReq.getRequestURI();
		boolean forwardAllowed=false;
		boolean refreshFlag=false;
		boolean reloadFlag=false;
		boolean accessForbidden=false;
		RequestDispatcher rd=null;
		

		String hmode=(String)request.getParameter("hmode");	
		String requestUser=(String)request.getParameter("user");	
		System.out.println("URI is........."+uri);
		
		//if session is available i.e. not null
		if(session!=null)
		{
		
			//System.out.println("session.getId() in filter="+session.getId());
			String hcode=(String)session.getAttribute("HOSPITAL_CODE");
			String role=(String)session.getAttribute("USER_ROLE");
			String sessionUser=(String)session.getAttribute("USER_ID");
			
			
				
		
		//in case the loginAction Servlet is called
			
			//if(uri.equals("/eAHIMS_user/startup/login.jsp")|| uri.equals("/eAHIMS_user/startup/admin") )
			if(uri.equals("/eAHIMS_user/startup/admin") )
			{
				forwardAllowed=true;
				
				//to reload the page if user reloads the page or press enter in address bar
				if(hmode==null && hcode==null)
					hmode="";
				if(hmode==null && hcode!=null)
					reloadFlag=true;
				else if(hcode!=null && !hmode.equals("LOGOUT")&& requestUser!=null && sessionUser.equalsIgnoreCase(requestUser))
				{
					reloadFlag=true;
				}
				/*if(hcode!=null && hmode.equals("ENTER_HINT_QUESTION"))
				{
					reloadFlag=false;
				}
				if(hcode!=null && hmode.equals("CHANGE_PASSWORD"))
				{
					reloadFlag=false;
				}*/
				
			}
			else if(!uri.equals("/eAHIMS_user/startup/admin") )	//in case any other URL is called
				{
			
				if(uri.equals("/eAHIMS_user/usermgmt/masters/umgmtWarningPage.jsp") && hcode==null)
			      {
				   forwardAllowed=true;
			      }
				else if(uri.equals("/eAHIMS_user/startup/login.jsp") && hcode==null)
			      {
				   forwardAllowed=true;
			      }
				else if(hcode==null)//if hcode is not available i.e. session is expired
				  {
					forwardAllowed=false;
				  }
				else if(role!=null && authorization.isURLInRole(uri,role))
				{
					forwardAllowed=true;
				}
				else
					accessForbidden=true;
			    /*if(authorization.isURLInRole(uri,role))
			    {
			      if(uri.equals("/eAHIMS_user/usermgmt/masters/umgmtWarningPage.jsp") && hcode==null)
			      {
				   forwardAllowed=true;
			      }
			     else if(hcode==null)//if hcode is not available i.e. session is expired
				  {
					forwardAllowed=false;
				  }
				  else //if hcode is not available i.e. session is valid
				  {
					forwardAllowed=true;
				 }
			   } else
			   {
			    	accessForbidden=true;
			   }*/
				}	   
	}
	else{//if session is null
	
		//if the person clicks the below URL ,it is allowed to go further
		if(uri.equals("/eAHIMS_user/startup/admin"))
		{
			forwardAllowed=true;
		}
		else if(uri.equals("/eAHIMS_user/startup/login.jsp"))
	      {
		   forwardAllowed=true;
	      }
		else if(uri.equals("/eAHIMS_user/startup/admin") && hmode==null)
		{
			forwardAllowed=true;
		}
		else	//if the person clicks the below URL ,it is allowed to go further
		if(uri.equals("/eAHIMS_user/startup/admin") && (hmode.equals("LOGIN")  ))
		{
			forwardAllowed=true;
		}
		else
			if(uri.equals("/eAHIMS_user/startup/admin") && (hmode.equals("INIT") || hmode.equals("LOGOUT") ))
			{
				forwardAllowed=true;
			}
		else
		if(uri.equals("/eAHIMS_user/usermgmt/masters/umgmtWarningPage.jsp"))
		{
			forwardAllowed=true;
		}
		else//if the person clicks another URL ,he is not allowed
		{
			forwardAllowed=false;
		}
	}
	
	//	to reload the page if user reloads the page or press enter in address bar
	if(reloadFlag)
	{
		String userId=(String)session.getAttribute("USER_ID");
		String actualSEATID=(String)session.getAttribute("SEAT_ID");
		//String EMPID=(String)session.getAttribute("EMPID");
		String hospitalcode=(String)session.getAttribute("HOSPITAL_CODE");
		
		httpReq.setAttribute("userId", userId);
		httpReq.setAttribute("ACTUALSEATID", actualSEATID);
		httpReq.setAttribute("HOSPITAL_CODE", hospitalcode);
		
		if(hospitalcode.equalsIgnoreCase(Config.SUPER_HOSPITAL_CODE))
		rd=httpReq.getRequestDispatcher("../startup/index1.jsp?userId="+userId+"&seatId="+actualSEATID+"&hospitalCode="+hospitalcode);
		else
			rd=httpReq.getRequestDispatcher("../startup/index.jsp?userId="+userId+"&seatId="+actualSEATID+"&hospitalCode="+hospitalcode);
		rd.forward(httpReq, httpRes);
		
		
	}
	else{
		if(refreshFlag)
			{
			rd=httpReq.getRequestDispatcher("/startup/login.jsp?hmode=INIT");
			rd.forward(httpReq, httpRes);
			}
		else{	//if user is allowed ,then proceed normally
			if(accessForbidden)
			{
					rd=httpReq.getRequestDispatcher("/usermgmt/masters/umgmtAccessForbidden.jsp");
					rd.forward(httpReq, httpRes);
			}
			else if(forwardAllowed)
				chain.doFilter(request,response);
			else//else  session expired jsp
			{
				rd=httpReq.getRequestDispatcher("/usermgmt/masters/umgmtWarningPage.jsp");
				//rd=httpReq.getRequestDispatcher("/eAHIMS_user/startup/login.jsp");
				
				rd.forward(httpReq, httpRes);
			}
		}
	

		
	}	
}

	public void destroy() {

	}

}
