/**********************************************************
 Project:	   AHIMS_G5	
 File:         SNOMEDCTServReqWrapper.java
 Created:      Jul 3, 2014
 Last Changed: Oct, 2016 By Pragya Sharma
 Author:       Clinical Team

This code is copyright (c) 2016 C-DAC Noida.
 ***********************************************************/

package application.servlet;

import hisglobal.config.HISConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The Class RequestWrapper.
 */
public final class SNOMEDCTServerServlet extends HttpServlet
{
		
	public SNOMEDCTServerServlet()
	{
		super();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req, resp);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		try
		{
			// Forward to CSNO Server
			String actualURI = HISConfig.HIS_SNOMEDCT_SERVER_URL+ req.getRequestURI().replace(HISConfig.HIS_SNOMEDCT_INTERNAL_URL,"");
			System.out.println("HIS-SNOMED-SERvlet:: "+actualURI + "?" + req.getQueryString());
			URL url = new URL(actualURI + "?" + req.getQueryString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream is=null;
			int resCode=connection.getResponseCode();
			System.out.println("resCode=="+resCode);
			if(resCode == 200){
				is = connection.getInputStream();
			}
			else{
				System.out.println("ELSE");
				throw new Exception("No Repsonse from SNOMED-CT Server");
			}
			
			/*InputStream input = new URL(actualURI + "?" + req.getQueryString()).openStream();
			OutputStream output = resp.getOutputStream();
			
			RequestDispatcher objReqDispatch = null;
			objReqDispatch = objRequest.getRequestDispatcher(actualURI);
			objReqDispatch.forward(request, response);
			//objResponse.sendRedirect(actualURI);*/

			// Writing to Response
			OutputStream writer = resp.getOutputStream();
			//resp.setContentType("json/html");
			int c;
			while((c=is.read())!=-1 ) writer.write(c);
			writer.flush();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			//resp.setContentType("json/html");
			PrintWriter writer = resp.getWriter();
			writer.println("[]");
		}
		finally
		{
		}
	}
}