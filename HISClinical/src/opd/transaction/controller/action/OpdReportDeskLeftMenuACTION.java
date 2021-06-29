package opd.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opd.transaction.controller.fb.OpdReportLeftMenuFB;
import opd.transaction.controller.util.OpdReportDeskLeftMenuUTIL;
import org.apache.struts.action.*;


public class OpdReportDeskLeftMenuACTION extends Action{
	/**
	 * the excute will called when a page is loaded for any time
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm 
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	  * @return action forwards to the output view called "NEW"
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OpdReportLeftMenuFB fb=(OpdReportLeftMenuFB)form;
		OpdReportDeskLeftMenuUTIL.getOpdReportDeskLeftMenuDetail(fb,request);
		return mapping.findForward("NEW");
	}
}
