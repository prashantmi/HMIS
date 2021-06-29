package enquiry.transaction.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.HospitalChargeEnquiryFB;
import enquiry.transaction.controller.fb.HospitalLabEnquiryFB;
import enquiry.transaction.controller.util.HospitalLabEnquiryUTIL;

public class HospitalLabEnquiryACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.DIRECTCALL(mapping,form,request,response);		
	}
	
	public ActionForward DIRECTCALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalLabEnquiryFB fb= (HospitalLabEnquiryFB) form;
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		WebUTIL.refreshTransState(request);
		HospitalLabEnquiryUTIL.getEssentials(fb,request);
		fb.setIsFinalCancelReqd("1");
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalLabEnquiryFB fb= (HospitalLabEnquiryFB) form;
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		WebUTIL.refreshTransState(request);
		HospitalLabEnquiryUTIL.getEssentials(fb,request);
		fb.setIsFinalCancelReqd("1");
		return mapping.findForward("NEW");
	}
	
	public ActionForward ONCHANGEHOSPITAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalLabEnquiryFB fb= (HospitalLabEnquiryFB) form;
		String strIsDirectCall=fb.getIsDirectCall();
		fb.reset(mapping, request);
		HospitalLabEnquiryUTIL.onChangeHospital(fb,request);
		fb.setIsDirectCall(strIsDirectCall);
		fb.setIsFinalCancelReqd("1");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETLABTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalLabEnquiryFB fb= (HospitalLabEnquiryFB) form;
		//fb.setIsDirectCall("DESK");
		HospitalLabEnquiryUTIL.getLabTests(fb,request);
		fb.setIsFinalCancelReqd("0");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETTESTSBYNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalLabEnquiryFB fb= (HospitalLabEnquiryFB) form;
		//fb.setIsDirectCall("DESK");
		HospitalLabEnquiryUTIL.getTestByName(fb,request);
		fb.setCurrentPage(1);
		fb.setIsFinalCancelReqd("0");
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalLabEnquiryFB fb = (HospitalLabEnquiryFB) form;
		fb.setHmode("");
		Status status=new Status();
		status.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, status);
		return mapping.findForward("NEW");
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		HospitalLabEnquiryFB fb = (HospitalLabEnquiryFB) form;
		return mapping.findForward("CANCEL");
	}

}
