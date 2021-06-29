package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.OpinionApprovalFB;
import mortuary.transaction.controller.utl.OpinionApprovalUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpinionApprovalACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		OpinionApprovalFB fb=(OpinionApprovalFB)form;
		WebUTIL.refreshTransState(request);
		OpinionApprovalUTL.getFinalOpinionToBeApproved(fb,request); 
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward DECEASEDDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpinionApprovalFB fb=(OpinionApprovalFB)form;
		OpinionApprovalUTL.getFinalOpinionToBeApprovedDetails(fb,request); 
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward ADDROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpinionApprovalFB fb=(OpinionApprovalFB)form;
		OpinionApprovalUTL.addRow(fb,request);
		fb.resetOpinion(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpinionApprovalFB fb=(OpinionApprovalFB)form;
		OpinionApprovalUTL.deleteRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpinionApprovalFB fb=(OpinionApprovalFB)form;
		OpinionApprovalUTL.saveApprovedFinalOpinion(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward SAMPLERESULT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpinionApprovalFB fb=(OpinionApprovalFB)form;
		OpinionApprovalUTL.getSampleResult(fb,request);
		return mapping.findForward("SAMPLE");
	}
	
	public ActionForward FINALCANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
}
