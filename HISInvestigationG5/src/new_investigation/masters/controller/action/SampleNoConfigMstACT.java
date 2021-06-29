/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	SAMPLE NUMBER CONFIG MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	Sample Patient Mapping
 ## Date of Creation					: 	01-Apr-2015
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

import new_investigation.masters.controller.fb.SampleNoConfigMstFB;
import new_investigation.masters.controller.fb.TestGroupInfoLocalMstFB;
import new_investigation.masters.controller.utl.SampleNoConfigMstUTIL;
import new_investigation.masters.controller.utl.TestGroupInfoLocalMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SampleNoConfigMstACT extends GenericController
{

	String target = null;

	public SampleNoConfigMstACT()
	{

		super(new SampleNoConfigMstUTIL(),"/masters/SampleNoConfigMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		SampleNoConfigMstFB samplenoconfig_fb = (SampleNoConfigMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		
		SampleNoConfigMstUTIL.fetchSampleNoConfig(samplenoconfig_fb, request);

		return mapping.findForward("ADD");
	}
	

	public ActionForward GETTEST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		SampleNoConfigMstFB samplenoconfig_fb = (SampleNoConfigMstFB) form;
		SampleNoConfigMstUTIL.getTest(samplenoconfig_fb, request);
		samplenoconfig_fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		SampleNoConfigMstFB samplenoconfig_fb = (SampleNoConfigMstFB) form;
		boolean hasFlag=  SampleNoConfigMstUTIL.saveSampleNoConfig(samplenoconfig_fb, request);
		if(hasFlag){
			samplenoconfig_fb.reset(mapping, request);
		}
		samplenoconfig_fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		SampleNoConfigMstFB samplenoconfig_fb = (SampleNoConfigMstFB) form;
		WebUTIL.refreshTransState(request);
		SampleNoConfigMstUTIL.fetchCheckListSampleNoConfig(samplenoconfig_fb, request);
		samplenoconfig_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		SampleNoConfigMstFB samplenoconfig_fb = (SampleNoConfigMstFB) form;
		boolean hasFlag= SampleNoConfigMstUTIL.savemodifySampleNoConfig(samplenoconfig_fb, request);
		if(hasFlag){

            return this.LIST(mapping, samplenoconfig_fb, request, response);
         }
           else
           {
       				samplenoconfig_fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		SampleNoConfigMstFB samplenoconfig_fb = (SampleNoConfigMstFB) form;
		SampleNoConfigMstUTIL.reFetchCheckListSampleNoConfig(samplenoconfig_fb, request);
		samplenoconfig_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}




}
