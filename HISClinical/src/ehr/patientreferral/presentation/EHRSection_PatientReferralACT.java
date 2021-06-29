/**
##		Date					: 22-05-2019
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Vasu
*/


package ehr.patientreferral.presentation;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EHRSection_PatientReferralACT extends CSRFGardTokenAction
{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.PATCLINICALDOC_PAT_REFERRAL_SELECT(mapping, form, request, response);
	}

	
	public ActionForward PATCLINICALDOC_PAT_REFERRAL_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		EHRSection_PatientReferralFB fb = (EHRSection_PatientReferralFB) form;
		EHRSection_PatientReferralUTL.getEssentialData(fb, request,response);
		
			return mapping.findForward("REFERRAL");
	}
		
	public ActionForward PAT_REFERRAL_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		EHRSection_PatientReferralFB fb = (EHRSection_PatientReferralFB) form;
		EHRSection_PatientReferralUTL.getEssentialDataToPopulate(fb, request,response);
		return null;
	}
	
	//Added by Vasu on 16.Dec.2019
	public ActionForward PATCLINICALDOC_PAT_REFERRAL_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		EHRSection_PatientReferralFB fb = (EHRSection_PatientReferralFB) form;
		//EHRSection_PatientReferralUTL.getEssentialData(fb, request,response);
		EHRSection_PatientReferralUTL.savePatientReferralDetails(fb, request,response);
			return null;
	}
	
	
	   //Added by Vasu on 18.Dec.2019
		public ActionForward DELETE_PREV_PAT_REFERRAL_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{

			EHRSection_PatientReferralFB fb = (EHRSection_PatientReferralFB) form;
			String slNo = request.getParameter("slno");
			EHRSection_PatientReferralUTL.deletePreviousReferrals(fb, request,response,slNo);
			return null;
		}
		
}