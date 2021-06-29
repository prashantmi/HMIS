/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Puneet
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Machine Result  Enquiry ACTION
 ## Purpose						        : 
 ## Date of Creation					: 12/10/2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.transactions.controller.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.machineEnquiryFB;
import new_investigation.transactions.controller.utl.machineEnquiryUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import hisglobal.backutil.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

public class machineEnquiryACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		machineEnquiryFB fb=(machineEnquiryFB)form;
		HttpSession session=WebUTIL.getSession(request);
		fb.setLabCode("-1");
		
				fb.setResultArray("");
				
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
		machineEnquiryUTIL.getEssential(fb,request);
	
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETSAMPLE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: GETMACHINE  ");
        machineEnquiryFB fb = (machineEnquiryFB) form;
    	HttpSession session= request.getSession();
		fb.setShowStatus("0");
		session.removeAttribute(InvestigationConfig.SAMPLE_NO_MACHINE_ENQUIRY);
        ControllerUTIL.setSysdate(request);
	    machineEnquiryUTIL.getMachineBasedSampleNo(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	
	
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: GETDETAILS  ");
        machineEnquiryFB fb = (machineEnquiryFB) form;
		fb.setShowStatus("0");
        ControllerUTIL.setSysdate(request);
	    machineEnquiryUTIL.setPatientEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	

	 
	 
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		machineEnquiryFB fb=(machineEnquiryFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	



		 
}
