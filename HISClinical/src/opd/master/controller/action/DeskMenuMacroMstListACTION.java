package opd.master.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.DeskMenuMacroMstListFB;
import opd.master.controller.util.DeskMenuMacroMstListUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeskMenuMacroMstListACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);			
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeskMenuMacroMstListFB fb=(DeskMenuMacroMstListFB) form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		DeskMenuMacroMstListUTIL.setSysdate(request);
		DeskMenuMacroMstListUTIL.getDeskType(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETMENU(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeskMenuMacroMstListFB fb =(DeskMenuMacroMstListFB) form;
		DeskMenuMacroMstListUTIL.getDeskMenuBasedOnDeskType(fb,request);		 
		 return mapping.findForward("NEW");
	}
	
	public ActionForward GETMACROHEAD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeskMenuMacroMstListFB fb =(DeskMenuMacroMstListFB) form;
		DeskMenuMacroMstListUTIL.getMacroHead(fb,request);
		return mapping.findForward("SAME");
	}
	
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//DeskMenuMacroMstListFB fb=(DeskMenuMacroMstListFB)form;
		//fb.setHmode("NEW");
		return mapping.findForward("ADD");
		 
	}
	public ActionForward MOD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		 return mapping.findForward("MODIFY");		 			 
	}
	
	public ActionForward DELETE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeskMenuMacroMstListFB fb =(DeskMenuMacroMstListFB) form;
		DeskMenuMacroMstListUTIL.deleteMacroHead(fb,request);
		return mapping.findForward("SAME");		 			 
	}
	
	
}
