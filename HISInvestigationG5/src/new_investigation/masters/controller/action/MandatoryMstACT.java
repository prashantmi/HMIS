/*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	MANDATORY MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	This is used to define the mandatory in Lab
 ## Date of Creation					: 	30-Jan-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/




package new_investigation.masters.controller.action;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.MandatoryMstFB;
import new_investigation.masters.controller.utl.MandatoryMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MandatoryMstACT extends GenericController
{

	String target = null;

	public MandatoryMstACT()
	{

		super(new MandatoryMstUTIL(),"/masters/MandatoryMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MandatoryMstFB mandatorymaster_fb = (MandatoryMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		mandatorymaster_fb.setMandatoryType("1");
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MandatoryMstFB mandatorymaster_fb = (MandatoryMstFB) form;
		boolean hasFlag=  MandatoryMstUTIL.saveMandatory(mandatorymaster_fb, request);
		if(hasFlag){
			mandatorymaster_fb.reset(mapping, request);
		}
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MandatoryMstFB mandatorymaster_fb = (MandatoryMstFB) form;
		WebUTIL.refreshTransState(request);
		MandatoryMstUTIL.fetchCheckListMandatory(mandatorymaster_fb, request);
		return mapping.findForward("ADD"); 


	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MandatoryMstFB mandatorymaster_fb = (MandatoryMstFB) form;
		boolean hasFlag= MandatoryMstUTIL.savemodifyMandatory(mandatorymaster_fb, request);
		if(hasFlag){
            return this.LIST(mapping, mandatorymaster_fb, request, response);
         }
           else
           {
       		mandatorymaster_fb.setHmode("MODIFY");
       		return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MandatoryMstFB mandatorymaster_fb = (MandatoryMstFB) form;
		MandatoryMstUTIL.reFetchCheckListMandatory(mandatorymaster_fb, request);
		mandatorymaster_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 


	}


}
