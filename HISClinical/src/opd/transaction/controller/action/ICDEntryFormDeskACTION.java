package opd.transaction.controller.action;

/*
 * @ author Pragya Sharma
 * Creation Date : 23-Sep-2011
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.transaction.controller.fb.ICDEntryFormDeskFB;
import opd.transaction.controller.util.ICDEntryFormDeskUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ICDEntryFormDeskACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEWENTRY(mapping, form, request, response);
	}

	public ActionForward NEWENTRY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDEntryFormDeskFB fb = (ICDEntryFormDeskFB) form;
		// Status objStatus = new Status();
		ICDEntryFormDeskUTL.setTabSequence(OpdConfig.ICD_ENTRY_FORM_NEW_ENTRY, request);
		// objStatus.add(Status.NEW);
		// WebUTIL.setStatus(request, objStatus);
		fb.setDeskMode("NEWENTRY");
		// fb.setHmode("NEW");
		return mapping.findForward("NEWENTRY");
	}

	public ActionForward MODIFICATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDEntryFormDeskFB fb = (ICDEntryFormDeskFB) form;
		// Status objStatus = new Status();
		ICDEntryFormDeskUTL.setTabSequence(OpdConfig.ICD_ENTRY_FORM_MODIFICATION, request);
		// objStatus.add(Status.NEW);
		// WebUTIL.setStatus(request, objStatus);
		fb.setDeskMode("MODIFICATION");
		return mapping.findForward("MODIFICATION");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
}
