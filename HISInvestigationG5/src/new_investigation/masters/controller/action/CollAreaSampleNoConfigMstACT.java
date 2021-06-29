/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	COLLECTION AREA SAMPLE NUMBER CONFIG MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	Sample Patient Mapping
 ## Date of Creation					: 	11-Apr-2015
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

import new_investigation.masters.controller.fb.CollAreaSampleNoConfigMstFB;
import new_investigation.masters.controller.utl.CollAreaSampleNoConfigMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CollAreaSampleNoConfigMstACT extends GenericController
{

	String target = null;

	public CollAreaSampleNoConfigMstACT()
	{

		super(new CollAreaSampleNoConfigMstUTIL(),"/masters/CollAreaSampleNoConfigMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollAreaSampleNoConfigMstFB collareasamplenoconfig_fb = (CollAreaSampleNoConfigMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		
		CollAreaSampleNoConfigMstUTIL.fetchCollAreaSampleNoConfig(collareasamplenoconfig_fb, request);

		return mapping.findForward("ADD");
	}
	

	public ActionForward GETAREA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollAreaSampleNoConfigMstFB collareasamplenoconfig_fb = (CollAreaSampleNoConfigMstFB) form;
		CollAreaSampleNoConfigMstUTIL.getArea(collareasamplenoconfig_fb, request);
		collareasamplenoconfig_fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollAreaSampleNoConfigMstFB collareasamplenoconfig_fb = (CollAreaSampleNoConfigMstFB) form;
		boolean hasFlag=  CollAreaSampleNoConfigMstUTIL.saveCollAreaSampleNoConfig(collareasamplenoconfig_fb, request);
		if(hasFlag){
			collareasamplenoconfig_fb.reset(mapping, request);
		}
		collareasamplenoconfig_fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollAreaSampleNoConfigMstFB collareasamplenoconfig_fb = (CollAreaSampleNoConfigMstFB) form;
		WebUTIL.refreshTransState(request);
		CollAreaSampleNoConfigMstUTIL.fetchCheckListCollAreaSampleNoConfig(collareasamplenoconfig_fb, request);
		collareasamplenoconfig_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollAreaSampleNoConfigMstFB collareasamplenoconfig_fb = (CollAreaSampleNoConfigMstFB) form;
		boolean hasFlag= CollAreaSampleNoConfigMstUTIL.savemodifyCollAreaSampleNoConfig(collareasamplenoconfig_fb, request);
		if(hasFlag){

            return this.LIST(mapping, collareasamplenoconfig_fb, request, response);
         }
           else
           {
       				collareasamplenoconfig_fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollAreaSampleNoConfigMstFB collareasamplenoconfig_fb = (CollAreaSampleNoConfigMstFB) form;
		CollAreaSampleNoConfigMstUTIL.reFetchCheckListCollAreaSampleNoConfig(collareasamplenoconfig_fb, request);
		collareasamplenoconfig_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	public ActionForward GETLAB(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollAreaSampleNoConfigMstFB collareasamplenoconfig_fb = (CollAreaSampleNoConfigMstFB) form;
		CollAreaSampleNoConfigMstUTIL.getAreaWiseLab(collareasamplenoconfig_fb, request);
		collareasamplenoconfig_fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}
	



}
