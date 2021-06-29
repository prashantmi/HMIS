package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.fb.MortuaryImageUploadFB;
import mortuary.transaction.controller.utl.MortuaryImageUploadUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MortuaryImageUploadACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	/** 
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
		MortuaryImageUploadFB fb=(MortuaryImageUploadFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		fb.setHmode("NEW");
		Status status=new Status();
		status.add(Status.NEW);
		WebUTIL.setStatus(request, status);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DECEASEDDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		MortuaryImageUploadFB fb=(MortuaryImageUploadFB)form;
		request.getSession().removeAttribute(MortuaryConfig.DECEASED_IMAGE_UPLOADED_IN_SESSION);
		request.getSession().removeAttribute(MortuaryConfig.PATIENT_IMAGE_MAP);
		MortuaryImageUploadUTL.getPatientImageDtlByDeceasedNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UPLOADIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		MortuaryImageUploadFB fb=(MortuaryImageUploadFB)form;
		MortuaryImageUploadUTL.uploadImage(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVEIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		MortuaryImageUploadFB fb=(MortuaryImageUploadFB)form;
		MortuaryImageUploadUTL.removeImage(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward DELETEPATIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MortuaryImageUploadFB fb=(MortuaryImageUploadFB)form;
		MortuaryImageUploadUTL.deleteDeceasedImage(fb,request);
		fb.setNoOfImages("0");
		return this.DECEASEDDTL(mapping, form, request, response);
	}
	
	public ActionForward SAVEPATIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MortuaryImageUploadFB fb=(MortuaryImageUploadFB)form;
		MortuaryImageUploadUTL.saveDeceasedImage(fb,request);
		fb.setNoOfImages("0");
		return this.DECEASEDDTL(mapping, form, request, response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
	
}
