package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.master.controller.fb.ModAllergyTypeFB;
import opd.master.controller.util.ModAllergyTypeUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ModAllergyTypeACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		generateToken(request);
		ModAllergyTypeFB fb=(ModAllergyTypeFB)form;
		WebUTIL.refreshTransState(request);
		ModAllergyTypeUTIL.getAllergySensistivity(fb, request);
		ModAllergyTypeUTIL.getAllergyType(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UPDATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		validateToken(request,response);
		ModAllergyTypeFB fb=(ModAllergyTypeFB)form;
		ModAllergyTypeUTIL.updateSave(fb,request);
		return mapping.findForward(fb.getHmode());
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		 	
		  return mapping.findForward("LIST");		 	   	
	}
	
	public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		ModAllergyTypeFB fb=(ModAllergyTypeFB)form;
		fb.reset(mapping,request);
		fb.setSourceFlag(OpdConfig.ALLERY_TYPE_SOURCE_FLAG_STATIC);
		return mapping.findForward("NEW");		 	   	
	}
}
