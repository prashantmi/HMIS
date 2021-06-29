/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: SIDDHARTH SRIVASTAVA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : RESULT ENTRY ACTION
 ## Purpose						        : 
 ## Date of Creation					: 13/04/2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.transactions.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.fb.invReportAddendumFB;
import new_investigation.transactions.controller.utl.InvResultEntryRespUTIL;
import new_investigation.transactions.controller.utl.InvResultEntryUTIL;
import new_investigation.transactions.controller.utl.InvResultValidationRespUTIL;
import new_investigation.transactions.controller.utl.InvResultValidationUTIL;
import new_investigation.transactions.controller.utl.OnlinePatientAcceptanceUTL;
import new_investigation.transactions.controller.utl.SampleCollectionUTL;
import new_investigation.transactions.controller.utl.invReportAddendumUTIL;
import new_investigation.vo.antibioticprocessVO;
import new_investigation.vo.invAntibioticProcessVO;
import new_investigation.vo.template.ResultEntryVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import hisglobal.backutil.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;

public class InvResultEntryRespACT extends CSRFGardTokenAction
{	
	String error="Error Message Starts From Here [Added By Prashant] :";

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session=WebUTIL.getSession(request);
		session.setAttribute("jsonResponse", null);
		WebUTIL.refreshTransState(request);
		return this.NEW(mapping,form,request,response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.setAttribute("jsonResponse", null);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward OLDENTRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
	    session.removeAttribute(InvestigationConfig.LIST_RESULT_ENTRY_RESP_TEST_TEMPLATE_VO);
	    
		return mapping.findForward("OLDENTRY");
	}
	
	
	public ActionForward AjaxGetEntryReqnList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		generateToken(request);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		HttpSession session=request.getSession();
        session.removeAttribute(InvestigationConfig.LIST_RESULT_ENTRY_RESP_TEST_TEMPLATE_VO);
		
		JsonObject jsonResponse = new JsonObject();
		StringWriter sw=new StringWriter();
		try {
			
			jsonResponse=InvResultEntryRespUTIL.AjaxGetEntryReqnList(fb,request);
			
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
		//isValue=1;
		HttpSession session=WebUTIL.getSession(request);
		System.out.println("OnlinePatientAcceptanceACT: GetReqnDetailsNonEditable  ");
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
	     //session.removeAttribute(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
	     return mapping.findForward("OLDENTRY");
	}
	
	
	public ActionForward GetReqnDetailsEditable(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		InvResultValidationRespFB fb = (InvResultValidationRespFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		
		String toDate=fb.getToDate();
	    String fromDate=fb.getFromDate();
	    String sampleAreaCode=fb.getSampleAreaCode();
		String sampleAreaName=fb.getSampleAreaName();
		fb.setIsraisingsave("0");
		
		InvResultEntryRespUTIL.showResultEntryPatDetails(fb,request);
		
		fb.setToDateHidden(toDate);
		fb.setFromDateHidden(fromDate);
		fb.setSampleAreaCode(sampleAreaCode);
		fb.setSampleAreaName(sampleAreaName);
	     return mapping.findForward("OLDENTRY");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request, response);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		fb.setNewEntry("1");
		InvResultEntryRespUTIL.saveResultEntryPatientDetails(fb, request);
		return this.OLDENTRY(mapping, form, request, response);
	} 
	
