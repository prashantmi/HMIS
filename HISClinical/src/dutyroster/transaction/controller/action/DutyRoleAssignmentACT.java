package dutyroster.transaction.controller.action;


import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import dutyroster.transaction.controller.fb.DutyRoleAssignmentFB;
import dutyroster.transaction.controller.utl.DutyRoleAssignmentUTL;

public class DutyRoleAssignmentACT extends CSRFGardTokenAction {

	/**
	 * the action IS CALLED BY DEFAULT AND IT REDIRECTS TO new 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "NEW"
	 */
	//*
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		
		return this.NEW(mapping,form,request,response);		
	}
	
	/**
	 * the action called when the page  is opend initially for Duty Area Employee Master
	 * it also gets the values of different Types of Shifts from the HDRT_ROSTER_CAT_MST
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		generateToken(request);
		DutyRoleAssignmentFB fb = (DutyRoleAssignmentFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		DutyRoleAssignmentUTL.getRosterForRoleAssignment(request,fb);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward GET_SHIFTS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		DutyRoleAssignmentFB fb = (DutyRoleAssignmentFB) form;
		DutyRoleAssignmentUTL.getShiftAndAreaForRoster(request,fb);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GET_EMPLOYEES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		DutyRoleAssignmentFB fb = (DutyRoleAssignmentFB) form;
		DutyRoleAssignmentUTL.getEmployeesForRosterShift(request,fb);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response) throws Exception
	{
		validateToken(request,_response);
		DutyRoleAssignmentFB fb = (DutyRoleAssignmentFB) form;
		DutyRoleAssignmentUTL.saveEmployeeRoleDetail(request,fb);
		return this.NEW(mapping, form, request, _response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		return mapping.findForward("CANCEL");
	}

}
