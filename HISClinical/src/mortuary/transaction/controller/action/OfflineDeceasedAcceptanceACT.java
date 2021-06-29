package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.fb.OfflineDeceasedAcceptanceFB;
import mortuary.transaction.controller.utl.OfflineDeceasedAcceptanceUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OfflineDeceasedAcceptanceACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	{
		generateToken(request);
		OfflineDeceasedAcceptanceFB fb=(OfflineDeceasedAcceptanceFB)form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		fb.setPatCrNo("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	{
		OfflineDeceasedAcceptanceFB fb=(OfflineDeceasedAcceptanceFB)form;
		OfflineDeceasedAcceptanceUTL.getessentialForAcceptance(fb,request); 
		fb.setHandoverFlag(MortuaryConfig.DEAD_BODY_HANDOVER_EXISTING);
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
		OfflineDeceasedAcceptanceFB fb=(OfflineDeceasedAcceptanceFB)form;
		OfflineDeceasedAcceptanceUTL.getRackBasedOnChamber(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OfflineDeceasedAcceptanceFB fb=(OfflineDeceasedAcceptanceFB)form;
		OfflineDeceasedAcceptanceUTL.saveOfflineStorageDetail(fb,request);
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		fb.setPatCrNo("");
		return mapping.findForward("NEW");
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
