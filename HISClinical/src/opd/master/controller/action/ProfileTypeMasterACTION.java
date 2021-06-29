package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ProfileTypeMasterFB;
import opd.master.controller.util.ProfileTypeMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ProfileTypeMasterACTION extends CSRFGardTokenAction 
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward ADD(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		generateToken(request);
		ProfileTypeMasterFB fb=(ProfileTypeMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ProfileTypeMasterFB fb=(ProfileTypeMasterFB)form;
		boolean flag=ProfileTypeMasterUTIL.saveProfileType(fb, request);
		if(flag)
			fb.reset(mapping, request);
		fb.setHmode("ADD");
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileTypeMasterFB fb=(ProfileTypeMasterFB)form;
		WebUTIL.refreshTransState(request);
		ProfileTypeMasterUTIL.getModifyDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ProfileTypeMasterFB fb=(ProfileTypeMasterFB)form;
		boolean flag=ProfileTypeMasterUTIL.modifySave(fb, request);
		if(!flag){
			fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}	
		else
			return mapping.findForward("LIST");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileTypeMasterFB fb=(ProfileTypeMasterFB)form;
		ProfileTypeMasterUTIL.getModifyDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return mapping.findForward("LIST");
	}
	

	
}
