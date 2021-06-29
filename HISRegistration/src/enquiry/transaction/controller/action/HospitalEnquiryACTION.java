
package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.HospitalEnquiryFB;
import enquiry.transaction.controller.util.HospitalEnquiryUTIL;



public class HospitalEnquiryACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.ISDIRECTCALL(mapping,form,request,response);		
	}
	
	public ActionForward ISDIRECTCALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalEnquiryFB fb= (HospitalEnquiryFB) form;
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		WebUTIL.refreshTransState(request);
		HospitalEnquiryUTIL.getHospitalEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalEnquiryFB fb= (HospitalEnquiryFB) form;
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		WebUTIL.refreshTransState(request);
		HospitalEnquiryUTIL.getHospitalEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	public ActionForward ONHOSPITALCHANGE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalEnquiryFB fb= (HospitalEnquiryFB) form;
		HospitalEnquiryUTIL.setHospitalEssentialsOnHospitalComboChange(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
	    WebUTIL.refreshTransState(request);
	    WebUTIL.setValueForModuleSwitching(request,"", "/startup/initPage.jsp");
	    return mapping.findForward("CANCEL");
    }

}
