package new_investigation.transactions.controller.action;


/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PUNEET SINGH
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Investigation Requisition Raising for external investigation
 ## Purpose						        : 
 ## Date of Creation					: 02-Feb-2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


import hisglobal.backutil.exception.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.externalInvestigationCaptureFB;
import new_investigation.transactions.controller.utl.externalInvestigationCaptureUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import MongoHelper.MongoXmlHandler;
public class externalInvestigationCaptureACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		externalInvestigationCaptureFB fb=(externalInvestigationCaptureFB)form;
		HttpSession session=WebUTIL.getSession(request);
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("external capture: GETPATDTL  ");
		externalInvestigationCaptureFB fb = (externalInvestigationCaptureFB) form;
		fb.setAptStatus("10");
		ControllerUTIL.setSysdate(request);
	
		externalInvestigationCaptureUTL.setPatientRegistrationEssentials(fb,request);

		
		if(fb.getPatStatusCode()!=null&&fb.getPatStatusCode().equals(InvestigationConfig.PATIENT_STATUS_IPD)&&fb.getAccountNo()!=null&& !"0".equals(fb.getAccountNo()))
		{
		externalInvestigationCaptureUTL.searchLaboratoryWiseTest(fb,request);
		}
		
		
		if(fb.getPatStatusCode()!=null&&Integer.parseInt(fb.getPatStatusCode())!=Integer.parseInt(InvestigationConfig.PATIENT_STATUS_IPD))
		{
		externalInvestigationCaptureUTL.searchLaboratoryWiseTest(fb,request);	
		}

		return mapping.findForward("NEW");
		
	}
	
	
	 
	
/*	public ActionForward SEARCHLABWISETEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		externalInvestigationCaptureUTL.searchLaboratoryWiseTest(fb,request);
		
		return mapping.findForward("NEW");
	}
*/
	
