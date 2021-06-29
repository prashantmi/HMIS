package opd.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.reports.controller.fb.OpdPatientsReferralDtlFB;
import opd.reports.controller.util.OpdPatientsReferralDtlUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdPatientsReferralDtlACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpdPatientsReferralDtlFB fb=(OpdPatientsReferralDtlFB)form;
		fb.reset(mapping,request);
		WebUTIL.refreshTransState(request);
		OpdPatientsReferralDtlUTIL.getEssenttial(fb,request);
		return mapping.findForward("NEW");
		
	}
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		OpdPatientsReferralDtlFB fb= (OpdPatientsReferralDtlFB) form;		
		
	//	byte[] byteArray =OpdPatientsReferralDtlUTIL.generateTextReport(fb,request,getServlet().getServletContext(),response);
		
		try {	
			byte[] byteArray =OpdPatientsReferralDtlUTIL.generateTextReport(fb,request,getServlet().getServletContext(),response);		
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
