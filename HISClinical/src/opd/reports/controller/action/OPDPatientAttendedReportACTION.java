package opd.reports.controller.action;
/**
 * @author Singaravelan
 * Creation Date:- 09-Sep-2013
 * Modifying Date:- 
 * Used For:-OPD Patient Attended/Waiting Report
 * Team Lead By:- 
 * Module:- Registation (HIS Project)
 * 
 */
import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.reports.controller.fb.OPDPatientAttendedReportFB;
import opd.reports.controller.util.OPDPatientAttendedReportUTIL;

public class OPDPatientAttendedReportACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);
	}	
	/*
	 * This method sets the essential details
	 */
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		OPDPatientAttendedReportFB fb=(OPDPatientAttendedReportFB) form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);	
		
		OPDPatientAttendedReportUTIL.getEssentials(fb,request);
	
		return mapping.findForward("NEW");
 	}
	
	
	
	/*
	 * This method is used to view the report
	 */
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException{
		OPDPatientAttendedReportFB fb= (OPDPatientAttendedReportFB) form;		
		WebUTIL.setAttributeInSession(request, "mode", request.getParameter("mode"));			
			
			
			
			byte[] byteArray =OPDPatientAttendedReportUTIL.generateTextReport(fb,request,getServlet().getServletContext(),response);		
			try {			
				OutputStream os =response.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os);
					if(byteArray!=null)
					{
							response.setHeader("Pragma","no-cache");		 		
							bos.write(byteArray, 0, byteArray.length);
							response.getOutputStream().flush();
							bos.close();			
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			
			
			
			return null;
	 	}
	
	
	
	
}
