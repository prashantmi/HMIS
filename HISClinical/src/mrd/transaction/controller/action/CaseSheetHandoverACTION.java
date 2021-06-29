package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.CaseSheetDispatchFB;
import mrd.transaction.controller.fb.CaseSheetHandoverFB;
import mrd.transaction.controller.utl.CaseSheetDispatchUTL;
import mrd.transaction.controller.utl.CaseSheetHandoverUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CaseSheetHandoverACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
		WebUTIL.refreshTransState(request);	
		//ControllerUTIL.setSysdate(request);
				fb.setHmode("NEW");
				//fb.setTempMode(fb.getHmode());
				fb.reset(mapping, request);
				CaseSheetHandoverUTL.getDeptUnitList(fb,request);
				fb.setDepartmentUnitCode("%");
				fb.setPatAdmNo("");
				fb.setStrCrNo("");
			//	return this.UNIT(mapping, form, request, response);
				return mapping.findForward("NEW");
	}
	
	public ActionForward UNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
		//ControllerUTIL.setSysdate(request);
		CaseSheetHandoverUTL.getWardOnBasisOfUnitCode(fb, request);
		fb.setHmode("UNIT");
		//fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	    //added by swati
		//date:01-may-2019
//		public ActionForward SHOWLISTADMNOWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
//		{
//			CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
//			//fb.setCurrentPage(1);
//			fb.setHmode("SHOWLISTADMNOWISE");
//			CaseSheetHandoverUTL.getCaseSheetListToReadyADMNOWISE(fb,request);
//			return mapping.findForward("NEW");
//		}
		
		//added by swati sagar
		//date:02-05-2019
//		public ActionForward SHOWLISTCRNOWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
//		{
//			CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
//			fb.setHmode("SHOWLISTCRNOWISE");
//			CaseSheetHandoverUTL.getCaseSheetListToReadyCRNOWISE(fb,request);
//			return mapping.findForward("NEW");
//		}
		

	public ActionForward SEARCHBYCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
		WebUTIL.refreshTransState(request);	
		//ControllerUTIL.setSysdate(request);
		fb.reset(mapping, request);
		fb.setSelection("1");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
		boolean flag=CaseSheetHandoverUTL.getPatientCaseSheetDtlByCrNo(fb,request);
		//fb.setSelection("1");
		if(!flag){
			return this.SEARCHBYCRNO(mapping, form, request, response);
		}
		fb.setIsAccept("1");
		return mapping.findForward("NEW");
	}
	//added bny swati  on date:06-may-2019
	//adm no wise dtl
	
	public ActionForward SHOWLISTADMNOWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
		fb.setCurrentPage(1);
		fb.setHmode("SHOWLISTADMNOWISE");
		CaseSheetHandoverUTL.getAdmNoWiseDtl(fb, request);
		return mapping.findForward("NEW");
		
		
	}
	
	
	//added bny swati  on date:07-may-2019
		//CR no wise dtl
		
		public ActionForward SHOWLISTCRNOWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
			fb.setCurrentPage(1);
			fb.setHmode("SHOWLISTCRNOWISE");
			CaseSheetHandoverUTL.getCrNoWiseDtl(fb, request);
			return mapping.findForward("NEW");


		}
	
	
	public ActionForward GETRECORDDISPATCHLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
		CaseSheetHandoverUTL.getRecordDispatchList(fb, request);
		fb.setIsAccept("1");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETENCLOSURES(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
		CaseSheetHandoverUTL.getAllEnclosureDetails(fb, request);
		return mapping.findForward("ENCLOSURE");
	}
	
	public ActionForward ADDENCLOSURES(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
		CaseSheetHandoverUTL.addEnclosures(fb, request);
		return mapping.findForward("ENCLOSURE");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CaseSheetHandoverFB fb=(CaseSheetHandoverFB)form;
		CaseSheetHandoverUTL.save(fb, request);
		return this.NEW(mapping, form, request, response);
	}
	
	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		WebUTIL.setAttributeInSession(request,MrdConfig.CASESHEET_ENCLOSURE_CHECKLIST_MAP,null);
		Status objStatus= new Status();
		objStatus.add(Status.RECORDFOUND);
		request.setAttribute(Config.STATUS_OBJECT, objStatus);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
			return mapping.findForward("CANCEL");
		
	}

	/*Action called for pagination*/
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CaseSheetHandoverFB fb = (CaseSheetHandoverFB) form;
		Status objStatus= new Status();
		CaseSheetHandoverUTL.setSelectedChecklist(fb, request);
		objStatus.add(Status.RECORDFOUND);
		//objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}
}
