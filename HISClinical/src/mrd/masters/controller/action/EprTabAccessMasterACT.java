package mrd.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.masters.controller.fb.EprTabAccessMasterFB;
import mrd.masters.controller.util.EprTabAccessMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class EprTabAccessMasterACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.NEW(mapping, form, _request, _response);
	}
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		EprTabAccessMasterFB fb=(EprTabAccessMasterFB)form;
		fb.reset(mapping, _request);
		WebUTIL.refreshTransState(_request);
		EprTabAccessMasterUTL.getEssentialForTabAccessMst(fb, _request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETEPRTAB(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		EprTabAccessMasterFB fb=(EprTabAccessMasterFB)form;
		EprTabAccessMasterUTL.getTabAccessPolicy(fb, _request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETUSERS(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		EprTabAccessMasterFB fb=(EprTabAccessMasterFB)form;
		EprTabAccessMasterUTL.getUsers(fb, _request);
		return mapping.findForward("USERPOPUP");
	}
	
	public ActionForward SAVEUSERS(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		EprTabAccessMasterFB fb=(EprTabAccessMasterFB)form;
		EprTabAccessMasterUTL.saveUsersToSession(fb, _request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		EprTabAccessMasterFB fb=(EprTabAccessMasterFB)form;
		EprTabAccessMasterUTL.saveTabAccessPolicy(fb, _request);
		return this.NEW(mapping, form, _request, _response);
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("CANCEL");		 	   	
	}
	
}
