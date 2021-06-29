package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.GuestHouseEnquiryFB;
import enquiry.transaction.controller.util.GuestHouseEnquiryUTL;

public class GuestHouseEnquiryACTION extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.DIRECTCALL(mapping,form,request,response);		
	}

	public ActionForward DIRECTCALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		GuestHouseEnquiryFB fb = (GuestHouseEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		//fb.setIsDirectCall("DIRECT");
		GuestHouseEnquiryUTL.getGuestHouseList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		GuestHouseEnquiryFB fb = (GuestHouseEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		//fb.setIsDirectCall("DESK");
		GuestHouseEnquiryUTL.getGuestHouseList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETGUESTHOUSEDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		GuestHouseEnquiryFB fb = (GuestHouseEnquiryFB) form;
		GuestHouseEnquiryUTL.getGuestHouseBedDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		GuestHouseEnquiryFB fb = (GuestHouseEnquiryFB) form;
		return mapping.findForward("CANCEL");
	}
	
	

}
