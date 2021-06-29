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

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.fb.invReportAddendumFB;
import new_investigation.transactions.controller.utl.InvestigationRaisingDtlUTL;
import new_investigation.transactions.controller.utl.SampleCollectionUTL;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import new_investigation.SMS.*; 
public class InvestigationRaisingDtlACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
		//Resetting selected lab test code array
		fb.setSelectlabid(null);
		fb.setLabwisetestteriff("0");
		fb.setLabTestCodeArray("");
		fb.setPatAdmNo("-1");
		fb.setLabTestCodeArray("");
		fb.reset(mapping, request);
		session.removeAttribute("IsAddendumRaisingPOPUP");
		session.removeAttribute("IsAddendum");
		session.removeAttribute("IsAddendumENTRY");
		
		//session.removeAttribute(InvestigationConfig.COMPONENT_WISE_INDICATION_MAP);
		fb.setIssearchtestnamewise("0");
		WebUTIL.refreshTransState(request);
		//InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request);
	//InvestigationReqDtlUTL.getInvReqDtlEssentials(fb, request); APTBASEDTEST
		 
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	//	System.out.println("InvestigationRaisingDtlACT: GETPATDTL  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		//System.out.println("InvestigationRaisingDtlACT: GETPATDTL =======1 "+fb.getPatAdmNo());
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(InvestigationConfig.LIST_PID_PAT);
		session.removeAttribute("pidsetforgrpcase");
		session.removeAttribute("labbasedapppointment");
	
		String casualitydesk="0";
		if(fb.getCasualitydesk()!=null && fb.getCasualitydesk().equals(""))
			casualitydesk="0";
		else
			casualitydesk="1";	
		
      fb.setLabbasedapppointment(null);
		fb.setAptStatus("10");
		ControllerUTIL.setSysdate(request);
		
		
		
		InvestigationRaisingDtlUTL.setPatientRegistrationEssentials(fb,request);
		session.removeAttribute("pidsetforgrpcase");
	//	session.removeAttribute(InvestigationConfig.LIST_PID_PAT);
		String callingdesk="";
		if(fb.getCallingdesk()!=null)
		{
			callingdesk=fb.getCallingdesk();
			
		}
		
		//tests advised from desk  
		//InvestigationRaisingDtlUTL.getAppointmentDetailsOnClickGO(fb,request);
		
		
		if(fb.getPatStatusCode()!=null&&fb.getPatStatusCode().equals(InvestigationConfig.PATIENT_STATUS_IPD)&&fb.getAccountNo()!=null&& !"0".equals(fb.getAccountNo()))
		{
		InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request);
		}
		
	   
		if(fb.getPatStatusCode()!=null&&Integer.parseInt(fb.getPatStatusCode())!=Integer.parseInt(InvestigationConfig.PATIENT_STATUS_IPD))
		{
		InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request);	
		}
		
		
		 InvestigationRaisingDtlUTL.setchargestestngroup(fb,request);
			
		 
		//InvestigationRaisingDtlUTL.getcharge(fb,request);	

		
		
		if(session.getAttribute("IsAddendumENTRY")!=null)
		{
			ReqDelete(fb,request);
		}
		fb.setIssearchtestnamewise("0");
			fb.setCallingdesk(callingdesk);
			((InvestigationRaisingDtlFB) request.getAttribute("InvestigationRaisingDtlFB")).setLabTestCodeArray("");
			
		//	System.out.println("InvestigationRaisingDtlACT: GETPATDTL  ==================================="+callingdesk);

			if(fb.getPatAdmNo()==null || !fb.getPatAdmNo().equals("-1"))
			{
				fb.setFlagDesk("1");
			}
			else
			{
				fb.setFlagDesk("0");
			}
			
			fb.setCallingdesk(casualitydesk);
		return mapping.findForward("NEW");
		
	}
	
	
	 
	
	public ActionForward SEARCHLABWISETEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	//	System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		String val=fb.getLabwisetestteriff();
		String selectlabid=fb.getSelectlabid();
		String labname=fb.getSearchLabName();
		String indications = fb.getVisitReason();
	//	System.out.println("====="+val);
		ControllerUTIL.setSysdate(request);
		
		String deskType="";
		if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
		{
			deskType="1";
		}
		
		fb.setCallingdesk(deskType);
		InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request);
	//	System.out.println("====="+val);
		fb.setLabwisetestteriff(val);
		fb.setSelectlabid(selectlabid);
		fb.setIssearchtestnamewise("0");
		fb.setSearchLabName(labname);
		fb.setCurrentPage(1);
		fb.setVisitReason(indications);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SEARCHLABWISETESTNEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	//	System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		String val=fb.getLabwisetestteriff();
		String selectlabid=fb.getSelectlabid();
		
		
	//	System.out.println("====="+val);
		ControllerUTIL.setSysdate(request);
		InvestigationRaisingDtlUTL.searchLaboratoryWiseTestNEW(fb,request);
	//	System.out.println("====="+val);
		fb.setLabwisetestteriff(val);
		fb.setSelectlabid(selectlabid);
		fb.setIssearchtestnamewise("2");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHLABWISETESTGROUPONCLICK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	//	System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		InvestigationRaisingDtlUTL.searchLaboratoryWiseTestGroupOnClick(fb,request);
		fb.setIssearchtestnamewise("0");
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SEARCHTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	//	System.out.println("InvestigationRaisingDtlACT: SEARCHTEST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		InvestigationRaisingDtlUTL.searchBookMark(fb,request);
		fb.setIssearchtestnamewise("0");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHGROUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	//	System.out.println("InvestigationRaisingDtlACT: SEARCHGROUP  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		InvestigationRaisingDtlUTL.getTestsBasedOnGroups(fb,request);
		fb.setIssearchtestnamewise("0");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHBOOKMARK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	//	System.out.println("InvestigationRaisingDtlACT: SEARCHBOOKMARK  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		InvestigationRaisingDtlUTL.searchBookMark(fb,request);
		fb.setIssearchtestnamewise("0");
		return mapping.findForward("NEW");
	}
	
	public ActionForward CLEARLABTESTLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	//	System.out.println("InvestigationRaisingDtlACT: CLEARLABTESTLIST  ");
		InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
		ControllerUTIL.setSysdate(request);
		request.getSession().removeAttribute(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
		fb.setIssearchtestnamewise("0");
		Status status = new Status();
		status.add(Status.TRANSINPROCESS, "", "");
		WebUTIL.setStatus(request, status);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
		Status  objStatus=new Status();
		fb.setIssearchtestnamewise("0");
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
		InvestigationRaisingDtlUTL.deleteRow(fb, request);
		fb.setIssearchtestnamewise("0");
    	return mapping.findForward("NEW"); 
	}
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request, response);
		 HttpSession session=WebUTIL.getSession(request);
		// System.out.println("act:SAVE");
		 InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
		// System.out.println("episode code:"+fb.getSelectedEpisodeRadio());
		 InvestigationRaisingDtlUTL.saveRequisitionDetails(fb, request);
			session.removeAttribute(InvestigationConfig.LIST_PID_PAT);
			

			String casualitydesk="0";
			if(fb.getCasualitydesk()!=null && fb.getCasualitydesk().equals(""))
				casualitydesk="0";
			else
				casualitydesk="1";	
			
		 fb.setFinalMandValues("");
		 fb.setNewlabtestcodearray("");
		 fb.setIssearchtestnamewise("0");
	//	 System.out.println("act:Save end.");
			session.setAttribute("IsAddendumRaisingPOPUP","");
			session.removeAttribute("pidsetforgrpcase");
			fb.setPidd("");
			fb.setPiddata("");
			fb.setPiddmodalopen("");
			//session.removeAttribute(InvestigationConfig.LIST_PID_PAT);
			fb.setPiddmodalopen(null);
			fb.setSelectlabid(null);
			fb.setLabwisetestteriff("0");
		 if(session.getAttribute("IsAddendum")!=null)
		 {
			// String msgg=(String) session.getAttribute("statusobject");
			 
			// session.setAttribute("addendumStatusFromRaising", " Raised Sucessfully");
			 if(session.getAttribute("IsAddendumENTRY")!=null)
			 {
					fb.setIsentry("1");
				 return this.NEW(mapping, form, request, response);
			 }
			 else
			 return mapping.findForward("GOTOADDENDUM");
		 } 
		 else
		 {
			 fb.setCallingdesk(casualitydesk);
		 return this.NEW(mapping, form, request, response);
		 }
		 
	 } 
	 
	 public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
		    InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
			Status  objStatus=new Status();
			objStatus.add(Status.NEW);
			WebUTIL.setStatus(request,objStatus);
			fb.setSelectlabid(null);
			fb.setLabwisetestteriff("0");
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
		//	System.out.println("InvestigationRaisingDtlACT: APTBASEDTEST  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			fb.setAptStatus("0");
			ControllerUTIL.setSysdate(request);
			InvestigationRaisingDtlUTL.getAptBasedTest(fb,request);
			return mapping.findForward("NEW");
		}
		
		
		
		public ActionForward APTDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//	System.out.println("InvestigationRaisingDtlACT: APTDETAIL  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			ControllerUTIL.setSysdate(request);
			InvestigationRaisingDtlUTL.setPatientRegistrationEssentials(fb,request);
			InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request); 
			InvestigationRaisingDtlUTL.searchBookMark(fb,request);
	
			if(fb.getLabbasedapppointment()!=null && !fb.getLabbasedapppointment().equals(""))
	{
				String data=fb.getLabbasedapppointment();
			fb.setSearchLabName(fb.getLabbasedapppointment().split("#")[4]);
		fb.setLabCodee(fb.getLabbasedapppointment().split("#")[4]);
		fb.setLabwisetestteriff("1");
		fb.setIssearchtestnamewise("0");
		fb.setSelectlabid(fb.getLabbasedapppointment().split("#")[4]);
		fb.setCurrentPage(1);
		fb.setTestLabTestCodeWise("myhisswitchTestLab");

			InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request);
			request.getSession().setAttribute("labbasedapppointment", fb.getLabbasedapppointment());
			fb.setLabbasedapppointment(data);
			fb.setSearchLabName(fb.getLabbasedapppointment().split("#")[4]);
			fb.setLabCodee(fb.getLabbasedapppointment().split("#")[4]);
			fb.setLabwisetestteriff("1");
			fb.setIssearchtestnamewise("0");
			fb.setSelectlabid(fb.getLabbasedapppointment().split("#")[4]);
			fb.setCurrentPage(1);
			fb.setCurrentPage(1);
			fb.setTestLabTestCodeWise("myhisswitchTestLab");
	}
			return mapping.findForward("NEW");
		}
		
		
	 //SEARCHTESTCODEWISE
		public ActionForward SEARCHTESTCODEWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//	System.out.println("InvestigationRaisingDtlACT: SEARCHTESTCODEWISE  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			ControllerUTIL.setSysdate(request);
			InvestigationRaisingDtlUTL.getTestsCodeWiseDtl(fb,request);
			
			return mapping.findForward("NEW");
		}
		
	  //TESTCODE
		
		public ActionForward TESTCODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//	System.out.println("InvestigationRaisingDtlACT: TESTCODE  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			ControllerUTIL.setSysdate(request);
			//InvestigationRaisingDtlUTL.getTestsCodeWiseDtl(fb,request);
			
			return mapping.findForward("NEW");
		}  
		
		
		//CHECKBILLDTL
		
		public ActionForward CHECKBILLDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//	System.out.println("InvestigationRaisingDtlACT: CHECKBILLDTL  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			ControllerUTIL.setSysdate(request);
			
			
			int strBuff = InvestigationRaisingDtlUTL.checkBillDtl(fb,request);;
			response.setHeader("Cache-Control", "no-cache");
			try {
				response.getWriter().print(strBuff);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
			//return mapping.findForward("NEW");
		} 
		
		//DELETEREQDTL
		
		public ActionForward DELETEREQDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//	System.out.println("InvestigationRaisingDtlACT: DELETEREQDTL  ");
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
			
			StringBuffer strBuff = InvestigationRaisingDtlUTL.modifyLabTestCodeArrayForAppoitmentNo(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		//changed by ashu
		
		public ActionForward AJX_USER_TEST_CODE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = InvestigationRaisingDtlUTL.getUserTestCodeVal(fb, objRequest_p);
			//System.out.println("strBuff act"+strBuff);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		public ActionForward AJX_CHECK_AVAILABILITY_TEST_CODE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
			
			StringBuffer strBuff = InvestigationRaisingDtlUTL.getRemarksForUnavailableTest(fb, objRequest_p);
			//System.out.println("strBuff act"+strBuff);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
		
		public ActionForward APTBYDESK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//	System.out.println("InvestigationRaisingDtlACT: APTBYDESK  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			fb.setAptStatus("2");
			ControllerUTIL.setSysdate(request);
			InvestigationRaisingDtlUTL.getAptByDesk(fb,request);
			return mapping.findForward("NEW");
		}
		
		
		public ActionForward GETAPPOINTMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	//		System.out.println("InvestigationRaisingDtlACT: GETAPPOINTMENT  ");
			InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
			fb.setAptStatus("5");
			ControllerUTIL.setSysdate(request);
			InvestigationRaisingDtlUTL.getAppointment(fb,request);
			return mapping.findForward("NEW");
		}
		
		 public ActionForward SAVEAPPOINTMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		 {
			 InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
			 if(fb.getAppointmentRefNo()!=null)
				 if(fb.getAppointmentRefNo().contains("#"))
					 fb.setAppointmentRefNo(fb.getAppointmentRefNo().split("#")[0]);
			 InvestigationRaisingDtlUTL.saveAppointmentDetails(fb, request);
			 return this.NEW(mapping, form, request, response);
		 } 
		
			public ActionForward DELETEGROUPROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)form;
				InvestigationRaisingDtlUTL.deleteGroupRow(fb, request);
		    	return mapping.findForward("NEW"); 
			}
		
			
			
			
			//SEARCH USER GROUP CODE WISE
			public ActionForward SEARCHGROUPCODEWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//		System.out.println("InvestigationRaisingDtlACT: SEARCHGROUPCODEWISE  ");
				InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
				ControllerUTIL.setSysdate(request);
				InvestigationRaisingDtlUTL.getGroupCodeWiseDtl(fb,request);
				
				return mapping.findForward("NEW");
			}
			//added by chandan
			public ActionForward SEARCHTESTKEYWORD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//		System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
				InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
				ControllerUTIL.setSysdate(request);
				InvestigationRaisingDtlUTL.searchLaboratoryWiseTest(fb,request);
				
				return mapping.findForward("NEW");
			}
			

			
			public ActionForward AJX_CHECK_REQFORM_TESTTYPE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
				
				StringBuffer strBuff = InvestigationRaisingDtlUTL.getRequisitionFormMasterData(fb, objRequest_p);
				//System.out.println("strBuff act"+strBuff);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			
			
			public static void ReqDelete(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p)
			{
				StringBuffer strBuff = InvestigationRaisingDtlUTL.pouplatePrvTestDtll(fb, objRequest_p);
				
			}
			
			//DELETEREQDTLRESULTENTRY
			
			public ActionForward DELETEREQDTLRESULTENTRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			//	System.out.println("InvestigationRaisingDtlACT: DELETEREQDTLRESULTENTRY  ");
				InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
				ControllerUTIL.setSysdate(request);
				String datat=InvestigationRaisingDtlUTL.deleteReqDtll(fb,request);
			//	ReqDelete(fb, request);
				response.setHeader("Cache-Control", "no-cache");
				try {
					response.getWriter().print(datat);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			} 

			
			
			public ActionForward SEARCHTESTKEYWORDNEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//		System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
				InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
				ControllerUTIL.setSysdate(request);
				InvestigationRaisingDtlUTL.searchLaboratoryWiseTestNEW(fb,request);
			    fb.setIssearchtestnamewise("1");
				fb.setTestLabTestCodeWise("myhisswitchTest");
				return mapping.findForward("NEW");
			}
			
			public ActionForward SEARCHTESTKEYWORDNEWLABWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//		System.out.println("InvestigationRaisingDtlACT: SEARCHLABWISETEST  ");
				InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB) form;
				
				String testlabvalues=fb.getSearchTestNamelabwise();
				String test="";
				String lab=testlabvalues.split("#")[1];
				
				String val=fb.getLabwisetestteriff();
				String selectlabid=fb.getSelectlabid();
				ControllerUTIL.setSysdate(request);
				InvestigationRaisingDtlUTL.searchLaboratoryWiseTestNEW(fb,request);
			    
			    fb.setLabwisetestteriff(val);
				fb.setSelectlabid(selectlabid);
				fb.setIssearchtestnamewise("2");
				fb.setSearchLabName(lab);
				fb.setCurrentPageNo("1");
				fb.setCurrentPage(1);
			//	fb.setTestLabTestCodeWise("myhisswitchTest");
				return mapping.findForward("NEW");
			}
			
			public ActionForward ISSUFFICIENTBALANCE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
				HttpSession session= objRequest_p.getSession();
				
				// List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
				 //Inv_EpisodeVO vo=lstPatEpisodeVO.get(0);
				 
				// List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
					
				// Inv_EpisodeVO vo=lstPatEpisodeVO.get(0);
				Inv_RequisitionRaisingPatientVO patVO=null;

				patVO= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
				
				int requisitionTypeForBilling=0;
				String patcatcode=patVO.getPatCategoryCode();
				String wardcode="";
                   
				   if(patVO.getPatStatusCode().equals("2"))
					{
					   wardcode=patVO.getPatwardcode();
						requisitionTypeForBilling=2;
					}
					else 
					{
						//visit type code 1-opd, 2,3-emergency, 4 special
						if(patVO.getPatvisittypecode()==null)
							requisitionTypeForBilling=1;
						else{
							
						
						if(patVO.getPatvisittypecode().equals("1"))
							requisitionTypeForBilling=1;
						if(patVO.getPatvisittypecode().equals("4"))
							requisitionTypeForBilling=4;
						if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
							requisitionTypeForBilling=3;
						}
						
					}
				
				
				
				String strBuff = InvestigationRaisingDtlUTL.issufficientbalance(fb, objRequest_p,requisitionTypeForBilling,patcatcode,wardcode);
				//System.out.println("strBuff act"+strBuff);
