package mrd.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.reports.controller.fb.IndoorPatientsBetweenTwoDatesFB;
import mrd.reports.controller.util.IndoorPatientsBetweenTwoDatesUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IndoorPatientsBetweenTwoDatesACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IndoorPatientsBetweenTwoDatesFB fb = (IndoorPatientsBetweenTwoDatesFB) form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		IndoorPatientsBetweenTwoDatesUTL.getEssential(fb, request);

		return mapping.findForward("NEW");
	}

	public ActionForward TEXT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws HisReportDataNotFoundException
	{
		IndoorPatientsBetweenTwoDatesFB fb = (IndoorPatientsBetweenTwoDatesFB) form;

		byte[] byteArray = IndoorPatientsBetweenTwoDatesUTL.generateTextReport(fb, request, getServlet().getServletContext(), response);
		try
		{
			OutputStream os = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);

			if (byteArray != null)
			{
				response.setHeader("Pragma", "no-cache");
				bos.write(byteArray, 0, byteArray.length);
				response.getOutputStream().flush();
				bos.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
