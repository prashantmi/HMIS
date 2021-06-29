/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PATHAN JOHN BASHA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :Online Patient Acceptance

 ## Purpose						        : 
 ## Date of Creation					: 23-02-2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/





package new_investigation.transactions.controller.action;

import hisglobal.backutil.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB;
import new_investigation.transactions.controller.utl.OnlinePatientAcceptanceUTL;
import new_investigation.transactions.controller.utl.SampleCollectionUTL;
import new_investigation.vo.OnlinePatientAcceptanceVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
 
public class OnlinePatientAcceptanceACT extends CSRFGardTokenAction
{
	
	//public int isValue=0;
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
	return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		OnlinePatientAcceptanceFB fb=(OnlinePatientAcceptanceFB)form;
		
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(InvestigationConfig.LIST_EPISODE_VO);
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

		OnlinePatientAcceptanceVO vo=null;
		
	
				

			if(!fb.getIsSampleAreaSelected().equals("1"))
			{
				WebUTIL.refreshTransState(request);
				fb.setLabTestCodeArray("");
				
				OnlinePatientAcceptanceUTL.getSampleCollectionArea(fb,request);
				//OnlinePatientAcceptanceUTL.getEssential(fb,request);
			}
			else
			{
				//SampleCollectionUTL.getPatList(fb,request);
				//WebUTIL.refreshTransState(request);
				String sampleAreaName=fb.getSampleAreaName();
				//updated by krishnan nema on 08/10/2018
				String sampleAreaCode=fb.getSampleAreaCode();
				
				
				//String sampleAreaCode=fb.getSampleAreaCode().split("#")[0];
				OnlinePatientAcceptanceUTL.getEssential(fb,request);
			     fb.setIsSampleAreaSelected("1");
			     fb.setShowStatus("0");
			     fb.setSampleAreaName(sampleAreaName);
			     fb.setSampleAreaCode(sampleAreaCode);
			     
			}
			/* Added by prashantMi */
			 fb.setAcceptedToNotAccepted("1");
		//session.removeAttribute(InvestigationConfig.COMPONENT_WISE_INDICATION_MAP);
		
	 
	/*	WebUTIL.refreshTransState(request);
		OnlinePatientAcceptanceUTL.getEssential(fb,request);
	 */
		return mapping.findForward("NEW");
		
		
	}
	
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("OnlinePatientAcceptanceACT: GETDETAILS  ");
        
		OnlinePatientAcceptanceFB fb = (OnlinePatientAcceptanceFB) form;
		System.out.println("patient_type = "+fb.getPatientType());
          
		ControllerUTIL.setSysdate(request);
		String sampleAreaName=fb.getSampleAreaName();
		String sampleAreaCode=fb.getSampleAreaCode();
		String parcrno=fb.getTempPatCRNo();
		/* Added by prashantMi */
		String acceptedToNotAccepted=fb.getAcceptedToNotAccepted();

		OnlinePatientAcceptanceUTL.setPatientEssentials(fb,request);
		 
		fb.setShowStatus("0");
		fb.setIsSampleAreaSelected("1");
		fb.setSampleAreaName(sampleAreaName);
		fb.setSampleAreaCode(sampleAreaCode);
		fb.setTempPatCRNo(parcrno);
		fb.setAcceptedToNotAccepted(acceptedToNotAccepted);

		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ");
		OnlinePatientAcceptanceFB fb = (OnlinePatientAcceptanceFB) form;
		ControllerUTIL.setSysdate(request);
		String sampleAreaCode=fb.getSampleAreaCode();
		String sampleAreaName=fb.getSampleAreaName();
		
		String machineCode=fb.getMachineCode();
		String acceptedToNotAccepted=fb.getAcceptedToNotAccepted();
		
		fb.setShowStatus("1");
		OnlinePatientAcceptanceUTL.getUserList(fb,request);
		OnlinePatientAcceptanceUTL.showPatDetails(fb,request);
		fb.setSampleAreaCode(sampleAreaCode);
		fb.setSampleAreaName(sampleAreaName);
		fb.setSelectedmachineCode(machineCode);
		fb.setAcceptedToNotAccepted(acceptedToNotAccepted);
		//fb.setAccepted("0");
		
		return mapping.findForward("NEW");
	}
	
	
	
	public ActionForward SEARCHLABWISETEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("OnlinePatientAcceptanceACT: SEARCHLABWISETEST  ");
		OnlinePatientAcceptanceFB fb = (OnlinePatientAcceptanceFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		//OnlinePatientAcceptanceUTL.searchLaboratoryWiseTest(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	 
	 
	
	/*
	 * public ActionForward PAGINATION(ActionMapping mapping,ActionForm
	 * form,HttpServletRequest request,HttpServletResponse response) {
	 * OnlinePatientAcceptanceFB fb=(OnlinePatientAcceptanceFB)form; Status
	 * objStatus=new Status(); objStatus.add(Status.TRANSINPROCESS);
	 * WebUTIL.setStatus(request,objStatus); return mapping.findForward("NEW"); }
	 */
	
	 
	
	
	 
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request, response);
		OnlinePatientAcceptanceFB fb=(OnlinePatientAcceptanceFB)form;
		HttpSession session=request.getSession();
		String sampleAreaName=fb.getSampleAreaName();
		String sampleAreaCode=fb.getSampleAreaCode();	
		String acceptedToNotAccepted=fb.getAcceptedToNotAccepted();
		OnlinePatientAcceptanceUTL.savePatientDetails(fb, request);
		// fb.setSampleAreaCode("-1");
	    fb.setSampleAreaCode(sampleAreaCode);
	    fb.setSampleAreaName(sampleAreaName);
		fb.setShowStatus("0");
	    fb.setIsSampleAreaSelected("1");
	    fb.setAcceptedToNotAccepted(acceptedToNotAccepted);
	    if(session.getAttribute("isFilterpatacc")!=null)
		return this.GETDETAILSSESSION(mapping, form, request, response);
	    else
			return this.NEW(mapping, form, request, response);

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
		public ActionForward AJX_DUPLICACY_DAILYLABNO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			OnlinePatientAcceptanceFB fb=(OnlinePatientAcceptanceFB)objForm_p;
		//	String sampleAreaCode=fb.getSampleAreaCode();
			StringBuffer strBuff = OnlinePatientAcceptanceUTL.checkDailyLabNoDuplicacy(fb, objRequest_p);
			//fb.setSampleAreaCode(sampleAreaCode);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	  
		/**
		 * AJX_CHECK_AUTO_SAMPLENO_GEN     
	 	* Ajax Function for Checking Duplicacy
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
		public ActionForward AJX_CHECK_AUTO_SAMPLENO_GEN(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
               OnlinePatientAcceptanceFB fb=(OnlinePatientAcceptanceFB)objForm_p;
               String sampleAreaCode=fb.getSampleAreaCode();
			StringBuffer strBuff = OnlinePatientAcceptanceUTL.checkAutoGenFormate(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		

		
		public ActionForward GETDETAILSSESSION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("OnlinePatientAcceptanceACT: GETDETAILS  ");

			
			
			
			
			
			
			OnlinePatientAcceptanceFB fb=(OnlinePatientAcceptanceFB)form;
			
			HttpSession session=WebUTIL.getSession(request);
			
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

			OnlinePatientAcceptanceVO vo=null;
			
	     	 
			if(session.getAttribute("isFilterpatacc")!=null)
			{
				vo=(OnlinePatientAcceptanceVO) session.getAttribute("filter_list_patacc");
				HelperMethods.populate(fb, vo);
			}
			
			
			ControllerUTIL.setSysdate(request);
			String sampleAreaName=fb.getSampleAreaName();
			String sampleAreaCode=fb.getSampleAreaCode();

			OnlinePatientAcceptanceUTL.setPatientEssentials(fb,request);
			 
			fb.setShowStatus("0");
			fb.setIsSampleAreaSelected("1");
			fb.setSampleAreaName(sampleAreaName);
			fb.setSampleAreaCode(sampleAreaCode);
			return mapping.findForward("NEW");
		}
}
