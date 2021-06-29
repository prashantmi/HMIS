
/**
 * @author Pawan Kumar B N
 * Creation Date 02-Jul-2012
 */

package mrd.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.reports.controller.fb.FluorosisEradicationProgrammeReportFB;
import mrd.reports.controller.util.FluorosisEradicationProgrammeReportUTL;
import mrd.reports.controller.util.FormPReportUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class FluorosisEradicationProgrammeReportACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		FluorosisEradicationProgrammeReportFB fb=(FluorosisEradicationProgrammeReportFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		FluorosisEradicationProgrammeReportUTL.getEssential(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		FluorosisEradicationProgrammeReportFB fb=(FluorosisEradicationProgrammeReportFB)form;
			
		byte[] byteArray =FormPReportUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
		try 
		{	
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

