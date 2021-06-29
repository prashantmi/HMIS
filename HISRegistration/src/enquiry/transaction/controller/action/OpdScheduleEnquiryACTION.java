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
import enquiry.transaction.controller.fb.OpdScheduleEnquiryFB;
import enquiry.transaction.controller.util.OpdScheduleEnquiryUTL;

public class OpdScheduleEnquiryACTION extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.DIRECTCALL(mapping,form,request,response);		
	}

	public ActionForward DIRECTCALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdScheduleEnquiryFB fb = (OpdScheduleEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		OpdScheduleEnquiryUTL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdScheduleEnquiryFB fb = (OpdScheduleEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		OpdScheduleEnquiryUTL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETUNITDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdScheduleEnquiryFB fb = (OpdScheduleEnquiryFB) form;
		OpdScheduleEnquiryUTL.getUnitWorkingDetail(fb,request);
		fb.setDepartmentCode("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETSPECIALCLINICDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdScheduleEnquiryFB fb = (OpdScheduleEnquiryFB) form;
		OpdScheduleEnquiryUTL.getSpecialClinicWorkingDetail(fb, request);
		fb.setDepartmentUnitCode("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCONSULTANT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdScheduleEnquiryFB fb = (OpdScheduleEnquiryFB) form;
		//WebUTIL.refreshTransState(request);
		OpdScheduleEnquiryUTL.getConsultant(fb, request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward GETDEPTBYNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdScheduleEnquiryFB fb = (OpdScheduleEnquiryFB) form;
		OpdScheduleEnquiryUTL.getDeptByName(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdScheduleEnquiryFB fb = (OpdScheduleEnquiryFB) form;
		fb.setHmode("");
		Status status=new Status();
		status.add(Status.LIST);
		WebUTIL.setStatus(request, status);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdScheduleEnquiryFB fb = (OpdScheduleEnquiryFB) form;
		return mapping.findForward("CANCEL");
	}
	
	

}
