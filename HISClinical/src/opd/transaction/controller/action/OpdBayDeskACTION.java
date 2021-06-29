package opd.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdDeskFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdBayDeskACTION extends DispatchAction
{
	/**
	 * the default action called when a page is loaded for the first time
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls the action "NEW"
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("NEW");
	}

	/**
	 * sets the view to OPD Bay
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward GENERICTEMPLATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("GENERICTEMPLATE");
	}

	public ActionForward DESKTREATMENTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("DESKTREATMENTDETAIL");
	}

	public ActionForward ADMISSIONADVICE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("ADMISSIONADVICE");
	}

	public ActionForward REQUISITIONRAISING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("REQUISITIONRAISING");
	}

	public ActionForward BLOODBAGREQ(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("BLOODBAGREQ");
	}

	public ActionForward OTLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("OTLIST");
	}
	
	public ActionForward PACREQUEST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PACREQUEST");
	}
	
	public ActionForward SERVICEREQUISITION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("SERVICEREQUISITION");
	}


	public ActionForward ESTIMATEREQUEST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("ESTIMATEREQUEST");
	}

	public ActionForward MEDICALCERTIFICATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("MEDICALCERTIFICATE");
	}

	public ActionForward DOCUMENTARCHIVAL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("DOCUMENTARCHIVAL");
	}

	public ActionForward DESKDIAGNOSIS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("DESKDIAGNOSIS");
	}
	public ActionForward DIETADVICE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("DIETADVICE");
	}
	
	
	public ActionForward UNIPAGEPRESCRIPTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("UNIPAGEPRESCRIPTION");
	}
	
	public ActionForward CHANGEUNITROOM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("CHANGEUNITROOM");
	}
	
	public ActionForward EMRDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdDeskFB fb = (OpdDeskFB) form;
		WebUTIL.getSession(request).setAttribute("crNo", fb.getPatCrNo());
		WebUTIL.getSession(request).setAttribute("departmentUnitCode", fb.getDepartmentUnitCode());
		return mapping.findForward("EMRDETAIL");
	}
	
	public ActionForward CHARTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CHARTDETAIL");
	}

}