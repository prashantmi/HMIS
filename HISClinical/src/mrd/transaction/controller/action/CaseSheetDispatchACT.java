package mrd.transaction.controller.action;

/**
 * @author Pawan Kumar B N
 * Creation Date 05-Jul-2012
 */
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.CaseSheetDispatchFB;
import mrd.transaction.controller.utl.CaseSheetDispatchUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * this process is used to ready the case sheet for dispatch 
 * 
 */
public class CaseSheetDispatchACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		WebUTIL.refreshTransState(request);	
		//ControllerUTIL.setSysdate(request);
		fb.setHmode("NEW");
		//fb.setTempMode(fb.getHmode());
		fb.reset(mapping, request);
		CaseSheetDispatchUTL.getDeptUnitList(fb,request);
		fb.setDepartmentUnitCode("%");
		fb.setPatAdmNo("");
		fb.setStrCrNo("");
	//	return this.UNIT(mapping, form, request, response);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		//ControllerUTIL.setSysdate(request);
		CaseSheetDispatchUTL.getWardOnBasisOfUnitCode(fb, request);
		fb.setHmode("UNIT");
		//fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SHOWLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		fb.setCurrentPage(1);
		fb.setHmode("SHOWLIST");
		CaseSheetDispatchUTL.getCaseSheetListToReady(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SHOWPATIENTDETAILS(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		CaseSheetDispatchUTL.getAllEnclosureDetails(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETESSENTIALS(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		CaseSheetDispatchUTL.getAllEnclosureDetails(fb,request);
		return mapping.findForward("POPUP");
	}
	
	
	//added by swati
	//date:01-may-2019
	public ActionForward SHOWLISTADMNOWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		//fb.setCurrentPage(1);
		fb.setHmode("SHOWLISTADMNOWISE");
		CaseSheetDispatchUTL.getCaseSheetListToReadyADMNOWISE(fb,request);
		return mapping.findForward("NEW");
	}
	
	//added by swati sagar
	//date:02-05-2019
	public ActionForward SHOWLISTCRNOWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		fb.setHmode("SHOWLISTCRNOWISE");
		CaseSheetDispatchUTL.getCaseSheetListToReadyCRNOWISE(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward ADDENCLOSURES(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		CaseSheetDispatchUTL.addEnclosures(fb,request);
		CaseSheetDispatchUTL.getAllEnclosureDetails(fb,request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward GETCHECKLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		CaseSheetDispatchUTL.getChecklistForCaseSheet(fb,request);
		return mapping.findForward("CHECKLIST");
	}
	
	public ActionForward ADDCHECKLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		CaseSheetDispatchUTL.addChecklist(fb,request);
		return mapping.findForward("CHECKLIST");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		CaseSheetDispatchUTL.saveCaseSheetDispatch(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	 
	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		WebUTIL.setAttributeInSession(request,MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP,null);
		WebUTIL.setAttributeInSession(request,MrdConfig.SELECTED_CHECKLIST_VO_LIST,null);
		WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY,null);
		CaseSheetDispatchFB fb=(CaseSheetDispatchFB)form;
		fb.setPatAdmNo("");
		fb.setStrCrNo("");
		Status objStatus= new Status();
		objStatus.add(Status.TRANSINPROCESS);
		request.setAttribute(Config.STATUS_OBJECT, objStatus);
		fb.setHmode("custom");
		return mapping.findForward("NEW");
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("CANCEL");
		
	}

	/*Action mainly called for pagination*/
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CaseSheetDispatchFB fb = (CaseSheetDispatchFB) form;
		Status objStatus= new Status();
		
		objStatus.add(Status.TRANSINPROCESS);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		//fb.setCurrentPage(1);
		return mapping.findForward("NEW");
	}
}
