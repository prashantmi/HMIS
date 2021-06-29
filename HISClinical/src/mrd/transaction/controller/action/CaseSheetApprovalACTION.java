package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.CaseSheetApprovalFB;
import mrd.transaction.controller.utl.CaseSheetApprovalUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CaseSheetApprovalACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		CaseSheetApprovalFB fb=(CaseSheetApprovalFB)form;
		WebUTIL.refreshTransState(request);	
		ControllerUTIL.setSysdate(request);
		fb.reset(mapping, request);
		//CaseSheetApprovalUTL.getDeptUnitList(fb,request);
		fb.setSelection("0");
		fb.setDepartmentUnitCode("%");
		CaseSheetApprovalUTL.getDeptUnitList(fb,request);
		return this.UNIT(mapping, form, request, response);
		//return mapping.findForward("NEW");
	}
	
	public ActionForward UNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetApprovalFB fb=(CaseSheetApprovalFB)form;
		CaseSheetApprovalUTL.getWardOnBasisOfUnitCode(fb, request);
		fb.setHmode("UNIT");
		return mapping.findForward("NEW");
	}

	public ActionForward SEARCHBYCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetApprovalFB fb=(CaseSheetApprovalFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		fb.setSelection("1");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetApprovalFB fb=(CaseSheetApprovalFB)form;
		boolean flag=CaseSheetApprovalUTL.getPatientCaseSheetDtlByCrNo(fb,request);
		if(!flag){
			return this.SEARCHBYCRNO(mapping, form, request, response);
		}
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETRECORDDISPATCHLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetApprovalFB fb=(CaseSheetApprovalFB)form;
		CaseSheetApprovalUTL.getRecordDispatchList(fb, request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CaseSheetApprovalFB fb=(CaseSheetApprovalFB)form;
		CaseSheetApprovalUTL.saveCaseSheetApprovalDetail(fb, request);
		return this.NEW(mapping, form, request, response);
	}
	
	
	
	public ActionForward POPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("POPUP");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("CANCEL");
		
	}

	/*Action called for pagination*/
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CaseSheetApprovalFB fb = (CaseSheetApprovalFB) form;
		Status objStatus= new Status();
		//CaseSheetApprovalUTL.setSelectedRecord(fb, request);
		objStatus.add(Status.RECORDFOUND);
		//objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTLByADMNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetApprovalFB fb=(CaseSheetApprovalFB)form;
		CaseSheetApprovalUTL.getPatientCaseSheetDtlByAdmNo(fb,request);
		fb.setSelection("2");
		fb.setIsAccept("1");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHBYADMNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetApprovalFB fb=(CaseSheetApprovalFB)form;
		WebUTIL.refreshTransState(request);	
		//ControllerUTIL.setSysdate(request);
		fb.reset(mapping, request);
		fb.setSelection("2");
		return mapping.findForward("NEW");

	}
}
