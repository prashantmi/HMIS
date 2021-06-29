package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.OperationTheaterEnquiryFB;
import enquiry.transaction.controller.util.OperationTheaterEnquiryUTIL;

public class OperationTheaterEnquiryACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		OperationTheaterEnquiryFB fb= (OperationTheaterEnquiryFB) form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		OperationTheaterEnquiryUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETOTDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		OperationTheaterEnquiryFB fb= (OperationTheaterEnquiryFB) form;
		OperationTheaterEnquiryUTIL.getOperationTheaterDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		OperationTheaterEnquiryFB fb = (OperationTheaterEnquiryFB) form;
		return mapping.findForward("CANCEL");
	}

}
