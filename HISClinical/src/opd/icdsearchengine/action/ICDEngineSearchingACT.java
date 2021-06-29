package opd.icdsearchengine.action;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.icdsearchengine.fb.ICDSearchEngineFB;
import opd.icdsearchengine.util.ICDSearchEngineUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ICDEngineSearchingACT extends DispatchAction
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDSearchEngineFB fb = (ICDSearchEngineFB) form;
		fb.reset(mapping, request);
		ICDSearchEngineUTL.setICDSearchingEssentials(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward GETRESULT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		ICDSearchEngineFB fb = (ICDSearchEngineFB) form;
		return mapping.findForward("NEW");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}

	public ActionForward REPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ICDSearchEngineFB fb = (ICDSearchEngineFB) form;
		try 
		{			
			byte[] byteArray = ICDSearchEngineUTL.printReport(fb,request,response);
			OutputStream os = response.getOutputStream();
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
			
		}
		return null;
	}
}
