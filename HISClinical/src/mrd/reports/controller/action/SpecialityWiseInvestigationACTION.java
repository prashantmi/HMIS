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

import mrd.MrdConfig;
import mrd.reports.controller.fb.SpecialityWiseInvestigationFB;
import mrd.reports.controller.util.SpecialityWiseInvestigationUTL;


public class SpecialityWiseInvestigationACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SpecialityWiseInvestigationFB fb=(SpecialityWiseInvestigationFB)form;
		WebUTIL.refreshTransState(request);
		SpecialityWiseInvestigationUTL.getEssential(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SpecialityWiseInvestigationFB fb=(SpecialityWiseInvestigationFB)form;
		String unit="";
		String[] unitArray=null;
		if(fb.getOption().equals(MrdConfig.DEPT_WISE))
		{
			unit=fb.getDeptCode().concat("%");
			fb.setUnit(unit);
			fb.setSpeciality("%");
		
		}
		if(fb.getOption().equals(MrdConfig.UNIT_WISE))
		{
			if(! fb.getUnit().equals("%"))
			{
				unitArray=fb.getUnit().split("@");
				fb.setUnit(unitArray[0]);
			}
			
		}
		
		try 
		{
			byte[] byteArray =SpecialityWiseInvestigationUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
			
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

