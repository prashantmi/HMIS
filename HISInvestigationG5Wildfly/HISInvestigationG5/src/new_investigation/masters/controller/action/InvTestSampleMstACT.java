/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	TEST SAMPLE MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	This master is used to define the collection areas for 
 											sample collection area process
 ## Date of Creation					: 	24-Feb-2015
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

import new_investigation.masters.controller.fb.InvTestSampleMstFB;
import new_investigation.masters.controller.utl.InvTestSampleMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InvTestSampleMstACT extends GenericController
{

	String target = null;

	public InvTestSampleMstACT()
	{

		super(new InvTestSampleMstUTIL(),"/masters/InvTestSampleMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvTestSampleMstFB testsample_fb = (InvTestSampleMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		InvTestSampleMstUTIL.fetchInvTestSample(testsample_fb, request);
		testsample_fb.setDefaultSample("0");

		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvTestSampleMstFB testsample_fb = (InvTestSampleMstFB) form;
		boolean hasFlag=  InvTestSampleMstUTIL.saveInvTestSample(testsample_fb, request);
		if(hasFlag){
			testsample_fb.reset(mapping, request);
		}
		testsample_fb.setHmode("ADD");

		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvTestSampleMstFB testsample_fb = (InvTestSampleMstFB) form;
		WebUTIL.refreshTransState(request);
		InvTestSampleMstUTIL.fetchCheckListInvTestSample(testsample_fb, request);
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvTestSampleMstFB testsample_fb = (InvTestSampleMstFB) form;
		boolean hasFlag= InvTestSampleMstUTIL.savemodifyInvTestSample(testsample_fb, request);
		if(hasFlag){
            return this.LIST(mapping, testsample_fb, request, response);
         }
           else
           {
       		testsample_fb.setHmode("MODIFY");
       		return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvTestSampleMstFB testsample_fb = (InvTestSampleMstFB) form;
		InvTestSampleMstUTIL.reFetchCheckListInvTestSample(testsample_fb, request);
		testsample_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}






}
