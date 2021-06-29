package opd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdCsultyPatientBelongingFB;
import opd.transaction.controller.util.OpdCsultyPatientBelongingUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdCsultyPatientBelongingACTION extends CSRFGardTokenAction
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.GETCRNO(mapping, form, request, response);
	}

	// Call Directly
	public ActionForward GETCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		
		Status objStatus = new Status();
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		
		fb.setHmode("GETCRNO");
		return mapping.findForward("NEW");
	}

	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		WebUTIL.refreshTransState(request);
		OpdCsultyPatientBelongingUTIL.getPatientBelongingDirectEssentials(fb,request);
		return mapping.findForward("NEW");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		WebUTIL.refreshTransState(request);
		return mapping.findForward("CANCEL");
	}
	
	// Call From Desk
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		OpdCsultyPatientBelongingUTIL.getPatientBelongingEssentials(fb, request);
		
		fb.setIsDirectCall("DESK");
		return mapping.findForward("NEW");
	}

	public ActionForward ADDDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		OpdCsultyPatientBelongingUTIL.addDetailRow(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward REMOVEDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		OpdCsultyPatientBelongingUTIL.removeDetailRow(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		OpdCsultyPatientBelongingUTIL.saveBelongingDetails(fb, request);

		if(fb.getIsDirectCall().equals("DIRECT"))
			return this.GETPATDTL(mapping, form, request, response);
		else
			return this.NEW(mapping, form, request, response);
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		Status objStatus = new Status();
		fb.setSelectedMode(fb.getHmode());
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYBELONGING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//validateToken(request,response);
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		OpdCsultyPatientBelongingUTIL.modifyBelonging(fb, request);
		fb.setSelectedMode("MODIFY");
		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		OpdCsultyPatientBelongingUTIL.saveModifyDetails(fb, request);
		fb.reset(mapping, request);
		fb.setSelectedMode("MODIFY");
		return mapping.findForward("MODIFY");
	}

	public ActionForward HANDOVER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		Status objStatus = new Status();
		fb.setSelectedMode(fb.getHmode());
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("MODIFY");
	}

	public ActionForward HANDOVERSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		OpdCsultyPatientBelongingUTIL.saveHandOverDetails(fb, request);

		if(fb.getIsDirectCall().equals("DIRECT"))
			return this.GETPATDTL(mapping, form, request, response);
		else
			return this.NEW(mapping, form, request, response);
	}

	public ActionForward HANDOVERLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdCsultyPatientBelongingFB fb = (OpdCsultyPatientBelongingFB) form;
		fb.setSelectedMode(fb.getHmode());
		return mapping.findForward("MODIFY");
	}
}
