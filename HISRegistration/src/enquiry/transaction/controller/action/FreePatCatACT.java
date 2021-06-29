package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import enquiry.transaction.controller.fb.FreePatientListEnquiryFB;
import enquiry.transaction.controller.util.FreePatCatUTL;

public class FreePatCatACT extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		FreePatientListEnquiryFB fb = (FreePatientListEnquiryFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		FreePatCatUTL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("CANCEL");
	}

}
