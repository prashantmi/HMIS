package new_investigation.reportGenerator.controler.action;

/**
 * @author Prashant Mishra <https://github.com/prashantmi>
 */

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

import hisglobal.presentation.ControllerUTIL;
import new_investigation.reportGenerator.mongoapp.ReportGeneratorCls;
import new_investigation.reports.controller.fb.InvTrackingReportFB;
import new_investigation.reportGenerator.controler.util.InvReportGeneratorUTL;
import new_investigation.transactions.controller.fb.InvResultReportPrintingFB;
import new_investigation.transactions.controller.fb.InvResultReportPrintingNewFB;

@SuppressWarnings({ "unused" })
public class InvReportGeneratorAction extends DispatchAction{

	String error="Error Message Starts From Here [Added By Prashant] :";
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		return this.NEW(mapping,form,request,response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvTrackingReportFB fb = (InvTrackingReportFB) form;
		return mapping.findForward("NEW");
	}
	
	public ActionForward AjaxGetPatPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvTrackingReportFB fb = (InvTrackingReportFB) form;
		JsonObject jsonResponse = new JsonObject();
		
		try {
			jsonResponse=InvReportGeneratorUTL.AjaxGetPatPDFReport(fb, request);
			
			jsonResponse.addProperty("isSuccess", "1");
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { 
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", error+sw.toString());
				response.getWriter().print(jsonResponse.toString());
			} catch (IOException e1) { e1.printStackTrace(); }
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		}
		catch (Exception e) {
			e.printStackTrace();
			
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { 
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", error+sw.toString());
				response.getWriter().print(jsonResponse.toString());
			} catch (IOException e1) { e1.printStackTrace(); }
		}
		return null;
	}
	
	
	
}
