/*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
 ## Name of Developer		 			:	Yogender yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:   Local Test Group Master
 ## Purpose								:	This master is used to capture the Local Test Group used for investigation Process
 ## Date of Creation					:   18-March-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/


package new_investigation.masters.controller.action;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.masterutil.GenericController;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;




import new_investigation.masters.controller.fb.MandatoryComboMstFB;
import new_investigation.masters.controller.fb.TestGroupMstFB;
import new_investigation.masters.controller.utl.LocalTestGroupMstUTIL;
import new_investigation.masters.controller.utl.MandatoryComboMstUTIL;


public class LocalTestGroupMstACT extends GenericController
{

	String target = null;

	public LocalTestGroupMstACT()
	{
									
		super(new LocalTestGroupMstUTIL(),"/masters/LocalTestGroupMstACTION");

	}



	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupMstFB testgroup_fb = (TestGroupMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LocalTestGroupMstUTIL.fetchLocalTestGroup(testgroup_fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupMstFB testgroup_fb = (TestGroupMstFB) form;
		boolean hasFlag=  LocalTestGroupMstUTIL.saveLocalTestGroup(testgroup_fb, request);
		if(hasFlag){
			testgroup_fb.reset(mapping, request);
		}
		return mapping.findForward("ADD");
	}


	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupMstFB testgroup_fb = (TestGroupMstFB) form;
		WebUTIL.refreshTransState(request);
		LocalTestGroupMstUTIL.fetchCheckListLocalTestGroup(testgroup_fb, request);
		return mapping.findForward("ADD");
		
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//System.out.println("---MODIFYSAVE");
		TestGroupMstFB testgroup_fb = (TestGroupMstFB) form;
		boolean hasFlag= LocalTestGroupMstUTIL.updateLocalTestGroup(testgroup_fb, request);
		
		if(hasFlag){
             return this.LIST(mapping, testgroup_fb, request, response);
          }
            else
            {
            	testgroup_fb.setHmode("MODIFY");
        		return mapping.findForward("ADD"); 
            }
		
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupMstFB testgroup_fb = (TestGroupMstFB) form;
		WebUTIL.refreshTransState(request);
		LocalTestGroupMstUTIL.modifyLocalTestGroup(testgroup_fb, request);
		
		return mapping.findForward("ADD");
	}
	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupMstFB testgroup_fb = (TestGroupMstFB) form;
		LocalTestGroupMstUTIL.clearLocalTestGroup(testgroup_fb, request);
		testgroup_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	public ActionForward DISPLAYNAME(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupMstFB testgroup_fb = (TestGroupMstFB) form;
		
		LocalTestGroupMstUTIL.fetchdisplaydataLocalTestGroup(testgroup_fb, request);
		
		testgroup_fb.setHmode("ADD");
		return mapping.findForward("ADD"); 
	}
	
}
