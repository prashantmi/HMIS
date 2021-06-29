/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: JATIN KUMAR
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : RESULT ENTRY ACTION
 ## Purpose						        : 
 ## Date of Creation					: 06/09/2016
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/




package new_investigation.transactions.controller.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.TestWiseConsumableFB;
import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.controller.utl.InvResultEntryUTIL;
import new_investigation.transactions.controller.utl.TestWiseConsumableUTIL;

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

public class TestWiseConsumableACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		TestWiseConsumableFB fb=(TestWiseConsumableFB)form;
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
		TestWiseConsumableUTIL.getEssential(fb,request);
		
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
		return mapping.findForward("NEW");
	}
	
 
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		TestWiseConsumableFB fb=(TestWiseConsumableFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	

		
		public ActionForward GETTYPEWISEDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvResultEntryACT: GETTYPEWISEDETAIL  ");
	        
			TestWiseConsumableFB fb = (TestWiseConsumableFB) form;
			fb.setShowStatus("0");
	          
			ControllerUTIL.setSysdate(request);
		      	 
			TestWiseConsumableUTIL.getEntryTypeDetails(fb,request);
			
			 
			return mapping.findForward("NEW");
		}
		
		public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvResultEntryACT: GETDETAILS  ");
	        
			TestWiseConsumableFB fb = (TestWiseConsumableFB) form;
			fb.setShowStatus("0");
	          
			ControllerUTIL.setSysdate(request);
		      	 
			TestWiseConsumableUTIL.setPatientEssentials(fb,request);
			 
			return mapping.findForward("NEW");
		}	
		
		
		public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvResultEntryACT: SHOWPATDETAILS  ");
	        
			TestWiseConsumableFB fb = (TestWiseConsumableFB) form;
			
	          
			ControllerUTIL.setSysdate(request);
		      	 
			TestWiseConsumableUTIL.getConsumableList(fb,request);
			TestWiseConsumableUTIL.getPatientDetails(fb, request);
			fb.setShowStatus("1");
			return mapping.findForward("NEW");
		}	
		public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
			System.out.println("InvResultEntryACT: SHOWPATDETAILS  ");
			 validateToken(request, response);
			TestWiseConsumableFB fb = (TestWiseConsumableFB) form;
			
	          
			ControllerUTIL.setSysdate(request);
		      	 
			TestWiseConsumableUTIL.saveConsumableListValues(fb, request);
			TestWiseConsumableUTIL.reFetchConsumableList(fb,request);
			fb.setShowStatus("1");
			return mapping.findForward("NEW");
		}	
		
}
