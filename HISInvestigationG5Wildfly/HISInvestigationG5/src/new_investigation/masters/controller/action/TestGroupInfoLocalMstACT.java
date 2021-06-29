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
import new_investigation.masters.controller.fb.TestGroupInfoLocalMstFB;
import new_investigation.masters.controller.utl.TestGroupInfoLocalMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TestGroupInfoLocalMstACT extends GenericController
{

	String target = null;

	public TestGroupInfoLocalMstACT()
	{

		super(new TestGroupInfoLocalMstUTIL(),"/masters/TestGroupInfoLocalMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoLocalMstFB testgroupinfolocal_fb = (TestGroupInfoLocalMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
								
		TestGroupInfoLocalMstUTIL.fetchTestGroupInfoLocal(testgroupinfolocal_fb, request);
		testgroupinfolocal_fb.setIsDynamicResult("0");
		testgroupinfolocal_fb.setIsDynamicTemplate("0");
		testgroupinfolocal_fb.setGlobalTemplate("1");
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETTEST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoLocalMstFB testgroupinfolocal_fb = (TestGroupInfoLocalMstFB) form;
		TestGroupInfoLocalMstUTIL.getTestLocal(testgroupinfolocal_fb, request);
		testgroupinfolocal_fb.setHmode("GETTEST");
	
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoLocalMstFB testgroupinfolocal_fb = (TestGroupInfoLocalMstFB) form;
		boolean hasFlag=  TestGroupInfoLocalMstUTIL.saveTestGroupInfoLocal(testgroupinfolocal_fb, request);
		if(hasFlag){
			testgroupinfolocal_fb.reset(mapping, request);
		}
		else{
			testgroupinfolocal_fb.setUserGroupCode("");
			testgroupinfolocal_fb.setLabCode("-1");
		}
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoLocalMstFB testgroupinfolocal_fb = (TestGroupInfoLocalMstFB) form;
		WebUTIL.refreshTransState(request);
		TestGroupInfoLocalMstUTIL.fetchCheckListTestGroupInfoLocal(testgroupinfolocal_fb, request);
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoLocalMstFB testgroupinfolocal_fb = (TestGroupInfoLocalMstFB) form;
		boolean hasFlag= TestGroupInfoLocalMstUTIL.saveTestGroupInfoLocal(testgroupinfolocal_fb, request);
		if(hasFlag){

            return this.LIST(mapping, testgroupinfolocal_fb, request, response);
         }
           else
           {
       				testgroupinfolocal_fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoLocalMstFB testgroupinfolocal_fb = (TestGroupInfoLocalMstFB) form;
		TestGroupInfoLocalMstUTIL.reFetchCheckListTestGroupInfoLocal(testgroupinfolocal_fb, request);
		testgroupinfolocal_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}



}
