/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PATHAN BASHA 
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :  REPORT PRINTING ACTION
 ## Purpose						        : 
 ## Date of Creation					: 25-05-2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.transactions.controller.action;



import investigationDesk.transactions.controller.fb.viewInvestigationFB;
import investigationDesk.transactions.controller.utl.viewInvestigationUTL;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultReportPrintingFB;
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB;
import new_investigation.transactions.controller.fb.machineResultEntryFB;
import new_investigation.transactions.controller.utl.InvResultEntryUTIL;
import new_investigation.transactions.controller.utl.InvResultReportPrintingUTIL;
import new_investigation.transactions.controller.utl.machineResultEntryUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class InvResultReportPrintinggRespACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultReportPrintingFB fb=(InvResultReportPrintingFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.setAttribute("jsonResponse", null);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward AajaxGetCollAreaNLabList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultReportPrintingFB fb=(InvResultReportPrintingFB)form;
		HttpSession session=WebUTIL.getSession(request);
		//Resetting selected lab test code array
		WebUTIL.refreshTransState(request);	
		JsonObject jsonResponse = new JsonObject();
		StringWriter sw=new StringWriter();
		
		try
		{
		//updated by krishnan nema on 05/10/2018
		UserVO userVO=ControllerUTIL.getUserVO(request);
		//fb.setPatTestType("2");
		Map mp=new HashMap();
		jsonResponse =InvResultReportPrintingUTIL.getEssentialnew(fb,request);
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
				jsonResponse.addProperty("errorMsg", sw.toString());
				response.getWriter().print(jsonResponse.toString());
				//response.getWriter().print(error+sw.toString());
			} catch (IOException e1) { e1.printStackTrace(); }
		}
		return null;
	
	}
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("OnlinePatientAcceptanceACT: GETDETAILS  ");
        
		InvResultReportPrintingFB fb=(InvResultReportPrintingFB)form;
		fb.setShowStatus("0");
        String dateType=fb.getDateType();
		ControllerUTIL.setSysdate(request);
		
		InvResultReportPrintingUTIL.setResultReportPrintingEssentials(fb,request);
		fb.setDateType(dateType);
		return mapping.findForward("NEW");
	}
	
	
	 public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ======chandannn");
		InvResultReportPrintingFB fb=(InvResultReportPrintingFB)form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		
		boolean flg=false;
		
		String ftpserver=InvResultReportPrintingUTIL.isfromFTPorMONGO(request,response);
        fb.setFtpserver(ftpserver);

		
		if(ftpserver==null || ftpserver.equals(""))
		InvResultReportPrintingUTIL.showResultEntryPatDetails(fb,request,response);
		else
			InvResultReportPrintingUTIL.showResultEntryPatDetailsFTPnew(fb,request,response);
	
		return null;
	} 
	
	
	 public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
		 InvResultReportPrintingFB fb=(InvResultReportPrintingFB)form;
			Status  objStatus=new Status();
			  objStatus.add(Status.TRANSINPROCESS);
			WebUTIL.setStatus(request,objStatus);
			return mapping.findForward("NEW");
		}
		
	 public ActionForward GETTYPEWISEDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvResultEntryACT: GETTYPEWISEDETAIL  ");
	        
			InvResultReportPrintingFB fb=(InvResultReportPrintingFB)form;
			fb.setShowStatus("0");
	          
			ControllerUTIL.setSysdate(request);
		      	 
			//InvResultEntryUTIL.getEntryTypeDetails(fb,request);
			
			 
			return mapping.findForward("NEW");
		}

	 
	 public ActionForward PRINTREPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			//isValue=1;
		 generateToken(request);
			System.out.println("InvResultReportPrintingACT: PRINTREPORT  ");
			InvResultReportPrintingFB fb=(InvResultReportPrintingFB)form;

				ControllerUTIL.setSysdate(request);
			
				if(fb.getFileName()!=null && !fb.getFileName().equals(""))
			request.setAttribute("hosptialcode", fb.getFileName().substring(0, 5));
				
		
			fb.setShowStatus("1");
			
			boolean flg=false;
			String ftpserver=InvResultReportPrintingUTIL.isfromFTPorMONGO(request,response);
            fb.setFtpserver(ftpserver);

			
			if(ftpserver==null || ftpserver.equals(""))
			InvResultReportPrintingUTIL.printReport(fb,request,response);
			else
				InvResultReportPrintingUTIL.printReportFTP(fb,request,response);	
			return null;
		} 

	 
	 public ActionForward SHOWPATDETAILS_VIEWINV(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			//isValue=1;
			System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ======chandannn");
			InvResultReportPrintingFB fb=(InvResultReportPrintingFB)form;
			ControllerUTIL.setSysdate(request);
			//fb.setShowStatus("1");
			boolean flg=false;
			String ftpserver=InvResultReportPrintingUTIL.isfromFTPorMONGO(request,response);
                   fb.setFtpserver(ftpserver);
			
			if(ftpserver==null || ftpserver.equals(""))
			InvResultReportPrintingUTIL.showResultEntryPatDetails_viewinv(fb,request,response);
			else 
				InvResultReportPrintingUTIL.showResultEntryPatDetails_viewinvFTP(fb,request,response);	
			return null;
		} 
		
	 
		
		


	 
	 
	 public ActionForward AjaxGetValidationReqnList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			
			generateToken(request);
			InvResultReportPrintingFB fb=(InvResultReportPrintingFB)form;
			HttpSession session=request.getSession();
	        
			JsonObject jsonResponse = new JsonObject();
			StringWriter sw=new StringWriter();
			try {
				
				 jsonResponse=InvResultReportPrintingUTIL.setResultReportPrintingEssentialsnew(fb,request);
				
			//	jsonResponse.addProperty("isSuccess", "1");
				response.setContentType("application/Json");
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


	 public ActionForward UPDATEREPORTS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			
			generateToken(request);
			InvResultReportPrintingFB fb=(InvResultReportPrintingFB)form;
			HttpSession session=request.getSession();
	        
			JsonObject jsonResponse = new JsonObject();
			StringWriter sw=new StringWriter();
			try {
				
				 jsonResponse=InvResultReportPrintingUTIL.UPDATEREPORTS(fb,request);
				
			//	jsonResponse.addProperty("isSuccess", "1");
				response.setContentType("application/Json");
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

	 
	 
	 
}
