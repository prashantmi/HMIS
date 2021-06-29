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

import dutyroster.masters.controller.fb.RosterCategoryMstFB;
import dutyroster.masters.controller.utl.RosterCategoryMstUTL;

public class RosterCategoryMstACT extends CSRFGardTokenAction
{
	
	/**
	 * the action called when you want to add a new Roster Category Master HDRT_ROSTER_CAT_MST
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
		Status status = new Status();
		status.add(Status.NEW);

		WebUTIL.refreshTransState(_request); 
		RosterCategoryMstFB _fb = (RosterCategoryMstFB) form;
		
		_fb.reset(mapping, _request);
		ControllerUTIL.setSysdate(_request);
		RosterCategoryMstUTL.getListOfRosterMainCategory(_fb, _request);
		WebUTIL.setStatus(_request, status);
		
		return mapping.findForward("ADD");
	}

	/**
	 * the action called when you want to add a new Roster Category Master
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
		RosterCategoryMstFB _fb = (RosterCategoryMstFB) form;

		boolean hasFlag = RosterCategoryMstUTL.saveRosterCategoryInfo(_fb, _request);
	//	WebUTIL.refreshTransState(_request);
		if (hasFlag)
		{
			_fb.reset(mapping, _request);
		}
		return mapping.findForward("ADD");
		//return this.ADD(mapping, form, _request, _response);
	
	}

	/**
	 * the action called when you want to Edit an Old Roster Category Master by getting it's Old Details
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
		RosterCategoryMstFB _fb = (RosterCategoryMstFB) form;
		WebUTIL.refreshTransState(_request);
		RosterCategoryMstUTL.fetchRosterCategoryInfo(_fb, _request);

		return mapping.findForward("ADD");
	}

	/**
	 * the action called when you want to Modify an Old Roster Category Master
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
		RosterCategoryMstFB _fb = (RosterCategoryMstFB) form;
	//	WebUTIL.refreshTransState(_request);
		boolean flag=RosterCategoryMstUTL.updateRosterCategoryInfo(_fb, _request);
		
	if(flag)
	{
		return mapping.findForward("LIST");
		
	}
	else
	   {
		
		return mapping.findForward("ADD");
	   }	
	}

	/**
	 * the action called when you want to View  Roster Category Master
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		RosterCategoryMstFB _fb = (RosterCategoryMstFB) form;
		WebUTIL.refreshTransState(_request);
		RosterCategoryMstUTL.fetchRosterCategoryInfo(_fb, _request);
		
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
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
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
	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		WebUTIL.refreshTransState(_request);
		return mapping.findForward("LIST");
	}
	
}
