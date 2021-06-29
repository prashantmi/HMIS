package mrd.reports.controller.action;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.reports.controller.fb.PediatricsImmunizationsReportFB;
import mrd.reports.controller.util.PediatricsImmunizationsReportUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class PediatricsImmunizationsReportACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PediatricsImmunizationsReportFB fb=(PediatricsImmunizationsReportFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		PediatricsImmunizationsReportUTL.getEssential(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward PRINTREPORT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PediatricsImmunizationsReportFB objFB = (PediatricsImmunizationsReportFB) form;
		PediatricsImmunizationsReportUTL.printReport(objFB, request);
		return mapping.findForward("REPORT");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}

