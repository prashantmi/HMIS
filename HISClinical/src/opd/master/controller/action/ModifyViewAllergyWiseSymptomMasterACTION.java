package opd.master.controller.action;

/**
 * @author  CDAC
 */

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
import opd.master.controller.util.ModifyViewAllergyWiseSymptomMasterUTIL;

public class ModifyViewAllergyWiseSymptomMasterACTION extends CSRFGardTokenAction
{
	
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		AllergyWiseSymptomMasterFB fb= (AllergyWiseSymptomMasterFB)form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		ModifyViewAllergyWiseSymptomMasterUTIL.getDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		AllergyWiseSymptomMasterFB fb= (AllergyWiseSymptomMasterFB)form;
		ModifyViewAllergyWiseSymptomMasterUTIL.getDetail(fb, request);
		return mapping.findForward("NEW");
	}	
	
	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		AllergyWiseSymptomMasterFB fb= (AllergyWiseSymptomMasterFB)form;
		ModifyViewAllergyWiseSymptomMasterUTIL.saveModifyDetail(fb, request);
		return mapping.findForward("NEW");
	}

	
	

	

}
