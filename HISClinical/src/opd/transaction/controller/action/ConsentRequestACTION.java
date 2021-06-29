package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HisHTMLParserUtil;
import hisglobal.utility.servlets.ServletsUtilityConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import opd.transaction.controller.fb.ConsentRequestFB;
import opd.transaction.controller.util.ConsentRequestUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsentRequestACTION extends CSRFGardTokenAction
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		ConsentRequestFB fb = (ConsentRequestFB) form;
		fb.setCurrentPage(1);
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		ConsentRequestUTIL.getEssentials(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward POPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ConsentRequestFB fb = (ConsentRequestFB) form;
		ConsentRequestUTIL.setConsentData(fb, request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//ConsentRequestFB fb = (ConsentRequestFB) form;
		//ConsentRequestUTIL.setConsentData(fb, request);
		return mapping.findForward("VIEW");
	}
	

	public ActionForward UPDATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ConsentRequestFB fb = (ConsentRequestFB) form;
		ConsentRequestUTIL.update(fb, request);
		fb.reset(mapping, request);
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward PRINT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ConsentRequestFB fb = (ConsentRequestFB) form;
		//Added by Vasu on 6.Mar.18 to decode base64 into Html Data
		String base64Data = fb.getConsentHtmlCode();
		String decoded = new String(DatatypeConverter.parseBase64Binary(base64Data));
		 fb.setConsentHtmlCode(HisHTMLParserUtil.freezeHTMLCodeElements(decoded));
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getConsentHtmlCode());
		// Water Marking
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());
		return mapping.findForward("POPUP");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ConsentRequestFB fb=(ConsentRequestFB)form;
		ConsentRequestUTIL.setData(fb, request);
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward POPUPPAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ConsentRequestFB fb=(ConsentRequestFB)form;
		//ConsentRequestUTIL.setData(fb, request);
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		fb.reset(mapping, request);
		return mapping.findForward("VIEW");
	}
}
