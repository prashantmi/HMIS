/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.complaints.presentation;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.utility.generictemplate.GenericTemplateConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;




import ehr.examination.presentation.EHRSection_ExaminationFB;
import ehr.examination.presentation.EHRSection_ExaminationUTL;
import ehr.patientTile.dataentry.EHRSection_PatientTileFB;

public class EHRSection_ComplaintsACT extends CSRFGardTokenAction
{
	
	public ActionForward PATCLINICALDOC_COMPLAINTS_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_ComplaintsFB fb = (EHRSection_ComplaintsFB) form;
		EHRSection_ComplaintsUTL.setTemplateTileEssentials(fb, request);
		fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
		EHRSection_ComplaintsUTL.setTempReportEssentials(fb, request);
		fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
		EHRSection_ComplaintsUTL.setViewReportEssentials(fb, request);
		return mapping.findForward("COMPLAINTS");
		
	}
	
	public ActionForward PATCLINICALDOC_COMPLAINTS_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_ComplaintsFB fb = (EHRSection_ComplaintsFB) form;
		return mapping.findForward("PREVIEW");
	}
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		EHRSection_ComplaintsFB fb = (EHRSection_ComplaintsFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		EHRSection_ComplaintsUTL.setTemplateTileEssentials(fb, request);
		fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
		EHRSection_ComplaintsUTL.setTempReportEssentials(fb, request);
		fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
		EHRSection_ComplaintsUTL.setViewReportEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	// Saving Clinical Data
		public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			validateToken(request,response);
			EHRSection_ComplaintsFB fb = (EHRSection_ComplaintsFB) form;
			EHRSection_ComplaintsUTL.savePatientClinicalData(fb, request);
			EHRSection_ComplaintsUTL.setTemplateTileEssentials(fb, request);
			fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
			EHRSection_ComplaintsUTL.setTempReportEssentials(fb, request);
			fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
			EHRSection_ComplaintsUTL.setViewReportEssentials(fb, request);
			return null;
		}
		
		
		public ActionForward PATCLINICALDOC_COMPLAINTS_ADDSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
				return mapping.findForward("ADDSAVE");
		}
		
		
		//Added by Vasu on 03.Dec.2018
		public ActionForward PATCLINICALDOC_ENC_COMPLAINTS_SELECT_OLD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			generateToken(request);
			EHRSection_ComplaintsFB fb = (EHRSection_ComplaintsFB) form;
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			EHRSection_ComplaintsUTL.setTemplateTileEssentials(fb, request);
			fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
			EHRSection_ComplaintsUTL.setTempReportEssentials(fb, request);
			fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
			EHRSection_ComplaintsUTL.setViewReportEssentials(fb, request);
			return mapping.findForward("NEW");
		}
		
		public ActionForward PATCLINICALDOC_ENC_COMPLAINTS_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			EHRSection_ComplaintsFB fb = (EHRSection_ComplaintsFB) form;
			EHRSection_ComplaintsUTL.getTemplateTileEssentials(fb, request);
			return mapping.findForward("NEW");
		}
		
		public ActionForward ENC_COMPLAINTS_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{

			EHRSection_ComplaintsFB fb = (EHRSection_ComplaintsFB) form;
			EHRSection_ComplaintsUTL.getEssentialDataToPopulate(fb, request,response);
			return null;
		}
}