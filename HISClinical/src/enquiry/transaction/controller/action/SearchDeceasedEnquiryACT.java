package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.SearchDeceasedEnquiryFB;
import enquiry.transaction.controller.util.SearchDeceasedEnquiryUTL;


public class SearchDeceasedEnquiryACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SearchDeceasedEnquiryFB fb=(SearchDeceasedEnquiryFB)form;
		WebUTIL.refreshTransState(request);
		SearchDeceasedEnquiryUTL.getEssentialForDeceasedEnquiry(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SearchDeceasedEnquiryFB fb=(SearchDeceasedEnquiryFB)form;
		SearchDeceasedEnquiryUTL.searchDeceased(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDECEASEDDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SearchDeceasedEnquiryFB fb=(SearchDeceasedEnquiryFB)form;
		SearchDeceasedEnquiryUTL.getDeceasedDtlByDeceaseNo(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
