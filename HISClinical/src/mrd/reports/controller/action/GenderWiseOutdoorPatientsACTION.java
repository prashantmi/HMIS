package mrd.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.hisconfig.Config;
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

import mrd.MrdConfig;
import mrd.reports.controller.fb.GenderWiseOutdoorPatientsFB;
import mrd.reports.controller.util.GenderWiseOutdoorPatientsUTL;


public class GenderWiseOutdoorPatientsACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		GenderWiseOutdoorPatientsFB fb=(GenderWiseOutdoorPatientsFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		GenderWiseOutdoorPatientsUTL.getEssential(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		GenderWiseOutdoorPatientsFB fb=(GenderWiseOutdoorPatientsFB)form;
		
		if(fb.getGender().equals(Config.GENDER_TYPE_MALE))
		{
			fb.setLabel(MrdConfig.MALE_LABEL);
		}
		if(fb.getGender().equals(Config.GENDER_TYPE_FEMALE))
		{
			fb.setLabel(MrdConfig.FEMALE_LABEL);
		}
		if(fb.getGender().equals(Config.GENDER_TYPE_OTHER))
		{
			fb.setLabel(MrdConfig.OTHERS_LABEL);
		}
		if(fb.getGender().equals("%"))
		{
			fb.setMaleCode(Config.GENDER_TYPE_MALE);
			fb.setFemaleCode(Config.GENDER_TYPE_FEMALE);
			fb.setOthersCode(Config.GENDER_TYPE_OTHER);
		}
		
		byte[] byteArray =GenderWiseOutdoorPatientsUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
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

