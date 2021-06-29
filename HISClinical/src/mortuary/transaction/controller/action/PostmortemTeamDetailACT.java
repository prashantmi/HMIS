package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.PostmortemTeamDetailFB;
import mortuary.transaction.controller.utl.PostmortemTeamDetailUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PostmortemTeamDetailACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		PostmortemTeamDetailFB fb=(PostmortemTeamDetailFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		PostmortemTeamDetailUTL.getEssentialForTeamDetail(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PostmortemTeamDetailFB fb=(PostmortemTeamDetailFB)form;
		PostmortemTeamDetailUTL.addRow(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PostmortemTeamDetailFB fb=(PostmortemTeamDetailFB)form;
		PostmortemTeamDetailUTL.deleteRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		PostmortemTeamDetailFB fb=(PostmortemTeamDetailFB)form;
		PostmortemTeamDetailUTL.saveTeamDetail(fb,request);
		return this.NEW(mapping, form, request, response);
	}
}
