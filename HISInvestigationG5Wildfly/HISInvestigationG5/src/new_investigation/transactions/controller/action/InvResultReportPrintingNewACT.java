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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultReportPrintingFB;
import new_investigation.transactions.controller.fb.InvResultReportPrintingNewFB;
import new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB;
import new_investigation.transactions.controller.utl.InvResultEntryUTIL;
import new_investigation.transactions.controller.utl.InvResultReportPrintingNewUTIL;
import new_investigation.transactions.controller.utl.InvResultReportPrintingUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InvResultReportPrintingNewACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return mapping.findForward("NEW");

		//return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		
		String patcrno=(String)request.getAttribute("patCrNo");
		
	
		InvResultReportPrintingNewFB fb=(InvResultReportPrintingNewFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
		     fb.setPatCRNo(patcrno);			
		
		Map mp=new HashMap();
		String json = InvResultReportPrintingNewUTIL.getEssential(fb,request);
		
		response.setHeader("Cache-Control", "no-cache");
		try {
			response.getWriter().print(json);
		    response.setContentType("application/Json");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);
	
		return null;
    	
	
	}
	
	
	public ActionForward GETDATACRNOWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		
		String patcrno=(String)request.getAttribute("patCrNo");
		
	
		InvResultReportPrintingNewFB fb=(InvResultReportPrintingNewFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
		    // fb.setPatCRNo(patcrno);			
		
		Map mp=new HashMap();
		String json = InvResultReportPrintingNewUTIL.getdatacrnowise(fb,request);
		
		response.setHeader("Cache-Control", "no-cache");
		try {
			response.getWriter().print(json);
		    response.setContentType("application/Json");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);
	
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
	
	
	 public ActionForward SHOWREPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ======chandannn");
		InvResultReportPrintingNewFB fb=(InvResultReportPrintingNewFB)form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		
		boolean flg=false;
		
		String ftpserver=InvResultReportPrintingUTIL.isfromFTPorMONGO(request,response);
        fb.setFtpserver(ftpserver);

		
		if(ftpserver==null || ftpserver.equals(""))
			InvResultReportPrintingNewUTIL.showResultEntryPatDetails(fb,request,response);
		else
			InvResultReportPrintingNewUTIL.showResultEntryPatDetailsFTPnew(fb,request,response);
	
		return null;
	} 
	
	
		

	 
	 
	 
}
