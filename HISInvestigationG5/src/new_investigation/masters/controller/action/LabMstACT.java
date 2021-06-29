/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PATHAN JOHN BASHA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :Lab Master
 ## Purpose						        : This master is used to define labs
 ## Date of Creation					:10-Jan-2015 
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
import new_investigation.masters.controller.fb.LabMstFB;
import new_investigation.masters.controller.utl.InvSampleMstUTIL;
import new_investigation.masters.controller.utl.LabMstUTIL;

public class LabMstACT extends GenericController
{
 
	String target = null;
   String message="";
	public LabMstACT()
	{

		super(new LabMstUTIL(),"/masters/LabMstACT");

	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LabMstUTIL.fetchLabD(fb, request);
		fb.setLabType("1");
		fb.setSampleNumberConfig("1");
		fb.setLabNumberConfig("1");
		fb.setNumberofTests("9999");
		fb.setDisplayHeader("1");

		fb.setLabWorkingDays("1111111"); //for weekdays
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
	    boolean hasFlag=  LabMstUTIL.saveLab(fb, request);
	     if(hasFlag){
	    	 fb.reset(mapping, request);
	     }
		return mapping.findForward("ADD");
	}

	 
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
		WebUTIL.refreshTransState(request);
		LabMstUTIL.fetchLab(fb, request);
		return mapping.findForward("ADD"); 
	}
  
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
		boolean hasFlag= LabMstUTIL.savemodifyLab(fb, request);
		if(hasFlag){
			 fb.reset(mapping, request);
				message="Data Saved Sucessfully";
				LIST(mapping, fb, request, response);
				return mapping.findForward("LIST");
			}
			else
		    	 return mapping.findForward("MODIFY"); 
			 
		}
public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
	    LabMstFB fb = (LabMstFB) form;
	    LabMstUTIL.reFetchLab(fb, request);
			fb.setHmode("MODIFY");
			return mapping.findForward("ADD"); 


		}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
		WebUTIL.refreshTransState(request);
		LabMstUTIL.fetchLab(fb, request);
		return mapping.findForward("ADD");
	}
	
}
