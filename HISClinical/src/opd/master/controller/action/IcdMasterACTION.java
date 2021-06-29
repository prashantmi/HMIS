package opd.master.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import opd.master.controller.fb.IcdMasterFB;
import opd.master.controller.util.IcdMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.OpdConfig;

public class IcdMasterACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		IcdMasterFB fb=(IcdMasterFB)form;
		fb.setDeskmode("GROUP");
		
		return this.GROUP(mapping,form,request,response);		
	}
	
	public ActionForward GROUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		IcdMasterFB fb=(IcdMasterFB)form;
		WebUTIL.refreshTransState(request);
		HttpSession session= request.getSession();
		session.setAttribute("mstFilename","OpdIcdGroupMaster");
		IcdMasterUTIL.setTabSequence(OpdConfig.OPD_DESKMODE_ICDMASTER_GROUP,request);
		if((fb.getHmode()!=null) && (fb.getHmode().equals("MODIFYSAVE") || fb.getHmode().equals("ADDSAVE")) )
			return mapping.findForward(fb.getHmode()+"TILE");
		else
		return mapping.findForward("GROUP");
	}
	
	public ActionForward SUBGROUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		IcdMasterFB fb=(IcdMasterFB)form;
		WebUTIL.refreshTransState(request);
		HttpSession session= request.getSession();
		session.setAttribute("mstFilename","OpdIcdSubGroupMaster");
		IcdMasterUTIL.setTabSequence(OpdConfig.OPD_DESKMODE_ICDMASTER_SUBGROUP,request);
		if((fb.getHmode()!=null) && (fb.getHmode().equals("MODIFYSAVE") || fb.getHmode().equals("ADDSAVE")) )
			return mapping.findForward(fb.getHmode()+"TILE");
		else
		return mapping.findForward("SUBGROUP");
	}
	
	public ActionForward DISEASE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		IcdMasterFB fb=(IcdMasterFB)form;
		WebUTIL.refreshTransState(request);
		HttpSession session= request.getSession();
		session.setAttribute("mstFilename","OpdIcdDiseaseMaster");
		IcdMasterUTIL.setTabSequence(OpdConfig.OPD_DESKMODE_ICDMASTER_DISEASE,request);
		if((fb.getHmode()!=null) && (fb.getHmode().equals("MODIFYSAVE") || fb.getHmode().equals("ADDSAVE")) )
			return mapping.findForward(fb.getHmode()+"TILE");
		else
		return mapping.findForward("DISEASE");
	}
	
	public ActionForward SUBDISEASE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		IcdMasterFB fb=(IcdMasterFB)form;
		WebUTIL.refreshTransState(request);
		HttpSession session= request.getSession();
		session.setAttribute("mstFilename","OpdSubDiseaseMaster");
		IcdMasterUTIL.setTabSequence(OpdConfig.OPD_DESKMODE_ICDMASTER_SUBDISEASE,request);
		if((fb.getHmode()!=null) && (fb.getHmode().equals("MODIFYSAVE") || fb.getHmode().equals("ADDSAVE")) )
			return mapping.findForward(fb.getHmode()+"TILE");
		else
		return mapping.findForward("SUBDISEASE");
	}
}
