package mrd.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import mrd.reports.controller.fb.AgeSexReligionDeptRegFB;
import mrd.reports.controller.util.AgeSexReligionDeptRegUTL;


public class AgeSexReligionDeptRegACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AgeSexReligionDeptRegFB fb=(AgeSexReligionDeptRegFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		AgeSexReligionDeptRegUTL.getEssential(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		AgeSexReligionDeptRegFB fb=(AgeSexReligionDeptRegFB)form;
		
		String minAge="";
		String maxAge="";
		
		if(! fb.getAgeRange().equals("%"))
		{
			minAge=fb.getAgeRange().split("-")[0];
			maxAge=fb.getAgeRange().split("-")[1];
			fb.setFromAge(minAge);
			fb.setToAge(maxAge);
			
		}
			
		byte[] byteArray =AgeSexReligionDeptRegUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
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

