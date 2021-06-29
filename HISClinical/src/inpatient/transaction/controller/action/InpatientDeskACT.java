package inpatient.transaction.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.InpatientDeskFB;
import inpatient.transaction.controller.utl.InpatientDeskUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InpatientDeskACT extends DispatchAction
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
		InpatientDeskFB fb=(InpatientDeskFB)form;
		WebUTIL.refreshTransState(request);
		InpatientDeskUTL.getAdmittedPatientList(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		InpatientDeskFB fb = (InpatientDeskFB) form;
		Status objStatus = new Status();
		fb.setHmode("");
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHPATLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		InpatientDeskFB fb = (InpatientDeskFB) form;
		InpatientDeskUTL.getSearchResultList(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward ADMNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		InpatientDeskFB fb = (InpatientDeskFB) form;
		InpatientDeskUTL.getOrderByAdmissionNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		InpatientDeskFB fb = (InpatientDeskFB) form;
		InpatientDeskUTL.getOrderByCrNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PATNAME(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		InpatientDeskFB fb = (InpatientDeskFB) form;
		InpatientDeskUTL.getOrderByPatName(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADMDATE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		InpatientDeskFB fb = (InpatientDeskFB) form;
		InpatientDeskUTL.getOrderByAdmissionDate(fb, request);
		return mapping.findForward("NEW");
	}
}
