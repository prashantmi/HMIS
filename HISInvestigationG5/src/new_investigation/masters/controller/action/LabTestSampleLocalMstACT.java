/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST SAMPLE LOCAL MASTER
 ## Purpose						        : This master is used for mapping the Sample to labs and test locally i.e. on hospital code 101
 ## Date of Creation					:23-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.masters.controller.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.LabTestSampleGlobalMstFB;
import new_investigation.masters.controller.utl.LabTestSampleGlobalMstUTIL;
import new_investigation.masters.controller.utl.LabTestSampleLocalMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

public class LabTestSampleLocalMstACT extends GenericController {
	
	
	String target = null;

	public LabTestSampleLocalMstACT()
	{

		super(new LabTestSampleLocalMstUTIL(),"/masters/LabTestSampleLocalMstACT");

	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestSampleGlobalMstFB fb = (LabTestSampleGlobalMstFB) form;		
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LabTestSampleLocalMstUTIL.fetchLabTestSampleEssential(fb,request);
	
		return mapping.findForward("ADD");
	}
	
	public ActionForward POPULATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestSampleGlobalMstFB fb = (LabTestSampleGlobalMstFB) form;		
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LabTestSampleLocalMstUTIL.fetchGlobalData(fb,request);
		fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}
	
	public ActionForward AJX_G_UOM(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		System.out.println("DeskHeaderACTION.AJX_G_UOM()");
		String containerCode = objRequest_p.getParameter("StrContainerCode");
		System.out.println("unitCodeWithDiagCodeType :"+containerCode);
		LabTestSampleGlobalMstFB objFB = (LabTestSampleGlobalMstFB) objForm_p;
		StringBuffer strBuff = LabTestSampleGlobalMstUTIL.getUMO(objFB, objRequest_p, containerCode);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		System.out.println("JSON"+strBuff.toString());
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestSampleGlobalMstFB fb = (LabTestSampleGlobalMstFB) form;
	    boolean hasFlag=  LabTestSampleLocalMstUTIL.saveLabTestSampleGlobal(fb, request);
	     if(hasFlag){
	    	 fb.reset(mapping, request);
	     }
	     LabTestSampleLocalMstUTIL.fetchLabTestSampleEssential(fb, request);
	     fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestSampleGlobalMstFB fb = (LabTestSampleGlobalMstFB) form;		
		WebUTIL.refreshTransState(request);
		
		LabTestSampleLocalMstUTIL.fetchModifyLabTestGlobal(fb, request);
		return mapping.findForward("ADD"); 
	}
  
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestSampleGlobalMstFB fb = (LabTestSampleGlobalMstFB) form;
		boolean hasFlag= LabTestSampleLocalMstUTIL.saveModifyLabTestSample(fb, request);
		if(hasFlag){
	    	 fb.reset(mapping, request);
	    	 return this.LIST(mapping,fb, request, response);
	     }
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD");
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestSampleGlobalMstFB fb = (LabTestSampleGlobalMstFB) form;
		WebUTIL.refreshTransState(request);
		LabTestSampleLocalMstUTIL.fetchModifyLabTestGlobal(fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestSampleGlobalMstFB fb = (LabTestSampleGlobalMstFB) form;
		LabTestSampleLocalMstUTIL.reFetchCheckListMacro(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

}
