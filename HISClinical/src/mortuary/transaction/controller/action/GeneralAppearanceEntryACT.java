package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.fb.GeneralAppearanceEntryFB;
import mortuary.transaction.controller.utl.GeneralAppearanceEntryUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class GeneralAppearanceEntryACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		GeneralAppearanceEntryFB fb=(GeneralAppearanceEntryFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);
		fb.resetOpinion(mapping, request);
		fb.setAddOpinionFlag(MortuaryConfig.NO);
		GeneralAppearanceEntryUTL.getEssentialForGeneralAppearance(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		GeneralAppearanceEntryFB fb=(GeneralAppearanceEntryFB)form;
		GeneralAppearanceEntryUTL.saveGeneralAppearance(fb,request);
		fb.reset(mapping, request);
		//return this.NEW(mapping, form, request, response);
		return mapping.findForward("NEW");
	}
	public ActionForward UPDATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		GeneralAppearanceEntryFB fb=(GeneralAppearanceEntryFB)form;
		GeneralAppearanceEntryUTL.updateGeneralAppearance(fb,request);
		return mapping.findForward("NEW");
	}	
	
	public ActionForward ADDROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		GeneralAppearanceEntryFB fb=(GeneralAppearanceEntryFB)form;
		GeneralAppearanceEntryUTL.addRow(fb,request);
		fb.resetOpinion(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		GeneralAppearanceEntryFB fb=(GeneralAppearanceEntryFB)form;
		GeneralAppearanceEntryUTL.deleteRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	
}
