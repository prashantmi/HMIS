package inpatient.reports.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InpatientCommonReportACTION extends DispatchAction
{

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("NEW");
	}
	

	public ActionForward DOCTORCALLBOOKLOGREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("DOCTORCALLBOOKLOGREPORTVIEW");
		}
		else
		{
			return mapping.findForward("DOCTORCALLBOOKLOGREPORT");
		}
	}
	

	public ActionForward LISTOFCASESHEETREPORT(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception
	{
		//System.out.println("DFFFFFFF" + arg0.findForward("CATEGORYWISEREPORT"));
		String reportMode = arg2.getParameter("reportMode");
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH")))
		{
			return arg0.findForward("LISTOFCASESHEETREPORTVIEW");
		}
		else
		{
			return arg0.findForward("LISTOFCASESHEETREPORT");
		}
	}
	
	public ActionForward JSYREGISTRATIONREPORT(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception
	{
		//System.out.println("DFFFFFFF" + arg0.findForward("CATEGORYWISEREPORT"));
		String reportMode = arg2.getParameter("reportMode");
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH")))
		{
			return arg0.findForward("JSYREGISTRATIONREPORTVIEW");
		}
		else
		{
			return arg0.findForward("JSYREGISTRATIONREPORT");
		}
	}
	
	public ActionForward SPECIALITYWISEOPERATION(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception
	{
		//System.out.println("DFFFFFFF" + arg0.findForward("CATEGORYWISEREPORT"));
		String reportMode = arg2.getParameter("reportMode");
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH")))
		{
			return arg0.findForward("SPECIALITYWISEOPERATIONVIEW");
		}
		else
		{
			return arg0.findForward("SPECIALITYWISEOPERATION");
		}
	}
	
	public ActionForward REGISTEREDMLCPATIENTREPORT(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception
	{
		String reportMode = arg2.getParameter("reportMode");
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH")))
		{
			return arg0.findForward("REGISTEREDMLCPATIENTREPORTVIEW");
		}
		else
		{
			return arg0.findForward("REGISTEREDMLCPATIENTREPORT");
		}
	}
	
	public ActionForward DISEASECODEREPORT(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception
	{
		String reportMode = arg2.getParameter("reportMode");
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH")))
		{
			return arg0.findForward("DISEASECODEREPORTVIEW");
		}
		else
		{
			return arg0.findForward("DISEASECODEREPORT");
		}
	}
	
	public ActionForward DEATHDETAILSREPORT(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception
	{
		String reportMode = arg2.getParameter("reportMode");
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH")))
		{
			return arg0.findForward("DEATHDETAILSREPORTVIEW");
		}
		else
		{
			return arg0.findForward("DEATHDETAILSREPORT");
		}
	}
	
	public ActionForward LISTOFABSCONDINGPATIENTREPORT(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception
	{
		String reportMode = arg2.getParameter("reportMode");
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH")))
		{
			return arg0.findForward("LISTOFABSCONDINGPATIENTREPORTVIEW");
		}
		else
		{
			return arg0.findForward("LISTOFABSCONDINGPATIENTREPORT");
		}
	}
	
	public ActionForward LISTOFNONMLCPATIENTREPORT(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception
	{
		String reportMode = arg2.getParameter("reportMode");
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH")))
		{
			return arg0.findForward("LISTOFNONMLCPATIENTREPORTVIEW");
		}
		else
		{
			return arg0.findForward("LISTOFNONMLCPATIENTREPORT");
		}
	}
	
	public ActionForward DAILYINDOOREREGISTERFORWARDREPORT(ActionMapping arg0, ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3) throws Exception
	{
		String reportMode = arg2.getParameter("reportMode");
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH")))
		{
			return arg0.findForward("DAILYINDOOREREGISTERFORWARDREPORTVIEW");
		}
		else
		{
			return arg0.findForward("DAILYINDOOREREGISTERFORWARDREPORT");
		}
	}
		
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		return mapping.findForward("CANCEL");
	}
	
}
