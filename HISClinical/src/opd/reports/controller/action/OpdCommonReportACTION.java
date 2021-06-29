package opd.reports.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdCommonReportACTION extends DispatchAction
{

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("NEW");
	}


	public ActionForward MORBIDITYREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("MORBIDITYREPORTVIEW");
		}
		else
		{
			return mapping.findForward("MORBIDITYREPORT");
		}
	}
	
	public ActionForward OPDMEDICALCERTIFICATEISSUEDREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDMEDICALCERTIFICATEISSUEDREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDMEDICALCERTIFICATEISSUEDREPORT");
		}
	}
	
	public ActionForward OPDCASESHEETREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDCASESHEETREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDCASESHEETREPORT");
		}
	}
	
	public ActionForward OPDDEPTUNITDOCTORWISEREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDEPTUNITDOCTORWISEREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDEPTUNITDOCTORWISEREPORT");
		}
	}
	
	public ActionForward OPDROSTERREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDROSTERREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDROSTERREPORT");
		}
	}

	
	public ActionForward OPDDEPTUNITEPISODESTATUSPATLISTREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDEPTUNITEPISODESTATUSPATLISTREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDEPTUNITEPISODESTATUSPATLISTREPORT");
		}
	}
	
	public ActionForward OPDDISCREPANCYREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDISCREPANCYREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDISCREPANCYREPORT");
		}
	}
		
	public ActionForward OPDDEPTWISEDISEASECODELISTREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDEPTWISEDISEASECODELISTREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDEPTWISEDISEASECODELISTREPORT");
		}
	}
	
	public ActionForward OPDREFERREDPATIENTLISTREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDREFERREDPATIENTLISTREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDREFERREDPATIENTLISTREPORT");
		}
	}
	
	public ActionForward OPDDEPTUNITREGCATPATCATREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDEPTUNITREGCATPATCATREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDEPTUNITREGCATPATCATREPORT");
		}
	}
	

	public ActionForward OPDDEPTUNITDIAGNOSISSTATREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDEPTUNITDIAGNOSISSTATREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDEPTUNITDIAGNOSISSTATREPORT");
		}
	}
	
	public ActionForward OPDCOUNTOLDPATNEXTVISITREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDCOUNTOLDPATNEXTVISITREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDCOUNTOLDPATNEXTVISITREPORT");
		}
	}
	
	public ActionForward OPDDEPTUNITUSERWISESTATREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDEPTUNITUSERWISESTATREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDEPTUNITUSERWISESTATREPORT");
		}
	}
	
	public ActionForward OPDDEPTUNITWISESTATREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDEPTUNITWISESTATREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDEPTUNITWISESTATREPORT");
		}
	}
	
	public ActionForward OPDDEPTUNITCATWISESTATREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDEPTUNITCATWISESTATREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDEPTUNITCATWISESTATREPORT");
		}
	}
	
	public ActionForward OPDPATREFERRALDTLREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			//HttpSession session=request.getSession();
			//String unitCode=(String)session.getAttribute(OpdConfig.OPD_DESK_UNIT_CODE);
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDPATREFERRALDTLREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDPATREFERRALDTLREPORT");
		}
	}
	
	public ActionForward OPDDEPTWISEREFERPATFROMREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDEPTWISEREFERPATFROMREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDEPTWISEREFERPATFROMREPORT");
		}
	}
	
	public ActionForward OPDDEPTWISEREFERPATTOREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDEPTWISEREFERPATTOREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDEPTWISEREFERPATTOREPORT");
		}
	}
	public ActionForward NUMBEROFPATIENTIMAGEUPLOADEDREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("NUMBEROFPATIENTIMAGEUPLOADEDREPORTVIEW");
		}
		else
		{
			return mapping.findForward("NUMBEROFPATIENTIMAGEUPLOADEDREPORT");
		}
	}
	
	public ActionForward OPDLISTINGOFPATIENTREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDLISTINGOFPATIENTREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDLISTINGOFPATIENTREPORT");
		}
	}
	public ActionForward PATIENTLISTINGREPORTFOROPDVISIT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
			{
		String reportMode = request.getParameter("reportMode");
		
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("PATIENTLISTINGREPORTFOROPDVISITVIEW");
		}
		else
		{
			return mapping.findForward("PATIENTLISTINGREPORTFOROPDVISIT");
		}
	}
	public ActionForward AVERAGESTAYTIMEOFTHEPATIENT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
			{
		String reportMode = request.getParameter("reportMode");
		
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("AVERAGESTAYTIMEOFTHEPATIENTVIEW");
		}
		else
		{
			return mapping.findForward("AVERAGESTAYTIMEOFTHEPATIENT");
		}
	}
	public ActionForward EQUIPMENTISSUEREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
			{
		String reportMode = request.getParameter("reportMode");
		
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("EQUIPMENTISSUEREPORTVIEW");
		}
		else
		{
			return mapping.findForward("EQUIPMENTISSUEREPORT");
		}
			}
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward ICDCODEREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("ICDCODEREPORTVIEW");
		}
		else
		{
			return mapping.findForward("ICDCODEREPORT");
		}
	}
	
	public ActionForward DIAGNOSISWISEOPDANDIPDREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("DIAGNOSISWISEOPDANDIPDREPORTVIEW");
		}
		else
		{
			return mapping.findForward("DIAGNOSISWISEOPDANDIPDREPORT");
		}
	}
	
	
	public ActionForward OPDDISEASEWISENOOFPATATTENDALLREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDISEASEWISENOOFPATATTENDALLREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDISEASEWISENOOFPATATTENDALLREPORT");
		}
	}
	
	
	public ActionForward OPDDISEASEWISENOOFPATATTENDINOUTREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("OPDDISEASEWISENOOFPATATTENDINOUTREPORTVIEW");
		}
		else
		{
			return mapping.findForward("OPDDISEASEWISENOOFPATATTENDINOUTREPORT");
		}
	}
	public ActionForward COUNTNOOFPATIENTOFANORECTANGLEDISEASEREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("COUNTNOOFPATIENTOFANORECTANGLEDISEASEREPORTVIEW");
		}
		else
		{
			return mapping.findForward("COUNTNOOFPATIENTOFANORECTANGLEDISEASEREPORT");
		}
	}
	
	/**
	 * Added By Singaravelan at 09-Sep-2013
	 */
	public ActionForward OPDPATIENTATTENDED(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String reportMode = request.getParameter("reportMode");
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH")))
			return mapping.findForward("OPDPATIENTATTENDEDVIEW");
		else
			return mapping.findForward("OPDPATIENTATTENDED");
	}
	
}
