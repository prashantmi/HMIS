package startup;

import java.io.*;
import java.util.*;
import java.lang.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponseWrapper;
import startup.*;

public final class LoginFilter implements Filter
{
	private FilterConfig filterConfig = null;	
	
	public void init(FilterConfig filterConfig)throws ServletException
	{
		System.out.println("inside init of login filter..........................");
		this.filterConfig = filterConfig;
	}
	
	public void destroy()
	{
		System.out.println("inside destroy of login filter......................");
		this.filterConfig = null;
	}
	
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain filterChain)
		throws IOException, ServletException
	{
		System.out.println("inside do filter of login filter..................");
		String sessionVal = "1";
		ServletContext context = filterConfig.getServletContext();
		HttpServletRequest req = (HttpServletRequest)request;
		System.out.println("request is from page "+req.getRequestURI());
		if(!req.getRequestURI().equals("/AHIMSWeb/startup/loginAction"))
		{
			System.out.println("inside if preetiiiiiiiiiiiii");
			String seatID = (String)req.getSession().getAttribute("SEATID");
			System.out.println("seatID is "+seatID);
			System.out.println("session id is "+(String)req.getSession().getId());
					
			if(seatID==null || seatID.equals(""))
				sessionVal = "0";
			else
			{
				sessionVal = "0";
				String[] temp;
				String val="";			
				ArrayList loginUserList = (ArrayList) context.getAttribute("LOGIN_USER");
				
				for(int i=0;i<loginUserList.size();i++)
				{
					System.out.println("loginUserList.get("+i+") "+loginUserList.get(i));
					val = (String)loginUserList.get(i);
					temp =val.split("#");
					
					if(temp[0].equals((String)req.getSession().getAttribute("USER_ID")))
						sessionVal="1";
				}
			}
			System.out.println("out of filter.....................");
		}
		
		if(sessionVal.equals("0"))
		{
			HttpServletResponse rep = (HttpServletResponse)response;
			
			rep.sendRedirect("index.jsp");
			
			//RequestDispatcher rd = req.getRequestDispatcher("/startup/index.jsp");
			//rd.forward(request,response);
			//filterChain.doFilter(request,response);
		}
		else	
			filterChain.doFilter(request,response);
	}
}