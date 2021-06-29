package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.DischargeRequestFB;
import inpatient.transaction.controller.utl.DischargeRequestUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DischargeRequestACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{

		generateToken(request);
		DischargeRequestFB fb=(DischargeRequestFB)form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.reset(mapping, request);
		DischargeRequestUTL.getPatientStatus(fb,request);
		fb.setNextVisitDateFlag(InpatientConfig.NEXT_VISIT_SELECTION_DAYS);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DischargeRequestFB fb=(DischargeRequestFB)form;
		DischargeRequestUTL.saveDischargeRequest(fb,request);
		return this.NEW(mapping,form,request,response);		
	}
}
