package mrd.transaction.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.SummonDetailDeskFB;
import mrd.transaction.controller.utl.SummonDetailDeskUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class SummonDetailDeskACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.COMPUTERIZED(mapping, form, request, response);
	}

	public ActionForward COMPUTERIZED(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		SummonDetailDeskFB fb=(SummonDetailDeskFB)form;
		Status objStatus =new Status();
		SummonDetailDeskUTL.setTabSequence(MrdConfig.SUMMON_DETAIL_UPLOAD_MODE_COMPUTERIZED, request);
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);
		fb.setDeskmode("COMPUTERIZED");
		return mapping.findForward("COMPUTERIZED");
	}

	public ActionForward MANUAL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		SummonDetailDeskUTL.setTabSequence(MrdConfig.SUMMON_DETAIL_UPLOAD_MODE_MANUAL, request);
		return mapping.findForward("MANUAL");
	}


	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
}
