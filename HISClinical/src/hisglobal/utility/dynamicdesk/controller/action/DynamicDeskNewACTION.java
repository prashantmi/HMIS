package hisglobal.utility.dynamicdesk.controller.action;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.controller.fb.DynamicDeskFB;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DynamicDeskNewACTION extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		DynamicDeskFB fb =  (DynamicDeskFB)form;
		System.out.println("Desk Type :"+fb.getDeskType());
		// Creating the Dynamic Desk Specific Session
		DynamicDeskUTIL.refreshSessionState(request);
		WebUTIL.refreshTransState(request);
		
		// Getting Desk Essentials
		DynamicDeskUTIL.getDeskEssentials(fb, request);

		return mapping.findForward("DESK");
	}
}
