package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.servlets.ServletsUtilityConfig;
import inpatient.transaction.controller.fb.MonitorVitalsFB;
import inpatient.transaction.controller.utl.MonitorVitalsUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class MonitorVitalsACT extends CSRFGardTokenAction 
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		MonitorVitalsFB fb=(MonitorVitalsFB)form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.reset(mapping, request);
		MonitorVitalsUTL.getEssentails(fb, request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		validateToken(request,response);
		MonitorVitalsFB fb= (MonitorVitalsFB)form;
		MonitorVitalsUTL.saveDetail(fb, request);
		fb.reset(mapping,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward ADDROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		MonitorVitalsFB fb= (MonitorVitalsFB)form;
		MonitorVitalsUTL.addRow(fb, request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}	
	
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		MonitorVitalsFB fb= (MonitorVitalsFB)form;
		MonitorVitalsUTL.deleteRow(fb, request);
		return mapping.findForward("NEW");
	}	
	
	public ActionForward REVOKE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		validateToken(request,response);
		MonitorVitalsFB fb= (MonitorVitalsFB)form;
		MonitorVitalsUTL.revokeVitals(fb, request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward VITALCHART(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		return mapping.findForward("VITALCHART");
	}	

	public ActionForward VITALCHARTPRINT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		MonitorVitalsFB fb= (MonitorVitalsFB)form;
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getHtmlVitalChartData());
		// Water Marking
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());
		return mapping.findForward("VITALCHART");
	}	
}
