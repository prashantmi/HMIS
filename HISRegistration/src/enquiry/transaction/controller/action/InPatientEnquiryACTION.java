package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.InPatientEnquiryFB;
import enquiry.transaction.controller.util.InPatientEnquiryUTL;

public class InPatientEnquiryACTION extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.GETINTPATIENT(mapping,form,request,response);		
	}
	//call Directly
	
	public ActionForward GETINTPATIENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		InPatientEnquiryFB fb = (InPatientEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		InPatientEnquiryUTL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	// Call from Desk
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		InPatientEnquiryFB fb = (InPatientEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		InPatientEnquiryUTL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		InPatientEnquiryFB fb = (InPatientEnquiryFB) form;
		InPatientEnquiryUTL.searchInPatient(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ORDERBYCRNO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		InPatientEnquiryFB fb = (InPatientEnquiryFB) form;
		InPatientEnquiryUTL.getOrderByCrNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ORDERBYNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		InPatientEnquiryFB fb = (InPatientEnquiryFB) form;
		InPatientEnquiryUTL.getOrderByName(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
	    WebUTIL.refreshTransState(request);
	    WebUTIL.setValueForModuleSwitching(request,"", "/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp");
	    return mapping.findForward("CANCEL");
    }
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InPatientEnquiryFB fb = (InPatientEnquiryFB) form;
		//WebUTIL.refreshTransState(request);
		InPatientEnquiryUTL.getInPatientDetail(fb, request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward GETUNIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		InPatientEnquiryFB fb = (InPatientEnquiryFB) form;
		InPatientEnquiryUTL.getDepartmentUnit(fb,request); 
		return mapping.findForward("NEW");
	}

}
