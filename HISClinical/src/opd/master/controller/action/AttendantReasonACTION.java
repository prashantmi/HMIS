package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.AttendantReasonFB;
import opd.master.controller.util.AttendantReasonMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AttendantReasonACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.ADD(mapping, form, request, response);
	}

	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

generateToken(request);
		AttendantReasonFB fb = (AttendantReasonFB) form;

		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		WebUTIL.setStatus(request, objStatus);
		
		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		AttendantReasonFB fb = (AttendantReasonFB) form;
		boolean flag = AttendantReasonMasterUTIL.saveRecord(fb, request);
		if (flag)
		{
			fb.reset(mapping, request);
		}
		fb.setHmode("ADD");
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		AttendantReasonFB fb = (AttendantReasonFB) form;
		AttendantReasonMasterUTIL.getRecord(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		AttendantReasonFB fb = (AttendantReasonFB) form;
		boolean flag = AttendantReasonMasterUTIL.modifyReason(fb, request);
		if (flag)
		{
			return mapping.findForward("LIST");
		}
		else
		{
			fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		AttendantReasonFB fb = (AttendantReasonFB) form;
		AttendantReasonMasterUTIL.getRecord(fb, request);
		return mapping.findForward("NEW");
	}
}
