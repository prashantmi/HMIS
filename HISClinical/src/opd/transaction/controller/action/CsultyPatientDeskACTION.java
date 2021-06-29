package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.CsultyPatientDeskFB;
import opd.transaction.controller.util.CsultyPatientDeskUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CsultyPatientDeskACTION extends CSRFGardTokenAction
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
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
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
		CsultyPatientDeskFB fb = (CsultyPatientDeskFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		CsultyPatientDeskUTIL.getCsultyPatientList(fb, request);
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
		CsultyPatientDeskFB fb = (CsultyPatientDeskFB) form;
		CsultyPatientDeskUTIL.getOrderByQueueNo(fb, request);
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
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CsultyPatientDeskFB fb = (CsultyPatientDeskFB) form;
		Status objStatus = new Status();
		fb.setHmode("");
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
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
		CsultyPatientDeskFB fb = (CsultyPatientDeskFB) form;
		CsultyPatientDeskUTIL.getOrderByCrNo(fb, request);
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
		CsultyPatientDeskFB fb = (CsultyPatientDeskFB) form;
		CsultyPatientDeskUTIL.getOrderByName(fb, request);
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
	public ActionForward PATIENTCATEGORY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CsultyPatientDeskFB fb = (CsultyPatientDeskFB) form;
		CsultyPatientDeskUTIL.getOrderByPatientCategory(fb, request);
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
	public ActionForward COLORCODE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CsultyPatientDeskFB fb = (CsultyPatientDeskFB) form;
		CsultyPatientDeskUTIL.getOrderByColorCode(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward TRIAGEDUR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CsultyPatientDeskFB fb = (CsultyPatientDeskFB) form;
		CsultyPatientDeskUTIL.getOrderByTriageDuration(fb, request);
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

	public ActionForward SAVEEPISODE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CsultyPatientDeskFB fb = (CsultyPatientDeskFB) form;
		CsultyPatientDeskUTIL.saveOpdPatientEpisode(fb, request);
		return this.NEW(mapping, form, request, response);
	}
}
