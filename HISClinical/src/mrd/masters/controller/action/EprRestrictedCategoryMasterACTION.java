package mrd.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.masters.controller.fb.EprRestrictedCategoryMasterFB;
import mrd.masters.controller.util.EprRestrictedCategoryMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class EprRestrictedCategoryMasterACTION extends CSRFGardTokenAction 
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		generateToken(request);
		EprRestrictedCategoryMasterFB fb=(EprRestrictedCategoryMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		EprRestrictedCategoryMasterUTIL.getEprRestrictedCategoryEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		EprRestrictedCategoryMasterFB fb=(EprRestrictedCategoryMasterFB)form;
		EprRestrictedCategoryMasterUTIL.save(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return mapping.findForward("CANCEL");
	}
	

	
}
