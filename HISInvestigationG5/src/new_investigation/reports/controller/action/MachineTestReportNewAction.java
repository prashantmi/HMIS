package new_investigation.reports.controller.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import new_investigation.reports.controller.fb.MachineTestReportNewFB;
import new_investigation.reports.controller.utl.MachineTestReportNewUTIL;

public class MachineTestReportNewAction  extends DispatchAction
{
	
	String error="Error Message Starts From Here [Added By Prashant] :";
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MachineTestReportNewFB fb = (MachineTestReportNewFB) form;
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward AjaxGetLabList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MachineTestReportNewFB fb = (MachineTestReportNewFB) form;
		
		try {
			 JsonObject jsonResponse = new JsonObject();
			
			 jsonResponse=MachineTestReportNewUTIL.AjaxGetLabList(fb, request);
			 
			 response.setContentType("application/Json");
			 response.getWriter().print(jsonResponse);
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { response.getWriter().print(error+sw.toString()); }
			catch (IOException e1) { e1.printStackTrace(); }
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		}
		catch (Exception e) {
			e.printStackTrace();
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { response.getWriter().print(error+sw.toString()); }
			catch (IOException e1) { e1.printStackTrace(); }
		}
		return null;
	}
	
	
	public ActionForward AjaxGetMachineList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MachineTestReportNewFB fb = (MachineTestReportNewFB) form;
		
		try {
			 JsonObject jsonResponse = new JsonObject();
			
			 jsonResponse=MachineTestReportNewUTIL.AjaxGetMachineList(fb, request);
			 
			 response.setContentType("application/Json");
			 response.getWriter().print(jsonResponse.toString());
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { response.getWriter().print(error+sw.toString()); }
			catch (IOException e1) { e1.printStackTrace(); }
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		}
		catch (Exception e) {
			e.printStackTrace();
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { response.getWriter().print(error+sw.toString()); }
			catch (IOException e1) { e1.printStackTrace(); }
		}
		return null;
	}
	
	
	public ActionForward AjaxGetMachineTestReportList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MachineTestReportNewFB fb = (MachineTestReportNewFB) form;
		
		try {
			 JsonObject jsonResponse = new JsonObject();
			
			 jsonResponse=MachineTestReportNewUTIL.AjaxGetMachineTestReportList(fb, request);
			 
			 response.setContentType("application/Json");
			 response.getWriter().print(jsonResponse.toString());
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { response.getWriter().print(error+sw.toString()); }
			catch (IOException e1) { e1.printStackTrace(); }
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		}
		catch (Exception e) {
			e.printStackTrace();
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { response.getWriter().print(error+sw.toString()); }
			catch (IOException e1) { e1.printStackTrace(); }
		}
		return null;
	}
	
	
}
