package mortuary.transaction.controller.action;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.PostmortemLoginFB;
import mortuary.transaction.controller.utl.PostmortemLoginUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PostmortemLoginACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		PostmortemLoginFB fb=(PostmortemLoginFB)form;
		WebUTIL.refreshTransState(request);
		DynamicDeskUTIL.refreshSessionState(request);
		PostmortemLoginUTL.goToPostmortemDesk(fb,request);
		return mapping.findForward("DYNAMICDESK");
	}
	
}
