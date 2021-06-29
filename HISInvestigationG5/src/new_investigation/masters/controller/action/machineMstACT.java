/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	MACHINE MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	NEW MACHINES
 ## Date of Creation					: 	16-OCT-2015
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

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.machineMstDATA;
import new_investigation.masters.controller.fb.machineMstFB;
import new_investigation.masters.controller.utl.LabCollectionAreaMstUTIL;
import new_investigation.masters.controller.utl.machineMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class machineMstACT extends GenericController
{

	String target = null;

	public machineMstACT()
	{

		super(new machineMstUTIL(),"/masters/machineMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		machineMstFB machinemst_fb = (machineMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
								
		machineMstUTIL.getEssentialMachineCombo(machinemst_fb, request);
		
		return mapping.findForward("ADD");
	}
	
/*	public ActionForward GETAREA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		machineMstFB labcollectionarea_fb = (machineMstFB) form;
		LabCollectionAreaMstUTIL.getArea(labcollectionarea_fb, request);
		labcollectionarea_fb.setHmode("GETAREA");
		
		if(hasFlag){
			collectionarea_fb.reset(mapping, request);
		}
		return mapping.findForward("ADD");
		
		
		return mapping.findForward("ADD");
	}*/

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		machineMstFB _fb = (machineMstFB) form;
		boolean hasFlag=  machineMstUTIL.saveMachine(_fb, request);
		System.out.println("machine type is "+_fb.getMachineType());
		if(hasFlag){
			_fb.reset(mapping, request);
			
		}
	
			return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		machineMstFB machinemst_fb = (machineMstFB) form;
		WebUTIL.refreshTransState(request);
		machineMstUTIL.fetchMachineDetails(machinemst_fb, request);
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		machineMstFB machineMst_fb = (machineMstFB) form;
		boolean hasFlag= machineMstUTIL.saveModifyMachine(machineMst_fb, request);
		if(hasFlag){

            return this.LIST(mapping, machineMst_fb, request, response);
         }
           else
           {
        	   		machineMst_fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
		
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		machineMstFB machine_fb = (machineMstFB) form;
		machineMstUTIL.reFetchMachineDetails(machine_fb, request);
		machine_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}



}
