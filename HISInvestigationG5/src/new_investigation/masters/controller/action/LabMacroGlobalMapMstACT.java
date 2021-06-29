/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
 ## Name of Developer		 			:	Yogender Yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	Global LAB Macro Mapping MASTER
 ## Purpose								:	This master is used to define the Global lab macro mapping master
 ## Date of Creation					: 	17-MAR-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/**************************************************************************************************************/ 


package new_investigation.masters.controller.action;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.LabMacroMapMstFB;
import new_investigation.masters.controller.utl.LabMacroGlobalMapMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LabMacroGlobalMapMstACT extends GenericController
{

	String target = null;

	public LabMacroGlobalMapMstACT()
	{

		super(new LabMacroGlobalMapMstUTIL(),"/masters/LabMacroGlobalMapMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMacroMapMstFB labmacromap_fb = (LabMacroMapMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
								
		LabMacroGlobalMapMstUTIL.fetchLabMacroGlobalMap(labmacromap_fb, request);
		
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETMACRO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMacroMapMstFB labmacromap_fb = (LabMacroMapMstFB) form;
		LabMacroGlobalMapMstUTIL.getGlobalMacro(labmacromap_fb, request);
		labmacromap_fb.setHmode("GETMACRO");
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMacroMapMstFB labmacromap_fb = (LabMacroMapMstFB) form;
		boolean hasFlag=  LabMacroGlobalMapMstUTIL.saveLabMacroGlobalMap(labmacromap_fb, request);
		if(hasFlag){
			labmacromap_fb.reset(mapping, request);
		}
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMacroMapMstFB labmacromap_fb = (LabMacroMapMstFB) form;
		WebUTIL.refreshTransState(request);
		LabMacroGlobalMapMstUTIL.fetchCheckListLabMacroGlobalMap(labmacromap_fb, request);
		/*labmacromap_fb.setHmode("GETMACRO");*/
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMacroMapMstFB labmacromap_fb = (LabMacroMapMstFB) form;
		boolean hasFlag= LabMacroGlobalMapMstUTIL.saveLabMacroGlobalMap(labmacromap_fb, request);
		if(hasFlag){

            return this.LIST(mapping, labmacromap_fb, request, response);
         }
           else
           {
       				labmacromap_fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMacroMapMstFB labmacromap_fb = (LabMacroMapMstFB) form;
		LabMacroGlobalMapMstUTIL.reFetchCheckListLabMacroGlobalMap(labmacromap_fb, request);
		labmacromap_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}



}
