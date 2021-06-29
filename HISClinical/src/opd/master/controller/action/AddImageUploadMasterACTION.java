package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.master.controller.fb.AddImageUploadMasterFB;
import opd.master.controller.util.AddImageUploadMasterUTIL;
import opd.transaction.controller.fb.OpdImageExamTabFB;
import opd.transaction.controller.util.OpdImageExamTabUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AddImageUploadMasterACTION extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		//generateToken(request);
		AddImageUploadMasterFB fb=(AddImageUploadMasterFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		fb.setTransactionMode(fb.getHmode());
		fb.setIsDefault(OpdConfig.NO);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AddImageUploadMasterFB fb=(AddImageUploadMasterFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		AddImageUploadMasterUTIL.getImageForModify(fb,request,response);
		request.setAttribute("ImageToBeViewed",fb.getUploadImageName());
		fb.setTransactionMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AddImageUploadMasterFB fb=(AddImageUploadMasterFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		AddImageUploadMasterUTIL.getImageForModify(fb,request,response);
		request.setAttribute("ImageToBeViewed",fb.getUploadImageName());
		return mapping.findForward("NEW");
	}
	
	public ActionForward IMAGE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AddImageUploadMasterFB fb=(AddImageUploadMasterFB)form;
		AddImageUploadMasterUTIL.addImage(fb,request);
		fb.setHmode(fb.getTransactionMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//validateToken(request,response);
		AddImageUploadMasterFB fb=(AddImageUploadMasterFB)form;
		if(AddImageUploadMasterUTIL.saveImage(fb,request))
		{
			fb.reset(mapping, request);
			WebUTIL.refreshTransState(request);
			fb.setTransactionMode(fb.getHmode());
			fb.setIsDefault(OpdConfig.NO);
		}
		fb.setHmode(fb.getTransactionMode());
		 return mapping.findForward("NEW");
		//return mapping.findForward("LIST");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//validateToken(request,response);
		AddImageUploadMasterFB fb=(AddImageUploadMasterFB)form;
		boolean flag=AddImageUploadMasterUTIL.saveModifyImage(fb, request);
		fb.setHmode(fb.getTransactionMode());
		if(flag)
		{
			return mapping.findForward("LIST");
		}
		else
		{
			return this.MODIFY(mapping, form, request, response);
		}
	}
	public ActionForward VIEWIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		AddImageUploadMasterFB fb=(AddImageUploadMasterFB)form;
		AddImageUploadMasterUTIL.setImageLog(fb, request,response);
		return mapping.findForward("VIEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		 	
		  return mapping.findForward("LIST");		 	   	
	}
}
