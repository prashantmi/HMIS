/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
 ## Name of Developer		 			:	Yogender Yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	Global LAB Canned MASTER
 ## Purpose								:	This master is used to define the Global lab Canned master
 ## Date of Creation					: 	27-MAR-2015
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

import new_investigation.masters.controller.fb.LabCannedMstFB;
import new_investigation.masters.controller.utl.GlobalLabCannedMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GlobalLabCannedMstACT extends GenericController
{

	String target = null;

	public GlobalLabCannedMstACT()
	{

		super(new GlobalLabCannedMstUTIL(),"/masters/GlobalLabCannedMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCannedMstFB labcanned_fb = (LabCannedMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
								
		GlobalLabCannedMstUTIL.fetchGlobalLabCanned(labcanned_fb, request);
		
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETLABCANNED(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCannedMstFB labcanned_fb = (LabCannedMstFB) form;
		GlobalLabCannedMstUTIL.getGlobalLabCanned(labcanned_fb, request);
		labcanned_fb.setHmode("GETLABCANNED");
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCannedMstFB labcanned_fb = (LabCannedMstFB) form;
		boolean hasFlag=  GlobalLabCannedMstUTIL.saveGlobalLabCanned(labcanned_fb, request);
		if(hasFlag){
			labcanned_fb.reset(mapping, request);
		}
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCannedMstFB labcanned_fb = (LabCannedMstFB) form;
		WebUTIL.refreshTransState(request);
		GlobalLabCannedMstUTIL.fetchCheckListGlobalLabCanned(labcanned_fb, request);
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCannedMstFB labcanned_fb = (LabCannedMstFB) form;
		boolean hasFlag= GlobalLabCannedMstUTIL.saveGlobalLabCanned(labcanned_fb, request);
		if(hasFlag){

            return this.LIST(mapping, labcanned_fb, request, response);
         }
           else
           {
       				labcanned_fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCannedMstFB labcanned_fb = (LabCannedMstFB) form;
		GlobalLabCannedMstUTIL.reFetchCheckListGlobalLabCanned(labcanned_fb, request);
		labcanned_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}



}
