/*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	EXTERNAL LAB MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	This master is used to capture the external laboratory
 ## Date of Creation					: 	07-Dec-2015  
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/


package new_investigation.masters.controller.action;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.masterutil.GenericController;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import new_investigation.masters.controller.fb.externalLabMstFB;
import new_investigation.masters.controller.utl.externalLabMstUTL;


public class externalLabMstACT extends GenericController
{

	String target = null;

	public externalLabMstACT()
	{

		super(new externalLabMstUTL(),"/masters/externalLabMstACTION");

	}



	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		externalLabMstFB parameter_fb = (externalLabMstFB) form;
		boolean hasFlag=  externalLabMstUTL.saveParameter(parameter_fb, request);
		if(hasFlag){
			parameter_fb.reset(mapping, request);
		}
		return mapping.findForward("ADD");
	}


	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		externalLabMstFB parameter_fb = (externalLabMstFB) form;
		WebUTIL.refreshTransState(request);
		externalLabMstUTL.fetchCheckListParameter(parameter_fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		externalLabMstFB parameter_fb = (externalLabMstFB) form;
		boolean hasFlag= externalLabMstUTL.savemodifyParameter(parameter_fb, request);
		
		if(hasFlag){

             return this.LIST(mapping, parameter_fb, request, response);
          }
            else
            {
        		parameter_fb.setHmode("MODIFY");
        		return mapping.findForward("ADD"); 
            }

		
		
		
	
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		externalLabMstFB parameter_fb = (externalLabMstFB) form;
		WebUTIL.refreshTransState(request);
		externalLabMstUTL.fetchCheckListParameter(parameter_fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		externalLabMstFB parameter_fb = (externalLabMstFB) form;
		WebUTIL.refreshTransState(request);

		externalLabMstUTL.reFetchCheckListParameter(parameter_fb, request);
		parameter_fb.setHmode("MODIFY");

		return mapping.findForward("ADD"); 
	}



}
