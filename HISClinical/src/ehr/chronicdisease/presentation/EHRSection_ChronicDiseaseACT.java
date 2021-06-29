/**
##		Date					: 14-05-2019
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Prachi
*/


package ehr.chronicdisease.presentation;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ehr.chronicdisease.presentation.EHRSection_ChronicDiseaseFB;


public class EHRSection_ChronicDiseaseACT extends CSRFGardTokenAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.PATCLINICALDOC_PAT_CHRONIC_DISEASE_SELECT(mapping, form, request, response);
	}

	
	public ActionForward PATCLINICALDOC_PAT_CHRONIC_DISEASE_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// Fetch Data
		
		EHRSection_ChronicDiseaseFB fb = (EHRSection_ChronicDiseaseFB) form;
		EHRSection_ChronicDiseaseUTL.getEssentialData(fb, request,response);
		
		// Set in EHRVO
		// Get FTL related from Session 
		// Generate FTL 
		// Set in FB Common Field
			return mapping.findForward("NEW");
	}
	
	public ActionForward PAT_CHRONIC_DISEASE_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		EHRSection_ChronicDiseaseFB fb = (EHRSection_ChronicDiseaseFB) form;
		EHRSection_ChronicDiseaseUTL.getEssentialDataToPopulate(fb, request,response);
		return null;
	}
}
	