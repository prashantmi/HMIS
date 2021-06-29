package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.HospitalTelephoneEnquiryFB;
import enquiry.transaction.controller.fb.InPatientEnquiryFB;
import enquiry.transaction.controller.fb.StaffEnquiryFB;
import enquiry.transaction.controller.util.HospitalTelephoneEnquiryUTL;
import enquiry.transaction.controller.util.StaffEnquiryUTL;

public class StaffEnquiryACTION extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.STAFFENQUIRY(mapping,form,request,response);		
	}
	
	//Call directly
	public ActionForward STAFFENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		StaffEnquiryFB fb = (StaffEnquiryFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		StaffEnquiryUTL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	//call from desk
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		StaffEnquiryFB fb = (StaffEnquiryFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		StaffEnquiryUTL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ONCHANGEHOSPITAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		StaffEnquiryFB fb = (StaffEnquiryFB) form;
		String strIsDirectCall=fb.getIsDirectCall();
		fb.reset(mapping, request);
		StaffEnquiryUTL.getStaffEssentialsOnHospitalChange(fb,request);
		fb.setIsDirectCall(strIsDirectCall);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		StaffEnquiryFB fb = (StaffEnquiryFB) form;
		StaffEnquiryUTL.searchStaffDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
	    WebUTIL.refreshTransState(request);
	    WebUTIL.setValueForModuleSwitching(request,"", "/startup/initPage.jsp");
	    return mapping.findForward("CANCEL");
    }
	

}
