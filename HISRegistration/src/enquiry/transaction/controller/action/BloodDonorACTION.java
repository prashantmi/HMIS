package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.fb.BloodDonorFB;
import enquiry.transaction.controller.util.BloodDonorUTIL;


public class BloodDonorACTION extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.ALL(mapping,form,request,response);		
	}
	
	public ActionForward ALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		BloodDonorFB fb = (BloodDonorFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setEmergencyCall(enquiryConfig.EMERGENCY_CALL_NO);
		BloodDonorUTIL.getBloodDonorGroups(fb,request);
		return mapping.findForward("NEW");
	}
	
public ActionForward ENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		BloodDonorFB fb = (BloodDonorFB) form;
		BloodDonorUTIL.getBloodDonorEnquiryDetails(fb,request);
		return mapping.findForward("NEW");
	}

}
