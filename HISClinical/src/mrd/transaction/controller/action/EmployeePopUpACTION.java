package mrd.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.EmployeePopUpFB;
import mrd.transaction.controller.utl.EmployeePopUpUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class EmployeePopUpACTION extends DispatchAction {
	
	protected ActionForward unspecified(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		return this.NEW(arg0, arg1, arg2, arg3);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		EmployeePopUpFB fb=(EmployeePopUpFB)form;
		fb.reset(mapping,request);
		EmployeePopUpUTIL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		EmployeePopUpFB fb = (EmployeePopUpFB) form;
		EmployeePopUpUTIL.searchEmployeeDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	//Added by Vasu on 13.March.2019
	public ActionForward DISCHARGEPREPAREDBY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		EmployeePopUpFB fb=(EmployeePopUpFB)form;
		fb.reset(mapping,request);
		EmployeePopUpUTIL.getEssentials(fb,request);
		return mapping.findForward("DISCHARGEPREPAREDBY");
	}

}
