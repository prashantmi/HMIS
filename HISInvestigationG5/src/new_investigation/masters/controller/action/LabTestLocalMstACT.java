package new_investigation.masters.controller.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.fb.LabTestGlobalMstFB;
import new_investigation.masters.controller.utl.LabTestGlobalMstUTIL;
import new_investigation.masters.controller.utl.LabTestLocalMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

public class LabTestLocalMstACT extends GenericController {
	
	public LabTestLocalMstACT()
	{
		
		super(new LabTestLocalMstUTIL(),"/masters/LabTestLocalMstACT");

	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LabTestLocalMstUTIL.fetchLabTest(fb,request);
		fb.setTestDays("1111111");
		fb.setReportAvailableAfter(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION);
		fb.setTestPrintingType(InvestigationConfig.RESULTPRINTINGSTANDARDRANGE);
		fb.setIsNablAccritedTest("0");
		fb.setIsPID("0");
		return mapping.findForward("ADD");
	}
	public ActionForward POPULATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LabTestLocalMstUTIL.fetchLabTestGlobalData(fb,request);
		//fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}
	
	public ActionForward AJX_G_Test(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
			{
				System.out.println("DeskHeaderACTION.AJX_G_Test()");
				String labCode = objRequest_p.getParameter("unit");
				System.out.println("unitCodeWithDiagCodeType :"+labCode);
				LabTestGlobalMstFB objFB = (LabTestGlobalMstFB) objForm_p;
				StringBuffer strBuff = LabTestLocalMstUTIL.getTestByLabCode(objFB, objRequest_p, labCode);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				System.out.println("JSON"+strBuff.toString());
				objResponse_p.getWriter().print(strBuff.toString());
				return null;	
			}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;
	    boolean hasFlag=  LabTestLocalMstUTIL.saveLabTestLocal(fb, request);
	     if(hasFlag){
	    	 fb.reset(mapping, request);
	     }
	     LabTestLocalMstUTIL.fetchLabTest(fb, request);
	     fb.setHmode("ADD");
	     fb.setTestDays("1111111");
			fb.setReportAvailableAfter(InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION);
			fb.setTestPrintingType(InvestigationConfig.RESULTPRINTINGSTANDARDRANGE);
			fb.setIsNablAccritedTest("0");
			fb.setIsPID("0");
		return mapping.findForward("ADD");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;
		WebUTIL.refreshTransState(request);
		LabTestLocalMstUTIL.fetchModifyLabTestLocal(fb, request);
		return mapping.findForward("ADD"); 
	}
  
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;
		boolean hasFlag= LabTestLocalMstUTIL.savemodifyLabTest(fb, request);
		if(hasFlag){
	    	 fb.reset(mapping, request);
	    	 return this.LIST(mapping,fb, request, response);
	     }
		else
			fb.setHmode("MODIFY");
		return mapping.findForward("ADD");
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;
		WebUTIL.refreshTransState(request);
		LabTestLocalMstUTIL.fetchModifyLabTestLocal(fb, request);
		return mapping.findForward("ADD");
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabTestGlobalMstFB fb = (LabTestGlobalMstFB) form;
		LabTestLocalMstUTIL.reFetchCheckListMacro(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}
}
