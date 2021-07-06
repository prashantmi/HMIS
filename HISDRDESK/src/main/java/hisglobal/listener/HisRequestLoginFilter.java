package hisglobal.listener;

import hisglobal.presentation.HisHttpServletRequest;
import hisglobal.presentation.WebUTIL;

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


public class HisRequestLoginFilter implements Filter {

	private FilterConfig fc;

	public void init(FilterConfig fc)
	{
		this.fc = fc;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		System.out.println("Entering AHIMS AuthFilter");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		ServletContext context  = fc.getServletContext();
		HttpSession session = httpReq.getSession(false);
		String uri=httpReq.getRequestURI();
		boolean forwardAllowed=false;
		boolean refreshFlag=false;
		boolean reloadFlag=false;
		RequestDispatcher rd=null;

		String hmode=(String)request.getParameter("hmode");		
		
		//if session is available i.e. not null
		if(session!=null)
		{
		
			//System.out.println("session.getId() in filter="+session.getId());
			String hcode=(String)session.getAttribute("HOSPITAL_CODE");
		
		//in case the loginAction Servlet is called
			
			if(uri.equals("/HISDRDESK/startup/loginAction") )
			{
				forwardAllowed=true;
				
				//to reload the page if user reloads the page or press enter in address bar
				if(hmode==null)
					hmode="";
				if(hcode!=null && !hmode.equals("LOG_OUT") )
				{
					reloadFlag=true;
				}
				if(hcode!=null && hmode.equals("ENTER_HINT_QUESTION"))
				{
					reloadFlag=false;
				}
				if(hcode!=null && hmode.equals("CHANGE_PASSWORD"))
				{
					reloadFlag=false;
				}
				
			}
			else
		if(!uri.equals("/HISClinical/startup/loginAction") )	//in case any other URL is called
			
			{
				if(hcode==null)//if hcode is not available i.e. session is expired
				{
					forwardAllowed=false;
				}
				else //if hcode is not available i.e. session is valid
				{
					forwardAllowed=true;
				}
			}
	}
	else{//if session is null
	
		//if the person clicks the below URL ,it is allowed to go further
		if(uri.equals("/HISClinical/startup/loginAction") && hmode==null)
		{
			forwardAllowed=true;
		}
		else	//if the person clicks the below URL ,it is allowed to go further
		if(uri.equals("/HISClinical/startup/loginAction") && (hmode.equals("LOGIN")  ))
		{
			forwardAllowed=true;
		}
		else
		if(uri.equals("/HISClinical/startup/loginAction") &&  hmode.equals("ENTER_HINT_QUESTION") )
		{
			refreshFlag=true;
		}
		else
		if(uri.equals("/HISClinical/startup/loginAction") && (hmode.equals("INIT") || hmode.equals("LOG_OUT") ))
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
		String actualSEATID=(String)session.getAttribute("ACTUALSEATID");
		String EMPID=(String)session.getAttribute("EMPID");
		String hospitalcode=(String)session.getAttribute("HOSPITAL_CODE");
		
		httpReq.setAttribute("userId", userId);
		httpReq.setAttribute("ACTUALSEATID", actualSEATID);
		httpReq.setAttribute("HOSPITAL_CODE", hospitalcode);
		
		
		rd=httpReq.getRequestDispatcher("../startup/mainPage.jsp?userId="+userId+"&seatId="+actualSEATID+"&empId="+EMPID+"&hospitalCode="+hospitalcode);
		rd.forward(httpReq, httpRes);
		
		
	}
	else{
		if(refreshFlag)
			{
			rd=httpReq.getRequestDispatcher("/startup/loginAction?hmode=INIT");
			rd.forward(httpReq, httpRes);
			}
		else{	//if user is allowed ,then proceed normally
			if(forwardAllowed)
				chain.doFilter(request,response);
			else//else  session expired jsp
			{
				rd=httpReq.getRequestDispatcher("/startup/sessionIsExpired.jsp");
				rd.forward(httpReq, httpRes);
			}
		}
	}
		
	
}

	public void destroy() {

	}

}
