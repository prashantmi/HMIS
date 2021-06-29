package opd.master.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import opd.master.controller.fb.HospitalDiseaseMasterFB;
import opd.master.controller.util.HospitalDiseaseMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.OpdConfig;

public class HospitalDiseaseMasterACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalDiseaseMasterFB fb=(HospitalDiseaseMasterFB)form;
		fb.setDeskmode("DISEASE");
		
		return this.DISEASE(mapping,form,request,response);		
	}
	
	public ActionForward DISEASE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalDiseaseMasterFB fb=(HospitalDiseaseMasterFB)form;
		WebUTIL.refreshTransState(request);
		HttpSession session= request.getSession();
		session.setAttribute("mstFilename","HospitalDiseaseMaster");
		HospitalDiseaseMasterUTIL.setTabSequence(OpdConfig.HOSPITALDISEASE_MASTER_DISEASE,request);
		if((fb.getHmode()!=null) && (fb.getHmode().equals("MODIFYSAVE") || fb.getHmode().equals("ADDSAVE")) )
			return mapping.findForward(fb.getHmode()+"TILE");
		else
		return mapping.findForward("DISEASE");
	}
	
	public ActionForward SUBDISEASE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HospitalDiseaseMasterFB fb=(HospitalDiseaseMasterFB)form;
		WebUTIL.refreshTransState(request);
		HttpSession session= request.getSession();
		session.setAttribute("mstFilename","HospitalSubDiseaseMaster");
		HospitalDiseaseMasterUTIL.setTabSequence(OpdConfig.HOSPITALDISEASE_MASTER_SUBDISEASE,request);
		if((fb.getHmode()!=null) && (fb.getHmode().equals("MODIFYSAVE") || fb.getHmode().equals("ADDSAVE")) )
			return mapping.findForward(fb.getHmode()+"TILE");
		else
		return mapping.findForward("SUBDISEASE");
	}
}