//				strBuff="1";
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			
			
			public ActionForward ISDUPLICATETESTRAISEDTODAY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
				HttpSession session= objRequest_p.getSession();
				
				// List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
				 //Inv_EpisodeVO vo=lstPatEpisodeVO.get(0);
				 
				// List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
					
				// Inv_EpisodeVO vo=lstPatEpisodeVO.get(0);
				Inv_RequisitionRaisingPatientVO patVO=null;

				patVO= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
				
				
				
				String strBuff = InvestigationRaisingDtlUTL.isduplicatetestraisedtoday(fb, objRequest_p,0,null,null);
				
				if(!strBuff.equals("0"))
					strBuff="1";
				else
					strBuff="0";
				//System.out.println("strBuff act"+strBuff);
//				strBuff="1";
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			

			
			public ActionForward ispidesixt(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
				HttpSession session= objRequest_p.getSession();
				
				// List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
				 //Inv_EpisodeVO vo=lstPatEpisodeVO.get(0);
				
				// List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
				
				// Inv_EpisodeVO vo=lstPatEpisodeVO.get(0);
				Inv_RequisitionRaisingPatientVO patVO=null;

				patVO= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
				
				
				
				String strBuff = InvestigationRaisingDtlUTL.ispidexist(fb, objRequest_p);
				
				if(!strBuff.equals("0"))
					strBuff="1";
				else
					strBuff="0";
				//System.out.println("strBuff act"+strBuff);
//				strBuff="1";
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			
			public ActionForward AJX_IS_LAB_MANDTORY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
				HttpSession session= objRequest_p.getSession();
				
				// List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
				 //Inv_EpisodeVO vo=lstPatEpisodeVO.get(0);
				 
				// List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
					
				// Inv_EpisodeVO vo=lstPatEpisodeVO.get(0);
				Inv_RequisitionRaisingPatientVO patVO=null;

				patVO= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
				
				
				
				String strBuff = InvestigationRaisingDtlUTL.AJX_IS_LAB_MANDTORY(fb, objRequest_p,0,null,null);
				
				
				
				
				//System.out.println("strBuff act"+strBuff);
//				strBuff="1";
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff);
				
				return null;
			}

			
			
			public ActionForward AJX_MODIFY_SITE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
				
				StringBuffer strBuff = InvestigationRaisingDtlUTL.modifysite(fb, objRequest_p);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			
			public ActionForward AJX_SETREQTYPE_FOR_APPOINTMENT(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvestigationRaisingDtlFB fb=(InvestigationRaisingDtlFB)objForm_p;
				
				String strBuff = InvestigationRaisingDtlUTL.setvisittypeforappointment(fb, objRequest_p);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff);
				return null;
			}

			
			  public ActionForward AJX_PRV_TEST_DTL_ALL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws IOException {
				    InvestigationRaisingDtlFB fb = (InvestigationRaisingDtlFB)objForm_p;
				    
				    StringBuffer strBuff = InvestigationRaisingDtlUTL.pouplatePrvTestDtl(fb, objRequest_p);
				    objResponse_p.setHeader("Cache-Control", "no-cache");
				    objResponse_p.getWriter().print(strBuff.toString());
				    return null;
				  }
			  
}
