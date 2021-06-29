/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Puneet
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Machine Result  ENTRY ACTION
 ## Purpose						        : 
 ## Date of Creation					: 28/09/2015
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
import new_investigation.transactions.controller.fb.machineResultEntryFB;
import new_investigation.transactions.controller.utl.machineResultEntryUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

public class machineResultEntryACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		machineResultEntryFB fb=(machineResultEntryFB)form;
		HttpSession session=WebUTIL.getSession(request);
		fb.setLabCode("-1");
		
		 String lab=fb.getLabCode();
		 String mcode=fb.getMachineCode();
		 String sdate=fb.getSampleCollDate();
		 String rdate=fb.getResultEntryDate();
		 String record=fb.getRecord();
		 String flag=fb.getFlag();
	     
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
		
		
		
		machineResultEntryUTIL.getEssential(fb,request);
	

	     fb.setLabCode(lab);
	     fb.setMachineCode(mcode);
	     fb.setSampleCollDate(sdate);
	     fb.setResultEntryDate(rdate);
	     fb.setRecord(record);
	     if(flag!=null && flag.equals("1"))
	     fb.setFlag("1");
	     
	     
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETMACHINE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: GETMACHINE  ");
        machineResultEntryFB fb = (machineResultEntryFB) form;
    	HttpSession session= request.getSession();
		fb.setShowStatus("0");
		session.removeAttribute(InvestigationConfig.LIST_MACHINE_COMBO);

        ControllerUTIL.setSysdate(request);
	    machineResultEntryUTIL.getLabBasedMachine(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	
	
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: GETDETAILS  ");
        machineResultEntryFB fb = (machineResultEntryFB) form;
		fb.setShowStatus("0");
        ControllerUTIL.setSysdate(request);
	    machineResultEntryUTIL.setPatientEssentials(fb,request);
	    fb.setFlag("");
		return mapping.findForward("NEW");
	}
	

	 
	 
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		machineResultEntryFB fb=(machineResultEntryFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	 
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request, response);
		 machineResultEntryFB fb=(machineResultEntryFB)form;
		 String lab=fb.getLabCode();
		 String mcode=fb.getMachineCode();
		 String sdate=fb.getSampleCollDate();
		 String rdate=fb.getResultEntryDate();
		 String record=fb.getRecord();
		 
	     machineResultEntryUTIL.saveMachineResultEntry(fb, request);
	     
	     fb.setLabCode(lab);
	     fb.setMachineCode(mcode);
	     fb.setSampleCollDate(sdate);
	     fb.setResultEntryDate(rdate);
	     fb.setRecord(record);
	     fb.setFlag("1");
			return mapping.findForward("NEW");

//		 return this.NEW(mapping, form, request, response);
	 } 
	 


		 
}
