/*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
 ## Name of Developer		 			:	Yogender yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:   Rejection Reason Master
 ## Purpose								:	This master is used to capture the Rejection Reason used for investigation Process
 ## Date of Creation					:   
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


import new_investigation.masters.controller.fb.RejectionReasonMstFB;
import new_investigation.masters.controller.utl.RejectionReasonMstUTIL;


public class RejectionReasonMstACT extends GenericController
{

	String target = null;

	public RejectionReasonMstACT()
	{

		super(new RejectionReasonMstUTIL(),"/masters/RejectionReasonMstACTION");

	}



	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		RejectionReasonMstFB rejectionreason_fb = (RejectionReasonMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		rejectionreason_fb.setAcceptanceType("1");
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		RejectionReasonMstFB rejectionreason_fb = (RejectionReasonMstFB) form;
		boolean hasFlag=  RejectionReasonMstUTIL.saveRejectionReason(rejectionreason_fb, request);
		if(hasFlag){
			rejectionreason_fb.reset(mapping, request);
		}
		return mapping.findForward("ADD");
	}


	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		RejectionReasonMstFB rejectionreason_fb = (RejectionReasonMstFB) form;
		WebUTIL.refreshTransState(request);
		RejectionReasonMstUTIL.fetchCheckListRejectionReason(rejectionreason_fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		RejectionReasonMstFB rejectionreason_fb = (RejectionReasonMstFB) form;
		boolean hasFlag= RejectionReasonMstUTIL.savemodifyRejectionReason(rejectionreason_fb, request);
		 
		if(hasFlag){
            // fb.reset(mapping, request);
             return this.LIST(mapping, rejectionreason_fb, request, response);
          }
            else
            {
            	rejectionreason_fb.setHmode("MODIFY");
    		    return mapping.findForward("ADD");
            }	  
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		RejectionReasonMstFB rejectionreason_fb = (RejectionReasonMstFB) form;
		WebUTIL.refreshTransState(request);
		RejectionReasonMstUTIL.fetchCheckListRejectionReason(rejectionreason_fb, request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		RejectionReasonMstFB rejectionreason_fb = (RejectionReasonMstFB) form;
		RejectionReasonMstUTIL.reFetchCheckListRejectionReason(rejectionreason_fb, request);
		rejectionreason_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

}
