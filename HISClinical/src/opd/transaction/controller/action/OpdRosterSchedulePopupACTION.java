package opd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdRosterSchedulePopupFB;
import opd.transaction.controller.util.OpdRosterSchedulePopupUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdRosterSchedulePopupACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.SHOWSCHEDULE(mapping, form, request, response);
	}

	public ActionForward SHOWSCHEDULE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdRosterSchedulePopupFB fb = (OpdRosterSchedulePopupFB) form;
		OpdRosterSchedulePopupUTIL.getSchedule(fb, request);		
		return mapping.findForward("POPUP");
	}

	public ActionForward GETSCHEDULE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		Status objStatus=new Status();
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("POPUP");
	}

	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdRosterSchedulePopupFB fb = (OpdRosterSchedulePopupFB) form;
		Status objStatus = new Status();
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT, objStatus);
		fb.setHmode("");
		return mapping.findForward("POPUP");
	}
}
