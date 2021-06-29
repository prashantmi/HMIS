package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ProfileGroupMasterFB;
import opd.master.controller.util.ProfileGroupMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ProfileGroupMasterACTION extends CSRFGardTokenAction 
{

	public ActionForward unspecified(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward ADD(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		generateToken(request);
		ProfileGroupMasterFB fb=(ProfileGroupMasterFB)form;
		WebUTIL.refreshTransState(request);
		//fb.reset(mapping, request);
		ProfileGroupMasterUTIL.fetchProfileGroupEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ProfileGroupMasterFB fb=(ProfileGroupMasterFB)form;
		boolean flag=ProfileGroupMasterUTIL.saveProfileGroupDetail(fb, request);
		if(flag)
			fb.reset(mapping, request);
		fb.setHmode("ADD");
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileGroupMasterFB fb=(ProfileGroupMasterFB)form;
		WebUTIL.refreshTransState(request);
		ProfileGroupMasterUTIL.fetchProfileGroupDetailModify(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ProfileGroupMasterFB fb=(ProfileGroupMasterFB)form;
		boolean flag=ProfileGroupMasterUTIL.modifySave(fb, request);
		if(!flag){
			fb.setHmode("MODIFY");
			return mapping.findForward("ADD");
		}	
		else
			return mapping.findForward("CANCEL");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ProfileGroupMasterFB fb=(ProfileGroupMasterFB)form;
		ProfileGroupMasterUTIL.fetchProfileGroupDetailModify(fb, request);
		fb.setHmode("VIEW");
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return mapping.findForward("CANCEL");
	}
	
	
}
