package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.BirthRegistrationUploadFB;
import mrd.transaction.controller.utl.BirthRegistrationUploadUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class BirthRegistrationUploadACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		BirthRegistrationUploadFB fb=(BirthRegistrationUploadFB)form;
		WebUTIL.refreshTransState(request);
		BirthRegistrationUploadUTL.getListOfBirth(fb,request);
		fb.setIsFromSearch(MrdConfig.NO);
		return mapping.findForward("NEW"); 	
	}
	
	public ActionForward GETDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BirthRegistrationUploadFB fb=(BirthRegistrationUploadFB)form;
		BirthRegistrationUploadUTL.getMotherNChildDetail(fb, request);
		BirthRegistrationUploadUTL.getEssentialForBirthRegUpload(fb,request);
		return mapping.findForward("NEW"); 	
	}
	
	public ActionForward GETHANDOVERDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BirthRegistrationUploadFB fb=(BirthRegistrationUploadFB)form;
		BirthRegistrationUploadUTL.getMotherNChildDetail(fb, request);
		BirthRegistrationUploadUTL.getEssentialForBirthRegUploadNHandover(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDUPHANDOVERDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BirthRegistrationUploadFB fb=(BirthRegistrationUploadFB)form;
		BirthRegistrationUploadUTL.getMotherNChildDetail(fb, request);
		BirthRegistrationUploadUTL.getEssentialForBirthRegUploadNHandover(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		BirthRegistrationUploadFB fb=(BirthRegistrationUploadFB)form;
		BirthRegistrationUploadUTL.saveForRegUpload(fb,request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward HANDOVERSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		BirthRegistrationUploadFB fb=(BirthRegistrationUploadFB)form;
		BirthRegistrationUploadUTL.saveHandoverForRegUpload(fb,request); 
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward HANDOVERDUPSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		BirthRegistrationUploadFB fb=(BirthRegistrationUploadFB)form;
		BirthRegistrationUploadUTL.saveHandoverForDuplicateRegUpload(fb,request); 
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward PRINTSLIP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BirthRegistrationUploadFB fb=(BirthRegistrationUploadFB)form;
		BirthRegistrationUploadUTL.printSlipForBirthRegUpload(fb,request);
		return mapping.findForward("PRINT");
	}
	
	public ActionForward SEARCHPOPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BirthRegistrationUploadFB fb=(BirthRegistrationUploadFB)form;
		fb.setSearchType(MrdConfig.SEARCH_TYPE_CHILD);
		return mapping.findForward("SEARCH");
	}
	
	public ActionForward SEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BirthRegistrationUploadFB fb=(BirthRegistrationUploadFB)form;
		BirthRegistrationUploadUTL.searchForBirthRegUpload(fb,request);
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
