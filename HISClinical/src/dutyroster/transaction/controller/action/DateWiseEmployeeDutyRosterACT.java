
package dutyroster.transaction.controller.action;


import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction; 
 



import dutyroster.transaction.controller.fb.DateWiseEmployeeDutyRosterFB;
import dutyroster.transaction.controller.fb.LocationDutyRosterFB;
import dutyroster.transaction.controller.utl.DateWiseEmployeeDutyRosterUTL;
import dutyroster.transaction.controller.utl.LocationDutyRosterUTL;

public class DateWiseEmployeeDutyRosterACT extends CSRFGardTokenAction
{
	

	/**
	 * the action IS CALLED BY DEFAULT AND IT REDIRECTS TO new 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
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
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		DateWiseEmployeeDutyRosterFB _fb = (DateWiseEmployeeDutyRosterFB) form;
		Status status = new Status();
		status.add(Status.NEW);
		WebUTIL.refreshTransState(_request);
		
		ControllerUTIL.setSysdate(_request);
		WebUTIL.setStatus(_request, status);
		
		_fb.reset(mapping, _request);
		
		DateWiseEmployeeDutyRosterUTL.getDutyRosterCategory(_request,_fb);
		
		return mapping.findForward("ADD");
	}

	/**
	 * the action called when the page  is opend initially for Duty Area Employee Master
	 * it also gets the values of different Types of Shifts from the HDRT_SHIFT_TYPE_MST 
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*
	
	
	public ActionForward GET_ROSTERS(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DateWiseEmployeeDutyRosterFB _fb = (DateWiseEmployeeDutyRosterFB) form;
		DateWiseEmployeeDutyRosterUTL.getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(_request,_fb);
		
		return mapping.findForward("ADD");
	}

	
	 /**
	 *  the action used to get duty area combo using the  pkg_duty_roster.prcedure_get_duty_area 
	 * it also gets the values  from the HDRT_DUTY_AREA_MST 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*
 

public ActionForward GET_DUTY_AREA(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 DateWiseEmployeeDutyRosterFB _fb = (DateWiseEmployeeDutyRosterFB) form;		 
	 DateWiseEmployeeDutyRosterUTL.getDutyAreaWithCapacityAndDesignationBasedOnRosterType(request,_fb);
	 
	 return mapping.findForward("ADD");
	 
	 //return null;
}

/**
 *  the action used to get Employees Based on  DESIGNATION and Area  Code
 * @param mapping -object of ActionMapping
 * @param form - object of ActionForm
 * @param _request - HttpServletRequest
 * @param _response - HttpServletResponse
 * @return calls action "ADD"
 */
//*


public ActionForward GET_EMPLOYEES_CALENDAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
 System.out.println("inside GET_BLOCK of AddDeptMasterACTION");
 DateWiseEmployeeDutyRosterFB _fb = (DateWiseEmployeeDutyRosterFB) form;		 
 DateWiseEmployeeDutyRosterUTL.getDateWiseEmployeesRoster(request,_fb);
 
 
 return mapping.findForward("ADD");
}


	
	/**
	 * the action called when you want to add a new  Shift Master in HDRT_SHIFT_MST
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
     //* 
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DateWiseEmployeeDutyRosterFB _fb = (DateWiseEmployeeDutyRosterFB) form;

		boolean hasFlag = DateWiseEmployeeDutyRosterUTL.saveEmpDutyRosterDateWise(_fb, _request);
		
		
		return this.NEW(mapping, form, _request, _response);
		
	
	}
	
	

	/**
	 *  the action used to FETCH DATA WHILE MODIFICATION
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*


	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 System.out.println("inside MODIFY of eMP DATE WISE ROSTER");
	 DateWiseEmployeeDutyRosterFB _fb = (DateWiseEmployeeDutyRosterFB) form;		 
	 DateWiseEmployeeDutyRosterUTL.fetchDateWiseEmpRoster(request,_fb);
	 
	 
	 return mapping.findForward("ADD");
	}
	
	/**
	 *  the action used to FETCH DATA WHILE MODIFICATION
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*


	public ActionForward REPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 System.out.println("inside MODIFY of eMP DATE WISE ROSTER");
	 DateWiseEmployeeDutyRosterFB _fb = (DateWiseEmployeeDutyRosterFB) form;		 
	 DateWiseEmployeeDutyRosterUTL.showDateWiseEmpRosterReport(request,_fb);
	 
	 
	 return mapping.findForward("ADD");
	}
	
	
	/**
	 * the action called when you want to add a new  Shift Master in HDRT_SHIFT_MST
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 * @throws Exception 
	 */
     //* 
	
	public ActionForward MODIFY_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		DateWiseEmployeeDutyRosterFB _fb = (DateWiseEmployeeDutyRosterFB) form;

		boolean hasFlag = DateWiseEmployeeDutyRosterUTL.saveAndModifyEmpDutyRosterDateWise(_fb, _request);
		
		
		return this.NEW(mapping, form, _request, _response);
		
	
	}
	
	/**
	 * the action called when you want to add a new  Shift Master in HDRT_SHIFT_MST
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 * @throws Exception 
	 */
     //* 
	
	public ActionForward GENERATE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		DateWiseEmployeeDutyRosterFB _fb = (DateWiseEmployeeDutyRosterFB) form;

		boolean hasFlag = DateWiseEmployeeDutyRosterUTL.generateEmpDutyRosterDateWise(_fb, _request);
		
		
		return this.NEW(mapping, form, _request, _response);
		
	
	}
	
	/**
	 * the action called when you want to get to the Listing Page
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "LIST"
	 */
	//*
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
			return mapping.findForward("CANCEL");
	}
	
	
}
