/*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
 ## Name of Developer		 			:	Yogender yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:   Canned Master
 ## Purpose								:	This master is used to define canned documents used for investigation Process
 ## Date of Creation					:   05-March-2015
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
import new_investigation.masters.controller.fb.CannedMstFB;
import new_investigation.masters.controller.utl.CannedMstUTIL;


public class CannedMstACT extends GenericController
{

	String target = null;

	public CannedMstACT()
	{
									
		super(new CannedMstUTIL(),"/masters/CannedMstACTION");

	}



	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CannedMstFB canned_fb = (CannedMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		canned_fb.setText1(""); 
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CannedMstFB canned_fb = (CannedMstFB) form;
		boolean hasFlag=  CannedMstUTIL.saveCanned(canned_fb, request);
		if(hasFlag){
			canned_fb.reset(mapping, request);
		}
		return mapping.findForward("ADD");
	}


	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CannedMstFB canned_fb = (CannedMstFB) form;
		WebUTIL.refreshTransState(request);
		CannedMstUTIL.modifyCanned(canned_fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CannedMstFB canned_fb = (CannedMstFB) form;
		boolean hasFlag= CannedMstUTIL.updateCanned(canned_fb, request);
		
		if(hasFlag){
             return this.LIST(mapping, canned_fb, request, response);
          }
            else
            {
            	canned_fb.setHmode("MODIFY");
        		return mapping.findForward("ADD"); 
            }
		
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CannedMstFB canned_fb = (CannedMstFB) form;
		WebUTIL.refreshTransState(request);
		CannedMstUTIL.modifyCanned(canned_fb, request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CannedMstFB canned_fb = (CannedMstFB) form;
		CannedMstUTIL.clearCanned(canned_fb, request);
		canned_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

}
