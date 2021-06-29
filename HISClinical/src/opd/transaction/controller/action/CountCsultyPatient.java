package opd.transaction.controller.action;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.bo.delegate.OpdEssentialDelegate;

public class CountCsultyPatient extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		String refreshFlag = "1";
		int oldNo = 0;
		int newNo = 0;

		HttpSession session = request.getSession();
		String unitCode = (String) request.getParameter("UNITCODE");
		String roomCode = (String) request.getParameter("ROOMCODE");
		
		String count = opdEssentialDelegate.getCsultyTotalPatCount(ControllerUTIL.getUserVO(request), unitCode, roomCode);
		String oldCount = (String) session.getAttribute("counter");
		
		String totalTodayPatient="";
		try
		{
			if (oldCount == null) oldCount = count;
			if (oldCount != null && count != null)
			{
				oldNo = Integer.parseInt(oldCount.trim());
				newNo = Integer.parseInt(count.trim());
			}
			if (oldNo == newNo || oldNo > newNo) refreshFlag = "0";
			else refreshFlag = "1";

			totalTodayPatient=opdEssentialDelegate.getCsultyTodayAdmPatCount(ControllerUTIL.getUserVO(request),unitCode,roomCode);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		try
		{
			DynamicDeskUTIL.setAttributeInSession(request,"counter", String.valueOf(oldNo));
			
			PrintWriter printWriter = response.getWriter();
			printWriter.write(count + "$" + refreshFlag + "$" + totalTodayPatient);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}	
}
