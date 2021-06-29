
package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.fb.BloodStockEnquiryFB;
import enquiry.transaction.controller.util.BloodStockEnquiryUTIL;


public class BloodStockEnquiryAction extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.ALL(mapping,form,request,response);		
	}
	
	public ActionForward ALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		BloodStockEnquiryFB fb = (BloodStockEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setChoice(enquiryConfig.ALL_BLOOD_COMPONENT_STOCK_ENQUIRY);
		BloodStockEnquiryUTIL.getBloodStockEnquiry(fb,request);
		return mapping.findForward("NEW");
	}
	
public ActionForward AVAILABLE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		BloodStockEnquiryFB fb = (BloodStockEnquiryFB) form;
		fb.setChoice(enquiryConfig.IN_STOCK_BLOOD_COMPONENT_STOCK_ENQUIRY);
		BloodStockEnquiryUTIL.getBloodStockEnquiry(fb,request);
		return mapping.findForward("NEW");
	}

}
