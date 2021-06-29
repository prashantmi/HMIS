/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISApplicationRequestFilter.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package application.filters;

import hissso.config.HISSSOConfig;
import hissso.config.HISSSOServerConfig;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class HISApplicationRequestFilter implements Filter
{
	// private FilterConfig objFilterConfig;

	public void init(FilterConfig objFilterConfig)
	{
		// this.objFilterConfig = objFilterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest objRequest = (HttpServletRequest) request;
		try
		{
			String strURI = objRequest.getRequestURI();
			//System.out.println("Inside HISApplicationRequestFilter >>>>> " +strURI);
			if(strURI.endsWith(".js") || strURI.endsWith(".css") || strURI.contains(HISSSOConfig.SSO_TGT_SERVICE_URL) || strURI.contains("/snomedct/csnoserv"))
			{
				// Forward as-it-is
				//chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
				chain.doFilter(request, response);
			}
			else
			{
				boolean authenticate = HISSSOServerConfig.onServerCheckAuthenticateRequest(objRequest);
				boolean allowed = HISSSOServerConfig.onServerCheckAllowedRequest(objRequest);
				if (allowed)
				{
					if(authenticate)
					{
						// Forward to User Desk
						//if(strURI.contains("/AHIMSG5/hislogin/captchaLgnFtr.action"))
						//changed by garima for extension change
						if(strURI.contains("/AHIMSG5/hislogin/captchaLgnFtr"))
								{
							//chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
							chain.doFilter(request, response);
								}
						else
						{
						RequestDispatcher objReqDispatch = null;
						objReqDispatch = objRequest.getRequestDispatcher(HISSSOServerConfig.SSO_LOGGEDIN_USERDESK_URL);
						objReqDispatch.forward(request, response);
						}
					}
					else
					{
						// Forward as-it-is
						//chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
						chain.doFilter(request, response);
					}
				}
				else if (authenticate)
				{
					// Forward as-it-is
					//chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
					chain.doFilter(request, response);
				}
				else
				{
					//changed by garima for extension change
					//if(strURI.equals("/AHIMSG5/hissso/logoutLogin.action"))
					if(strURI.equals("/AHIMSG5/hissso/logoutLogin"))
						chain.doFilter(request, response);
						//chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
					else
					{
						// Forward to Error Page
						RequestDispatcher objReqDispatch = null;
						objReqDispatch = objRequest.getRequestDispatcher(HISSSOConfig.SSO_AUTHENTICATION_ERROR_PAGE_URL);
						objReqDispatch.forward(request, response);
					}
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.write("<html><head><title>Exception/Error Detail(s)</title></head><body>");
			out.write("<h3><center><font color='red'>Error Detail(s)</font></center></h3>");
			out.write("<h1><center><font color='red'>Error Name:This Activity/Values Not Allowed.<font></center></h1>");
			out.write("</ul>");
			out.write("<br><br>");
			out.write("</body></html>");
		}
	}

	public void destroy()
	{
		// Nothing to do right now for HIS
	}
}
