


package new_investigation.masters.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import new_investigation.masters.controller.fb.TemplateMstFB;
import new_investigation.masters.controller.fb.LabTestSampleGlobalMstFB;
import new_investigation.masters.controller.utl.TemplateMstUTIL;
import new_investigation.masters.controller.utl.LabTestSampleGlobalMstUTIL;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

public class TemplateMstACT extends GenericController {

	
	public TemplateMstACT()
	{

		super(new TemplateMstUTIL(),"/masters/TemplateMstACT");

	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TemplateMstFB fb = (TemplateMstFB) form;		
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		fb.setTemplateType("1");
		return mapping.findForward("ADD");
	}                                                                 
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TemplateMstFB fb = (TemplateMstFB) form;
	    boolean hasFlag=  TemplateMstUTIL.saveTemplate(fb, request);
	     if(hasFlag){
	    	 fb.reset(mapping, request);
	     }
	     fb.setHmode("ADD");
	     fb.setTemplateName("");
	     fb.setTemplateType("1");
		return mapping.findForward("ADD");
	}
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TemplateMstFB fb = (TemplateMstFB) form;		
		WebUTIL.refreshTransState(request);
		
		TemplateMstUTIL.fetchModifyTemplate(fb, request);
		return mapping.findForward("ADD"); 
	}
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TemplateMstFB fb = (TemplateMstFB) form;
		boolean hasFlag= TemplateMstUTIL.saveModifyTemplate(fb, request);
		if(hasFlag){
	    	 fb.reset(mapping, request);
	    	 return this.LIST(mapping,fb, request, response);
	     }
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD");
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		TemplateMstFB fb = (TemplateMstFB) form;
		TemplateMstUTIL.reFetchCheckListMacro(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}
	
}
