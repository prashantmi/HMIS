package opd.reports.controller.action;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.reports.controller.fb.OpdRosterFB;
import opd.reports.controller.util.OpdRosterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdRosterACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpdRosterFB fb=(OpdRosterFB)form;
		fb.reset(mapping,request);
		WebUTIL.refreshTransState(request);
		OpdRosterUTIL.getEssential(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		OpdRosterFB fb= (OpdRosterFB) form;		
		
		try {	
			byte[] byteArray =OpdRosterUTIL.generateTextReport(fb,request,getServlet().getServletContext(),response);		
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
		catch(HisReportDataNotFoundException e)
		{
			return mapping.findForward("ERROR");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
			return null;
	 }

}
