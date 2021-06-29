package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.GuidlinesEnquiryFB;
import enquiry.transaction.controller.util.GuidlinesEnquiryUTIL;

public class GuidlinesEnquiryACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GuidlinesEnquiryFB fb = (GuidlinesEnquiryFB) form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		GuidlinesEnquiryUTIL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETTEMPLATEID(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GuidlinesEnquiryFB fb = (GuidlinesEnquiryFB) form;
		GuidlinesEnquiryUTIL.getTemplateId(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GuidlinesEnquiryFB fb = (GuidlinesEnquiryFB) form;
		return mapping.findForward("CANCEL");
	}
	
	
	
	
}
