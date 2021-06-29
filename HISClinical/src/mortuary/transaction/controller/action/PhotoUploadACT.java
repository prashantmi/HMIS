package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.PhotoUploadFB;
import mortuary.transaction.controller.utl.PhotoUploadUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PhotoUploadACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		PhotoUploadFB fb=(PhotoUploadFB)form;
		WebUTIL.refreshTransState(request);
		PhotoUploadUTL.getExistingPhoto(fb,request ); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward REFRESHFORIMAGE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PhotoUploadFB fb=(PhotoUploadFB)form;
		PhotoUploadUTL.addNewImage(fb, request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PhotoUploadFB fb=(PhotoUploadFB)form;
		PhotoUploadUTL.removeNewImage(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		PhotoUploadFB fb=(PhotoUploadFB)form;
		PhotoUploadUTL.saveImage(fb, request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("VIEW");
	}
}
