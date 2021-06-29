
/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	LOCAL LAB MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	This master is used to capture Local  Labs used for investigation Process.  
 ## Date of Creation					: 	27-Mar-2015
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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import new_investigation.masters.controller.fb.LabMstFB;
import new_investigation.masters.controller.utl.LocalLabMstUTIL;

public class LocalLabMstACT extends GenericController
{


	
	public LocalLabMstACT()
	{

		super(new LocalLabMstUTIL(),"/masters/LocalLabMstACT");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LocalLabMstUTIL.fetchLocalLabD(fb, request);
		fb.setLabType("1");
		fb.setSampleNumberConfig("1");
		fb.setLabNumberConfig("1");
		fb.setNumberofTests("9999");
		fb.setDisplayHeader("1");
		fb.setLabWorkingDays("1111111"); //for weekdays
		fb.setFinalRemark("1");
	
		fb.setIstestmodify("0");
		fb.setIsAppointment("0");
		fb.setIsaptmand("0");
		fb.setIsfilmused("0");
		fb.setResultentered("0");
		fb.setHidefromdesk("0");
		fb.setAccesstoaddendum("0");
		fb.setSopbased("1");
		
		return mapping.findForward("ADD");
	}

	public ActionForward POPULATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LocalLabMstUTIL.Populate(fb, request);
		
		fb.setHmode("ADD");
		
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
		boolean hasFlag=  LocalLabMstUTIL.saveLocalLab(fb, request);
	
		if(hasFlag){
			fb.reset(mapping, request);
		}
		
		return mapping.findForward("ADD");
	}


	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
		WebUTIL.refreshTransState(request);
		LocalLabMstUTIL.fetchLocalLab(fb, request);
		
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
		boolean hasFlag= LocalLabMstUTIL.savemodifyLocalLab(fb, request);
		if(hasFlag){
			
            return this.LIST(mapping, fb, request, response);
		}
		else
			
			return mapping.findForward("MODIFY"); 

	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
		LocalLabMstUTIL.reFetchLocalLab(fb, request);
		fb.setHmode("MODIFY");
		
		return mapping.findForward("ADD"); 


	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabMstFB fb = (LabMstFB) form;
		WebUTIL.refreshTransState(request);
		LocalLabMstUTIL.fetchLocalLab(fb, request);
		
		return mapping.findForward("ADD");
	}

}
