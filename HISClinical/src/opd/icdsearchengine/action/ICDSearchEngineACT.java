package opd.icdsearchengine.action;


import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.icdsearchengine.fb.ICDSearchEngineFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ICDSearchEngineACT extends DispatchAction
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDSearchEngineFB fb = (ICDSearchEngineFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}

/*	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		ICDSearchEngineFB fb = (ICDSearchEngineFB) form; // PatientAuditLogMstUTL.getData(fb, request);
		PatientAuditLogMstUTL.getPatientAuditLogEssentials(fb, request);
		request.getSession().removeAttribute(Config.AUDIT_HEADER_LIST);
		request.getSession().removeAttribute(Config.PATIENT_AUDIT_LOG_OPTIONS);
		fb.setAuditHeaderSize("0");
		fb.setIsDateWise("0");
		return mapping.findForward("NEW");
	}
*/
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
