package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.DuplicateRecordPrintReqFB;
import mrd.transaction.controller.utl.DuplicateRecordPrintReqUTL;
import opd.transaction.controller.fb.EstimateRequestFB;
import opd.transaction.controller.util.EstimateRequestUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DuplicateRecordPrintRequestACT extends CSRFGardTokenAction {
	
	
		public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			return this.NEW(mapping, form, request, response);
		}
		
		public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			generateToken(request);
			DuplicateRecordPrintReqFB fb=(DuplicateRecordPrintReqFB)form;
			fb.reset(mapping, request);
			DuplicateRecordPrintReqUTL.getEssentialData(fb, request);
			return mapping.findForward("NEW");
		}
		
		public ActionForward CHANGEREQTYPE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			DuplicateRecordPrintReqFB fb=(DuplicateRecordPrintReqFB)form;
			if(fb.getReqType().equals("1"))
				return mapping.findForward("GETPATDTL");
			else
				return mapping.findForward("NEW");
		}

		public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			DuplicateRecordPrintReqFB fb=(DuplicateRecordPrintReqFB)form;
			
			return mapping.findForward("GETPATDTL");
		}
		
		
		public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			validateToken(request,response);
			DuplicateRecordPrintReqFB fb=(DuplicateRecordPrintReqFB)form;
			DuplicateRecordPrintReqUTL.saveDuplicateRecordPrintReqDtl(fb, request);
			return mapping.findForward("NEW");
		}
		
		public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			return mapping.findForward("NEW");
		}
}
