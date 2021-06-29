package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.OTConsultantEnquiryFB;
import enquiry.transaction.controller.util.OTConsultantEnquiryUTIL;

public class OTConsultantEnquiryACTION extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OTConsultantEnquiryFB fb = (OTConsultantEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		OTConsultantEnquiryUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CONSULTANTDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OTConsultantEnquiryFB fb = (OTConsultantEnquiryFB) form;
		OTConsultantEnquiryUTIL.getConsultantDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHBYNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OTConsultantEnquiryFB fb = (OTConsultantEnquiryFB) form;
		OTConsultantEnquiryUTIL.getConsultantByName(fb, request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("inside CANCEL ACTION");
	    WebUTIL.refreshTransState(request);	
	   /* OTConsultantEnquiryFB fb = (OTConsultantEnquiryFB) form;
	    fb.reset(mapping, request);*/
		return this.NEW(mapping, form, request, response);
    }

}
