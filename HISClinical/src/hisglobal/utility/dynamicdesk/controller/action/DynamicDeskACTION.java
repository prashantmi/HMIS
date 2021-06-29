package hisglobal.utility.dynamicdesk.controller.action;

import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.fb.DynamicDeskFB;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DynamicDeskACTION extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		DynamicDeskFB fb =  (DynamicDeskFB)form;
		// Creating the Dynamic Desk Specific Session
		DynamicDeskUTIL.createSessionState(request);
		
		// Getting Dynamic Desk Essentials From Session
		HttpSession session = WebUTIL.getSession(request);
		fb.setDeskType((String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
		fb.setDepartmentUnitCode((String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
		fb.setWardCode((String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE));
		
		// Setting Dynamic Desk Essentials From Session
		DynamicDeskUTIL.getDynamicDeskEssentials(fb, request);

		return mapping.findForward("DESK");
	}
}
