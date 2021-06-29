package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.InsuranceClaimReceiveFB;
import mrd.transaction.controller.utl.InsuranceClaimReceiveUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InsuranceClaimReceiveACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		InsuranceClaimReceiveFB fb=(InsuranceClaimReceiveFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		fb.setCidNoFlag(MrdConfig.INSURANCE_FILE_TRACKING_FLAG);
		InsuranceClaimReceiveUTL.getEssenForInsuranceClaimReceive(fb, request);
		return mapping.findForward("NEW");
	}
	public ActionForward CIDNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//InsuranceClaimReceiveFB fb=(InsuranceClaimReceiveFB)form;
		return mapping.findForward("CIDNO");
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	public ActionForward SEARCHPOPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//InsuranceClaimReceiveFB fb=(InsuranceClaimReceiveFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MrdConfig.ALL_PAT_INFO_LIST);
		return mapping.findForward("SEARCHPOPUP");
	}
	public ActionForward SEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		InsuranceClaimReceiveFB fb=(InsuranceClaimReceiveFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MrdConfig.ALL_PAT_INFO_LIST);
		InsuranceClaimReceiveUTL.getPatInformation(fb, request);
		return mapping.findForward("SEARCHPOPUP");
	}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		InsuranceClaimReceiveFB fb=(InsuranceClaimReceiveFB)form;
		InsuranceClaimReceiveUTL.saveInsuranceDetail(fb, request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
}
