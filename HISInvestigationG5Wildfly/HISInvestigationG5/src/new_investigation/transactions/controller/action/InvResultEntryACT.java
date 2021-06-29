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
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.fb.invReportAddendumFB;
import new_investigation.transactions.controller.utl.InvResultEntryUTIL;
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

import hisglobal.backutil.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;

public class InvResultEntryACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session=WebUTIL.getSession(request);
		WebUTIL.refreshTransState(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
		generateToken(request);
		InvResultEntryFB fb=(InvResultEntryFB)form;
		HttpSession session=WebUTIL.getSession(request);
		ResultEntryVO vo=null;
		fb.setIsraisingsave("0");
		//Resetting selected lab test code array
				fb.setLabTestCodeArray("");
				
				if(fb.getShowStatus()!=null){
					if(fb.getShowStatus().equals("1"))
					{
						fb.setShowStatus("1");
					}else
						fb.setShowStatus("0");
				}
				else
					fb.setShowStatus("0");
				
				
				//added by chandan
				if(fb.getIsSampleAreaSelected()!=null){
					if(fb.getIsSampleAreaSelected().equals("1"))
					{
						fb.setIsSampleAreaSelected("1");
					}else
						fb.setIsSampleAreaSelected("0");
				}
				else
					fb.setIsSampleAreaSelected("0");

				
				String isFilter="";	
				if(session.getAttribute("isFilter")!=null)
				{
					isFilter =	(String) session.getAttribute("isFilter");
					
					vo=(ResultEntryVO) session.getAttribute(InvestigationConfig.FILTER_LIST);	
					//vo=lstPatVO.get(0);
					session.setAttribute("isFilterList", vo);
					session.setAttribute("isFilter", "1");
					//HelperMethods.populate(fb, vo);
				}else
				{
					isFilter="0";	
				//	WebUTIL.refreshTransState(request);
					  if(!fb.getIsSampleAreaSelected().equals("1"))
						{
							WebUTIL.refreshTransState(request);
							fb.setLabTestCodeArray("");
						
							
							InvResultEntryUTIL.getSampleCollectionArea(fb,request);
							//OnlinePatientAcceptanceUTL.getEssential(fb,request);
						}
						else
						{
							
							//SampleCollectionUTL.getPatList(fb,request);
							//WebUTIL.refreshTransState(request);
							String sampleAreaName=fb.getSampleAreaName();
							//updated by krishnan nema on 27/09/2018
							//String sampleAreaCode=fb.getSampleAreaCode();
							String sampleAreaCode=fb.getSampleAreaCode().split("#")[0];
							fb.setSampleAreaCode(sampleAreaCode);
							
							InvResultEntryUTIL.getEssential(fb,request);
							//InvResultEntryUTIL.getEssential(fb,request);
						     fb.setIsSampleAreaSelected("1");
						     fb.setShowStatus("0");
						     fb.setSampleAreaName(sampleAreaName);
						     fb.setSampleAreaCode(sampleAreaCode);
						     session.removeAttribute(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO);
						}
				
				}
	  
					
		        fb.setSearchBy("1");	
		 		 
		
		
		//session.removeAttribute(InvestigationConfig.COMPONENT_WISE_INDICATION_MAP);
				
		      
				
		
		
		//InvResultEntryUTIL.getEssential(fb,request);
		
		if(fb.getNewEntry()!=null){
			if(fb.getNewEntry().equals("2"))
			{
				fb.setNewEntry("2");
			}else
				fb.setNewEntry("1");
		}
		else
			fb.setNewEntry("1");
		
	
		fb.setLabCode("%");
		
		
		if(isFilter.equals("1"))
			return this.GETDETAILSSESSION(mapping, form, request, response);
			else
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETDETAILSSESSION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: GETDETAILS  ");
		HttpSession session=WebUTIL.getSession(request);
		InvResultEntryFB fb = (InvResultEntryFB) form;
		fb.setShowStatus("0");
		ResultEntryVO vo=null;
		List labcombo=new ArrayList();
		//WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
	      	 
		if(session.getAttribute("isFilter")!=null)
		{
			vo=(ResultEntryVO) session.getAttribute("isFilterList");
			HelperMethods.populate(fb, vo);
		}
		
		
		if(fb.getIsSampleAreaSelected()!=null){
			if(fb.getIsSampleAreaSelected().equals("1"))
			{
				fb.setIsSampleAreaSelected("1");
			}else
				fb.setIsSampleAreaSelected("0");
		}
		else
			fb.setIsSampleAreaSelected("0");
		
		labcombo=(List) session.getAttribute(InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY);
		//WebUTIL.refreshTransState(request);
		
		
		/*if(!fb.getIsSampleAreaSelected().equals("0"))
		{
			WebUTIL.refreshTransState(request);
			fb.setLabTestCodeArray("");
			InvResultEntryUTIL.getSampleCollectionArea(fb,request);
			//OnlinePatientAcceptanceUTL.getEssential(fb,request);
		}
		else
		{
			*///SampleCollectionUTL.getPatList(fb,request);
			//
			String sampleAreaName=fb.getSampleAreaName();
			String sampleAreaCode=fb.getSampleAreaCode();
			WebUTIL.refreshTransState(request);
			InvResultEntryUTIL.setPatientEssentials(fb,request);
			//InvResultEntryUTIL.getEssential(fb,request);
			//InvResultEntryUTIL.getEssential(fb,request);
		     fb.setIsSampleAreaSelected("1");
		     fb.setShowStatus("0");
		     fb.setSampleAreaName(sampleAreaName);
		     fb.setSampleAreaCode(sampleAreaCode);
		     
		/*}*/
		
		
		
		//InvResultEntryUTIL.setPatientEssentials(fb,request);
		session.setAttribute(InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY, labcombo);
		session.removeAttribute(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO);
		
		
		//session.setAttribute("isFilter", "1");
		 
		return mapping.findForward("NEW");
	}
	
	
	
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
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
	
	 
	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("InvResultEntryACT: SHOWPATDETAILS  ");
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
	
	
	
	public ActionForward SEARCHLABWISETEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: SEARCHLABWISETEST  ");
		InvResultEntryFB fb = (InvResultEntryFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		//InvResultEntryUTIL.searchLaboratoryWiseTest(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	 
	 
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvResultEntryFB fb=(InvResultEntryFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	 
	 public ActionForward SAVETODRAFT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
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
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
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
	 
	 /**
		 * SHOWCANNEDDETAILS     
	 	*  
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
		public ActionForward SHOWCANNEDDETAILS(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			InvResultEntryFB fb=(InvResultEntryFB)objForm_p;
			
		 
			
			
			StringBuffer strBuff = InvResultEntryUTIL.showCannedDetails(fb,objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	  
	
 //SHOWCANNEDDETAILS
		
		
		/*public ActionForward SHOWCANNEDDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			//isValue=1;
			System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ");
			InvResultEntryFB fb = (InvResultEntryFB) form;
			ControllerUTIL.setSysdate(request);
			fb.setShowStatus("1");
			InvResultEntryUTIL.showCannedDetails(fb,request);
			 
			return mapping.findForward("NEW");
		}
*/
		//GETTYPEWISEDETAIL
		
		public ActionForward GETTYPEWISEDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvResultEntryACT: GETTYPEWISEDETAIL  ");
	        
			InvResultEntryFB fb = (InvResultEntryFB) form;
			fb.setShowStatus("0");
	          
			ControllerUTIL.setSysdate(request);
		      	 
			InvResultEntryUTIL.getEntryTypeDetails(fb,request);
			
			 
			return mapping.findForward("NEW");
		}
		
		
		 public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		 {
			 generateToken(request);
			 InvResultEntryFB fb=(InvResultEntryFB)form;
			 InvResultEntryUTIL.modifyResultEntryPatientDetails(fb, request);
			 return this.NEW(mapping, form, request, response);
		 } 
		 
		 public ActionForward MODIFYDRAFT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		 {
			 generateToken(request);
			 InvResultEntryFB fb=(InvResultEntryFB)form;
			 InvResultEntryUTIL.modifyResultEntryPatientDetails(fb, request);
			 return this.NEW(mapping, form, request, response);
		 } 
		 
		 /**
			 * SHOWCANNEDDETAILS     
		 	*  
		 	* @param objMapping_p
		 	* @param objForm_p
		 	* @param objRequest_p
		 	* @param objResponse_p
		 	* @return
		 	* @throws Exception,HisException,SQLException
		 	*/
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
				InvResultEntryFB fb = (InvResultEntryFB) form;
				ControllerUTIL.setSysdate(request);
				//fb.setShowStatus("1");
				String selectedCheckBoxValue=fb.getSelectedCheckbox();
				String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
				String crNo=arrSelectedRequisitions[0].split("\\$")[0];
				String reqNO=arrSelectedRequisitions[0].split("\\$")[1];
				String reqDNo=arrSelectedRequisitions[0].split("\\$")[2];
				 String grpCode=arrSelectedRequisitions[0].split("\\$")[3];
				 String testCode=arrSelectedRequisitions[0].split("\\$")[4];
				 String labCode=arrSelectedRequisitions[0].split("\\$")[5];
				
				 // save data for addendum report 
				 InvResultEntryUTIL.saveAddendumDetails(fb, request);
				 
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

				/* if(mpBilled!=null)
                 {
					 List<invAntibioticProcessVO> hyprlistdata1=new ArrayList<invAntibioticProcessVO>();

         			boolean flag=false;
                 Iterator itr1=mpBilled.keySet().iterator();
              while(itr1.hasNext())
	 	 	{
            	  String organisgm1=(String)itr1.next();
  	            
		 			 
		 			List<antibioticprocessVO> lstVOSample=mpBilled.get(organisgm1);
		 			
		 			int rowSpanSize=lstVOSample.size();
		 			
		 			for(int k=0;k<lstVOSample.size();k++)
		 			{
		 				antibioticprocessVO voo=lstVOSample.get(k);
		 				
		 				 if(voo.getRequisitionDNo().equals(fb.getRequisitionDNo()[0]) )
						  {
		 					 if(fb.getIscallfromonloaddynamic()!=null && !fb.getIscallfromonloaddynamic().equals("1"))
		 					 {
		 					
		 						 
		 					mpBilled.remove(organisgm1);
		 					session.setAttribute(InvestigationConfig.list_in_sessionhyperlinkdata,mpBilled);
		 					 
		 					 }
						 //flag=true;
						  }
		 			}
		 			
	 		}
                 }*/
				 
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
