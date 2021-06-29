package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.HospitalFacilityEnquiryFB;
import enquiry.transaction.controller.util.HospitalFacilityEnquiryUTL;

public class HospitalFacilityEnquiryACTION extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.DIRECTCALL(mapping,form,request,response);		
	}

	public ActionForward DIRECTCALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityEnquiryFB fb = (HospitalFacilityEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		HospitalFacilityEnquiryUTL.getAllHospitalFacilityList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityEnquiryFB fb = (HospitalFacilityEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		HospitalFacilityEnquiryUTL.getAllHospitalFacilityList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETFACILITYDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityEnquiryFB fb = (HospitalFacilityEnquiryFB) form;
		HospitalFacilityEnquiryUTL.getFacilityDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityEnquiryFB fb = (HospitalFacilityEnquiryFB) form;
		return mapping.findForward("CANCEL");
	}
	
	

}
