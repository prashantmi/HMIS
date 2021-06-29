package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.TemplateMappingMstFB;
import opd.master.controller.util.TemplateMappingMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class TemplateMappingMstACT extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		generateToken(request);
		TemplateMappingMstFB fb =(TemplateMappingMstFB)form;		 
		fb.reset(mapping,request);
		WebUTIL.refreshTransState(request);
		TemplateMappingMstUTL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETALLDEPT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		TemplateMappingMstFB fb =(TemplateMappingMstFB)form;		 
		//fb.reset(mapping,request);
		TemplateMappingMstUTL.getDepartment(fb,request);
		fb.setHmode("ADD");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETALLUNIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		TemplateMappingMstFB fb =(TemplateMappingMstFB)form;		 
		//fb.reset(mapping,request);
		TemplateMappingMstUTL.getAllUnits(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETUNITNOTADDED(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		TemplateMappingMstFB fb =(TemplateMappingMstFB)form;		 
		//fb.reset(mapping,request);
		TemplateMappingMstUTL.getUnitNotAdded(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETWARD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		TemplateMappingMstFB fb =(TemplateMappingMstFB)form;		 
		//fb.reset(mapping,request);
		TemplateMappingMstUTL.getWardNotAdded(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		//TemplateMappingMstFB fb =(TemplateMappingMstFB)form;		 
		//fb.reset(mapping,request);
		Status status=new Status();
		status.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, status);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		 TemplateMappingMstFB fb = (TemplateMappingMstFB) form;
		 TemplateMappingMstUTL.saveTemplateMapping(fb,request);
		 return this.ADD(mapping, form, request, response);	
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		TemplateMappingMstFB fb = (TemplateMappingMstFB) form;
		TemplateMappingMstUTL.modifyTemplateMapping(fb, request);
		return mapping.findForward("LIST");		
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		TemplateMappingMstFB fb = (TemplateMappingMstFB) form;
		WebUTIL.refreshTransState(request);
		TemplateMappingMstUTL.getTemplateMapping(fb,request);
		return mapping.findForward("MODIFY");		
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		 	
		return mapping.findForward("LIST");		 	   	
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		 	
		TemplateMappingMstFB fb = (TemplateMappingMstFB) form;
		WebUTIL.refreshTransState(request);
		TemplateMappingMstUTL.getTemplateMapping(fb,request);
		return mapping.findForward("MODIFY");				 	   	
	}
	
}
