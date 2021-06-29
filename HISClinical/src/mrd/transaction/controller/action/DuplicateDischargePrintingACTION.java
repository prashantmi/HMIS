package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.DuplicateDischargePrintingFB;
import mrd.transaction.controller.utl.DuplicateDischargePrintingUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DuplicateDischargePrintingACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		DuplicateDischargePrintingFB fb=(DuplicateDischargePrintingFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		DuplicateDischargePrintingFB fb=(DuplicateDischargePrintingFB)form;
		DuplicateDischargePrintingUTL.getDischargePatientByCrNo(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETDISCHARGEDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		DuplicateDischargePrintingFB fb=(DuplicateDischargePrintingFB)form;
		DuplicateDischargePrintingUTL.setDischargePrintSaveOption(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPRINTDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		DuplicateDischargePrintingFB fb=(DuplicateDischargePrintingFB)form;
		DuplicateDischargePrintingUTL.getDischargePrintDtl(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DuplicateDischargePrintingFB fb=(DuplicateDischargePrintingFB)form;
		DuplicateDischargePrintingUTL.saveDischargePrinting(fb, request);
		return this.GETPATDTL(mapping, form, request, response);
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward POPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("POPUP");
	}


}
