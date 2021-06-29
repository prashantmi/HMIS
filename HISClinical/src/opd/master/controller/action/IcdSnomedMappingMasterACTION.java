package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.HospitalDiseaseMasterFB;
import opd.master.controller.fb.icdSnomedMappingMasterFB;
import opd.master.controller.util.HospitalDiseaseMasterUTIL;
import opd.master.controller.util.IcdSnomedMappingMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IcdSnomedMappingMasterACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{

		return this.ADD(mapping, form, request, response);
	}
	public ActionForward ADD(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		generateToken(request);
		icdSnomedMappingMasterFB fb=(icdSnomedMappingMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		IcdSnomedMappingMasterUTIL.getIcdSnomedEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	public ActionForward SAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		generateToken(request);

		System.out.println("Inside Hospital Disease Save");
		icdSnomedMappingMasterFB fb=(icdSnomedMappingMasterFB)form;
		boolean flag=IcdSnomedMappingMasterUTIL.saveIcdSnomedMapping(fb, request);
		if(flag)
			fb.reset(mapping, request);
		fb.setHmode("ADD");
		return mapping.findForward("NEW");
	}
	

	public ActionForward MODIFY(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		icdSnomedMappingMasterFB fb=(icdSnomedMappingMasterFB)form;
		return mapping.findForward("NEW");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		icdSnomedMappingMasterFB fb=(icdSnomedMappingMasterFB)form;
		IcdSnomedMappingMasterUTIL.fetchIcdSnomedModify(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return mapping.findForward("LIST");
	}
	

}