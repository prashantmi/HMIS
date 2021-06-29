
/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	CONTAINER MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	This master is used to define the Containers in Investigation Module
 ## Date of Creation					: 	18-Feb-2015
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

import new_investigation.masters.controller.fb.ContainerMstFB;
import new_investigation.masters.controller.utl.ContainerMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ContainerMstACT extends GenericController
{

	String target = null;

	public ContainerMstACT()
	{

		super(new ContainerMstUTIL(),"/masters/ContainerMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ContainerMstFB container_fb = (ContainerMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		ContainerMstUTIL.fetchContainer(container_fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ContainerMstFB container_fb = (ContainerMstFB) form;
		boolean hasFlag=  ContainerMstUTIL.saveContainer(container_fb, request);
		if(hasFlag){
			container_fb.reset(mapping, request);
		}
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ContainerMstFB container_fb = (ContainerMstFB) form;
		WebUTIL.refreshTransState(request);
		ContainerMstUTIL.fetchCheckListContainer(container_fb, request);
		container_fb.setHmode("MODIFY");

		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ContainerMstFB container_fb = (ContainerMstFB) form;
		boolean hasFlag= ContainerMstUTIL.savemodifyContainer(container_fb, request);
		if(hasFlag){
            return this.LIST(mapping, container_fb, request, response);
         }
           else
           {
       		container_fb.setHmode("MODIFY");
       		return mapping.findForward("ADD"); 
           }
		}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ContainerMstFB container_fb = (ContainerMstFB) form;
		WebUTIL.refreshTransState(request);
		ContainerMstUTIL.reFetchCheckListContainer(container_fb, request);
		container_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

}
