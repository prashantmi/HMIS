package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.PatImageUploadFB;
import mrd.transaction.controller.utl.PatImageUploadUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatImageUploadACT extends CSRFGardTokenAction
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
		PatImageUploadFB fb=(PatImageUploadFB)form;
		WebUTIL.refreshTransState(request);	
		ControllerUTIL.setSysdate(request);
		fb.reset(mapping, request);
		fb.setHmode("NEW");
		Status status=new Status();
		status.add(Status.NEW);
		WebUTIL.setStatus(request, status);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatImageUploadFB fb=(PatImageUploadFB)form;
		request.getSession().removeAttribute(MrdConfig.PATIENT_IMAGE_UPLOADED_IN_SESSION);
		request.getSession().removeAttribute(MrdConfig.PATIENT_IMAGE_MAP);
		PatImageUploadUTL.getPatientImageDtlByCrNo(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UPLOADIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatImageUploadFB fb=(PatImageUploadFB)form;
		PatImageUploadUTL.uploadImage(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVEIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatImageUploadFB fb=(PatImageUploadFB)form;
		PatImageUploadUTL.removeImage(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward DELETEPATIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatImageUploadFB fb=(PatImageUploadFB)form;
		PatImageUploadUTL.deletePatientImage(fb,request);
		fb.setNoOfImages("0");
		return this.GETPATDTL(mapping, form, request, response);
	}
	
	public ActionForward SAVEPATIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//validateToken(request,response);
		System.out.println("PatImageUploadACT.SAVEPATIMAGE()");
		PatImageUploadFB fb=(PatImageUploadFB)form;
		PatImageUploadUTL.savePatImage(fb,request);
		fb.setNoOfImages("0");
		return this.MODIFYPATIMAGE(mapping, form, request, response);     //calling method MODIFYPATIMAGE by warsh on 27-10-17 using default set image
		//return this.GETPATDTL(mapping, form, request, response);
	}

	
	public ActionForward MODIFYPATIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//validateToken(request,response);
		PatImageUploadFB fb=(PatImageUploadFB)form;
		PatImageUploadUTL.modifyPatImage(fb,request);
		return this.GETPATDTL(mapping, form, request, response);
	}
	
	
	
}
