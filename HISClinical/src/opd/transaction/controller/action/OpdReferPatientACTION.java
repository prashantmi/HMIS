package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.transaction.controller.fb.OpdReferPatientFB;
import opd.transaction.controller.util.OpdReferPatientUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdReferPatientACTION extends CSRFGardTokenAction
{
	/**
	 * the default action called when a page is loaded for the first time
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action "NEW"
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("inside unspecified of oldDepartmentVisitACTION ");
		return this.NEW(mapping, form, request, response);
	}

	/**
	 * action mainly called at the initial loading of a page or when a form is reset refreshes Transaction State sets all
	 * initial Old Patient Visit essentials
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		System.out.println("inside new of oldDepartmentVisitACTION");

		OpdReferPatientFB fb = (OpdReferPatientFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		OpdReferPatientUTIL.getEssentialsByChoice(fb, request);
		System.out.println("before forwarding to NEW");
		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		HttpSession session = request.getSession();
		OpdReferPatientFB fb = (OpdReferPatientFB) form;
		OpdReferPatientUTIL.saveReferPatientDetail(fb, request);
		String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if (deskType == null) deskType = "";
		if (deskType.equals(DynamicDeskConfig.DESK_TYPE_CMO_DESK)) return mapping.findForward("LIST");
		else return this.NEW(mapping, form, request, response);
	}

}
