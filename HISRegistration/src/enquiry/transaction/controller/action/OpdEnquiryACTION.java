package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.OpdEnquiryFB;

import enquiry.transaction.controller.util.OpdEnquiryUTIL;


public class OpdEnquiryACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return this.CALLDIRECTLY(mapping,form,request,response);		
	}
	
	//call Directly
	public ActionForward CALLDIRECTLY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdEnquiryFB fb = (OpdEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		OpdEnquiryUTIL.getEssentials(fb,request); 
		return mapping.findForward("NEW");
	}
	
	//call from desk
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdEnquiryFB fb = (OpdEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		OpdEnquiryUTIL.getEssentials(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward ONCHANGEHOSPITAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		OpdEnquiryFB fb = (OpdEnquiryFB) form;
		String strIsDirectCall=fb.getIsDirectCall();
		fb.reset(mapping, request);
		OpdEnquiryUTIL.getDepartmentEssentials(fb,request);
		fb.setIsDirectCall(strIsDirectCall);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETUNIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdEnquiryFB fb = (OpdEnquiryFB) form;
		OpdEnquiryUTIL.getDepartmentUnit(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdEnquiryFB fb = (OpdEnquiryFB) form;
		OpdEnquiryUTIL.getSearchDetail(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdEnquiryFB fb = (OpdEnquiryFB) form;
		OpdEnquiryUTIL.getAllConsulatantDetailsForUnit(fb,request); 
		return mapping.findForward("POPUP");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdEnquiryFB fb = (OpdEnquiryFB) form;
		return mapping.findForward("CANCEL");
	}


}
