package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.InsuranceClaimRecordEntryFB;
import mrd.transaction.controller.utl.InsuranceClaimRecordEntryUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InsuranceClaimRecordEntryACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
		
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		InsuranceClaimRecordEntryFB fb=(InsuranceClaimRecordEntryFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		InsuranceClaimRecordEntryUTL.getEssenForInsuranceClaimRecordEntry(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//InsuranceClaimRecordEntryFB fb=(InsuranceClaimRecordEntryFB)form;
		Status objStatus = new Status();
		objStatus.add(Status.DONE);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETINSURANCEDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		InsuranceClaimRecordEntryFB fb=(InsuranceClaimRecordEntryFB)form;
		InsuranceClaimRecordEntryUTL.getInsuranceDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		InsuranceClaimRecordEntryFB fb=(InsuranceClaimRecordEntryFB)form;
		if(InsuranceClaimRecordEntryUTL.saveInsuranceClaimRecordEntry(fb, request))
		{
			WebUTIL.refreshTransState(request);	
			fb.reset(mapping, request);
			InsuranceClaimRecordEntryUTL.getEssenForInsuranceClaimRecordEntry(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.NEW,"","Opinion Detail Saved");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}
	
}
