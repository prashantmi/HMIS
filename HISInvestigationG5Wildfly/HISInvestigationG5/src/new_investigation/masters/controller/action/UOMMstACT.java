/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PATHAN JOHN BASHA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :UOM Master
 ## Purpose						        : 
 ## Date of Creation					:12-Jan-2015 
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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import new_investigation.masters.controller.fb.InvSampleMstFB;
import new_investigation.masters.controller.fb.UOMMstFB;
import new_investigation.masters.controller.utl.UOMMstUTL;
import new_investigation.masters.controller.utl.InvSampleMstUTIL;

public class UOMMstACT extends GenericController
{
 
	String message="";
	public UOMMstACT()
	{

		super(new UOMMstUTL(),"/masters/UnitOfMeasurmentMstACT");

	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UOMMstFB fb = (UOMMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		UOMMstUTL.fetchUOMD(fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UOMMstFB fb = (UOMMstFB) form;
	    boolean hasFlag=  UOMMstUTL.saveUOM(fb, request);
	     if(hasFlag){
	    	 fb.reset(mapping, request);
	     }
		return mapping.findForward("ADD");
	}

	 

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UOMMstFB fb = (UOMMstFB) form;
		WebUTIL.refreshTransState(request);
		UOMMstUTL.fetchUOM(fb, request);
		return mapping.findForward("ADD"); 
	}
  
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UOMMstFB fb = (UOMMstFB) form;
		boolean hasFlag= UOMMstUTL.savemodifyUOM(fb, request);
		if(hasFlag){
			LIST(mapping, fb, request, response);
			return mapping.findForward("LIST");
		}
		else
			fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UOMMstFB fb = (UOMMstFB) form;
		WebUTIL.refreshTransState(request);
		UOMMstUTL.fetchUOM(fb, request);
		return mapping.findForward("ADD");
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UOMMstFB fb = (UOMMstFB) form;
		UOMMstUTL.reFetchUom(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 


	}
}
