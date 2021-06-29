/*
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PUNEET SINGH KHURANA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Film Used
 ## Purpose						        : Film No Entry
 ## Date of Creation					: 25/04/2016
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 




*/

package new_investigation.transactions.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.filmUsedFB;
import new_investigation.transactions.controller.utl.filmUsedUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
 
public class filmUsedACT extends CSRFGardTokenAction
{
	
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
	return this.NEW(mapping,form,request,response);
	}
	
	//get details on load based on all labs and sysdate
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
		request.getSession().removeAttribute("totalaused");
		request.getSession().removeAttribute("wasteused");
		request.getSession().removeAttribute("addtionalused");
		
		generateToken(request);
		filmUsedFB fb=(filmUsedFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
		
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
		
		filmUsedUTL.getEssential(fb,request);
	 
		
		return mapping.findForward("NEW");
	}
	
	
	//getdetails on click go
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("film used: GETDETAILS  ");
        
		filmUsedFB fb = (filmUsedFB) form;
		fb.setShowStatus("0");
          
		ControllerUTIL.setSysdate(request);
	      	 
		filmUsedUTL.setPatientEssentials(fb,request);
		 
		return mapping.findForward("NEW");
	}
	
	//display pat details and film no
	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		request.getSession().removeAttribute("totalaused");
		request.getSession().removeAttribute("wasteused");
		request.getSession().removeAttribute("addtionalused");
		System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ");
		filmUsedFB fb = (filmUsedFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		filmUsedUTL.showPatDetails(fb,request);
		fb.setTestStatus("1");
		return mapping.findForward("NEW");
	}
	
	
	 
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		filmUsedFB fb=(filmUsedFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	//save film values
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request, response);
		 filmUsedFB fb=(filmUsedFB)form;
		 filmUsedUTL.savePatientDetails(fb, request);
		 fb.reset(mapping, request);
		 
			request.getSession().removeAttribute("totalaused");
			request.getSession().removeAttribute("wasteused");
			request.getSession().removeAttribute("addtionalused");

			
	     return this.NEW(mapping, form, request, response);
	 } 
	 

		
	  
}
