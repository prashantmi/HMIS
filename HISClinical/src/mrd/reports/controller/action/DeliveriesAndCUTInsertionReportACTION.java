package mrd.reports.controller.action;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.reports.controller.fb.DeliveriesAndCUTInsertionReportFB;
import mrd.reports.controller.util.DeliveriesAndCUTInsertionReportUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class DeliveriesAndCUTInsertionReportACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeliveriesAndCUTInsertionReportFB fb=(DeliveriesAndCUTInsertionReportFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		DeliveriesAndCUTInsertionReportUTL.getEssential(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward PRINTREPORT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeliveriesAndCUTInsertionReportFB objFB = (DeliveriesAndCUTInsertionReportFB) form;
		DeliveriesAndCUTInsertionReportUTL.printReport(objFB, request);
		return mapping.findForward("REPORT");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}

