package mrd.reports.controller.action;

/**
 * @author Adil Wasi
 * Creation Date 27-Jul-2012
 */
import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.reports.controller.fb.OpdStaticReportFB;
import mrd.reports.controller.util.OpdStaticReportUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class OpdStaticReportACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpdStaticReportFB fb=(OpdStaticReportFB)form;
		fb.reset(mapping,request);
		WebUTIL.refreshTransState(request);
		OpdStaticReportUTL.getEssential(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UNIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpdStaticReportFB fb=(OpdStaticReportFB) form;
		OpdStaticReportUTL.getUnitBasedOnDept(fb, request);	
		return mapping.findForward("NEW");
	}
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		OpdStaticReportFB fb=(OpdStaticReportFB)form;
		/*try{
			for (int i = 0; i < fb.getMultipleHospitalCode().length; i++)
			{
				if (i == 0) fb.setAllHospitalCode(fb.getMultipleHospitalCode()[i]);
				else fb.setAllHospitalCode(fb.getAllHospitalCode() + "," + fb.getMultipleHospitalCode()[i]);

			}
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		
		
		try 
		{			
			byte[] byteArray =OpdStaticReportUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
			OutputStream os ;
			try{
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

