/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.serviceprocedure.presentation;

import java.sql.SQLException;

import hisglobal.backutil.exception.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import investigationDesk.InvestigationConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ehr.chronicdisease.presentation.EHRSection_ChronicDiseaseFB;
import ehr.chronicdisease.presentation.EHRSection_ChronicDiseaseUTL;
import ehr.patientreferral.presentation.EHRSection_PatientReferralFB;
import ehr.patientreferral.presentation.EHRSection_PatientReferralUTL;

public class EHRSection_ServiceProcedureACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.PATCLINICALDOC_SERVICE_PROCEDURE_SELECT(mapping, form, request, response);
	}

	
	public ActionForward PATCLINICALDOC_SERVICE_PROCEDURE_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		EHRSection_ServiceProcedureFB fb = (EHRSection_ServiceProcedureFB) form;
		EHRSection_ServiceProcedureUTL.getEssentialData(fb, request,response);
		
			return mapping.findForward("NEW");
	}
	
	public ActionForward SERVICE_PROCEDURE_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		EHRSection_ServiceProcedureFB fb = (EHRSection_ServiceProcedureFB) form;
		EHRSection_ServiceProcedureUTL.getEssentialDataToPopulate(fb, request,response);
		return null;
	}		
}