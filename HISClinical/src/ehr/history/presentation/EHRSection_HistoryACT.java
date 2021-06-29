/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.history.presentation;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.utility.generictemplate.GenericTemplateConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class EHRSection_HistoryACT extends CSRFGardTokenAction
{
	
	public ActionForward PATCLINICALDOC_HISTORY_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_HistoryFB fb = (EHRSection_HistoryFB) form;
		EHRSection_HistoryUTL.setTemplateTileEssentials(fb, request);
		fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
		EHRSection_HistoryUTL.setTempReportEssentials(fb, request);
		fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
		EHRSection_HistoryUTL.setViewReportEssentials(fb, request);
		return mapping.findForward("HISTORY");
		
	}
	
	public ActionForward PATCLINICALDOC_HISTORY_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_HistoryFB fb = (EHRSection_HistoryFB) form;
		return mapping.findForward("PREVIEW");
	}	
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		EHRSection_HistoryFB fb = (EHRSection_HistoryFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		EHRSection_HistoryUTL.setTemplateTileEssentials(fb, request);
		fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
		EHRSection_HistoryUTL.setTempReportEssentials(fb, request);
		fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
		EHRSection_HistoryUTL.setViewReportEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	// Saving Clinical Data
		public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			validateToken(request,response);
			EHRSection_HistoryFB fb = (EHRSection_HistoryFB) form;
			EHRSection_HistoryUTL.savePatientClinicalData(fb, request);
			EHRSection_HistoryUTL.setTemplateTileEssentials(fb, request);
			fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
			EHRSection_HistoryUTL.setTempReportEssentials(fb, request);
			fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
			EHRSection_HistoryUTL.setViewReportEssentials(fb, request);
			return null;
		}
		
		
		public ActionForward PATCLINICALDOC_HISTORY_ADDSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
				return mapping.findForward("ADDSAVE");
		}
		
		//Added by Vasu on 03.Dec.2018
		public ActionForward PATCLINICALDOC_PAT_HISTORY_SELECT_OLD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			generateToken(request);
			EHRSection_HistoryFB fb = (EHRSection_HistoryFB) form;
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			EHRSection_HistoryUTL.setTemplateTileEssentials(fb, request);
			fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
			EHRSection_HistoryUTL.setTempReportEssentials(fb, request);
			fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
			EHRSection_HistoryUTL.setViewReportEssentials(fb, request);
			return mapping.findForward("HISTORY");
		}
		
		//Added by Vasu on 03.Dec.2018
		public ActionForward PATCLINICALDOC_PAT_HISTORY_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{					
		    EHRSection_HistoryFB fb = (EHRSection_HistoryFB) form;
			EHRSection_HistoryUTL.getTemplateTileEssentials(fb, request);
			return mapping.findForward("NEW");
		}
		
		public ActionForward PAT_HISTORY_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{

			EHRSection_HistoryFB fb = (EHRSection_HistoryFB) form;
			EHRSection_HistoryUTL.getEssentialDataToPopulate(fb, request,response);
			return null;
		}
}