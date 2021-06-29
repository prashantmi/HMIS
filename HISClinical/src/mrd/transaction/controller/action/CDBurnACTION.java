package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.CDBurnFB;
import mrd.transaction.controller.utl.CDBurnUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CDBurnACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		CDBurnFB fb=(CDBurnFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		ControllerUTIL.setSysdate(request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CDBurnFB fb=(CDBurnFB)form;
		CDBurnUTL.getRecordToBurn(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SETRECORDTOBURN(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CDBurnFB fb=(CDBurnFB)form;
		CDBurnUTL.setRecordToBurn(fb,request);
		fb.setRemarks("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CDBurnFB fb=(CDBurnFB)form;
		CDBurnUTL.saveCDBurnDetail(fb,request);
		return null;
	}
	
	public ActionForward BURN(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//CDBurnFB fb=(CDBurnFB)form;
		//CDBurnUTL.setRecordToBurn(fb,request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward SHOWPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CDBurnFB fb=(CDBurnFB)form;
		CDBurnUTL.showProfile(fb,request);
		return mapping.findForward("SHOWPROFILE");
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("CANCEL");
		
	}
	
}
