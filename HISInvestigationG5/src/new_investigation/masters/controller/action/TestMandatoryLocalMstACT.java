/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :TEST MANDATORY GLOBAL MASTER
 ## Purpose						        : This master is used for mapping test with mandatory locally i.e. on hospital code 101
 ## Date of Creation					:19-Mar-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.masters.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.TestMandatoryMstFB;
import new_investigation.masters.controller.utl.TestMandatoryLocalMstUTIL;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

public class TestMandatoryLocalMstACT extends GenericController {
	
	public TestMandatoryLocalMstACT()
	{

		super(new TestMandatoryLocalMstUTIL(),"/masters/TestMandatoryLocalMstACT");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMandatoryMstFB fb = (TestMandatoryMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
								
		TestMandatoryLocalMstUTIL.fetchTestMandatory(fb, request);
		
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETMAND(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMandatoryMstFB fb = (TestMandatoryMstFB) form;
		TestMandatoryLocalMstUTIL.getMandatory(fb, request);
		fb.setHmode("GETMAND");
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMandatoryMstFB fb = (TestMandatoryMstFB) form;
		boolean hasFlag=  TestMandatoryLocalMstUTIL.saveTestMandatoryMst(fb, request);
		if(hasFlag){
			fb.reset(mapping, request);
			TestMandatoryLocalMstUTIL.fetchTestMandatory(fb, request);
		}
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMandatoryMstFB fb = (TestMandatoryMstFB) form;
		WebUTIL.refreshTransState(request);
		TestMandatoryLocalMstUTIL.fetchCheckListLabCollectionArea(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMandatoryMstFB fb = (TestMandatoryMstFB) form;
		boolean hasFlag= TestMandatoryLocalMstUTIL.saveTestMandatoryMst(fb, request);
		if(hasFlag){

            return this.LIST(mapping,fb, request, response);
         }
           else
           {
       				fb.setHmode("MODIFY");
       				return mapping.findForward("ADD");
           }
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMandatoryMstFB fb = (TestMandatoryMstFB) form;
		//TestMandatoryGlobalMstUTIL.
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

}
