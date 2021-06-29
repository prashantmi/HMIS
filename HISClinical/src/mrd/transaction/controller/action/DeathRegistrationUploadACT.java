package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.DeathRegistrationUploadFB;
import mrd.transaction.controller.utl.DeathRegistrationUploadUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeathRegistrationUploadACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		DeathRegistrationUploadFB fb=(DeathRegistrationUploadFB)form;
		WebUTIL.refreshTransState(request);
		DeathRegistrationUploadUTL.getListOfDeath(fb,request);
		return mapping.findForward("NEW"); 	
	}
	
	public ActionForward GETDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeathRegistrationUploadFB fb=(DeathRegistrationUploadFB)form;
		DeathRegistrationUploadUTL.getEssentialForBirthRegUpload(fb,request);
		return mapping.findForward("NEW"); 	
	}
	
	public ActionForward GETHANDOVERDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeathRegistrationUploadFB fb=(DeathRegistrationUploadFB)form;
		DeathRegistrationUploadUTL.getEssentialForBirthRegUploadNHandover(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDUPHANDOVERDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeathRegistrationUploadFB fb=(DeathRegistrationUploadFB)form;
		DeathRegistrationUploadUTL.getEssentialForBirthRegUploadNHandover(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeathRegistrationUploadFB fb=(DeathRegistrationUploadFB)form;
		DeathRegistrationUploadUTL.saveForRegUpload(fb,request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward HANDOVERSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeathRegistrationUploadFB fb=(DeathRegistrationUploadFB)form;
		DeathRegistrationUploadUTL.saveHandoverForRegUpload(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward DUPLICATEHANDOVERSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeathRegistrationUploadFB fb=(DeathRegistrationUploadFB)form;
		DeathRegistrationUploadUTL.saveDuplicateHandoverForRegUpload(fb,request);
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
	
	public ActionForward SEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//DeathRegistrationUploadFB fb=(DeathRegistrationUploadFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MrdConfig.SERACH_DEATH_REGISTRATION_UPLOAD_LIST);
		return mapping.findForward("SEARCH"); 	
	}
	
	public ActionForward SEARCHDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeathRegistrationUploadFB fb=(DeathRegistrationUploadFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MrdConfig.SERACH_DEATH_REGISTRATION_UPLOAD_LIST);
		DeathRegistrationUploadUTL.getPatDeathUploadList(fb,request);
		return mapping.findForward("SEARCH"); 	
	}
	
}
