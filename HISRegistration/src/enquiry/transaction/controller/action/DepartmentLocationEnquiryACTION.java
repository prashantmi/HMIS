package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.DepartmentLocationEnquiryFB;
import enquiry.transaction.controller.fb.PatientEnquiryFB;
import enquiry.transaction.controller.util.DepartmentLocationEnquiryUTIL;
import enquiry.transaction.controller.util.PatientEnquiryUTIL;

public class DepartmentLocationEnquiryACTION extends DispatchAction {
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		DepartmentLocationEnquiryFB fb = (DepartmentLocationEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		DepartmentLocationEnquiryUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		DepartmentLocationEnquiryFB fb = (DepartmentLocationEnquiryFB) form;
		DepartmentLocationEnquiryUTIL.searchDepartmentLocation(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("inside CANCEL ACTION");
	    WebUTIL.refreshTransState(request);		
		return mapping.findForward("CANCEL");
    }

}
