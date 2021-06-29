package opd.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.CsultyDeskLoginFB;
import opd.transaction.controller.util.CsultyDeskLoginUTIL;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CsultyDeskFooterMainACTION extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		CsultyDeskLoginFB fb =  (CsultyDeskLoginFB)form;
		// Getting Casualty Desk Essentials for Footer
		CsultyDeskLoginUTIL.getCsultyDeskEssentialForFooter(fb, request);
		return mapping.findForward("FOOTER");
	}
}
