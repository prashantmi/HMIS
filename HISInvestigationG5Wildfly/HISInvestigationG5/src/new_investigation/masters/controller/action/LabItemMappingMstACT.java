/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	TEST GROUP INFO LOCAL MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	Mapping master to map test to Test Groups
 ## Date of Creation					: 	16-MAR-2015
 ## Modification Log					:				
 ##		Modify Date						: 30-NOV-2015	
 ##		Reason	(CR/PRS)				: Addition of Printing Template
 ##		Modify By						: Akshita Topre
/**************************************************************************************************************/ 

package new_investigation.masters.controller.action;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.fb.LabItemMappingMstFB;
import new_investigation.masters.controller.utl.LabItemMappingMstUTL;
import new_investigation.masters.controller.utl.LabItemMappingMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LabItemMappingMstACT extends GenericController
{

	String target = null;

	public LabItemMappingMstACT()
	{

		super(new LabItemMappingMstUTL(),"/masters/LabItemMappingMstACT");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabItemMappingMstFB labItemMappingMstFB = (LabItemMappingMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
								
		LabItemMappingMstUTL.getEssentials(labItemMappingMstFB, request);
		
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETITEMS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabItemMappingMstFB labItemMappingMstFB = (LabItemMappingMstFB) form;
		LabItemMappingMstUTL.getItems(labItemMappingMstFB, request);
		labItemMappingMstFB.setHmode("GETTEST");
	
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabItemMappingMstFB labItemMappingMstFB = (LabItemMappingMstFB) form;
		boolean hasFlag=  LabItemMappingMstUTL.saveNewItemList(labItemMappingMstFB, request);
		if(hasFlag){
			labItemMappingMstFB.reset(mapping, request);
		}
		else{
			
			labItemMappingMstFB.setLabCode("-1");
		}
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabItemMappingMstFB labItemMappingMstFB = (LabItemMappingMstFB) form;
		WebUTIL.refreshTransState(request);
		LabItemMappingMstUTL.getEssentials(labItemMappingMstFB, request);
		LabItemMappingMstUTL.fetchInfo(labItemMappingMstFB, request);
		labItemMappingMstFB.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabItemMappingMstFB labItemMappingMstFB = (LabItemMappingMstFB) form;
		boolean hasFlag= LabItemMappingMstUTL.saveNewItemList(labItemMappingMstFB, request);
		if(hasFlag){

            return this.LIST(mapping, labItemMappingMstFB, request, response);
         }
           else
           {
       				labItemMappingMstFB.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabItemMappingMstFB labItemMappingMstFB = (LabItemMappingMstFB) form;
		LabItemMappingMstUTL.getEssentials(labItemMappingMstFB, request);
		LabItemMappingMstUTL.reFetchInfo(labItemMappingMstFB, request);
		labItemMappingMstFB.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}



}
