package inpatient.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inpatient.InpatientConfig;
import inpatient.reports.controller.fb.SpecialityWiseOperationFB;
import inpatient.reports.controller.util.SpecialityWiseOperationUTL;

import mrd.reports.controller.fb.WardCensusReportFB;
import mrd.reports.controller.util.WardCensusReportUTIL;

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
	
	/**
	 * Get All Unit through Ajax
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETALLUNIT_AJAX(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		SpecialityWiseOperationFB fb=(SpecialityWiseOperationFB)form;
		//ControllerUTIL.setSysdate(objRequest_p);
		
		StringBuffer strBuff = SpecialityWiseOperationUTL.getAllUnit_AJAX(fb, request);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(strBuff.toString());
		
		return null;
		
	}
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		SpecialityWiseOperationFB fb=(SpecialityWiseOperationFB)form;
		String unit="";
		String[] unitArray=null;
		if(fb.getOption().equals(InpatientConfig.DEPT_WISE))
		{
			unit=fb.getDeptCode().concat("%");
			fb.setUnit(unit);
			fb.setSpeciality("%");
			if(fb.getOperationTypeForDept().equals("%"))
			{
				fb.setOperationTypeMajor(InpatientConfig.OPERATION_TYPE_MAJOR);
				fb.setOperationTypeMinor(InpatientConfig.OPERATION_TYPE_MINOR);
			}
			if(fb.getOperationTypeForDept().equals(InpatientConfig.OPERATION_TYPE_MAJOR))
				fb.setLabel(InpatientConfig.MAJOR_LABEL);
			if(fb.getOperationTypeForDept().equals(InpatientConfig.OPERATION_TYPE_MINOR))
				fb.setLabel(InpatientConfig.MINOR_LABEL);
			
		}
		if(fb.getOption().equals(InpatientConfig.UNIT_WISE))
		{
			if(! fb.getUnit().equals("%"))
			{
				unitArray=fb.getUnit().split("@");
				fb.setUnit(unitArray[0]);
			}
			if(fb.getOperationType().equals("%"))
			{
				fb.setOperationTypeMajor(InpatientConfig.OPERATION_TYPE_MAJOR);
				fb.setOperationTypeMinor(InpatientConfig.OPERATION_TYPE_MINOR);
			}
			if(fb.getOperationType().equals(InpatientConfig.OPERATION_TYPE_MAJOR))
				fb.setLabel(InpatientConfig.MAJOR_LABEL);
			if(fb.getOperationType().equals(InpatientConfig.OPERATION_TYPE_MINOR))
				fb.setLabel(InpatientConfig.MINOR_LABEL);
			
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

