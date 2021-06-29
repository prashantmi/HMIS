/*
## Copyright Information	: C-DAC, Noida
## Project Name				: eSushrut
## Author          		   	: github.com/prashantmi
## Module Name				: INVESTIGATION
*/



package new_investigation.transactions.controller.action;

import hisglobal.backutil.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.reports.controller.fb.InvTrackingReportFB;
import new_investigation.reports.controller.utl.InvTrackingReportUTIL;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.transactions.controller.utl.InvResultEntryUTIL;
import new_investigation.transactions.controller.utl.InvResultValidationRespUTIL;
import new_investigation.transactions.controller.utl.InvResultValidationUTIL;
import new_investigation.vo.template.ResultEntryVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class InvResultValidationRespACT extends CSRFGardTokenAction
{	
	String error="Error Message Starts From Here [Added By Prashant] :";

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session=WebUTIL.getSession(request);
		session.setAttribute("resultValidationSessionJson", null);
		WebUTIL.refreshTransState(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session=WebUTIL.getSession(request);
		session.setAttribute("resultValidationSessionJson", null);
		WebUTIL.refreshTransState(request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward OLDVALIDATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
		session.removeAttribute(InvestigationConfig.LIST_RESULT_VALIDATION_RESP_TEST_TEMPLATE_VO);
		return mapping.findForward("OLDVALIDATION");
	}
	
	public ActionForward AajaxGetCollAreaNLabList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
		JsonObject jsonResponse = new JsonObject();
		StringWriter sw=new StringWriter();
		try {
			
			jsonResponse=InvResultValidationRespUTIL.AajaxGetCollAreaNLabList(fb, request);
			
			jsonResponse.addProperty("isSuccess", "1");
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { 
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", error+sw.toString());
				response.getWriter().print(jsonResponse.toString());
				//response.getWriter().print(error+sw.toString()); 
			} catch (IOException e1) { e1.printStackTrace(); }
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		}
		catch (Exception e) {
			e.printStackTrace();
			
			
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { 
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", error+sw.toString());
				response.getWriter().print(jsonResponse.toString());
				//response.getWriter().print(error+sw.toString());
			} catch (IOException e1) { e1.printStackTrace(); }
		}
		return null;
	}
	
	public ActionForward AjaxGetLabList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
		JsonObject jsonResponse = new JsonObject();
		StringWriter sw=new StringWriter();
		try {
			
			jsonResponse=InvResultValidationRespUTIL.AjaxGetLabList(fb, request);
			
			jsonResponse.addProperty("isSuccess", "1");
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { 
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", error+sw.toString());
				response.getWriter().print(jsonResponse.toString());
				//response.getWriter().print(error+sw.toString()); 
			} catch (IOException e1) { e1.printStackTrace(); }
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		}
		catch (Exception e) {
			e.printStackTrace();
			
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { 
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", error+sw.toString());
				response.getWriter().print(jsonResponse.toString());
				//response.getWriter().print(error+sw.toString());
			} catch (IOException e1) { e1.printStackTrace(); }
		}
		return null;
	}
	
	
	public ActionForward AjaxGetValidationReqnList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		generateToken(request);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		HttpSession session=request.getSession();
        session.removeAttribute(InvestigationConfig.LIST_RESULT_VALIDATION_RESP_TEST_TEMPLATE_VO);
		
		JsonObject jsonResponse = new JsonObject();
		StringWriter sw=new StringWriter();
		try {
			
			jsonResponse=InvResultValidationRespUTIL.AjaxGetValidationReqnList(fb,request);
			
			jsonResponse.addProperty("isSuccess", "1");
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			
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
	
	public ActionForward AjaxValidateReqnResult(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

		generateToken(request);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		HttpSession session=request.getSession();
		
		JsonObject jsonResponse = new JsonObject();
		StringWriter sw=new StringWriter();
		try {
			
			InvResultValidationRespUTIL.AjaxValidateReqnResult(fb,request);
			
			jsonResponse.addProperty("isSuccess", "1");
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			
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
	
	
	public ActionForward GetReqnDetailsNonEditable(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HttpSession session=WebUTIL.getSession(request);
		session.setAttribute("resultValidationSessionJson", null);
		System.out.println("InvResultValidationResp: GetReqnDetailsNonEditable  ");
		InvResultValidationRespFB fb = (InvResultValidationRespFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		String sampleAreaName=fb.getSampleAreaName();
		String sampleAreaCode=fb.getSampleAreaCode();
		String reqDno = fb.getSelectedCheckbox().split("#")[2];
		System.out.println("reqDno = "+reqDno);
		request.setAttribute("dnoForRangeValidation", reqDno);
		fb.setToviewonly("1");
		InvResultValidationRespUTIL.GetReqnDetails(fb,request);
		fb.setSampleAreaName(sampleAreaName);
	     fb.setSampleAreaCode(sampleAreaCode);
	     fb.setIspreview("2");
	     return mapping.findForward("OLDVALIDATION");
	}
	
	public ActionForward GetReqnDetailsEditable(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HttpSession session=WebUTIL.getSession(request);
		session.setAttribute("resultValidationSessionJson", null);
		System.out.println("InvResultValidationResp: GetReqnDetailsEditable  ");
		InvResultValidationRespFB fb = (InvResultValidationRespFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		String sampleAreaName=fb.getSampleAreaName();
		String sampleAreaCode=fb.getSampleAreaCode();
		fb.setToviewonly("0");
		String newEntry = fb.getNewEntry();
		InvResultValidationRespUTIL.GetReqnDetails(fb,request);
		fb.setSampleAreaName(sampleAreaName);
	    fb.setSampleAreaCode(sampleAreaCode);
	    fb.setNewEntry(newEntry);
	     return mapping.findForward("OLDVALIDATION");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request, response);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		fb.setNewEntry("1");
		InvResultValidationRespUTIL.saveResultValidationPatientDetails(fb, request);
		return this.OLDVALIDATION(mapping, form, request, response);
	} 
	
	public ActionForward SAVETODRAFT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request, response);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		fb.setNewEntry("1");
		InvResultValidationRespUTIL.saveResultValidationPatientDetails(fb, request);
		fb.setIsSaveToDraft(null);
		return this.OLDVALIDATION(mapping, form, request, response);
	} 
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		fb.setNewEntry("2");
		InvResultValidationRespUTIL.saveResultValidationPatientDetails(fb, request);
		fb.setNewEntry("1");
		return this.OLDVALIDATION(mapping, form, request, response);
	} 
	
	
	public ActionForward AJX_DUPLICACY_DAILYLABNO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		InvResultValidationRespFB fb=(InvResultValidationRespFB)objForm_p;

		objResponse_p.setHeader("Cache-Control", "no-cache");
		return null;
	}

	public ActionForward GOFORRESULTENTRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvResultValidationRespFB fb=(InvResultValidationRespFB) form;
		try {
			InvResultValidationRespUTIL.getResultValidationTemplateForSelectedWorkOrders(request,fb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("SAME");
	}

	public ActionForward AJX_INSERT_MODIFY_CANNEDDETAILS(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		InvResultValidationRespFB fb=(InvResultValidationRespFB)objForm_p;
		System.out.println("ajax fire");
		String val=InvResultValidationRespUTIL.checkCannedCodeName(fb,objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(val);
		return null;
	}

	public ActionForward PRINTREPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("viewInvestigationACT: printreport  ");
		InvResultValidationRespFB fb = (InvResultValidationRespFB) form;
		
		InvResultValidationRespUTIL.printReport(fb,request,response);
		
		return null;
	}
	
	public ActionForward AJX_CHECK_REQFORM_TESTTYPE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		InvResultValidationRespFB fb=(InvResultValidationRespFB)objForm_p;
		
		StringBuffer strBuff = InvResultValidationRespUTIL.getRequisitionFormMasterData(fb, objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}

	public ActionForward GETFILEUPLOADDATATESTWISE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		InvResultValidationRespFB fb=(InvResultValidationRespFB)objForm_p;
		
		String strBuff = InvResultValidationRespUTIL.getfileuploaddatatestwise(fb, objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff);
		return null;
	}

	public ActionForward GETECHODATA(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		InvResultValidationRespFB fb=(InvResultValidationRespFB)objForm_p;
		 String reqdno=objRequest_p.getParameter("ECHODNO");
		String strBuff = InvResultValidationRespUTIL.getechodata(reqdno, objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		strBuff = strBuff.replaceAll("&", "@ampersand@");
		strBuff = strBuff.replaceAll("%", "@percent@");
		objResponse_p.getWriter().print(strBuff);
		return null;
	}
}
