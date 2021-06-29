package opd.transaction.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import opd.OpdConfig;
import opd.bo.delegate.OpdEssentialDelegate;

public class BackgroundQuery extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		HttpSession session = request.getSession();

		String refreshFlag = "0";
		String oldCount="";
		int oldNo = 0;
		int newNo = 0;
		
		String noOfPatAttendedDisplay = "";
		String noOfPatPending = "";
		String noOfTotalPat = "";
		try
		{
			String unitCode = (String) request.getParameter("UNITCODE");
			String roomCode = (String) request.getParameter("ROOMCODE");
			
			// Old Patient Pending Count
			oldCount = (String) session.getAttribute("counter");
			if(oldCount!=null)
			{
				try
				{
					Integer.parseInt(oldCount.trim());
				}
				catch(NumberFormatException e)
				{
					oldCount = "0";
				}
			}
			
			// Total New Patient Pending Count
			noOfPatPending = opdEssentialDelegate.getOpdPatientCount(ControllerUTIL.getUserVO(request), unitCode, roomCode);
			if (noOfPatPending==null || noOfPatPending.trim().equals(""))	noOfPatPending = "0";
			try
			{
				Integer.parseInt(noOfPatPending.trim());
			}
			catch(NumberFormatException e)
			{
				noOfPatPending = "0";
			}
			
			// Attendant Patient 
			noOfPatAttendedDisplay = opdEssentialDelegate.getAttendedOpdPatientCount(ControllerUTIL.getUserVO(request), unitCode, roomCode);
			if (noOfPatAttendedDisplay==null || noOfPatAttendedDisplay.trim().equals(""))	noOfPatAttendedDisplay = "0";
			try
			{
				Integer.parseInt(noOfPatAttendedDisplay.trim());
			}
			catch(NumberFormatException e)
			{
				noOfPatAttendedDisplay = "0";
			}
			
			noOfTotalPat = String.valueOf(Integer.parseInt(noOfPatPending) + Integer.parseInt(noOfPatAttendedDisplay));

			if (noOfTotalPat != null)
			{
				newNo = Integer.parseInt(noOfTotalPat.trim());
				if(oldCount != null)	oldNo = Integer.parseInt(oldCount.trim());
				else oldNo = newNo;

				if (oldNo < newNo)	refreshFlag = "1";
				else	refreshFlag = "0";
			}
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			DynamicDeskUTIL.setAttributeInSession(request,OpdConfig.OPD_NO_OF_ATTENDED_PAT, noOfPatAttendedDisplay);
			DynamicDeskUTIL.setAttributeInSession(request,"counter", noOfTotalPat.trim());
			
			PrintWriter printWriter = response.getWriter();
			printWriter.write(noOfTotalPat + "$" + refreshFlag + "$" + noOfPatAttendedDisplay + "$" + noOfPatPending);
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
