package inpatient.transaction.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.NursingStationPatListFB;
import inpatient.transaction.controller.utl.NursingStationPatListUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class NursingStationPatListACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		NursingStationPatListFB fb=(NursingStationPatListFB)form;
		WebUTIL.refreshTransState(request);
		NursingStationPatListUTL.getAdmittedPatientList(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		NursingStationPatListFB fb = (NursingStationPatListFB) form;
		Status objStatus = new Status();
		fb.setHmode("");
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADMNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		NursingStationPatListFB fb = (NursingStationPatListFB) form;
		NursingStationPatListUTL.getOrderByAdmissionNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		NursingStationPatListFB fb = (NursingStationPatListFB) form;
		NursingStationPatListUTL.getOrderByCrNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PATNAME(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		NursingStationPatListFB fb = (NursingStationPatListFB) form;
		NursingStationPatListUTL.getOrderByPatName(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADMDATE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		NursingStationPatListFB fb = (NursingStationPatListFB) form;
		NursingStationPatListUTL.getOrderByAdmissionDate(fb, request);
		return mapping.findForward("NEW");
	}
	
}
