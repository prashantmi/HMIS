package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import registration.config.RegistrationConfig;

import enquiry.transaction.controller.fb.PatientEnquiryFB;
import enquiry.transaction.controller.util.PatientEnquiryUTIL;

public class PatientEnquiryACTION extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.PATIENTDETAIL(mapping,form,request,response);		
	}
	//call directly
	public ActionForward PATIENTDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientEnquiryFB fb = (PatientEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		PatientEnquiryUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	//call from desk
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientEnquiryFB fb = (PatientEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		PatientEnquiryUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientEnquiryFB fb = (PatientEnquiryFB) form;
		PatientEnquiryUTIL.searchPatient(fb,request);
		fb.setGnum_country_code(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
		fb.setGnum_state_code(RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
		fb.setNum_dist_id(RegistrationConfig.REGISTRATIONDESK_DEFAULT_DISTRICT_CODE);

		return mapping.findForward("NEW");
	}
	
	public ActionForward ORDERBYCRNO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientEnquiryFB fb = (PatientEnquiryFB) form;
		PatientEnquiryUTIL.getOrderByCrNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ORDERBYNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientEnquiryFB fb = (PatientEnquiryFB) form;
		PatientEnquiryUTIL.getOrderByName(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
	    WebUTIL.refreshTransState(request);
	    WebUTIL.setValueForModuleSwitching(request,"", "/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp");
	    return mapping.findForward("CANCEL");
    }

}
