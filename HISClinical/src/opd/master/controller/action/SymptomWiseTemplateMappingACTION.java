package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.master.controller.fb.SymptomWiseTemplateMasterFB;
import opd.master.controller.util.ModifyViewSymptomWiseTemplateMappingMasterUTIL;
import opd.master.controller.util.SymptomWiseTemplateMappingMasterUTL;
import opd.master.controller.util.ModifyViewAllergyWiseSymptomMasterUTIL;


/**
 * @author dell
 *
 */
/**
 * @author user
 *
 */
public class SymptomWiseTemplateMappingACTION extends CSRFGardTokenAction
{
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		SymptomWiseTemplateMasterFB fb = (SymptomWiseTemplateMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		SymptomWiseTemplateMappingMasterUTL.getTemplateListForSymptom(fb, request);
		return mapping.findForward("NEW");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		SymptomWiseTemplateMasterFB fb = (SymptomWiseTemplateMasterFB) form;
		ControllerUTIL.setSysdate(request);
		SymptomWiseTemplateMappingMasterUTL.addSymptomWiseTemplateList(fb, request);
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SymptomWiseTemplateMasterFB fb= (SymptomWiseTemplateMasterFB)form;
		ModifyViewSymptomWiseTemplateMappingMasterUTIL.getDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		SymptomWiseTemplateMasterFB fb= (SymptomWiseTemplateMasterFB)form;
		ModifyViewSymptomWiseTemplateMappingMasterUTIL.saveModifyDetail(fb, request);
		return mapping.findForward("LIST");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		SymptomWiseTemplateMasterFB fb= (SymptomWiseTemplateMasterFB)form;
		ModifyViewSymptomWiseTemplateMappingMasterUTIL.getDetail(fb, request);
		return mapping.findForward("NEW");
	}	



}
