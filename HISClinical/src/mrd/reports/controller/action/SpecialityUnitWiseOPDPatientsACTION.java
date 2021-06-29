package mrd.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.reports.controller.fb.SpecialityUnitWiseOpdPatientsFB;
import mrd.reports.controller.util.SpecialityUnitWiseOPDPatientsUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class SpecialityUnitWiseOPDPatientsACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SpecialityUnitWiseOpdPatientsFB fb=(SpecialityUnitWiseOpdPatientsFB)form;
		WebUTIL.refreshTransState(request);
		SpecialityUnitWiseOPDPatientsUTL.getEssential(fb,request);
	
		return mapping.findForward("NEW");
	}
	
/*	public ActionForward UNIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SpecialityUnitWiseOpdPatientsFB fb=(SpecialityUnitWiseOpdPatientsFB) form;
	
		SpecialityUnitWiseOPDPatientsUTL.setUnitBasedOnDept(fb,request);
		return mapping.findForward("NEW");
	}
	*/
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		SpecialityUnitWiseOpdPatientsFB fb=(SpecialityUnitWiseOpdPatientsFB)form;
		
		if(fb.getUnit().equals("%"))
		{
			String unit=fb.getDepartmentCode().concat("%");
			fb.setUnit(unit);
		}		
		
		byte[] byteArray =SpecialityUnitWiseOPDPatientsUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
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

