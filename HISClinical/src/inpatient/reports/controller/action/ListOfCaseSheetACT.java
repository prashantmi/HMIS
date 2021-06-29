package inpatient.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import inpatient.reports.controller.fb.ListOfCaseSheetFB;
import inpatient.reports.controller.util.ListOfCaseSheetUTL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class ListOfCaseSheetACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ListOfCaseSheetFB fb=(ListOfCaseSheetFB)form;
		WebUTIL.refreshTransState(request);
		ListOfCaseSheetUTL.getEssential(fb,request);
		//boolean flag=
			ListOfCaseSheetUTL.getDeptUnitList(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ListOfCaseSheetFB fb=(ListOfCaseSheetFB)form;
		//WebUTIL.refreshTransState(request);	
		ControllerUTIL.setSysdate(request);
		ListOfCaseSheetUTL.getWardOnBasisOfUnitCode(fb, request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		ListOfCaseSheetFB fb=(ListOfCaseSheetFB)form;
		
		try 
		{		
			
			if(! fb.getUnitCode().equals("%")){
			String unitCodeWithDiagCodeType = fb.getUnitCode();
			String unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^"));
			fb.setUnitCode(unitCode);
			}
			byte[] byteArray =ListOfCaseSheetUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
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

