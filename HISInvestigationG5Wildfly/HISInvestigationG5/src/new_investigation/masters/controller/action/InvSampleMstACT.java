
/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PATHAN JOHN BASHA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :Sample Master
 ## Purpose						        : 
 ## Date of Creation					:05-Jan-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.masters.controller.action;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.InvSampleMstFB;
import new_investigation.masters.controller.utl.InvSampleMstUTIL;
import new_investigation.masters.controller.utl.MandatoryMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InvSampleMstACT extends GenericController
{
 
	String target = null;
    String message="";
	public InvSampleMstACT()
	{

		super(new InvSampleMstUTIL(),"/masters/SampleMstACTION");

	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvSampleMstFB fb = (InvSampleMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		InvSampleMstUTIL.fetchSample(fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvSampleMstFB fb = (InvSampleMstFB) form;
	    boolean hasFlag=  InvSampleMstUTIL.saveCheckList(fb, request);
	     if(hasFlag){
	    	 fb.reset(mapping, request);
	     }
	      
		return mapping.findForward("ADD");
	}

	 
	 
	 
		 
	 
	 
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvSampleMstFB fb = (InvSampleMstFB) form;
		WebUTIL.refreshTransState(request);
		InvSampleMstUTIL.fetchCheckList(fb, request);
		return mapping.findForward("ADD"); 
	}
  
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvSampleMstFB fb = (InvSampleMstFB) form;
		boolean hasFlag= InvSampleMstUTIL.savemodifyCheckList(fb, request);
		if(hasFlag){
			 fb.reset(mapping, request);
			
			LIST(mapping, fb, request, response);
			return mapping.findForward("LIST");
		}
		else
			fb.setHmode("MODIFY");
			return mapping.findForward("ADD"); 
		 
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvSampleMstFB fb = (InvSampleMstFB) form;
		InvSampleMstUTIL.reFetchSample(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 


	}

	
}
