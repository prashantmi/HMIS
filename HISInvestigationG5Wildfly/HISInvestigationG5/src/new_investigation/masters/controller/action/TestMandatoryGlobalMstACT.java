/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :TEST MANDATORY GLOBAL MASTER
 ## Purpose						        : This master is used for mapping test with mandatory globally i.e. on hospital code 100
 ## Date of Creation					:16-Mar-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.masters.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import new_investigation.masters.controller.fb.TestMandatoryMstFB;
import new_investigation.masters.controller.utl.TestMandatoryGlobalMstUTIL;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

public class TestMandatoryGlobalMstACT extends GenericController {
	

		public TestMandatoryGlobalMstACT()
		{

			super(new TestMandatoryGlobalMstUTIL(),"/masters/TestMandatoryGlobalMstACT");

		}

		public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			TestMandatoryMstFB fb = (TestMandatoryMstFB) form;
			WebUTIL.refreshTransState(request);
			ControllerUTIL.setSysdate(request);
									
			TestMandatoryGlobalMstUTIL.fetchTestMandatory(fb, request);
			
			return mapping.findForward("ADD");
		}
		
		public ActionForward GETMAND(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			TestMandatoryMstFB fb = (TestMandatoryMstFB) form;
			TestMandatoryGlobalMstUTIL.getMandatory(fb, request);
			fb.setHmode("GETMAND");
			return mapping.findForward("ADD");
		}

		public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			TestMandatoryMstFB fb = (TestMandatoryMstFB) form;
			boolean hasFlag=  TestMandatoryGlobalMstUTIL.saveTestMandatoryMst(fb, request);
			if(hasFlag){
				
				fb.reset(mapping, request);
				TestMandatoryGlobalMstUTIL.fetchTestMandatory(fb, request);
			}
			return mapping.findForward("ADD");
		}



		public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			TestMandatoryMstFB fb = (TestMandatoryMstFB) form;
			WebUTIL.refreshTransState(request);
			TestMandatoryGlobalMstUTIL.fetchCheckListLabCollectionArea(fb, request);
			fb.setHmode("MODIFY");
			return mapping.findForward("ADD");
			
		}

		public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			TestMandatoryMstFB fb = (TestMandatoryMstFB) form;
			boolean hasFlag= TestMandatoryGlobalMstUTIL.saveTestMandatoryMst(fb, request);
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
