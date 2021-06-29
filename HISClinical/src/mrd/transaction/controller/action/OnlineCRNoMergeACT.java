package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.OnlineCRNoMergeFB;
import mrd.transaction.controller.utl.OnlineCRNoMergeUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OnlineCRNoMergeACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		OnlineCRNoMergeFB fb=(OnlineCRNoMergeFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		OnlineCRNoMergeUTL.getEssentialForOnlineCRNoMerge(fb,request); 
		request.getSession().removeAttribute(MrdConfig.ARR_ONLINE_SEARCH_PATIENT);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		OnlineCRNoMergeFB fb=(OnlineCRNoMergeFB) form;
		OnlineCRNoMergeUTL.searchPatient(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OnlineCRNoMergeFB fb=(OnlineCRNoMergeFB) form;
		OnlineCRNoMergeUTL.saveOnlineCRNumberMerge(fb,request); 
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
}

