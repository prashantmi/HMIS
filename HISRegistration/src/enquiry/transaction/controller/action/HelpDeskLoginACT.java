package enquiry.transaction.controller.action;

import hisglobal.presentation.ControllerUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HelpDeskLoginACT extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ControllerUTIL.setSysdate(request);
		//DynamicDeskUTIL.refreshSessionState(request);

		//DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_TYPE, DynamicDeskConfig.DESK_TYPE_HELP_DESK);
		//DynamicDeskUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_ROWS_SPAN, "30,0%,50%,0%,0");// Desk Header, Desk Top Menu, Center Desk, Bottom Menu, Desk Footer

		return mapping.findForward("DESK");
	}
}
