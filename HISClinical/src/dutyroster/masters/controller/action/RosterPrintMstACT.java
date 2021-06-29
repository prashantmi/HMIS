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

import dutyroster.masters.controller.fb.RosterPrintMstFB;  
import dutyroster.masters.controller.utl.RosterPrintMstUTL; 

public class RosterPrintMstACT extends CSRFGardTokenAction
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
	 * the action called when the page  is opend initially for rOSTER PRINT Master
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
		RosterPrintMstFB _fb = (RosterPrintMstFB) form;
		Status status = new Status();
		status.add(Status.NEW);
		WebUTIL.refreshTransState(_request);
		
		ControllerUTIL.setSysdate(_request);
		WebUTIL.setStatus(_request, status);
		
		_fb.reset(mapping, _request);
		
		RosterPrintMstUTL.getRosterCategory(_request,_fb);
		
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
 

public ActionForward GET_ROSTER_TYPES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 System.out.println("inside GET_BLOCK of AddDeptMasterACTION");
	 RosterPrintMstFB _fb = (RosterPrintMstFB) form;		 
	_fb.setDisplayRosterBy("");
	 	 
	 RosterPrintMstUTL.getRosterTypeBasedOnRosterCategory(request,_fb);
	 
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


public ActionForward GET_ROSTER_PRINT_DETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
 System.out.println("inside GET_BLOCK of AddDeptMasterACTION");
 RosterPrintMstFB _fb = (RosterPrintMstFB) form;	
 
 
 
 RosterPrintMstUTL.getRosterPrintDetailsBasedOnRosterType(request,_fb);
 
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


public ActionForward CHANGE_ORDER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
 System.out.println("inside CHANGE_SEQUENCE of RosterPrintmstACTION");
 RosterPrintMstFB _fb = (RosterPrintMstFB) form;	
 
 
 
 RosterPrintMstUTL.getRosterPrintDetailsBasedOnRosterType(request,_fb);
 
 return mapping.findForward("CHANGE_ORDER");
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
		RosterPrintMstFB _fb = (RosterPrintMstFB) form;

		boolean hasFlag = RosterPrintMstUTL.saveAndModifyRosterPrintMstInfo(_fb, _request);
		
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

	public ActionForward SAVE_CHANGE_ORDER(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		RosterPrintMstFB _fb = (RosterPrintMstFB) form;

		boolean hasFlag = RosterPrintMstUTL.changeDisplayOrder(_fb, _request);
		
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
