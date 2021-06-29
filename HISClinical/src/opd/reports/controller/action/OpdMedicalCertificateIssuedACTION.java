package opd.reports.controller.action;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.reports.controller.fb.OpdMedicalCertificateIssuedFB;
import opd.reports.controller.util.OpdMedicalCertificateIssuedUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdMedicalCertificateIssuedACTION extends DispatchAction
{
	public  ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpdMedicalCertificateIssuedFB fb=(OpdMedicalCertificateIssuedFB) form;
		fb.reset(mapping,request);
		WebUTIL.refreshTransState(request);
		OpdMedicalCertificateIssuedUTIL.getEssential(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UNIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OpdMedicalCertificateIssuedFB fb=(OpdMedicalCertificateIssuedFB) form;
		OpdMedicalCertificateIssuedUTIL.getUnitBasedOnDept(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		OpdMedicalCertificateIssuedFB fb=(OpdMedicalCertificateIssuedFB) form;
		try
		{
			byte[] byteArray=OpdMedicalCertificateIssuedUTIL.generateTextReport(fb, request, getServlet().getServletContext(), response);
			OutputStream os=response.getOutputStream();
			BufferedOutputStream bos=new BufferedOutputStream(os);
				if(byteArray!=null)
				{
					response.setHeader("pragma", "no-cache");
					bos.write(byteArray, 0, byteArray.length);
					response.getOutputStream().flush();
					bos.close();
				}
		}
		catch (HisReportDataNotFoundException e) 
		{
			return mapping.findForward("ERROR");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
