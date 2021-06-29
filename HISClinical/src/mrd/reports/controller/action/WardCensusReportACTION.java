package mrd.reports.controller.action;

/**
 * @author Adil Wasi
 * Creation Date 24-Jul-2012
 */
import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.reports.controller.fb.WardCensusReportFB;
import mrd.reports.controller.util.WardCensusReportUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class WardCensusReportACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		WardCensusReportFB fb=(WardCensusReportFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		WardCensusReportUTIL.getEssential(fb,request);
		WardCensusReportUTIL.getUnitBasedOnDept(fb, request);	
	
		return mapping.findForward("NEW");
	}
	
	public ActionForward UNIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		WardCensusReportFB fb=(WardCensusReportFB) form;
		WardCensusReportUTIL.getUnitBasedOnDept(fb, request);	
		return mapping.findForward("NEW");
	}
	
	/**
	 * Get All Unitthrough Ajax
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
		WardCensusReportFB fb=(WardCensusReportFB)form;
		//ControllerUTIL.setSysdate(objRequest_p);
		
		StringBuffer strBuff = WardCensusReportUTIL.getAllUnit_AJAX(fb, request);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(strBuff.toString());
		
		return null;
		
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
		WardCensusReportFB fb=(WardCensusReportFB)form;
		//ControllerUTIL.setSysdate(objRequest_p);
		
		StringBuffer strBuff = WardCensusReportUTIL.getAllWard_AJAX(fb, request);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(strBuff.toString());
		
		return null;
		
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
	public ActionForward GETALLROOM_AJAX(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		WardCensusReportFB fb=(WardCensusReportFB)form;
		//ControllerUTIL.setSysdate(objRequest_p);
		
		StringBuffer strBuff = WardCensusReportUTIL.getAllRoom_AJAX(fb, request);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(strBuff.toString());
		
		return null;
		
	}
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		WardCensusReportFB fb=(WardCensusReportFB)form;
		
		
			
		try 
		{	
			byte[] byteArray =WardCensusReportUTIL.generateTextReport(fb,request,getServlet().getServletContext(),response);
			OutputStream os;
			try {
				os = response.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				if(byteArray!=null)
					{
							response.setHeader("Pragma","no-cache");		 		
							bos.write(byteArray, 0, byteArray.length);
							response.getOutputStream().flush();
							bos.close();			
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		catch (HisReportDataNotFoundException e)
		{
			return mapping.findForward("ERROR");
		}
		return null;
	}

}

