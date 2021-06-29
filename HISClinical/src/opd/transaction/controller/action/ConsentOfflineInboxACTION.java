package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.servlets.ServletsUtilityConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.ConsentOfflineInboxFB;
import opd.transaction.controller.util.ConsentOfflineInboxUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsentOfflineInboxACTION  extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		ConsentOfflineInboxFB fb = (ConsentOfflineInboxFB) form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		fb.setPatCrNo(null);
		ConsentOfflineInboxUTIL.setSysdate(request);
		
		return mapping.findForward("NEW");
	}
	
	
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ConsentOfflineInboxFB fb=(ConsentOfflineInboxFB)form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request);
		ConsentOfflineInboxUTIL.getEssentials(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward POPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ConsentOfflineInboxFB fb = (ConsentOfflineInboxFB) form;
		ConsentOfflineInboxUTIL.setConsentData(fb, request);
		return mapping.findForward("POPUP");
	}

	public ActionForward UPDATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ConsentOfflineInboxFB fb = (ConsentOfflineInboxFB) form;
		ConsentOfflineInboxUTIL.update(fb, request);
		fb.reset(mapping, request);
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward PRINT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ConsentOfflineInboxFB fb = (ConsentOfflineInboxFB) form;
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getConsentHtmlCode());
		// Water Marking
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());
		return mapping.findForward("POPUP");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	 {
		ConsentOfflineInboxFB fb=(ConsentOfflineInboxFB)form;
		 fb.setPatCrNo(null);
		return this.NEW(mapping,form,request,response);
    }
	 public ActionForward INIT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	 {
		 ConsentOfflineInboxFB fb=(ConsentOfflineInboxFB)form;
		 fb.setPatCrNo(null);
		return mapping.findForward("CANCEL");
	 }
}
