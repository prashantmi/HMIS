
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
 



import dutyroster.transaction.controller.fb.LocationDutyRosterFB;
import dutyroster.transaction.controller.utl.LocationDutyRosterUTL; 

public class LocationDutyRosterACT extends CSRFGardTokenAction
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
		LocationDutyRosterFB _fb = (LocationDutyRosterFB) form;
		Status status = new Status();
		status.add(Status.NEW);
		WebUTIL.refreshTransState(_request);
		
		ControllerUTIL.setSysdate(_request);
		WebUTIL.setStatus(_request, status);
		
		_fb.reset(mapping, _request);
		
		LocationDutyRosterUTL.getRosterAndAreaTypeListHavingRosterModeLocation(_request,_fb);
		
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
	 LocationDutyRosterFB _fb = (LocationDutyRosterFB) form;		 
	 LocationDutyRosterUTL.getDutyAreaWithCapacityAndDesignationBasedOnRosterType(request,_fb);
	 
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


public ActionForward GET_LOCATION_WISE_ROSTER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
 System.out.println("inside GET_LOCATION_WISE_ROSTER of LOCATION WISE ROSTER");
 LocationDutyRosterFB _fb = (LocationDutyRosterFB) form;		 
 LocationDutyRosterUTL.getLocationWiseRosterEssentials(request,_fb);
 
 
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


public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
 System.out.println("inside GET_LOCATION_WISE_ROSTER of LOCATION WISE ROSTER");
 generateToken(request);
 LocationDutyRosterFB _fb = (LocationDutyRosterFB) form;		 
 LocationDutyRosterUTL.fetchLocationWiseRosterDetails(request,_fb);
 
 
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
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		LocationDutyRosterFB _fb = (LocationDutyRosterFB) form;

		boolean hasFlag=LocationDutyRosterUTL.saveLocationDutyRoster(_fb, _request);
		
	if(hasFlag==true)
		return this.NEW(mapping, form, _request, _response);
	else
		{
		LocationDutyRosterUTL.getLocationWiseRosterEssentials(_request,_fb);
		return mapping.findForward("ADD");	
		}
	
	
	}
	
	
	/**
	 *  the action used to FETCH DATA WHILE MODIFICATION
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 * @throws Exception 
	 */
	//*


	public ActionForward GENERATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		System.out.println("inside GET_LOCATION_WISE_ROSTER of LOCATION WISE ROSTER");
	 LocationDutyRosterFB _fb = (LocationDutyRosterFB) form;		 
	 boolean hasFlag=LocationDutyRosterUTL.generateLocationWiseRoster(request,_fb);
	// _fb.reset(mapping, request);
	 return this.NEW(mapping, form, request, response);
	// return mapping.findForward("ADD");
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
		System.out.println("inside GET_LOCATION_WISE_ROSTER of LOCATION WISE ROSTER");
		 LocationDutyRosterFB _fb = (LocationDutyRosterFB) form;		 
		 LocationDutyRosterUTL.fetchLocationWiseRosterDetails(request,_fb);
		 
		 
		 return mapping.findForward("REPORT");
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
		
		return mapping.findForward("CANCEL");
	}
	
	
}
