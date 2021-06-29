package opd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpenEpisodeFB;
import opd.transaction.controller.util.OpenEpisodeUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpenEpisodeACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}

	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		Status objStatus= new Status();
		//HttpSession ses=WebUTIL.getSession(request);
		OpenEpisodeFB fb=(OpenEpisodeFB)form;
		fb.reset(mapping,request);
		WebUTIL.refreshTransState(request);
		request.removeAttribute(Config.STATUS_OBJECT);
		WebUTIL.getSession(request).removeAttribute(Config.STATUS_OBJECT);
		OpenEpisodeUTIL.setSysdate(request);
		objStatus.add(Status.NEW,"","");
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpenEpisodeFB fb=(OpenEpisodeFB) form;
		OpenEpisodeUTIL.setPatientDtlByCrno(fb,request);
		return mapping.findForward("SAME");
	}
	
	public ActionForward OPEN(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse respovse) throws Exception
	{
		validateToken(request,respovse);
		OpenEpisodeFB fb=(OpenEpisodeFB) form;
		OpenEpisodeUTIL.openPatientEpisode(fb,request);
		return mapping.findForward("NEW");
	}
}