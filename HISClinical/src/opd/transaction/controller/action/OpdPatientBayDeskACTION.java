package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdPatientDeskFB;
import opd.transaction.controller.util.OpdPatientBayDeskUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdPatientBayDeskACTION extends CSRFGardTokenAction
{

	/**
	 * the default action called when a page is loaded for the first time
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action "NEW"
	 */
	// *
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	/**
	 * action mainly called at the initial loading of a page or when a form is reset refreshes Transaction State sets all
	 * OpdPatient essentials sets PatientList
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	// *
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		WebUTIL.refreshTransState(request);
		System.out.println("Department List : OpdPatientDeskACTION");
		OpdPatientBayDeskUTIL.getOpdDeskEssentials(opdPatientDeskFB, request);
		System.out.println("Waiting Patient List : OpdPatientDeskACTION");
		OpdPatientBayDeskUTIL.getTodayOpdPatientList(opdPatientDeskFB, request);
		return mapping.findForward("NEW");
	}


	/**
	 * Make Patient List Order By QUEUENO
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	// *
	public ActionForward QUEUENO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		OpdPatientBayDeskUTIL.getOrderByQueueNo(opdPatientDeskFB, request);
		return mapping.findForward("NEW");
	}

	/**
	 * Make Patient List Order By CRNO
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	// *
	public ActionForward CRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		OpdPatientBayDeskUTIL.getOrderByCrNo(opdPatientDeskFB, request);
		return mapping.findForward("NEW");
	}

	/**
	 * Make Patient List Order By NAME
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	// *
	public ActionForward NAME(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		OpdPatientBayDeskUTIL.getOrderByName(opdPatientDeskFB, request);
		return mapping.findForward("NEW");
	}

	/**
	 * Make Patient List Order By PATIENTCATEGORY
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	// *
	public ActionForward PATIENTCATEGORY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		OpdPatientBayDeskUTIL.getOrderByPatientCategory(opdPatientDeskFB, request);
		return mapping.findForward("NEW");
	}

	/**
	 * close the episode OF the patient.
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */

	public ActionForward SAVEEPISODE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		validateToken(request,response);
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		OpdPatientBayDeskUTIL.saveOpdPatientEpisode(opdPatientDeskFB, request);
		return this.NEW(mapping, form, request, response);
	}

	/**
	 * sets the next visit date OF the patient.
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */

}