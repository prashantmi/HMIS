package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.DeskMenuMacroMstAddFB;
import opd.master.controller.util.DeskMenuMacroMstAddUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeskMenuMacroMstAddACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		DeskMenuMacroMstAddFB fb=(DeskMenuMacroMstAddFB) form;
	
		fb.reset(mapping, request);
		DeskMenuMacroMstAddUTIL.setDesk(fb,request);
	
		if(fb.getHmode().equalsIgnoreCase("MOD"))
		{
			DeskMenuMacroMstAddUTIL.getMacroHeadForModify(fb,request);
		}
		else
		{
			DeskMenuMacroMstAddUTIL.setMacroHeadEssential(fb,request);
			
		}
			
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		 	
		//DeskMenuMacroMstAddFB fb=(DeskMenuMacroMstAddFB)form;
		
		 return mapping.findForward("VIEW");		
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeskMenuMacroMstAddFB fb = (DeskMenuMacroMstAddFB) form;
	
		DeskMenuMacroMstAddUTIL.saveMacroHead(fb,request);	
		fb.setHmode("ADD");
		 //return mapping.findForward("NEW");		
		 return this.NEW(mapping,form,request,response);	
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeskMenuMacroMstAddFB fb = (DeskMenuMacroMstAddFB) form;
		DeskMenuMacroMstAddUTIL.modifySaveMacroHead(fb,request);
	//	return this.NEW(mapping, form, request, response);
		 return mapping.findForward("VIEW");		
	}
	
	public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeskMenuMacroMstAddFB fb = (DeskMenuMacroMstAddFB) form;
		fb.setHmode("ADD");
		 return this.NEW(mapping, form, request, response);
		
	}
}
