package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.CaseSheetReceivedFB;
import mrd.transaction.controller.utl.CaseSheetReceivedUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CaseSheetReceivedACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	/** Getting the List Of Case Sheet, Rack & Shelf List
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		CaseSheetReceivedFB fb=(CaseSheetReceivedFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		CaseSheetReceivedUTL.getEssentialForCaseSheetAcceptence(fb, request);
		return mapping.findForward("NEW");
	}
	
	/** Getting the List Of Shelf On Basis of Rack
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward SHELF(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetReceivedFB fb=(CaseSheetReceivedFB)form;
		CaseSheetReceivedUTL.getShelf(fb, request,response);
		return null;
	}
	
	public ActionForward GETCASESHEETDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetReceivedFB fb=(CaseSheetReceivedFB)form;
		CaseSheetReceivedUTL.getAllEnclosureDetails(fb, request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CaseSheetReceivedFB fb=(CaseSheetReceivedFB)form;
		CaseSheetReceivedUTL.saveCaseSheetAcceptence(fb, request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
	/*Action mainly called for pagination*/
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CaseSheetReceivedFB fb = (CaseSheetReceivedFB) form;
		Status objStatus= new Status();
		objStatus.add(Status.LIST);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}
}
