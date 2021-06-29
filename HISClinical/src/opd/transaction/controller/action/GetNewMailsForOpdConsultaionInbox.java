package opd.transaction.controller.action;

import hisglobal.presentation.ControllerUTIL;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.bo.delegate.OpdPatientDelegate;

/**
 * Servlet implementation class for Servlet: GetNewMailsForOpdConsultaionInbox
 */
public class GetNewMailsForOpdConsultaionInbox extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	static final long serialVersionUID = 1L;

	public GetNewMailsForOpdConsultaionInbox()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String seatId = ControllerUTIL.getUserVO(request).getSeatId();
			OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
			
			String count = opdPatientDelegate.getNoOfNewMails(seatId, OpdConfig.OPD_CONSULTANT_ACK_STATUS_NEW);
			
			PrintWriter printWriter = response.getWriter();
			printWriter.write(count);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
