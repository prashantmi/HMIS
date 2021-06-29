package new_investigation.transactions.controller.action;


/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PAWAN KUMAR B N
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Investigation Requisition Raising

 ## Purpose						        : 
 ## Date of Creation					: 15-Jan-2015
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

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;
import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.fb.invRaisingCumSamCollectionFB;
import new_investigation.transactions.controller.utl.InvestigationRaisingDtlUTL;
import new_investigation.transactions.controller.utl.SampleCollectionUTL;
import new_investigation.transactions.controller.utl.invRaisingCumSamCollectionUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.jfree.chart.plot.RainbowPalette;
public class invRaisingCumSamCollectionACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
		//Resetting selected lab test code array
		
		fb.setLabTestCodeArray("");
		fb.reset(mapping, request);
		
		
		invRaisingCumSamCollectionUTL.getCollectionAreaCombo(fb,request);
		
		
		//session.removeAttribute(InvestigationConfig.COMPONENT_WISE_INDICATION_MAP);
	//	WebUTIL.refreshTransState(request);
		 
	//InvestigationReqDtlUTL.getInvReqDtlEssentials(fb, request); APTBASEDTEST
		 
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: GETPATDTL  ");
		invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
		ControllerUTIL.setSysdate(request);
		invRaisingCumSamCollectionUTL.setPatientRegistrationEssentials(fb,request);

 
		if(fb.getPatStatusCode()!=null&&fb.getPatStatusCode().equals(InvestigationConfig.PATIENT_STATUS_IPD)&&fb.getAccountNo()!=null&& !"0".equals(fb.getAccountNo()))
		{

 
		invRaisingCumSamCollectionUTL.searchLaboratoryWiseTest(fb,request);

		 }
		if(fb.getPatStatusCode()!=null&&Integer.parseInt(fb.getPatStatusCode())!=Integer.parseInt(InvestigationConfig.PATIENT_STATUS_IPD))
		{
		invRaisingCumSamCollectionUTL.searchLaboratoryWiseTest(fb,request);	
		}

		return mapping.findForward("NEW");
		
	}
	
	
	 
	
	public ActionForward SEARCHLABWISETEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
		invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
		ControllerUTIL.setSysdate(request);
		invRaisingCumSamCollectionUTL.searchLaboratoryWiseTest(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SEARCHLABWISETESTGROUPONCLICK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
		invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
		ControllerUTIL.setSysdate(request);
		invRaisingCumSamCollectionUTL.searchLaboratoryWiseTestGroupOnClick(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SEARCHTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHTEST  ");
		invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
		ControllerUTIL.setSysdate(request);
		invRaisingCumSamCollectionUTL.searchBookMark(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHGROUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHGROUP  ");
		invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
		ControllerUTIL.setSysdate(request);
		invRaisingCumSamCollectionUTL.getTestsBasedOnGroups(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHBOOKMARK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHBOOKMARK  ");
		invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
		ControllerUTIL.setSysdate(request);
		invRaisingCumSamCollectionUTL.searchBookMark(fb,request);
		
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward CLEARLABTESTLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: CLEARLABTESTLIST  ");
		invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
		ControllerUTIL.setSysdate(request);
		request.getSession().removeAttribute(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
		
		Status status = new Status();
		status.add(Status.TRANSINPROCESS, "", "");
		WebUTIL.setStatus(request, status);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)form;
		invRaisingCumSamCollectionUTL.deleteRow(fb, request);
    	return mapping.findForward("NEW"); 
	}
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request, response);
		 invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)form;
		 invRaisingCumSamCollectionUTL.saveRequisitionDetails(fb, request);
		 fb.setFinalMandValues("");
		 fb.setLabTestCodeArray("");
		 
		 HttpSession session=request.getSession();
		 session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS); 
		 session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS); 
		 session.removeAttribute(InvestigationConfig.MAP_BOOK_MARK); 
		 //LIST_LAB_WISE_TEST_DTLS
	    return this.NEW(mapping, form, request, response);
	 } 
	 
	 public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
		 invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)form;
			Status  objStatus=new Status();
			  objStatus.add(Status.NEW);
			WebUTIL.setStatus(request,objStatus);
			return mapping.findForward("CANCEL");
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
		public ActionForward AJX_DELETE_LABTESTCODEARRAY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)objForm_p;
			
			StringBuffer strBuff = invRaisingCumSamCollectionUTL.deleteLabTestCodeArray(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
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
		public ActionForward AJX_MODIFY_LABTESTCODEARRAY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)objForm_p;
			
			StringBuffer strBuff = invRaisingCumSamCollectionUTL.modifyLabTestCodeArray(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		
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
		public ActionForward AJX_MODIFY_PRIORITY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)objForm_p;
			
			StringBuffer strBuff = invRaisingCumSamCollectionUTL.modifyPriority(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		
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
		public ActionForward AJX_TEST_COMBO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)objForm_p;
			
			StringBuffer strBuff = invRaisingCumSamCollectionUTL.pouplateTestCombo(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
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
		public ActionForward AJX_TEST_GROUP(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)objForm_p;
			
			StringBuffer strBuff = invRaisingCumSamCollectionUTL.pouplateTestGroup(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	 
		public ActionForward AJX_PRV_TEST_DTL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)objForm_p;
			
			StringBuffer strBuff = invRaisingCumSamCollectionUTL.pouplatePrvTestDtl(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	 
		
		
		public ActionForward APTBASEDTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: APTBASEDTEST  ");
			invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
			fb.setAptStatus("0");
			ControllerUTIL.setSysdate(request);
			invRaisingCumSamCollectionUTL.getAptBasedTest(fb,request);
			return mapping.findForward("NEW");
		}
		
		
		
		public ActionForward APTDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: APTDETAIL  ");
			invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
			ControllerUTIL.setSysdate(request);
			invRaisingCumSamCollectionUTL.setPatientRegistrationEssentials(fb,request);
			invRaisingCumSamCollectionUTL.searchLaboratoryWiseTest(fb,request); 
			invRaisingCumSamCollectionUTL.searchBookMark(fb,request);
			return mapping.findForward("NEW");
		}
		
		
		/**
		 * AJX_DUPLICACY_SAMPLENO     
	 	* Ajax Function for Checking Duplicacy
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
		public ActionForward AJX_DUPLICACY_SAMPLENO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)objForm_p;
			String sampleNo = objRequest_p.getParameter("strSampleNo");
			String sampleAreaCode = objRequest_p.getParameter("sampleAreaCode");
			StringBuffer strBuff = SampleCollectionUTL.checkSampleNoDuplicacyRaisingCumSampleCollection(fb, objRequest_p,sampleNo,sampleAreaCode);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	  
		 //SEARCHTESTCODEWISE
		public ActionForward SEARCHTESTCODEWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: SEARCHTESTCODEWISE  ");
			invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
			ControllerUTIL.setSysdate(request);
			 
			invRaisingCumSamCollectionUTL.getTestsCodeWiseDtl(fb,request);
			
			return mapping.findForward("NEW");
		}
		
	  //TESTCODE
		
		public ActionForward TESTCODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: TESTCODE  ");
			invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
			ControllerUTIL.setSysdate(request);
			//InvestigationRaisingDtlUTL.getTestsCodeWiseDtl(fb,request);
			
			return mapping.findForward("NEW");
		}  
		
		
		//DELETEREQDTL
		
		public ActionForward DELETEREQDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: DELETEREQDTL  ");
			invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
			ControllerUTIL.setSysdate(request);
			invRaisingCumSamCollectionUTL.deleteReqDtl(fb,request);
			
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
		public ActionForward AJX_DUPLICACY_LABTEST(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) objForm_p;
			
			StringBuffer strBuff = invRaisingCumSamCollectionUTL.checkRequisitionPending(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		
		
		
		public ActionForward AJX_MODIFY_LABTESTCODEARRAY_APTNO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)objForm_p;
			
			//StringBuffer strBuff = InvestigationRaisingDtlUTL.modifyLabTestCodeArrayForAppoitmentNo(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
		//	objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	 
		
		//to print bar code
		public ActionForward PRINT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
			return mapping.findForward("PRINT"); 
		}

		
		//functions for advised test through desk
		
		public ActionForward APTBYDESK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: APTBYDESK  ");
			invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
			fb.setAptStatus("2");
			fb.setAreaCode(fb.getSampleAreaCode());
			ControllerUTIL.setSysdate(request);
			invRaisingCumSamCollectionUTL.getAptByDesk(fb,request);
			return mapping.findForward("NEW");
		}
		
		
		public ActionForward GETAPPOINTMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: GETAPPOINTMENT  ");
			invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
			fb.setAptStatus("5");
			
			ControllerUTIL.setSysdate(request);
			invRaisingCumSamCollectionUTL.getAppointment(fb,request);
			return mapping.findForward("NEW");
		}
		
		 public ActionForward SAVEAPPOINTMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		 {
			 invRaisingCumSamCollectionFB fb=(invRaisingCumSamCollectionFB)form;
			 invRaisingCumSamCollectionUTL.saveAppointmentDetails(fb, request);
			 return this.NEW(mapping, form, request, response);
		 } 
		 
			//SEARCH USER GROUP CODE WISE
			public ActionForward SEARCHGROUPCODEWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				System.out.println("InvestigationRaisingDtlACT: SEARCHGROUPCODEWISE  ");
				invRaisingCumSamCollectionFB fb = (invRaisingCumSamCollectionFB) form;
				ControllerUTIL.setSysdate(request);
				invRaisingCumSamCollectionUTL.getGroupCodeWiseDtl(fb,request);
				
				return mapping.findForward("NEW");
			}
			
			
}
