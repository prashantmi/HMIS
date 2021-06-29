package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.MrdMedicalCampFB;
import mrd.transaction.controller.fb.MrdRecordIssueFB;
import mrd.transaction.controller.utl.MrdMedicalCampUTL;
import mrd.transaction.controller.utl.MrdRecordIssueUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class mrdMedicalCampACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		MrdMedicalCampFB fb=(MrdMedicalCampFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		MrdMedicalCampUTL.getCampListForMedicalCamp(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDCAMPDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		System.out.println("mrdMedicalCampACTION.ADDCAMPDETAIL()");
		MrdMedicalCampFB fb=(MrdMedicalCampFB)form;
		MrdMedicalCampUTL.getCampEmpNameForMedicalCamp(fb,request);
		return mapping.findForward("ADDCAMPDETAIL");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		System.out.println("mrdMedicalCampACTION.SAVE()");
		MrdMedicalCampFB fb=(MrdMedicalCampFB)form;
		if(MrdMedicalCampUTL.saveCampDetail(fb,request))
		{
			WebUTIL.refreshTransState(request);	
			fb.reset(mapping, request);
			MrdMedicalCampUTL.getCampListForMedicalCamp(fb,request);
			Status objStatus = new Status();
			objStatus.add(Status.NEW,"","Camp Added Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYCAMPDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		System.out.println("mrdMedicalCampACTION.MODIFYCAMPDETAIL()");		
		MrdMedicalCampFB fb=(MrdMedicalCampFB)form;
		System.out.println("Selected Camp :"+fb.getStrCampId());
		MrdMedicalCampUTL.getCampEmpNameForMedicalCamp(fb,request);
		MrdMedicalCampUTL.getCampDetail(fb,request);
		return mapping.findForward("MODIFYCAMPDETAIL");
	}	
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MrdMedicalCampFB fb=(MrdMedicalCampFB)form;
		System.out.println("mrdMedicalCampACTION.UPDATE()");
		System.out.println("Selected Camp :"+fb.getStrCampId());
		if(MrdMedicalCampUTL.updateCampDetail(fb,request))
		{
			WebUTIL.refreshTransState(request);	
			fb.reset(mapping, request);
			MrdMedicalCampUTL.getCampListForMedicalCamp(fb,request);
			Status objStatus = new Status();
			objStatus.add(Status.NEW,"","Camp Updated Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}
	
	public ActionForward VIEWCAMPDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		System.out.println("mrdMedicalCampACTION.VIEWCAMPDETAIL()");	
		MrdMedicalCampFB fb=(MrdMedicalCampFB)form;
		System.out.println("Selected Camp :"+fb.getStrCampId());
		MrdMedicalCampUTL.getCampEmpNameForMedicalCamp(fb,request);
		MrdMedicalCampUTL.getCampDetail(fb,request);
		return mapping.findForward("VIEWCAMPDETAIL");
	}
	
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("CANCEL");
		
	}
	
}
