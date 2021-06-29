package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.DrugAdministrationFB;
import inpatient.transaction.controller.utl.DrugAdministrationUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DrugAdministrationACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		DrugAdministrationFB fb = (DrugAdministrationFB) form;
		fb.reset(mapping, request);
		fb.setDrugSource(InpatientConfig.SOURCE_OF_DRUG);
		fb.setDrugSourceFromPat(InpatientConfig.SOURCE_OF_DRUG_PATIENT);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		DrugAdministrationUTL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SHOWCOMPLETETREATMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//DrugAdministrationFB fb=(DrugAdministrationFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG);
		return mapping.findForward("SHOWCOMPLETETREATMENT");		
	}
	
	
	public ActionForward PREVDRUGSHEDULE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		DrugAdministrationUTL.getPrevDrugShedule(fb,request);
		return mapping.findForward("PREVDRUGSCHEDULE");		
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		DrugAdministrationUTL.saveDrugAdmin(fb,request);
		fb.reset(mapping, request);
		return this.NEW(mapping,form,request,response);
		//return mapping.findForward("NEW");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		fb.setViewTreatAdminChoice(InpatientConfig.DATEWISE_TREAT_INFO);
		DrugAdministrationUTL.getDrugInfo(fb,request);
		return mapping.findForward("DRUGGIVEN");	
	}
	
	public ActionForward DRUGGIVEN(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		DrugAdministrationUTL.getDrugInfo(fb,request);
		//fb.reset(mapping, request);
		return mapping.findForward("DRUGGIVEN");	
	}
	public ActionForward ADDDRUGEXEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		DrugAdministrationUTL.addNewDrugExeRow(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");		
	}
	public ActionForward ADDDACTIVITYROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		DrugAdministrationUTL.addNewActivityRow(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");		
	}
	public ActionForward DELETEDRUGEXEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		DrugAdministrationUTL.deleteDrugExeRow(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");		
	}
	public ActionForward DELETEDACTIVITYROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		DrugAdministrationUTL.deleteActivityRow(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");		
	}
	
	public ActionForward INSTRUCTION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//DrugAdministrationFB fb=(DrugAdministrationFB)form;
		//DrugAdministrationUTL.getIntruction(fb,request);
		return mapping.findForward("INSTRUCTION");	
	}
	
	public ActionForward OTHERTREATMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		fb.setChoice("0");
		//DrugAdministrationUTL.getIntruction(fb,request);
		return mapping.findForward("OTHERTREATMENT");	
	}
	/*
	
	
	
	public ActionForward DELETEIVFLUIDROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		DrugAdministrationUTL.deleteIvfluidRow(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");		
	}
	
	public ActionForward DRUGROUTE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		DrugAdministrationUTL.getDrugRoute(fb,request,response);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");		
	}
	
	
	
	public ActionForward SCHEDULESHIFTWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugAdministrationFB fb=(DrugAdministrationFB)form;
		DrugAdministrationUTL.getDrugSheduleShiftWise(fb,request);
		return mapping.findForward("SCHEDULESHIFTWISE");	
	}
	
	
	*/

}
