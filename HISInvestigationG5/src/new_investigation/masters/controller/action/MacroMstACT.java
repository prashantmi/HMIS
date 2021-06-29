/*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
  ## Name of Developer		 			:	Yogender yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:   Macro Master
 ## Purpose								:	This master is used to capture the Macro used for investigation Process
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
import new_investigation.masters.controller.fb.MacroMstFB;
import new_investigation.masters.controller.utl.MacroMstUTIL;


public class MacroMstACT extends GenericController
{

	String target = null;

	public MacroMstACT()
	{

		super(new MacroMstUTIL(),"/masters/MacroMstACTION");

	}
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
			return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MacroMstFB macro_fb = (MacroMstFB) form;
		boolean hasFlag=  MacroMstUTIL.saveMacro(macro_fb, request);
		
		macro_fb.setMacroText("");
		macro_fb.setRemarks("");
		return mapping.findForward("ADD");
	}


	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MacroMstFB macro_fb = (MacroMstFB) form;
		WebUTIL.refreshTransState(request);
		MacroMstUTIL.fetchCheckListMacro(macro_fb, request);
		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MacroMstFB macro_fb = (MacroMstFB) form;
		boolean hasFlag= MacroMstUTIL.savemodifyMacro(macro_fb, request);
		if(hasFlag){
			
             return this.LIST(mapping, macro_fb, request, response);
          }
            else
            {
            	macro_fb.setHmode("MODIFY");
        		return mapping.findForward("ADD"); 
            }
		
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MacroMstFB macro_fb = (MacroMstFB) form;
		WebUTIL.refreshTransState(request);
		MacroMstUTIL.fetchCheckListMacro(macro_fb, request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MacroMstFB macro_fb = (MacroMstFB) form;
		MacroMstUTIL.reFetchCheckListMacro(macro_fb, request);
		macro_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}
}
