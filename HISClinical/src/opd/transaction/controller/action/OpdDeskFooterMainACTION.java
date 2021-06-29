package opd.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdDeskLoginFb;
import opd.transaction.controller.util.OpdDeskLoginUTIL;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdDeskFooterMainACTION extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		OpdDeskLoginFb fb =  (OpdDeskLoginFb)form;
		// Getting OPD Desk Essentials for Footer
		OpdDeskLoginUTIL.getOPDDeskEssentialForFooter(fb, request);
		return mapping.findForward("FOOTER");
	}
}
