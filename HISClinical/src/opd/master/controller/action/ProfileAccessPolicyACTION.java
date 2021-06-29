package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ProfileAccessPolicyFB;
import opd.master.controller.util.ProfileAccessPolicyUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ProfileAccessPolicyACTION extends CSRFGardTokenAction 
{
	
public ActionForward unspecified(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward ADD(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		generateToken(request);
		ProfileAccessPolicyFB fb=(ProfileAccessPolicyFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		ProfileAccessPolicyUTIL.fetchProfileAccessPolicyEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDEPTUNIT(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileAccessPolicyFB fb=(ProfileAccessPolicyFB)form;
		ProfileAccessPolicyUTIL.getDeptUnitOfProfileAccess(fb, request);
		fb.setHmode("ADD");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ProfileAccessPolicyFB fb=(ProfileAccessPolicyFB)form;
		boolean flag=ProfileAccessPolicyUTIL.saveProfileAccessPolicy(fb, request);
		if(flag)
			fb.reset(mapping, request);
		fb.setHmode("ADD");
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileAccessPolicyFB fb=(ProfileAccessPolicyFB)form;
		WebUTIL.refreshTransState(request);
		ProfileAccessPolicyUTIL.fetchProfileAccessPolicyModify(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ProfileAccessPolicyFB fb=(ProfileAccessPolicyFB)form;
		boolean flag=ProfileAccessPolicyUTIL.modifySave(fb, request);
		if(!flag){
			fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}	
		else
			return mapping.findForward("CANCEL");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileAccessPolicyFB fb=(ProfileAccessPolicyFB)form;
		ProfileAccessPolicyUTIL.fetchProfileAccessPolicyModify(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return mapping.findForward("CANCEL");
	}

}
