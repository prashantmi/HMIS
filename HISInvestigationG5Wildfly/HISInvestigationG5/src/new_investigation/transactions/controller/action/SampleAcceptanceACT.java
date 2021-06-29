/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PATHAN JOHN BASHA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :Sample Acceptance

 ## Purpose						        : 
 ## Date of Creation					: 12-03-2015
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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.SampleAcceptanceFB;
import new_investigation.transactions.controller.utl.SampleAcceptanceUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
 
public class SampleAcceptanceACT extends CSRFGardTokenAction
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
		SampleAcceptanceFB fb=(SampleAcceptanceFB)form;
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
 
		WebUTIL.refreshTransState(request);
		SampleAcceptanceUTL.getSampleAcceptanceEssential(fb,request);
		
		//SampleAcceptanceUTL.getMachine(fb,request);
		
		if(fb.getAcceptance()!=null)
			if(fb.getAcceptance().equals("2"))
				fb.setAcceptance("2");
			else
				fb.setAcceptance("1");
		else
			fb.setAcceptance("1");
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("SampleAcceptanceACT: GETDETAILS  ");
        
		SampleAcceptanceFB fb = (SampleAcceptanceFB) form;
		fb.setShowStatus("0");
          
		ControllerUTIL.setSysdate(request);
	      	 
		SampleAcceptanceUTL.getSampleAcceptanceEssentialsOnClickGo(fb,request);
		 
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("SampleAcceptanceACT: SHOWPATDETAILS  ");
		SampleAcceptanceFB fb = (SampleAcceptanceFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		SampleAcceptanceUTL.showPatDetailsOnRadioButton(fb,request);
		return mapping.findForward("NEW");
	}
	
	 
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SampleAcceptanceFB fb=(SampleAcceptanceFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
 
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request, response);
		 SampleAcceptanceFB fb=(SampleAcceptanceFB)form;
		 SampleAcceptanceUTL.saveSampleAcceptanceDetail(fb, request);
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
			 SampleAcceptanceFB fb=(		 SampleAcceptanceFB)objForm_p;
			
			StringBuffer strBuff = 		 SampleAcceptanceUTL.checkDailyLabNoDuplicacy(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
	  
		 public ActionForward REJECT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		 {
			 SampleAcceptanceFB fb=(SampleAcceptanceFB)form;
			 SampleAcceptanceUTL.rejectSampleAcceptanceDetail(fb, request);
		    return this.NEW(mapping, form, request, response);
		 }
		 
		 public ActionForward AJX_CHECK_AUTO_SAMPLENO_GEN(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
			 SampleAcceptanceFB fb=(SampleAcceptanceFB)objForm_p;
				
			   String strBuff = SampleAcceptanceUTL.checkAutoGenFormate(fb, objRequest_p);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff.toString());
				return null;
			} 
		 
	  
		 public ActionForward GETDETAILSMACHINE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
				System.out.println("SampleAcceptanceACT: GETDETAILSMACHINE  ");
				Map mp=new HashMap();
				SampleAcceptanceFB fb = (SampleAcceptanceFB) form;
				fb.setShowStatus("0");
		          
				ControllerUTIL.setSysdate(request);
			      	 
				mp.put(InvestigationConfig.MAP_SAMPLE_ACCEPTANCE_VO,null);
				WebUTIL.setMapInSession(mp, request);				
				return mapping.findForward("NEW");
			}
		 
		 //added by krishnan nema on 24/10/2018
		 public ActionForward MODIFYACCEPTED(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		 {
			 SampleAcceptanceFB fb=(SampleAcceptanceFB)form;
			 SampleAcceptanceUTL.modifySampleAcceptanceDetail(fb, request);
			 fb.setShowStatus("0");
			 return this.NEW(mapping, form, request, response);
		 }
		 
}
