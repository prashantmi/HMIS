/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.allergies.presentation;

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

public class EHRSection_AllergiesACT extends CSRFGardTokenAction
{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.PATCLINICALDOC_PAT_ALLERGY_SELECT(mapping, form, request, response);
	}

	
	public ActionForward PATCLINICALDOC_PAT_ALLERGY_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		EHRSection_AllergiesFB fb = (EHRSection_AllergiesFB) form;
		EHRSection_AllergiesUTL.getEssentialData(fb, request,response);
				
		return mapping.findForward("NEW");
	}	
		
	public ActionForward PAT_ALLERGY_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		EHRSection_AllergiesFB fb = (EHRSection_AllergiesFB) form;
		EHRSection_AllergiesUTL.getEssentialDataToPopulate(fb, request,response);
		return null;
	}
}