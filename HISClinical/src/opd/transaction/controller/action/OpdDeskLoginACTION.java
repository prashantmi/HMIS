package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdDeskLoginFb;
import opd.transaction.controller.util.OpdDeskLoginUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdDeskLoginACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		OpdDeskLoginFb fb = (OpdDeskLoginFb) form;
		WebUTIL.refreshTransState(request);
		DynamicDeskUTIL.refreshSessionState(request);
		boolean flag = OpdDeskLoginUTIL.getOpdDeskEssentials(fb, request);
		if (!flag)
			return this.GETROOM(mapping, form, request, response);
		else return mapping.findForward("NEW");
	}
	
	public ActionForward GETROOM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println("OpdDeskLoginACTION.GETROOM()");
		OpdDeskLoginFb fb = (OpdDeskLoginFb) form;
		boolean flag = OpdDeskLoginUTIL.getRoomByUnitCode(fb, request);
		if (!flag)
		{
			OpdDeskLoginUTIL.goToOpdDesk(fb, request);
			return mapping.findForward("DYNAMICDESK");
		}
		else return mapping.findForward("NEW");

	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdDeskLoginFb fb = (OpdDeskLoginFb) form;
		OpdDeskLoginUTIL.goToOpdDesk(fb, request);
		return mapping.findForward("DYNAMICDESK");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		WebUTIL.refreshTransState(request);
		DynamicDeskUTIL.refreshSessionState(request);
		return mapping.findForward("CANCEL");
	}
}
