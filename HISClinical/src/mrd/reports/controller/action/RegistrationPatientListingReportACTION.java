package mrd.reports.controller.action;

/**
 * @author Pawan Kumar B N
 * Creation Date 09-Jul-2012
 */
import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.reports.controller.fb.RegistrationPatientListingReportFB;
import mrd.reports.controller.util.RegistrationPatientListingReportUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class RegistrationPatientListingReportACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		RegistrationPatientListingReportFB fb=(RegistrationPatientListingReportFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		RegistrationPatientListingReportUTL.getEssential(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		RegistrationPatientListingReportFB fb=(RegistrationPatientListingReportFB)form;
		
		
			
		try 
		{	
			byte[] byteArray =RegistrationPatientListingReportUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
			OutputStream os;
			try {
				os = response.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				if(byteArray!=null)
					{
							response.setHeader("Pragma","no-cache");		 		
							bos.write(byteArray, 0, byteArray.length);
							response.getOutputStream().flush();
							bos.close();			
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		catch (HisReportDataNotFoundException e)
		{
			return mapping.findForward("ERROR");
		}
		return null;
	}

}

