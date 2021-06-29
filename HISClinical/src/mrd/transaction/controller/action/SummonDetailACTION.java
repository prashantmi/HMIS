package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.SummonDtlFB;
import mrd.transaction.controller.utl.SummonDetailUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class SummonDetailACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);

		SummonDtlFB fb=(SummonDtlFB)form;
		WebUTIL.refreshTransState(request);	
		fb.setSummonFlag(MrdConfig.IS_MANUAL_SUMMON_DTL);
		fb.setCidNoFlag(MrdConfig.SUMMON_FILE_TRACKING_FLAG);
		fb.reset(mapping, request);
		SummonDetailUTL.getEssentialForSummonDtl(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		validateToken(request,response);
		SummonDtlFB fb=(SummonDtlFB)form;
		SummonDetailUTL.saveSummonDetail(fb,request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CIDNO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		//SummonDtlFB fb=(SummonDtlFB)form;
		return mapping.findForward("CIDNO");
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
	
	
}
