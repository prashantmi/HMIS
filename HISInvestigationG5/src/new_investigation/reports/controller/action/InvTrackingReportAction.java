package new_investigation.reports.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import hisglobal.presentation.ControllerUTIL;
import new_investigation.reportGenerator.mongoapp.ReportGeneratorCls;
import hisglobal.presentation.WebUTIL;
import new_investigation.reports.controller.fb.InvTrackingReportFB;
import new_investigation.reports.controller.utl.InvTrackingReportUTIL;
import new_investigation.transactions.controller.fb.InvResultReportPrintingFB;
import new_investigation.transactions.controller.fb.InvResultReportPrintingNewFB;

@SuppressWarnings({ "unused" })
public class InvTrackingReportAction extends DispatchAction{

	String error="Error Message Starts From Here [Added By Prashant] :";
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session=WebUTIL.getSession(request);
		WebUTIL.refreshTransState(request);
		return this.NEW(mapping,form,request,response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvTrackingReportFB fb = (InvTrackingReportFB) form;
		return mapping.findForward("NEW");
	}
	
	public ActionForward UrlExternalCall(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvTrackingReportFB fb = (InvTrackingReportFB) form;
		InvTrackingReportUTIL.GetTestTurnAroundTime(fb, request);
		
		/*
		 * fb.setCrNo_url(fb.getCrNo()); fb.setBillNo_url(fb.getBillNo());
		 * fb.setSearchType_url(fb.getSearchType()); fb.setIsGroup_url(fb.getIsGroup());
		 * fb.setTestCode_url(fb.getTestCode()); fb.setGroupCode_url(fb.getGroupCode());
		 * fb.setRequisitionNo_url(fb.getRequisitionNo());
		 * fb.setRequisitionDNo_url(fb.getRequisitionDNo());
		 * fb.setForTestOrGroupOrAll_url(fb.getForTestOrGroupOrAll());
		 */
		
		return mapping.findForward("NEW");
	}
	

	public ActionForward AjaxGetPatDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvTrackingReportFB fb = (InvTrackingReportFB) form;
		JsonObject jsonResponse = new JsonObject();
		try {
			InvTrackingReportUTIL.GetTestTurnAroundTime(fb, request);
			jsonResponse=InvTrackingReportUTIL.AjaxGetPatDetails(fb, request);
			
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
	
	
	public ActionForward AjaxGetReqnList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvTrackingReportFB fb = (InvTrackingReportFB) form;
		JsonObject jsonResponse = new JsonObject();
		try {			
			jsonResponse=InvTrackingReportUTIL.AjaxGetPatReqnList(fb, request);
			
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
	
	public ActionForward AjaxGetPDFReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		InvTrackingReportFB fb=(InvTrackingReportFB)form;
		try {
			ControllerUTIL.setSysdate(request);
			fb.setShowStatus("1");
		
			boolean flg=false;
			
			String ftpserver=InvTrackingReportUTIL.isfromFTPorMONGO(request,response);
			ftpserver="ftp://testuser:friiction@10.226.24.194/ftpserver";
	        fb.setFtpserver(ftpserver);
	
			if(ftpserver==null || ftpserver.equals(""))
				InvTrackingReportUTIL.AjaxGetPDFReportMongo(fb,request,response);
			else
				InvTrackingReportUTIL.AjaxGetPDFReportFTP(fb,request,response);
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { response.getWriter().print(error+sw.toString()); }
			catch (IOException e1) { e1.printStackTrace(); }
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
	
	
	public ActionForward AjaxGetReqnTestParamList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvTrackingReportFB fb = (InvTrackingReportFB) form;
		JsonObject jsonResponse = new JsonObject();
		try {
			jsonResponse=InvTrackingReportUTIL.AjaxGetReqnTestParamList(fb ,request);
			
			jsonResponse.addProperty("isSuccess", "1");
			response.setContentType("application/Json;charset=UTF-8");
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
	
	
	public ActionForward UrlExternalCall_Reqwise_Reports(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvTrackingReportFB fb = (InvTrackingReportFB) form;
		InvTrackingReportUTIL.GetTestTurnAroundTime(fb, request);
		
		/*
		 * fb.setCrNo_url(fb.getCrNo()); fb.setBillNo_url(fb.getBillNo());
		 * fb.setSearchType_url(fb.getSearchType()); fb.setIsGroup_url(fb.getIsGroup());
		 * fb.setTestCode_url(fb.getTestCode()); fb.setGroupCode_url(fb.getGroupCode());
		 * fb.setRequisitionNo_url(fb.getRequisitionNo());
		 * fb.setRequisitionDNo_url(fb.getRequisitionDNo());
		 * fb.setForTestOrGroupOrAll_url(fb.getForTestOrGroupOrAll());
		 */
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward FromDeskCall(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvTrackingReportFB fb = (InvTrackingReportFB) form;
		String Crno = request.getParameter("patCrNo");
		    request.getSession().setAttribute("patCrNo", fb.getCrNo());
		   fb.setCrNo(Crno);
		InvTrackingReportUTIL.GetTestTurnAroundTime(fb, request);
		
		return mapping.findForward("NEW");
	}

}
