package investigationDesk.transactions.controller.action;


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
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import investigationDesk.InvestigationConfig;
import investigationDesk.transactions.controller.fb.InvestigationRaisingDtlFB;
import investigationDesk.transactions.controller.utl.InvestigationRaisingDtlUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
public class InvestigationRaisingDtlACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
/*	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
		//Resetting selected lab test code array
		
		fb.setLabTestCodeArray("");
		fb.reset(mapping, request);
		
		//session.removeAttribute(InvestigationConfig.COMPONENT_WISE_INDICATION_MAP);
		WebUTIL.refreshTransState(request);
		 
	//InvestigationReqDtlUTL.getInvReqDtlEssentials(fb, request); APTBASEDTEST
		 
		return mapping.findForward("NEW");
	}*/
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: GETPATDTL  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		InvestigationRaisingDtlUTL.setPatientRegistrationEssentials(fb,request);

 
		if(fb.getPatStatusCode()!=null&&fb.getPatStatusCode().equals(InvestigationConfig.PATIENT_STATUS_IPD)&&fb.getAccountNo()!=null&& !"0".equals(fb.getAccountNo()))
		{

 
		InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request);

		 }
		if(fb.getPatStatusCode()!=null&&Integer.parseInt(fb.getPatStatusCode())!=Integer.parseInt(InvestigationConfig.PATIENT_STATUS_IPD))
		{
		InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request);	
		}

		return mapping.findForward("NEW");
		
	}
	
	// Added by VASU on 24-Nov-2017 for Investigation order
	public ActionForward EXTNEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: GETPATDTL  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		WebUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_TYPE, fb.getDeskType());
		InvestigationRaisingDtlUTL.setPatientRegistrationEssentials(fb,request);

			 
		if(fb.getPatStatusCode()!=null&&fb.getPatStatusCode().equals(InvestigationConfig.PATIENT_STATUS_IPD)&&fb.getAccountNo()!=null&& !"0".equals(fb.getAccountNo()))
		{

			 
		 InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request);

		}
		if(fb.getPatStatusCode()!=null&&Integer.parseInt(fb.getPatStatusCode())!=Integer.parseInt(InvestigationConfig.PATIENT_STATUS_IPD))
		{
		 InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request);	
		}

			return mapping.findForward("NEW");
					
		}

	 
	
	public ActionForward SEARCHLABWISETEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SEARCHLABWISETESTGROUPONCLICK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		InvestigationRaisingDtlUTL.searchLaboratoryWiseTestGroupOnClick(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SEARCHTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHTEST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		InvestigationRaisingDtlUTL.searchBookMark(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHGROUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHGROUP  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		InvestigationRaisingDtlUTL.getTestsBasedOnGroups(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHBOOKMARK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SEARCHBOOKMARK  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		InvestigationRaisingDtlUTL.searchBookMark(fb,request);
		
		
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
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
		InvestigationRaisingDtlUTL.deleteRow(fb, request);
    	return mapping.findForward("NEW"); 
	}
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	 {
		 InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
		 InvestigationRaisingDtlUTL.saveRequisitionDetails(fb, request);
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
		 InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
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
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = InvestigationRaisingDtlUTL.deleteLabTestCodeArray(fb, objRequest_p);
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
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = InvestigationRaisingDtlUTL.modifyLabTestCodeArray(fb, objRequest_p);
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
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = InvestigationRaisingDtlUTL.modifyPriority(fb, objRequest_p);
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
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = InvestigationRaisingDtlUTL.pouplateTestCombo(fb, objRequest_p);
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
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = InvestigationRaisingDtlUTL.pouplateTestGroup(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	 
		public ActionForward AJX_PRV_TEST_DTL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = InvestigationRaisingDtlUTL.pouplatePrvTestDtl(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	 
		
		
		public ActionForward APTBASEDTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: APTBASEDTEST  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			fb.setAptStatus("0");
			ControllerUTIL.setSysdate(request);
			InvestigationRaisingDtlUTL.getAptBasedTest(fb,request);
			return mapping.findForward("NEW");
		}
		
		
		
		public ActionForward APTDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvestigationRaisingDtlACT: APTDETAIL  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			ControllerUTIL.setSysdate(request);
			InvestigationRaisingDtlUTL.setPatientRegistrationEssentials(fb,request);
			InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request); 
			InvestigationRaisingDtlUTL.searchBookMark(fb,request);
			return mapping.findForward("NEW");
		}
		
		
		 //SEARCHTESTCODEWISE
			public ActionForward SEARCHTESTCODEWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				System.out.println("InvestigationRaisingDtlACT: SEARCHTESTCODEWISE  ");
				InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
				ControllerUTIL.setSysdate(request);
				InvestigationRaisingDtlUTL.getTestsCodeWiseDtl(fb,request);
				
				return mapping.findForward("NEW");
			}
			
		  //TESTCODE
			
			public ActionForward TESTCODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				System.out.println("InvestigationRaisingDtlACT: TESTCODE  ");
				InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
				ControllerUTIL.setSysdate(request);
				//InvestigationRaisingDtlUTL.getTestsCodeWiseDtl(fb,request);
				
				return mapping.findForward("NEW");
			}  
			
			
			//DELETEREQDTL
			
			public ActionForward DELETEREQDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				System.out.println("InvestigationRaisingDtlACT: DELETEREQDTL  ");
				InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
				ControllerUTIL.setSysdate(request);
				InvestigationRaisingDtlUTL.deleteReqDtl(fb,request);
				
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
				InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) objForm_p;
				
				StringBuffer strBuff = InvestigationRaisingDtlUTL.checkRequisitionPending(fb, objRequest_p);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			
			
			
			
			public ActionForward AJX_MODIFY_LABTESTCODEARRAY_APTNO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
				
				//StringBuffer strBuff = InvestigationRaisingDtlUTL.modifyLabTestCodeArrayForAppoitmentNo(fb, objRequest_p);
				objResponse_p.setHeader("Cache-Control", "no-cache");
			//	objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			
			
	 
	  
}
