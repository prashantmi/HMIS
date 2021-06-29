/******************************************Start of program*************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	TEST PARAMETER COMBO MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	This is used to define the Test Parameter combo values
  											for Test Parameter Master whose Element Type is combo i.e. D 
 ## Date of Creation					: 	27-Feb-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/***********************************************************************************************************/

package new_investigation.masters.controller.action;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.TestParaComboMstFB;
import new_investigation.masters.controller.utl.TestParaComboMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TestParaComboMstACT extends GenericController
{

	String target = null;

	public TestParaComboMstACT()
	{

		super(new TestParaComboMstUTIL(),"/masters/TestParaComboMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParaComboMstFB testparacombo_fb = (TestParaComboMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		TestParaComboMstUTIL.fetchTestParaCombo(testparacombo_fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParaComboMstFB testparacombo_fb = (TestParaComboMstFB) form;
		
		boolean hasFlag=  TestParaComboMstUTIL.saveTestParaCombo(testparacombo_fb, request);
		if(hasFlag){
			testparacombo_fb.reset(mapping, request);
			
			testparacombo_fb.setHmode("ADD");
            return this.ADD(mapping, testparacombo_fb, request, response);
		}
		testparacombo_fb.setTestparaValue(null);
		testparacombo_fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParaComboMstFB testparacombo_fb = (TestParaComboMstFB) form;
		WebUTIL.refreshTransState(request);
		TestParaComboMstUTIL.fetchCheckListTestParaCombo(testparacombo_fb, request);
			return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParaComboMstFB testparacombo_fb = (TestParaComboMstFB) form;
		boolean hasFlag= TestParaComboMstUTIL.savemodifyTestParaCombo(testparacombo_fb, request);
		if(hasFlag){
            return this.LIST(mapping, testparacombo_fb, request, response);
         }
           else
           {
       		
		testparacombo_fb.setHmode("MODIFY");
		return mapping.findForward("ADD");
           }
	}


	public ActionForward DISPLAYDATA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParaComboMstFB testparacombo_fb = (TestParaComboMstFB) form;
		String TestParaCode=testparacombo_fb.getTestCode();
		String paratype=testparacombo_fb.getParaType();
		
		TestParaComboMstUTIL.fetchdisplaydataTestParaCombo(testparacombo_fb, request);
		testparacombo_fb.setTestCode(TestParaCode);
		testparacombo_fb.setParaType(paratype);
		
		testparacombo_fb.setTestparaValue(null);
		testparacombo_fb.setHmode("ADD");
		
		return mapping.findForward("ADD"); 
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestParaComboMstFB testparacombo_fb = (TestParaComboMstFB) form;
		TestParaComboMstUTIL.reFetchCheckListTestParaCombo(testparacombo_fb, request);
		testparacombo_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	public ActionForward GETPARAMETER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse 

			response)
				{
					TestParaComboMstFB testparacombo_fb = (TestParaComboMstFB) form;
					TestParaComboMstUTIL.fetchParameterCombo(testparacombo_fb, request);
					testparacombo_fb.setTestparaValue(null);
					testparacombo_fb.setHmode("ADD");
					return mapping.findForward("ADD");
				}




}
