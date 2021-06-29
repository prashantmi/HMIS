package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.master.controller.fb.DeskWiseDefaultProfileMstFB;
import opd.master.controller.util.DeskWiseDefaultProfileMstUTIL;


public class DeskWiseDefaultProfileMstACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.MODIFY(mapping, form, request, response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		DeskWiseDefaultProfileMstFB fb = (DeskWiseDefaultProfileMstFB) form;
		WebUTIL.refreshTransState(request);
		DeskWiseDefaultProfileMstUTIL.getDeskType(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DESKNAME(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeskWiseDefaultProfileMstFB fb = (DeskWiseDefaultProfileMstFB) form;
		DeskWiseDefaultProfileMstUTIL.getDeskName(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MENUNAME(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeskWiseDefaultProfileMstFB fb = (DeskWiseDefaultProfileMstFB) form;
		DeskWiseDefaultProfileMstUTIL.getMenuName(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeskWiseDefaultProfileMstFB fb = (DeskWiseDefaultProfileMstFB) form;
		boolean hasFlag = DeskWiseDefaultProfileMstUTIL.saveDefaultProfileDetails(fb, request);
		if (hasFlag)
		{
			fb.reset(mapping, request);
		}
			
		return mapping.findForward("NEW");
	}

	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
