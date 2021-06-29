/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST GLOBAL Master
 ## Purpose						        : This master is used for mapping the test to labs globally i.e. on hospital code 100
 ## Date of Creation					:19-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/


package new_investigation.masters.controller.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.fb.LabTestGlobalMstFB;
import new_investigation.masters.controller.utl.LabTestGlobalMstUTIL;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

public class LabTestGlobalMstACT extends GenericController {
	
	
	String target = null;

	public LabTestGlobalMstACT()
	{

		super(new LabTestGlobalMstUTIL(),"/masters/LabTestGlobalMstACT");

	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;		
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		
		LabTestGlobalMstUTIL.fetchLabTest(fb,request);
		fb.setTestDays("1111111");
		fb.setReportAvailableAfter(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION);
		fb.setTestPrintingType(InvestigationConfig.RESULTPRINTINGSTANDARDRANGE);
		fb.setIsNablAccritedTest("0");
		return mapping.findForward("ADD");
	}
	public ActionForward AJX_G_Test(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
			{
				System.out.println("DeskHeaderACTION.AJX_G_Test()");
				String labCode = objRequest_p.getParameter("unit");
				System.out.println("unitCodeWithDiagCodeType :"+labCode);
				LabTestGlobalMstFB objFB = (LabTestGlobalMstFB) objForm_p;
				StringBuffer strBuff = LabTestGlobalMstUTIL.getTestByLabCode(objFB, objRequest_p, labCode);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				System.out.println("JSON"+strBuff.toString());
				objResponse_p.getWriter().print(strBuff.toString());
				return null;	
			}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;
	    boolean hasFlag=  LabTestGlobalMstUTIL.saveLabTestGlobal(fb, request);
	     if(hasFlag){
	    	 fb.reset(mapping, request);
	     }
	     LabTestGlobalMstUTIL.fetchLabTest(fb, request);
	     fb.setHmode("ADD");
	     fb.setTestDays("1111111");
			fb.setReportAvailableAfter(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION);
			fb.setTestPrintingType(InvestigationConfig.RESULTPRINTINGSTANDARDRANGE);
			fb.setIsNablAccritedTest("0");
		return mapping.findForward("ADD");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;		
		WebUTIL.refreshTransState(request);
		LabTestGlobalMstUTIL.fetchModifyLabTestGlobal(fb, request);

		return mapping.findForward("ADD"); 
	}
  
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;
		boolean hasFlag= LabTestGlobalMstUTIL.savemodifyLabTest(fb, request);
		if(hasFlag){
	    	 fb.reset(mapping, request);
	    	 return this.LIST(mapping,fb, request, response);
	     }
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD");
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;
		WebUTIL.refreshTransState(request);
		LabTestGlobalMstUTIL.fetchModifyLabTestGlobal(fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;
		LabTestGlobalMstUTIL.reFetchCheckListMacro(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}
	
}


