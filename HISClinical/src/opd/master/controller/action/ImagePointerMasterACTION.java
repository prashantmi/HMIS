package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ImagePointerMasterFB;
import opd.master.controller.util.ImagePointerMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ImagePointerMasterACTION extends CSRFGardTokenAction 
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		ImagePointerMasterFB fb= (ImagePointerMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.setHmode("ADD");
		fb.setTempMode(fb.getHmode());
		fb.reset(mapping,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		ImagePointerMasterFB fb= (ImagePointerMasterFB)form;
		ImagePointerMasterUTIL.saveDetail(fb, request);
		fb.reset(mapping,request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ImagePointerMasterFB fb= (ImagePointerMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.setTempMode(fb.getHmode());
		ImagePointerMasterUTIL.getModifyDetail(fb, request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ImagePointerMasterFB fb = (ImagePointerMasterFB)form;
		boolean flag=ImagePointerMasterUTIL.saveModifyDetail(fb,request);
		if(flag)
		{
			return mapping.findForward("LIST");
		}
		else
		{	
			fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ImagePointerMasterFB fb= (ImagePointerMasterFB)form;
		ImagePointerMasterUTIL.getModifyDetail(fb, request);
		return mapping.findForward("NEW");
	}
}
