package opd.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdDeskFooterFB;
import opd.transaction.controller.util.OpdDeskFooterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdDeskFooterACTION extends DispatchAction
{
	// *
	public ActionForward unspecified(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception
	{
		return this.NEW(arg0, arg1, arg2, arg3);
	}
	// *
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OpdDeskFooterFB opdDeskFooterFB = (OpdDeskFooterFB) form;
		OpdDeskFooterUTIL.setInitials(opdDeskFooterFB, request);

		return mapping.findForward("NEW");
	}
}
