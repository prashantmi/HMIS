/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/

package ehr.treatmentgiven.presentation;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ehr.diagnosis.presentation.EHRSection_DiagnosisFB;
import ehr.diagnosis.presentation.EHRSection_DiagnosisUTL;
import ehr.treatmentgiven.presentation.EHRSection_TreatmentGivenFB;
import ehr.treatmentgiven.presentation.EHRSection_TreatmentGivenUTL;

public class EHRSection_TreatmentGivenACT extends CSRFGardTokenAction
{
	
	/*public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}*/

	/*public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return this.ENCTREATMENTGIVEN(mapping, form, request, response);
	}*/
	
	public ActionForward PATCLINICALDOC_TREATMENTGIVEN_ADDSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		EHRSection_TreatmentGivenFB fb = (EHRSection_TreatmentGivenFB) form;
		fb.reset(mapping, request);
		EHRSection_TreatmentGivenUTL.getPatientEncTreatmentGivenDetail(fb, request);
		return mapping.findForward("TREATMENTGIVEN");
		
	}
	
	public ActionForward PATCLINICALDOC_TREATMENTGIVEN_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		EHRSection_TreatmentGivenFB fb = (EHRSection_TreatmentGivenFB) form;
		fb.reset(mapping, request);
		EHRSection_TreatmentGivenUTL.getPatientEncTreatmentGivenDetail(fb, request);
		return mapping.findForward("ENCTREATMENTGIVEN");
		
	}
	
	public ActionForward TREATMENTGIVEN_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		generateToken(request);
		EHRSection_TreatmentGivenFB fb = (EHRSection_TreatmentGivenFB) form;
		fb.reset(mapping, request);
		EHRSection_TreatmentGivenUTL.saveDetails(request,response,fb);
		return null;
		
	}
	
	public ActionForward PATCLINICALDOC_TREATMENTGIVEN_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		return mapping.findForward("PREVIEW");
	}
	
}