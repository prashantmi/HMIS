package opd.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opd.transaction.controller.util.LeftMenuOpdDeskUTIL;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import opd.transaction.controller.fb.LeftMenuOpdDeskFB;

public class LeftMenuOpdDeskACTION extends Action {
	/**
	 * the excute will called when a page is loaded for any time
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	  * @return action forwards to the output view called "NEW"
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LeftMenuOpdDeskFB fb=(LeftMenuOpdDeskFB)form;
		LeftMenuOpdDeskUTIL.getOpdLeftMenuDetail(fb,request);
		return mapping.findForward("NEW");
	}
}
