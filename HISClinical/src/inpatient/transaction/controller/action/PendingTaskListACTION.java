package inpatient.transaction.controller.action;

import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.PendingTaskListFB;
import inpatient.transaction.controller.utl.PendingTaskListUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PendingTaskListACTION extends DispatchAction
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PendingTaskListFB fb = (PendingTaskListFB) form;
		fb.reset(mapping, request);
		PendingTaskListUTIL.getEssentials(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward GETCONSENTDTLBYCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PendingTaskListFB fb = (PendingTaskListFB) form;
		PendingTaskListUTIL.getConsentDetailByCrNo(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETTREATMENTDTLBYCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PendingTaskListFB fb = (PendingTaskListFB) form;
		PendingTaskListUTIL.getPendingTreatmentDetailByCrNo(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPENDINGTREATMENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PendingTaskListFB fb = (PendingTaskListFB) form;
		PendingTaskListUTIL.getPendingTreatmentList(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}

	public ActionForward GETPENDINGSAMPLECOLLECTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PendingTaskListFB fb = (PendingTaskListFB) form;
		PendingTaskListUTIL.getPendingSampleCollectionList(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETSAMPLECOLLECTIONDTLBYCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PendingTaskListFB fb = (PendingTaskListFB) form;
		PendingTaskListUTIL.getPendingSampleCollectionByCrNo(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPENDINGINSTRUCTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PendingTaskListFB fb = (PendingTaskListFB) form;
		PendingTaskListUTIL.getPendingInstructionList(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPENDINGVITALMONITORING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PendingTaskListFB fb = (PendingTaskListFB) form;
		PendingTaskListUTIL.getPendingVitalMonitoring(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPENDINGVITALMONITORINGBYCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PendingTaskListFB fb = (PendingTaskListFB) form;
		PendingTaskListUTIL.getPendingMonitoringByCrNo(fb, request);
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
}
