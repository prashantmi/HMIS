package enquiry.transaction.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.HospitalConsultantEnquiryFB;
import enquiry.transaction.controller.util.HospitalConsultantEnquiryUTIL;


public class HospitalConsultantEnquiryACTION extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.DIRECTCALL(mapping,form,request,response);		
	}
	
	public ActionForward DIRECTCALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalConsultantEnquiryFB fb = (HospitalConsultantEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		HospitalConsultantEnquiryUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalConsultantEnquiryFB fb = (HospitalConsultantEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		HospitalConsultantEnquiryUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CONSULTANTDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalConsultantEnquiryFB fb = (HospitalConsultantEnquiryFB) form;
		HospitalConsultantEnquiryUTIL.getConsultantDetails(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ONCHANGEHOSPITAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalConsultantEnquiryFB fb = (HospitalConsultantEnquiryFB) form;
		String strIsDirectCall=fb.getIsDirectCall();
		fb.reset(mapping, request);
		HospitalConsultantEnquiryUTIL.onChangeHospital(fb,request);
		fb.setIsDirectCall(strIsDirectCall);
		fb.setIsPageList("0");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHBYNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalConsultantEnquiryFB fb = (HospitalConsultantEnquiryFB) form;
		HospitalConsultantEnquiryUTIL.searchConsultantDetailsByName(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHBYDEPT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalConsultantEnquiryFB fb = (HospitalConsultantEnquiryFB) form;
		HospitalConsultantEnquiryUTIL.searchConsultantDetailsByDept(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHCONSULTANTUNITDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalConsultantEnquiryFB fb = (HospitalConsultantEnquiryFB) form;
		HospitalConsultantEnquiryUTIL.getConsultantEnquiryUnitDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalConsultantEnquiryFB fb = (HospitalConsultantEnquiryFB) form;
		fb.setHmode("");
		Status status=new Status();
		status.add(Status.LIST);
		WebUTIL.setStatus(request, status);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("inside CANCEL ACTION");
	    WebUTIL.refreshTransState(request);	
	   /* HospitalConsultantEnquiryFB fb = (HospitalConsultantEnquiryFB) form;
	    fb.reset(mapping, request);*/
		return mapping.findForward("CANCEL");
    }

}
