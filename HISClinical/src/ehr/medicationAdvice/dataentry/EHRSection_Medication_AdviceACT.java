package ehr.medicationAdvice.dataentry;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ehr.medicationAdvice.dataentry.EHRSection_Medication_AdviceFB;
import ehr.medicationAdvice.dataentry.EHRSection_Medication_AdviceUTL;;

public class EHRSection_Medication_AdviceACT extends CSRFGardTokenAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		return mapping.findForward("NEW");
	}
	public ActionForward PATCLINICALDOC_ENC_MED_ADV_TEXT_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//System.out.println("Medication action called.......");
		EHRSection_Medication_AdviceFB fb = (EHRSection_Medication_AdviceFB) form;
		//fb.reset(mapping, request);
		EHRSection_Medication_AdviceUTL.getEssentials(fb, request,response);
		return mapping.findForward("NEW");
	}
	public ActionForward PATCLINICALDOC_ENC_MED_ADV_TEXT_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		//validateToken(request,response);
		EHRSection_Medication_AdviceFB fb = (EHRSection_Medication_AdviceFB) form;
		EHRSection_Medication_AdviceUTL.saveDetails( request, response, fb);
		EHRSection_Medication_AdviceUTL.getEssentials(fb, request,response);
		return null;
		
	}
	
}
