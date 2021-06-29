package hisglobal.utility.dynamicdesk.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DynamicDeskMenusACTION extends DispatchAction
{
	public ActionForward TOP(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		/*DynamicDeskFB fb = (DynamicDeskFB)form;
		HttpSession session = WebUTIL.getSession(request);
		
		fb.setDeskType((String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
		fb.setDeskID((String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID));
		fb.setMenuLocation(DynamicDeskConfig.DYNAMIC_DESK_MENU_LOCATION_TOP);
		boolean flag = DynamicDeskUTIL.getDynamicDeskMenus(fb, request);
		if(flag)		*/
		return mapping.findForward("TOP");
		/*else
			return mapping.findForward("NONE");*/
	}

	public ActionForward LEFT(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		return mapping.findForward("LEFT");
	}

	public ActionForward RIGHT(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		return mapping.findForward("RIGHT");
	}

	public ActionForward BOTTOM(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		return mapping.findForward("BOTTOM");
	}
}
