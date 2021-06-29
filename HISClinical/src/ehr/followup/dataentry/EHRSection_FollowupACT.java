package ehr.followup.dataentry;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.DischargeRequestUTL;
import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ehr.statusdischarge.dataentry.EHRSection_StatusAtDischargeFB;
import ehr.statusdischarge.dataentry.EHRSection_StatusAtDischargeUTL;

public class EHRSection_FollowupACT extends CSRFGardTokenAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		//System.out.println("swati");
		return this.NEW(mapping, form, request, response);
	}

	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		//System.out.println("sstt");
			return mapping.findForward("NEW");
	}
	
	
	public ActionForward FOLLOWUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_FollowupFB fb = (EHRSection_FollowupFB) form;
		//EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		//fb.reset(mapping, request);
		//System.out.println("FOLLOWUP::::::");
		EHRSection_FollowupUTL.getEssentials(fb, request,response);
		return mapping.findForward("FOLLOWUP");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		EHRSection_FollowupFB fb = (EHRSection_FollowupFB) form;
		//System.out.println("FOLLOW SAVE");
		EHRSection_FollowupUTL.saveDetails(request,response,fb);
		return null;
		
	}
	
/*PAT CLINICAL DOCUMENTS SECTION */
	
	
	public ActionForward PATCLINICALDOC_FOLLOWUPADVICE_ADDSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_FollowupFB fb = (EHRSection_FollowupFB) form;
		//EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		//System.out.println("final1");
		//fb.reset(mapping, request);
		EHRSection_FollowupUTL.getEssentials(fb, request,response);
		return mapping.findForward("FOLLOWUP");
	}

	/*
	 * For Adding 'Diagnosis' key 'ENCDIAGNOSIS' Composition for Patient Document 
	 * */
	public ActionForward PATCLINICALDOC_FOLLOWUPADVICE_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		 EHRSection_FollowupFB fb = (EHRSection_FollowupFB) form;
		 //fb.reset(mapping, request);
		 //System.out.println("final2");
		 //System.out.println("jhellof1");
		 EHRSection_FollowupUTL.getEssentials(fb, request,response);
		 return mapping.findForward("SELECT");
		 //return mapping.findForward("FOLLOWUP");
	}
	
	public ActionForward PATCLINICALDOC_FOLLOWUPADVICE_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		//System.out.println("final3");
		return mapping.findForward("PREVIEW");
	}
	
	public ActionForward FOLLOWUPADVICE_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		
		EHRSection_FollowupFB fb = (EHRSection_FollowupFB) form;
		//System.out.println("sagar");
		EHRSection_FollowupUTL.saveDetails(request,response,fb);
		return null;
		
	}
	
	//Added by Vasu on 31.Oct.2018
	
	public ActionForward PATCLINICALDOC_ENC_FOLLOWUP_OPD_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_FollowupFB fb = (EHRSection_FollowupFB) form;
		//EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		//fb.reset(mapping, request);
		//System.out.println("SINGLEDATA");
		EHRSection_FollowupUTL.getEssentials(fb, request,response);
		return mapping.findForward("FOLLOWUP");
	}
	
	//Added by Vasu on 12.Dec.2018 
	public ActionForward PATCLINICALDOC_ENC_FOLLOWUP_DISCHARGE_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		EHRSection_FollowupFB fb = (EHRSection_FollowupFB) form;
		//System.out.println("save....");
		//EHRSection_FollowupUTL.saveDetails(request,response,fb);sysout
		EHRSection_FollowupUTL.saveDischargeFollowUpDetails(request,response,fb);
		EHRSection_FollowupUTL.getDischargeFollowUpEssentials(fb,request,response);
		//EHRSection_FollowupUTL.getEssentials(fb, request,response);
		return null;
		
	}
	//Added by Vasu on 18.Dec.2018
	public ActionForward PATCLINICALDOC_ENC_FOLLOWUP_DISCHARGE_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_FollowupFB fb = (EHRSection_FollowupFB) form;
		//EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		//fb.reset(mapping, request);
		//System.out.println("final3");
		EHRSection_FollowupUTL.getPatientStatus(fb,request);
		EHRSection_FollowupUTL.getDischargeFollowUpEssentials(fb,request,response);
		fb.setNextVisitDateFlag(InpatientConfig.NEXT_VISIT_SELECTION_DAYS);
		return mapping.findForward("DISCHARGEFOLLOWUP");
	}
	
	//Added by 
	public ActionForward PATCLINICALDOC_ENC_FOLLOWUP_OPD_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		//validateToken(request,response);
		EHRSection_FollowupFB fb = (EHRSection_FollowupFB) form;
		//System.out.println("final5");
		EHRSection_FollowupUTL.saveDetails(request,response,fb);
		EHRSection_FollowupUTL.getEssentials(fb,request,response);
		return null;
		
	}
}
