package registration.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import registration.controller.fb.EpisodeTriageDetailFB;
import registration.controller.util.EpisodeTriageDetailUTIL;

public class EpisodeTriageDetailACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.GETCRNO(mapping, form, request, response);
	}

	// Call Directly
	public ActionForward GETCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		EpisodeTriageDetailFB fb = (EpisodeTriageDetailFB) form;
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		
		Status objStatus = new Status();
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		ControllerUTIL.setSysdate(request);
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		
		fb.setHmode("GETCRNO");
		return mapping.findForward("NEW");
	}

	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		EpisodeTriageDetailFB fb = (EpisodeTriageDetailFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		EpisodeTriageDetailUTIL.setTriageDetailDirectEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		return mapping.findForward("CANCEL");
	}
	
	// From Desk
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		EpisodeTriageDetailFB fb = (EpisodeTriageDetailFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		EpisodeTriageDetailUTIL.setTriageDetailEssentials(fb,request);
		
		fb.setIsDirectCall("DESK");
		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		EpisodeTriageDetailFB fb = (EpisodeTriageDetailFB) form;
		EpisodeTriageDetailUTIL.saveTriageDetails(fb,request);

		if(fb.getIsDirectCall().equals("DIRECT"))
			return this.GETPATDTL(mapping, form, request, response);
		else
			return this.NEW(mapping, form, request, response);
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{		
		EpisodeTriageDetailFB fb = (EpisodeTriageDetailFB) form;
		fb.setEntryMode("ALL");
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		EpisodeTriageDetailFB fb = (EpisodeTriageDetailFB) form;
		EpisodeTriageDetailUTIL.modifyTriageDetails(fb,request);

		if(fb.getIsDirectCall().equals("DIRECT"))
			return this.GETPATDTL(mapping, form, request, response);
		else
			return this.NEW(mapping, form, request, response);
	}
}
