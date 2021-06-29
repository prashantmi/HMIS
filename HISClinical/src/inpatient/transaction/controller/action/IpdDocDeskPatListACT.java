package inpatient.transaction.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.IpdPatDocDeskFB;
import inpatient.transaction.controller.utl.IpdPatDocDeskUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IpdDocDeskPatListACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{

		return this.NEW(mapping, form, request, response);
	}
	
	/** Getting the List of Admitted patient
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		IpdPatDocDeskFB fb=(IpdPatDocDeskFB)form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		IpdPatDocDeskUTL.getAdmittedPatientList(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		IpdPatDocDeskFB fb = (IpdPatDocDeskFB) form;
		Status objStatus = new Status();
		fb.setHmode("");
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADMNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		IpdPatDocDeskFB fb = (IpdPatDocDeskFB) form;
		IpdPatDocDeskUTL.getOrderByAdmissionNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		IpdPatDocDeskFB fb = (IpdPatDocDeskFB) form;
		IpdPatDocDeskUTL.getOrderByCrNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PATNAME(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		IpdPatDocDeskFB fb = (IpdPatDocDeskFB) form;
		IpdPatDocDeskUTL.getOrderByPatName(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADMDATE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		IpdPatDocDeskFB fb = (IpdPatDocDeskFB) form;
		IpdPatDocDeskUTL.getOrderByAdmissionDate(fb, request);
		return mapping.findForward("NEW");
	}
}
