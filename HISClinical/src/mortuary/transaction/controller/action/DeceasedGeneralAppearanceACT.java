package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.DeceasedGeneralAppearanceFB;
import mortuary.transaction.controller.utl.DeceasedGeneralAppearanceUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeceasedGeneralAppearanceACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		DeceasedGeneralAppearanceFB fb=(DeceasedGeneralAppearanceFB)form;
		WebUTIL.refreshTransState(request);
		fb.setDeceasedNo("");
		Status objStatus = new Status();
		objStatus.add(Status.DONE);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}
	public ActionForward DECEASEDDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeceasedGeneralAppearanceFB fb=(DeceasedGeneralAppearanceFB)form;
		DeceasedGeneralAppearanceUTL.getDeceasedGeneralAppearance(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeceasedGeneralAppearanceFB fb=(DeceasedGeneralAppearanceFB)form;
		DeceasedGeneralAppearanceUTL.saveDeceasedGeneralAppearance(fb,request);
		return this.NEW(mapping, form, request, response) ;
	}
	
	public ActionForward SEARCHPOPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("SEARCH");
	}
	
	public ActionForward SEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeceasedGeneralAppearanceFB fb=(DeceasedGeneralAppearanceFB)form;
		DeceasedGeneralAppearanceUTL.searchDeceasedNo(fb,request);
		return mapping.findForward("SEARCH");
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
