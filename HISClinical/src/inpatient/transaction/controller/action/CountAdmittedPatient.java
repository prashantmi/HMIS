package inpatient.transaction.controller.action;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import inpatient.transaction.delegate.InPatientEssentialDelegate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CountAdmittedPatient extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		InPatientEssentialDelegate essDelegate=new InPatientEssentialDelegate();
		
		
		String refreshFlag = "1";
		int oldNo = 0;
		int newNo = 0;
		
		HttpSession session = request.getSession();
		
		String unitCode=(String)request.getParameter("UNITCODE");
		String wardCode=(String)request.getParameter("WARDCODE");
		String roomCode=(String)request.getParameter("ROOMCODE");
		
		String totalcount=essDelegate.getTotalAdmittedPatient(ControllerUTIL.getUserVO(request),unitCode,wardCode,roomCode);
		String oldCount = (String) session.getAttribute("counter");
		
		String totalTodayPatient="";
		String totalDischargePatient="";
		try
		{
			if (oldCount == null) oldCount = totalcount;
			if (oldCount != null && totalcount != null)
			{
				oldNo = Integer.parseInt(oldCount.trim());
				newNo = Integer.parseInt(totalcount.trim());
			}
			if (oldNo == newNo || oldNo > newNo)
				refreshFlag = "0";
			else 
				refreshFlag = "1";
			
			totalTodayPatient=essDelegate.getTodayAdmittedPatient(ControllerUTIL.getUserVO(request),unitCode,wardCode,roomCode);
			
			totalDischargePatient=essDelegate.getTodayDischargedPatient(ControllerUTIL.getUserVO(request),unitCode,wardCode,roomCode); 
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			
			DynamicDeskUTIL.setAttributeInSession(request,"counter", String.valueOf(oldNo));
			
			PrintWriter printWriter = response.getWriter();
			printWriter.write(totalcount + "$" + totalTodayPatient + "$" + totalDischargePatient + "$" + refreshFlag);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