/*	public ActionForward SEARCHLABWISETESTGROUPONCLICK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		externalInvestigationCaptureUTL.searchLaboratoryWiseTestGroupOnClick(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SEARCHTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHTEST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		externalInvestigationCaptureUTL.searchBookMark(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHGROUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHGROUP  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		externalInvestigationCaptureUTL.getTestsBasedOnGroups(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHBOOKMARK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHBOOKMARK  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		externalInvestigationCaptureUTL.searchBookMark(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward CLEARLABTESTLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: CLEARLABTESTLIST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		request.getSession().removeAttribute(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
		
		Status status = new Status();
		status.add(Status.TRANSINPROCESS, "", "");
		WebUTIL.setStatus(request, status);
		
		return mapping.findForward("NEW");
	}
	*/
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	/*
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
		externalInvestigationCaptureUTL.deleteRow(fb, request);
    	return mapping.findForward("NEW"); 
	}
	
	*/
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request, response);
		 externalInvestigationCaptureFB fb=(externalInvestigationCaptureFB)form;
		 
		
		 
		 FormFile file = fb.getFile();//Get the FormFile object from ActionForm.
		 String fileName = file.getFileName(); //Get file size of uploaded file.
		 Integer fileSize = file.getFileSize(); //Get file size of uploaded file.
		 String contentType = file.getContentType(); //The content type of the uploaded file.
		 byte[] filedatabyte=file.getFileData(); 
		 if(filedatabyte!=null && filedatabyte.length>0)
		 {
         MongoXmlHandler.getInstance("externalCapture").savePDFFile(fileName, filedatabyte);
         fb.setFileName(fileName);
         fb.setDbName("externalCapture");
		 }
		 // Get InputStream object of uploaded File.
		// InputStream inputFile = file.getInputStream();

		/*
		 * FileOutputStream fileOut = new FileOutputStream("c:\\temp\\" + fileName);
                    fileOut.write(fileData, 0, fileSize);
                    fileOut.flush();
                     fileOut.close();
                     */ 
		 
		 externalInvestigationCaptureUTL.saveRequisitionDetails(fb, request);
		 return this.NEW(mapping, form, request, response);
	 } 
	 
	 public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
		    InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
			Status  objStatus=new Status();
			objStatus.add(Status.NEW);
			WebUTIL.setStatus(request,objStatus);
			return mapping.findForward("CANCEL");
		}
		
	 
	 public ActionForward UPLOAD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
		    InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
			Status  objStatus=new Status();
			objStatus.add(Status.NEW);
			WebUTIL.setStatus(request,objStatus);
			return mapping.findForward("UPLOAD");
		}
	 /**
		 * AJX_DELETE_LABTESTCODEARRAY     
	 	* Ajax Function for Deleting selected lab Test
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
	/*	public ActionForward AJX_DELETE_LABTESTCODEARRAY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = externalInvestigationCaptureUTL.deleteLabTestCodeArray(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		*/
		 /**
		 * AJX_MODIFY_LABTESTCODEARRAY     
	 	* Ajax Function for modifying selected lab Test
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
		/*public ActionForward AJX_MODIFY_LABTESTCODEARRAY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = externalInvestigationCaptureUTL.modifyLabTestCodeArray(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		*/
		
		/**
		 * AJX_MODIFY_LABTESTCODEARRAY     
	 	* Ajax Function for modifying selected lab Test
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
	/*	public ActionForward AJX_MODIFY_PRIORITY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = externalInvestigationCaptureUTL.modifyPriority(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		*/
		/**
		 * AJX_MODIFY_LABTESTCODEARRAY     
	 	* Ajax Function for modifying selected lab Test
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
		/*public ActionForward AJX_TEST_COMBO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = externalInvestigationCaptureUTL.pouplateTestCombo(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		*/
		/** AJX_PRV_TEST_DTL
		 * AJX_MODIFY_LABTESTCODEARRAY     
	 	* Ajax Function for modifying selected lab Test
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
	/*	public ActionForward AJX_TEST_GROUP(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = externalInvestigationCaptureUTL.pouplateTestGroup(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	 
		public ActionForward AJX_PRV_TEST_DTL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = externalInvestigationCaptureUTL.pouplatePrvTestDtl(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	 
		
		
		public ActionForward APTBASEDTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: APTBASEDTEST  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			fb.setAptStatus("0");
			ControllerUTIL.setSysdate(request);
			externalInvestigationCaptureUTL.getAptBasedTest(fb,request);
			return mapping.findForward("NEW");
		}
		
		
		
		public ActionForward APTDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: APTDETAIL  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			ControllerUTIL.setSysdate(request);
			externalInvestigationCaptureUTL.setPatientRegistrationEssentials(fb,request);
			externalInvestigationCaptureUTL.searchLaboratoryWiseTest(fb,request); 
			externalInvestigationCaptureUTL.searchBookMark(fb,request);
			return mapping.findForward("NEW");
		}
		*/
		
		
	 //SEARCHTESTCODEWISE
	/*	public ActionForward SEARCHTESTCODEWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: SEARCHTESTCODEWISE  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			ControllerUTIL.setSysdate(request);
			externalInvestigationCaptureUTL.getTestsCodeWiseDtl(fb,request);
			
			return mapping.findForward("NEW");
		}
		
	  //TESTCODE
		
		public ActionForward TESTCODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: TESTCODE  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			ControllerUTIL.setSysdate(request);
			//externalInvestigationCaptureUTL.getTestsCodeWiseDtl(fb,request);
			
			return mapping.findForward("NEW");
		}  
		
		
		//DELETEREQDTL
		
		public ActionForward DELETEREQDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: DELETEREQDTL  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			ControllerUTIL.setSysdate(request);
			externalInvestigationCaptureUTL.deleteReqDtl(fb,request);
			
			return mapping.findForward("NEW");
		} 
		
		
		
		
		/** AJX_DUPLICACY_LABTEST
		 * AJX_MODIFY_LABTESTCODEARRAY     
	 	* Ajax Function for modifying selected lab Test
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
	/*	public ActionForward AJX_DUPLICACY_LABTEST(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) objForm_p;
			
			StringBuffer strBuff = externalInvestigationCaptureUTL.checkRequisitionPending(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		
		
		
		public ActionForward AJX_MODIFY_LABTESTCODEARRAY_APTNO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = externalInvestigationCaptureUTL.modifyLabTestCodeArrayForAppoitmentNo(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		
		
		public ActionForward APTBYDESK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: APTBYDESK  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			fb.setAptStatus("2");
			ControllerUTIL.setSysdate(request);
			externalInvestigationCaptureUTL.getAptByDesk(fb,request);
			return mapping.findForward("NEW");
		}
		
		
		public ActionForward GETAPPOINTMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: GETAPPOINTMENT  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			fb.setAptStatus("5");
			ControllerUTIL.setSysdate(request);
			externalInvestigationCaptureUTL.getAppointment(fb,request);
			return mapping.findForward("NEW");
		}
		
		 public ActionForward SAVEAPPOINTMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		 {
			 InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
			 externalInvestigationCaptureUTL.saveAppointmentDetails(fb, request);
			 return this.NEW(mapping, form, request, response);
		 } 
		
		 
		 */
		
}
