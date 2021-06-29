

package dutyroster.masters.controller.action;


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

import dutyroster.masters.controller.fb.DutyAreaEmployeeMstFB;  
import dutyroster.masters.controller.utl.DutyAreaEmployeeMstUTL; 

public class DutyAreaEmployeeMstACT extends CSRFGardTokenAction
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
	 * it also gets the values of different Types of Shifts from the HDRT_SHIFT_TYPE_MST 
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

		DutyAreaEmployeeMstFB _fb = (DutyAreaEmployeeMstFB) form;
		Status status = new Status();
		status.add(Status.NEW);
		WebUTIL.refreshTransState(_request);
		
		ControllerUTIL.setSysdate(_request);
		WebUTIL.setStatus(_request, status);
		
		_fb.reset(mapping, _request);
		
		DutyAreaEmployeeMstUTL.getEmployeeAreaEssentials(_request,_fb);
		
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
	 System.out.println("inside GET_BLOCK of AddDeptMasterACTION");
	 DutyAreaEmployeeMstFB _fb = (DutyAreaEmployeeMstFB) form;		 
	 DutyAreaEmployeeMstUTL.getDutyAreaBasedOnDutyAreaType(request,_fb);
	 
	 return mapping.findForward("ADD");
}

	
/**
 *  the action used to get ROLE iD'S AND DESIGNATION
 * @param mapping -object of ActionMapping
 * @param form - object of ActionForm
 * @param _request - HttpServletRequest
 * @param _response - HttpServletResponse
 * @return calls action "ADD"
 */
//*


public ActionForward GET_ROLE_AND_DESIGNATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
 System.out.println("inside GET_BLOCK of AddDeptMasterACTION");
 DutyAreaEmployeeMstFB _fb = (DutyAreaEmployeeMstFB) form;		 
 DutyAreaEmployeeMstUTL.getRoleAndDesignation(request,_fb);
 
 return mapping.findForward("ADD");
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


public ActionForward GET_EMPLOYEES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
 System.out.println("inside GET_BLOCK of AddDeptMasterACTION");
 DutyAreaEmployeeMstFB _fb = (DutyAreaEmployeeMstFB) form;		 
 DutyAreaEmployeeMstUTL.getLeftEmployeesBasedOnDesignationAndArea(request,_fb);
 DutyAreaEmployeeMstUTL.getRightEmployeesBasedOnDesignationAndArea(request,_fb);
 
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
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		DutyAreaEmployeeMstFB _fb = (DutyAreaEmployeeMstFB) form;

		boolean hasFlag = DutyAreaEmployeeMstUTL.saveAndModifyDutyAreaEmpInfo(_fb, _request);
		
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
		WebUTIL.refreshTransState(_request);
		return mapping.findForward("INITIAL");
	}
	
	
}
