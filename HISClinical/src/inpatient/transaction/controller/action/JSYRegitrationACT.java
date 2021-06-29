package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.JSYRegitrationFB;
import inpatient.transaction.controller.utl.JSYRegitrationUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class JSYRegitrationACT extends CSRFGardTokenAction{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		JSYRegitrationFB fb = (JSYRegitrationFB) form;
		fb.reset(mapping, request);
		fb.setPatCrNo("");
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
	    return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		JSYRegitrationFB fb = (JSYRegitrationFB) form;
		fb.reset(mapping, request);
		JSYRegitrationUTL.getPatDetailForJSYRegistration(fb,request);
		 return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		JSYRegitrationFB fb = (JSYRegitrationFB) form;
		JSYRegitrationUTL.saveJSYDetail(fb, request);
		 return mapping.findForward("NEW");
	}
	
}	