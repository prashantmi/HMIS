package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.DeceasedStorageFB;
import mortuary.transaction.controller.utl.DeceasedStorageUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeceasedStorageACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		DeceasedStorageFB fb=(DeceasedStorageFB)form;
		WebUTIL.refreshTransState(request);
		DeceasedStorageUTL.getDeceasedListForStorage(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DECEASEDDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeceasedStorageFB fb=(DeceasedStorageFB)form;
		DeceasedStorageUTL.getDeceasedDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	
	
	/**Getting Rack on the Basis of Chamber
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward GETRACK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeceasedStorageFB fb=(DeceasedStorageFB)form;
		DeceasedStorageUTL.getRackBasedOnChamber(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCHAMBER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeceasedStorageFB fb=(DeceasedStorageFB)form;
		DeceasedStorageUTL.getChamberBasedOnMortuaryArea(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETMORTUARYAREA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeceasedStorageFB fb=(DeceasedStorageFB)form;
		DeceasedStorageUTL.getAreaBasedOnMortuary(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeceasedStorageFB fb=(DeceasedStorageFB)form;
		DeceasedStorageUTL.saveDeceasedStorageDetail(fb,request);
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
