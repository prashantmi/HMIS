package inpatient.transaction.controller.action;

/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */

import inpatient.transaction.controller.fb.VitalsRecordingFB;
import inpatient.transaction.controller.utl.VitalsRecordingUTL;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.servlets.ServletsUtilityConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.util.GenericTemplateTileUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class VitalsRecordingACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	// Setting Essentials
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//generateToken(request);
		VitalsRecordingFB fb = (VitalsRecordingFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		GenericTemplateTileUTIL.setTemplateTileEssentials(fb, request);
		VitalsRecordingUTL.getEssentails(fb, request);
		return mapping.findForward("NEW");
	}

	// Saving Clinical Data
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		VitalsRecordingFB fb = (VitalsRecordingFB) form;
		if(GenericTemplateTileUTIL.savePatientClinicalData(fb, request))
		{
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			GenericTemplateTileUTIL.setTemplateTileEssentials(fb, request);
			VitalsRecordingUTL.getEssentails(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.TRANSINPROCESS,"Record Saved Successfully","");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
			
		}
		return mapping.findForward("NEW");
	}

	public ActionForward VITALCHART(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		return mapping.findForward("VITALCHART");
	}	

	public ActionForward VITALCHARTPRINT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		VitalsRecordingFB fb= (VitalsRecordingFB)form;
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getHtmlVitalChartData());
		// Water Marking
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());
		return mapping.findForward("VITALCHART");
	}	
}
