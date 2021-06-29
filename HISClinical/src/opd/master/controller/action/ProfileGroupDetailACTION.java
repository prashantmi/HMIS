package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ProfileGroupDetailFB;
import opd.master.controller.util.ProfileGroupDetailUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ProfileGroupDetailACTION extends CSRFGardTokenAction 
{

public ActionForward unspecified(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward ADD(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		generateToken(request);
		ProfileGroupDetailFB fb=(ProfileGroupDetailFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		ProfileGroupDetailUTIL.fetchProfileGroupDetailEssentials(fb,request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ProfileGroupDetailFB fb=(ProfileGroupDetailFB)form;
		boolean flag=ProfileGroupDetailUTIL.saveProfileGroupAccessPrivDetail(fb, request);
		
		if(flag)
			fb.reset(mapping, request);
		fb.setHmode("ADD");
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileGroupDetailFB fb=(ProfileGroupDetailFB)form;
		WebUTIL.refreshTransState(request);
		ProfileGroupDetailUTIL.fetchProfileGroupDetailAccessModify(fb, request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ProfileGroupDetailFB fb=(ProfileGroupDetailFB)form;
		boolean flag=ProfileGroupDetailUTIL.modifySave(fb, request);
		if(!flag){
			fb.setHmode("MODIFY");
			return mapping.findForward("ADD");
		}	
		else
			return mapping.findForward("CANCEL");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileGroupDetailFB fb=(ProfileGroupDetailFB)form;
		ProfileGroupDetailUTIL.fetchProfileGroupDetailAccessModify(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHUSER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ProfileGroupDetailFB fb = (ProfileGroupDetailFB) form;
		ProfileGroupDetailUTIL.setSearchUsers(fb, request);
		 fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDACCESSPRIVUSER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ProfileGroupDetailFB fb = (ProfileGroupDetailFB) form;
		ProfileGroupDetailUTIL.addSearchUsers(fb, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEUSERPRIV(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ProfileGroupDetailFB fb = (ProfileGroupDetailFB) form;
		ProfileGroupDetailUTIL.removeUserPriv(fb, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return mapping.findForward("CANCEL");
	}
}
