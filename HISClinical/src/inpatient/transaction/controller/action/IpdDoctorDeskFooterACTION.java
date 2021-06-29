package inpatient.transaction.controller.action;

import inpatient.transaction.controller.fb.IpdDoctorDeskLoginFB;
import inpatient.transaction.controller.utl.IpdDoctorDeskLoginUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class IpdDoctorDeskFooterACTION extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		IpdDoctorDeskLoginFB fb =  (IpdDoctorDeskLoginFB)form;
		// Getting IPD Doctor Desk Essentials for Footer
		IpdDoctorDeskLoginUTL.getIPDDoctorDeskEssentialForFooter(fb, request);
		return mapping.findForward("FOOTER");
	}
}
