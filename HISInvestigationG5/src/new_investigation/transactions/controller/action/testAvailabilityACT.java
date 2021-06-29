/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Puneet
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Test Availability Process
 ## Purpose						        : 
 ## Date of Creation					: 29/03/2016
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.transactions.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.PackingListGenerationFB;
import new_investigation.transactions.controller.fb.testAvailabilityFB;
import new_investigation.transactions.controller.utl.PackingListGenerationUTL;
import new_investigation.transactions.controller.utl.testAvailabilityUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

public class testAvailabilityACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		testAvailabilityFB fb=(testAvailabilityFB)form;
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
		testAvailabilityUTIL.getEssential(fb,request);
	
		fb.setIsAvailable("1");
		fb.setTestStatus("-1");
		fb.setLabCode("-1");
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("TEST AVAILABILITY ACTION : GETTEST  ");
        testAvailabilityFB fb = (testAvailabilityFB) form;
		fb.setShowStatus("0");
        ControllerUTIL.setSysdate(request);
	    testAvailabilityUTIL.setPatientEssentials(fb,request);
	    fb.setIsAvailable("1");
		return mapping.findForward("NEW");
	}
	

	 
	 
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		testAvailabilityFB fb=(testAvailabilityFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	 
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request, response);
			System.out.println("TEST AVAILABILITY ACTION : SAVE  ");

		 testAvailabilityFB fb=(testAvailabilityFB)form;
	     testAvailabilityUTIL.saveMachineResultEntry(fb, request);
		 return this.NEW(mapping, form, request, response);
	 } 
	 

	 public ActionForward VIEWREPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	 {
		 testAvailabilityFB fb=(testAvailabilityFB)form;
		 fb.setShowStatus("0");
	    return  mapping.findForward("VIEWREPORT");
	 } 
		 
}
