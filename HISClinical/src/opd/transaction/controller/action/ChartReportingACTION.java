package opd.transaction.controller.action;

/**
 * @copyright CDAC
 * @author Pragya Sharma
 */

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.ChartReportingFB;
import opd.transaction.controller.util.ChartReportingUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ChartReportingACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	/**
	 * Setting Essentials
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return Forwarded Page
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ChartReportingFB fb = (ChartReportingFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.reset(mapping, request);
		ChartReportingUTIL.setChartReportingEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	/**
	 * Getting Chart
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return Forwarded Page
	 */
	public ActionForward GETREPORT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ChartReportingFB fb = (ChartReportingFB) form;
		if(ChartReportingUTIL.getChartViewData(fb, request))
			return mapping.findForward("VIEW");
		else
			return mapping.findForward("NEW");
	}

	/**
	 * Customize Chart
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return Forwarded Page
	 */
	public ActionForward CUSTOM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ChartReportingFB fb = (ChartReportingFB) form;
		if(ChartReportingUTIL.customizeChart(fb, request))
			return mapping.findForward("CUSTOM");
		else
			return mapping.findForward("NEW");
	}

	/**
	 * Order Chart
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return Forwarded Page
	 */
	public ActionForward ORDER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ChartReportingFB fb = (ChartReportingFB) form;
		ChartReportingUTIL.orderChart(fb, request);
		return mapping.findForward(fb.getTargetPage());
	}

	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ChartReportingFB fb=(ChartReportingFB)form;
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward(fb.getTargetPage());
	}
	
	/**
	 * Showing Graph of Data
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return Forwarded Page
	 */
	public ActionForward SHOWGRAPH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ChartReportingFB fb = (ChartReportingFB) form;
		ChartReportingUTIL.showChartGraph(fb, request, response);
		return mapping.findForward("GRAPH");
	}
}
