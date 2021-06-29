package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.ANCTrimesterChecklistDetailFB;
import inpatient.transaction.controller.utl.ANCTrimesterChecklistDetailUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ANCTrimesterChecklistDetailACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		ANCTrimesterChecklistDetailFB fb = (ANCTrimesterChecklistDetailFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		ANCTrimesterChecklistDetailUTL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ALLCHECKLISTSHOW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ANCTrimesterChecklistDetailFB fb = (ANCTrimesterChecklistDetailFB) form;
		ANCTrimesterChecklistDetailUTL.getAllTrimesterChecklistPopup(fb, request);
		return mapping.findForward("ALLCHKLSTPOPUP");
	}
	
	public ActionForward ADDDRUGROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ANCTrimesterChecklistDetailFB fb = (ANCTrimesterChecklistDetailFB) form;
		ANCTrimesterChecklistDetailUTL.addDrugRow(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward DELETEDRUGROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ANCTrimesterChecklistDetailFB fb = (ANCTrimesterChecklistDetailFB) form;
		ANCTrimesterChecklistDetailUTL.deleteDrugRow(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward ADDINVSTROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ANCTrimesterChecklistDetailFB fb = (ANCTrimesterChecklistDetailFB) form;
		ANCTrimesterChecklistDetailUTL.addInvstRow(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward DELETEINVSTROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ANCTrimesterChecklistDetailFB fb = (ANCTrimesterChecklistDetailFB) form;
		ANCTrimesterChecklistDetailUTL.deleteInvstRow(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ANCTrimesterChecklistDetailFB fb = (ANCTrimesterChecklistDetailFB) form;
		ANCTrimesterChecklistDetailUTL.saveANCTrimesterChklstDetail(fb, request);		
		return NEW(mapping, form, request, response);
	}
}
