package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdChangeTreatmentCategoryFB;
import opd.transaction.controller.util.OpdChangeTreatmentCategoryUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdChangeTreatmentCategoryACTION extends CSRFGardTokenAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}

	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		generateToken(request);
		 //HttpSession ses=WebUTIL.getSession(request);
		 OpdChangeTreatmentCategoryFB fb = (OpdChangeTreatmentCategoryFB) form;
		 fb.reset(mapping,request);
		 //WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		 OpdChangeTreatmentCategoryUTIL.setSysdate(request);
		 OpdChangeTreatmentCategoryUTIL.setPatientDtlByCrno(fb,request);
		 
		 return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdChangeTreatmentCategoryFB fb = (OpdChangeTreatmentCategoryFB) form;
		
		OpdChangeTreatmentCategoryUTIL.setPatientDtlByCrno(fb,request);
		return mapping.findForward("SAME");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		OpdChangeTreatmentCategoryFB fb = (OpdChangeTreatmentCategoryFB) form;
		OpdChangeTreatmentCategoryUTIL.saveSecondaryCategoryChange(fb, request);
		return this.NEW(mapping, form, request, response);
	}



}
