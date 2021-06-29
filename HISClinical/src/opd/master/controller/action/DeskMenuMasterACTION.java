package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.master.controller.fb.DeskMenuMasterFB;
import opd.master.controller.util.DeskMenuMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeskMenuMasterACTION extends CSRFGardTokenAction 
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		DeskMenuMasterFB fb= (DeskMenuMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);
		DeskMenuMasterUTIL.getEssentails(fb, request);
		fb.setIsTemplateBased(OpdConfig.IS_TEMPLATE_NO);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		DeskMenuMasterFB fb= (DeskMenuMasterFB)form;
		DeskMenuMasterUTIL.saveDetail(fb, request);
		fb.reset(mapping,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeskMenuMasterFB fb= (DeskMenuMasterFB)form;
		WebUTIL.refreshTransState(request);
		DeskMenuMasterUTIL.getEssentails(fb, request);
		DeskMenuMasterUTIL.getModifyDetail(fb, request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeskMenuMasterFB fb = (DeskMenuMasterFB)form;
		boolean flag=DeskMenuMasterUTIL.saveModifyDetail(fb,request);
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
		DeskMenuMasterFB fb= (DeskMenuMasterFB)form;
		DeskMenuMasterUTIL.getModifyDetail(fb, request);
		return mapping.findForward("NEW");
	}
}
