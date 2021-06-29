/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	COLLECTION AREA MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	This master is used to define the collection areas for 
 											sample collection area process
 ## Date of Creation					: 	20-Feb-2015
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
import new_investigation.masters.controller.fb.CollectionAreaMstFB;
import new_investigation.masters.controller.utl.CollectionAreaMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CollectionAreaMstACT extends GenericController
{

	String target = null;

	public CollectionAreaMstACT()
	{

		super(new CollectionAreaMstUTIL(),"/masters/CollectionAreaMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollectionAreaMstFB collectionarea_fb = (CollectionAreaMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		CollectionAreaMstUTIL.fetchCollectionArea(collectionarea_fb, request);
		collectionarea_fb.setCollectionareaType("1");
		collectionarea_fb.setSopbased("1");
		
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollectionAreaMstFB collectionarea_fb = (CollectionAreaMstFB) form;
		boolean hasFlag=  CollectionAreaMstUTIL.saveCollectionArea(collectionarea_fb, request);
		if(hasFlag){
			collectionarea_fb.reset(mapping, request);
			
			request.removeAttribute(InvestigationConfig.LIST_WARD_COMBO);
			CollectionAreaMstUTIL.fetchCollectionArea(collectionarea_fb, request);
			
		}
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollectionAreaMstFB collectionarea_fb = (CollectionAreaMstFB) form;
		WebUTIL.refreshTransState(request);
		CollectionAreaMstUTIL.fetchCheckListCollectionArea(collectionarea_fb, request);
		collectionarea_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollectionAreaMstFB collectionarea_fb = (CollectionAreaMstFB) form;
		boolean hasFlag= CollectionAreaMstUTIL.savemodifyCollectionArea(collectionarea_fb, request);
		if(hasFlag){

            return this.LIST(mapping, collectionarea_fb, request, response);
         }
           else
           {
       				collectionarea_fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CollectionAreaMstFB collectionarea_fb = (CollectionAreaMstFB) form;
		CollectionAreaMstUTIL.reFetchCheckListCollectionArea(collectionarea_fb, request);
		collectionarea_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}



}
