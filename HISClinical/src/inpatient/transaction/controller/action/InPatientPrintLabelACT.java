package inpatient.transaction.controller.action;

import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.InPatientPrintLabelFB;
import inpatient.transaction.controller.utl.InPatientPrintLabelUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InPatientPrintLabelACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InPatientPrintLabelFB fb=(InPatientPrintLabelFB)form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.reset(mapping, request);
		InPatientPrintLabelUTL.setSelectedPatientDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PRINTLABEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InPatientPrintLabelFB fb=(InPatientPrintLabelFB)form;
		InPatientPrintLabelUTL.prepareLabel(fb,request);
		//fb.reset(mapping, request);
		fb.setNoOfLabel("");
		return mapping.findForward("NEW");	
	}
}
