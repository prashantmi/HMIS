package hisglobal.utility.dynamicdesk.controller.action;

import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DynamicDeskForwardACTION extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		HttpSession session = WebUTIL.getSession(request);	
		String deskType = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		return mapping.findForward(deskType);
	}
}
