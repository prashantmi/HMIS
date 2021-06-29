package inpatient.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import inpatient.reports.controller.fb.DoctorCallBookLogReportFB;
import inpatient.reports.controller.util.DoctorCallBookLogReportUTL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class DoctorCallBookLogReportACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DoctorCallBookLogReportFB fb=(DoctorCallBookLogReportFB)form;
		//WebUTIL.refreshTransState(request);
		DoctorCallBookLogReportUTL.getEssential(fb,request);
		DoctorCallBookLogReportUTL.getWardOnBasisOfUnitCode(fb,request);
		DoctorCallBookLogReportUTL.getConsultantDetails(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		DoctorCallBookLogReportFB fb=(DoctorCallBookLogReportFB)form;
		
		try 
		{		
			if(fb.getCrNo().equals(""))
				fb.setCrNo("%");
			if(fb.getAdmnNo().equals(""))
				fb.setAdmnNo("%");
			
			byte[] byteArray =DoctorCallBookLogReportUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
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
		catch (HisReportDataNotFoundException e)
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

