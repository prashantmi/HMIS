/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	LAB COLLECTION AREA MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	Mapping Collection Areas to Lab
 ## Date of Creation					: 	11-MAR-2015
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
import new_investigation.masters.controller.fb.LabCollectionAreaMstFB;
import new_investigation.masters.controller.utl.LabCollectionAreaMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LabCollectionAreaMstACT extends GenericController
{

	String target = null;

	public LabCollectionAreaMstACT()
	{

		super(new LabCollectionAreaMstUTIL(),"/masters/LabCollectionAreaMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCollectionAreaMstFB labcollectionarea_fb = (LabCollectionAreaMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
								
		LabCollectionAreaMstUTIL.fetchLabCollectionArea(labcollectionarea_fb, request);
		
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETAREA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCollectionAreaMstFB labcollectionarea_fb = (LabCollectionAreaMstFB) form;
		LabCollectionAreaMstUTIL.getArea(labcollectionarea_fb, request);
		labcollectionarea_fb.setHmode("GETAREA");
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCollectionAreaMstFB labcollectionarea_fb = (LabCollectionAreaMstFB) form;
		boolean hasFlag=  LabCollectionAreaMstUTIL.saveLabCollectionArea(labcollectionarea_fb, request);
		if(hasFlag){
			labcollectionarea_fb.reset(mapping, request);
			return this.LIST(mapping, labcollectionarea_fb, request, response);
		}
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCollectionAreaMstFB labcollectionarea_fb = (LabCollectionAreaMstFB) form;
		WebUTIL.refreshTransState(request);
		LabCollectionAreaMstUTIL.fetchCheckListLabCollectionArea(labcollectionarea_fb, request);
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCollectionAreaMstFB labcollectionarea_fb = (LabCollectionAreaMstFB) form;
		boolean hasFlag= LabCollectionAreaMstUTIL.saveLabCollectionArea(labcollectionarea_fb, request);
		if(hasFlag){

            return this.LIST(mapping, labcollectionarea_fb, request, response);
         }
           else
           {
       				labcollectionarea_fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabCollectionAreaMstFB labcollectionarea_fb = (LabCollectionAreaMstFB) form;
		LabCollectionAreaMstUTIL.reFetchCheckListLabCollectionArea(labcollectionarea_fb, request);
		labcollectionarea_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}



}
