package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.PostmortemHandoverDetailFB;
import mortuary.transaction.controller.utl.PostmortemHandoverDetailUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PostmortemHandoverDetailACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		PostmortemHandoverDetailFB fb=(PostmortemHandoverDetailFB)form;
		WebUTIL.refreshTransState(request);
		PostmortemHandoverDetailUTL.getPostmortemReadyToHandover(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DECEASEDDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemHandoverDetailFB fb=(PostmortemHandoverDetailFB)form;
		PostmortemHandoverDetailUTL.getEssentialForPostmortemHandover(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		PostmortemHandoverDetailFB fb=(PostmortemHandoverDetailFB)form;
		PostmortemHandoverDetailUTL.savePostmortemHandoverDetail(fb,request);
		return this.NEW(mapping, form, request, response);
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
