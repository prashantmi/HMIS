package dutyroster.reports.controller.action;


import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;  
 
import dutyroster.reports.controller.fb.EmpWiseSupervisiorEmpRosterReportFB;
import dutyroster.reports.controller.utl.EmpWiseSupervisiorEmpRosterReportUTL;

public class EmpWiseSupervisiorEmpRosterReportACT extends DispatchAction
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
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest _request,HttpServletResponse response){		
				
		return this.NEW(mapping, form, _request, response);
	}

	
	/**
	 * the action IS CALLED BY DEFAULT AND IT REDIRECTS TO new 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest _request,HttpServletResponse response){		
		
		EmpWiseSupervisiorEmpRosterReportFB _fb = (EmpWiseSupervisiorEmpRosterReportFB) form;
		Status status = new Status();
		status.add(Status.NEW);
		WebUTIL.refreshTransState(_request);
		
		ControllerUTIL.setSysdate(_request);
		WebUTIL.setStatus(_request, status);
		
		_fb.reset(mapping, _request);
		
		EmpWiseSupervisiorEmpRosterReportUTL.getEmpListofSupervisior(_request, _fb);
		
		
		
		return mapping.findForward("ADD");	
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
	
	
	public ActionForward GET_REPORT(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		EmpWiseSupervisiorEmpRosterReportFB _fb = (EmpWiseSupervisiorEmpRosterReportFB) form;
		Status status = new Status();
		status.add(Status.NEW);
	//	WebUTIL.refreshTransState(_request);
		
		ControllerUTIL.setSysdate(_request);
		WebUTIL.setStatus(_request, status);
		
		//_fb.reset(mapping, _request);
		
		EmpWiseSupervisiorEmpRosterReportUTL.getEmpWiseRosterReport(_request,_fb);
		
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
		
		/*EmpWiseSupervisiorEmpRosterReportFB _fb = (EmpWiseSupervisiorEmpRosterReportFB) form;
		_fb.reset(mapping, _request);
		
		WebUTIL.refreshTransState(_request);
		return mapping.findForward("ADD");
	  return this.NEW(mapping, form, _request, _response);*/
		  WebUTIL.refreshTransState(_request);
			return mapping.findForward("CANCEL");
	}
	
	
}
