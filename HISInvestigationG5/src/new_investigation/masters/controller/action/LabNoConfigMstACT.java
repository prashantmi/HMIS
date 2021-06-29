/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	LAB NUMBER CONFIG MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	Lab Patient Mapping
 ## Date of Creation					: 	19-Mar-2015
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

import new_investigation.masters.controller.fb.LabNoConfigMstFB;
import new_investigation.masters.controller.fb.TestGroupInfoLocalMstFB;
import new_investigation.masters.controller.utl.LabNoConfigMstUTIL;
import new_investigation.masters.controller.utl.TestGroupInfoLocalMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LabNoConfigMstACT extends GenericController
{

	String target = null;

	public LabNoConfigMstACT()
	{

		super(new LabNoConfigMstUTIL(),"/masters/LabNoConfigMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabNoConfigMstFB labnoconfig_fb = (LabNoConfigMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		
		LabNoConfigMstUTIL.fetchLabNoConfig(labnoconfig_fb, request);

		return mapping.findForward("ADD");
	}
	

	public ActionForward GETTEST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabNoConfigMstFB labnoconfig_fb = (LabNoConfigMstFB) form;
		LabNoConfigMstUTIL.getTest(labnoconfig_fb, request);
		labnoconfig_fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabNoConfigMstFB labnoconfig_fb = (LabNoConfigMstFB) form;
		boolean hasFlag=  LabNoConfigMstUTIL.saveLabNoConfig(labnoconfig_fb, request);
		if(hasFlag){
			labnoconfig_fb.reset(mapping, request);
		}
		labnoconfig_fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabNoConfigMstFB labnoconfig_fb = (LabNoConfigMstFB) form;
		WebUTIL.refreshTransState(request);
		LabNoConfigMstUTIL.fetchCheckListLabNoConfig(labnoconfig_fb, request);
		labnoconfig_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabNoConfigMstFB labnoconfig_fb = (LabNoConfigMstFB) form;
		boolean hasFlag= LabNoConfigMstUTIL.savemodifyLabNoConfig(labnoconfig_fb, request);
		if(hasFlag){

            return this.LIST(mapping, labnoconfig_fb, request, response);
         }
           else
           {
       				labnoconfig_fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabNoConfigMstFB labnoconfig_fb = (LabNoConfigMstFB) form;
		LabNoConfigMstUTIL.reFetchCheckListLabNoConfig(labnoconfig_fb, request);
		labnoconfig_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}




}
