package opd.transaction.controller.action;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdBayDeskLoginFB;
import opd.transaction.controller.util.OpdBayDeskLoginUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdBayDeskLoginACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdBayDeskLoginFB fb = (OpdBayDeskLoginFB) form;
		WebUTIL.refreshTransState(request);
		DynamicDeskUTIL.refreshSessionState(request);
		boolean flag = OpdBayDeskLoginUTIL.getOpdDeskEssentials(fb, request);
		if (!flag)
			return this.GETROOM(mapping, form, request, response);
		else return mapping.findForward("NEW");
	}
	
	public ActionForward GETROOM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdBayDeskLoginFB fb = (OpdBayDeskLoginFB) form;
		boolean flag = OpdBayDeskLoginUTIL.getRoomByUnitCode(fb, request);
		if (!flag)
		{
			OpdBayDeskLoginUTIL.goToOpdDesk(fb, request);
			return mapping.findForward("DYNAMICDESK");
		}
		else return mapping.findForward("NEW");

	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdBayDeskLoginFB fb = (OpdBayDeskLoginFB) form;
		OpdBayDeskLoginUTIL.goToOpdDesk(fb, request);
		return mapping.findForward("DYNAMICDESK");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		WebUTIL.refreshTransState(request);
		DynamicDeskUTIL.refreshSessionState(request);
		return mapping.findForward("CANCEL");
	}
}
