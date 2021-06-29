package hisglobal.utility.clinicianworkbench.controller.action;


import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.fb.DynamicDeskFB;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DynamicDeskClinicianWorkbenchACTION extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		DynamicDeskFB fb =  (DynamicDeskFB)form;
	
		
		// Getting Dynamic Desk Essentials From Session
		HttpSession session = WebUTIL.getSession(request);

	
		return mapping.findForward("NEW");
	}
}
