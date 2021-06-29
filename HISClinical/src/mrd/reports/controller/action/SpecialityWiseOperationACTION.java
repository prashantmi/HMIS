package mrd.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.reports.controller.fb.SpecialityWiseOperationFB;
import mrd.reports.controller.util.SpecialityWiseOperationUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class SpecialityWiseOperationACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SpecialityWiseOperationFB fb=(SpecialityWiseOperationFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		SpecialityWiseOperationUTL.getEssential(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		SpecialityWiseOperationFB fb=(SpecialityWiseOperationFB)form;
		String unit="";
		String[] unitArray=null;
		if(fb.getOption().equals(MrdConfig.DEPT_WISE))
		{
			unit=fb.getDeptCode().concat("%");
			fb.setUnit(unit);
			fb.setSpeciality("%");
			if(fb.getOperationTypeForDept().equals("%"))
			{
				fb.setOperationTypeMajor(MrdConfig.OPERATION_TYPE_MAJOR);
				fb.setOperationTypeMinor(MrdConfig.OPERATION_TYPE_MINOR);
			}
			if(fb.getOperationTypeForDept().equals(MrdConfig.OPERATION_TYPE_MAJOR))
				fb.setLabel(MrdConfig.MAJOR_LABEL);
			if(fb.getOperationTypeForDept().equals(MrdConfig.OPERATION_TYPE_MINOR))
				fb.setLabel(MrdConfig.MINOR_LABEL);
			
		}
		if(fb.getOption().equals(MrdConfig.UNIT_WISE))
		{
			if(! fb.getUnit().equals("%"))
			{
				unitArray=fb.getUnit().split("@");
				fb.setUnit(unitArray[0]);
			}
			if(fb.getOperationType().equals("%"))
			{
				fb.setOperationTypeMajor(MrdConfig.OPERATION_TYPE_MAJOR);
				fb.setOperationTypeMinor(MrdConfig.OPERATION_TYPE_MINOR);
			}
			if(fb.getOperationType().equals(MrdConfig.OPERATION_TYPE_MAJOR))
				fb.setLabel(MrdConfig.MAJOR_LABEL);
			if(fb.getOperationType().equals(MrdConfig.OPERATION_TYPE_MINOR))
				fb.setLabel(MrdConfig.MINOR_LABEL);
			
		}
		

		
		byte[] byteArray =SpecialityWiseOperationUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
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

