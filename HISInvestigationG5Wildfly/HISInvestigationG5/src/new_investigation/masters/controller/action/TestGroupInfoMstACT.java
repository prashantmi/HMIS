/****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	TEST GROUP INFO MASTER
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
import new_investigation.masters.controller.fb.TestGroupInfoMstFB;
import new_investigation.masters.controller.utl.TestGroupInfoMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TestGroupInfoMstACT extends GenericController
{

	String target = null;

	public TestGroupInfoMstACT()
	{

		super(new TestGroupInfoMstUTIL(),"/masters/TestGroupInfoMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoMstFB testgroupinfo_fb = (TestGroupInfoMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
								
		TestGroupInfoMstUTIL.fetchTestGroupInfo(testgroupinfo_fb, request);
		testgroupinfo_fb.setIsDynamicResult("0");
		testgroupinfo_fb.setIsDynamicTemplate("0");
		
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETTEST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoMstFB testgroupinfo_fb = (TestGroupInfoMstFB) form;
		TestGroupInfoMstUTIL.getTest(testgroupinfo_fb, request);
		testgroupinfo_fb.setHmode("GETTEST");
	
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoMstFB testgroupinfo_fb = (TestGroupInfoMstFB) form;
		boolean hasFlag=  TestGroupInfoMstUTIL.saveTestGroupInfo(testgroupinfo_fb, request);
		if(hasFlag){
			testgroupinfo_fb.reset(mapping, request);
		}
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoMstFB testgroupinfo_fb = (TestGroupInfoMstFB) form;
		WebUTIL.refreshTransState(request);
		TestGroupInfoMstUTIL.fetchCheckListTestGroupInfo(testgroupinfo_fb, request);
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoMstFB testgroupinfo_fb = (TestGroupInfoMstFB) form;
		boolean hasFlag= TestGroupInfoMstUTIL.saveTestGroupInfo(testgroupinfo_fb, request);
		if(hasFlag){

            return this.LIST(mapping, testgroupinfo_fb, request, response);
         }
           else
           {
       				testgroupinfo_fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestGroupInfoMstFB testgroupinfo_fb = (TestGroupInfoMstFB) form;
		TestGroupInfoMstUTIL.reFetchCheckListTestGroupInfo(testgroupinfo_fb, request);
		testgroupinfo_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}



}
