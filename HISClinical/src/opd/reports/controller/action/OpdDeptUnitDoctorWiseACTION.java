package opd.reports.controller.action;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.reports.controller.fb.OpdDeptUnitDoctorWiseFB;
import opd.reports.controller.util.OpdDeptUnitDoctorWiseUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdDeptUnitDoctorWiseACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpdDeptUnitDoctorWiseFB fb=(OpdDeptUnitDoctorWiseFB)form;
		fb.reset(mapping,request);
		WebUTIL.refreshTransState(request);
		OpdDeptUnitDoctorWiseUTIL.getEssential(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UNIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpdDeptUnitDoctorWiseFB fb=(OpdDeptUnitDoctorWiseFB) form;
		OpdDeptUnitDoctorWiseUTIL.getUnitBasedOnDept(fb,request);	
		return mapping.findForward("NEW");
	}
	public ActionForward DOCTORNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpdDeptUnitDoctorWiseFB fb=(OpdDeptUnitDoctorWiseFB) form;
		OpdDeptUnitDoctorWiseUTIL.getDoctorNameBasedOnUnit(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		OpdDeptUnitDoctorWiseFB fb=(OpdDeptUnitDoctorWiseFB)form;
		
		try 
		{			
			byte[] byteArray =OpdDeptUnitDoctorWiseUTIL.generateTextReport(fb,request,getServlet().getServletContext(),response);
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
