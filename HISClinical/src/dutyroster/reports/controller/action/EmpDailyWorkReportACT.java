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
 
import dutyroster.reports.controller.fb.EmpDailyWorkReportFB;
import dutyroster.reports.controller.utl.EmpDailyWorkReportUTL;

public class EmpDailyWorkReportACT extends DispatchAction
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
		EmpDailyWorkReportFB _fb = (EmpDailyWorkReportFB) form;
		Status status = new Status();
		status.add(Status.NEW);
		WebUTIL.refreshTransState(_request);
		
		ControllerUTIL.setSysdate(_request);
		WebUTIL.setStatus(_request, status);
		
		_fb.reset(mapping, _request);
		
		EmpDailyWorkReportUTL.getListOfRoleBasedRosterMainCategory(_request,_fb);
		
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
		EmpDailyWorkReportFB _fb = (EmpDailyWorkReportFB) form;
		EmpDailyWorkReportUTL.getListOfRoleBasedRosterCategoryBasedOnRosterMainCategory(_request,_fb);
		
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
		EmpDailyWorkReportFB _fb = (EmpDailyWorkReportFB) form;
		EmpDailyWorkReportUTL.getListOfRoleBasedRostersOnTheBasisOfRosterCategory(_request,_fb);
		
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

	 EmpDailyWorkReportFB _fb = (EmpDailyWorkReportFB) form;		 
	 EmpDailyWorkReportUTL.getDutyAreasBasedOnRosterType(request,_fb);
	 
	 return mapping.findForward("ADD");
	 
	 //return null;
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


public ActionForward GET_EMP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

 EmpDailyWorkReportFB _fb = (EmpDailyWorkReportFB) form;		 
 EmpDailyWorkReportUTL.getListOfAllMappedEmployeesHavingUserId(request,_fb);
 
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


public ActionForward GET_REPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
 System.out.println("inside GET_BLOCK of AddDeptMasterACTION");
 EmpDailyWorkReportFB _fb = (EmpDailyWorkReportFB) form;		 
 EmpDailyWorkReportUTL.getEmpDailyWorkReport(request,_fb);
 
 
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
		return mapping.findForward("CANCEL");
	}
	
	
}
