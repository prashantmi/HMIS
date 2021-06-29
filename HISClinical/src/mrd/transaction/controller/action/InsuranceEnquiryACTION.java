package mrd.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.InsuranceEnquiryFB;
import mrd.transaction.controller.utl.InsuranceEnquiryUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InsuranceEnquiryACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		InsuranceEnquiryFB fb=(InsuranceEnquiryFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		InsuranceEnquiryUTL.getEssenForInsuranceEnquiry(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		InsuranceEnquiryFB fb=(InsuranceEnquiryFB)form;
		InsuranceEnquiryUTL.searchInsuranceDtl(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		InsuranceEnquiryFB fb=(InsuranceEnquiryFB)form;
		InsuranceEnquiryUTL.getDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
}
