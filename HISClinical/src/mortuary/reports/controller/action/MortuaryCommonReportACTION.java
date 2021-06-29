package mortuary.reports.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MortuaryCommonReportACTION extends DispatchAction
{

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("NEW");
	}
	

	public ActionForward LISTOFDECEASED(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("LISTOFDECEASEDVIEW");
		}
		else
		{
			return mapping.findForward("LISTOFDECEASED");
		}
	}
	
	public ActionForward DOCTORWISEPOSTMORTEM(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("DOCTORWISEPOSTMORTEMVIEW");
		}
		else
		{
			return mapping.findForward("DOCTORWISEPOSTMORTEM");
		}
	}
	
	public ActionForward LABANDTESTWISEPOSTMORTEM(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("LABANDTESTWISEPOSTMORTEMVIEW");
		}
		else
		{
			return mapping.findForward("LABANDTESTWISEPOSTMORTEM");
		}
	}
	
	public ActionForward TESTWISEPOSTMORTEM(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("TESTWISEPOSTMORTEMVIEW");
		}
		else
		{
			return mapping.findForward("TESTWISEPOSTMORTEM");
		}
	}
	
	public ActionForward IONAMEANDPSWISEPOSTMORTEM(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("IONAMEANDPSWISEPOSTMORTEMVIEW");
		}
		else
		{
			return mapping.findForward("IONAMEANDPSWISEPOSTMORTEM");
		}
	}
	
	public ActionForward DOCTORWISEPOSTMORTEMCOUNT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("DOCTORWISEPOSTMORTEMCOUNTVIEW");
		}
		else
		{
			return mapping.findForward("DOCTORWISEPOSTMORTEMCOUNT");
		}
	}
	
	public ActionForward DEATHMANNERWISEPOSTMORTEMCOUNT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("DEATHMANNERWISEPOSTMORTEMCOUNTVIEW");
		}
		else
		{
			return mapping.findForward("DEATHMANNERWISEPOSTMORTEMCOUNT");
		}
	}
	
	public ActionForward BROUGHTDEADCASES(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("BROUGHTDEADCASESVIEW");
		}
		else
		{
			return mapping.findForward("BROUGHTDEADCASES");
		}
	}
	
	
		public ActionForward CANCEL(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		return mapping.findForward("CANCEL");
	}
	
}
