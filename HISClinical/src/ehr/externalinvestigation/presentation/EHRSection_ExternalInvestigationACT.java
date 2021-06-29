/**
##		Date					: 05-Aug-2019
##		Reason	(CR/PRS)		: External Investigation Section at SPD 
##		Created By				: Vasu
*/


package ehr.externalinvestigation.presentation;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.utility.generictemplate.GenericTemplateConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.transaction.controller.util.ExternalInvestigationUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ehr.history.presentation.EHRSection_HistoryFB;
import ehr.history.presentation.EHRSection_HistoryUTL;
import ehr.treatmentdetail.dataentry.EHRSection_TreatmentFB;
import ehr.treatmentdetail.dataentry.EHRSection_TreatmentUTL;



public class EHRSection_ExternalInvestigationACT extends CSRFGardTokenAction
{
	
	public ActionForward PATCLINICALDOC_EXT_INV_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_ExternalInvestigationFB fb = (EHRSection_ExternalInvestigationFB) form;
		
		fb.setTestConductedFrom(OpdConfig.TEST_CONDUCTED_IN_HOUSE);
		ExternalInvestigationUTIL.setSysdate(request);
		EHRSection_ExternalInvestigationUTL.getParameterForExtInv(fb,request);
		
		return mapping.findForward("NEW");
		
	}
	
	public ActionForward PATCLINICALDOC_EXT_INV_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		EHRSection_ExternalInvestigationFB fb = (EHRSection_ExternalInvestigationFB) form;
		
		EHRSection_ExternalInvestigationUTL.saveExternalInvestigationDetails(request,response,fb);
		
		//EHRSection_TreatmentUTL.setEssentials(fb, request);
		return null;
		
	}
	
	public ActionForward DELETE_PREV_EXT_INV_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		EHRSection_ExternalInvestigationFB fb = (EHRSection_ExternalInvestigationFB) form;
		EHRSection_ExternalInvestigationUTL.deletePreviousExtInvestigations(fb, request,response);
		return null;
	}
	
}