/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.examination.presentation;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.utility.generictemplate.GenericTemplateConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ehr.history.presentation.EHRSection_HistoryFB;
import ehr.history.presentation.EHRSection_HistoryUTL;



public class EHRSection_ExaminationACT extends CSRFGardTokenAction
{
	
	public ActionForward PATCLINICALDOC_EXAMINATION_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_ExaminationFB fb = (EHRSection_ExaminationFB) form;
		EHRSection_ExaminationUTL.setTemplateTileEssentials(fb, request);
		fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
		EHRSection_ExaminationUTL.setTempReportEssentials(fb, request);
		fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
		EHRSection_ExaminationUTL.setViewReportEssentials(fb, request);
		return mapping.findForward("EXAMINATION");
		
	}
	
	public ActionForward PATCLINICALDOC_EXAMINATION_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_ExaminationFB fb = (EHRSection_ExaminationFB) form;
		return mapping.findForward("PREVIEW");
	}	
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		EHRSection_ExaminationFB fb = (EHRSection_ExaminationFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		EHRSection_ExaminationUTL.setTemplateTileEssentials(fb, request);
		fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
		EHRSection_ExaminationUTL.setTempReportEssentials(fb, request);
		fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
		EHRSection_ExaminationUTL.setViewReportEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	// Saving Clinical Data
		public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			//validateToken(request,response);
			EHRSection_ExaminationFB fb = (EHRSection_ExaminationFB) form;
			EHRSection_ExaminationUTL.savePatientClinicalData(fb, request);
			EHRSection_ExaminationUTL.setTemplateTileEssentials(fb, request);
			fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
			EHRSection_ExaminationUTL.setTempReportEssentials(fb, request);
			fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
			EHRSection_ExaminationUTL.setViewReportEssentials(fb, request);
			return null;
		}
		
		
		public ActionForward PATCLINICALDOC_EXAMINATION_ADDSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
				return mapping.findForward("ADDSAVE");
		}
		
		//Added by Vasu on 03.Dec.2018
		public ActionForward PATCLINICALDOC_ENC_EXAMINATION_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			generateToken(request);
			EHRSection_ExaminationFB fb = (EHRSection_ExaminationFB) form;
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			EHRSection_ExaminationUTL.setTemplateTileEssentials(fb, request);
			fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
			EHRSection_ExaminationUTL.setTempReportEssentials(fb, request);
			fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
			EHRSection_ExaminationUTL.setViewReportEssentials(fb, request);
			return mapping.findForward("NEW");
		}
		
		//Added by Vasu on 29.April.2019
		public ActionForward PATCLINICALDOC_ENC_EXAM_SELECT_OLD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			//generateToken(request);
			EHRSection_ExaminationFB fb = (EHRSection_ExaminationFB) form;
			EHRSection_ExaminationUTL.setTemplateTileEssentials(fb, request);
			fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
			EHRSection_ExaminationUTL.setTempReportEssentials(fb, request);
			fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
			EHRSection_ExaminationUTL.setViewReportEssentials(fb, request);
			return mapping.findForward("ADDSAVE");
		}
		public ActionForward PATCLINICALDOC_ENC_EXAM_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			EHRSection_ExaminationFB fb = (EHRSection_ExaminationFB) form;
			EHRSection_ExaminationUTL.getTemplateTileEssentials(fb, request);
			return mapping.findForward("NEW");
		}
		
		public ActionForward ENC_EXAM_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{

			EHRSection_ExaminationFB fb = (EHRSection_ExaminationFB) form;
			EHRSection_ExaminationUTL.getEssentialDataToPopulate(fb, request,response);
			return null;
		}
}