/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Puneet
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Machine Result  ENTRY ACTION
 ## Purpose						        : 
 ## Date of Creation					: 28/09/2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.transactions.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.reports.controller.utl.MachineTestReportNewUTIL;
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.transactions.controller.fb.machineResultEntryFB;
import new_investigation.transactions.controller.utl.InvResultValidationRespUTIL;
import new_investigation.transactions.controller.utl.machineResultEntryUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

public class invMachineResultEntryActResp extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
			return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETMACHINE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: GETMACHINE  ");
        machineResultEntryFB fb = (machineResultEntryFB) form;
    	HttpSession session= request.getSession();
		fb.setShowStatus("0");
		session.removeAttribute(InvestigationConfig.LIST_MACHINE_COMBO);

        ControllerUTIL.setSysdate(request);
	    machineResultEntryUTIL.getLabBasedMachine(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward AjaxGetMachineList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		machineResultEntryFB fb = (machineResultEntryFB) form;
		
		try {
			 JsonObject jsonResponse = new JsonObject();
			
			 jsonResponse=machineResultEntryUTIL.AjaxGetMachineList(fb, request);
			 
			 response.setContentType("application/Json");
			 response.getWriter().print(jsonResponse.toString());
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			StringWriter sw=new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { response.getWriter().print(sw.toString()); }
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
			try { response.getWriter().print(sw.toString()); }
			catch (IOException e1) { e1.printStackTrace(); }
		}
		return null;
	}
	
	
	
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: GETDETAILS  ");
        machineResultEntryFB fb = (machineResultEntryFB) form;
		fb.setShowStatus("0");
        ControllerUTIL.setSysdate(request);
	    machineResultEntryUTIL.setPatientEssentials(fb,request);
	    fb.setFlag("");
		return mapping.findForward("NEW");
	}
	

	 
	 
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		machineResultEntryFB fb=(machineResultEntryFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	 
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request, response);
		 machineResultEntryFB fb=(machineResultEntryFB)form;
		 String lab=fb.getLabCode();
		 String mcode=fb.getMachineCode();
		 String sdate=fb.getSampleCollDate();
		 String rdate=fb.getResultEntryDate();
		 String record=fb.getRecord();
		 
	     machineResultEntryUTIL.saveMachineResultEntry(fb, request);
	     
	     fb.setLabCode(lab);
	     fb.setMachineCode(mcode);
	     fb.setSampleCollDate(sdate);
	     fb.setResultEntryDate(rdate);
	     fb.setRecord(record);
	     fb.setFlag("1");
			return mapping.findForward("NEW");

//		 return this.NEW(mapping, form, request, response);
	 } 
	 


	 
	 public ActionForward AjaxGetLabList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
		 
		 machineResultEntryFB fb = (machineResultEntryFB) form;
			
		try {
				 JsonObject jsonResponse = new JsonObject();
				
				 jsonResponse=machineResultEntryUTIL.AjaxGetLabList(fb, request);
				 
				 response.setContentType("application/Json");
				 response.getWriter().print(jsonResponse);
			}
			
			
	catch(JsonIOException e)
	       {
				e.printStackTrace();
				StringWriter sw=new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				response.setContentType("text/html");
				try { response.getWriter().print(sw.toString()); }
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
				try { response.getWriter().print(sw.toString()); }
				catch (IOException e1) { e1.printStackTrace(); }
			}
			return null;
		}
	 
		 
	 
	 
	 
	 public ActionForward AjaxGetValidationReqnList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			
			generateToken(request);
			machineResultEntryFB fb=(machineResultEntryFB)form;
			HttpSession session=request.getSession();
	        
			JsonObject jsonResponse = new JsonObject();
			StringWriter sw=new StringWriter();
			try {
				
				 jsonResponse=machineResultEntryUTIL.setPatientEssentialsnewform(fb,request);
				
			//	jsonResponse.addProperty("isSuccess", "1");
			//	 setContentType("text/html; charset=UTF-8");
				response.setContentType("application/Json;charset=UTF-8");
				response.getWriter().print(jsonResponse.toString());
			}
			catch (JsonIOException e) {
				e.printStackTrace();
				
				e.printStackTrace(new PrintWriter(sw));
				response.setContentType("text/html");
				try { 
					jsonResponse.addProperty("isSuccess", "0");
					jsonResponse.addProperty("errorMsg",sw.toString());
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
					jsonResponse.addProperty("errorMsg", sw.toString());
					response.getWriter().print(jsonResponse.toString());
				} catch (IOException e1) { e1.printStackTrace(); }
			}
			return null;
		}


	 
	 
	 public ActionForward AjaxValidateReqnResult(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

			generateToken(request);
			machineResultEntryFB fb=(machineResultEntryFB)form;
			HttpSession session=request.getSession();
			
			JsonObject jsonResponse = new JsonObject();
			StringWriter sw=new StringWriter();
			try {
				
			 jsonResponse=	machineResultEntryUTIL.saveMachineResultEntrynew(fb,request);
				
				//jsonResponse.addProperty("isSuccess", "1");
				response.setContentType("application/Json");
				response.getWriter().print(jsonResponse.toString());
			}
			catch (JsonIOException e) {
				e.printStackTrace();
				
				e.printStackTrace(new PrintWriter(sw));
				response.setContentType("text/html");
				try { 
					jsonResponse.addProperty("isSuccess", "0");
					jsonResponse.addProperty("errorMsg", sw.toString());
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
					jsonResponse.addProperty("errorMsg",sw.toString());
					response.getWriter().print(jsonResponse.toString());
				} catch (IOException e1) { e1.printStackTrace(); }
			}
			return null;
		}
		

	 
	 
}
