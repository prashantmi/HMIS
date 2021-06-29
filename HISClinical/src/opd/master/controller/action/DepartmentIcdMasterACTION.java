package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.master.controller.fb.DepartmentIcdMasterFB;
import opd.master.controller.util.DepartmentIcdMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DepartmentIcdMasterACTION extends CSRFGardTokenAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		generateToken(request);
		DepartmentIcdMasterFB fb= (DepartmentIcdMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.setChoice(OpdConfig.CHOICE_DEPARTMENT);
		DepartmentIcdMasterUTIL.setDeptIcdEssential(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		DepartmentIcdMasterFB fb= (DepartmentIcdMasterFB)form;
		WebUTIL.refreshTransState(request);
		DepartmentIcdMasterUTIL.getDeptIcdDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		DepartmentIcdMasterFB fb= (DepartmentIcdMasterFB)form;
		DepartmentIcdMasterUTIL.displayListForAdd(fb,request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward DELETE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		DepartmentIcdMasterFB fb= (DepartmentIcdMasterFB)form;
		DepartmentIcdMasterUTIL.deleteDeptIcdCode(fb,request);
		return mapping.findForward("DELETE");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		DepartmentIcdMasterFB fb= (DepartmentIcdMasterFB)form;
		DepartmentIcdMasterUTIL.saveDeptIcdCode(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		DepartmentIcdMasterFB fb= (DepartmentIcdMasterFB)form;
		DepartmentIcdMasterUTIL.displayListForRemove(fb,request);
		return mapping.findForward("DELETE");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		DepartmentIcdMasterFB fb= (DepartmentIcdMasterFB)form;
		DepartmentIcdMasterUTIL.search(fb,request);
		return mapping.findForward("NEW");
	}
	
public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("CANCEL");
	}

	

	

}
