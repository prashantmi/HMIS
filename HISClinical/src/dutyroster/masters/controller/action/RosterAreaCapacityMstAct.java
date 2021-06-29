


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

import dutyroster.masters.controller.fb.RosterAreaCapacityMstFB;  
import dutyroster.masters.controller.utl.RosterAreaCapacityMstUTL;  

public class RosterAreaCapacityMstAct extends CSRFGardTokenAction
{
	

	

	
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
	
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		RosterAreaCapacityMstFB _fb = (RosterAreaCapacityMstFB) form;
		WebUTIL.refreshTransState(_request);
		_fb.reset(mapping, _request);
		RosterAreaCapacityMstUTL.getRosterIdList(_request,_fb);
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
	 RosterAreaCapacityMstFB _fb = (RosterAreaCapacityMstFB) form;		 
	 RosterAreaCapacityMstUTL.getDutyAreaAndShiftsBasedOnRosterType(request,_fb);
	 _fb.setHmode("ADD");
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
		RosterAreaCapacityMstFB _fb = (RosterAreaCapacityMstFB) form;

		boolean hasFlag = RosterAreaCapacityMstUTL.saveRosterAreaCapacityInfo(_fb, _request);
		
		return this.ADD(mapping, form, _request, _response);
	}
	


	/**
	 * the action called when you want to Edit an Old Shift Master by getting it's Old Details
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		RosterAreaCapacityMstFB _fb = (RosterAreaCapacityMstFB) form;
		WebUTIL.refreshTransState(_request);
		RosterAreaCapacityMstUTL.fetchRosterAreaCapacityInfo(_fb, _request);

		return mapping.findForward("ADD");
	}

	
	/**
	 * the action called when you want to Modify an Old Shift Master
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "LIST" if Successfull else to "ADD" if there is a Duplicate Record Entry
	 * @throws Exception 
	 */
	//*
	
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		RosterAreaCapacityMstFB _fb = (RosterAreaCapacityMstFB) form;
		WebUTIL.refreshTransState(_request);
		boolean flag=RosterAreaCapacityMstUTL.updateRosterAreaCapacityInfo(_fb, _request);
		
	if(flag)
		return mapping.findForward("LIST");
	else
	   {
		
		return mapping.findForward("ADD");
	   }	
	}
	
		
	
	
	
	

	/**
	 * the action called when you want to View  Old Shift Master
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RosterAreaCapacityMstFB _fb = (RosterAreaCapacityMstFB) form;
		WebUTIL.refreshTransState(_request);
		RosterAreaCapacityMstUTL.fetchRosterAreaCapacityInfo(_fb, _request);
		
		return mapping.findForward("ADD");
		
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
		return mapping.findForward("LIST");
	}
	
		
	 
	/**
	 * the action called when you want to a get to the Listing Page
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "LIST"
	 */
	//*
	
	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		WebUTIL.refreshTransState(_request);
		return mapping.findForward("LIST");
	}
	
	
}
