/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.investigation.dataentry;

import hisglobal.backutil.exception.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import investigationDesk.InvestigationConfig;





import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class EHRSection_InvestigationAdviceACT extends CSRFGardTokenAction
{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	
	public ActionForward REQUISITIONRAISING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
		//fb.reset(mapping, request);
		//EHRSection_InvestigationUTL.getEssentials(fb, request,response);
		return mapping.findForward("REQUISITIONRAISING");
	}
	
	/*public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
		//EHRSection_InvestigationUTL.saveDetails(request,response,fb);
		return null;
		
	}*/
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		generateToken(request);
		//System.out.println("EHRSection_InvestigationAdviceACT: GETPATDTL  ");
		EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
		ControllerUTIL.setSysdate(request);
		EHRSection_InvestigationAdviceUTL.setPatientRegistrationEssentials(fb,request);

 
		if(fb.getPatStatusCode()!=null&&fb.getPatStatusCode().equals(InvestigationConfig.PATIENT_STATUS_IPD)&&fb.getAccountNo()!=null&& !"0".equals(fb.getAccountNo()))
		{

 
		EHRSection_InvestigationAdviceUTL.searchLaboratoryWiseTest(fb,request);

		 }
		if(fb.getPatStatusCode()!=null&&Integer.parseInt(fb.getPatStatusCode())!=Integer.parseInt(InvestigationConfig.PATIENT_STATUS_IPD))
		{
		EHRSection_InvestigationAdviceUTL.searchLaboratoryWiseTest(fb,request);	
		}

		return mapping.findForward("NEW");
		
	}
	
	
	 
	
	public ActionForward SEARCHLABWISETEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("EHRSection_InvestigationAdviceACT: SEARCHLABWISETEST  ");
		EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
		ControllerUTIL.setSysdate(request);
		EHRSection_InvestigationAdviceUTL.searchLaboratoryWiseTest(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SEARCHLABWISETESTGROUPONCLICK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("EHRSection_InvestigationAdviceACT: SEARCHLABWISETEST  ");
		EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
		ControllerUTIL.setSysdate(request);
		EHRSection_InvestigationAdviceUTL.searchLaboratoryWiseTestGroupOnClick(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SEARCHTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("EHRSection_InvestigationAdviceACT: SEARCHTEST  ");
		EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
		ControllerUTIL.setSysdate(request);
		EHRSection_InvestigationAdviceUTL.searchBookMark(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHGROUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("EHRSection_InvestigationAdviceACT: SEARCHGROUP  ");
		EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
		ControllerUTIL.setSysdate(request);
		EHRSection_InvestigationAdviceUTL.getTestsBasedOnGroups(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHBOOKMARK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("EHRSection_InvestigationAdviceACT: SEARCHBOOKMARK  ");
		EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
		ControllerUTIL.setSysdate(request);
		EHRSection_InvestigationAdviceUTL.searchBookMark(fb,request);
		
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward CLEARLABTESTLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("EHRSection_InvestigationAdviceACT: CLEARLABTESTLIST  ");
		EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
		ControllerUTIL.setSysdate(request);
		request.getSession().removeAttribute(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
		
		Status status = new Status();
		status.add(Status.TRANSINPROCESS, "", "");
		WebUTIL.setStatus(request, status);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)form;
		EHRSection_InvestigationAdviceUTL.deleteRow(fb, request);
    	return mapping.findForward("NEW"); 
	}
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request,response);
		 EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)form;
		 EHRSection_InvestigationAdviceUTL.saveRequisitionDetails(fb, request);
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
		 EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)form;
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
			validateToken(objRequest_p,objResponse_p);
			EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)objForm_p;
			
			StringBuffer strBuff = EHRSection_InvestigationAdviceUTL.deleteLabTestCodeArray(fb, objRequest_p);
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
			validateToken(objRequest_p,objResponse_p);
			EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)objForm_p;
			
			StringBuffer strBuff = EHRSection_InvestigationAdviceUTL.modifyLabTestCodeArray(fb, objRequest_p);
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
			validateToken(objRequest_p,objResponse_p);
			EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)objForm_p;
			
			StringBuffer strBuff = EHRSection_InvestigationAdviceUTL.modifyPriority(fb, objRequest_p);
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
			EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)objForm_p;
			
			StringBuffer strBuff = EHRSection_InvestigationAdviceUTL.pouplateTestCombo(fb, objRequest_p);
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
			EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)objForm_p;
			
			StringBuffer strBuff = EHRSection_InvestigationAdviceUTL.pouplateTestGroup(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	 
		public ActionForward AJX_PRV_TEST_DTL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)objForm_p;
			
			StringBuffer strBuff = EHRSection_InvestigationAdviceUTL.pouplatePrvTestDtl(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	 
		
		
		public ActionForward APTBASEDTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			//System.out.println("EHRSection_InvestigationAdviceACT: APTBASEDTEST  ");
			EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
			fb.setAptStatus("0");
			ControllerUTIL.setSysdate(request);
			EHRSection_InvestigationAdviceUTL.getAptBasedTest(fb,request);
			return mapping.findForward("NEW");
		}
		
		
		
		public ActionForward APTDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			//System.out.println("EHRSection_InvestigationAdviceACT: APTDETAIL  ");
			EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
			ControllerUTIL.setSysdate(request);
			EHRSection_InvestigationAdviceUTL.setPatientRegistrationEssentials(fb,request);
			EHRSection_InvestigationAdviceUTL.searchLaboratoryWiseTest(fb,request); 
			EHRSection_InvestigationAdviceUTL.searchBookMark(fb,request);
			return mapping.findForward("NEW");
		}
		
		
		 //SEARCHTESTCODEWISE
			public ActionForward SEARCHTESTCODEWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				//System.out.println("EHRSection_InvestigationAdviceACT: SEARCHTESTCODEWISE  ");
				EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
				ControllerUTIL.setSysdate(request);
				EHRSection_InvestigationAdviceUTL.getTestsCodeWiseDtl(fb,request);
				
				return mapping.findForward("NEW");
			}
			
		  //TESTCODE
			
			public ActionForward TESTCODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				//System.out.println("EHRSection_InvestigationAdviceACT: TESTCODE  ");
				EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
				ControllerUTIL.setSysdate(request);
				//EHRSection_InvestigationAdviceUTL.getTestsCodeWiseDtl(fb,request);
				
				return mapping.findForward("NEW");
			}  
			
			
			//DELETEREQDTL
			
			public ActionForward DELETEREQDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
				//System.out.println("EHRSection_InvestigationAdviceACT: DELETEREQDTL  ");
				validateToken(request,response);
				EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
				ControllerUTIL.setSysdate(request);
				EHRSection_InvestigationAdviceUTL.deleteReqDtl(fb,request);
				
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
				EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) objForm_p;
				
				StringBuffer strBuff = EHRSection_InvestigationAdviceUTL.checkRequisitionPending(fb, objRequest_p);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			
			
			
			
			public ActionForward AJX_MODIFY_LABTESTCODEARRAY_APTNO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)objForm_p;
				
				//StringBuffer strBuff = EHRSection_InvestigationAdviceUTL.modifyLabTestCodeArrayForAppoitmentNo(fb, objRequest_p);
				objResponse_p.setHeader("Cache-Control", "no-cache");
			//	objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			
			

	 
	
	
	
	
	
}