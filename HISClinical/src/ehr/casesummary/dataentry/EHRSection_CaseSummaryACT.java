package ehr.casesummary.dataentry;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ehr.statusdischarge.dataentry.EHRSection_StatusAtDischargeFB;
import ehr.statusdischarge.dataentry.EHRSection_StatusAtDischargeUTL;
import emr.vo.PatientClinicalDocDetailVO;
import ehr.EHRConfig;

public class EHRSection_CaseSummaryACT extends CSRFGardTokenAction {

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
		
	public ActionForward PATCLINICALDOC_ENC_CLN_DTL_CASE_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_CaseSummaryFB fb = (EHRSection_CaseSummaryFB) form;
		
		EHRSection_CaseSummaryUTL.getEssentials(fb, request,response);
		
		PatientClinicalDocDetailVO clinicalDocVO = new PatientClinicalDocDetailVO();
		HttpSession session = request.getSession();
		clinicalDocVO = (PatientClinicalDocDetailVO) request.getSession().getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
		
		if(clinicalDocVO.getDocumentType().equals("51"))
		{
			return mapping.findForward("CASESUMMARYOPD");
		}
		else
		{
		return mapping.findForward("CASESUMMARY");
		}
		//return null;
	}
	
	public ActionForward PATCLINICALDOC_ENC_CLN_DTL_CASE_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		EHRSection_CaseSummaryFB fb = (EHRSection_CaseSummaryFB) form;
		EHRSection_CaseSummaryUTL.saveDetails( request, response, fb);
		EHRSection_CaseSummaryUTL.getEssentials(fb, request,response);
		return null;
		
	}
	
}
