package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.HospitalChargeEnquiryFB;
import enquiry.transaction.controller.fb.HospitalDepartmentEnquiryFB;
import enquiry.transaction.controller.util.HospitalDepartmentEnquiryUTIL;



public class HospitalDepartmentEnquiryACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.DIRECTCALL(mapping,form,request,response);		
	}
	
	public ActionForward DIRECTCALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalDepartmentEnquiryFB fb= (HospitalDepartmentEnquiryFB) form;
		fb.reset(mapping, request);
		fb.setSearchText("");
		fb.setIsDirectCall("DIRECT");
		WebUTIL.refreshTransState(request);
		HospitalDepartmentEnquiryUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalDepartmentEnquiryFB fb= (HospitalDepartmentEnquiryFB) form;
		fb.reset(mapping, request);
		fb.setSearchText("");
		fb.setIsDirectCall("DIRECT");
		WebUTIL.refreshTransState(request);
		HospitalDepartmentEnquiryUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DEPTDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalDepartmentEnquiryFB fb= (HospitalDepartmentEnquiryFB) form;
		fb.setSearchText("");
		fb.setIsDirectCall("DESK");
		HospitalDepartmentEnquiryUTIL.getDepartmentDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDEPTBYNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalDepartmentEnquiryFB fb= (HospitalDepartmentEnquiryFB) form;
		HospitalDepartmentEnquiryUTIL.getDeptByName(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UNITDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalDepartmentEnquiryFB fb= (HospitalDepartmentEnquiryFB) form;
		HospitalDepartmentEnquiryUTIL.getUnitDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward  BEDSTATUS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			{
    	HospitalDepartmentEnquiryFB fb = (HospitalDepartmentEnquiryFB) form;
		HospitalDepartmentEnquiryUTIL.beddetail(fb,request,response);	
		return mapping.findForward("BEDSTATUS");
	}
	public ActionForward BEDDETAILS(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		HospitalDepartmentEnquiryFB fb = (HospitalDepartmentEnquiryFB) form;
		HospitalDepartmentEnquiryUTIL.beddetail(fb,request,response);
		return null;
	}
	
public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
	HospitalDepartmentEnquiryFB fb = (HospitalDepartmentEnquiryFB) form;
		return mapping.findForward("CANCEL");
	}

}
