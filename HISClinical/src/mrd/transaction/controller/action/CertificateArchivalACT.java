package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.CertificateArchivalFB;
import mrd.transaction.controller.utl.CertificateArchivalUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CertificateArchivalACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		CertificateArchivalFB fb=(CertificateArchivalFB)form;
		fb.reset(mapping, request);
		CertificateArchivalUTL.getRecordListToArchived(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETSHELF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		CertificateArchivalFB fb=(CertificateArchivalFB)form; 
		CertificateArchivalUTL.getShelfBasedOnRack(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CertificateArchivalFB fb=(CertificateArchivalFB)form; 
		CertificateArchivalUTL.saveRecordArchivalInMrd(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
			return mapping.findForward("CANCEL");
		
	}
}
