package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ProfileRestrictedCategoryMasterFB;
import opd.master.controller.util.ProfileRestrictedCategoryMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ProfileRestrictedCategoryMasterACTION extends CSRFGardTokenAction 
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward ADD(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		generateToken(request);
		ProfileRestrictedCategoryMasterFB fb=(ProfileRestrictedCategoryMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		ProfileRestrictedCategoryMasterUTIL.fetchRestrictedCategoryEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATCATEGORY(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileRestrictedCategoryMasterFB fb=(ProfileRestrictedCategoryMasterFB)form;
		ProfileRestrictedCategoryMasterUTIL.getPatientCategoryForProfileType(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ProfileRestrictedCategoryMasterFB fb=(ProfileRestrictedCategoryMasterFB)form;
		ProfileRestrictedCategoryMasterUTIL.saveProfileRestrictedCategory(fb, request);
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileRestrictedCategoryMasterFB fb=(ProfileRestrictedCategoryMasterFB)form;
		WebUTIL.refreshTransState(request);
		ProfileRestrictedCategoryMasterUTIL.fetchPatientCatModify(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ProfileRestrictedCategoryMasterFB fb=(ProfileRestrictedCategoryMasterFB)form;
		boolean flag=ProfileRestrictedCategoryMasterUTIL.modifySave(fb, request);
		if(!flag){
			fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}	
		else
			return mapping.findForward("CANCEL");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileRestrictedCategoryMasterFB fb=(ProfileRestrictedCategoryMasterFB)form;
		ProfileRestrictedCategoryMasterUTIL.fetchPatientCatModify(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return mapping.findForward("LIST");
	}
	

	
}
