package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.BloodTransfusionReactionFB;
import inpatient.transaction.controller.utl.BloodTransfusionReactionUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class BloodTransfusionReactionACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		BloodTransfusionReactionFB fb = (BloodTransfusionReactionFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		BloodTransfusionReactionUTL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward BAGDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//BloodTransfusionReactionFB fb = (BloodTransfusionReactionFB) form;
		return mapping.findForward("BAGDETAIL");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		BloodTransfusionReactionFB fb=(BloodTransfusionReactionFB)form;
		BloodTransfusionReactionUTL.saveBloodTransReactionDtl(fb, request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward ADDMACRO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BloodTransfusionReactionFB fb = (BloodTransfusionReactionFB) form;
		BloodTransfusionReactionUTL.getSetMacros(fb, request);		
		return mapping.findForward("MACROPOPUP");
	}
	public ActionForward POPUPREACTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BloodTransfusionReactionFB fb = (BloodTransfusionReactionFB) form;
		//BloodTransfusionReactionUTL.getSetMacros(fb, request);		
		return mapping.findForward("POPUPREACTION");
	}
}

