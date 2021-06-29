package dutyroster.transaction.controller.action;


import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.RosterDtlVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction; 
 


import dutyroster.DutyRosterConfig;
import dutyroster.transaction.controller.fb.ExChangeOfDutyFB;
import dutyroster.transaction.controller.utl.ExChangeOfDutyUTL;
import dutyroster.transaction.controller.utl.LocationDutyRosterUTL;

public class ExChangeOfDutyACT extends CSRFGardTokenAction
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
		ExChangeOfDutyFB _fb = (ExChangeOfDutyFB) form;
		Status status = new Status();
		status.add(Status.NEW);
		WebUTIL.refreshTransState(_request);
		
		ControllerUTIL.setSysdate(_request);
		WebUTIL.setStatus(_request, status);
		
		_fb.reset(mapping, _request);
		
		ExChangeOfDutyUTL.getRosterMainCategory(_request,_fb);
		
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
	
	
	public ActionForward GET_ROSTER_CATEGORY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		ExChangeOfDutyFB _fb = (ExChangeOfDutyFB) form;
		ExChangeOfDutyUTL.getRosterCategoryBasedOnRosterMainCategory(_request,_fb);
		
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
	
	
	public ActionForward GET_EMP(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		ExChangeOfDutyFB _fb = (ExChangeOfDutyFB) form;
		ExChangeOfDutyUTL.getEmpList(_request,_fb);
		
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
	
	
	public ActionForward GET_DUTY_LIST_REQUEST_BY_EMP(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		ExChangeOfDutyFB _fb = (ExChangeOfDutyFB) form;
		ExChangeOfDutyUTL.getRequestByEmpDutyListForExchange(_request,_fb);
		
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
	
	
	public ActionForward GET_DUTY_LIST_EXCHANGE_WITH_EMP(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		ExChangeOfDutyFB _fb = (ExChangeOfDutyFB) form;
		ExChangeOfDutyUTL.getRequestWithEmpDutyListForExchange(_request,_fb);
		
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
	 * @throws Exception 
	 */
	//*
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		ExChangeOfDutyFB _fb = (ExChangeOfDutyFB) form;
		
		boolean hasFlag = true;

if(_fb.getMode().equals("1")) 		//For ExChange
	hasFlag=ExChangeOfDutyUTL.saveExchangeofDuty(_request,_fb);
	else
if(_fb.getMode().equals("2")) 	//For Change
	hasFlag=ExChangeOfDutyUTL.saveChangeofDuty(_request,_fb);
	
		
		
		
		if(hasFlag==true)
			return this.NEW(mapping, form, _request, _response);
		else
			{
			ExChangeOfDutyUTL.getRequestWithEmpDutyListForExchange(_request,_fb);
			return mapping.findForward("ADD");	
			}
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
