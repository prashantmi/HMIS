package enquiry.setup.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.setup.controller.fb.HospitalFacilityMasterFB;
import enquiry.setup.controller.util.HospitalFacilityMasterUTL;

public class HospitalFacilityMasterACTION extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}

	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityMasterFB fb = (HospitalFacilityMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		HospitalFacilityMasterUTL.getAllHospitalFacilityList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityMasterFB fb = (HospitalFacilityMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsValid("-1");
		return mapping.findForward("ADD");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityMasterFB fb = (HospitalFacilityMasterFB) form;
		HospitalFacilityMasterUTL.saveHospitalFacility(fb, request);		
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityMasterFB fb = (HospitalFacilityMasterFB) form;
		HospitalFacilityMasterUTL.getHospitalFacility(fb, request);		
		return mapping.findForward("ADD");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityMasterFB fb = (HospitalFacilityMasterFB) form;
		HospitalFacilityMasterUTL.modifyHospitalFacility(fb, request);		
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityMasterFB fb = (HospitalFacilityMasterFB) form;
		HospitalFacilityMasterUTL.getHospitalFacility(fb, request);		
		return mapping.findForward("ADD");
	}
	
	public ActionForward DELETE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityMasterFB fb = (HospitalFacilityMasterFB) form;
		HospitalFacilityMasterUTL.deleteHospitalFacility(fb, request);		
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CHANGEORDER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityMasterFB fb = (HospitalFacilityMasterFB) form;
		HospitalFacilityMasterUTL.setHospitalFacilityList(fb, request);		
		return mapping.findForward("CHANGEORDER");
	}
	
	public ActionForward SAVECHANGEDORDER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityMasterFB fb = (HospitalFacilityMasterFB) form;
		HospitalFacilityMasterUTL.changeDisplayOrder(fb, request);		
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		HospitalFacilityMasterFB fb = (HospitalFacilityMasterFB) form;
		return mapping.findForward("CANCEL");
	}
	
	

}
