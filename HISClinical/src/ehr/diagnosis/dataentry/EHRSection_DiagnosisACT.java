/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.diagnosis.dataentry;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ehr.diagnosis.dataentry.EHRSection_DiagnosisUTL;
import ehr.diagnosis.dataentry.EHRSection_DiagnosisFB;

public class EHRSection_DiagnosisACT extends CSRFGardTokenAction
{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//generateToken(request);
			return mapping.findForward("NEW");
	}
	
	
	public ActionForward DESKDIAGNOSIS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_DiagnosisFB fb = (EHRSection_DiagnosisFB) form;
		//fb.reset(mapping, request);
		EHRSection_DiagnosisUTL.getEssentials(fb, request,response);
		return mapping.findForward("DESKDIAGNOSIS");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		//validateToken(request,response);
	//	generateToken(request);
		EHRSection_DiagnosisFB fb = (EHRSection_DiagnosisFB) form;
		EHRSection_DiagnosisUTL.saveDetails(request,response,fb);
		return null;
		
	}
	
	/*
	 * For Adding 'Diagnosis' key '' Composition for Patient Document 
	 * */
	public ActionForward PATCLINICALDOC_DESKDIAGNOSIS_ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		EHRSection_DiagnosisFB fb = (EHRSection_DiagnosisFB) form;
		//fb.reset(mapping, request);
		EHRSection_DiagnosisUTL.getEssentials(fb, request,response);
		return mapping.findForward("DESKDIAGNOSIS");
		
	}
}