	//added by krishnan nema on 01/02/2019 for save to draft changes
	public ActionForward SAVETODRAFT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request, response);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		fb.setNewEntry("1");
		InvResultEntryRespUTIL.saveResultEntryPatientDetails(fb, request);
		fb.setIsSaveToDraft(null);
		return this.OLDENTRY(mapping, form, request, response);
	} 
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		//fb.setNewEntry("2");
		InvResultEntryRespUTIL.modifyResultEntryPatientDetails(fb, request);
		//fb.setNewEntry("1");
		return this.OLDENTRY(mapping, form, request, response);
	} 
	
	public ActionForward MODIFYDRAFT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultValidationRespFB fb=(InvResultValidationRespFB)form;
		//fb.setNewEntry("2");
		InvResultEntryRespUTIL.modifyResultEntryPatientDetails(fb, request);
		//fb.setNewEntry("1");
		return this.OLDENTRY(mapping, form, request, response);
	}
	
	
	
	
	
	
	
	
	
	public ActionForward GETDETAILS2(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		InvResultEntryFB fb = (InvResultEntryFB) form;
		fb.setShowStatus("0");
          
		String searchBy = fb.getSearchBy();
		
		ControllerUTIL.setSysdate(request);
	      	
		String sampleAreaName=fb.getSampleAreaName();
		String sampleAreaCode=fb.getSampleAreaCode();
		
		InvResultEntryUTIL.setPatientEssentials(fb,request);
		 
		if(searchBy.equals("1"))
	    	fb.setSearchBy("1");
	    else 
	    	fb.setSearchBy("0");
	
		
		
		
	
		//InvResultEntryUTIL.getEssential(fb,request);
	     fb.setIsSampleAreaSelected("1");
	     fb.setShowStatus("0");
	     fb.setSampleAreaName(sampleAreaName);
	     fb.setSampleAreaCode(sampleAreaCode);
	     
	     fb.setCurrentPage(1);
		
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GetReqnDetailsEditable2(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("InvResultEntryACT: GetReqnDetailsEditable  ");
		InvResultEntryFB fb = (InvResultEntryFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		
		String toDate=fb.getToDate();
	    String fromDate=fb.getFromDate();
	    String sampleAreaCode=fb.getSampleAreaCode();
		String sampleAreaName=fb.getSampleAreaName();
		fb.setIsraisingsave("0");
		
		InvResultEntryUTIL.showResultEntryPatDetails(fb,request);
		 
		fb.setToDateHidden(toDate);
		fb.setFromDateHidden(fromDate);
		fb.setSampleAreaCode(sampleAreaCode);
		fb.setSampleAreaName(sampleAreaName);
		return mapping.findForward("NEW");
	}
	
	
	
	
	
	
	 
	 public ActionForward SAVETODRAFT2(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		// validateToken(request, response);
		 InvResultEntryFB fb=(InvResultEntryFB)form;
		 
		 
		/* List<FormFile> myFiless = fb.getFiles();
		 System.out.println("upload files size:");
		 System.out.println(myFiless.size());
		 for(FormFile f : myFiless){
			 System.out.println(f.getFileName());
			// FormFile myFile =(FormFile)myFiles.get(i) ;
			 //System.out.println(myFile.getContentType());
			 }*/
		InvResultEntryUTIL.saveResultEntryPatientDetails(fb, request);
		fb.setIsSaveToDraft(null);
		 return this.NEW(mapping, form, request, response);
	 } 
	
	
	 public ActionForward SAVE2(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		// validateToken(request, response);
		 InvResultEntryFB fb=(InvResultEntryFB)form;
		 
		 
		/* List<FormFile> myFiless = fb.getFiles();
		 System.out.println("upload files size:");
		 System.out.println(myFiless.size());
		 for(FormFile f : myFiless){
			 System.out.println(f.getFileName());
			// FormFile myFile =(FormFile)myFiles.get(i) ;
			 //System.out.println(myFile.getContentType());
			 }*/
		 
		 
		 
		 
		InvResultEntryUTIL.saveResultEntryPatientDetails(fb, request);
		
		 return this.NEW(mapping, form, request, response);
	 } 
	 
	 
	 
	 
	 
	 
	
		public ActionForward SHOWCANNEDDETAILS(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvResultEntryFB fb=(InvResultEntryFB)objForm_p;
			
		 
			
			
			StringBuffer strBuff = InvResultEntryUTIL.showCannedDetails(fb,objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	  
	
 
		
		
		 public ActionForward MODIFY2(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		 {
			 generateToken(request);
			 InvResultEntryFB fb=(InvResultEntryFB)form;
			 InvResultEntryUTIL.modifyResultEntryPatientDetails(fb, request);
			 return this.NEW(mapping, form, request, response);
		 } 
		 
		 public ActionForward MODIFYDRAFT2(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		 {
			 generateToken(request);
			 InvResultEntryFB fb=(InvResultEntryFB)form;
			 InvResultEntryUTIL.modifyResultEntryPatientDetails(fb, request);
			 return this.OLDENTRY(mapping, form, request, response);
		 } 
		
		 
			public ActionForward AUTOCANNEDDETAILS(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvResultEntryFB fb=(InvResultEntryFB)objForm_p;
					
				StringBuffer strBuff = InvResultEntryUTIL.autoCannedDetails(fb,objRequest_p);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			
			public ActionForward AJX_INSERT_MODIFY_CANNEDDETAILS(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvResultEntryFB fb=(InvResultEntryFB)objForm_p;
				System.out.println("ajax fire");
				String val=InvResultEntryUTIL.checkCannedCodeName(fb,objRequest_p);
				/*StringBuffer strBuff = InvResultEntryUTIL.autoCannedDetails(fb,objRequest_p);*/
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(val);
				return null;
			}
		 
			
			public ActionForward PRINTREPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				System.out.println("viewInvestigationACT: printreport  ");
				InvResultEntryFB fb = (InvResultEntryFB) form;
				
				InvResultEntryUTIL.printReport(fb,request,response);
				
				return null;
				//return mapping.findForward("NEW");
			}
			
			
			public ActionForward AJX_CHECK_BILLING(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvResultEntryFB fb=(InvResultEntryFB)objForm_p;
				System.out.println("ACT:AJX_CHECK_BILLING");
				String val=InvResultEntryUTIL.checkBilling(fb,objRequest_p);
				/*StringBuffer strBuff = InvResultEntryUTIL.autoCannedDetails(fb,objRequest_p);*/
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(val);
				return null;
			}	

			
			public ActionForward AJX_CHECK_REQFORM_TESTTYPE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvResultEntryFB fb=(InvResultEntryFB)objForm_p;
				
				StringBuffer strBuff = InvResultEntryUTIL.getRequisitionFormMasterData(fb, objRequest_p);
				//System.out.println("strBuff act"+strBuff);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			
			
			public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				//isValue=1;
				HttpSession session=WebUTIL.getSession(request);
				session.removeAttribute("addendumStatusFromRaising");
				System.out.println("OnlinePatientAcceptanceACT: GETPATDTL ");
				InvResultValidationRespFB fb = (InvResultValidationRespFB) form;
				ControllerUTIL.setSysdate(request);
				//fb.setShowStatus("1");
				String selectedCheckBoxValue=fb.getSelectedCheckbox();
				String[] selectedCheckBoxValueArr=selectedCheckBoxValue.split("\\$");
				String crNo=selectedCheckBoxValueArr[0];
				String reqNO=selectedCheckBoxValueArr[1];
				String reqDNo=selectedCheckBoxValueArr[2];
				String grpCode=selectedCheckBoxValueArr[3];
				String testCode=selectedCheckBoxValueArr[4];
				String labCode=selectedCheckBoxValueArr[5];
				if(labCode.equals("0")) {labCode="%";}
				 // save data for addendum report 
				 //InvResultEntryRespUTIL.saveAddendumDetails(fb, request);
				 
				 session.setAttribute("PatCrNo", crNo);
				 session.setAttribute("testCode", testCode);
				 session.setAttribute("labCode", labCode);
				 session.setAttribute("IsAddendum", "1");
				 session.setAttribute("IsAddendumENTRY", "1");
				 session.setAttribute("reqNo", reqNO);
				 session.setAttribute("reqDno", reqDNo);
				 
				 InvestigationRaisingDtlFB fb1 = new InvestigationRaisingDtlFB(); 
					
				 request.setAttribute("InvestigationRaisingDtlFB", fb1);
				 fb1.setAptStatus("10");
				fb1.setPatCrNo(crNo);
				fb1.setTestCodee(testCode);
			    fb1.setLabCodee(labCode);
	            fb1.setIsAddendum("1");
	            fb1.setHmode("GETPATDTL");  
	            fb1.setRequisitionNo(reqNO);
	            
	            return mapping.findForward("GETPATDTL");
			}
			

			
			
			public ActionForward CHECKCISPARAMETERDEPENDENT(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvResultEntryFB fb=(InvResultEntryFB)objForm_p;
				System.out.println("ajax fire");
				HttpSession session=objRequest_p.getSession();
				String dependendtvalue=fb.getDependentelementcodevalue();
				String val=InvResultEntryUTIL.CHECKCISPARAMETERDEPENDENT(dependendtvalue,objRequest_p,fb.getRequisitionNo(),fb.getSelectedindex(),fb);
				
				 Map<String,List<antibioticprocessVO>> mpBilled= ((Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_in_sessionhyperlinkdata));

				 
				 if(fb.getIscallfromonloaddynamic()!=null && !fb.getIscallfromonloaddynamic().equals("1"))
					 {
					 fb.setIscallfromonloaddynamic(null);	 
					 }
				 
				/*StringBuffer strBuff = InvResultEntryUTIL.autoCannedDetails(fb,objRequest_p);*/
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(val);
				return null;
			}


			
			
}
