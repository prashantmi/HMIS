package opd.master.controller.action;

import org.apache.struts.actions.DispatchAction;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import opd.master.controller.fb.UnitEpisodeKeywordFB;
import opd.master.controller.util.UnitEpisodeKeywordUTIL;

public class UnitEpisodeKeywordACTION extends CSRFGardTokenAction
{

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		UnitEpisodeKeywordFB fb = (UnitEpisodeKeywordFB) form;

		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		UnitEpisodeKeywordUTIL.setEssential(fb, request);

		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitEpisodeKeywordFB fb = (UnitEpisodeKeywordFB) form;

		boolean saveFlag = UnitEpisodeKeywordUTIL.AddKeywordUnitWise(fb, request);
		if (saveFlag)
		{
			WebUTIL.refreshTransState(request);
			fb.reset(mapping, request);
			UnitEpisodeKeywordUTIL.setEssential(fb, request);
			Status status = new Status();
			status.add(Status.NEW, "Keyword Successully Added To Units", "");
			WebUTIL.setStatus(request, status);
		}
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitEpisodeKeywordFB fb = (UnitEpisodeKeywordFB) form;

		WebUTIL.refreshTransState(request);

		UnitEpisodeKeywordUTIL.fetchModifyData(fb, request);

		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitEpisodeKeywordFB fb = (UnitEpisodeKeywordFB) form;

		UnitEpisodeKeywordUTIL.modifyKeywordsUnitWise(fb, request);

		return mapping.findForward("LIST");
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitEpisodeKeywordFB fb = (UnitEpisodeKeywordFB) form;

		WebUTIL.refreshTransState(request);

		UnitEpisodeKeywordUTIL.fetchViewData(fb, request);

		return mapping.findForward("MODIFY");
	}
}
