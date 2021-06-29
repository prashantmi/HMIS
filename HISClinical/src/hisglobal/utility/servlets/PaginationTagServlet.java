package hisglobal.utility.servlets;

/**
 * @author : C-DAC, Noida
 * Project : AHIMS
 * Developed By : Pragya Sharma
 */

import hisglobal.tools.tag.PaginationTag;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaginationTagServlet extends HttpServlet
{
	public PaginationTagServlet()
	{
		super();
	}
	
	public void doPost(HttpServletRequest request_p, HttpServletResponse response_p) throws javax.servlet.ServletException, java.io.IOException
	{
		doGet(request_p, response_p);
	}

	public void doGet(HttpServletRequest request_p, HttpServletResponse response_p) throws javax.servlet.ServletException, java.io.IOException
	{
		try
		{
			String pageNo = (String) request_p.getParameter("pageNo");
			
			List<String> lstData = PaginationTag.getPageOverview(request_p, Integer.parseInt(pageNo));
			
			PrintWriter printWriter = response_p.getWriter();
			for(String str :  lstData)
			{
				printWriter.write(str);
				printWriter.write("#");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
