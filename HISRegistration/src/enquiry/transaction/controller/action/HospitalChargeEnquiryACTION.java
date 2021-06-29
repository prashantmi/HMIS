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
import enquiry.transaction.controller.util.HospitalChargeEnquiryUTL;

public class HospitalChargeEnquiryACTION extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.DIRECTCALL(mapping,form,request,response);		
	}

	public ActionForward DIRECTCALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalChargeEnquiryFB fb = (HospitalChargeEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		HospitalChargeEnquiryUTL.getAllHospitalTarrifList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalChargeEnquiryFB fb = (HospitalChargeEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DESK");
		HospitalChargeEnquiryUTL.getAllHospitalTarrifList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCHARGEDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalChargeEnquiryFB fb = (HospitalChargeEnquiryFB) form;
		HospitalChargeEnquiryUTL.getChargeDetailByTariff(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalChargeEnquiryFB fb = (HospitalChargeEnquiryFB) form;
		fb.setHmode("");
		Status status=new Status();
		status.add(Status.LIST);
		WebUTIL.setStatus(request, status);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETTARIFFBYGROUPID(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalChargeEnquiryFB fb = (HospitalChargeEnquiryFB) form;
		HospitalChargeEnquiryUTL.getTariffByGroupId(fb, request);
		//fb.reset(mapping, request);
		fb.setCurrentPage(1);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETTARIFFBYNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalChargeEnquiryFB fb = (HospitalChargeEnquiryFB) form;
		HospitalChargeEnquiryUTL.searchTariffByName(fb, request);
		fb.reset(mapping, request);
		//fb.setCurrentPage(1);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETSELECTEDHOSPITALTARIFFLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalChargeEnquiryFB fb = (HospitalChargeEnquiryFB) form;
		HospitalChargeEnquiryUTL.getSelectedHospitalTarrifList(fb, request);
		/*if("1".equals(fb.getStrModeGrpTariff()))
			HospitalChargeEnquiryUTL.getTariffByGroupId(fb, request,false);
		else
			HospitalChargeEnquiryUTL.searchTariffByName(fb, request, false);*/
		//fb.reset(mapping, request);
		fb.setCurrentPage(1);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalChargeEnquiryFB fb = (HospitalChargeEnquiryFB) form;
		return mapping.findForward("CANCEL");
	}
	
	

}
