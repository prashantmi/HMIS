/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/

package ehr.diagnosis.presentation;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class EHRSection_DiagnosisACT extends CSRFGardTokenAction
{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return this.DESKDIAGNOSIS(mapping, form, request, response);
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
		//generateToken(request);
		EHRSection_DiagnosisFB fb = (EHRSection_DiagnosisFB) form;
		EHRSection_DiagnosisUTL.saveDetails(request,response,fb);
		return null;
		
	}
	
	public ActionForward PATCLINICALDOC_ENCDIAGNOSIS_ADDSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_DiagnosisFB fb = (EHRSection_DiagnosisFB) form;
		//fb.reset(mapping, request);
		EHRSection_DiagnosisUTL.getEssentials(fb, request,response);
		return mapping.findForward("DESKDIAGNOSIS");
	}

	/*
	 * For Adding 'Diagnosis' key 'ENCDIAGNOSIS' Composition for Patient Document 
	 * */
	public ActionForward PATCLINICALDOC_ENCDIAGNOSIS_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		EHRSection_DiagnosisFB fb = (EHRSection_DiagnosisFB) form;
		//fb.reset(mapping, request);
		EHRSection_DiagnosisUTL.getPatientEncDiagnosisDetail(fb, request);
		return mapping.findForward("ENCDIAGNOSIS");
		
	}
	
	public ActionForward PATCLINICALDOC_ENCDIAGNOSIS_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		return mapping.findForward("PREVIEW");
	}
	
	public ActionForward ENCDIAGNOSIS_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		//validateToken(request,response);
		//generateToken(request);
		EHRSection_DiagnosisFB fb = (EHRSection_DiagnosisFB) form;
		EHRSection_DiagnosisUTL.saveDetails(request,response,fb);
		return null;
		
	}
	
	/**Added by Vasu on 31.Oct.2018*/
	public ActionForward PATCLINICALDOC_ENC_DIAGNOSIS_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_DiagnosisFB fb = (EHRSection_DiagnosisFB) form;
		//fb.reset(mapping, request);
		if(EHRSection_DiagnosisUTL.getPatientEncDiagnosisDetail(fb, request));
		{
		EHRSection_DiagnosisUTL.getEssentials(fb, request,response);
		}
		return mapping.findForward("DESKDIAGNOSIS");
	}
	
	//Added by Vasu on 12.Dec.2018
	public ActionForward PATCLINICALDOC_ENC_DIAGNOSIS_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		//validateToken(request,response);
		//generateToken(request);
		EHRSection_DiagnosisFB fb = (EHRSection_DiagnosisFB) form;
		EHRSection_DiagnosisUTL.saveDetails(request,response,fb);
		if(EHRSection_DiagnosisUTL.getPatientEncDiagnosisDetail(fb, request));
		{
		EHRSection_DiagnosisUTL.getEssentials(fb, request,response);
		}
		return null;
	}
}