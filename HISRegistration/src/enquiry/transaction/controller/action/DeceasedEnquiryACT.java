package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.fb.DeceasedEnquiryFB;
import enquiry.transaction.controller.util.DeceasedEnquiryUTL;

public class DeceasedEnquiryACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeceasedEnquiryFB fb=(DeceasedEnquiryFB)form;
		WebUTIL.refreshTransState(request);
		fb.setDeceasedNo("");
		DeceasedEnquiryUTL.getAllDeceasedListInMortuary(fb,request);
		fb.setChkRadioBtn(enquiryConfig.DECEASED_TYPE_ALL);
		return mapping.findForward("NEW");
	}
	
	public ActionForward FILTER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeceasedEnquiryFB fb=(DeceasedEnquiryFB)form;
		DeceasedEnquiryUTL.getDeceasedDtlByDeceaseType(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDECEASEDDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeceasedEnquiryFB fb=(DeceasedEnquiryFB)form;
		DeceasedEnquiryUTL.getDeceasedDtlByDeceaseNo(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
