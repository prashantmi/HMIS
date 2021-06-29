package inpatient.reports.controller.action;

/**
 * @author Adil Wasi
 * Creation Date 03-Dec-2012
 */
import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inpatient.reports.controller.fb.ListofAbscondingPatientReportFB;
import inpatient.reports.controller.util.ListofAbscondingPatientReportUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class ListofAbscondingPatientReportACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ListofAbscondingPatientReportFB fb=(ListofAbscondingPatientReportFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		ListofAbscondingPatientReportUTL.getEssential(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	
	
	/**
	 * Get All Ward through Ajax
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETALLWARD_AJAX(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ListofAbscondingPatientReportFB fb=(ListofAbscondingPatientReportFB)form;
		//ControllerUTIL.setSysdate(objRequest_p);
		
		StringBuffer strBuff = ListofAbscondingPatientReportUTL.getAllWard_AJAX(fb, request);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(strBuff.toString());
		
		return null;
		
	}
	
	
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		ListofAbscondingPatientReportFB fb=(ListofAbscondingPatientReportFB)form;
		
		
			
		byte[] byteArray =ListofAbscondingPatientReportUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
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

