/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.investigation.presentation;

import java.sql.SQLException;

import hisglobal.backutil.exception.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import investigationDesk.InvestigationConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ehr.allergies.presentation.EHRSection_AllergiesFB;
import ehr.allergies.presentation.EHRSection_AllergiesUTL;
import ehr.chronicdisease.presentation.EHRSection_ChronicDiseaseFB;
import ehr.chronicdisease.presentation.EHRSection_ChronicDiseaseUTL;

public class EHRSection_InvestigationAdviceACT extends CSRFGardTokenAction
{
	
	
			public ActionForward PATCLINICALDOC_ENCINVESTIGATION_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
			{		
				EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)form;
				//fb.reset(mapping, request);
				EHRSection_InvestigationAdviceUTL.getPatientInvestigationTestDetail(fb, request);
				return mapping.findForward("ENCINVESTIGATION");
				
			}
			
			public ActionForward PATCLINICALDOC_ENCINVESTIGATION_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
			{		
				return mapping.findForward("PREVIEW");
				
			}
	 
			public ActionForward REQUISITIONRAISING(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
			{
				
				EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
				ControllerUTIL.setSysdate(request);
				HttpSession session = WebUTIL.getSession(request);	
				String deskType = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
				fb.setDeskType(deskType);
	
//				WebUTIL.setAttributeInSession(request, DynamicDeskConfig.DYNAMIC_DESK_TYPE, fb.getDeskType());
				EHRSection_InvestigationAdviceUTL.setPatientRegistrationEssentials(fb,request);

		 
				if(fb.getPatStatusCode()!=null&&fb.getPatStatusCode().equals(InvestigationConfig.PATIENT_STATUS_IPD)&&fb.getAccountNo()!=null&& !"0".equals(fb.getAccountNo()))
				{

		 
				EHRSection_InvestigationAdviceUTL.searchLaboratoryWiseTest(fb,request);

				 }
				if(fb.getPatStatusCode()!=null&&Integer.parseInt(fb.getPatStatusCode())!=Integer.parseInt(InvestigationConfig.PATIENT_STATUS_IPD))
				{
				EHRSection_InvestigationAdviceUTL.searchLaboratoryWiseTest(fb,request);	
				}

				return mapping.findForward("REQUISITIONRAISING");
				
			}
			
			
			
			public ActionForward SEARCHLABWISETEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			//	System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
				EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
				ControllerUTIL.setSysdate(request);
				EHRSection_InvestigationAdviceUTL.searchLaboratoryWiseTest(fb,request);
				
				return mapping.findForward("NEW");
			}
			
			
			public ActionForward SEARCHLABWISETESTGROUPONCLICK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				//System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
				EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
				ControllerUTIL.setSysdate(request);
				EHRSection_InvestigationAdviceUTL.searchLaboratoryWiseTestGroupOnClick(fb,request);
				
				return mapping.findForward("NEW");
			}
			
			
			public ActionForward SEARCHTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				//System.out.println("InvestigationRaisingDtlACT: SEARCHTEST  ");
				EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
				ControllerUTIL.setSysdate(request);
				EHRSection_InvestigationAdviceUTL.searchBookMark(fb,request);
				
				return mapping.findForward("NEW");
			}
			
			public ActionForward SEARCHGROUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				//System.out.println("InvestigationRaisingDtlACT: SEARCHGROUP  ");
				EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
				ControllerUTIL.setSysdate(request);
				EHRSection_InvestigationAdviceUTL.getTestsBasedOnGroups(fb,request);
				
				return mapping.findForward("NEW");
			}
			
			public ActionForward SEARCHBOOKMARK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				//System.out.println("InvestigationRaisingDtlACT: SEARCHBOOKMARK  ");
				EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
				ControllerUTIL.setSysdate(request);
				EHRSection_InvestigationAdviceUTL.searchBookMark(fb,request);
				
				
				return mapping.findForward("NEW");
			}
			public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
				EHRSection_InvestigationAdviceUTL.deleteRow(fb, request);
		    	return mapping.findForward("NEW"); 
			}
			 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
			 {
				 EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)form;
				 EHRSection_InvestigationAdviceUTL.saveDetails(request, response , fb);
				 fb.setFinalMandValues("");
				 fb.setLabTestCodeArray("");
				 
				 HttpSession session=request.getSession();
				 session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS); 
				 session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS); 
				 session.removeAttribute(InvestigationConfig.MAP_BOOK_MARK); 
				 //LIST_LAB_WISE_TEST_DTLS
				 return null;
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
					//System.out.println("InvestigationRaisingDtlACT: APTBASEDTEST  ");
					EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
					fb.setAptStatus("0");
					ControllerUTIL.setSysdate(request);
					EHRSection_InvestigationAdviceUTL.getAptBasedTest(fb,request);
					return mapping.findForward("NEW");
				}
				
				
				
				public ActionForward APTDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
					//System.out.println("InvestigationRaisingDtlACT: APTDETAIL  ");
					EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
					ControllerUTIL.setSysdate(request);
					EHRSection_InvestigationAdviceUTL.setPatientRegistrationEssentials(fb,request);
					EHRSection_InvestigationAdviceUTL.searchLaboratoryWiseTest(fb,request); 
					EHRSection_InvestigationAdviceUTL.searchBookMark(fb,request);
					return mapping.findForward("NEW");
				}
				
				
				 //SEARCHTESTCODEWISE
					public ActionForward SEARCHTESTCODEWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
						//System.out.println("InvestigationRaisingDtlACT: SEARCHTESTCODEWISE  ");
						EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
						ControllerUTIL.setSysdate(request);
						EHRSection_InvestigationAdviceUTL.getTestsCodeWiseDtl(fb,request);
						
						return mapping.findForward("NEW");
					}
					
				  //TESTCODE
					
					public ActionForward TESTCODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
						//System.out.println("InvestigationRaisingDtlACT: TESTCODE  ");
						EHRSection_InvestigationAdviceFB fb = (EHRSection_InvestigationAdviceFB) form;
						ControllerUTIL.setSysdate(request);
						//EHRSection_InvestigationAdviceUTL.getTestsCodeWiseDtl(fb,request);
						
						return mapping.findForward("NEW");
					}  
					
					
					//DELETEREQDTL
					
					public ActionForward DELETEREQDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
						//System.out.println("InvestigationRaisingDtlACT: DELETEREQDTL  ");
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
		            
					//Added by Vasu on 02.Nov.2018
					public ActionForward PATCLINICALDOC_ENC_INV_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
					{		
						EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)form;
						//fb.reset(mapping, request);
						EHRSection_InvestigationAdviceUTL.getPatientInvestigationTestDetail(fb, request);
						return mapping.findForward("ENCINVESTIGATION");
						
					}	
					
					//Added by Vasu on 24.May.2019
					public ActionForward PATCLINICALDOC_ENC_INV_ADV_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
					{		
						EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)form;
						EHRSection_InvestigationAdviceUTL.getEssentialData(fb, request,response);
						return mapping.findForward("INVESTIGATIONADVICE");
						
					}	

	               //Added by Vasu on 29.May.2019
					public ActionForward ENC_INV_ADV_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
					{

						EHRSection_InvestigationAdviceFB fb=(EHRSection_InvestigationAdviceFB)form;
						EHRSection_InvestigationAdviceUTL.getEssentialDataToPopulate(fb, request,response);
						return null;
					}
}