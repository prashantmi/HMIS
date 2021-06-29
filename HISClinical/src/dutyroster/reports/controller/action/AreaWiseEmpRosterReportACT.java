

package dutyroster.reports.controller.action;


import inpatient.reports.controller.util.SpecialityWiseOperationUTL;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;  
 
import dutyroster.reports.controller.fb.AreaWiseEmpRosterReportFB;
import dutyroster.reports.controller.utl.AreaWiseEmpRosterReportUTL;

public class AreaWiseEmpRosterReportACT extends DispatchAction
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
		AreaWiseEmpRosterReportFB _fb = (AreaWiseEmpRosterReportFB) form;
		Status status = new Status();
		status.add(Status.NEW);
		WebUTIL.refreshTransState(_request);
		
		ControllerUTIL.setSysdate(_request);
		WebUTIL.setStatus(_request, status);
		
		_fb.reset(mapping, _request);
		
		AreaWiseEmpRosterReportUTL.getDutyRosterCategory(_request,_fb);
	//	AreaWiseEmpRosterReportUTL.getEssential(_fb,_request);
		
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
	
	public ActionForward GET_ROSTER_CATEGORY(ActionMapping mapping,ActionForm form,HttpServletRequest _request,HttpServletResponse _response)
	{
		
		AreaWiseEmpRosterReportFB _fb = (AreaWiseEmpRosterReportFB) form;
		AreaWiseEmpRosterReportUTL.getRosterCategoryBasedOnHospital(_request,_fb);
		
		return mapping.findForward("ADD");
	}
	
	public ActionForward GET_DUTY_AREA(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		AreaWiseEmpRosterReportFB _fb = (AreaWiseEmpRosterReportFB) form;
		AreaWiseEmpRosterReportUTL.getDutyAreasBasedOnRosterCategory(_request,_fb);
		
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
 

public ActionForward GET_ROSTERS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 AreaWiseEmpRosterReportFB _fb = (AreaWiseEmpRosterReportFB) form;		 
	 AreaWiseEmpRosterReportUTL.getRostersBasedOnDutyAreaAndRosterCatg(request,_fb);
	 
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
 AreaWiseEmpRosterReportFB _fb = (AreaWiseEmpRosterReportFB) form;		 
 AreaWiseEmpRosterReportUTL.getAreaWiseReport(request,_fb);
 
 
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
		
	/*	AreaWiseEmpRosterReportFB _fb = (AreaWiseEmpRosterReportFB) form;
		_fb.reset(mapping, _request);
		
		WebUTIL.refreshTransState(_request);
		return mapping.findForward("ADD");
	  return this.NEW(mapping, form, _request, _response);*/
		
		  WebUTIL.refreshTransState(_request);
			return mapping.findForward("CANCEL");
	}
	
	
}
