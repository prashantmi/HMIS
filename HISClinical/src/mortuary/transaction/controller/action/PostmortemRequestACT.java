package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.PostmortemRequestFB;
import mortuary.transaction.controller.utl.PostmortemRequestUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PostmortemRequestACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	{
		generateToken(request);
		PostmortemRequestFB fb=(PostmortemRequestFB)form;
		WebUTIL.refreshTransState(request);
		PostmortemRequestUTL.getDeceasedListForPostmortemRequest(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DECEASEDDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestFB fb=(PostmortemRequestFB)form;
		PostmortemRequestUTL.getEssentialForPostmortemRequest(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDOPINION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestFB fb=(PostmortemRequestFB)form;
		PostmortemRequestUTL.addOpinionRow(fb,request); 
		fb.resetOpinion(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEOPINION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestFB fb=(PostmortemRequestFB)form;
		PostmortemRequestUTL.deleteOpinionRow(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDITEM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestFB fb=(PostmortemRequestFB)form;
		PostmortemRequestUTL.addItemRow(fb,request); 
		fb.resetItem(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEITEM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestFB fb=(PostmortemRequestFB)form;
		PostmortemRequestUTL.deleteItemRow(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDRELATIVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestFB fb=(PostmortemRequestFB)form;
		PostmortemRequestUTL.addRelativeRow(fb,request); 
		fb.resetRelative(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETERELATIVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestFB fb=(PostmortemRequestFB)form;
		PostmortemRequestUTL.deleteRelativeRow(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		PostmortemRequestFB fb=(PostmortemRequestFB)form;
		if(PostmortemRequestUTL.savePostmortemRequest(fb,request))
		{
			WebUTIL.refreshTransState(request);
			PostmortemRequestUTL.getDeceasedListForPostmortemRequest(fb,request);
		}
		return mapping.findForward("NEW");
	}
	
	public ActionForward VIEWCONSENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("VIEWCONSENT");
	}
	
	
	/** Back To The Previous Page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	/** Back To the Init Page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward FINALCANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
