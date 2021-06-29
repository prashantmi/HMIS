package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.HospitalTelephoneEnquiryFB;
import enquiry.transaction.controller.util.HospitalTelephoneEnquiryUTL;

public class HospitalTelephoneEnquiryACTION extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.DIRECTCALL(mapping,form,request,response);		
	}

	public ActionForward DIRECTCALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalTelephoneEnquiryFB fb = (HospitalTelephoneEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		HospitalTelephoneEnquiryUTL.getEssentials(fb,request);
		//HospitalTelephoneEnquiryUTL.getTelePhoneDetailByEmp(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalTelephoneEnquiryFB fb = (HospitalTelephoneEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		HospitalTelephoneEnquiryUTL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ONCHANGEHOSPITAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalTelephoneEnquiryFB fb = (HospitalTelephoneEnquiryFB) form;
		String strIsDirectCall=fb.getIsDirectCall();
		fb.reset(mapping, request);
		HospitalTelephoneEnquiryUTL.getTelephoneEssentialsOnHospitalChange(fb,request);
		fb.setIsDirectCall(strIsDirectCall);
		return mapping.findForward("NEW");
	}
	
	/*public ActionForward GETTELEPHONEBYDEPTNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalTelephoneEnquiryFB fb = (HospitalTelephoneEnquiryFB) form;
		HospitalTelephoneEnquiryUTL.getTelePhoneDetailByDeptName(fb, request);
		return mapping.findForward("NEW");
	}*/
	
	public ActionForward GETTELEPHONEBYEMP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalTelephoneEnquiryFB fb = (HospitalTelephoneEnquiryFB) form;
		HospitalTelephoneEnquiryUTL.getTelePhoneDetailByEmp(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalTelephoneEnquiryFB fb = (HospitalTelephoneEnquiryFB) form;
		return mapping.findForward("CANCEL");
	}
	
	

}
