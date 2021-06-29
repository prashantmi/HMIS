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

import opd.master.controller.fb.AllergyWiseSymptomMasterFB;
import opd.master.controller.util.AddAllergyWiseSymptomMasterUTIL;
import opd.master.controller.util.ModifyViewAllergyWiseSymptomMasterUTIL;


/**
 * @author dell
 *
 */
/**
 * @author user
 *
 */
public class AddAllergyWiseSymptomMasterACTION extends CSRFGardTokenAction
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
		AllergyWiseSymptomMasterFB fb = (AllergyWiseSymptomMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		AddAllergyWiseSymptomMasterUTIL.getAllergyTypeNotInAllergyWiseSymptom(fb, request);
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
		AllergyWiseSymptomMasterFB fb = (AllergyWiseSymptomMasterFB) form;
		ControllerUTIL.setSysdate(request);
		AddAllergyWiseSymptomMasterUTIL.addAllergyTypeAgainstSymptom(fb, request);
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AllergyWiseSymptomMasterFB fb= (AllergyWiseSymptomMasterFB)form;
		ModifyViewAllergyWiseSymptomMasterUTIL.getDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		AllergyWiseSymptomMasterFB fb= (AllergyWiseSymptomMasterFB)form;
		ModifyViewAllergyWiseSymptomMasterUTIL.saveModifyDetail(fb, request);
		return mapping.findForward("LIST");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		AllergyWiseSymptomMasterFB fb= (AllergyWiseSymptomMasterFB)form;
		ModifyViewAllergyWiseSymptomMasterUTIL.getDetail(fb, request);
		return mapping.findForward("NEW");
	}	



}
