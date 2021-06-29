package enquiry.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.HospitalWardEnquiryFB;
import enquiry.transaction.controller.fb.OpdScheduleEnquiryFB;
import enquiry.transaction.controller.util.HospitalWardEnquiryUTIL;
import enquiry.transaction.controller.util.OpdScheduleEnquiryUTL;

public class HospitalWardEnquiryACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.DIRECTCALL(mapping,form,request,response);		
	}
	
	public ActionForward DIRECTCALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalWardEnquiryFB fb= (HospitalWardEnquiryFB) form;
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		WebUTIL.refreshTransState(request);
		HospitalWardEnquiryUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalWardEnquiryFB fb= (HospitalWardEnquiryFB) form;
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		WebUTIL.refreshTransState(request);
		HospitalWardEnquiryUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETWARDDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalWardEnquiryFB fb= (HospitalWardEnquiryFB) form;
		boolean flag=HospitalWardEnquiryUTIL.getWardDetail(fb,request,response);
		if(flag)
			return mapping.findForward("NEW");
		else{
			return mapping.findForward("BEDSTATUS");
		}	
			//return mapping.findForward("NEW");
	}
	
	public ActionForward ONCHANGEHOSPITAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalWardEnquiryFB fb= (HospitalWardEnquiryFB) form;
		String strIsDirectCall=fb.getIsDirectCall();
		fb.reset(mapping, request);
		HospitalWardEnquiryUTIL.onChangeHospital(fb,request);
		fb.setIsDirectCall(strIsDirectCall);
		fb.setIsPageList("0");
		return mapping.findForward("NEW");
	}
	
	/*public ActionForward  BEDSTATUS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			{
    	HospitalWardEnquiryFB fb = (HospitalWardEnquiryFB) form;
		HospitalWardEnquiryUTIL.beddetail(fb,request,response);	
		return mapping.findForward("BEDSTATUS");
	}*/
	public ActionForward BEDDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		HospitalWardEnquiryFB fb = (HospitalWardEnquiryFB) form;
		//HospitalWardEnquiryUTIL.beddetail(fb,request,response);
		HospitalWardEnquiryUTIL.bedDetail(fb,request,response);
		return mapping.findForward("BEDSTATUS");
	}
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	{
		HospitalWardEnquiryFB fb = (HospitalWardEnquiryFB) form;
		return mapping.findForward("CANCEL");
	}

}
