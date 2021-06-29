/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PATHAN JOHN BASHA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :TEST Master
 ## Purpose						        : 
 ## Date of Creation					:15-Jan-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.masters.controller.action;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import new_investigation.masters.controller.fb.TestMstFB;
 
import new_investigation.masters.controller.utl.TestMstUTL;

public class TestMstACT extends GenericController
{
	
	
	
	String target = null;
    String message="";
	public TestMstACT()
	{

		super(new TestMstUTL(),"/masters/TestMstACTION");

	}
 
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMstFB fb = (TestMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		
		TestMstUTL.fetchTestD(fb, request);
		fb.setTestType("S");
		fb.setLoincTiming("Pt");
		fb.setPrintedWith("0");
		fb.setResultEntryForm("0");
		fb.setDepartmentPrintedWith("0");
		fb.setDepartmentResultEntryForm("0");
		fb.setIsreportrequiredonseperatepage("0");
		fb.setIsreportupload("0");
		fb.setIsfrontpage("0");
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMstFB fb = (TestMstFB) form;
	    boolean hasFlag=  TestMstUTL.saveTest(fb, request);
	     if(hasFlag){
	    	 fb.reset(mapping, request);
	     }
		return mapping.findForward("ADD");
	}


	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMstFB fb = (TestMstFB) form;
		WebUTIL.refreshTransState(request);
		TestMstUTL.fetchTest(fb, request);
		return mapping.findForward("ADD"); 
	}
  
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMstFB fb = (TestMstFB) form;
		boolean hasFlag= TestMstUTL.savemodifyTest(fb, request);
		if(hasFlag){
			LIST(mapping, fb, request, response);
			return mapping.findForward("LIST");
		}
		else
			fb.setHmode("MODIFY");
			return mapping.findForward("ADD"); 
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMstFB fb = (TestMstFB) form;
		WebUTIL.refreshTransState(request);
		TestMstUTL.fetchTest(fb, request);
		return mapping.findForward("ADD");
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TestMstFB fb = (TestMstFB) form;
		TestMstUTL.reFetchTest(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 


	}
}
